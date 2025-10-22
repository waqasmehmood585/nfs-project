package com.nakisa.nlaAutomation.pageObjects;

import com.nakisa.nlaAutomation.stepDefinitions.MasterHooks;
import com.nakisa.nlaAutomation.validations.AG_Classifications_Validation;
import com.nakisa.nlaAutomation.validations.AG_PostingDocument_Validation;
import com.nakisa.nlaAutomation.validations.metaModel.AG_Classifications_MetaModel;
import com.nakisa.nlaAutomation.validations.metaModel.AG_JournalPosting_MetaModel;
import io.cucumber.datatable.DataTable;
import lombok.extern.slf4j.Slf4j;

import org.apache.directory.api.util.Strings;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

@Slf4j
public class C_Activation_Group_PageObject extends Common_BasePage_PageObject {

	public @FindBy(css = ".q-page .absolute #name-input") WebElement activationGroupName;
	public @FindBy(css = "#q-app .q-page .absolute #contract-rate-input") WebElement contractRateField;
	public @FindBy(css = "#q-app .q-page .absolute #use-ibr-rate") WebElement contractIBRRate;
	public @FindBy(css = "#q-app .q-page #terms-conditions-step") WebElement termsAndConditonsAGTab;
	public @FindBy(css = "#q-app .q-page #unit-list-step") WebElement unitAGTab;
	public @FindBy(css = "#q-app .q-page .q-table .q-tr:nth-child(2) .q-td:nth-child(6)") WebElement unitAGStatus;
	public @FindBy(css = ".q-page .absolute tr:nth-child(2) #tnc-exercise") WebElement enableExercise;
	public @FindBy(css = "#q-app .q-page #classifications-step") WebElement classificationAGTab;
	public @FindBy(css = "#q-app .q-page #unit-list-step") WebElement unitListAGTab;

	//For Lessor
	public @FindBy(css = "#q-app .q-page #event-id-unit-ship-btn") WebElement unitListShipButtonAGTab;
	public @FindBy(css = "#q-app .q-page #event-id-unit-deliver-btn") WebElement unitListDeliverButtonAGTab;
	public @FindBy(css = "#q-app .q-page #event-id-unit-return-btn") WebElement unitListReturnButtonAGTab;
	public @FindBy(css = "#q-app .q-page #event-id-unit-send-to-initial-btn") WebElement sendToInitialBtnAG;
	public @FindBy(css = "#q-app .q-page #event-id-unit-received-btn") WebElement receivedBtnAG;
	public @FindBy(css = ".q-dialog #submit-btn") WebElement submitBtnUnitListAG;
	public @FindBy(css = "#q-app .q-page #event-id-unit-activate-btn") WebElement activateBtnAG;
	public @FindBy(css = "#q-app .q-page #unit-mass-action") WebElement unitExecute;
	public @FindBy(css = ".q-card .form-input #ag-units-bulk-action") WebElement bulkAction;
	public @FindBy(css = "#q-app .q-page .absolute .q-table__bottom .q-field__control") WebElement recordPerPageBtn;
	public @FindBy(css = ".desktop .q-menu .q-virtual-scroll__content") WebElement recordPerPageOption;
	public @FindBy(css = ".q-page .absolute .q-item") WebElement pageInfo;
	public @FindBy(css = ".q-page #event-id-ag-generate-schedules-btn") WebElement generateSchedulesAG;
	public @FindBy(css = ".q-page #event-id-ag-send-to-assessment-btn") WebElement sendAssessmentAG;
	public @FindBy(css = "#q-app .q-page #accounting-step") WebElement accountingAGTab;
	public @FindBy(css = "#q-app .q-page .absolute #indexation-type") WebElement agAccountingIndexationTypeLease;
	public @FindBy(css = "#q-app .q-page .absolute #current-index-level-input") WebElement agAccountingCurrentIndexLease;
	public @FindBy(css = "#q-app .q-page .absolute #conditionally-indexed") WebElement agAccountingConditionalIndexLease;
	public @FindBy(css = "#q-app .q-page .absolute #indexation-level-percentage-change-lower-bound-input") WebElement agAccountingConditionalIndexLeaseMin;
	public @FindBy(css = "#q-app .q-page .absolute #indexation-level-percentage-change-upper-bound-input") WebElement agAccountingConditionalIndexLeaseMax;
	public @FindBy(css = "#q-app .q-page .absolute #consumer-price-index-category") WebElement agAccountingCPICategoryLease;
	public @FindBy(css = ".q-page .q-field #reference-date-input-input") WebElement agAccountingReferenceDateLease;
	public @FindBy(css = "#q-app .q-page .absolute #indexation-type-non-lease") WebElement agAccountingIndexationTypeNonLease;
	public @FindBy(css = "#q-app .q-page .absolute #current-index-level-non-lease-input") WebElement agAccountingCurrentIndexNonLease;
	public @FindBy(css = "#q-app .q-page .absolute #consumer-price-index-category-non-lease") WebElement agAccountingCPICategoryNonLease;
	public @FindBy(css = ".q-page .q-field #reference-date-non-lease-input-input") WebElement agAccountingReferenceDateNonLease;
	public @FindBy(css = "#q-app .q-page .absolute #conditionally-indexed-nonlease") WebElement agAccountingConditionalIndexNonLease;
	public @FindBy(css = "#q-app .q-page .absolute #indexation-level-percentage-change-lower-bound-non-lease-input") WebElement agAccountingConditionalIndexNonLeaseMin;
	public @FindBy(css = "#q-app .q-page .absolute #indexation-level-percentage-change-upper-bound-non-lease-input") WebElement agAccountingConditionalIndexNonLeaseMax;
	public @FindBy(css = "#q-app .q-page tbody tr:nth-child(2) td:nth-child(2) #ias-useful-life-year-input") WebElement useFulLifeYearIAS;
	public @FindBy(css = "#q-app .q-page tbody tr:nth-child(2) td:nth-child(2) #ias-useful-life-month-input") WebElement useFulLifeMonthIAS;
	public @FindBy(css = "#q-app .q-page tbody tr:nth-child(2) td:nth-child(2) #ias-useful-life-day-input") WebElement useFulLifeDayIAS;
	public @FindBy(css = "#q-app .q-page tbody tr:nth-child(2) td:nth-child(3) #gaap-useful-life-year-input") WebElement useFulLifeYearGAAP;
	public @FindBy(css = "#q-app .q-page tbody tr:nth-child(2) td:nth-child(3) #gaap-useful-life-month-input") WebElement useFulLifeMonthGAAP;
	public @FindBy(css = "#q-app .q-page tbody tr:nth-child(2) td:nth-child(3) #gaap-useful-life-day-input") WebElement useFulLifeDayGAAP;
	public @FindBy(css = "#q-app .q-page tbody tr:nth-child(3) td:nth-child(2) #useful-life-year-input") WebElement useFulLifeYearIASLessor;
	public @FindBy(css = "#q-app .q-page tbody tr:nth-child(3) td:nth-child(2) #useful-life-month-input") WebElement useFulLifeMonthIASLessor;
	public @FindBy(css = "#q-app .q-page tbody tr:nth-child(3) td:nth-child(3) #useful-life-year-input") WebElement useFulLifeYearGAAPLessor;
	public @FindBy(css = "#q-app .q-page tbody tr:nth-child(3) td:nth-child(3) #useful-life-month-input") WebElement useFulLifeMonthGAAPLessor;
	public @FindBy(css = "#q-app .q-page tr:nth-child(1) td:nth-child(3) #confirmed-classification") WebElement confirmClassificationGAAP;
	public @FindBy(css = "#q-app .q-page #context-menu-title-btn") WebElement contextMenuAG;
	public @FindBy(css = ".q-menu #revert-revision") WebElement revertAGButton;
	public @FindBy(css = ".q-dialog .q-card #reversal-reason-selector-dialog") WebElement reversalReasonDropdown;
	public @FindBy(css = ".q-dialog .q-card #document-date-input-input") WebElement documentDate;
	public @FindBy(css = ".q-dialog .q-card #posting-date-input-input") WebElement postingDate;

	// AG Split
	public @FindBy(css = ".q-menu #split-ag-item") WebElement splitAGButton;
	public @FindBy(css = ".q-dialog .q-card #add-new-ag-btn") WebElement addAGButton;
	public @FindBy(css = ".q-dialog .q-card .q-tr:nth-child(3) #ag-name-input-input") WebElement activationGroupSplitName;

	public @FindBy(css = ".q-dialog .q-card #next-btn") WebElement nextButton;

	// AG Event

	public @FindBy(css = "#q-app .q-page #context-menu-event") WebElement addEventBtn;
	public @FindBy(css = ".q-dialog .q-field #name-input") WebElement activationGroupEventName;
	public @FindBy(css = ".q-dialog #update-contract-rate-ibr") WebElement contractRateEvent;
	public @FindBy(css = ".q-dialog[role='dialog'] #modification-date-input-input") WebElement modificationDateAG;
	public @FindBy(css = ".q-dialog #modification-reasons") WebElement modificationReason;
	public @FindBy(css = ".q-page .q-field #rou-end-date-input-input") WebElement rouEndDateValueDefinition;
	public @FindBy(css = ".q-dialog .dialog-body [v-css-selectors='asset'][tabindex='0']") WebElement IASTab;
	public @FindBy(css = ".q-dialog .dialog-body .q-tab:nth-child(1)[role='tab']") WebElement Tab0;
	public @FindBy(css = ".q-dialog .dialog-body .q-tab:nth-child(2)[role='tab']") WebElement Tab1;
	public @FindBy(css = ".q-dialog .dialog-body .row #is-termination") WebElement terminateEventBtn;
	public @FindBy(css = ".q-dialog .dialog-body .row #apply-indexation-lease") WebElement applyIndexationOnLeaseTerm;
	public @FindBy(css = ".q-dialog .dialog-body .row #apply-indexation-non-lease") WebElement applyIndexationOnNonLeaseTerm;
	public @FindBy(css = ".q-page-container #use-non-lease-on-indexation") WebElement useNonLeaseOnIndexationI;
	public @FindBy(css = ".q-dialog[role='dialog'] #indexation-treatment-type") WebElement indexationTreatmentType;
	public @FindBy(css = ".q-dialog[role='dialog'] #indexation-date-type") WebElement indexationDateType;
	public @FindBy(css = ".q-dialog[role='dialog'] #indexation-start-date-input-input") WebElement leaseIndexationDate;
	public @FindBy(css = ".q-dialog[role='dialog'] #indexation-date-type-non-lease") WebElement NonLeaseIndexationDateType;
	public @FindBy(css = ".q-dialog[role='dialog'] #indexation-start-date-non-lease-input-input") WebElement NonLeaseIndexationDate;

	public @FindBy(css = ".q-dialog .row #termination-type") WebElement terminationReasonField;
	public @FindBy(css = ".q-dialog .row #casualty-penalty-input") WebElement penaltyAmountField;
	public @FindBy(css = ".q-dialog .q-field #decrease-in-term-ifrs-input") WebElement assetDecreaseAmountIASTerms;
	public @FindBy(css = ".q-dialog .q-field #nbv-on-event-date-input") WebElement NBVonEventDate;
	public @FindBy(css = ".q-dialog .q-field #recoverable-amount-input") WebElement RecoverableAmount;
	public @FindBy(css = ".q-dialog .q-card .q-panel #decrease-in-asset-input") WebElement assetDecreaseAmountGAAPAsset;
	public @FindBy(css = ".q-dialog .q-card .q-panel #decrease-in-asset-input") WebElement assetDecreaseAmountIASAsset;
	public @FindBy(css = ".q-dialog .q-field #gain-amount-input") WebElement GainAmount;
	public @FindBy(css = "#q-app .q-page #revision-btn") WebElement revisionButton;
	public @FindBy(css = ".q-page .q-field [role='presentation'].mdi-calendar") WebElement rouEndDateCalender;
	public @FindBy(css = "#q-app .q-page #definition-step") WebElement definitionAGTab;
	public @FindBy(css = ".q-page #asset-definition-form-expansion #fair-market-value-input") WebElement fmv_Unit;
	public @FindBy(css = ".q-page #asset-definition-form-expansion #residual-value-input") WebElement salvageUnitValue;

	// Charge
	public @FindBy(css = "#q-app .q-layout #charge-list-step") WebElement Chargestab;

	public @FindBy(css = "#q-app .q-page .q-btn--actionable#add-btn") WebElement addChargeButton;
	public @FindBy(css = ".q-dialog .q-card #name-input") WebElement chargeName;
	public @FindBy(css = ".q-dialog .q-card #non-lease-category") WebElement expenseCategory;
	public @FindBy(css = ".q-dialog .q-card #due-date-input-input") WebElement DueDate;
	public @FindBy(css = ".q-dialog .q-card #amount-input") WebElement chargeAmount;
	public @FindBy(css = ".q-dialog .q-card #unit") WebElement Unit;
	public @FindBy(css = ".q-page-container .q-card #event-id-ag-activate-btn") WebElement draftAGActivate;
	public @FindBy(css = ".qcard-dialogue .q-card__section #document-date-input-input") WebElement documentDateInput;
	public @FindBy(css = ".qcard-dialogue .q-card__section #posting-date-input-input") WebElement postingDateInput;
	public @FindBy(css = ".q-dialog .q-card #close-btn") WebElement cancelBtn;
	public @FindBy(css = "#q-app .q-drawer .q-page .q-item #activation-group-selector") WebElement activationGroupSelector;
	public @FindBy(css = ".q-page .form-input.col-3 #purchasing-organization") WebElement purchaseOrganization;
	public @FindBy(css = ".q-page .form-input.col-3 #purchasing-order") WebElement purchaseOrder;
	public @FindBy(css = "#q-app .q-page .q-table__container #term-state-selector") WebElement termState;
	public @FindBy(css = ".qcard-dialogue #base-indexation-rate-input")WebElement baseIndexRate;
	public @FindBy(css = ".qcard-dialogue #base-indexation-date-input-input")WebElement baseIndexDate;
	public @FindBy(css = ".qcard-dialogue #reference-indexation-rate-input")WebElement refIndexRate;
	public @FindBy(css = ".qcard-dialogue #reference-indexation-date-input-input")WebElement refIndexDate;
	public @FindBy(css = "#q-app  #indexation-treatment-type")WebElement gaapIndexationType;
	public @FindBy(css = ".q-td #edit-btn") WebElement termEditBtn;
	public @FindBy(css = ".q-card__section .q-field #base-indexation-rate-input") WebElement baseIndexRateInTerm;
	private String[] indexList = null;
	String splitAGName;
    static String[] unitIds= null;
	String[] chargeIds= null;
	MasterAgreement_PageObject masterAgreementPageObject = masterAgreement_PageObject.get();

