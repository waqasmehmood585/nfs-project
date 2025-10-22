package com.nakisa.nlaAutomation.stepServices;

import io.cucumber.datatable.DataTable;

public interface SAPPostingBot_StepService {

	void createSapPostingBotProfile(String system, String Company,String accountingStandardValue);
	
	void createSapPostingBotJob(String statuses,String batchSize);


    void createConsolidatedJob(String jobType,String reportLevel, DataTable dataTable);

    void downloadAndValidateReport(String standard,String sheetName);

    void createCreateGLJob(String jobType,String reportLevel, DataTable dataTable);
    void createSAPScheduledJob(String reportLevel, DataTable dataTable);

    void editSchJobSAP(String reportType);

    void validatingActionButton();

    void createJournalVoucherDefinitionTab(DataTable dt);

    void createJournalVoucherEntriesTab(DataTable dt);

    void createJournalVoucherAdditionalInfoTab(DataTable dt);

    void jvSendToApproval();

    void jvRework();
    void jvApprove();

    void jvSave();

    void jvClose();
}
