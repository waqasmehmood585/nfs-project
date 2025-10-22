package com.nakisa.nlaAutomation.stepDefinitions;

import com.nakisa.nlaAutomation.Listeners.CustomAbstractTestNGCucumberTests;
import com.nakisa.nlaAutomation.Listeners.TestRetryAnalyzer;
import com.nakisa.nlaAutomation.configurations.LoadTheConfigurationProperties;
import com.nakisa.nlaAutomation.pageObjects.Dashboard_PageObject;
import com.nakisa.nlaAutomation.pageObjects.MasterAgreement_PageObject;
import com.nakisa.nlaAutomation.utils.CommonExcelValidation;
import com.nakisa.nlaAutomation.utils.DriverFactory;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FileUtils;
import org.apache.directory.api.util.Strings;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.SessionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class MasterHooks extends DriverFactory {

	@Autowired
	LoadTheConfigurationProperties loadTheConfigurationProperties;
	public static ThreadLocal<LoadTheConfigurationProperties> configurationProperties = new ThreadLocal<>();
	public static ThreadLocal<String> checkForValidationFailures = new ThreadLocal<>();
	public static ThreadLocal<String> searchAGID = new ThreadLocal<>();
	public static ThreadLocal<String> datetocheckPostings=new ThreadLocal<>();
	public static ThreadLocal<String> batchProfileID=new ThreadLocal<>();
	public static ThreadLocal<String> reportProfileID=new ThreadLocal<>();
	public static ThreadLocal<String> massIndexationJobID=new ThreadLocal<>();
	public static ThreadLocal<String> downloadedExcelFilePath=new ThreadLocal<>();
	public static ThreadLocal<String> searchCTID = new ThreadLocal<>();
	public static ThreadLocal<String> searchMLAID = new ThreadLocal<>();
	public static ThreadLocal<String> searchLCID = new ThreadLocal<>();
	public static ThreadLocal<String> searchLCTCID = new ThreadLocal<>();
	public static ThreadLocal<String> reportJobID = new ThreadLocal<>();
	public static ThreadLocal<String> loginFlag = new ThreadLocal<>();
	public static ThreadLocal<String> sapProfileId = new ThreadLocal<>();
	public static ThreadLocal<SessionId> sessionId = new ThreadLocal<>();
	public static ThreadLocal<String> searchUNID = new ThreadLocal<>();
	public static ThreadLocal<String> agReverted = new ThreadLocal<>();
	public static ThreadLocal<String> timeTCStart = new ThreadLocal<>();
	public static ThreadLocal<String> timeTCEnd = new ThreadLocal<>();
	public static ThreadLocal<String> durationTC = new ThreadLocal<>();
	public static ThreadLocal<String> tcCategory = new ThreadLocal<>();
	public static ThreadLocal<String> tcID = new ThreadLocal<>();
	public static ThreadLocal<String> csvPath = new ThreadLocal<>();
	public static ThreadLocal<String> retries = new ThreadLocal<>();
	public static ThreadLocal<String> customers = new ThreadLocal<>();
	public static ThreadLocal<String> fixVersion = new ThreadLocal<>();
	public static ThreadLocal<String> title = new ThreadLocal<>();
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public static ThreadLocal<String> agSplit = new ThreadLocal<>();
	public static ThreadLocal<String> CoreServiceVersion = new ThreadLocal<>();
	public static ThreadLocal<String> stackName = new ThreadLocal<>();
	public static ThreadLocal<String> retryPath = new ThreadLocal<>();
	public static ThreadLocal<List<String>> downloadedFiles = ThreadLocal.withInitial(ArrayList::new);
	public static ThreadLocal<String> massWorkflowJobID=new ThreadLocal<>();

	public static ThreadLocal<HashMap<String, String>> map = ThreadLocal.withInitial(HashMap::new);

	@Before
	public void setup() {
		log.info("Inside the before method");
		try {
			configurationProperties.set(loadTheConfigurationProperties);
			log.info("Thread id is: " + Thread.currentThread().getId());
			LocalDateTime currentDateTime = LocalDateTime.now();
			timeTCStart.set(currentDateTime.format(formatter));
			setDriver();
			setPageObjects();
			csvPath.set(CustomAbstractTestNGCucumberTests.csvPath);
			retryPath.set(CustomAbstractTestNGCucumberTests.retryPath);
//			CommonExcelValidation.createCSV("Automation-Results");  //Uncomment when run by cucumber for CSV of single
//			csvPath.set(CommonExcelValidation.getCSVFilePath("Automation-Results"));//Uncomment when run by cucumber for CSV of Single
		} catch (Exception exception) {
			log.error("Error at initialising driver/page objects: " + exception.getMessage());
		}
	}

	@After
	public void tearDownAndScreenShotOnFailure(Scenario scenario) {
		try {
			log.info("Inside the after method");
			CommonExcelValidation.endTime(scenario);
			File folder = new File(downloadedExcelFilePath.get());
			int maxCount = MasterHooks.configurationProperties.get().getReTryCount();
			// Handle case where driver is null
			if (driverThread.getDriver() == null) {
				int currentRetry = CommonExcelValidation.getRetryCount(scenario.getName());
				log.warn("Driver is null. Current retry count: " + currentRetry + ", Max retry allowed: " + maxCount);

				if (currentRetry < maxCount) {
					CommonExcelValidation.RetryEntryToExcel(scenario.getName());
					CommonExcelValidation.addResultsInCSVFile(tcID.get(), "FAILED");
					TestRetryAnalyzer.reTryCount.set(currentRetry);
					releasingAllSourcesBeforeClosingTheTestCase();
					Assert.fail("Driver was null. Retrying test case: " + scenario.getName());
				} else {
					CommonExcelValidation.addResultsInCSVFile(tcID.get(), "FAILED");
					releasingAllSourcesBeforeClosingTheTestCase();
					Assert.fail("Driver was null and max retries reached for test case: " + scenario.getName());
				}
				return; // Exit early, no further action needed
			}
			if (driverThread.getDriver() != null) {
				if (scenario.isFailed()) {
					// Write in to retry Excel file the number.
					CommonExcelValidation.RetryEntryToExcel(scenario.getName());
					if (!Strings.isEmpty(MasterHooks.checkForValidationFailures.get())) { //if UI failed and validation failed
						if (CommonExcelValidation.getRetryCount(scenario.getName()) < maxCount) { // Checking for remaining retries
							//deleting the mismatch files because scenario is failed on UI as well
							CommonExcelValidation.addResultsInCSVFile(tcID.get(), "FAILED");
							CommonExcelValidation.deleteMismatchFiles();
							TestRetryAnalyzer.reTryCount.set(CommonExcelValidation.getRetryCount(scenario.getName()));
							CommonExcelValidation.deleteFolder(folder);
							releasingAllSourcesBeforeClosingTheTestCase();
						} else { // If No retries remains, fail the Scenario
							CommonExcelValidation.addResultsInCSVFile(tcID.get(), "FAILED");
							if (!CommonExcelValidation.deleteFolderIfEmpty(folder)) {
								File renamedFolder = new File(System.getProperty("user.dir") + "\\Automation-Results\\" + MasterAgreement_PageObject.testCaseName.get() + "_Report");
								folder.renameTo(renamedFolder);
							}
							releasingAllSourcesBeforeClosingTheTestCase();
							CommonExcelValidation.setMaxRetryCountInExcel(scenario.getName(), 4);
							TestRetryAnalyzer.reTryCount.set(CommonExcelValidation.getRetryCount(scenario.getName()));
							retryPath.remove();
							Assert.fail("Test case is Failed, Mismatch File is Generated/UI failed for: " + MasterAgreement_PageObject.testCaseName.get());
						}
					} else {// UI Failed and Validation Passed
						if(MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("enterNewData")){//if failed while Writing baseline
							File baseValueExcelFolder = new File("src/test/resources/validationExcelFiles/"
									+ MasterHooks.configurationProperties.get().getWhichBaselineToUseForValidation() + "/"
									+ MasterAgreement_PageObject.testCaseName.get() + "/");
							CommonExcelValidation.deleteFolder(baseValueExcelFolder);
						}
						//Running till the max count
						TestRetryAnalyzer.reTryCount.set(CommonExcelValidation.getRetryCount(scenario.getName()));
						CommonExcelValidation.addResultsInCSVFile(tcID.get(), "FAILED");
						CommonExcelValidation.deleteFolderIfEmpty(folder);
						releasingAllSourcesBeforeClosingTheTestCase();
					}
				} else { // For Scenario Passed
					if (!Strings.isEmpty(MasterHooks.checkForValidationFailures.get())) { // UI Passed and Validation Failed
						// Fail the Scenario
						CommonExcelValidation.addResultsInCSVFile(tcID.get(), "FAILED");
						if (!CommonExcelValidation.deleteFolderIfEmpty(folder)) {
							File renamedFolder = new File(System.getProperty("user.dir") + "\\Automation-Results\\" + MasterAgreement_PageObject.testCaseName.get() + "_Report");
							folder.renameTo(renamedFolder);
						}
						CommonExcelValidation.setMaxRetryCountInExcel(scenario.getName(), maxCount+1);
						TestRetryAnalyzer.reTryCount.set(CommonExcelValidation.getRetryCount(scenario.getName()));
						releasingAllSourcesBeforeClosingTheTestCase();
						Assert.fail("Test case is Failed, Mismatch File is Generated for: " + MasterAgreement_PageObject.testCaseName.get());
					} else { //UI Passed and Validation Passed
						// DO nothing, Let the Scenario kill the waves
						if(TestRetryAnalyzer.reTryCount.get()==null){
							TestRetryAnalyzer.reTryCount.set(maxCount+1);
						}
						CommonExcelValidation.addResultsInCSVFile(tcID.get(), "PASSED");
						CommonExcelValidation.deleteFolder(folder);
						releasingAllSourcesBeforeClosingTheTestCase();
					}
				}
			}
		} catch (Exception exception) {
			log.error("Method tearDownAndScreenShotOnFailure is failed \n" + exception.getMessage());
		}
	}

	@AfterStep
	public void captureExceptionImage(Scenario scenario) throws IOException {
		if (scenario.isFailed() && driverThread.getDriver() != null) {
			try {
				File sourcePath = ((TakesScreenshot) driverThread.getDriver()).getScreenshotAs(OutputType.FILE);
				byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
				scenario.attach(fileContent, "image/png", "");
			} catch (Exception e) {
				log.warn("Failed to capture screenshot: " + e.getMessage());
			}
		}
	}

	public void releasingAllSourcesBeforeClosingTheTestCase() {
		importExport_pageObject.get().releasingClassThreads();
		aG_SchedulesPage.get().releaseAGSchedulesThread();
		searchAGID.remove();
		CoreServiceVersion.remove();
		stackName.remove();
		customers.remove();
		fixVersion.remove();
		title.remove();
		datetocheckPostings.remove();
		batchProfileID.remove();
		massIndexationJobID.remove();
		downloadedExcelFilePath.remove();
		downloadedFiles.remove();
		sessionId.remove();
		searchCTID.remove();
		searchLCID.remove();
		searchMLAID.remove();
		loginFlag.remove();
		timeTCStart.remove();
		timeTCEnd.remove();
		durationTC.remove();
		tcCategory.remove();
		tcID.remove();
		retries.remove();
		sapProfileId.remove();
		checkForValidationFailures.remove();
		try {
			if (driverThread.getDriver() != null) {
				driverThread.getDriver().manage().deleteAllCookies();
				driverThread.getDriver().quit();
				driverThread.removeDriver();
			}
		} catch (NoSuchWindowException exception) {
			log.error("Error releasing resources", exception);
			driverThread.getDriver().quit();
			driverThread.removeDriver();
		}
		csvPath.remove();
		agReverted.remove();
		massWorkflowJobID.remove();
		dashboard_pageObject.get().releasingClassThreads();
		map.remove();
		reportProfileID.remove();
	}

}
