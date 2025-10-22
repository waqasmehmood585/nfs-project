package com.nakisa.nlaAutomation.stepDefinitions;

import com.nakisa.nlaAutomation.stepServices.ActivationGroup_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import org.springframework.beans.factory.annotation.Autowired;

public class ActivationGroup_StepDefinition extends DriverFactory {

	@Autowired
	ActivationGroup_StepService c_activation_group_stepService;

	@Then("User enters the Terms and Conditions tab of Activation Group")
	public void user_enters_the_terms_and_conditions_tab_of_Activation_Group() {
		c_activation_group_stepService.fillAGTermsAndCondition();
	}

	@Then("User enters the Accounting tab of Activation Group")
	public void user_enters_the_Accounting_tab_of_Activation_Group(){
		c_activation_group_stepService.fillActivationGroupAccounting();
	}

	@Then("User enters the Classification tab of Activation Group")
	public void User_enters_the_Classification_tab_of_Activation_Group(){
		c_activation_group_stepService.fillActivationGroupClassification();
	}
	@Then("User enters the {string} Classification Values of Activation Group")
	public void User_enters_the_Classification_Values_of_Activation_Group(String level){
		c_activation_group_stepService.fillActivationGroupClassificationFields(level);
	}

	@Then("User enters the {string} Consumer Price Index Values of Activation Group")
	public void User_enters_the_Consumer_Price_Index_Values_of_Activation_Group(String level){
		c_activation_group_stepService.fillActivationGroupAccountingCPIFields(level);
	}

	@Then("User exercise the Term and Condition number {string}")
	public void user_exercise_the_Term_and_Condition_number(String indexes) {
		c_activation_group_stepService.exerciseTermsAndConditions(indexes);
	}

	@Then("User Completes the Activation Group Unit List")
	public void User_completes_the_Activation_Group_Unit_List() {
		c_activation_group_stepService.fillActivationGroupUnitList();
	}

	@Then("User index the Term and Condition number {string}")
	public void User_index_the_Term_and_Condition_number(String indexes) {
		c_activation_group_stepService.indexTermsAndConditions(indexes);
	}

    @Then("user modifies the Base Index Value to {string}")
    public void user_changes_base_index_value(String value) {
        c_activation_group_stepService.changeBaseIndexValue(value);
    }

	@Then("User tries to create AG Event at {string} Level")
	public void user_tries_to_create_AG_Event(String event){
		c_activation_group_stepService.createAGEvents(event);
	}

	@And("User makes the Rou End Date Change To {string}")
	public void user_makes_the_rou_end_date_change_to(String rouEndDate) {
		c_activation_group_stepService.changeRouEndDate(rouEndDate);
	}

	@Then("User enters the Terms and Conditions tab of Activation Group Event")
	public void User_enters_the_Terms_and_Conditions_tab_of_Activation_Group_Event() {
		c_activation_group_stepService.fillAGTermsAndCondition();
	}

	@And("User Disables the terms and conditions {string}")
	public void user_disable_the_terms_and_Conditions(String index) {
		c_activation_group_stepService.exerciseTermsAndConditions(index);
	}

	@And("User Exercises the terms and conditions {string}")
	public void user_exercises_the_terms_and_Conditions(String index) {
		c_activation_group_stepService.exerciseTermsAndConditions(index);
	}

