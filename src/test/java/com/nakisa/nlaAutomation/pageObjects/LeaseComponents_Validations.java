package com.nakisa.nlaAutomation.pageObjects;

import io.cucumber.datatable.DataTable;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
public class LeaseComponents_Validations extends Common_BasePage_PageObject {

    public @FindBy(css = "#q-app .q-drawer .q-page .q-item #lease-component-selector") WebElement leaseComponentSelector;
    public @FindBy(css = "#q-app .q-drawer .q-page .q-item #activation-group-selector") WebElement activationGroupSelector;
    public @FindBy(css = ".q-page-container .q-card #event-id-lc-callback-btn") WebElement callBackButton;
    public @FindBy(css = ".q-page .absolute #edit-unit-distribution-btn") WebElement unitDistributionBtnDefinition;
    public @FindBy(css = ".q-dialog #add-new-unit-distribution-row") WebElement addNewUnitRowDefinition;
    public @FindBy(css = ".q-dialog #number-of-units-undefined-input") WebElement unitQuantity;
    public @FindBy(css = ".q-page .absolute #lease-component-definition-general-info-form-expansion #residual-value-input") WebElement salvageValueDefinition;
    public @FindBy(css = "#q-app .q-page #terms-conditions-step") WebElement termsConditionsTabLeaseComponent;
    public @FindBy(css = ".q-page #lease-component-terms-and-conditions-form-expansion .q-btn--actionable#add-tnc-btn") WebElement addTermAndConditionButton;
    public @FindBy(css = "#q-app .q-page #definition-step") WebElement contractDefinitionTab;
    public LeaseComponents_Validations() {
        super();
        log.info("Driver is inside this class: " + this.getClass().getSimpleName());
        PageFactory.initElements(driver, this);
    }

