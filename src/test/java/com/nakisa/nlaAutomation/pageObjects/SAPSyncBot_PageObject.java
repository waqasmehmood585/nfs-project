package com.nakisa.nlaAutomation.pageObjects;

import io.cucumber.datatable.DataTable;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


@Slf4j
public class SAPSyncBot_PageObject extends Common_BasePage_PageObject {

    public @FindBy(css = ".q-dialog .q-card .q-tab-panels #hour-freq-input") WebElement hourlyHours;
    public @FindBy(css = ".q-dialog .q-card .q-tab-panels #day-freq-input") WebElement dailyDay;
    public @FindBy(css = ".q-dialog .q-card .q-tab-panels #hour-of-day-selection") WebElement hour;
    public @FindBy(css = ".q-dialog .q-card .q-tab-panels #month-freq-input") WebElement monthlyMonth;
    public @FindBy(css = ".q-dialog .q-card .q-tab-panels #day-of-month-selection") WebElement date;

    public SAPSyncBot_PageObject() {
        super();
        log.info("Driver is inside this class: " + this.getClass().getSimpleName());
        PageFactory.initElements(driver, this);
    }

    public void testConnection() {
        waitTillWebElementIsVisible("testConnection", ".q-page .q-table .q-tr:nth-child(2) .q-td:nth-child(8) .q-btn");
        waitForPageToLoad(800);
        log.info("User is on the SAP sync Bot page!!!!");
        try {
            waitAndClickOnElement(driver.findElement(By.cssSelector(".q-page .q-table .q-tr:nth-child(2) .q-td:nth-child(8) .q-btn")));
            waitUntilLoadingSpinnerIsShown("nlaBatchJobListLoader");
            waitUntilLoadingSpinnerIsGone("nlaBatchJobListLoader");
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void creatingSapSyncProfile(String name, DataTable dt) {
        log.info("User is on the SAP Sync Profile Page!!!!");
        List<Map<String, String>> formFields = dt.asMaps(String.class, String.class);
        try {
            //Clicking on Hamburger menu and clicking on Options
            waitTillWebElementIsVisible("hamburgerMenu", ".desktop .q-header Button[aria-label='Menu']");
            WaitUntilElementIsClickable(driver.findElement(By.cssSelector(".desktop .q-header Button[aria-label='Menu']")));
            waitAndClickOnElement(driver.findElement(By.cssSelector(".desktop .q-header Button[aria-label='Menu']")));
            handleWait(200);
            waitUnTillWebElementIsVisible("hamburgerDrawer", "#q-app .main-menu .q-drawer");
            waitAndClickOnElement("sapSyncProfile", ".q-list .q-item:nth-child(4)");
            //completed clicking

            waitTillWebElementIsVisible("createBtn", ".q-page .q-btn-group .q-btn:nth-child(1)");
            int recordBeforeProfile = getRecordCountBefore("#q-app .q-table__bottom .q-table__control:nth-child(4) span.q-table__bottom-item");
            waitAndClickOnElement(driver.findElement(By.cssSelector(".q-page .q-btn-group .q-btn:nth-child(1)")));
            handleWait(1000);
            waitTillWebElementIsVisible("profileName", ".q-card .q-field [aria-label='Profile Name *']");
            sendingValueToWebElement("profileName", driver.findElement(By.cssSelector(".q-card .q-field [aria-label='Profile Name *']")), name);
            waitTillWebElementIsVisible("overrideDefaultLanguage", ".q-card .q-field [aria-label='Override Default Language']");
            sendingValueToWebElement("overrideDefaultLanguage", driver.findElement(By.cssSelector(".q-card .q-field [aria-label='Override Default Language']")), formFields.get(0).get("Override Default Language"));
            handleWait(1000);
            waitTillWebElementIsVisible("selectAll", ".q-card .q-checkbox:nth-child(1) .q-checkbox__inner");
            waitAndClickOnElement(driver.findElement(By.cssSelector(".q-card .q-checkbox:nth-child(1) .q-checkbox__inner")));
            waitTillWebElementIsVisible("overridePartnerTableName", ".q-card .q-field [aria-label='Override Partner Table Name']");
            sendingValueToWebElement("overridePArtnerTableName", driver.findElement(By.cssSelector(".q-card .q-field [aria-label='Override Partner Table Name']")), formFields.get(0).get("Override Partner Table Name"));
            waitTillWebElementIsEnabled("submitBtn", ".q-card .q-btn:nth-child(2)");
            waitAndClickOnElement(driver.findElement(By.cssSelector(".q-card .q-btn:nth-child(2)")));
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");


            //edit profile of SAP Sync Bot
            log.info("User is going to Edit the SAP Sync Profile!!!!");
            waitTillWebElementIsVisible("editProfile", ".q-table .q-tr:nth-child(3) .q-td:nth-child(2)");
            waitAndClickOnElement(driver.findElement(By.cssSelector(".q-table .q-tr:nth-child(3) .q-td:nth-child(2)")));
            waitTillWebElementIsVisible("popupHeaderText", ".q-card .text-h6");
            String SyncProfilePopUp = driver.findElement(By.cssSelector(".q-card .text-h6")).getText();
            if (SyncProfilePopUp.equalsIgnoreCase("Edit Sync Profile")) {
                waitAndClickOnElement(driver.findElement(By.cssSelector(".q-card .q-btn:nth-child(2)")));
                waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
                waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
            } else {
                waitAndClickOnElement(driver.findElement(By.cssSelector(".q-card .q-btn")));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletingProfile() {
        try {
            //delete profile of SAP Sync Bot
            log.info("User is going to Delete the SAP Sync Profile!!!!");
            waitTillWebElementIsVisible("checkbox", ".q-table .q-tr:nth-child(3) .q-td:nth-child(1) .q-checkbox");
            waitAndClickOnElement(driver.findElement(By.cssSelector(".q-table .q-tr:nth-child(3) .q-td:nth-child(1) .q-checkbox")));
            waitTillWebElementIsVisible("deleteProfile", ".q-page .q-btn-group .q-btn:nth-child(2)");
            waitAndClickOnElement(driver.findElement(By.cssSelector(".q-page .q-btn-group .q-btn:nth-child(2)")));
            waitTillWebElementIsVisible("deletePopup", ".q-card .q-btn:nth-child(2)");
            waitAndClickOnElement(driver.findElement(By.cssSelector(".q-card .q-btn:nth-child(2)")));
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public int getRecordCountBefore(String cssSelector) {
        int recordCount = 0;

        // Wait until the element with the record count is visible
        if (waitUnTillWebElementIsVisible("bottonRecord", cssSelector)) {
            String records = driver.findElement(By.cssSelector(cssSelector)).getText();
            recordCount = Integer.parseInt(records.substring(records.indexOf("f") + 2));
            log.info("Record Before Count is " + recordCount);
        }
        return recordCount;
    }

    public void creatingSapSyncPostingJob(DataTable dt) {
        log.info("User is going to create the SAP sync Posting Job!!!!");
        List<Map<String, String>> jobValue = dt.asMaps(String.class, String.class);
        try {
            //Clicking on Hamburger menu and clicking on SAP Sync Bot
            waitTillWebElementIsVisible("hamburgerMenu", ".desktop .q-header Button[aria-label='Menu']");
            WaitUntilElementIsClickable(driver.findElement(By.cssSelector(".desktop .q-header Button[aria-label='Menu']")));
            waitAndClickOnElement(driver.findElement(By.cssSelector(".desktop .q-header Button[aria-label='Menu']")));
            Thread.sleep(200);
            waitUnTillWebElementIsVisible("hamburgerDrawer", "#q-app .main-menu .q-drawer");
            waitAndClickOnElement("sapSyncJob", ".q-list .q-item:nth-child(5)");
            //Completed Clicking on SAP Sync Bot Job

            waitTillWebElementIsVisible("addButton", ".q-page .q-btn-group .q-btn:nth-child(1)");
            int recordCountBeforeJob = 0;
            waitAndClickOnElement(driver.findElement(By.cssSelector(".q-page .q-btn-group .q-btn:nth-child(1)")));
            waitUntilLoadingSpinnerIsShown("nlaFieldSpinner");
            waitUntilLoadingSpinnerIsGone("nlaFieldSpinner");
            waitTillWebElementIsVisible("globalSyncProfile", ".q-card .q-field [aria-label='Global Sync Profile *']");
            //clickOnDropDownAndSelectValue("globalSyncProfile", driver.findElement(By.cssSelector(".q-card .q-field [aria-label='Global Sync Profile *']")), jobValue.get(0).get("Global Sync Profile"));
            waitAndClickOnElement(driver.findElement(By.cssSelector(".q-card .q-field [aria-label='Global Sync Profile *']")));
            waitTillWebElementIsVisible("dropdownValues", ".q-menu .q-item:nth-child(1)");
            int dropDownOptionSize;
            dropDownOptionSize = getSizeOfElements(".q-menu .q-item");
            String innerTextValue = "";
            for (int cssIndex = 1; cssIndex <= dropDownOptionSize; cssIndex++) {
                innerTextValue = getValueFromElement(".q-menu div div:nth-child(" + cssIndex + ") .q-item");
                if (jobValue.get(0).get("Global Sync Profile").equals(innerTextValue)) {
                    waitAndClickOnElement("optionClick", ".q-menu div div:nth-child(" + cssIndex + ") .q-item");
                    break;
                }
            }
            waitTillWebElementIsVisible("syncNow", ".q-card .q-btn:nth-child(2)");
            waitAndClickOnElement(driver.findElement(By.cssSelector(".q-card .q-btn:nth-child(2)")));
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
            log.info("SAP Sync Job created successfully!!!!");
            String records = driver.findElement(By.cssSelector("#q-app .q-table__bottom  .q-table__control:nth-child(3)")).getText();
            int recordAfterJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
            if (recordAfterJob > recordCountBeforeJob) {
                log.info("The SAP Sync job record is increased");
            } else {
                Assert.fail("The Sap Sync Job record is not increasing");
            }
            waitAndClickOnElement(driver.findElement(By.cssSelector(".q-table tbody .q-tr:nth-child(1) div:nth-child(2)")));
            checkJobCompletion("Done");
        }
     catch(InterruptedException e) {
        throw new RuntimeException(e);
    }
}

    private void checkJobCompletion(String jobStatusToCheck) {
        log.info("Checking the Jobs Completed Successfully");
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(1500)).pollingEvery(Duration.ofMillis(10000)).ignoring(WebDriverException.class);
        wait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    waitAndClickOnElement(driver.findElement(By.cssSelector(".q-page .q-btn-group .q-btn:nth-child(3)")));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (driver.findElement(By.cssSelector(".q-table tbody tr:nth-child(2) td:nth-child(7) .q-badge")).getText().equalsIgnoreCase(jobStatusToCheck)) {
                    log.info("Job is completed successfully with status " + jobStatusToCheck);
                    return true;
                } else if (driver.findElement(By.cssSelector(".q-table tbody tr:nth-child(2) td:nth-child(7) .q-badge")).getText().equalsIgnoreCase("Failed")) {
                    log.info("Job is completed but with status Failed");
                    Assert.fail("Job is completed but with status Failed");
                    return true;
                } else {
                    log.info("Job is not Completed Yet");
                    return false;
                }
            }
        });
    }
    public void sapSyncScheduleJob(DataTable dt){
        log.info("User is going to create a SAP Sync Schedule job!!!!");
        List<Map<String, String>> scheduleFields = dt.asMaps(String.class, String.class);
        try {
        //writing the code for Hamburger menu and clicking on SAP Sync Schedle job
        waitTillWebElementIsVisible("hamburgerMenu", ".desktop .q-header Button[aria-label='Menu']");
        WaitUntilElementIsClickable(driver.findElement(By.cssSelector(".desktop .q-header Button[aria-label='Menu']")));
        waitAndClickOnElement(driver.findElement(By.cssSelector(".desktop .q-header Button[aria-label='Menu']")));
        Thread.sleep(200);
        waitUnTillWebElementIsVisible("hamburgerDrawer", "#q-app .main-menu .q-drawer");
        waitAndClickOnElement("sapSyncScheduleJob", ".q-list .q-item:nth-child(6)");
        //completed clicking on SAP Sync Schedule job
            waitTillWebElementIsVisible("addButton", ".q-page .q-btn-group .q-btn:nth-child(1)");
            int recordCountBeforeJob = 0;
            waitAndClickOnElement(driver.findElement(By.cssSelector(".q-page .q-btn-group .q-btn:nth-child(1)")));
            waitUntilLoadingSpinnerIsShown("nlaFieldSpinner");
            waitUntilLoadingSpinnerIsGone("nlaFieldSpinner");



            if (scheduleFields.get(0).get("Schedule Option").equalsIgnoreCase("Hourly")) {
                waitAndClickOnElement("hourlyScheduleOption", ".q-card .q-tabs [v-css-selectors='hourly-tab']");
                clickOnDropDownAndSelectValue("hourlyHours", hourlyHours, scheduleFields.get(0).get("Hour"));
            } else if (scheduleFields.get(0).get("Schedule Option").equalsIgnoreCase("Daily")) {
                waitAndClickOnElement("dailyScheduleOption", ".q-card .q-tabs [v-css-selectors='daily-tab']");
                clickOnDropDownAndSelectValue("dailyDay", dailyDay, scheduleFields.get(0).get("Date"));
                clickOnDropDownAndSelectValue("dailyHour", hour, scheduleFields.get(0).get("Hour"));
            } else if (scheduleFields.get(0).get("Schedule Option").equalsIgnoreCase("Weekly")) {
                waitAndClickOnElement("weeklyScheduleOption", ".q-card .q-tabs [v-css-selectors='weekly-tab']");
                waitAndClickOnElement("sundayCheckbox", ".q-card .q-panel [v-css-selectors='sunday-checkbox']");

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
                waitAndClickOnElement("weeklyScheduleOption", ".q-card .q-tabs [v-css-selectors='monthly-tab']");
                clickOnDropDownAndSelectValue("monthlyMonth", monthlyMonth, scheduleFields.get(0).get("Month"));
                clickOnDropDownAndSelectValue("monthlyDate", date, scheduleFields.get(0).get("Date"));
                clickOnDropDownAndSelectValue("monthlyHour", hour, scheduleFields.get(0).get("Hour"));
            } else if (scheduleFields.get(0).get("Schedule Option").equalsIgnoreCase("Yearly")) {
                waitAndClickOnElement("yearlyScheduleOption", ".q-card .q-tabs [v-css-selectors='yearly-tab']");
                waitAndClickOnElement("januaryCheckbox", ".q-card .q-panel [v-css-selectors='jan-checkbox']");

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


            waitAndClickOnElement(driver.findElement(By.cssSelector(".q-card .q-field [aria-label='Global Sync Profile *']")));
            waitTillWebElementIsVisible("dropdownValues", ".q-menu .q-item:nth-child(1)");
            int dropDownOptionSize;
            dropDownOptionSize = getSizeOfElements(".q-menu .q-item");
            String innerTextValue = "";
            for (int cssIndex = 1; cssIndex <= dropDownOptionSize; cssIndex++) {
                innerTextValue = getValueFromElement(".q-menu div div:nth-child(" + cssIndex + ") .q-item");
                if (scheduleFields.get(0).get("Global Sync Profile").equals(innerTextValue)) {
                    waitAndClickOnElement("optionClick", ".q-menu div div:nth-child(" + cssIndex + ") .q-item");
                    break;
                }
            }
            waitTillWebElementIsVisible("scheduleSync", ".q-card .q-btn:nth-child(3)");
            waitAndClickOnElement(driver.findElement(By.cssSelector(".q-card .q-btn:nth-child(3)")));
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
            log.info("SAP Schedule Sync Job created successfully!!!!");
            String records = driver.findElement(By.cssSelector("#q-app .q-table__bottom  .q-table__control:nth-child(3)")).getText();
            int recordAfterJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
            if (recordAfterJob > recordCountBeforeJob) {
                log.info("The SAP Sync Schedule job record is increased");
            } else {
                Assert.fail("The Sap Sync Schedule Job record is not increasing");
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changingOfState(String state) {
        try {
            log.info("User is going to enable/disable the sap sync scheduled job record!!!!");
            int sizeOfState = driver.findElements(By.cssSelector(".q-table .q-tr:nth-child(2) .q-td:nth-child(6) .q-radio")).size();
            for (int i = 1; i <= sizeOfState; i++) {
                String textOfState = driver.findElement(By.cssSelector(".q-table .q-tr:nth-child(2) .q-td:nth-child(6) .q-radio:nth-child(" + i + ")")).getText();
                if (textOfState.equalsIgnoreCase(state)) {
                    waitAndClickOnElement("stateToClick", ".q-table .q-tr:nth-child(2) .q-td:nth-child(6) .q-radio:nth-child(" + i + ") .q-radio__inner");
                    break;
                }
            }
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public void  deleteSapSyncScheduleJob(){
        log.info("User is going to delete the Sap sync schedule job!!!!");
        try {
            waitAndClickOnElement(driver.findElement(By.cssSelector(".q-table .q-tr:nth-child(2) .q-td:nth-child(7) .q-btn")));
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}