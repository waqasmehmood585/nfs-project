package com.nakisa.nlaAutomation.stepDefinitions;

import com.nakisa.nlaAutomation.stepServices.MasterAgreement_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import org.springframework.beans.factory.annotation.Autowired;

public class MaterAgreement_StepDefinition extends DriverFactory {

	@Autowired
	MasterAgreement_StepService masterAgreement_StepService;

	@Given("Reading test case inputs from excel file {string}")
	public void readingTestCaseInputsFromExcelFile(String testCaseNumber) {
		masterAgreement_PageObject.get().readTCInputs(testCaseNumber,"Inputs" );
	}

	@Given("Reading test case inputs from excel file {string} using sheet {string}")
	public void readingTestCaseInputsFromExcelFileUsingSheet(String fileName, String sheetName) {
		masterAgreement_PageObject.get().readTCInputs(fileName, sheetName);
	}

	@Then("User tries to create Master Agreement")
	public void userTriesToCreateMasterAgreement() {
		masterAgreement_StepService.createMasterAgreementEntity();
	}

    @Then("user enters the Partners tab of {string} to validate Contact Import")
    public void userEntersThePartnersTabOf(String entityLevel) {
		masterAgreement_PageObject.get().changeTabAndValidate(entityLevel);
    }
	@Then("User creates Master Agreement")
	public void userTriesToCreateMasterAgreement(DataTable dataTable) {
		masterAgreement_StepService.createMasterAgreementEntity(dataTable);
	}

	@Then("User delete the {string}")
	public void userDeleteThe(String level) {
		masterAgreement_StepService.deleteEntity(level);
	}

	@Then("User selects partner role and partner under MLA Partner page Level")
	public void userSelectsPartnerRoleAndPartnerUnderMLAPartnerPageLevel() {
		masterAgreement_StepService.addMasterAgreementPartners();
	}

	@Then("User copy the Master Agreement with Name as {string} and Lease area {string}")
	public void userCopyTheMasterAgreementWithNameAndLeaseArea(String mlaName, String fieldType) {
		masterAgreement_StepService.copyMasterAgreementWithNameAndLeaseArea(mlaName, fieldType);
	}

	@Then("User validate the fields of Master Agreement with the following data")
	public void userValidateTheFieldsOfMasterAgreementWithTheFollowingData(DataTable dt) {
		masterAgreement_StepService.validationOfMasterAgreementFields(dt);
	}

	@Then("User selects a record from {string} landing page to copy")
	public void userSelectsRecordFromLandingPageToCopy(String entityName) {
		masterAgreement_StepService.SelectingRecordFromLandingPageToCopy(entityName);
	}
}
