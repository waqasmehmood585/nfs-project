package com.nakisa.nlaAutomation.pageObjects;

import io.cucumber.datatable.DataTable;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;


@Slf4j
public class ActivationGroup_Validations extends Common_BasePage_PageObject {
    MasterAgreement_PageObject masterAgreementPageObject = masterAgreement_PageObject.get();
    public @FindBy(css = ".q-menu .q-list .q-item:nth-child(2) #report-btn") WebElement revertReport;
    public @FindBy(css = ".q-dialog .q-card #cancel-btn") WebElement cancelButton;
    public @FindBy(css = "#q-app .q-page #revision-btn") WebElement revisionButton;
    public @FindBy(css = ".q-dialog .q-card #cancel-btn") WebElement closeButton;
    public @FindBy(css = "#q-app .q-drawer #schedule-nav-expansion  .q-list .q-item:nth-child(2) .q-item__section--main") WebElement GAAPscheduleLinkToOpen;
    public @FindBy(css = "#q-app .q-drawer #schedule-nav-expansion  .q-list .q-item:nth-child(1) .q-item__section--main") WebElement IASscheduleLinkToOpen;
    public @FindBy(css = ".q-layout .q-page #dropdown-additional-views") WebElement schedulesDropdown;
    // AG Revert
    public @FindBy(css = "#q-app .q-page #context-menu-title-btn") WebElement contextMenuAG;
    public @FindBy(css = "#q-app .q-page tr:nth-child(1) td:nth-child(3) #confirmed-classification") WebElement confirmClassificationGAAP;
    public @FindBy(css = ".q-page-container .q-card #event-id-ag-activate-btn") WebElement draftAGActivate;
    public @FindBy(css = ".qcard-dialogue .q-card__section #document-date-input-input") WebElement documentDateInput;
    public @FindBy(css = ".qcard-dialogue .q-card__section #posting-date-input-input") WebElement postingDateInput;
    public @FindBy(css = ".q-dialog .q-card #close-btn") WebElement cancelBtn;
    public @FindBy(css = "#q-app .q-page #definition-step") WebElement definitionAGTab;
    public @FindBy(css = "#q-app .q-drawer .q-page .q-item #activation-group-selector") WebElement activationGroupSelector;
    public @FindBy(css = ".q-page-container .q-card--bordered #reference-indexation-rate-input") WebElement referenceIndexRate;
    public @FindBy(css = "#q-app .q-layout #charge-list-step") WebElement ChargesTab;
    public @FindBy(css = "#q-app .q-layout #classifications-step") WebElement agClassificationTab;
    public @FindBy(css = "#q-app .q-page .absolute #conditionally-indexed") WebElement agAccountingConditionalIndexLease;
    public @FindBy(css = "#q-app .q-page .absolute #conditionally-indexed-nonlease") WebElement agAccountingConditionalIndexNonLease;
    public @FindBy(css = "#q-app .q-page #notifications-step") WebElement notificationsTab;
    public @FindBy(css = "#q-app .q-page #definition-step") WebElement agDefinitionTab;
    public @FindBy(css = "#q-app .q-page #accounting-step") WebElement agAccountingTab;
    public @FindBy(css = "#q-app .q-page #use-ibr-rate") WebElement contractIBRRate;
    public @FindBy(css = "#q-app .q-page #embedded-derivative") WebElement embeddedDerivative;
    public @FindBy(css = "#q-app .q-page #functional-area") WebElement functionalArea;
    public @FindBy(css = "#q-app .q-page #business-area") WebElement businessArea;
    public @FindBy(css = "#q-app .q-page #segment") WebElement segment;
    public @FindBy(css = "#q-app .q-page #network") WebElement network;
    public @FindBy(css = "#q-app .q-page #track-cost") WebElement trackCost;
    public @FindBy(css = "#q-app .q-page #internal-order-type") WebElement internalOrderType;
    public @FindBy(css = "#q-app .q-page #internal-order") WebElement internalOrder;
    public @FindBy(css = "#q-app .q-page #purchasing-organization") WebElement purchaseOrganization;
    public @FindBy(css = "#q-app .q-page #purchasing-order") WebElement purchaseOrder;
    public @FindBy(css = "#q-app .q-expansion-item #ias-company-asset-gbv-input") WebElement companyAssetGbvIAS;
    public @FindBy(css = "#q-app .q-expansion-item #ias-company-asset-ad-input") WebElement companyAssetAdIAS;
    public @FindBy(css = "#q-app .q-expansion-item #ias-company-current-year-ad-input") WebElement companyCurrentYearAdIAS;
    public @FindBy(css = "#q-app .q-expansion-item #ias-lease-asset-gbv-input") WebElement leaseAssetGbvIAS;
    public @FindBy(css = "#q-app .q-expansion-item #ias-lease-asset-ad-input") WebElement leaseAssetAdIAS;
    public @FindBy(css = "#q-app .q-expansion-item #ias-lease-current-year-ad-input") WebElement leaseCurrentYearAdIAS;
    public @FindBy(css = "#q-app .q-expansion-item #gaap-company-asset-gbv-input") WebElement companyAssetGbvGAAP;
    public @FindBy(css = "#q-app .q-expansion-item #gaap-company-asset-ad-input") WebElement companyAssetAdGAAP;
    public @FindBy(css = "#q-app .q-expansion-item #gaap-company-current-year-ad-input") WebElement companyCurrentYearAdGAAP;
    public @FindBy(css = "#q-app .q-expansion-item #gaap-lease-asset-gbv-input") WebElement leaseAssetGbvGAAP;
    public @FindBy(css = "#q-app .q-expansion-item #gaap-lease-asset-ad-input") WebElement leaseAssetAdGAAP;
    public @FindBy(css = "#q-app .q-expansion-item #gaap-lease-current-year-ad-input") WebElement leaseCurrentYearAdGAAP;
    //Carry-over Liability Balance
    public @FindBy(css = "#q-app .q-expansion-item #ias-accrued-interest-expense-input") WebElement accruedInterestExpenseIAS;
    public @FindBy(css = "#q-app .q-expansion-item #gaap-accrued-interest-expense-input") WebElement accruedInterestExpenseGAAP;
    //Carry-Over Impairment Balance
    public @FindBy(css = "#q-app .q-expansion-item #ias-company-impairment-reserve-balance-input") WebElement companyImpairmentReverveBalanceIAS;
    public @FindBy(css = "#q-app .q-expansion-item #ias-company-non-recoverable-impairment-loss-input") WebElement CompanyAssetNonRecoverableImpairmentLossIAS;
    public @FindBy(css = "#q-app .q-expansion-item #ias-lease-asset-impairment-reserve-balance-input") WebElement LeaseAssetImpairmentReserveBalanceIAS;
    public @FindBy(css = "#q-app .q-expansion-item #ias-lease-asset-non-recoverable-impairment-loss-input") WebElement LeaseAssetNonRecoverableImpairmentLossIAS;
    public @FindBy(css = "#q-app .q-expansion-item #impaired-lease") WebElement DepreciationStraightLineGAAP;
    public @FindBy(css = "#q-app .q-page #name-input") WebElement activationGroupName;
    public @FindBy(css = "#q-app .q-page #unit-list-step") WebElement agUnitTab;
    public @FindBy(css = "#q-app .q-page #workflow-btn") WebElement unitWorkflow;
    public @FindBy(css = "#q-app .q-page #select-all-checkbox") WebElement bulkModifyUnit;
    public @FindBy(css = "#q-app .q-page #snapshot-data-item") WebElement capturedConfiguration;
    public @FindBy(css = "#q-app .q-page #activation-group-workflow") WebElement workflowContextMenu;
    public @FindBy(css = ".row #event-id-ag-generate-schedules-btn") WebElement generateSchedulesBtn;
    public @FindBy(css = ".row #event-id-ag-cancel-generating-schedule-2-btn") WebElement schedulesCancelBtn;
    public @FindBy(css = "#q-app .q-drawer #schedule-nav-expansion .q-list .q-item:nth-child(1)") WebElement schedulesBtn;


