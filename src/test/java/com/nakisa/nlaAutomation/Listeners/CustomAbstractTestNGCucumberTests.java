package com.nakisa.nlaAutomation.Listeners;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nakisa.nlaAutomation.utils.CommonExcelValidation;
import io.cucumber.core.options.CucumberPropertiesProvider;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.apiguardian.api.API;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Abstract TestNG Cucumber Test
 * <p>
 * Runs each cucumber scenario found in the features as separated test.
 *
 * @see TestNGCucumberRunner
 */
@API(status = API.Status.STABLE)
public abstract class CustomAbstractTestNGCucumberTests {

  private TestNGCucumberRunner testNGCucumberRunner;
  public static String csvPath = null;
  public static String csvFileName = null;
  public static String retryPath = null;
  private static final String GITLAB_URL = "https://gitlab.com/api/v4";
  private static final String PROJECT_PATH = "nakisainc/core/core-services"; // or numeric project ID


  @BeforeClass(alwaysRun = true)
  public void setUpClass(ITestContext context) {
    XmlTest currentXmlTest = context.getCurrentXmlTest();
    CucumberPropertiesProvider properties = currentXmlTest::getParameter;
    testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    String path = CommonExcelValidation.lastModifiedFile("Automation-Results");
    CommonExcelValidation.createCSV(path);
    //        File resultFolder = new File("nfs-csv");
    //        if(!resultFolder.exists()){
    //            resultFolder.mkdirs();
    //        }else{
    //            CommonExcelValidation.deleteFiles(new File("nfs-csv"));
    //        }
  }

  @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios", retryAnalyzer = com.nakisa.nlaAutomation.Listeners.TestRetryAnalyzer.class)
  public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
    // the 'featureWrapper' parameter solely exists to display the feature
    // file in a test report
    testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
  }

  /**
   * Returns two dimensional array of {@link PickleWrapper}s with their associated {@link FeatureWrapper}s.
   *
   * @return a two dimensional array of scenarios features.
   */
  @DataProvider
  public Object[][] scenarios() {
    if (testNGCucumberRunner == null) {
      return new Object[0][0];
    }
    return testNGCucumberRunner.provideScenarios();
  }

  @AfterClass(alwaysRun = true)
  public void tearDownClass() {
    if (testNGCucumberRunner == null) {
      return;
    }
    testNGCucumberRunner.finish();
  }