	public C_Activation_Group_PageObject() {
		super();
		log.info("Driver is inside this class: " + this.getClass().getSimpleName());
		PageFactory.initElements(driver, this);
	}

//	public void fillTermsAndConditionsAG() {
//		log.info("Going to fill the Terms & Conditions of Activation Group");
//		waitTillWebElementIsVisible("activationGroupName",activationGroupName);
//		WaitUntilElementIsClickable(termsAndConditonsAGTab);
//		actionMoveAndClick("termsAndConditonsAGTab",termsAndConditonsAGTab);
//		waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
//		waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
//		WaitUntilElementIsClickable(enableExercise);
//		actionMoveAndClick("enableExercise",enableExercise);
//		waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
//		waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
//		log.info("Completed filling the Terms and Conditions of Activation Group");
//	}

	public void fillTermsAndConditionsOfAg() {
		log.info("Clicking on Terms And Conditions");
//		waitTillWebElementIsVisible("activationGroupName",activationGroupName);
		waitTillWebElementIsVisible("termsAndConditonsAGTab", termsAndConditonsAGTab);
		try {
			waitAndClickOnElement(termsAndConditonsAGTab);
		} catch (InterruptedException e) {e.printStackTrace();}
		waitUntilLoadingSpinnerIsShown("nlaTabChange");
		waitUntilLoadingSpinnerIsGone("nlaTabChange");
		if(ImportExport_PageObject.lCExistingTerms.get()==null) {
			int recordBeforeImport;
			if (driver.findElements(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).size() == 0) {
				recordBeforeImport = 0;
			} else {
				String records = driver.findElement(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
				recordBeforeImport = Integer.parseInt(records.substring(records.indexOf("f") + 2));
			}
			ImportExport_PageObject.lCExistingTerms.set(recordBeforeImport);
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
//		waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
		log.info("On the Terms And Conditions Tab");
	}

	public void enableOrDisableTermsAndConditions(String indexes) {
		log.info("Going to fill the Terms & Conditions of Activation Group");
		String idPath = driver.findElement(By.cssSelector("#q-app .q-page .q-item.col-auto:nth-child(1) .q-item__label:nth-child(2)")).getText();
		MasterHooks.searchAGID.set(idPath);
		termStateCheckboxes();
		if (indexes.length() <= 1) {
			System.out.println("Index length is 1");
			int numberLength = indexes.length();
			indexList = indexes.split("", numberLength);
		} else {
			System.out.println("Index length is more than 1");
			int numberLength = ((indexes.length() / 2) + 1);
			indexList = indexes.split(",", numberLength);
		}
		for (int indexCount = 0; indexCount < indexList.length; indexCount++) {
			int nthChild = Integer.parseInt(indexList[indexCount]) + 1;
			waitTillWebElementIsVisible("termExercise", ".q-page .absolute tr:nth-child(" + nthChild + ") #tnc-exercise");
			WaitUntilElementIsClickable(driver.findElement(By.cssSelector(".q-page .absolute tr:nth-child(" + nthChild + ") #tnc-exercise")));
			driver.findElement(By.cssSelector(".q-page .absolute tr:nth-child(" + nthChild + ") #tnc-exercise")).click();
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			log.info("Completed filling the Terms and Conditions of Activation Group for " + indexList[indexCount] + " item");
		}
	}


	public void activateUnitList() {
		log.info("Activating unit list in Activation Group");
		waitTillWebElementIsVisible("unitListAGTab", unitListAGTab);
		try {
			waitAndClickOnElement(unitListAGTab);
			waitUntilLoadingSpinnerIsShown("nlaTabChange");
			waitUntilLoadingSpinnerIsGone("nlaTabChange");
			waitTillWebElementIsVisible("checkBoxes","#q-app .q-page .q-td #select-checkbox");
			int unitSize = driver.findElements(By.cssSelector("#q-app .q-page .q-td #select-checkbox")).size();
			if(!(unitSize>1)) {
				MasterHooks.searchUNID.set(driver.findElement(By.cssSelector("#q-app .q-page .id div")).getText());
				waitTillWebElementIsVisible("sendAssessmentAG", sendToInitialBtnAG);
				waitAndClickOnElement(sendToInitialBtnAG);

				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				if (getValuesFromExcel("Inception","Contract Level","Principal Position",0).equalsIgnoreCase("Lessor")) {
					waitTillWebElementIsVisible("Ship", unitListShipButtonAGTab);
					unitListShipButtonAGTab.click();
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitTillWebElementIsVisible("submitButton", submitButton);
					clickOnSubmitPopup("Submit / Add");
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
					waitTillWebElementIsVisible("Deliver", unitListDeliverButtonAGTab);
					unitListDeliverButtonAGTab.click();
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitTillWebElementIsVisible("submitButton", submitButton);
					clickOnSubmitPopup("Submit / Add");
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
					waitTillWebElementIsVisible("Return", unitListReturnButtonAGTab);
					unitListReturnButtonAGTab.click();
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				} else {
					//actionMoveAndClick("unitListAGTab",unitListAGTab);
					waitTillWebElementIsVisible("receivedBtnAG", receivedBtnAG);
					waitAndClickOnElement(receivedBtnAG);
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitTillWebElementIsVisible("submitButton", submitButton);
					clickOnSubmitPopup("Submit / Add");
					waitTillWebElementIsVisible("activateBtnAG", activateBtnAG);
					waitAndClickOnElement(activateBtnAG);
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				}
			}else{
				waitTillWebElementIsVisible("unitExecuteButton", unitExecute);
                //Storing the Id's of All Units
                int sizeOfCheckBox = driver.findElements(By.cssSelector("#q-app .q-page .q-table .q-td #select-checkbox")).size();
                unitIds= new String[sizeOfCheckBox];
                for(int i=0; i<unitIds.length; i++){
                    String unitId = driver.findElement(By.cssSelector("#q-app .q-page .q-tr:nth-child("+(i+2)+") .id div")).getText();
                    unitIds[i] = unitId;
                }
                waitTillWebElementIsVisible("unitExecuteButton", unitExecute);
				//For Send to Initial
				waitAndClickOnElement("selectAllCheckBox","#q-app .q-page .q-table #select-all-checkbox");
				waitAndClickOnElement(unitExecute);
				waitTillWebElementIsVisible("bukAction",bulkAction);
				clickOnDropDownAndSelectValue("sendToInitial",bulkAction,"Send to Initial");
				clickOnSubmitPopup("Submit / Add");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				//For Received
				waitAndClickOnElement("selectAllCheckBox","#q-app .q-page .q-table #select-all-checkbox");
				waitAndClickOnElement(unitExecute);
				waitTillWebElementIsVisible("bukAction",bulkAction);
				clickOnDropDownAndSelectValue("Received",bulkAction,"Received");
				clickOnSubmitPopup("Submit / Add");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				//For Activate
				waitAndClickOnElement("selectAllCheckBox","#q-app .q-page .q-table #select-all-checkbox");
				waitAndClickOnElement(unitExecute);
				waitTillWebElementIsVisible("bukAction",bulkAction);
				clickOnDropDownAndSelectValue("Activate",bulkAction,"Activate");
				clickOnSubmitPopup("Submit / Add");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
		}
		catch (InterruptedException | IOException e) {
			throw new RuntimeException(e);
		}
		log.info("Activated unit list in Activation Group");
	}

	public void changePageRecordSize(String size) {
		log.info("changing Page Record Size to " + size);
		waitTillWebElementIsVisible("recordPerPageBtn", recordPerPageBtn);
		WaitUntilElementIsClickable(recordPerPageBtn);
		recordPerPageBtn.click();
		//after the click
		waitTillWebElementIsVisible("recordPerPageOption", recordPerPageOption);
		WaitUntilElementIsClickable(recordPerPageOption);
		if (size.equalsIgnoreCase("20")) {
			driver.findElement(By.cssSelector(".desktop .q-menu .q-virtual-scroll__content [role='option']:nth-child(1)")).click();
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
		} else if (size.equalsIgnoreCase("50")) {
			driver.findElement(By.cssSelector(".desktop .q-menu .q-virtual-scroll__content [role='option']:nth-child(2)")).click();
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
		} else {
			driver.findElement(By.cssSelector(".desktop .q-menu .q-virtual-scroll__content [role='option']:nth-child(3)")).click();
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
		}
		log.info("changed Page Record Size to " + size);
	}

	public void fillAGAccounting() {
		log.info("Clicking on Accounting Tab");
		waitTillWebElementIsVisible("accountingAGTab", accountingAGTab);
		try {
			waitAndClickOnElement(accountingAGTab);
		} catch (InterruptedException e) {e.printStackTrace();}
		waitUntilLoadingSpinnerIsShown("nlaTabChange");
		waitUntilLoadingSpinnerIsGone("nlaTabChange");
//		waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
//		waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
		log.info("On the Accounting Tab");
	}

	public void fillActivationGroupCPI(String level) {
		log.info("Going to fill the Accounting Tab of Activation Group");
		waitTillWebElementIsVisible("agAccountingIndexationType", agAccountingIndexationTypeLease);
		String AGLevel = level.equalsIgnoreCase("Inception") ? "Activation Group Level" : "eventdata";

		//CPI (Lease)
		try {
			if(getValuesFromExcel(level,AGLevel,"CPI (Lease)",0).equalsIgnoreCase("Yes")) {
				String cpiValue = getValuesFromExcel(level,AGLevel,"CPI-Lease",0);

				if(!driver.findElement(By.cssSelector("#q-app .q-page .absolute #indexation-type-value")).getText().contains(cpiValue)){
					clickOnDropDownAndSelectValue("agAccountingIndexationType", agAccountingIndexationTypeLease, cpiValue);
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				}
				if (cpiValue.contains("CPI Local")) {
					handleCurrentIndexField(level, AGLevel, "Current Index Level-Lease", agAccountingCurrentIndexLease);
					// Handle Conditional Indexation
					if (getValuesFromExcel(level, AGLevel, "Conditional Indexation (Lease)", 0).equalsIgnoreCase("Yes")) {
						handleConditionalIndexationFields(level, AGLevel,
								"CPI Min (Lease)", agAccountingConditionalIndexLeaseMin,
								agAccountingConditionalIndexLease, "agAccountingConditionalIndexLease");
						handleConditionalIndexationFields(level, AGLevel,
								"CPI Max (Lease)", agAccountingConditionalIndexLeaseMax,
								agAccountingConditionalIndexLease, "agAccountingConditionalIndexLease");
					} else if (getValuesFromExcel(level, AGLevel, "Conditional Indexation (Lease)", 0).equalsIgnoreCase("No")) {
						String checkboxStatusLease = agAccountingConditionalIndexLease.getAttribute("aria-checked");
						if (checkboxStatusLease.equalsIgnoreCase("true")) {
							waitTillWebElementIsVisible("agAccountingConditionalIndexLease", agAccountingConditionalIndexLease);
							waitAndClickOnElement(agAccountingConditionalIndexLease);
							waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
							waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
						}
					}
				}
				else {
					handleCPIGlobalFields(level, AGLevel,
							"CPI Category-Lease", agAccountingCPICategoryLease,
							agAccountingReferenceDateLease, "Reference Date-Lease");

					if (getValuesFromExcel(level, AGLevel, "Conditional Indexation (Lease)", 0).equalsIgnoreCase("Yes")) {
						handleConditionalIndexationFields(level, AGLevel,
								"CPI Min (Lease)", agAccountingConditionalIndexLeaseMin,
								agAccountingConditionalIndexLease, "Conditional Indexation (Lease)");
						handleConditionalIndexationFields(level, AGLevel,
								"CPI Max (Lease)", agAccountingConditionalIndexLeaseMax,
								agAccountingConditionalIndexLease, "Conditional Indexation (Lease)");
					} else if (getValuesFromExcel(level, AGLevel, "Conditional Indexation (Lease)", 0).equalsIgnoreCase("No")) {
						String checkboxStatusLease = agAccountingConditionalIndexLease.getAttribute("aria-checked");
						if (checkboxStatusLease.equalsIgnoreCase("true")) {
							waitTillWebElementIsVisible("agAccountingConditionalIndexLease", agAccountingConditionalIndexLease);
							waitAndClickOnElement(agAccountingConditionalIndexLease);
							waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
							waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
						}
					}
				}
			}
			// CPI (Non-Lease)
			if(getValuesFromExcel(level,AGLevel,"CPI (Non-Lease)",0).equalsIgnoreCase("Yes")) {
				handleWait(200);
				waitTillWebElementIsVisible("agAccountingIndexationType", agAccountingIndexationTypeNonLease);
				WaitUntilElementIsClickable(agAccountingIndexationTypeNonLease);
				String cpiValue = getValuesFromExcel(level,AGLevel,"CPI-NonLease",0);

				if(!driver.findElement(By.cssSelector("#q-app .q-page .absolute #indexation-type-non-lease-value")).getText().contains(cpiValue)){
					clickOnDropDownAndSelectValue("agAccountingIndexationType", agAccountingIndexationTypeNonLease, cpiValue);
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				}
				if (cpiValue.contains("CPI Local")) {
					handleCurrentIndexField(level, AGLevel, "Current Index Level-NonLease", agAccountingCurrentIndexNonLease);
					// Handle Conditional Indexation
					if (getValuesFromExcel(level, AGLevel, "Conditional Indexation (Non-Lease)", 0).equalsIgnoreCase("Yes")) {
						handleConditionalIndexationFields(level, AGLevel,
								"CPI Min (Non-Lease)", agAccountingConditionalIndexNonLeaseMin,
								agAccountingConditionalIndexNonLease, "agAccountingConditionalIndexNonLease");
						handleConditionalIndexationFields(level, AGLevel,
								"CPI Max (Non-Lease)", agAccountingConditionalIndexNonLeaseMax,
								agAccountingConditionalIndexNonLease, "agAccountingConditionalIndexNonLease");

					} else if (getValuesFromExcel(level, AGLevel, "Conditional Indexation (Non-Lease)", 0).equalsIgnoreCase("No")) {
						String checkboxStatusNonLease = agAccountingConditionalIndexNonLease.getAttribute("aria-checked");
						if (checkboxStatusNonLease.equalsIgnoreCase("true")) {
							waitTillWebElementIsVisible("agAccountingConditionalIndexNonLease", agAccountingConditionalIndexNonLease);
							waitAndClickOnElement(agAccountingConditionalIndexNonLease);
							waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
							waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
						}
					}
				}
				else {
					handleCPIGlobalFields(level, AGLevel,
							"CPI Category-NonLease", agAccountingCPICategoryNonLease,
							agAccountingReferenceDateNonLease, "Reference Date-NonLease");
					// Handle Conditional Indexation
					if (getValuesFromExcel(level, AGLevel, "Conditional Indexation (Non-Lease)", 0).equalsIgnoreCase("Yes")) {
						handleConditionalIndexationFields(level, AGLevel,
								"CPI Min (Non-Lease)", agAccountingConditionalIndexNonLeaseMin,
								agAccountingConditionalIndexNonLease, "Conditional Indexation (Non-Lease)");
						handleConditionalIndexationFields(level, AGLevel,
								"CPI Max (Non-Lease)", agAccountingConditionalIndexNonLeaseMax,
								agAccountingConditionalIndexNonLease, "Conditional Indexation (Non-Lease)");
					} else if (getValuesFromExcel(level, AGLevel, "Conditional Indexation (Non-Lease)", 0).equalsIgnoreCase("No")) {
						String checkboxStatusNonLease = agAccountingConditionalIndexNonLease.getAttribute("aria-checked");
						if (checkboxStatusNonLease.equalsIgnoreCase("true")) {
							waitTillWebElementIsVisible("agAccountingConditionalIndexNonLease", agAccountingConditionalIndexNonLease);
							waitAndClickOnElement(agAccountingConditionalIndexNonLease);
							waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
							waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
						}
					}
				}
			}

			if (getValuesFromExcel("Inception", "Activation Group Level", "Use Non Lease On Indexation", 0).equalsIgnoreCase("Yes")) {
				waitTillWebElementIsVisible("useNonLeaseOnIndexationI", useNonLeaseOnIndexationI);
				WaitUntilElementIsClickable(useNonLeaseOnIndexationI);
				useNonLeaseOnIndexationI.click();
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		log.info("Completed filling the Accounting Tab of Activation Group");
	}

	private void handleCurrentIndexField(String level, String AGLevel, String fieldKey, WebElement fieldElement) {
		// Ensure the element is visible
		waitTillWebElementIsVisible(fieldKey, fieldElement);
		WaitUntilElementIsClickable(fieldElement);

		// Clear the field if not at Activation Group Level
		if (!AGLevel.equalsIgnoreCase("Activation Group Level")) {
			clearField(fieldElement);
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
		}

		// Send value to the field
		handleWait(2000);
		sendingValueToWebElement(fieldKey, fieldElement, getValuesFromExcel(level, AGLevel, fieldKey, 0));
		handleWait(2000);
		// Refresh the accounting tab and wait for loaders
		accountingAGTab.click();
		waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
		waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
	}

	private void handleCPIGlobalFields(String level, String AGLevel, String cpiCategoryKey, WebElement cpiCategoryElement,
									   WebElement referenceDateElement, String referenceDateKey) {

		// Handle CPI Category Dropdown
		String cpiCategoryValue = getValuesFromExcel(level, AGLevel, cpiCategoryKey, 0);
		waitTillWebElementIsVisible(cpiCategoryKey, cpiCategoryElement);
		if(cpiCategoryKey.equalsIgnoreCase("CPI Category-Lease")){
			if (!(driver.findElement(By.cssSelector("#q-app .q-page .absolute #consumer-price-index-category-value")).getText().contains(cpiCategoryValue))){
				try {
					clickOnDropDownToTypeAndSelectValue(cpiCategoryKey, cpiCategoryElement, cpiCategoryValue);
				} catch (InterruptedException | IOException e) {
					e.printStackTrace();
				}
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
		} else if (cpiCategoryKey.equalsIgnoreCase("CPI Category-NonLease")) {
			if (!(driver.findElement(By.cssSelector("#q-app .q-page .absolute #consumer-price-index-category-non-lease")).getText().contains(cpiCategoryValue))){
				try {
					clickOnDropDownToTypeAndSelectValue(cpiCategoryKey, cpiCategoryElement, cpiCategoryValue);
				} catch (InterruptedException | IOException e) {
					e.printStackTrace();
				}
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
		}

		// Handle Reference Date Field
		try {
			waitAndClickOnElement(referenceDateElement);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(!(referenceDateElement.getAttribute("value").length()==0)){
			clearField(referenceDateElement);
		}
		waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
		waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
		try {
			waitAndClickOnElement(referenceDateElement);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		handleWait(1000);
		sendingValueToWebElement(referenceDateKey, referenceDateElement, getValuesFromExcel(level, AGLevel, referenceDateKey, 0));
		waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
		waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");

	}


	private void handleConditionalIndexationFields(String level, String AGLevel, String fieldKey, WebElement fieldElement, WebElement conditionalIndexElement,
												   String conditionalIndexKey) {
		// Handle Conditional Indexation Checkbox
			String checkboxStatus = conditionalIndexElement.getAttribute("aria-checked");
			if (checkboxStatus.equalsIgnoreCase("false")) {
				waitTillWebElementIsVisible(conditionalIndexKey, conditionalIndexElement);
				try {
					waitAndClickOnElement(conditionalIndexElement);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}

		// Handle Min/Max Fields
		// Fetch the value for the field
		String value = getValuesFromExcel(level, AGLevel, fieldKey, 0);

		if (!value.equalsIgnoreCase("-")) {
			waitTillWebElementIsVisible(fieldKey, fieldElement);

			// Handle the value based on its content
			if (value.equalsIgnoreCase("")) {
				// Clear the field for an empty value
				try {
					waitAndClickOnElement(fieldElement);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				clearField(fieldElement);
			} else {
				try {
					waitAndClickOnElement(fieldElement);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (!AGLevel.equalsIgnoreCase("Activation Group Level")) {
					clearField(fieldElement);
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				}
				sendingValueToWebElement(fieldKey, fieldElement, value);
			}

			// Refresh the accounting tab and wait for any loaders
			accountingAGTab.click();
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
		}
	}

	public void fillAGClassification() {
		log.info("Clicking on Classification Tab");
		waitTillWebElementIsVisible("classificationAGTab", classificationAGTab);
		try {
			waitAndClickOnElement(classificationAGTab);
		} catch (InterruptedException e) {e.printStackTrace();}
		waitUntilLoadingSpinnerIsShown("nlaTabChange");
		waitUntilLoadingSpinnerIsGone("nlaTabChange");
//		waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
//		waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
		log.info("On the Classification Tab");
	}

	public void fillUseFulLifeAndClassification(String level) {
		log.info("Going to fill the Classification Tab of Activation Group");
		String AGLevel = null;
		if (level.equalsIgnoreCase("Inception")) {
			AGLevel = "Activation Group Level";
		} else {
			AGLevel = "eventdata";
		}
		String principalPosition = getValuesFromExcel("Inception","Contract Level","Principal Position",0);
		if (principalPosition.equalsIgnoreCase("Lessee")) {
			String gAAPClassification =getValuesFromExcel(level,AGLevel,"GAAP - Confirm Classification",0);
			if(!(driver.findElement(By.cssSelector("#q-app .q-page tr:nth-child(1) td:nth-child(3) #confirmed-classification-value")).getText().equalsIgnoreCase(gAAPClassification))) {
				waitTillWebElementIsVisible("conformClassificationGAAP", confirmClassificationGAAP);
				waitForClickablility("#q-app .q-page #confirmed-classification");
				try {
					clickOnDropDownAndSelectValue("confirmClassificationGAAP", confirmClassificationGAAP, gAAPClassification);
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				} catch (InterruptedException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (getValuesFromExcel(level,AGLevel,"UseFul Life",0).equalsIgnoreCase("Yes")) {
			//				if (principalPosition.equalsIgnoreCase("Lessee")) {
			waitTillWebElementIsVisible("useFullLifeYearIAS", useFulLifeYearIAS);
			useFulLifeYearIAS.sendKeys(Keys.chord(Keys.CONTROL, "a"),getValuesFromExcel(level, AGLevel,"UseFul Life IAS - Years",0));
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			useFulLifeMonthIAS.sendKeys(Keys.chord(Keys.CONTROL, "a"),getValuesFromExcel(level, AGLevel,"UseFul Life IAS - Months",0));
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			useFulLifeDayIAS.sendKeys(Keys.chord(Keys.CONTROL, "a"),getValuesFromExcel(level, AGLevel,"UseFul Life IAS - Days",0));
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			useFulLifeYearGAAP.sendKeys(Keys.chord(Keys.CONTROL, "a"),getValuesFromExcel(level, AGLevel,"UseFul Life GAAP - Years",0));
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			useFulLifeMonthGAAP.sendKeys(Keys.chord(Keys.CONTROL, "a"),getValuesFromExcel(level, AGLevel,"UseFul Life GAAP - Months",0));
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			useFulLifeDayGAAP.sendKeys(Keys.chord(Keys.CONTROL, "a"),getValuesFromExcel(level, AGLevel,"UseFul Life GAAP - Days",0));
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");

//				}
//				else{
//					waitTillWebElementIsVisible("useFullLifeYearIASLessor", useFulLifeYearIASLessor);
//					waitAndClickOnElement(useFulLifeYearIASLessor);
//					sendingValueToWebElement("useFullLifeYearIASLessor", useFulLifeYearIASLessor,
//							getValuesFromExcel(level,AGLevel,"UseFul Life - Years",0));
//					classificationAGTab.click();
//					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
//					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
//					waitAndClickOnElement(useFulLifeMonthIASLessor);
//					sendingValueToWebElement("useFullLifeMonthIASLessor",
//							useFulLifeMonthIASLessor, getValuesFromExcel(level,AGLevel,"UseFul Life - Months",0));
//					classificationAGTab.click();
//					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
//					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
//					waitAndClickOnElement(useFulLifeYearGAAPLessor);
//					sendingValueToWebElement("useFullLifeYearGAAPLessor", useFulLifeYearGAAPLessor,
//							getValuesFromExcel(level,AGLevel,"UseFul Life - Years",0));
//					classificationAGTab.click();
//					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
//					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
//					waitAndClickOnElement(useFulLifeMonthGAAPLessor);
//					sendingValueToWebElement("useFullLifeMonthGAAPLessor",
//							useFulLifeMonthGAAPLessor,getValuesFromExcel(level,AGLevel,"UseFul Life - Months",0));
//				}
		}
	}

	public void indexedOrUnIndexedTermsAndConditions(String indexes) {
		log.info("Going to fill the Terms & Conditions of Activation Group");
		termStateCheckboxes();
		if (indexes.length() <= 1) {
			System.out.println("Index length is 1");
			int numberLength = indexes.length();
			indexList = indexes.split("", numberLength);
		} else {
			System.out.println("Index length is more than 1");
			int numberLength = ((indexes.length() / 2) + 1);
			indexList = indexes.split(",", numberLength);
		}
		for (int indexCount = 0; indexCount < indexList.length; indexCount++) {
			int nthChild = Integer.parseInt(indexList[indexCount]) + 1;
			WaitUntilElementIsClickable(driver.findElement(By.cssSelector(".q-page .absolute tr:nth-child(" + nthChild + ") #tnc-apply-indexation")));
			driver.findElement(By.cssSelector(".q-page .absolute tr:nth-child(" + nthChild + ") #tnc-apply-indexation")).click();
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			log.info("Completed filling the Terms and Conditions of Activation Group for " + indexList[indexCount] + " item");
		}
	}

	public void create_AG_Events(String event) {
		log.info("Creating AG Event");
		String principalPosition = getValuesFromExcel("Inception","Contract Level","Principal Position",0);
		waitTillWebElementIsVisible("addEventBtn", addEventBtn);
		WaitUntilElementIsClickable(addEventBtn);
		waitForClickablility("#q-app .q-page #context-menu-event");
		try {
			waitAndClickOnElement(addEventBtn);
			waitUntilLoadingSpinnerIsShown("nlaAddButtonLoader");
			waitUntilLoadingSpinnerIsGone("nlaAddButtonLoader");
		} catch (InterruptedException e) {e.printStackTrace();}
		WaitUntilElementIsClickable(activationGroupEventName);
		activationGroupEventName.click();
		sendingValueToWebElement("activationGroupName", activationGroupEventName, getValuesFromExcel(event,"eventdata","Event Name",0));
		//waitTillWebElementIsVisible("modificationDateAG",modificationDateAG);
		WaitUntilElementIsClickable(modificationDateAG);
		modificationDateAG.click();
		sendingValueToWebElement("modificationDateAG", modificationDateAG, getValuesFromExcel(event,"eventdata","Modification Date",0));
		waitUntilLoadingSpinnerIsShown("nlaAddButtonLoader");
		waitUntilLoadingSpinnerIsGone("nlaAddButtonLoader");
		//WaitUntilElementIsClickable(modificationReason);
		String terminate = getValuesFromExcel(event,"eventdata","Termination Event",0);

		if (terminate.equalsIgnoreCase("Yes")) {
			try {
				WaitUntilElementIsClickable(terminateEventBtn);
				waitAndClickOnElement(terminateEventBtn);
				WaitUntilElementIsClickable(terminationReasonField);
				clickOnDropDownAndSelectValue("terminationReasonField", terminationReasonField, getValuesFromExcel(event,"eventdata","Termination Reason",0));
			} catch (InterruptedException | IOException exception) {
				exception.printStackTrace();
			}
			WaitUntilElementIsClickable(penaltyAmountField);
			sendingValueToWebElement("penaltyAmountField", penaltyAmountField, getValuesFromExcel(event,"eventdata","Penalty Amount",0));
		} else {
			String reasonForModification = getValuesFromExcel(event,"eventdata","Modification Reason",0);
			String[] reasonLength = reasonForModification.split(",");
			try {
				for (String reason : reasonLength) {
					if(!reason.equalsIgnoreCase("Contract Rate Change")) {
						WaitUntilElementIsClickable(modificationReason);
						clickOnDropDownAndSelectValue("modificationReason", modificationReason, reason);
						waitUntilLoadingSpinnerIsShown("nlaAddButtonLoader");
						waitUntilLoadingSpinnerIsGone("nlaAddButtonLoader");
						waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
					}
					String leaseType = getValuesFromExcel("Inception","Contract Level","Lease Type",0);
					Set<String> excludedLeaseTypes = Set.of(
							"06 - Non-Lease Service Contract",
							"05 - Low Value Lease Contract",
							"Lease Low value",
							"04 - Short-Term Lease Contract",
							"Lease Short Term",
							"Lease Contract (Operating/Short Term)",
							"01 - Lease Contract (Operating/Short Term)"
					);

					if (!excludedLeaseTypes.contains(leaseType)) {
						String selectedValue = reason.equalsIgnoreCase("Contract Rate Change")
								? "Revise Discount Rate"
								: "Unchanged Discount Rate";
						if(getSizeOfElements(".q-dialog #update-contract-rate-ibr button.q-icon")==1) {
							if (!getValueFromElement(".q-dialog #update-contract-rate-ibr-value").equalsIgnoreCase(selectedValue)) {
								clickOnDropDownAndSelectValue("contractRateEventFiled", contractRateEvent, selectedValue);
							}
						}else{
							clickOnDropDownAndSelectValue("contractRateEventFiled", contractRateEvent, selectedValue);
						}
					}
				}
				for (String reason : reasonLength) {
					if (reason.equalsIgnoreCase("Indexation")) {
						if (getValuesFromExcel(event,"eventdata","Use Non Lease On Indexation-Lease",0).equalsIgnoreCase("Yes")) {
							waitTillWebElementIsVisible("applyIndexationOnLeaseTerm", applyIndexationOnLeaseTerm);
							WaitUntilElementIsClickable(applyIndexationOnLeaseTerm);
							waitAndClickOnElement(applyIndexationOnLeaseTerm);
							waitUntilLoadingSpinnerIsShown("nlaAddButtonLoader");
							waitUntilLoadingSpinnerIsGone("nlaAddButtonLoader");
							if (principalPosition.equalsIgnoreCase("Lessee")) {
								if (!getValuesFromExcel("Inception", "Contract Level", "Indexed Currency", 0).equalsIgnoreCase("Yes")) {
									waitTillWebElementIsVisible("indexationTreatmentType", indexationTreatmentType);
									WaitUntilElementIsClickable(indexationTreatmentType);
									if (!(driver.findElements(By.cssSelector(".q-dialog[role='dialog'] #indexation-treatment-type[aria-disabled='true']")).size() == 1)) {
										clickOnDropDownAndSelectValue("indexationTreatmentType", indexationTreatmentType,
												getValuesFromExcel(event, "eventdata", "USGAAP Indexation Treatment Type", 0));
									}
								}
							}
							waitTillWebElementIsVisible("indexationDateType", indexationDateType);
							WaitUntilElementIsClickable(indexationDateType);
							clickOnDropDownAndSelectValue("indexationDateType", indexationDateType,
									getValuesFromExcel(event,"eventdata","Lease Indexation Date Type",0));
							if (!getValuesFromExcel(event, "eventdata", "Lease Indexation Date Type", 0).equalsIgnoreCase("ROU Anniversary Date")) {
								String leaseIndexationDateValue = getValuesFromExcel(event, "eventdata", "Lease Indexation Date", 0);
								if (!leaseIndexationDate.getAttribute("value").equalsIgnoreCase(leaseIndexationDateValue)) {
									sendingValueToWebElement("leaseIndexationDate", leaseIndexationDate,
											getValuesFromExcel(event, "eventdata", "Lease Indexation Date", 0));
								}
							}

						}
						if(getValuesFromExcel(event,"eventdata","Use Non Lease On Indexation-NonLease",0).equalsIgnoreCase("Yes")) {
							waitTillWebElementIsVisible("applyIndexationOnNonLeaseTerm", applyIndexationOnNonLeaseTerm);
							WaitUntilElementIsClickable(applyIndexationOnNonLeaseTerm);
							waitAndClickOnElement(applyIndexationOnNonLeaseTerm);
							waitUntilLoadingSpinnerIsShown("nlaAddButtonLoader");
							waitUntilLoadingSpinnerIsGone("nlaAddButtonLoader");
							waitTillWebElementIsVisible("NonLeaseIndexationDateType", NonLeaseIndexationDateType);
							WaitUntilElementIsClickable(NonLeaseIndexationDateType);
							clickOnDropDownAndSelectValue("NonLeaseIndexationDateType", NonLeaseIndexationDateType,
									getValuesFromExcel(event,"eventdata","Non-Lease Indexation Date Type",0));
							if(!getValuesFromExcel(event,"eventdata","Non-Lease Indexation Date Type",0).equalsIgnoreCase("ROU Anniversary Date")) {
								String nonLeaseIndexationDateValue = getValuesFromExcel(event, "eventdata", "Non-Lease Indexation Date", 0);
//								String date =driver.findElement(By.cssSelector(".q-dialog[role='dialog'] #indexation-start-date-non-lease-input-value")).getText();
								if (!NonLeaseIndexationDate.getAttribute("value").equalsIgnoreCase(nonLeaseIndexationDateValue)) {
									sendingValueToWebElement("NonLeaseIndexationDate", NonLeaseIndexationDate,
											getValuesFromExcel(event, "eventdata", "Non-Lease Indexation Date", 0));
								}
							}

						}
					}
					if (reason.equalsIgnoreCase("Decrease in Term")) {
						if (!getValuesFromExcel(event,"eventdata","ROU Asset Decrease Amount Term - IAS",0).equalsIgnoreCase("-")) {
							System.out.println(getValuesFromExcel(event,"eventdata","ROU Asset Decrease Amount Term - IAS",0));
							waitTillWebElementIsVisible("assetDecreaseAmountIASTerms", assetDecreaseAmountIASTerms);
							WaitUntilElementIsClickable(assetDecreaseAmountIASTerms);
							assetDecreaseAmountIASTerms.click();
							sendingValueToWebElement("assetDecreaseAmountIASTerms", assetDecreaseAmountIASTerms, getValuesFromExcel(event,"eventdata","ROU Asset Decrease Amount Term - IAS",0));
						}
					}
					if (reason.equalsIgnoreCase("Decrease in Asset")) {
						ArrayList<String> tabNames = new ArrayList<String>();
						tabNames.add(Tab0.getText());
						tabNames.add(Tab1.getText());

						for (String tab : tabNames) {
							if (tab.equalsIgnoreCase("GAAP")) {
								if (!getValuesFromExcel(event,"eventdata","ROU Asset Decrease Amount - GAAP",0).equalsIgnoreCase("-")) {
									try {
										waitTillWebElementIsVisible("assetDecreaseAmountGAAPAsset", assetDecreaseAmountGAAPAsset);
										WaitUntilElementIsClickable(assetDecreaseAmountGAAPAsset);
										waitAndClickOnElement(assetDecreaseAmountGAAPAsset);
										sendingValueToWebElement("assetDecreaseAmountGAAPAsset", assetDecreaseAmountGAAPAsset, getValuesFromExcel(event,"eventdata","ROU Asset Decrease Amount - GAAP",0));
									} catch (org.openqa.selenium.StaleElementReferenceException ex) {
										waitTillWebElementIsVisible("assetDecreaseAmountGAAPAsset", assetDecreaseAmountGAAPAsset);
										WaitUntilElementIsClickable(assetDecreaseAmountGAAPAsset);
										clickWithJS(assetDecreaseAmountGAAPAsset);
										sendingValueToWebElement("assetDecreaseAmountGAAPAsset", assetDecreaseAmountGAAPAsset, getValuesFromExcel(event,"eventdata","ROU Asset Decrease Amount - GAAP",0));
									}
								}
							} else {
								if (!getValuesFromExcel(event,"eventdata","ROU Asset Decrease Amount - IAS",0).equalsIgnoreCase("-")) {
									try {
										waitTillWebElementIsVisible("IAS", ".q-dialog .q-card .q-field #decrease-in-asset-input");
										WaitUntilElementIsClickable(getElement(".q-dialog .q-card .q-field #decrease-in-asset-input"));
										waitAndClickOnElement(getElement(".q-dialog .q-card .q-field #decrease-in-asset-input"));
										clearField(getElement(".q-dialog .q-card .q-field #decrease-in-asset-input"));
										getElement(".q-dialog .q-card .q-field #decrease-in-asset-input")
												.sendKeys(getValuesFromExcel(event,"eventdata","ROU Asset Decrease Amount - IAS",0));
//										sendingValueToWebElement("assetDecreaseAmountIASAsset", IAS, getValuesFromExcel(event,"eventdata","ROU Asset Decrease Amount - IAS",0));
									} catch (org.openqa.selenium.StaleElementReferenceException ex) {
										WebElement IASVal = driver.findElement(By.cssSelector(".q-dialog .q-card .q-field #decrease-in-asset-input"));
										waitTillWebElementIsVisible("IASVal", IASVal);
										WaitUntilElementIsClickable(IASVal);
										clickWithJS(IASVal);
										clearField(IASVal);
										IASVal.sendKeys(getValuesFromExcel(event,"eventdata","ROU Asset Decrease Amount - IAS",0));
//										sendingValueToWebElement("assetDecreaseAmountIASAsset", IASVal, getValuesFromExcel(event,"eventdata","ROU Asset Decrease Amount - IAS",0));
									}
								}
							}
							WaitUntilElementIsClickable(Tab1);
							Tab1.click();
						}

					}
					if (reason.equalsIgnoreCase("Asset Impairment - Loss")) {
						ArrayList<String> tabNames = new ArrayList<String>();
						tabNames.add(Tab0.getText());
						tabNames.add(Tab1.getText());

						for (String tab : tabNames) {
							if (!getValuesFromExcel(event,"eventdata","NBV On Event Date - " + tab,0).equalsIgnoreCase("-")) {
								waitTillWebElementIsVisible("NBVonEventDate", NBVonEventDate);
								WaitUntilElementIsClickable(NBVonEventDate);
								waitAndClickOnElement(NBVonEventDate);
								sendingValueToWebElement("NBVonEventDate", NBVonEventDate, getValuesFromExcel(event,"eventdata","NBV On Event Date - " + tab,0));
							}
							if (!getValuesFromExcel(event,"eventdata","Recoverable Amount - " + tab,0).equalsIgnoreCase("-")) {
								waitTillWebElementIsVisible("RecoverableAmount", RecoverableAmount);
								WaitUntilElementIsClickable(RecoverableAmount);
								RecoverableAmount.click();
								sendingValueToWebElement("RecoverableAmount", RecoverableAmount, getValuesFromExcel(event,"eventdata","Recoverable Amount - " + tab,0));
							}
							Thread.sleep(200);
							WaitUntilElementIsClickable(Tab1);
							Tab1.click();
						}

					}
					if (reason.equalsIgnoreCase("Asset Impairment - Gain")) {
						if (!getValuesFromExcel(event,"eventdata","Gain Amount",0).equalsIgnoreCase("-")) {
							waitTillWebElementIsVisible("GainAmount", GainAmount);
							WaitUntilElementIsClickable(GainAmount);
							GainAmount.click();
							sendingValueToWebElement("GainAmount", GainAmount, getValuesFromExcel(event,"eventdata","Gain Amount",0));
						}
					}
				}
			} catch (InterruptedException | IOException exception) {
				exception.printStackTrace();
			}
		}
		waitTillWebElementIsVisible("submitButton", submitButton);
		WaitUntilElementIsClickable(submitButton);
		clickOnSubmitPopup("Submit / Add");
		waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
		waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
		log.info("Completed Creating the AG Event");
	}

	public void changeRouEndDateAG(String rouEndDate) {
		log.info("Changing Rou End Date for AG Event");
		clickOnTab("definition");
		waitUntilLoadingSpinnerIsShown("nlaTabChange");
		waitUntilLoadingSpinnerIsGone("nlaTabChange");
//		waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
//		waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
		rouEndDateValueDefinition.click();
		sendingValueToWebElement("rouEndDate",rouEndDateValueDefinition,rouEndDate);
//		rouEndDateValueDefinition.sendKeys(Keys.chord(Keys.CONTROL, "a"),rouEndDate);
//		try{
//			waitAndClickOnElement(rouEndDateValueDefinition);
//			clearField(rouEndDateValueDefinition);
//			waitUntilLoadingSpinnerIsGone("nlaCalenderLoader");
//			waitUntilLoadingSpinnerIsShown("nlaCalenderLoader");
//			waitAndClickOnElement(rouEndDateValueDefinition);
//			rouEndDateValueDefinition.sendKeys(rouEndDate);
//		}catch(InterruptedException e){
//			e.printStackTrace();
//		}
		waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
		waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
		log.info("Changed Rou End Date for AG Event");
	}

	public void changeContractIBRRateEvent(String level) {
		log.info("Changing Contract Rate at AG");
		waitTillWebElementIsVisible("contractIBRRate", contractIBRRate);
		if (getValuesFromExcel(level,"eventdata","Use IBR Rate",0).equalsIgnoreCase("Yes") && (contractIBRRate.getAttribute("aria-checked").equalsIgnoreCase("false"))) {
			WaitUntilElementIsClickable(contractIBRRate);
			contractIBRRate.click();
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
		} else if (getValuesFromExcel(level,"eventdata","Use IBR Rate",0).equalsIgnoreCase("No")) {
			if ((contractIBRRate.getAttribute("aria-checked").equalsIgnoreCase("true"))) {
				WaitUntilElementIsClickable(contractIBRRate);
				contractIBRRate.click();
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
			WaitUntilElementIsClickable(contractRateField);
			contractRateField.click();
//			clearField(contractRateField);
			contractRateField.sendKeys(Keys.chord(Keys.CONTROL, "a"), getValuesFromExcel(level,"eventdata","Contract Rate",0));
//			sendingValueToWebElement("contractRateField", contractRateField, getValuesFromExcel(level,"eventdata","Contract Rate",0));
			accountingAGTab.click();
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
		}
		log.info("Contract Rate Changed");
	}

	public void changeActiviationGroupLevel(String level) {
		log.info("Moving AG Revision");
		String revisionLevel;
		if(Strings.isEmpty(MasterHooks.massIndexationJobID.get())){
			revisionLevel = level;
		} else {
			revisionLevel = level+" "+MasterHooks.massIndexationJobID.get();
		}
		waitTillWebElementIsVisible("RevisionButton", revisionButton);
		WaitUntilElementIsClickable(revisionButton);
		try {
			waitAndClickOnElement(revisionButton);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		waitUntilLoadingSpinnerIsShown("nlaRevisionButtonLoader");
//		waitUntilLoadingSpinnerIsGone("nlaRevisionButtonLoader");
		waitTillWebElementIsVisible("dropDownItems", ".q-menu .q-list .q-item:nth-child(1) .q-item__section #open-in-new-btn");
		int size = driver.findElements(By.cssSelector(".q-menu .q-list .q-item")).size();

		for (int item = 1; item <= size; item++) {
			waitTillWebElementIsVisible("AGLevel", ".q-menu .q-list .q-item:nth-child(" + item + ") .q-item__section--main .q-item__label");
			String innerText = driver.findElement(By.cssSelector(".q-menu .q-list .q-item:nth-child(" + item + ") .q-item__section--main .q-item__label")).getText();
			if (innerText.equalsIgnoreCase(revisionLevel)) {
				try {
					waitAndClickOnElement("revisionLevel", ".q-menu .q-list .q-item:nth-child(" + item + ")");
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				break;
			}
		}
		waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
		waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
		log.info("Moved to AG " + level + " Revision");
	}

	public void changeActivationGroupStatusLevel(String status, String entity, String level) {
		log.info("Moving to " + entity + " with name " + level + " and the status is " +status);
		waitTillWebElementIsVisible("RevisionButton", revisionButton);
		WaitUntilElementIsClickable(revisionButton);
		try {
			waitAndClickOnElement(revisionButton);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		waitTillWebElementIsVisible("dropDownItems", ".q-menu .q-list .q-item:nth-child(1) .q-item__section #open-in-new-btn");
		int size = driver.findElements(By.cssSelector(".q-menu .q-list .q-item")).size();
		for (int item = 1; item <= size; item++) {
			waitUnTillWebElementIsVisible("AGLevel", ".q-menu .q-list .q-item:nth-child("+item+") .q-item__section--main .q-item__label");
			String innerTextLevel = driver.findElement(By.cssSelector(".q-menu .q-list .q-item:nth-child("+item+") .q-item__section--main .q-item__label")).getText();
			waitUnTillWebElementIsVisible("status", ".q-menu .q-list .q-item:nth-child("+ item+") .q-item__section .q-chip__content");
			String innerTextStatus = driver.findElement(By.cssSelector(".q-menu .q-list .q-item:nth-child("+item+") .q-item__section .q-chip__content")).getText();
			if (innerTextLevel.equalsIgnoreCase(level) && innerTextStatus.equalsIgnoreCase(status)) {
				try {
					waitAndClickOnElement("revisionLevel", ".q-menu .q-list .q-item:nth-child(" + item + ")");
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				break;
			}
		}
		waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
		waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
		log.info("Moved to " + entity + " with name " + level + " and the status is " +status);
	}

	public void revertAG(String reversalReason) {
		log.info("Reverting the AG");
		try{
			waitTillWebElementIsVisible("AGContextMenu", contextMenuAG);
			WaitUntilElementIsClickable(contextMenuAG);
			waitAndClickOnElement(contextMenuAG);
			waitTillWebElementIsVisible("RevertButton", revertAGButton);
			WaitUntilElementIsClickable(revertAGButton);
			waitAndClickOnElement(revertAGButton);
			waitTillWebElementIsVisible("documentDate",documentDate);
			WaitUntilElementIsClickable(documentDate);
			//Checking the Required Fields
			checkRequiredField("documentDate",".q-dialog .required-field#document-date-input");
			waitAndClickOnElement(documentDate);
			sendingValueToWebElement("documentDate", documentDate, getValuesFromExcel("Inception","Activation Group Level","Document Date",0));
			WaitUntilElementIsClickable(postingDate);
			//Checking the Required Fields
			checkRequiredField("postingDate",".q-dialog .required-field#posting-date-input");
			waitAndClickOnElement(postingDate);
			sendingValueToWebElement("documentDate", postingDate, getValuesFromExcel("Inception","Activation Group Level","Posting Date",0));
			handleWait(200);
			//Checking the Required Fields
			checkRequiredField("reversalReason",".q-dialog .required-field #reversal-reason-selector-dialog");
			clickOnDropDownToTypeAndSelectValue("reversalReason", reversalReasonDropdown, reversalReason);
			waitTillWebElementIsVisible("submitButton", submitButton);
			WaitUntilElementIsClickable(submitButton);
			clickOnSubmitPopup("Submit / Add");
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			handleWait(3000);
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			String entityStatus = driver.findElement(By.cssSelector("#q-app .q-page .q-item.col-auto:nth-child(4) .q-item__label:nth-child(2)")).getText();
			Assert.assertEquals(entityStatus, "Reverted","Activation Group is not in 'Reverted' State");
			MasterHooks.agReverted.set("Reverted");
			log.info("AG is Reverted");
		} catch (InterruptedException | IOException e) {
			throw new RuntimeException(e);
		}

	}
	public void entityStatus(String entity, String status) {
		log.info("Checking the "+entity+" Status");
		try{
			if(entity.equalsIgnoreCase("Unit")){
				waitTillWebElementIsVisible("unitAGTab",unitAGTab);
				unitAGTab.click();
				Thread.sleep(200);
				String unitStatus = driver.findElement(By.cssSelector("#q-app .q-page .q-table .q-tr:nth-child(2) .q-td:nth-child(6)")).getText();
				Assert.assertEquals(unitStatus, status);
				log.info(entity+" Expected Status: "+status +" :: " + "Actual Status: " + unitStatus);
			} else if (entity.equalsIgnoreCase("Contract") || entity.equalsIgnoreCase("Lease Component") || entity.equalsIgnoreCase("Activation Group")) {
				Thread.sleep(200);
				String entityStatus = driver.findElement(By.cssSelector("#q-app .q-page .q-item.col-auto:nth-child(4) .q-item__label:nth-child(2)")).getText();
				Assert.assertEquals(entityStatus, status);
				log.info(entity+"- Expected Status: "+status +" :: " + "Actual Status: " + entityStatus);
			} else if (entity.equalsIgnoreCase("InterCompanyTransfer Activation Group")) {
				Thread.sleep(200);
				String entityStatus = driver.findElement(By.cssSelector("#q-app .q-page .q-item.col-auto:nth-child(5) .q-item__label:nth-child(2)")).getText();
				Assert.assertEquals(entityStatus, status);
				log.info(entity+"- Expected Status: "+status +" :: " + "Actual Status: " + entityStatus);
			} else if (entity.equalsIgnoreCase("Master Agreement")){
				String mlaStatus = driver.findElement(By.cssSelector("#q-app .q-page .q-item.col-auto:nth-child(3) .q-item__label:nth-child(2)")).getText();
				Assert.assertEquals(mlaStatus, status);
			}
		}catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void addCharges(String level) {
		try{
			log.info("Trying to open the charge tab");
			String termLevel = level;
			String termSubLevel = null;
			if (level.equalsIgnoreCase("Inception")) {
				termSubLevel = "Activation Group Level";
			}
			else {
				termSubLevel = "eventdata";
			}
			int totalNumberOfCharges = masterAgreementPageObject.getInputValues().get(termLevel).get(termSubLevel).get("Name").size();
			for (int ChargeCounter = 0; ChargeCounter < totalNumberOfCharges; ChargeCounter++) {
				if (ChargeCounter == 0) {
					waitAndClickOnElement(Chargestab);
//					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
//					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
					log.info("User is on the Charge tab");
				}
				waitTillWebElementIsVisible("chargeName",addChargeButton);
				waitAndClickOnElement(addChargeButton);
				waitTillWebElementIsVisible("chargeName",chargeName);
				waitAndClickOnElement(chargeName);
				log.info("User is entering the input values");
				sendingValueToWebElement("ChargeName", chargeName,
						getValuesFromExcel(termLevel,termSubLevel,"Name",ChargeCounter));
				clickOnDropDownToTypeAndSelectValue("ExpenseCategory", expenseCategory,
						getValuesFromExcel(termLevel,termSubLevel,"Expense Category",ChargeCounter));
				waitAndClickOnElement(DueDate);
				sendingValueToWebElement("DueDate", DueDate,
						getValuesFromExcel(termLevel,termSubLevel,"Due Date",ChargeCounter));
				waitAndClickOnElement(chargeAmount);
				sendingValueToWebElement("Amount", chargeAmount,
						getValuesFromExcel(termLevel,termSubLevel,"Amount",ChargeCounter));
				int unitSize = Integer.parseInt(getValuesFromExcel("Inception","Lease Component Level","Quantity",0));
				if(unitSize==1) {
					clickOnDropDownToTypeAndSelectValue("Unit", Unit,
							getValuesFromExcel(termLevel,termSubLevel,"Unit",ChargeCounter));
				}else{
					clickOnDropDownToTypeAndSelectValue("splitUnit", Unit, unitIds[ChargeCounter]);
				}
				log.info("The user has filled all the required information");
				log.info("The user is clicking on the Submit button");
				clickOnSubmitPopup("Submit / Add");
				Thread.sleep(4000);
			}
			//Storing the Id's of All Units
			int sizeOfChargeCheckBox = driver.findElements(By.cssSelector("#q-app .q-page .q-table .q-td #charge-list-checkbox")).size();
			chargeIds= new String[sizeOfChargeCheckBox];
			for(int i=0; i<chargeIds.length; i++){
				String unitId = driver.findElement(By.cssSelector("#q-app .q-page .q-tr:nth-child("+(i+2)+") .q-td:nth-child(2)")).getText();
				chargeIds[i] = unitId;
			}
		} catch (InterruptedException | IOException exception) {
			exception.printStackTrace();
		}
	}

	public void assetInformationDefinitionPage(String level) {
		log.info("Entering Activation Group Definition page Values at the "+level+" Level");
		try {
			if (driver.findElements(By.cssSelector("#q-app .q-page #definition-step.q-stepper__tab--active")).size() == 0) {
				waitAndClickOnElement(definitionAGTab);
				waitUntilLoadingSpinnerIsShown("nlaTabChange");
				waitUntilLoadingSpinnerIsGone("nlaTabChange");
			}
			String AGLevel = null;
			if (level.equalsIgnoreCase("Inception")) {
				AGLevel = "Activation Group Level";
			} else {
				AGLevel = "eventdata";
			}
			if(!(getValuesFromExcel(level,AGLevel,"FMV / Unit (Reassessed)",0).equalsIgnoreCase("-") ||
					getValuesFromExcel(level,AGLevel,"FMV / Unit (Reassessed)",0).isEmpty())){
				sendingValueToWebElement("fmv_Unit", fmv_Unit,
						getValuesFromExcel(level,AGLevel,"FMV / Unit (Reassessed)",0));
				definitionAGTab.click();
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
			if(!(getValuesFromExcel(level,AGLevel,"Salvage Value / Unit",0).equalsIgnoreCase("-") ||
					getValuesFromExcel(level,AGLevel,"Salvage Value / Unit",0).isEmpty())){
				waitTillWebElementIsVisible("salvageValueField", ".q-page #asset-definition-form-expansion #residual-value-input");
				checkRequiredField("salvageUnitValue",".q-page #asset-definition-form-expansion #residual-value-input");
				waitAndClickOnElement(salvageUnitValue);
				salvageUnitValue.sendKeys(Keys.chord(Keys.CONTROL, "a"),getValuesFromExcel(level,AGLevel,"Salvage Value / Unit",0));
				handleWait(1000);
//				sendingValueToWebElement("salvageUnitValue", salvageUnitValue,
//						getValuesFromExcel(level,AGLevel,"Salvage Value / Unit",0));
				waitAndClickOnElement(definitionAGTab);
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
			String transferOfOwnership= getValuesFromExcel(level,AGLevel,"Transfer of Ownership",0);
			if(transferOfOwnership.equalsIgnoreCase("Yes")){
				waitAndClickOnElement("transferOwnership",".q-page #asset-definition-form-expansion #transfer-of-ownership[aria-checked='false']");
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}else if(transferOfOwnership.equalsIgnoreCase("No")){
				waitAndClickOnElement("transferOwnership",".q-page #asset-definition-form-expansion #transfer-of-ownership[aria-checked='true']");
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
			String specializedAsset= getValuesFromExcel(level,AGLevel,"Specialized Asset",0);
			if(specializedAsset.equalsIgnoreCase("Yes")){
				waitAndClickOnElement("specializedAsset",".q-page #asset-definition-form-expansion #specialized-asset[aria-checked='false']");
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}else if(specializedAsset.equalsIgnoreCase("No")){
				waitAndClickOnElement("specializedAsset",".q-page #asset-definition-form-expansion #specialized-asset[aria-checked='true']");
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
			log.info("Completed filling the Activation Group Definition page Values at the "+level+" Level");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void moveToChargeTab() {
		log.info("Clicking on Charge List Tab");
//		waitTillWebElementIsVisible("activationGroupName",activationGroupName);
		waitTillWebElementIsVisible("ChargesTab", Chargestab);
		Chargestab.click();
		waitUntilLoadingSpinnerIsShown("nlaTabChange");
		waitUntilLoadingSpinnerIsGone("nlaTabChange");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {e.printStackTrace();}
		if(ImportExport_PageObject.aGExistingCharge.get()==null) {
			int recordBeforeImport;
			if (driver.findElements(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).size() == 0) {
				recordBeforeImport = 0;
			} else {
				String records = driver.findElement(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
				recordBeforeImport = Integer.parseInt(records.substring(records.indexOf("f") + 2));
			}
			ImportExport_PageObject.aGExistingCharge.set(recordBeforeImport);
		}
		log.info("On the Charge List Tab");
	}

	public void moveToUnitListTab() {
		log.info("Clicking on Unit List Tab");
		waitTillWebElementIsVisible("unitListAGTab", unitListAGTab);
		try {
			waitAndClickOnElement(unitListAGTab);
		} catch (InterruptedException e) {e.printStackTrace();}
		waitUntilLoadingSpinnerIsShown("nlaTabChange");
		waitUntilLoadingSpinnerIsGone("nlaTabChange");
		MasterHooks.searchUNID.set(driver.findElement(By.cssSelector("#q-app .q-page .id div")).getText());
		log.info("On the Charge List Tab");
	}

	public void verifyScheduleGenFailure(){
		int statusSize=driver.findElements(By.cssSelector(".q-drawer .q-expansion-item__container .q-list .q-item .q-badge")).size();
		for (int index=1; index<=statusSize; index++){
			String scheduleStatus=driver.findElement(By.cssSelector(".q-drawer .q-expansion-item__container .q-list .q-item:nth-child("+index+") .q-badge")).getText();
			if(scheduleStatus.equalsIgnoreCase("Failed")){
				log.info("The Generation of schedules are failing!!!");
			}
			else {
				Assert.fail("The Generation of Schedules are successfully done");
			}
		}
	}

	public void verifyAGButtonStatus() {
		log.info("User is already on the Activation Group Page");
		if(driver.findElements(By.cssSelector("#q-app .q-card .q-btn-group .q-btn:nth-child(1) a[aria-disabled='true']")).size()==1) {
			log.info("There are some of the Postings left, that needs to be Posted!!");
		}
		else if(driver.findElements(By.cssSelector("#q-app .q-card .q-btn-group .q-btn:nth-child(1) a[aria-disabled='true']")).isEmpty()){
			try {
				waitAndClickOnElement(driver.findElement(By.cssSelector("#q-app .q-card #event-id-ag-lease-end-btn")));
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				log.info("All the postings were completed and AG is on Lease End now");
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		else {
			Assert.fail("Found problem while doing Lease End!!!");
		}
	}

	public void VerifyThatModificationRecognationFieldsAreEnabled(String date) {
		log.info("Verify That Modification Recognition Fields Are Enabled!!!");
		WaitUntilElementIsClickable(draftAGActivate);
		try {
			waitAndClickOnElement(draftAGActivate);
		} catch (InterruptedException e) {e.printStackTrace();}
		// Check Document Date is Enabled
		WaitUntilElementIsClickable(documentDateInput);
		documentDateInput.isEnabled();
		Boolean checkDocDate = documentDateInput.isEnabled();
		checkDocDate.equals(true);
		documentDateInput.click();
		clearField(documentDateInput);
		sendingValueToWebElement("documentDateInput",documentDateInput,date);
		documentDateInput.click();
		// Check Posting Date is Enabled
		WaitUntilElementIsClickable(postingDateInput);
		postingDateInput.isEnabled();
		Boolean checkPostingDate = postingDateInput.isEnabled();
		checkPostingDate.equals(true);
		postingDateInput.click();
		clearField(postingDateInput);
		sendingValueToWebElement("documentDateInput",postingDateInput,date);
		postingDateInput.click();
		cancelBtn.click();
		log.info("Modification Recognition Fields Are Enabled!!!");
	}

	public void validateTheClassificationsTabValues(String level) {
		log.info("Validating "+ level+" Level Classification Tab Values");
		C_Activation_Group_PageObject activationGroup= new C_Activation_Group_PageObject();
		File baseValueExcelFolder = new File("src/test/resources/validationExcelFiles/"
				+ MasterHooks.configurationProperties.get().getWhichBaselineToUseForValidation() + "/"
				+ MasterAgreement_PageObject.testCaseName.get() + "/");

		File baseValueExcelFile = new File("src/test/resources/validationExcelFiles/"
				+ MasterHooks.configurationProperties.get().getWhichBaselineToUseForValidation() + "/"
				+ MasterAgreement_PageObject.testCaseName.get() + "/"
				+ MasterAgreement_PageObject.testCaseName.get() + "_Classifications" + ".xlsx");

		String principalPosition = getValuesFromExcel("Inception","Contract Level","Principal Position",0);

		if (!baseValueExcelFolder.exists()) {
			baseValueExcelFolder.mkdir();
		}
		if(MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("enterNewData") && level.equalsIgnoreCase("Inception")){
			if(baseValueExcelFile.exists()){
				Assert.fail("File Already Exists");
			}
		}
		if(driver.findElements(By.cssSelector("#q-app .q-page .q-stepper__tab--active#classifications-step")).size()==0) {
			log.info("Clicking on Classification Tab");
			waitTillWebElementIsVisible("classificationAGTab", classificationAGTab);
			try {
				waitAndClickOnElement(classificationAGTab);
			} catch (InterruptedException e) {e.printStackTrace();}
			waitUntilLoadingSpinnerIsShown("nlaTabChange");
			waitUntilLoadingSpinnerIsGone("nlaTabChange");
			log.info("On the Classification Tab");
		}
		if(MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("enterNewData")) {
			if (level.equalsIgnoreCase("Inception")) {
				ArrayList<AG_Classifications_Validation> classificationValues = new ArrayList<AG_Classifications_Validation>();
				classificationValues = activationGroup.readColumnsHeading("Classifications");
				activationGroup_Classifications.get().writeClassificationHeadings(classificationValues, "Classifications", baseValueExcelFile);
			}
			ArrayList<AG_Classifications_Validation> classificationValues = new ArrayList<AG_Classifications_Validation>();
			classificationValues=activationGroup.readClassificationValues("Classifications", level);
			activationGroup_Classifications.get().writeObjectValues(classificationValues, "Classifications", baseValueExcelFile);
		}else{
			if(MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("validateAll")) {
				ArrayList<AG_Classifications_Validation> classificationValues = new ArrayList<AG_Classifications_Validation>();
				classificationValues = activationGroup.readClassificationValues("Classifications", level);
				AG_Classifications_MetaModel agClassificationMeta = activationGroup_Classifications.get().createFromFile(baseValueExcelFile, "Classifications");
				agClassificationMeta.compareByDocumentType(classificationValues, level, "Classifications", classificationValues.get(1).getClassificationLevel());
			}
		}
		if(MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("enterNewData")) {
			if (level.equalsIgnoreCase("Inception")) {
				ArrayList<AG_Classifications_Validation> classificationValues = new ArrayList<AG_Classifications_Validation>();
				classificationValues = activationGroup.readColumnsHeading("Classification Summary");
				activationGroup_Classifications.get().writeClassificationHeadings(classificationValues, "Classification Summary", baseValueExcelFile);
			}
			ArrayList<AG_Classifications_Validation> classificationValues = new ArrayList<AG_Classifications_Validation>();
			classificationValues=activationGroup.readClassificationValues("Classification Summary", level);
			activationGroup_Classifications.get().writeObjectValues(classificationValues, "Classification Summary", baseValueExcelFile);
		}else{
			if(MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("validateAll")) {
				ArrayList<AG_Classifications_Validation> classificationValues = new ArrayList<AG_Classifications_Validation>();
				classificationValues = activationGroup.readClassificationValues("Classification Summary", level);
				AG_Classifications_MetaModel agClassificationMeta = activationGroup_Classifications.get().createFromFile(baseValueExcelFile, "Classification Summary");
				agClassificationMeta.compareByDocumentType(classificationValues, level, "Classification Summary", classificationValues.get(1).getClassificationLevel());
			}
		}
		if(MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("enterNewData")) {
			if (level.equalsIgnoreCase("Inception")) {
				ArrayList<AG_Classifications_Validation> classificationValues = new ArrayList<AG_Classifications_Validation>();
				classificationValues=activationGroup.readColumnsHeading("Carry-Over Balance");
				activationGroup_Classifications.get().writeClassificationHeadings(classificationValues,"Carry-Over Balance",baseValueExcelFile);
			}
			ArrayList<AG_Classifications_Validation> classificationValues = new ArrayList<AG_Classifications_Validation>();
			classificationValues=activationGroup.readClassificationValues("Carry-Over Balance", level);
			activationGroup_Classifications.get().writeObjectValues(classificationValues, "Carry-Over Balance", baseValueExcelFile);
		}else{
			if(MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("validateAll")) {
				ArrayList<AG_Classifications_Validation> classificationValues = new ArrayList<AG_Classifications_Validation>();
				classificationValues = activationGroup.readClassificationValues("Carry-Over Balance", level);
				AG_Classifications_MetaModel agClassificationMeta = activationGroup_Classifications.get().createFromFile(baseValueExcelFile, "Carry-Over Balance");
				agClassificationMeta.compareByDocumentType(classificationValues, level, "Carry-Over Balance", classificationValues.get(1).getClassificationLevel());
			}
		}
		if(principalPosition.equalsIgnoreCase("Lessee")) {
			if(MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("enterNewData")) {
				if (level.equalsIgnoreCase("Inception")) {
					ArrayList<AG_Classifications_Validation> classificationValues = new ArrayList<AG_Classifications_Validation>();
					classificationValues = activationGroup.readColumnsHeading("Carry-Over Liability Balance");
					activationGroup_Classifications.get().writeClassificationHeadings(classificationValues, "Carry-Over Liability Balance", baseValueExcelFile);
				}
				ArrayList<AG_Classifications_Validation> classificationValues = new ArrayList<AG_Classifications_Validation>();
				classificationValues=activationGroup.readClassificationValues("Carry-Over Liability Balance", level);
				activationGroup_Classifications.get().writeObjectValues(classificationValues, "Carry-Over Liability Balance", baseValueExcelFile);
			}else{
				if(MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("validateAll")) {
					ArrayList<AG_Classifications_Validation> classificationValues = new ArrayList<AG_Classifications_Validation>();
					classificationValues = activationGroup.readClassificationValues("Carry-Over Liability Balance", level);
					AG_Classifications_MetaModel agClassificationMeta = activationGroup_Classifications.get().createFromFile(baseValueExcelFile, "Carry-Over Liability Balance");
					agClassificationMeta.compareByDocumentType(classificationValues, level, "Carry-Over Liability Balance", classificationValues.get(1).getClassificationLevel());
				}
			}
			if(MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("enterNewData")) {
				if (level.equalsIgnoreCase("Inception")) {
					ArrayList<AG_Classifications_Validation> classificationValues = new ArrayList<AG_Classifications_Validation>();
					classificationValues = activationGroup.readColumnsHeading("Carry-Over Impairment Balance");
					activationGroup_Classifications.get().writeClassificationHeadings(classificationValues, "Carry-Over Impairment Balance", baseValueExcelFile);
				}
				ArrayList<AG_Classifications_Validation> classificationValues = new ArrayList<AG_Classifications_Validation>();
				classificationValues=activationGroup.readClassificationValues("Carry-Over Impairment Balance", level);
				activationGroup_Classifications.get().writeObjectValues(classificationValues, "Carry-Over Impairment Balance", baseValueExcelFile);
			}else{
				if(MasterHooks.configurationProperties.get().getWantToGenerateBaseline().equalsIgnoreCase("validateAll")) {
					ArrayList<AG_Classifications_Validation> classificationValues = new ArrayList<AG_Classifications_Validation>();
					classificationValues = activationGroup.readClassificationValues("Carry-Over Impairment Balance", level);
					AG_Classifications_MetaModel agClassificationMeta = activationGroup_Classifications.get().createFromFile(baseValueExcelFile, "Carry-Over Impairment Balance");
					agClassificationMeta.compareByDocumentType(classificationValues, level, "Carry-Over Impairment Balance", classificationValues.get(1).getClassificationLevel());
				}
			}
		}
		log.info("Validation Completed for "+ level+" Level Classification Tab Values");
	}

	private ArrayList<AG_Classifications_Validation> readClassificationValues(String sheet, String classificationLevel) {
		log.info("Reading Classification Fields Values");
		ArrayList<AG_Classifications_Validation> classificationValues = new ArrayList<AG_Classifications_Validation>();
		int standards=driver.findElements(By.cssSelector("#q-app .q-page  .table-header-emphasize.wrap-text")).size();
		String principalPosition = getValuesFromExcel("Inception","Contract Level","Principal Position",0);
		for(int index=1; index <= standards; index++){
			AG_Classifications_Validation classificationsValue = new AG_Classifications_Validation();
			String standardName=driver.findElement(By.cssSelector("#q-app .q-page table tr th:nth-child(" + (index+1) + ") .table-header-emphasize.wrap-text")).getText();
			classificationsValue.setStandardName(standardName);
			classificationsValue.setClassificationLevel(classificationLevel);
			if(sheet.equalsIgnoreCase("Classifications")) {
				String systemClassification=driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(1) td:nth-child("+ (index+1) +") .field-value")).getText();
				String confirmClassification = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(1) td:nth-child("+ (index+1) +") #confirmed-classification-value")).getText();
				classificationsValue.setSystemClassification(systemClassification);
				classificationsValue.setConfirmClassification(confirmClassification);
			}
			if(sheet.equalsIgnoreCase("Classification Summary")) {
				String purchaseOption = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(1) .field-value")).getText();
				classificationsValue.setPurchaseOption(purchaseOption);
				if(principalPosition.equalsIgnoreCase("Lessor")) {
					String 	interestRate = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(2) .field-value")).getText();
					classificationsValue.setInterestRate(interestRate);
				}else{
					String transferOfOwnership = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(2) .field-value")).getText();
					String specializedAsset = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(3) .field-value")).getText();
					classificationsValue.setTransferOfOwnership(transferOfOwnership);
					classificationsValue.setSpecializedAsset(specializedAsset);
				}
				String usefulLifeYear = driver.findElement(By.cssSelector(".q-page td:nth-child("+(index+1) +") input[id$='-useful-life-year-input']")).getAttribute("value");
				String usefulLifeMonth = driver.findElement(By.cssSelector(".q-page td:nth-child("+(index+1) +")  input[id$='-useful-life-month-input']")).getAttribute("value");
				String remainingUsefulLifeYear,remainingUsefulLifeMonth,remainingTermYear,remainingTermMonth;
				if(principalPosition.equalsIgnoreCase("Lessee")) {
					remainingUsefulLifeYear = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(6) .field-value")).getText();
					remainingUsefulLifeMonth = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(7) .field-value")).getText();;
					remainingTermYear = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(9) .field-value")).getText();
					remainingTermMonth = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(10) .field-value")).getText();
				}else{
					remainingUsefulLifeYear = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(5) .field-value")).getText();
					remainingUsefulLifeMonth = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(6) .field-value")).getText();;
					remainingTermYear = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(7) .field-value")).getText();
					remainingTermMonth = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(8) .field-value")).getText();
				}
				classificationsValue.setUsefulLifeYear(usefulLifeYear);
				classificationsValue.setUsefulLifeMonth(usefulLifeMonth);
				classificationsValue.setRemainingUsefulLifeYear(remainingUsefulLifeYear);
				classificationsValue.setRemainingUsefulLifeMonth(remainingUsefulLifeMonth);
				classificationsValue.setRemainingTermYear(remainingTermYear);
				classificationsValue.setRemainingTermMonth(remainingTermMonth);

				String infiniteUsefulLife,fmv_UnitReassessed;
				if(principalPosition.equalsIgnoreCase("Lessee")) {
					String leaseTermThreshold = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(11) .field-value")).getText();
					String leaseTermToUsefulLife = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(12) .field-value")).getText();
					infiniteUsefulLife = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(13) .field-value")).getText();
					fmv_UnitReassessed = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(15) .field-value")).getText();
					String pvTotal_Unit = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(16) .field-value")).getText();
					String pvmlpTotal_Unit = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(17) .field-value")).getText();
					String pvThreshold = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(18) .field-value")).getText();
					String pvToFMV = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(19) .field-value")).getText();

					classificationsValue.setLeaseTermThreshold(leaseTermThreshold);
					classificationsValue.setLeaseTermToUsefulLife(leaseTermToUsefulLife);
					classificationsValue.setInfiniteUsefulLife(infiniteUsefulLife);
					classificationsValue.setFmv_UnitReassessed(fmv_UnitReassessed);
					classificationsValue.setPvTotal_Unit(pvTotal_Unit);
					classificationsValue.setPvmlpTotal_Unit(pvmlpTotal_Unit);
					classificationsValue.setPvThreshold(pvThreshold);
					classificationsValue.setPvToFMV(pvToFMV);
				}else {
					infiniteUsefulLife = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child(" + (index + 1) + ") .row:nth-child(9) .field-value")).getText();
					fmv_UnitReassessed = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child(" + (index + 1) + ") .row:nth-child(11) .field-value")).getText();
					String netLeaseInvestment = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(12) .field-value")).getText();
					String pvOfURV = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(13) .field-value")).getText();
					String pvOfLeasePayment = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(14) .field-value")).getText();
					String leaseReceivable = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(15) .field-value")).getText();
					String deferredIdcTotal = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(16) .field-value")).getText();
					String profitLoss = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(2) td:nth-child("+(index+1) +") .row:nth-child(17) .field-value")).getText();

					classificationsValue.setInfiniteUsefulLife(infiniteUsefulLife);
					classificationsValue.setFmv_UnitReassessed(fmv_UnitReassessed);
					classificationsValue.setNetLeaseInvestment(netLeaseInvestment);
					classificationsValue.setPvOfURV(pvOfURV);
					classificationsValue.setPvOfLeasePayment(pvOfLeasePayment);
					classificationsValue.setLeaseReceivable(leaseReceivable);
					classificationsValue.setDeferredIdcTotal(deferredIdcTotal);
					classificationsValue.setProfitLoss(profitLoss);
				}
			}
			if(sheet.equalsIgnoreCase("Carry-Over Balance")){
				String overrideCapitalizationDate= driver.findElement(By.cssSelector(".q-page td:nth-child(" + (index+1) + ") input[aria-label='Override Capitalization Date']")).getAttribute("value");
				if(overrideCapitalizationDate.isEmpty()||overrideCapitalizationDate.equalsIgnoreCase("-")){
					classificationsValue.setOverrideCapitalizationDate("null");
				}else{
					classificationsValue.setOverrideCapitalizationDate(overrideCapitalizationDate);
				}

				if(principalPosition.equalsIgnoreCase("Lessee")) {
					String companyAssetGBV = driver.findElement(By.cssSelector(".q-page td:nth-child(" + (index+1) + ")  input[id$='-company-asset-gbv-input']")).getAttribute("value").replace(",","");
					String companyAssetAD = driver.findElement(By.cssSelector(".q-page td:nth-child(" + (index+1) + ") input[id$='-company-asset-ad-input']")).getAttribute("value").replace(",","");;
					String companyCurrentYearAD = driver.findElement(By.cssSelector(".q-page td:nth-child(" + (index+1) + ") input[id$='-company-current-year-ad-input']")).getAttribute("value").replace(",","");;
					String leaseAssetGBV = driver.findElement(By.cssSelector(".q-page td:nth-child(" + (index+1) + ")  input[id$='-lease-asset-gbv-input']")).getAttribute("value").replace(",","");;
					String leaseAssetAD = driver.findElement(By.cssSelector(".q-page td:nth-child(" + (index+1) + ") input[id$='-lease-asset-ad-input']")).getAttribute("value").replace(",","");;
					String leaseCurrentYearAD = driver.findElement(By.cssSelector(".q-page td:nth-child(" + (index+1) + ") input[id$='-lease-current-year-ad-input']")).getAttribute("value").replace(",","");;

					classificationsValue.setCompanyAssetGBV(companyAssetGBV.isEmpty() || companyAssetGBV.equalsIgnoreCase("-") ? "null" : companyAssetGBV);
					classificationsValue.setCompanyAssetAD(companyAssetAD.isEmpty() || companyAssetAD.equalsIgnoreCase("-") ? "null" : companyAssetAD);
					classificationsValue.setCompanyCurrentYearAD(companyCurrentYearAD.isEmpty() || companyCurrentYearAD.equalsIgnoreCase("-") ? "null" : companyCurrentYearAD);
					classificationsValue.setLeaseAssetGBV(leaseAssetGBV.isEmpty() || leaseAssetGBV.equalsIgnoreCase("-") ? "null" : leaseAssetGBV);
					classificationsValue.setLeaseAssetAD(leaseAssetAD.isEmpty() || leaseAssetAD.equalsIgnoreCase("-") ? "null" : leaseAssetAD);
					classificationsValue.setLeaseCurrentYearAD(leaseCurrentYearAD.isEmpty() || leaseCurrentYearAD.equalsIgnoreCase("-") ? "null" : leaseCurrentYearAD);
				}else {
					String accumulatedDepreciation = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(3) td:nth-child(" + (index+1) + ") .row:nth-child(2) .field-value")).getText().replace(",","");;
					String accruedRent_DeferredLeaseClearing = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(3) td:nth-child(" + (index+1) + ") .row:nth-child(3) .field-value")).getText().replace(",","");;
					String carryingAmountOfNetInvestment = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(3) td:nth-child(" + (index+1) + ") .row:nth-child(4) .field-value")).getText().replace(",","");;

					classificationsValue.setAccumulatedDepreciation(accumulatedDepreciation.isEmpty() || accumulatedDepreciation.equalsIgnoreCase("-") ? "null" : accumulatedDepreciation);
					classificationsValue.setAccruedRent_DeferredLeaseClearing(accruedRent_DeferredLeaseClearing.isEmpty() || accumulatedDepreciation.equalsIgnoreCase("-") ? "null" : accruedRent_DeferredLeaseClearing);
					classificationsValue.setCarryingAmountOfNetInvestment(carryingAmountOfNetInvestment.isEmpty() || accumulatedDepreciation.equalsIgnoreCase("-") ? "null" : carryingAmountOfNetInvestment);
				}
			}
			if(sheet.equalsIgnoreCase("Carry-Over Liability Balance") && principalPosition.equalsIgnoreCase("Lessee")){
				String accruedInterestExpense = driver.findElement(By.cssSelector(".q-page td:nth-child(" + (index+1) + ") input[id$='-accrued-interest-expense-input']")).getAttribute("value").replace(",","");;

				classificationsValue.setAccruedInterestExpense(accruedInterestExpense.isEmpty()||accruedInterestExpense.equalsIgnoreCase("-") ? "null" : accruedInterestExpense);
			}
			if(sheet.equalsIgnoreCase("Carry-Over Impairment Balance") && principalPosition.equalsIgnoreCase("Lessee")){
				if(!standardName.equalsIgnoreCase("GAAP")) {
					String companyImpairmentReserveBalance = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(5) td:nth-child(" + (index+1) + ") #ias-company-impairment-reserve-balance-input")).getAttribute("value").replace(",","");;
					String companyAssetNonRecoverableImpairmentLoss = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(5) td:nth-child(" + (index+1) + ") #ias-company-non-recoverable-impairment-loss-input")).getAttribute("value").replace(",","");;
					String leaseAssetImpairmentReserveBalance = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(5) td:nth-child(" + (index+1) + ") #ias-lease-asset-impairment-reserve-balance-input")).getAttribute("value").replace(",","");;
					String leaseAssetNonRecoverableImpairmentLoss = driver.findElement(By.cssSelector(".q-page tbody tr:nth-child(5) td:nth-child(" + (index+1) + ") #ias-lease-asset-non-recoverable-impairment-loss-input")).getAttribute("value").replace(",","");;

					classificationsValue.setCompanyImpairmentReserveBalance(companyImpairmentReserveBalance.isEmpty()||companyImpairmentReserveBalance.equalsIgnoreCase("-") ? "null" : companyImpairmentReserveBalance);
					classificationsValue.setCompanyAssetNonRecoverableImpairmentLoss(companyAssetNonRecoverableImpairmentLoss.isEmpty()||companyAssetNonRecoverableImpairmentLoss.equalsIgnoreCase("-") ? "null" : companyAssetNonRecoverableImpairmentLoss);
					classificationsValue.setLeaseAssetImpairmentReserveBalance(leaseAssetImpairmentReserveBalance.isEmpty()||leaseAssetImpairmentReserveBalance.equalsIgnoreCase("-") ? "null" : leaseAssetImpairmentReserveBalance);
					classificationsValue.setLeaseAssetNonRecoverableImpairmentLoss(leaseAssetNonRecoverableImpairmentLoss.isEmpty()||leaseAssetNonRecoverableImpairmentLoss.equalsIgnoreCase("-") ? "null" : leaseAssetNonRecoverableImpairmentLoss);
					classificationsValue.setStraightLineDepreciation("FALSE");
				}else{
					classificationsValue.setCompanyImpairmentReserveBalance("null");
					classificationsValue.setCompanyAssetNonRecoverableImpairmentLoss("null");
					classificationsValue.setLeaseAssetImpairmentReserveBalance("null");
					classificationsValue.setLeaseAssetNonRecoverableImpairmentLoss("null");
					if(driver.findElements(By.cssSelector(".q-page #impaired-lease[aria-checked='true']")).size()==1){
						classificationsValue.setStraightLineDepreciation("TRUE");
					}else{
						classificationsValue.setStraightLineDepreciation("FALSE");
					}
				}
			}
			classificationValues.add(classificationsValue);
		}
		log.info("Done Reading Classification Fields Values");
		return classificationValues;
	}

	public ArrayList<AG_Classifications_Validation> readColumnsHeading(String sheet) {
		log.info("Reading Columns Heading for "+ sheet);
		ArrayList<AG_Classifications_Validation> classificationsValues = new ArrayList<AG_Classifications_Validation>();
		AG_Classifications_Validation classificationsValue = new AG_Classifications_Validation();
		String principalPosition = getValuesFromExcel("Inception","Contract Level","Principal Position",0);
		classificationsValue.setStandardName("Standard Name");
		classificationsValue.setClassificationLevel("Classification Level");
		if(sheet.equalsIgnoreCase("Classifications")) {
			classificationsValue.setSystemClassification("System Classification");
			classificationsValue.setConfirmClassification("Confirm Classification");
		}
		if(sheet.equalsIgnoreCase("Classification Summary")) {
			classificationsValue.setPurchaseOption("Purchase Option");
			if(principalPosition.equalsIgnoreCase("Lessor")) {
				classificationsValue.setInterestRate("Interest Rate");
			}else{
				classificationsValue.setTransferOfOwnership("Transfer of Ownership");
				classificationsValue.setSpecializedAsset("Specialized Asset");
			}
			classificationsValue.setUsefulLifeYear("Useful Life (Year)");
			classificationsValue.setUsefulLifeMonth("Useful Life (Month)");
			classificationsValue.setRemainingUsefulLifeYear("Remaining Useful Life (Year)");
			classificationsValue.setRemainingUsefulLifeMonth("Remaining Useful Life (Month");
			classificationsValue.setRemainingTermYear("Remaining Term (Year)");
			classificationsValue.setRemainingTermMonth("Remaining Term (Month)");

			if(principalPosition.equalsIgnoreCase("Lessee")) {
				classificationsValue.setLeaseTermThreshold("Lease Term Threshold");
				classificationsValue.setLeaseTermToUsefulLife("Lease Term to Useful Life");
			}
			classificationsValue.setInfiniteUsefulLife("Infinite Useful Life");
			classificationsValue.setFmv_UnitReassessed("FMV / Unit (Reassessed)");
			if(principalPosition.equalsIgnoreCase("Lessee")) {
				classificationsValue.setPvTotal_Unit("PV (Total / Unit)");
				classificationsValue.setPvmlpTotal_Unit("PVMLP (Total / Unit)");
				classificationsValue.setPvThreshold("PV Threshold");
				classificationsValue.setPvToFMV("PV to FMV");
			}else{
				classificationsValue.setNetLeaseInvestment("Net Lease Investment");
				classificationsValue.setPvOfURV("PV of URV");
				classificationsValue.setPvOfLeasePayment("PV of Lease Payment");
				classificationsValue.setLeaseReceivable("Lease Receivable");
				classificationsValue.setDeferredIdcTotal("Deferred IDC Total");
				classificationsValue.setProfitLoss("Profit/Loss");
			}
		}
		if(sheet.equalsIgnoreCase("Carry-Over Balance")){
			classificationsValue.setOverrideCapitalizationDate("Override Capitalization Date");
			if(principalPosition.equalsIgnoreCase("Lessee")) {
				classificationsValue.setCompanyAssetGBV("Company Asset GBV");
				classificationsValue.setCompanyAssetAD("Company Asset AD");
				classificationsValue.setCompanyCurrentYearAD("Company Current Year AD");
				classificationsValue.setLeaseAssetGBV("Lease Asset GBV");
				classificationsValue.setLeaseAssetAD("Lease Asset AD");
				classificationsValue.setLeaseCurrentYearAD("Lease Current Year AD");
			}else {
				classificationsValue.setAccumulatedDepreciation("Accumulated Depreciation");
				classificationsValue.setAccruedRent_DeferredLeaseClearing("Accrued Rent Clearing/Deferred Lease Clearing");
				classificationsValue.setCarryingAmountOfNetInvestment("Carrying Amount of Net Investment");
			}
		}
		if(sheet.equalsIgnoreCase("Carry-Over Liability Balance") && principalPosition.equalsIgnoreCase("Lessee")){
			classificationsValue.setAccruedInterestExpense("Accrued Interest Expense");
		}
		if(sheet.equalsIgnoreCase("Carry-Over Impairment Balance") && principalPosition.equalsIgnoreCase("Lessee")){
			classificationsValue.setCompanyImpairmentReserveBalance("Company Impairment Reserve Balance");
			classificationsValue.setCompanyAssetNonRecoverableImpairmentLoss("Company Asset Non-Recoverable Impairment Loss");
			classificationsValue.setLeaseAssetImpairmentReserveBalance("Lease Asset Impairment Reserve Balance");
			classificationsValue.setLeaseAssetNonRecoverableImpairmentLoss("Lease Asset Non-Recoverable Impairment Loss");
			classificationsValue.setStraightLineDepreciation("Impairment Carry Over Balances are NOT applicable for GAAP");
		}
		classificationsValues.add(classificationsValue);
		return classificationsValues;
	}


	public void splitActivationGroup() {
		log.info("Splitting the Activation Group");
		try {
//			if(driver.findElement(By.cssSelector("#q-app .q-page .q-item.col-auto:nth-child(4) .q-item__label:nth-child(2)")).getText().equalsIgnoreCase("Active")){
//				Assert.fail("Unable to do AG Split because Activation Group is in Active State");
//			}
			//Getting the AG Name
			waitTillWebElementIsVisible("agName","#q-app .q-page .q-item.col-auto:nth-child(2) .q-item__label:nth-child(2)");
			String activationGroupName = driver.findElement(By.cssSelector("#q-app .q-page .q-item.col-auto:nth-child(2) .q-item__label:nth-child(2)")).getText();
			splitAGName = activationGroupName+"-1";
			waitTillWebElementIsVisible("AGContextMenu", contextMenuAG);
			WaitUntilElementIsClickable(contextMenuAG);
			contextMenuAG.click();
			if(driver.findElements(By.cssSelector(".q-menu #split-ag-item[aria-disabled=true]")).size()==1){
				Assert.fail("Split Activation Group Button is Disabled. Please Check Your Unit Quantity");
			}
			waitTillWebElementIsVisible("splitAG", splitAGButton);
			WaitUntilElementIsClickable(splitAGButton);
			waitAndClickOnElement(splitAGButton);
			//Activation Group Management
			waitTillWebElementIsVisible("AddAGButton",".q-dialog .q-card #add-new-ag-btn");
			WaitUntilElementIsClickable(addAGButton);
			waitAndClickOnElement(addAGButton);
//			String name = activationGroupName.substring(0,activationGroupName.indexOf(" "));
//			String date = activationGroupName.substring(activationGroupName.indexOf(" ")+1);
            WaitUntilElementIsClickable(activationGroupSplitName);
			waitAndClickOnElement(activationGroupSplitName);
//			splitAGName = name+"-1 "+date;
			activationGroupSplitName.sendKeys(Keys.chord(Keys.CONTROL, "a"),splitAGName);
//			sendingValueToWebElement("activationGroupName",activationGroupSplitName,splitAGName);
			handleWait(1000);
			waitAndClickOnElement("popUpHeader", ".q-dialog  .q-card .q-card__section .text-h6.col");
			if(driver.findElements(By.cssSelector(".q-dialog .q-card #next-btn[aria-disabled=true]")).size()==1){
				Assert.fail("Activation Group is not added and Next button is Disabled");
			}
			WaitUntilElementIsClickable(nextButton);
			waitAndClickOnElement(nextButton);
			//Unit Distribution
			int unitSize = driver.findElements(By.cssSelector(".q-dialog .q-card .q-table tr.cursor-pointer")).size()+1;
            for (int index =3 ; index<=unitSize; index++){
				//Clicking on 2nd dropdown to assign AG
				waitUnTillWebElementIsVisible("unidropdown",".q-card .q-table tr.cursor-pointer:nth-child("+index+") #activation-group-select");
				WebElement AGName = driver.findElement(By.cssSelector(".q-dialog .q-card .q-table tr.cursor-pointer:nth-child("+index+") .q-td #activation-group-select"));
				clickOnDropDownAndSelectValue("activationGroup",AGName,splitAGName);
			}
			WaitUntilElementIsClickable(nextButton);
			waitAndClickOnElement(nextButton);
			//Clicking on Split Button
			WaitUntilElementIsClickable(nextButton);
			waitAndClickOnElement(nextButton);
			Thread.sleep(3000);
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			MasterHooks.agSplit.set("Split");
			//Validating the Activation Group Split
			int dropDownOptionSize = clickOnDropDownAndGetDropDownSize("activationGroupSplitSelector",activationGroupSelector);
			if(dropDownOptionSize==1){
				Assert.fail("Activation Group has not been Split Successfully");
			}
		} catch (InterruptedException | IOException e) {
			throw new RuntimeException(e);
		}
	}
	public void validateTheEntityStatus(String entityLevel, String entityStatus) {
		log.info("User is on the " + entityLevel + " level");
		waitTillWebElementIsVisible("EntityStatus", ".q-card .q-toolbar .q-item:nth-child(4) .q-item__label:nth-child(2)");
		String entityStatusText=driver.findElement(By.cssSelector(".q-card .q-toolbar .q-item:nth-child(4) .q-item__label:nth-child(2)")).getText();
		if(entityStatus.equalsIgnoreCase(entityStatusText)){
		log.info(entityLevel + " level is on " + entityStatusText + " status and is matching with provided status " + entityStatus);
		}
    else{
	Assert.fail("Status of " + entityLevel + " level is " + entityStatusText + " which is not matching with the provided status " +entityStatus);
		}
	}

	public void selectValueOfPurchaseOrder() {
		try {
			log.info("User is going to enter the Purchase Organization and Purchase Order");
			waitTillWebElementIsVisible("Purchase Organization", purchaseOrder);
			if (!(getValuesFromExcel("Inception","Activation Group Level","Purchase Organization",0).equalsIgnoreCase("-") ||
					getValuesFromExcel("Inception","Activation Group Level","Purchase Organization",0).equalsIgnoreCase(""))) {
				clickOnDropDownToTypeAndSelectValue("Purchase Organization", purchaseOrganization, getValuesFromExcel("Inception","Activation Group Level","Purchase Organization",0));
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
			if (!(getValuesFromExcel("Inception","Activation Group Level","Purchase Order",0).equalsIgnoreCase("-") ||
					getValuesFromExcel("Inception","Activation Group Level","Purchase Order",0).equalsIgnoreCase(""))) {
				clickOnDropDownToTypeAndSelectValue("Purchase Order", purchaseOrder, getValuesFromExcel("Inception","Activation Group Level","Purchase Order",0));
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public void validateLeaseEndBalances(String standard) {
		try {
			log.info("checking the Lease End Account Balances for " + standard + " standard");
			waitTillWebElementIsVisible("ContextMenu", ".q-toolbar #context-menu-title-btn");
			waitAndClickOnElement("ContextMenu", ".q-toolbar #context-menu-title-btn");
			Thread.sleep(500);
			waitTillWebElementIsVisible("RemianingBalanceItmes", ".q-menu #remaining-balances-item");
			waitAndClickOnElement("RemainingBalanceItmes", ".q-menu #remaining-balances-item");
			waitUntilLoadingSpinnerIsGone("nlaJournalSpinner");
			if (standard.equalsIgnoreCase("GAAP")||standard.equalsIgnoreCase("GAAP - Service Contract")||
			standard.equalsIgnoreCase("GAAP - Short term")||standard.equalsIgnoreCase("GAAP - Low value")) {
				waitTillWebElementIsVisible("GAAPTab", ".q-card .q-tabs .q-tab:nth-child(2)");
				waitAndClickOnElement("GAAPTab", ".q-card .q-tabs .q-tab:nth-child(2)");
				Thread.sleep(1000);
			}
			if (standard.equalsIgnoreCase("IAS - Service Contract") || standard.equalsIgnoreCase("GAAP - Service Contract")
			|| standard.equalsIgnoreCase("IAS - Short term")||standard.equalsIgnoreCase("GAAP - Short term")||
					standard.equalsIgnoreCase("IAS - Low value")||standard.equalsIgnoreCase("GAAP - Low value")){
				String textOfAccountType = driver.findElement(By.cssSelector(".q-card .q-tab-panels .q-table__bottom")).getText();
				String updatedtextOFAccount = textOfAccountType.substring(textOfAccountType.indexOf("N"));
				if (updatedtextOFAccount.equalsIgnoreCase("No accounts to show")) {
					waitAndClickOnElement("CancelButton", ".q-card #close-btn");
					Thread.sleep(1000);
				}
			}

			else {
				waitTillWebElementIsVisible("AccountTypes", ".q-card .q-tab-panels .q-table tbody tr .text-left:nth-child(1)");
				int sizeOfAccountTypes = driver.findElements(By.cssSelector(".q-card .q-tab-panels .q-table tbody tr .text-left:nth-child(1)")).size();
				int sizeOfRemainingBalance = driver.findElements(By.cssSelector(".q-card .q-tab-panels .q-table tbody tr .text-left:nth-child(2)")).size();
				for (int i = 1; i <= sizeOfAccountTypes; i++) {
					String nameOfAccount = driver.findElement(By.cssSelector(".q-card .q-tab-panels .q-table tbody tr:nth-child(" + i + ") .text-left:nth-child(1)")).getText();
					String remainingBalance = driver.findElement(By.cssSelector(".q-card .q-tab-panels .q-table tbody tr:nth-child(" + i + ") .text-left:nth-child(2)")).getText();
					if (remainingBalance.equalsIgnoreCase("0")) {
						log.info("Account Type is: " + nameOfAccount + " and Remaining Balance is " + remainingBalance);

					} else {
						Assert.fail("Account Type: " + nameOfAccount + " contains remaining balance " + remainingBalance + " which is greater than 0");
					}

				}

				waitAndClickOnElement("CancelButton", ".q-card #close-btn");
				Thread.sleep(1000);

			}

		}catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

	}

	public void validateUnitStatus(String unitStatus) {
		log.info("Going to Validate the Unit Status at AG level");
        try {
            waitAndClickOnElement("UnitTab", "#q-app .q-page #unit-list-step");
			Thread.sleep(1000);
			waitTillWebElementIsVisible("UnitStatus", ".q-page .q-table__container .q-table .q-tr:nth-child(2) .q-td:nth-child(6)");
			String unitStatusOnAG=driver.findElement(By.cssSelector(".q-page .q-table__container .q-table .q-tr:nth-child(2) .q-td:nth-child(6)")).getText();
			if(unitStatusOnAG.equalsIgnoreCase(unitStatus)){
				log.info("Correct Unit status: " + unitStatusOnAG + " is showing at AG level and matching with " +unitStatus+ " provided");
			}
			else{
				Assert.fail("Wrong Unit status: " + unitStatusOnAG + " is showing at AG level and not matching with " +unitStatus+ " provided");
			}

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
		}
	}
	public void termStateCheckboxes() {
		try {
			waitTillWebElementIsEnabled("termState", "#q-app .q-page .q-table__container #term-state-selector");
			waitAndClickOnElement(termState);
			int termStateSize = driver.findElements(By.cssSelector(".q-menu .q-item .q-checkbox")).size();
			for (int i = 1; i <= termStateSize; i++) {
				if (driver.findElements(By.cssSelector(".q-menu .q-item:nth-child(" + i + ") .q-checkbox[aria-checked='false']")).size() == 1) {
					waitAndClickOnElement("termStateCheckbox", ".q-menu .q-item:nth-child(" + i + ") .q-checkbox");
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				}
			}
			waitTillWebElementIsVisible("T&CAGTab", termsAndConditonsAGTab);
			waitAndClickOnElement(termsAndConditonsAGTab);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	public void enterIndexationInformation(int row, DataTable dt) {
		log.info("Entering Indexation Information at AG Term Level");
		List<Map<String, String>> data = dt.asMaps(String.class, String.class);
		try {
			waitTillWebElementIsVisible("agTermEdit", ".q-page .absolute tr:nth-child(" + (row + 1) + ") .q-td #edit-btn");
			waitAndClickOnElement("agTermEdit", ".q-page .absolute tr:nth-child(" + (row + 1) + ") .q-td #edit-btn");
			waitTillWebElementIsVisible("baseIndexRate", ".qcard-dialogue #base-indexation-rate-input");
			if (!data.get(0).get("Base Index").equalsIgnoreCase("null")) {
				waitAndClickOnElement(baseIndexRate);
				clearField(baseIndexRate);
				sendingValueToWebElement("baseIndexationRate", baseIndexRate, data.get(0).get("Base Index"));
			}
			waitTillWebElementIsVisible("baseIndexDate", ".qcard-dialogue #base-indexation-date-input-input");
			if (!data.get(0).get("Base Indexation Date").equalsIgnoreCase("null")) {
				waitAndClickOnElement(baseIndexDate);
				clearField(baseIndexDate);
				sendingValueToWebElement("baseIndexationDate", baseIndexDate, data.get(0).get("Base Indexation Date"));
			}
			waitTillWebElementIsVisible("refIndexRate", ".qcard-dialogue #reference-indexation-rate-input");
			if (!data.get(0).get("Reference index").equalsIgnoreCase("null")) {
				waitAndClickOnElement(refIndexRate);
				clearField(refIndexRate);
				sendingValueToWebElement("refIndexationRate", refIndexRate, data.get(0).get("Reference index"));
			}
			waitTillWebElementIsVisible("refIndexDate", ".qcard-dialogue #reference-indexation-date-input-input");
			if (!data.get(0).get("Reference Indexation Date").equalsIgnoreCase("null")) {
				waitAndClickOnElement(refIndexDate);
				clearField(refIndexDate);
				sendingValueToWebElement("refIndexationDate", refIndexDate, data.get(0).get("Reference Indexation Date"));
			}
			clickOnSubmitPopup("Submit / Add");
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void changeBaseIndexValue(String value) {
		log.info("Editing the term to change Base Index Value");
		try {
			waitAndClickOnElement(termEditBtn);
			waitTillWebElementIsVisible("Base Index Rate in Term", baseIndexRateInTerm);
			baseIndexRateInTerm.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
			sendingValueToWebElement("baseValue",baseIndexRateInTerm,value);
			handleWait(1000);
			waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
			log.info("Different Base and Reference Indexation rates successfully entered");
			waitAndClickOnElement(submitButton);
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			log.info("Term successfully edited");
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void gaapIndexationTreatmentType(String treatmentType){
		log.info("Entering GAAP Indexation type as " +treatmentType);
		waitTillWebElementIsVisible("Accounting Tab", accountingAGTab);
		try {
			waitAndClickOnElement(accountingAGTab);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		waitTillWebElementIsVisible("gaapIndexationTreatmentType", gaapIndexationType);
		try {
			clickOnDropDownAndSelectValue("gaapIndexationTreatmentType", gaapIndexationType, treatmentType);
		} catch (InterruptedException | IOException e) {
			throw new RuntimeException(e);
		}
		waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
		waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
	}
}
