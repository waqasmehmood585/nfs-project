package com.nakisa.nlaAutomation.stepServices.ui;

import com.nakisa.nlaAutomation.utils.DriverFactory;
import com.nakisa.nlaAutomation.stepDefinitions.MasterHooks;
import com.nakisa.nlaAutomation.stepServices.Common_StepService;

import org.apache.directory.api.util.Strings;
import org.springframework.stereotype.Component;

@Component
public class UI_Common_StepService extends DriverFactory implements Common_StepService {

	@Override
	public void loginInTheApplication() {
		common_LoginPage.get().getLoginPage();
		common_LoginPage.get().initializeUsers();
		common_LoginPage.get().enterUsernamePasswordAndClickOnSumbit("");
	}

	@Override
	public void loginInTheApplicationWithSpecificUser() {
		common_LoginPage.get().getLoginPage();
		common_LoginPage.get().enterUsernamePasswordAndClickOnSumbit("Specific");
	}

	@Override
	public void openAppLinkInNewTab(String microServiceName) {
		common_LoginPage.get().selectAndOpenAppLinkInNewTab(microServiceName);
		if(Strings.isEmpty(MasterHooks.loginFlag.get()) || !MasterHooks.configurationProperties.get().getRunAutomationOnNCPBuild()) {
			common_LoginPage.get().initializeUsers();
			common_LoginPage.get().enterUsernamePasswordAndClickOnSumbit("");
		}
//		if(microServiceName.equalsIgnoreCase("nakisa-financial-suite")){
//			common_LoginPage.get().closeWhatsNewPopUp();
//		}
	}

	@Override
	public void openAppLinkInNewTabWithSpecificUser(String microServiceName) {
		common_LoginPage.get().selectAndOpenAppLinkInNewTab(microServiceName);
		if(Strings.isEmpty(MasterHooks.loginFlag.get()) || !MasterHooks.configurationProperties.get().getRunAutomationOnNCPBuild()) {
			common_LoginPage.get().enterUsernamePasswordAndClickOnSumbit("Specific");
		}
//		if( microServiceName.equalsIgnoreCase("nakisa-financial-suite")){
//			common_LoginPage.get().closeWhatsNewPopUp();
//		}
	}

	@Override
	public void userOpensTheApplicationUrlWithSpecificUser(String microServiceName, String user) {
		common_LoginPage.get().selectAndOpenAppLinkInNewTab(microServiceName);
		common_LoginPage.get().enterUsernamePasswordAndClickOnSumbit(user);
//		if( microServiceName.equalsIgnoreCase("nakisa-financial-suite")){
//			common_LoginPage.get().closeWhatsNewPopUp();
//		}
	}

	@Override
	public void changeWorkFlowTransition(String entityLevel, String workFlowStateToChange) throws InterruptedException {
		common_BasePage.get().changeWorkFlowState(entityLevel,workFlowStateToChange);
	}

	@Override
	public void openHamburgerMenuAndClickOnOption(String mainOption,String subOption,String option) {
		hamburgerMenu_pageObject.get().openHamburgerMenuAndClickOnOptions(mainOption,subOption,option);
	}
	@Override
	public void approverRejectorComment(String entityLevel, String workFlowStateToChange, String comment){
		common_BasePage.get().approverRejectorComment(entityLevel, workFlowStateToChange, comment);
	}
	@Override
	public void validatingCommentorMessage(String entityLevel, String validationMessage){
		common_BasePage.get().validatingCommentorMessage( entityLevel,  validationMessage);
	}
}
