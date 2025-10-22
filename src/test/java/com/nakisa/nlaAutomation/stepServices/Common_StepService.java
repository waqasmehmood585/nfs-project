package com.nakisa.nlaAutomation.stepServices;

public interface Common_StepService {

	void loginInTheApplication();

	void loginInTheApplicationWithSpecificUser();

	void openAppLinkInNewTab(String microServiceName);

	void openAppLinkInNewTabWithSpecificUser(String microServiceName);

	void userOpensTheApplicationUrlWithSpecificUser(String microServiceName, String user);

	void changeWorkFlowTransition(String entityLevel, String workFlowStateToChange) throws InterruptedException;

	void openHamburgerMenuAndClickOnOption(String mainOption,String subOption,String option);

	void approverRejectorComment(String entityLevel, String workFlowStateToChange, String comment);

	void validatingCommentorMessage(String entityLevel, String validationMessage);
}
