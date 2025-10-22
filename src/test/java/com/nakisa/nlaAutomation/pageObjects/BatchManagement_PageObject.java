package com.nakisa.nlaAutomation.pageObjects;

import com.nakisa.nlaAutomation.stepDefinitions.MasterHooks;
import com.nakisa.nlaAutomation.utils.DriverThread;
import io.cucumber.datatable.DataTable;
import lombok.extern.slf4j.Slf4j;

import org.apache.directory.api.util.Strings;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;

@Slf4j
public class BatchManagement_PageObject extends Common_BasePage_PageObject{
    MasterAgreement_PageObject masterAgreementPageObject = masterAgreement_PageObject.get();
    DriverThread driverThread = new DriverThread();

    int index;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body .row .col-12 .col-3 label.q-field") List<WebElement> filters;
    public @FindBy(css = ".q-dialog .q-card .dialog-footer  #add-posting-job-btn") WebElement addOperationalJob;
    public @FindBy(css = "#q-app .q-page .q-btn-group #create-job-btn") WebElement addJobButton;
    public @FindBy(css = ".q-card .dialog-body .q-field #batch-size-input-input") WebElement batchSize;
    public @FindBy(css = ".q-card .dialog-body .form-input-error #principal-position-type") WebElement principalPosition;
    public @FindBy(css = ".q-card .dialog-body .form-input #posting-transaction-type-selector") WebElement postingTransactionType;
    public @FindBy(css=".q-card .dialog-body .form-input #reversal-reason-type-selector") WebElement reversalReason;
    public @FindBy(css = ".q-card .dialog-body .form-input #journal-types") WebElement journalType;
    public @FindBy(css = ".q-card .dialog-body .form-input #job-posting-statuses") WebElement postingStatuses;
    public @FindBy(css = ".q-card .dialog-body .form-input #internal-posting-date-type-selector") WebElement internalPostingDate;
    public @FindBy(css = ".q-card .dialog-body .form-input #valid-from-input-input") WebElement fromDateJob;
    public @FindBy(css = ".q-card .dialog-body .form-input #valid-from-type") WebElement dateFromType;
    public @FindBy(css = ".q-card .dialog-body .form-input #valid-to-type") WebElement dateToType;
    public @FindBy(css = ".q-card .dialog-body .form-input #valid-to-input-input") WebElement toDateJob;
    public @FindBy(css = ".q-card .dialog-body .collapsable-form #skip-item-with-open-draft-checkbox") WebElement OpenDraft;
    public @FindBy(css = ".q-card .form-input #list-object-filter")WebElement ListFilter;
    public @FindBy(css = ".q-card .form-input .q-field [placeholder='Object List *']")WebElement objectList;
    public @FindBy(css = "#q-app .q-page .tasks-grid #refresh-btn") WebElement refreshButton;
    public @FindBy(css = "#q-app .q-page .q-btn-group #refresh-job-btn") WebElement refreshJobButton;
    public @FindBy(css=".q-dialog .q-card .q-table #id-search-input") WebElement profileSearchFilter;
    public @FindBy(css=".q-dialog .q-card .q-table #id-search-input-input") WebElement profileSearchFilterMassModification;
    public @FindBy(css = ".dialog-body .form-input-error #name-input") WebElement profileName;
    public @FindBy(css = ".q-card .dialog-body .form-input #modification-date-type-selector") WebElement modificationDateType;
    public @FindBy(css = " .q-card .dialog-body .form-input #modification-date-input-input") WebElement modificationDate;

    // Mass Indexation
    public @FindBy(css = ".q-card .dialog-body .row:nth-child(2) .form-input #job-indexation-type-selector") WebElement indexationTypeLease;  //check this
    public @FindBy(css = ".q-card .dialog-body .row:nth-child(2) .form-input #reference-date-type-selector") WebElement referenceDateTypeLease;  //check this
    public @FindBy(css = ".q-card .dialog-body .row:nth-child(2) .form-input #reference-date-input-input") WebElement referenceDateLease; //check this
    public @FindBy(css = ".q-card .dialog-body .row:nth-child(2) .form-input #index-level-type-selector") WebElement indexLevelTypeLease;
    public @FindBy(css = ".q-card .dialog-body .row:nth-child(2) .form-input #new-index-level-input-input") WebElement newIndexLevelLease;
    public @FindBy(css = ".q-card .dialog-body .form-input #indexation-treatment-type-dropdown") WebElement gaapIndexationTreatment;
    public @FindBy(css = ".q-card .dialog-body .form-input #indexation-date-type-selector") WebElement indexationDateTypeLease;
    public @FindBy(css = ".q-card .dialog-body .form-input #indexation-start-date-input-input") WebElement indexationDateLease;
    public @FindBy(css = ".q-card .dialog-body .row:nth-child(4) .form-input #job-indexation-type-selector") WebElement indexationTypeNonLease;  //check this
    public @FindBy(css = ".q-card .dialog-body .row:nth-child(4) .form-input #reference-date-type-selector") WebElement referenceDateTypeNonLease;  //check this
    public @FindBy(css = ".q-card .dialog-body .row:nth-child(4) .form-input #reference-date-input-input") WebElement referenceDateNonLease;
    public @FindBy(css = ".q-card .dialog-body .row:nth-child(4) .form-input #index-level-type-selector") WebElement indexLevelTypeNonLease;
    public @FindBy(css = ".q-card .dialog-body .row:nth-child(4) .form-input #new-index-level-input-input") WebElement newIndexLevelNonLease;
    public @FindBy(css = ".q-card .dialog-body .form-input #indexation-date-type-non-lease-selector") WebElement indexationDateTypeNonLease;
    public @FindBy(css = ".q-card .dialog-body .form-input #indexation-start-date-non-lease-input-input") WebElement indexationDateNonLease;
    public @FindBy(css = ".q-card .dialog-body #posting-date-type-selector") WebElement postingDateType;
    public @FindBy(css = ".q-card .dialog-body #internal-asset-class-selector") WebElement assetClassFilter;
    public @FindBy(css = ".q-card .dialog-body #asset-classes") WebElement internalAssetClass;
    public @FindBy(css = ".q-card .dialog-body #consumer-price-index-category-selector") WebElement cpiCategoryFilter;
    public @FindBy(css = ".q-card .dialog-body #cpi-categories") WebElement cpiCategories;
    public @FindBy(css = ".q-card .dialog-body #posting-date-input-input") WebElement postingDate;
    public @FindBy(css = ".q-card .dialog-body #document-date-input-input") WebElement documentDate;
    public @FindBy(css = ".q-card .dialog-body .sub-form #skip-item-with-open-draft-checkbox") WebElement skipAGWithOpenDraft;
    public @FindBy(css = ".q-card .dialog-body #list-object-filter") WebElement listFilterType;

    //Mass Modification
    public @FindBy(css = ".q-card .dialog-body .form-input #modification-type-selector") WebElement ModificationPostingType;
    public @FindBy(css = ".q-card .dialog-body .q-col-gutter-sm div.q-item #apply-global-on-event-name-checkbox") WebElement agEventCheckBox;
    public @FindBy(css = ".q-card .dialog-body .q-col-gutter-sm #activation-group-event-name-input") WebElement ActivationGroupEveName;
    public @FindBy(css = ".q-card .dialog-body .q-col-gutter-sm div.q-item #apply-global-on-modification-dates-checkbox") WebElement modificationCheckBox;

    public @FindBy(css = ".q-card .dialog-body .form-input #modification-date-type-selector") WebElement ModificationDateType;
    public @FindBy(css = ".q-card .dialog-body .form-input #activation-date-type-selector") WebElement Activationdatestype;
    public @FindBy(css = ".q-card .dialog-body .form-input input[aria-label='Posting Date *']") WebElement PostingDate;
    public @FindBy(css = ".q-card .dialog-body .form-input #document-date-input") WebElement DocumentDate;
    public @FindBy(css = ".q-card .dialog-body .form-input input[aria-label='Modification Date *']") WebElement ModificationDate;
    public @FindBy(css = ".q-card .dialog-body .form-input #apply-global-on-contract-rate-checkbox")WebElement Contractrates;
    public @FindBy(css = ".q-card .dialog-body .form-input #contract-rate-input-input")WebElement ContractRateInput;
    public @FindBy (css = ".q-card .dialog-body .form-input #use-ibr-rate-selector")WebElement IBRrate;
    public @FindBy(css = ".q-card .dialog-body .form-input #apply-global-on-term-flags-checkbox") WebElement TermsConditionsCheck;
    public @FindBy(css = ".q-card .dialog-body .form-input #apply-indexation-term-type-selector")WebElement ApplyIndex;

    public @FindBy(css = ".q-card .dialog-body .form-input #exercise-term-type-selector")WebElement exercise;
    public @FindBy(css = ".q-card .dialog-body  #lease-component-event-name-input")WebElement LCEventName;
    public @FindBy(css = ".q-card .form-input div[placeholder='Object List *']")WebElement ObjList;
    public @FindBy(css = ".q-card .dialog-body .form-input #apply-global-on-effective-dates-checkbox") WebElement EffectiveDateTypeCheckbox;

    public @FindBy(css = ".q-card .dialog-body .form-input #effective-date-type-selector") WebElement EffectiveDateType;
    public @FindBy(css = ".q-card .dialog-body .form-input input[aria-label='Effective Date *']") WebElement EffectiveDate;

    //Schedule Job
    public @FindBy(css = ".q-dialog .q-card .q-tab-panels #hour-freq-input") WebElement hourlyHours;
    public @FindBy(css = ".q-dialog .q-card .q-tab-panels #day-freq-input") WebElement dailyDay;
    public @FindBy(css = ".q-dialog .q-card .q-tab-panels #hour-of-day-selection") WebElement hour;
    public @FindBy(css = ".q-dialog .q-card .q-tab-panels #month-freq-input") WebElement monthlyMonth;
    public @FindBy(css = ".q-dialog .q-card .q-tab-panels #day-of-month-selection") WebElement date;
    public @FindBy(css = " .q-dialog .q-card .dialog-footer  #schedule-job-btn") WebElement scheduleJobButton;
    // Mass Modification
    public @FindBy(css  ="#q-app .q-table tr:nth-of-type(2) Button[id$='template-btn']") WebElement Exceldownloadtemplate;
    public @FindBy(css = "#q-app .q-table tr:nth-of-type(2) Button[id$='input-upload-btn']")WebElement ExcelUploadtemplate;
    public @FindBy (css = "input[type='file']")WebElement uploaderBtn;
    public @FindBy(css = "button#import-dialog-btn .block") WebElement importBtn;

    // Mass Workflow Transition
    public @FindBy(css = ".q-card .dialog-body .form-input #target-entity-type-selector") WebElement targetEntity;
    public @FindBy(css = ".q-card .dialog-body .form-input #allow-hitchhiking-checkbox") WebElement hitchHiking;
    public @FindBy(css = ".q-dialog[role='dialog'] #date-type-selector") WebElement documentPostingDateType;
    public @FindBy(css = ".q-dialog .q-card .dialog-footer #add-job-btn") WebElement addWorkflowJob;
    public @FindBy(css = "#q-app .q-toolbar #user-btn") WebElement userBtn;
    public @FindBy(css = "[role='menu'] .q-item[role='listitem'] div.text-grey") WebElement userEmail;
    public @FindBy(css = "#q-app .jobs-grid #created-by-filter-input") WebElement createdByFilter;
    public @FindBy(css = "#q-app .q-table__container.tasks-grid #report-btn")WebElement reportBtn;
    public @FindBy(css = ".q-page .q-table #cancel-btn")WebElement cancelJob;
    public @FindBy(css = "#q-app .q-table__container .q-td:nth-child(1) .q-field input[aria-label='Search']") WebElement searchScheduledJobFieldID;
    public @FindBy(css = "#q-app .q-page .q-anchor--skip >button") WebElement cancelSearchJob;
    public @FindBy(css =".q-card #name-selector-input")WebElement schedulerJobName;
    public @FindBy(css = ".q-card .dialog-body .form-input #internal-posting-date-type-selector")WebElement postingDocumentDateType;
    public @FindBy(css = ".q-page .col.q-py-sm tbody #mass-workflow-job-btn") WebElement importToMassWorkflowButton;
    public @FindBy(css = "#q-app .q-table__container .q-td:nth-child(1) #id-search-input")WebElement searchScheduledJobID;
    public @FindBy(css = "#q-app tbody tr:nth-child(2)  .q-radio[aria-label='Enable'][aria-checked='true']") WebElement enableScheduleJobRadioBtn;
    public @FindBy(css = "#q-app tbody tr:nth-child(2)  .q-radio[aria-label='Disable']") WebElement disableScheduleJobRadioBtn;
    public @FindBy(css = "#q-app tbody tr:nth-child(2) Button.text-red") WebElement scheduledJobDeletion;

    //Inter Company Transfer
    public @FindBy(css = ".q-dialog .qcard-dialogue #name-input-input") WebElement jobNameInterCompanyTransfer;
    public @FindBy(css = ".q-dialog .qcard-dialogue #transfer-date-input") WebElement transferDateInterCompanyTransfer;

    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body #erp-system-filter-type") WebElement erpSystemFilter;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body #lease-area-filter-type") WebElement leaseAreaFilter;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body #business-unit-filter-type") WebElement businessUnitFilter;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body #company-filter-type") WebElement companyFilter;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body #erp-systems") WebElement erpSystemFilterValues;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body #lease-areas") WebElement leaseAreaFilterValue;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body #business-units") WebElement businessUnitFilterValues;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body #companies") WebElement companyFilterValues;
    public @FindBy(css = "#q-app .q-table tr:nth-of-type(2) #revert-btn") WebElement revertButtonICTJob;
    public @FindBy(css = ".q-page .jobs-grid tbody tr:nth-child(2) #view-btn .mdi-content-copy") WebElement copyButtonBatchJob;
    public @FindBy(css = "#q-app .q-page #refresh-btn") WebElement jobRefreshButton;

    public BatchManagement_PageObject() {
        super();
        log.info("Driver is inside this class: " + this.getClass().getSimpleName());
        PageFactory.initElements(driver, this);
    }

