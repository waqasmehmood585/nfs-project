package com.nakisa.nlaAutomation.stepDefinitions;

import com.nakisa.nlaAutomation.stepServices.ActivationGroup_Event_StepService;
import com.nakisa.nlaAutomation.stepServices.ActivationGroup_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import org.springframework.beans.factory.annotation.Autowired;

public class ActivationGroup_Event_StepDefinition extends DriverFactory {

	@Autowired
	ActivationGroup_Event_StepService c_ag_event_stepService;

	@Autowired
	ActivationGroup_StepService c_activation_group_stepService;

	@Then("User tries to create AG Event {string} With ModificationDate {string} And Reason {string} Terminate Option {string} "
			+ "and Penalty Amount Option {string}")
	public void user_tries_to_create_AG_Event_with_ModificationDate_and_Reason_terminateOption_penaltyAmount(String agEventName, String modDate, String reason, String terminate, String penaltyAmount) throws InterruptedException {
		c_ag_event_stepService.createAGEvent(agEventName, modDate, reason,terminate , penaltyAmount);
	}

    @Then("User deletes the draft revision")
    public void userDeletesTheDraftRevison() {
		c_ag_event_stepService.deleteDraft();
    }
}
