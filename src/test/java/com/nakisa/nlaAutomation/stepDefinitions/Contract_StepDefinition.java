package com.nakisa.nlaAutomation.stepDefinitions;

import com.nakisa.nlaAutomation.stepServices.Contract_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import org.springframework.beans.factory.annotation.Autowired;

public class Contract_StepDefinition extends DriverFactory {

	@Autowired
	Contract_StepService c_creation_stepService;

	@And("User tries to create Contract")
	public void userTriesToCreateContract() {
		c_creation_stepService.createContractEntity();
	}

	@Then("User answers all the questions")
	public void userAnswersAllTheQuestions() throws InterruptedException {
		c_creation_stepService.leaseDeterminationQuestions();
	}

	@Then("User enters data under Contract Definition page")
	public void userEntersDataUnderContractDefinitionPage() {
		c_creation_stepService.contractDefinitionTab();
	}

	@Then("User selects partner role and partner under Contract Partner page at {string} Level")
	public void userSelectsPartnerRoleAndPartnerUnderContractPartnerPage(String level) {
		c_creation_stepService.addContractPartners(level);
	}

	@Then("User verify that for GVI Contract User should not be able to Add {string} Partners")
	public void userVerifyForGVIContractUserNotAbleToAddPartners(String partnerRole) {
		c_creation_stepService.verifyLessorAddPartners(partnerRole);
	}

	@Then("User verify that for GVI Contract User should not be able to Replace {string} Partners")
	public void userVerifyForGVIContractUserNotAbleToReplacePartners(String partnerRole) {
		c_creation_stepService.verifyLessorReplacePartners(partnerRole);
	}

	@Then("User edit Partner contact details")
	public void userEditAndDeletePartnersContact(DataTable dataTable) {
		c_creation_stepService.editPartnersContact(dataTable);
	}

	@Then("User delete the contact {string} from the Partner {string}")
	public void userDeletePartnersContact(String partnerNum, String contactNum) {
		c_creation_stepService.deletePartnersContact(partnerNum, contactNum);
	}

	@Then("User deletes the Partner {string} at {string} level")
	public void userDeletethePartnersAtLevel(String partnerNum, String level) {
		c_creation_stepService.deletePartner(partnerNum,level);
	}
	@Then("User fills the Accounting Tab of Contract")
	public void userFillsTheAccountingTabOfContract() {
		c_creation_stepService.fillUpContractAccounting();
	}

	@Then("User adds a New Contract Event {string}")
	public void userAddNewContractEvent(String eventName) {
		c_creation_stepService.userAddNewContractEvent(eventName);
	}

	@Then ("User Replace Partner {string} to {string} with Replacement Date {string}")
	public void userReplacepartners(String partnerNum, String newName, String replacementDate) {
		c_creation_stepService.userReplacePartners(partnerNum, newName, replacementDate);
	}

	@Then ("User add Contact on Contract Partners")
	public void userAddContactOnContractPartner(DataTable dataTable) {
		c_creation_stepService.userAddContactOnContractPartner(dataTable);
	}

	@Then("User subscribes usernames in notifications tab")
	public void userSubscribesNotifications() {
		c_creation_stepService.userSubscribesNotifications();
	}

    @And("User creates Contract")
    public void userCreatesContract(DataTable dataTable) {
		c_creation_stepService.createEntity(dataTable);
    }

    @Then("enters data under Contract Definition page")
    public void entersDataUnderContractDefinitionPage(DataTable dataTable) {
		c_creation_stepService.dataUnderContractDefinitionPage(dataTable);
    }

    @Then("User fills Accounting Tab of Contract")
    public void userFillsAccountingTabOfContract(DataTable dataTable) {
		c_creation_stepService.dataUnderContractAccountingPage(dataTable);
    }

    @Then("user delete partners {string}")
    public void userDeletePartners(String arg0) {
		c_creation_stepService.deleteMultiPartners(arg0);
    }

	@Then("User copy the Contract with Name as {string} and Company Code as {string}")
	public void userCopyTheContractWithNameAsAndCompanyCodeAs(String contName, String compCode) {
		c_creation_stepService.copyContractWithName(contName, compCode);
    }

	@Then("User validate the fields of Contract with the following data")
	public void userValidateTheFieldsOfContractWithTheFollowingData(DataTable dt) {
		c_creation_stepService.validationOfContractFields(dt);
	}


	@Then("User click on Partners and Accounting Page to validate the contract data")
	public void userClickOnPartnersAndAccountingPageToValidateTheContractData() {
		c_creation_stepService.validatingContractPartnerAndAccountingData();
	}
}

