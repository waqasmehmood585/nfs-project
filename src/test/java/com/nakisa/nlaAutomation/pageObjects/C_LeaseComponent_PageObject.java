package com.nakisa.nlaAutomation.pageObjects;

import com.nakisa.nlaAutomation.Constant;
import com.nakisa.nlaAutomation.stepDefinitions.MasterHooks;
import io.cucumber.datatable.DataTable;
import lombok.extern.slf4j.Slf4j;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
public class C_LeaseComponent_PageObject extends Common_BasePage_PageObject {

    MasterAgreement_PageObject masterAgreementPageObject = masterAgreement_PageObject.get();
    private String name = null;

    public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #has-identified-asset button:nth-child(1)") WebElement question1_Yes;
    //public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #has-identified-asset button:nth-child(1) .q-focus-helper") WebElement question1_YesColored;
    public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #has-identified-asset button:nth-child(2)") WebElement question1_No;
    public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #is-service-contract button:nth-child(1)") WebElement question2_Yes;
    public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #is-service-contract button:nth-child(2)") WebElement question2_No;
    /*public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #has-identified-asset button:nth-child(1)") WebElement addContract;
    public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #has-identified-asset button:nth-child(1)") WebElement addContract;
    public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #has-identified-asset button:nth-child(1)") WebElement addContract;
    public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #has-identified-asset button:nth-child(1)") WebElement addContract;
    public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #has-identified-asset button:nth-child(1)") WebElement addContract;*/
    public @FindBy(css = "#q-app .q-page #definition-step") WebElement contractDefinitionTab;
    public @FindBy(css = "#q-app .q-page #definition-step .q-ripple") WebElement contractDefinitionTabWait;
    public @FindBy(css = "#q-app .q-page .absolute") WebElement isPageLoaded;
    public @FindBy(css = "#q-app .q-page .absolute #lease-type") WebElement contractLeaseType;
    public @FindBy(css = "#q-app .q-page .absolute #currency-input") WebElement contractCurrency;
    public @FindBy(css = "#q-app .q-page #partners-step") WebElement contractPartnerTab;
    public @FindBy(css = "#q-app .q-page #partners-step .q-ripple") WebElement contractPartnerTabWait;
    public @FindBy(css = "#q-app .q-page .absolute #add-partners-btn") WebElement addContractPartner;
    //    public @FindBy(css = ".q-dialog .q-card .absolute #partner-role-filter-input") WebElement selectContractPartnerRole;
    public @FindBy(css = ".q-dialog .q-card #partner-role-filter-input") WebElement selectContractPartnerRole;
    //    public @FindBy(css = ".q-dialog .q-card .absolute #contract-partners-row-checkbox") WebElement selectContractPartnerOptions;
    public @FindBy(css = ".q-dialog .q-card #contract-partners-row-checkbox") WebElement selectContractPartnerOptions;
    public @FindBy(css = "#q-app .q-page #accounting-step") WebElement contractAccountingTab;
    public @FindBy(css = "#q-app .q-page .absolute #contract-rate-input") WebElement contractRate;
    public @FindBy(css = "#q-app .q-page .absolute #use-ibr-rate") WebElement contractIBRRate;
    public @FindBy(css = "#q-app .q-page .absolute #compounding-frequency") WebElement contractAccountingCompounding;
    public @FindBy(css = "#q-app .q-page .absolute #calendar-type") WebElement contractAccountingCalenderType;
    public @FindBy(css = "#q-app .q-page .absolute #cost-center-input") WebElement contractCostCenter;
    public @FindBy(css = "#q-app .q-page .absolute #profit-center-input") WebElement contractProfitCenter;
    public @FindBy(css = ".q-dialog .q-card #submit-btn") WebElement approveButton;

    //From here starts the lease Componetn additions
    public @FindBy(css = "#q-app .q-page #navigation-expansion #lease-component-revision-nav-add-btn") WebElement addLeaseComponents;
    //    public @FindBy(css = ".q-dialog .q-card .absolute #name-input") WebElement leaseComponentName;
    public @FindBy(css = ".q-dialog .q-card #name-input") WebElement leaseComponentName;
    public @FindBy(css = ".q-page .absolute #lease-component-definition-general-info-form-expansion #fair-market-value-input") WebElement fmvValueDefinition;
    public @FindBy(css = ".q-page .absolute #lease-component-definition-general-info-form-expansion #residual-value-input") WebElement salvageValueDefinition;
    public @FindBy(css = ".q-page .absolute #lease-component-definition-general-info-form-expansion #gross-book-value-input") WebElement gbvUnitDefinition;
    public @FindBy(css = ".q-page .absolute #asset-class") WebElement assetClassValueDefinition;
    public @FindBy(css = ".q-page .absolute #quantity-input") WebElement quantityValueDefinition;
    public @FindBy(css = ".q-page .absolute #unit-of-measure") WebElement unitMeasureValueDefinition;
    public @FindBy(css = ".q-page .q-field #rou-end-date-input-input") WebElement rouEndDateValueDefinition;
    public @FindBy(css = ".q-page .absolute #spreading-frequency") WebElement spreadingFrequencyDefinition;
    public @FindBy(css = ".q-page .q-field #rou-start-date-input-input") WebElement rouStartDateValueDefinition;
    public @FindBy(css = ".q-page .absolute #guaranteed-residual-value-input") WebElement residualValueDefinition;
    public @FindBy(css = ".q-page .absolute #unguaranteed-residual-value-input") WebElement UnguaranteedResidualValueDefinition;
    public @FindBy(css = "#q-app .q-page #terms-conditions-step") WebElement termsConditionsTabLeaseComponent;
    public @FindBy(css = ".q-page .absolute #edit-unit-distribution-btn") WebElement unitDistributionBtnDefination;
    public @FindBy(css = ".q-dialog #add-new-unit-distribution-row") WebElement addNewUnitRowDefination;

