package com.nakisa.nlaAutomation.pageObjects;

import com.nakisa.nlaAutomation.stepDefinitions.MasterHooks;
import com.nakisa.nlaAutomation.utils.DriverThread;
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
public class UserAuxiliary_PageObject extends Common_BasePage_PageObject{

    MasterAgreement_PageObject masterAgreementPageObject = masterAgreement_PageObject.get();
    DriverThread driverThread = new DriverThread();


    public @FindBy(css="#q-app .q-page .q-btn-group #add-btn") WebElement addProfileButton;
    public @FindBy(css = ".q-card .dialog-body #principal-position-type") WebElement principalPosition;
    public @FindBy(css = ".q-card .dialog-body  #name-input") WebElement profileName;

    //Filters
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body #erp-filter-type") WebElement erpSystemFilter;
    public @FindBy(css = ".q-dialog  .q-card #calendar-type") WebElement calendarType;
    public @FindBy(css = ".q-dialog  .q-card #fiscal-variant") WebElement fiscalVariant;
    public @FindBy(css =".q-dialog  .qcard-dialogue .dialog-body #lease-area-filter-type")WebElement leaseAreaFilter;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body #business-unit-filter-type")WebElement businessUnitFilter;
    public @FindBy (css = ".q-dialog  .qcard-dialogue .dialog-body #company-filter-type")WebElement companyFilter;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body #department-filter-type")WebElement leaseDepartmentFilter;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body #lease-group-filter-type") WebElement leaseGroupFilter;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body  #cost-center-filter-type") WebElement costCenterFilter;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body #wbs-filter-type") WebElement wbsFilter;
    public @FindBy(css =".q-dialog  .qcard-dialogue .dialog-body  #profit-center-filter-type") WebElement profitCenterFilter;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body #functional-area-filter-type") WebElement functionalAreaFilter;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body #business-area-filter-type") WebElement businessAreaFilter;

    //Values
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body #erp-systems")WebElement erpSystemFilterValues;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body #lease-areas")WebElement leaseAreaFilterValue;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body #business-units")WebElement businessUnitFilterValues;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body #company-codes") WebElement companyFilterValues;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body #departments")WebElement leaseDepartmentFilterValues;
    public @FindBy(css = ".q-dialog  .q-card #lease-groups") WebElement leaseGroupFieldValues;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body #cost-centers")WebElement costCenterFilterValues;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body #work-breakdown-structures")WebElement wbsFilterValues;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body #profit-centers")WebElement profitCenterFilterValues;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body #functional-areas")WebElement functionalAreaFilterValues;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body #business-areas") WebElement businessAreaFilterValues;
    public @FindBy(css = "#q-app .q-table__container .q-td:nth-child(1) #id-input-input") WebElement searchProfileIDField;
    public @FindBy(css = "#q-app .q-table .q-tr:nth-child(1) #name-input-input") WebElement searchNameField;
    public @FindBy(css = "#q-app .q-table .q-tr:nth-child(1) #col-name-filter") WebElement searchPrincipalPositionField;
    public @FindBy(css = "#q-app .q-table .q-tr:nth-child(1) #fiscal-variant-filter") WebElement searchFiscalVariantField;
    public @FindBy(css = "#q-app .q-table .q-tr:nth-child(1) #lease-areas-filter") WebElement searchLeaseAreaField;
    public @FindBy(css = "#q-app .q-table .q-tr:nth-child(1) #business-units-filter") WebElement searchBusinessUnitField;
    public @FindBy(css = "#q-app .q-table .q-tr:nth-child(1) #companies-filter") WebElement searchCompanyField;
    public @FindBy(css = "#q-app .q-table .q-tr:nth-child(1) #lease-departments-filter") WebElement searchLeaseDepartmentField;
    public @FindBy(css = "#q-app .q-table .q-tr:nth-child(1) #lease-groups-filter") WebElement searchLeaseGroupField;
    public @FindBy(css = "#q-app .q-table .q-tr:nth-child(1) #cost-centers-filter") WebElement searchCostCenterField;
    public @FindBy(css = "#q-app .q-table .q-tr:nth-child(1) #work-breakdown-structures-filter") WebElement searchWBSField;
    public @FindBy(css = "#q-app .q-table .q-tr:nth-child(1) #profit-centers-filter") WebElement searchProfitCenterField;
    public @FindBy(css = ".q-page .q-table .q-tr:nth-child(2) #edit-btn") WebElement editProfileBtn;
    public @FindBy(css = ".q-page .q-table .q-tr:nth-child(2) #delete-btn") WebElement deleteProfileBtn;
    public @FindBy(css = "#q-app .q-page .q-anchor--skip >button") WebElement cancelSearchJob;


