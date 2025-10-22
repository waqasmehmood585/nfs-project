package com.nakisa.nlaAutomation.pageObjects;

import com.nakisa.nlaAutomation.Constant;
import com.nakisa.nlaAutomation.stepDefinitions.MasterHooks;

import io.cucumber.datatable.DataTable;
import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;

@Slf4j
public class C_Creation_PageObject extends Common_BasePage_PageObject {

	MasterAgreement_PageObject masterAgreementPageObject = masterAgreement_PageObject.get();
	public @FindBy(css = "#q-app .q-page #navigation-expansion #contract-nav-add-btn") WebElement addContract;
	public @FindBy(css = ".q-dialog .q-card #principal-position-type") WebElement contractPrincipalPosition;
	public @FindBy(css = ".q-dialog .q-card #business-unit") WebElement contractLeaseBusinessUnit;
	public @FindBy(css = ".q-dialog .q-card #company-code") WebElement contractCompanyCode;
	public @FindBy(css = ".q-dialog .q-card #name-input") WebElement contractName;
	public @FindBy(css = ".q-dialog .q-card #master-agreement-value") WebElement MasterAgreementValue;
	public @FindBy(css = ".q-dialog .q-card #submit-btn") WebElement approveButton;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #has-identified-asset button:nth-child(1)") WebElement question1_Yes;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #has-identified-asset button:nth-child(2)") WebElement question1_No;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #is-service-contract button:nth-child(1)") WebElement question2_Yes;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #is-service-contract button:nth-child(2)") WebElement question2_No;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #economic-benefit button:nth-child(1)") WebElement question3_Yes;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #economic-benefit button:nth-child(2)") WebElement question3_No;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #right-to-direct") WebElement question4;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #can-customer-operate button:nth-child(1)") WebElement question5_Yes;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #can-customer-operate button:nth-child(2)") WebElement question5_No;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #is-usage-predetermined button:nth-child(1)") WebElement question6_Yes;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #is-usage-predetermined button:nth-child(2)") WebElement question6_No;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #is-short-term-no-purchase button:nth-child(1)") WebElement question7_Yes;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #is-short-term-no-purchase button:nth-child(2)") WebElement question7_No;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #is-low-value-asset button:nth-child(1)") WebElement question8_Yes;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #is-low-value-asset button:nth-child(2)") WebElement question8_No;
	public @FindBy(css = "#q-app .q-page #definition-step") WebElement contractDefinitionTab;
	public @FindBy(css = "#q-app .q-page .absolute #lease-type") WebElement contractLeaseType;
	public @FindBy(css = "#q-app .q-page .absolute #currency") WebElement contractCurrency;
	public @FindBy(css = "#q-app .q-page .absolute #contract-indexed-currency-form-expansion #use-indexed-currency") WebElement considerIndexedCurrency;
	public @FindBy(css = "#q-app .q-page #partners-step") WebElement contractPartnerTab;
	public @FindBy(css = "#q-app .q-page .absolute #add-partners-btn") WebElement addContractPartner;
	public @FindBy(css = ".q-dialog .q-card #partner-role-filter") WebElement selectContractPartnerRole;
	public @FindBy(css = ".q-dialog .q-card #partner-filter") WebElement selectContractPartner;
	public @FindBy(css = ".q-dialog .q-card #effective-from-input-input") WebElement contractPartnerReplacementDate;
	public @FindBy(css = ".q-dialog .q-card #contract-partners-row-checkbox") WebElement selectContractPartnerOption;
	public @FindBy(css = ".q-dialog .q-card input[aria-label='Search']") WebElement searchPartner;
	public @FindBy(css = "#q-app .q-page #accounting-step") WebElement contractAccountingTab;
	public @FindBy(css = "#q-app .q-page .absolute #contract-rate-input") WebElement contractRateField;
	public @FindBy(css = "#q-app .q-page .absolute #use-ibr-rate") WebElement contractIBRRate;
	public @FindBy(css = "#q-app .q-page .absolute #spreading") WebElement contractProvisioning;
	public @FindBy(css = "#q-app .q-page .absolute #use-implicit-rate") WebElement contractImplicitRate;
	public @FindBy(css = "#q-app .q-page .absolute #compounding-frequency") WebElement contractAccountingCompounding;
	public @FindBy(css = "#q-app .q-page .absolute #calendar-type") WebElement contractAccountingCalenderType;
	public @FindBy(css = "#q-app .q-page .absolute #add-allocation-btn") WebElement addCostCenterAllocation;
	public @FindBy(css = ".q-dialog .q-card #cost-center-allocation-add") WebElement costCenterAllocationAdd;
	public @FindBy(css = "#q-app .q-page .absolute #generate-vendor-invoice") WebElement generateVendorInvoice;
	public @FindBy(css = ".q-dialog .q-card #cancel-btn") WebElement cancelPopup;
//	public @FindBy(css = "#q-app .q-page .q-stepper__tab--active .q-stepper__title") WebElement currentWorkingTab;
	public final String currentWorkingTab="#q-app .q-page .q-stepper__tab--active .q-stepper__title";

	public @FindBy(css = "#q-app .q-page .absolute #cost-center-input") WebElement contractCostCenter;
	public @FindBy(css = "#q-app .q-page .absolute #profit-center-input") WebElement contractProfitCenter;
	public @FindBy(css = ".absolute") WebElement contractAccountingPage;
	public @FindBy(css = "#q-app .q-page .absolute #asset-value-currency-type") WebElement assetTypeCurrencyValue;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #contract-is-non-lease button:nth-child(1)") WebElement question1_Yes_Lessor;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #contract-is-non-lease button:nth-child(2)") WebElement question1_No_Lessor;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #transfer-of-ownership-test button:nth-child(1)") WebElement question2_Yes_Lessor;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #transfer-of-ownership-test button:nth-child(2)") WebElement question2_No_Lessor;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #lease-purchase-option-test button:nth-child(1)") WebElement question3_Yes_Lessor;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #lease-purchase-option-test button:nth-child(2)") WebElement question3_No_Lessor;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #lease-term-test button:nth-child(1)") WebElement question4_Yes_Lessor;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #lease-term-test button:nth-child(2)") WebElement question4_No_Lessor;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #present-value-test-a button:nth-child(1)") WebElement question5_Yes_Lessor;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #present-value-test-a button:nth-child(2)") WebElement question5_No_Lessor;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #alternative-use-test button:nth-child(1)") WebElement question6_Yes_Lessor;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #alternative-use-test button:nth-child(2)") WebElement question6_No_Lessor;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #present-value-test-b button:nth-child(1)") WebElement question7_Yes_Lessor;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #present-value-test-b button:nth-child(2)") WebElement question7_No_Lessor;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #collectibility-test button:nth-child(1)") WebElement question8_Yes_Lessor;
	public @FindBy(css = "#q-app .q-page #contract-lease-determination-form-expansion #collectibility-test button:nth-child(2)") WebElement question8_No_Lessor;
	//Contract Event
	public @FindBy(css = "#q-app .q-page #context-menu-event") WebElement addEventBtn;
	public @FindBy(css = ".q-dialog .q-field #name-input") WebElement contractEventName;
	//Contact Details
	public @FindBy(css = ".q-dialog .q-card input#name-input") WebElement contactName;
	public @FindBy(css = ".q-dialog .q-card input#position-input") WebElement contactPosition;
	public @FindBy(css = ".q-dialog .q-card input#email-input") WebElement contactEmail;
	public @FindBy(css = ".q-dialog .q-card input#phone-input") WebElement contactPhone;
	public @FindBy(css = ".q-dialog .q-card input#address-input") WebElement contactAddress;
	public @FindBy(css = ".q-dialog .q-card input#description-input") WebElement contactDescription;
	public @FindBy(css = "#q-app .q-page #notifications-step") WebElement notificationsTab;
	public @FindBy(css = "#q-app .q-page .row:nth-child(1) #subscription-to-all-selector") WebElement allCategoriesDropdown;
	public @FindBy(css = "#q-app .q-page .row:nth-child(2) #subscription-to-all-selector") WebElement workflowEventsDropdown;
	public @FindBy(css = "#q-app .q-page .row:nth-child(3) #subscription-to-all-selector") WebElement criticalDatesDropdown;
	public @FindBy(css = "#q-app .q-page .row:nth-child(4) #subscription-to-all-selector") WebElement valueConfigsDropdown;
	public @FindBy(css = "#q-app .q-page .absolute #external-reference-input") WebElement externalReference;
	public @FindBy(css = "#q-app .q-page .absolute #internal-reference-input") WebElement internalReference;
	public @FindBy(css = "#q-app .q-page .absolute #valid-from-input-input") WebElement validityFrom;
	public @FindBy(css = "#q-app .q-page .absolute #valid-to-input-input") WebElement validityTo;
	public @FindBy(css = "#q-app .q-page .absolute #contract-category-group") WebElement contractCategory;
	public @FindBy(css = "#q-app .q-page .absolute #amendment-date-input-input") WebElement amendentDate;
	public @FindBy(css = "#q-app .q-page .absolute #form-of-lease") WebElement formLease;
	public @FindBy(css = "#q-app .q-page .absolute #joint-venture-lease-type") WebElement jointVenture;
	public @FindBy(css = "#q-app .q-page .absolute #description-textarea") WebElement description;
	public @FindBy(css = "#q-app .q-page .absolute #department") WebElement leaseDepartment;
	public @FindBy(css = "#q-app .q-page .absolute #lease-group") WebElement leaseGroup;
	public @FindBy(css = "#q-app .q-page .absolute #signing-person-input") WebElement signingPerson;
	public @FindBy(css = "#q-app .q-page .absolute #place-of-signature-input") WebElement signingPlace;
	public @FindBy(css = "#q-app .q-page .absolute #date-of-signature-input-input") WebElement signatureDate;
	public @FindBy(css = "#q-app .q-page .absolute #contract-group-1") WebElement group1;
	public @FindBy(css = "#q-app .q-page .absolute #contract-group-2") WebElement group2;
	public @FindBy(css = "#q-app .q-page .absolute #group-3-input") WebElement group3;
	public @FindBy(css = "#q-app .q-page .absolute #group-4-input") WebElement group4;
	public @FindBy(css = "#q-app .q-page .absolute #work-breakdown-structure") WebElement wbs;
	public @FindBy(css = "#q-app .q-page .absolute #profit-center") WebElement profitCenter;
	public @FindBy(css = "#q-app .q-page .absolute #functional-area") WebElement functionalArea;
	public @FindBy(css = "#q-app .q-page .absolute #business-area") WebElement businessArea;
	public @FindBy(css = "#q-app .q-page .absolute #segment") WebElement segment;
	public @FindBy(css = "#q-app .q-page .absolute #network") WebElement network;
	public @FindBy(css = "#q-app .q-page .absolute #track-cost") WebElement trackCost;
	public @FindBy(css = "#q-app .q-page .absolute #internal-order-type") WebElement internalOrderType;
	public @FindBy(css = "#q-app .q-page .absolute #internal-order") WebElement internalOrder;
	public @FindBy(css = "#q-app .q-page .absolute #payment-term") WebElement paymentTerm;
	public @FindBy(css = "#q-app .q-page .absolute #payment-block") WebElement paymentBlock;
	public @FindBy(css = "#q-app .q-page .absolute #payment-method") WebElement paymentMethod;
	public @FindBy(css = "#q-app .q-page .absolute #lease-type") WebElement leaseType;
	public @FindBy(css = "#context-menu-btn") WebElement contractContextBtn;
	public @FindBy(css = ".q-menu #contract-item") WebElement copyContractBtn;
	public @FindBy(css = "#q-app .q-drawer .q-page .q-item #contract-selector")WebElement contractSelector;
	int partnerSizeBefore;
	int partnerSizeAfter;
	private String[] indexList = null;

