package com.nakisa.nlaAutomation.pageObjects;

import com.nakisa.nlaAutomation.Constant;
import com.nakisa.nlaAutomation.stepDefinitions.MasterHooks;
import com.nakisa.nlaAutomation.utils.ReadInputValuesFromExcel;

import io.cucumber.datatable.DataTable;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;

@Slf4j
public class MasterAgreement_PageObject extends Common_BasePage_PageObject {

	private LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>>> inputsForNLA;

	public static ThreadLocal<String> testCaseName = new ThreadLocal<>();

	public @FindBy(css = "#q-app .q-page .q-tabs__content #master-agreement-tab") WebElement masterAgreementMenuTab;
	public @FindBy(css = "#q-app .q-page .landing-mla table tbody tr:nth-child(1)") WebElement masterAgreementTable;

	public @FindBy(css = "#q-app .q-toolbar button.q-btn--unelevated") WebElement addMasterAgreement;
	public @FindBy(css = ".q-dialog .q-card #year") WebElement masterAgreementYear;
	public @FindBy(css = ".q-dialog .q-card #lease-area") WebElement masterAgreementLeaseArea;
	public @FindBy(css = ".q-dialog .q-card #business-unit") WebElement masterAgreementBusinessUnit;
	public @FindBy(css = ".q-dialog .q-card #asset-class") WebElement masterAgreementAssetClass;
	public @FindBy(css = ".q-dialog .q-card #name-input") WebElement masterAgreementName;
	public @FindBy(css = "#q-app .q-page #description-textarea") WebElement description;
	public @FindBy(css = "#q-app .q-page #valid-from-input-input") WebElement validFrom;
	public @FindBy(css = "#q-app .q-page #valid-to-input-input") WebElement validTo;
	public @FindBy(css = "#q-app .q-page #target-value-input") WebElement target;
	public @FindBy(css = "#q-app .q-page #currency") WebElement currencyMla;
	public @FindBy(css = "#q-app .q-page #legal-jurisdiction-input") WebElement legalJurisdiction;
	public @FindBy(css = "#q-app .q-page #agreement-group") WebElement agreementGroup;
	public @FindBy(css = "#q-app .q-page #company-code") WebElement companyCode;
	public @FindBy(css = "#q-app .q-page #department") WebElement department;
	public @FindBy(css = "#q-app .q-page #lease-group") WebElement leaseGroup;
	public @FindBy(css = "#q-app .q-page #signing-person-input") WebElement signingPerson;
	public @FindBy(css = "#q-app .q-page #place-of-signature-input") WebElement placeSignature;
	public @FindBy(css = "#q-app .q-page #date-of-signature-input-input") WebElement dateSignature;
	public @FindBy(css = "#q-app .q-page #internal-asset-class") WebElement assetClass;
	public @FindBy(css = "#q-app .q-page #lease-area") WebElement leaseArea;
	public @FindBy(css = "#q-app .q-page #business-unit") WebElement businessUnit;
	public @FindBy(css = "#q-app .q-page .q-stepper__tab--active") WebElement currentWorkingTab;
	public @FindBy(css = "#q-app .q-page #partners-step") WebElement masterAgreementPartnerTab;
	public @FindBy(css = "#q-app .q-page .absolute #add-partners-btn") WebElement addMasterAgreementPartner;
	public @FindBy(css = ".q-dialog .q-card #partner-role-filter") WebElement selectMasterAgreementPartnerRole;
	public @FindBy(css = ".q-dialog .q-card #select-partner-checkbox") WebElement selectMLAPartnerOption;
	public @FindBy(css = "#q-app .q-page #definition-step") WebElement masterAgreementDefinitionTab;
	public @FindBy(css = "#mla-context-menu-btn")WebElement masterAgreementContextBtn;
	public @FindBy(css = ".q-menu #master-agreement-item")WebElement copyMasterAgreementBtn;
	public @FindBy(css = "#contextMenu-menu-btn") WebElement landingPageContextMenuBtn;

	public MasterAgreement_PageObject() {
		super();
		log.info("Driver is inside this class: " + this.getClass().getSimpleName());
		PageFactory.initElements(driver, this);
	}

