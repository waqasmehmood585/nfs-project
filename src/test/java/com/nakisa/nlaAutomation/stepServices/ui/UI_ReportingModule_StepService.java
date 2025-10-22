package com.nakisa.nlaAutomation.stepServices.ui;

import io.cucumber.datatable.DataTable;
import org.springframework.stereotype.Component;

import com.nakisa.nlaAutomation.stepServices.ReportingModule_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;

@Component
public class UI_ReportingModule_StepService extends DriverFactory implements ReportingModule_StepService {

	@Override
	public void createDisclosureReportJob(String jobType, String reportLevel, DataTable dataTable) {
		reportingModule_pageObject.get().createDisclosureReportJob(jobType, reportLevel, dataTable);
	}

	@Override
	public void createActivityAnalysisReportJob(String jobType, String reportLevel, DataTable dataTable) {
		reportingModule_pageObject.get().createActivityAnalysisReportJob(jobType, reportLevel, dataTable);
	}

	@Override
	public void userDownloadAndValidateReport(String standard, String sheetName) {
		reportingModule_pageObject.get().downloadAndValidateReport(standard, sheetName);
	}

	@Override
	public void createPeriodicPostingStatusReportJob(String jobType, String reportLevel, DataTable dataTable) {
		reportingModule_pageObject.get().createPeriodicPostingStatusReportJob(jobType, reportLevel,dataTable);
	}

	@Override
	public void deleteScheduledJob(String reportType) {
		reportingModule_pageObject.get().deleteScheduledJob(reportType);
	}

	@Override
	public void enableOrDisableScheduledJob(String operation,String reportType) {
		reportingModule_pageObject.get().enableOrDisableScheduledJob(operation,reportType);
	}

	@Override
	public void cancelJobForReport() {
		reportingModule_pageObject.get().cancelJobForReport();
	}

	@Override
	public void editSchJobNFS() {
		reportingModule_pageObject.get().editSchJobNFS();
	}
	@Override
	public void statusOfDisclosureReport(String jobType,String reportLevel){
		reportingModule_pageObject.get().statusOfDisclosureReport(jobType, reportLevel);
	}
	@Override
	public void downloadAndValidateFinancialReport(String sheetName, String Standard, String Classification){
		reportingModule_pageObject.get().downloadAndValidateFinancialReport(sheetName, Standard, Classification);
	}

	@Override
	public void selectingFilters(DataTable dt){
		reportingModule_pageObject.get().selectingFiltersForCEReport(dt);
	}
	@Override
	public void downloadAndValidateDQIReports(String sheetName){
		reportingModule_pageObject.get().downloadAndValidateDQIReports(sheetName);
	}

	@Override
	public void deleteAllScheduledJob(String reportName) {
		reportingModule_pageObject.get().deleteAllScheduledJob(reportName);
	}
}