    public void verifyLeaseComponentIsNotCallBack(String leaseComponentName) {
        log.info("Checking that Lease Component should not be Call Backed when AG is Reverted");
        try {
            WaitUntilElementIsClickable(leaseComponentSelector);
            clickOnDropDownToTypeAndSelectValue("leaseComponentSelector", leaseComponentSelector, leaseComponentName);
            waitAndClickOnElement(callBackButton);
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            String alertMessage = driver.findElement(By.cssSelector(".desktop .q-notifications .q-notification[role='alert'] .q-notification__message")).getText();
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
            Assert.assertEquals("User is able to Call Back Lease Component", "Failed to delete Activation Groups of Lease Component; There are some REVERTED or INACTIVE revisions", alertMessage);
            log.info("User is not able to Call Back Lease Component When Activation Group is Reverted");
            // Moving to AG
            waitTillWebElementIsVisible("activationGroupSelector", activationGroupSelector);
            clickOnDropDownToTypeAndSelectValue("activationGroupSelector", activationGroupSelector,leaseComponentName);
            waitTillWebElementIsVisible("activationGroupNameField", ".q-page .absolute #name-input");
            log.info("User is Activation Group tab");

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void userValidateAllCOBFields(String level) {
        log.info("Validating all the COB required and Populated Fields at "+level);
        if(level.equalsIgnoreCase("Lease Component")) {
            waitTillWebElementIsVisible("IASLeaseAssetGBV","#q-app tbody .required-field #ias-lease-asset-gbv-input");
            checkRequiredField("IASLeaseAssetGBV", "#q-app tbody .required-field #ias-lease-asset-gbv-input");
            checkRequiredField("IASLeaseAssetAD", "#q-app tbody .required-field #ias-lease-asset-ad-input");
            checkRequiredField("GAAPLeaseAssetGBV", "#q-app tbody .required-field #gaap-lease-asset-gbv-input");
            checkRequiredField("GAAPLeaseAssetAD", "#q-app tbody .required-field #gaap-lease-asset-ad-input");
            checkRequiredField("IASCompanyImpairmentReverseBalance", "#q-app tbody .required-field #ias-company-impairment-reserve-balance-input");
            checkRequiredField("IASCompanyAssetNon-RecoverableImpairmentLoss", "#q-app tbody .required-field #ias-company-non-recoverable-impairment-loss-input");
            checkRequiredField("IASLeaseAssetImpairmentReserveBalance", "#q-app tbody .required-field #ias-lease-asset-impairment-reserve-balance-input");
            checkRequiredField("IASLeaseAssetNon-RecoverableImpairmentLoss", "#q-app tbody  .required-field #ias-lease-asset-non-recoverable-impairment-loss-input");
            checkRequiredField("GAAPUseStraight-LineDepreciation", "#q-app tbody #impaired-lease[aria-checked='true']");
            //Checking populated Fields IAS
//            checkPopulatedField("IASCompanyAssetGBV", "#q-app tbody tr:nth-child(1) td:nth-child(2) #company-asset-gbv");
//            checkPopulatedField("IASCompanyAssetAD", "#q-app tbody tr:nth-child(1) td:nth-child(2) #company-asset-ad-input");
//            checkPopulatedField("IASCompanyCurrentYearAD", "#q-app tbody tr:nth-child(1) td:nth-child(2) #company-current-year-ad-input");
//            checkPopulatedField("IASLeaseAssetGBV", "#q-app tbody tr:nth-child(1) td:nth-child(2) #lease-asset-gbv-input");
//            checkPopulatedField("IASLeaseAssetAD", "#q-app tbody tr:nth-child(1) td:nth-child(2) #lease-asset-ad-input");
//            checkPopulatedField("IASLeaseCurrentYearAD", "#q-app tbody tr:nth-child(1) td:nth-child(2) #company-asset-gbv-input");
//            //GAAP
//            checkPopulatedField("GAAPCompanyAssetGBV", "#q-app tbody tr:nth-child(1) td:nth-child(3) #company-asset-gbv-input");
//            checkPopulatedField("GAAPCompanyAssetAD", "#q-app tbody tr:nth-child(1) td:nth-child(3) #company-asset-ad-input");
//            checkPopulatedField("GAAPCompanyCurrentYearAD", "#q-app tbody tr:nth-child(1) td:nth-child(3) #company-current-year-ad-input");
//            checkPopulatedField("GAAPLeaseAssetGBV", "#q-app tbody tr:nth-child(1) td:nth-child(3) #lease-asset-gbv-input");
//            checkPopulatedField("GAAPLeaseAssetAD", "#q-app tbody tr:nth-child(1) td:nth-child(3) #lease-asset-ad-input");
//            checkPopulatedField("GAAPLeaseCurrentYearAD", "#q-app tbody tr:nth-child(1) td:nth-child(2) #company-asset-gbv-input");
//            //IAS
//            checkPopulatedField("IASAccruedInterestExpense", "#q-app tbody tr:nth-child(2) td:nth-child(2) #accrued-interest-expense-input");
//            checkPopulatedField("GAAPAccruedInterestExpense", "#q-app tbody tr:nth-child(2) td:nth-child(3) #accrued-interest-expense-input");
//
//            checkPopulatedField("IASCompanyImpairmentReverseBalance", "#q-app tbody tr:nth-child(3) td:nth-child(2) #company-impairment-reserve-balance-input");
//            checkPopulatedField("IASCompanyAssetNon-RecoverableImpairmentLoss", "#q-app tbody tr:nth-child(3) td:nth-child(2) #company-asset-ad-input");
//            checkPopulatedField("IASLeaseAssetImpairmentReserveBalance", "#q-app tbody tr:nth-child(3) td:nth-child(2) #lease-asset-gbv-input");
//            checkPopulatedField("IASLeaseAssetNon-RecoverableImpairmentLoss", "#q-app tbody tr:nth-child(3) td:nth-child(2) #lease-asset-ad-input");

        }else{
            waitTillWebElementIsVisible("IASLeaseAssetGBV","#q-app tbody .required-field #ias-lease-asset-gbv-input");
            checkRequiredField("IASLeaseAssetGBV", "#q-app tbody .required-field #ias-lease-asset-gbv-input");
            checkRequiredField("IASLeaseAssetAD", "#q-app tbody .required-field #ias-lease-asset-ad-input");
            checkRequiredField("GAAPLeaseAssetGBV", "#q-app tbody .required-field #gaap-lease-asset-gbv-input");
            checkRequiredField("GAAPLeaseAssetAD", "#q-app tbody .required-field #gaap-lease-asset-ad-input");
            checkRequiredField("IASCompanyImpairmentReverseBalance", "#q-app tbody .required-field #ias-company-impairment-reserve-balance-input");
            checkRequiredField("IASCompanyAssetNon-RecoverableImpairmentLoss", "#q-app tbody .required-field #ias-company-non-recoverable-impairment-loss-input");
            checkRequiredField("IASLeaseAssetImpairmentReserveBalance", "#q-app tbody .required-field #ias-lease-asset-impairment-reserve-balance-input");
            checkRequiredField("IASLeaseAssetNon-RecoverableImpairmentLoss", "#q-app tbody  .required-field #ias-lease-asset-non-recoverable-impairment-loss-input");
            checkRequiredField("GAAPUseStraight-LineDepreciation", "#q-app tbody #impaired-lease[aria-checked='true']");
        }
        log.info("Validation for all the COB required and Populated Fields Completed");
    }

    public void unitTableValidations() {
        log.info("Validating the Fields of Unit table");
        try {
            int rowSizeBefore;
            int rowSizeAfter;
            waitTillWebElementIsVisible("unitTable",unitDistributionBtnDefinition);
            waitAndClickOnElement(unitDistributionBtnDefinition);
            waitUntilLoadingSpinnerIsShown("nlaDropDownPopUp");
            waitTillWebElementIsVisible("activationGroupNameField", ".q-dialog tbody tr input[aria-label='Activation Group Name *']");
            waitForClickablility(".q-dialog .required-field#number-of-units-undefined");
            checkRequiredField("unitQuantity",".q-dialog .required-field#number-of-units-undefined");
            //Validating that 'Number of Unit' field is mandatory, and it is empty by default
            log.info("--Validating that Number of units field is empty by default");
            if(!driver.findElement(By.cssSelector(".q-dialog #number-of-units-undefined-input")).getText().equalsIgnoreCase("")) {
                Assert.fail("Number of Unit Field is not Empty By Default and the Value is "+driver.findElement(By.cssSelector(".q-dialog #number-of-units-undefined-input")).getText());
            }
            log.info("Number of Unit Field is Empty By Default");
            //Validating the Add 'New Activation Group' Button Behavior
            log.info("--Validating that Activation Group Button (+) is disabled when 'Number of Unit Field' is Empty--");
            if(!(driver.findElements(By.cssSelector(".q-dialog #add-new-unit-distribution-row[aria-disabled=true]")).size() ==1)){
                Assert.fail("Add new Activation Group Button is Enabled When 'Number of Unit Field' is Empty");
            }
            log.info("Add new Unit Row Button is Disabled When 'Number of Unit Field' is Empty");
            //Sending Value to 1st Unit Fields
            sendingValueToWebElement("quantityUnits", unitQuantity, "1");
            waitAndClickOnElement("popUpHeader", ".q-dialog  .q-card .q-card__section .text-h6.col");
            //Validating the Add 'New Activation Group' Button Behavior
            log.info("--Validating that Activation Group Button (+) is disabled when 'Number of Unit Field' is Empty--");
            if(!(driver.findElements(By.cssSelector(".q-dialog #add-new-unit-distribution-row[aria-disabled=true]")).isEmpty())){
                Assert.fail("Add new Activation Group Button is Disabled When 'Number of Unit Field' is not Empty");
            }
            log.info("Add new Activation Group Button is Enabled When 'Number of Unit Field' is not Empty");
            //Validating the Add Button Behavior When User Clicks on It
            rowSizeBefore = driver.findElements(By.cssSelector(".q-dialog .q-card tbody .q-tr")).size();
            waitAndClickOnElement(addNewUnitRowDefinition);
            rowSizeAfter = driver.findElements(By.cssSelector(".q-dialog .q-card tbody .q-tr")).size();
            if(rowSizeBefore==rowSizeAfter){
                Assert.fail("New Activation Group has not been added");
            }
            log.info("New Activation Group is added");
            //Validating the Add 'New Activation Group' Button Behavior
            log.info("--Validating that Activation Group Button (+) is disabled when 'Number of Unit Field' is Empty--");
            if(!(driver.findElements(By.cssSelector(".q-dialog #add-new-unit-distribution-row[aria-disabled=true]")).size() ==1)){
                Assert.fail("Add new Unit Row Button is Enabled When 'Number of Unit Field' is Empty");
            }
            log.info("Add new Activation Group Button is Disabled When 'Number of Unit Field' is Empty");
            //Sending Value to 2nd Unit Fields
            waitTillWebElementIsVisible("2ndUnit",".q-dialog .q-card tr:nth-child(3) #number-of-units-undefined-input");
            WebElement secondUnitQuantity = driver.findElement(By.cssSelector(".q-dialog .q-card tr:nth-child(3) #number-of-units-undefined-input"));
            sendingValueToWebElement("quantityUnits",secondUnitQuantity , "2");
            waitAndClickOnElement("popUpHeader", ".q-dialog  .q-card .q-card__section .text-h6.col");
            //Deleting the Added new Activation Group
            rowSizeBefore =driver.findElements(By.cssSelector(".q-dialog .q-card tbody .q-tr")).size();
            waitAndClickOnElement("AGDelete",".q-dialog .q-card tr:nth-child(3) #delete-unit-distribution-btn");
            rowSizeAfter = driver.findElements(By.cssSelector(".q-dialog .q-card tbody .q-tr")).size();
            if(rowSizeBefore==rowSizeAfter){
                Assert.fail("New Row has not been deleted");
            }
            log.info("New Row is Deleted");
            waitAndClickOnElement("cancelButton",".q-dialog .q-card #cancel-btn");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void userVerifyThatFieldIsDisabled(String fieldName,String conditionToCheck) {
        log.info("Checking that " + fieldName + " is "+conditionToCheck);
        try {
            if(conditionToCheck.equalsIgnoreCase("Disable")){
                switch (fieldName) {
                    case "Event Button":
                        waitTillWebElementIsVisible("AGEventButton", "#q-app .q-page #context-menu-event[aria-disabled='true']");
                        if (driver.findElements(By.cssSelector("#q-app .q-page #context-menu-event[aria-disabled='true']")).size() != 1) {
                            Assert.fail(fieldName + " is Enabled at Activation Group");
                        }
                        break;
                    case "Salvage Value":
                        waitAndClickOnElement("definitionPage", ".q-page .q-card #definition-step");
                        waitTillWebElementIsVisible("salvageValueDisabledField", ".q-page .absolute #residual-value");
                        if (driver.findElements(By.cssSelector(".q-page .absolute #residual-value[aria-readonly=true]")).size() != 1) {
                            Assert.fail(fieldName + " is "+conditionToCheck+" at Lease Component Event");
                        }
                        break;
                    case "Override Inception IBR Field":
                        waitTillWebElementIsVisible("OverrideIBR", "#q-app .form-input #ibr-rate-carry-over[aria-readonly='true']");
                        if (driver.findElements(By.cssSelector("#q-app .form-input #ibr-rate-carry-over[aria-readonly='true']")).size() != 1) {
                            Assert.fail(fieldName + " is "+conditionToCheck+" at Event");
                        }
                        break;
                    case "Lease End Button":
                        waitTillWebElementIsVisible("leaseEndButton", "#q-app .q-page #event-id-ag-lease-end-btn");
                        if (driver.findElements(By.cssSelector("#q-app .q-page #event-id-ag-lease-end-btn[aria-disabled='true']")).size() != 1) {
                            Assert.fail(fieldName + " is "+conditionToCheck+" at Activation Group");
                        }
                        break;
                    default: {
                        log.warn("No validation implemented for: " + fieldName);
                        break;
                    }
                }
            }else if(conditionToCheck.equalsIgnoreCase("Enable")){
                switch (fieldName) {
                    case "Event Button":
                        waitTillWebElementIsVisible("AGEventButton", "#q-app .q-page #context-menu-event[aria-disabled='true']");
                        if (!driver.findElements(By.cssSelector("#q-app .q-page #context-menu-event[aria-disabled='true']")).isEmpty()) {
                            Assert.fail(fieldName + " is "+conditionToCheck+" at Activation Group");
                        }
                        break;
                    case "Lease End Button":
                        waitTillWebElementIsVisible("leaseEndButton", "#q-app .q-page #event-id-ag-lease-end-btn");
                        if (!driver.findElements(By.cssSelector("#q-app .q-page #event-id-ag-lease-end-btn[aria-disabled='true']")).isEmpty()) {
                            Assert.fail(fieldName + " is "+conditionToCheck+" at Activation Group");
                        }
                        break;
                }
            }
            log.info(fieldName + " is Disabled");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void validationOfFields(String entity,DataTable dt) {
        log.info("Validation of "+entity+" fields");
        List<Map<String, String>> validation = dt.asMaps(String.class, String.class);
        if (entity.equalsIgnoreCase("Contract")) {
            //Company Code
            log.info("Validating the Company Code");
            waitTillWebElementIsVisible("contractDefinitionTab",contractDefinitionTab);
          try {
            waitAndClickOnElement(contractDefinitionTab);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
          waitTillWebElementIsVisible("contractCompany","#q-app .q-page .absolute #company-code");
            WebElement companyCode = driver.findElement(By.cssSelector("#q-app .q-page .absolute #company-code .q-field__inner .q-field__native #company-code-value"));
            String value = companyCode.getText();
            if(!value.contains(validation.get(0).get("Company Code"))){
                Assert.fail("Application Company Code "+value +"is not matching with Expected Company Code "+validation.get(0).get("Company Code"));
            }
            log.info("Application Company Code "+value +"is matching with Expected Company Code "+validation.get(0).get("Company Code"));
        } else if (entity.equalsIgnoreCase("Lease Component")) {
            // Terms & Conditions
            log.info("Validating the Terms & Conditions");
            try {
                waitAndClickOnElement(termsConditionsTabLeaseComponent);
                waitTillWebElementIsVisible("addTermIcon", addTermAndConditionButton);
                if (validation.get(0).get("Terms and Conditions").equalsIgnoreCase("Yes")) {
                    if (driver.findElements(By.cssSelector(".q-table .q-tr:nth-child(2)")).size() == 1) {
                        log.info("User is able to see the copied record of terms and Condition");
                    }
                } else {
                    log.info("User is not able to see the copied record of terms and Condition");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
