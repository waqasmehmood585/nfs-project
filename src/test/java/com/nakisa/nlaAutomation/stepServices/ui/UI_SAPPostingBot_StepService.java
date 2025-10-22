package com.nakisa.nlaAutomation.stepServices.ui;

import io.cucumber.datatable.DataTable;
import org.springframework.stereotype.Component;

import com.nakisa.nlaAutomation.stepServices.SAPPostingBot_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;

@Component
public class UI_SAPPostingBot_StepService extends DriverFactory implements SAPPostingBot_StepService{

	@Override
	public void createSapPostingBotProfile(String system, String Company,String accountingStandardValue) {
		sapPostingBot_PageObject.get().createSAPPostingProfile(system, Company, accountingStandardValue);
	}

	@Override
	public void createSapPostingBotJob(String statuses, String batchSize) {
		sapPostingBot_PageObject.get().createSAPPostingJob(statuses, batchSize);
	}

	@Override
	public void createConsolidatedJob(String jobType,String reportLevel,  DataTable dataTable) {
		sapPostingBot_PageObject.get().createConsolidatedJob(jobType, reportLevel, dataTable);
	}

	@Override
	public void downloadAndValidateReport(String standard, String sheetName) {
		sapPostingBot_PageObject.get().downloadAndValidateReport(standard, sheetName);
	}

	@Override
	public void createCreateGLJob(String jobType,String reportLevel,DataTable dataTable) {
		sapPostingBot_PageObject.get().createGLJob(jobType,reportLevel, dataTable);
	}

	@Override
	public void createSAPScheduledJob(String reportLevel, DataTable dataTable) {
		sapPostingBot_PageObject.get().createSAPScheduledJob(reportLevel, dataTable);
	}
	@Override
	public void editSchJobSAP(String reportType) {
		sapPostingBot_PageObject.get().editSchJobSAP(reportType);
	}
	@Override
	public void validatingActionButton(){
		sapPostingBot_PageObject.get().validatingActionButton();
	}
	@Override
	public void createJournalVoucherDefinitionTab(DataTable dt){
		sapPostingBot_PageObject.get().createJournalVoucherDefinitionTab(dt);
	}
	@Override
	public void createJournalVoucherEntriesTab(DataTable dt){
		sapPostingBot_PageObject.get().createJournalVoucherEntriesTab(dt);
	}
	@Override
	public void createJournalVoucherAdditionalInfoTab(DataTable dt){
		sapPostingBot_PageObject.get().createJournalVoucherAdditionalInfoTab(dt);
	}
	@Override
	public void jvSendToApproval(){
		sapPostingBot_PageObject.get().jvSendToApproval();
	}
	@Override
	public void jvRework(){
		sapPostingBot_PageObject.get().jvRework();
	}
	@Override
	public void jvApprove(){
		sapPostingBot_PageObject.get().jvApprove();
	}
	@Override
	public void jvSave(){
		sapPostingBot_PageObject.get().jvSave();
	}
	@Override
	public void jvClose(){
		sapPostingBot_PageObject.get().jvClose();
	}
}
