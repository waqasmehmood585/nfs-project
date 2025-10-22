package com.nakisa.nlaAutomation.stepDefinitions;

import com.nakisa.nlaAutomation.pageObjects.LeaseComponents_Validations;
import com.nakisa.nlaAutomation.stepServices.LeaseComponent_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import org.springframework.beans.factory.annotation.Autowired;

public class LeaseComponent_StepDefinition extends DriverFactory {

	@Autowired
	LeaseComponent_StepService c_leaseComponent_stepService;

	@And("User tries to create Lease Component")
	public void userTriesToCreateLeaseComponent() {
		c_leaseComponent_stepService.createLeaseComponentEntity();
	}

	@Then("User enters data under Lease Component Definition page")
	public void userEntersDataUnderLeaseComponentDefinitionPage() throws InterruptedException {
		c_leaseComponent_stepService.userFillsDataUnderDefinitionOfLeaseComponents();
	}

	@And("User add new term and condition at {string} level")
	public void userAddNewTermAndCondition(String level) throws InterruptedException {
		c_leaseComponent_stepService.fillsTheTermAndConditionInformation(level);
	}

	@Then("User enters the carry over balance tab of lease component")
	public void User_enters_the_carry_over_balance_tab_of_lease_component() {
		c_leaseComponent_stepService.fillCarryOverBalance();
	}
	@Then("User enters data under the carry over balance page")
	public void User_enters_data_under_the_carry_over_balance_page() {
		c_leaseComponent_stepService.fillCarrOverBalanceValues();
	}
	@Then("User should not be able to callback Lease Component {string} when AG is Reverted")
	public void verifyLeaseComponentIsNotCallBack(String leaseComponentName){
		leaseComponents_validations.get().verifyLeaseComponentIsNotCallBack(leaseComponentName);
	}

    @Then("User validate all COB fields at {string}")
    public void userValidateAllCOBFields(String level) {
		c_leaseComponent_stepService.userValidateAllCOBFields(level);
	}
	@Then("User clicks on Clone feature at Lease Component event level to clone the term")
	public void userClicksOnCloneFeatureAtLeaseComponentEventLevelToCloneTheTerm() {
		c_leaseComponent_stepService.cloningOfTerm();
    }

	@Then("User validate the Unit distribution table")
	public void userValidateUnitTable(){
		c_leaseComponent_stepService.unitTableValidations();
	}
	@Then("User change the {string} value to {string}")
	public void userChangeValue(String fieldName, String value) {
		c_leaseComponent_stepService.userChangeValue(fieldName, value);
	}

    @Then("User verify that {string} is {string}")
    public void userVerifyThatFieldIsDisabled(String fieldName, String conditionToCheck) {
		c_leaseComponent_stepService.userVerifyThatFieldIsDisabled(fieldName,conditionToCheck);
    }

	@Then("User copy the Lease Component with Name as {string} and Terms & Conditions {string}")
	public void userCopyTheLeaseComponentWithNameAsAndTermsConditions(String lcName, String termsCheckBox) {
		c_leaseComponent_stepService.copyLcWithName(lcName, termsCheckBox);
	}


	@Then("User validate the fields of {string} with the data")
	public void userValidateTheFieldWithData(String entity, DataTable dt) {
		c_leaseComponent_stepService.validationOfFields(entity,dt);

	}
}

