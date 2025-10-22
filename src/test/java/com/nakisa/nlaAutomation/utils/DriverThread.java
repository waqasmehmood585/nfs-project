package com.nakisa.nlaAutomation.utils;

import com.nakisa.nlaAutomation.Constant;
import com.nakisa.nlaAutomation.stepDefinitions.MasterHooks;
import lombok.Getter;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.UUID;

public class DriverThread {

	@Getter
    private static final DriverThread driverThread = new DriverThread();
	private final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
//	private RemoteWebDriver  driverRemote = null;

    public void setChromeDriver() {
		String path = System.getProperty("user.dir") + "\\Automation-Results\\";
		File folder = new File(path + UUID.randomUUID());
		folder.mkdirs();
		MasterHooks.downloadedExcelFilePath.set(folder.getAbsolutePath());

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--ignore-certificate-errors");

		try {
			if (MasterHooks.configurationProperties.get().getRunEnvironment().equalsIgnoreCase("remote")) {
				options.setCapability("se:downloadsEnabled", true);
				int maxRetries = 4; // Define the maximum number of retries
				int attempt = 0;
				while (attempt < maxRetries) {
					try {
						RemoteWebDriver driverRemote = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), options, false);

						// Validate that the session is properly initialized
						if (driverRemote.getSessionId() == null) {
							throw new WebDriverException("Session ID is null after initializing RemoteWebDriver");
						}

						MasterHooks.sessionId.set(driverRemote.getSessionId());
						driver.set(driverRemote);
						if(driverThread.getDriver()!=null) {
							break;
						}
					} catch (NoSuchSessionException e) {
						System.err.println("No active session found. Retrying... Attempt " + (attempt + 1));
						attempt++;
						if (attempt == maxRetries) {
							throw new RuntimeException("Failed to initialize remote driver after multiple attempts: " + e.getMessage(), e);
						}
					}catch(Exception e){
						throw new RuntimeException("Failed to initialize remote driver: " + e.getMessage(), e);
					}
				}
			} else{
				HashMap<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("plugins.always_open_pdf_externally", true);
				prefs.put("download.default_directory", folder.getAbsolutePath());
				options.setExperimentalOption("prefs", prefs);
				if(Constant.runHeadless){
					options.addArguments("--headless");
				}
				if(!MasterHooks.configurationProperties.get().getRunAutomationOnNCPBuild()) {
					String secure="http://"+MasterHooks.configurationProperties.get().getEnvName()+"/dev/nakisa-financial-suite";
					options.addArguments("--unsafely-treat-insecure-origin-as-secure=http://"+MasterHooks.configurationProperties.get().getEnvName()+"/dev/nakisa-financial-suite," +
							"http://"+MasterHooks.configurationProperties.get().getEnvName()+"/dev/sap-posting-bot");
				}
//				options.setHeadless(Constant.runHeadless);
				options.setPageLoadStrategy(PageLoadStrategy.NONE);
//				System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
				driver.set(new ChromeDriver(options));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setEdgeDriver() {
		System.setProperty("webdriver.edge.driver", "src/test/resources/drivers/edgedriver.exe");
		EdgeOptions options = new EdgeOptions();
		driver.set(new EdgeDriver(options));
	}
	public WebDriver getDriver() {
		return driver.get();
	}

	public void removeDriver() {
		driver.remove();
	}

}
