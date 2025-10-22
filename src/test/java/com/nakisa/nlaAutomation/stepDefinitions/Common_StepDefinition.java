package com.nakisa.nlaAutomation.stepDefinitions;

import com.nakisa.nlaAutomation.stepServices.Common_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import org.springframework.beans.factory.annotation.Autowired;

public class Common_StepDefinition extends DriverFactory {

	@Autowired
	private Common_StepService common_StepService;

	@Given("User logins into the NCP cockpit with correct credentials")
	public void userLoginsIntoTheNCPCockpitWithCorrectCredentials() throws InterruptedException {
		if(!MasterHooks.configurationProperties.get().getRunEnvironment().equalsIgnoreCase("local")) {
			common_StepService.loginInTheApplication();
		}
	}

	@Given("User logins into the NCP cockpit with specific credentials")
	public void userLoginsIntoTheNCPCockpitWithSpecificCredentials() {
		if(!MasterHooks.configurationProperties.get().getRunEnvironment().equalsIgnoreCase("local")) {
			common_StepService.loginInTheApplicationWithSpecificUser();
		}
	}

	@Then("User opens the {string} application url")
	public void userOpensTheApplicationUrl(String microServiceName) throws InterruptedException {
//		if (MasterHooks.configurationProperties.get().getRunAutomationOnNCPBuild()) {
		common_StepService.openAppLinkInNewTab(microServiceName);
//		}
	}

	@Then("User opens the {string} application url with specific user")
	public void userOpensTheApplicationUrlWithSpecificUser(String microServiceName) {
		common_StepService.openAppLinkInNewTabWithSpecificUser(microServiceName);
	}

	@Then("User sends the {string} for {string} workflow transition")
	public void userSendsTheMasterAgreementFor(String entityLevel, String workFlowStateToChange) throws InterruptedException {
		common_StepService.changeWorkFlowTransition(entityLevel, workFlowStateToChange);
	}

	@Then("Open Hamburger menu & Click On {string}-->{string}-->{string}")
	public void openHamburgerMenuAndClickOnOption(String mainOption,String subOption,String option){
		common_StepService.openHamburgerMenuAndClickOnOption(mainOption,subOption,option);
	}
	@Then("User sends the {string} for {string} workflow with a message {string}")
	public void userSendsTheForWorkflowWithAMessage(String entityLevel, String workFlowStateToChange, String comment) {
		common_StepService.approverRejectorComment(entityLevel, workFlowStateToChange, comment);
	}

	@Then("User validate the {string} with a message {string}")
	public void userValidateAMessage(String entityLevel, String validationMessage) {
		common_StepService.validatingCommentorMessage(entityLevel, validationMessage);
	}

	@Then("User stores the {string} id")
	public void userStoresTheId(String entityName) {
		common_BasePage.get().storeId(entityName);
	}

	@Then("User opens the {string} application url with specific user {string}")
	public void userOpensTheApplicationUrlWithSpecificUser(String microServiceName, String user) {
		common_StepService.userOpensTheApplicationUrlWithSpecificUser(microServiceName, user);
	}

    @Then("User checks that {string} column exists on {string} landing page")
    public void userChecksThatColumnExistsOnLandingPage(String columnName,String entity) {
		common_BasePage.get().checkColumnOnLandingPage(columnName, entity);
    }
}
