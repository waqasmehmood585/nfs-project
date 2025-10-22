package com.nakisa.nlaAutomation.pageObjects;

import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

@Slf4j
public class C_AG_Event_PageObject extends Common_BasePage_PageObject{

	public @FindBy(css = "#q-app .q-page #context-menu-event") WebElement addEventBtn;
	public @FindBy(css = ".q-dialog .q-field #name-input") WebElement activationGroupName;
	public @FindBy(css = ".q-dialog .q-field [v-css-selectors='modification-date-input']") WebElement modificationDateAG;
	public @FindBy(css = ".q-dialog #modification-reasons") WebElement modificationReason;
	public @FindBy(css = ".q-page .q-field [v-css-selectors='rou-end-date-input']") WebElement rouEndDateValueDefinition;
	public @FindBy(css = ".q-dialog .dialog-body [v-css-selectors='asset'][tabindex='0']") WebElement IASTab;
	public @FindBy(css = ".q-dialog .dialog-body .row #is-termination") WebElement terminateEventBtn;
	public @FindBy(css = ".q-dialog .dialog-body .row #is-enforce-non-lease-on-indexation") WebElement useNonLeaseOnIndexation;
	public @FindBy(css = ".q-dialog .row #termination-type") WebElement terminationReasonField;
	public @FindBy(css = ".q-dialog .row #casualty-penalty-input") WebElement penaltyAmountField;
	public @FindBy(css = ".q-dialog .q-field #decrease-in-term-ifrs-input") WebElement assetDecreaseAmountIASTerms;
	public @FindBy(css = ".q-dialog .q-field #nbv-on-event-date-input") WebElement NBVonEventDate;
	public @FindBy(css = ".q-dialog .q-field #recoverable-amount-input") WebElement RecoverableAmount;
	public @FindBy(css = ".q-dialog .q-card .q-panel #decrease-in-asset-input") WebElement assetDecreaseAmountGAAPAsset;
	public @FindBy(css = ".q-dialog .q-card .q-panel #decrease-in-asset-input") WebElement assetDecreaseAmountIASAsset;
	public @FindBy(css = ".q-dialog .q-field #gain-amount-input") WebElement GainAmount;
	public @FindBy(css = "#q-app .q-page #revision-btn") WebElement RevisionDropdownClick;
	public @FindBy(css = ".q-menu .q-list") WebElement DropDownList;
	public @FindBy(css = ".q-menu .q-list .delete-btn #rev-id-draft-btn") WebElement RevisionDelete;
	public @FindBy(css = ".q-menu .q-list .q-item:nth-child(1)") WebElement RevisionDraftClick;


	MasterAgreement_PageObject masterAgreementPageObject = masterAgreement_PageObject.get();
	public C_AG_Event_PageObject() {
		super();
		log.info("Driver is inside this class: " + this.getClass().getSimpleName());
		PageFactory.initElements(driver, this);
	}

	public void create_AG_Event(String agEventName, String modDate, String reason, String terminate, String penaltyAmount) {
		log.info("Creating AG Event");
		waitTillWebElementIsVisible("addEventBtn",addEventBtn);
		WaitUntilElementIsClickable(addEventBtn);
		addEventBtn.click();
		WaitUntilElementIsClickable(activationGroupName);
		activationGroupName.click();
		sendingValueToWebElement("activationGroupName", activationGroupName, agEventName);
		//waitTillWebElementIsVisible("modificationDateAG",modificationDateAG);
		modificationDateAG.click();
		sendingValueToWebElement("modificationDateAG",modificationDateAG, modDate);
		waitUntilLoadingSpinnerIsShown("nlaAddButtonLoader");
		waitUntilLoadingSpinnerIsGone("nlaAddButtonLoader");
		//WaitUntilElementIsClickable(modificationReason);

		if(terminate.equalsIgnoreCase("Terminate")){
			WaitUntilElementIsClickable(terminateEventBtn);
			terminateEventBtn.click();
			try {
				WaitUntilElementIsClickable(terminationReasonField);
				clickOnDropDownAndSelectValue("terminationReasonField" ,terminationReasonField, reason);
			} catch (InterruptedException | IOException exception) {
				exception.printStackTrace();
			}
			WaitUntilElementIsClickable(penaltyAmountField);
			penaltyAmountField.sendKeys(penaltyAmount);

		} else {
			try {
				WaitUntilElementIsClickable(modificationReason);
				clickOnDropDownAndSelectValue("modificationReason", modificationReason, reason);
			} catch (InterruptedException | IOException exception) {
				exception.printStackTrace();
			}
		}

		waitTillWebElementIsVisible("submitButton",submitButton);
		WaitUntilElementIsClickable(submitButton);
		clickOnSubmitPopup("Submit / Add");
		waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
		waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");

		log.info("Completed Creating the AG Event");
	}

	public void deleteDraft() {
		try {
			waitAndClickOnElement(RevisionDropdownClick);
			waitUntilLoadingSpinnerIsShown("nlaRevisionButtonLoader");
			waitUntilLoadingSpinnerIsGone("nlaRevisionButtonLoader");
//			Thread.sleep(2500);
			waitTillWebElementIsVisible("dropDownItems", ".q-menu .q-list .q-item:nth-child(1) .q-item__section #open-in-new-btn");
			waitAndClickOnElement(RevisionDraftClick);
			waitAndClickOnElement(RevisionDelete);
			clickOnSubmitPopup("Submit / Add");
			waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
			waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			log.info("Draft Deleted Successfully");
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

	}
}
