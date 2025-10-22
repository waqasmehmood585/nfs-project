package com.nakisa.nlaAutomation.stepServices.ui;

import com.nakisa.nlaAutomation.stepServices.LeaseComponent_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;

import io.cucumber.datatable.DataTable;
import org.springframework.stereotype.Component;

@Component
public class UI_LeaseComponent_StepService extends DriverFactory implements LeaseComponent_StepService {

	@Override
	public void createLeaseComponentEntity() {
		c_LeaseComponentPage.get().addLeaseComponents();
	}

	@Override
	public void userFillsDataUnderDefinitionOfLeaseComponents() {
		c_LeaseComponentPage.get().fillDefinitionForLeaseComponents();
	}

	@Override
	public void fillsTheTermAndConditionInformation(String level) throws InterruptedException {
		c_LeaseComponentPage.get().fillTermAndConditionInfo(level);
	}
	@Override
	public void fillCarryOverBalance() {
		c_LeaseComponentPage.get().fillCarryOverBalance();

	}
	@Override
	public void fillCarrOverBalanceValues() {
		c_LeaseComponentPage.get().fillCarrOverBalanceValues();
	}

	@Override
	public void userValidateAllCOBFields(String level) {
		leaseComponents_validations.get().userValidateAllCOBFields(level);
	}
	@Override
	public void cloningOfTerm(){
		c_LeaseComponentPage.get().cloningOfTerm();
	}

	@Override
	public void unitTableValidations() {
		leaseComponents_validations.get().unitTableValidations();
	}

	@Override
	public void userChangeValue(String fieldName, String value) {
		c_LeaseComponentPage.get().userChangeValue(fieldName, value);
	}

	@Override
	public void userVerifyThatFieldIsDisabled(String fieldName, String conditionToCheck) {
		leaseComponents_validations.get().userVerifyThatFieldIsDisabled(fieldName,conditionToCheck);
	}
	@Override
	public void copyLcWithName(String lcName, String termsCheckBox){
		c_LeaseComponentPage.get().copyLcWithName(lcName, termsCheckBox);
	}
	@Override
	public void validationOfFields(String entity,DataTable dt){
		leaseComponents_validations.get().validationOfFields(entity,dt);
	}

}
