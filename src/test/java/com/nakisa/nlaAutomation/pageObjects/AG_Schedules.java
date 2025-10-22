package com.nakisa.nlaAutomation.pageObjects;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.amazonaws.services.s3.transfer.Transfer;
import com.nakisa.nlaAutomation.validations.AG_PostingDocument_Validation;
import com.nakisa.nlaAutomation.validations.metaModel.AG_ChargePosting_MetaModel;
import com.nakisa.nlaAutomation.validations.metaModel.AG_JournalPosting_MetaModel;
import com.nakisa.nlaAutomation.validations.metaModel.AG_PostingDocument_MetaModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.DataFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nakisa.nlaAutomation.stepDefinitions.MasterHooks;

import org.apache.logging.log4j.util.Strings;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

@Slf4j
public class AG_Schedules extends Common_BasePage_PageObject {
	//	AG_PostingDocument_MetaModel agPostingDocumentMetaModel = aG_PostingDocument_MetaModel.get();
	MasterAgreement_PageObject masterAgreementPageObject = masterAgreement_PageObject.get();
	//	AG_JournalPosting_MetaModel ag_journalPosting_metaModel = new AG_JournalPosting_MetaModel();
	public static ThreadLocal<String> iasAccrualHeading = new ThreadLocal<>();
	public static ThreadLocal<String> iasDepreciationHeading = new ThreadLocal<>();
	public static ThreadLocal<String> iasPaymentHeading = new ThreadLocal<>();
	public static ThreadLocal<String> gaapAccrualHeading = new ThreadLocal<>();
	public static ThreadLocal<String> gaapDepreciationHeading = new ThreadLocal<>();
	public static ThreadLocal<String> gaapPaymentHeading = new ThreadLocal<>();
	private String dataValue = null;
	private String[][] postingValues;
	private Elements trElement = null;
	private String[][] columnHeadings;
	private String[][] postingColumnValues;
	private int counter = 0;
	private String[][] scheduleValues;
	private String[][] applicationValues;
	private String[][] excelValidationValues;
	private int rowNumber = 0;
	private int totalNumberOfRows = 0;
	private int totalNumberOfColumns = 0;
	private int copyRowIndex = 0;
	private File baseValueExcelFolder;
	private File baseValueExcelFile;
	private File misMatchExcelFile;
	private String journalSheetName = null;
	int rowSize;
	int totalSizeOfHeadings;
	int columnSize;
	private int postingNoA;
	private int postingNoB;
	private String scheduleLevels = null;
	private String documentLevels = null;
	private int chargeNumberToPost;
	private String[] indexList = null;
	public @FindBy(css = "#q-app .q-drawer #schedule-nav-expansion  .q-list .q-item:nth-child(1) .q-item__section--main") WebElement IASscheduleLinkToOpen;
	public @FindBy(css = "#q-app .q-drawer #schedule-nav-expansion  .q-list .q-item:nth-child(2) .q-item__section--main") WebElement GAAPscheduleLinkToOpen;
	public @FindBy(css = "#q-app .q-page .q-table") WebElement scheduleViewTable;
	public @FindBy(css = "#q-app .q-page #left-year-btn") WebElement schedulePreviousYear;
	public @FindBy(css = "#q-app .q-page #current-year-btn") WebElement scheduleCurrentYear;
	public @FindBy(css = "#q-app .q-page #year-range-btn") WebElement scheduleYearRange;
	public @FindBy(css = "[role='menu'] .q-list #all-year-checkbox") WebElement scheduleAllYearCheckBox;
	public @FindBy(css = "[role='menu'] .q-list #year-range-btn") WebElement submitAllYear;
	public @FindBy(css = "#q-app .q-page #right-year-btn") WebElement scheduleNextYear;
	public @FindBy(css = "#q-app .q-page .q-drawer #activation-group-selection") WebElement ActivationGroupLink;
	public @FindBy(css = ".q-dialog .q-card #cancel-btn") WebElement closeButton;
	public @FindBy(css = ".q-dialog .q-card #submit-btn") WebElement PostButton;
	public @FindBy(css = ".journal-dialog-body  .journal-tab-section div .q-mb-sm:nth-child(1) div:nth-child(1) .general-ledger-info-value") WebElement documentDate;
	public @FindBy(css = ".journal-dialog-body  .journal-tab-section div .q-mb-sm:nth-child(1) div:nth-child(2) .general-ledger-info-value") WebElement postingDate;
	public @FindBy(css = ".journal-dialog-body  .journal-tab-section div .q-mb-sm:nth-child(1) div:nth-child(3) .general-ledger-info-value") WebElement fiscalYear;
	public @FindBy(css = ".journal-dialog-body  .journal-tab-section div .q-mb-sm:nth-child(1) div:nth-child(4) .general-ledger-info-value") WebElement fiscalPeriod;
	public @FindBy(css = ".journal-dialog-body  .journal-tab-section div .q-mb-sm:nth-child(2) div:nth-child(2) .status") WebElement internalStatus;
	public @FindBy(css = ".journal-dialog-body  .journal-tab-section div .q-mb-sm:nth-child(2) div:nth-child(4) .status") WebElement externalStatus;
	public @FindBy(css = ".journal-dialog-body  .journal-tab-section div .q-mb-sm:nth-child(3) div:nth-child(1) .general-ledger-info-value") WebElement journalDate;
	public @FindBy(css = ".journal-dialog-body  .journal-tab-section div .q-mb-sm:nth-child(3) div:nth-child(2) .general-ledger-info-value") WebElement companyCode;
	//	public @FindBy(css=".journal-dialog-body  .journal-tab-section div .q-mb-sm:nth-child(1) div:nth-child(1) .general-ledger-info-value") WebElement standard;
	public @FindBy(css = ".q-page-container .q-page .q-pt-sm #context-menu-journals") WebElement JournalsButton;
	public @FindBy(css = ".q-layout .q-page #dropdown-additional-views") WebElement schedulesDropdown;
	public @FindBy(css = ".qcard-dialogue .q-pa-none #revision-selector-dialog-value") WebElement revisonName;

	public @FindBy(css = ".desktop .q-header #toggle-drawer-btn") WebElement hamburgerMenu;
	public @FindBy(css = ".q-drawer #main-menu-item-landing-pages") WebElement landingPage;
	public @FindBy(css = "#q-app .global-search .q-field input[aria-label='Search']") WebElement search;
	public @FindBy(css = "#q-app .global-search #globalSearch-searchOption-dropDownBtn") WebElement SearchDropdown;
	public @FindBy(css = "#q-app .q-layout #charge-list-step") WebElement chargeTab;
	public @FindBy(css = "#q-app .q-page #charge-postings-btn") WebElement chargeJournalsButton;
	public @FindBy(css = ".q-dialog .q-card #submit-btn") WebElement chargePostButton;
	public @FindBy(css = ".q-dialog .q-card .q-mt-sm .q-tab:nth-child(2)") WebElement GAAPTab;
	public @FindBy(css = ".q-dialog .q-card #cancel-btn") WebElement chargeCloseButton;
	public @FindBy(css = "#q-app .text-center #open-termination-btn") WebElement TerminationJournalsButton;
	public @FindBy(css = ".qcard-dialogue .q-card__section #document-date-input-input") WebElement documentDateInput;
	public @FindBy(css = ".qcard-dialogue .q-card__section #posting-date-input-input") WebElement postingDateInput;
	public @FindBy(css = "#q-app .q-layout #charge-list-step") WebElement ChargesTab;
	public @FindBy(css = "#q-app .form-input #ibr-rate-carry-over input[aria-label='Override Inception IBR (%)']") WebElement overrideIBR;
	public @FindBy(css = "#q-app .q-page #accounting-step")WebElement agACcountingTab;
	public @FindBy(css = "#q-app .q-page #revision-btn") WebElement revisionButton;

	public AG_Schedules() {
		super();
		log.info("Driver is inside this class: " + this.getClass().getSimpleName());
		PageFactory.initElements(driver, this);
	}