    //Terms and conditions
    public @FindBy(css = ".q-page #lease-component-terms-and-conditions-form-expansion .q-btn--actionable#add-tnc-btn") WebElement addTermAndConditionButton;
    public @FindBy(css = ".q-card .dialog-body #term-type") WebElement leaseTermTypeTermsAndConditions;
    public @FindBy(css = ".q-card .dialog-body #non-lease-category") WebElement nonLeaseTermTypeTermsAndConditions;
    public @FindBy(css = ".q-card .dialog-body #expense-category") WebElement nameTermsAndConditions;
    public @FindBy(css = ".q-card .dialog-body #name-input") WebElement IDCNameTermsAndConditions;
    public @FindBy(css = ".q-card .dialog-body #amount-input") WebElement amountTermsAndConditions;
    public @FindBy(css = ".q-card .dialog-body #payment-frequency") WebElement paymentFrequencyTermsAndConditions;
    public @FindBy(css = ".q-card .dialog-body #amount-frequency") WebElement amountFrequencyTermsAndConditions;
    public @FindBy(css = ".q-card .dialog-body #first-payment-date-input-input") WebElement firstPaymentDateTermsAndConditions;
    public @FindBy(css = ".q-card .dialog-body #last-payment-date-input-input") WebElement lastPaymentDateTermsAndConditions;
    public @FindBy(css = ".q-card .dialog-body #months-input") WebElement termMonthsTermsAndConditions;
    public @FindBy(css = ".q-card .dialog-body #days-input") WebElement termDaysTermsAndConditions;
    public @FindBy(css = ".q-card .dialog-body #payment-timing") WebElement paymentTimingTermsAndConditions;
    public @FindBy(css = ".q-card .dialog-body #payment-mode") WebElement paymentModeTermsAndConditions;
    public @FindBy(css = ".q-card .dialog-body #escalating-rent") WebElement EscalatingRent;
    public @FindBy(css = ".q-card .dialog-body #stop-escalating-at-zero") WebElement StopEscalatingRent;
    public @FindBy(css = ".q-card .dialog-body #escalation-fast-forward #escalation-fast-forward-input") WebElement EscalationFastForward;
    public @FindBy(css = ".q-card .dialog-body #escalating-rent-amount-input") WebElement EscalationRentAmount;
    public @FindBy(css = ".q-card .dialog-body #escalating-rent-type") WebElement EscalationRentType;
    public @FindBy(css = ".q-card .dialog-body #frequency-offset #frequency-offset-input") WebElement EscalationFrequencyOffset;
    public @FindBy(css = ".q-card .dialog-body #escalation-frequency #escalation-frequency-input") WebElement EscalationFrequency;
    public @FindBy(css = ".q-dialog .q-field #non-lease-category-value") WebElement addNonLeaseTypeNameTermsAndConditions;
    public @FindBy(css = ".q-dialog #non-lease-category-input") WebElement addPaymentCategoryTermsAndConditions;
    public @FindBy(css = ".q-dialog #expense-category-input") WebElement addLeaseNameTermsAndConditions;
    public @FindBy(css = ".q-page .absolute [role='presentation']") WebElement tickSign;
    public @FindBy(css = "#q-app .q-page #definition-step") WebElement leaseComponentDefinitionTab;
    public @FindBy(css = "#q-app .q-page #carry-over-balance-step") WebElement leaseComponentCarryOverBalanceTab;
    public @FindBy(css = "#q-app .q-page .absolute #lease-component-carry-over-balance-form-expansion") WebElement leaseComponentCarryOverBalanceTabExpansion;
    public @FindBy(css = "#q-app .q-page tbody tr:nth-child(1) td:nth-child(2) #lease-asset-ad-input") WebElement leaseAssetAD;
    public @FindBy(css = ".q-card .dialog-body #month-end") WebElement monthEndTermsAndConditions;
    public @FindBy(css = "#q-app .q-page tbody tr:nth-child(1) td:nth-child(2) #company-asset-gbv-input") WebElement visiblityOfCarryingAmountOfNetInvestment;
    public @FindBy(css = ".q-card .dialog-body #over-ride-first-payment-amount") WebElement overrideFirstPayemtcheckBox;
    public @FindBy(css = ".q-card .dialog-body #over-ride-last-payment-amount") WebElement overrideLastPayemtcheckBox;
    public @FindBy(css = ".q-card .dialog-body #first-payment-amount-input") WebElement overrideFirstPayemtAmount;
    public @FindBy(css = ".q-card .dialog-body #first-spreading-amount-input") WebElement overrideFirstProvisionAmount;
    public @FindBy(css = ".q-card .dialog-body #last-payment-amount-input") WebElement overrideLastPayemtAmount;
    public @FindBy(css = ".q-card .dialog-body #last-spreading-amount-input") WebElement overrideLastProvisionAmount;
    public @FindBy(css = ".q-card .dialog-body #first-special-payment-date-input-input") WebElement overrideFirstPayemtDate;
    public @FindBy(css = ".q-card .dialog-body #last-special-payment-date-input-input") WebElement overrideLastPayemtDate;
    // Carry Over Balances
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
    public @FindBy(css = "#context-menu-btn") WebElement lcContextBtn;
    public @FindBy(css = ".q-menu #lease-component-item:nth-child(1)") WebElement copyLcBtn;
    public @FindBy(css = "#include-terms-and-conditions .q-checkbox__inner") WebElement copyIncludeTerms;
    public @FindBy(css = "#q-app .q-drawer .q-page .q-item #lease-component-selector")WebElement leaseComponentSelector;

    public C_LeaseComponent_PageObject() {
        super();
        log.info("Driver is inside this class: " + this.getClass().getSimpleName());
        PageFactory.initElements(driver, this);
    }

