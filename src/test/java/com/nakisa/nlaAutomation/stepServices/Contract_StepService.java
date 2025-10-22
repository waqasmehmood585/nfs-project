package com.nakisa.nlaAutomation.stepServices;

import io.cucumber.datatable.DataTable;

public interface Contract_StepService {

	void createContractEntity();

	void leaseDeterminationQuestions() throws InterruptedException;

	void contractDefinitionTab();

	void addContractPartners(String level);
	void verifyLessorAddPartners(String partnerRole);
	void verifyLessorReplacePartners(String partnerRole);
	void editPartnersContact(DataTable dataTable);
	void deletePartnersContact(String partnerNum,String contactNum);
	void deletePartner(String partnerNum, String level);
	void fillUpContractAccounting();

	void userAddNewContractEvent(String eventName);

	void userReplacePartners(String partnerNum, String newName,	String replacementDate);

	void userAddContactOnContractPartner(DataTable dataTable);
	void userSubscribesNotifications();

    void createEntity(DataTable dataTable);

	void dataUnderContractDefinitionPage(DataTable dataTable);

	void dataUnderContractAccountingPage(DataTable dataTable);

	void deleteMultiPartners(String arg0);
	void copyContractWithName(String contName, String compCode);
	void validationOfContractFields(DataTable dt);
	void validatingContractPartnerAndAccountingData();
}
