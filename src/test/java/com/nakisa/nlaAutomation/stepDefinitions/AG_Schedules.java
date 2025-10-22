package com.nakisa.nlaAutomation.stepDefinitions;

import com.nakisa.nlaAutomation.utils.DriverFactory;

import io.cucumber.java.en.Then;

public class AG_Schedules extends DriverFactory {

	@Then("User clicks on {string} standard to validate {string} schedule view at {string} level")
	public void userClicksOnSchedulesViewToValidateSchedulesValues(String standardName, String scheduleView, String scheduleLevel) {
		aG_SchedulesPage.get().clickOnSchedulesAndValidate(standardName, scheduleView, scheduleLevel);
//		MasterHooks.checkForValidationFailures.set("");
		if (standardName.equalsIgnoreCase("GAAP")) {
			aG_SchedulesPage.get().moveBackToActivationGroupLevel();
		}
	}

	@Then("User clicks on {string} schedule to validate {string} status of {string} is {string}")
	public void userClicksOnSchedulesViewToCheckTheScheduleStatus(String standardName, String statusView, String scheduleView, String status) {
		aG_SchedulesPage.get().scheduleViewStatusValidation(standardName,statusView,scheduleView,status);
//		MasterHooks.checkForValidationFailures.set("");
		if (standardName.equalsIgnoreCase("GAAP")) {
			aG_SchedulesPage.get().moveBackToActivationGroupLevel();
		}
	}

	@Then("User clicks on {string} schedule to validate {string} status of {string} is {string} from {string} to {string}")
	public void userClicksOnSchedulesViewToCheckTheScheduleStatusOnPeriod(String standardName, String statusView, String scheduleView, String status, String fromDate, String toDate) {
		aG_SchedulesPage.get().scheduleStatusOnPeriods(standardName,statusView,scheduleView,status, fromDate, toDate);
//		MasterHooks.checkForValidationFailures.set("");
		if (standardName.equalsIgnoreCase("GAAP")) {
			aG_SchedulesPage.get().moveBackToActivationGroupLevel();
		}
	}
	@Then("User clicks on {string} standard to validate {string} status of {string} is {string}")
	public void userClicksOnStandardToCheckTheDocumentStatus(String standardName, String statusView, String document, String status) {
		aG_SchedulesPage.get().clickOnDocumentAndCheckStatus(standardName, statusView, document, status);
	}

	@Then("User clicks on {string} standard to validate {string} documents")
	public void userClicksOnStandardToValidateDocuments(String standardName, String documentLevel ) {
		aG_SchedulesPage.get().clickOnJournalsToValidateJournalDocuments(standardName, documentLevel);
	}

	@Then("User Search {string} with ID")
	public void searchEntityWithID(String entityLevel) {
		aG_SchedulesPage.get().searchEntityWithID(entityLevel);
	}

	@Then("User clicks on {string} standard to Post the Charge {string} with validation as {string}")
	public void userClicksOnChargesToAddPostCharge(String standardName, String chargeNumber, String validation) {
		aG_SchedulesPage.get().chargePosting(standardName, chargeNumber, validation);
	}

    @Then("User clicks on {string} Standard to {string} the {string} of {string} Schedules from {string} to {string}")
    public void ClicksOnScheduleAndPost(String standardName, String action, String paymentType, String scheduleView, String postingFrom, String postingTo) {
		aG_SchedulesPage.get().ClicksOnScheduleAndPost(standardName,action,paymentType, scheduleView, postingFrom,postingTo);
		if (standardName.equalsIgnoreCase("GAAP")) {
			aG_SchedulesPage.get().moveBackToActivationGroupLevel();
		}
    }

	@Then("User clicks on {string} Standard to {string} the {string} of {string} Schedules")
	public void userClicksOnStandardToPostTheSchedules(String standardName, String action, String paymentType, String scheduleView) {
		aG_SchedulesPage.get().ClickingOnScheduleAndPosting(standardName,action,paymentType, scheduleView);
	}
	@Then("User verify the {string} is removed with {string}")
	public void userVerifyThedocumentTypeWithdate(String documentType, String date) {
		aG_SchedulesPage.get().validationOfDates(documentType, date);
		}

	@Then("user changed the Document Date as {string} and Posting Date as {string}")
	public void userChangedTheDocumentDateAsAndPostingDateAs(String docDate, String postDate) {
		aG_SchedulesPage.get().changeDocAndPostDates(docDate, postDate);
	}

	@Then("User clicks on {string} standard to see the History of {string} from {string} Schedules")
	public void userClicksOnStandardToSeeTheHistoryOfFromSchedules(String standardName, String paymentType, String scheduleView) {
		aG_SchedulesPage.get().historyOfPostingData(standardName, paymentType, scheduleView);
	}

	@Then("User clicks on {string} standard to validate the IBR rate at {string} level")
	public void userClicksOnStandardToValidateTheIBRRateAtLevel(String standardName, String level) {
		aG_SchedulesPage.get().validationOfIBRRate(standardName, level);

	}
}



