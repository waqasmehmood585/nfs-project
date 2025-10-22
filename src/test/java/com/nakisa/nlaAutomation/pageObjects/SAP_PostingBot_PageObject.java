package com.nakisa.nlaAutomation.pageObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;

import com.nakisa.nlaAutomation.Constant;
import io.cucumber.datatable.DataTable;
import org.apache.directory.api.util.Strings;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.nakisa.nlaAutomation.stepDefinitions.MasterHooks;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SAP_PostingBot_PageObject  extends Common_BasePage_PageObject {
	MasterAgreement_PageObject masterAgreementPageObject = masterAgreement_PageObject.get();
	ReportingModule_PageObject reportingModulePageObject = reportingModule_pageObject.get();
	private int index;
	private File baseValueExcelFile;
	private File baseValueDownloadedExcelFile;
	private File misMatchExcelFile;
	private int totalNumberOfRows = 0;
	private int totalNumberOfColumns = 0;
	private String[][] excelValidationValues;
	private String[][] excelDownloadedValidationValues;
	private String filePathString;
	private String recordPerPage="#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item";
	//SAP Posting Bot
	public @FindBy (css = "#q-app .q-page .q-btn-group .q-btn.bg-primary") WebElement addProfileButton;
	public @FindBy (css = ".q-dialog .q-card #system") WebElement erpSystem;
	public @FindBy (css = ".q-dialog .q-card .col-4 label#company-filter-type") WebElement companyFilter;
	public @FindBy (css = ".q-dialog .q-card #companies") WebElement companies;
	public @FindBy (css = ".q-dialog .q-card .col-4 label#accounting-standard-filter-type") WebElement accountingStandardFilter;
	public @FindBy (css = ".q-dialog .q-card #accounting-standard") WebElement accountingStandard;
	public @FindBy (css = ".q-dialog .q-card input[aria-label='Batch Size *']") WebElement batchSizeInput;
	public @FindBy (css = ".q-dialog .q-card #job-posting-statuses") WebElement postingStatuses;
	public @FindBy(css=".q-dialog .q-card .q-table #id-search-input") WebElement profileSearchFilter;
	public @FindBy(css = ".q-dialog .q-card .row button.text-accent:nth-child(2)") WebElement addSPBJob;
	public @FindBy(css = ".q-dialog .q-card .row button.text-accent:nth-child(3)") WebElement addSPBScheduleJob;
	public @FindBy(css = "#q-app .q-page .jobs-grid .q-btn-group .q-btn.text-primary .mdi-refresh") WebElement refreshSPBJob;
	public @FindBy(css = "#q-app .q-page .tasks-grid .q-btn-group .q-btn.text-primary") WebElement refreshSPBTask;
	//CTR Profile
	public @FindBy(css = "#q-app .q-page .q-btn-group #add-btn") WebElement addButton;
	public @FindBy(css = ".q-dialog  .q-card #name-input") WebElement reportName;
	public @FindBy(css = ".q-dialog  .q-card #principal-position-type") WebElement principalPosition;
	public @FindBy(css = ".q-dialog  .q-card #erp-filter-type") WebElement erpFilterType;
	public @FindBy(css = ".q-dialog  .q-card #calendar-type") WebElement calendarType;
	public @FindBy(css = ".q-dialog  .q-card #fiscal-variant") WebElement fiscalVariant;
	public @FindBy(css = ".q-dialog  .q-card #lease-area-filter-type") WebElement leaseAreaFilterType;
	public @FindBy(css = ".q-dialog  .q-card #business-unit-filter-type") WebElement businessUnitFilterType;
	public @FindBy(css = ".q-dialog  .q-card #company-filter-type") WebElement companyFilterType;
	public @FindBy(css = ".q-dialog  .q-card #department-filter-type") WebElement departmentFilterType;
	public @FindBy(css = ".q-dialog  .q-card #lease-group-filter-type") WebElement leaseGroupFilterType;
	public @FindBy(css = ".q-dialog  .q-card #vendor-filter-type") WebElement vendorFilterType;
	public @FindBy(css = ".q-dialog  .q-card #internal-asset-info-class-filter-type")WebElement  internalAssetClassFilterType;
//	public @FindBy(css = ".q-dialog  .q-card #accounting-standard") WebElement accountingStandard;
	public @FindBy(css = ".q-dialog  .q-card #lease-classification-filter-type") WebElement leaseClassificationFilterType;
	public @FindBy(css = ".q-dialog  .q-card #erp-systems") WebElement erpSystems;
	public @FindBy(css = ".q-dialog  .q-card #lease-areas") WebElement leaseAreas;
	public @FindBy(css = ".q-dialog  .q-card #business-units") WebElement businessUnits;
	public @FindBy(css = ".q-dialog  .q-card #company-codes") WebElement companyCodes;
	public @FindBy(css = ".q-dialog  .q-card #departments") WebElement departments;
	public @FindBy(css = ".q-dialog  .q-card #lease-groups") WebElement leaseGroups;
	public @FindBy(css = ".q-dialog  .q-card #vendors") WebElement vendors;
	public @FindBy(css = ".q-dialog  .q-card #internal-asset-info-classes")WebElement  internalAssetClasses;
	public @FindBy(css = ".q-dialog  .q-card #lease-classifications") WebElement leaseClassifications;
	//CTR Job
	public @FindBy(css = "#q-app .q-page .q-btn-group #add-btn") WebElement addJobButton;
	public @FindBy(css = "#q-app .q-page .q-btn-group #create-job-btn") WebElement addScheduleJobButton;
	public @FindBy(css = ".q-dialog  .q-card #select-profile-btn") WebElement selectProfile;
	public @FindBy(css = ".q-dialog  .q-card #date-range-type") WebElement dateRangeType;
	public @FindBy(css = ".q-dialog  .q-card #start-date-input-input") WebElement startDateInput;
	public @FindBy(css = ".q-dialog  .q-card #end-date-input-input") WebElement endDateInput;
	public @FindBy(css = ".q-dialog  .q-card #transaction-type-value-filter-selector") WebElement transactionTypeValueFilter;
	public @FindBy(css = ".q-dialog  .q-card #transaction-type-values") WebElement transactionTypeValues;
	public @FindBy(css = ".q-dialog  .q-card #ledger-types-filter-selector") WebElement ledgerTypesFilter;
	public @FindBy(css = ".q-dialog  .q-card #ledger-types") WebElement ledgerTypes;
	public @FindBy(css = ".q-dialog  .q-card #external-posting-status-filter-type") WebElement externalPostingStatusFilterType;
	public @FindBy(css = ".q-dialog  .q-card #external-posting-status") WebElement externalPostingStatus;
	public @FindBy(css = ".q-dialog .q-card #contract-id-input") WebElement contractId;
	public @FindBy(css = ".q-dialog  .q-card #cost-center-id-input")WebElement  costCenterId;
	public @FindBy(css = ".q-dialog  .q-card #profit-center-id-input") WebElement profitCenterId;
	public @FindBy(css = ".q-dialog  .q-card #business-area-id-input") WebElement businessAreaId;
	public @FindBy(css = ".q-dialog  .q-card #functional-area-id-input") WebElement functionalAreaId;
	public @FindBy(css = ".q-dialog  .q-card #run-consolidated-transaction-job-btn") WebElement consolidatedJobAddButton;
	public @FindBy(css = "#q-app .q-page #refresh-btn") WebElement jobRefreshButton;
	public @FindBy(css = "#q-app .q-page tbody tr:nth-child(2) #generate-btn") WebElement reportDownloadButton;
	//GL Report
	public @FindBy(css = "#q-app .q-toolbar button:nth-child(6)") WebElement userBtn;
	public @FindBy(css = "[role='menu'] .q-item[role='listitem'] div.text-grey") WebElement userEmail;
	public @FindBy(css = "#q-app  #search-created-by-input") WebElement createdByFilter;
	public @FindBy(css = "#q-app  [css-selectors='search-createdBy']") WebElement createdByFilterCTR;
	public @FindBy(css = ".q-dialog  .q-card #from-date-input-input") WebElement fromYear;
	public @FindBy(css = ".q-dialog  .q-card #from-period") WebElement fromPostingPeriod;
	public @FindBy(css = ".q-dialog  .q-card #to-date-input-input") WebElement toYear;
	public @FindBy(css = ".q-dialog  .q-card #to-period") WebElement toPostingYear;
	public @FindBy(css = ".q-dialog  .q-card #posting-category-filter-type") WebElement postingCategoryFilterType;
	public @FindBy(css = ".q-dialog  .q-card #exchange-rate-types") WebElement exchangeRateTypes;
	public @FindBy(css = ".q-dialog  .q-card #lease-type-filter-type") WebElement leaseTypeFilterType;
	public @FindBy(css = ".q-dialog  .q-card #lease-types") WebElement leaseTypes;
	public @FindBy(css = ".q-dialog  .q-card #account-type-filter-type") WebElement accountTypeFilterType;
	public @FindBy(css = ".q-dialog  .q-card #account-types") WebElement accountTypes;
	public @FindBy(css = ".q-dialog  .q-card #general-ledger-account-type") WebElement generalLedgerAccountType;
	public @FindBy(css = ".q-dialog  .q-card #general-ledger-accounts") WebElement generalLedgerAccounts;
	public @FindBy(css = ".q-dialog  .q-card #nfs-entity-type") WebElement nfsEntityType;
	public @FindBy(css = ".q-dialog  .q-card #reference-field-type-filter") WebElement ctrObjectType;
	public @FindBy(css = ".q-dialog  .q-card #application-reference-name-filter") WebElement ctrApplicationFilter;
	public @FindBy(css = ".q-dialog  .q-card #reference-display-ids-list-input") WebElement ctrObjectList;
	public @FindBy(css = ".q-dialog  .q-card #nfs-id .q-field") WebElement nfsIdInput;
	public @FindBy(css = "#q-app .q-page tbody tr:nth-child(2) #done-action") WebElement reportDownloadGL;
	public @FindBy(css = ".q-dialog  .q-card #name-input-input") WebElement ScheduleJobName;
	public @FindBy(css = ".q-dialog .q-card #x-btn") WebElement cancelJob;
	public @FindBy(css = "#q-app .q-table .q-tr:nth-child(2) .q-td:nth-child(5)") WebElement clickEditSchJob;
	public @FindBy(css = ".q-card .dialog-body .q-panel .q-checkbox[v-css-selectors='jan-checkbox']") WebElement monthBox1;
	public @FindBy(css = ".q-card .dialog-body .q-panel .q-checkbox[v-css-selectors='apr-checkbox']") WebElement monthBox2;
	public @FindBy(css = ".q-card .dialog-body .q-tab[v-css-selectors='yearly-tab']") WebElement yearlyTab;
	public @FindBy(css = ".q-dialog .q-card #submit-btn[aria-disabled='true']") WebElement disableSubmit;
	public @FindBy(css = "#q-app .q-page tbody td:nth-child(6) input[aria-label='Search']") WebElement companiesFilter;
	public @FindBy(css = "#q-app .q-page table #system-filter") WebElement systemFilter;
	public @FindBy(css = "#q-app .q-page #system-filter button.q-icon") WebElement systemCancelIcon;
	public @FindBy(css = "#q-app .q-table .q-tr:nth-child(2)")WebElement actionButton;
	public @FindBy(css = ".q-dialog  .q-card #x-btn") WebElement cancelBtn;
	public @FindBy(css = ".q-dialog .q-card #id-input-input")WebElement searchProfileInputField;
	// JV
	public @FindBy(css = ".q-page .q-btn#add-btn") WebElement createJournalVoucherBtn;
	public @FindBy(css = ".q-dialog .qcard-dialogue #name-textarea") WebElement nameJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #lease-area") WebElement leaseAreaJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #business-unit") WebElement businessAreaJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #system") WebElement systemJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #company") WebElement companyJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #currency") WebElement currencyJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #document-type") WebElement documentTypeJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #accounting-standard") WebElement accountingStandardJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #lease-lassification-type") WebElement leaseClassificationJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #posting-date-input-input") WebElement postingDateJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #document-date-input-input") WebElement documentDateJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #principal-position-type") WebElement principalPositionJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #automatic-reversal") WebElement automaticReversalJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #reversal-posting-date-input-input") WebElement reversalPostingDateJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #reversal-document-date-input-input") WebElement reversalDocumentDateJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #reversal-reason") WebElement reversalReasonJournalVoucher;

//	public @FindBy(css = ".q-dialog .qcard-dialogue #document-comments-textarea") WebElement nameJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #work-breakdown-structure") WebElement wbsJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #segment") WebElement segmentJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #network") WebElement networkJournalVoucher;
	public @FindBy(css = ".q-menu .q-item:nth-child(1)") WebElement networkValueJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #functional-area") WebElement functionalAreaJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #payment-term") WebElement paymentTermJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #payment-block") WebElement paymentBlockJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #payment-method") WebElement paymentMethodJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #plant") WebElement plantJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #internal-order") WebElement internalOrderJournalVoucher;
	public @FindBy(css = ".q-dialog .q-card input[aria-label='Reference Key']") WebElement referenceKeyJournalVoucher;
	public @FindBy(css = ".q-dialog .q-card input[aria-label='Reference Field 1 *']") WebElement referenceKey1JournalVoucher;
	public @FindBy(css = ".q-dialog .q-card input[aria-label='Reference Field 2']") WebElement referenceKey2JournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #add-btn-note") WebElement addrefrenceKeybtnJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue .q-tab:nth-child(2)") WebElement entriesTabJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue .q-tab:nth-child(3)") WebElement additonalTabJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #general-ledger") WebElement accountNumberJournalVoucher;
	public @FindBy(css = ".q-dialog .q-card input[css-selectors='debit_contract_currency']") WebElement debitContractJournalVoucher;
	public @FindBy(css = ".q-dialog .q-card input[css-selectors='debit_company_currency']") WebElement debitCompanyJournalVoucher;
	public @FindBy(css = ".q-dialog .q-card input[css-selectors='debit_second_currency']") WebElement debitSecondJournalVoucher;
	public @FindBy(css = ".q-dialog .q-card input[css-selectors='credit_contract_currency']") WebElement creditContractJournalVoucher;
	public @FindBy(css = ".q-dialog .q-card input[css-selectors='credit_company_currency']") WebElement creditCompanyJournalVoucher;
	public @FindBy(css = ".q-dialog .q-card input[css-selectors='credit_second_currency']") WebElement creditSecondJournalVoucher;
	public @FindBy(css = ".q-dialog .qcard-dialogue #add-btn-entry") WebElement addAccountEntry;
	public @FindBy(css = ".q-dialog .qcard-dialogue tr:nth-child(3) #edit-btn") WebElement editBtnJV;
	public @FindBy(css = ".q-dialog .qcard-dialogue #cost-center") WebElement costCenterJV;
	public @FindBy(css = ".q-dialog .qcard-dialogue #profit-center") WebElement profitCenterJV;
	public @FindBy(css = ".q-dialog .qcard-dialogue #vendor") WebElement vendorJV;
	public @FindBy(css = ".q-dialog .qcard-dialogue #calculate-taxes-choice") WebElement calculateTaxJV;
	public @FindBy(css = ".q-dialog .qcard-dialogue #tax-jurisdiction") WebElement taxJurisdictionJV;
	public @FindBy(css = ".q-dialog .qcard-dialogue #tax-determination") WebElement taxDeterminationJV;
	public @FindBy(css = ".q-dialog .qcard-dialogue [aria-label='Note']") WebElement additionalInformationNoteJV;
	public @FindBy(css = ".q-dialog .qcard-dialogue #submit-btn-create") WebElement createJV;
	public @FindBy(css = ".q-dialog .qcard-dialogue #submit-btn") WebElement sendToApprovalJV;
	public @FindBy(css = ".q-dialog .qcard-dialogue #reject-btn") WebElement reworkJV;
	public @FindBy(css = ".q-dialog .qcard-dialogue #submit-btn-post") WebElement approveJV;
	public @FindBy(css = ".q-dialog .qcard-dialogue #update-btn") WebElement saveJV;
	public @FindBy(css = ".q-dialog .qcard-dialogue #cancel-btn") WebElement closeJV;

	public SAP_PostingBot_PageObject() {
		super();
		log.info("Driver is inside this class: " + this.getClass().getSimpleName());
		PageFactory.initElements(driver, this);
	}

	public void createSAPPostingProfile(String system, String company,String accountingStandardValue) {
		log.info("Creating SAP Posting Profile");
		removeAIAssistant();
		try {
			boolean profileCreated;
			boolean isChecked = waitUntilTextEqualsIgnoreCase("#q-app [role='tablist'] .q-tab--active", "Posting Job", 5);
			waitUntilTextEqualsIgnoreCase(".q-page .q-table__middle .q-tr th:nth-child(4)","ERP System",7);
			if (waitUntilElementIsShown("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item",7)) {
				deleteExistingProfiles();
			}
			int recordBeforeProfile = 0;
			do {
				waitTillWebElementIsVisible("addProfileButton",addProfileButton);
				waitAndClickOnElement(addProfileButton);
				waitTillWebElementIsVisible("createProfilePopUp", ".q-dialog  .qcard-dialogue");
				waitTillWebElementIsVisible("systemDropDown", erpSystem);
				clickOnDropDownAndSelectValue("sapErpSystem", erpSystem, system);

				if (!company.equalsIgnoreCase("All")) {
					clickOnDropDownAndSelectValue("companyFilter", companyFilter, "List");
					clickOnDropDownToTypeAndSelectCheckBox("companyValues", companies, company);
				}
				if(accountingStandardValue.equalsIgnoreCase("All")){
					clickOnDropDownAndSelectValue("accountingStandardFilter", accountingStandardFilter, "All");
				}else{
					clickOnDropDownAndSelectValue("accountingStandardFilter", accountingStandardFilter, "List");
					clickOnDropDownToTypeAndSelectCheckBox("accountingStandardValues", accountingStandard, accountingStandardValue);
				}
//				waitAndClickOnElement(companies);
				waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
				clickOnSubmitPopup("Submit / Add");
				waitUntilLoadingSpinnerIsShown("nlaAlertMessage");

				profileCreated = driver.findElement(By.cssSelector(".desktop .q-notifications .q-notification[role='alert']")).getText().equalsIgnoreCase("Profile cannot be created since there already exists a profile with overlapping companies");
				if (profileCreated) {
					waitAndClickOnElement("cancelButton", ".q-dialog .q-card #cancel-btn");
					waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
					Thread.sleep(5000);
				}
			} while (profileCreated);

			//Waiting for page records to Increase
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					String records = driver.findElement(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
					int recordAfterProfile = Integer.parseInt(records.substring(records.indexOf("f") + 2));
					if (recordAfterProfile > recordBeforeProfile) {
						return true;
					} else {
						return false;
					}
				}
			});
			log.info("Profile Created Successfully");
			waitTillWebElementIsVisible("sapProfileID","#q-app .q-page .q-table tbody tr:nth-child(2) td.id div");
			MasterHooks.sapProfileId.set(driver.findElement(By.cssSelector("#q-app .q-page .q-table tbody tr:nth-child(2) td.id div")).getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteExistingProfiles() {
		try {

			waitUnTillWebElementIsVisible("firstCheckBox", "tbody tr:nth-child(2) .q-checkbox");
			waitAndClickOnElement("allCheckBoxes", "table tr:nth-child(1) .q-checkbox");
			waitAndClickOnElement("deleteButton", ".posting-profiles-grid .q-btn.bg-red.q-btn--actionable");
			waitTillWebElementIsVisible("popUp", ".q-dialog .q-card");
			waitAndClickOnElement("decommissionSubmit", ".q-dialog .q-card .q-btn.bg-red.q-btn--actionable");
			waitUntilLoadingSpinnerIsGone("nlaDropDownPopUp");
			waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
			waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
			log.info("Existing Profile has been Decommissioned Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createSAPPostingJob(String statuses, String batchSize) {
		log.info("Creating SAP Posting Job");
		try {
			boolean isChecked = waitUntilTextEqualsIgnoreCase("#q-app [role='tablist'] .q-tab--active", "Posting Job", 5);
			waitTillWebElementIsVisible("jobsGrid", "#q-app .q-page .jobs-grid");
			waitTillWebElementIsVisible("sapProfileAddButton", addProfileButton);
			int recordBeforeJob = reportingModulePageObject.getRecordCountBefore("","#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item");
			waitTillWebElementIsVisible("jobsGrid","#q-app .jobs-grid");
			waitTillWebElementIsVisible("addProfileButton","#q-app .q-page .q-btn-group .q-btn.bg-primary");
			waitAndClickOnElement(addProfileButton);
			waitTillWebElementIsVisible("createProfilePopUp",".q-dialog  .qcard-dialogue");
			waitTillWebElementIsVisible("batchSizeSPB",".q-dialog .q-card input[aria-label='Batch Size *']");
			sendingValueToWebElement("batchSizeSPB", batchSizeInput, batchSize);
			if(!statuses.equalsIgnoreCase("null")) {
//				if(statuses.equalsIgnoreCase("Open")) {
					clickOnDropDownAndSelectCheckBoxes("postingStatusesSPB", postingStatuses, statuses);
//				}else {
//					clickOnDropDownAndSelectCheckBoxes("postingStatusesSPB", postingStatuses, "Open");
//				}
				waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
			}
			selectProfile(MasterHooks.sapProfileId.get());
			WaitUntilElementIsClickable(addSPBJob);
			waitAndClickOnElement(addSPBJob);
			waitUntilLoadingSpinnerIsShown("nlaDropDownPopUp");
			waitUntilLoadingSpinnerIsGone("nlaDropDownPopUp");
			Thread.sleep(1000);
			log.info("Created SAP Posting Job");
			//Waiting for page records to Increase
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					String records = driver.findElement(By.cssSelector("#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
					int recordAfterJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
					if(recordAfterJob!=recordBeforeJob) {
						return true;
					} else {
						return false;
					}
				}
			});
			//Waiting for job to move from creating status
			Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(300)).pollingEvery(Duration.ofMillis(5000)).ignoring(WebDriverException.class);
			wait1.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					try {
						waitAndClickOnElement(refreshSPBJob);
						waitUntilLoadingSpinnerIsShown("nlaBatchJobListLoader");
						waitUntilLoadingSpinnerIsGone("nlaBatchJobListLoader");
						if (!driver.findElement(By.cssSelector("#q-app .q-page .jobs-grid .q-table tbody tr:nth-child(2) .q-badge[role='status']"))
								.getText().equalsIgnoreCase("Creating")) {
							waitAndClickOnElement("firstJobSelected","#q-app .q-page .jobs-grid .q-table tbody tr.selected-job-row");
							waitAndClickOnElement(refreshSPBTask);
							Thread.sleep(1000);
							return true;
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return false;
				}
			});
			//Waiting for task Status to be Done/Complete
			int jobsSize = driver.findElements(By.cssSelector("#q-app .q-page .col .q-table tbody tr")).size();
			for(index=2;index<=jobsSize;index++) {
				checkJobCompletion("Done",index);
			}
			String records = driver.findElement(By.cssSelector("#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
			int temp1 = Integer.parseInt(records.substring(records.indexOf("f") + 2));
			for(index=0;index<=5;index++) {
				//Waiting for job Done status
				Wait<WebDriver> wait2 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(600)).pollingEvery(Duration.ofMillis(10000)).ignoring(WebDriverException.class);
				wait2.until(new Function<WebDriver, Boolean>() {
					@Override
					public Boolean apply(WebDriver driver) {
						try {
							waitAndClickOnElement(refreshSPBJob);
							waitUntilLoadingSpinnerIsShown("nlaBatchJobListLoader");
							waitUntilLoadingSpinnerIsGone("nlaBatchJobListLoader");
							String records = driver.findElement(By.cssSelector("#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
							int temp2 = Integer.parseInt(records.substring(records.indexOf("f") + 2));
							String status= driver.findElement(By.cssSelector("#q-app .q-page .jobs-grid .q-table tbody tr:nth-child("+ ((temp2-temp1) + 2 + index) +") .q-badge[role='status']"))
									.getText();
							if (status.equalsIgnoreCase("Finished")|| status.equalsIgnoreCase("Interrupted")|| status.equalsIgnoreCase("Failed")) {
								return true;
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						return false;
					}
				});
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectProfile(String profileID){
		log.info("Selecting Profile");
		try {
			waitTillWebElementIsVisible("profileSearchField", profileSearchFilter);
			int countBeforeSearch=driver.findElements(By.cssSelector(".q-dialog .q-card tbody tr .q-td.id:nth-child(2)")).size();
			sendingValueToWebElement("profileID", profileSearchFilter, profileID);
			if (countBeforeSearch > 1) {
				Wait<WebDriver> waitTemp = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(100)).ignoring(WebDriverException.class);
				waitTemp.until(new Function<WebDriver, Boolean>() {
					@Override
					public Boolean apply(WebDriver driver) {
						if (countBeforeSearch != driver.findElements(By.cssSelector(".q-dialog .q-card tbody tr .q-td.id:nth-child(2)")).size()) {
							return true;
						}
						return false;
					}
				});
			}
			//1st profile selected after search
			waitAndClickOnElement("selectprofile", ".q-dialog .q-card tbody tr:nth-child(2) .q-td .q-checkbox");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("Selected Profile");
	}

	public void checkJobCompletion(String jobStatusToCheck,int jobNumber) {
		log.info("Check the Jobs Completed Successfully");
		SoftAssert softAssert = new SoftAssert();
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(1200)).pollingEvery(Duration.ofMillis(10000)).ignoring(WebDriverException.class);
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					try {
						waitAndClickOnElement(refreshSPBTask);
						Thread.sleep(200);
						System.out.println("Clicked : " +jobNumber);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (driver.findElement(By.cssSelector(".q-page .col.q-py-sm tbody tr:nth-child("+jobNumber+") .q-td [role='status']")).getText().equalsIgnoreCase(jobStatusToCheck)) {
						log.info("Job Number " + jobNumber +" Completed" );
						return true;
					}else if (driver.findElement(By.cssSelector(".q-page .col.q-py-sm tbody tr:nth-child("+jobNumber+") .q-td [role='status']")).getText().equalsIgnoreCase("Failed")) {
						log.info("Job Number " + jobNumber +" Failed" );
						Assert.fail("SAP Task"+ jobNumber +" is failed");
						return true;
					} else {
						log.info("Job Number " + jobNumber +" is not Completed Yet" );
						return false;
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Job Completed Successfully");
	}

	public void createConsolidatedJob(String jobType,String reportLevel, DataTable dataTable) {
		log.info("Creating Consolidated Job");
		List<Map<String, String>> job = dataTable.asMaps(String.class, String.class);
		try {
			waitUntilLoadingSpinnerIsShown("sapReportPageLoader");
			waitUntilLoadingSpinnerIsGone("sapReportPageLoader");
			waitUntilTextEqualsIgnoreCase("#q-app .q-table tr th:nth-child(5)","Ledger Types",10);
			if(!jobType.equalsIgnoreCase("Scheduled Job")) {
				applyCreatedByFilter("Consolidated Report");
			}
			waitForWebElementToDisappear("deleteButton","#q-app #delete-btn");
			int recordBeforeJob = reportingModulePageObject.getRecordCountBefore(jobType, recordPerPage);
			if(jobType.equalsIgnoreCase("Scheduled Job")) {
				waitAndClickOnElement(addScheduleJobButton);
			}else{
				waitAndClickOnElement(addJobButton);
			}
			waitTillWebElementIsVisible("createJobPopUp",".q-dialog  .qcard-dialogue");
			if(job.get(0).get("Filter Profile/Object").equalsIgnoreCase("Object")){
				waitAndClickOnElement("objectTab",".q-dialog  .q-card .tab-option:nth-child(3)");
			}
			if(jobType.equalsIgnoreCase("Scheduled Job")) {
				waitAndClickOnElement(ScheduleJobName);
				sendingValueToWebElement("reportName", ScheduleJobName, job.get(0).get("Name"));
			}
			if(job.get(0).get("Filter Profile/Object").equalsIgnoreCase("Object")) {
				waitTillWebElementIsVisible("ctrApplicationFilter",ctrApplicationFilter);
				clickOnDropDownAndSelectValue("ctrApplicationFilter",ctrApplicationFilter,job.get(0).get("Application"));
				String values = "";
				HashMap<String, String> threadMap = MasterHooks.map.get();
				String objectType = job.get(0).get("Object Type");
				clickOnDropDownAndSelectValue("objectType", ctrObjectType, job.get(0).get("Object Type"));
				if (job.get(0).get("Object List").equalsIgnoreCase("All")) {
					String mappedKey = mapObjectType(objectType);
					values = getRecordId(mappedKey, threadMap, false);
					clickOnDropDownToTypeAndSelectCheckBox("objectListValue", ctrObjectList, values);
				} else {
					String objectListValue = job.get(0).get("Object List");
					values = getRecordId(objectListValue, threadMap, true);
					clickOnDropDownToTypeAndSelectCheckBox("objectListValue", ctrObjectList, values);
				}
			}else {
				selectProfileForReports("consolidatedTransactionProfile", MasterHooks.reportProfileID.get());
			}
			waitTillWebElementIsVisible("dateRangeType",dateRangeType);
			clickOnDropDownAndSelectValue("dateRangeType",dateRangeType, job.get(0).get("Date Range Type"));
			sendingValueToWebElement("startDateInput",startDateInput, job.get(0).get("Period Start"));
			sendingValueToWebElement("endDateInput",endDateInput, job.get(0).get("Period End"));
			clickOnDropDownAndSelectValue("accountingStandard", accountingStandard, job.get(0).get("Accounting Standard"));
			//Additional Filters
			if(job.get(0).get("Ledger Type filter 1").equalsIgnoreCase("List")){
				clickOnDropDownToTypeAndSelectValue("transactionTypeValueFilter",transactionTypeValueFilter, job.get(0).get("Ledger Type filter 1"));
				clickOnDropDownToTypeAndSelectCheckBox("transactionTypeValues",transactionTypeValues, job.get(0).get("Transaction Type"));
			}
			if(job.get(0).get("Ledger Type filter 2").equalsIgnoreCase("List")){
				clickOnDropDownToTypeAndSelectValue("ledgerTypesFilter",ledgerTypesFilter, job.get(0).get("Ledger Type filter 2"));
				clickOnDropDownToTypeAndSelectCheckBox("ledgerTypes",ledgerTypes, job.get(0).get("Ledger Types"));
			}
			if(job.get(0).get("External Posting Status Filter").equalsIgnoreCase("List")){
				clickOnDropDownToTypeAndSelectValue("externalPostingStatusFilterType",externalPostingStatusFilterType, job.get(0).get("External Posting Status Filter"));
				clickOnDropDownToTypeAndSelectCheckBox("externalPostingStatus",externalPostingStatus, job.get(0).get("External Posting Status"));
			}

			if(!(job.get(0).get("Business Area ID").equalsIgnoreCase("null") )){
				sendingValueToWebElement("businessAreaId",businessAreaId, job.get(0).get("Business Area ID"));
			}
			if(!job.get(0).get("Functional Area ID").equalsIgnoreCase("null")){
				sendingValueToWebElement("functionalAreaId",functionalAreaId, job.get(0).get("Functional Area ID"));
			}
			if(jobType.equalsIgnoreCase("Scheduled Job")){
				reportingModulePageObject.reportScheduling(reportLevel, job);
			}
			if(!(consolidatedJobAddButton.isEnabled())) {
				Assert.assertFalse(disableSubmit.isEnabled(), "Mandatory fields are missing");
				log.info("Mandatory fields are missing");
			}
			else {
				waitAndClickOnElement(consolidatedJobAddButton);
				if(jobType.equalsIgnoreCase("Scheduled Job")) {
					waitUntilLoadingSpinnerIsShown("nlaBatchJobListLoader");
					waitUntilLoadingSpinnerIsGone("nlaBatchJobListLoader");
				}
				//Waiting for page records to Increase
				reportingModulePageObject.waitForRecordChange(recordBeforeJob, recordPerPage);
				log.info("CTR Job Added Successfully");
			}
		}catch (InterruptedException | IOException e) {e.printStackTrace();}

	}
	private void selectProfileForReports(String reportName, String profileID){
		log.info("Searching the Profile for "+reportName);
		handleWait(1000);
		waitTillWebElementIsVisible("searchProfileInputField",searchProfileInputField);
		waitTillWebElementIsVisible("profileRadioButtons",".q-card .q-radio");
			waitTillWebElementIsVisible("searchProfile",searchProfileInputField);
			int countBeforeSearch=driver.findElements(By.cssSelector(".q-dialog .q-card tbody tr .q-td.id:nth-child(2)")).size();
			sendingValueToWebElement("searchProfile",searchProfileInputField,profileID);
			if (countBeforeSearch > 1) {
				Wait<WebDriver> waitTemp = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(100)).ignoring(WebDriverException.class);
				waitTemp.until(new Function<WebDriver, Boolean>() {
					@Override
					public Boolean apply(WebDriver driver) {
						if (countBeforeSearch != driver.findElements(By.cssSelector(".q-dialog .q-card tbody tr .q-td.id:nth-child(2)")).size()) {
							return true;
						}
						return false;
					}
				});
			}
			try {
				waitAndClickOnElement("selectProfile", ".q-dialog .q-card tbody tr:nth-child(2) .q-td:nth-child(1) .q-radio");
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
	}

	public void downloadAndValidateReport(String standard, String sheetName) {
		log.info("Downloading And Validating Report File");
		baseValueExcelFile = new File("src/test/resources/validationExcelFiles/"
				+ MasterHooks.configurationProperties.get().getWhichBaselineToUseForValidation() + "/"
				+ MasterAgreement_PageObject.testCaseName.get() + "/"
				+ MasterAgreement_PageObject.testCaseName.get() +"_"+standard+ "_Output.xlsx");
		filePathString=downloadReport(sheetName);
		baseValueDownloadedExcelFile = new File(filePathString);
//		Reading Base Excel & Downloaded Excel file
		readReportValidationValues(sheetName);
		sortingDownloadedFileValues(sheetName);

		//Compare Base Excel with Download excel
		compareScheduleValues(excelDownloadedValidationValues, excelValidationValues, sheetName, standard);
		excelDownloadedValidationValues=null;
		excelValidationValues=null;
		//delete downloaded file
		if(Strings.isEmpty(MasterHooks.checkForValidationFailures.get())) {
			File folder=new File(MasterHooks.downloadedExcelFilePath.get());
			for (File file : folder.listFiles()) {
				file.delete();


				log.info("Excel file deleted successfully!!!");
			}
		}
	}

	public String downloadReport(String sheetName) {
		log.info("Downloading report file");
		//Checking Files in folder before download
		File folder=new File(MasterHooks.downloadedExcelFilePath.get());
		File [] listOfFilesBeforeDown=folder.listFiles();
		int sizeOfFileBeforeDown=listOfFilesBeforeDown.length;
		System.out.println("The list of files before Excel download are " + sizeOfFileBeforeDown);

		try {
			if(sheetName.equalsIgnoreCase("GL Balance Report")){
				waitAndClickOnElement(reportDownloadGL);
			}else{
				waitAndClickOnElement(reportDownloadButton);
			}

		} catch (InterruptedException e) {e.printStackTrace();}
		//Waiting for file download loader
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofMillis(300)).ignoring(WebDriverException.class);
		wait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				if(driver.findElements(By.cssSelector("#q-app .q-table .q-spinner")).size()==0){
					return true;
				}
				return false;
			}
		});
		try {
			if(MasterHooks.configurationProperties.get().getRunEnvironment().equalsIgnoreCase("remote")) {
				download_file_selenium_grid(folder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		//Waiting for file to download
		Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(300)).pollingEvery(Duration.ofMillis(2000)).ignoring(WebDriverException.class);
		wait1.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				File [] listOfFilesAfterDown=folder.listFiles();
				int sizeAfterDown=listOfFilesAfterDown.length;
				if(sizeAfterDown!=sizeOfFileBeforeDown){
					return true;
				}
				return false;
			}
		});
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		File [] downloadedFile=folder.listFiles();
		File lastDownloadedFile = null;
		if (downloadedFile != null && downloadedFile.length > 0) {
			// Sort files by last modified timestamp in descending order
			Arrays.sort(downloadedFile, Comparator.comparingLong(File::lastModified).reversed());

			// Get the first file (last modified file)
			lastDownloadedFile = downloadedFile[0];

			// Print the details of the last downloaded file
			System.out.println("Last downloaded file:");
			System.out.println("File Name: " + lastDownloadedFile.getName());
			System.out.println("Last Modified: " + lastDownloadedFile.lastModified());
			System.out.println("Absolute Path: " + lastDownloadedFile.getAbsolutePath());
		}
		log.info("Report Downloaded & returning path");
		return  downloadedFile[0].getAbsolutePath();
	}

	public void readReportValidationValues(String sheetName) {
		try {
			FileInputStream file = new FileInputStream(baseValueExcelFile);
			XSSFWorkbook myWorkbook = new XSSFWorkbook(file);
			FileInputStream file1 = new FileInputStream(baseValueDownloadedExcelFile);
			XSSFWorkbook myWorkbook1 = new XSSFWorkbook(file1);
			XSSFSheet mySheet = myWorkbook.getSheet(sheetName);
			XSSFSheet mySheet1 = myWorkbook1.getSheet(sheetName);
			totalNumberOfRows = mySheet.getLastRowNum();
			int rowNum = 0;
			int colNum = 0;
			switch(sheetName) {
				case "Consolidated Transaction Report":
					rowNum = Constant.getCTRRowNumber();
					colNum = Constant.getCTRColNumber();
					break;
				case "GL Balance Report":
					rowNum = Constant.getGLRowNumber();
					colNum = Constant.getGLColNumber();
			}
			totalNumberOfColumns = mySheet.getRow(rowNum).getLastCellNum();
			excelValidationValues = new String[totalNumberOfRows-(rowNum-1)][totalNumberOfColumns-colNum];
			excelDownloadedValidationValues = new String[totalNumberOfRows-(rowNum-1)][totalNumberOfColumns-colNum];

			for (int numberOfRowCount = rowNum; numberOfRowCount <= totalNumberOfRows; numberOfRowCount++) {
				Row row = mySheet.getRow(numberOfRowCount);
				Row row1 = mySheet1.getRow(numberOfRowCount);
				for (int numberOfColumnCount = colNum; numberOfColumnCount < totalNumberOfColumns; numberOfColumnCount++) {
					DataFormatter df=new DataFormatter();
					Cell cell = row.getCell(numberOfColumnCount);
					Cell cell1 = row1.getCell(numberOfColumnCount);
//					String value = cell.getStringCellValue();
					String value = df.formatCellValue(cell);
					String value1 = df.formatCellValue(cell1);
					System.out.println("Baseline Value " +value + " Downloaded Value " + value1);
					excelValidationValues[numberOfRowCount - rowNum][numberOfColumnCount-colNum] = value;
					excelDownloadedValidationValues[numberOfRowCount - rowNum][numberOfColumnCount-colNum] = value1;
				}

			}
			file.close();
			file1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void sortingDownloadedFileValues(String sheetName) {
		log.info("Applying Sorting");

		Comparator<String[]> dateComparator;
		if(sheetName.equalsIgnoreCase("GL Balance Report")) {
			dateComparator = new Comparator<String[]>() {
				@Override
				public int compare(String[] row1, String[] row2) {
					String value1 = row1[2];
					String value2 = row2[2];
					return value1.compareTo(value2);
				}
			};
		}else{
			dateComparator = new Comparator<String[]>() {
				@Override
				public int compare(String[] row1, String[] row2) {
					String value1 = row1[8];
					String value2 = row2[8];
					return value1.compareTo(value2);
				}
			};
		}

		// Sort the 2D array based on column 9(CTR), 3(GL) (Account Name)
		Arrays.sort(excelDownloadedValidationValues,1, excelDownloadedValidationValues.length,dateComparator);
		Arrays.sort(excelValidationValues,1, excelValidationValues.length,dateComparator);
		System.out.print("Sorted Values Downloaded Report Excel File");
		for (String[] row : excelDownloadedValidationValues) {
			for (String value : row) {
				System.out.print(value + " ");
			}
			System.out.println();
		}
		System.out.print("Sorted Values Of Baseline Excel File");
		for (String[] row : excelValidationValues) {
			for (String value : row) {
				System.out.print(value + " ");
			}
			System.out.println();
		}

	}

	public void compareScheduleValues(String[][] downloadedValues, String[][] baseValues, String sheetName, String standard) {
		double actualDifferenceValue = 0.0;

		for (int headingCount = 0; headingCount < baseValues[0].length; headingCount++) {
			for (int rowCount = 1; rowCount < baseValues.length; rowCount++) {
				if (baseValues[rowCount][headingCount].equals(downloadedValues[rowCount][headingCount])) {
					System.out.println("Values are matching : " + baseValues[rowCount][headingCount].equals(downloadedValues[rowCount][headingCount])
							+ ", Expected Result is: " + baseValues[rowCount][headingCount] + " and Actual Result is : " + downloadedValues[rowCount][headingCount]);
				} else {
					if (baseValues[rowCount][headingCount].contains(".") || downloadedValues[rowCount][headingCount].contains(".")) {

						if (baseValues[rowCount][headingCount].contains("%") || downloadedValues[rowCount][headingCount].contains("%")) {
							downloadedValues[rowCount][headingCount] = downloadedValues[rowCount][headingCount].replace("%", "");
							baseValues[rowCount][headingCount] = baseValues[rowCount][headingCount].replace("%", "");
						}
						if(baseValues[rowCount][headingCount].contains("(") || downloadedValues[rowCount][headingCount].contains("(")) {
							downloadedValues[rowCount][headingCount] = downloadedValues[rowCount][headingCount].replace("(", "-").replace(")", "");
							baseValues[rowCount][headingCount] = baseValues[rowCount][headingCount].replace("(", "-").replace(")", "");
						}

						actualDifferenceValue = Math.abs(Double.parseDouble(downloadedValues[rowCount][headingCount].replaceAll(",", ""))
								- Double.parseDouble(baseValues[rowCount][headingCount].replaceAll(",", "")));
						if (MasterHooks.configurationProperties.get().getAllowedDifferenceToSkip() >= actualDifferenceValue) {
							System.out.println("Values are not matching but actual difference is under the allowed difference : "
									+ baseValues[rowCount][headingCount].equals(downloadedValues[rowCount][headingCount]));
							System.out.println("\t" + "Base Excel value is : " + baseValues[rowCount][headingCount]);
							System.out.println("\t" + "Downloaded Report value is : " + downloadedValues[rowCount][headingCount]);
							System.out.println("\t" + "Allowed difference value is: " + MasterHooks.configurationProperties.get().getAllowedDifferenceToSkip()
									+ " and actual difference value is : " + actualDifferenceValue);
						} else {
							System.out.println("Values are not matching : " + baseValues[rowCount][headingCount].equals(downloadedValues[rowCount][headingCount]));
							System.out.println("\t" + "Base Excel value is : " + baseValues[rowCount][headingCount]);
							System.out.println("\t" + "Downloaded Report value is : " + downloadedValues[rowCount][headingCount]);
							System.out.println("\t" + "Allowed difference value is : " + MasterHooks.configurationProperties.get().getAllowedDifferenceToSkip()
									+ " and actual difference value is : " + actualDifferenceValue);
							int rowNumber=0;
							int columnNumber=0;
							switch(sheetName) {
								case "Consolidated Transaction Report":
									rowNumber = rowCount + Constant.getCTRRowNumber() + 1;
									columnNumber = headingCount + Constant.getCTRColNumber() + 1;
									break;
								case "GL Balance Report":
									rowNumber = rowCount + Constant.getGLRowNumber() + 1;
									columnNumber = headingCount + Constant.getGLColNumber() + 1;
									break;
							}

							try {
								writeMisMatchExcelValues(sheetName,baseValues[0][headingCount] +" "+ rowNumber + ":" + columnNumber, baseValues[rowCount][headingCount], downloadedValues[rowCount][headingCount],
										MasterHooks.configurationProperties.get().getAllowedDifferenceToSkip(), actualDifferenceValue, standard);

							} catch (IOException ex) {
								ex.printStackTrace();
							}
						}
					} else {
						System.out.println("Values are not matching : " + baseValues[rowCount][headingCount].equals(downloadedValues[rowCount][headingCount]));
						System.out.println("\t" + "Base Excel value is : " + baseValues[rowCount][headingCount]);
						System.out.println("\t" + "Downloaded Report value is : " + downloadedValues[rowCount][headingCount]);
						int rowNumber=0;
						int columnNumber=0;
						switch(sheetName) {
							case "Consolidated Transaction Report":
								rowNumber = rowCount + Constant.getCTRRowNumber() + 1;
								columnNumber = headingCount + Constant.getCTRColNumber() + 1;
								break;
						}

						try {
							writeMisMatchExcelValues(sheetName,baseValues[0][headingCount] +" "+ rowNumber + ":" + columnNumber, baseValues[rowCount][headingCount], downloadedValues[rowCount][headingCount],
									MasterHooks.configurationProperties.get().getAllowedDifferenceToSkip(), actualDifferenceValue, standard);

						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}
	}

	public void writeMisMatchExcelValues(String sheetName, String fieldName, String excelValue, String nlaValue, Double allowedDifference,
										 Double actualDifference, String standard) throws IOException {
		copyMisMatchExcelTemplateFile(standard,sheetName);
		MasterHooks.checkForValidationFailures.set("Failed");
		FileInputStream file = new FileInputStream(misMatchExcelFile);
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		int sheetIndex = workbook.getSheetIndex(sheetName);
		XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
		XSSFRow row;

		int rowNumber = 0;
		int columnNumber = 0;

		rowNumber = sheet.getLastRowNum() + 1;

		row = sheet.createRow(rowNumber++);
		row.createCell(columnNumber++).setCellValue(MasterAgreement_PageObject.testCaseName.get());
		sheet.autoSizeColumn(columnNumber);
		row.createCell(columnNumber++).setCellValue(sheetName);
		sheet.autoSizeColumn(columnNumber);
		row.createCell(columnNumber++).setCellValue(fieldName);
		sheet.autoSizeColumn(columnNumber);
		row.createCell(columnNumber++).setCellValue(excelValue);
		sheet.autoSizeColumn(columnNumber);
		row.createCell(columnNumber++).setCellValue(nlaValue);
		sheet.autoSizeColumn(columnNumber);
		row.createCell(columnNumber++).setCellValue(allowedDifference);
		sheet.autoSizeColumn(columnNumber);
		row.createCell(columnNumber++).setCellValue(actualDifference);
		sheet.autoSizeColumn(columnNumber);

		columnNumber = 0;

		file.close();
		FileOutputStream fileOutput = new FileOutputStream(misMatchExcelFile);
		workbook.write(fileOutput);
		fileOutput.close();
		workbook.close();
	}

	public void copyMisMatchExcelTemplateFile(String standard, String sheetName) {
		try {
			XSSFWorkbook excelWorkbook=null;
			File validationFolder = new File("Automation-Results/validationResults/" + MasterHooks.configurationProperties.get().getWhichBaselineToUseForValidation() + "/"
					+ MasterAgreement_PageObject.testCaseName.get() + "/");
			misMatchExcelFile = new File("Automation-Results/validationResults/" + MasterHooks.configurationProperties.get().getWhichBaselineToUseForValidation() + "/"
					+ MasterAgreement_PageObject.testCaseName.get() + "/"
					+ MasterAgreement_PageObject.testCaseName.get() + "_" + standard + "_Output.xlsx");
			if(!validationFolder.exists()){
				validationFolder.mkdirs();
			}
			if (misMatchExcelFile.exists()){
				FileInputStream	file = new FileInputStream(misMatchExcelFile);
				excelWorkbook = (XSSFWorkbook) WorkbookFactory.create(file);
			}else{
				excelWorkbook = new XSSFWorkbook();
			}

			int num=excelWorkbook.getSheetIndex(sheetName);
			if(num==-1){
				XSSFSheet excelSheet = excelWorkbook.createSheet(sheetName);
				XSSFRow row;

				int rowNumber = 0;
				int columnNumber = 0;

				row = excelSheet.createRow(rowNumber++);
				row.createCell(columnNumber).setCellValue("Test Case Number");
				excelSheet.autoSizeColumn(columnNumber++);
				row.createCell(columnNumber).setCellValue("Sheet Name");
				excelSheet.autoSizeColumn(columnNumber++);
				row.createCell(columnNumber).setCellValue("Field Name");
				excelSheet.autoSizeColumn(columnNumber++);
				row.createCell(columnNumber).setCellValue("Excel Value");
				excelSheet.autoSizeColumn(columnNumber++);
				row.createCell(columnNumber).setCellValue("Application Value");
				excelSheet.autoSizeColumn(columnNumber++);
				row.createCell(columnNumber).setCellValue("Allowed Difference");
				excelSheet.autoSizeColumn(columnNumber++);
				row.createCell(columnNumber).setCellValue("Actual Difference");
				excelSheet.autoSizeColumn(columnNumber++);
				FileOutputStream outputStream = new FileOutputStream(misMatchExcelFile);
				excelWorkbook.write(outputStream);
				outputStream.close();
			}
			excelWorkbook.close();
//file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createGLJob(String jobType,String reportLevel, DataTable dataTable) {
		log.info("Creating GL Balance Job");
		List<Map<String, String>> job = dataTable.asMaps(String.class, String.class);
		try {
			waitUntilLoadingSpinnerIsShown("sapReportPageLoader");
			waitUntilLoadingSpinnerIsGone("sapReportPageLoader");
			if(!jobType.equalsIgnoreCase("Scheduled Job")) {
				waitUntilTextEqualsIgnoreCase("#q-app .q-table tr th:nth-child(10)","Account Types",10);
				applyCreatedByFilter("GL Report");
			}else{
				waitUntilTextEqualsIgnoreCase("#q-app .q-table tr th:nth-child(9)","Account Types",10);
			}
			int recordBeforeJob;
			if(waitUntilElementIsShown("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item",6)){
				String records = driver.findElement(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
				recordBeforeJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
			}else {
				recordBeforeJob=0;
			}

			if(jobType.equalsIgnoreCase("Scheduled Job")) {
				waitAndClickOnElement(addScheduleJobButton);
				waitAndClickOnElement(ScheduleJobName);
				sendingValueToWebElement("reportName", ScheduleJobName, job.get(0).get("Name"));
				waitAndClickOnElement("cancelButton",".q-dialog .q-card #cancel-btn");
				// Repeating the above lines because of dropdown null values issue
				waitTillWebElementIsVisible("addScheduledJobButton", addScheduleJobButton);
				waitAndClickOnElement(addScheduleJobButton);
				waitAndClickOnElement(ScheduleJobName);
				sendingValueToWebElement("reportName", ScheduleJobName, job.get(0).get("Name"));
			}else{
				waitAndClickOnElement(addJobButton);
			}
			waitTillWebElementIsVisible("createJobPopUp", ".q-dialog  .qcard-dialogue");
			selectProfileForReports("GLBalanceProfile", MasterHooks.reportProfileID.get());
			if(job.get(0).get("Calendar Type").equalsIgnoreCase("Fiscal Variant")){
				waitTillWebElementIsVisible("fromYear",fromYear);
				sendingValueToWebElement("fromYear",fromYear,job.get(0).get("From Year"));
				clickOnDropDownAndSelectValue("fromPostingYear",fromPostingPeriod, job.get(0).get("From Posting Period"));
				sendingValueToWebElement("toYear",toYear, job.get(0).get("To Year"));
				clickOnDropDownAndSelectValue("toPostingYear",toPostingYear, job.get(0).get("To Posting Year"));
			}else{
				waitTillWebElementIsVisible("dateRangeType",dateRangeType);
				clickOnDropDownAndSelectValue("dateRangeType",dateRangeType, job.get(0).get("Date Range Type"));
				sendingValueToWebElement("startDateInput",startDateInput, job.get(0).get("Period Start"));
				sendingValueToWebElement("endDateInput",endDateInput, job.get(0).get("Period End"));
			}
//			clickOnDropDownAndSelectValue("principalPosition",principalPosition,
//					masterAgreement_PageObject.get().getInputValues().get(reportLevel).get("eventdata").get("Principal Position").get(0));
			clickOnDropDownAndSelectValue("accountingStandard",accountingStandard, job.get(0).get("Accounting Standard"));
			clickOnDropDownAndSelectValue("postingCategory",postingCategoryFilterType, job.get(0).get("Posting Category"));
			clickOnDropDownAndSelectValue("currencyType",exchangeRateTypes, job.get(0).get("Currency Type"));
			if(job.get(0).get("Lease Type Filter").equalsIgnoreCase("List")){
				clickOnDropDownAndSelectValue("leaseTypeFilterType",leaseTypeFilterType, job.get(0).get("Lease Type Filter"));
				clickOnDropDownAndSelectCheckBoxes("leaseTypes",leaseTypes, job.get(0).get("Lease Type"));
			}
			if(job.get(0).get("Classification Filter Type").equalsIgnoreCase("List")){
				clickOnDropDownAndSelectValue("leaseClassificationFilterType",leaseClassificationFilterType, job.get(0).get("Classification Filter Type"));
				clickOnDropDownAndSelectCheckBoxes("leaseClassifications",leaseClassifications, job.get(0).get("Lease Classification"));
			}
			if (job.get(0).get("Internal Asset Class Filter Type").equalsIgnoreCase("List")) {
				clickOnDropDownAndSelectValue("internalAssetClassFilterType", internalAssetClassFilterType, job.get(0).get("Internal Asset Class Filter Type"));
				clickOnDropDownAndSelectCheckBoxes("internalAssetClasses", internalAssetClasses, job.get(0).get("Internal Asset Classes"));
				waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
			}
			if (job.get(0).get("Vendor Filter Type").equalsIgnoreCase("List")) {
				clickOnDropDownAndSelectValue("vendorFilterType", vendorFilterType, job.get(0).get("Vendor Filter Type"));
				clickOnDropDownToTypeAndSelectCheckBox("vendors", vendors, job.get(0).get("Vendor"));
				waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
			}
			if (job.get(0).get("Account Type Filter Type").equalsIgnoreCase("List")) {
				clickOnDropDownAndSelectValue("accountTypeFilterType", accountTypeFilterType, job.get(0).get("Account Type Filter Type"));
				clickOnDropDownAndSelectCheckBoxes("accountTypes", accountTypes, job.get(0).get("Account Type"));
				waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
			}
			if (job.get(0).get("General Ledger Account Filter Type").equalsIgnoreCase("List")) {
				clickOnDropDownAndSelectValue("generalLedgerAccountType", generalLedgerAccountType, job.get(0).get("General Ledger Account Filter Type"));
				clickOnDropDownToTypeAndSelectCheckBox("generalLedgerAccounts", generalLedgerAccounts, job.get(0).get("General Ledger Account"));
				waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
			}
			String values = "";
			HashMap<String, String> threadMap = MasterHooks.map.get();
			String objectType=job.get(0).get("Object Type");
			if (!objectType.equalsIgnoreCase("null")) {
				clickOnDropDownAndSelectValue("objectType", nfsEntityType, job.get(0).get("Object Type"));
				if (job.get(0).get("Object List").equalsIgnoreCase("All")) {
					String mappedKey = mapObjectType(objectType);
					if (!mappedKey.isEmpty()) {
						values = getRecordId(mappedKey, threadMap, false);
					}
					clickOnDropDownToTypeAndSelectCheckBox("objectListValue", nfsIdInput, values);
				} else {
					String objectListValue= job.get(0).get("Object List");
					values = getRecordId(objectListValue, threadMap, true);
					clickOnDropDownToTypeAndSelectCheckBox("objectListValue", nfsIdInput, values);
				}
			}
			if (jobType.equalsIgnoreCase("Scheduled Job")) {
				reportingModulePageObject.reportScheduling(reportLevel,job);
			}
			if(!(submitButton.isEnabled())) {
				Assert.assertFalse(disableSubmit.isEnabled(), "Mandatory fields are missing");
				log.info("Mandatory fields are missing");
			}
			else {
				clickOnSubmitPopup("Submit / Add");
				waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
				waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
				//Waiting for page records to Increase
				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
				wait.until(new Function<WebDriver, Boolean>() {
					@Override
					public Boolean apply(WebDriver driver) {
						String records = driver.findElement(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
						int recordAfterJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
						if (recordAfterJob != recordBeforeJob) {
							return true;
						} else {
							return false;
						}
					}
				});
				log.info("GL Balance Job Added Successfully");
			}
		}catch(Exception e){e.printStackTrace();}


	}

	public void applyCreatedByFilter(String reportName){
		log.info("Applying Created By filter in Jobs section");
		waitUntilLoadingSpinnerIsShown("nlaBatchJobListLoader");
		waitUntilLoadingSpinnerIsGone("nlaBatchJobListLoader");
		try {
			waitAndClickOnElement(userBtn);
		} catch (InterruptedException e) { e.printStackTrace();}
		waitTillWebElementIsVisible("userEmail", userEmail);
		if(reportName.equalsIgnoreCase("GL Report")) {
			if (!(driver.findElements(By.cssSelector("#q-app #search-created-by .q-icon")).size() == 1)) {
				createdByFilter.click();
				sendingValueToWebElement("createdByFilter", createdByFilter, userEmail.getText());
				waitUntilLoadingSpinnerIsShown("nlaBatchJobListLoader");
				waitUntilLoadingSpinnerIsGone("nlaBatchJobListLoader");
			}
		}else{
			if (!(driver.findElements(By.cssSelector("#q-app tr:nth-child(1) td:nth-child(3) button.q-icon")).size() == 1)) {
				createdByFilterCTR.click();
				sendingValueToWebElement("createdByFilter", createdByFilterCTR, userEmail.getText());
				waitUntilLoadingSpinnerIsShown("nlaBatchJobListLoader");
				waitUntilLoadingSpinnerIsGone("nlaBatchJobListLoader");
			}
		}
		log.info("Applied Created By filter in Jobs section");
	}

	public void createSAPScheduledJob(String reportLevel, DataTable dataTable) {
		log.info("Creating SAP Posting Scheduled Job");
		List<Map<String, String>> job = dataTable.asMaps(String.class, String.class);
		try {
			boolean isChecked = waitUntilTextEqualsIgnoreCase("#q-app [role='tablist'] .q-tab--active", "Posting Job", 5);
			waitTillWebElementIsVisible("sapProfileAddButton", addProfileButton);
			int recordBeforeJob;
			if (driver.findElements(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).size() == 0) {
				recordBeforeJob = 0;
			} else {
				String records = driver.findElement(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
				recordBeforeJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
			}
			waitUntilTextEqualsIgnoreCase(".q-table__container .q-tr .text-center:nth-child(6)","Last Successful Run",20);
			waitTillWebElementIsVisible("addProfileButton","#q-app .q-page .q-btn-group .q-btn.bg-primary");
			waitAndClickOnElement(addProfileButton);
			waitTillWebElementIsVisible("createProfilePopUp", ".q-dialog  .qcard-dialogue");
			waitTillWebElementIsVisible("batchSizeInput", ".q-dialog .q-card input[aria-label='Batch Size *']");
			sendingValueToWebElement("batchSizeSPB", batchSizeInput, job.get(0).get("Batch Size"));
//			String postingStatus = job.get(0).get("Posting Statues");
//			if (!(postingStatus.equalsIgnoreCase("null"))) {
////				if (postingStatus.equalsIgnoreCase("Open")) {
//					clickOnDropDownAndSelectCheckBoxes("postingStatusesSPB", postingStatuses, postingStatus);
////				} else {
////					clickOnDropDownAndSelectCheckBoxes("postingStatusesSPB", postingStatuses, "Open");
////				}
//				waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
//			}
			reportingModulePageObject.reportScheduling(reportLevel, job);
			selectProfile(MasterHooks.sapProfileId.get());
			WaitUntilElementIsClickable(addSPBScheduleJob);
			waitAndClickOnElement(addSPBScheduleJob);
			waitUntilLoadingSpinnerIsShown("nlaDropDownPopUp");
			waitUntilLoadingSpinnerIsGone("nlaDropDownPopUp");
			Thread.sleep(1000);
			log.info("Created SAP Posting Scheduled Job");
			//Waiting for page records to Increase
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					String records = driver.findElement(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
					int recordAfterJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
					if (recordAfterJob != recordBeforeJob) {
						return true;
					} else {
						return false;
					}
				}
			});}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editSchJobSAP(String reportType) {
		try {
			waitTillWebElementIsVisible("clickEditSchJob",clickEditSchJob);
			waitAndClickOnElement(clickEditSchJob);
			waitTillWebElementIsVisible("editSchJob", ".q-dialog  .qcard-dialogue");
			waitAndClickOnElement(yearlyTab);
			waitAndClickOnElement(monthBox1);
			waitAndClickOnElement(monthBox2);
			if (reportType.equalsIgnoreCase("Consolidated Transaction Report")) {
				waitAndClickOnElement(consolidatedJobAddButton);
			}
			if (reportType.equalsIgnoreCase("GL Balance Report")) {
				clickOnSubmitPopup("Submit / Add");
			}
			log.info("Schedule job edited Successfully");
		}

		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	// Selecting System from filter at SAP profile (need to move this in common if it's common)
	public void clickOnFilterAndSelectOption(String webElementName, WebElement webElement, String value) {
		try {
			waitTillWebElementIsVisible(webElementName, webElement);
			WaitUntilElementIsClickable(webElement);
			Thread.sleep(500);
			waitAndClickOnElement(webElement);
			final String dropDownValues;
			dropDownValues = ".q-menu .q-item:nth-child(1)";
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					if (!driver.findElement(By.cssSelector(".q-menu .q-item:nth-child(1)")).getText().equalsIgnoreCase("No results")) {
						return true;
					}
					return false;
				}
			});
			int dropDownOptionSize;
			dropDownOptionSize=driver.findElements(By.cssSelector(".q-menu .q-item")).size();

			String optionToClickWithIndex="";
			String innerTextValue="";
			for (int cssIndex = 1; cssIndex <= dropDownOptionSize; cssIndex++) {
				innerTextValue="";
				waitTillWebElementIsVisible("DropDownValue",".q-menu .q-item:nth-child(" + cssIndex + ") .q-item__section--main");
				innerTextValue = driver.findElement(By.cssSelector(".q-menu .q-item:nth-child(" + cssIndex + ") .q-item__section--main")).getText();
				if (value.equals(innerTextValue)) {
					optionToClickWithIndex = ".q-menu .q-item:nth-child(" + cssIndex + ") .q-item__section--main";
					break;
				}
			}
			if(optionToClickWithIndex.equalsIgnoreCase("")){
				log.info("Trying for 2nd Time");
				dropDownOptionSize=driver.findElements(By.cssSelector(".q-menu .q-item")).size();
				for (int cssIndex = 1; cssIndex <= dropDownOptionSize; cssIndex++) {
					waitTillWebElementIsVisible("DropDownValue",".q-menu .q-item:nth-child(" + cssIndex + ") .q-item__section--main");
					innerTextValue = driver.findElement(By.cssSelector(".q-menu .q-item:nth-child(" + cssIndex + ") .q-item__section--main")).getText();
					if (value.equals(innerTextValue)) {
						optionToClickWithIndex = ".q-menu .q-item:nth-child(" + cssIndex + ") .q-item__section--main";
						break;
					}
				}
			}
			if (optionToClickWithIndex.equalsIgnoreCase("")) {
				log.error("Dropdown value is not matching, expecting value should be > " + value);
			}
			waitAndClickOnElement("DropdownValueSelected",optionToClickWithIndex);
		}catch (Exception e){e.printStackTrace();}
	}

	public void validatingActionButton() {
		waitTillWebElementIsEnabled("ID", "#q-app .q-table .q-tr:nth-child(1) .q-td:nth-child(1)");
		try {
			waitAndClickOnElement(driver.findElement(By.cssSelector("#q-app .q-table .q-tr:nth-child(1) .q-td:nth-child(1)")));
			waitUntilLoadingSpinnerIsShown("nlaJournalSpinner");
			waitUntilLoadingSpinnerIsGone("nlaJournalSpinner");
			waitAndClickOnElement(driver.findElement(By.cssSelector(".main-content-area .q-card__section .q-tabs .q-tab:nth-child(1)")));
			waitTillWebElementIsVisible("firstSectionmsg", ".hover-labels-panel .hover-label-item:nth-child(1)");
			String firstSectionmsg=driver.findElement(By.cssSelector(".hover-labels-panel .hover-label-item:nth-child(1)")).getText();
			if(firstSectionmsg.equalsIgnoreCase("General Information")) {
				log.info("Correct header name: " + firstSectionmsg + " is showing");
			}
			else{
				Assert.fail("Wrong header name: " +firstSectionmsg+ " is showing");
			}
			Thread.sleep(1000);
			//waitAndClickOnElement("ExpandButton", ".q-card__section.text-primary:nth-child(2) .q-expansion-item.overflow-hidden:nth-child(1) .cursor-pointer.text-white:nth-child(4)");
			//Thread.sleep(1000);
			//waitAndClickOnElement("ExpandButton", ".q-card__section.text-primary:nth-child(2) .q-expansion-item.overflow-hidden:nth-child(2) .cursor-pointer.text-white:nth-child(4)");
			waitTillWebElementIsVisible("applicationReferences", ".hover-labels-panel .hover-label-item:nth-child(2)");
			String secondSectionmsg=driver.findElement(By.cssSelector(".hover-labels-panel .hover-label-item:nth-child(2)")).getText();
			if(secondSectionmsg.equalsIgnoreCase("Application References")) {
				log.info("Correct header name: " + secondSectionmsg + " is showing");
			}
			else{
				Assert.fail("Wrong header name: " +secondSectionmsg+ " is showing");
			}
//			Thread.sleep(1000);
//			waitAndClickOnElement("ExpandButton", ".q-card__section.text-primary:nth-child(2) .q-expansion-item.overflow-hidden:nth-child(2) .cursor-pointer.text-white:nth-child(4)");
//			Thread.sleep(1000);
//			waitAndClickOnElement("ExpandButton", ".q-card__section.text-primary:nth-child(2) .q-expansion-item.overflow-hidden:nth-child(3) .cursor-pointer.text-white:nth-child(4)");
//			String thirdSectionmsg=driver.findElement(By.cssSelector(".q-card__section.text-primary:nth-child(2) .q-expansion-item.overflow-hidden:nth-child(3) .q-item__label")).getText();
//			if(thirdSectionmsg.equalsIgnoreCase("Ledger Entries")) {
//				log.info("Correct header name: " + thirdSectionmsg + " is showing");
//			}
//			else{
//				Assert.fail("Wrong header name: " +thirdSectionmsg+ " is showing");
//			}
//			Thread.sleep(1000);

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void createJournalVoucherDefinitionTab(DataTable dt) {
		log.info("Creating New Journal Voucher definition tab");
		try {
			waitUntilLoadingSpinnerIsShown("nlaLoadingPage");
			waitUntilLoadingSpinnerIsGone("nlaLoadingPage");
			waitTillWebElementIsVisible("jvCreateButton",".q-page .q-btn#pageActionContextMenuAddJournalVoucherBtn");
			waitAndClickOnElement("jvCreateButton",".q-page .q-btn#pageActionContextMenuAddJournalVoucherBtn");
			List<Map<String, String>> journalVoucher = dt.asMaps(String.class, String.class);
			//Definition Tab
			waitTillWebElementIsVisible("jvPopUp", ".q-dialog .qcard-dialogue");
			waitTillWebElementIsVisible("nameJV", nameJournalVoucher);
			WaitUntilElementIsClickable(nameJournalVoucher);
			sendingValueToWebElement("nameJV", nameJournalVoucher, journalVoucher.get(0).get("Name"));
			waitTillWebElementIsVisible("leaseAreaJV", leaseAreaJournalVoucher);
			waitTillWebElementIsVisible("leaseAreaJV", leaseAreaJournalVoucher);
			clickOnDropDownToTypeAndSelectValue("leaseAreaJV", leaseAreaJournalVoucher, journalVoucher.get(0).get("Lease Area"));
			waitTillWebElementIsVisible("businessAreaJV", businessAreaJournalVoucher);
			clickOnDropDownToTypeAndSelectValue("businessAreaJV", businessAreaJournalVoucher, journalVoucher.get(0).get("Business Area"));
			waitTillWebElementIsVisible("systemJV", systemJournalVoucher);
			clickOnDropDownToTypeAndSelectValue("systemJV", systemJournalVoucher, journalVoucher.get(0).get("ERP System"));
			waitTillWebElementIsVisible("companyJV", companyJournalVoucher);
			clickOnDropDownToTypeAndSelectValue("systemJV", companyJournalVoucher, journalVoucher.get(0).get("Company"));
			waitTillWebElementIsVisible("currencyJV", currencyJournalVoucher);
			clickOnDropDownToTypeAndSelectValue("currencyJV", currencyJournalVoucher, journalVoucher.get(0).get("Currency"));
			waitTillWebElementIsVisible("documentTypeJV", documentTypeJournalVoucher);
			clickOnDropDownToTypeAndSelectValue("documentTypeJV", documentTypeJournalVoucher, journalVoucher.get(0).get("Document Type"));
			waitTillWebElementIsVisible("accountingStandardJV", accountingStandardJournalVoucher);
			clickOnDropDownToTypeAndSelectValue("accountingStandardJV", accountingStandardJournalVoucher, journalVoucher.get(0).get("Accounting Standard"));
			waitTillWebElementIsVisible("leaseClassificationJV", leaseClassificationJournalVoucher);
			clickOnDropDownAndSelectValue("leaseClassificationJV", leaseClassificationJournalVoucher, journalVoucher.get(0).get("Lease Classification"));
			waitTillWebElementIsVisible("postingDateJV", postingDateJournalVoucher);
			WaitUntilElementIsClickable(postingDateJournalVoucher);
			waitAndClickOnElement(postingDateJournalVoucher);
			sendingValueToWebElement("postingDateJV", postingDateJournalVoucher, journalVoucher.get(0).get("Posting Date"));
			waitTillWebElementIsVisible("documentDateJV", documentDateJournalVoucher);
			WaitUntilElementIsClickable(documentDateJournalVoucher);
			waitAndClickOnElement(documentDateJournalVoucher);
			sendingValueToWebElement("documentDateJV", documentDateJournalVoucher, journalVoucher.get(0).get("Document Date"));
			waitTillWebElementIsVisible("principalPositionJV", principalPositionJournalVoucher);
			clickOnDropDownAndSelectValue("principalPositionJV", principalPositionJournalVoucher, journalVoucher.get(0).get("Principal Position"));
			//Automatic Reversal
			if(journalVoucher.get(0).get("Automatic Reversal").equalsIgnoreCase("Yes")){
				waitTillWebElementIsVisible("reversalPostingDateJournalVoucher", reversalPostingDateJournalVoucher);
				sendingValueToWebElement("reversalPostingDateJournalVoucher", reversalPostingDateJournalVoucher, journalVoucher.get(0).get("Reversal Posting Date"));
				waitTillWebElementIsVisible("reversalDocumentDateJournalVoucher", reversalDocumentDateJournalVoucher);
				sendingValueToWebElement("reversalDocumentDateJournalVoucher", reversalDocumentDateJournalVoucher, journalVoucher.get(0).get("Reversal Posting Date"));
				waitTillWebElementIsVisible("reversalReasonJournalVoucher", reversalReasonJournalVoucher);
				WaitUntilElementIsClickable(reversalReasonJournalVoucher);
				clickOnDropDownAndSelectValue("reversalReasonJournalVoucher",reversalReasonJournalVoucher,journalVoucher.get(0).get("Reversal Reason"));
			}else{
				waitTillWebElementIsVisible("automaticReversalJournalVoucher",automaticReversalJournalVoucher);
				waitAndClickOnElement(automaticReversalJournalVoucher);
			}
			waitAndClickOnElement("wbs",".q-dialog .qcard-dialogue #cost-object-work-breakdown-structure");
			waitTillWebElementIsVisible("wbsJV", wbsJournalVoucher);
			clickOnDropDownToTypeAndSelectValue("wbsJV", wbsJournalVoucher, journalVoucher.get(0).get("WBS"));
			waitTillWebElementIsVisible("segmentJV", segmentJournalVoucher);
			clickOnDropDownToTypeAndSelectValue("segmentJV", segmentJournalVoucher, journalVoucher.get(0).get("Segment"));
			waitTillWebElementIsVisible("networkJV", networkJournalVoucher);
			clickOnDropDownToTypeAndSelectValue("networkJV", networkJournalVoucher, journalVoucher.get(0).get("Network"));
//			WaitUntilElementIsClickable(networkJournalVoucher);
//			waitAndClickOnElement(networkJournalVoucher);
//			waitAndClickOnElement(networkValueJournalVoucher);
			waitTillWebElementIsVisible("functionalAreaJV", functionalAreaJournalVoucher);
			clickOnDropDownToTypeAndSelectValue("functionalAreaJV", functionalAreaJournalVoucher, journalVoucher.get(0).get("Functional Area"));
			waitTillWebElementIsVisible("paymentTermJV", paymentTermJournalVoucher);
			clickOnDropDownToTypeAndSelectValue("paymentTermJV", paymentTermJournalVoucher, journalVoucher.get(0).get("Payment Term"));
			waitTillWebElementIsVisible("paymentBlockJV", paymentBlockJournalVoucher);
			clickOnDropDownAndSelectValue("paymentBlockJV", paymentBlockJournalVoucher, journalVoucher.get(0).get("Payment Block"));
			waitTillWebElementIsVisible("paymentMethodJV", paymentMethodJournalVoucher);
			clickOnDropDownAndSelectValue("paymentMethodJV", paymentMethodJournalVoucher, journalVoucher.get(0).get("Payment Method"));
			waitTillWebElementIsVisible("plantJV", plantJournalVoucher);
			clickOnDropDownToTypeAndSelectValue("plantJV", plantJournalVoucher, journalVoucher.get(0).get("Plant"));
			waitTillWebElementIsVisible("internalOrderJV", internalOrderJournalVoucher);
			clickOnDropDownToTypeAndSelectValue("internalOrderJV", internalOrderJournalVoucher, journalVoucher.get(0).get("Internal Order"));
//			//Reference Key
//			waitTillWebElementIsVisible("referenceKeyJV", referenceKeyJournalVoucher);
//			WaitUntilElementIsClickable(referenceKeyJournalVoucher);
//			waitAndClickOnElement(referenceKeyJournalVoucher);
//			sendingValueToWebElement("referenceKeyJV", referenceKeyJournalVoucher,journalVoucher.get(0).get("Reference Key"));
//			//Reference Key 1
//			waitTillWebElementIsVisible("referenceKey1JV", referenceKey1JournalVoucher);
//			WaitUntilElementIsClickable(referenceKey1JournalVoucher);
//			waitAndClickOnElement(referenceKey1JournalVoucher);
//			sendingValueToWebElement("referenceKey1JV", referenceKey1JournalVoucher,journalVoucher.get(0).get("Reference Key 1"));
//			//Reference Key 2
//			waitTillWebElementIsVisible("referenceKey2JV", referenceKey2JournalVoucher);
//			WaitUntilElementIsClickable(referenceKey2JournalVoucher);
//			waitAndClickOnElement(referenceKey2JournalVoucher);
//			sendingValueToWebElement("referenceKey2JV",referenceKey2JournalVoucher,journalVoucher.get(0).get("Reference Key 2"));
//			waitTillWebElementIsVisible("referenceKeyAddBtn", addrefrenceKeybtnJournalVoucher);
//			//Add Refrence Keys
//			WaitUntilElementIsClickable(addrefrenceKeybtnJournalVoucher);
//			waitAndClickOnElement(addrefrenceKeybtnJournalVoucher);

		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

	public void createJournalVoucherEntriesTab(DataTable dt) {
		log.info("Creating New Journal Voucher entries tab");

		try {
			List<Map<String, String>> journalVoucherList = dt.asMaps(String.class, String.class);
			// Click Entries tab first
			waitAndClickOnElement(entriesTabJournalVoucher);
			log.info("Entries tab is clicked");
			for (int i = 0; i < journalVoucherList.size(); i++) {
				Map<String, String> row = journalVoucherList.get(i);
				// Account Number
				waitTillWebElementIsVisible("accountNumberJV", accountNumberJournalVoucher);
				clickOnDropDownToTypeAndSelectValue("accountNumberJV", accountNumberJournalVoucher, row.get("Account Number"));
				// Get Account Category after selecting Account Number
				String accountCategory = driver.findElement(By.cssSelector(".q-card .q-table .q-tr:nth-child(1) .q-td:nth-child(3)")).getText();
				log.info("Account Category identified: " + accountCategory);
				// Debit Entry Flow
				if (!row.get("Debit Contract").equalsIgnoreCase("NULL")) {
					WaitUntilElementIsClickable(debitContractJournalVoucher);
					waitAndClickOnElement(debitContractJournalVoucher);
					clearField(debitContractJournalVoucher);
					sendingValueToWebElement("debitContractJV", debitContractJournalVoucher, row.get("Debit Contract"));
					waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
				}
				if (!row.get("Debit Company").equalsIgnoreCase("NULL")) {
					WaitUntilElementIsClickable(debitCompanyJournalVoucher);
					waitAndClickOnElement(debitCompanyJournalVoucher);
					clearField(debitCompanyJournalVoucher);
					sendingValueToWebElement("debitCompanyJV", debitCompanyJournalVoucher, row.get("Debit Company"));
					waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
				}
				if (!row.get("Debit Second").equalsIgnoreCase("NULL")) {
					WaitUntilElementIsClickable(debitSecondJournalVoucher);
					waitAndClickOnElement(debitSecondJournalVoucher);
					clearField(debitSecondJournalVoucher);
					sendingValueToWebElement("debitSecondJV", debitSecondJournalVoucher, row.get("Debit Second"));
					waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
				}
				// Credit Entry Flow
				if (!row.get("Credit Contract").equalsIgnoreCase("NULL")) {
					WaitUntilElementIsClickable(creditContractJournalVoucher);
					waitAndClickOnElement(creditContractJournalVoucher);
					clearField(creditContractJournalVoucher);
					sendingValueToWebElement("creditContractJV", creditContractJournalVoucher, row.get("Credit Contract"));
					waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
				}
				if (!row.get("Credit Company").equalsIgnoreCase("NULL")) {
					WaitUntilElementIsClickable(creditCompanyJournalVoucher);
					waitAndClickOnElement(creditCompanyJournalVoucher);
					clearField(creditCompanyJournalVoucher);
					sendingValueToWebElement("creditCompanyJV", creditCompanyJournalVoucher, row.get("Credit Company"));
					waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
				}
				if (!row.get("Credit Second").equalsIgnoreCase("NULL")) {
					WaitUntilElementIsClickable(creditSecondJournalVoucher);
					waitAndClickOnElement(creditSecondJournalVoucher);
					clearField(creditSecondJournalVoucher);
					sendingValueToWebElement("creditSecondJV", creditSecondJournalVoucher, row.get("Credit Second"));
					waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
				}
				// Add Account Entry first
				WaitUntilElementIsClickable(addAccountEntry);
				waitAndClickOnElement(addAccountEntry);
				handleWait(2000);
				// If Category is "Profit and Loss", click edit and update cost center
				if (accountCategory.equalsIgnoreCase("Profit and Loss")) {
					waitTillWebElementIsVisible("debitContractJV", editBtnJV);
					WaitUntilElementIsClickable(editBtnJV);
					waitAndClickOnElement(editBtnJV);
					if(!row.get("Cost Center").equalsIgnoreCase("NULL")) {
						waitTillWebElementIsVisible("costCenterJV", costCenterJV);
						clickOnDropDownToTypeAndSelectValue("costCenterJV", costCenterJV, row.get("Cost Center"));
					}
					if(!row.get("Profit Center").equalsIgnoreCase("NULL")) {
						waitTillWebElementIsVisible("profitCenterJV", profitCenterJV);
						clickOnDropDownToTypeAndSelectValue("profitCenterJV", profitCenterJV, row.get("Profit Center"));

					}waitAndClickOnElement("updateButton",".q-dialog .qcard-dialogue #update-btn-entry");
//					WaitUntilElementIsClickable(addAccountEntry);
//					waitAndClickOnElement(addAccountEntry);
					log.info("Cost Center and Profit Center updated for Profit and Loss category");
				} else {
					if (accountCategory.equalsIgnoreCase("Balance Sheet")) {
						waitTillWebElementIsVisible("debitContractJV", editBtnJV);
						WaitUntilElementIsClickable(editBtnJV);
						waitAndClickOnElement(editBtnJV);
						if(!row.get("Profit Center").equalsIgnoreCase("NULL")) {
							waitTillWebElementIsVisible("profitCenterJV", profitCenterJV);
							clickOnDropDownToTypeAndSelectValue("profitCenterJV", profitCenterJV, row.get("Profit Center"));
						}
						if(!row.get("Vendor").equalsIgnoreCase("NULL")) {
							waitTillWebElementIsVisible("vendorJV", vendorJV);
							clickOnDropDownToTypeAndSelectValue("vendorJV", vendorJV, row.get("Vendor"));
						}
						if(!row.get("Tax Jurisdiction").equalsIgnoreCase("NULL")) {
							waitTillWebElementIsVisible("calculateTaxJV", calculateTaxJV);
							waitAndClickOnElement(calculateTaxJV);
							waitTillWebElementIsVisible("taxJurisdictionJV", taxJurisdictionJV);
							clickOnDropDownToTypeAndSelectValue("taxJurisdictionJV", taxJurisdictionJV, row.get("Tax Jurisdiction"));
							waitTillWebElementIsVisible("taxDeterminationJV", taxDeterminationJV);
							clickOnDropDownToTypeAndSelectValue("taxDeterminationJV", taxDeterminationJV, row.get("Tax Determination"));
						}
						waitAndClickOnElement("updateButton",".q-dialog .qcard-dialogue #update-btn-entry");
//						WaitUntilElementIsClickable(addAccountEntry);
//						waitAndClickOnElement(addAccountEntry);
						log.info("Cost Center updated for Profit and Loss category");
					}
				}
			}

			log.info("Journal Voucher entries added successfully");

		} catch (Exception e) {
			log.error("Exception in createJournalVoucherEntriesTab: " + e.getMessage());
			e.printStackTrace();
		}
	}


	public void createJournalVoucherAdditionalInfoTab(DataTable dt) {
		log.info("Creating New Journal Voucher additional information tab");
		try {
			List<Map<String, String>> journalVoucher = dt.asMaps(String.class, String.class);
			//Additional information Tab
			waitAndClickOnElement(additonalTabJournalVoucher);
			log.info("Additional information tab is clicked");
			WaitUntilElementIsClickable(additionalInformationNoteJV);
			waitAndClickOnElement(additionalInformationNoteJV);
			sendingValueToWebElement("additionalInformationNoteJV", additionalInformationNoteJV, journalVoucher.get(0).get("Note"));
			WaitUntilElementIsClickable(addrefrenceKeybtnJournalVoucher);
			waitAndClickOnElement(addrefrenceKeybtnJournalVoucher);
			WaitUntilElementIsClickable(createJV);
			waitAndClickOnElement(createJV);
			log.info("journal Voucher is created");


		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void jvSendToApproval() {
		log.info("Sending JV to approval");
		try {
			waitTillWebElementIsVisible("sendToApprovalJV", sendToApprovalJV);
			waitAndClickOnElement(sendToApprovalJV);
			log.info("JV sent to approval");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void jvRework() {
		log.info("Rework the JV");
		try {
			waitTillWebElementIsVisible("reworkJV", reworkJV);
			waitAndClickOnElement(reworkJV);
			log.info("JV has been reworked");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void jvApprove() {
		log.info("Approve the JV");
		try {
			waitTillWebElementIsVisible("approveJV", approveJV);
			waitAndClickOnElement(approveJV);
			log.info("JV has been Approved");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void jvSave() {
		log.info("Save the JV");
		try {
			waitTillWebElementIsVisible("saveJV", saveJV);
			waitAndClickOnElement(saveJV);
			log.info("JV has been Saved");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void jvClose() {
		log.info("Close the JV");
		try {
			waitTillWebElementIsVisible("closeJV", closeJV);
			waitAndClickOnElement(closeJV);
			log.info("JV has been Closed");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
