package com.nakisa.nlaAutomation.pageObjects;
import com.nakisa.nlaAutomation.stepDefinitions.MasterHooks;
import lombok.extern.slf4j.Slf4j;
import org.apache.directory.api.util.Strings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.IOException;

@Slf4j
public class C_LeaseComponent_Event_PageObject extends Common_BasePage_PageObject{

	public @FindBy(css = "#q-app .q-page #context-menu-event") WebElement addEventBtn;
	public @FindBy(css = ".q-dialog .q-field #name-input") WebElement LeaseComponentEventName;
	public @FindBy(css = "#q-app .q-page #terms-conditions-step") WebElement termsConditionsTabLeaseComponent;
	public @FindBy(css = ".q-page .absolute #lease-component-terms-and-conditions-form-expansion #edit-btn") WebElement editBtnTermsAndCondition;
	public @FindBy(css = "#q-app .q-drawer .q-page .q-item #contract-selection") WebElement currentLeaseComponent;
	public @FindBy(css = "#q-app .q-drawer .q-page .q-item #lease-component-selector") WebElement leaseComponentSelector;
	public @FindBy(css = "#q-app .q-drawer .q-page .q-item #contract-selector") WebElement contractSelector;
	public @FindBy(css = "#q-app .q-drawer .q-page #master-agreement-selection .q-item__section--main") WebElement masterAgreementSelector;
	public @FindBy(css = "#q-app .q-drawer .q-page .q-item #activation-group-selector") WebElement activationGroupSelector;

	public C_LeaseComponent_Event_PageObject() {
		super();
		log.info("Driver is inside this class: " + this.getClass().getSimpleName());
		PageFactory.initElements(driver, this);
	}

	public void moveBetweenEntities(String level, String name) throws InterruptedException {
		log.info("Clicking on the current" + level);
		String AGidPath;
		try {
			switch(level) {
				case "Master Agreement":
					WaitUntilElementIsClickable(masterAgreementSelector);
					waitAndClickOnElement(masterAgreementSelector);
					break;
				case "Contract":
					WaitUntilElementIsClickable(contractSelector);
					clickOnDropDownToTypeAndSelectValue("contractSelector",contractSelector,name);
					break;
				case "Lease Component":
					WaitUntilElementIsClickable(leaseComponentSelector);
					handleWait(1500);
					clickOnDropDownToTypeAndSelectValue("leaseComponentSelector",leaseComponentSelector,name);
					break;
				case "Activation Group":
					WaitUntilElementIsClickable(activationGroupSelector);
					int dropDownSize = clickOnDropDownAndGetDropDownSize("activationGroupSplitSelector",activationGroupSelector);
					if(dropDownSize>=2){
						clickOnDropDownToTypeAndSelectValue("activationGroupSplitSelector",activationGroupSelector,name);
                    }else{
						clickOnDropDownToTypeAndSelectValue("activationGroupSelector",activationGroupSelector,name);
                    }
					waitTillWebElementIsVisible("activationGroupID","#q-app .q-page .q-item.col-auto:nth-child(1) .q-item__label:nth-child(2)");
					AGidPath = driver.findElement(By.cssSelector("#q-app .q-page .q-item.col-auto:nth-child(1) .q-item__label:nth-child(2)")).getText();
					MasterHooks.searchAGID.set(AGidPath);
                    break;
                case "Split Activation Group":
					WaitUntilElementIsClickable(activationGroupSelector);
					if(!Strings.isEmpty(MasterHooks.agSplit.get())){
						name = c_ActivationGroupPage.get().splitAGName;
					}
					clickOnDropDownToTypeAndSelectValue("activationGroupSplitSelector",activationGroupSelector,name);
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
					//Assigning the Split AG ID to sane Local Thread (Search AG ID)
					AGidPath = driver.findElement(By.cssSelector("#q-app .q-page .q-item.col-auto:nth-child(1) .q-item__label:nth-child(2)")).getText();
					MasterHooks.searchAGID.set(AGidPath);
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
		waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
		log.info("On the current" + level);
	}
	public void create_LeaseComponent_Event(String nameOfEvent) throws InterruptedException {
		log.info("Creating Lease Component Event");
		waitTillWebElementIsVisible("addEventBtn",addEventBtn);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#q-app .q-page #context-menu-event")));
		WaitUntilElementIsClickable(addEventBtn);
		//addEventBtn.click();
		waitForPageToLoad(6);
		waitAndClickOnElement(addEventBtn);
		waitTillWebElementIsVisible("LeaseComponentEventName",LeaseComponentEventName);
		WaitUntilElementIsClickable(LeaseComponentEventName);
		waitAndClickOnElement(LeaseComponentEventName);
		sendingValueToWebElement("activationGroupName", LeaseComponentEventName, nameOfEvent);
		clickOnSubmitPopup("Submit / Add");
		waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
		waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
		log.info("Created Lease Component Event");
	}

	public void edit_Terms_And_Conditions() {
		log.info("Editing Terms And Conditions");
		waitTillWebElementIsVisible("termsConditionsTabLeaseComponent",termsConditionsTabLeaseComponent);
		WaitUntilElementIsClickable(termsConditionsTabLeaseComponent);
		termsConditionsTabLeaseComponent.click();
		waitUntilLoadingSpinnerIsShown("nlaTabChange");
		waitUntilLoadingSpinnerIsGone("nlaTabChange");
		waitTillWebElementIsVisible("editBtnTermsAndCondition",editBtnTermsAndCondition);
		editBtnTermsAndCondition.click();
	}
	public void checkingEditableTerm() {
		try {
			log.info("User is checking whether the term is Editable or not");
			waitTillWebElementIsVisible("Terms&Conditions", termsConditionsTabLeaseComponent);
			waitAndClickOnElement(termsConditionsTabLeaseComponent);
			waitTillWebElementIsVisible("EditButton", ".q-table .q-tr:nth-child(2) #edit-btn");
			if(driver.findElements(By.cssSelector(".q-table .q-tr:nth-child(2) .q-btn[aria-disabled=true]#edit-btn")).size()==1){
				Assert.fail("Lease Component is already approved");
			}
			else{
				log.info("User can Edit the Terms!!!!!");
			}
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

	}
}
