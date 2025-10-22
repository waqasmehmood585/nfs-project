package com.nakisa.nlaAutomation.pageObjects;

import io.cucumber.datatable.DataTable;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
public class AuditLogs_PageObject extends Common_BasePage_PageObject {
    public @FindBy(css = ".q-dialog .q-card .q-tab-panels #hour-freq-input") WebElement hourlyHours;
    public @FindBy(css = ".q-dialog .q-card .q-tab-panels #day-freq-input") WebElement dailyDay;
    public @FindBy(css = ".q-dialog .q-card .q-tab-panels #hour-of-day-selection") WebElement hour;
    public @FindBy(css = ".q-dialog .q-card .q-tab-panels #month-freq-input") WebElement monthlyMonth;
    public @FindBy(css = ".q-dialog .q-card .q-tab-panels #day-of-month-selection") WebElement date;
    public @FindBy(css = " .q-dialog .q-card .dialog-footer  #schedule-job-btn") WebElement scheduleJobButton;
    public AuditLogs_PageObject() {
        super();
        log.info("Driver is inside this class: " + this.getClass().getSimpleName());
        PageFactory.initElements(driver, this);
    }

    public void selectingFiltersForAuditLogs(DataTable dt) {
        log.info("User is on the Audit Logs Page!!!!");
        List<Map<String, String>> filters = dt.asMaps(String.class, String.class);
        //Clicking on Hamburger menu and clicking on Audit Logs for FOS
        waitTillWebElementIsVisible("hamburgerMenu", ".desktop .q-header Button[aria-label='Menu']");
        WaitUntilElementIsClickable(driver.findElement(By.cssSelector(".desktop .q-header Button[aria-label='Menu']")));
        try {
            waitAndClickOnElement(driver.findElement(By.cssSelector(".desktop .q-header Button[aria-label='Menu']")));
        Thread.sleep(200);
        waitUnTillWebElementIsVisible("hamburgerDrawer", "#q-app .main-menu .q-drawer");
        waitAndClickOnElement("auditLogs", ".q-list .q-item__section[title='Audit Logs']");
        waitTillWebElementIsVisible("latestAuditLogs","#main-menu-item-view-latest-audit-logs");
        waitAndClickOnElement(driver.findElement(By.cssSelector("#main-menu-item-view-latest-audit-logs")));
        }
    catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Completed Clicking on Audit logs in FOS
        waitUntilLoadingSpinnerIsShown("nlaLoadingPage");
        waitUntilLoadingSpinnerIsGone("nlaLoadingPage");
        waitTillWebElementIsVisible("drawer", "#q-app .aggs-container");
        waitUntilLoadingSpinnerIsGone("nlaLoadingPage");
        processFilter(filters, "Created At", 1);
        processFilter(filters, "Created By", 2);
        processFilter(filters, "Detail 1", 3);
        processFilter(filters, "Detail 1 Type", 4);
        processFilter(filters, "Detail 2", 5);
        processFilter(filters, "Detail 2 Type", 6);
        processFilter(filters, "Detail 3", 7);
        processFilter(filters, "Detail 3 Type", 8);
        processFilter(filters, "Entity Name", 9);
        processFilter(filters, "Field Name", 10);
        processFilter(filters, "New Value", 11);
        processFilter(filters, "Old Value", 12);
        processFilter(filters, "Revision Id", 13);
        processFilter(filters, "Revision Type", 14);
    }