//  @AfterSuite
//  public void tearDownSuite(ITestContext context){
//    try {
//      pushCsvFolderToGitLab();
//    } catch (IOException | InterruptedException e) {
//      throw new RuntimeException(e);
//    }
//  }

  public static void pushCsvFolderToGitLab() throws IOException, InterruptedException {
    Path sourcePath = Paths.get(csvPath);
    // Specify the destination file path
    Path destinationPath = Paths.get("nfs-csv" + csvFileName);

    try {
      // Copy the file
      Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
      System.out.println("CSV file copied successfully!");
    } catch (IOException e) {
      System.err.println("An error occurred while copying the file: " + e.getMessage());
    }

    Properties properties = new Properties();
    try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\secrets.properties")) {
      properties.load(fis); // Load properties from file
    } catch (IOException e) {
      System.err.println("Error loading secrets file: " + e.getMessage());
      e.printStackTrace();
    }
    String branch = properties.getProperty("branch");
    String accessToken = properties.getProperty("accessToken");
    String confluenceBaseUrl = properties.getProperty("confluenceBaseUrl");
    String baseCsvPath = properties.getProperty("baseCsvPath");

    String dirPath = "engops/nfs/nfs-csv";
    String encodedProjectPath = URLEncoder.encode(PROJECT_PATH, StandardCharsets.UTF_8);  // becomes nakisainc%2Fcore%2Fcore-services
    String encodedBranch = URLEncoder.encode(branch, StandardCharsets.UTF_8);
    String encodedPath = URLEncoder.encode(dirPath, StandardCharsets.UTF_8);
    HttpClient client = HttpClient.newHttpClient();
    ObjectMapper mapper = new ObjectMapper();

    // Step 1: Get remote files in nfs-csv/
    HttpRequest listReq = HttpRequest.newBuilder()
        .uri(URI.create(GITLAB_URL + "/projects/" + encodedProjectPath +
            "/repository/tree?path=" + encodedPath + "&ref=" + encodedBranch + "&recursive=true"))
        .header("PRIVATE-TOKEN", accessToken)
        .GET()
        .build();


    HttpResponse<String> listResp = client.send(listReq, HttpResponse.BodyHandlers.ofString());
    Set<String> remoteFiles = new HashSet<>();
    if (listResp.statusCode() == 200) {
      ArrayNode remoteArray = (ArrayNode) mapper.readTree(listResp.body());
      for (JsonNode file : remoteArray) {
        if ("blob".equals(file.get("type").asText())) {
          remoteFiles.add(file.get("path").asText());
        }
      }
    }

    // Step 2: Build commit actions
    ArrayNode actions = mapper.createArrayNode();

    // Local files
    Path localDir = Paths.get("nfs-csv");
    Set<String> localFiles = new HashSet<>();
    if (!Files.exists(localDir) || !Files.isDirectory(localDir)) {
      System.err.println("Local folder does not exist: " + localDir.toAbsolutePath());
      return;
    }
    try (Stream<Path> walk = Files.walk(localDir)) {
      walk.filter(Files::isRegularFile)
          .forEach(path -> {
            try {
              String relPath = dirPath + "/" + localDir.relativize(path).toString().replace("\\", "/");
              localFiles.add(relPath);

              String content = Files.readString(path);
              ObjectNode action = mapper.createObjectNode();
              action.put("action", remoteFiles.contains(relPath) ? "update" : "create");
              action.put("file_path", relPath);
              action.put("content", content);
              actions.add(action);
            } catch (IOException e) {
              System.err.println("Failed to process file: " + path);
              e.printStackTrace();
            }
          });
    } catch (IOException e) {
      System.err.println("Error walking through directory: " + localDir);
      e.printStackTrace();
    }


    // Deleted files
    for (String remote : remoteFiles) {
      if (!localFiles.contains(remote)) {
        ObjectNode action = mapper.createObjectNode();
        action.put("action", "delete");
        action.put("file_path", remote);
        actions.add(action);
      }
    }

    if (actions.isEmpty()) {
      System.out.println("No changes to commit in 'nfs-csv/'.");
      return;
    }

    // Step 3: Make a commit
    ObjectNode commitPayload = mapper.createObjectNode();
    commitPayload.put("branch", branch);
    commitPayload.put("commit_message", "Automation: CSV results Updated in gitlab");
    commitPayload.set("actions", actions);

    HttpRequest commitReq = HttpRequest.newBuilder()
        .uri(URI.create(GITLAB_URL + "/projects/" + encode(PROJECT_PATH) + "/repository/commits"))
        .header("PRIVATE-TOKEN", accessToken)
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(commitPayload.toString()))
        .build();

    HttpResponse<String> commitResp = client.send(commitReq, HttpResponse.BodyHandlers.ofString());
    if (commitResp.statusCode() == 201) {
      System.out.println("✅ Commit successful to branch: " + branch);
    } else {
      System.err.println("Commit failed: " + commitResp.body());
    }
    if (commitResp.statusCode() == 201) {
      System.out.println("✅ Commit successful to branch: " + branch);
      try {
        triggerConfluenceUpdate(dirPath, branch, confluenceBaseUrl, baseCsvPath);  // Call after commit
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    } else {
      System.err.println("Commit failed: " + commitResp.body());
    }

  }
  public static void triggerConfluenceUpdate(String csvPath, String branch, String confluenceBaseUrl, String baseCsvPath) throws Exception {
    // Encode GitLab URL for safe query passing
    String gitLabUrl= baseCsvPath + branch + "/" + csvPath + "?ref_type=heads";;
    String encodedGitLabUrl = URLEncoder.encode(gitLabUrl, StandardCharsets.UTF_8);

    // Build dynamic API URL
    String apiUrl = confluenceBaseUrl+ "/confluence/automationConfluenceReport"
        + "?project=" + "nfs"
        + "&gitLabUrl=" + encodedGitLabUrl;

    // Allow self-signed SSL certificates
    disableSslVerification();

    // Create connection
    URL url = new URL(apiUrl);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");

    // Print response code
    System.out.println("Response Code: " + conn.getResponseCode());

    // Read and print response
    try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
      String inputLine;
      StringBuilder response = new StringBuilder();
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine).append("\n");
      }
      System.out.println("Response:\n" + response);
    }
  }

  private static String encode(String path) {
    return path.replace("/", "%2F");
  }

  private static void disableSslVerification() throws Exception {
    TrustManager[] trustAllCerts = new TrustManager[] {
        new X509TrustManager() {
          public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
          }

          public void checkClientTrusted(X509Certificate[] certs, String authType) {
          }

          public void checkServerTrusted(X509Certificate[] certs, String authType) {
          }
        }
    };
    SSLContext sc = SSLContext.getInstance("TLS");
    sc.init(null, trustAllCerts, new SecureRandom());
    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
  }

}
