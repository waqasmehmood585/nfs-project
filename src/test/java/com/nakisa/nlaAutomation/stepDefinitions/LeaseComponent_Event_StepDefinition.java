package com.nakisa.nlaAutomation.stepDefinitions;

import com.nakisa.nlaAutomation.stepServices.LeaseComponent_Event_StepService;
import com.nakisa.nlaAutomation.stepServices.LeaseComponent_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import org.springframework.beans.factory.annotation.Autowired;

public class LeaseComponent_Event_StepDefinition extends DriverFactory {

	@Autowired
	LeaseComponent_Event_StepService c_leaseComponent_event_stepService;
	@Autowired
	LeaseComponent_StepService c_leaseComponent_stepService;

	@Then("User click on the {string} with Name {string}")
	public void user_click_on_the_lease_component(String level,String name) throws InterruptedException {
		c_leaseComponent_event_stepService.moveBetweenEntities(level,name);
	}

	@Then("User adds a New LC Event {string}")
	public void user_adds_a_New_LC_Event(String nameOfEvent) throws InterruptedException {
		c_leaseComponent_event_stepService.createLeaseComponentEvent(nameOfEvent);
	}

	@And("user add new terms and conditions To The Event with SelectType {string} PaymentTerm {string} "
			+ "Name {string} Amount {string} PaymentFrequency {string} FirstPaymentDate {string} "
			+ "EndPaymentDate {string} PaymentType {string}")
	public void userAddNewTermsAndCondtionsAndFillsIt(String selectType, String paymentType, String name, String amount,
													  String paymentFrequency, String firstPaymentDate, String endPaymentDate, String paymentTiming) throws InterruptedException {
		//c_leaseComponent_stepService.fillsTheTermsAndConditionsOfLeaseComponents(selectType, paymentType, name, amount, 
		//		paymentFrequency, firstPaymentDate, endPaymentDate, paymentTiming);
	}
	@Then("User clicks on Terms & Conditions on Lease Component to check the editable term")
	public void userClicksOnTermsConditionsOnLeaseComponentToCheckTheEditableTerm() {
		c_leaseComponent_event_stepService.checkingEditableTerm();
	}
}