    public ActivationGroup_Validations() {
        super();
        log.info("Driver is inside this class: " + this.getClass().getSimpleName());
        PageFactory.initElements(driver, this);
    }

    public void revertReport() {
        log.info("Verifying the AG Revert Report");
        try {
            if(driver.findElements(By.cssSelector(".q-menu .q-list .q-item:nth-child(1) .q-item__section #open-in-new-btn")).size()!=1) {
                waitAndClickOnElement(revisionButton);
                waitTillWebElementIsVisible("dropDownItems", ".q-menu .q-list .q-item:nth-child(1) .q-item__section #open-in-new-btn");
            }

            waitTillWebElementIsVisible("revertReport", revertReport);
            waitAndClickOnElement(revertReport);
            Thread.sleep(3000);
            //Checking the Steps Status
            int taskSize = driver.findElements(By.cssSelector(".q-dialog .q-card .q-table__middle .q-badge")).size();
            for(int index=1; index<=taskSize; index++){
                String stepName = driver.findElement(By.cssSelector(".q-dialog .q-card .q-table__middle .q-tr:nth-child("+index+") .q-td:nth-child(2)")).getText();
                String stepStatus = driver.findElement(By.cssSelector(".q-dialog .q-card .q-table__middle .q-tr:nth-child("+index+") .q-badge")).getText();
                if(!stepStatus.equalsIgnoreCase("Success")){
                    Assert.assertEquals("AG Revert Report Status for Step: '"+stepName+ "' is not Success","Success",stepStatus);
                }
                log.info("AG Revert Report Status for Step: '"+stepName+ "' is Success","Success",stepStatus);
            }
            //Checking the Report Status
            String status = driver.findElement(By.cssSelector(".q-dialog .q-card .q-table__top .q-badge")).getText();
            if (!status.equalsIgnoreCase("Done")) {
                Assert.assertEquals("AG Revert Report Status is not Done","Done",status);
            }
            log.info("AG Revert Report Status is Done");
            waitAndClickOnElement(cancelButton);
            waitAndClickOnElement(revisionButton);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void verifyPostings(String standard ,String scheduleView) {
        log.info("Verifying that Post Button is disabled");
        try {
            if(standard.equalsIgnoreCase("IAS")) {
                waitTillWebElementIsVisible("scheduleLinkToOpen", IASscheduleLinkToOpen);
                waitAndClickOnElement(IASscheduleLinkToOpen);
                if(scheduleView.equalsIgnoreCase("Liability")){
                    clickOnDropDownAndSelectValue("liabilitySchedule", schedulesDropdown, "Liability Schedule");
                }
            }
            if(standard.equalsIgnoreCase("GAAP")) {
                waitTillWebElementIsVisible("scheduleLinkToOpen", GAAPscheduleLinkToOpen);
                waitAndClickOnElement(GAAPscheduleLinkToOpen);
                if(scheduleView.equalsIgnoreCase("Liability")){
                    clickOnDropDownAndSelectValue("liabilitySchedule", schedulesDropdown, "Liability Schedule");
                }
            }
            int postButtonSize;
            if(scheduleView.equalsIgnoreCase("AllColumns")) {
                waitAndClickOnElement("AccrualPosting", "#q-app .q-page .q-table  tbody tr:nth-child(1) #accrual-postings-btn");
                postButtonSize = driver.findElements(By.cssSelector(".q-dialog .q-card #submit-btn[aria-disabled=true]")).size();
                if (!(postButtonSize == 1)) {
                    Assert.assertEquals("Accrual Payment Post Button is Enabled after AG Revert", 0, postButtonSize);
                }
                log.info("Accrual Payment Post Button is disabled");
                waitAndClickOnElement(closeButton);
                waitAndClickOnElement("DepreciationPosting", "#q-app .q-page .q-table  tbody tr:nth-child(1) #depreciation-postings-btn");
                postButtonSize = driver.findElements(By.cssSelector(".q-dialog .q-card #submit-btn[aria-disabled=true]")).size();
                if (!(postButtonSize == 1)) {
                    Assert.assertEquals("Depreciation Payment Post Button is Enabled after AG Revert", 0, postButtonSize);
                }
                log.info("Depreciation Payment Post Button is disabled");
                waitAndClickOnElement(closeButton);
            } else if (scheduleView.equalsIgnoreCase("Liability")) {
                waitAndClickOnElement("PaymentPosting", "#q-app .q-page .q-table  tbody tr:nth-child(1) #payment-postings-btn");
                postButtonSize = driver.findElements(By.cssSelector(".q-dialog .q-card #submit-btn[aria-disabled=true]")).size();
                if (!(postButtonSize == 1)) {
                    Assert.assertEquals("Liability Payment Post Button is Enabled after AG Revert", 0, postButtonSize);
                }
                log.info("Liability Payment Post Button is disabled");
                waitAndClickOnElement(closeButton);
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void verifyButtonStatus(String buttonName, String buttonStatus, String agStatus) {
        log.info("Verifying that " + buttonName + " Button is disabled for " + agStatus + " Activation Group");
        try {
            int buttonSize=0;
            waitTillWebElementIsVisible("AGContextMenu", contextMenuAG);
            WaitUntilElementIsClickable(contextMenuAG);
            contextMenuAG.click();
            if (buttonName.equalsIgnoreCase("Revert")) {
                waitTillWebElementIsVisible("RevertButton", ".q-menu #revert-revision");
                if (buttonStatus.equalsIgnoreCase("Disabled")) {
                    buttonSize = driver.findElements(By.cssSelector(".q-menu #revert-revision[aria-disabled=true]")).size();
                    if (!(buttonSize == 1)) {
                        Assert.assertEquals("AG Revert Button is not disabled for '" + agStatus + "' Activation Group", 0, buttonSize);
                    }
                    log.info("AG Revert Button is disabled for '" + agStatus + "' Activation Group");
                } else if (buttonStatus.equalsIgnoreCase("Enabled")) {
                    buttonSize = driver.findElements(By.cssSelector(".q-menu #revert-revision[aria-enabled=true]")).size();
                    if (!(buttonSize == 1)) {
                        Assert.assertEquals("AG Revert Button is not enabled for '" + agStatus + "' Activation Group", 0, buttonSize);
                    }
                    log.info("AG Revert Button is enabled for '" + agStatus + "' Activation Group");
                }
            }
            else if (buttonName.equalsIgnoreCase("Split")) {
                waitTillWebElementIsVisible("SplitButton", ".q-menu #split-ag-item");
                if (buttonStatus.equalsIgnoreCase("Disabled")) {
                    buttonSize = driver.findElements(By.cssSelector(".q-menu #split-ag-item[aria-disabled=true]")).size();
                    if (!(buttonSize == 1)) {
                        Assert.assertEquals("AG Split Button is not disabled for '" + agStatus + "' Activation Group", 0, buttonSize);
                    }
                    log.info("AG Revert Button is disabled for '" + agStatus + "' Activation Group");
                } else if (buttonStatus.equalsIgnoreCase("Enabled")) {
                    buttonSize = driver.findElements(By.cssSelector(".q-menu #split-ag-item[aria-enabled=true]")).size();
                    if (!(buttonSize == 1)) {
                        Assert.assertEquals("AG Split Button is not enabled for '" + agStatus + "' Activation Group", 0, buttonSize);
                    }
                    log.info("AG Split Button is enabled for '" + agStatus + "' Activation Group");
                }
            }
            definitionAGTab.click();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void verifyActivationGroupStatusLevel(String level,String status,String existence) {
        log.info("Verifying that Activation Group: '" + level + "' with '" + status + "' Status "+ existence);
        try {
            boolean exists = true;
            if(driver.findElements(By.cssSelector(".q-menu .q-list .q-item:nth-child(1) .q-item__section #open-in-new-btn")).size()!=1) {
                waitAndClickOnElement(revisionButton);
                waitTillWebElementIsVisible("dropDownItems", ".q-menu .q-list .q-item:nth-child(1) .q-item__section #open-in-new-btn");
            }
            int size = driver.findElements(By.cssSelector(".q-menu .q-list .q-item")).size();

            if(existence.equalsIgnoreCase("Exists")) {
                for (int item = 1; item <= size; item++) {
                    waitUnTillWebElementIsVisible("AGLevel", ".q-menu .q-list .q-item:nth-child("+item+") .q-item__section--main .q-item__label");
                    String innerTextLevel = driver.findElement(By.cssSelector(".q-menu .q-list .q-item:nth-child("+item+") .q-item__section--main .q-item__label")).getText();
                    waitUnTillWebElementIsVisible("status", ".q-menu .q-list .q-item:nth-child("+ item+") .q-item__section .q-chip__content");
                    String innerTextStatus = driver.findElement(By.cssSelector(".q-menu .q-list .q-item:nth-child("+item+") .q-item__section .q-chip__content")).getText();
                    if (innerTextLevel.equalsIgnoreCase(level) && innerTextStatus.equalsIgnoreCase(status)) {
                        log.info("Activation Group: '" + level + "' with '" + status + "' Status "+ existence);
                        exists = false;
                        break;
                    }
                }
                if(exists){
                    Assert.fail("Activation Group: '" + level + "' with '" + status + "' Status Not"+ existence);
                }
            }
            else{
                for (int item = 1; item <= size; item++) {
                    waitUnTillWebElementIsVisible("AGLevel", ".q-menu .q-list .q-item:nth-child("+item+") .q-item__section--main .q-item__label");
                    String innerTextLevel = driver.findElement(By.cssSelector(".q-menu .q-list .q-item:nth-child("+item+") .q-item__section--main .q-item__label")).getText();
                    waitUnTillWebElementIsVisible("status", ".q-menu .q-list .q-item:nth-child("+ item+") .q-item__section .q-chip__content");
                    String innerTextStatus = driver.findElement(By.cssSelector(".q-menu .q-list .q-item:nth-child("+item+") .q-item__section .q-chip__content")).getText();
                    if (innerTextLevel.equalsIgnoreCase(level) && innerTextStatus.equalsIgnoreCase(status)) {
                        Assert.fail("Activation Group: '" + level + "' with '" + status + "' Status "+ existence);
                    }
                }
                log.info("Activation Group: '" + level + "' with '" + status + "' Status "+ existence);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void verifyAGLevelWithReferenceNumber(String level,String status) {
        log.info("Verifying that Activation Group: " + level + " should exists with " + status + " Status and Reference Number");
        try {
            boolean exists = true;
            if(driver.findElements(By.cssSelector(".q-menu .q-list .q-item:nth-child(1) .q-item__section #open-in-new-btn")).size()!=1) {
                waitAndClickOnElement(revisionButton);
                waitTillWebElementIsVisible("dropDownItems", ".q-menu .q-list .q-item:nth-child(1) .q-item__section #open-in-new-btn");
            }
            int size = driver.findElements(By.cssSelector(".q-menu .q-list .q-item")).size();
            for (int item = 1; item <= size; item++) {
                waitUnTillWebElementIsVisible("AGLevel", ".q-menu .q-list .q-item:nth-child("+item+") .q-item__section--main .q-item__label");
                String innerTextLevel = driver.findElement(By.cssSelector(".q-menu .q-list .q-item:nth-child("+item+") .q-item__section--main .q-item__label")).getText();
                waitUnTillWebElementIsVisible("status", ".q-menu .q-list .q-item:nth-child("+ item+") .q-item__section .q-chip__content");
                String innerTextStatus = driver.findElement(By.cssSelector(".q-menu .q-list .q-item:nth-child("+item+") .q-item__section .q-chip__content")).getText();
                if (innerTextLevel.equalsIgnoreCase(level) && innerTextStatus.equalsIgnoreCase(status)) {
                    if (!driver.findElement(By.cssSelector(".q-menu .q-list .q-item:nth-child("+item+") .q-item__section--side:nth-child(6) div.q-item__label")).getText().equalsIgnoreCase("")) {
                        log.info(" ** Activation Group: '" + level + "' exists with '" + status + "' Status and Reference Number ** ");
                        exists = false;
                        break;
                    }
                    else{
                        Assert.fail(" ** Activation Group: '" + level + "' exists with '" + status + "' Status but with Empty Reference Number ** ");
                    }
                }
            }
            if(exists){
                Assert.fail(" ** Activation Group: '" + level + "' does not exists with '" + status + "' Status and Reference number **");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void verifyAGRevertChanges() {
        log.info("Verifying the Changes in Activation Group after AG Revert");
        log.info(" --Entering Activation Group Definition page and Checking the Transfer of Ownership Checkbox-- ");
        if(!waitUnTillWebElementIsVisible("transferOfOwnerShip",".q-page #asset-definition-form-expansion #transfer-of-ownership[aria-checked=true]")){
            Assert.fail("Transfer of Owner Ship Checkbox is Unchecked");
        }
        log.info("Transfer of Ownership Checkbox is Checked");

        log.info("-- Checking the Classification of GAAP Schedules-- ");
        if(!GAAPscheduleLinkToOpen.getText().equalsIgnoreCase("GAAP - Finance")){
            Assert.fail("GAAP Classification is not Finance");
        }
        log.info("GAAP Classification is Finance");

    }

    public void validateConfirmClassificationValue(String value) {
        log.info("Verifying Confirm Classification's Drop Down Values at Activation Group");
        String[] valuesToCheck = value.split(",");
        waitTillWebElementIsVisible("confirmClassificationGAAP", confirmClassificationGAAP);
        WaitUntilElementIsClickable(confirmClassificationGAAP);
        try {
            waitAndClickOnElement(confirmClassificationGAAP);
        } catch (InterruptedException e) {e.printStackTrace();}
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
        int dropDownOptionSize=driver.findElements(By.cssSelector(".q-menu .q-item")).size();
        Assert.assertEquals("Schedules are not generated", valuesToCheck.length,dropDownOptionSize);
        List<String> innerTextValue = new ArrayList<>();
        for (int cssIndex = 1; cssIndex <= dropDownOptionSize; cssIndex++) {
            waitTillWebElementIsVisible("DropDownValue",".q-menu .q-item:nth-child(" + cssIndex + ") .q-item__section--main");
            innerTextValue.add(driver.findElement(By.cssSelector(".q-menu .q-item:nth-child(" + cssIndex + ") .q-item__section--main")).getText());
        }
        for (String Value: valuesToCheck){
            Assert.assertTrue(Value + " Option is present in the drop down",innerTextValue.contains(Value));
        }
        log.info("Done Verifying Confirm Classification's Drop Down Values at Activation Group");
    }

    public void VerifyIndexationInformation(int rowNum, DataTable dataTable) {
        log.info("Verify Indexation Information at Term Level");
        List<Map<String,String>> info=dataTable.asMaps(String.class, String.class);
        try {
            waitAndClickOnElement("agTerm", ".q-page .absolute tr:nth-child("+(rowNum+1)+")");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //BaseIndexRate
        int baseIndex = Integer.parseInt(info.get(0).get("Base Index"));
        waitTillWebElementIsVisible("ReferenceIndexRate", referenceIndexRate);
        String baseRate=driver.findElement(By.cssSelector(".q-page-container .q-card--bordered #base-indexation-rate-input")).getAttribute("value");
        int baseIndexRateValue =  Integer.parseInt(baseRate);
        Assert.assertEquals(baseIndex, baseIndexRateValue);
        log.info("Base IndexRate Matched");
        //BaseIndexDate
        String baseIndexRateDate = driver.findElement(By.cssSelector(".q-page-container .q-card--bordered #base-indexation-date-input-input")).getAttribute("value");
        Assert.assertEquals(info.get(0).get("Base Indexation Date"), baseIndexRateDate);
        log.info("Base IndexRate Date Matched");
        //ReferenceIndexRate
        int refIndex = Integer.parseInt(info.get(0).get("Reference index"));
        int referenceIndexRateValue = Integer.parseInt(referenceIndexRate.getAttribute("value"));
        Assert.assertEquals(refIndex, referenceIndexRateValue);
        log.info("Reference IndexRate Matched");
        //ReferenceIndexDate
        String referenceIndexRateDate = driver.findElement(By.cssSelector(".q-page-container .q-card--bordered #reference-indexation-date-input-input")).getAttribute("value");
        Assert.assertEquals(info.get(0).get("Reference Indexation Date"), referenceIndexRateDate);
        log.info("Reference IndexRate Date Matched");
        try {
            waitAndClickOnElement("agTerm", ".q-page .absolute tr:nth-child("+(rowNum+1)+")");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void verifyDocumentAndPostingDate(String event) {
        log.info("Opening the Modification Recognition Pop up");
        WaitUntilElementIsClickable(draftAGActivate);
        try {
            waitAndClickOnElement(draftAGActivate);
            String modDate = masterAgreementPageObject.getInputValues().get(event).get("eventdata").get("Modification Date").get(0);
            waitTillWebElementIsVisible("documentDate", documentDateInput);
            if (!modDate.equalsIgnoreCase(documentDateInput.getAttribute("value"))){
                Assert.fail("Document date is not same as Modification date ");
            }
            log.info("Document date is same as Modification date");
            if (!modDate.equalsIgnoreCase(postingDateInput.getAttribute("value"))){
                Assert.fail("Posting date is not same as Modification date ");
            }
            log.info("Posting date is same as Modification date");
            cancelBtn.click();
        } catch (InterruptedException e) {e.printStackTrace();}

    }

    public void numberOfActivationGroups(String numberOfAG) {
        log.info("Checking the Number of Activation Groups in Dropdown");
        //Validating the Activation Group Split
        try {
            if(numberOfAG.equalsIgnoreCase("Null")){
                clickOnDropDownToTypeAndVerifyValueIsNull("activationGroupSelector",activationGroupSelector,masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Activation Group Name").get(0));
            }else{
                String[] AGNameLength=masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Activation Group Name").get(0).split(",");
                int dropDownOptionSize = clickOnDropDownAndGetDropDownSize("activationGroupSplitSelector", activationGroupSelector);
                if (!(dropDownOptionSize == Integer.parseInt(numberOfAG))) {
                    Assert.fail("Number of Activation Group are not matching with the Provided Number: " + numberOfAG);
                }
            }
            log.info("Number of Activation Group are matching with the Provided Number: "+ numberOfAG);
            waitAndClickOnElement(activationGroupSelector);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void unitIdCheck(){
        log.info("Checking the Unit Id of Charge in Split AG");
        try {
            waitAndClickOnElement(ChargesTab);
            waitUntilLoadingSpinnerIsShown("nlaTabChange");
            waitUntilLoadingSpinnerIsGone("nlaTabChange");
            waitTillWebElementIsVisible("unitId","#q-app .q-page .q-tr:nth-child(2) .q-td:nth-child(5)");
            String unitId = driver.findElement(By.cssSelector("#q-app .q-page .q-tr:nth-child(2) .q-td:nth-child(5)")).getText();
            if(!unitId.equalsIgnoreCase(C_Activation_Group_PageObject.unitIds[1])){
                    Assert.fail(C_Activation_Group_PageObject.unitIds[1] + ": This Unit Id is not matching with Respective Split Activation Group");
                }
           log.info(C_Activation_Group_PageObject.unitIds[1] + ": This Unit Id is matching with Respective Split Activation Group");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void conditionalIndexationCheckbox(String fieldName){
        log.info("Checking that "+ fieldName+ " is not present on UI for Indexed Currency Contracts");
        //For Lease
        if(elementsVisibility("conditionalIndexationLease", "#q-app .q-page .absolute #conditionally-indexed")){
            Assert.fail("Conditional Indexation lease checkbox is present on UI for Indexed Currency Contracts");
        }
        log.info("Conditional Indexation lease checkbox is not present on UI for Indexed Currency Contracts");
        //For Non-Lease
        if(elementsVisibility("conditionalIndexationNonLease", "#q-app .q-page .absolute #conditionally-indexed-nonlease")){
            Assert.fail("Conditional Indexation Non-Lease checkbox is present on UI for Indexed Currency Contracts");
        }
        log.info("Conditional Indexation Non-Lease checkbox is not present on UI for Indexed Currency Contracts");
    }

    public void verifyCategories(String entityType) {
        try {
            log.info("Verification of Categories at " + entityType + " level");
            waitAndClickOnElement(notificationsTab);
            waitUntilLoadingSpinnerIsShown("nlaTabChange");
            waitUntilLoadingSpinnerIsGone("nlaTabChange");
            waitTillWebElementIsVisible("Category",".q-mt-md .q-gutter-sm div.row div.col-4");
            int categorySize=driver.findElements(By.cssSelector(".q-mt-md .q-gutter-sm div.row div.col-4")).size();
            for(int i=1;i<=categorySize;i++){
                String categoryName=driver.findElement(By.cssSelector(".q-mt-md .q-gutter-sm div.row:nth-child(" + i + ") div.col-4")).getText();
                if(!categoryName.equalsIgnoreCase("Conflicted Default Values Configs")&& !categoryName.equalsIgnoreCase("Scheduled Job Completion")){
                    log.info("User is getting the category as: " + categoryName + " at " + entityType + " level");
                }
                else{
                    Assert.fail("User is getting the deprecated category as " + categoryName + " at " + entityType + " level");
                }

            }
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void agAccounting(DataTable dt) {
        log.info("Hitting the AG ACCounting tab");
        try {
            List<Map<String, String>> agAccountingData = dt.asMaps(String.class, String.class);
            waitAndClickOnElement(agAccountingTab);
            waitUntilLoadingSpinnerIsShown("nlaTabChange");
            waitUntilLoadingSpinnerIsGone("nlaTabChange");
            //IBR Rate
            waitTillWebElementIsVisible("contractImplicitRate", contractIBRRate);
            WaitUntilElementIsClickable(contractIBRRate);
            waitAndClickOnElement(contractIBRRate);
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
            //Embedded Derivative
            WaitUntilElementIsClickable(embeddedDerivative);
            waitAndClickOnElement(embeddedDerivative);
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
            //Functional Area
            waitTillWebElementIsVisible("functionalArea", functionalArea);
            clickOnDropDownToTypeAndSelectValue("functionalArea", functionalArea, agAccountingData.get(0).get("Functional Area"));
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
            //Business Area
            waitTillWebElementIsVisible("businessArea", businessArea);
            clickOnDropDownToTypeAndSelectValue("businessArea", businessArea, agAccountingData.get(0).get("Business Area"));
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
            //Segment
            waitTillWebElementIsVisible("segment", segment);
            clickOnDropDownToTypeAndSelectValue("segment", segment, agAccountingData.get(0).get("Segment"));
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
            //Network
            clickOnDropDownAndSelectValue("network", network, agAccountingData.get(0).get("Network"));
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
            //Track Cost
            WaitUntilElementIsClickable(trackCost);
            waitAndClickOnElement(trackCost);
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
            //Internal order Type
            waitTillWebElementIsVisible("internalOrderType", internalOrderType);
            clickOnDropDownToTypeAndSelectValue("internalOrderType", internalOrderType, agAccountingData.get(0).get("Internal Order Type"));
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
            //Network
            clickOnDropDownToTypeAndSelectValue("internalOrder", internalOrder, agAccountingData.get(0).get("Internal Order"));
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
            //Purchase Organization
            waitTillWebElementIsVisible("purchaseOrganization", purchaseOrganization);
            clickOnDropDownToTypeAndSelectValue("purchaseOrganization", purchaseOrganization, agAccountingData.get(0).get("Purchase Organization"));
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
            //Purchase Order
            waitTillWebElementIsVisible("purchaseOrder", purchaseOrder);
            clickOnDropDownToTypeAndSelectValue("purchaseOrder", purchaseOrder, agAccountingData.get(0).get("Purchase order"));
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public void carryOverBalance() {
        log.info("Entering the Data in Cary Over Balance Fields");
        try {
            waitAndClickOnElement(agClassificationTab);
            waitUntilLoadingSpinnerIsShown("nlaTabChange");
            waitUntilLoadingSpinnerIsGone("nlaTabChange");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        waitUntilLoadingSpinnerIsShown("nlaTabChange");
        waitUntilLoadingSpinnerIsGone("nlaTabChange");
        if(!(masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Company Asset GBV").get(0).equalsIgnoreCase("-")||
                masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Company Asset GBV").get(0).equalsIgnoreCase(""))){
            WaitUntilElementIsClickable(companyAssetGbvIAS);
            sendingValueToWebElement("companyAssetGbvIAS",companyAssetGbvIAS,
                    masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Company Asset GBV").get(0));
            agClassificationTab.click();
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        }
        if(!(masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Company Asset AD").get(0).equalsIgnoreCase("-")||
                masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Company Asset AD").get(0).equalsIgnoreCase(""))){
            WaitUntilElementIsClickable(companyAssetAdIAS);
            sendingValueToWebElement("companyAssetAdIAS",companyAssetAdIAS,
                    masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Company Asset AD").get(0));
            agClassificationTab.click();
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        }
        if(!(masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Company Current Year AD").get(0).equalsIgnoreCase("-")||
                masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Company Current Year AD").get(0).equalsIgnoreCase(""))){
            WaitUntilElementIsClickable(companyCurrentYearAdIAS);
            sendingValueToWebElement("companyCurrentYearAdIAS",companyCurrentYearAdIAS,
                    masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Company Current Year AD").get(0));
            agClassificationTab.click();
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        }
        if(!(masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Lease Asset GBV").get(0).equalsIgnoreCase("-")||
                masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Lease Asset GBV").get(0).equalsIgnoreCase(""))){
            WaitUntilElementIsClickable(leaseAssetGbvIAS);
            sendingValueToWebElement("leaseAssetGbvIAS",leaseAssetGbvIAS,
                    masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Lease Asset GBV").get(0));
            agClassificationTab.click();
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        }
        if(!(masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Lease Asset AD").get(0).equalsIgnoreCase("-")||
                masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Lease Asset AD").get(0).equalsIgnoreCase(""))){
            WaitUntilElementIsClickable(leaseAssetAdIAS);
            sendingValueToWebElement("leaseAssetAdIAS",leaseAssetAdIAS,
                    masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Lease Asset AD").get(0));
            agClassificationTab.click();
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        }
        if(!(masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Lease Current Year AD").get(0).equalsIgnoreCase("-")||
                masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Lease Current Year AD").get(0).equalsIgnoreCase(""))){
            WaitUntilElementIsClickable(leaseCurrentYearAdIAS);
            sendingValueToWebElement("leaseCurrentYearAdIAS",leaseCurrentYearAdIAS,
                    masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Lease Current Year AD").get(0));
            agClassificationTab.click();
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        }
        //Impairment Liability Balance IAS
        if(!(masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Accrued Interest Expense").get(0).equalsIgnoreCase("-")||
                masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Accrued Interest Expense").get(0).equalsIgnoreCase(""))) {
            WaitUntilElementIsClickable(accruedInterestExpenseIAS);
            sendingValueToWebElement("accruedInterestExpense", accruedInterestExpenseIAS,
                    masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Accrued Interest Expense").get(0));
            agClassificationTab.click();
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        }
        //GAAP
        if(!(masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Company Asset GBV").get(1).equalsIgnoreCase("-")||
                masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Company Asset GBV").get(1).equalsIgnoreCase(""))){
            WaitUntilElementIsClickable(companyAssetGbvGAAP);
            sendingValueToWebElement("companyAssetGbvGAAP",companyAssetGbvGAAP,
                    masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Company Asset GBV").get(1));
            agClassificationTab.click();
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        }
        if(!(masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Company Asset AD").get(1).equalsIgnoreCase("-")||
                masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Company Asset AD").get(1).equalsIgnoreCase(""))){
            WaitUntilElementIsClickable(companyAssetAdGAAP);
            sendingValueToWebElement("companyAssetAdIAS",companyAssetAdGAAP,
                    masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Company Asset AD").get(1));
            agClassificationTab.click();
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        }
        if(!(masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Company Current Year AD").get(1).equalsIgnoreCase("-")||
                masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Company Current Year AD").get(1).equalsIgnoreCase(""))){
            WaitUntilElementIsClickable(companyCurrentYearAdGAAP);
            sendingValueToWebElement("companyCurrentYearAdIAS",companyCurrentYearAdGAAP,
                    masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Company Current Year AD").get(1));
            agClassificationTab.click();
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        }
        if(!(masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Lease Asset GBV").get(1).equalsIgnoreCase("-")||
                masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Lease Asset GBV").get(1).equalsIgnoreCase(""))){
            WaitUntilElementIsClickable(leaseAssetGbvGAAP);
            sendingValueToWebElement("leaseAssetGbvGAAP",leaseAssetGbvGAAP,
                    masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Lease Asset GBV").get(1));
            agClassificationTab.click();
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        }
        if(!(masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Lease Asset AD").get(1).equalsIgnoreCase("-")||
                masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Lease Asset AD").get(1).equalsIgnoreCase(""))){
            WaitUntilElementIsClickable(leaseAssetAdGAAP);
            sendingValueToWebElement("leaseAssetAdGAAP",leaseAssetAdGAAP,
                    masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Lease Asset AD").get(1));
            agClassificationTab.click();
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        }
        if(!(masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Lease Current Year AD").get(1).equalsIgnoreCase("-")||
                masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Lease Current Year AD").get(1).equalsIgnoreCase(""))){
            WaitUntilElementIsClickable(leaseCurrentYearAdGAAP);
            sendingValueToWebElement("leaseCurrentYearAdGAAP",leaseCurrentYearAdGAAP,
                    masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Lease Current Year AD").get(1));
            agClassificationTab.click();
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        }
        //Impairment Liability Balance GAAP
        if(!(masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Accrued Interest Expense").get(1).equalsIgnoreCase("-")||
                masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Accrued Interest Expense").get(1).equalsIgnoreCase(""))) {
            WaitUntilElementIsClickable(accruedInterestExpenseGAAP);
            sendingValueToWebElement("accruedInterestExpense", accruedInterestExpenseGAAP,
                    masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Accrued Interest Expense").get(1));
            agClassificationTab.click();
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        }
        //Impairment Carry Over Balance IAS
        if(!(masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Company Impairment Reserve Balance").get(0).equalsIgnoreCase("-")||
                masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Company Impairment Reserve Balance").get(0).equalsIgnoreCase(""))){
            WaitUntilElementIsClickable(companyImpairmentReverveBalanceIAS);
            sendingValueToWebElement("companyImpairmentReverveBalanceIAS",companyImpairmentReverveBalanceIAS,
                    masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Company Impairment Reserve Balance").get(0));
            agClassificationTab.click();
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        }
        if(!(masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Company Asset Non-Recoverable Impairment Loss").get(0).equalsIgnoreCase("-")||
                masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Company Asset Non-Recoverable Impairment Loss").get(0).equalsIgnoreCase(""))){
            WaitUntilElementIsClickable(CompanyAssetNonRecoverableImpairmentLossIAS);
            sendingValueToWebElement("CompanyAssetNonRecoverableImpairmentLossIAS",CompanyAssetNonRecoverableImpairmentLossIAS,
                    masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Company Asset Non-Recoverable Impairment Loss").get(0));
            agClassificationTab.click();
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        }
        if(!(masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Lease Asset Impairment Reserve Balance").get(0).equalsIgnoreCase("-")||
                masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Lease Asset Impairment Reserve Balance").get(0).equalsIgnoreCase(""))){
            WaitUntilElementIsClickable(LeaseAssetImpairmentReserveBalanceIAS);
            sendingValueToWebElement("LeaseAssetImpairmentReserveBalanceIAS",LeaseAssetImpairmentReserveBalanceIAS,
                    masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Lease Asset Impairment Reserve Balance").get(0));
            agClassificationTab.click();
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        }
        if(!(masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Lease Asset Non-Recoverable Impairment Loss").get(0).equalsIgnoreCase("-")||
                masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Lease Asset Non-Recoverable Impairment Loss").get(0).equalsIgnoreCase(""))){
            WaitUntilElementIsClickable(LeaseAssetNonRecoverableImpairmentLossIAS);
            sendingValueToWebElement("LeaseAssetNonRecoverableImpairmentLossIAS",LeaseAssetNonRecoverableImpairmentLossIAS,
                    masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Lease Asset Non-Recoverable Impairment Loss").get(0));
            agClassificationTab.click();
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        }
        //GAAP
        if(masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Depreciation on Straight Line").get(0).equalsIgnoreCase("Yes")){
            WaitUntilElementIsClickable(DepreciationStraightLineGAAP);
            try {
                waitAndClickOnElement(DepreciationStraightLineGAAP);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        }
    }

    public void agAllFields() {
        log.info("Clicking on All Fields in all Tabs of Activation Group");
        try {
            waitAndClickOnElement(agDefinitionTab);
            waitUntilLoadingSpinnerIsShown("nlaTabChange");
            waitUntilLoadingSpinnerIsGone("nlaTabChange");
            sendingValueToWebElement("agName", activationGroupName, "Update Name");
            waitAndClickOnElement(agDefinitionTab);
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
            waitAndClickOnElement(agUnitTab);
            waitUntilLoadingSpinnerIsShown("nlaTabChange");
            waitUntilLoadingSpinnerIsGone("nlaTabChange");
            waitTillWebElementIsVisible("workflow", unitWorkflow);
            waitAndClickOnElement(unitWorkflow);
            waitAndClickOnElement("closeButton",".q-card #workflow-cancel-btn");
            waitTillWebElementIsVisible("AGContextMenu", contextMenuAG);
            WaitUntilElementIsClickable(contextMenuAG);
            contextMenuAG.click();
            WaitUntilElementIsClickable(bulkModifyUnit);
            waitAndClickOnElement(bulkModifyUnit);
//           waitAndClickOnElement("checkBox",".q-card .q-table .q-tr:nth-child(1) .q-td [role=checkbox]");
//            clickOnSubmitPopup("Submit / Add");
//            WaitUntilElementIsClickable(contextMenuAG);
//            contextMenuAG.click();
//            WaitUntilElementIsClickable(capturedConfiguration);
//            waitAndClickOnElement(capturedConfiguration);
//            waitAndClickOnElement("closeButton",".q-card #close-btn");
//            WaitUntilElementIsClickable(contextMenuAG);
//            contextMenuAG.click();
//            waitAndClickOnElement(workflowContextMenu);
//            waitAndClickOnElement("closeButton",".q-card #workflow-cancel-btn");
        }
        catch (InterruptedException e) {
        }
    }

    public void generateSchedulesAndCancel() {
        log.info("Attempting to click on Generate Schedules button");
        try {
            waitTillWebElementIsVisible("generateSchedulesBtn", generateSchedulesBtn);
            waitAndClickOnElement(generateSchedulesBtn);
            waitTillWebElementIsVisible("schedules cancel button", schedulesCancelBtn);
            log.info("Cancel button is visible, attempting to click");
            waitAndClickOnElement(schedulesCancelBtn);
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
            waitTillWebElementIsVisible("Schedules", schedulesBtn);
            String attribute = schedulesBtn.getAttribute("aria-disabled");
            if (Objects.equals(attribute, "true")) {
                log.info("Schedules are cancelled, button is disabled.");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void verifyJournalsButton() {
        log.info("Verifying that the Journals Button is not Present");
        if(driver.findElements(By.cssSelector(".q-page-container .q-page .q-pt-sm #context-menu-journals")).size()==1){
            Assert.fail("Journals Button is Present for Lessor When IDC Term is not Exercised");
        }
        log.info("Journals Button is Not Present for Lessor When IDC Term is not Exercised");
    }

    public void unitCostObjectValidations() {
        log.info("Validating Unit Cost Object Modification...");
        try {
            waitTillWebElementIsVisible("unitTab", agUnitTab);
            waitAndClickOnElement(agUnitTab);
            waitUntilLoadingSpinnerIsShown("nlaTabChange");
            waitUntilLoadingSpinnerIsGone("nlaTabChange");

            waitTillWebElementIsVisible("unitId", "#q-app .q-page .q-tr:nth-child(2) .q-td:nth-child(2)");
            waitAndClickOnElement("unit", "#q-app .q-page .q-tr:nth-child(2) .q-td:nth-child(2)");
            waitUntilLoadingSpinnerIsShown("nlaPopupLoader");
            waitUntilLoadingSpinnerIsGone("nlaPopupLoader");
            waitTillWebElementIsVisible("costCenterHistory",".q-dialog .q-card #allocations-selector-dialog");
            int historyDropDownSize = clickOnDropDownAndGetDropDownSize("costCenterHistory", driver.findElement(By.cssSelector(".q-dialog .q-card #allocations-selector-dialog")));
            if (historyDropDownSize < 2) {
                Assert.fail("Unit Cost Object Modification not applied successfully");
            }
            //Validate Badges
            waitAndClickOnElement("costCenterHistory", ".q-dialog .q-card #allocations-selector-dialog");
            waitTillWebElementIsVisible("badges",".q-item .column:nth-child(2) .q-badge");
            List<WebElement> badges = driver.findElements(By.cssSelector(".q-item .column:nth-child(2) .q-badge"));

            String firstBadge  = badges.get(0).getText().trim();
            String secondBadge = badges.get(1).getText().trim();

            Assert.assertEquals("First badge did not match expected value 'Latest'", "Latest", firstBadge);
            Assert.assertEquals("Second badge did not match expected value 'History'", "History", secondBadge);
            log.info("Unit Cost Object Modification validation successful");
            waitAndClickOnElement(cancelButton);
        } catch (InterruptedException | IOException e) {
            log.error("Error during Unit Cost Object Modification validation", e);
            throw new RuntimeException("Validation failed due to unexpected exception", e);
        }
    }
}
