package com.nakisa.nlaAutomation.stepServices.ui;

import com.nakisa.nlaAutomation.stepServices.MasterAgreement_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.it.Data;
import org.springframework.stereotype.Component;

@Component
public class UI_MasterAgreement_StepService extends DriverFactory implements MasterAgreement_StepService {

	@Override
	public void createMasterAgreementEntity() {
		masterAgreement_PageObject.get().addMasterAgreement();
	}
	@Override
	public void createMasterAgreementEntity(DataTable dataTable) {
		masterAgreement_PageObject.get().addMasterAgreement(dataTable);
	}

	@Override
	public void deleteEntity(String level) {
		masterAgreement_PageObject.get().deleteEntity(level);
	}

	@Override
	public void addMasterAgreementPartners() {
		masterAgreement_PageObject.get().addPartners();
	}
	@Override
	public void copyMasterAgreementWithNameAndLeaseArea(String mlaName, String fieldType){
		masterAgreement_PageObject.get().copyMasterAgreementWithNameAndLeaseArea(mlaName, fieldType);
	}
	@Override
	public void validationOfMasterAgreementFields(DataTable dt){
		masterAgreement_PageObject.get().validationOfMasterAgreementFields(dt);
	}
	@Override
	public void SelectingRecordFromLandingPageToCopy(String entityName){
		masterAgreement_PageObject.get().SelectingRecordFromLandingPageToCopy(entityName);
	}

}