	public void readTCInputs(String testCaseNumber, String sheetName) {
		try {
			inputsForNLA = ReadInputValuesFromExcel.readExcelInputs(testCaseNumber, sheetName);
			testCaseName.set(testCaseNumber);
//			 System.out.println(inputsForNLA);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	public void readBaseTCInputs(String testCaseNumber) {
		testCaseName.set(testCaseNumber);
	}
	public LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>>> getInputValues() {
		return inputsForNLA;
	}

	public void addMasterAgreement() {
		log.info("Creating a new master agreement");

		if (MasterHooks.configurationProperties.get().getRunAutomationOnNCPBuild()) {
			waitUntilLoadingSpinnerIsShown("nlaLoadingPage");
			waitUntilLoadingSpinnerIsGone("nlaLoadingPage");
		}
//		else {
//			waitTillWebElementIsVisible("LoginButton", "body form input[value=Login]");
//			driver.findElement(By.cssSelector("body form input[value=Login]")).click();
//			waitUntilLoadingSpinnerIsShown("nlaLoadingPage");
//			waitUntilLoadingSpinnerIsGone("nlaLoadingPage");
//		}
		waitTillWebElementIsVisible("masterAgreementTab", masterAgreementMenuTab);
		try {
			waitAndClickOnElement(masterAgreementMenuTab);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		removeAIAssistant();
    try {
      waitAndClickOnElement(masterAgreementMenuTab);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    waitUntilLoadingSpinnerIsShown("nlaLoadingPage");
//		waitUntilLoadingSpinnerIsGone("nlaLoadingPage");
		waitTillWebElementIsVisible("addMasterAgreement", addMasterAgreement);
		WaitUntilElementIsClickable(addMasterAgreement);
    try {
      waitAndClickOnElement(addMasterAgreement);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    try {
			String leaseArea = getValuesFromExcel("Inception","Master Agreement Level","Lease Area",0);
			clickOnDropDownToTypeAndSelectValue("masterAgreementLeaseArea", masterAgreementLeaseArea, leaseArea);
			waitAndClickOnElement(masterAgreementName);
		} catch (InterruptedException | IOException exception) {
			exception.printStackTrace();
		}
		sendingValueToWebElement("masterAgreementName", masterAgreementName,
				getValuesFromExcel("Inception","Master Agreement Level","Master Agreement Name",0)+" "+ Constant.getTodaysDate());
		//Re-entering the name
		if (driver.findElements(By.cssSelector(".q-dialog .q-card #submit-btn[aria-disabled='true']")).size() == 1) {
				masterAgreementName.click();
				masterAgreementName.sendKeys(getValuesFromExcel("Inception","Master Agreement Level","Master Agreement Name",0) + " " + Constant.getTodaysDate());
		}
		clickOnSubmitPopup("Submit / Add");
		waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
		waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
		//Storing the MLA ID
		String idPath = driver.findElement(By.cssSelector("#q-app .q-page .q-item.col-auto:nth-child(1) .q-item__label:nth-child(2)")).getText();
		MasterHooks.searchMLAID.set(idPath);
		log.info("Master Agreement is created successfully");
	}

	public void changeTabAndValidate(String entityLevel){
		log.info("entering the "+ entityLevel + " Partners Tab to validate Contact");
		try {
			waitAndClickOnElement("partnerTab","#q-app .q-stepper__header #partners-step");
			waitUntilLoadingSpinnerIsShown("nlaTabChange");
			waitUntilLoadingSpinnerIsGone("nlaTabChange");
			int contactSize = driver.findElements(By.cssSelector("tbody tr td .q-chip .q-chip__content")).size();
			List<WebElement> contact = driver.findElements(By.cssSelector("tbody tr td .q-chip .q-chip__content"));
			for(int index=0;index<contactSize;index++){
				if(contact.get(index).getText().equalsIgnoreCase("SmokeTC015")){
					log.info("Contact is Present at "+ entityLevel);
					break;
				}
				if(index==(contactSize-1)){
					Assert.fail("Contact is not visible at Partners Tab.");
				}
			}
		} catch (InterruptedException e) {e.printStackTrace();}
	}

	public void addMasterAgreement(DataTable dataTable) {
		log.info("Creating a new master agreement for Code Coverage");
		List<Map<String,String>> mlaInfo=dataTable.asMaps(String.class, String.class);
		if (MasterHooks.configurationProperties.get().getRunAutomationOnNCPBuild()) {
			waitUntilLoadingSpinnerIsShown("nlaLoadingPage");
			waitUntilLoadingSpinnerIsGone("nlaLoadingPage");
		}
		waitTillWebElementIsVisible("masterAgreementTab", masterAgreementMenuTab);
		masterAgreementMenuTab.click();
		waitUntilLoadingSpinnerIsShown("nlaLoadingPage");
		waitTillWebElementIsVisible("addMasterAgreement", addMasterAgreement);
		WaitUntilElementIsClickable(addMasterAgreement);
		addMasterAgreement.click();
		try {
			if(!mlaInfo.get(0).get("Year").equalsIgnoreCase("null")) {
				clickOnDropDownAndSelectValue("masterAgreementYear", masterAgreementYear,
						mlaInfo.get(0).get("Year"));
			}
			if(!mlaInfo.get(0).get("Lease Area").equalsIgnoreCase("null")) {
				clickOnDropDownToTypeAndSelectValue("masterAgreementLeaseArea", masterAgreementLeaseArea,
						mlaInfo.get(0).get("Lease Area"));
			}
			if(!mlaInfo.get(0).get("Business Unit").equalsIgnoreCase("null")) {
				clickOnDropDownToTypeAndSelectValue("masterAgreementBusinessUnit", masterAgreementBusinessUnit,
						mlaInfo.get(0).get("Business Unit"));
			}
			if(!mlaInfo.get(0).get("Asset Class").equalsIgnoreCase("null")) {
				clickOnDropDownToTypeAndSelectValue("masterAgreementAssetClass", masterAgreementAssetClass,
						mlaInfo.get(0).get("Asset Class"));
			}

			masterAgreementName.click();
			sendingValueToWebElement("masterAgreementName", masterAgreementName,
					mlaInfo.get(0).get("MLA Name") + " " + Constant.getTodaysDate());
			clickOnSubmitPopup("Submit / Add");
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			if(!mlaInfo.get(0).get("valid From").equalsIgnoreCase("null")) {
				sendingValueToWebElement("validFrom", validFrom, mlaInfo.get(0).get("valid From"));
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
			if(!mlaInfo.get(0).get("Valid To").equalsIgnoreCase("null")) {
				sendingValueToWebElement("validTo", validTo, mlaInfo.get(0).get("Valid To"));
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
			if(!mlaInfo.get(0).get("Asset Class").equalsIgnoreCase("null")) {
				if (driver.findElements(By.cssSelector("#q-app .q-page #internal-asset-class .q-icon")).size() == 1) {
					waitAndClickOnElement(driver.findElement(By.cssSelector("#q-app .q-page #internal-asset-class .q-icon")));
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
					clickOnDropDownToTypeAndSelectValue("internalAssetClass", assetClass,
							mlaInfo.get(0).get("Asset Class"));
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				} else {
					clickOnDropDownToTypeAndSelectValue("internalAssetClass", assetClass,
							mlaInfo.get(0).get("Asset Class"));
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				}
			}
			if(!mlaInfo.get(0).get("Agreement Group").equalsIgnoreCase("null")) {
				clickOnDropDownToTypeAndSelectValue("agreementGroup", agreementGroup, mlaInfo.get(0).get("Agreement Group"));
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
			if(!mlaInfo.get(0).get("Target Value").equalsIgnoreCase("null")) {
				sendingValueToWebElement("targetValue", target, mlaInfo.get(0).get("Target Value"));
				waitAndClickOnElement(masterAgreementDefinitionTab);
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
			if(!mlaInfo.get(0).get("Legal Jurisdiction").equalsIgnoreCase("null")) {
				sendingValueToWebElement("legalJurisdiction", legalJurisdiction, mlaInfo.get(0).get("Legal Jurisdiction"));
				waitAndClickOnElement(masterAgreementDefinitionTab);
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
			if(!mlaInfo.get(0).get("Business Unit").equalsIgnoreCase("null")) {
				if (driver.findElements(By.cssSelector("#q-app .q-page #business-unit .q-icon")).size() == 1) {
					waitAndClickOnElement(driver.findElement(By.cssSelector("#q-app .q-page #business-unit .q-icon")));
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
					clickOnDropDownToTypeAndSelectValue("businessUnit", businessUnit,
							mlaInfo.get(0).get("Business Unit"));
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				} else {
					clickOnDropDownToTypeAndSelectValue("businessUnit", businessUnit,
							mlaInfo.get(0).get("Business Unit"));
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				}
			}
			if(!mlaInfo.get(0).get("Company Code").equalsIgnoreCase("null")) {
				clickOnDropDownToTypeAndSelectValue("companyCode", companyCode,
						mlaInfo.get(0).get("Company Code"));
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
			if(!mlaInfo.get(0).get("Currency").equalsIgnoreCase("null")) {
				clickOnDropDownToTypeAndSelectValue("currency", currencyMla,
						mlaInfo.get(0).get("Currency"));
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
			if(!mlaInfo.get(0).get("lease Department").equalsIgnoreCase("null")) {
				clickOnDropDownToTypeAndSelectValue("leaseDepartment", department,
						mlaInfo.get(0).get("lease Department"));
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
				if(!mlaInfo.get(0).get("Lease Group").equalsIgnoreCase("null")) {
					clickOnDropDownToTypeAndSelectValue("leseGroup", leaseGroup,
							mlaInfo.get(0).get("Lease Group"));
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				}
			if(!mlaInfo.get(0).get("Signing Person").equalsIgnoreCase("null")) {
				sendingValueToWebElement("signingPerson", signingPerson, mlaInfo.get(0).get("Signing Person"));
				waitAndClickOnElement(masterAgreementDefinitionTab);
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
			if(!mlaInfo.get(0).get("Place of Signature").equalsIgnoreCase("null")) {
				sendingValueToWebElement("signingPlace", placeSignature, mlaInfo.get(0).get("Place of Signature"));
				waitAndClickOnElement(masterAgreementDefinitionTab);
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
			if(!mlaInfo.get(0).get("Date of Signature").equalsIgnoreCase("null")) {
				sendingValueToWebElement("dateSigning", dateSignature, mlaInfo.get(0).get("Date of Signature"));
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
		} catch (InterruptedException | IOException exception) {
			exception.printStackTrace();
		}
		log.info("Master Agreement is created successfully");
	}

	public void deleteEntity(String level) {
		log.info("Going to delete "+level);
		try {
			waitAndClickOnElement(driver.findElement(By.cssSelector("#q-app .q-toolbar Button[id$='context-menu-btn']")));
			waitTillWebElementIsVisible("contextOptions",".q-menu .q-item#lease-component-item");
			waitAndClickOnElement(driver.findElement(By.cssSelector(".q-menu .q-item:nth-child(2)")));
			waitTillWebElementIsVisible("contextOptions",".q-dialog .q-card");
			clickOnSubmitPopup("Submit / Add");
			if(!level.equalsIgnoreCase("Master Agreement")){
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
		} catch (InterruptedException e) {e.printStackTrace();}
		log.info(level+" is deleted");
	}

	public void addPartners() {
		try {

			if(!currentWorkingTab.getText().equalsIgnoreCase("Partners")){
				masterAgreementPartnerTab.click();
				waitUntilLoadingSpinnerIsShown("nlaTabChange");
				waitUntilLoadingSpinnerIsGone("nlaTabChange");
			}
			int totalNumberOfPartnerRole = getInputValues().get("Inception").get("Master Agreement Level").get("Partner Role").size();
			for (int partnerCounter = 0; partnerCounter < totalNumberOfPartnerRole; partnerCounter++) {
				waitTillWebElementIsVisible("addContractPartner", addMasterAgreementPartner);
				addMasterAgreementPartner.click();
				clickOnDropDownToTypeAndSelectValue("selectContractPartnerRole", selectMasterAgreementPartnerRole,
						getValuesFromExcel("Inception","Master Agreement Level","Partner Role",partnerCounter));
				c_CreationPage.get().selectPartner(getValuesFromExcel("Inception","Master Agreement Level","Partner Name",partnerCounter));

				clickOnSubmitPopup("Submit / Add");
			}
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			log.info("Partner has been added Successfully");
		} catch (InterruptedException | IOException exception) {
			exception.printStackTrace();
		}
	}
	public void copyMasterAgreementWithNameAndLeaseArea(String mlaName, String fieldType){
		log.info("User is going to copy the Master Agreement with Name: " + mlaName);
        try {
            waitAndClickOnElement(masterAgreementContextBtn);
			waitTillWebElementIsVisible("menuItems", driver.findElement(By.cssSelector(".q-menu .q-item:nth-child(1)")));
			waitAndClickOnElement(copyMasterAgreementBtn);
			waitTillWebElementIsVisible("masterAgreementName", masterAgreementName);
			sendingValueToWebElement("masterAgreementName", masterAgreementName, mlaName);
			if(!fieldType.equalsIgnoreCase("")){
				clickOnDropDownToTypeAndSelectValue("leaseArea", masterAgreementLeaseArea, fieldType);
			}
			clickOnSubmitPopup("Submit / Add");
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
			waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
			log.info("MLA copied successfully");

        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
	public void validationOfMasterAgreementFields(DataTable dt) {
		log.info("Validation of fields at Master Agreement!!!!");
		List<Map<String, String>> mlaValidation = dt.asMaps(String.class, String.class);
		Map<String, String> expectedValues = mlaValidation.get(0);
		Map<String, String> fieldSelectors = new HashMap<>();
		fieldSelectors.put("Asset Class", "#q-app .q-page #internal-asset-class span");
		fieldSelectors.put("Business Unit", "#q-app .q-page #business-unit span");
		fieldSelectors.put("Company Code", "#q-app .q-page #company-code span");
		fieldSelectors.put("Lease Department", "#q-app .q-page #department span");
		fieldSelectors.put("Lease Group", "#q-app .q-page #lease-group span");

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
					Assert.fail("Value from the feature line is not matching with the value getting from application: " + field + " = " + actualValue);
				}
			}
		}
	}
	public void SelectingRecordFromLandingPageToCopy(String entityName) {
		log.info("User is going to select the record from " + entityName + " landing page to copy");
		try {
			switch (entityName) {
				case "Master Agreement":
					waitTillWebElementIsVisible("checkboxes", driver.findElement(By.cssSelector(".q-table .q-tr:nth-child(1) .q-td:nth-child(1) .q-checkbox ")));
					int sizeOfCheckbox = driver.findElements(By.cssSelector(".q-table .q-tr .q-td:nth-child(1) .q-checkbox")).size() - 18;
					for (int i = 1; i <= sizeOfCheckbox; i++) {
						waitAndClickOnElement(driver.findElement(By.cssSelector(".q-table .q-tr:nth-child(" + i + " ) .q-td .q-checkbox")));
					}
					waitAndClickOnElement(landingPageContextMenuBtn);
					waitTillWebElementIsVisible("copyContextMenu", "#contextMenu-menu-item:nth-child(1)");
					waitAndClickOnElement(driver.findElement(By.cssSelector("#contextMenu-menu-item:nth-child(1)")));
					waitTillWebElementIsVisible("copyButtonOnPopup", "#submit-btn");
					clickOnSubmitPopup("Submit / Add");
					waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
					waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
					log.info(entityName + " copied successfully from landing page");
					break;

				case "Contract":
					waitTillWebElementIsVisible("checkboxes", driver.findElement(By.cssSelector(".q-table .q-tr:nth-child(1) .q-td:nth-child(1) .q-checkbox ")));
					sizeOfCheckbox = driver.findElements(By.cssSelector(".q-table .q-tr .q-td:nth-child(1) .q-checkbox")).size() - 18;
					for (int i = 1; i <= sizeOfCheckbox; i++) {
						waitAndClickOnElement(driver.findElement(By.cssSelector(".q-table .q-tr:nth-child(" + i + " ) .q-td .q-checkbox")));
					}
					waitAndClickOnElement(landingPageContextMenuBtn);
					waitTillWebElementIsVisible("copyContextMenu", "#contextMenu-menu-item:nth-child(1)");
					waitAndClickOnElement(driver.findElement(By.cssSelector("#contextMenu-menu-item:nth-child(1)")));
					waitTillWebElementIsVisible("toggleBtnCopy", ".q-toggle .q-toggle__inner");
					waitAndClickOnElement("toggleBtnCopy", ".q-toggle .q-toggle__inner");
					waitTillWebElementIsVisible("copyButtonOnPopup", "#submit-btn");
					clickOnSubmitPopup("Submit / Add");
					waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
					waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
					log.info(entityName + " copied successfully from landing page");
					break;

					case "Lease Component":
					waitTillWebElementIsVisible("checkboxes", driver.findElement(By.cssSelector(".q-table .q-tr:nth-child(1) .q-td:nth-child(1) .q-checkbox ")));
					sizeOfCheckbox = driver.findElements(By.cssSelector(".q-table .q-tr .q-td:nth-child(1) .q-checkbox")).size() - 18;
					for (int i = 1; i <= sizeOfCheckbox; i++) {
						waitAndClickOnElement(driver.findElement(By.cssSelector(".q-table .q-tr:nth-child(" + i + " ) .q-td .q-checkbox")));
					}
					waitAndClickOnElement(landingPageContextMenuBtn);
					waitTillWebElementIsVisible("copyContextMenu", "#contextMenu-menu-item:nth-child(1)");
					waitAndClickOnElement(driver.findElement(By.cssSelector("#contextMenu-menu-item:nth-child(1)")));
					waitTillWebElementIsVisible("toggleBtnCopy", ".q-toggle .q-toggle__inner");
					waitAndClickOnElement("toggleBtnCopy", ".q-toggle .q-toggle__inner");
					waitTillWebElementIsVisible("copyButtonOnPopup", "#submit-btn");
					clickOnSubmitPopup("Submit / Add");
					waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
					waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
					log.info(entityName + " copied successfully from landing page");

			}
		}catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