	@Then("User changes the record to be shown per page to {string}")
	public void user_changes_the_record_to_be_shown_per_page(String size) {
		c_activation_group_stepService.changeRecordsPerPageOption(size);
	}
	@Then("User changes the Contract Rate or IBR at {string} level")
	public void user_changes_the_Contract_Rate_or_IBR_at(String level) {
		c_activation_group_stepService.changeContractIBRRateEvent(level);
	}
	@Then("User Click on Activation Group {string} Level")
	public void user_Click_on_Activation_Group_Level(String level) {
		c_activation_group_stepService.changeActiviationGroupLevel(level);
	}
	@Then("User clicks on {string} status {string} with name {string}")
	public void user_Click_on_Activation_Group_Level(String status, String entity,String level) {
		c_activation_group_stepService.changeActivationGroupStatusLevel(status, entity, level);
	}
	@Then("User revert the AG with reversal reason as {string}")
	public void user_Clicks_on_Revert_Button_toRevert_the_AG(String reversalReason) {
		c_activation_group_stepService.revertAG(reversalReason);
	}
	@Then("User verify the AG Revert Report")
	public void user_Verify_AG_Revert_Report() {
		c_activation_group_stepService.revertReport();
	}
	@Then("User clicks on {string} standard to validate Post button is disabled in {string}")
	public void user_Verify_Post_Button_Is_Disabled(String standard ,String scheduleView) {
		c_activation_group_stepService.verifyPosting(standard, scheduleView);
		if (standard.equalsIgnoreCase("GAAP")) {
			aG_SchedulesPage.get().moveBackToActivationGroupLevel();
		}
	}
	@Then("User verify that {string} status is {string}")
	public void user_Verify_the_Entity_Status(String entity, String status) {
		c_activation_group_stepService.entityStatus(entity, status);
	}

	@Then("User clicks on Charges to add charge at {string} level")
	public void userClicksOnChargesToAddChargeAtLevel(String level) {
		c_activation_group_stepService.addCharges(level);
	}

	@Then("User enters the {string} Definition Page values of Activation Group")
	public void userEntersTheDefinitionPageValuesOfActivationGroup(String level) {
		c_activation_group_stepService.definitionPageValues(level);
	}
	@Then("User enters the Terms and Conditions tab of Lease Component")
	public void userEntersTheTermsAndConditionsTabOfLeaseComponent() {
		c_activation_group_stepService.fillAGTermsAndCondition();
	}

	@Then("User enters the Charge List tab of Activation Group")
	public void userEntersTheChargeListTabOfActivationGroup() {
		c_activation_group_stepService.moveToChargeTab();
	}

    @Then("User enters the Unit List tab of Activation Group")
    public void userEntersTheUnitListTabOfActivationGroup() {
		c_activation_group_stepService.moveToUnitListTab();
    }

    @And("User verify that Schedule Generation is failing")
    public void userVerifyThatScheduleGenerationIsFailing() {
		c_activation_group_stepService.verifyScheduleGenerationFailure();
    }

	@Then("User validate the button status of Activation Group")
	public void userValidateTheButtonStatusOfActivationGroup() {
		c_activation_group_stepService.verifyAGButtonStatus();}

	@And("User Verify that Modification recognition fields are enabled for {string}")
	public void userVerifyThatModificationRecognationFieldsAreEnabledForAnd(String date)  {
		c_activation_group_stepService.VerifyThatModificationRecognationFieldsAreEnabled( date);
	}

	@And("User verify that {string} button is {string} for {string} Activation Group")
	public void userVerifyTheButtonIsDisabled(String buttonName, String buttonStatus, String agStatus) {
		c_activation_group_stepService.verifyButtonStatus(buttonName,buttonStatus,agStatus);
	}
	@Then("User verify that Activation Group {string} with {string} status should {string}")
	public void user_Verify_AG_With_Status(String level,String status, String existence) {
		c_activation_group_stepService.verifyActivationGroupStatusLevel(level, status,existence);
	}

	@Then("User verify that Activation Group {string} exists with {string} status and reference number")
	public void user_Verify_AG_With_Status_And_Reference_Number(String level,String status) {
		c_activation_group_stepService.verifyAGLevelWithReferenceNumber(level, status);

	}
	@Then("User verify the changes in Activation Group after AG Revert")
	public void user_Verify_Changes_In_AG() {
		c_activation_group_stepService.verifyAGRevertChanges();
	}

    @Then("User validate the {string} Classifications Tab Values")
    public void userValidateTheClassificationsTabValues(String level) {
		c_activation_group_stepService.validateTheClassificationsTabValues(level);
    }

	@Then("user validate confirm Classification Value {string}")
	public void userValidateConfirmClassificationValue(String value) {
		c_activation_group_stepService.ValidateConfirmClassificationValue(value);
	}
	@Then("User Split the Activation Group")
	public void user_splits_Activation_Group() {
		c_activation_group_stepService.splitActivationGroup();
	}

