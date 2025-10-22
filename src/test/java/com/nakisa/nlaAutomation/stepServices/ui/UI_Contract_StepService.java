package com.nakisa.nlaAutomation.stepServices.ui;

import com.nakisa.nlaAutomation.stepServices.Contract_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;

import io.cucumber.datatable.DataTable;

import org.springframework.stereotype.Component;

@Component
public class UI_Contract_StepService extends DriverFactory implements Contract_StepService {

	@Override
	public void createContractEntity() {
		c_CreationPage.get().addContract();
	}

	@Override
	public void leaseDeterminationQuestions() throws InterruptedException {
		c_CreationPage.get().fillTheLeaseDeterminationQuestions();
	}

	@Override
	public void contractDefinitionTab() {
		c_CreationPage.get().fillTheDefinitionTab();
	}

	@Override
	public void addContractPartners(String level) {
		c_CreationPage.get().enterContractPartnersInformation(level);
	}

	@Override
	public void verifyLessorAddPartners(String partnerRole) {
		c_CreationPage.get().verifyLessorAddPartners(partnerRole);
	}

	@Override
	public void verifyLessorReplacePartners(String partnerRole) {
		c_CreationPage.get().verifyLessorReplacePartners(partnerRole);
	}

	@Override
	public void editPartnersContact(DataTable dataTable) {
		c_CreationPage.get().editPartnersContact(dataTable);
	}

	@Override
	public void deletePartnersContact(String partnerNum, String contactNum) {
		c_CreationPage.get().deletePartnersContact(partnerNum,contactNum);
	}
	@Override
	public void deletePartner(String partnerNum, String level) {
		c_CreationPage.get().deletePartner(partnerNum, level);
	}

	@Override
	public void fillUpContractAccounting() {
		c_CreationPage.get().fillContractAccounting();
	}

	@Override
	public void userAddNewContractEvent(String eventName) {
		c_CreationPage.get().addNewContractEvent(eventName);
	}

	@Override
	public void userReplacePartners(String partnerNum, String newName, String replacementDate) {
		c_CreationPage.get().replacePartnerContractEvent(partnerNum, newName, replacementDate);
		
	}

	@Override
	public void userAddContactOnContractPartner(DataTable dataTable) {
		c_CreationPage.get().userAddContactOnContractPartner(dataTable);
	}
	@Override
	public void userSubscribesNotifications() {
		c_CreationPage.get().userSubscribesNotifications();
	}

	@Override
	public void createEntity(DataTable dataTable) {
		c_CreationPage.get().contractEntityCodeCoverage(dataTable);
	}

	@Override
	public void dataUnderContractDefinitionPage(DataTable dataTable) {
		c_CreationPage.get().contractDefinitionPageCodeCoverage(dataTable);
	}

	@Override
	public void dataUnderContractAccountingPage(DataTable dataTable) {
		c_CreationPage.get().contractAccountingPageCodeCoverage(dataTable);
	}

	@Override
	public void deleteMultiPartners(String arg0) {
		c_CreationPage.get().deleteMultiPartners(arg0);
	}
	@Override
	public void copyContractWithName(String contName, String compCode){
		c_CreationPage.get().copyContractWithName(contName, compCode);
	}
	@Override
	public void validationOfContractFields(DataTable dt){
		c_CreationPage.get().validationOfContractFields(dt);
	}
	@Override
	public void validatingContractPartnerAndAccountingData(){
		c_CreationPage.get().validatingContractPartnerAndAccountingData();
	}

}
