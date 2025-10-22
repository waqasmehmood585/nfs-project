package com.nakisa.nlaAutomation.stepServices;

import io.cucumber.datatable.DataTable;

public interface ReportingModule_StepService {

	void createDisclosureReportJob(String jobType, String reportLevel, DataTable dataTable);

	void createActivityAnalysisReportJob(String jobType, String reportLevel, DataTable dataTable);

	void userDownloadAndValidateReport(String standard, String sheetName);
	void createPeriodicPostingStatusReportJob(String jobType, String reportLevel, DataTable dataTable);
	void deleteScheduledJob(String reportType);
	void enableOrDisableScheduledJob(String operation,String reportType);
	void cancelJobForReport();
	void  editSchJobNFS();
	void statusOfDisclosureReport(String jobType, String reportLevel);
	void downloadAndValidateFinancialReport(String sheetName, String Standard, String Classification);
	void selectingFilters(DataTable dt);

	void downloadAndValidateDQIReports(String sheetName);

	void deleteAllScheduledJob(String reportName);
}