	public void clickOnSchedulesAndValidate(String standardName, String scheduleView, String scheduleLevel) {
		scheduleLevels = scheduleLevel;
		baseValueExcelFolder = new File("src/test/resources/validationExcelFiles/"
				+ MasterHooks.configurationProperties.get().getWhichBaselineToUseForValidation() + "/"
				+ MasterAgreement_PageObject.testCaseName.get() + "/");

		baseValueExcelFile = new File("src/test/resources/validationExcelFiles/"
				+ MasterHooks.configurationProperties.get().getWhichBaselineToUseForValidation() + "/"
				+ MasterAgreement_PageObject.testCaseName.get() + "/"
				+ MasterAgreement_PageObject.testCaseName.get() + "_" + standardName + "_Output.xlsx");

		copyRowIndex = 0;
		if (MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("validateSchedules") ||
				MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("validateAll")) {
			readValidationScheduleValues(scheduleView, scheduleLevel);
			applicationValues = new String[totalNumberOfRows][totalNumberOfColumns];
		}

		int scheduleSize = driver.findElements(By.cssSelector("#q-app .q-drawer #schedule-nav-expansion .q-list .q-focusable")).size();
		//for (int size = 0; size < scheduleSize; size++) {
		if (MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("enterNewData")) {
			createExcelFile(standardName, scheduleLevel + scheduleView);
		}
		rowNumber = 0;
		waitTillWebElementIsVisible("scheduleLinkToOpen", IASscheduleLinkToOpen);
		String standName=driver.findElement(By.cssSelector("#q-app .q-drawer #schedule-nav-expansion  .q-list .q-item:nth-child(1) .q-item__section--main")).getText();
		if(!standName.equalsIgnoreCase("IAS - Low value")) {
			waitTillWebElementIsVisible("scheduleLinkToOpen", GAAPscheduleLinkToOpen);
		}
		try {
			if(standardName.equalsIgnoreCase("IAS")) {
				waitAndClickOnElement(IASscheduleLinkToOpen);
			}else {
				waitAndClickOnElement(GAAPscheduleLinkToOpen);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		waitUntilLoadingSpinnerIsShown("nlaScheduleLoader");
		waitUntilLoadingSpinnerIsGone("nlaScheduleLoader");
		try {
			String scheduleName = getValueFromElement(".q-layout .q-page #dropdown-additional-views div.items-center");

			// Map scheduleView to dropdown values
			Map<String, String> scheduleMap = new HashMap<>();
			scheduleMap.put("Liability", "Liability Schedule");
			scheduleMap.put("AssetTransition", "Asset Transition Schedule");
			scheduleMap.put("Payment", "Payment Schedule");
			scheduleMap.put("ProvisionPayment", "Provision Payment Schedule");
			scheduleMap.put("Provision", "Provision Schedule");

			// Check if the scheduleView exists in the map
			if (scheduleMap.containsKey(scheduleView)) {
				String expectedSchedule = scheduleMap.get(scheduleView);

				if (!scheduleName.equalsIgnoreCase(expectedSchedule)) {
					clickOnDropDownAndSelectValue(expectedSchedule, schedulesDropdown, expectedSchedule);
					waitUntilLoadingSpinnerIsShown("nlaScheduleLoader");
					waitUntilLoadingSpinnerIsGone("nlaScheduleLoader");
				}
			}
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
			log.error("Error occurred while selecting schedule view", e);
		}

		try {
			if (!driver.findElements(By.cssSelector("#q-app .q-page #year-range-btn")).isEmpty()) {
				int rowSizeBefore = driver.findElements(By.cssSelector("#q-app .q-page .q-table  tbody tr")).size();
				scheduleYearRange.click();
				waitTillWebElementIsVisible("allYearCheckBox",scheduleAllYearCheckBox);
//				String dropDownValues = "[role='menu'] .q-list #all-year-checkbox";
//				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
//				wait.until(new Function<WebDriver, Boolean>() {
//					@Override
//					public Boolean apply(WebDriver driver) {
//						if (waitTillWebElementIsVisible("dropDownValue", dropDownValues)) {
//							return true;
//						} else {
//							return false;
//						}
//					}
//				});
				waitAndClickOnElement(scheduleAllYearCheckBox);
				waitAndClickOnElement(submitAllYear);
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				waitTillWebElementIsVisible("scheduleViewTable", scheduleViewTable);
				int rowSizeBeforeAfter = driver.findElements(By.cssSelector("#q-app .q-page .q-table  tbody tr")).size();
				if(rowSizeBeforeAfter==rowSizeBefore){
					Assert.fail("Schedules not showing for all years at AG");
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (!MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("notValidate")) {
			readScheduleValues(scheduleLevel, scheduleView);
		}
		if (MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("enterNewData")) {
			writeDataIntoExcel(columnHeadings, standardName, scheduleView, scheduleLevel);
			writeDataIntoExcel(scheduleValues, standardName, scheduleView, scheduleLevel);
		}

//		while (driver.findElements(By.cssSelector("#q-app .q-page #right-year-btn")).size() > 0) {
//			scheduleNextYear.click();
//			waitTillWebElementIsVisible("scheduleViewTable", scheduleViewTable);
//			readScheduleValues(scheduleLevel, scheduleView);
//			if (MasterHooks.configurationProperties.get().getWantToGenerateBaseline()) {
//				writeDataIntoExcel(scheduleValues, standardName, scheduleView, scheduleLevel);
//			}
//		}
		if (MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("validateSchedules") ||
				MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("validateAll")) {
			compareScheduleValues(standardName, applicationValues, excelValidationValues, scheduleView);
		}
		System.out.println("Done");
		counter = 0;
		//}
	}

	public void readScheduleValues(String scheduleLevel, String scheduleView) {

//		Read Schedules Values
		String pageSource = driver.getPageSource();
		Document documentValues = Jsoup.parse(pageSource);
		documentValues.getElementsByAttributeValue("class", "q-table").forEach(item -> {
			trElement = item.getElementsByTag("tr");
		});

		columnSize = trElement.get(1).select("th").size();
		int rowSize = trElement.size() - 2;

		if (counter == 0) {
			readColumnHeadings(trElement, columnSize);
		}

		scheduleValues = new String[rowSize][columnSize];
		for (int i = 2; i < trElement.size(); i++) {
			Element row = trElement.get(i);
			Elements rowItems = row.select("td");
			for (int j = 0; j < rowItems.size(); j++) {
				dataValue = rowItems.get(j).select("td div").text();
				if (dataValue.matches(".*[a-zA-Z].* .*[a-zA-Z].*")) {
					dataValue = dataValue.substring(0, dataValue.indexOf(" "));
				}
				scheduleValues[i-2][j] = dataValue;
			}
		}

		if (MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("validateSchedules") ||
				MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("validateAll")) {
			copyData(scheduleValues);
		}
	}

	public void readColumnHeadings(Elements trElement, int columnSize) {
		counter = 1;
		columnHeadings = new String[1][columnSize];
		Element row = trElement.get(1);
		Elements rowItems = row.select("th");
		for (int j = 0; j < rowItems.size(); j++) {
			dataValue = rowItems.get(j).select("th").text();
			columnHeadings[0][j] = dataValue;
		}
	}

	public ArrayList<AG_PostingDocument_Validation> readColumnHeadingsPostingValues(String scheduleLevel) {

		ArrayList<AG_PostingDocument_Validation> postingDocumentValues = new ArrayList<AG_PostingDocument_Validation>();
		AG_PostingDocument_Validation postingDocumentValue = new AG_PostingDocument_Validation();
		postingDocumentValue.setDocumentType("Document Type");
		if (scheduleLevel.equalsIgnoreCase("Journal")){
			postingDocumentValue.setJournalLevel("Journal Level");
		}
		postingDocumentValue.setDocumentDate("Document Date");
		postingDocumentValue.setPostingDate("Posting Date");
		postingDocumentValue.setFiscalYear("Fiscal Year");
		postingDocumentValue.setFiscalPeriod("Fiscal Period");
		postingDocumentValue.setInternalStatus("Internal Status");
		postingDocumentValue.setExternalStatus("External Status");
		postingDocumentValue.setJournalDate("Journal Date");
		postingDocumentValue.setCompanyCode("Company");
		postingDocumentValue.setStandard("Standard");
		postingDocumentValue.setAccountNumber("Account Number");
		postingDocumentValue.setPayment("Payment(Debit/Credit)");
		if (!(masterAgreementPageObject.getInputValues().get("Inception").get("Contract Level").get("Indexed Currency").get(0).equalsIgnoreCase("Yes"))) {
			postingDocumentValue.setAmountContract("amountCONTRACT");
			postingDocumentValue.setCurrencyContract("CurrencyCONTRACT");
		}
		postingDocumentValue.setAmountCompany("amountCOMPANY");
		postingDocumentValue.setCurrencyCompany("CurrencyCOMPANY");
		if (MasterHooks.configurationProperties.get().getParallelCurrency()) {
			postingDocumentValue.setAmountGroup("amountGroup");
			postingDocumentValue.setCurrencyGroup("CurrencyGroup");
		}
		postingDocumentValues.add(postingDocumentValue);
		return postingDocumentValues;
	}

	// Journal Posting Values
	public ArrayList<AG_PostingDocument_Validation> readPostingValues(String scheduleLevel) {

		String DocumentType = null;
		ArrayList<AG_PostingDocument_Validation> postingDocumentValues = new ArrayList<AG_PostingDocument_Validation>();
		int sizeOfAccounts = driver.findElements(By.cssSelector(".journal-dialog-body  .journal-tab-section .q-table tbody .q-tr")).size() - 2;
		for (int index = 0; index < sizeOfAccounts; index++) {
			AG_PostingDocument_Validation postingValue = new AG_PostingDocument_Validation();
			waitUnTillWebElementIsVisible("documentType",".journal-dialog-body  .q-mb-sm.journal-tabs-header .q-tab[aria-selected='true'] .q-tab__label");
			String documentType = getValueFromElement(".journal-dialog-body  .q-mb-sm.journal-tabs-header .q-tab[aria-selected='true'] .q-tab__label").replaceAll(" ","");
			waitUnTillWebElementIsVisible("Standard",".journal-dialog-body .q-mt-sm .q-tab[aria-selected='true'] .q-mr-sm");
			String Standard = getValueFromElement(".journal-dialog-body .q-mt-sm .q-tab[aria-selected='true'] .q-mr-sm");
			waitUnTillWebElementIsVisible("date",".journal-dialog-body .q-mt-sm .q-tab[aria-selected='true'] .q-mr-sm");
			String date = getValueFromElement(".journal-dialog-body  .journal-tab-section div .q-mb-sm:nth-child(1) div:nth-child(1) .general-ledger-info-value");
			boolean terminationCheck = documentType.equalsIgnoreCase("AssetTermination") || documentType.equalsIgnoreCase("LiabilityTermination") || documentType.equalsIgnoreCase("ClosedLedger");
			if(scheduleLevel.equalsIgnoreCase("Charge")){
				DocumentType = documentType.replaceAll(" ", "") + "_" + Standard.substring(0, Standard.indexOf("-") - 1) + "_" + date +"_"+chargeNumberToPost;
			}
			else if(terminationCheck){
				DocumentType = documentType.replaceAll(" ", "") + "_" + Standard.substring(0, Standard.indexOf("-") - 1);
			}
			else{
				DocumentType = documentType.replaceAll(" ", "") + "_" + Standard.substring(0, Standard.indexOf("-") - 1)+ "_" + date;
			}
			postingValue.setDocumentType(DocumentType);
			if (scheduleLevel.equalsIgnoreCase("Journal")){
				String checkElementLevel = getValueFromElement(".qcard-dialogue .q-pa-none #revision-selector-dialog-value");
				if (checkElementLevel.contains("Inception")) {
					journalSheetName = "Inception";
				} else if (checkElementLevel.contains("Mass Indexation")) {
					journalSheetName = "Mass Indexation-" + fiscalYear.getText() + "-" + fiscalPeriod.getText();
				} else {
					journalSheetName = checkElementLevel.substring(0, checkElementLevel.indexOf("-") + 3).trim();
				}
				postingValue.setJournalLevel(journalSheetName);
			}
			if(!terminationCheck) {
				postingValue.setDocumentDate(documentDate.getText());
				postingValue.setPostingDate(postingDate.getText());
				postingValue.setFiscalYear(fiscalYear.getText());
				postingValue.setFiscalPeriod(fiscalPeriod.getText());
			}else{
				postingValue.setDocumentDate("null");
				postingValue.setPostingDate("null");
				postingValue.setFiscalYear("null");
				postingValue.setFiscalPeriod("null");
			}
			postingValue.setInternalStatus(internalStatus.getText());
			postingValue.setExternalStatus(externalStatus.getText());
			postingValue.setJournalDate(journalDate.getText());
			postingValue.setCompanyCode(companyCode.getText());
			postingValue.setStandard(Standard.substring(0, Standard.indexOf("-") - 1));
			postingValue.setAccountNumber(getValueFromElement(".journal-dialog-body  .journal-tab-section .q-table .q-tr:nth-child(" + (index + 2) + ") td:nth-child(2)"));
			if (getValueFromElement(".journal-dialog-body  .journal-tab-section .q-table .q-tr:nth-child(" + (index + 2) + ") td:nth-child(6)").equalsIgnoreCase("-")) {
				postingValue.setPayment("Credit");
			} else {
				postingValue.setPayment("Debit");
			}
			int currencyposition=6;
			int amountPosition=6;
			if (!(getValuesFromExcel("Inception","Contract Level","Indexed Currency",0).equalsIgnoreCase("Yes"))) {
				String contractAmount = getValueFromElement(".journal-dialog-body  .journal-tab-section .q-table .q-tr:nth-child(" + (index + 2) + ") td:nth-child("+(amountPosition++) +") span span");
				if (contractAmount.equalsIgnoreCase("-")) {
					postingValue.setAmountContract(getValueFromElement(".journal-dialog-body  .journal-tab-section .q-table .q-tr:nth-child(" + (index + 2) + ") td:nth-child("+(amountPosition++) +") span span"));
				} else {
					postingValue.setAmountContract(contractAmount);
					amountPosition++;
				}
				postingValue.setCurrencyContract(getValueFromElement(".journal-dialog-body  .journal-tab-section .q-table .q-tr:nth-child(1) th:nth-child("+ (currencyposition++) +") .items-center"));
			}
			String companyAmount = getValueFromElement(".journal-dialog-body  .journal-tab-section .q-table .q-tr:nth-child(" + (index + 2) + ") td:nth-child("+(amountPosition++) +") span span");
			if (companyAmount.equalsIgnoreCase("-")) {
				postingValue.setAmountCompany(driver.findElement(By.cssSelector(".journal-dialog-body  .journal-tab-section .q-table .q-tr:nth-child(" + (index + 2) + ") td:nth-child("+(amountPosition++) +") span span")).getText());
			} else {
				postingValue.setAmountCompany(companyAmount);
				amountPosition++;
			}
			postingValue.setCurrencyCompany(getValueFromElement(".journal-dialog-body  .journal-tab-section .q-table .q-tr:nth-child(1) th:nth-child("+ (currencyposition++) +") .items-center"));
			if (MasterHooks.configurationProperties.get().getParallelCurrency()) {
				String groupAmount = getValueFromElement(".journal-dialog-body  .journal-tab-section .q-table .q-tr:nth-child(" + (index + 2) + ") td:nth-child("+(amountPosition++) +") span span");
				if (groupAmount.equalsIgnoreCase("-")) {
					postingValue.setAmountGroup(getValueFromElement(".journal-dialog-body  .journal-tab-section .q-table .q-tr:nth-child(" + (index + 2) + ") td:nth-child("+(amountPosition++) +") span span"));
				} else {
					postingValue.setAmountGroup(groupAmount);
					amountPosition++;
				}
				postingValue.setCurrencyGroup(getValueFromElement(".journal-dialog-body  .journal-tab-section .q-table .q-tr:nth-child(1) th:nth-child("+ (currencyposition++) +") .items-center"));
			}

			postingDocumentValues.add(postingValue);
			currencyposition=0;
			amountPosition=0;
		}
		return postingDocumentValues;
	}

	public void createExcelFile(String standardName, String sheetName) {
		try {
			FileInputStream file = null;
			XSSFWorkbook excelWorkbook = null;

			if (!baseValueExcelFolder.exists()) {
				baseValueExcelFolder.mkdir();
			}

			if (!baseValueExcelFile.exists()) {
				excelWorkbook = new XSSFWorkbook();
			} else {
				file = new FileInputStream(baseValueExcelFile);
				excelWorkbook = (XSSFWorkbook) WorkbookFactory.create(file);
			}
			int index = excelWorkbook.getSheetIndex(sheetName);
			if (index == -1){
				XSSFSheet excelSheet = excelWorkbook.createSheet(sheetName);
			}
			FileOutputStream outputStream = new FileOutputStream(baseValueExcelFile);
			excelWorkbook.write(outputStream);
			outputStream.close();
			excelWorkbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeDataIntoExcel(String[][] scheduleValues, String standardName, String scheduleView, String scheduleLevel) {
		try {
			FileInputStream file = new FileInputStream(baseValueExcelFile);
			XSSFWorkbook excelWorkbook = new XSSFWorkbook(file);
			XSSFSheet excelSheet = excelWorkbook.getSheet(scheduleLevel + scheduleView);
			int columnNumber = 0;

			for (int rowIndex = 0; rowIndex < scheduleValues.length; rowIndex++) {
				Row row = excelSheet.createRow(rowNumber++);
				for (int excelColumnIndex = 0; excelColumnIndex < columnSize; excelColumnIndex++) {

					Cell cell = row.createCell(columnNumber++);
					cell.setCellValue(scheduleValues[rowIndex][excelColumnIndex]);
					excelSheet.autoSizeColumn(excelColumnIndex);
				}
				columnNumber = 0;
			}
			file.close();
			FileOutputStream outputStream = new FileOutputStream(baseValueExcelFile);
			excelWorkbook.write(outputStream);
			outputStream.close();
			excelWorkbook.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public void readValidationScheduleValues(String scheduleView, String scheduleLevel) {
		try {
			FileInputStream file = new FileInputStream(baseValueExcelFile);
			XSSFWorkbook myWorkbook = new XSSFWorkbook(file);
			XSSFSheet mySheet = myWorkbook.getSheet(scheduleLevel + scheduleView);
			totalNumberOfRows = mySheet.getLastRowNum();
			totalNumberOfColumns = mySheet.getRow(0).getLastCellNum();
			excelValidationValues = new String[totalNumberOfRows][totalNumberOfColumns];

			for (int numberOfRowCount = 1; numberOfRowCount <= totalNumberOfRows; numberOfRowCount++) {
				Row row = mySheet.getRow(numberOfRowCount);
				for (int numberOfColumnCount = 0; numberOfColumnCount < totalNumberOfColumns; numberOfColumnCount++) {
					DataFormatter df=new DataFormatter();
					Cell cell = row.getCell(numberOfColumnCount);
					String value = df.formatCellValue(cell);
					excelValidationValues[numberOfRowCount - 1][numberOfColumnCount] = value;
				}
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void compareScheduleValues(String standardName, String[][] applicationValues, String[][] validationValues, String sheetName) {
		double actualDifferenceValue = 0.0;

		int a = applicationValues.length;
		int b = validationValues.length;

		if (applicationValues.length != validationValues.length) {
			throw new IllegalArgumentException("Row dimensions of the arrays do not match.");
		}
		for (int headingCount = 0; headingCount < totalNumberOfColumns; headingCount++) {
			for (int rowCount = 0; rowCount < totalNumberOfRows; rowCount++) {
				if (validationValues[rowCount][headingCount].equals(applicationValues[rowCount][headingCount])) {
					System.out.println("Values are matching : " + validationValues[rowCount][headingCount].equals(applicationValues[rowCount][headingCount])
							+ ", Expected Result is: " + validationValues[rowCount][headingCount] + " and Actual Result is : " + applicationValues[rowCount][headingCount]);
				} else {
					if (validationValues[rowCount][headingCount].contains(".") || applicationValues[rowCount][headingCount].contains(".")) {

						if (validationValues[rowCount][headingCount].contains("%") || applicationValues[rowCount][headingCount].contains("%")) {
							applicationValues[rowCount][headingCount] = applicationValues[rowCount][headingCount].replace("%", "");
							validationValues[rowCount][headingCount] = validationValues[rowCount][headingCount].replace("%", "");
						}

						actualDifferenceValue = Math.abs(Double.parseDouble(applicationValues[rowCount][headingCount].replaceAll(",", ""))
								- Double.parseDouble(validationValues[rowCount][headingCount].replaceAll(",", "")));
						if (MasterHooks.configurationProperties.get().getAllowedDifferenceToSkip() >= actualDifferenceValue) {
							System.out.println("Values are not matching but actual difference is under the allowed difference : "
									+ validationValues[rowCount][headingCount].equals(applicationValues[rowCount][headingCount]));
							System.out.println("\t" + "Excel value is : " + validationValues[rowCount][headingCount]);
							System.out.println("\t" + "NLA value is : " + applicationValues[rowCount][headingCount]);
							System.out.println("\t" + "Allowed difference value is: " + MasterHooks.configurationProperties.get().getAllowedDifferenceToSkip()
									+ " and actual difference value is : " + actualDifferenceValue);
						} else {
							System.out.println("Values are not matching : " + validationValues[rowCount][headingCount].equals(applicationValues[rowCount][headingCount]));
							System.out.println("\t" + "Excel value is : " + validationValues[rowCount][headingCount]);
							System.out.println("\t" + "NLA value is : " + applicationValues[rowCount][headingCount]);
							System.out.println("\t" + "Allowed difference value is : " + MasterHooks.configurationProperties.get().getAllowedDifferenceToSkip()
									+ " and actual difference value is : " + actualDifferenceValue);
							int rowNumber = rowCount + 2;
							int columnNumber = headingCount + 1;
							try {
								aG_PostingDocument_MetaModel.get().writeMisMatchExcelValues(sheetName, columnHeadings[0][headingCount]+ ":" + rowNumber + ":" + columnNumber, validationValues[rowCount][headingCount], applicationValues[rowCount][headingCount],
										MasterHooks.configurationProperties.get().getAllowedDifferenceToSkip(), actualDifferenceValue, standardName, sheetName, "");

							} catch (IOException ex) {
								ex.printStackTrace();
							}
						}
					} else {
						System.out.println("Values are not matching : " + validationValues[rowCount][headingCount].equals(applicationValues[rowCount][headingCount]));
						System.out.println("\t" + "Excel value is : " + validationValues[rowCount][headingCount]);
						System.out.println("\t" + "NLA value is : " + applicationValues[rowCount][headingCount]);
						int rowNumber = rowCount + 2;
						int columnNumber = headingCount + 1;
						try {
							aG_PostingDocument_MetaModel.get().writeMisMatchExcelValues(sheetName, columnHeadings[0][headingCount]+":"+ rowNumber + ":" + columnNumber, validationValues[rowCount][headingCount], applicationValues[rowCount][headingCount],
									MasterHooks.configurationProperties.get().getAllowedDifferenceToSkip(), actualDifferenceValue, standardName, sheetName, "");

						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}
	}

	public void copyData(String[][] scheduleValues) {
		for (int rowIndex = 0; rowIndex < scheduleValues.length; rowIndex++) {
			for (int excelColumnIndex = 0; excelColumnIndex < columnSize; excelColumnIndex++) {
				applicationValues[copyRowIndex + rowIndex][excelColumnIndex] = scheduleValues[rowIndex][excelColumnIndex];
			}
		}
		copyRowIndex = copyRowIndex + scheduleValues.length;
	}


	public void moveBackToActivationGroupLevel() {
		try {
			waitAndClickOnElement(ActivationGroupLink);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clickOnJournalsToValidateJournalDocuments(String standardName, String documentLevel) {
		AG_Schedules ag_Schedules = new AG_Schedules();
		try {
			Thread.sleep(1000);
			if (documentLevel.equalsIgnoreCase("Termination")) {
				if (standardName.equalsIgnoreCase("IAS")) {
					waitAndClickOnElement(IASscheduleLinkToOpen);
				}
				else {
					waitAndClickOnElement(GAAPscheduleLinkToOpen);
				}
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				int btnGroupSize = driver.findElements(By.cssSelector("#q-app .q-btn-group .q-btn")).size();
				if (btnGroupSize == 1) {
					waitAndClickOnElement(TerminationJournalsButton);
					waitUntilLoadingSpinnerIsShown("nlaJournalSpinner");
					waitUntilLoadingSpinnerIsGone("nlaJournalSpinner");
					Thread.sleep(1000);
				}
				else{
					waitAndClickOnElement(driver.findElement(By.cssSelector("#q-app .q-page #current-year-btn")));
					waitTillWebElementIsVisible("Dropdown", ".q-menu .q-item:nth-child(1)");
					int sizeOfDrpDown=driver.findElements(By.cssSelector(".q-menu .q-item")).size();
					if(!(driver.findElements(By.cssSelector(".q-menu .q-item:nth-child(" + sizeOfDrpDown + ") .q-item--active")).size()==1)) {
						waitAndClickOnElement(driver.findElement(By.cssSelector(".q-menu .q-item:nth-child(" + sizeOfDrpDown + ")")));
						waitTillWebElementIsVisible("terminationJournalButton", TerminationJournalsButton);
					}
					waitAndClickOnElement(TerminationJournalsButton);
				}
			}
			else {
//				AG_Schedules ag_Schedules = new AG_Schedules();
				waitAndClickOnElement(JournalsButton);
			}
			waitUntilLoadingSpinnerIsShown("nlaJournalSpinner");
			waitUntilLoadingSpinnerIsGone("nlaJournalSpinner");
			Thread.sleep(1000);
			documentLevels = documentLevel;
			baseValueExcelFolder = new File("src/test/resources/validationExcelFiles/"
					+ MasterHooks.configurationProperties.get().getWhichBaselineToUseForValidation() + "/"
					+ MasterAgreement_PageObject.testCaseName.get() + "/");

			baseValueExcelFile = new File("src/test/resources/validationExcelFiles/"
					+ MasterHooks.configurationProperties.get().getWhichBaselineToUseForValidation() + "/"
					+ MasterAgreement_PageObject.testCaseName.get() + "/"
					+ MasterAgreement_PageObject.testCaseName.get() + "_" + standardName + "_Output.xlsx");

			if (!baseValueExcelFolder.exists()) {
				baseValueExcelFolder.mkdir();
			}
			if(MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("enterNewData") &&
					documentLevel.equalsIgnoreCase("Inception")){
				if(baseValueExcelFile.exists()){
					Assert.fail("File Already Exists");
				}
			}

			int sizeOfStandards = driver.findElements(By.cssSelector(".journal-dialog-body .q-mt-sm .q-tab .q-mr-sm")).size();
			for (int i =1; i<=sizeOfStandards; i++){
				Thread.sleep(200);
				String standard = driver.findElement(By.cssSelector(".journal-dialog-body .q-mt-sm .q-tab[aria-selected='true'] .q-mr-sm")).getText();
				if (standard.contains(standardName)){
					break;
				}
				driver.findElement(By.cssSelector(".journal-dialog-body .q-mt-sm .q-tab:nth-child("+ (i +1) +") .q-mr-sm")).click();
				waitUntilLoadingSpinnerIsShown("nlaJournalSpinner");
				waitUntilLoadingSpinnerIsGone("nlaJournalSpinner");
			}
//			ArrayList<AG_PostingDocument_Validation> postingDocumentValues = new ArrayList<AG_PostingDocument_Validation>();
			String excelSheetName = documentLevel+"Journal";
			if(MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("enterNewData")){
				ArrayList<AG_PostingDocument_Validation> postingDocumentValues = new ArrayList<AG_PostingDocument_Validation>();
				postingDocumentValues=ag_Schedules.readColumnHeadingsPostingValues("Journal");
				aG_JournalPosting_MetaModel.get().writeJournalHeadings(postingDocumentValues, excelSheetName, baseValueExcelFile);

				int sizeOfDocuments = driver.findElements(By.cssSelector(".journal-dialog-body .journal-tabs-header div .q-tab .q-tab__label")).size();
				for (int i = 1; i<=sizeOfDocuments; i++) {
					try {
						waitAndClickOnElement("document", ".journal-dialog-body .journal-tabs-header div .q-tab:nth-child(" + i + ") .q-tab__label");
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					String tabname=driver.findElement(By.cssSelector(".journal-dialog-body .journal-tabs-header div .q-tab:nth-child("+ i +") .q-tab__label")).getText();
					if(!tabname.equalsIgnoreCase("Currency Rounding Adjustment")){
						ArrayList<AG_PostingDocument_Validation> journalDocumentValues = new ArrayList<AG_PostingDocument_Validation>();
						journalDocumentValues=ag_Schedules.readPostingValues("Journal");
						aG_JournalPosting_MetaModel.get().writeObjectValues(journalDocumentValues, excelSheetName, baseValueExcelFile);
					}

				}
			}
			else {
				int sizeOfDocuments = driver.findElements(By.cssSelector(".journal-dialog-body .journal-tabs-header div .q-tab .q-tab__label")).size();
				for (int i = 1; i<=sizeOfDocuments; i++) {
					try {
						waitAndClickOnElement("document", ".journal-dialog-body .journal-tabs-header div .q-tab:nth-child(" + i + ") .q-tab__label");
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					String tabname=driver.findElement(By.cssSelector(".journal-dialog-body .journal-tabs-header div .q-tab:nth-child("+ i +") .q-tab__label")).getText();
					if(!tabname.equalsIgnoreCase("Currency Rounding Adjustment")) {
						if(MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("validateAll")) {
							ArrayList<AG_PostingDocument_Validation> journalDocumentValues = new ArrayList<AG_PostingDocument_Validation>();
							journalDocumentValues = aG_SchedulesPage.get().readPostingValues("Journal");
							AG_JournalPosting_MetaModel agJournalDocumentMeta = aG_JournalPosting_MetaModel.get().createFromFile(baseValueExcelFile, excelSheetName);
							agJournalDocumentMeta.compareByDocumentType(journalDocumentValues, journalDocumentValues.get(1).getDocumentType(), excelSheetName, journalDocumentValues.get(1).getJournalLevel());
						}
					}
				}
			}
			waitAndClickOnElement(closeButton);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

	}

	public void searchEntityWithID(String entityLevel) {
		try {
			log.info("Going to Search "+entityLevel);
			waitAndClickOnElement(hamburgerMenu);
			waitAndClickOnElement(landingPage);
//			Thread.sleep(2000);
			waitUntilLoadingSpinnerIsShown("nlaLoadingPage");
			waitUntilLoadingSpinnerIsGone("nlaLoadingPage");
//			if(!waitUnTillWebElementIsVisible("LandingPageRecords",".q-page .main-content .search-results-container")){
//				Assert.fail("Landing Page Records are Empty");
//			}
			//Closing All the Opened Tabs in NFS Application
			if (!driver.findElements(By.cssSelector("#q-app .q-header .q-tabs .q-tab .q-btn")).isEmpty()) {
				int tabSize = driver.findElements(By.cssSelector("#q-app .q-header .q-tabs .q-tab .q-btn")).size();
				// Loop to delete each tab
				for (int i = 0; i < tabSize; i++) {
					// Re-fetch the tabs list in each iteration
					List<WebElement> tabs = driver.findElements(By.cssSelector("#q-app .q-header .q-tabs .q-tab .q-btn"));
					// Click the first tab (since the list will update after each click)
					if (!tabs.isEmpty()) {
						tabs.get(0).click();
						// Add a wait if necessary, e.g., for tab deletion animation
						//Thread.sleep(1000); // Adjust the delay if needed
					}
				}
			}
			waitTillWebElementIsVisible("globalSearch", SearchDropdown);
			waitAndClickOnElement(SearchDropdown);
			waitTillWebElementIsVisible("dropDownList", ".q-menu .q-list .q-item[role='listitem']:nth-child(1)");
			int listSize=driver.findElements(By.cssSelector(".q-menu .q-list .q-item[role='listitem']")).size();
			for(int index=1;index<=listSize;index++) {
				String entityName=driver.findElement(By.cssSelector(".q-menu .q-list .q-item[role='listitem']:nth-child("+index+") .q-item__section--main")).getText();
				if(entityName.equalsIgnoreCase(entityLevel)) {
					waitAndClickOnElement("searchLevel", ".q-menu .q-list .q-item[role='listitem']:nth-child("+index+") .q-item__section--main");
					break;
				}
			}
			String searchID=null;
			HashMap<String, String> threadMap = MasterHooks.map.get();;
			switch(entityLevel) {
				case "Master Agreement":
					searchID = MasterHooks.searchMLAID.get();
					break;
				case "Contract":
					searchID = MasterHooks.searchCTID.get();
					break;
				case "Lease Component":
					searchID = MasterHooks.searchLCID.get();
					break;
				case "Activation Group":
					searchID = MasterHooks.searchAGID.get();
					break;
				case "InterCompanyTransfer Contract":
					searchID = getRecordId("InterCompanyTransfer Contract", threadMap, false);
					break;
				case "InterCompanyTransfer Activation Group":
					searchID = getRecordId("InterCompanyTransfer Activation Group", threadMap, false);
					break;
			}
			log.info(entityLevel+"-ID : " + searchID);
			waitTillWebElementIsVisible("Search",search);
			waitAndClickOnElement(search);
			sendingValueToWebElement(entityLevel+"-ID", search, searchID);
			waitUntilLoadingSpinnerIsShown("nlaGlobalSearchSpinner");
			waitUntilLoadingSpinnerIsGone("nlaGlobalSearchSpinner");

//			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
//			wait.until(new Function<WebDriver, Boolean>() {
//				@Override
//				public Boolean apply(WebDriver driver) {
//					if (!(driver.findElements(By.cssSelector("#q-app .search-suggestions .loading-skeleton:nth-child(1)")).size()==1||
//							driver.findElements(By.cssSelector("#q-app .no-search-suggestions .circular")).size()==1)) {
//						return true;
//					}
//					return false;
//				}
//			});
//			if(driver.findElements(By.cssSelector("#q-app .no-search-suggestions")).size()==1) {
//				Assert.fail("No Data Found While Search!");
//			}
			if(!waitUnTillWebElementIsVisible("searchResult","#q-app .search-suggestions-wrapper .q-item__section--top button.q-btn")){
				waitAndClickOnElement(SearchDropdown);
				waitTillWebElementIsVisible("dropDownList", ".q-menu .q-list .q-item[role='listitem']:nth-child(1)");
				int listSizes =driver.findElements(By.cssSelector(".q-menu .q-list .q-item[role='listitem']")).size();
				for(int index=1;index<=listSizes;index++) {
					String entityName=driver.findElement(By.cssSelector(".q-menu .q-list .q-item[role='listitem']:nth-child("+index+") .q-item__section--main")).getText();
					if(entityName.equalsIgnoreCase(entityLevel)) {
						waitAndClickOnElement("searchLevel", ".q-menu .q-list .q-item[role='listitem']:nth-child("+index+") .q-item__section--main");
						break;
					}
				}
				waitUntilLoadingSpinnerIsShown("nlaGlobalSearchSpinner");
				waitUntilLoadingSpinnerIsGone("nlaGlobalSearchSpinner");
			}
			waitTillWebElementIsVisible("searchResult","#q-app .search-suggestions-wrapper .q-item__section--top button.q-btn");
			waitAndClickOnElement("searchedEntity","#q-app .search-suggestions-wrapper .q-item__section--top button.q-btn");
//			Thread.sleep(1000);
//			waitAndClickOnElement("searchedEntity", "#q-app .search-suggestions ul.standard-layout-wrapper li:nth-child(1) .layout");
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			//Moving to Active Entity
			moveToActiveEntity(entityLevel);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		log.info("Searched & Contract Selected");
	}

	public void moveToActiveEntity(String entityType){

		if(entityType.equalsIgnoreCase("Activation Group") || entityType.equalsIgnoreCase("Lease Component")) {
			waitTillWebElementIsVisible("entityLevel", revisionButton);
			String entityLevelName = driver.findElement(By.cssSelector("#q-app .q-card .q-toolbar .q-item__label .text-subtitle2")).getText();
			if (!entityLevelName.equalsIgnoreCase("Active")) {
				waitTillWebElementIsVisible("RevisionButton", revisionButton);
				WaitUntilElementIsClickable(revisionButton);
				try {
					waitAndClickOnElement(revisionButton);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				waitTillWebElementIsVisible("dropDownItems", ".q-menu .q-list .q-item:nth-child(1) .q-item__section #open-in-new-btn");
				int size = driver.findElements(By.cssSelector(".q-menu .q-list .q-item")).size();

				for (int item = 1; item <= size; item++) {
					waitTillWebElementIsVisible("EntityLevel", ".q-menu .q-list .q-item:nth-child(" + item + ") .q-item__section--main .q-item__label");
					String innerText = driver.findElement(By.cssSelector(".q-menu .q-list .q-item:nth-child(" + item + ") .q-chip__content")).getText();
					if (innerText.equalsIgnoreCase("Active")) {
						try {
							waitAndClickOnElement("revisionLevel", ".q-menu .q-list .q-item:nth-child(" + item + ")");
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
						break;
					}
				}
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
		}
	}

	public void movingToScheduleView(String standardName, String scheduleView) {
		log.info("Moving to "+standardName+" "+scheduleView);
		waitTillWebElementIsVisible("scheduleLinkToOpen", IASscheduleLinkToOpen);
		waitTillWebElementIsVisible("scheduleLinkToOpen", GAAPscheduleLinkToOpen);
		try{
			if(standardName.equalsIgnoreCase("IAS")) {
				waitAndClickOnElement(IASscheduleLinkToOpen);
			}else {
				waitAndClickOnElement(GAAPscheduleLinkToOpen);
			}
			waitUntilLoadingSpinnerIsShown("nlaScheduleLoader");
			waitUntilLoadingSpinnerIsGone("nlaScheduleLoader");
			waitTillWebElementIsVisible("scheduleDropdown", schedulesDropdown);
			WaitUntilElementIsClickable(schedulesDropdown);
			try {
				String scheduleName = getValueFromElement(".q-layout .q-page #dropdown-additional-views div.items-center");

				// Map scheduleView to dropdown values
				Map<String, String> scheduleMap = new HashMap<>();
				scheduleMap.put("Liability", "Liability Schedule");
				scheduleMap.put("AssetTransition", "Asset Transition Schedule");
				scheduleMap.put("Payment", "Payment Schedule");
				scheduleMap.put("ProvisionPayment", "Provision Payment Schedule");
				scheduleMap.put("Provision", "Provision Schedule");

				// Check if the scheduleView exists in the map
				if (scheduleMap.containsKey(scheduleView)) {
					String expectedSchedule = scheduleMap.get(scheduleView);

					if (!scheduleName.equalsIgnoreCase(expectedSchedule)) {
						clickOnDropDownAndSelectValue(expectedSchedule, schedulesDropdown, expectedSchedule);
						waitUntilLoadingSpinnerIsShown("nlaScheduleLoader");
						waitUntilLoadingSpinnerIsGone("nlaScheduleLoader");
					}
				}
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
				log.error("Error occurred while selecting schedule view", e);
			}

			if (!driver.findElements(By.cssSelector("#q-app .q-page #year-range-btn")).isEmpty()) {
				int rowSizeBefore = driver.findElements(By.cssSelector("#q-app .q-page .q-table  tbody tr")).size();
				scheduleYearRange.click();
				waitTillWebElementIsVisible("allYearCheckBox",scheduleAllYearCheckBox);
				waitAndClickOnElement(scheduleAllYearCheckBox);
				waitAndClickOnElement(submitAllYear);
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				waitTillWebElementIsVisible("scheduleViewTable", scheduleViewTable);
				int rowSizeBeforeAfter = driver.findElements(By.cssSelector("#q-app .q-page .q-table  tbody tr")).size();
				if(rowSizeBeforeAfter==rowSizeBefore){
					Assert.fail("Schedules not showing for all years at AG");
				}
			}
		}catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void scheduleViewStatusValidation(String standardName,String statusView, String scheduleView, String status) {
		//Moving to Schedule View
		movingToScheduleView(standardName, scheduleView);
		//checking the Status of Schedule View
		rowSize = driver.findElements(By.cssSelector("#q-app .q-page .q-table  tbody tr")).size();
		for (int rowNum = 1; rowNum <= rowSize; rowNum++) {
			statusValidation(standardName,statusView,scheduleView,status,rowNum);
		}
	}

	public void statusValidation(String standardName, String statusView, String scheduleView, String status, int rowNum) {
		log.info("Checking the '" + scheduleView + "' Status of " + statusView + " for " + standardName);

		String periodDateAllColumns = null;
		String statusDepreciationInternal = null;
		String statusDepreciationExternal = null;

		if(scheduleView.equalsIgnoreCase("AllColumns")){
			periodDateAllColumns = driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") td:nth-child(9)")).getText();
			statusDepreciationInternal = driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") td:nth-child(6)")).getText();
			statusDepreciationExternal = driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") td:nth-child(7)")).getText();
		}
		String paymentDate = driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") td:nth-child(6)")).getText();
		//Internal Status of All Columns (Accrual, Liability_Payment, Operating_Lease, Payment)
		String statusInternal = driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") td:nth-child(3)")).getText();
		//External Status of All Columns (Accrual, Liability_Payment, Operating_Lease, Payment)
		String statusExternal = driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") td:nth-child(4)")).getText();


		if (statusView.equalsIgnoreCase("Internal")) {
			if (scheduleView.equalsIgnoreCase("AllColumns")) {
				if (!statusInternal.equalsIgnoreCase("N/A")) {
					Assert.assertEquals(statusInternal, status,"Accrual " + statusView + " Status is not matching for this Period: " + periodDateAllColumns);
					log.info("Accrual '" + statusView + "' Status is '" + status + "' for this period: " + periodDateAllColumns);
				}
				if(driver.findElements(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") #depreciation-postings-btn")).size()==1){
					Assert.assertEquals(statusDepreciationInternal, status,"Depreciation '" + statusView + "' Status is not matching for this Period: " + periodDateAllColumns);
					log.info("Depreciation '" + statusView + "' Status is '" + status + "' for this period: " + periodDateAllColumns);
				}
			} else if (scheduleView.equalsIgnoreCase("Liability")) {
				if (!statusInternal.equalsIgnoreCase("N/A")) {
					Assert.assertEquals(statusInternal,status,"Payment '" + statusView + "' Status is not matching for this Period: " + paymentDate);
					log.info("Payment '" + statusView + "' Status is '" + status + "' for this period: " + paymentDate);
				}
			} else if (scheduleView.equalsIgnoreCase("OperatingLease")) {
				if (!statusInternal.equalsIgnoreCase("N/A")) {
					Assert.assertEquals(statusInternal,status,"Accrual " + statusView + " Status is not matching for this Period: " + paymentDate);
					log.info("Accrual '" + statusView + "' Status is '" + status + "' for this period: " + paymentDate);
				}
			} else if (scheduleView.equalsIgnoreCase("Payment")) {
				if (!statusInternal.equalsIgnoreCase("N/A")) {
					Assert.assertEquals(statusInternal,status, "Payment '" + statusView + "' Status is not matching for this Period: " + paymentDate);
					log.info("Payment '" + statusView + "' Status is '" + status + "' for this period: " + paymentDate);
				}
			}
			else if (scheduleView.equalsIgnoreCase("ProvisionSchedule")) {
				if (!statusInternal.equalsIgnoreCase("N/A")) {
					Assert.assertEquals(statusInternal, status,"Provision '" + statusView + "' Status is not matching for this Period: " + paymentDate);
					log.info("Provision '" + statusView + "' Status is '" + status + "' for this period: " + paymentDate);
				}
			}
			else if (scheduleView.equalsIgnoreCase("ProvisionPayment")) {
				if (!statusInternal.equalsIgnoreCase("N/A")) {
					Assert.assertEquals( statusInternal,status,"Provision Payment '" + statusView + "' Status is not matching for this Period: " + paymentDate);
					log.info("Provision Payment '" + statusView + "' Status is '" + status + "' for this period: " + paymentDate);
				}
			}
		} else if (statusView.equalsIgnoreCase("External")) {
			if (scheduleView.equalsIgnoreCase("AllColumns")) {
				if (!statusExternal.equalsIgnoreCase("N/A")) {
					Assert.assertEquals(statusExternal, status, "Accrual " + statusView + " Status is not matching for this Period: " + periodDateAllColumns);
					log.info("Accrual '" + statusView + "' Status is '" + status + "' for this period: " + periodDateAllColumns);
				}
				if(driver.findElements(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") #depreciation-postings-btn")).size()==1){
					Assert.assertEquals(statusDepreciationExternal, status, "Depreciation '" + statusView + "' Status is not matching for this Period: " + periodDateAllColumns);
					log.info("Depreciation '" + statusView + "' Status is '" + status + "' for this period: " + periodDateAllColumns);
				}
			} else if (scheduleView.equalsIgnoreCase("Liability")) {
				if (!statusExternal.equalsIgnoreCase("N/A")) {
					Assert.assertEquals(statusExternal,status,"Payment " + statusView + " Status is not matching for this Period: " + paymentDate);
					log.info("Payment '" + statusView + "' Status is '" + status + "' for this period: " + paymentDate);
				}
			} else if (scheduleView.equalsIgnoreCase("OperatingLease")) {
				if (!statusExternal.equalsIgnoreCase("N/A")) {
					Assert.assertEquals(statusExternal,status,"Accrual " + statusView + " Status is not matching for this Period: " + paymentDate);
					log.info("Accrual '" + statusView + "' Status is '" + status + "' for this period: " + paymentDate);
				}
			} else if (scheduleView.equalsIgnoreCase("Payment")) {
				if (!statusExternal.equalsIgnoreCase("N/A")) {
					Assert.assertEquals(statusExternal,status,"Payment '" + statusView + "' Status is not matching for this Period: " + paymentDate);
					log.info("Payment '" + statusView + "' Status is '" + status + "' for this period: " + paymentDate);
				}
			}
			else if (scheduleView.equalsIgnoreCase("ProvisionSchedule")) {
				if (!statusExternal.equalsIgnoreCase("N/A")) {
					Assert.assertEquals(statusExternal,status,"Provision '" + statusView + "' Status is not matching for this Period: " + paymentDate);
					log.info("Provision Schedule '" + statusView + "' Status is '" + status + "' for this period: " + paymentDate);
				}
			}
			else if (scheduleView.equalsIgnoreCase("ProvisionPayment")) {
				if (!statusExternal.equalsIgnoreCase("N/A")) {
					Assert.assertEquals(statusExternal,status,"Provision Payment '" + statusView + "' Status is not matching for this Period: " + paymentDate);
					log.info("Provision Payment '" + statusView + "' Status is '" + status + "' for this period: " + paymentDate);
				}
			}
		} else if (statusView.equalsIgnoreCase("Internal and External")) {
			if (scheduleView.equalsIgnoreCase("AllColumns")) {
				//Internal
				if (!statusInternal.equalsIgnoreCase("N/A")) {
					Assert.assertEquals(statusInternal,status,"Accrual Internal Status is not matching for this Period: " + periodDateAllColumns) ;
					log.info("Accrual Internal Status is '" + status + "' for this period: " + periodDateAllColumns);
				}
				if(driver.findElements(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") #depreciation-postings-btn")).size()==1){
					Assert.assertEquals(statusDepreciationInternal,status,"Depreciation Internal Status is not matching for this Period: " + periodDateAllColumns);
					log.info("Depreciation Internal Status is '" + status + "' for this period: " + periodDateAllColumns);
				}
				//External
				if (!statusExternal.equalsIgnoreCase("N/A")) {
					Assert.assertEquals(statusExternal,status,"Accrual External Status is not matching for this Period: " + periodDateAllColumns);
					log.info("Accrual External Status is '" + status + "' for this period: " + periodDateAllColumns);
				}
				if(driver.findElements(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") #depreciation-postings-btn")).size()==1){
					Assert.assertEquals(statusDepreciationExternal,status,"Depreciation External Status is not matching for this Period: " + periodDateAllColumns);
					log.info("Depreciation External Status is '" + status + "' for this period: " + periodDateAllColumns);
				}
			} else if (scheduleView.equalsIgnoreCase("Liability")) {
				if (!statusInternal.equalsIgnoreCase("N/A")) {
					Assert.assertEquals(statusInternal,status,"Payment '" + statusView + "' Payment Status is not matching for this Period: " + paymentDate);
					log.info("Payment " + statusView + " Status is '" + status + "' for this period: " + paymentDate);
				}
				if (!statusExternal.equalsIgnoreCase("N/A")) {
					Assert.assertEquals(statusExternal,status,"Payment '" + statusView + "' Payment Status is not matching for this Period: " + paymentDate);
					log.info("Payment " + statusView + " Status is '" + status + "' for this period: " + paymentDate);
				}
			} else if (scheduleView.equalsIgnoreCase("OperatingLease")) {
				// Internal
				if (!statusInternal.equalsIgnoreCase("N/A")) {
					Assert.assertEquals(statusInternal,status,"Accrual " + statusView + " Status is not matching for this Period: " + paymentDate);
					log.info("Accrual '" + statusView + "' Status is '" + status + "' for this period: " + paymentDate);
				}
				//External
				if (!statusExternal.equalsIgnoreCase("N/A")) {
					Assert.assertEquals(statusExternal,status,"Accrual " + statusView + " Status is not matching for this Period: " + paymentDate);
					log.info("Accrual '" + statusView + "' Status is '" + status + "' for this period: " + paymentDate);
				}
			} else if (scheduleView.equalsIgnoreCase("Payment")) {
				//Internal
				if (!statusInternal.equalsIgnoreCase("N/A")) {
					Assert.assertEquals(statusInternal,status,"Payment '" + statusView + "' Status is not matching for this Period: " + paymentDate);
					log.info("Payment '" + statusView + "' Status is '" + status + "' for this period: " + paymentDate);
				}
				//External
				if (!statusExternal.equalsIgnoreCase("N/A")) {
					Assert.assertEquals(statusExternal,status,"Payment '" + statusView + "' Status is not matching for this Period: " + paymentDate);
					log.info("Payment '" + statusView + "' Status is '" + status + "' for this period: " + paymentDate);
				}
			}
			else if (scheduleView.equalsIgnoreCase("ProvisionSchedule")) {
				//Internal
				if (!statusInternal.equalsIgnoreCase("N/A")) {
					Assert.assertEquals(statusInternal,status,"Provision '" + statusView + "' Status is not matching for this Period: " + paymentDate);
					log.info("Provision '" + statusView + "' Status is '" + status + "' for this period: " + paymentDate);
				}
				//External
				if (!statusExternal.equalsIgnoreCase("N/A")) {
					Assert.assertEquals(statusExternal,status,"Provision '" + statusView + "' Status is not matching for this Period: " + paymentDate);
					log.info("Provision Schedule '" + statusView + "' Status is '" + status + "' for this period: " + paymentDate);
				}
			}
			else if (scheduleView.equalsIgnoreCase("ProvisionPayment")) {
				//Internal
				if (!statusInternal.equalsIgnoreCase("N/A")) {
					Assert.assertEquals( statusInternal,status,"Provision Payment '" + statusView + "' Status is not matching for this Period: " + paymentDate);
					log.info("Provision Payment '" + statusView + "' Status is '" + status + "' for this period: " + paymentDate);
				}
				//External
				if (!statusExternal.equalsIgnoreCase("N/A")) {
					Assert.assertEquals(statusExternal,status,"Provision Payment '" + statusView + "' Status is not matching for this Period: " + paymentDate);
					log.info("Provision Payment '" + statusView + "' Status is '" + status + "' for this period: " + paymentDate);
				}
			}

		}
	}


	public void scheduleStatusOnPeriods(String standardName, String statusView, String scheduleView, String status,String fromDate, String toDate) {
		//Moving to Schedule View
		movingToScheduleView(standardName,scheduleView);
		rowSize = driver.findElements(By.cssSelector("#q-app .q-page .q-table  tbody tr")).size();
		for (int rowNum = 1; rowNum <= rowSize; rowNum++) {
			scheduleStatusValidationOnSpecificDates( standardName, statusView, scheduleView, status, fromDate, toDate, rowNum);
		}
	}

	public void scheduleStatusValidationOnSpecificDates(String standardName,  String statusView, String scheduleView, String status, String fromDate, String toDate, int rowNum){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date startDate = formatter.parse(fromDate);
			Date endDate = formatter.parse(toDate);
			if (scheduleView.equalsIgnoreCase("Liability") || scheduleView.equalsIgnoreCase("ProvisionPayment") || scheduleView.equalsIgnoreCase("Provision")) {
				String applicationPaymentDate = driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") td:nth-child(6) div")).getText();
				String Character = applicationPaymentDate.substring(0, 1);
				String applicationPaymentDateWOBar = "";
				if (Character.equalsIgnoreCase("|")) {
					applicationPaymentDateWOBar = applicationPaymentDate.substring(1, 11);
				} else {
					applicationPaymentDateWOBar = applicationPaymentDate.substring(0, 10);
				}
				Date newApplicationPaymentDate = formatter.parse(applicationPaymentDateWOBar);
				if (!newApplicationPaymentDate.before(startDate) && !newApplicationPaymentDate.after(endDate)) {
					statusValidation(standardName,statusView,scheduleView,status,rowNum);
				}
			}else{
				Date periodStartDate = formatter.parse(driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") td:nth-child(9)")).getText());
				if (!periodStartDate.before(startDate) && !periodStartDate.after(endDate)) {
					statusValidation(standardName,statusView,scheduleView,status,rowNum);
				}
			}
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public void clickOnDocumentAndCheckStatus(String standardName, String statusView, String document,  String status) {
		log.info("Checking the '" + statusView + "' Status of "+ document+ " for " + standardName);
		try {
			if(document.equalsIgnoreCase("Journals")){
				waitAndClickOnElement(JournalsButton);
				waitUntilLoadingSpinnerIsShown("nlaJournalSpinner");
				waitUntilLoadingSpinnerIsGone("nlaJournalSpinner");
				log.info("User is on the Journals");
			}
			if(document.contains("Charge")){
				waitAndClickOnElement(chargeTab);
				log.info("User is on the Charge tab");
				String chargeNumber = document.substring(7,8);
				int chargeNumberToCheck = Integer.parseInt(chargeNumber);
				waitTillWebElementIsVisible("ChargeJournalsButton", chargeJournalsButton);
				waitAndClickOnElement("chargeJournalsButton", "#q-app .q-page table tbody tr:nth-child(" + (chargeNumberToCheck+1) + ") #charge-postings-btn");
				waitUntilLoadingSpinnerIsShown("nlaJournalSpinner");
				waitUntilLoadingSpinnerIsGone("nlaJournalSpinner");
			}
			Thread.sleep(1000);
			int sizeOfStandards = driver.findElements(By.cssSelector(".journal-dialog-body .q-mt-sm .q-tab .q-mr-sm")).size();
			for (int i =1; i<=sizeOfStandards; i++){
				Thread.sleep(400);
				String standard = driver.findElement(By.cssSelector(".journal-dialog-body .q-mt-sm .q-tab[aria-selected='true'] .q-mr-sm")).getText();
				if (standard.contains(standardName)){
					break;
				}
				driver.findElement(By.cssSelector(".journal-dialog-body .q-mt-sm .q-tab:nth-child("+ (i +1) +") .q-mr-sm")).click();
				waitUntilLoadingSpinnerIsShown("nlaJournalSpinner");
				waitUntilLoadingSpinnerIsGone("nlaJournalSpinner");
			}
			int sizeOfDocuments = driver.findElements(By.cssSelector(".journal-dialog-body .journal-tabs-header div .q-tab .q-tab__label")).size();
			for (int j = 1; j <= sizeOfDocuments; j++) {
				try {
					waitAndClickOnElement("document", ".journal-dialog-body .journal-tabs-header div .q-tab:nth-child(" + j + ") .q-tab__label");
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				String documentName = driver.findElement(By.cssSelector(".journal-dialog-body .journal-tabs-header div .q-tab:nth-child(" + j + ") .q-tab__label")).getText();
				String internalStatus = driver.findElement(By.cssSelector(".q-dialog .q-card .q-px-sm div .q-mb-sm:nth-child(2) .col-3:nth-child(2) .q-badge")).getText();
				String externalStatus = driver.findElement(By.cssSelector(".q-dialog .q-card .q-px-sm div .q-mb-sm:nth-child(2) .col-3:nth-child(4) .q-badge")).getText();

				if(statusView.equalsIgnoreCase("Internal")){
					Assert.assertEquals(internalStatus,status,statusView+" Status of "+ documentName + " is not "+ status +" for " + standardName + " Standard");
					log.info(statusView+" Status of "+ document + " is "+ status + " for " + standardName + " Standard");
				} else if (statusView.equalsIgnoreCase("External")) {
					Assert.assertEquals(internalStatus,status,statusView+" Status of "+ documentName + " is not "+ status +" for " + standardName + " Standard");
					log.info(statusView+" Status of "+ document + " is "+ status + " for " + standardName + " Standard");
				} else if (statusView.equalsIgnoreCase("Internal and External")) {
					//Internal
					Assert.assertEquals(internalStatus,status,statusView+" Status of "+ documentName + " is not "+ status +" for " + standardName + " Standard");
//					log.info(statusView+" Status of "+ document + " is "+ status + " for " + standardName + " Standard");
					//External
					Assert.assertEquals(internalStatus,status,statusView+" Status of "+ documentName + " is not "+ status +" for " + standardName + " Standard");
					log.info(statusView+" Status of "+ document + " is "+ status + " for " + standardName + " Standard");
				}
			}
			waitAndClickOnElement(closeButton);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void chargePosting(String standardName, String chargeNumber, String validation) {
		try {
			log.info("Posting the Charge");
			waitAndClickOnElement(ChargesTab);
//					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
//					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			log.info("User is on the Charge tab");
			baseValueExcelFolder = new File("src/test/resources/validationExcelFiles/"
					+ MasterHooks.configurationProperties.get().getWhichBaselineToUseForValidation() + "/"
					+ MasterAgreement_PageObject.testCaseName.get() + "/");

			baseValueExcelFile = new File("src/test/resources/validationExcelFiles/"
					+ MasterHooks.configurationProperties.get().getWhichBaselineToUseForValidation() + "/"
					+ MasterAgreement_PageObject.testCaseName.get() + "/"
					+ MasterAgreement_PageObject.testCaseName.get() + "_" + standardName + "_Output.xlsx");
			// Creating/Validating the Baseline File
			if (MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("enterNewData") &&
					validation.equalsIgnoreCase("true")) {
				createExcelFile(standardName, "Charge" + standardName);
			}
			AG_Schedules ag_Schedules=new AG_Schedules();
			//Checking the Size of Charge Number to Post
			if (chargeNumber.length() <= 1) {
				System.out.println("Index length is 1");
				int numberLength = chargeNumber.length();
				indexList = chargeNumber.split("", numberLength);
			} else {
				System.out.println("Index length is more than 1");
				int numberLength = ((chargeNumber.length() / 2) + 1);
				indexList = chargeNumber.split(",", numberLength);
			}
			for (int indexCount = 0; indexCount < indexList.length; indexCount++) {
				int nthChild = Integer.parseInt(indexList[indexCount]) + 1;
				waitTillWebElementIsVisible("ChargeJournalsButton", chargeJournalsButton);
				waitAndClickOnElement("chargeJournalsButton", "#q-app .q-page table tbody tr:nth-child(" + (nthChild) + ") #charge-postings-btn");
				waitUntilLoadingSpinnerIsShown("nlaJournalSpinner");
				waitUntilLoadingSpinnerIsGone("nlaJournalSpinner");

				int standardsTabSize = driver.findElements(By.cssSelector(".journal-dialog-body .q-mt-sm .q-tab")).size();
				for (int i = 1; i <= standardsTabSize; i++) {
					Thread.sleep(200);
					String standard = driver.findElement(By.cssSelector(".journal-dialog-body .q-mt-sm .q-tab[aria-selected='true'] .q-mr-sm")).getText();
					if (standard.contains(standardName)) {
						break;
					}
					driver.findElement(By.cssSelector(".journal-dialog-body .q-mt-sm .q-tab:nth-child(" + (i + 1) + ") .q-mr-sm")).click();
					waitUntilLoadingSpinnerIsShown("nlaJournalSpinner");
					waitUntilLoadingSpinnerIsGone("nlaJournalSpinner");
				}
				if (chargePostButton.getText().equalsIgnoreCase("Post")) {
					waitAndClickOnElement(chargePostButton);
					waitUntilLoadingSpinnerIsShown("nlaJournalSpinner");
					waitUntilLoadingSpinnerIsGone("nlaJournalSpinner");
					if (validation.equalsIgnoreCase("true")) {
						ArrayList<AG_PostingDocument_Validation> chargePostingDocument = new ArrayList<AG_PostingDocument_Validation>();
						if (MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("enterNewData")) {
							//Reading & Writing Charge Document Headings for IAS
							ArrayList<AG_PostingDocument_Validation> postingDocumentHeading = new ArrayList<AG_PostingDocument_Validation>();
							postingDocumentHeading = ag_Schedules.readColumnHeadingsPostingValues("Charge");
							aG_chargePosting_MetaModel.get().writeObjectValues(postingDocumentHeading, "Charge" + standardName, baseValueExcelFile);
							//Reading & Writing Charge Document Values
							chargePostingDocument = ag_Schedules.readPostingValues("Charge");
							aG_chargePosting_MetaModel.get().writeObjectValues(chargePostingDocument, "Charge" + standardName, baseValueExcelFile);

						} else {
							//Validation Of Charge Document Values
							if(MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("validateAll")) {
								chargePostingDocument = aG_SchedulesPage.get().readPostingValues("Charge");
								AG_ChargePosting_MetaModel ag_chargePosting = aG_chargePosting_MetaModel.get().createFromFile(baseValueExcelFile, "Charge" + standardName);
								ag_chargePosting.compareByDocumentType(chargePostingDocument, chargePostingDocument.get(1).getDocumentType(), "Charge" + standardName);
							}
						}
					}
				}
				waitAndClickOnElement(closeButton);
			}

		} catch (InterruptedException e) {e.printStackTrace();}
	}

	public void ClicksOnScheduleAndPost(String standardName, String action, String paymentType, String scheduleView, String postingFrom, String postingTo) {
		movingToScheduleView(standardName, scheduleView);
		waitTillWebElementIsVisible("scheduleTable" ,"#q-app .q-page .q-table  tbody tr:nth-child(1)");
		rowSize = driver.findElements(By.cssSelector("#q-app .q-page .q-table  tbody tr")).size();
		for (int rowNum = 1; rowNum <= rowSize; rowNum++) {
			performPostingsOnSpecificDates(standardName, action, paymentType, scheduleView, postingFrom, postingTo, rowNum);
		}
	}

	public void performPostingsOnSpecificDates(String standardName, String action, String paymentType, String scheduleView, String postingFrom, String postingTo, int rowNum) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date startDate = formatter.parse(postingFrom);
			Date endDate = formatter.parse(postingTo);
			if (scheduleView.equalsIgnoreCase("Liability") || scheduleView.equalsIgnoreCase("ProvisionPayment") ||
					scheduleView.equalsIgnoreCase("Payment") || scheduleView.equalsIgnoreCase("Provision")) {
				waitTillWebElementIsVisible("scheduleTable" ,"#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") td:nth-child(6) div");
				String applicationPaymentDate = driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") td:nth-child(6) div")).getText();
				String Character = applicationPaymentDate.substring(0, 1);
				String applicationPaymentDateWOBar = "";
				if (Character.equalsIgnoreCase("|")) {
					applicationPaymentDateWOBar = applicationPaymentDate.substring(1, 11);
				} else {
					applicationPaymentDateWOBar = applicationPaymentDate.substring(0, 10);
				}
				Date newApplicationPaymentDate = formatter.parse(applicationPaymentDateWOBar);
				if (!newApplicationPaymentDate.before(startDate) && !newApplicationPaymentDate.after(endDate)) {
					postings(standardName, rowNum, scheduleView, action, paymentType);
				}
			} else {
				Date periodStartDate;
				String leaseType= getValuesFromExcel("Inception","Contract Level","Lease Type",0);
				if (leaseType.equalsIgnoreCase("Lease Low Value") || leaseType.equalsIgnoreCase("Lease Short Term")
						|| leaseType.equalsIgnoreCase("05 - Low Value Lease Contract") || leaseType.equalsIgnoreCase("04 - Short-Term Lease Contract")
						|| leaseType.equalsIgnoreCase("01 - Lease Contract (Operating/Short Term)") || leaseType.equalsIgnoreCase("Lease Contract (Operating/Short Term)")) {
					periodStartDate = formatter.parse(driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") td:nth-child(6)")).getText());
				} else {
					periodStartDate = formatter.parse(driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") td:nth-child(9)")).getText());
				}
				if (!periodStartDate.before(startDate) && !periodStartDate.after(endDate)) {
					postings(standardName, rowNum, scheduleView, action, paymentType);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void postings(String standardName, int rowNum, String scheduleView, String action, String paymentType) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			baseValueExcelFile = new File("src/test/resources/validationExcelFiles/"
					+ MasterHooks.configurationProperties.get().getWhichBaselineToUseForValidation() + "/"
					+ MasterAgreement_PageObject.testCaseName.get() + "/"
					+ MasterAgreement_PageObject.testCaseName.get() + "_" + standardName + "_Output.xlsx");
			AG_Schedules ag_Schedules = new AG_Schedules();
			if (paymentType.equalsIgnoreCase("Accrual") || paymentType.equalsIgnoreCase("Provision")) {
				boolean actionToDo;
				if(action.equalsIgnoreCase("Post")){
					actionToDo=!(driver.findElements(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") #accrual-postings-btn")).size() == 0) &&
							driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") td:nth-child(3)")).getText().contains("Open");
				}else{
					actionToDo=!(driver.findElements(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") #accrual-postings-btn")).size() == 0) &&
							driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") td:nth-child(3)")).getText().contains("Posted");
				}
				if (actionToDo) {
					waitAndClickOnElement("AccrualPosting", "#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") #accrual-postings-btn");
					waitUntilLoadingSpinnerIsShown("nlaJournalSpinner");
					waitUntilLoadingSpinnerIsGone("nlaJournalSpinner");
					if(!action.equalsIgnoreCase("Validate")) {
						waitAndClickOnElement(PostButton);
						waitUntilLoadingSpinnerIsShown("nlaJournalSpinner");
						waitUntilLoadingSpinnerIsGone("nlaJournalSpinner");
					}
					if(!action.equalsIgnoreCase("Reverse")) {
						String documentName = driver.findElement(By.cssSelector(".journal-dialog-body  .q-mb-sm.journal-tabs-header .q-tab__label")).getText();
						ArrayList<AG_PostingDocument_Validation> postingDocumentValues = new ArrayList<AG_PostingDocument_Validation>();
						if (MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("enterNewData")) {
							boolean flag;
							if (standardName.equalsIgnoreCase("IAS")) {
								flag = Strings.isEmpty(AG_Schedules.iasAccrualHeading.get());
							} else {
								flag = Strings.isEmpty(AG_Schedules.gaapAccrualHeading.get());
							}
							if (flag) {
								//Reading & Writing Accrual Document Headings
								ArrayList<AG_PostingDocument_Validation> postingDocumentHeading = new ArrayList<AG_PostingDocument_Validation>();
								postingDocumentHeading = ag_Schedules.readColumnHeadingsPostingValues("Accrual");
								if (paymentType.equalsIgnoreCase("Provision")) {
									aG_PostingDocument_MetaModel.get().writeObjectValues(postingDocumentHeading, "Provision_Posting", baseValueExcelFile);
								} else {
									aG_PostingDocument_MetaModel.get().writeObjectValues(postingDocumentHeading, "Accrual_Posting", baseValueExcelFile);
								}
								if (standardName.equalsIgnoreCase("IAS")) {
									AG_Schedules.iasAccrualHeading.set("Heading Done");
								} else {
									AG_Schedules.gaapAccrualHeading.set("Heading Done");
								}
							}
							//Reading & Writing Accrual Document Values
							if(paymentType.equalsIgnoreCase("Provision")) {
								postingDocumentValues = ag_Schedules.readPostingValues("Provision");
								aG_PostingDocument_MetaModel.get().writeObjectValues(postingDocumentValues, "Provision_Posting", baseValueExcelFile);
							}else {
								postingDocumentValues = ag_Schedules.readPostingValues("Accrual");
								aG_PostingDocument_MetaModel.get().writeObjectValues(postingDocumentValues, "Accrual_Posting", baseValueExcelFile);
							}
						} else {
							//Validation Of Accrual Document Values
							if(MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("validateAll")) {
								postingDocumentValues = aG_SchedulesPage.get().readPostingValues("Accrual");
								if (paymentType.equalsIgnoreCase("Provision")) {
									AG_PostingDocument_MetaModel ag_PostingDocument = aG_PostingDocument_MetaModel.get().createFromFile(baseValueExcelFile, "Provision_Posting");
									ag_PostingDocument.compareByDocumentType(postingDocumentValues, postingDocumentValues.get(1).getDocumentType(), "Provision_Posting");
								} else {
									AG_PostingDocument_MetaModel ag_PostingDocument = aG_PostingDocument_MetaModel.get().createFromFile(baseValueExcelFile, "Accrual_Posting");
									ag_PostingDocument.compareByDocumentType(postingDocumentValues, postingDocumentValues.get(1).getDocumentType(), "Accrual_Posting");
								}
							}
						}
					}
					waitAndClickOnElement(closeButton);
				}
			}
			if (paymentType.equalsIgnoreCase("Depreciation")) {
				boolean actionToDo;
				if(action.equalsIgnoreCase("Post")){
					actionToDo=!(driver.findElements(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") #depreciation-postings-btn")).size() == 0) &&
							driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") td:nth-child(6)")).getText().contains("Open");
				}else{
					actionToDo=!(driver.findElements(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") #depreciation-postings-btn")).size() == 0) &&
							driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") td:nth-child(6)")).getText().contains("Posted");
				}
				if (actionToDo) {
					waitAndClickOnElement("DepreciationPosting", "#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") #depreciation-postings-btn");
					waitUntilLoadingSpinnerIsShown("nlaJournalSpinner");
					waitUntilLoadingSpinnerIsGone("nlaJournalSpinner");
					if(!action.equalsIgnoreCase("Validate")) {
						waitAndClickOnElement(PostButton);
						waitUntilLoadingSpinnerIsShown("nlaJournalSpinner");
						waitUntilLoadingSpinnerIsGone("nlaJournalSpinner");
					}
					if(!action.equalsIgnoreCase("Reverse")) {
						String documentName = driver.findElement(By.cssSelector(".journal-dialog-body  .q-mb-sm.journal-tabs-header .q-tab__label")).getText();
						ArrayList<AG_PostingDocument_Validation> postingDocumentValues = new ArrayList<AG_PostingDocument_Validation>();
						if (MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("enterNewData")) {
							boolean flag;
							if (standardName.equalsIgnoreCase("IAS")) {
								flag = Strings.isEmpty(AG_Schedules.iasDepreciationHeading.get());
							} else {
								flag = Strings.isEmpty(AG_Schedules.gaapDepreciationHeading.get());
							}
							if (flag) {
								//Reading & Writing Depreciation Document Headings
								ArrayList<AG_PostingDocument_Validation> postingDocumentHeading = new ArrayList<AG_PostingDocument_Validation>();
								postingDocumentHeading = ag_Schedules.readColumnHeadingsPostingValues("Depreciation");
								aG_PostingDocument_MetaModel.get().writeObjectValues(postingDocumentHeading, "Depreciation_Posting", baseValueExcelFile);
								if (standardName.equalsIgnoreCase("IAS")) {
									AG_Schedules.iasDepreciationHeading.set("Heading Done");
								} else {
									AG_Schedules.gaapDepreciationHeading.set("Heading Done");
								}
							}
							//Reading & Writing Depreciation Document Values
							postingDocumentValues = ag_Schedules.readPostingValues("Depreciation");
							aG_PostingDocument_MetaModel.get().writeObjectValues(postingDocumentValues, "Depreciation_Posting", baseValueExcelFile);
						} else {
							//Validation Of Depreciation Document Values
							if(MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("validateAll")) {
								postingDocumentValues = aG_SchedulesPage.get().readPostingValues("Depreciation");
								AG_PostingDocument_MetaModel ag_PostingDocument = aG_PostingDocument_MetaModel.get().createFromFile(baseValueExcelFile, "Depreciation_Posting");
								ag_PostingDocument.compareByDocumentType(postingDocumentValues, postingDocumentValues.get(1).getDocumentType(), "Depreciation_Posting");
							}
						}
					}
					waitAndClickOnElement(closeButton);
				}
			}
			if (paymentType.equalsIgnoreCase("Accrual & Depreciation")) {
				boolean actionToDo;
				if(action.equalsIgnoreCase("Post")){
					actionToDo=!(driver.findElements(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") #accrual-postings-btn")).size() == 0) &&
							driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") td:nth-child(3)")).getText().contains("Open");
				}else{
					actionToDo=!(driver.findElements(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") #accrual-postings-btn")).size() == 0) &&
							driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") td:nth-child(3)")).getText().contains("Posted");
				}
				if (actionToDo) {
					waitAndClickOnElement("AccrualPosting", "#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") #accrual-postings-btn");
					waitUntilLoadingSpinnerIsShown("nlaJournalSpinner");
					waitUntilLoadingSpinnerIsGone("nlaJournalSpinner");
					if(!action.equalsIgnoreCase("Validate")) {
						waitAndClickOnElement(PostButton);
						waitUntilLoadingSpinnerIsShown("nlaJournalSpinner");
						waitUntilLoadingSpinnerIsGone("nlaJournalSpinner");
					}
					if(!action.equalsIgnoreCase("Reverse")) {
						String documentName = driver.findElement(By.cssSelector(".journal-dialog-body  .q-mb-sm.journal-tabs-header .q-tab__label")).getText();
						ArrayList<AG_PostingDocument_Validation> postingDocumentValues = new ArrayList<AG_PostingDocument_Validation>();
						if (MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("enterNewData")) {
							boolean flag;
							if (standardName.equalsIgnoreCase("IAS")) {
								flag = Strings.isEmpty(AG_Schedules.iasAccrualHeading.get());
							} else {
								flag = Strings.isEmpty(AG_Schedules.gaapAccrualHeading.get());
							}
							if (flag) {
								//Reading & Writing Accrual Document Headings
								ArrayList<AG_PostingDocument_Validation> postingDocumentHeading = new ArrayList<AG_PostingDocument_Validation>();
								postingDocumentHeading = ag_Schedules.readColumnHeadingsPostingValues("Accrual & Depreciation");
								aG_PostingDocument_MetaModel.get().writeObjectValues(postingDocumentHeading, "Accrual_Posting", baseValueExcelFile);
								if (standardName.equalsIgnoreCase("IAS")) {
									AG_Schedules.iasAccrualHeading.set("Heading Done");
								} else {
									AG_Schedules.gaapAccrualHeading.set("Heading Done");
								}
							}
							//Reading & Writing Accrual Document Values
							postingDocumentValues = ag_Schedules.readPostingValues("Accrual & Depreciation");
							aG_PostingDocument_MetaModel.get().writeObjectValues(postingDocumentValues, "Accrual_Posting", baseValueExcelFile);
						} else {
							//Validation Of Accrual Document Values
							if(MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("validateAll")) {
								postingDocumentValues = aG_SchedulesPage.get().readPostingValues("Accrual & Depreciation");
								AG_PostingDocument_MetaModel ag_PostingDocument = aG_PostingDocument_MetaModel.get().createFromFile(baseValueExcelFile, "Accrual_Posting");
								ag_PostingDocument.compareByDocumentType(postingDocumentValues, postingDocumentValues.get(1).getDocumentType(), "Accrual_Posting");
							}
						}
					}
					waitAndClickOnElement(closeButton);
				}
				boolean actionToDep;
				if(action.equalsIgnoreCase("Post")){
					actionToDep=!(driver.findElements(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") #depreciation-postings-btn")).size() == 0) &&
							driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") td:nth-child(6)")).getText().contains("Open");
				}else{
					actionToDep=!(driver.findElements(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") #depreciation-postings-btn")).size() == 0) &&
							driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") td:nth-child(6)")).getText().contains("Posted");
				}
				if (actionToDep) {
					waitAndClickOnElement("DepreciationPosting", "#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") #depreciation-postings-btn");
					waitUntilLoadingSpinnerIsShown("nlaJournalSpinner");
					waitUntilLoadingSpinnerIsGone("nlaJournalSpinner");
					if(!action.equalsIgnoreCase("Validate")) {
						waitAndClickOnElement(PostButton);
						waitUntilLoadingSpinnerIsShown("nlaJournalSpinner");
						waitUntilLoadingSpinnerIsGone("nlaJournalSpinner");
					}
					if(!action.equalsIgnoreCase("Reverse")) {
						String documentName = driver.findElement(By.cssSelector(".journal-dialog-body  .q-mb-sm.journal-tabs-header .q-tab__label")).getText();
						ArrayList<AG_PostingDocument_Validation> postingDocumentValues = new ArrayList<AG_PostingDocument_Validation>();
						if (MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("enterNewData")) {
							boolean flag;
							if (standardName.equalsIgnoreCase("IAS")) {
								flag = Strings.isEmpty(AG_Schedules.iasDepreciationHeading.get());
							} else {
								flag = Strings.isEmpty(AG_Schedules.gaapDepreciationHeading.get());
							}
							if (flag) {
								//Reading & Writing Depreciation Document Headings
								ArrayList<AG_PostingDocument_Validation> postingDocumentHeading = new ArrayList<AG_PostingDocument_Validation>();
								postingDocumentHeading = ag_Schedules.readColumnHeadingsPostingValues("Accrual & Depreciation");
								aG_PostingDocument_MetaModel.get().writeObjectValues(postingDocumentHeading, "Depreciation_Posting", baseValueExcelFile);
								if (standardName.equalsIgnoreCase("IAS")) {
									AG_Schedules.iasDepreciationHeading.set("Heading Done");
								} else {
									AG_Schedules.gaapDepreciationHeading.set("Heading Done");
								}
							}
							//Reading & Writing Depreciation Document Values
							postingDocumentValues = ag_Schedules.readPostingValues("Accrual & Depreciation");
							aG_PostingDocument_MetaModel.get().writeObjectValues(postingDocumentValues, "Depreciation_Posting", baseValueExcelFile);
						} else {
							//Validation Of Depreciation Document Values
							if(MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("validateAll")) {
								postingDocumentValues = aG_SchedulesPage.get().readPostingValues("Accrual & Depreciation");
								AG_PostingDocument_MetaModel ag_PostingDocument = aG_PostingDocument_MetaModel.get().createFromFile(baseValueExcelFile, "Depreciation_Posting");
								ag_PostingDocument.compareByDocumentType(postingDocumentValues, postingDocumentValues.get(1).getDocumentType(), "Depreciation_Posting");
							}
						}
					}
					waitAndClickOnElement(closeButton);
				}
			}
			if (paymentType.equalsIgnoreCase("Payment") || paymentType.equalsIgnoreCase("ProvisionPayment")) {
				boolean actionToDo;
				if(action.equalsIgnoreCase("Post")){
					actionToDo=!(driver.findElements(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") #payment-postings-btn")).size() == 0) &&
							driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") td:nth-child(3)")).getText().contains("Open");
				}else{
					actionToDo=!(driver.findElements(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") #payment-postings-btn")).size() == 0) &&
							driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") td:nth-child(3)")).getText().contains("Posted");
				}
				if (actionToDo) {
					waitAndClickOnElement("PaymentPosting", "#q-app .q-page .q-table  tbody tr:nth-child(" + rowNum + ") #payment-postings-btn");
					waitUntilLoadingSpinnerIsShown("nlaJournalSpinner");
					waitUntilLoadingSpinnerIsGone("nlaJournalSpinner");
					if(!action.equalsIgnoreCase("Validate")) {
						waitAndClickOnElement(PostButton);
						waitUntilLoadingSpinnerIsShown("nlaJournalSpinner");
						waitUntilLoadingSpinnerIsGone("nlaJournalSpinner");
					}
					if(!action.equalsIgnoreCase("Reverse")) {
						String documentName = driver.findElement(By.cssSelector(".journal-dialog-body  .q-mb-sm.journal-tabs-header .q-tab__label")).getText();
						ArrayList<AG_PostingDocument_Validation> postingDocumentValues = new ArrayList<AG_PostingDocument_Validation>();
						if (MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("enterNewData")) {
							boolean flag1;
							if (standardName.equalsIgnoreCase("IAS")) {
								flag1 = Strings.isEmpty(AG_Schedules.iasPaymentHeading.get());
							} else {
								flag1 = Strings.isEmpty(AG_Schedules.gaapPaymentHeading.get());
							}
							if (flag1) {
								//Reading & Writing Payment Document Headings
								ArrayList<AG_PostingDocument_Validation> postingDocumentHeading = new ArrayList<AG_PostingDocument_Validation>();
								postingDocumentHeading = ag_Schedules.readColumnHeadingsPostingValues("Payment");
								aG_PostingDocument_MetaModel.get().writeObjectValues(postingDocumentHeading, "Payment_Posting", baseValueExcelFile);
								if (standardName.equalsIgnoreCase("IAS")) {
									AG_Schedules.iasPaymentHeading.set("Heading Done");
								} else {
									AG_Schedules.gaapPaymentHeading.set("Heading Done");
								}
							}
							//Reading & Writing Paymet Document Values
							postingDocumentValues = aG_SchedulesPage.get().readPostingValues("Payment");
							aG_PostingDocument_MetaModel.get().writeObjectValues(postingDocumentValues, "Payment_Posting", baseValueExcelFile);
						} else {
							//Validation Of Payment Document Values
							if(MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("validateAll")) {
								postingDocumentValues = aG_SchedulesPage.get().readPostingValues("Payment");
								AG_PostingDocument_MetaModel ag_PostingDocument = aG_PostingDocument_MetaModel.get().createFromFile(baseValueExcelFile, "Payment_Posting");
								ag_PostingDocument.compareByDocumentType(postingDocumentValues, postingDocumentValues.get(1).getDocumentType(), "Payment_Posting");
							}
						}
					}
					waitAndClickOnElement(closeButton);

				}
			}
			waitUntilLoadingSpinnerIsGone("nlaDropDownPopUp");
		} catch (InterruptedException e) {e.printStackTrace();}
	}
	public void releaseAGSchedulesThread(){
		iasAccrualHeading.remove();
		iasDepreciationHeading.remove();
		iasPaymentHeading.remove();
		gaapAccrualHeading.remove();
		gaapDepreciationHeading.remove();
		gaapPaymentHeading.remove();
	}
	public void ClickingOnScheduleAndPosting(String standardName, String action, String paymentType, String scheduleView) {
		try{
			movingToScheduleView(standardName, scheduleView);
			waitTillWebElementIsVisible("scheduleTable", "#q-app .q-page .q-table  tbody tr:nth-child(1)");
			if (paymentType.equalsIgnoreCase("Accrual")) {
				if (driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(1) td:nth-child(3)")).getText().contains("Open")) {
					waitAndClickOnElement("AccrualPosting", "#q-app .q-page .q-table  tbody tr:nth-child(1) #accrual-postings-btn");
				}
			}
			if (paymentType.equalsIgnoreCase("Depreciation")) {
				if (driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(1) td:nth-child(6)")).getText().contains("Open")) {
					waitAndClickOnElement("DepreciationPosting", "#q-app .q-page .q-table  tbody tr:nth-child(1) #depreciation-postings-btn");
				}
			}
			if (paymentType.equalsIgnoreCase("Payment")) {
				if (driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(1) td:nth-child(3)")).getText().contains("Open")) {
					waitAndClickOnElement("DepreciationPosting", "#q-app .q-page .q-table  tbody tr:nth-child(1) #payment-postings-btn");
				}
			}
			waitUntilLoadingSpinnerIsShown("nlaJournalSpinner");
			waitUntilLoadingSpinnerIsGone("nlaJournalSpinner");
		}catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	public void validationOfDates(String documentType, String date) {
		try {
			if (documentType.equalsIgnoreCase("Document Date")) {
				WaitUntilElementIsClickable(documentDateInput);
				documentDateInput.click();
				clearField(documentDateInput);
			} else {
				WaitUntilElementIsClickable(postingDateInput);
				postingDateInput.click();
				clearField(postingDateInput);
			}
			Thread.sleep(1000);
			if (driver.findElements(By.cssSelector(".qcard-dialogue .q-card__actions .q-btn[disabled]#submit-btn")).size() == 1) {
				log.info("Post Button is disabled as the Document date / Posting date is missing");
			} else {
				Assert.fail("Post button is Active while the Document date / Posting Date (which is Mandatory field) is missing on Posting Popup!!!");
			}
			if (documentType.equalsIgnoreCase("Document Date")) {
				sendingValueToWebElement("DocumentDateInput", documentDateInput, date);
			} else {
				waitAndClickOnElement(closeButton);
			}
		}catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	public void changeDocAndPostDates(String docDate, String postDate) {
		try {
			waitTillWebElementIsVisible("Document Date", documentDateInput);
			waitAndClickOnElement(documentDateInput);
			documentDateInput.click();
			clearField(documentDateInput);
			sendingValueToWebElement("DocumentDateInput", documentDateInput, docDate);
			log.info("USer changed the document date as " + docDate);
			Thread.sleep(1000);
			waitTillWebElementIsVisible("Posting date", postingDateInput);
			waitAndClickOnElement(postingDateInput);
			postingDateInput.click();
			clearField(postingDateInput);
			sendingValueToWebElement("DocumentDateInput", postingDateInput, postDate);
			log.info("USer changed the Posting date as " + postDate);
			waitAndClickOnElement(PostButton);
			waitUntilLoadingSpinnerIsShown("nlaJournalSpinner");
			waitUntilLoadingSpinnerIsGone("nlaJournalSpinner");
			waitAndClickOnElement(closeButton);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

	}

	public void historyOfPostingData(String standardName, String paymentType, String scheduleView) {
		try {
			movingToScheduleView(standardName, scheduleView);
			waitTillWebElementIsVisible("scheduleTable", "#q-app .q-page .q-table  tbody tr:nth-child(1)");
			log.info("User is going to check the history of Postings");
			if (paymentType.equalsIgnoreCase("Accrual")) {
				if (driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(1) td:nth-child(3)")).getText().contains("Reversed")) {
					waitAndClickOnElement("AccrualPosting", "#q-app .q-page .q-table  tbody tr:nth-child(1) #accrual-postings-btn");
				}
			}
			if (paymentType.equalsIgnoreCase("Depreciation")) {
				if (driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(1) td:nth-child(6)")).getText().contains("Reversed")) {
					waitAndClickOnElement("DepreciationPosting", "#q-app .q-page .q-table  tbody tr:nth-child(1) #depreciation-postings-btn");
				}
			}
			if (paymentType.equalsIgnoreCase("Payment")) {
				if (driver.findElement(By.cssSelector("#q-app .q-page .q-table  tbody tr:nth-child(1) td:nth-child(3)")).getText().contains("Reversed")) {
					waitAndClickOnElement("DepreciationPosting", "#q-app .q-page .q-table  tbody tr:nth-child(1) #payment-postings-btn");
				}
			}
			waitUntilLoadingSpinnerIsShown("nlaJournalSpinner");
			waitUntilLoadingSpinnerIsGone("nlaJournalSpinner");
			waitAndClickOnElement("HistoryButton",".q-card .q-mb-sm #documents-selector-dialog");
			waitUntilLoadingSpinnerIsShown("nlaFieldSpinner");
			waitUntilLoadingSpinnerIsGone("nlaFieldSpinner");
			waitAndClickOnElement("ActualHistory",".q-menu  .q-link div[aria-label=History]");
			Thread.sleep(1000);
			log.info("History checked!!!!");
			waitTillWebElementIsVisible("CloseButton", closeButton);
			waitAndClickOnElement(closeButton);
		}catch(InterruptedException e){
			throw new RuntimeException(e);
		}
	}

	public void validationOfIBRRate(String standardName, String level) {
		try {
			log.info("user open the IAS Standard!!");
			String AGLevel = "Activation Group Level";
			waitAndClickOnElement(IASscheduleLinkToOpen);
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			waitTillWebElementIsVisible("scheduleViewTable", scheduleViewTable);
			waitTillWebElementIsVisible("scheduleDropdown", schedulesDropdown);
			String ibrBeforeUpdation = driver.findElement(By.cssSelector("#q-app .q-card .q-markup-table .q-table tbody tr:nth-child(1) td:nth-child(32)")).getText();
			String ibrBeforeUpdate = ibrBeforeUpdation.substring(0, ibrBeforeUpdation.length() - 1);
			moveBackToActivationGroupLevel();
			waitTillWebElementIsVisible("agAccountingTab", agACcountingTab);
			waitAndClickOnElement(agACcountingTab);
			String IBRRate=masterAgreementPageObject.getInputValues().get(level).get(AGLevel).get("Use IBR Rate").get(0);
			if(IBRRate.equalsIgnoreCase("Yes")){
				waitTillWebElementIsVisible("OverrideIBR", overrideIBR);
				sendingValueToWebElement("OverrideIBR", overrideIBR, masterAgreementPageObject.getInputValues().get(level).get(AGLevel).get("Override Inception IBR").get(0));
			}
			waitAndClickOnElement(agACcountingTab);
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			waitTillWebElementIsVisible("generateSchedules", ".q-page-container .q-card .q-btn--actionable#event-id-ag-generate-schedules-btn");
			driver.findElement(By.cssSelector(".q-page-container .q-card .q-btn--actionable#event-id-ag-generate-schedules-btn")).click();
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			Thread.sleep(6000);
			IASscheduleLinkToOpen.click();
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			if(driver.findElements(By.cssSelector("#q-app .q-page .q-table")).size()!=1){
				waitAndClickOnElement(IASscheduleLinkToOpen);
			}
			waitTillWebElementIsVisible("scheduleViewTable", scheduleViewTable);
			waitTillWebElementIsVisible("ibrRate","#q-app .q-card .q-markup-table .q-table tbody tr:nth-child(1) td:nth-child(33)");
			String ibrAfterUpdation = driver.findElement(By.cssSelector("#q-app .q-card .q-markup-table .q-table tbody tr:nth-child(1) td:nth-child(33)")).getText();
			String ibrAfterUpdate = ibrAfterUpdation.substring(0, ibrBeforeUpdation.length() - 1);
			if(ibrBeforeUpdate.equalsIgnoreCase(ibrAfterUpdate)){
				Assert.fail("IBR before update: " +ibrBeforeUpdate + "  is equal to IBR updated: " + ibrAfterUpdate);
			}
			else{
				log.info("IBR before update: " +ibrBeforeUpdate + "  is not equal to IBR updated: " + ibrAfterUpdate);
			}
			moveBackToActivationGroupLevel();
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

	}

	public void moveBetweenStandards(String standardName){
		try{
			if (standardName.equalsIgnoreCase("IAS")) {
				waitAndClickOnElement(IASscheduleLinkToOpen);
			}
			else{
				waitAndClickOnElement(GAAPscheduleLinkToOpen);
			}
			Thread.sleep(5000);
			waitTillWebElementIsVisible("scheduleViewTable", scheduleViewTable);
			waitTillWebElementIsVisible("scheduleDropdown", schedulesDropdown);
			WaitUntilElementIsClickable(schedulesDropdown);
			if (driver.findElements(By.cssSelector("#q-app .q-page #year-range-btn")).size() > 0) {
				int rowSizeBefore = driver.findElements(By.cssSelector("#q-app .q-page .q-table  tbody tr")).size();
				scheduleYearRange.click();
				waitTillWebElementIsVisible("allYearCheckBox", scheduleAllYearCheckBox);
				waitAndClickOnElement(scheduleAllYearCheckBox);
				waitAndClickOnElement(submitAllYear);
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				waitTillWebElementIsVisible("scheduleViewTable", scheduleViewTable);
				int rowSizeBeforeAfter = driver.findElements(By.cssSelector("#q-app .q-page .q-table  tbody tr")).size();
				if (rowSizeBeforeAfter == rowSizeBefore) {
					Assert.fail("Schedules not showing for all years at AG");
				}
			}

		}catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}