    public void createPostingJobs(DataTable dt) {
        log.info("Creating Operational Posting Jobs");
        List<Map<String,String>> job=dt.asMaps(String.class, String.class);
        try{
            applyCreatedByFilter();
            if(MasterHooks.configurationProperties.get().getRunAutomationOnNCPBuild()){
                Thread.sleep(2000);
            }
            //Getting Initial Page Record
            int recordBeforeJob;
            if(driver.findElements(By.cssSelector("#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).isEmpty()){
                recordBeforeJob=0;
            }else {
                String records = driver.findElement(By.cssSelector("#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
                recordBeforeJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
            }
            waitAndClickOnElement(addJobButton);
            waitTillWebElementIsVisible("createProfilePopUp",".q-dialog  .qcard-dialogue");
            //Profile
            selectProfile("Operational Posting Profile", MasterHooks.batchProfileID.get());
            sendingValueToWebElement("batchSize",batchSize,job.get(0).get("Batch Size"));
            waitUnTillWebElementIsVisible("profileSection",".q-dialog .q-card .q-table .q-tr:nth-child(2)");
//            clickOnDropDownAndSelectValue("principalPosition",principalPosition,job.get(0).get("Principal Position"));
            clickOnDropDownAndSelectValue("postingTranscationType",postingTransactionType,job.get(0).get("Transaction Type"));
//            if(job.get(0).get("Transaction Type").equalsIgnoreCase("Reversal")){
//                clickOnDropDownAndSelectValue("reversalReason", reversalReason, job.get(0).get("Reversal Reason"));
//            }
            clickOnDropDownAndSelectCheckBoxes("journalType",journalType,job.get(0).get("Journal Types"));
            waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
            clickOnDropDownAndSelectCheckBoxes("postingStatuses", postingStatuses, job.get(0).get("Posting Statuses"));
            waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
            clickOnDropDownAndSelectValue("internalPostingDate",internalPostingDate, job.get(0).get("Posting Date Type"));
            clickOnDropDownAndSelectValue("fromDateType",dateFromType,job.get(0).get("From Date Type"));
            if(job.get(0).get("From Date Type").equalsIgnoreCase("User Defined")){
                waitTillWebElementIsVisible("dateFromType",dateFromType);
                sendingValueToWebElement("fromDate",fromDateJob,job.get(0).get("From Date"));
            }
            MasterHooks.datetocheckPostings.set(job.get(0).get("From Date"));
            clickOnDropDownAndSelectValue("toDateType",dateToType,job.get(0).get("To Date Type"));
            if(job.get(0).get("To Date Type").equalsIgnoreCase("User Defined")){
                waitTillWebElementIsVisible("dateToType",dateToType);
                sendingValueToWebElement("fromDate",toDateJob,job.get(0).get("To Date"));
            }

            if(!job.get(0).get("Open Drafts").equalsIgnoreCase("Yes")) {
                waitAndClickOnElement(OpenDraft);
            }
            if (!job.get(0).get("Entity Type").equalsIgnoreCase("All")) {
                clickOnDropDownAndSelectValue("entityType", ListFilter, job.get(0).get("Entity Type"));
                waitTillWebElementIsVisible("objectList", objectList);
                String ObjectList = job.get(0).get("Entity Type");
                String ID = null;
                switch (ObjectList) {
                    case "Master Agreement":
                        ID = MasterHooks.searchMLAID.get();
                        break;
                    case "Contract":
                        ID = MasterHooks.searchCTID.get();
                        break;
                    case "Lease Component":
                        ID = MasterHooks.searchLCID.get();
                        break;
                    case "Activation Group":
                        ID = MasterHooks.searchAGID.get();
                        break;
                }
                if (!Strings.isEmpty(MasterHooks.agReverted.get())) {
                    clickOnDropDownToTypeAndVerifyValueIsNull("objectList", objectList, ID);
                    waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                    waitAndClickOnElement(cancelButton);
                } else {
                    clickOnDropDownToTypeAndSelectCheckBox("objectList", objectList, ID);
                    waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                    WaitUntilElementIsClickable(addOperationalJob);
                    waitAndClickOnElement(addOperationalJob);
                    waitUntilLoadingSpinnerIsGone("nlaAddButtonLoader");
                    if (driver.findElements(By.cssSelector(".q-dialog .q-card #submit-btn")).size() == 1) {
                        clickOnSubmitPopup("Submit / Add");
                    }
                    waitUntilLoadingSpinnerIsGone("nlaDropDownPopUp");
                    handleWait(1000);
                    log.info("Created Posting Job");
                    //Waiting for page records to Increase
                    Wait<WebDriver> wait =
                            new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
                    wait.until(new Function<WebDriver, Boolean>() {
                        @Override
                        public Boolean apply(WebDriver driver) {
                            String records = driver.findElement(By.cssSelector("#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
                            int recordAfterJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
                            if (recordAfterJob != recordBeforeJob) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    });
                }
            }

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public void selectProfile(String jobName, String profileID){
        log.info("Selecting Profile for " + jobName);
        try {
            waitTillWebElementIsVisible("profileSearchField", profileSearchFilter);
            waitTillWebElementIsVisible("profileRadioButton",".q-dialog .q-card tbody tr:nth-child(2) .q-td:nth-child(1) .q-radio");
            int countBeforeSearch = driver.findElements(By.cssSelector(".q-dialog .q-card tbody tr .q-td.id:nth-child(2)")).size();
            if (countBeforeSearch > 1) {
                if (jobName.equalsIgnoreCase("Mass Modification") || jobName.equalsIgnoreCase("Mass Workflow")) {
                    sendingValueToWebElement("profileID", profileSearchFilterMassModification, profileID);
                } else {
                    sendingValueToWebElement("profileID", profileSearchFilter, profileID);
                }

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
                handleWait(1000);
            }
            //1st profile selected after search
            waitAndClickOnElement("selectprofile", ".q-dialog .q-card tbody tr:nth-child(2) .q-td:nth-child(1) .q-radio");


//            if(driver.findElements(By.cssSelector(".q-dialog .q-card #profile-section-expansion button.q-btn:nth-child(5)")).size()==1) {
//                waitAndClickOnElement(lastProfilePageButton);
////                waitAndClickOnElement(nextProfilePageButton); // Here this CSS will act as previous profile Button
//            }else if(driver.findElements(By.cssSelector(".q-dialog .q-card #profile-section-expansion .q-table__control button.q-btn:nth-child(3)")).size()==1) {
//                waitAndClickOnElement(nextProfilePageButton);
//            }
//            Thread.sleep(1000);
//            int profilesSize=driver.findElements(By.cssSelector(".q-dialog .q-card tbody tr .q-td:nth-child(1) .q-radio")).size();
//            for(int row=1; row<=profilesSize;row++){
//                String agID=driver.findElement(By.cssSelector(".q-dialog .q-card tbody tr:nth-child("+row+") .q-td:nth-child(14) .q-chip__content")).getText();
//                if(agID.equalsIgnoreCase(profileID)){
//                    waitAndClickOnElement("selectProfile",".q-dialog .q-card tbody tr:nth-child("+row+") .q-td:nth-child(1) .q-radio");
//                    break;
//                }
//            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Selected Profile");
    }

    public void userCheckJobStatus(String jobType,String status) {
        log.info("Checking the "+jobType+ " status is "+ status);
        if (jobType.contains("Batch")) {
            String jobStatus;
            if(jobType.contains("Mass Modification") || jobType.contains("Mass Workflow")) {
                waitTillWebElementIsVisible("jobStatus","#q-app .q-page .jobs-grid .q-table tbody tr:nth-child(2) .q-td:nth-child(5)");
                jobStatus = "#q-app .q-page .jobs-grid .q-table tbody tr:nth-child(2) .q-td:nth-child(5)";
            } else if (jobType.contains("Inter Company Transfer")) {
                waitTillWebElementIsVisible("jobStatus","#q-app .q-page .jobs-grid .q-table tbody tr:nth-child(2) .q-td:nth-child(4)");
                jobStatus = "#q-app .q-page .jobs-grid .q-table tbody tr:nth-child(2) .q-td:nth-child(4)";
            } else{
                waitTillWebElementIsVisible("jobStatus","#q-app .q-page .jobs-grid .q-table tbody tr:nth-child(2) .q-badge[role='status']");
                jobStatus = "#q-app .q-page .jobs-grid .q-table tbody tr:nth-child(2) .q-badge[role='status']";
            }
                //Waiting for job to move from creating status
                Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(600)).pollingEvery(Duration.ofMillis(5000)).ignoring(WebDriverException.class);
                wait1.until(new Function<WebDriver, Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        try {
                            waitAndClickOnElement(refreshJobButton);
                            handleWait(500);
                            if (!getValueFromElement(jobStatus).equalsIgnoreCase("Creating")) {
                                waitAndClickOnElement("firstJobSelected", "#q-app .q-page .jobs-grid .q-table tbody tr.selected-job-row");
                                waitAndClickOnElement(refreshButton);
                                Thread.sleep(500);
                                return true;
                            }
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        return false;
                    }
                });
                //Waiting for Job Status to be Done/Complete
                waitTillWebElementIsVisible("refreshButton", refreshButton);
                try {
                    waitAndClickOnElement(refreshButton);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            int jobsSize = driver.findElements(By.cssSelector("#q-app .q-page .col .q-table tbody tr")).size();
            for (index = 2; index <= jobsSize; index++) {
                checkBatchJobCompletion(status, index);
            }
        } else if (jobType.contains("Report")) {
            if(jobType.contains("Disclosure")){
                int jobsSize = driver.findElements(By.cssSelector("#q-app .q-page .col .q-table tbody tr")).size();
                for (index = 2; index <= jobsSize; index++) {
                    checkBatchJobCompletion(status, index);
                }
            }else {
                checkReportJobCompletion();
            }
        }
    }

    public void checkBatchJobCompletion(String jobStatusToCheck,int jobNumber) {
        log.info("Check the Jobs Completed Successfully");
        try {
            waitAndClickOnElement(refreshJobButton);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(1200)).pollingEvery(Duration.ofMillis(5000)).ignoring(WebDriverException.class);
            wait.until(new Function<WebDriver, Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    try {
                        waitAndClickOnElement(refreshButton);
                        handleWait(200);
                        System.out.println("Clicked : " +jobNumber);
                        waitTillWebElementIsVisible("status",getElement(".q-page .col.q-py-sm tbody tr:nth-child("+jobNumber+") .q-td [role='status']"));
                        if (getValueFromElement(".q-page .col.q-py-sm tbody tr:nth-child("+jobNumber+") .q-td [role='status']").equalsIgnoreCase(jobStatusToCheck)) {
                            if(jobStatusToCheck.equalsIgnoreCase("Pending User Input")){
                                waitAndClickOnElement(refreshJobButton);
                                if(!waitTillWebElementIsEnabled("downloadFileButton", Exceldownloadtemplate)){
                                    waitAndClickOnElement(refreshJobButton);
                                    waitUntilLoadingSpinnerIsShown("batchJobGridLoader");
                                    waitUntilLoadingSpinnerIsGone("batchJobGridLoader");
                                }
                                downloadUserInputFile();
                            }
                            else if(jobStatusToCheck.equalsIgnoreCase("Transition Done")){
                                log.info("Job is Done");
                            }
                            else if(jobStatusToCheck.equalsIgnoreCase("InterCompanyTransfer Done")){
                                waitAndClickOnElement(reportBtn);
                                waitTillWebElementIsVisible("addImportReportPopUp", ".q-dialog .q-card tbody tr");
                                waitAndClickOnElement("refresh",".q-card #refresh-btn");
                                waitAndClickOnElement("popUpClose",".q-card #close-btn");
                                waitUntilLoadingSpinnerIsGone("nlaDropDownPopUp");
                                log.info("InterCompanyTransfer Job is Done");
                            }
                            return true;
                        }

                       else if(driver.findElement(By.cssSelector(".q-page .col.q-py-sm tbody tr:nth-child("+jobNumber+") .q-td [role='status']")).getText().equalsIgnoreCase("Failed")){
                            Assert.fail("Batch Job Is failed");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Job Completed Successfully");
    }

    public void checkReportJobCompletion(){
        log.info("Checking the Jobs Completed Successfully");
        try {
            Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(1200)).pollingEvery(Duration.ofMillis(40000)).ignoring(WebDriverException.class);
            wait1.until(new Function<WebDriver, Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    try {
                        waitAndClickOnElement(jobRefreshButton);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (driver.findElement(By.cssSelector("#q-app tbody tr:nth-child(2) [role='status']")).getText().equalsIgnoreCase("Done")) {
                        log.info("Job is Completed");
                        return true;
                    } else if (driver.findElement(By.cssSelector("#q-app tbody tr:nth-child(2) [role='status']")).getText().equalsIgnoreCase("Failed")) {
                        log.info("Job is Failed");
                        org.testng.Assert.assertEquals(driver.findElement(By.cssSelector("#q-app tbody tr:nth-child(2) [role='status']")).getText(), "Failed");
                        return true;
                    } else {
                        log.info("Job is not Completed Yet");
                        return false;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downloadUserInputFile() {
        log.info("Downloading Excel Template");
        try {
            waitAndClickOnElement(refreshJobButton);
            waitUntilLoadingSpinnerIsShown("batchJobGridLoader");
            waitUntilLoadingSpinnerIsGone("batchJobGridLoader");
            File folder=new File(MasterHooks.downloadedExcelFilePath.get());
            File [] listOfFilesBeforeDown=folder.listFiles();
            int sizeOfFileBeforeDown=listOfFilesBeforeDown.length;
            System.out.println("The list of files before Excel download are " + sizeOfFileBeforeDown);
            waitTillWebElementIsVisible("DownloadExcelTemplate", Exceldownloadtemplate);
            WaitUntilElementIsClickable(Exceldownloadtemplate);
            actionMoveAndClick("ExcelDownloadTemplate",Exceldownloadtemplate);
            handleWait(2000);
//            waitUntilLoadingSpinnerIsGone("batchJobGridLoader");
            Wait<WebDriver> fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
                fluentWait.until(new Function<WebDriver, Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        if (driver.findElements(By.cssSelector("#q-app .q-page .jobs-grid .q-spinner")).isEmpty()) {
                            return true;
                        } else {
                          try {
                              log.info("Spinner still visible... refreshing the job page");
                              waitAndClickOnElement(getElement("#q-app .q-btn-group #refresh-job-btn"));
                          } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                          }
                          return false; // keep waiting
                        }
                    }
                });
//            waitUntilLoadingSpinnerIsShown("downloadLoader");
//            waitUntilLoadingSpinnerIsGone("downloadLoader");
            if(MasterHooks.configurationProperties.get().getRunEnvironment().equalsIgnoreCase("remote")) {
                download_file_selenium_grid(folder);
            }

            Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(300)).pollingEvery(Duration.ofMillis(1000)).ignoring(WebDriverException.class);
            wait1.until(new Function<WebDriver, Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    File [] listOfFilesAfterDown=folder.listFiles();
                    int sizeAfterDown=listOfFilesAfterDown.length;
                    if(sizeAfterDown!=sizeOfFileBeforeDown){
                        log.info("File downloaded & available in folder");
                        return true;
                    }
                    log.info("File is not downloaded Yet");
                    return false;
                }
            });
//            File [] downloadedFile=folder.listFiles();
//            waitTillWebElementIsVisible("UploadExcelTemplate", ExcelUploadtemplate);
//            waitAndClickOnElement(ExcelUploadtemplate);
//            uploaderBtn.sendKeys( downloadedFile[0].getAbsolutePath());
//            waitTillWebElementIsVisible("ImportButton", importBtn);
//            waitAndClickOnElement(importBtn);
//            waitUntilLoadingSpinnerIsGone("nlaDropDownPopUp");
//
//            for (File file : folder.listFiles()) {
//                Thread.sleep(5000);
//                file.delete();
//                log.info("Excel file deleted successfully!!!");
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void createIndexationPostingJobs(DataTable dt) {
        log.info("Creating Posting Jobs");
        List<Map<String, String>> indexJob = dt.asMaps(String.class, String.class);
        try {
            applyCreatedByFilter();
            int recordBeforeJob;
            if (driver.findElements(By.cssSelector("#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).size() == 0) {
                recordBeforeJob = 0;
            } else {
                String records = driver.findElement(By.cssSelector("#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
                recordBeforeJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
            }
            waitAndClickOnElement(addJobButton);
            waitTillWebElementIsVisible("createProfilePopUp", ".q-dialog  .qcard-dialogue");
            sendingValueToWebElement("batchSize", batchSize, indexJob.get(0).get("Batch Size"));
//            waitTillWebElementIsVisible("profileSection",".q-dialog .q-card .q-table .q-tr:nth-child(2)");
//            clickOnDropDownAndSelectValue("principalPosition", principalPosition, indexJob.get(0).get("Principal Position"));
            clickOnDropDownAndSelectValue("modificationDateType", modificationDateType,indexJob.get(0).get("Modification Date Type"));
            if (indexJob.get(0).get("Modification Date Type").equalsIgnoreCase("User Defined")) {
                waitAndClickOnElement(modificationDate);
                sendingValueToWebElement("modificationDate", modificationDate, indexJob.get(0).get("Modification Date"));
            }

            clickOnDropDownAndSelectValue("indexationTypeLease", indexationTypeLease,indexJob.get(0).get("Indexation Type (Lease)"));
            if(indexJob.get(0).get("Indexation Type (Lease)").equalsIgnoreCase("CPI Global")){
                clickOnDropDownAndSelectValue("referenceDateTypeLease", referenceDateTypeLease, indexJob.get(0).get("Reference Date Type (Lease)"));
                if(indexJob.get(0).get("Reference Date Type (Lease)").equalsIgnoreCase("User Defined")){
                    waitAndClickOnElement(referenceDateLease);
                    sendingValueToWebElement("referenceDateLease",referenceDateLease, indexJob.get(0).get("Reference Date (Lease)"));
                }
            }
            else if (indexJob.get(0).get("Indexation Type (Lease)").equalsIgnoreCase("CPI Local")){
//                sendingValueToWebElement("referenceDateLease",referenceDateLease, indexJob.get(0).get("Reference Date (Lease)"));
//                clickOnDropDownAndSelectValue("indexLevelTypeLease", indexLevelTypeLease, indexJob.get(0).get("Index Level Type (Lease)"));
                waitAndClickOnElement(newIndexLevelLease);
                sendingValueToWebElement("newIndexLevelLease",newIndexLevelLease, indexJob.get(0).get("New Index Level (Lease)"));
            }
//            clickOnDropDownToTypeAndSelectValue("GAAPIndexationTreamnet",gaapIndexationTreatment,indexJob.get(0).get("GAAP Indexation Treatment"));
            clickOnDropDownAndSelectValue("IndexationDateTypeLease",indexationDateTypeLease, indexJob.get(0).get("Indexation Date Type (Lease)"));
            if(indexJob.get(0).get("Indexation Date Type (Lease)").equalsIgnoreCase("User Defined")){
                waitAndClickOnElement(indexationDateLease);
                sendingValueToWebElement("indexationDateLease",indexationDateLease,indexJob.get(0).get("Indexation Date (Lease)"));
            }

            // For Indexation Type = Non-Lease
            if(!indexJob.get(0).get("Indexation Type (Non-Lease)").equalsIgnoreCase("null"))
            {
                clickOnDropDownAndSelectValue("indexationTypeNonLease", indexationTypeNonLease,indexJob.get(0).get("Indexation Type (Non-Lease)"));
                if(indexJob.get(0).get("Indexation Type (Non-Lease)").equalsIgnoreCase("CPI Global")){
                    clickOnDropDownAndSelectValue("referenceDateTypeNonLease", referenceDateTypeNonLease, indexJob.get(0).get("Reference Date Type (Non-Lease)"));
                    if(indexJob.get(0).get("Reference Date Type (Non-Lease)").equalsIgnoreCase("User Defined")){
                        waitAndClickOnElement(referenceDateNonLease);
                        sendingValueToWebElement("referenceDateNonLease",referenceDateNonLease, indexJob.get(0).get("Reference Date (Lease)"));
                    }
                }
                else if (indexJob.get(0).get("Indexation Type (Non-Lease)").equalsIgnoreCase("CPI Local")){
//                sendingValueToWebElement("referenceDateLease",referenceDateLease, indexJob.get(0).get("Reference Date (Lease)"));
//                    clickOnDropDownAndSelectValue("indexLevelTypeNonLease", indexLevelTypeNonLease, indexJob.get(0).get("Index Level Type (Non-Lease)"));
                    waitAndClickOnElement(newIndexLevelLease);
                    sendingValueToWebElement("newIndexLevelNonLease",newIndexLevelNonLease, indexJob.get(0).get("New Index Level (Non-Lease)"));
                }
                clickOnDropDownAndSelectValue("IndexationDateTypeNonLease",indexationDateTypeNonLease, indexJob.get(0).get("Indexation Date Type (Non-Lease)"));
                if(indexJob.get(0).get("Indexation Date Type (Non-Lease)").equalsIgnoreCase("User Defined")){
                    waitAndClickOnElement(indexationDateNonLease);
                    sendingValueToWebElement("indexationDateNonLease",indexationDateNonLease,indexJob.get(0).get("Indexation Date (Non-Lease)"));
                }
            }
            WaitUntilElementIsClickable(gaapIndexationTreatment);
            if(!indexJob.get(0).get("GAAP Indexation Treatment").equalsIgnoreCase("null")) {
                clickOnDropDownAndSelectValue("indexationTreatment", gaapIndexationTreatment,indexJob.get(0).get("GAAP Indexation Treatment"));
            }
            clickOnDropDownAndSelectValue("postingDateType", postingDateType,indexJob.get(0).get("Posting Date Type"));
            if(!(indexJob.get(0).get("Posting Date Type").equalsIgnoreCase("Modification Date") || indexJob.get(0).get("Posting Date Type").equalsIgnoreCase("Next Available Posting"))) {
                waitAndClickOnElement(postingDate);
                sendingValueToWebElement("postingDate",postingDate,indexJob.get(0).get("Posting Date"));
                waitAndClickOnElement(documentDate);
                sendingValueToWebElement("documentDate",documentDate,indexJob.get(0).get("Document Date"));
            }
            //Asset Class
            clickOnDropDownAndSelectValue("assetClassFilter",assetClassFilter,indexJob.get(0).get("Asset Class Filter"));
            if(!indexJob.get(0).get("Asset Class Filter").equalsIgnoreCase("All")){
                clickOnDropDownToTypeAndSelectCheckBox("internalAssetClass",internalAssetClass,indexJob.get(0).get("Internal Asset Class"));
            }
            //CPI Ctaegory
            clickOnDropDownAndSelectValue("cpiCategoryFilter",cpiCategoryFilter,indexJob.get(0).get("CPI Category Filter"));
            if(!indexJob.get(0).get("CPI Category Filter").equalsIgnoreCase("All")){
                clickOnDropDownToTypeAndSelectCheckBox("cpiCategories",cpiCategories,indexJob.get(0).get("CPI Categories"));
            }

            selectProfile("Mass Indexation",MasterHooks.batchProfileID.get());
            if(indexJob.get(0).get("Open Drafts").equalsIgnoreCase("Yes")) {
                waitAndClickOnElement(OpenDraft);
            }
            if (!indexJob.get(0).get("Entity Type").equalsIgnoreCase("All")) {
                clickOnDropDownAndSelectValue("entityType", ListFilter, indexJob.get(0).get("Entity Type"));
                waitTillWebElementIsVisible("objectList", objectList);
                String ObjectList = indexJob.get(0).get("Entity Type");
                String ID = null;
                switch (ObjectList) {
                    case "Master Agreement":
                        ID = MasterHooks.searchMLAID.get();
                        break;
                    case "Contract":
                        ID = MasterHooks.searchCTID.get();
                        break;
                    case "Lease Component":
                        ID = MasterHooks.searchLCID.get();
                        break;
                    case "Activation Group":
                        ID = MasterHooks.searchAGID.get();
                        break;
                }
                if (!Strings.isEmpty(MasterHooks.agReverted.get())) {
                    clickOnDropDownToTypeAndVerifyValueIsNull("objectList", objectList, ID);
                    waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                    waitAndClickOnElement(cancelButton);
                } else {
                    String dropdownId = ObjectList.equalsIgnoreCase("Activation Group") ? "objectListMassIndexation" : "objectList";
                    clickOnDropDownToTypeAndSelectCheckBox(dropdownId, objectList, ID);
                    waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");

                    handleWait(500);
                    addOperationalJob.click();
                    waitUntilLoadingSpinnerIsGone("nlaAddButtonLoader");
                    handleWait(500);
                    if (driver.findElements(By.cssSelector(".q-dialog .q-card #submit-btn")).size() == 1) {
                        clickOnSubmitPopup("Submit / Add");
                    }
                    waitUntilLoadingSpinnerIsGone("nlaDropDownPopUp");
                    log.info("Created Indexation Job");
                    //Waiting for page records to Increase
                    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
                    wait.until(new Function<WebDriver, Boolean>() {
                        @Override
                        public Boolean apply(WebDriver driver) {
                            String records =
                                    driver.findElement(By.cssSelector("#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
                            int recordAfterJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
                            if (recordAfterJob != recordBeforeJob) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    });

                    waitTillWebElementIsVisible("MassIndexationJobID", ".q-page .col.q-py-sm tbody tr:nth-child(2) .q-td:nth-child(1) .col-auto");
                    String massIndexationJobID = driver.findElement(By.cssSelector(".q-page .col.q-py-sm tbody tr:nth-child(2) .q-td:nth-child(1) .col-auto")).getText();
                    MasterHooks.massIndexationJobID.set(massIndexationJobID.substring(4, massIndexationJobID.length()));
                    log.info("Created Indexation Job");
                }
            }

        }catch (InterruptedException|IOException e) {
            e.printStackTrace();
        }
    }

    public void createIndexationPostingScheduleJobs(DataTable dt) {
        log.info("Creating Indexation Scheduler Jobs");
        List<Map<String, String>> indexScheduleJob = dt.asMaps(String.class, String.class);
        try {
            Thread.sleep(1000);
            int recordBeforeJob;
            if (driver.findElements(By.cssSelector("#q-app .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).isEmpty()) {
                recordBeforeJob = 0;
            } else {
                String records = driver.findElement(By.cssSelector("#q-app .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
                recordBeforeJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
            }
            if(MasterHooks.configurationProperties.get().getRunAutomationOnNCPBuild()){
                Thread.sleep(2000);
            }
            waitAndClickOnElement(addJobButton);
            waitTillWebElementIsVisible("createProfilePopUp", ".q-dialog  .qcard-dialogue");
            sendingValueToWebElement("batchSize", batchSize, indexScheduleJob.get(0).get("Batch Size"));
            waitTillWebElementIsVisible("profileSection",".q-dialog .q-card .q-table .q-tr:nth-child(2)");
//            clickOnDropDownAndSelectValue("principalPosition", principalPosition, indexScheduleJob.get(0).get("Principal Position"));
            clickOnDropDownAndSelectValue("modificationDateType", modificationDateType, indexScheduleJob.get(0).get("Modification Date Type"));
            clickOnDropDownAndSelectValue("indexationType", indexationTypeLease, indexScheduleJob.get(0).get("Indexation Type (Lease)"));
            clickOnDropDownAndSelectValue("referenceDateType", referenceDateTypeLease, indexScheduleJob.get(0).get("Reference Date Type (Lease)"));
            clickOnDropDownAndSelectValue("IndexationDateTypeLease", indexationDateTypeLease, indexScheduleJob.get(0).get("Indexation Date Type (Lease)"));

            // For Indexation Type = Non-Lease
            if (!indexScheduleJob.get(0).get("Indexation Type (Non-Lease)").equalsIgnoreCase("null")) {
                clickOnDropDownAndSelectValue("indexationTypeNonLease", indexationTypeNonLease, indexScheduleJob.get(0).get("Indexation Type (Non-Lease)"));
                clickOnDropDownAndSelectValue("referenceDateTypeNonLease", referenceDateTypeNonLease, indexScheduleJob.get(0).get("Reference Date Type (Non-Lease)"));
                clickOnDropDownAndSelectValue("IndexationDateTypeNonLease", indexationDateTypeNonLease, indexScheduleJob.get(0).get("Indexation Date Type (Non-Lease)"));
            }
            // For GAAP Treatment
            WaitUntilElementIsClickable(gaapIndexationTreatment);
            clickOnDropDownAndSelectValue("indexationTreatment", gaapIndexationTreatment, indexScheduleJob.get(0).get("GAAP Indexation Treatment"));
            clickOnDropDownAndSelectValue("postingDateType", postingDateType, indexScheduleJob.get(0).get("Posting Date Type"));
            //Asset Class

            clickOnDropDownAndSelectValue("assetClassFilter", assetClassFilter, indexScheduleJob.get(0).get("Asset Class Filter"));
            if(!indexScheduleJob.get(0).get("Asset Class Filter").equalsIgnoreCase("All")){
                clickOnDropDownToTypeAndSelectCheckBox("internalAssetClass",internalAssetClass,indexScheduleJob.get(0).get("Internal Asset Class"));
            }
            //CPI Ctaegory
            clickOnDropDownAndSelectValue("cpiCategoryFilter",cpiCategoryFilter,indexScheduleJob.get(0).get("CPI Category Filter"));
            if(!indexScheduleJob.get(0).get("CPI Category Filter").equalsIgnoreCase("All")){
                clickOnDropDownToTypeAndSelectCheckBox("cpiCategories",cpiCategories,indexScheduleJob.get(0).get("CPI Categories"));
            }

            // Schedule Option
            if (indexScheduleJob.get(0).get("Schedule Option").equalsIgnoreCase("Hourly")) {
                waitAndClickOnElement("hourlyScheduleOption", ".q-card .dialog-body .q-tabs #hourly-tab");
                clickOnDropDownAndSelectValue("hourlyHours", hourlyHours, indexScheduleJob.get(0).get("Hour"));
            } else if (indexScheduleJob.get(0).get("Schedule Option").equalsIgnoreCase("Daily")) {
                waitAndClickOnElement("dailyScheduleOption", ".q-card .dialog-body .q-tabs #daily-tab");
                clickOnDropDownAndSelectValue("dailyDay", dailyDay, indexScheduleJob.get(0).get("Date"));
                clickOnDropDownAndSelectValue("dailyHour", hour, indexScheduleJob.get(0).get("Hour"));
            } else if (indexScheduleJob.get(0).get("Schedule Option").equalsIgnoreCase("Weekly")) {
                waitAndClickOnElement("weeklyScheduleOption", ".q-card .dialog-body .q-tabs #weekly-tab");
                waitAndClickOnElement("sundayCheckbox", ".q-card .dialog-body .q-panel #sunday-checkbox");

                String[] fieldValuesLength = indexScheduleJob.get(0).get("Week Days").split(",");
                for (String value : fieldValuesLength) {
                    for (int days = 2; days <= 8; days++) {
                        String dayName = driver.findElement(By.cssSelector(".q-card .dialog-body .q-panel .q-checkbox:nth-child(" + days + ")")).getText();
                        if (value.equalsIgnoreCase(dayName)) {
                            waitAndClickOnElement("weekDayCheckbox", ".q-card .dialog-body .q-panel .q-checkbox:nth-child(" + days + ")");
                        }
                    }
                }
                clickOnDropDownAndSelectValue("weeklyHour", hour, indexScheduleJob.get(0).get("Hour"));
            } else if (indexScheduleJob.get(0).get("Schedule Option").equalsIgnoreCase("Monthly")) {
                waitAndClickOnElement("weeklyScheduleOption", ".q-card .dialog-body .q-tabs #monthly-tab");
                clickOnDropDownAndSelectValue("monthlyMonth", monthlyMonth, indexScheduleJob.get(0).get("Month"));
                clickOnDropDownAndSelectValue("monthlyDate", date, indexScheduleJob.get(0).get("Date"));
                clickOnDropDownAndSelectValue("monthlyHour", hour, indexScheduleJob.get(0).get("Hour"));
            } else if (indexScheduleJob.get(0).get("Schedule Option").equalsIgnoreCase("Yearly")) {
                waitAndClickOnElement("yearlyScheduleOption", ".q-card .dialog-body .q-tabs #yearly-tab");
                waitAndClickOnElement("januaryCheckbox", ".q-card .dialog-body .q-panel #jan-checkbox");

                String[] fieldValuesLength = indexScheduleJob.get(0).get("Months Name").split(",");
                for (String value : fieldValuesLength) {
                    for (int months = 2; months <= 13; months++) {
                        String dayName = driver.findElement(By.cssSelector(".q-card .dialog-body .q-panel .q-checkbox:nth-child(" + months + ")")).getText();
                        if (value.equalsIgnoreCase(dayName)) {
                            waitAndClickOnElement("monthCheckbox", ".q-card .dialog-body .q-panel .q-checkbox:nth-child(" + months + ")");
                        }
                    }
                }
                clickOnDropDownAndSelectValue("monthlyDate", date, indexScheduleJob.get(0).get("Date"));
                clickOnDropDownAndSelectValue("monthlyHour", hour, indexScheduleJob.get(0).get("Hour"));
            }
            selectProfile("Mass Indexation",MasterHooks.batchProfileID.get());
            if (indexScheduleJob.get(0).get("Open Drafts").equalsIgnoreCase("Yes")) {
                waitAndClickOnElement(OpenDraft);
            }
            String values = "";
            HashMap<String, String> threadMap = MasterHooks.map.get();
            //Object List
            String entityType =  indexScheduleJob.get(0).get( "Entity Type");
            if (!(entityType.equalsIgnoreCase("null"))) {
                clickOnDropDownAndSelectValue("entityType", ListFilter, indexScheduleJob.get(0).get("Entity Type"));
                waitTillWebElementIsVisible("objectList", objectList);
                if (indexScheduleJob.get(0).get("Entity ID").equalsIgnoreCase("All")) {

                    switch (entityType) {
                        case "Master Agreement":
                            values = getRecordId("MasterAgreement", threadMap, false);
                            break;
                        case "Contract":
                            values = getRecordId("Contract", threadMap, false);
                            break;
                        case "Lease Component":
                            values = getRecordId("LeaseComponent", threadMap, false);
                            break;
                        case "Activation Group":
                            values = getRecordId("ActivationGroup", threadMap, false);
                            break;
                        default:
                            System.out.println("Unknown ObjectList type: " + entityType);
                    }

                } else {
                    String objectListValue= indexScheduleJob.get(0).get("Entity ID");
                    values = getRecordId(objectListValue, threadMap, true);
                }
            }

            clickOnDropDownToTypeAndSelectCheckBox("objectListMassIndexation", objectList, values);
            waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
            WaitUntilElementIsClickable(scheduleJobButton);
            waitAndClickOnElement(scheduleJobButton);
            waitUntilLoadingSpinnerIsShown("nlaDropDownPopUp");
            waitUntilLoadingSpinnerIsGone("nlaDropDownPopUp");
            log.info("Created Indexation Schedule Job");
            //Waiting for page records to Increase
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
            wait.until(new Function<WebDriver, Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    String records = driver.findElement(By.cssSelector("#q-app .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
                    int recordAfterJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
                    if (recordAfterJob != recordBeforeJob) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            log.info("Created Scheduled Indexation Job");
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public void createModificationJobs(DataTable dt) {
        log.info("Creating Posting Jobs");
        List<Map<String, String>> job = dt.asMaps(String.class, String.class);
        try {
            applyCreatedByFilter();
            int recordBeforeJob;
            if (driver.findElements(By.cssSelector("#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).size() == 0) {
                recordBeforeJob = 0;
            } else {
                String records = driver.findElement(By.cssSelector("#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
                recordBeforeJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
            }
            waitAndClickOnElement(addJobButton);
            waitTillWebElementIsVisible("createProfilePopUp", ".q-dialog  .qcard-dialogue");
            sendingValueToWebElement("batchSize", batchSize, job.get(0).get("Batch Size"));
            waitTillWebElementIsVisible("profileSection",".q-dialog .q-card .q-table .q-tr:nth-child(2)");
            //  clickOnDropDownAndSelectValue("principalPosition", principalPosition, job.get(0).get("Principal Position"));
//            clickOnDropDownAndSelectValue("contractPrincipalPosition", principalPosition,
//                    masterAgreementPageObject.getInputValues().get("Inception").get("Contract Level").get("Principal Position").get(0));

            if (job.get(0).get("Transaction Type").equalsIgnoreCase("Activation Group Modification")) {
                clickOnDropDownAndSelectCheckBoxes("postingTransactionType", ModificationPostingType, job.get(0).get("Transaction Type"));

                if (!job.get(0).get("Activation Group Event Name").equalsIgnoreCase("null")) {
                    waitTillWebElementIsVisible("AGEventCheckBox", agEventCheckBox);
                    waitAndClickOnElement(agEventCheckBox);
                    waitTillWebElementIsVisible("ActivationGroupEveName", ActivationGroupEveName);
                    waitAndClickOnElement(ActivationGroupEveName);
                    sendingValueToWebElement("ActivationGroupEveName", ActivationGroupEveName, job.get(0).get("Activation Group Event Name"));
                }
                if (!job.get(0).get("Modification Date Type").equalsIgnoreCase("null")) {
                    waitTillWebElementIsVisible("ModificationDateCheckBox", modificationCheckBox);
                    waitAndClickOnElement(modificationCheckBox);
                    waitTillWebElementIsVisible("ModificationType", ModificationDateType);
                    if (!job.get(0).get("Modification Date Type").equalsIgnoreCase("User Defined")) {
                        clickOnDropDownAndSelectValue("ModificationType", ModificationDateType, job.get(0).get("Modification Date Type"));
                    }
                    else {
                        clickOnDropDownAndSelectValue("ModificationType", ModificationDateType, job.get(0).get("Modification Date Type"));
                        sendingValueToWebElement("ModificationDate", ModificationDate, job.get(0).get("Modification Date"));
                    }

                    if (job.get(0).get("Activation Dates Types").equalsIgnoreCase("Next Available Posting") || job.get(0).get("Activation Dates Types").equalsIgnoreCase("Job Date")) {
                        waitTillWebElementIsVisible("ActivationDateType", Activationdatestype);
                        clickOnDropDownAndSelectValue("ActivationDateType", Activationdatestype, job.get(0).get("Activation Dates Types"));
                    } else {
                        waitTillWebElementIsVisible("ActivationDateType", Activationdatestype);
                        clickOnDropDownAndSelectValue("ActivationDateType", Activationdatestype, job.get(0).get("Activation Dates Types"));
                        sendingValueToWebElement("PostingDate", PostingDate, job.get(0).get("Posting Date"));
                        sendingValueToWebElement("DocumentDate", DocumentDate, job.get(0).get("Document Date"));

                    }
//                    if (!job.get(0).get("Modification Date").equalsIgnoreCase("null")) {
//                        sendingValueToWebElement("ModificationDate", ModificationDate, job.get(0).get("Modification Date"));
//                    }

//                    if(!job.get(0).get("Posting Date").equalsIgnoreCase("null")) {
//                        sendingValueToWebElement("PostingDate", PostingDate, job.get(0).get("Posting Date"));
//                    }
//                    if(!job.get(0).get("Document Date").equalsIgnoreCase("null")) {
//                        sendingValueToWebElement("DocumentDate", DocumentDate, job.get(0).get("Document Date"));
//                    }

                }

                if (!job.get(0).get("Contract rate").equalsIgnoreCase("null")) {
                    waitTillWebElementIsVisible("ContractRateCheck", Contractrates);
                    waitAndClickOnElement(Contractrates);
                    if (!job.get(0).get("Contract rate").equalsIgnoreCase("IBR")) {
                        waitTillWebElementIsVisible("contract", ContractRateInput);
                        waitAndClickOnElement(ContractRateInput);
                        sendingValueToWebElement("Contract rate", ContractRateInput, job.get(0).get("Contract rate"));
                    } else {
                        waitTillWebElementIsVisible("IBR", IBRrate);
                        waitAndClickOnElement(IBRrate);
                    }
                }


//                if (!job.get(0).get("Contract rate").equalsIgnoreCase("null")){
//                        waitTillWebElementIsVisible("IBR", IBRrate);
//                        waitAndClickOnElement(IBRrate);
//                    }


                if (!job.get(0).get("Apply Indexation").equalsIgnoreCase("null")) {
                    waitTillWebElementIsVisible("Termscheck", TermsConditionsCheck);
                    waitAndClickOnElement(TermsConditionsCheck);
                    //if (job.get(0).get("Apply Indexation").equalsIgnoreCase("Apply on All T&Cs") || job.get(0).get("Apply Indexation").equalsIgnoreCase("Keep Current State")) {
                    waitTillWebElementIsVisible("Indexation", ApplyIndex);
                    clickOnDropDownAndSelectValue("Indexation", ApplyIndex, job.get(0).get("Apply Indexation"));
                    // }
                    // if (job.get(0).get("Exercise").equalsIgnoreCase("Apply on All T&Cs") || job.get(0).get("Exercise").equalsIgnoreCase("Keep Current State")) {
                    waitTillWebElementIsVisible("Exercise", exercise);
                    clickOnDropDownAndSelectValue("Exercise", exercise, job.get(0).get("Exercise"));
                    //  }
                }
            } else if (job.get(0).get("Transaction Type").equalsIgnoreCase("Lease Component Modification")) {

                clickOnDropDownAndSelectCheckBoxes("postingTransactionType", ModificationPostingType, job.get(0).get("Transaction Type"));
                if (!job.get(0).get("LC Event Name").equalsIgnoreCase("null")) {
                    waitTillWebElementIsVisible("EventNameOfModificationJob", agEventCheckBox);
                    waitAndClickOnElement(agEventCheckBox);
                    waitTillWebElementIsVisible("LCEveName", LCEventName);
                    waitAndClickOnElement(LCEventName);
                    sendingValueToWebElement("LCEveName", LCEventName, job.get(0).get("LC Event Name"));
                }
//                if (!job.get(0).get("Apply Indexation").equalsIgnoreCase("null")) {
//                    waitTillWebElementIsVisible("Termscheck", TermsConditionsCheck);
//                    waitAndClickOnElement(TermsConditionsCheck);
////                    if (job.get(0).get("Apply Indexation").equalsIgnoreCase("Apply on All T&Cs") || job.get(0).get("Apply Indexation").equalsIgnoreCase("Keep Current State")) {
////                        waitTillWebElementIsVisible("Indexation", ApplyIndex);
//                    clickOnDropDownAndSelectValue("Indexation", ApplyIndex, job.get(0).get("Apply Indexation"));
//                }
//                }
            }
            else {
                clickOnDropDownAndSelectValue("postingTransactionType", ModificationPostingType, job.get(0).get("Transaction Type"));
                if (!job.get(0).get("Effective Date Type").equalsIgnoreCase("null")) {
                    waitTillWebElementIsVisible("EffectivedateTypeCheckbox", EffectiveDateTypeCheckbox);
                    waitAndClickOnElement(EffectiveDateTypeCheckbox);
                    if(!job.get(0).get("Effective Date Type").equalsIgnoreCase("Job Date")){
                        clickOnDropDownAndSelectValue("Effective Date Type",EffectiveDateType, job.get(0).get("Effective Date Type"));
                        sendingValueToWebElement("Effective Date", EffectiveDate, job.get(0).get("Effective Date"));
                    }
                    else {
                        clickOnDropDownAndSelectValue("Effective Date Type",EffectiveDateType, job.get(0).get("Effective Date Type"));
                    }

                }
            }
            selectProfile("Mass Modification",MasterHooks.batchProfileID.get());

            if(!job.get(0).get("Open Drafts").equalsIgnoreCase("Yes")){
                waitTillWebElementIsVisible("SkipItemWithOpenDraft", skipAGWithOpenDraft);
                waitAndClickOnElement(skipAGWithOpenDraft);
            }
            String entityLevel = job.get(0).get("List Filter Type");
            if (!entityLevel.equalsIgnoreCase("All")){
                waitTillWebElementIsVisible("ListFilterType", listFilterType);
                clickOnDropDownAndSelectValue("ListFilter", listFilterType, entityLevel);
                String ID=null;
                switch(entityLevel) {
                    case "Master Agreement":
                        ID=MasterHooks.searchMLAID.get();
                        break;
                    case "Contract":
                        ID=MasterHooks.searchCTID.get();
                        break;
                    case "Lease Component":
                        ID=MasterHooks.searchLCID.get();
                        break;
                    case "Activation Group":
                        ID=MasterHooks.searchAGID.get();
                        break;
                }
                waitTillWebElementIsVisible("ObjList", ObjList);
                if (!Strings.isEmpty(MasterHooks.agReverted.get())) {
                    clickOnDropDownToTypeAndVerifyValueIsNull("objectList", objectList, ID);
                    waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                    waitAndClickOnElement(cancelButton);
                } else {
                    clickOnDropDownToTypeAndSelectCheckBox("ObjList", ObjList, ID);
                    WebElement submitButton = driver.findElement(By.cssSelector(".q-dialog .q-card #run-job-btn"));
                    waitAndClickOnElement(submitButton);
                    waitUntilLoadingSpinnerIsGone("nlaDropDownPopUp");
                    log.info("Created Mass Modification Job");
                    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
                    wait.until(new Function<WebDriver, Boolean>() {
                        @Override
                        public Boolean apply(WebDriver driver) {
                            String records =
                                    driver.findElement(By.cssSelector("#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
                            int recordAfterJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
                            if (recordAfterJob != recordBeforeJob) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    });
                    waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                }
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public void createWorkflowPostingJobs(DataTable dt) {
        log.info("Creating Posting Jobs");
        List<Map<String, String>> workflowJob = dt.asMaps(String.class, String.class);
        try {
            applyCreatedByFilter();
            int recordBeforeJob;
            if (driver.findElements(By.cssSelector("#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).size() == 0) {
                recordBeforeJob = 0;
            } else {
                String records = driver.findElement(By.cssSelector("#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
                recordBeforeJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
            }
            waitAndClickOnElement(addJobButton);
            waitTillWebElementIsVisible("createProfilePopUp", ".q-dialog  .qcard-dialogue");
            selectProfile("Mass Workflow",MasterHooks.batchProfileID.get());
            waitTillWebElementIsVisible("profileSection",".q-dialog .q-card .q-table .q-tr:nth-child(2)");
            sendingValueToWebElement("batchSize", batchSize, workflowJob.get(0).get("Batch Size"));
            waitTillWebElementIsVisible("profileSection",".q-dialog .q-card .q-table .q-tr:nth-child(2)");
            clickOnDropDownAndSelectValue("targetEntity", targetEntity ,workflowJob.get(0).get("Target Entity"));
           handleWait(1000);
            if(workflowJob.get(0).get("Default Workbook Input").equalsIgnoreCase("true")){
                waitAndClickOnElement("defaultWorkbookInput",".q-card .dialog-body #use-default-workbook-input-checkbox");
            }
//            if (!workflowJob.get(0).get("Target Entity").equalsIgnoreCase("Master Agreement")) {
//                waitTillWebElementIsVisible("principalPosition", principalPosition);
//                clickOnDropDownAndSelectValue("principalPosition", principalPosition, workflowJob.get(0).get("Principal Position"));
//            }
            WebElement initiallState = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".q-card .dialog-body .form-input #initial-state-type-selector")));
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".q-card .dialog-body .form-input #initial-state-type-selector")));
            clickOnDropDownAndSelectValue("initialState", initiallState, workflowJob.get(0).get("Initial State"));
            int count = 0;
            Map<String, String> row = workflowJob.get(0); // Get each row as a map

                for (String key : row.keySet()) { // Iterate over all column names
                    if (key.contains("Action")) { // Check if column contains "Action"
                            count ++;
                        }
                    }
                System.out.println("Size of Actions are :" +count);

            //Loop for Actions
            for(int i=0;i<count;i++){
                waitUnTillWebElementIsVisible("actionSteps", ".q-card .dialog-body #new-transitions-selector");
                handleWait(500);
                WebElement actionSelector = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".q-card .dialog-body #new-transitions-selector")));
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".q-card .dialog-body #new-transitions-selector")));
                clickOnDropDownAndSelectValue("actionSteps", actionSelector, workflowJob.get(0).get("Action at Step "+(i+1)));
            }
//            clickOnDropDownAndSelectValue("finalState", finalState, workflowJob.get(0).get("Final State"));
            if (workflowJob.get(0).get("Allow Hitchhiking").equalsIgnoreCase("Yes")) {
                waitTillWebElementIsVisible("hitchHiking", hitchHiking);
                waitAndClickOnElement(hitchHiking);
            }
            if(!workflowJob.get(0).get("Document and Posting Date Type").equalsIgnoreCase("null")){
                clickOnDropDownAndSelectValue("documentPostingDateType", documentPostingDateType, workflowJob.get(0).get("Document and Posting Date Type"));
            }
          if (!workflowJob.get(0).get("Entity Type").equalsIgnoreCase("All")) {
            clickOnDropDownAndSelectValue("entityType", ListFilter, workflowJob.get(0).get("Entity Type"));
            waitTillWebElementIsVisible("objectList", objectList);
            String ObjectList = workflowJob.get(0).get("Entity Type");
            String ID = null;
            switch (ObjectList) {
              case "Master Agreement":
                ID = MasterHooks.searchMLAID.get();
                break;
              case "Contract":
                ID = MasterHooks.searchCTID.get();
                break;
              case "Lease Component":
                ID = MasterHooks.searchLCID.get();
                break;
              case "Activation Group":
                ID = MasterHooks.searchAGID.get();
                break;
            }
            clickOnDropDownToTypeAndSelectCheckBox("objectList", objectList, ID);
            waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
          }
          WaitUntilElementIsClickable(addWorkflowJob);
          waitAndClickOnElement(addWorkflowJob);
          waitUntilLoadingSpinnerIsGone("nlaDropDownPopUp");
          handleWait(5000);
          log.info("Created Workflow Job");
          //Waiting for page records to Increase
          Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
          wait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
              String records = driver.findElement(By.cssSelector("#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
              int recordAfterJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
              if (recordAfterJob != recordBeforeJob) {
                return true;
              } else {
                return false;
              }
            }
          });
        }
        catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public void changeExcelFile(String jobName) {
        log.info("Reading the Data from " + jobName + " section in Excel and changing the file");
        try {
            File folder = new File(MasterHooks.downloadedExcelFilePath.get());
            File[] downloadedFiles = folder.listFiles();
            if (downloadedFiles == null || downloadedFiles.length == 0) {
                throw new FileNotFoundException("No Excel file found in: " + MasterHooks.downloadedExcelFilePath.get());
            }
            String downloadedFilePath = downloadedFiles[0].getAbsolutePath();
            FileInputStream file = new FileInputStream(downloadedFilePath);
            XSSFWorkbook myWorkbook = new XSSFWorkbook(file);

            // Iterate over all sheets in the workbook
            for (int i = 0; i < myWorkbook.getNumberOfSheets(); i++) {
                XSSFSheet sheet = myWorkbook.getSheetAt(i);
                String sheetName = sheet.getSheetName();
                log.info("Processing sheet: " + sheetName);

                if (jobName.contains("Mass Modification")) {
                    if (sheetName.equalsIgnoreCase("Lease Component T&C")) {
                        updateMassModificationSheet(sheet, jobName);
                    }
                } else if (jobName.contains("Mass Workflow")) {
                    if (sheetName.equalsIgnoreCase("Accounting")) {
                        updateMassWorkflowSheet(sheet, jobName);
                    }
                } else if (jobName.contains("Inter Company Transfer")) {
                    if (sheetName.equalsIgnoreCase("Contracts") || sheetName.equalsIgnoreCase("Cost Center Allocation")) {
                        updateICTSheet(sheet, jobName);
                    }
                } else {
                    log.info("Skipping sheet: " + sheetName + " (no rules for this job)");
                }
            }

            // Save changes
            file.close();
            try (FileOutputStream fileOutput = new FileOutputStream(downloadedFilePath)) {
                myWorkbook.write(fileOutput);
            }
            myWorkbook.close();
            Thread.sleep(2000);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        log.info("✅ Changes have been done in all relevant sheets of the downloaded Excel file.");
    }

    private void updateMassModificationSheet(XSSFSheet mySheet, String jobName) {
        int dataSize = masterAgreement_PageObject.get().getInputValues().get(jobName).get("eventdata").get("Operation").size();
        int rowNum = mySheet.getLastRowNum();

        for (int rows = 0; rows < dataSize; rows++) {
            int columnNumber = 0;
            XSSFRow row = mySheet.createRow(rows + (rowNum + 1));

            row.createCell(columnNumber++).setCellValue(masterAgreement_PageObject.get().getInputValues().get(jobName).get("eventdata").get("Operation").get(rows));
            row.createCell(columnNumber++).setCellValue(UUID.randomUUID().toString());
            row.createCell(columnNumber++).setCellValue(MasterHooks.searchLCID.get());

            // Currency
            String currency = masterAgreement_PageObject.get().getInputValues().get(jobName).get("eventdata").get("Currency Display ID").get(rows);
            if (!currency.equalsIgnoreCase("-") && !currency.isEmpty()) {
                row.createCell(columnNumber++).setCellValue(currency);
            } else {
                columnNumber++;
            }

            row.createCell(columnNumber++).setCellValue(masterAgreement_PageObject.get().getInputValues().get(jobName).get("eventdata").get("Term Type").get(rows));
            row.createCell(columnNumber++).setCellValue(masterAgreement_PageObject.get().getInputValues().get(jobName).get("eventdata").get("Expense Category Display ID").get(rows));
            row.createCell(columnNumber++).setCellValue(masterAgreement_PageObject.get().getInputValues().get(jobName).get("eventdata").get("Name").get(rows));
            row.createCell(columnNumber++).setCellValue(masterAgreement_PageObject.get().getInputValues().get(jobName).get("eventdata").get("Amount Frequency").get(rows));
            row.createCell(columnNumber++).setCellValue(masterAgreement_PageObject.get().getInputValues().get(jobName).get("eventdata").get("Amount").get(rows));
            row.createCell(columnNumber++).setCellValue(masterAgreement_PageObject.get().getInputValues().get(jobName).get("eventdata").get("Payment Frequency").get(rows));

            // Dates
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                String firstPayment = masterAgreement_PageObject.get().getInputValues().get(jobName).get("eventdata").get("First Payment Date").get(rows);
                String lastPayment = masterAgreement_PageObject.get().getInputValues().get(jobName).get("eventdata").get("Payment Term End Date").get(rows);

                Date firstdate = dateFormat.parse(firstPayment);
                row.createCell(columnNumber++).setCellValue(firstdate);

                Date lastdate = dateFormat.parse(lastPayment);
                row.createCell(columnNumber++).setCellValue(lastdate);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            row.createCell(columnNumber++).setCellValue(masterAgreement_PageObject.get().getInputValues().get(jobName).get("eventdata").get("Payment Calculation Mode").get(rows));
            row.createCell(columnNumber++).setCellValue(masterAgreement_PageObject.get().getInputValues().get(jobName).get("eventdata").get("Payment Mode").get(rows));

            // Optional fields (first payment amount, last payment amount, etc.)
            addIfPresent(row, columnNumber++, jobName, "First Payment Amount", rows);
            addIfPresent(row, columnNumber++, jobName, "Last Payment Amount", rows);
            addIfPresent(row, columnNumber++, jobName, "Notification Date", rows);
            addIfPresent(row, columnNumber++, jobName, "Month End", rows);
            row.createCell(columnNumber++).setCellValue(masterAgreement_PageObject.get().getInputValues().get(jobName).get("eventdata").get("Payment Timing").get(rows));
            addIfPresent(row, columnNumber++, jobName, "Escalating Rent", rows);
            addIfPresent(row, columnNumber++, jobName, "Stop Escalating At Zero", rows);
            addIfPresent(row, columnNumber++, jobName, "Escalation Fast Forward", rows);
            addIfPresent(row, columnNumber++, jobName, "Escalating Rent Amount / Percentage", rows);
            addIfPresent(row, columnNumber++, jobName, "Escalating Rent Type", rows);
            addIfPresent(row, columnNumber++, jobName, "Frequency Offset", rows);
            addIfPresent(row, columnNumber++, jobName, "Escalation Frequency", rows);
        }
    }

    private void updateMassWorkflowSheet(XSSFSheet mySheet, String jobName) {
        int dataSize = masterAgreement_PageObject.get().getInputValues().get(jobName).get("eventdata").get("Indexation Type (Lease)").size();

        for (int rows = 0; rows < dataSize; rows++) {
            int columnNumber = 0;
            XSSFRow row = mySheet.createRow(rows + 1);
            // Lease
            row.createCell(columnNumber++).setCellValue(MasterHooks.searchAGID.get());
            row.createCell(columnNumber++).setCellValue(masterAgreement_PageObject.get().getInputValues().get(jobName).get("eventdata").get("Indexation Type (Lease)").get(rows));
            addIfPresent(row, columnNumber++, jobName, "Current Index Level (Lease)", rows);
            row.createCell(columnNumber++).setCellValue(masterAgreement_PageObject.get().getInputValues().get(jobName).get("eventdata").get("Consumer Price Index Category Display ID (Lease)").get(rows));
            row.createCell(columnNumber++).setCellValue(masterAgreement_PageObject.get().getInputValues().get(jobName).get("eventdata").get("Reference Date (Lease)").get(rows));
            row.createCell(columnNumber++).setCellValue(masterAgreement_PageObject.get().getInputValues().get(jobName).get("eventdata").get("Conditionally Indexed (Lease)").get(rows));
            addIfPresent(row, columnNumber++, jobName, "Minimum Percentage Change (Lease)", rows);
            addIfPresent(row, columnNumber++, jobName, "Maximum Percentage Change (Lease)", rows);
            // Non-Lease
            row.createCell(columnNumber++).setCellValue(masterAgreement_PageObject.get().getInputValues().get(jobName).get("eventdata").get("Indexation Type (Non-Lease)").get(rows));
            addIfPresent(row, columnNumber++, jobName, "Current Index Level (Non-Lease)", rows);
            row.createCell(columnNumber++).setCellValue(masterAgreement_PageObject.get().getInputValues().get(jobName).get("eventdata").get("Consumer Price Index Category Display ID (Non-Lease)").get(rows));
            row.createCell(columnNumber++).setCellValue(masterAgreement_PageObject.get().getInputValues().get(jobName).get("eventdata").get("Reference Date (Non-Lease)").get(rows));
            row.createCell(columnNumber++).setCellValue(masterAgreement_PageObject.get().getInputValues().get(jobName).get("eventdata").get("Conditionally Indexed (Non-Lease)").get(rows));
            addIfPresent(row, columnNumber++, jobName, "Minimum Percentage Change (Non-Lease)", rows);
            addIfPresent(row, columnNumber++, jobName, "Maximum Percentage Change (Non-Lease)", rows);
        }
    }

    private void updateICTSheet(XSSFSheet mySheet, String jobName) {
        String sheetName = mySheet.getSheetName();
        XSSFRow row = mySheet.getRow(1);
        switch (sheetName) {
            case "Contracts":
                updateColumn(row, mySheet, jobName, "New Master Agreement Display Id");
                updateColumn(row, mySheet, jobName, "New Erp System Display Id");
                updateColumn(row, mySheet, jobName, "New Lease Area Display Id");
                updateColumn(row, mySheet, jobName, "New Business Unit Display Id");
                updateColumn(row, mySheet, jobName, "New Company Display Id");
                updateColumn(row, mySheet, jobName, "New Work Breakdown Structure Display Id");
                updateColumn(row, mySheet, jobName, "New Profit Center Display Id");
                updateColumn(row, mySheet, jobName, "New Responsible Cost Center Display Id");
                updateColumn(row, mySheet, jobName, "New Functional Area Display Id");
                updateColumn(row, mySheet, jobName, "New Business Area Display Id");
                updateColumn(row, mySheet, jobName, "New Segment Display Id");
                updateColumn(row, mySheet, jobName, "New Network Display Id");
                updateColumn(row, mySheet, jobName, "New Track Cost");
                updateColumn(row, mySheet, jobName, "New Internal Order Type Display Id");
                updateColumn(row, mySheet, jobName, "New Internal Order Display Id");
                break;
            case "Cost Center Allocation":
                updateColumn(row, mySheet, jobName, "New Cost Center Display Id");
                updateColumn(row, mySheet, jobName, "New Profit Center Display Id");
                updateColumn(row, mySheet, jobName, "New Allocation Percentage");
                updateColumn(row, mySheet, jobName, "New Main Cost Center");
                break;
            default:
                log.info("No ICT updates for sheet: " + sheetName);
        }
    }

    private void updateColumn(XSSFRow row, XSSFSheet sheet, String jobName, String fieldName) {
        int colIndex = findColumnIndex(sheet, fieldName);
        if (colIndex == -1) return; // column not found

        String value = masterAgreement_PageObject.get().getInputValues().get(jobName).get("eventdata").get(fieldName).get(0);
        if (value != null && !value.isEmpty() && !value.equalsIgnoreCase("-")) {
            XSSFCell cell = row.getCell(colIndex);
            if (cell == null) {
                cell = row.createCell(colIndex);
            }
            cell.setCellValue(value);
        }
    }

    private int findColumnIndex(XSSFSheet sheet, String headerName) {
        XSSFRow headerRow = sheet.getRow(0);
        for (Cell cell : headerRow) {
            if (cell.getStringCellValue().trim().equalsIgnoreCase(headerName)) {
                return cell.getColumnIndex();
            }
        }
        return -1; // not found
    }

    private void addIfPresent(XSSFRow row, int columnNumber, String jobName, String key, int index) {
        String value = masterAgreement_PageObject.get().getInputValues().get(jobName).get("eventdata").get(key).get(index);

        if (value != null && !value.equalsIgnoreCase("-") && !value.isEmpty()) {
            row.createCell(columnNumber).setCellValue(value);
        }
    }


    public void uploadDownloadedExcel(String jobType) {
        log.info("Uploading Excel file after changes");
        File folder = new File(MasterHooks.downloadedExcelFilePath.get());
        try {
            String filePath = null;
            File[] downloadedFile;
            File lastDownloadedFile = null;
            try {
                downloadedFile = folder.listFiles();
            }catch (Exception e){
                downloadedFile = folder.listFiles();
            }
            if (downloadedFile != null && downloadedFile.length > 0) {
                // Sort files by last modified timestamp in descending order
                Arrays.sort(downloadedFile, Comparator.comparingLong(File::lastModified).reversed());
                // Get the first file (last modified file)
                lastDownloadedFile=downloadedFile[0];
                filePath = downloadedFile[0].getAbsolutePath();
                // Print the details of the last downloaded file
                System.out.println("Last downloaded file:");
                System.out.println("File Name: " + lastDownloadedFile.getName());
                System.out.println("Last Modified: " + lastDownloadedFile.lastModified());
                System.out.println("Absolute Path: " + lastDownloadedFile.getAbsolutePath());
            }
            waitAndClickOnElement(driver.findElement(By.cssSelector("#q-app .q-btn-group #refresh-job-btn")));
            waitUntilLoadingSpinnerIsShown("batchJobGridLoader");
            waitUntilLoadingSpinnerIsGone("batchJobGridLoader");
            waitTillWebElementIsVisible("UploadExcelTemplate", ExcelUploadtemplate);
            waitAndClickOnElement(ExcelUploadtemplate);

            if (MasterHooks.configurationProperties.get().getRunEnvironment().equalsIgnoreCase("remote")){
                upload_file_selenium_grid(filePath);
            }else{
                Thread.sleep(2000);
                uploaderBtn.sendKeys(filePath);
            }
            waitTillWebElementIsVisible("ImportButton", importBtn);
            waitAndClickOnElement(importBtn);
            waitUntilLoadingSpinnerIsGone("nlaDropDownPopUp");

            for (File file : folder.listFiles()) {
                Thread.sleep(5000);
                file.delete();
                log.info("Excel file deleted successfully!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String massJobType= null;
        int jobsSize = driver.findElements(By.cssSelector("#q-app .q-page .col .q-table tbody tr")).size();
        if(jobType.equalsIgnoreCase("Mass Modification")){
            massJobType="Mass Modification Done";
        } else if (jobType.equalsIgnoreCase("Mass Workflow")) {
            massJobType="Transition Done";
        } else if (jobType.equalsIgnoreCase("Inter Company Transfer")) {
            massJobType="InterCompanyTransfer Done";
        }

        for(index=2;index<=jobsSize;index++) {
            checkBatchJobCompletion(massJobType,index);
        }
        log.info("Uploaded Excel file after changes");
    }

    public void applyCreatedByFilter(){
        log.info("Applying Created By filter in Jobs section");
        try {
            waitTillWebElementIsVisible("taskRefreshButton","#q-app .q-btn-group #refresh-job-btn");
            waitAndClickOnElement(driver.findElement(By.cssSelector("#q-app .q-btn-group #refresh-job-btn")));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        waitUntilLoadingSpinnerIsShown("nlaBatchJobListLoader");
        waitUntilLoadingSpinnerIsGone("nlaBatchJobListLoader");
        if(!(driver.findElements(By.cssSelector("#q-app .jobs-grid #created-by-filter .q-icon")).size()==1)) {
            try {
                waitAndClickOnElement(userBtn);
            } catch (InterruptedException e) { e.printStackTrace();}
            waitTillWebElementIsVisible("userEmail", userEmail);
            createdByFilter.click();
            sendingValueToWebElement("createdByFilter", createdByFilter, userEmail.getText());
            waitUntilLoadingSpinnerIsShown("nlaBatchJobListLoader");
            waitUntilLoadingSpinnerIsGone("nlaBatchJobListLoader");
        }
        log.info("Applied Created By filter in Jobs section");
    }
    public void verifyStatusOfJob(String jobType) {
        waitTillWebElementIsVisible("ReportsActionButton", reportBtn);
        try {
            waitAndClickOnElement(reportBtn);
            waitUntilLoadingSpinnerIsShown("nlaJournalSpinner");
            waitUntilLoadingSpinnerIsGone("nlaJournalSpinner");
            Thread.sleep(1000);
            if(jobType.equalsIgnoreCase("Mass Indexation Job")||jobType.equalsIgnoreCase("Mass WorkflowTransition Job")) {
                String statusText = driver.findElement(By.cssSelector(".q-table tbody:nth-child(2) .text-center:nth-child(3) div:nth-child(1)")).getText();
                if (statusText.equalsIgnoreCase("FAILED")) {
                    log.info("Mass Indexation / Mass Workflow transition is in Failed status!!!");
                } else {
                    Assert.fail("Mass Indexation / Mass Workflow transition is passing!!!");
                }
            }
            else{
                String statusText=driver.findElement(By.cssSelector(".q-card .q-table__container tbody .q-td:nth-child(3)")).getText();
                if (statusText.equalsIgnoreCase("FAILED")) {
                    log.info("Mass Modification is in Failed status!!!");
                } else {
                    Assert.fail("Mass Modification is passing!!!");
                }
            }
            waitAndClickOnElement("closeButton",".q-card #close-btn");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void massWorkflowJobCompletion() {
        log.info("Checking that Mass Workflow Job of Mass Import is Completed");
        try {
            int attempts = 0;
            while (attempts < 5 && !(driver.findElements(By.cssSelector(".q-page .col.q-py-sm tbody #mass-workflow-job-btn")).size() == 1)) {
                waitAndClickOnElement(refreshButton);
                attempts++;
            }
            waitTillWebElementIsVisible("importToMassWorkflowButton",importToMassWorkflowButton);
            waitAndClickOnElement(importToMassWorkflowButton);
            waitUntilLoadingSpinnerIsShown("nlaListLoader");
            waitUntilLoadingSpinnerIsGone("nlaListLoader");
//            waitTillWebElementIsVisible("workFlowSearchFilterID", workFlowSearchFilterID);
//            workFlowSearchFilterID.click();
//            sendingValueToWebElement("workFlowSearchFilterID", workFlowSearchFilterID, MasterHooks.massWorkflowJobID.get());
//            waitUntilLoadingSpinnerIsShown("nlaListLoader");
//            waitUntilLoadingSpinnerIsGone("nlaListLoader");
            try {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(1200)).pollingEvery(Duration.ofMillis(15000)).ignoring(WebDriverException.class);
                wait.until(new Function<WebDriver, Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        try {
                            waitAndClickOnElement(refreshButton);
                            Thread.sleep(200);
                            if(driver.findElements(By.cssSelector(".q-page .col.q-py-sm tbody tr:nth-child(2) .q-td [role='status']")).size()==1){
                                return true;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return false;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            int jobsSize = driver.findElements(By.cssSelector("#q-app .q-page .col .q-table tbody tr")).size();
            for(index=2;index<=jobsSize;index++) {
                checkBatchJobCompletion("Transition Done",index);
            }
            waitAndClickOnElement(driver.findElement(By.cssSelector("#q-app .q-py-sm .q-btn-group #refresh-job-btn")));
            waitUntilLoadingSpinnerIsShown("nlaListLoader");
            waitUntilLoadingSpinnerIsGone("nlaListLoader");
            if(!driver.findElement(By.cssSelector(".q-page .col.q-py-sm tbody tr:nth-child(2) .q-td [role='status']")).getText().equalsIgnoreCase("Transition Done")){
                Assert.fail("Mass Workflow Job Status is : "+getValueFromElement(".q-page .col.q-py-sm tbody tr:nth-child(2) .q-td [role='status']") + " But Expected is Transition Done");
            }
        } catch (InterruptedException e) {e.printStackTrace();}
    }

    public void cancelTheCreatedJob() {
        try {
            String search_ID = driver.findElement(By.cssSelector(".q-page .q-table .q-tr--no-hover.selected-job-row td:nth-child(1)")).getText();
            waitAndClickOnElement(searchScheduledJobFieldID);
            searchScheduledJobFieldID.sendKeys(search_ID);
            Thread.sleep(1000);
            waitAndClickOnElement(cancelJob);
            waitUntilLoadingSpinnerIsShown("nlaListLoader");
            waitUntilLoadingSpinnerIsGone("nlaListLoader");
            if (driver.findElement(By.cssSelector("#q-app tbody tr:nth-child(2) [role='status']")).getText().equalsIgnoreCase("Generation Cancelled")) {
                log.info("Job is Cancelled" );
            }


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void createBatchPostingScheduleJob(DataTable dt) {
        log.info("Creating Scheduled Posting Jobs");
        List<Map<String, String>> job = dt.asMaps(String.class, String.class);
        try {
            Thread.sleep(1000);
            //Getting Initial Page Record
            int recordBeforeJob;
            if (driver.findElements(By.cssSelector("#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).size() == 0) {
                recordBeforeJob = 0;
            } else {
                String records = driver.findElement(By.cssSelector("#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
                recordBeforeJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
            }
            waitAndClickOnElement(addJobButton);
            waitTillWebElementIsVisible("createProfilePopUp", ".q-dialog  .qcard-dialogue");
            sendingValueToWebElement("Name", schedulerJobName, job.get(0).get("Name"));
            sendingValueToWebElement("batchSize", batchSize, job.get(0).get("Batch Size"));
            waitTillWebElementIsVisible("profileSection",".q-dialog .q-card .q-table .q-tr:nth-child(2)");
//            clickOnDropDownAndSelectValue("principalPosition", principalPosition, job.get(0).get("Principal Position"));
            clickOnDropDownAndSelectValue("postingTranscationType", postingTransactionType, job.get(0).get("Transaction Type"));
            if (job.get(0).get("Transaction Type").equalsIgnoreCase("Reversal")) {
                clickOnDropDownAndSelectValue("reversalReason", reversalReason, job.get(0).get("Reversal Reason"));
            }
            clickOnDropDownAndSelectCheckBoxes("journalType", journalType, job.get(0).get("Journal Types"));
            waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
            clickOnDropDownAndSelectCheckBoxes("postingStatuses", postingStatuses, job.get(0).get("Posting Statuses"));
            waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
            clickOnDropDownAndSelectValue("fromDateType", dateFromType, job.get(0).get("From Date Type"));
            if (job.get(0).get("From Date Type").equalsIgnoreCase("User Defined")) {
                waitTillWebElementIsVisible("dateFromType", dateFromType);
                sendingValueToWebElement("fromDate", fromDateJob, job.get(0).get("From Date"));
            }
            MasterHooks.datetocheckPostings.set(job.get(0).get("From Date"));
            clickOnDropDownAndSelectValue("toDateType", dateToType, job.get(0).get("To Date Type"));
            clickOnDropDownAndSelectValue("postingAndDocumentDateType", postingDocumentDateType, job.get(0).get("Posting & Document Date Type"));
            //SchedleOption
            if (job.get(0).get("Schedule Option").equalsIgnoreCase("Hourly")) {
                waitAndClickOnElement("hourlyScheduleOption", ".q-card .dialog-body .q-tabs #hourly-tab");
                clickOnDropDownAndSelectValue("hourlyHours", hourlyHours, job.get(0).get("Hour"));
            } else if (job.get(0).get("Schedule Option").equalsIgnoreCase("Daily")) {
                waitAndClickOnElement("dailyScheduleOption", ".q-card .dialog-body .q-tabs #daily-tab");
                clickOnDropDownAndSelectValue("dailyDay", dailyDay, job.get(0).get("Date"));
                clickOnDropDownAndSelectValue("dailyHour", hour, job.get(0).get("Hour"));
            } else if (job.get(0).get("Schedule Option").equalsIgnoreCase("Weekly")) {
                waitAndClickOnElement("weeklyScheduleOption", ".q-card .dialog-body .q-tabs #weekly-tab");
                waitAndClickOnElement("sundayCheckbox", ".q-card .dialog-body .q-panel #sunday-checkbox");

                String[] fieldValuesLength = job.get(0).get("Week Days").split(",");
                for (String value : fieldValuesLength) {
                    for (int days = 2; days <= 8; days++) {
                        String dayName = driver.findElement(By.cssSelector(".q-card .dialog-body .q-panel .q-checkbox:nth-child(" + days + ")")).getText();
                        if (value.equalsIgnoreCase(dayName)) {
                            waitAndClickOnElement("weekDayCheckbox", ".q-card .dialog-body .q-panel .q-checkbox:nth-child(" + days + ")");
                        }
                    }
                }
                clickOnDropDownAndSelectValue("weeklyHour", hour, job.get(0).get("Hour"));
            } else if (job.get(0).get("Schedule Option").equalsIgnoreCase("Monthly")) {
                waitAndClickOnElement("weeklyScheduleOption", ".q-card .dialog-body .q-tabs #monthly-tab");
                clickOnDropDownAndSelectValue("monthlyMonth", monthlyMonth, job.get(0).get("Month"));
                clickOnDropDownAndSelectValue("monthlyDate", date, job.get(0).get("Date"));
                clickOnDropDownAndSelectValue("monthlyHour", hour, job.get(0).get("Hour"));
            } else if (job.get(0).get("Schedule Option").equalsIgnoreCase("Yearly")) {
                waitAndClickOnElement("yearlyScheduleOption", ".q-card .dialog-body .q-tabs #yearly-tab");
                waitAndClickOnElement("januaryCheckbox", ".q-card .dialog-body .q-panel #jan-checkbox");

                String[] fieldValuesLength = job.get(0).get("Months Name").split(",");
                for (String value : fieldValuesLength) {
                    for (int months = 2; months <= 13; months++) {
                        String dayName = driver.findElement(By.cssSelector(".q-card .dialog-body .q-panel .q-checkbox:nth-child(" + months + ")")).getText();
                        if (value.equalsIgnoreCase(dayName)) {
                            waitAndClickOnElement("monthCheckbox", ".q-card .dialog-body .q-panel .q-checkbox:nth-child(" + months + ")");
                        }
                    }
                }
                clickOnDropDownAndSelectValue("monthlyDate", date, job.get(0).get("Date"));
                clickOnDropDownAndSelectValue("monthlyHour", hour, job.get(0).get("Hour"));
            }
            selectProfile("Schedule Operational Posting", MasterHooks.batchProfileID.get());
            if (!job.get(0).get("Open Drafts").equalsIgnoreCase("Yes")) {
                waitAndClickOnElement(OpenDraft);
            }
            String values = "";
            HashMap<String, String> threadMap = MasterHooks.map.get();
            //Object List
            String entityType =  job.get(0).get( "Entity Type");
            if (!(entityType.equalsIgnoreCase("null"))) {
                clickOnDropDownAndSelectValue("entityType", ListFilter, job.get(0).get("Entity Type"));
                waitTillWebElementIsVisible("objectList", objectList);
                if ( job.get(0).get("Entity ID").equalsIgnoreCase("All")) {

                    switch (entityType) {
                        case "Master Agreement":
                            values = getRecordId("MasterAgreement", threadMap, false);
                            break;
                        case "Contract":
                            values = getRecordId("Contract", threadMap, false);
                            break;
                        case "Lease Component":
                            values = getRecordId("LeaseComponent", threadMap, false);
                            break;
                        case "Activation Group":
                            values = getRecordId("ActivationGroup", threadMap, false);
                            break;
                        default:
                            System.out.println("Unknown ObjectList type: " + entityType);
                    }

                } else {
                    String objectListValue= job.get(0).get("Entity ID");
                    values = getRecordId(objectListValue, threadMap, true);
                }
            }
            if (!Strings.isEmpty(MasterHooks.agReverted.get())) {
                clickOnDropDownToTypeAndVerifyValueIsNull("objectList", objectList, values);
                waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                waitAndClickOnElement(cancelButton);
            } else {
                clickOnDropDownToTypeAndSelectCheckBox("ObjList", ObjList, values);
                waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                WaitUntilElementIsClickable(scheduleJobButton);
                waitAndClickOnElement(scheduleJobButton);
                waitUntilLoadingSpinnerIsShown("nlaDropDownPopUp");
                waitUntilLoadingSpinnerIsGone("nlaDropDownPopUp");
                log.info("Created Schedule Posting Job");
                //Waiting for page records to Increase
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
                wait.until(new Function<WebDriver, Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        try {
                            Thread.sleep(2000);
                            int recordAfterJobs;
                            if (driver.findElements(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).size() == 0) {
                                recordAfterJobs = 0;
                            } else {
                                String records = driver.findElement(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
                                recordAfterJobs = Integer.parseInt(records.substring(records.indexOf("f") + 2));
                            }
                            if (recordAfterJobs != recordBeforeJob) {
                                return true;
                            } else {
                                return false;
                            }
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                log.info("Profile deleted successfully");
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void enableOrDisableIndexationScheduleJob() {
        try {
        String records = driver.findElement(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
        int recordBeforeJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
        waitTillWebElementIsVisible("searchScheduledJob", searchScheduledJobID);
        String scheduleID=driver.findElement(By.cssSelector("#q-app .q-table__container .q-tr:nth-child(2) .q-td:nth-child(1)")).getText();
        waitAndClickOnElement(searchScheduledJobID);
        sendingValueToWebElement("ScheduledJobName", searchScheduledJobID, scheduleID);
        handleWait(1000);
        waitTillWebElementIsVisible("enableScheduleJobRadioBtn", enableScheduleJobRadioBtn);
        waitTillWebElementIsVisible("disableScheduleJob", disableScheduleJobRadioBtn);
        waitAndClickOnElement(disableScheduleJobRadioBtn);
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
        waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
        int result = driver.findElements(By.cssSelector("#q-app tbody tr:nth-child(2) .q-radio[aria-label='Disable'][aria-checked=true]")).size();
        Assert.assertEquals("Scheduled job has been disabled.", 1, result);
        log.info("Scheduled job disabled successfully");
            waitAndClickOnElement(cancelSearchJob);
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
            wait.until(new Function<WebDriver, Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    String records = driver.findElement(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
                    int recordAfterJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
                    return recordAfterJob == recordBeforeJob;
                }
            });
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteScheduleJobForIndexation() {
        try {
            log.info("User is going to delete the sceduled job");
            int recordBeforeJob;
            String records = driver.findElement(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
            recordBeforeJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
            waitTillWebElementIsVisible("scheduleDeletebutton", scheduledJobDeletion);
            handleWait(3000);
            waitAndClickOnElement(scheduledJobDeletion);
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
            wait.until(new Function<WebDriver, Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    int recordAfterJobs;
                    if (driver.findElements(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).size() == 0) {
                        recordAfterJobs = 0;
                    } else {
                        String records = driver.findElement(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
                        recordAfterJobs = Integer.parseInt(records.substring(records.indexOf("f") + 2));
                    }
                    return recordAfterJobs < recordBeforeJob;

                }
            });
            log.info("Scheduled job deleted successfully");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int getRecordCountBefore(String name, String cssSelector) {
        int recordCount = 0;

        // Wait until the element with the record count is visible
        if (waitUnTillWebElementIsVisible(name, cssSelector)) {
            String records = driver.findElement(By.cssSelector(cssSelector)).getText();
            recordCount = Integer.parseInt(records.substring(records.indexOf("f") + 2));
            log.info("Record Before Count is " + recordCount);
        }
        return recordCount;
    }

    public void createInterCompanyTransferJob(DataTable dt) {
        log.info("Creating Inter Company Transfer Job");
        List<Map<String, String>> job = dt.asMaps(String.class, String.class);
        try {
            applyCreatedByFilter();
            int recordBeforeJob;
            if (driver.findElements(By.cssSelector("#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).isEmpty()) {
                recordBeforeJob = 0;
            } else {
                String records = driver.findElement(By.cssSelector("#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
                recordBeforeJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
            }
            waitAndClickOnElement(addJobButton);
            waitTillWebElementIsVisible("createProfilePopUp", ".q-dialog .qcard-dialogue");
            //Job Information
            waitAndClickOnElement("jobInformation",".q-dialog #info-btn");
            waitAndClickOnElement("submitButton",".q-dialog .q-card #submit-btn");
            waitTillWebElementIsEnabled("name",".q-dialog #name-input-input");
            sendingValueToWebElement("jobName",jobNameInterCompanyTransfer,job.get(0).get("Name"));
            waitTillWebElementIsEnabled("transferDate",".q-dialog .qcard-dialogue #transfer-date-input");
            sendingValueToWebElement("transferDate",transferDateInterCompanyTransfer,job.get(0).get("Transfer Date"));
            waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
            //ERP System
            if(!job.get(0).get("Erp System Filter").equalsIgnoreCase("All")){
                waitTillWebElementIsVisible("erpSystemDropdown", erpSystemFilter);
                clickOnDropDownAndSelectValue("erpSystemFilter",erpSystemFilter, job.get(0).get("Erp System Filter"));
                waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
                waitTillWebElementIsEnabled("erpSystemFilterValues",".q-dialog  .qcard-dialogue .dialog-body #erp-systems");
                clickOnDropDownToTypeAndSelectCheckBox("erpSystemDropDown", erpSystemFilterValues, MasterHooks.configurationProperties.get().getErpSystem());
                waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
            }
            //Lease Area
            if(!job.get(0).get("Lease Area").equalsIgnoreCase("All")){
                clickOnDropDownAndSelectValue("leaseAreaFilter",leaseAreaFilter,"List");
                waitTillWebElementIsVisible("leaseAreaField",leaseAreaFilterValue);
                clickOnDropDownToTypeAndSelectCheckBox("leaseAreaValues",leaseAreaFilterValue,job.get(0).get("Lease Area"));
                waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
                waitForWebElementToDisappear("dropDownOptions",".q-menu");
            }
            //Business Unit
            if(!job.get(0).get("Business Unit").equalsIgnoreCase("All")){
                clickOnDropDownAndSelectValue("BusinessUnitFilter",businessUnitFilter,"List");
                waitTillWebElementIsVisible("BusinessUnitField",businessUnitFilterValues);
                clickOnDropDownToTypeAndSelectCheckBox("BusinessUnitValues",businessUnitFilterValues,job.get(0).get("Business Unit"));
                waitAndClickOnElement(leaseAreaFilter);
                waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
                waitForWebElementToDisappear("dropDownOptions",".q-menu");
                waitAndClickOnElement(profileName);
            }
            //Company
            if(!job.get(0).get("Company").equalsIgnoreCase("All")){
                waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                waitAndClickOnElement(profileName);
                clickOnDropDownAndSelectValue("CompanyFilter",companyFilter,"List");
                waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
                waitTillWebElementIsVisible("CompanyField",companyFilterValues);
                clickOnDropDownToTypeAndSelectCheckBox("companyCodes",companyFilterValues,job.get(0).get("Company"));
                waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
                waitForWebElementToDisappear("dropDownOptions",".q-menu");
                waitAndClickOnElement(profileName);
            }
            String entityLevel = job.get(0).get("List Filter Type");
            if (!entityLevel.equalsIgnoreCase("All")) {
                waitTillWebElementIsVisible("ListFilterType", listFilterType);
                clickOnDropDownAndSelectValue("ListFilter", listFilterType, entityLevel);
                String ID = MasterHooks.searchCTID.get();
                waitTillWebElementIsVisible("ObjList", objectList);
                clickOnDropDownToTypeAndSelectCheckBox("ObjList", objectList, ID);
                waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
            }
            WebElement submitButton = driver.findElement(By.cssSelector(".q-dialog .q-card #run-job-btn"));
            waitAndClickOnElement(submitButton);
            waitTillWebElementIsVisible("warningProceedButton", ".q-dialog .q-card #submit-btn");
            waitAndClickOnElement("warningProceedButton", ".q-dialog .q-card #submit-btn");
            waitUntilLoadingSpinnerIsGone("nlaDropDownPopUp");
            log.info("Created Inter Company Transfer Job");
            Wait<WebDriver> wait =
                new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
            wait.until(new Function<WebDriver, Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    String records =
                        driver.findElement(By.cssSelector("#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
                    int recordAfterJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
                    if (recordAfterJob != recordBeforeJob) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void revertJob(String jobName) {
        log.info("Reverting the " + jobName + " Job");
        try {
            applyCreatedByFilter();
            log.info("Reverting the Job");
            waitTillWebElementIsVisible("revertJobButton", revertButtonICTJob);
            waitAndClickOnElement(revertButtonICTJob);
            waitTillWebElementIsVisible("warningProceedButton", ".q-dialog .q-card #submit-btn");
            waitAndClickOnElement("warningProceedButton", ".q-dialog .q-card #submit-btn");
            waitUntilLoadingSpinnerIsGone("nlaDropDownPopUp");
            //Job Completion
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(300)).pollingEvery(Duration.ofMillis(5000)).ignoring(WebDriverException.class);
            wait.until(new Function<WebDriver, Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    try {
                        waitAndClickOnElement(refreshJobButton);
                        handleWait(3000);
                        if (driver.findElements(By.cssSelector("#q-app .q-page-container .q-spinner")).isEmpty() && driver.findElements(By.cssSelector("#q-app .jobs-grid .q-table tr:nth-of-type(2) .q-td:nth-child(4) .q-chip")).size()==1) {
                            String jobStatus = driver.findElement(By.cssSelector("#q-app .jobs-grid .q-table tr:nth-of-type(2) .q-td:nth-child(4) .q-chip__content")).getText().replaceFirst("^\\S+\\s*", "").trim();
                            if(jobStatus.equalsIgnoreCase("Reverted")){
                                log.info("Revert Job is Completed");
                            }
                            return true;
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return false;
                }
            });
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void viewJob(String jobName){
        log.info("Reverting the "+jobName+" Job");
        log.info("Selector issue");
    }

    public void userCancelJob(String cancelOperation, String jobName) {
        log.info("Canceling the " + cancelOperation + " of " + jobName);
        try {
            if(cancelOperation.contains("Task")) {
                waitAndClickOnElement(refreshButton);
                cancelTasks(cancelOperation, jobName);
                //If Jobs are more than 20
                String records = driver.findElement(By.cssSelector("#q-app .tasks-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
                int recordAfterJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
                if (recordAfterJob > 20) {
                    cancelJob(jobName);
                }
            }else if (cancelOperation.contains("Job")) {
                cancelJob(jobName);
            }
//            else if (cancelOperation.equalsIgnoreCase("Task & Job")) {
//                cancelTasks(cancelOperation, jobName);
//                //cancel Job
//                cancelJob(jobName);
//            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
}

    private void cancelTasks(String cancelOperation, String jobName) {
        int jobsSize = driver.findElements(By.cssSelector("#q-app .q-page .col .q-table tbody tr")).size();
        int jobToCancel = cancelOperation.equalsIgnoreCase("All Task") ? jobsSize : jobsSize - 1;
        int jobIndex = jobName.equalsIgnoreCase("Disclosure Report") ? 1 : 2;
        log.info("Cancelling tasks for job '{}', from row {} to {}", jobName, jobIndex, jobToCancel);

        for (index = jobIndex; index <= jobToCancel; index++) {
            try {
                String selector;
                waitTillWebElementIsVisible("job","#q-app .q-page .col .q-table tbody tr:nth-child("+index+")");
                switch (jobName) {
                    case "Mass Indexation":
                        selector = "#q-app .q-page .col .q-table tbody tr:nth-child(" + index + ") #report-btn:nth-child(2)";
                        break;
                    case "Mass Workflow Transition":
                    case "Mass Modification":
                    case "Inter Company Transfer":
                        selector = "#q-app .q-page .col .q-table tbody tr:nth-child(" + index + ") #cancel-btn";
                        break;
                    case "Operational Postings":
                        selector = "#q-app .q-page .col .q-table tbody tr:nth-child(" + index + ") #report-btn:nth-child(1)";
                        break;
                    case "Disclosure Report":
                        selector = "#q-app .q-page .col .q-table tbody tr:nth-child(" + index + ") #report-btn";
                        break;
                    default:
                        log.warn("Unknown job type: {}", jobName);
                        continue;
                }
                if(!(getSizeOfElements(selector)==1)){
                    waitAndClickOnElement(refreshButton);
                }
                waitAndClickOnElement("taskCancelButton", selector);
                log.info("Clicked cancel on row {}", index);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(1200)).pollingEvery(Duration.ofMillis(5000)).ignoring(WebDriverException.class);
        for (index = jobIndex; index <= jobToCancel; index++) {
            wait.until(new Function<WebDriver, Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    try {
                        waitAndClickOnElement(refreshButton);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handleWait(300);
                    String status = getValueFromElement(".q-page .col.q-py-sm tbody tr:nth-child("+index+") .q-td [role='status']");
                    if (status.equalsIgnoreCase("Cancelled") || status.equalsIgnoreCase("Generation Cancelled") ||
                        status.equalsIgnoreCase("Mass Modification Cancelled") || status.equalsIgnoreCase("Template Generation Cancelled") ) {
                        log.info("Task No: " + index + " has been Cancelled Successfully");
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    private void cancelJob(String jobName) {
        log.info("Cancelling the Job " +jobName);
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(1200)).pollingEvery(Duration.ofMillis(5000)).ignoring(WebDriverException.class);
        boolean isReport = jobName.contains("Report");
        String cancelSelector = "";
        String statusSelector = "";
        WebElement refreshButtonSelector = isReport ? jobRefreshButton : refreshJobButton;
        try {
            waitAndClickOnElement(refreshButtonSelector);
            handleWait(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Determine selectors based on job type
        switch (jobName) {
            case "Activity Analysis Report":
                cancelSelector = ".q-table .q-tr:nth-child(2) #cancel-action";
                statusSelector = ".q-table .q-tr:nth-child(2) .status .q-badge";
                break;
            case "Periodic Posting Status Report":
                cancelSelector = ".q-table .q-tr:nth-child(2) #report-btn";
                statusSelector = ".q-table .q-tr:nth-child(2) .status .q-badge";
                break;
            case "Operational Postings":
            case "Mass Indexation":
                cancelSelector = "#q-app .q-page .jobs-grid #report-btn:nth-child(1)";
                statusSelector = ".q-page .jobs-grid tbody tr:nth-child(2) .q-td [role='status']";
                break;
            case "Mass Workflow Transition":
            case "Mass Modification":
                cancelSelector = "#q-app .q-page .jobs-grid #report-btn:nth-child(1)";
                statusSelector = ".q-page .jobs-grid tbody tr:nth-child(2) .q-td:nth-child(5) div";
                break;
            case "Inter Company Transfer":
                cancelSelector = "#q-app .q-page .jobs-grid #report-btn:nth-child(1)";
                statusSelector = ".q-page .jobs-grid tbody tr:nth-child(2) .q-td:nth-child(4) div";
                break;
            case "GL Balance Report":
            case "Consolidated Transaction Report":
                cancelSelector = "#q-app .q-page tr:nth-child(2) #cancel-action";
                statusSelector = ".q-table .q-tr:nth-child(2) .status .q-badge";
                break;
            default:
                log.warn("Unknown job type: {}", jobName);
                return;
        }
        try {
            if(!(getSizeOfElements(cancelSelector)==1)) {
                waitAndClickOnElement(refreshButtonSelector);
            }
            waitAndClickOnElement("cancelButton", cancelSelector);
            log.info("Clicked cancel button for job: {}", jobName);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Failed to click cancel for " + jobName, e);
        }
        String finalStatusSelector = statusSelector;
        wait.until(driver -> {
            try {
                waitAndClickOnElement(refreshButtonSelector);
                if (!isReport) {
                    waitUntilLoadingSpinnerIsGone("batchJobGridLoader");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            handleWait(300);
            String status = getValueFromElement(finalStatusSelector);
            if (status != null && status.equalsIgnoreCase("Cancelled")) {
                log.info(jobName+" has been Cancelled Successfully");
                return true;
            }
            return false;
        });
    }

    public void userCopyJob(String jobName) {
        log.info("Copying the Job of {}", jobName);
        // Get initial record count
        int recordBeforeJob;
        List<WebElement> recordElements = driver.findElements(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item"));
        if (!recordElements.isEmpty()) {
            String records = recordElements.get(0).getText();
            recordBeforeJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
        } else {
            recordBeforeJob = 0;
        }
        // Open Copy dialog
        String selector = "";
        switch (jobName) {
            case "Mass Indexation":
            case "Mass Workflow Transition":
            case "Mass Modification":
            case "Inter Company Transfer":
                selector = ".q-page .jobs-grid tbody tr:nth-child(2) #view-btn .mdi-content-copy";
                break;
            case "Operational Postings":
                selector = ".q-page .jobs-grid tbody tr:nth-child(2) #copy-btn";
                break;
            case "Activity Analysis Report":
            case "Periodic Posting Status Report":
            case "Disclosure Report":
            case "GL Balance Report":
            case "Consolidated Transaction Report":
                selector = ".q-page tbody tr:nth-child(2) #view-btn .mdi-content-copy";
                break;
        }
        waitTillWebElementIsVisible("copyButton", selector);
        try {
            waitAndClickOnElement("copyButton", selector);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Failed to click Copy button", e);
        }
        waitTillWebElementIsVisible("popup", ".desktop .q-dialog .q-card");
        if(!jobName.equalsIgnoreCase("Inter Company Transfer")) {
            waitTillWebElementIsVisible("radioButton", ".q-dialog .q-card tbody tr:nth-child(2) .q-td:nth-child(1) .q-radio");
            if (getSizeOfElements(".q-dialog .q-card tbody tr:nth-child(2) .q-td:nth-child(1) .q-radio[aria-checked='true']") == 0) {
                String profileType = jobName.contains("Report") ? "reportProfileID" : "batchProfileID";
                String profileValue = jobName.contains("Report")
                        ? MasterHooks.reportProfileID.get()
                        : MasterHooks.batchProfileID.get();
                selectProfile(profileType, profileValue);
                try {
                    switch (jobName) {
                        case "Operational Postings":
                        case "Mass Indexation":
                            clickOnDropDownToTypeAndSelectCheckBox("objectList", objectList, MasterHooks.searchAGID.get());
                            WaitUntilElementIsClickable(addOperationalJob);
                            waitAndClickOnElement(addOperationalJob);
                            break;
                        case "Mass Workflow Transition":
                            clickOnDropDownToTypeAndSelectCheckBox("objectList", objectList, MasterHooks.searchMLAID.get());
                            WaitUntilElementIsClickable(addWorkflowJob);
                            waitAndClickOnElement(addWorkflowJob);
                            break;
                        case "Mass Modification":
                            clickOnDropDownToTypeAndSelectCheckBox("objectList", objectList, MasterHooks.searchLCID.get());
                            waitAndClickOnElement(driver.findElement(By.cssSelector(".q-dialog .q-card #run-job-btn")));
                            break;
                        case "Periodic Posting Status Report":
                        case "GL Balance Report":
                            clickOnDropDownToTypeAndSelectCheckBox("objectList", objectList, MasterHooks.searchAGID.get());
                            break;
                        case "Disclosure Report":
                            clickOnDropDownToTypeAndSelectCheckBox("objectList", objectList, MasterHooks.searchCTID.get());
                            break;
                        default:
                            log.info("Unknown job type: {}", jobName);
                            return;
                    }
                } catch (InterruptedException | IOException e) {
                    throw new RuntimeException("Error while selecting profile for " + jobName, e);
                }
            }  else {
                try {
                    switch (jobName) {
                        case "Operational Postings":
                        case "Mass Indexation":
                            WaitUntilElementIsClickable(addOperationalJob);
                            waitAndClickOnElement(addOperationalJob);
                            break;
                        case "Mass Workflow Transition":
                            WaitUntilElementIsClickable(addWorkflowJob);
                            waitAndClickOnElement(addWorkflowJob);
                            break;
                        case "Mass Modification":
                            waitAndClickOnElement(driver.findElement(By.cssSelector(".q-dialog .q-card #run-job-btn")));
                            break;
                        case "Consolidated Transaction Report":
                            waitAndClickOnElement(driver.findElement(By.cssSelector(".q-dialog  .q-card #run-consolidated-transaction-job-btn")));
                            break;

                    }
                    waitUntilLoadingSpinnerIsGone("nlaAddButtonLoader");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Error while submitting copied job", e);
                }
            }
            if (driver.findElements(By.cssSelector(".q-dialog .q-card #submit-btn")).size() == 1) {
                clickOnSubmitPopup("Submit / Add");
            }
            waitUntilLoadingSpinnerIsGone("nlaDropDownPopUp");
            handleWait(1000);
        } else{
            try {
                WebElement submitButton = driver.findElement(By.cssSelector(".q-dialog .q-card #run-job-btn"));
                waitAndClickOnElement(submitButton);
                waitTillWebElementIsVisible("warningProceedButton", ".q-dialog .q-card #submit-btn");
                waitAndClickOnElement("warningProceedButton", ".q-dialog .q-card #submit-btn");
                waitUntilLoadingSpinnerIsGone("nlaDropDownPopUp");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        log.info("Copied the job successfully{}", jobName);
        // Wait for record count to increase (new job added)
        Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);

        wait.until(driver -> {
            String records = driver.findElement(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
            int recordAfterJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
            return recordAfterJob != recordBeforeJob;
        });

    }
}
