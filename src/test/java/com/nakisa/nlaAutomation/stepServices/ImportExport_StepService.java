package com.nakisa.nlaAutomation.stepServices;

import io.cucumber.datatable.DataTable;

public interface ImportExport_StepService {

	void createExportJob(String entityType, String level);
	void landingPage(String entityType);
	void exportRecord(String entityType);

    void userPerformedMassImportWithExcelFile(String importType, String importFile, DataTable dt);

    void userOpenReportToGetRecordID(String importType);

    void userValidatedTheRecordsImport(String importLevel);

    void importFromTermsAndConditionsTab(String fileName);

    void deleteFile();

    void checkColumnExist(DataTable dt);
    void downloadTemplateFile(String entityType, DataTable dt);
    void userPerformedMassImportWithWrongExcelFile(String importType, String importFile, DataTable dt);
    void massWorkflowJobCompletion();
    void exportLCTerm();
    void validateAccountingImport();
    void downloadAndValidateImportReport(String report);
    void verifyMessage(String importType, String report,String message);
    void updateImportFile(String importType, String massImportExcelUpdateFile);

}