    public void processFilter(List<Map<String, String>> filters, String filterName, int index) {
        try {
            if (!filters.get(0).get(filterName).equalsIgnoreCase("null")) {
                waitTillWebElementIsVisible("drawerField", "#q-app .aggs-column .aggs-content .q-expansion-item:nth-child(" + index + ") #aggsViewAggTitle");
                waitAndClickOnElement(driver.findElement(By.cssSelector("#q-app .aggs-column .aggs-content .q-expansion-item:nth-child(" + index + ") #aggsViewAggTitle")));
                waitTillWebElementIsVisible("drawerValues", ".q-expansion-item:nth-child(" + index + ") .agg-item:nth-child(1) #aggsPanelView-checkboxAgg-checkboxBtn .q-checkbox__bg");
                waitAndClickOnElement("drawerValues", ".q-expansion-item:nth-child(" + index + ") .agg-item:nth-child(1) #aggsPanelView-checkboxAgg-checkboxBtn .q-checkbox__bg");
                waitUntilLoadingSpinnerIsShown("nlaLoadingPage");
                waitUntilLoadingSpinnerIsGone("nlaLoadingPage");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void creatingProfile(String name) {
        log.info("User is going to create a profile with the selected filters!!!!");
        try {
            waitTillWebElementIsVisible("filterProfiles", ".aggs-header #aggsPanelView-filterProfileManager-openDialog-btn .q-icon");
            waitAndClickOnElement(driver.findElement(By.cssSelector(".aggs-header #aggsPanelView-filterProfileManager-openDialog-btn .q-icon")));
            waitTillWebElementIsVisible("createProfileBtn", "#aggsPanelView-filterProfileManager-openCreateDialog-btn");
            waitAndClickOnElement(driver.findElement(By.cssSelector("#aggsPanelView-filterProfileManager-openCreateDialog-btn")));
            waitTillWebElementIsVisible("profileName", ".q-card .q-field__inner input[aria-label='Profile Name']");
            sendingValueToWebElement("profileName", driver.findElement(By.cssSelector(".q-card .q-field__inner input[aria-label='Profile Name']")), name);
            waitTillWebElementIsVisible("createBtn", "#aggsPanelView-createFilterProfile-createBtn");
            waitAndClickOnElement(driver.findElement(By.cssSelector("#aggsPanelView-createFilterProfile-createBtn")));
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
            log.info("User is going to view the profile");
            waitAndClickOnElement(driver.findElement(By.cssSelector("#aggsPanelView-filterProfileManager-selectFilterProfile-radioBtn")));
            waitTillWebElementIsVisible("viewBtn", ".q-card .q-list #aggsPanelView-filterProfileManager-openViewFilterProfileDialog-btn");
            waitAndClickOnElement(driver.findElement(By.cssSelector(".q-card .q-list #aggsPanelView-filterProfileManager-openViewFilterProfileDialog-btn")));
            waitTillWebElementIsVisible("closeBtn", "#aggsPanelView-filterProfileManager-closeViewFilterProfileDialog-footerBtn");
            waitAndClickOnElement(driver.findElement(By.cssSelector("#aggsPanelView-filterProfileManager-closeViewFilterProfileDialog-footerBtn")));
            log.info("User is applying the filters for profile created");
            waitTillWebElementIsVisible("applyBtn", "#aggsPanelView-filterProfileManager-applyDialog-btn");
            waitAndClickOnElement(driver.findElement(By.cssSelector("#aggsPanelView-filterProfileManager-applyDialog-btn")));
            waitUntilLoadingSpinnerIsShown("nlaLoadingPage");
            waitUntilLoadingSpinnerIsGone("nlaLoadingPage");
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void deletingProfile() {
        log.info("User is going to delete the profile created!!!!");
        try {
            waitAndClickOnElement(driver.findElement(By.cssSelector(".aggs-header #aggsPanelView-filterProfileManager-openDialog-btn .q-icon")));
            waitTillWebElementIsVisible("deleteBtn", "#aggsPanelView-filterProfileManager-openDeleteFilterProfileDialog-btn");
            waitAndClickOnElement(driver.findElement(By.cssSelector("#aggsPanelView-filterProfileManager-openDeleteFilterProfileDialog-btn")));
            waitTillWebElementIsVisible("deleteConfirmationPopUp", "#aggsPanelView-deleteConfirmationDialog-confirmBtn");
            waitAndClickOnElement(driver.findElement(By.cssSelector("#aggsPanelView-deleteConfirmationDialog-confirmBtn")));
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
            waitAndClickOnElement(driver.findElement(By.cssSelector("#aggsPanelView-filterProfileManager-cancelDialog-btn")));
            log.info("User is checking the reset filters button!!!!");
            waitTillWebElementIsVisible("resetFilter", "#aggsPanelView-resetAllFilters-btn");
            waitAndClickOnElement(driver.findElement(By.cssSelector("#aggsPanelView-resetAllFilters-btn")));
            waitUntilLoadingSpinnerIsShown("nlaLoadingPage");
            waitUntilLoadingSpinnerIsGone("nlaLoadingPage");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void toggleFilterOperations() {
    log.info("User is performing the toggle operations for ascending/descending filters!!!!");
        try {
            waitAndClickOnElement(driver.findElement(By.cssSelector("#aggsPanelView-filterOperations-showMenu-btn")));
            log.info("User is sorting the filters!!!!");
            waitTillWebElementIsVisible("sortingBtn","#aggsPanelView-filterOperations-sortBtn");
            waitAndClickOnElement(driver.findElement(By.cssSelector("#aggsPanelView-filterOperations-sortBtn")));
            waitAndClickOnElement(driver.findElement(By.cssSelector("#aggsPanelView-filterOperations-selectAllAggs-checkboxBtn")));
            waitForWebElementToDisappear("drawerElements", "#q-app .aggs-column .aggs-content .q-expansion-item #aggsViewAggTitle");
            waitAndClickOnElement(driver.findElement(By.cssSelector("#aggsPanelView-filterOperations-selectAllAggs-checkboxBtn")));
            waitTillWebElementIsVisible("drawerElements", "#q-app .aggs-column .aggs-content .q-expansion-item #aggsViewAggTitle");
            waitTillWebElementIsVisible("expandAll", "#aggsPanelView-collapseAllAggs-btn");
            waitAndClickOnElement(driver.findElement(By.cssSelector("#aggsPanelView-collapseAllAggs-btn")));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public void exportTableType(String exportType){
        log.info("User is going to export the table of Audit logs!!!!");
        try {
            String[] exportTypes = exportType.split(",");
            for (String type : exportTypes) {
                waitTillWebElementIsVisible("exportTable", "#dynamicPage-exportTable-btn");
                waitAndClickOnElement("exportTable", "#dynamicPage-exportTable-btn");
                waitTillWebElementIsVisible("exportTableTypes", ".q-menu .q-item");
                int sizeOfTableExport = driver.findElements(By.cssSelector(".q-menu .q-item")).size();
                for (int i = 1; i <= sizeOfTableExport; i++) {
                    String nameOfTableExport = driver.findElement(By.cssSelector(".q-menu .q-item:nth-child(" + i + ")")).getText();
                    if (nameOfTableExport.equalsIgnoreCase(type)) {
                        waitAndClickOnElement(driver.findElement(By.cssSelector(".q-menu .q-item:nth-child(" + i + ")")));
                        if (!type.equalsIgnoreCase("Schedule")) {
                            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
                            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
                            break;
                        }

                    }
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void scheduleAuditLogReport(String reportFormat, DataTable dt) {
        log.info("User is on the Schedule page of Audit log!!!!");
        List<Map<String, String>> scheduleFields = dt.asMaps(String.class, String.class);
        try {
            waitTillWebElementIsVisible("format", ".q-card  .q-gutter-sm .q-radio");
            int sizeOfFormat = driver.findElements(By.cssSelector(".q-card  .q-gutter-sm .q-radio")).size();
            for (int i = 1; i <= sizeOfFormat; i++) {
                String nameOfFormat = driver.findElement(By.cssSelector(".q-card  .q-gutter-sm .q-radio:nth-child(" + i + ") .q-radio__label")).getText();
                if (nameOfFormat.equalsIgnoreCase(reportFormat)) {
                    waitAndClickOnElement(driver.findElement(By.cssSelector(".q-card  .q-gutter-sm .q-radio:nth-child(" + i + ") .q-radio__icon")));
                    break;
                }
            }
            waitTillWebElementIsVisible("scheduledFrom", ".q-card .q-px-sm .q-field__inner [aria-label='Scheduled From *']");
            sendingValueToWebElement("scheduledFrom", driver.findElement(By.cssSelector(".q-card .q-px-sm .q-field__inner [aria-label='Scheduled From *']")), scheduleFields.get(0).get("Schedule From"));
            waitTillWebElementIsVisible("scheduledTo", ".q-card .q-px-sm .q-field__inner [aria-label='Scheduled To *']");
            sendingValueToWebElement("scheduledTo", driver.findElement(By.cssSelector(".q-card .q-px-sm .q-field__inner [aria-label='Scheduled To *']")), scheduleFields.get(0).get("Schedule To"));
            if (scheduleFields.get(0).get("Schedule Option").equalsIgnoreCase("Hourly")) {
                waitAndClickOnElement("hourlyScheduleOption", ".q-card .q-tabs #hourly-tab");
                clickOnDropDownAndSelectValue("hourlyHours", hourlyHours, scheduleFields.get(0).get("Hour"));
            } else if (scheduleFields.get(0).get("Schedule Option").equalsIgnoreCase("Daily")) {
                waitAndClickOnElement("dailyScheduleOption", ".q-card .q-tabs #daily-tab");
                clickOnDropDownAndSelectValue("dailyDay", dailyDay, scheduleFields.get(0).get("Date"));
                clickOnDropDownAndSelectValue("dailyHour", hour, scheduleFields.get(0).get("Hour"));
            } else if (scheduleFields.get(0).get("Schedule Option").equalsIgnoreCase("Weekly")) {
                waitAndClickOnElement("weeklyScheduleOption", ".q-card .q-tabs #weekly-tab");
                waitAndClickOnElement("sundayCheckbox", ".q-card .q-panel #sunday-checkbox");

                String[] fieldValuesLength = scheduleFields.get(0).get("Week Days").split(",");
                for (String value : fieldValuesLength) {
                    for (int days = 2; days <= 8; days++) {
                        String dayName = driver.findElement(By.cssSelector(".q-card .q-panel .q-checkbox:nth-child(" + days + ")")).getText();
                        if (value.equalsIgnoreCase(dayName)) {
                            waitAndClickOnElement("weekDayCheckbox", ".q-card .q-panel .q-checkbox:nth-child(" + days + ")");
                        }
                    }
                }
                clickOnDropDownAndSelectValue("weeklyHour", hour, scheduleFields.get(0).get("Hour"));
            } else if (scheduleFields.get(0).get("Schedule Option").equalsIgnoreCase("Monthly")) {
                waitAndClickOnElement("weeklyScheduleOption", ".q-card .q-tabs #monthly-tab");
                clickOnDropDownAndSelectValue("monthlyMonth", monthlyMonth, scheduleFields.get(0).get("Month"));
                clickOnDropDownAndSelectValue("monthlyDate", date, scheduleFields.get(0).get("Date"));
                clickOnDropDownAndSelectValue("monthlyHour", hour, scheduleFields.get(0).get("Hour"));
            } else if (scheduleFields.get(0).get("Schedule Option").equalsIgnoreCase("Yearly")) {
                waitAndClickOnElement("yearlyScheduleOption", ".q-card .q-tabs #yearly-tab");
                waitAndClickOnElement("januaryCheckbox", ".q-card .q-panel #jan-checkbox");

                String[] fieldValuesLength = scheduleFields.get(0).get("Months Name").split(",");
                for (String value : fieldValuesLength) {
                    for (int months = 2; months <= 13; months++) {
                        String dayName = driver.findElement(By.cssSelector(".q-card .q-panel .q-checkbox:nth-child(" + months + ")")).getText();
                        if (value.equalsIgnoreCase(dayName)) {
                            waitAndClickOnElement("monthCheckbox", ".q-card .q-panel .q-checkbox:nth-child(" + months + ")");
                        }
                    }
                }
                clickOnDropDownAndSelectValue("monthlyDate", date, scheduleFields.get(0).get("Date"));
                clickOnDropDownAndSelectValue("monthlyHour", hour, scheduleFields.get(0).get("Hour"));
            }
            waitTillWebElementIsVisible("scheduleJobButton", "#dynamicPage-createScheduleReport-btn");
            waitAndClickOnElement(driver.findElement(By.cssSelector("#dynamicPage-createScheduleReport-btn")));
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportArchivedAuditLogs(DataTable dt) {
        log.info("User is on the export archived audit logs pop up card!!!!");
        try {
            List<Map<String, String>> formValues = dt.asMaps(String.class, String.class);
            //Clicking on Hamburger menu and clicking on Audit Logs for FOS
            waitTillWebElementIsVisible("hamburgerMenu", ".desktop .q-header Button[aria-label='Menu']");
            WaitUntilElementIsClickable(driver.findElement(By.cssSelector(".desktop .q-header Button[aria-label='Menu']")));
            try {
                waitAndClickOnElement(driver.findElement(By.cssSelector(".desktop .q-header Button[aria-label='Menu']")));
                Thread.sleep(200);
                waitUnTillWebElementIsVisible("hamburgerDrawer", "#q-app .main-menu .q-drawer");
                waitAndClickOnElement("auditLogs", ".q-list .q-item__section[title='Audit Logs']");
                waitTillWebElementIsVisible("archivedAuditLogs","#main-menu-item-export-archieved-audit-logs");
                waitAndClickOnElement(driver.findElement(By.cssSelector("#main-menu-item-export-archieved-audit-logs")));
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //Completed Clicking on Audit logs in FOS


            waitTillWebElementIsVisible("fromDate", ".q-card  .q-field__inner [aria-label='From Date *']");
            clickOnCalendarAndPerformAction("dromDate", driver.findElement(By.cssSelector(".q-card  .q-field__inner [aria-label='From Date *']")), formValues.get(0).get("From Date"));
            waitTillWebElementIsVisible("fromTo", ".q-card  .q-field__inner [aria-label='To Date *']");
            clickOnCalendarAndPerformAction("dromDate", driver.findElement(By.cssSelector(".q-card  .q-field__inner [aria-label='To Date *']")), formValues.get(0).get("To Date"));
            waitTillWebElementIsVisible("entity", ".q-card  .q-field__inner [aria-label='Entity *']");
            clickOnDropDownAndSelectValue("entity", driver.findElement(By.cssSelector(".q-card  .q-field__inner [aria-label='Entity *']")), formValues.get(0).get("Entity"));
            waitTillWebElementIsVisible("createdBy", ".q-card  .q-field__inner [aria-label='Created By']");
            sendingValueToWebElement("createdBy", driver.findElement(By.cssSelector(".q-card  .q-field__inner [aria-label='Created By']")), formValues.get(0).get("Created By"));
            waitTillWebElementIsVisible("exportBtn", "#auditLogExportDialog-export-btn");
            waitAndClickOnElement(driver.findElement(By.cssSelector("#auditLogExportDialog-export-btn")));
            waitUntilLoadingSpinnerIsShown("nlaAddButtonLoader");
            waitUntilLoadingSpinnerIsGone("nlaAddButtonLoader");
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);

        }

    }
}