    public void addLeaseComponents(){
        log.info("Creating a new Lease Component");
        try {
            waitAndClickOnElement(addLeaseComponents);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        waitUntilLoadingSpinnerIsShown("nlaAddButtonLoader");
        waitUntilLoadingSpinnerIsGone("nlaAddButtonLoader");
        waitTillWebElementIsVisible("leaseComponentName",leaseComponentName);
        leaseComponentName.click();
        sendingValueToWebElement("leaseComponentName", leaseComponentName,
                getValuesFromExcel("Inception","Lease Component Level","Lease Component Name",0)+" "+ Constant.getTodaysDate());
        clickOnSubmitPopup("Submit / Add");
        waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
        waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        log.info("Lease Component is created Successfully");
    }

    public void fillDefinitionForLeaseComponents() {
        log.info("Going to fill the Definition of Lease Components");
        waitTillWebElementIsVisible("fmvValueDefinition", fmvValueDefinition);
        String leaseType=getValuesFromExcel("Inception","Contract Level","Lease Type",0);
        if (!(leaseType.equalsIgnoreCase("Lease Low Value") || leaseType.equalsIgnoreCase("Lease Short Term") ||
                leaseType.equalsIgnoreCase("05 - Low Value Lease Contract") || leaseType.equalsIgnoreCase("04 - Short-Term Lease Contract"))) {
            WaitUntilElementIsClickable(fmvValueDefinition);
            if(!getValuesFromExcel("Inception","Lease Component Level","FMV / Unit",0).equalsIgnoreCase("0")) {
                fmvValueDefinition.click();
                sendingValueToWebElement("fmvValueDefinition", fmvValueDefinition,
                        getValuesFromExcel("Inception", "Lease Component Level", "FMV / Unit", 0));
                try {
                    waitAndClickOnElement(leaseComponentDefinitionTab);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    leaseComponentDefinitionTab.click();
                }
                waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
            }
            if(!getValuesFromExcel("Inception","Contract Level","Principal Position",0).equalsIgnoreCase("Lessor")) {
                if(!getValuesFromExcel("Inception", "Lease Component Level", "Salvage Value / Unit", 0).equalsIgnoreCase("0")) {
                    WaitUntilElementIsClickable(salvageValueDefinition);
                    checkRequiredField("salvageUnitValue", ".q-page .absolute #lease-component-definition-general-info-form-expansion #residual-value-input");
                    salvageValueDefinition.click();
                    sendingValueToWebElement("salvageValueDefinition", salvageValueDefinition,
                            getValuesFromExcel("Inception", "Lease Component Level", "Salvage Value / Unit", 0));
                    leaseComponentDefinitionTab.click();
                    waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                    waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
                }
            }
        }
        if(driver.findElements(By.cssSelector(".q-page .absolute #lease-component-definition-general-info-form-expansion #gross-book-value")).size() == 1) {
            waitTillWebElementIsVisible("gbvUnitDefinition", gbvUnitDefinition);
            WaitUntilElementIsClickable(gbvUnitDefinition);
            gbvUnitDefinition.click();
            sendingValueToWebElement("gbvUnitDefinition", gbvUnitDefinition,
                    getValuesFromExcel("Inception","Lease Component Level","GBV / Unit",0));
            leaseComponentDefinitionTab.click();
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        }
        if(!(getValuesFromExcel("Inception","Lease Component Level","Asset Class",0).equalsIgnoreCase("-")||
                getValuesFromExcel("Inception","Lease Component Level","Asset Class",0).equalsIgnoreCase(""))) {
            try {
                String assetClass = getValuesFromExcel("Inception","Lease Component Level","Asset Class",0);
                WaitUntilElementIsClickable(assetClassValueDefinition);
                clickOnDropDownToTypeAndSelectValue("assetClassValueDefinition", assetClassValueDefinition, assetClass);
            } catch (InterruptedException | IOException exception) {
                exception.printStackTrace();
            }
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        }
        //Master-0 Quantity
//        quantityValueDefinition.click();
//        sendingValueToWebElement("quantityValueDefinition", quantityValueDefinition,
//                getValuesFromExcel("Inception","Lease Component Level","Quantity",0));
//        leaseComponentDefinitionTab.click();
//        waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
//        waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        try {
            String unitOfMeasure = getValuesFromExcel("Inception","Lease Component Level","Unit of Measure",0);
            clickOnDropDownToTypeAndSelectValue("unitMeasureValueDefinition", unitMeasureValueDefinition, unitOfMeasure);
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
            if(!(getValuesFromExcel("Inception","Contract Level","Provisioning",0).equalsIgnoreCase("No") ||
                    getValuesFromExcel("Inception","Lease Component Level","Provisioning Frequency",0).equalsIgnoreCase("Straight-Line"))) {
                clickOnDropDownAndSelectValue("spreadingFrequencyDefinition", spreadingFrequencyDefinition,
                        getValuesFromExcel("Inception","Lease Component Level","Provisioning Frequency",0));
                waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
            }
        } catch (InterruptedException | IOException exception) {
            exception.printStackTrace();
        }
        rouStartDateValueDefinition.click();
        sendingValueToWebElement("rouStartDateValueDefinition", rouStartDateValueDefinition,
                getValuesFromExcel("Inception","Lease Component Level","ROU Start Date",0));
        rouEndDateValueDefinition.click();
        waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
        waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        sendingValueToWebElement("rouEndDateValueDefinition", rouEndDateValueDefinition,
                getValuesFromExcel("Inception","Lease Component Level","ROU End Date",0));
        leaseComponentDefinitionTab.click();
        waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
        waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        //Master-1 Quantity
        unitDistributionBtnDefination.click();
        waitUntilLoadingSpinnerIsShown("nlaDropDownPopUp");
        waitTillWebElementIsVisible("activationGroupNameField", ".q-dialog tbody tr input[aria-label='Activation Group Name *']");
        String[] AGNameLength=getValuesFromExcel("Inception","Lease Component Level","Activation Group Name",0).split(",");
        String[] quantityUnitsLength=getValuesFromExcel("Inception","Lease Component Level","Quantity",0).split(",");
        for(int agNameCount=1;agNameCount<=AGNameLength.length;agNameCount++) {
            try {
                if(agNameCount!=1) {
                    waitAndClickOnElement(addNewUnitRowDefination);
                }
                WebElement activationGroupName=driver.findElement(By.cssSelector(".q-dialog tbody tr:nth-child("+(agNameCount+1)+") input[aria-label='Activation Group Name *']"));
                waitForClickablility(".q-dialog tbody tr:nth-child("+(agNameCount+1)+") input[aria-label='Activation Group Name *']");
                sendingValueToWebElement("activationGroupName", activationGroupName, AGNameLength[agNameCount-1]+" "+ Constant.getTodaysDate());
//				clickOnDropDownToTypeAndSelectValue("activationGroupName", activationGroupName, AGNameLength[agNameCount-1]);

                WebElement quantityUnits=driver.findElement(By.cssSelector(".q-dialog tbody tr:nth-child("+(agNameCount+1)+") input[aria-label='Number of Units *']"));
                waitForClickablility(".q-dialog .q-card tr:nth-child("+(agNameCount+1)+") input[aria-label='Number of Units *']");
                sendingValueToWebElement("quantityUnits", quantityUnits, quantityUnitsLength[agNameCount-1]);
//				clickOnDropDownToTypeAndSelectValue("quantityUnits", quantityUnits, quantityUnitsLength[agNameCount-1]);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        clickOnSubmitPopup("Submit / Add");
        waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
        waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        //Storing the Lease Component ID
        String idPath = driver.findElement(By.cssSelector("#q-app .q-page .q-item.col-auto:nth-child(1) .q-item__label:nth-child(2)")).getText();
        MasterHooks.searchLCID.set(idPath);
        residualValueDefinition.click();
        sendingValueToWebElement("residualValueDefinition", residualValueDefinition,
                getValuesFromExcel("Inception","Lease Component Level","Guaranteed Residual Value",0));
       // leaseComponentDefinitionTab.click();
        waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
        waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        if(driver.findElements(By.cssSelector(".q-page .absolute #unguaranteed-residual-value")).size() == 1) {
            waitTillWebElementIsVisible("UnguaranteedResidualValueDefinition",UnguaranteedResidualValueDefinition);
            UnguaranteedResidualValueDefinition.click();
            sendingValueToWebElement("UnguaranteedResidualValueDefinition", UnguaranteedResidualValueDefinition,
                    getValuesFromExcel("Inception","Lease Component Level","Unguaranteed Residual Value",0));
            leaseComponentDefinitionTab.click();
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        }
        log.info("Completed filling the Definition of Lease Components");
    }

    public void fillTermAndConditionInfo(String level) throws InterruptedException {
        log.info("Going to fill the Terms & Conditions of Lease Components");
        String termSubLevel = level.equalsIgnoreCase("Inception") ? "Lease Component Level" : "eventdata";
        int totalNumberOfTerms = masterAgreementPageObject.getInputValues().get(level).get(termSubLevel).get("Term Category").size();
        for (int termCounter = 0; termCounter < totalNumberOfTerms; termCounter++) {
            if (termCounter == 0) {
                waitAndClickOnElement(termsConditionsTabLeaseComponent);
                waitUntilLoadingSpinnerIsShown("nlaTabChange");
                waitUntilLoadingSpinnerIsGone("nlaTabChange");
                if(MasterHooks.configurationProperties.get().getRunAutomationOnNCPBuild()) {
                    handleWait(2000);
                }
            }
            waitTillWebElementIsVisible("addTermAndConditionButton", addTermAndConditionButton);
            WaitUntilElementIsClickable(addTermAndConditionButton);
            waitAndClickOnElement(addTermAndConditionButton);
            String termCategoryValue = getValuesFromExcel(level,termSubLevel,"Term Category",termCounter);
            waitTillWebElementIsVisible("Term Category",".q-menu .q-list");
            String termCategoryCSS = null;
            if (termCategoryValue.equalsIgnoreCase("Lease")) {
                termCategoryCSS = ".q-menu .q-list #add-lease-term-btn";
            } else if (termCategoryValue.equalsIgnoreCase("Non Lease")) {
                termCategoryCSS = ".q-menu .q-list #add-non-lease-term-btn";
            }
            waitAndClickOnElement(termCategoryValue, termCategoryCSS);
            waitUntilLoadingSpinnerIsShown("nlaAddButtonLoader");
            waitUntilLoadingSpinnerIsGone("nlaAddButtonLoader");
            waitTillWebElementIsVisible("amountTermsAndConditions", amountTermsAndConditions);
            name = getValuesFromExcel(level,termSubLevel,"Name",termCounter);
            try {
                if (termCategoryValue.equalsIgnoreCase("Lease")) {
                    clickOnDropDownAndSelectValue("leaseTermTypeTermsAndConditions", leaseTermTypeTermsAndConditions,
                            getValuesFromExcel(level,termSubLevel,"Type",termCounter));
                    if (name.contains(" ")) {
                        name = name.substring(0, name.indexOf(" "));
                    } if (name.contains("IDC")) {
                        waitAndClickOnElement(IDCNameTermsAndConditions);
                        sendingValueToWebElement("NameofTerm", IDCNameTermsAndConditions, name);

                    } else {
                        clickOnDropDownToTypeAndSelectValue("nameTermsAndConditions", nameTermsAndConditions, name);
                    }
                    //clickOnDropDownToTypeAndSelectValue("nameTermsAndConditions", nameTermsAndConditions, name);
                } else if (termCategoryValue.equalsIgnoreCase("Non Lease")) {
                    clickOnDropDownToTypeAndSelectValue("nonLeaseTermTypeTermsAndConditions", nonLeaseTermTypeTermsAndConditions,
                            getValuesFromExcel(level,termSubLevel,"Type",termCounter));
                }
                handleWait(800);
            } catch (IOException e) {
                e.printStackTrace();
            }
            waitAndClickOnElement(amountTermsAndConditions);
            amountTermsAndConditions.sendKeys(Keys.chord(Keys.CONTROL, "a"), getValuesFromExcel(level,termSubLevel,"Lease Amount / Unit",termCounter));
//            sendingValueToWebElement("amountTermsAndConditions",amountTermsAndConditions,
//                    getValuesFromExcel(termLevel,termSubLevel,"Lease Amount / Unit",termCounter));
            if (!name.equalsIgnoreCase("Expected")) {
                if (!name.equalsIgnoreCase("IDC")){
                    try {
                        //Toggle Button
                        waitTillWebElementIsVisible("advanceMode", ".q-card .dialog-body #basic-mode .q-toggle__inner");
                        WaitUntilElementIsClickable(driver.findElement(By.cssSelector(".q-card .dialog-body #basic-mode .q-toggle__inner")));
                        waitAndClickOnElement("mode", ".q-card .dialog-body #basic-mode .q-toggle__inner");
                        if (!(name.equalsIgnoreCase("Purchase") || name.equalsIgnoreCase("Termination") || name.equalsIgnoreCase("Cash"))) {
                            clickOnDropDownAndSelectValue("paymentFrequencyTermsAndConditions", paymentFrequencyTermsAndConditions,
                                    getValuesFromExcel(level,termSubLevel,"Payment Frequency",termCounter));
                            if(!getValuesFromExcel(level,termSubLevel,"Payment Frequency",termCounter).equalsIgnoreCase("One Time")) {
                                clickOnDropDownAndSelectValue("amountFrequency", amountFrequencyTermsAndConditions,
                                        getValuesFromExcel(level,termSubLevel,"Amount Frequency",termCounter));
                            }
                        }
                        if(name.equalsIgnoreCase("Cash")) {
                            waitAndClickOnElement(firstPaymentDateTermsAndConditions);
                            clearFieldByBackSpace(firstPaymentDateTermsAndConditions);
                            sendingValueToWebElement("firstPaymentDateTermsAndConditions", firstPaymentDateTermsAndConditions,
                                    getValuesFromExcel(level, termSubLevel, "First Payment Date", termCounter));
                        }else{
                            waitAndClickOnElement(firstPaymentDateTermsAndConditions);
                            sendingValueToWebElement("firstPaymentDateTermsAndConditions", firstPaymentDateTermsAndConditions,
                                    getValuesFromExcel(level, termSubLevel, "First Payment Date", termCounter));
                        }
                        waitAndClickOnElement("popUpHeader", ".q-dialog  .q-card .q-card__section .text-h6.col");
                        if (!(name.equalsIgnoreCase("Purchase") || name.equalsIgnoreCase("Termination") || name.equalsIgnoreCase("Cash"))) {
                            if(!getValuesFromExcel(level,termSubLevel,"First Payment Date",termCounter)
                                    .equalsIgnoreCase(getValuesFromExcel(level,termSubLevel,"Last Payment Date",termCounter))) {
                                sendingValueToWebElement("lastPaymentDateTermsAndConditions", lastPaymentDateTermsAndConditions,
                                        getValuesFromExcel(level,termSubLevel,"Last Payment Date",termCounter));
                                waitAndClickOnElement("popUpHeader", ".q-dialog  .q-card .q-card__section .text-h6.col");
                            }
                            if(getValuesFromExcel(level,termSubLevel,"Month End",termCounter).equalsIgnoreCase("Yes")){
                                monthEndTermsAndConditions.click();
                            }
                          clickOnDropDownAndSelectValue("paymentM0de", paymentModeTermsAndConditions,
                                        getValuesFromExcel(level,termSubLevel,"Payment Mode",termCounter));

                            if(getValuesFromExcel(level,termSubLevel,"Override First Payment",termCounter).equalsIgnoreCase("Yes")) {
                                waitAndClickOnElement(overrideFirstPayemtcheckBox);
                                String overrideFirstPayment=getValuesFromExcel(level,termSubLevel,"Override First Payment Amount",termCounter);
                                if(!(overrideFirstPayment.equalsIgnoreCase("")||overrideFirstPayment.equalsIgnoreCase("-"))) {
                                    waitTillWebElementIsVisible("overrideFirstPayemtAmount", overrideFirstPayemtAmount);
                                    sendingValueToWebElement("overrideFirstPayemtAmount", overrideFirstPayemtAmount,overrideFirstPayment);
                                }
                                String overrideFirstDate=getValuesFromExcel(level,termSubLevel,"Override First Payment Date",termCounter);
                                if(!(overrideFirstDate.equalsIgnoreCase("")||overrideFirstDate.equalsIgnoreCase("-"))) {
                                    waitTillWebElementIsVisible("overrideFirstPayemtDate", overrideFirstPayemtDate);
                                    WaitUntilElementIsClickable(overrideFirstPayemtDate);
                                    sendingValueToWebElement("overrideFirstPayemtDate", overrideFirstPayemtDate,
                                            getValuesFromExcel(level,termSubLevel,"Override First Payment Date",termCounter));
                                }
                                if((getValuesFromExcel("Inception","Contract Level","Provisioning",0).equalsIgnoreCase("Yes")) &&
                                       !(getValuesFromExcel(level,termSubLevel,"Payment Frequency",termCounter).equalsIgnoreCase("One Time"))) {
                                    String overrideFirstProvisnAmount = getValuesFromExcel(level,termSubLevel,"Override First Provision Payment",termCounter);
                                    if (!(overrideFirstProvisnAmount.equalsIgnoreCase("") || overrideFirstProvisnAmount.equalsIgnoreCase("-"))) {
                                        waitTillWebElementIsVisible("overrideFirstProvisonAmount", overrideFirstProvisionAmount);
                                        WaitUntilElementIsClickable(overrideFirstProvisionAmount);
                                        sendingValueToWebElement("overrideFirstProvisionAmount", overrideFirstProvisionAmount,
                                                getValuesFromExcel(level,termSubLevel,"Override First Provision Payment",termCounter));
                                    }
                                }
                            }
                            if(getValuesFromExcel(level,termSubLevel,"Override Last Payment",termCounter).equalsIgnoreCase("Yes")) {
                                waitAndClickOnElement(overrideLastPayemtcheckBox);
                                String overrideLastPayment=getValuesFromExcel(level,termSubLevel,"Override Last Payment Amount",termCounter);
                                if(!(overrideLastPayment.equalsIgnoreCase("")||overrideLastPayment.equalsIgnoreCase("-"))) {
                                    waitTillWebElementIsVisible("overrideLastPayemtAmount", overrideLastPayemtAmount);
                                    sendingValueToWebElement("overrideLastPayemtAmount", overrideLastPayemtAmount,overrideLastPayment);
                                }
                                String overrideLastDate=getValuesFromExcel(level,termSubLevel,"Override Last Payment Date",termCounter);
                                if(!(overrideLastDate.equalsIgnoreCase("")||overrideLastDate.equalsIgnoreCase("-"))) {
                                    waitTillWebElementIsVisible("overrideFirstPayemtDate", overrideLastPayemtDate);
                                    WaitUntilElementIsClickable(overrideLastPayemtDate);
                                    sendingValueToWebElement("overrideFirstPayemtDate", overrideLastPayemtDate,
                                            getValuesFromExcel(level,termSubLevel,"Override Last Payment Date",termCounter));
                                }
                                if((getValuesFromExcel("Inception","Contract Level","Provisioning",0).equalsIgnoreCase("Yes")) &&
                                        !(getValuesFromExcel(level,termSubLevel,"Payment Frequency",termCounter).equalsIgnoreCase("One Time"))) {
                                    String overrideLastProvisnAmount = getValuesFromExcel(level,termSubLevel,"Override Last Provision Payment",termCounter);
                                    if (!(overrideLastProvisnAmount.equalsIgnoreCase("") || overrideLastProvisnAmount.equalsIgnoreCase("-"))) {
                                        waitTillWebElementIsVisible("overrideLastProvisonAmount", overrideLastProvisionAmount);
                                        WaitUntilElementIsClickable(overrideFirstProvisionAmount);
                                        sendingValueToWebElement("overrideLastProvisionAmount", overrideLastProvisionAmount,
                                                getValuesFromExcel(level,termSubLevel,"Override Last Provision Payment",termCounter));
                                    }
                                }
                            }

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    clickOnDropDownAndSelectValue("paymentTimingTermsAndConditions", paymentTimingTermsAndConditions,
                            getValuesFromExcel(level,termSubLevel,"Payment Timing",termCounter));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String escalatingRent= getValuesFromExcel(level,termSubLevel,"Escalating Rent",termCounter);
                String stopEscalatingRent= getValuesFromExcel(level,termSubLevel,"Stop Escalating At Zero",termCounter);
                if (escalatingRent.equalsIgnoreCase("yes")){
                    waitAndClickOnElement(EscalatingRent);
                    if (stopEscalatingRent.equalsIgnoreCase("No")){
                        waitAndClickOnElement(StopEscalatingRent);
                    }
                    WaitUntilElementIsClickable(EscalationFastForward);
                    sendingValueToWebElement("escalationFastForward", EscalationFastForward,
                            getValuesFromExcel(level,termSubLevel,"Escalation Fast Forward",termCounter));
                    sendingValueToWebElement("escalationRentAmount", EscalationRentAmount,
                            getValuesFromExcel(level,termSubLevel,"Escalating Rent Amount / Percentage",termCounter));
                    try {
                        clickOnDropDownAndSelectValue("escalationRentType", EscalationRentType,
                                getValuesFromExcel(level,termSubLevel,"Escalating Rent Type",termCounter));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    sendingValueToWebElement("escalationFrequencyOffset", EscalationFrequencyOffset,
                            getValuesFromExcel(level,termSubLevel,"Frequency Offset",termCounter));
                    sendingValueToWebElement("escalationRentAmount", EscalationFrequency,
                            getValuesFromExcel(level,termSubLevel,"Escalation Frequency",termCounter));
                }

            }
            if (!name.equalsIgnoreCase("IDC")) {
                if (getValuesFromExcel("Inception", "Contract Level", "Generate Vendor Invoice", 0).equalsIgnoreCase("Yes")) {
                    //Excel
                    String partnerNamesFromExcel = getValuesFromExcel(level, termSubLevel, "Partner Name", termCounter);
                    String percentagesFromExcel = getValuesFromExcel(level, termSubLevel, "Partner Percentage", termCounter);
                    String[] partnerNameLengthFromExcel = partnerNamesFromExcel.split(",");
                    String[] percentagesLength = percentagesFromExcel.split(",");
                    for (String value : partnerNameLengthFromExcel) {
                        //Application
                        List<WebElement> partnerNamesFromApplication = driver.findElements(By.cssSelector(".q-card .dialog-body .q-table .q-tr .q-td:nth-child(1)"));
                        List<WebElement> partnerPercentagesFromApplication = driver.findElements(By.cssSelector(".q-card .dialog-body .q-table #percentage-undefined-input"));
                        int partnerNameSizeApplication = partnerNamesFromApplication.size() - 1;
                        for (int partner = 0; partner < partnerNameSizeApplication; partner++) {
                            if (partnerNamesFromApplication.get(partner + 1).getText().equalsIgnoreCase(partnerNameLengthFromExcel[partner])) {
                                partnerPercentagesFromApplication.get(partner).click();
                                partnerPercentagesFromApplication.get(partner).sendKeys(Keys.chord(Keys.CONTROL, "a"),percentagesLength[partner]);
      //                          sendingValueToWebElement("partnerPercentages", partnerPercentagesFromApplication.get(partner), percentagesLength[partner]);
                            }
                        }
                    }


//                    if(!(getValuesFromExcel("Inception","Lease Component Level","Percentage - Partner 1",0).equalsIgnoreCase("-")||
//                            getValuesFromExcel("Inception","Lease Component Level","Percentage - Partner 1",0).equalsIgnoreCase(""))){
//                        WaitUntilElementIsClickable(companyAssetGbvIAS);
//                        sendingValueToWebElement("companyAssetGbvIAS",companyAssetGbvIAS,
//                                getValuesFromExcel("Inception","Lease Component Level","Company Asset GBV",0));
//                        leaseComponentCarryOverBalanceTab.click();
//                        waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
//                        waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");

                }
            }
            clickOnSubmitPopup("Submit / Add");
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
            log.info("Completed filling the Terms and Conditions of Lease Components");
        }
    }

    public void fillCarryOverBalance() {
        log.info("Clicking on Carry-Over Balance Tab");
        waitTillWebElementIsVisible("leaseComponentCarryOverBalanceTab", leaseComponentCarryOverBalanceTab);
        leaseComponentCarryOverBalanceTab.click();
        waitUntilLoadingSpinnerIsShown("nlaTabChange");
        waitUntilLoadingSpinnerIsGone("nlaTabChange");
        waitTillWebElementIsVisible("leaseComponentCarryOverBalanceTabExpansion",leaseComponentCarryOverBalanceTabExpansion);
        log.info("On the Carry-Over Balance Tab");
    }

    public void fillCarrOverBalanceValues() {
//        String []keys;
//        if(getValuesFromExcel("Inception","Contract Level","Principal Position",0).equalsIgnoreCase("Lessor")) {
//            keys = new String[]{"Accumulated Depreciation", "Accrued Rent Clearing / Deferred Lease Clearing", "Carrying Amount of Net Investment"};
//            waitTillWebElementIsVisible("visiblityOfCarryingAmountOfNetInvestment",visiblityOfCarryingAmountOfNetInvestment);
//        } else {
//            keys = new String[]{"Company Asset GBV", "Company Asset AD", "Company Current Year AD", "Lease Asset GBV", "Lease Asset AD", "Lease Current Year AD"};
//            waitTillWebElementIsVisible("Lease Asset AD", leaseAssetAD);
//        }
        waitTillWebElementIsVisible("leaseComponentCarryOverBalanceTabExpansion",leaseComponentCarryOverBalanceTabExpansion);
//        for(int j = 2; j<=3; j++){
//            String cssPath = "#q-app .q-page tbody tr:nth-child(1) td:nth-child("+j+") input";
//            List<WebElement> carryOverBalanceElements = driver.findElements(By.cssSelector(cssPath));
//            for(int i = 0; i< keys.length; i++){
//                String value = "";
//                if(j==2){
//                    value = getValuesFromExcel("Inception","Lease Component Level",keys[i],0);
//                }else {
//                    value = getValuesFromExcel("Inception","Lease Component Level",keys[i],1);
//                }
//                if(!(value.contains("-")|| value.isEmpty()|| value.equalsIgnoreCase(" "))){
//                    waitTillWebElementIsVisible(keys[i],carryOverBalanceElements.get(i));
//                    try{
//                        waitAndClickOnElement(carryOverBalanceElements.get(i));
//                    }catch (InterruptedException e){
//                        e.printStackTrace();
//                    }
//                    sendingValueToWebElement(keys[i],carryOverBalanceElements.get(i),value);
//                }
//            }
//        }
        try {
            if(getValuesFromExcel("Inception","Contract Level","Principal Position",0).equalsIgnoreCase("Lessee")) {
                if(!(getValuesFromExcel("Inception","Lease Component Level","Company Asset GBV",0).equalsIgnoreCase("-")||
                        getValuesFromExcel("Inception","Lease Component Level","Company Asset GBV",0).equalsIgnoreCase(""))){
                    WaitUntilElementIsClickable(companyAssetGbvIAS);
                    sendingValueToWebElement("companyAssetGbvIAS",companyAssetGbvIAS,
                            getValuesFromExcel("Inception","Lease Component Level","Company Asset GBV",0));
                    leaseComponentCarryOverBalanceTab.click();
                    waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                    waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
                }
                if(!(getValuesFromExcel("Inception","Lease Component Level","Company Asset AD",0).equalsIgnoreCase("-")||
                        getValuesFromExcel("Inception","Lease Component Level","Company Asset AD",0).equalsIgnoreCase(""))){
                    WaitUntilElementIsClickable(companyAssetAdIAS);
                    sendingValueToWebElement("companyAssetAdIAS",companyAssetAdIAS,
                            getValuesFromExcel("Inception","Lease Component Level","Company Asset AD",0));
                    leaseComponentCarryOverBalanceTab.click();
                    waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                    waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
                }
                if(!(getValuesFromExcel("Inception","Lease Component Level","Company Current Year AD",0).equalsIgnoreCase("-")||
                        getValuesFromExcel("Inception","Lease Component Level","Company Current Year AD",0).equalsIgnoreCase(""))){
                    WaitUntilElementIsClickable(companyCurrentYearAdIAS);
                    sendingValueToWebElement("companyCurrentYearAdIAS",companyCurrentYearAdIAS,
                            getValuesFromExcel("Inception","Lease Component Level","Company Current Year AD",0));
                    leaseComponentCarryOverBalanceTab.click();
                    waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                    waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
                }
                if(!(getValuesFromExcel("Inception","Lease Component Level","Lease Asset GBV",0).equalsIgnoreCase("-")||
                        getValuesFromExcel("Inception","Lease Component Level","Lease Asset GBV",0).equalsIgnoreCase(""))){
                    WaitUntilElementIsClickable(leaseAssetGbvIAS);
                    sendingValueToWebElement("leaseAssetGbvIAS",leaseAssetGbvIAS,
                            getValuesFromExcel("Inception","Lease Component Level","Lease Asset GBV",0));
                    leaseComponentCarryOverBalanceTab.click();
                    waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                    waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
                }
                if(!(getValuesFromExcel("Inception","Lease Component Level","Lease Asset AD",0).equalsIgnoreCase("-")||
                        getValuesFromExcel("Inception","Lease Component Level","Lease Asset AD",0).equalsIgnoreCase(""))){
                    WaitUntilElementIsClickable(leaseAssetAdIAS);
                    sendingValueToWebElement("leaseAssetAdIAS",leaseAssetAdIAS,
                            getValuesFromExcel("Inception","Lease Component Level","Lease Asset AD",0));
                    leaseComponentCarryOverBalanceTab.click();
                    waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                    waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
                }
                if(!(getValuesFromExcel("Inception","Lease Component Level","Lease Current Year AD",0).equalsIgnoreCase("-")||
                        getValuesFromExcel("Inception","Lease Component Level","Lease Current Year AD",0).equalsIgnoreCase(""))){
                    WaitUntilElementIsClickable(leaseCurrentYearAdIAS);
                    sendingValueToWebElement("leaseCurrentYearAdIAS",leaseCurrentYearAdIAS,
                            getValuesFromExcel("Inception","Lease Component Level","Lease Current Year AD",0));
                    leaseComponentCarryOverBalanceTab.click();
                    waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                    waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
                }
                //Impairment Liability Balance IAS
                if(!(getValuesFromExcel("Inception","Lease Component Level","Accrued Interest Expense",0).equalsIgnoreCase("-")||
                        getValuesFromExcel("Inception","Lease Component Level","Accrued Interest Expense",0).equalsIgnoreCase(""))) {
                    WaitUntilElementIsClickable(accruedInterestExpenseIAS);
                    sendingValueToWebElement("accruedInterestExpense", accruedInterestExpenseIAS,
                            getValuesFromExcel("Inception","Lease Component Level","Accrued Interest Expense",0));
                    leaseComponentCarryOverBalanceTab.click();
                    waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                    waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
                }
                //GAAP
                if(!(getValuesFromExcel("Inception","Lease Component Level","Company Asset GBV",1).equalsIgnoreCase("-")||
                        getValuesFromExcel("Inception","Lease Component Level","Company Asset GBV",1).equalsIgnoreCase(""))){
                    WaitUntilElementIsClickable(companyAssetGbvGAAP);
                    sendingValueToWebElement("companyAssetGbvGAAP",companyAssetGbvGAAP,
                            getValuesFromExcel("Inception","Lease Component Level","Company Asset GBV",1));
                    leaseComponentCarryOverBalanceTab.click();
                    waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                    waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
                }
                if(!(getValuesFromExcel("Inception","Lease Component Level","Company Asset AD",1).equalsIgnoreCase("-")||
                        getValuesFromExcel("Inception","Lease Component Level","Company Asset AD",1).equalsIgnoreCase(""))){
                    WaitUntilElementIsClickable(companyAssetAdGAAP);
                    sendingValueToWebElement("companyAssetAdIAS",companyAssetAdGAAP,
                            getValuesFromExcel("Inception","Lease Component Level","Company Asset AD",1));
                    leaseComponentCarryOverBalanceTab.click();
                    waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                    waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
                }
                if(!(getValuesFromExcel("Inception","Lease Component Level","Company Current Year AD",1).equalsIgnoreCase("-")||
                        getValuesFromExcel("Inception","Lease Component Level","Company Current Year AD",1).equalsIgnoreCase(""))){
                    WaitUntilElementIsClickable(companyCurrentYearAdGAAP);
                    sendingValueToWebElement("companyCurrentYearAdIAS",companyCurrentYearAdGAAP,
                            getValuesFromExcel("Inception","Lease Component Level","Company Current Year AD",1));
                    leaseComponentCarryOverBalanceTab.click();
                    waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                    waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
                }
                if(!(getValuesFromExcel("Inception","Lease Component Level","Lease Asset GBV",1).equalsIgnoreCase("-")||
                        getValuesFromExcel("Inception","Lease Component Level","Lease Asset GBV",1).equalsIgnoreCase(""))){
                    WaitUntilElementIsClickable(leaseAssetGbvGAAP);
                    sendingValueToWebElement("leaseAssetGbvGAAP",leaseAssetGbvGAAP,
                            getValuesFromExcel("Inception","Lease Component Level","Lease Asset GBV",1));
                    leaseComponentCarryOverBalanceTab.click();
                    waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                    waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
                }
                if(!(getValuesFromExcel("Inception","Lease Component Level","Lease Asset AD",1).equalsIgnoreCase("-")||
                        getValuesFromExcel("Inception","Lease Component Level","Lease Asset AD",1).equalsIgnoreCase(""))){
                    WaitUntilElementIsClickable(leaseAssetAdGAAP);
                    sendingValueToWebElement("leaseAssetAdGAAP",leaseAssetAdGAAP,
                            getValuesFromExcel("Inception","Lease Component Level","Lease Asset AD",1));
                    leaseComponentCarryOverBalanceTab.click();
                    waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                    waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
                }
                if(!(getValuesFromExcel("Inception","Lease Component Level","Lease Current Year AD",1).equalsIgnoreCase("-")||
                        getValuesFromExcel("Inception","Lease Component Level","Lease Current Year AD",1).equalsIgnoreCase(""))){
                    WaitUntilElementIsClickable(leaseCurrentYearAdGAAP);
                    sendingValueToWebElement("leaseCurrentYearAdGAAP",leaseCurrentYearAdGAAP,
                            getValuesFromExcel("Inception","Lease Component Level","Lease Current Year AD",1));
                    leaseComponentCarryOverBalanceTab.click();
                    waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                    waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
                }
                //Impairment Liability Balance GAAP
                if(!(getValuesFromExcel("Inception","Lease Component Level","Accrued Interest Expense",1).equalsIgnoreCase("-")||
                        getValuesFromExcel("Inception","Lease Component Level","Accrued Interest Expense",1).equalsIgnoreCase(""))) {
                    WaitUntilElementIsClickable(accruedInterestExpenseGAAP);
                    sendingValueToWebElement("accruedInterestExpense", accruedInterestExpenseGAAP,
                            getValuesFromExcel("Inception","Lease Component Level","Accrued Interest Expense",1));
                    leaseComponentCarryOverBalanceTab.click();
                    waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                    waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
                }
                //Impairment Carry Over Balance IAS
                if(!(getValuesFromExcel("Inception","Lease Component Level","Company Impairment Reserve Balance",0).equalsIgnoreCase("-")||
                        getValuesFromExcel("Inception","Lease Component Level","Company Impairment Reserve Balance",0).equalsIgnoreCase(""))){
                    WaitUntilElementIsClickable(companyImpairmentReverveBalanceIAS);
                    sendingValueToWebElement("companyImpairmentReverveBalanceIAS",companyImpairmentReverveBalanceIAS,
                            getValuesFromExcel("Inception","Lease Component Level","Company Impairment Reserve Balance",0));
                    leaseComponentCarryOverBalanceTab.click();
                    waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                    waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
                }
                if(!(getValuesFromExcel("Inception","Lease Component Level","Company Asset Non-Recoverable Impairment Loss",0).equalsIgnoreCase("-")||
                        getValuesFromExcel("Inception","Lease Component Level","Company Asset Non-Recoverable Impairment Loss",0).equalsIgnoreCase(""))){
                    WaitUntilElementIsClickable(CompanyAssetNonRecoverableImpairmentLossIAS);
                    sendingValueToWebElement("CompanyAssetNonRecoverableImpairmentLossIAS",CompanyAssetNonRecoverableImpairmentLossIAS,
                            getValuesFromExcel("Inception","Lease Component Level","Company Asset Non-Recoverable Impairment Loss",0));
                    leaseComponentCarryOverBalanceTab.click();
                    waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                    waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
                }
                if(!(getValuesFromExcel("Inception","Lease Component Level","Lease Asset Impairment Reserve Balance",0).equalsIgnoreCase("-")||
                        getValuesFromExcel("Inception","Lease Component Level","Lease Asset Impairment Reserve Balance",0).equalsIgnoreCase(""))){
                    WaitUntilElementIsClickable(LeaseAssetImpairmentReserveBalanceIAS);
                    sendingValueToWebElement("LeaseAssetImpairmentReserveBalanceIAS",LeaseAssetImpairmentReserveBalanceIAS,
                            getValuesFromExcel("Inception","Lease Component Level","Lease Asset Impairment Reserve Balance",0));
                    leaseComponentCarryOverBalanceTab.click();
                    waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                    waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
                }
                if(!(getValuesFromExcel("Inception","Lease Component Level","Lease Asset Non-Recoverable Impairment Loss",0).equalsIgnoreCase("-")||
                        getValuesFromExcel("Inception","Lease Component Level","Lease Asset Non-Recoverable Impairment Loss",0).equalsIgnoreCase(""))){
                    WaitUntilElementIsClickable(LeaseAssetNonRecoverableImpairmentLossIAS);
                    sendingValueToWebElement("LeaseAssetNonRecoverableImpairmentLossIAS",LeaseAssetNonRecoverableImpairmentLossIAS,
                            getValuesFromExcel("Inception","Lease Component Level","Lease Asset Non-Recoverable Impairment Loss",0));
                    leaseComponentCarryOverBalanceTab.click();
                    waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                    waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
                }
                //GAAP
                if(getValuesFromExcel("Inception","Lease Component Level","Depreciation on Straight Line",0).equalsIgnoreCase("Yes")){
                    WaitUntilElementIsClickable(DepreciationStraightLineGAAP);
                    waitAndClickOnElement(DepreciationStraightLineGAAP);
                    waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                    waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
                }

            } else {

            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void cloningOfTerm() {
        try {
            waitTillWebElementIsVisible("TermsAndConditionsTab",termsConditionsTabLeaseComponent);
            waitAndClickOnElement(termsConditionsTabLeaseComponent);
            handleWait(1000);
            waitTillWebElementIsVisible("sizeOFElements", ".q-table .q-tr");
            int sizeOFElements = driver.findElements(By.cssSelector(".q-table .q-tr")).size()-1;
            for (int i=2; i <= sizeOFElements; i++) {
                if (driver.findElements(By.cssSelector(".q-table .q-tr:nth-child(" + i + ") .q-td:nth-child(12) #clone-btn")).size() == 1) {
                    waitAndClickOnElement("CloneButton", ".q-table .q-tr:nth-child(" + i + ") .q-td:nth-child(12) #clone-btn");
                    handleWait(1000);
                    waitTillWebElementIsVisible("ConfirmationBox", ".q-card #submit-btn");
                    waitAndClickOnElement("SubmitButton", ".q-card #submit-btn");
                    log.info("Term is cloned");
                    handleWait(3000);
                }
            }
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void userChangeValue(String fieldName, String value) {
        log.info("Change the "+ fieldName +" value to "+ value);
        leaseComponentDefinitionTab.click();
        unitDistributionBtnDefination.click();
        waitUntilLoadingSpinnerIsShown("nlaDropDownPopUp");
        WebElement unitField = driver.findElement(By.cssSelector(".q-dialog tbody input[aria-label='Number of Units *']"));
        waitTillWebElementIsVisible("unitField",unitField);
        sendingValueToWebElement("unitFieldUpdatedValue",unitField,value);
        clickOnSubmitPopup("Submit / Add");
        waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
        waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        log.info(fieldName + " value has been Updated");

    }

    public void copyLcWithName(String lcName, String termsCheckBox) {
        log.info("User is going to copy the Lease Component with Name: " + lcName);
        try {
            waitAndClickOnElement(lcContextBtn);
            waitTillWebElementIsVisible("menuItems", driver.findElement(By.cssSelector(".q-menu .q-item:nth-child(1)")));
            waitAndClickOnElement(copyLcBtn);
            waitTillWebElementIsVisible("lcName", leaseComponentName);
            sendingValueToWebElement("lcName", leaseComponentName, lcName);
            if (termsCheckBox.equalsIgnoreCase("Yes")) {
                waitAndClickOnElement(copyIncludeTerms);
            }
            clickOnSubmitPopup("Submit / Add");
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
            int dropDownOptionSize = clickOnDropDownAndGetDropDownSize("leaseComponentSelector", leaseComponentSelector);
            if(dropDownOptionSize==1){
                Assert.fail("Lease Component has not been copied Successfully");
            }
            log.info("Lease Component copied successfully");
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}