    public UserAuxiliary_PageObject() {
        super();
        log.info("Driver is inside this class: " + this.getClass().getSimpleName());
        PageFactory.initElements(driver, this);
    }

    public void createProfile(String jobType, DataTable dt) {
        log.info("Creating a "+ jobType + " Profile");
        if(jobType.equalsIgnoreCase("Report")){
            boolean isChecked = waitUntilTextEqualsIgnoreCase("#q-app [role='tablist'] .q-tab--active", "Report Profiles", 5);
        }else{
            boolean isChecked = waitUntilTextEqualsIgnoreCase("#q-app [role='tablist'] .q-tab--active", "Batch Job Profiles", 5);
        }

        List<Map<String, String>> profile = dt.asMaps(String.class, String.class);
        try {
            if (MasterHooks.configurationProperties.get().getRunAutomationOnNCPBuild()) {
                handleWait(2000);
            }
            waitTillWebElementIsVisible("addProfileButton", addProfileButton);
            int recordBeforeProfile;
            if (waitUnTillWebElementIsVisible("batchPageRecord", "#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")) {
                String records = driver.findElement(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
                int recordCount = Integer.parseInt(records.substring(records.indexOf("f") + 2));
                for(int i=0;i<recordCount;i++){
                    waitUnTillWebElementIsVisible("deleteButton",".q-page .q-tr:nth-child(2) #delete-btn");
                    waitAndClickOnElement("deleteButton",".q-page .q-tr:nth-child(2) #delete-btn");
                    clickOnSubmitPopup("Submit / Add");
                    waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
                    Thread.sleep(1000);
                    String alertMessage = driver.findElement(By.cssSelector(".desktop .q-notifications .q-notification[role='alert'] .q-notification__message")).getText();
                    waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
                    if (!alertMessage.contains("successfully")) {
                        Assert.assertEquals(alertMessage, "Profile is not successfully decommissioned", "Expected Message is not equal to Actual Message | Profile is not deleted successfully");
                    }
                }
            }
            recordBeforeProfile = 0;

            waitAndClickOnElement(addProfileButton);
            //Profile Name
            waitTillWebElementIsVisible("createProfilePopUp", ".q-dialog  .qcard-dialogue");
            waitAndClickOnElement(profileName);
            sendingValueToWebElement("profileName", profileName, profile.get(0).get("Name"));
            waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
            //Principal Position
            waitTillWebElementIsVisible("createProfilePopUp", ".q-dialog  .qcard-dialogue");
            clickOnDropDownAndSelectValue("principalPosition",principalPosition, profile.get(0).get("Principal Position"));
            waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
            //ERP System
            if(!profile.get(0).get("Erp System Filter").equalsIgnoreCase("All")){
                waitTillWebElementIsVisible("erpSystemDropdown", erpSystemFilter);
                clickOnDropDownAndSelectValue("erpSystemFilter",erpSystemFilter, profile.get(0).get("Erp System Filter"));
                waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
                waitTillWebElementIsEnabled("erpSystemFilterValues",".q-dialog  .qcard-dialogue .dialog-body #erp-systems");
                clickOnDropDownToTypeAndSelectCheckBox("erpSystemDropDown", erpSystemFilterValues, MasterHooks.configurationProperties.get().getErpSystem());
                waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
                waitForWebElementToDisappear("dropDownOptions",".q-menu");
                if(jobType.equalsIgnoreCase("Report")){
                    clickOnDropDownAndSelectValue("calendarType", calendarType, profile.get(0).get("Calendar Type"));
                    if (profile.get(0).get("Calendar Type").equalsIgnoreCase("Fiscal Variant")) {
                        waitTillWebElementIsVisible("fiscalVariant", fiscalVariant);
                        clickOnDropDownToTypeAndSelectValue("fiscalVariant", fiscalVariant,
                                profile.get(0).get("Fiscal Variant") );
                    }
                }
            }
            //Lease Area
            if(!profile.get(0).get("Lease Area").equalsIgnoreCase("All")){
                clickOnDropDownAndSelectValue("leaseAreaFilter",leaseAreaFilter,"List");
                waitTillWebElementIsVisible("leaseAreaField",leaseAreaFilterValue);
                clickOnDropDownToTypeAndSelectCheckBox("leaseAreaValues",leaseAreaFilterValue,profile.get(0).get("Lease Area"));
                waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
                waitForWebElementToDisappear("dropDownOptions",".q-menu");
            }
            //Business Unit
            if(!profile.get(0).get("Business Unit").equalsIgnoreCase("All")){
                clickOnDropDownAndSelectValue("BusinessUnitFilter",businessUnitFilter,"List");
                waitTillWebElementIsVisible("BusinessUnitField",businessUnitFilterValues);
                clickOnDropDownToTypeAndSelectCheckBox("BusinessUnitValues",businessUnitFilterValues,profile.get(0).get("Business Unit"));
                waitAndClickOnElement(leaseAreaFilter);
                waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
                waitForWebElementToDisappear("dropDownOptions",".q-menu");
                waitAndClickOnElement(profileName);
            }
            //Company
            if(!profile.get(0).get("Company").equalsIgnoreCase("All")){
                waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                waitAndClickOnElement(profileName);
                clickOnDropDownAndSelectValue("CompanyFilter",companyFilter,"List");
                waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
                waitTillWebElementIsVisible("CompanyField",companyFilterValues);
                clickOnDropDownToTypeAndSelectCheckBox("companyCodes",companyFilterValues,profile.get(0).get("Company"));
                waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
                waitForWebElementToDisappear("dropDownOptions",".q-menu");
                waitAndClickOnElement(profileName);
            }
            //Lease Department
            if(!profile.get(0).get("Lease Department").equalsIgnoreCase("All")){
                clickOnDropDownAndSelectValue("leaseDepartmentFilter",leaseDepartmentFilter,"List");
                waitTillWebElementIsVisible("leaseDepartmentField",leaseDepartmentFilterValues);
                clickOnDropDownToTypeAndSelectCheckBox("leaseDepartmentValues",leaseDepartmentFilterValues,profile.get(0).get("Lease Department"));
                waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
                waitForWebElementToDisappear("dropDownOptions",".q-menu");
            }
            //Lease Group
            if(!profile.get(0).get("Lease Group").equalsIgnoreCase("All")){
                clickOnDropDownAndSelectValue("leaseGroupFilter",leaseGroupFilter,"List");
                waitTillWebElementIsVisible("leaseGroupField",leaseGroupFieldValues);
                clickOnDropDownToTypeAndSelectCheckBox("leaseGroupValues",leaseGroupFieldValues,profile.get(0).get("Lease Group"));
                waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
                waitForWebElementToDisappear("dropDownOptions",".q-menu");
            }
            //Cost Center
            if(!profile.get(0).get("Cost Center").equalsIgnoreCase("All")){
                clickOnDropDownAndSelectValue("CostCenterFilter",costCenterFilter,"List");
                waitTillWebElementIsVisible("CostCenterField",costCenterFilterValues);
                clickOnDropDownToTypeAndSelectCheckBox("CostCenterValues",costCenterFilterValues,profile.get(0).get("Cost Center"));
                waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
            }
            //WBS
            if(!profile.get(0).get("WBS").equalsIgnoreCase("All")){
                clickOnDropDownAndSelectValue("WBSFilter",wbsFilter,"List");
                waitTillWebElementIsVisible("WBSField",wbsFilterValues);
                clickOnDropDownToTypeAndSelectCheckBox("WBSValues",wbsFilterValues,profile.get(0).get("WBS"));
                waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
            }
            //Profit Center
            if(!profile.get(0).get("Profit Center").equalsIgnoreCase("All")){
                clickOnDropDownAndSelectValue("ProfitCenterFilter",profitCenterFilter,"List");
                waitTillWebElementIsVisible("ProfitCenterField",profitCenterFilterValues);
                clickOnDropDownToTypeAndSelectCheckBox("ProfitCenterValues",profitCenterFilterValues,profile.get(0).get("Profit Center"));
                waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
                waitForWebElementToDisappear("dropDownOptions",".q-menu");
            }
            if(jobType.equalsIgnoreCase("Batch")) {
                //Functional Area
                if (!profile.get(0).get("Functional Area").equalsIgnoreCase("All")) {
                    clickOnDropDownAndSelectValue("FunctionalAreaFilter", functionalAreaFilter, "List");
                    waitTillWebElementIsVisible("FunctionalAreaField", functionalAreaFilterValues);
                    clickOnDropDownToTypeAndSelectCheckBox("FunctionalAreaValues", functionalAreaFilterValues, profile.get(0).get("Functional Area"));
                    waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                    waitForWebElementToDisappear("dropDownOptions", ".q-menu");
                }
                //Business Area
                if (!profile.get(0).get("Business Area").equalsIgnoreCase("All")) {
                    clickOnDropDownAndSelectValue("BusinessAreaFilter", businessAreaFilter, "List");
                    waitTillWebElementIsVisible("BusinessAreaField", businessAreaFilterValues);
                    clickOnDropDownToTypeAndSelectCheckBox("BusinessAreaValues", businessAreaFilterValues, profile.get(0).get("Business Area"));
                    waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                    waitForWebElementToDisappear("dropDownOptions", ".q-menu");
                }
            }
            waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
            clickOnSubmitPopup("Submit / Add");
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
            //Waiting for page records to Increase
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
            wait.until(new Function<WebDriver, Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    String records = driver.findElement(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
                    int recordAfterProfile = Integer.parseInt(records.substring(records.indexOf("f") + 2));
                    if(recordAfterProfile!=recordBeforeProfile) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            handleWait(200);
            //Storing The latest created Profile ID
            if(jobType.equalsIgnoreCase("Batch")) {
                MasterHooks.batchProfileID.set(driver.findElement(By.cssSelector("#q-app .q-page .q-table tbody tr:nth-child(2) td.id div")).getText());
            } else if (jobType.equalsIgnoreCase("Report")) {
                MasterHooks.reportProfileID.set(driver.findElement(By.cssSelector("#q-app .q-page .q-table tbody tr:nth-child(2) td.id div")).getText());
            }
            log.info("Profile has been Created Successfully");
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void editProfile(String jobType, DataTable dt) {
        log.info("Editing profile of " + jobType);
        List<Map<String, String>> profile = dt.asMaps(String.class, String.class);
        waitTillWebElementIsVisible("records", "#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item");
        int recordBeforeProfile = getRecordCountBefore(jobType, "#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item");

        waitTillWebElementIsVisible("searchProfileField", searchProfileIDField);
        if(jobType.equalsIgnoreCase("Report")) {
            sendingValueToWebElement("reportProfileID", searchProfileIDField, MasterHooks.reportProfileID.get());
        } else if (jobType.equalsIgnoreCase("Batch")) {
            sendingValueToWebElement("batchProfileID", searchProfileIDField, MasterHooks.batchProfileID.get());
        }


        try {
            waitTillWebElementIsVisible("editProfileBtnJob", editProfileBtn);
            waitAndClickOnElement(editProfileBtn);
            waitTillWebElementIsVisible("createProfilePopUp", ".q-dialog  .qcard-dialogue");
            waitTillWebElementIsVisible("profileName", profileName);
            //Profile Name
            if(!profile.get(0).get("Name").equalsIgnoreCase("null")) {
                waitTillWebElementIsVisible("createProfilePopUp", ".q-dialog  .qcard-dialogue");
                waitAndClickOnElement(profileName);
                sendingValueToWebElement("profileName", profileName, profile.get(0).get("Name"));
                waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
            }
            //Principal Position
            if(!profile.get(0).get("Principal Position").equalsIgnoreCase("null")) {
                waitTillWebElementIsVisible("createProfilePopUp", ".q-dialog  .qcard-dialogue");
                waitAndClickOnElement(principalPosition);
                sendingValueToWebElement("profileName", principalPosition, profile.get(0).get("Principal Position"));
                waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
            }
            //ERP System
            if (!profile.get(0).get("Erp System Filter").equalsIgnoreCase("null")) {
                if (profile.get(0).get("Erp System Filter").equalsIgnoreCase("All")) {
                    waitTillWebElementIsVisible("erpSystemDropdown", erpSystemFilter);
                    clickOnDropDownAndSelectValue("erpSystemFilter", erpSystemFilter, profile.get(0).get("Erp System Filter"));
                    waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                }else{
                    clickOnDropDownToTypeAndSelectValue("erpSystemDropDown", erpSystemFilterValues, MasterHooks.configurationProperties.get().getErpSystem());
                    waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                    waitForWebElementToDisappear("dropDownOptions", ".q-menu");
                }
            }
            if (jobType.equalsIgnoreCase("Report")) {
                if (!profile.get(0).get("Calendar Type").equalsIgnoreCase("null")) {
                    clickOnDropDownAndSelectValue("calendarType", calendarType, profile.get(0).get("Calendar Type"));
                    if (profile.get(0).get("Calendar Type").equalsIgnoreCase("Fiscal Variant")) {
                        waitTillWebElementIsVisible("fiscalVariant", fiscalVariant);
                        clickOnDropDownToTypeAndSelectValue("fiscalVariant", fiscalVariant,
                                profile.get(0).get("Fiscal Variant"));
                    }
                }
            }
            //Lease Area
            if (!profile.get(0).get("Lease Area").equalsIgnoreCase("null")) {
                if (!profile.get(0).get("Lease Area").equalsIgnoreCase("All")) {
                    clickOnDropDownAndSelectValue("leaseAreaFilter", leaseAreaFilter, "List");
                    waitTillWebElementIsVisible("leaseAreaField", leaseAreaFilterValue);
                    clickOnDropDownToTypeAndSelectCheckBox("leaseAreaValues", leaseAreaFilterValue, profile.get(0).get("Lease Area"));
                    waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                    waitForWebElementToDisappear("dropDownOptions", ".q-menu");
                }
            }
            //Business Unit
            if (!profile.get(0).get("Business Unit").equalsIgnoreCase("null")) {
                if (!profile.get(0).get("Business Unit").equalsIgnoreCase("All")) {
                    clickOnDropDownAndSelectValue("BusinessUnitFilter", businessUnitFilter, "List");
                    waitTillWebElementIsVisible("BusinessUnitField", businessUnitFilterValues);
                    clickOnDropDownToTypeAndSelectCheckBox("BusinessUnitValues", businessUnitFilterValues, profile.get(0).get("Business Unit"));
                    waitAndClickOnElement(leaseAreaFilter);
                    waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                    waitForWebElementToDisappear("dropDownOptions", ".q-menu");
                    waitAndClickOnElement(profileName);
                }
            }
            //Company
            if (!profile.get(0).get("Company").equalsIgnoreCase("null")) {
                if (!profile.get(0).get("Company").equalsIgnoreCase("All")) {
                    waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                    waitAndClickOnElement(profileName);
                    clickOnDropDownAndSelectValue("CompanyFilter", companyFilter, "List");
                    waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                    waitTillWebElementIsVisible("CompanyField", companyFilterValues);
                    clickOnDropDownToTypeAndSelectCheckBox("CompanyValues", companyFilterValues, profile.get(0).get("Company"));
                    waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                    waitForWebElementToDisappear("dropDownOptions", ".q-menu");
                    waitAndClickOnElement(profileName);
                }
            }
            //Lease Department
            if (!profile.get(0).get("Lease Department").equalsIgnoreCase("null")) {
                if (!profile.get(0).get("Lease Department").equalsIgnoreCase("All")) {
                    clickOnDropDownAndSelectValue("leaseDepartmentFilter", leaseDepartmentFilter, "List");
                    waitTillWebElementIsVisible("leaseDepartmentField", leaseDepartmentFilterValues);
                    clickOnDropDownToTypeAndSelectCheckBox("leaseDepartmentValues", leaseDepartmentFilterValues, profile.get(0).get("Lease Department"));
                    waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                    waitForWebElementToDisappear("dropDownOptions", ".q-menu");
                }
            }
            //Lease Group
            if (!profile.get(0).get("Lease Group").equalsIgnoreCase("null")) {
                if (!profile.get(0).get("Lease Group").equalsIgnoreCase("All")) {
                    clickOnDropDownAndSelectValue("leaseGroupFilter", leaseGroupFilter, "List");
                    waitTillWebElementIsVisible("leaseGroupField", leaseGroupFieldValues);
                    clickOnDropDownToTypeAndSelectCheckBox("leaseGroupValues", leaseGroupFieldValues, profile.get(0).get("Lease Department"));
                    waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                    waitForWebElementToDisappear("dropDownOptions", ".q-menu");
                }
            }
            //Cost Center
            if (!profile.get(0).get("Cost Center").equalsIgnoreCase("null")) {
                if (!profile.get(0).get("Cost Center").equalsIgnoreCase("All")) {
                    clickOnDropDownAndSelectValue("CostCenterFilter", costCenterFilter, "List");
                    waitTillWebElementIsVisible("CostCenterField", costCenterFilterValues);
                    clickOnDropDownToTypeAndSelectCheckBox("CostCenterValues", costCenterFilterValues, profile.get(0).get("Cost Center"));
                    waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                    waitForWebElementToDisappear("dropDownOptions", ".q-menu");
                    clickOnDropDownAndSelectValue("WBSFilter", wbsFilter, "List");
                    wbsFilterValues.getAttribute("aria-disabled").equalsIgnoreCase("true");
                    clickOnDropDownAndSelectValue("WBSFilter", wbsFilter, "All");
                }
            }
            //WBS
            if (!profile.get(0).get("WBS").equalsIgnoreCase("null")) {
                if (!profile.get(0).get("WBS").equalsIgnoreCase("All")) {
                    clickOnDropDownAndSelectValue("WBSFilter", wbsFilter, "List");
                    waitTillWebElementIsVisible("WBSField", wbsFilterValues);
                    clickOnDropDownToTypeAndSelectCheckBox("WBSValues", wbsFilterValues, profile.get(0).get("WBS"));
                    waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                    waitForWebElementToDisappear("dropDownOptions", ".q-menu");
                }
            }
            //Profit Center
            if (!profile.get(0).get("Profit Center").equalsIgnoreCase("null")) {
                if (!profile.get(0).get("Profit Center").equalsIgnoreCase("All")) {
                    clickOnDropDownAndSelectValue("ProfitCenterFilter", profitCenterFilter, "List");
                    waitTillWebElementIsVisible("ProfitCenterField", profitCenterFilterValues);
                    clickOnDropDownToTypeAndSelectCheckBox("ProfitCenterValues", profitCenterFilterValues, profile.get(0).get("Profit Center"));
                    waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                    waitForWebElementToDisappear("dropDownOptions", ".q-menu");
                }
            }
            if(jobType.equalsIgnoreCase("Batch")) {
                //Functional Area
                if (!profile.get(0).get("Functional Area").equalsIgnoreCase("null")) {
                    if (!profile.get(0).get("Functional Area").equalsIgnoreCase("All")) {
                        clickOnDropDownAndSelectValue("FunctionalAreaFilter", functionalAreaFilter, "List");
                        waitTillWebElementIsVisible("FunctionalAreaField", functionalAreaFilterValues);
                        clickOnDropDownToTypeAndSelectCheckBox("FunctionalAreaValues", functionalAreaFilterValues, profile.get(0).get("Functional Area"));
                        waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                        waitForWebElementToDisappear("dropDownOptions", ".q-menu");
                    }
                }
                //Business Area
                if (!profile.get(0).get("Business Area").equalsIgnoreCase("null")) {
                    if (!profile.get(0).get("Business Area").equalsIgnoreCase("All")) {
                        clickOnDropDownAndSelectValue("BusinessAreaFilter", businessAreaFilter, "List");
                        waitTillWebElementIsVisible("BusinessAreaField", businessAreaFilterValues);
                        clickOnDropDownToTypeAndSelectCheckBox("BusinessAreaValues", businessAreaFilterValues, profile.get(0).get("Business Area"));
                        waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                        waitForWebElementToDisappear("dropDownOptions", ".q-menu");
                    }
                }
            }
            waitAndClickOnElement("popupHeader",".desktop .q-dialog .q-card .dialog-header");
            clickOnSubmitPopup("Submit / Add");
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
//            if(!(recordBeforeProfile==1)){
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
                wait.until(new Function<WebDriver, Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        handleWait(2000);
                        int recordAfterProfile = getRecordCountBefore(jobType, "#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item");
                        return recordAfterProfile == recordBeforeProfile;
                    }
                });
//            }else {
//                handleWait(2000);
//                if(!driver.findElements(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).isEmpty()){
//                    org.testng.Assert.fail("Profile has not been edited successfully.");
//                }
//            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
//        clickOnSubmitPopup("Submit / Add");
        log.info("Profile Edited Successfully");

    }

    public void deleteProfile(String jobType){
        log.info("Deleting "+jobType+" Profile");
        try {
            waitTillWebElementIsVisible("records", "#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item");
            int recordBeforeProfile = getRecordCountBefore(jobType, "#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item");
            waitTillWebElementIsVisible("searchProfileField", searchProfileIDField);
            if(jobType.equalsIgnoreCase("Report")) {
                sendingValueToWebElement("reportProfileID", searchProfileIDField, MasterHooks.reportProfileID.get());
            } else if (jobType.equalsIgnoreCase("Batch")) {
                sendingValueToWebElement("batchProfileID", searchProfileIDField, MasterHooks.batchProfileID.get());
            }
            waitTillWebElementIsVisible("profile",".q-page .q-table .q-tr:nth-child(2)");
            waitAndClickOnElement(deleteProfileBtn);
            clickOnSubmitPopup("Submit / Add");
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            handleWait(1000);
            String alertMessage = driver.findElement(By.cssSelector(".desktop .q-notifications .q-notification[role='alert'] .q-notification__message")).getText();
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
            if (!alertMessage.contains("successfully")) {
                org.testng.Assert.assertEquals(alertMessage, "Profile was successfully decommissioned", "Expected Message is not equal to Actual Message | Profile is not deleted successfully");
            }
            waitAndClickOnElement(cancelSearchJob);
            if(!(recordBeforeProfile==1)) {
                Wait<WebDriver> wait =
                        new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
                wait.until(new Function<WebDriver, Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        String records = driver.findElement(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
                        int recordAfterProfile = Integer.parseInt(records.substring(records.indexOf("f") + 2));
                        if (recordAfterProfile != recordBeforeProfile) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                });
            }else{
                if(!driver.findElements(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).isEmpty()){
                    org.testng.Assert.fail("Profile has not been deleted successfully.");
                }
            }
            log.info(jobType +" Profile has been deleted successfully");
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int getRecordCountBefore(String name, String cssSelector) {
        int recordCount = 0;

        // Wait until the element with the record count is visible
        if (waitUnTillWebElementIsVisible(name, cssSelector)) {
            String records = driver.findElement(By.cssSelector(cssSelector)).getText();
            recordCount = Integer.parseInt(records.substring(records.indexOf("f") + 2));
            log.info("Record Before Count is "+recordCount);
        }
        return recordCount;
    }

    public void searchTheRecordsInProfile(String jobType, DataTable dt) {
        log.info("Searching Report's Profile data");
        List<Map<String, String>> searchRecord = dt.asMaps(String.class, String.class);
        try {
            waitTillWebElementIsVisible("records", "#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item");
            int recordBefore = getRecordCountBefore(jobType, "#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item");
            waitTillWebElementIsVisible("searchProfileField", searchProfileIDField);
            sendingValueToWebElement("profileID", searchProfileIDField, MasterHooks.reportProfileID.get());
            waitTillWebElementIsVisible("profile",".q-page .q-table .q-tr:nth-child(2)");

            waitTillWebElementIsVisible("nameSearch", "#q-app .q-table .q-tr:nth-child(1) .q-td:nth-child(2)");
            waitAndClickOnElement(searchNameField);
            sendingValueToWebElement("nameSearch", searchNameField, searchRecord.get(0).get("Name"));
            waitForFilterResult(recordBefore);
            waitTillWebElementIsVisible("nameSearch", "#q-app .q-table .q-tr:nth-child(1) .q-td:nth-child(2)");
            waitAndClickOnElement(cancelSearchJob);
            waitForFilterCancel(recordBefore);

            if (!searchRecord.get(0).get("Principal Position").equalsIgnoreCase("null")) {
                clickOnDropDownToTypeAndSelectValue("principalPosition", searchPrincipalPositionField, searchRecord.get(0).get("System"));
                waitForFilterResult(recordBefore);
                waitTillWebElementIsVisible("principalPosition", "#q-app .q-table .q-tr:nth-child(1) .q-td:nth-child(5)");
                waitAndClickOnElement(cancelSearchJob);
                waitForFilterCancel(recordBefore);
            }
            if (!searchRecord.get(0).get("Calendar Type").equalsIgnoreCase("null")) {
                clickOnDropDownAndSelectValue("calendarSearch", searchPrincipalPositionField, searchRecord.get(0).get("Calendar Type"));
                waitForFilterResult(recordBefore);
                waitTillWebElementIsVisible("calendarSearch", "#q-app .q-table .q-tr:nth-child(1) .q-td:nth-child(6)");
                waitAndClickOnElement(cancelSearchJob);
                waitForFilterCancel(recordBefore);
            }
            if (!searchRecord.get(0).get("Fiscal Variant").equalsIgnoreCase("null")) {
                clickOnDropDownToTypeAndSelectValue("fyVariantSearch", searchFiscalVariantField, searchRecord.get(0).get("Fiscal Variant"));
                waitForFilterResult(recordBefore);
                waitTillWebElementIsVisible("fyVariantSearch", "#q-app .q-table .q-tr:nth-child(1) .q-td:nth-child(7)");
                waitAndClickOnElement(cancelSearchJob);
                waitForFilterCancel(recordBefore);
            }
            if (!searchRecord.get(0).get("Lease Area").equalsIgnoreCase("null")) {
                clickOnDropDownToTypeAndSelectValue("leaseAreaSearch", searchLeaseAreaField, searchRecord.get(0).get("Lease Area"));
                waitForFilterResult(recordBefore);
                waitTillWebElementIsVisible("leaseAreaSearch", "#q-app .q-table .q-tr:nth-child(1) .q-td:nth-child(9)");
                waitAndClickOnElement(cancelSearchJob);
                waitForFilterCancel(recordBefore);
            }
            if (!searchRecord.get(0).get("Business Unit").equalsIgnoreCase("null")) {
                clickOnDropDownToTypeAndSelectValue("businessUnitSearch", searchBusinessUnitField, searchRecord.get(0).get("Business Unit"));
                waitForFilterResult(recordBefore);
                waitTillWebElementIsVisible("businessUnitSearch", "#q-app .q-table .q-tr:nth-child(1) .q-td:nth-child(10)");
                waitAndClickOnElement(cancelSearchJob);
                waitForFilterCancel(recordBefore);
            }
            if (!searchRecord.get(0).get("Company").equalsIgnoreCase("null")) {
                clickOnDropDownToTypeAndSelectValue("companySearch", searchCompanyField, searchRecord.get(0).get("Company"));
                waitForFilterResult(recordBefore);
                waitTillWebElementIsVisible("companySearch", "#q-app .q-table .q-tr:nth-child(1) .q-td:nth-child(11)");
                waitAndClickOnElement(cancelSearchJob);
                waitForFilterCancel(recordBefore);
            }
            if (!searchRecord.get(0).get("Lease Department").equalsIgnoreCase("null")) {
                clickOnDropDownToTypeAndSelectValue("leaseDepartmentSearch", searchLeaseDepartmentField, searchRecord.get(0).get("Lease Department"));
                waitForFilterResult(recordBefore);
                waitTillWebElementIsVisible("leaseDepartmentSearch", "#q-app .q-table .q-tr:nth-child(1) .q-td:nth-child(12)");
                waitAndClickOnElement(cancelSearchJob);
                waitForFilterCancel(recordBefore);
            }
            if (!searchRecord.get(0).get("Lease Group").equalsIgnoreCase("null")) {
                clickOnDropDownToTypeAndSelectValue("leaseGroupSearch", searchLeaseGroupField, searchRecord.get(0).get("Lease Group"));
                waitForFilterResult(recordBefore);
                waitTillWebElementIsVisible("leaseGroupSearch", "#q-app .q-table .q-tr:nth-child(1) .q-td:nth-child(13)");
                waitAndClickOnElement(cancelSearchJob);
                waitForFilterCancel(recordBefore);
            }
            if (!searchRecord.get(0).get("Cost Center").equalsIgnoreCase("null")) {
                clickOnDropDownToTypeAndSelectValue("costCenterSearch", searchCostCenterField, searchRecord.get(0).get("Cost Center"));
                waitForFilterResult(recordBefore);
                waitTillWebElementIsVisible("costCenterSearch", "#q-app .q-table .q-tr:nth-child(1) .q-td:nth-child(14)");
                waitAndClickOnElement(cancelSearchJob);
                waitForFilterCancel(recordBefore);
            }
            if (!searchRecord.get(0).get("WBS").equalsIgnoreCase("null")) {
                clickOnDropDownToTypeAndSelectValue("WBSSearch", searchWBSField, searchRecord.get(0).get("WBS"));
                waitForFilterResult(recordBefore);
                waitTillWebElementIsVisible("WBSSearch", "#q-app .q-table .q-tr:nth-child(1) .q-td:nth-child(15)");
                waitAndClickOnElement(cancelSearchJob);
                waitForFilterCancel(recordBefore);
            }
            if (!searchRecord.get(0).get("Profit Center").equalsIgnoreCase("null")) {
                clickOnDropDownToTypeAndSelectValue("profitCenterSearch", searchProfitCenterField, searchRecord.get(0).get("Profit Center"));
                waitForFilterResult(recordBefore);
                waitTillWebElementIsVisible("profitCenterSearch", "#q-app .q-table .q-tr:nth-child(1) .q-td:nth-child(16)");
                waitAndClickOnElement(cancelSearchJob);
                waitForFilterCancel(recordBefore);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void waitForFilterResult(int recordBeforeJob) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
        wait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                int recordAfterJob;
                if (driver.findElements(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).size() == 0) {
                    recordAfterJob = 0;
                } else {
                    String records = driver.findElement(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
                    recordAfterJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
                }
                return recordAfterJob<=recordBeforeJob ;
            }

        });
    }

    public void waitForFilterCancel(int recordBeforeJob) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
        wait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                String records = driver.findElement(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
                int recordAfterJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
                return recordAfterJob == recordBeforeJob;
            }
        });
    }


}