	public C_Creation_PageObject() {
		super();
		log.info("Driver is inside this class: " + this.getClass().getSimpleName());
		PageFactory.initElements(driver, this);
	}

	public void addContract() {
		log.info("Creating a new contract");
		try {
			waitTillWebElementIsEnabled("addContract", "#q-app .q-page #navigation-expansion #contract-nav-add-btn");
			WaitUntilElementIsClickable(addContract);
			waitAndClickOnElement(addContract);
//			waitUntilLoadingSpinnerIsShown("nlaFieldSpinner");
			waitUntilLoadingSpinnerIsGone("nlaFieldSpinner");
			WaitUntilElementIsClickable(contractName);
			//waitTillWebElementIsVisible("MasterAgreementValue",MasterAgreementValue);
			WaitUntilElementIsClickable(contractPrincipalPosition);
			Thread.sleep(300);
			clickOnDropDownAndSelectValue("contractPrincipalPosition", contractPrincipalPosition,
					getValuesFromExcel("Inception","Contract Level","Principal Position",0));
			String leaseBusinessUnit = getValuesFromExcel("Inception","Contract Level","Lease Business Unit",0);
			clickOnDropDownToTypeAndSelectValue("contractLeaseBusinessUnit", contractLeaseBusinessUnit, leaseBusinessUnit);
			String companyCode = null;
			if(MasterAgreement_PageObject.testCaseName.get().contains("TC-PF-")){
				companyCode=MasterHooks.configurationProperties.get().getPfizerCompanyCode();
			}else{
				companyCode=getValuesFromExcel("Inception","Contract Level","Company Code",0);
			}
			clickOnDropDownToTypeAndSelectValue("contractCompanyCode", contractCompanyCode, companyCode);
		} catch (InterruptedException | IOException exception) {
			exception.printStackTrace();
		}
		contractName.click();
		sendingValueToWebElement("contractName", contractName,
				getValuesFromExcel("Inception","Contract Level","Contract Name",0)+" "+ Constant.getTodaysDate());
		clickOnSubmitPopup("Submit / Add");
//		waitUntilLoadingSpinnerIsShown("nlaFieldSpinner");
//		waitUntilLoadingSpinnerIsGone("nlaFieldSpinner");
		log.info("Contract is created successfully");
	}

