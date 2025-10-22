package com.nakisa.nlaAutomation.stepServices;

import io.cucumber.datatable.DataTable;

public interface LeaseComponent_StepService {

	void createLeaseComponentEntity();

	void userFillsDataUnderDefinitionOfLeaseComponents();

	void fillsTheTermAndConditionInformation(String level) throws InterruptedException;
	void fillCarryOverBalance();
	void fillCarrOverBalanceValues();

    void userValidateAllCOBFields(String level);
	void  cloningOfTerm();

	void unitTableValidations();
	void userChangeValue(String fieldName, String value);
	void userVerifyThatFieldIsDisabled(String fieldName, String conditionToCheck);
	void copyLcWithName(String lcName, String termsCheckBox);
	void validationOfFields(String entity,DataTable dt);

}
