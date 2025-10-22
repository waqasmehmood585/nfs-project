package com.nakisa.nlaAutomation.pageObjects;

import com.nakisa.nlaAutomation.Constant;
import com.nakisa.nlaAutomation.stepDefinitions.MasterHooks;
import io.cucumber.datatable.DataTable;
import lombok.extern.slf4j.Slf4j;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class ReportingModule_PageObject extends Common_BasePage_PageObject {

    private File misMatchExcelFile;
    private static final String subHeader = "eventdata";

    public @FindBy(css = ".q-dialog  .q-card #name-input") WebElement reportNameScheduleJob;
    public @FindBy(css = ".q-dialog  .q-card #submit-btn") WebElement submitButton;
    private String recordPerPage = "#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item";

    //Disclosure Jobs
    public @FindBy(css = "#q-app .q-page .q-btn-group #create-job-btn") WebElement addJobButton;
    public @FindBy(css = ".q-dialog .q-card #from-date-input-input") WebElement fromYear;
    public @FindBy(css = ".q-dialog .q-card #from-period") WebElement fromPeriod;
    public @FindBy(css = ".q-dialog .q-card #to-date-input-input") WebElement toYear;
    public @FindBy(css = ".q-dialog .q-card #to-period") WebElement toPeriod;
    public @FindBy(css = ".q-dialog .q-card #start-date-input-input[aria-label='From Date *']") WebElement fromDate;
    public @FindBy(css = ".q-dialog .q-card #start-date-input-input[aria-label='To Date *']") WebElement toDate;
    public @FindBy(css = ".q-dialog .q-card #principal-position-type") WebElement principalPosition;
    public @FindBy(css = ".q-dialog .q-card #accounting-standard-type") WebElement accountingStandard;
    public @FindBy(css = ".q-dialog .q-card #classifications") WebElement classification;
    public @FindBy(css = ".q-dialog .q-card #currency") WebElement currency;
    public @FindBy(css = ".q-dialog .q-card #activation-group-status") WebElement activationGroupStatus;
    public @FindBy(css = ".q-dialog  .q-card #list-object-filter") WebElement objectListType;
    public @FindBy(css = ".q-dialog  .q-card #object-list-input .q-field") WebElement objectList;
    public @FindBy(css = ".q-dialog  .q-card #master-agreement-object-list-input") WebElement objectListAAR;
    public @FindBy(css = ".q-dialog  .q-card #report-toggle-asset-roll-forward-report") WebElement assetRollForwardReport;
    public @FindBy(css = ".q-dialog  .q-card #report-toggle-lessee-cash-flow-report") WebElement cashFlowReport;
    public @FindBy(css = ".q-dialog  .q-card #report-toggle-expense-report") WebElement expenseReport;
    public @FindBy(css = ".q-dialog  .q-card #report-toggle-lease-liability-report") WebElement leaseLiabilityReportt;
    public @FindBy(css = ".q-dialog  .q-card #report-toggle-maturity-analysis-report") WebElement maturityAnalysisReport;
    public @FindBy(css = ".q-dialog  .q-card #report-toggle-non-lease-charges-expense-report") WebElement nonLeaseChargesExpenseReport;
    public @FindBy(css = ".q-dialog  .q-card #report-toggle-weighted-avg-discount-rate-report") WebElement weightedAvgDiscountRateReport;
    public @FindBy(css = ".q-dialog  .q-card #report-toggle-weighted-avg-lease-term-report") WebElement weightedAvgLeaseTermReport;
    public @FindBy(css = "#q-app .q-page .tasks-grid #refresh-btn") WebElement taskRefreshButton;
    //Activity Analysis Job
    public @FindBy(css = "#q-app .q-page .q-btn-group #add-btn") WebElement addJobButtonAA;
    public @FindBy(css = "#q-app .q-page .q-btn-group #create-job-btn") WebElement addScheduleJobButtonAA;
    public @FindBy(css = ".q-dialog  .q-card #report-types") WebElement reportTypeAA;
    public @FindBy(css = ".q-dialog  .q-card #lease-areas") WebElement activationGroupStatusAA;
    public @FindBy(css = ".q-dialog  .q-card #lease-type") WebElement leaseTypeAA;
    public @FindBy(css = ".q-dialog  .q-card #partner") WebElement partnerAA;
    public @FindBy(css = ".q-dialog  .q-card #internal-asset-class") WebElement assetClassAA;
    public @FindBy(css = ".q-dialog  .q-card #object-list-type") WebElement objectListTypeAA;
    public @FindBy(css = "#q-app .q-page #refresh-btn") WebElement jobRefreshButton;
    public @FindBy(css = "#q-app .q-page tbody tr:nth-child(2) #generate-btn") WebElement reportDownloadButtonAA;
    //Periodic Posting Status Job
    public @FindBy(css = ".q-dialog  .q-card #posting-type") WebElement postingType;
    public @FindBy(css = ".q-dialog  .q-card #internal-payment-posting-status-filter-type") WebElement internalStatusFilter;
    public @FindBy(css = ".q-dialog  .q-card .form-input:nth-child(2) #external-posting-status") WebElement internalStatus;
    public @FindBy(css = ".q-dialog  .q-card #external-payment-posting-status-filter-type") WebElement externalStatusFilter;
    public @FindBy(css = ".q-dialog  .q-card .form-input:nth-child(4) #external-posting-status") WebElement externalStatus;
    public @FindBy(css = ".q-dialog .q-card #object-list-input") WebElement objectListPPSR;

    //Schedule Job
    public @FindBy(css = ".q-dialog .q-card .q-tab-panels #hour-freq-input") WebElement hourlyHours;
    public @FindBy(css = ".q-dialog .q-card .q-tab-panels #day-freq-input") WebElement dailyDay;
    public @FindBy(css = ".q-dialog .q-card .q-tab-panels #hour-of-day-selection") WebElement hour;
    public @FindBy(css = ".q-dialog .q-card .q-tab-panels #month-freq-input") WebElement monthlyMonth;
    public @FindBy(css = ".q-dialog .q-card .q-tab-panels #day-of-month-selection") WebElement date;
    public @FindBy(css = "#q-app .q-toolbar #user-btn") WebElement userBtn;
    public @FindBy(css = "[role='menu'] .q-item[role='listitem'] div.text-grey") WebElement userEmail;
    public @FindBy(css = "#q-app .jobs-grid #search-created-by-input") WebElement createdByFilter;
    public @FindBy(css = "#q-app .q-table__container .q-td:nth-child(2) #id-input-input") WebElement searchProfileIDFieldSAPReports;
    public @FindBy(css = "#q-app .q-table__container #id-search-input") WebElement searchScheduleJobIDReports;
    public @FindBy(css = "#q-app .q-table__container #search-id-input") WebElement searchScheduleJobIDDRReports;
    public @FindBy(css = "#q-app .q-table__container .q-td:nth-child(1) .q-field input[aria-label='Search']") WebElement searchScheduledJobFieldID;
    public @FindBy(css = ".q-page .q-btn-group #delete-btn") WebElement deleteProfile;
    public @FindBy(css = ".q-page .q-btn-group #delete-profile-btn") WebElement deleteProfileDisclosure;
    public @FindBy(css = "#q-app tbody tr:nth-child(2) Button.text-red") WebElement scheduledJobDeletion;
    public @FindBy(css = "#q-app tbody tr:nth-child(2)  .q-radio[aria-label='Enable'][aria-checked='true']") WebElement enableScheduleJobRadioBtn;
    public @FindBy(css = "#q-app tbody tr:nth-child(2)  .q-radio[aria-label='Disable']") WebElement disableScheduleJobRadioBtn;
    public @FindBy(css = "#q-app .q-page .q-anchor--skip>button.q-icon") WebElement cancelSearchJob;
    public @FindBy(css = ".q-page .q-table #cancel-action") WebElement cancelJob;
    public @FindBy(css = ".q-page .q-table .q-tr:nth-child(2) .q-td:nth-child(1)") WebElement selectProfiletoDelete;
    public @FindBy(css = ".q-card #submit-btn") WebElement confirmDeletion;
    public @FindBy(css = ".q-card .q-btn:nth-child(2)") WebElement confirmDeletionSAP;
    public @FindBy(css = ".q-page .q-table #able-action-btn") WebElement editProfileBtnAAPPSR;
    public @FindBy(xpath = "//*[contains(@id, 'edit-btn')]") WebElement editProfileBtnDR;
    public @FindBy(css = "#q-app .q-table .q-tr:nth-child(2)") WebElement clickEditSchJob;
    public @FindBy(css = ".q-card .dialog-body .q-panel #jan-checkbox") WebElement monthBox1;
    public @FindBy(css = ".q-card .dialog-body .q-panel #apr-checkbox") WebElement monthBox2;
    public @FindBy(css = ".q-card .dialog-body #yearly-tab") WebElement yearlyTab;
    public @FindBy(css = ".q-dialog .q-card #submit-btn[aria-disabled='true']") WebElement disableSubmit;
    public @FindBy(css = "#q-app .q-page-container [name='ag_display_id.keyword'] #aggsPanelView-searchAggsValues-btn") WebElement clickOnSearchAG;
    public @FindBy(css = "#q-app .q-page-container [name='ag_display_id'] #aggsPanelView-searchAggsValues-btn") WebElement clickOnSearchAGDQI;

    public @FindBy(css = "#q-app .q-page-container .q-anchor--skip > button") WebElement agClearField;
    public @FindBy(css = "#q-app .q-page-container [name='ag_display_id.keyword'] .agg-value-search-wrapper .q-field [placeholder='Search']") WebElement AgSearchField;
    public @FindBy(css = "#q-app .aggs-container .q-expansion-item:nth-child(6) .agg-value-search-wrapper .q-field [placeholder='Search']") WebElement AgSearchFieldDQI;
    public @FindBy(css = "#q-app .q-page-container [name='ag_display_id.keyword'] #aggValueSearchScrollArea") WebElement enterSerachedAgID;
    public @FindBy(css = "#q-app .q-page-container [name='ag_display_id'] #aggValueSearchScrollArea") WebElement enterSerachedAgIDDQI;
    public @FindBy(css = "#q-app .q-page-container #dashboardChart-exportOption-fabBtn") WebElement downloadClickCashFlow;
    public @FindBy(css = " #q-app .q-page-container #toolbar-exportTable-btn") WebElement downloadDQIReports;

    public @FindBy(css = "#q-app .q-page-container #dashboardChart-exportOptionItem-fabBtn:nth-child(1)") WebElement downloadXLS;
    public @FindBy(css = ".q-menu .page-export-options-excel-item:nth-child(1)") WebElement downloadXLSDQI;
    public @FindBy(css = ".desktop .q-header Button[aria-label='Menu']") WebElement hamburgerMenu;
    public @FindBy(css = "#q-app .main-menu #main-menu-item-analytics-exports-page") WebElement hamburgerReports;
    public @FindBy(css = "#q-app .q-page-container .buttons .q-btn[aria-label='Refresh']") WebElement refresh;
    public @FindBy(css = "#q-app .q-page-container .q-table tr:nth-child(1) .q-btn[aria-label='Download']") WebElement downloadReport;
    public @FindBy(css = "#q-app .q-page-container  .q-btn[aria-label='Delete All Files']") WebElement deleteAllFiles;
    public @FindBy(css = "#q-app .q-page-container #aggsPanelView-resetAllFilters-btn") WebElement resetFilters;
    public @FindBy(css = "#q-app .q-page-container #aggsPanelView-collapseAllAggs-btn") WebElement collapseExpandBtn;
    public @FindBy(css = "#q-app .q-page-container [name='ag_display_id']") WebElement agDisplayIDDQI;
    public @FindBy(css = ".q-dialog  .q-card #id-search-input") WebElement searchProfileInputField;


    public ReportingModule_PageObject() {
        super();
        log.info("Driver is inside this class: " + this.getClass().getSimpleName());
        PageFactory.initElements(driver, this);
    }

    public void createDisclosureReportJob(String jobType, String reportLevel, DataTable dataTable) {
        log.info("Creating Disclosure Report Jobs");
        List<Map<String, String>> job = dataTable.asMaps(String.class, String.class);
        try {
            if (!jobType.equalsIgnoreCase("Scheduled Job")) {
                waitAndClickOnElement("jobRefreshButton", "#q-app .q-page .jobs-grid #refresh-job-btn");
                applyCreatedByFilter("Disclosure");
            }
            int recordBeforeJob;
            waitTillWebElementIsVisible("addJobButton", addJobButton);
            if (!jobType.equalsIgnoreCase("Scheduled Job")) {
                recordBeforeJob = getRecordCountBefore(jobType, "#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item");
            } else {
                recordBeforeJob = getRecordCountBefore(jobType, recordPerPage);
            }
            if (MasterHooks.configurationProperties.get().getRunAutomationOnNCPBuild()) {
                handleWait(2000);
            }
            waitAndClickOnElement(addJobButton);
            waitTillWebElementIsVisible("createJobPopUp", ".q-dialog  .qcard-dialogue");
            if (jobType.equalsIgnoreCase("Scheduled Job")) {
                waitAndClickOnElement(reportNameScheduleJob);
                sendingValueToWebElement("reportName", reportNameScheduleJob, job.get(0).get("Name"));
            }

            selectProfile("Disclosure Report", MasterHooks.reportProfileID.get());
            waitTillWebElementIsVisible("createJobPopUpCalendarNameField", ".q-dialog .q-card #calendar-type");
            //Job Date Inputs
            if (!job.get(0).get("Calendar Type").equalsIgnoreCase("Fiscal Variant")) {

                sendingValueToWebElement("fromDate", fromDate, job.get(0).get("From Date"));
                sendingValueToWebElement("toDate", toDate, job.get(0).get("To Date"));
            } else {

                sendingValueToWebElement("fromYear", fromYear, job.get(0).get("From Year"));
                clickOnDropDownToTypeAndSelectValue("fromPostingPeriod", fromPeriod, job.get(0).get("From Posting Period"));
                sendingValueToWebElement("toYear", toYear, job.get(0).get("To Year"));
                clickOnDropDownToTypeAndSelectValue("toPostingPeriod", toPeriod, job.get(0).get("To Posting Period"));
            }
            //Additional Filters
//            clickOnDropDownAndSelectValue("drPrincipalPosition", principalPosition,
//                   job.get(0).get("Principal Position", 0));
            clickOnDropDownAndSelectValue("drAccountingStandard", accountingStandard, job.get(0).get("Accounting Standard"));
            clickOnDropDownAndSelectCheckBoxes("drClassification", classification, job.get(0).get("Classification"));
            clickOnDropDownToTypeAndSelectValue("drCurrency", currency, job.get(0).get("Currency"));
            String agStatus = job.get(0).get("Activation Group Status");
            if (!agStatus.equalsIgnoreCase("null")) {
                clickOnDropDownAndSelectValue("activationGroupStatus", activationGroupStatus, job.get(0).get("Activation Group Status"));
            }
            String values = "";
            HashMap<String, String> threadMap = MasterHooks.map.get();
            //Object List
            String ObjectList = job.get(0).get("Object List Type");
            if (!(ObjectList.equalsIgnoreCase("null"))) {
                clickOnDropDownAndSelectValue("objectListType", objectListType, job.get(0).get("Object List Type"));
                waitTillWebElementIsVisible("objectListFiled", objectList);
                if (job.get(0).get("Object List ID").equalsIgnoreCase("All")) {

                    switch (ObjectList) {
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
                            System.out.println("Unknown ObjectList type: " + ObjectList);
                    }
                    clickOnDropDownToTypeAndSelectCheckBox("objectListValue", objectList, values);
                } else {
                    String objectListValue = job.get(0).get("Object List ID");
                    values = getRecordId(objectListValue, threadMap, true);
                    clickOnDropDownToTypeAndSelectCheckBox("objectListValue", objectList, values);
                }
            }
            //Reports Toggle Buttons
            selectReportDR(job, "Asset Roll Forward Report", assetRollForwardReport);
            selectReportDR(job, "Cash Flow Report", cashFlowReport);
            selectReportDR(job, "Expense Report", expenseReport);
            selectReportDR(job, "Lease Liability Report", leaseLiabilityReportt);
            selectReportDR(job, "Maturity Analysis Report", maturityAnalysisReport);
            selectReportDR(job, "Non Lease Charge Expense Report", nonLeaseChargesExpenseReport);
            selectReportDR(job, "Weighted Avg Discount Rate Report", weightedAvgDiscountRateReport);
            selectReportDR(job, "Weighted Avg Lease Term Report", weightedAvgLeaseTermReport);

            if (jobType.equalsIgnoreCase("Scheduled Job")) {
                reportScheduling(reportLevel, job);
            }
            if (driver.findElements(By.cssSelector("#submit-btn[aria-disabled='true']")).size() == 1) {
                Assert.assertFalse(disableSubmit.isEnabled(), "Mandatory fields are missing");
                log.info("Mandatory fields are missing");
            } else {
                clickOnSubmitPopup("Submit / Add");
                waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
                waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
                log.info("Created Disclosure Job");
                //Waiting for page records to Increase
                if (!jobType.equalsIgnoreCase("Scheduled Job")) {
                    waitAndClickOnElement("jobRefreshButton", "#q-app .q-page .jobs-grid #refresh-job-btn");
                    waitForRecordChange(recordBeforeJob, "#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item");
                } else {
                    waitForRecordChange(recordBeforeJob, recordPerPage);
                }
            }

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

    }

    //check job completion status
    private void checkJobCompletion(String jobStatusToCheck, int jobNumber) {
        log.info("Checking the Jobs Completed Successfully");

        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(1200)).pollingEvery(Duration.ofMillis(10000)).ignoring(WebDriverException.class);
            wait.until(new Function<WebDriver, Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    try {
                        waitAndClickOnElement(taskRefreshButton);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (driver.findElement(By.cssSelector(".q-page .col.q-py-sm tbody tr:nth-child(" + jobNumber + ") .q-td [role='status']")).getText().equalsIgnoreCase(jobStatusToCheck)) {
                        log.info("Job Number " + jobNumber + " Completed");
                        return true;
                    } else if (driver.findElement(By.cssSelector(".q-page .col.q-py-sm tbody tr:nth-child(" + jobNumber + ") .q-td [role='status']")).getText().equalsIgnoreCase("Failed")) {
                        log.info("Job Number " + jobNumber + " Failed");
                        Assert.fail("The DR Report Task" + jobNumber + " Is Failed");
                        return true;
                    } else {
                        log.info("Job Number " + jobNumber + " is not Completed Yet");
                        return false;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Job Completed Successfully");
    }

    public void createActivityAnalysisReportJob(String jobType, String reportLevel, DataTable dataTable) {
        log.info("Creating Activity Analysis Report Jobs");
        List<Map<String, String>> job = dataTable.asMaps(String.class, String.class);
        try {
            handleWait(2000);
            if (!jobType.equalsIgnoreCase("Scheduled Job")) {
                applyCreatedByFilter("Activity Analysis");
            }
            int recordBeforeJob = getRecordCountBefore(jobType, recordPerPage);
            if (!jobType.equalsIgnoreCase("Scheduled Job")) {
                waitTillWebElementIsVisible("addAAJobButton", addJobButtonAA);
                waitAndClickOnElement(addJobButtonAA);
            } else {
                waitTillWebElementIsVisible("addSchelueAAJobButton", addScheduleJobButtonAA);
                waitAndClickOnElement(addScheduleJobButtonAA);
            }

            waitTillWebElementIsVisible("createJobPopUp", ".q-dialog  .qcard-dialogue");
            if (jobType.equalsIgnoreCase("Scheduled Job")) {
                waitAndClickOnElement(reportNameScheduleJob);
                sendingValueToWebElement("reportName", reportNameScheduleJob, job.get(0).get("Name"));
            }
            clickOnDropDownAndSelectCheckBoxes("reportTypeAA", reportTypeAA,
                    job.get(0).get("Report Type"));
            waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
            waitForWebElementToDisappear("dropDownOptions", ".q-menu");
            if (job.get(0).get("Report Type").contains("By Amount")) {
                waitTillWebElementIsVisible("activationGroupStatusAA", activationGroupStatusAA);
                clickOnDropDownAndSelectCheckBoxes("activationGroupStatusAA", activationGroupStatusAA, job.get(0).get("Activation Group Status"));
                waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                waitForWebElementToDisappear("dropDownOptions", ".q-menu");
            }

            selectProfile("Activity Analysis Report", MasterHooks.reportProfileID.get());
            waitTillWebElementIsVisible("createJobPopUpCalendarNameField", ".q-dialog .q-card #calendar-type");
            //Fiscal Year And Period
            if (!job.get(0).get("Calendar Type").equalsIgnoreCase("Fiscal Variant")) {
                sendingValueToWebElement("fromDateAA", fromDate, job.get(0).get("From Date"));
                sendingValueToWebElement("toDateAA", toDate, job.get(0).get("To Date"));
            } else {

                sendingValueToWebElement("fromYear", fromYear, job.get(0).get("From Year"));
                clickOnDropDownToTypeAndSelectValue("fromPostingPeriod", fromPeriod, job.get(0).get("From Posting Period"));
                sendingValueToWebElement("toYear", toYear, job.get(0).get("To Year"));
                clickOnDropDownToTypeAndSelectValue("toPostingPeriod", toPeriod, job.get(0).get("To Posting Period"));
            }
            //Classification
//            clickOnDropDownAndSelectValue("aaPrincipalPosition", principalPosition,
//                    job.get(0).get("Principal Position"));
            clickOnDropDownToTypeAndSelectValue("aaAccountingStandard", accountingStandard, job.get(0).get("Accounting Standard"));
            if (!job.get(0).get("Classification").equalsIgnoreCase("null")) {
                clickOnDropDownAndSelectCheckBoxes("aaClassification", classification, job.get(0).get("Classification"));
            }
            waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
            waitForWebElementToDisappear("dropDownOptions", ".q-menu");
            if (!job.get(0).get("Lease Type").equalsIgnoreCase("null")) {
                clickOnDropDownAndSelectValue("aaLeaseType", leaseTypeAA, job.get(0).get("Lease Type"));
            }
            if (!job.get(0).get("Partner").equalsIgnoreCase("null")) {
                clickOnDropDownToTypeAndSelectValue("aaPartner", partnerAA, job.get(0).get("Partner"));
            }
            if (!job.get(0).get("Asset Class").equalsIgnoreCase("null")) {
                clickOnDropDownToTypeAndSelectValue("aaAssetClass", assetClassAA, job.get(0).get("Asset Class"));
            }
            if (job.get(0).get("Report Type").equalsIgnoreCase("By Count")) {
                clickOnSubmitPopup("Submit / Add");
                waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
                waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
                log.info("Created Activity Analysis Job");
            } else if (job.get(0).get("Report Type").equalsIgnoreCase("By Amount,By Count") ||
                    job.get(0).get("Report Type").equalsIgnoreCase("By Amount")) {
                String values = "";
                HashMap<String, String> threadMap = MasterHooks.map.get();
                //Object List
                String ObjectList = job.get(0).get("Object List Type");
                if (!(ObjectList.equalsIgnoreCase("null"))) {
                    clickOnDropDownAndSelectValue("objectListType", objectListTypeAA, job.get(0).get("Object List Type"));
                    waitTillWebElementIsVisible("objectListFiled", objectListAAR);
                    if (job.get(0).get("Object List ID").equalsIgnoreCase("All")) {

                        switch (ObjectList) {
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
                                System.out.println("Unknown ObjectList type: " + ObjectList);
                        }
                        clickOnDropDownToTypeAndSelectCheckBox("objectListValue", objectListAAR, values);
                    } else {
                        String objectListValue = job.get(0).get("Object List ID");
                        values = getRecordId(objectListValue, threadMap, true);
                        clickOnDropDownToTypeAndSelectCheckBox("objectListValue", objectListAAR, values);
                    }
                }
            }
            if (jobType.equalsIgnoreCase("Scheduled Job")) {
                reportScheduling(reportLevel, job);
            }
            if (!job.get(0).get("Report Type").equalsIgnoreCase("By Count")) {
                if (driver.findElements(By.cssSelector("#submit-btn[aria-disabled='true']")).size() == 1) {
                    Assert.assertFalse(disableSubmit.isEnabled(), "Mandatory fields are missing");
                    log.info("Mandatory fields are missing");
                } else {
                    clickOnSubmitPopup("Submit / Add");
                    waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
                    waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
                    log.info("Created Activity Analysis Job");
                    waitTillWebElementIsVisible("ID", "#q-app .q-page .q-table tbody tr:nth-child(2) td.id div");
                    String profileID = driver.findElement(By.cssSelector("#q-app .q-page .q-table tbody tr:nth-child(2) td.id div")).getText();
                    System.out.println(jobType + " Profile ID: " + profileID);
                    MasterHooks.reportJobID.set(profileID);
                    //Waiting for page records to Increase
                    waitForRecordChange(recordBeforeJob, recordPerPage);
                    log.info("Job is created & completed for " + reportLevel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downloadAndValidateReport(String standard, String sheetName) {
        log.info("Downloading And Validating Report File");
        File baseValueExcelFile;
        String filePathString;

        if (sheetName.equalsIgnoreCase("DQI AG Report") || sheetName.equalsIgnoreCase("DQI Contract Report")) {
            baseValueExcelFile = new File("src/test/resources/validationExcelFiles/"
                    + MasterHooks.configurationProperties.get().getWhichBaselineToUseForValidation() + "/"
                    + MasterAgreement_PageObject.testCaseName.get() + "/"
                    + MasterAgreement_PageObject.testCaseName.get() + "_Output.xlsx");
        } else {
            baseValueExcelFile = new File("src/test/resources/validationExcelFiles/"
                    + MasterHooks.configurationProperties.get().getWhichBaselineToUseForValidation() + "/"
                    + MasterAgreement_PageObject.testCaseName.get() + "/"
                    + MasterAgreement_PageObject.testCaseName.get() + "_" + standard + "_Output.xlsx");
        }

        if (sheetName.equalsIgnoreCase("Cashflow Report")
                || sheetName.equalsIgnoreCase("Contract Expiration Report")
                || sheetName.equalsIgnoreCase("Income Statement Report")
                || sheetName.equalsIgnoreCase("Balance Sheet Report")
                || sheetName.equalsIgnoreCase("DQI AG Report")) {
            filePathString = downloadFinancialReports();
        } else {
            filePathString = downloadReport(sheetName);
        }

        File baseValueDownloadedExcelFile = new File(filePathString);
        boolean disclosureSheets = sheetName.equalsIgnoreCase("Asset Roll Forward") || sheetName.equalsIgnoreCase("Cash Flow") ||
                sheetName.equalsIgnoreCase("Expense") || sheetName.equalsIgnoreCase("Lease Liability") ||
                sheetName.equalsIgnoreCase("Maturity Analysis") || sheetName.equalsIgnoreCase("Non Lease Charge Expense") ||
                sheetName.equalsIgnoreCase("Weighted Avg Discount Rate") || sheetName.equalsIgnoreCase("Weighted Avg Lease Term");

        if (disclosureSheets) {
            sheetName = sheetName.replaceAll("\\s+", "");
        }
        if (sheetName.equalsIgnoreCase("MaturityAnalysis")) {
            sheetName = "MaturityAnalysisByCalYear";
        }
        if (sheetName.equalsIgnoreCase("NonLeaseChargeExpense")) {
            sheetName = "NonLeaseChargesExpense";
        }

        // Get skip rows and columns from Constant class
        Set<Integer> skipRows = new HashSet<>(Constant.getSkipRowsForDQIReports());
        Set<Integer> skipColumns = new HashSet<>(Constant.getSkipColumnsForDQIReports());

        // Reading Base Excel & Downloaded Excel file with skip logic
        Map<String, String[][]> ExcelValues = readReportValidationValues(sheetName, baseValueExcelFile, baseValueDownloadedExcelFile, skipRows, skipColumns);

        if (sheetName.equalsIgnoreCase("Periodic Posting Status Report")) {
            ExcelValues = sortingDownloadedFileValues(ExcelValues.get("excelDownloadedValidationValues"), ExcelValues.get("excelValidationValues"));
        }

        // Compare Base Excel with Downloaded Excel
        compareScheduleValues(ExcelValues.get("excelDownloadedValidationValues"), ExcelValues.get("excelValidationValues"), sheetName, standard);

        // Delete downloaded file if no validation failures
        if (Strings.isEmpty(MasterHooks.checkForValidationFailures.get())) {
            File folder = new File(MasterHooks.downloadedExcelFilePath.get());
            for (File file : folder.listFiles()) {
                file.delete();
                log.info("Excel file deleted successfully!!!");
            }
        }
    }


    public String downloadReport(String reportName) {
        log.info("Downloading the " + reportName);
        //Checking Files in folder before download
        File folder = new File(MasterHooks.downloadedExcelFilePath.get());
        File[] listOfFilesBeforeDown = folder.listFiles();
        assert listOfFilesBeforeDown != null;
        int sizeOfFileBeforeDown = listOfFilesBeforeDown.length;
        System.out.println("The list of files before Excel download are " + sizeOfFileBeforeDown);

        if (reportName.contains("Activity Analysis") || reportName.contains("Periodic Posting")) {
            try {
                waitAndClickOnElement(reportDownloadButtonAA);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            int reportSize = driver.findElements(By.cssSelector(".q-page .tasks-grid tbody .q-tr")).size();
            reportName = reportName + " Report";
            for (int report = 1; report <= reportSize; report++) {
                String reportTask = driver.findElement(By.cssSelector(".q-page .tasks-grid tbody tr:nth-child(" + report + ") .q-td:nth-child(2) div")).getText();
                if (reportName.equalsIgnoreCase(reportTask)) {
                    try {
                        waitAndClickOnElement("downloadingButton", ".q-page .tasks-grid tbody tr:nth-child(" + report + ") .q-btn#generate-btn");
                        break;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        //Waiting for file download loader
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofMillis(300)).ignoring(WebDriverException.class);
        wait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.findElements(By.cssSelector("#q-app .q-table .q-spinner")).size() == 0;
            }
        });
        try {
            if (MasterHooks.configurationProperties.get().getRunEnvironment().equalsIgnoreCase("remote")) {
                download_file_selenium_grid(folder);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Waiting for file to download
        Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(300)).pollingEvery(Duration.ofMillis(2000)).ignoring(WebDriverException.class);
        wait1.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                File[] listOfFilesAfterDown = folder.listFiles();
                assert listOfFilesAfterDown != null;
                int sizeAfterDown = listOfFilesAfterDown.length;
                return sizeAfterDown != sizeOfFileBeforeDown;
            }
        });
        handleWait(3000);

        File[] downloadedFile = folder.listFiles();
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
        assert lastDownloadedFile != null;
        return lastDownloadedFile.getAbsolutePath();
    }

    public Map<String, String[][]> readReportValidationValues(
            String sheetName,
            File baseValueExcelFile,
            File baseValueDownloadedExcelFile,
            Set<Integer> skipRows,
            Set<Integer> skipColumns) {
        System.out.println(" *** Validating the " + sheetName + " Report ***");
        try {
            int totalNumberOfRows;
            int totalNumberOfColumns;
            Map<String, String[][]> result = new HashMap<>();
            String[][] excelValidationValues;
            String[][] excelDownloadedValidationValues;
            FileInputStream file = new FileInputStream(baseValueExcelFile);
            XSSFWorkbook myWorkbook = new XSSFWorkbook(file);
            FileInputStream file1 = new FileInputStream(baseValueDownloadedExcelFile);
            XSSFWorkbook myWorkbook1 = new XSSFWorkbook(file1);

            XSSFSheet mySheet = myWorkbook.getSheet(sheetName);
            XSSFSheet mySheet1 = myWorkbook1.getSheet(sheetName);
            totalNumberOfRows = mySheet.getLastRowNum();
            int rowNum = 0;
            int colNum = 0;
            switch (sheetName) {
                case "Activity Analysis By Amount":
                    rowNum = Constant.getAARowNumber();
                    colNum = Constant.getAAColNumber();
                    break;
                case "Periodic Posting Status Report":
                    rowNum = Constant.getPPSRRowNumber();
                    colNum = Constant.getPPSRColNumber();
                    break;
                case "Cashflow Report":
                    rowNum = Constant.getCFRowNumber();
                    colNum = Constant.getCFColNumber();
                    break;
                case "Contract Expiration Report":
                    rowNum = Constant.getCERowNumber();
                    colNum = Constant.getCEColNumber();
                    break;
                case "Income Statement Report":
                    rowNum = Constant.getISRowNumber();
                    colNum = Constant.getISColNumber();
                    break;
                case "Balance Sheet Report":
                    rowNum = Constant.getBSRowNumber();
                    colNum = Constant.getBSColNumber();
                    break;
                case "DQI AG Report":
                    rowNum = Constant.getDQIAGRowNumber();
                    colNum = Constant.getDQIAGColNumber();
                    break;
                case "AssetRollForward":
                case "CashFlow":
                case "Expense":
                case "LeaseLiability":
                case "MaturityAnalysisByCalYear":
                case "NonLeaseChargesExpense":
                case "WeightedAvgDiscountRate":
                case "WeightedAvgLeaseTerm":
                    rowNum = Constant.getDRRowNumber();
                    colNum = Constant.getDRColNumber();
                    break;
            }
            totalNumberOfColumns = mySheet.getRow(rowNum).getLastCellNum();
            excelValidationValues = new String[totalNumberOfRows - (rowNum - 1)][totalNumberOfColumns - colNum];
            excelDownloadedValidationValues = new String[totalNumberOfRows - (rowNum - 1)][totalNumberOfColumns - colNum];

            if (!sheetName.equalsIgnoreCase("DQI AG Report")) {
                for (int numberOfRowCount = rowNum; numberOfRowCount <= totalNumberOfRows; numberOfRowCount++) {
                    Row row = mySheet.getRow(numberOfRowCount);
                    Row row1 = mySheet1.getRow(numberOfRowCount);
                    for (int numberOfColumnCount = colNum; numberOfColumnCount < totalNumberOfColumns; numberOfColumnCount++) {
                        DataFormatter df = new DataFormatter();
                        Cell cell = row.getCell(numberOfColumnCount);
                        Cell cell1 = row1.getCell(numberOfColumnCount);
//					String value = cell.getStringCellValue();
                        String value = df.formatCellValue(cell);
                        String value1 = df.formatCellValue(cell1);
                        excelValidationValues[numberOfRowCount - rowNum][numberOfColumnCount - colNum] = value;
                        excelDownloadedValidationValues[numberOfRowCount - rowNum][numberOfColumnCount - colNum] = value1;
                    }

                }
            } else {
                // Determine valid row count after skipping
                int validRows = 0;
                for (int i = rowNum; i <= totalNumberOfRows; i++) {
                    if (!skipRows.contains(i)) validRows++;
                }

                // Determine valid column count after skipping
                int validColumns = 0;
                for (int j = colNum; j < totalNumberOfColumns; j++) {
                    if (!skipColumns.contains(j)) validColumns++;
                }

                excelValidationValues = new String[validRows][validColumns];
                excelDownloadedValidationValues = new String[validRows][validColumns];

                int rowIndex = 0;
                for (int numberOfRowCount = rowNum; numberOfRowCount <= totalNumberOfRows; numberOfRowCount++) {
                    if (skipRows.contains(numberOfRowCount)) continue;

                    Row row = mySheet.getRow(numberOfRowCount);
                    Row row1 = mySheet1.getRow(numberOfRowCount);

                    int colIndex = 0;
                    for (int numberOfColumnCount = colNum; numberOfColumnCount < totalNumberOfColumns; numberOfColumnCount++) {
                        if (skipColumns.contains(numberOfColumnCount)) continue;

                        DataFormatter df = new DataFormatter();
                        Cell cell = (row != null) ? row.getCell(numberOfColumnCount) : null;
                        Cell cell1 = (row1 != null) ? row1.getCell(numberOfColumnCount) : null;

                        String value = (cell != null) ? df.formatCellValue(cell) : "";
                        String value1 = (cell1 != null) ? df.formatCellValue(cell1) : "";

                        excelValidationValues[rowIndex][colIndex] = value;
                        excelDownloadedValidationValues[rowIndex][colIndex] = value1;

                        colIndex++;
                    }
                    rowIndex++;
                }
            }


            file.close();
            file1.close();
            result.put("excelValidationValues", excelValidationValues);
            result.put("excelDownloadedValidationValues", excelDownloadedValidationValues);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String cleanSpaces(String value) {
        if (value == null) return null;
        return value
                .replaceAll("[\\u200B\\uFEFF\\u00A0]", "") // remove invisible chars
                .trim(); // remove normal spaces
    }

    public void compareScheduleValues(String[][] downloadedValues, String[][] baseValues, String sheetName, String standard) {
        double actualDifferenceValue = 0.0;

        for (int headingCount = 0; headingCount < baseValues[0].length; headingCount++) {
            for (int rowCount = 1; rowCount < baseValues.length; rowCount++) {
                String baseValue = cleanSpaces(baseValues[rowCount][headingCount]);
                String downloadedValue = cleanSpaces(downloadedValues[rowCount][headingCount]);
                if (baseValue.equals(downloadedValue)) {
                    System.out.println("Values are matching : " + true
                            + ", Expected Result is: " + baseValue + " and Actual Result is : " + downloadedValue);
                } else {
                    if (baseValue.contains(".") || downloadedValue.contains(".")) {

                        if (baseValue.contains("%") || downloadedValue.contains("%")) {
                            downloadedValue = downloadedValue.replace("%", "");
                            baseValue = baseValue.replace("%", "");
                        }
                        if (baseValue.contains("(") || downloadedValue.contains("(")) {
                            downloadedValue = downloadedValue.replace("(", "-").replace(")", "");
                            baseValue = baseValue.replace("(", "-").replace(")", "");
                        }

                        actualDifferenceValue = Math.abs(Double.parseDouble(cleanSpaces(downloadedValue.replaceAll(",", "")))
                                - Double.parseDouble(cleanSpaces(baseValue.replaceAll(",", ""))));
                        if (MasterHooks.configurationProperties.get().getAllowedDifferenceToSkip() >= actualDifferenceValue) {
                            System.out.printf(
                                    "Values differ within allowed range → Base: %s | Downloaded: %s | Allowed Δ: %s | Actual Δ: %.6f | Equal? %s%n",
                                    baseValue, downloadedValue, MasterHooks.configurationProperties.get().getAllowedDifferenceToSkip(), actualDifferenceValue, baseValue.equals(downloadedValue)
                            );
                        } else {
                            System.out.printf(
                                    "Values not matching → Base: %s | Downloaded: %s | Equal: %s | Allowed Δ: %s | Actual Δ: %.6f%n",
                                    baseValue, downloadedValue, baseValue.equals(downloadedValue), MasterHooks.configurationProperties.get().getAllowedDifferenceToSkip(), actualDifferenceValue
                            );
                            int rowNumber = 0;
                            int columnNumber = 0;
                            switch (sheetName) {
                                case "Activity Analysis By Amount":
                                    rowNumber = rowCount + Constant.getAARowNumber() + 1;
                                    columnNumber = headingCount + Constant.getAAColNumber() + 1;
                                    break;
                                case "Periodic Posting Status Report":
                                    rowNumber = rowCount + Constant.getPPSRRowNumber() + 1;
                                    columnNumber = headingCount + Constant.getPPSRColNumber() + 1;
                                    break;
                                case "Cashflow Report":
                                    rowNumber = rowCount + Constant.getCFRowNumber() + 1;
                                    columnNumber = headingCount + Constant.getCFColNumber() + 1;
                                    break;
                                case "Income Statement Report":
                                    rowNumber = rowCount + Constant.getISRowNumber() + 1;
                                    columnNumber = headingCount + Constant.getISColNumber() + 1;
                                    break;
                                case "Balance Sheet Report":
                                    rowNumber = rowCount + Constant.getBSRowNumber() + 1;
                                    columnNumber = headingCount + Constant.getBSColNumber() + 1;
                                    break;
                                case "Contract Expiration Report":
                                    rowNumber = rowCount + Constant.getCERowNumber() + 1;
                                    columnNumber = headingCount + Constant.getCEColNumber() + 1;
                                    break;
                                case "DQI AG Report":
                                    rowNumber = rowCount + Constant.getDQIAGRowNumber() + 1;
                                    columnNumber = headingCount + Constant.getDQIAGColNumber() + 1;
                                    break;
                                case "AssetRollForward":
                                case "CashFlow":
                                case "Expense":
                                case "LeaseLiability":
                                case "MaturityAnalysisByCalYear":
                                case "NonLeaseChargesExpense":
                                case "WeightedAvgDiscountRate":
                                case "WeightedAvgLeaseTerm":
                                    rowNumber = rowCount + Constant.getDRRowNumber() + 1;
                                    columnNumber = headingCount + Constant.getDRColNumber() + 1;
                                    break;
                            }

                            try {
                                writeMisMatchExcelValues(sheetName, baseValues[0][headingCount] + " " + rowNumber + ":" + columnNumber, baseValues[rowCount][headingCount], downloadedValues[rowCount][headingCount],
                                        MasterHooks.configurationProperties.get().getAllowedDifferenceToSkip(), actualDifferenceValue, standard);

                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    } else {
                        System.out.printf("Values not matching → Base: %s | Downloaded: %s%n", baseValue, downloadedValue);

                        int rowNumber = 0;
                        int columnNumber = 0;
                        switch (sheetName) {
                            case "Activity Analysis By Amount":
                                rowNumber = rowCount + Constant.getAARowNumber() + 1;
                                columnNumber = headingCount + Constant.getAAColNumber() + 1;
                                break;
                            case "Periodic Posting Status Report":
                                rowNumber = rowCount + Constant.getPPSRRowNumber() + 1;
                                columnNumber = headingCount + Constant.getPPSRColNumber() + 1;
                                break;
                            case "Cashflow Report":
                                rowNumber = rowCount + Constant.getCFRowNumber() + 1;
                                columnNumber = headingCount + Constant.getCFColNumber() + 1;
                                break;
                            case "Income Statement Report":
                                rowNumber = rowCount + Constant.getISRowNumber() + 1;
                                columnNumber = headingCount + Constant.getISColNumber() + 1;
                                break;
                            case "Balance Sheet Report":
                                rowNumber = rowCount + Constant.getBSRowNumber() + 1;
                                columnNumber = headingCount + Constant.getBSColNumber() + 1;
                                break;
                            case "Contract Expiration Report":
                                rowNumber = rowCount + Constant.getCERowNumber() + 1;
                                columnNumber = headingCount + Constant.getCEColNumber() + 1;
                                break;
                            case "DQI AG Report":
                                rowNumber = rowCount + Constant.getDQIAGRowNumber() + 1;
                                columnNumber = headingCount + Constant.getDQIAGColNumber() + 1;
                                break;
                        }

                        try {
                            writeMisMatchExcelValues(sheetName, baseValues[0][headingCount] + " " + rowNumber + ":" + columnNumber, baseValues[rowCount][headingCount], downloadedValues[rowCount][headingCount],
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
        copyMisMatchExcelTemplateFile(standard, sheetName);
        MasterHooks.checkForValidationFailures.set("Failed");
        FileInputStream file = new FileInputStream(misMatchExcelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        int sheetIndex = workbook.getSheetIndex(sheetName);
        XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
        XSSFRow row;

        int rowNumber;
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
            XSSFWorkbook excelWorkbook;
            File validationFolder = new File("Automation-Results/validationResults/" + MasterHooks.configurationProperties.get().getWhichBaselineToUseForValidation() + "/"
                    + MasterAgreement_PageObject.testCaseName.get() + "/");
            if (standard.equalsIgnoreCase("")) {
                misMatchExcelFile = new File("Automation-Results/validationResults/" + MasterHooks.configurationProperties.get().getWhichBaselineToUseForValidation() + "/"
                        + MasterAgreement_PageObject.testCaseName.get() + "/"
                        + MasterAgreement_PageObject.testCaseName.get() + "_Output.xlsx");
            } else {
                misMatchExcelFile = new File("Automation-Results/validationResults/" + MasterHooks.configurationProperties.get().getWhichBaselineToUseForValidation() + "/"
                        + MasterAgreement_PageObject.testCaseName.get() + "/"
                        + MasterAgreement_PageObject.testCaseName.get() + "_" + standard + "_Output.xlsx");
            }
            if (!validationFolder.exists()) {
                validationFolder.mkdirs();
            }
            if (misMatchExcelFile.exists()) {
                FileInputStream file = new FileInputStream(misMatchExcelFile);
                excelWorkbook = (XSSFWorkbook) WorkbookFactory.create(file);
            } else {
                excelWorkbook = new XSSFWorkbook();
            }

            int num = excelWorkbook.getSheetIndex(sheetName);
            if (num == -1) {
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

    public void createPeriodicPostingStatusReportJob(String jobType, String reportLevel, DataTable dataTable) {
        log.info("Creating Periodic Posting Status Report Jobs");
        List<Map<String, String>> job = dataTable.asMaps(String.class, String.class);
        try {
            handleWait(2000);
            if (!jobType.equalsIgnoreCase("Scheduled Job")) {
                applyCreatedByFilter("Periodic Posting");
            }
            int recordBeforeJob;
            if (waitUnTillWebElementIsVisible("PPSRRecords", recordPerPage)) {
                String records = driver.findElement(By.cssSelector(recordPerPage)).getText();
                recordBeforeJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
            } else {
                recordBeforeJob = 0;
            }
            if (!jobType.equalsIgnoreCase("Scheduled Job")) {
                waitTillWebElementIsVisible("addAAJobButton", addJobButtonAA);
                waitAndClickOnElement(addJobButtonAA);
            } else {
                waitTillWebElementIsVisible("addSchelueAAJobButton", addScheduleJobButtonAA);
                waitAndClickOnElement(addScheduleJobButtonAA);
            }

            waitTillWebElementIsVisible("createJobPopUp", ".q-dialog  .qcard-dialogue");
            if (jobType.equalsIgnoreCase("Scheduled Job")) {
                waitAndClickOnElement(reportNameScheduleJob);
                sendingValueToWebElement("reportName", reportNameScheduleJob, job.get(0).get("Name"));
            }
            clickOnDropDownAndSelectCheckBoxes("postingType", postingType, job.get(0).get("Posting Type"));
            waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
            waitForWebElementToDisappear("dropDownOptions", ".q-menu");
            if (!job.get(0).get("Internal Status Filter").contains("All")) {
                clickOnDropDownAndSelectValue("internalStatusFilter", internalStatusFilter, "List");
                clickOnDropDownAndSelectCheckBoxes("internalStatus", internalStatus, job.get(0).get("Internal Status"));
                waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                waitForWebElementToDisappear("dropDownOptions", ".q-menu");
                if (job.get(0).get("Internal Status").contains("Done")) {
                    if (!job.get(0).get("External Status Filter").contains("All")) {
                        clickOnDropDownAndSelectValue("externalStatusFilter", externalStatusFilter, "List");
                        clickOnDropDownAndSelectCheckBoxes("externalStatus", externalStatus, job.get(0).get("External Status"));
                        waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                        waitForWebElementToDisappear("dropDownOptions", ".q-menu");
                    }
                }
            }
            // Select PPSR Profile
//            WaitUntilElementIsClickable(selectProfile);
//            waitAndClickOnElement(selectProfile);
//            waitTillWebElementIsVisible("createJobPopUpNameField", ".q-dialog  .qcard-dialogue [role='radio']");
//            waitAndClickOnElement("selectProfile", ".q-dialog  .q-card .q-radio[id='" + MasterHooks.PPSRProfileID.get() + "-profile-radio-btn']");
//            waitAndClickOnElement(selectProfileSubmitBtn);
            selectProfile("Periodic Posting Report", MasterHooks.reportProfileID.get());
            waitTillWebElementIsVisible("createJobPopUpCalendarNameField", ".q-dialog .q-card #calendar-type");
            //Fiscal Year And Period
            if (!job.get(0).get("Calendar Type").equalsIgnoreCase("Fiscal Variant")) {

                sendingValueToWebElement("fromDateAA", fromDate, job.get(0).get("From Date"));
                sendingValueToWebElement("toDateAA", toDate, job.get(0).get("To Date"));
            } else {

                sendingValueToWebElement("fromYear", fromYear, job.get(0).get("From Year"));
                waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                clickOnDropDownAndSelectValue("fromPostingPeriod", fromPeriod, job.get(0).get("From Posting Period"));
                sendingValueToWebElement("toYear", toYear, job.get(0).get("To Year"));
                waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                clickOnDropDownAndSelectValue("toPostingPeriod", toPeriod, job.get(0).get("To Posting Period"));
            }
            //Classification
//            clickOnDropDownAndSelectValue("ppsrPrincipalPosition", principalPosition, job.get(0).get("Principal Position");
            clickOnDropDownToTypeAndSelectValue("ppsrAccountingStandard", accountingStandard, job.get(0).get("Accounting Standard"));
            clickOnDropDownAndSelectCheckBoxes("ppsrClassification", classification, job.get(0).get("Classification"));
            waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
            waitForWebElementToDisappear("dropDownOptions", ".q-menu");
            String values = "";
            HashMap<String, String> threadMap = MasterHooks.map.get();
            //Object List
            String ObjectList = job.get(0).get("Object List Type");
            if (!(ObjectList.equalsIgnoreCase("null"))) {
                clickOnDropDownAndSelectValue("objectListType", objectListTypeAA, job.get(0).get("Object List Type"));
                waitTillWebElementIsVisible("objectListFiled", objectListPPSR);
                if (job.get(0).get("Object List ID").equalsIgnoreCase("All")) {

                    switch (ObjectList) {
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
                            System.out.println("Unknown ObjectList type: " + ObjectList);
                    }

                    clickOnDropDownToTypeAndSelectCheckBox("objectListValue", objectListPPSR, values);
                } else {
                    String objectListValue = job.get(0).get("Object List ID");
                    values = getRecordId(objectListValue, threadMap, true);
                    clickOnDropDownToTypeAndSelectCheckBox("objectListValue", objectListPPSR, values);
                }
            }
            if (jobType.equalsIgnoreCase("Scheduled Job")) {
                reportScheduling(reportLevel, job);
            }
            if (driver.findElements(By.cssSelector("#submit-btn[aria-disabled='true']")).size() == 1) {
                Assert.assertFalse(disableSubmit.isEnabled(), "Mandatory fields are missing");
                log.info("Mandatory fields are missing");
            } else {
                clickOnSubmitPopup("Submit / Add");
                waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
                waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
                log.info("Created Periodic Posting Status Job");
                waitTillWebElementIsVisible("ID", "#q-app .q-page .q-table tbody tr:nth-child(2) td.id div");
                String profileID = driver.findElement(By.cssSelector("#q-app .q-page .q-table tbody tr:nth-child(2) td.id div")).getText();
                System.out.println(jobType + " Profile ID: " + profileID);
                MasterHooks.reportJobID.set(profileID);
                //Waiting for page records to Increase
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
                wait.until(new Function<WebDriver, Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        String records = driver.findElement(By.cssSelector(recordPerPage)).getText();
                        int recordAfterProfile = Integer.parseInt(records.substring(records.indexOf("f") + 2));
                        return recordAfterProfile != recordBeforeJob;
                    }
                });
            }
            log.info("Job is created & completed for " + reportLevel);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, String[][]> sortingDownloadedFileValues(String[][] excelDownloadedValidationValues, String[][]
            excelValidationValues) {
        log.info("Applying Sorting");
        Map<String, String[][]> result = new HashMap<>();

        Comparator<String[]> dateComparator = new Comparator<String[]>() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            @Override
            public int compare(String[] row1, String[] row2) {
                try {
                    Date date1 = dateFormat.parse(row1[2]);
                    Date date2 = dateFormat.parse(row2[2]);
                    return date1.compareTo(date2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        };

        // Sort the 2D array based on column 3 (date)
        Arrays.sort(excelDownloadedValidationValues, 1, excelDownloadedValidationValues.length, dateComparator);
        Arrays.sort(excelValidationValues, 1, excelValidationValues.length, dateComparator);
        log.info("\n📘 Downloaded Report (Sorted):");
        printTable(excelDownloadedValidationValues);

        log.info("\n📗 Base Excel (Sorted):");
        printTable(excelValidationValues);
        result.put("excelValidationValues", excelValidationValues);
        result.put("excelDownloadedValidationValues", excelDownloadedValidationValues);
        return result;
    }

    private void printTable(String[][] data) {
        if (data == null || data.length == 0) return;

        int cols = data[0].length;
        int[] colWidths = new int[cols];
        int maxColWidth = 30; // truncate after 30 characters

        // Find the max width per column (up to maxColWidth)
        for (String[] row : data) {
            for (int i = 0; i < cols; i++) {
                if (row[i] != null) {
                    int len = Math.min(row[i].length(), maxColWidth);
                    colWidths[i] = Math.min(Math.max(colWidths[i], len), maxColWidth);
                }
            }
        }

        // Print each row with aligned columns
        for (String[] row : data) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < cols; i++) {
                String cell = row[i] == null ? "" : row[i];
                if (cell.length() > maxColWidth) {
                    cell = cell.substring(0, maxColWidth - 3) + "..."; // add ellipsis
                }
                sb.append(String.format("%-" + (colWidths[i] + 2) + "s", cell));
            }
            log.info(sb.toString().trim());
        }
    }

    public void reportScheduling(String reportLevel,List<Map<String, String>> job) {
        try {
            String cssSelectorToClick, daily, weekly, monthly, yearly, sundayCheckbox, januaryCheckbox;
            if (reportLevel.contains("Consolidated Transaction") || reportLevel.contains("GL Balance") || reportLevel.contains("SAP Posting")) {
                cssSelectorToClick = ".q-card .dialog-body .q-tabs [v-css-selectors='hourly-tab']";
                daily = ".q-card .dialog-body .q-tabs [v-css-selectors='daily-tab']";
                weekly = ".q-card .dialog-body .q-tabs [v-css-selectors='weekly-tab']";
                monthly = ".q-card .dialog-body .q-tabs [v-css-selectors='monthly-tab']";
                yearly = ".q-card .dialog-body .q-tabs [v-css-selectors='yearly-tab']";
                sundayCheckbox = ".q-card .dialog-body .q-panel [v-css-selectors='sunday-checkbox']";
                januaryCheckbox = ".q-card .dialog-body .q-panel [v-css-selectors='jan-checkbox']";

            } else {
                cssSelectorToClick = ".q-card .dialog-body .q-tabs #hourly-tab";
                daily = ".q-card .dialog-body .q-tabs #daily-tab";
                weekly = ".q-card .dialog-body .q-tabs #weekly-tab";
                monthly = ".q-card .dialog-body .q-tabs #monthly-tab";
                yearly = ".q-card .dialog-body .q-tabs #yearly-tab";
                sundayCheckbox = ".q-card .dialog-body .q-panel #sunday-checkbox";
                januaryCheckbox = ".q-card .dialog-body .q-panel #jan-checkbox";
            }
            //Hourly
            if (job.get(0).get("Hourly").equalsIgnoreCase("Yes")) {
                waitTillWebElementIsVisible("hourlyScheduleOption", cssSelectorToClick);
                waitAndClickOnElement("hourlyScheduleOption", cssSelectorToClick);
                clickOnDropDownAndSelectValueFromPages("hourlyHours", hourlyHours,job.get(0).get("Hour"));
            } //Daily
            else if (job.get(0).get("Daily").equalsIgnoreCase("Yes")) {
                waitAndClickOnElement("dailyScheduleOption", daily);
                clickOnDropDownAndSelectValueFromPages("dailyDay", dailyDay,job.get(0).get("Date"));
                clickOnDropDownAndSelectValueFromPages("dailyHour", hour, job.get(0).get("Hour"));
            } //Weekly
            else if (job.get(0).get("Weekly").equalsIgnoreCase("Yes")) {
                waitAndClickOnElement("weeklyScheduleOption", weekly);
                waitAndClickOnElement("sundayCheckbox", sundayCheckbox);

                String[] fieldValuesLength =job.get(0).get("Week Days").split(",");
                for (String value : fieldValuesLength) {
                    for (int days = 2; days <= 8; days++) {
                        String dayName = driver.findElement(By.cssSelector(".q-card .dialog-body .q-panel .q-checkbox:nth-child(" + days + ")")).getText();
                        if (value.equalsIgnoreCase(dayName)) {
                            waitAndClickOnElement("weekDayCheckbox", ".q-card .dialog-body .q-panel .q-checkbox:nth-child(" + days + ")");
                        }
                    }
                }
                clickOnDropDownAndSelectValueFromPages("weeklyHour", hour, job.get(0).get("Hour"));
            } //Monthly
            else if (job.get(0).get("Monthly").equalsIgnoreCase("Yes")) {
                waitAndClickOnElement("weeklyScheduleOption", monthly);
                clickOnDropDownAndSelectValueFromPages("monthlyMonth", monthlyMonth, job.get(0).get("Month"));
                clickOnDropDownAndSelectValueFromPages("monthlyDate", date,job.get(0).get( "Date"));
                clickOnDropDownAndSelectValueFromPages("monthlyHour", hour, job.get(0).get( "Hour"));
            } //Yearly
            else if (job.get(0).get("Yearly").equalsIgnoreCase("Yes")) {
                waitAndClickOnElement("yearlyScheduleOption", yearly);
                waitAndClickOnElement("januaryCheckbox", januaryCheckbox);

                String[] fieldValuesLength = job.get(0).get( "Month").split(",");
                for (String value : fieldValuesLength) {
                    for (int months = 2; months <= 13; months++) {
                        String dayName = driver.findElement(By.cssSelector(".q-card .dialog-body .q-panel .q-checkbox:nth-child(" + months + ")")).getText();
                        if (value.equalsIgnoreCase(dayName)) {
                            waitAndClickOnElement("monthCheckbox", ".q-card .dialog-body .q-panel .q-checkbox:nth-child(" + months + ")");
                        }
                    }
                }
                clickOnDropDownAndSelectValueFromPages("monthlyDate", date,job.get(0).get( "Date"));
                clickOnDropDownAndSelectValueFromPages("monthlyHour", hour, job.get(0).get( "Hour"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void applyCreatedByFilter(String reportName) {
        log.info("Applying Created By filter in Jobs section");
        int recordBeforeJob;
        String cssSelector;
        boolean flag = false;
        if (reportName.equalsIgnoreCase("Disclosure")) {
            if (waitUnTillWebElementIsVisible("DRTaskGrid", "#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")) {
                String records = driver.findElement(By.cssSelector("#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
                recordBeforeJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
            } else {
                recordBeforeJob = 0;
            }
            if (!(driver.findElements(By.cssSelector("#q-app .jobs-grid #search-created-by .q-icon")).size() == 1)) {
                try {
                    waitAndClickOnElement(userBtn);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                waitTillWebElementIsVisible("userEmail", userEmail);
                createdByFilter.click();
                sendingValueToWebElement("createdByFilter", createdByFilter, userEmail.getText());
                flag = true;
            }
            cssSelector = "#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item";
        } else {
            if (waitUnTillWebElementIsVisible("pageRecord", recordPerPage)) {
                String records = driver.findElement(By.cssSelector(recordPerPage)).getText();
                recordBeforeJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
            } else {
                recordBeforeJob = 0;
            }
            if (!(driver.findElements(By.cssSelector("#q-app td:nth-child(2) button.q-icon")).size() == 1)) {
                try {
                    waitAndClickOnElement(userBtn);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                waitTillWebElementIsVisible("userEmail", userEmail);
                driver.findElement(By.cssSelector("#q-app td:nth-child(2) #search-and-filter-input ")).click();
                sendingValueToWebElement("createdByFilter", driver.findElement(By.cssSelector("#q-app td:nth-child(2) #search-and-filter-input ")),
                        userEmail.getText());
                flag = true;
            }
            cssSelector = recordPerPage;
        }
        if (recordBeforeJob != 0 && flag) {
            handleWait(2000);
            if (waitUnTillWebElementIsVisible("pageRecord", cssSelector)) {
                String records = getValueFromElement(cssSelector);
                int recordAfter = Integer.parseInt(records.substring(records.indexOf("f") + 2));
                Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
                if (recordBeforeJob == recordAfter) {
                    wait.until(driver -> {
                        handleWait(5000);
                        String updatedRecords = getValueFromElement(cssSelector);
                        int updatedCount = Integer.parseInt(updatedRecords.substring(updatedRecords.indexOf("f") + 2).trim());
                        return updatedCount != recordBeforeJob;
                    });
                } else {
                    wait.until(driver -> {
                        String updatedRecords = getValueFromElement(cssSelector);
                        int updatedCount = Integer.parseInt(updatedRecords.substring(updatedRecords.indexOf("f") + 2).trim());
                        return updatedCount != recordBeforeJob;
                    });
                }
            }
        }
        log.info("Applied Created By filter in Jobs section");
    }

    public void deleteScheduledJob(String reportType) {
        log.info("User is going to delete the "+reportType+" Scheduled job");
        try {
            switch (reportType) {
                case "Disclosure Report":
                    waitTillWebElementIsVisible("searchProfileField", searchScheduleJobIDDRReports);
                    sendingValueToWebElement("profileID", searchScheduleJobIDDRReports, MasterHooks.reportJobID.get());
                    break;
                case "Periodic Posting Status Report":
                case "Activity Analysis Report":
                    waitTillWebElementIsVisible("searchProfileField", searchScheduleJobIDReports);
                    sendingValueToWebElement("profileID", searchScheduleJobIDReports, MasterHooks.reportJobID.get());
                    break;
                case "Consolidated Transaction Report":
                case "GL Balance Report":
                    waitTillWebElementIsVisible("searchProfileField", searchProfileIDFieldSAPReports);
                    sendingValueToWebElement("profileID", searchProfileIDFieldSAPReports, MasterHooks.sapProfileId.get());
                    break;
            }

            waitTillWebElementIsVisible("scheduledJobDeletion", scheduledJobDeletion);
            waitAndClickOnElement(scheduledJobDeletion);
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            handleWait(500);
            String alertMessage = driver.findElement(By.cssSelector(".desktop .q-notifications .q-notification[role='alert'] .q-notification__message")).getText();
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
            if (!alertMessage.contains("successfully")) {
                Assert.fail(reportType +" Schedule Job has not been deleted successfully");
            }
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
            wait.until(new Function<WebDriver, Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    try {
                        waitAndClickOnElement(jobRefreshButton);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return driver.findElements(By.cssSelector("#q-app tbody tr:nth-child(2)")).isEmpty();
                }
            });
            log.info(reportType +" Schedule Job has not been deleted successfully");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void enableOrDisableScheduledJob(String operation,String reportType) {
        log.info("Enabling or Disabling the  " + reportType + " Report Schedule Job");
        try {
            waitTillWebElementIsVisible("records", recordPerPage);
            int recordBeforeJob = getRecordCountBefore(reportType, recordPerPage);
            switch (reportType) {
                case "Disclosure Report":
                    waitTillWebElementIsVisible("searchProfileField", searchScheduleJobIDDRReports);
                    sendingValueToWebElement("profileID", searchScheduleJobIDDRReports, MasterHooks.reportJobID.get());
                    break;
                case "Periodic Posting Status Report":
                case "Activity Analysis Report":
                    waitTillWebElementIsVisible("searchProfileField", searchScheduleJobIDReports);
                    sendingValueToWebElement("profileID", searchScheduleJobIDReports, MasterHooks.reportJobID.get());
                    break;
                case "Consolidated Transaction Report":
                case "GL Balance Report":
                    waitTillWebElementIsVisible("searchProfileField", searchProfileIDFieldSAPReports);
                    sendingValueToWebElement("profileID", searchProfileIDFieldSAPReports, MasterHooks.sapProfileId.get());
                    break;
            }

            if(operation.equalsIgnoreCase("Enable")){
                waitTillWebElementIsVisible("enableScheduleJobRadioBtn", enableScheduleJobRadioBtn);
                waitAndClickOnElement(enableScheduleJobRadioBtn);
            } else if (operation.equalsIgnoreCase("Disable")) {
                waitTillWebElementIsVisible("disableScheduleJob", disableScheduleJobRadioBtn);
                waitAndClickOnElement(disableScheduleJobRadioBtn);
            }
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            handleWait(500);
            String alertMessage = driver.findElement(By.cssSelector(".desktop .q-notifications .q-notification[role='alert'] .q-notification__message")).getText();
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
            if (!alertMessage.contains("successfully")) {
               Assert.fail("Schedule Job has not been "+operation+" successfully");
            }
            log.info("Schedule Job has been "+operation+" successfully");
            waitAndClickOnElement(cancelSearchJob);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void cancelJobForReport() {
        try {
            String search_ID = driver.findElement(By.cssSelector(".q-page .q-table .q-tr:nth-child(2) .q-td:nth-child(1) div")).getText();
            waitAndClickOnElement(searchScheduledJobFieldID);
            searchScheduledJobFieldID.sendKeys(search_ID);
            handleWait(1000);
            waitAndClickOnElement(cancelJob);
            handleWait(1000);
            if (driver.findElement(By.cssSelector("#q-app tbody tr:nth-child(2) [role='status']")).getText().equalsIgnoreCase("Cancelled")) {
                log.info("Job is Cancelled");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void editSchJobNFS() {
        try {
            waitAndClickOnElement(clickEditSchJob);
            waitTillWebElementIsVisible("editSchJob", ".q-dialog  .qcard-dialogue");
            waitAndClickOnElement(yearlyTab);
            waitAndClickOnElement(monthBox1);
            waitAndClickOnElement(monthBox2);
            clickOnSubmitPopup("Submit / Add");
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
            log.info("Schedule job edited Successfully");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void statusOfDisclosureReport(String jobType, String reportLevel) {
        log.info("User is checking the status of job");
        if (!jobType.equalsIgnoreCase("Scheduled Job")) {
            int jobsSize = driver.findElements(By.cssSelector("#q-app .tasks-grid tbody tr")).size();
            for (int index = 1; index <= jobsSize; index++) {
                checkJobCompletion("Done", index);
            }
        }
        log.info("Job is created & completed for " + reportLevel);

    }

    public void downloadAndValidateFinancialReport(String sheetName, String standard, String classification) {
        log.info("Downloading the " + sheetName);
        waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
        try {
            waitUnTillWebElementIsVisible("classification", "#q-app .q-page-container [name='activation_group_classification.keyword']");
            if (getSizeOfElements("#q-app .q-page-container #aggsPanelView-collapseAllAggs-btn[aria-label='Collapse All']") == 0) {
                waitAndClickOnElement(collapseExpandBtn);
            }
            waitTillWebElementIsVisible("pageGrid", "#q-app .q-page-container #pageDashboardGridLayout");
            waitAndClickOnElement(resetFilters);
            waitUnTillWebElementIsVisible("accountingStandard", "#q-app .q-page-container [name='accounting_standard_display_id.keyword']");
            handleWait(500);
            //Accounting Standard filter
            int standardSize = driver.findElements(By.cssSelector("#q-app .q-page-container #aggsPanelView-selectFilterValue-radioBtn")).size();
            for (int i = 1; i <= standardSize; i++) {
                waitTillWebElementIsVisible("AccountingStandard", "#q-app .aggs-container .agg-item:nth-child(1) #aggsPanelView-selectFilterValue-radioBtn");
                String standardName = driver.findElement(By.cssSelector("#q-app .aggs-container .agg-item:nth-child(" + i + ") #aggsPanelView-selectFilterValue-radioBtn")).getText();
                if (standardName.equalsIgnoreCase(standard)) {
                    driver.findElement(By.cssSelector("#q-app .aggs-container .agg-item:nth-child(" + i + ") #aggsPanelView-selectFilterValue-radioBtn")).click();
                    break;
                }
            }
            waitUnTillWebElementIsVisible("classification", "#q-app .q-page-container [name='activation_group_classification.keyword']");
            handleWait(500);
            //Classification filter
            int classificationSize = driver.findElements(By.cssSelector("#q-app .q-page-container [name='activation_group_classification.keyword'] #aggsPanelView-checkboxAgg-checkboxBtn")).size();
            for (int j = 1; j <= classificationSize; j++) {
                waitTillWebElementIsVisible("classification", "#q-app .aggs-container [name='activation_group_classification.keyword'] .agg-item:nth-child(1)");
                String classificationName = driver.findElement(By.cssSelector("#q-app .aggs-container [name='activation_group_classification.keyword'] .agg-item:nth-child(" + j + ")")).getText();
                if (classificationName.equalsIgnoreCase(classification)) {
                    driver.findElement(By.cssSelector("#q-app .aggs-container [name='activation_group_classification.keyword'] .agg-item:nth-child(" + j + ")")).click();
                    break;
                }
            }
            waitUnTillWebElementIsVisible("agStatus", "#q-app .q-page-container [name='activation_group_revision_state.keyword']");
            handleWait(500);
            //AG Status filter
            int agStatusSize = driver.findElements(By.cssSelector("#q-app .q-page-container [name='activation_group_revision_state.keyword'] #aggsPanelView-checkboxAgg-checkboxBtn")).size();
            for (int l = 1; l <= agStatusSize; l++) {
                waitTillWebElementIsVisible("classification", "#q-app .aggs-container [name='activation_group_revision_state.keyword'] .agg-item:nth-child(1)");
                String agStatusName = driver.findElement(By.cssSelector("#q-app .aggs-container [name='activation_group_revision_state.keyword'] .agg-item:nth-child(" + l + ")")).getText();
                if (agStatusName.equalsIgnoreCase("Active")) {
                    driver.findElement(By.cssSelector("#q-app .aggs-container [name='activation_group_revision_state.keyword'] .agg-item:nth-child(" + l + ")")).click();
                    break;
                }
            }
            boolean flag = false;
            waitUnTillWebElementIsVisible("agID", "#q-app .q-page-container [name='ag_display_id.keyword'] .agg-item:nth-child(1)"); // AG Display ID Filters
            handleWait(500);
            int AGIDSize = getSizeOfElements("#q-app .q-page-container [name='ag_display_id.keyword'] .agg-item");
            String AGID = MasterHooks.searchAGID.get();
            if (AGIDSize > 0) {
                for (int k = 1; k <= AGIDSize; k++) {
                    waitTillWebElementIsVisible("AGIDList", "#q-app .q-page-container [name='ag_display_id.keyword'] .agg-item:nth-child(1)");// AG Display ID 1st Child
                    String AGIDPresent = getValueFromElement("#q-app .q-page-container [name='ag_display_id.keyword'] .agg-item:nth-child(" + k + ")");
                    if (AGID.equalsIgnoreCase(AGIDPresent)) {
                        getElement("#q-app .q-page-container [name='ag_display_id.keyword'] .agg-item:nth-child(" + k + ")").click();
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                waitAndClickOnElement(clickOnSearchAG); //Search Icon under AG filter
                waitAndClickOnElement(AgSearchField); //Search Field for value
                sendingValueToWebElement("agSerachFiled", AgSearchField, AGID);
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(120)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
                wait.until(new Function<WebDriver, Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        waitTillWebElementIsVisible("searchedAG", enterSerachedAgID);
                        if (getValueFromElement("#q-app .q-page-container [name='ag_display_id.keyword'] #aggValueSearchScrollArea").equalsIgnoreCase("No Data Found")) {
                            try {
                                waitAndClickOnElement(agClearField);
                                waitAndClickOnElement(AgSearchField);
                                sendingValueToWebElement("agSerachFiled", AgSearchField, AGID);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return false;
                        }
                        return true;
                    }
                });
                waitAndClickOnElement(enterSerachedAgID);
            }
            waitAndClickOnElement("Click", "#q-app .q-page-container tr:nth-child(1) td:nth-child(1)");
            waitAndClickOnElement("Download,", "#q-app .q-page-container #dashboardChart-chartOptions-fabBtn");
            waitAndClickOnElement(downloadClickCashFlow);
            waitUntilLoadingSpinnerIsShown("nlaDropDownPopUp");
            waitAndClickOnElement(getElement(".q-dialog .q-card #schedulingDialog-scheduleReportXlsFormat-radioBtn")); //xls file raido button
            waitAndClickOnElement(getElement(".q-dialog .q-card #schedulingDialog-createScheduleReport-btn"));// popup export button
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            handleWait(1000);
            String alertMessage = driver.findElement(By.cssSelector(".desktop .q-notifications .q-notification[role='alert'] .q-notification__message")).getText();
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
            if (!alertMessage.contains("Success")) {
                Assert.fail(sheetName + " has not been generated successfully");
            }
            //Validating Report
            downloadAndValidateReport(standard, sheetName); //needs to change after bug fix NFS-
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String downloadFinancialReports() {
        try {
            waitAndClickOnElement(hamburgerMenu);
            waitAndClickOnElement(hamburgerReports);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Downloading report file");
        //Checking Files in folder before download
        File folder = new File(MasterHooks.downloadedExcelFilePath.get());
        File[] listOfFilesBeforeDown = folder.listFiles();
        assert listOfFilesBeforeDown != null;
        int sizeOfFileBeforeDown = listOfFilesBeforeDown.length;
        System.out.println("The list of files before Excel download are " + sizeOfFileBeforeDown);
        waitTillWebElementIsVisible("table", "#q-app .q-page-container .q-table");
        //Waiting for COMPLETE CONDITION
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(600)).pollingEvery(Duration.ofMillis(5000)).ignoring(WebDriverException.class);
        wait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                waitTillWebElementIsVisible("statusField","#q-app .q-page-container .q-table tr:nth-child(1) td:nth-child(6)");
                if (!getValueFromElement("#q-app .q-page-container .q-table tr:nth-child(1) td:nth-child(6)").equalsIgnoreCase("COMPLETED")) {
                    try {
                        waitAndClickOnElement(refresh);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    return true;
                }
                return false;
            }
        });
        try {
            waitAndClickOnElement(downloadReport);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            if (MasterHooks.configurationProperties.get().getRunEnvironment().equalsIgnoreCase("remote")) {
                download_file_selenium_grid(folder);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //Waiting for file to download
        Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(300)).pollingEvery(Duration.ofMillis(2000)).ignoring(WebDriverException.class);
        wait1.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                File[] listOfFilesAfterDown = folder.listFiles();
                assert listOfFilesAfterDown != null;
                int sizeAfterDown = listOfFilesAfterDown.length;
                return sizeAfterDown != sizeOfFileBeforeDown;
            }
        });

        try {
            waitAndClickOnElement(deleteAllFiles);
            waitTillWebElementIsVisible("deleteButton", ".q-card Button:nth-child(2)");
            waitAndClickOnElement("deleteButton", ".q-card Button:nth-child(2)");
            handleWait(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        File[] downloadedFile = folder.listFiles();
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
        assert lastDownloadedFile != null;
        return lastDownloadedFile.getAbsolutePath();
    }

    public void selectingFiltersForCEReport(DataTable dt) {
        List<Map<String, String>> CEReport = dt.asMaps(String.class, String.class);
        log.info("Contract Expiration Report Page is Opened!!!");
        try {
            waitTillWebElementIsVisible("Contract Expiration Drawer", "#pageDashboardGridLayout0 .pivot-table-view");
            if (getSizeOfElements("#q-app .q-page-container #aggsViewToggleIcon[aria-label='Collapse All']")== 0) {
                waitAndClickOnElement("collapseExpandBtn", "#q-app .q-page-container #aggsPanelView-collapseAllAggs-btn");

            }
            waitAndClickOnElement("resetFilter", "#q-app .q-page-container #aggsPanelView-resetAllFilters-btn");
            waitTillWebElementIsVisible("Contract Expiration Drawer", "#q-app .q-page-container .q-list");
            for (int i = 0; i < CEReport.size(); i++) {
                Map<String, String> filter = CEReport.get(i);
                String filterName = filter.get("Filter Name");
                String filterValue = filter.get("Filter value");

                System.out.println("Filter Name: " + filterName);
                System.out.println("Filter Value: " + filterValue);

                // Logic to apply the filters
                applyFilter(filterName, filterValue, CEReport);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void applyFilter(String filterName, String filterValue, List<Map<String, String>> CEReport) {
        try {
            if (filterName.equalsIgnoreCase("Accounting Standard")) {
                int sizeOfAc = driver.findElements(By.cssSelector("#q-app .q-page-container .q-list .q-expansion-item .agg-item #radioAggRadio")).size();
                for (int l = 1; l <= sizeOfAc; l++) {
                    String acName = driver.findElement(By.cssSelector("#q-app .q-page-container .q-list .q-expansion-item .agg-item:nth-child(" + l + ") #radioAggRadio")).getText();
                    if (filterValue.equalsIgnoreCase(acName)) {
                        waitAndClickOnElement("AccountingStandardSelec", "#q-app .q-page-container .q-list .q-expansion-item .agg-item:nth-child(" + l + ") #radioAggRadio");
                        waitTillWebElementIsVisible("Contract Expiration Drawer", "#pageDashboardGridLayout0 .pivot-table-view");
                        break;
                    }
                }
            }
            handleWait(500);
            int i;
            boolean flag = false;
            int drawerSize = driver.findElements(By.cssSelector("#q-app .q-page-container .aggs-content #aggsViewVisibleAggsExpansionItem .agg-title")).size();
            for (i = 2; i <= drawerSize; i++) {
                String name = driver.findElement(By.cssSelector("#q-app .q-page-container .aggs-content #aggsViewVisibleAggsExpansionItem:nth-child(" + i + ") .agg-name")).getText();
                if (filterName.equalsIgnoreCase(name)) {
                    flag = true;
                    break;
                }

            }

            if (flag) {
                if (!filterValue.equalsIgnoreCase("null")) {
                    if (filterName.equalsIgnoreCase("Activation Group Id") && filterValue.equalsIgnoreCase("id")) {
                        flag = false;
                        int AGIDSize = driver.findElements(By.cssSelector("#q-app .q-page-container [name='activation_group_display_id.keyword'] .agg-item")).size();
                        String AGID = MasterHooks.searchAGID.get();
                        for (int agID = 1; agID <= AGIDSize; agID++) {
                            waitTillWebElementIsVisible("AGIDList", "#q-app .q-page-container [name='activation_group_display_id.keyword'] .agg-item:nth-child(1)");
                            String AGIDPresent = driver.findElement(By.cssSelector("#q-app .q-page-container [name='activation_group_display_id.keyword'] .agg-item:nth-child(" + agID + ")")).getText();
                            if (AGID.equalsIgnoreCase(AGIDPresent)) {
                                driver.findElement(By.cssSelector("#q-app .q-page-container [name='activation_group_display_id.keyword'] .agg-item:nth-child(" + agID + ")")).click();
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            waitAndClickOnElement("clickonsearchAG", "#q-app .q-page-container [name='activation_group_display_id.keyword'] #aggsPanelView-searchAggsValues-btn");
                            waitAndClickOnElement("AgsearchField", "#q-app .q-page-container [name='activation_group_display_id.keyword'] .agg-value-search-wrapper .q-field [placeholder='Search']");
                            sendingValueToWebElement("agSerachFiled", AgSearchField, MasterHooks.searchAGID.get());
                            handleWait(3000);
                            waitAndClickOnElement("enterSerachedAgID", "#q-app .q-page-container [name='activation_group_display_id.keyword'] #aggValueSearchScrollArea");

                        }

                    }
                    int valueSize = driver.findElements(By.cssSelector("#q-app .q-page-container .q-list .q-expansion-item:nth-child(" + i + ") #checkboxAggCheckbox")).size();
                    String[] dataName = filterValue.split(",");
                    for (int header = i; header <= drawerSize; header++) {
                        for (int innerData = 1; innerData <= valueSize; innerData++) {
                            for (String featureDate : dataName) {
                                waitTillWebElementIsVisible("labelName",
                                        "#q-app .q-page-container .q-expansion-item:nth-child(" + header + ") .agg-item:nth-child(" + innerData + ") .q-checkbox__label");
                                String className = driver.findElement(By.cssSelector("#q-app .q-page-container .q-expansion-item:nth-child(" + header + ") .agg-item:nth-child(" + innerData + ") .q-checkbox__label")).getText();
                                if (!className.equalsIgnoreCase("") && featureDate.equalsIgnoreCase(className)) {
                                    if (!filterValue.equalsIgnoreCase("Activation Group Id")) {
                                        waitAndClickOnElement("clickElement", "#q-app .q-page-container .q-expansion-item:nth-child(" + header + ") .agg-item:nth-child(" + innerData + ") #checkboxAggCheckbox");
                                        waitTillWebElementIsVisible("clickElement", "#pageDashboardGridLayout0 .pivot-table-view");
                                    }
                                }
                            }

                        }

                        break;
                    }

                }
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void downloadAndValidateDQIReports(String sheetName) {
        log.info("Downloading the " + sheetName);
        try {
            String prefix ="#q-app .q-page-container [name='ag_display_id']";
            waitUnTillWebElementIsVisible("agID", prefix);
            waitAndClickOnElement(agDisplayIDDQI);
            handleWait(2000);
            boolean flag = false;
            int AGIDSize = getSizeOfElements(prefix + " .agg-item");
            String AGID = MasterHooks.searchAGID.get();
            if (AGIDSize > 0) {
                for (int k = 1; k <= AGIDSize; k++) {
                    waitTillWebElementIsVisible("AGIDList", prefix + " .agg-item:nth-child(1)");
                    String AGIDPresent = getValueFromElement(prefix + " .agg-item:nth-child(" + k + ")");
                    if (AGID.equalsIgnoreCase(AGIDPresent)) {
                        driver.findElement(By.cssSelector(prefix + " .agg-item:nth-child(" + k + ")")).click();
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                waitAndClickOnElement(clickOnSearchAGDQI);
                waitAndClickOnElement(AgSearchFieldDQI);
                sendingValueToWebElement("agSerachFiled", AgSearchFieldDQI, AGID);
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(120)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
                wait.until(new Function<WebDriver, Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        waitTillWebElementIsVisible("searchedAG", enterSerachedAgIDDQI);
                        if (driver.findElement(By.cssSelector(prefix + " #aggValueSearchScrollArea")).getText().equalsIgnoreCase("No Data Found")) {
                            try {
                                waitAndClickOnElement(agClearField);
                                waitAndClickOnElement(AgSearchFieldDQI);
                                sendingValueToWebElement("agSerachFiled", AgSearchFieldDQI, AGID);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return false;
                        }
                        return true;
                    }
                });
                waitAndClickOnElement(enterSerachedAgIDDQI);
            }
            waitUnTillWebElementIsVisible("DownloadBtn", "#q-app .q-page-container #toolbar-exportTable-btn");
            waitAndClickOnElement(downloadDQIReports);
            waitUntilLoadingSpinnerIsShown("nlaDropDownPopUp");
            waitAndClickOnElement("xls",".q-card #schedulingDialog-scheduleReportXlsFormat-radioBtn");
            waitAndClickOnElement("exportButton",".q-card #schedulingDialog-createScheduleReport-btn");
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            handleWait(1000);
            String alertMessage = driver.findElement(By.cssSelector(".desktop .q-notifications .q-notification[role='alert'] .q-notification__message")).getText();
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
            if (!alertMessage.contains("Success")) {
                Assert.fail(sheetName + " has not been generated successfully");
            }
            //Validating Report
            downloadAndValidateReport("", sheetName);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int getRecordCountBefore(String name, String cssSelector) {
        int recordCount = 0;

        // Wait until the element with the record count is visible
        if (waitUntilElementIsShown(cssSelector,6)) {
            String records = driver.findElement(By.cssSelector(cssSelector)).getText();
            recordCount = Integer.parseInt(records.substring(records.indexOf("f") + 2));
            log.info("Record Count is "+recordCount);
        }
        return recordCount;
    }

    public void waitForRecordChange(int recordBefore, String cssSelector) {

        // FluentWait to poll for the change in record count
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(WebDriverException.class);

        // Wait until the record count changes
        wait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                int recordAfter ;
                String records;
                records = getValueFromElement(cssSelector);
                recordAfter = Integer.parseInt(records.substring(records.indexOf("f") + 2));
                if(recordBefore == recordAfter){
                    handleWait(5000);
                    records = getValueFromElement(cssSelector);
                    recordAfter = Integer.parseInt(records.substring(records.indexOf("f") + 2));
                }

                // If the record count has changed, return true
                return recordAfter > recordBefore;
            }
        });
    }

    public void selectReportDR(List<Map<String, String>> job, String fieldName, WebElement webElement) {
        try {
                if (job.get(0).get(fieldName).equalsIgnoreCase("Yes")) {
                    waitAndClickOnElement(webElement);
                }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void selectProfile(String reportName, String profileID) {
        log.info("Searching the Profile for " + reportName);
        waitTillWebElementIsVisible("searchProfile", searchProfileInputField);
        waitTillWebElementIsVisible("profileRadioButton",".q-dialog .q-card tbody tr:nth-child(2) .q-td:nth-child(1) .q-radio");
        int countBeforeSearch = driver.findElements(By.cssSelector(".q-dialog .q-card tbody tr .q-td.id:nth-child(2)")).size();
        if (countBeforeSearch > 1) {
            sendingValueToWebElement("searchProfile", searchProfileInputField, profileID);
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
        try {
            waitAndClickOnElement("profileRadioButton", ".q-dialog .q-card tbody tr:nth-child(2) .q-td:nth-child(1) .q-radio");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteAllScheduledJob(String reportName){
        log.info(""+reportName);
        waitTillWebElementIsVisible("addJobButton"+reportName, addJobButton);

        if (waitUntilElementIsShown(recordPerPage, 6)) {
            String records = getValueFromElement(recordPerPage);
            int recordCount = Integer.parseInt(records.substring(records.indexOf("f") + 2));
            for(int i=0;i<recordCount;i++){
                try {
                    waitAndClickOnElement("deleteButton", ".q-page .q-tr:nth-child(2) .actions [type='button']");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
                waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
            }
        }
        if(getSizeOfElements(recordPerPage)!=0){
            Assert.fail(reportName + " All Schedule job are not deleted");
        }
    }
}