	public void fillTheLeaseDeterminationQuestions() throws InterruptedException {
		log.info("Going to fill the questions");
		waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
		waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
		if(getValuesFromExcel("Inception","Contract Level","Principal Position",0).equalsIgnoreCase("Lessor")){
			String Q1 = getValuesFromExcel("Inception","Contract Level","Is the contract a Non-Lease contract?",0);
			String Q2 = getValuesFromExcel("Inception","Contract Level","Does the lease transfer ownership of the underlying asset to the lessee by the end of the lease term?",0);
			String Q3 = getValuesFromExcel("Inception","Contract Level","Does the lease grant the lessee an option to purchase the underlying asset that the lessee is reasonably certain to exercise?",0);
			String Q4 = getValuesFromExcel("Inception","Contract Level","Is the lease term for a major part of the remaining economic life of the underlying asset?",0);
			String Q5 = getValuesFromExcel("Inception","Contract Level","Does the present value of the sum of (1) the lease payments and (2) any lessee residual value guarantee, equal or exceed substantially all of the underlying asset's fair value?",0);
			String Q6 = getValuesFromExcel("Inception","Contract Level","Is the underlying asset of such a specialized nature that it is expected to have no alternative use to the lessor at the end of the lease term?",0);
			String Q7 = getValuesFromExcel("Inception","Contract Level","Does the present value of the sum of (1) the lease payments and (2) any residual value guarantee from the lessee or a third party unrelated to the lessor equal or exceed substantially all of the underlying asset's fair value?",0);
			String Q8 = getValuesFromExcel("Inception","Contract Level","Is it probable that the lessor will collect the lease payments plus any amount necessary to satisfy a residual value guarantee?",0);

			if(Q1.equalsIgnoreCase("Yes")) {
				waitTillWebElementIsEnabled("question1_Yes_Lessor","#q-app .q-page #contract-lease-determination-form-expansion #contract-is-non-lease button:nth-child(1)");
				waitTillWebElementIsVisible("question1_Yes_Lessor", question1_Yes_Lessor);
				waitAndClickOnElement(question1_Yes_Lessor);
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}else {
				waitTillWebElementIsEnabled("question1_No_Lessor","#q-app .q-page #contract-lease-determination-form-expansion #contract-is-non-lease button:nth-child(2)");
				waitTillWebElementIsVisible("question1_No_Lessor", question1_No_Lessor);
				waitAndClickOnElement(question1_No_Lessor);
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				if(Q2.equalsIgnoreCase("Yes")){
					question2_Yes_Lessor.click();
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				} else {
					question2_No_Lessor.click();
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
					if(Q3.equalsIgnoreCase("Yes")) {
						question3_Yes_Lessor.click();
						waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
						waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
					} else {
						question3_No_Lessor.click();
						waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
						waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
						if(Q4.equalsIgnoreCase("Yes")) {
							question4_Yes_Lessor.click();
							waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
							waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
						} else {
							question4_No_Lessor.click();
							waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
							waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
							if(Q5.equalsIgnoreCase("Yes")) {
								question5_Yes_Lessor.click();
								waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
								waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
							} else {
								question5_No_Lessor.click();
								waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
								waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
								if(Q6.equalsIgnoreCase("Yes")) {
									question6_Yes_Lessor.click();
									waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
									waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
								} else {
									question6_No_Lessor.click();
									waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
									waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
									if(Q7.equalsIgnoreCase("Yes")) {
										question7_Yes_Lessor.click();
										waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
										waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
										if(Q8.equalsIgnoreCase("Yes")) {
											question8_Yes_Lessor.click();
											waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
											waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
										} else {
											question8_No_Lessor.click();
											waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
											waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
										}
									} else {
										question7_No_Lessor.click();
										waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
										waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
									}
								}
							}
						}
					}
				}

			}
		} else {
			String Q1 = getValuesFromExcel("Inception", "Contract Level", "Does the lease contain an identified asset?", 0);
			String Q2 = getValuesFromExcel("Inception", "Contract Level", "Is this a service contract?", 0);
			String Q3 = getValuesFromExcel("Inception", "Contract Level", "Does the customer have the right to obtain substantially all of the economic benefits from use of the asset?", 0);
			String Q4 = getValuesFromExcel("Inception", "Contract Level", "Who has the right to direct how and for what purpose the asset is used throughout the lease term?", 0);
			String Q5 = getValuesFromExcel("Inception", "Contract Level", "If 'Neither', does the customer have the right to operate the asset throughout the period of use, without the supplier having the right to change those operating instructions?", 0);
			String Q6 = getValuesFromExcel("Inception", "Contract Level", "Did the customer design the asset in a way that predetermines how and for what use the asset will be used throughout the period of use?", 0);
			String Q7 = getValuesFromExcel("Inception", "Contract Level", "Is the lease term less than 12 months with no purchase option (IFRS) or no purchase option that is likely to be exercised (US GAAP)?", 0);
			String Q8 = getValuesFromExcel("Inception", "Contract Level", "Is the lease for a low value asset ?", 0);
//			log.info("Going to fill the questions");
//			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
//			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			if (Q1.equalsIgnoreCase("Yes")) {
				waitTillWebElementIsEnabled("question1_Yes", "#q-app .q-page #contract-lease-determination-form-expansion #has-identified-asset button:nth-child(1)");
				WaitUntilElementIsClickable(question1_Yes);
				waitTillWebElementIsVisible("question1_Yes", question1_Yes);
//				question1_Yes.click();
				waitAndClickOnElement(question1_Yes);
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			} else {
				waitTillWebElementIsVisible("question1_No", question1_No);
				waitAndClickOnElement(question1_No);
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
			if (Q2.equalsIgnoreCase("Yes")) {
				question2_Yes.click();
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			} else {
				question2_No.click();
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				if (Q3.equalsIgnoreCase("Yes")) {
					question3_Yes.click();
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
					try {
						clickOnDropDownAndSelectValue("question4", question4, Q4);
					} catch (IOException e) {
						e.printStackTrace();
					}
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
					if (Q4.equalsIgnoreCase("Neither")) {

						if (Q5.equalsIgnoreCase("Yes")) {
							WaitUntilElementIsClickable(question5_Yes);
							question5_Yes.click();
							waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
							waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
						} else {
							WaitUntilElementIsClickable(question5_No);
							question5_No.click();
							waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
							waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
						}

						if (Q6.equalsIgnoreCase("Yes")) {
							WaitUntilElementIsClickable(question6_Yes);
							question6_Yes.click();
							waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
							waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
						} else {
							WaitUntilElementIsClickable(question6_No);
							question6_No.click();
							waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
							waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
						}

						if (Q7.equalsIgnoreCase("Yes")) {
							WaitUntilElementIsClickable(question7_Yes);
							question7_Yes.click();
							waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
							waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
						} else {
							WaitUntilElementIsClickable(question7_No);
							question7_No.click();
							waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
							waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
						}

						if (Q8.equalsIgnoreCase("Yes")) {
							WaitUntilElementIsClickable(question8_Yes);
							question8_Yes.click();
							waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
							waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
						} else {
							WaitUntilElementIsClickable(question8_No);
							question8_No.click();
							waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
							waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
						}

					} else {
						if (Q7.equalsIgnoreCase("Yes")) {
							WaitUntilElementIsClickable(question7_Yes);
							question7_Yes.click();
							waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
							waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
						} else {
							WaitUntilElementIsClickable(question7_No);
							question7_No.click();
							waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
							waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");

							if (Q8.equalsIgnoreCase("Yes")) {
								WaitUntilElementIsClickable(question8_Yes);
								question8_Yes.click();
								waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
								waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
							} else {
								WaitUntilElementIsClickable(question8_No);
								question8_No.click();
								waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
								waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
							}
						}
					}
				} else {
					question3_No.click();
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				}
			}
		}
		log.info("All the questions are answered");
	}

	public void fillTheDefinitionTab() {
		contractDefinitionTab.click();
		try {
			waitUntilLoadingSpinnerIsShown("nlaTabChange");
			waitUntilLoadingSpinnerIsGone("nlaTabChange");
			if(getValuesFromExcel("Inception","Contract Level","Contract Name",0).equalsIgnoreCase("NFS-13642")) {
				waitTillWebElementIsVisible("leaseType", leaseType);
				String leaseTypeValue = getValuesFromExcel("Inception","Contract Level","Lease Type",0);
				clickOnDropDownAndSelectValue("leaseType", leaseType, leaseTypeValue);
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
			String currency = getValuesFromExcel("Inception","Contract Level","Contract Currency",0);
			clickOnDropDownToTypeAndSelectValue("contractCurrency", contractCurrency, currency);
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			if (getValuesFromExcel("Inception","Contract Level","Indexed Currency",0).equalsIgnoreCase("Yes")) {
				WaitUntilElementIsClickable(considerIndexedCurrency);
				waitAndClickOnElement(considerIndexedCurrency);
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				String assetTypeValue=getValuesFromExcel("Inception","Contract Level","Asset Value Currency Type",0);
				waitTillWebElementIsVisible("assetTypeCurrencyValue",assetTypeCurrencyValue);
				WaitUntilElementIsClickable(assetTypeCurrencyValue);
				clickOnDropDownAndSelectValue("assetTypeCurrencyValue",assetTypeCurrencyValue,assetTypeValue);
			}
			//Storing the MLA ID
			String idPath = driver.findElement(By.cssSelector("#q-app .q-page .q-item.col-auto:nth-child(1) .q-item__label:nth-child(2)")).getText();
			MasterHooks.searchCTID.set(idPath);
		} catch (InterruptedException | IOException exception) {
			exception.printStackTrace();
		}
	}

//	public void enterContractPartnersInformation() {
//		try {
//			contractPartnerTab.click();
//			waitUntilLoadingSpinnerIsShown("nlaTabChange");
//			waitUntilLoadingSpinnerIsGone("nlaTabChange");
////			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
////			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
//			waitTillWebElementIsVisible("addContractPartner", addContractPartner);
//			addContractPartner.click();
//			clickOnDropDownToTypeAndSelectValue("selectContractPartnerRole", selectContractPartnerRole,
//					getValuesFromExcel("Inception","Contract Level","Partner Role",0));
//			int totalNumberOfPartnerRole = getValuesFromExcel("Inception").get("Contract Level").get("Partner Role").size();
//			for (int partnerCounter = 0; partnerCounter < totalNumberOfPartnerRole; partnerCounter++) {	
//				selectPartner("selectContractPartnerOption", selectContractPartnerOption,
//						getValuesFromExcel("Inception","Contract Level","Partner Name",partnerCounter));
//			}
//			clickOnSubmitPopup("Submit / Add");
////			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
////			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
//		} catch (InterruptedException | IOException exception) {
//			exception.printStackTrace();
//		}
//	}

	/**    public void fillPartners(String Role, String Option1, String Option2 ) {
	 //        contractPartnerTab.click();
	 //        waitTillWebElementIsVisible("addContractPartner", addContractPartner);
	 //        addContractPartner.click();
	 //        //waitUntilLoadingSpinnerIsShown("nlaDropDownPopUp");
	 //        try {
	 //            clickOnDropDownToTypeAndSelectValue("selectContractPartnerRole", selectContractPartnerRole, Role);
	 //        } catch (InterruptedException | IOException exception) {
	 //            exception.printStackTrace();
	 //        }
	 //        selectPartners("selectContractPartnerOptions", selectContractPartnerOptions, Option1);
	 //        selectPartners("selectContractPartnerOptions", selectContractPartnerOptions, Option2);
	 //        clickOnSubmitPopup("Submit / Add");
	 //        waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
	 //        waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
	 //    } **/
	public void selectPartner(String value) {
		try{
			Thread.sleep(1000);
			int partnerOptionSize = driver.findElements(By.cssSelector(".q-dialog .q-card table tbody tr")).size() - 1;
			waitTillWebElementIsVisible("partnerName",searchPartner);
			sendingValueToWebElement("partnerName",searchPartner,value);
//			if(MasterHooks.configurationProperties.get().getRunAutomationOnNCPBuild()) {
//				waitUntilLoadingSpinnerIsShown("nlaPopupLoader");
//				waitUntilLoadingSpinnerIsGone("nlaPopupLoader");
//			}
            Thread.sleep(1000);
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					if (waitTillWebElementIsVisible("partner", driver.findElement(By.cssSelector(".q-dialog .q-card table tbody tr:nth-child(2)"))) &&
							(driver.findElements(By.cssSelector(".q-dialog .q-card table tbody tr:nth-child(2)")).size()<partnerOptionSize)) {
						return true;
					} else {
						return false;
					}
				}
			});

