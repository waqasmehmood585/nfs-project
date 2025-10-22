package com.nakisa.nlaAutomation.stepDefinitions;

import io.cucumber.datatable.DataTable;
import org.springframework.beans.factory.annotation.Autowired;

import com.nakisa.nlaAutomation.stepServices.SAPPostingBot_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;

import io.cucumber.java.en.Then;

public class SAPPostingBot_StepDefination extends DriverFactory {

	@Autowired
	private SAPPostingBot_StepService sapPostingBot_StepService;

	@Then("Create SAP Posting Profile for System {string} and Company {string} and Accounting Standard {string}")
	public void createSapPostingBotProfile(String system, String company,String accountingStandardValue) {
		sapPostingBot_StepService.createSapPostingBotProfile(system, company, accountingStandardValue);
	}

	@Then("create SAP Posting job with Posting Statuses {string} & batch size {string}")
	public void createSapPostingBotJob(String statuses, String batchSize) {
		sapPostingBot_StepService.createSapPostingBotJob(statuses, batchSize);
	}

	@Then("User Create Consolidated Transaction Report {string} for {string}")
	public void userCreateConsolidatedTransactionReportJobFor(String jobType, String reportLevel, DataTable dataTable) {
		sapPostingBot_StepService.createConsolidatedJob(jobType, reportLevel, dataTable);
	}

	@Then("Download and Validate {string} standard {string} Report")
	public void downloadAndValidateReport(String standard, String sheetName) {
		sapPostingBot_StepService.downloadAndValidateReport(standard, sheetName);
	}

    @Then("User Create GL Balance Report {string} for {string}")
    public void userCreateGLBalanceReportJobFor(String jobType, String reportLevel, DataTable dataTable) {
		sapPostingBot_StepService.createCreateGLJob(jobType,reportLevel, dataTable);
    }

	@Then("User create SAP Posting Scheduled Job for {string}")
	public void userCreateGLBalanceReportJobFor(String reportLevel, DataTable dataTable) {
		sapPostingBot_StepService.createSAPScheduledJob(reportLevel, dataTable);
	}

	@Then("Users edit the created scheduled job for {string}")
	public void usersEditTheCreatedScheduledJobFor(String reportType) {
		sapPostingBot_StepService.editSchJobSAP(reportType);
	}

	@Then("User clicks on Actions button to validate the dialogue sections")
	public void user_clicks_on_actions_button_to_validate_the_dialogue_sections() {
		sapPostingBot_StepService.validatingActionButton();
    }


    @Then("User creates journal voucher definition tab")
    public void userCreatesAJournalsVoucherDefinitionTab(DataTable dt) {
		sapPostingBot_StepService.createJournalVoucherDefinitionTab(dt);
    }

	@Then("User creates journal voucher entries tab")
	public void userCreatesJournalVoucherEntriesTab(DataTable dt) {
		sapPostingBot_StepService.createJournalVoucherEntriesTab(dt);
	}

	@Then("User creates journal voucher additional information tab")
	public void userCreatesJournalVoucherAdditionalInfoTab(DataTable dt) {
		sapPostingBot_StepService.createJournalVoucherAdditionalInfoTab(dt);
	}

    @Then("User Send To Approval Journal Voucher")
    public void userSendToApprovalJV() {
		sapPostingBot_StepService.jvSendToApproval();
    }

	@Then("User Rework the Journal Voucher")
	public void userReworkTheJV() {
		sapPostingBot_StepService.jvRework();
	}
	@Then("User Approve the Journal Voucher")
	public void userApproveTheJV() {
		sapPostingBot_StepService.jvApprove();
	}

	@Then("User Save the Journal Voucher")
	public void userSaveTheJV() {
		sapPostingBot_StepService.jvSave();

	}

	@Then("User Close the Journal Voucher")
	public void userCloseTheJV() {
		sapPostingBot_StepService.jvClose();
	}
}
