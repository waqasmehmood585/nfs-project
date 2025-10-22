package com.nakisa.nlaAutomation.stepServices;

import io.cucumber.datatable.DataTable;

public interface ActivationGroup_StepService {

	void fillAGTermsAndCondition();
	void exerciseTermsAndConditions(String indexes);
	void fillActivationGroupUnitList();
	void changeRecordsPerPageOption(String size);
	void fillActivationGroupAccounting();
	void fillActivationGroupClassification();
	void fillActivationGroupClassificationFields(String level);
	void fillActivationGroupAccountingCPIFields(String level);
	void indexTermsAndConditions(String indexes);
	void createAGEvents(String event);
	void changeRouEndDate(String rouEndDate);
    void changeContractIBRRateEvent(String level);
	void changeActiviationGroupLevel(String level);
	void changeActivationGroupStatusLevel(String status, String entity, String level);
	void revertAG(String reversalReason);
	void revertReport();
	void verifyPosting(String standard ,String scheduleView);
	void entityStatus(String entity, String status);
	void addCharges(String level);
	void definitionPageValues(String level);
	void moveToChargeTab();
	void moveToUnitListTab();
	void verifyScheduleGenerationFailure();
    void verifyAGButtonStatus();
	void VerifyThatModificationRecognationFieldsAreEnabled(String Date);
	void verifyButtonStatus(String buttonName,String buttonStatus,String agStatus);
	void verifyActivationGroupStatusLevel(String level, String status, String existence);
	void verifyAGLevelWithReferenceNumber(String level, String status);
	void verifyAGRevertChanges();
	void splitActivationGroup();
	void numberOfActivationGroups(String numberOfAG);

	void validateTheClassificationsTabValues(String level);

	void ValidateConfirmClassificationValue(String value);
	void validateTheStatus(String entityLevel, String entityStatus);
	void verifyDocumentAndPostingDate(String event);
	void VerifyIndexationInformation(int row, DataTable dataTable);
	void unitIdCheck();
	void conditionalIndexationCheckbox(String fieldName);

    void verifyCategories(String entityType);

	void dataInAccountingTabOfActivationGroup(DataTable dataTable);

	void dataUnderTheCarryOverBalancePageInActivationGroup();

    void selectValueOfPurchaseOrder();

    void validateLeaseEndBalances(String standard);

	void generateSchedulesAndCancel();
	void verifyJournalsButton();

    void validateUnitStatus(String unitStatus);

	void enterIndexationInformation(int row, DataTable dt);

	void indexationTreatmentType(String treatmentType);

	void changeBaseIndexValue(String value);

	void unitCostObjectValidations();
}