			waitAndClickOnElement(driver.findElement(By.cssSelector(".q-dialog .q-card table tbody tr:nth-child(2) td:nth-child(1)")));
			//clicking on (x) button
			waitAndClickOnElement(driver.findElement(By.cssSelector(".q-dialog .q-card .q-field .q-icon[type='button']")));

			// Commenting this because previously we select partner through loop
//			String optionToClickWithIndex = "";
//			for (int cssIndex = 2; cssIndex <= partnerOptionSize; cssIndex++) {
//				String innerTextValue = driver.findElement(By.cssSelector(".q-dialog .q-card table tbody tr:nth-child(" + cssIndex + ") td:nth-child(3)")).getText();
//				if (innerTextValue.equalsIgnoreCase(value)) {
//					try {
//						waitAndClickOnElement(driver.findElement(By.cssSelector(".q-dialog .q-card table tbody tr:nth-child(" + cssIndex + ") td:nth-child(1)")));
//					} catch (InterruptedException e) {
//						throw new RuntimeException(e);
//					}
//					break;
//				}
//			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void enterContractPartnersInformation(String level) {
		try {
			String contractLevel=null;
			if (level.equalsIgnoreCase("Inception")) {
				contractLevel = "Contract Level";
			} else {
				contractLevel = "eventdata";
			}
			if(!getValueFromElement(currentWorkingTab).equalsIgnoreCase("Partners")){
				contractPartnerTab.click();
				waitUntilLoadingSpinnerIsShown("nlaTabChange");
				waitUntilLoadingSpinnerIsGone("nlaTabChange");
			}
			Thread.sleep(1000);
			partnerSizeBefore =driver.findElements(By.cssSelector(".q-page .q-table tbody tr.q-tr")).size()-1;
			int totalNumberOfPartnerRole = masterAgreementPageObject.getInputValues().get(level).get(contractLevel).get("Partner Role").size();
			for (int partnerCounter = 0; partnerCounter < totalNumberOfPartnerRole; partnerCounter++) {
				WaitUntilElementIsClickable(addContractPartner);
				waitAndClickOnElement(addContractPartner);
				if(MasterHooks.configurationProperties.get().getRunAutomationOnNCPBuild()) {
					waitUntilLoadingSpinnerIsShown("nlaPopupLoader");
					waitUntilLoadingSpinnerIsGone("nlaPopupLoader");
				}
				clickOnDropDownToTypeAndSelectValue("selectContractPartnerRole", selectContractPartnerRole,
						getValuesFromExcel(level,contractLevel,"Partner Role",partnerCounter));
//				if(MasterHooks.configurationProperties.get().getRunAutomationOnNCPBuild()) {
//					waitUntilLoadingSpinnerIsShown("nlaPopupLoader");
//					waitUntilLoadingSpinnerIsGone("nlaPopupLoader");
//				}
				selectPartner(getValuesFromExcel(level,contractLevel,"Partner Name",partnerCounter));
				// For Event
//				if(contractLevel.equalsIgnoreCase("eventdata")) {
//					waitTillWebElementIsVisible("effectiveDate", contractPartnerEffectiveDate);
//					contractPartnerEffectiveDate.click();
//					Thread.sleep(500);
//					sendingValueToWebElement("effectiveDate", contractPartnerEffectiveDate,
//							getValuesFromExcel(level,contractLevel,"Effective Date",partnerCounter));
//				}
				clickOnSubmitPopup("Submit / Add");
			}
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					partnerSizeAfter = driver.findElements(By.cssSelector(".q-page .q-table tbody tr.q-tr")).size()-1;
					if (partnerSizeAfter==partnerSizeBefore+totalNumberOfPartnerRole) {
						return true;
					}
					return false;
				}
			});
			Assert.assertEquals(partnerSizeAfter,partnerSizeBefore+totalNumberOfPartnerRole,"Partner has not been added Successfully");
			log.info("Partner has been added Successfully");
		} catch (InterruptedException | IOException exception) {
			exception.printStackTrace();
		}
	}

	public void fillContractAccounting() {
		try {
			waitAndClickOnElement(contractAccountingTab);
			waitUntilLoadingSpinnerIsShown("nlaTabChange");
			waitUntilLoadingSpinnerIsGone("nlaTabChange");
//			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
//		waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
//		waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
		String principalPosition=getValuesFromExcel("Inception","Contract Level","Principal Position",0);
		String companyCode= getValuesFromExcel("Inception","Contract Level","Company Code",0);
		String leaseType = getValuesFromExcel("Inception","Contract Level","Lease Type",0);
		String provisioning =getValuesFromExcel("Inception","Contract Level","Provisioning",0);
		if(!provisioning.equalsIgnoreCase("Yes")) {
			boolean provisionExist = driver.findElements(By.cssSelector("#q-app .q-page .absolute #spreading")).size()==1;
			if (!(principalPosition.equalsIgnoreCase("Lessor") || (companyCode.contains("1000 - CA") && (companyCode.contains("2000 - CA")))) && provisionExist) {
				waitTillWebElementIsVisible("contractProvisioning", contractProvisioning);
				WaitUntilElementIsClickable(contractProvisioning);
				try {
					waitAndClickOnElement(contractProvisioning);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
			try {
				Set<String> excludedLeaseTypes = new HashSet<>(Arrays.asList(
						"Non-Lease",
						"01 - Non-Lease Contract",
						"Lease Short term",
						"04 - Short-Term Lease Contract",
						"Lease Low Value",
						"05 - Low Value Lease Contract",
						"Non-Lease Service Contract",
						"06 - Non-Lease Service Contract",
						"01 - Lease Contract (Operating/Short Term)",
						"Lease Contract (Operating/Short Term)"
				));
				if (!excludedLeaseTypes.contains(leaseType)) {

					if (getValuesFromExcel("Inception","Contract Level","Use IBR Rate",0).equalsIgnoreCase("Yes")) {
						waitTillWebElementIsVisible("contractIBRRate", contractIBRRate);
						WaitUntilElementIsClickable(contractIBRRate);
						waitAndClickOnElement(contractIBRRate);
						waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
						waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
					} else if (getValuesFromExcel("Inception","Contract Level","Use Implicit Rate",0).equalsIgnoreCase("Yes")) {
						waitTillWebElementIsVisible("contractImplicitRate", contractImplicitRate);
						waitAndClickOnElement(contractImplicitRate);
						waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
						waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
					} else {
						WaitUntilElementIsClickable(contractRateField);
						waitAndClickOnElement(contractRateField);
						sendingValueToWebElement("contractRateField", contractRateField,
								getValuesFromExcel("Inception","Contract Level","Contract Rate",0));
						waitAndClickOnElement(contractAccountingTab);
						waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
						waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
					}
				}
			} catch (InterruptedException e) {e.printStackTrace();}
		}
//		if (getValuesFromExcel("Inception","Contract Level","Contract Rate",0)!=null) {
//
//			WaitUntilElementIsClickable(contractRateField);
//			contractRateField.click();
//			sendingValueToWebElement("contractRateField", contractRateField, getValuesFromExcel("Inception","Contract Level","Contract Rate",0));
//			contractAccountingTab.click();
//			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
//			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
//		} else {
//			contractIBRRate.click();
//			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
//			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
//		}

		try {
			if(!(leaseType.equalsIgnoreCase("Lease Short term") || leaseType.equalsIgnoreCase("Lease Low Value") ||
					leaseType.equalsIgnoreCase("04 - Short-Term Lease Contract")||
					leaseType.equalsIgnoreCase("05 - Low Value Lease Contract")||
					leaseType.equalsIgnoreCase("Lease Contract (Operating/Short Term)")||
					leaseType.equalsIgnoreCase("01 - Lease Contract (Operating/Short Term)"))){
				waitTillWebElementIsVisible("contractAccountingCompounding", contractAccountingCompounding);
				clickOnDropDownAndSelectValue("contractAccountingCompounding", contractAccountingCompounding,
						getValuesFromExcel("Inception","Contract Level","Compounding Frequency",0));
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
			WaitUntilElementIsClickable(contractAccountingCalenderType);
			clickOnDropDownAndSelectValue("contractAccountingCalenderType", contractAccountingCalenderType,
					getValuesFromExcel("Inception","Contract Level","Calendar Type",0));
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			String cost = getValuesFromExcel("Inception","Contract Level","Cost Center",0);
			if(cost.isEmpty() || cost.equalsIgnoreCase("-")) {
				waitAndClickOnElement("WBS","#q-app .q-page #cost-object-work-breakdown-structure");
				waitTillWebElementIsVisible("WBS","#q-app .q-page .absolute #work-breakdown-structure");
				clickOnDropDownToTypeAndSelectValue("wbs",wbs,
						getValuesFromExcel("Inception","Contract Level","WBS",0));
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				clickOnDropDownToTypeAndSelectValue("profitCenter",profitCenter,
						getValuesFromExcel("Inception","Contract Level","Profit Center",0));
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}else{
				waitAndClickOnElement(addCostCenterAllocation);
				waitUntilLoadingSpinnerIsShown("nlaDropDownPopUp");
				waitTillWebElementIsVisible("costCenterField", ".q-dialog .q-card #cost-center");
				String[] costCenterLength = null;
				if (MasterAgreement_PageObject.testCaseName.get().contains("TC-PF-")) {
					costCenterLength = MasterHooks.configurationProperties.get().getPfizerCostCenter().split(",");
				} else {
					costCenterLength = getValuesFromExcel("Inception","Contract Level","Cost Center",0).split(",");
				}
				String[] profitCenterLength = getValuesFromExcel("Inception","Contract Level","Profit Center",0).split(",");
				String[] percentageAllocationLength = getValuesFromExcel("Inception","Contract Level","Percentage Allocation",0).split(",");
				boolean mainCostObjectSize = driver.findElements(By.cssSelector(".q-dialog .q-card tr #main-cost-object")).size() >= 1;

				for (int costCenterCount = 1; costCenterCount <= costCenterLength.length; costCenterCount++) {
					if (costCenterCount != 1) {
						waitAndClickOnElement(costCenterAllocationAdd);
						Thread.sleep(300);
					}
					if (mainCostObjectSize) {
						waitTillWebElementIsVisible("costCenter", ".q-dialog .q-card tr:nth-child(" + (costCenterCount + 1) + ") #cost-center");
						WebElement ContractCostCenter = driver.findElement(By.cssSelector(".q-dialog .q-card tr:nth-child(" + (costCenterCount + 1) + ") #cost-center"));
						waitForClickablility(".q-dialog .q-card tr:nth-child(" + (costCenterCount + 1) + ") #cost-center");
						clickOnDropDownToTypeAndSelectValue("contractCostCenter", ContractCostCenter, costCenterLength[costCenterCount - 1]);
						if (!(profitCenterLength[costCenterCount - 1].equalsIgnoreCase("") || profitCenterLength[costCenterCount - 1].equalsIgnoreCase("-"))) {
							WebElement ContractProfitCenter = driver.findElement(By.cssSelector(".q-dialog .q-card tr:nth-child(" + (costCenterCount + 1) + ") #profit-center"));
							waitForClickablility(".q-dialog .q-card tr:nth-child(" + (costCenterCount + 1) + ") #profit-center");
							clickOnDropDownToTypeAndSelectValue("contractProfitCenter", ContractProfitCenter, profitCenterLength[costCenterCount - 1]);
						}
					} else {
						WebElement ContractCostCenter = driver.findElement(By.cssSelector(".q-dialog .q-card tr:nth-child(" + costCenterCount + ") #cost-center"));
						waitForClickablility(".q-dialog .q-card tr:nth-child(" + costCenterCount + ") #cost-center");
						clickOnDropDownToTypeAndSelectValue("contractCostCenter", ContractCostCenter, costCenterLength[costCenterCount - 1]);
						if (!(profitCenterLength[costCenterCount - 1].equalsIgnoreCase("") || profitCenterLength[costCenterCount - 1].equalsIgnoreCase("-"))) {
							WebElement ContractProfitCenter = driver.findElement(By.cssSelector(".q-dialog .q-card tr:nth-child(" + costCenterCount + ") #profit-center"));
							waitForClickablility(".q-dialog .q-card tr:nth-child(" + costCenterCount + ") #profit-center");
							clickOnDropDownToTypeAndSelectValue("contractProfitCenter", ContractProfitCenter, profitCenterLength[costCenterCount - 1]);
						}
					}


					if (mainCostObjectSize) {
						WebElement costCenterPercentage = driver.findElement(By.cssSelector(".q-dialog .q-card tr:nth-child(" + (costCenterCount + 1) + ") #allocation-percentage-input"));
						waitForClickablility(".q-dialog .q-card tr:nth-child(" + (costCenterCount + 1) + ") #allocation-percentage-input");
						sendingValueToWebElement("percentageAllocation", costCenterPercentage, percentageAllocationLength[costCenterCount - 1]);
					}
				}
				if (mainCostObjectSize) {
					int mainCostObject = Integer.parseInt(getValuesFromExcel("Inception","Contract Level","Main Cost Object",0));
					waitAndClickOnElement("mainCostObject", ".q-dialog .q-card tr:nth-child(" + (mainCostObject + 1) + ") #main-cost-object");
				}
				clickOnSubmitPopup("Submit / Add");
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
			if (getValuesFromExcel("Inception","Contract Level","Generate Vendor Invoice",0).equalsIgnoreCase("Yes")) {
				waitTillWebElementIsVisible("generateVendorCheckbox", generateVendorInvoice);
				if (driver.findElements(By.cssSelector("#generate-vendor-invoice[aria-disabled='true']")).size() == 1) {
					Assert.fail("GVI checkbox is disabled");
				} else {
					waitAndClickOnElement(generateVendorInvoice);
				}
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				if(driver.findElements(By.cssSelector("#q-app .q-page .absolute #generate-vendor-invoice[aria-checked='false']")).size()==1){
					try {
						waitAndClickOnElement(generateVendorInvoice);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				}
			}
		} catch (InterruptedException | IOException exception) {
			exception.printStackTrace();
		}
	}

	public void addNewContractEvent(String eventName) {
		log.info("Creating Contract Event");
		waitTillWebElementIsVisible("addEventBtn",addEventBtn);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#q-app .q-page #context-menu-event")));
		WaitUntilElementIsClickable(addEventBtn);
		//addEventBtn.click();
		waitForPageToLoad(6);
		try {
			waitAndClickOnElement(addEventBtn);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitTillWebElementIsVisible("contractEventName",contractEventName);
		WaitUntilElementIsClickable(contractEventName);
		contractEventName.click();
		sendingValueToWebElement("contractEventName", contractEventName, eventName);
		clickOnSubmitPopup("Submit / Add");
		waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
		waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
		log.info("Created Contract Event");
	}

	public void replacePartnerContractEvent(String partnerNum, String newName, String replacementDate) {
		log.info("Replacing Partners in Contract Event");
		try {
			if(!getValueFromElement(currentWorkingTab).equalsIgnoreCase("Partners")){
				contractPartnerTab.click();
				waitUntilLoadingSpinnerIsShown("nlaTabChange");
				waitUntilLoadingSpinnerIsGone("nlaTabChange");
			}
			waitTillWebElementIsVisible("replaceButton",".q-page .q-table tbody button.text-orange-8");
			WebElement partnerReplaceButton=driver.findElement(By.cssSelector(".q-page .q-table tbody tr.q-tr:nth-child("+(Integer.parseInt(partnerNum) + 1)+") button.text-orange-8"));
			waitAndClickOnElement(partnerReplaceButton);
			waitUntilLoadingSpinnerIsShown("nlaDropDownPopUp");
			selectPartner(newName);
//			clickOnDropDownToTypeAndSelectValue("selectContractPartner", selectContractPartner, newName);
			waitAndClickOnElement(contractPartnerReplacementDate);
			sendingValueToWebElement("contractPartnerEffectiveDate", contractPartnerReplacementDate, replacementDate);
			clickOnSubmitPopup("Submit / Add");
			waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
			String alertMessage = driver.findElement(By.cssSelector(".desktop .q-notifications .q-notification[role='alert'] .q-notification__message")).getText();
			Assert.assertEquals(alertMessage,"Replaced Contract Partners","Partner is not replaced successfully");
			log.info("Partner has been Replaced Successfully");
		} catch (InterruptedException e){
			e.printStackTrace();
		}

	}

	public void userAddContactOnContractPartner(DataTable dataTable) {
		log.info("Adding Contact On Contract");
		try {
			List<Map<String, String>> contactDetails = dataTable.asMaps(String.class, String.class);
			if(!getValueFromElement(currentWorkingTab).equalsIgnoreCase("Partners")){
				contractPartnerTab.click();
				waitUntilLoadingSpinnerIsShown("nlaTabChange");
				waitUntilLoadingSpinnerIsGone("nlaTabChange");
			}
			for (int dtSize = 0; dtSize < contactDetails.size(); dtSize++) {
				WebElement partnerReplaceButton = driver.findElement(By.cssSelector(".q-page .q-table tbody tr.q-tr:nth-child("+ (Integer.parseInt(contactDetails.get(dtSize).get("Partner Number")) + 1)+ ") button"));
				waitAndClickOnElement(partnerReplaceButton);
				waitUntilLoadingSpinnerIsShown("nlaDropDownPopUp");
				sendingValueToWebElement("contactName", contactName, contactDetails.get(dtSize).get("Name"));
				sendingValueToWebElement("contactPosition", contactPosition, contactDetails.get(dtSize).get("Position"));
				LocalDateTime myDateObj = LocalDateTime.now();
				DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
				String formattedDate = myDateObj.format(myFormatObj).replaceAll(" ", "").replaceAll(":", "");
				String domainName = contactDetails.get(dtSize).get("Email");
				String email = formattedDate + domainName;
				sendingValueToWebElement("contactEmail", contactEmail, email);
				sendingValueToWebElement("contactPhone", contactPhone, contactDetails.get(dtSize).get("Phone"));
				sendingValueToWebElement("contactAddress", contactAddress, contactDetails.get(dtSize).get("Address"));
				sendingValueToWebElement("contactDescription", contactDescription, contactDetails.get(dtSize).get("Description"));
				clickOnSubmitPopup("Submit / Add");
				waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
				String alertMessage = driver.findElement(By.cssSelector(".desktop .q-notifications .q-notification[role='alert'] .q-notification__message")).getText();
				waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
				Assert.assertEquals(alertMessage,"Adding Contact successfully","Contact is not added successfully");
				log.info("Partner's Contact has been Added Successfully");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		log.info("Added Contact On Contract");
	}

	public void verifyLessorAddPartners(String partnerRole) {
		log.info("Checking the Lessor Partner disabled in dropdown for GVI Contract");
		try {
			String partnerRoleDisabled = null;
			if(!getValueFromElement(currentWorkingTab).equalsIgnoreCase("Partners")){
				contractPartnerTab.click();
				waitUntilLoadingSpinnerIsShown("nlaTabChange");
				waitUntilLoadingSpinnerIsGone("nlaTabChange");
			}
			waitTillWebElementIsVisible("addContractPartner", addContractPartner);
			addContractPartner.click();
			if (clickOnDropDownToTypeAndCheckEnableValue("LessorPartner", selectContractPartnerRole, partnerRole)) {
				partnerRoleDisabled = "true";
			} else {
				partnerRoleDisabled = "false";
			}
			Assert.assertEquals(partnerRoleDisabled, "true", "Lessor Partner is not disabled");
			log.info("Lessor Partner is disabled in dropdown");
			waitTillWebElementIsVisible("cancelPopup", cancelPopup);
			cancelPopup.click();
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyLessorReplacePartners(String partnerRole) {
		log.info("Checking the Lessor Partner replace button disable for GVI Contract");
		String replaceButtonNotVisible = null;
		if(!getValueFromElement(currentWorkingTab).equalsIgnoreCase("Partners")){
			contractPartnerTab.click();
			waitUntilLoadingSpinnerIsShown("nlaTabChange");
			waitUntilLoadingSpinnerIsGone("nlaTabChange");
		}
		int partnerSize = driver.findElements(By.cssSelector(".q-page .q-table tbody tr.q-tr")).size() - 1;
		for (int cssIndex = 2; cssIndex <= partnerSize; cssIndex++) {
			String role = driver.findElement(By.cssSelector(".q-page .q-table tbody tr.q-tr:nth-child(" + cssIndex + ") td:nth-child(4)")).getText();
			if (partnerRole.equalsIgnoreCase(role)) {
				int buttonSize = driver.findElements(By.cssSelector(".q-page .q-table tbody tr.q-tr:nth-child(" + cssIndex + ") .q-btn")).size();
				if (buttonSize == 0) {
					replaceButtonNotVisible = "true";
				} else {
					replaceButtonNotVisible = "false";
				}
				Assert.assertEquals(replaceButtonNotVisible, "true","Replace button is visible for Lessor Partner");
				log.info("Lessor Partner replace button is not visible");
			}
		}
	}

	public void editPartnersContact(DataTable dataTable) {
		log.info("Editing the Partner's Contact");
		try {
			WebElement partnerContact;
			List<Map<String, String>> contactDetails = dataTable.asMaps(String.class, String.class);
			if(!getValueFromElement(currentWorkingTab).equalsIgnoreCase("Partners")){
				contractPartnerTab.click();
				waitUntilLoadingSpinnerIsShown("nlaTabChange");
				waitUntilLoadingSpinnerIsGone("nlaTabChange");
			}
			String contractStatus=null;
			if(driver.findElement(By.cssSelector("#q-app .q-page .q-item.col-auto:nth-child(1) .q-item__label:nth-child(1)")).getText()
					.equalsIgnoreCase("Master Agreement ID")){
				contractStatus="Active";
			}else{
				contractStatus = driver.findElement(By.cssSelector("#q-app .q-page .q-item.col-auto:nth-child(4) .q-item__label:nth-child(2)")).getText();
			}
			if (contractStatus.equalsIgnoreCase("Active")) {
				for (int dtSize = 0; dtSize < contactDetails.size(); dtSize++) {
					int contactSize = driver.findElements(By.cssSelector(".q-page .q-table tbody tr.q-tr:nth-child(" + (Integer.parseInt(contactDetails.get(dtSize).get("Partner Number")) + 1) + ") .q-chip")).size();
					if(!(contactSize >1)){
						partnerContact = driver.findElement(By.cssSelector(".q-page .q-table tbody tr.q-tr:nth-child(" + (Integer.parseInt(contactDetails.get(dtSize).get("Partner Number")) + 1) + ") .q-chip"));
					}else{
						partnerContact = driver.findElement(By.cssSelector(".q-page .q-table tbody tr.q-tr:nth-child(" + (Integer.parseInt(contactDetails.get(dtSize).get("Partner Number")) + 1) + ") .q-chip:nth-child("+contactSize+")"));
					}
					waitAndClickOnElement(partnerContact);
					waitTillWebElementIsVisible("contactName",contactName);
					if(!contactDetails.get(dtSize).get("Name").equalsIgnoreCase("null")){
						clearField(contactName);
						sendingValueToWebElement("contactName", contactName, contactDetails.get(dtSize).get("Name"));
					}
					if(!contactDetails.get(dtSize).get("Position").equalsIgnoreCase("null")){
						clearField(contactPosition);
						sendingValueToWebElement("contactPosition", contactPosition, contactDetails.get(dtSize).get("Position"));
					}
					if(!contactDetails.get(dtSize).get("Email").equalsIgnoreCase("null")){
						LocalDateTime myDateObj = LocalDateTime.now();
						DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
						String formattedDate = myDateObj.format(myFormatObj).replaceAll(" ", "").replaceAll(":", "");
						String domainName = contactDetails.get(dtSize).get("Email");
						String email = formattedDate + domainName;
						clearField(contactEmail);
						sendingValueToWebElement("contactEmail", contactEmail, email);
					}
					if(!contactDetails.get(dtSize).get("Phone").equalsIgnoreCase("null")){
						clearField(contactPhone);
						sendingValueToWebElement("contactPhone", contactPhone, contactDetails.get(dtSize).get("Phone"));
					}
					if(!contactDetails.get(dtSize).get("Address").equalsIgnoreCase("null")){
						clearField(contactAddress);
						sendingValueToWebElement("contactAddress", contactAddress, contactDetails.get(dtSize).get("Address"));
					}
					if(!contactDetails.get(dtSize).get("Description").equalsIgnoreCase("null")){
						clearField(contactAddress);
						sendingValueToWebElement("contactDescription", contactDescription, contactDetails.get(dtSize).get("Description"));
					}
					clickOnSubmitPopup("Submit / Add");
					waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
					String alertMessage = driver.findElement(By.cssSelector(".desktop .q-notifications .q-notification[role='alert'] .q-notification__message")).getText();
					waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
					Assert.assertEquals(alertMessage,"Editing Contact successfully","Contact is not edited");
					log.info("Partner's Contact has been Edited Successfully");
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deletePartner(String partnerNum, String level) {
		log.info("Deleting the Partner's Contact");
		try {
			WebElement partnerContactDeleteButton = null;
			if(!getValueFromElement(currentWorkingTab).equalsIgnoreCase("Partners")){
				waitAndClickOnElement(contractPartnerTab);
				waitUntilLoadingSpinnerIsShown("nlaTabChange");
				waitUntilLoadingSpinnerIsGone("nlaTabChange");
			}
			if(level.equalsIgnoreCase("Contract Inception")) {
				partnerContactDeleteButton = driver.findElement(By.cssSelector(".q-page .q-table tbody tr.q-tr:nth-child(" + (Integer.parseInt(partnerNum) + 1) +") button:nth-child(3)"));
			}else if(level.equalsIgnoreCase("Contract Event") || level.equalsIgnoreCase("Master Agreement Inception")){
				partnerContactDeleteButton = driver.findElement(By.cssSelector(".q-page .q-table tbody tr.q-tr:nth-child(" + (Integer.parseInt(partnerNum) + 1) +") button:nth-child(2)"));
			}
			partnerSizeBefore = driver.findElements(By.cssSelector(".q-page .q-table tbody tr.q-tr")).size() - 1;
			waitAndClickOnElement(partnerContactDeleteButton);
			clickOnSubmitPopup("Submit / Add");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					partnerSizeAfter = driver.findElements(By.cssSelector(".q-page .q-table tbody tr.q-tr")).size() - 1;
					if (partnerSizeAfter==partnerSizeBefore- 1) {
						return true;
					}
					return false;
				}
			});
			Assert.assertEquals(partnerSizeAfter, partnerSizeBefore - 1, "Partner has not been deleted Successfully");
			log.info("Partner has been Deleted Successfully");

		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deletePartnersContact(String contactNum,String partnerNum) {
		log.info("Deleting the Partner's Contact");
		try {
			if(!getValueFromElement(currentWorkingTab).equalsIgnoreCase("Partners")){
				contractPartnerTab.click();
				waitUntilLoadingSpinnerIsShown("nlaTabChange");
				waitUntilLoadingSpinnerIsGone("nlaTabChange");
			}
			int contactSizeBefore = driver.findElements(By.cssSelector(".q-page .q-table tbody tr.q-tr:nth-child(" + (Integer.parseInt(partnerNum) + 1) + ") .q-chip")).size();
			WebElement partnerContactDeleteButton = driver.findElement(By.cssSelector(".q-page .q-table tbody tr.q-tr:nth-child(" + (Integer.parseInt(partnerNum) + 1) + ") .q-chip:nth-child(" + contactNum + ") .q-icon[aria-label='Remove']"));
			waitAndClickOnElement(partnerContactDeleteButton);
			clickOnSubmitPopup("Submit / Add");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					if ((contactSizeBefore- 1)==driver.findElements(By.cssSelector(".q-page .q-table tbody tr.q-tr:nth-child(" + (Integer.parseInt(partnerNum) + 1) + ") .q-chip")).size()) {
						return true;
					}
					return false;
				}
			});
			int contactSizeAfter = driver.findElements(By.cssSelector(".q-page .q-table tbody tr.q-tr:nth-child(" + (Integer.parseInt(partnerNum) + 1) + ") .q-chip")).size();
			Assert.assertEquals(contactSizeAfter, contactSizeBefore - 1, "Failed to delete Partner's contact:");
			log.info("Partners contact has been Deleted Successfully");
		}catch (InterruptedException e) {e.printStackTrace();}
	}

	public void userSubscribesNotifications() {
		try {
			log.info("subscribing first Three usernames in Workflow Events");
			waitAndClickOnElement(notificationsTab);
			waitUntilLoadingSpinnerIsShown("nlaTabChange");
			waitUntilLoadingSpinnerIsGone("nlaTabChange");
			int recordSize = 2;
			int columnSize = driver.findElements(By.cssSelector("#q-app .q-page #subscription-to-all-selector")).size();
			for (int i=2; i<=columnSize; i++) {
				waitTillWebElementIsVisible("subscribeCheckBox", "#q-app .q-page .row:nth-child(" + i + ") #subscription-to-all-selector");
				waitAndClickOnElement("subscribeCheckBox", "#q-app .q-page .row:nth-child(" + i + ") #subscription-to-all-selector");
				for (int j = 2; j <= recordSize; j++) {
					waitAndClickOnElement("checkBox", ".q-virtual-scroll__content .q-item:nth-child(" + j + ") .q-checkbox[role='checkbox']");
					log.info("Checkbox " + j + " is Checked");
				}
				waitAndClickOnElement("subscribeCheckBox", "#q-app .q-page .row:nth-child(" + i + ") #subscription-to-all-selector");
				Thread.sleep(1200);
			}
			Thread.sleep(1000);
			waitTillWebElementIsVisible("subscribeCheckBox","#q-app .q-page .row:nth-child(1) #subscription-to-all-selector");
			waitAndClickOnElement("subscribeCheckBox","#q-app .q-page .row:nth-child(1) #subscription-to-all-selector");
			recordSize = 5;
			waitTillWebElementIsVisible("checkBox", ".q-virtual-scroll__content .q-item:nth-child(1) .q-checkbox[role='checkbox']");
			for(int i=4; i<=recordSize; i++) {
				waitAndClickOnElement("checkBox", ".q-virtual-scroll__content .q-item:nth-child(" + i + ") .q-checkbox[role='checkbox']");
				log.info("Checkbox " + i + " is Checked");
			}
			waitAndClickOnElement("subscribeCheckBox","#q-app .q-page .row:nth-child(1) #subscription-to-all-selector" );
			Thread.sleep(1000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void contractEntityCodeCoverage(DataTable dataTable) {
		log.info("Creating Contact for Code Coverage");
		List<Map<String,String>> contractInfo=dataTable.asMaps(String.class, String.class);
		try {
			waitTillWebElementIsEnabled("addContract", "#q-app .q-page #navigation-expansion #contract-nav-add-btn");
			WaitUntilElementIsClickable(addContract);
			waitAndClickOnElement(addContract);
			waitUntilLoadingSpinnerIsGone("nlaFieldSpinner");
			WaitUntilElementIsClickable(contractName);
			WaitUntilElementIsClickable(contractPrincipalPosition);
			Thread.sleep(300);
			clickOnDropDownAndSelectValue("contractPrincipalPosition", contractPrincipalPosition,
					contractInfo.get(0).get("Principal Position"));
			clickOnDropDownToTypeAndSelectValue("contractLeaseBusinessUnit", contractLeaseBusinessUnit,
					contractInfo.get(0).get("Business Unit"));
			String companyCode = null;
			clickOnDropDownToTypeAndSelectValue("contractCompanyCode", contractCompanyCode,
					contractInfo.get(0).get("Company Code"));
		} catch (InterruptedException | IOException exception) {
			exception.printStackTrace();
		}
		contractName.click();
		sendingValueToWebElement("contractName", contractName,
				contractInfo.get(0).get("Name")+" "+ Constant.getTodaysDate());
		clickOnSubmitPopup("Submit / Add");
		log.info("Created Contact for Code Coverage");
	}

	public void contractDefinitionPageCodeCoverage(DataTable dataTable) {
		log.info("Data under Contact definition for Code Coverage");
		List<Map<String,String>> contractInfo=dataTable.asMaps(String.class, String.class);
		try {
			contractDefinitionTab.click();
			waitUntilLoadingSpinnerIsShown("nlaTabChange");
			waitUntilLoadingSpinnerIsGone("nlaTabChange");
			clickOnDropDownAndSelectValue("contractLeaseType", contractLeaseType,
					contractInfo.get(0).get("Lease Type"));
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			WaitUntilElementIsClickable(contractCurrency);
			clickOnDropDownToTypeAndSelectValue("contractCurrency", contractCurrency, contractInfo.get(0).get("Currency"));
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			sendingValueToWebElement("validityFrom",validityFrom,contractInfo.get(0).get("Validity From"));
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			sendingValueToWebElement("validityTo",validityTo,contractInfo.get(0).get("Validity To"));
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			sendingValueToWebElement("externalRef",externalReference,contractInfo.get(0).get("External CT Ref"));
			waitAndClickOnElement(contractDefinitionTab);
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			sendingValueToWebElement("internalRef",internalReference,contractInfo.get(0).get("Internal CT Ref"));
			waitAndClickOnElement(contractDefinitionTab);
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			clickOnDropDownAndSelectValue("contractCategory", contractCategory, contractInfo.get(0).get("Contract Category"));
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			sendingValueToWebElement("amendmentDate", amendentDate, contractInfo.get(0).get("Amendment Date"));
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			clickOnDropDownAndSelectValue("formOfLease", formLease, contractInfo.get(0).get("Form of Lease"));
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			clickOnDropDownAndSelectValue("jointVenture", jointVenture, contractInfo.get(0).get("Joint Venture"));
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			sendingValueToWebElement("description",description,contractInfo.get(0).get("Description"));
			waitAndClickOnElement(contractDefinitionTab);
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			clickOnDropDownToTypeAndSelectValue("leaseDepartment", leaseDepartment, contractInfo.get(0).get("Lease Department"));
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			clickOnDropDownToTypeAndSelectValue("leaseGroup", leaseGroup, contractInfo.get(0).get("Lease Group"));
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			clickOnDropDownAndSelectValue("group1", group1, contractInfo.get(0).get("Group 1"));
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			clickOnDropDownAndSelectValue("group2", group2, contractInfo.get(0).get("Group 2"));
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			sendingValueToWebElement("group3",group3,contractInfo.get(0).get("Group 3"));
			waitAndClickOnElement(contractDefinitionTab);
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			sendingValueToWebElement("group4",group4,contractInfo.get(0).get("Group 4"));
			waitAndClickOnElement(contractDefinitionTab);
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			sendingValueToWebElement("signingPerson",signingPerson,contractInfo.get(0).get("Signing Person"));
			waitAndClickOnElement(contractDefinitionTab);
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			sendingValueToWebElement("signingPlace",signingPlace,contractInfo.get(0).get("Signature Place"));
			waitAndClickOnElement(contractDefinitionTab);
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			sendingValueToWebElement("signingDate",signatureDate,contractInfo.get(0).get("Date Of Signature"));
			waitAndClickOnElement(contractDefinitionTab);
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
		} catch (InterruptedException | IOException exception) {
			exception.printStackTrace();
		}
		log.info("Data under Contact definition for Code Coverage is Done");
	}

	public void contractAccountingPageCodeCoverage(DataTable dataTable) {
		log.info("Data under Contact Accounting for Code Coverage");
		List<Map<String,String>> contractInfo=dataTable.asMaps(String.class, String.class);
		try {
			clickOnDropDownToTypeAndSelectValue("functionalArea", functionalArea, contractInfo.get(0).get("Functional Area"));
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			clickOnDropDownToTypeAndSelectValue("businessArea", businessArea, contractInfo.get(0).get("Business Area"));
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			clickOnDropDownToTypeAndSelectValue("segment", segment, contractInfo.get(0).get("Segment"));
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			if(!contractInfo.get(0).get("Network").equalsIgnoreCase("null")) {
				clickOnDropDownAndSelectValue("network", network, contractInfo.get(0).get("Network"));
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
			if(contractInfo.get(0).get("Track Cost").equalsIgnoreCase("True")){
				waitAndClickOnElement(trackCost);
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
			clickOnDropDownToTypeAndSelectValue("internalOrderType", internalOrderType, contractInfo.get(0).get("Order Type"));
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			clickOnDropDownToTypeAndSelectValue("internalOrder", internalOrder, contractInfo.get(0).get("Internal Order"));
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			if (!contractInfo.get(0).get("Payment Terms").equalsIgnoreCase("null")) {
				clickOnDropDownToTypeAndSelectValue("paymentTerms", paymentTerm, contractInfo.get(0).get("Payment Terms"));
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
			if (!contractInfo.get(0).get("Payment Block").equalsIgnoreCase("null")) {
				clickOnDropDownAndSelectValue("paymentBlock", paymentBlock, contractInfo.get(0).get("Payment Block"));
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
			if (!contractInfo.get(0).get("Payment Method").equalsIgnoreCase("null")) {
				clickOnDropDownAndSelectValue("paymentMethod", paymentMethod, contractInfo.get(0).get("Payment Method"));
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
		} catch (InterruptedException|IOException e) {e.printStackTrace();}


		log.info("Data under Contact Accounting for Code Coverage");
	}

	public void deleteMultiPartners(String termNumber) {
		try {
			if (termNumber.length() <= 1) {
				System.out.println("Index length is 1");
				int numberLength = termNumber.length();
				indexList = termNumber.split("", numberLength);
			} else {
				System.out.println("Index length is more than 1");
				int numberLength = ((termNumber.length() / 2) + 1);
				indexList = termNumber.split(",", numberLength);
			}
			for (int indexCount = 0; indexCount < indexList.length; indexCount++) {
				int nthChild = Integer.parseInt(indexList[indexCount]) + 1;
				//waitTillWebElementIsVisible(".q-page .q-table__container .q-table tbody tr:nth-child("+nthchild+") .q-td #select-partner-checkbox");
				driver.findElement(By.cssSelector(".q-page .q-table__container .q-table tbody tr:nth-child(" + nthChild + ") .q-td #select-partner-checkbox")).click();
			}

			waitTillWebElementIsVisible("RemovePartner", ".q-page .q-btn-group #remove-partners-btn");
			waitAndClickOnElement(driver.findElement(By.cssSelector(".q-page .q-btn-group #remove-partners-btn")));
			waitTillWebElementIsVisible("ConfirmationBox", ".q-card  #submit-btn");
			waitAndClickOnElement(driver.findElement(By.cssSelector(".q-card  #submit-btn")));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void copyContractWithName(String contName, String compCode) {
		log.info("User is going to copy the Contract with Name: " + contName);
		try {
			waitAndClickOnElement(contractContextBtn);
			waitTillWebElementIsVisible("menuItems", driver.findElement(By.cssSelector(".q-menu .q-item:nth-child(1)")));
			waitAndClickOnElement(copyContractBtn);
			waitTillWebElementIsVisible("contractName", contractName);
			handleWait(500);
			if(!compCode.equalsIgnoreCase("")){
				clickOnDropDownToTypeAndSelectValue("companyCode", contractCompanyCode, compCode);
			}
			sendingValueToWebElement("contractName", contractName, contName);
			clickOnSubmitPopup("Submit / Add");
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
			waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
			int dropDownOptionSize = clickOnDropDownAndGetDropDownSize("contractSelector", contractSelector);
			if(dropDownOptionSize==1){
				Assert.fail("Contract has not been copied Successfully");
			}
			log.info("Contract copied successfully");

		} catch (InterruptedException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void validationOfContractFields(DataTable dt) {
		log.info("Validation of fields at Contract!!!!");
		try {
			waitAndClickOnElement(contractDefinitionTab);
			waitTillWebElementIsVisible("businessUnit", "#q-app .q-page #business-unit");
			List<Map<String, String>> contractValidation = dt.asMaps(String.class, String.class);
			Map<String, String> expectedValues = contractValidation.get(0);
			Map<String, String> fieldSelectors = new HashMap<>();
			fieldSelectors.put("Business Unit", "#q-app .q-page #business-unit span");
			fieldSelectors.put("Company Code", "#q-app .q-page #company-code span");
			for (Map.Entry<String, String> entry : fieldSelectors.entrySet()) {
				String field = entry.getKey();
				String selector = entry.getValue();
				String expectedValue = expectedValues.get(field);
				if (expectedValue.matches("^\"+$")) {
					expectedValue = "";
				}
				if (!expectedValue.equalsIgnoreCase("null")) {
					String actualValue = driver.findElement(By.cssSelector(selector)).getText();
					if (actualValue.equalsIgnoreCase(expectedValue)) {
						log.info("Value from the feature line is matched with the value getting from application: " + field + " = " + actualValue);
					} else {
						org.junit.Assert.fail("Value from the feature line is not matching with the value getting from application: " + field + " = " + actualValue);
					}
				}
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	public void validatingContractPartnerAndAccountingData(){
		log.info("User is going to validate the data for Partners and Accounting Page of Contract!!!!");
        try {
            waitAndClickOnElement(contractPartnerTab);
			waitTillWebElementIsVisible("addPartner", addContractPartner);
			if(driver.findElements(By.cssSelector(".q-table .q-tr:nth-child(2)")).size()==1){
				log.info("Partner is added at Partner page");
			}
			else{
				log.info("Partner is not added at Partner page");
			}
			waitAndClickOnElement(contractAccountingTab);
			waitTillWebElementIsVisible("contractRate", contractRateField);
			if(driver.findElements(By.cssSelector(".q-table .q-tr")).size()==1){
				log.info("Cost Object is added at Accounting page");
			}
			else{
				log.info("Cost Object is not added at Accounting page");
			}

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