	@Then("User verify the total number of Activation Groups are {string}")
	public void user_verify_Number_Of_Activation_Group(String numberOfAG) {
		c_activation_group_stepService.numberOfActivationGroups(numberOfAG);
	}
	@Then("User validate the {string} with status {string}")
    public void userValidateTheStatus(String entityLevel, String entityStatus) {
	c_activation_group_stepService.validateTheStatus(entityLevel, entityStatus);
    }
	@Then("User validate the Modification Recognition Popup dates at {string} Level")
	public void VerifyDocumentAndPostingDate(String event) {
		c_activation_group_stepService.verifyDocumentAndPostingDate(event);
	}
	@Then("User click on Term {int} to validate Indexation Information")
	public void userClickOnTermAndVerifyTheIndexationInformationBaseIndexRefIndex(int row,DataTable dt) {
		c_activation_group_stepService.VerifyIndexationInformation(row, dt);
	}
	@Then("User checks the Unit Id of Charge")
	public void userChecksTheUnitIdOfCharge() {
		c_activation_group_stepService.unitIdCheck();
	}

    @Then("User verify that {string} is not present")
    public void userVerifyThatIsNotPresent(String fieldName) {
		c_activation_group_stepService.conditionalIndexationCheckbox(fieldName);
    }
	@Then("user clicks on Notification tab at {string} level to verify the categories")
	public void verifyCategoryOfNotificationTab(String level) {
		c_activation_group_stepService.verifyCategories(level);
	}

    @Then("User sends the Data in Accounting tab of Activation Group")
    public void userSendsTheDataInAccountingTabOfActivationGroup(DataTable dataTable) {
		c_activation_group_stepService.dataInAccountingTabOfActivationGroup(dataTable);
    }

    @Then("User enters data under the carry over balance page in Activation Group")
    public void userEntersDataUnderTheCarryOverBalancePageInActivationGroup() {
		c_activation_group_stepService.dataUnderTheCarryOverBalancePageInActivationGroup();
    }
    @Then("User clicks on all fields in AG")
    public void userClicksOnAllFieldsInAG() {
		activationGroup_validations.get().agAllFields();
    }

    @Then("user selects the value of Purchase Organization and Purchase Order")
    public void userSelectsTheValueOfPurchaseOrganizationAndPurchaseOrder() {
		c_activation_group_stepService.selectValueOfPurchaseOrder();
    }

    @Then("User validated the Lease End Account Balances for {string}")
    public void userValidatedTheLeaseEndAccountBalancesForIASAndGAAP(String standard) {
		c_activation_group_stepService.validateLeaseEndBalances(standard);
    }
	@And("User clicks on Generate Schedules button and click on Cancel button")
	public void userClicksOnGenerateSchedulesButtonAndClickOnCancelButton() {
		c_activation_group_stepService.generateSchedulesAndCancel();
	}

	@Then("User verify that there should be No Journals Button")
	public void userVerIfyThatThereShouldBeNoJournalsButton() {
		c_activation_group_stepService.verifyJournalsButton();
	}

	@Then("User validated the Unit status as {string} on AG level")
	public void userValidatedTheUnitStatusAsOnAGLevel(String unitStatus) {
		c_activation_group_stepService.validateUnitStatus(unitStatus);

	}

	@Then("User click on Term {int} to enter Indexation Information")
	public void userEnterIndexationInformation(int row, DataTable dt) {
		c_activation_group_stepService.enterIndexationInformation(row, dt);

	}

	@Then("User selects the GAAP Indexation Treatment Type as {string}")
	public void userSelectsTheGAAPIndexationTreatmentTypeAs(String treatmentType) {
		c_activation_group_stepService.indexationTreatmentType(treatmentType);
	}

    @Then("User validates the Unit Cost Object Modification")
    public void userValidatesTheUnitCostObjectModification() {
		c_activation_group_stepService.unitCostObjectValidations();
    }
}
