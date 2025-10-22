package com.nakisa.nlaAutomation.stepServices.ui;

import com.nakisa.nlaAutomation.stepServices.ActivationGroup_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;

import io.cucumber.datatable.DataTable;
import org.springframework.stereotype.Component;

@Component
public class UI_ActivationGroup_StepService extends DriverFactory implements ActivationGroup_StepService {

	@Override
	public void fillAGTermsAndCondition() {
		c_ActivationGroupPage.get().fillTermsAndConditionsOfAg();
	}

	@Override
	public void exerciseTermsAndConditions(String indexes) {
		c_ActivationGroupPage.get().enableOrDisableTermsAndConditions(indexes);
	}

	@Override
	public void fillActivationGroupUnitList() {
		c_ActivationGroupPage.get().activateUnitList();
	}

	@Override
	public void changeRecordsPerPageOption(String size) {
		c_ActivationGroupPage.get().changePageRecordSize(size);
	}

	@Override
	public void fillActivationGroupAccounting() {
		c_ActivationGroupPage.get().fillAGAccounting();
	}

	@Override
	public void fillActivationGroupClassification() {
		c_ActivationGroupPage.get().fillAGClassification();
	}

	@Override
	public void fillActivationGroupClassificationFields(String level) {
		c_ActivationGroupPage.get().fillUseFulLifeAndClassification(level);
	}

	@Override
	public void fillActivationGroupAccountingCPIFields(String level) {
		c_ActivationGroupPage.get().fillActivationGroupCPI(level);
	}

	@Override
	public void indexTermsAndConditions(String indexes) {
		c_ActivationGroupPage.get().indexedOrUnIndexedTermsAndConditions(indexes);
	}

	@Override
	public void createAGEvents(String event) {
		c_ActivationGroupPage.get().create_AG_Events(event);
	}

	@Override
	public void changeRouEndDate(String rouEndDate) {
		c_ActivationGroupPage.get().changeRouEndDateAG(rouEndDate);
	}
	@Override
	public void changeContractIBRRateEvent(String level) {
		c_ActivationGroupPage.get().changeContractIBRRateEvent(level);
	}

	@Override
	public void changeActiviationGroupLevel(String level) {
		c_ActivationGroupPage.get().changeActiviationGroupLevel(level);
	}
	@Override
	public void changeActivationGroupStatusLevel(String status, String entity, String level) {
		c_ActivationGroupPage.get().changeActivationGroupStatusLevel(status,entity, level);
	}
	@Override
	public void revertAG(String reversalReason) {
		c_ActivationGroupPage.get().revertAG(reversalReason);
	}
	@Override
	public void revertReport() {activationGroup_validations.get().revertReport();}
	@Override
	public void verifyPosting(String standard ,String scheduleView) {activationGroup_validations.get().verifyPostings(standard,scheduleView);}
	@Override
	public void entityStatus(String entity, String status) {
		c_ActivationGroupPage.get().entityStatus(entity, status);
	}
	@Override
	public void addCharges(String level) {
		c_ActivationGroupPage.get().addCharges(level);
	}

	@Override
	public void definitionPageValues(String level) {
		c_ActivationGroupPage.get().assetInformationDefinitionPage(level);
	}
	@Override
	public void moveToChargeTab() {
		c_ActivationGroupPage.get().moveToChargeTab();
	}

	@Override
	public void moveToUnitListTab() {
		c_ActivationGroupPage.get().moveToUnitListTab();
	}

	@Override
	public void verifyScheduleGenerationFailure() {
		c_ActivationGroupPage.get().verifyScheduleGenFailure();
	}
	@Override
	public void verifyAGButtonStatus(){
		c_ActivationGroupPage.get().verifyAGButtonStatus();
	}

	@Override
	public void VerifyThatModificationRecognationFieldsAreEnabled(String date)  {
		c_ActivationGroupPage.get().VerifyThatModificationRecognationFieldsAreEnabled(date);
	}
	@Override
	public void verifyButtonStatus(String buttonName, String buttonStatus, String agStatus) {activationGroup_validations.get().verifyButtonStatus(buttonName,buttonStatus,agStatus);}

	@Override
	public void verifyActivationGroupStatusLevel(String level, String status,String existence) {
		activationGroup_validations.get().verifyActivationGroupStatusLevel(level,status,existence);
	}
	@Override
	public void verifyAGLevelWithReferenceNumber(String level, String status ) {
		activationGroup_validations.get().verifyAGLevelWithReferenceNumber(level,status);
	}
	@Override
	public void verifyAGRevertChanges() {
		activationGroup_validations.get().verifyAGRevertChanges();
	}

	@Override
	public void validateTheClassificationsTabValues(String level) {
		c_ActivationGroupPage.get().validateTheClassificationsTabValues(level);
	}

	@Override
	public void ValidateConfirmClassificationValue(String value) {
		activationGroup_validations.get().validateConfirmClassificationValue(value);
	}
	@Override
	public void verifyDocumentAndPostingDate(String event) {
		activationGroup_validations.get().verifyDocumentAndPostingDate(event);
	}
	@Override
	public void splitActivationGroup()  {
		c_ActivationGroupPage.get().splitActivationGroup();
	}
	@Override
	public void numberOfActivationGroups(String numberOfAG) {
		activationGroup_validations.get().numberOfActivationGroups(numberOfAG);
	}
	@Override
	public void VerifyIndexationInformation(int row, DataTable dataTable){
		activationGroup_validations.get().VerifyIndexationInformation(row, dataTable);
	}
	@Override
	public  void validateTheStatus(String entityLevel, String entityStatus){
		c_ActivationGroupPage.get().validateTheEntityStatus(entityLevel, entityStatus);
	}
	@Override
	public void unitIdCheck()  {
		activationGroup_validations.get().unitIdCheck();

	}
	@Override
	public void conditionalIndexationCheckbox(String fieldName)  {
		activationGroup_validations.get().conditionalIndexationCheckbox(fieldName);

	}
	@Override
	public void verifyCategories(String entityType){
		activationGroup_validations.get().verifyCategories(entityType);
	}

	@Override
	public void dataInAccountingTabOfActivationGroup(DataTable dataTable) {
		activationGroup_validations.get().agAccounting(dataTable);
	}

	@Override
	public void dataUnderTheCarryOverBalancePageInActivationGroup() {
		activationGroup_validations.get().carryOverBalance();
	}
	@Override
	public void selectValueOfPurchaseOrder() {
		c_ActivationGroupPage.get().selectValueOfPurchaseOrder();
	}
	@Override
	public void validateLeaseEndBalances(String standard) {
		c_ActivationGroupPage.get().validateLeaseEndBalances(standard);
	}
	@Override
	public void generateSchedulesAndCancel() {
		activationGroup_validations.get().generateSchedulesAndCancel();

	}
	@Override
	public void verifyJournalsButton() {
		activationGroup_validations.get().verifyJournalsButton();
	}
	@Override
	public void validateUnitStatus(String unitStatus) {
		c_ActivationGroupPage.get().validateUnitStatus(unitStatus);

	}
	@Override
	public void enterIndexationInformation(int row, DataTable dt){
		c_ActivationGroupPage.get().enterIndexationInformation(row, dt);
	}
	@Override
	public void indexationTreatmentType(String treatmentType){
		c_ActivationGroupPage.get().gaapIndexationTreatmentType(treatmentType);
	}

	@Override
	public void changeBaseIndexValue(String value) {
		c_ActivationGroupPage.get().changeBaseIndexValue(value);
	}

	@Override
	public void unitCostObjectValidations() {
		activationGroup_validations.get().unitCostObjectValidations();
	}
}
