package com.nakisa.nlaAutomation.stepServices;

import io.cucumber.datatable.DataTable;

public interface MasterAgreement_StepService {

	void createMasterAgreementEntity();
	void createMasterAgreementEntity(DataTable dataTable);

	void deleteEntity(String level);

    void addMasterAgreementPartners();
	void copyMasterAgreementWithNameAndLeaseArea(String mlaName, String fieldType);
	void validationOfMasterAgreementFields(DataTable dt);
	void SelectingRecordFromLandingPageToCopy(String entityName);
}
