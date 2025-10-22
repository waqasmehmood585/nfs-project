package com.nakisa.nlaAutomation.stepServices.ui;

import com.nakisa.nlaAutomation.stepServices.ImportExport_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;
import io.cucumber.datatable.DataTable;
import org.springframework.stereotype.Component;

@Component
public class UI_ImportExport_StepService extends DriverFactory implements ImportExport_StepService {

	@Override
	public void createExportJob(String entityType, String level) {
		importExport_pageObject.get().createExportJob(entityType, level);
	}

	@Override
	public void landingPage(String entityType) {
		importExport_pageObject.get().landingPage(entityType);
	}

	@Override
	public void exportRecord(String entityType) {
		importExport_pageObject.get().exportRecord(entityType);
	}

	@Override
	public void userPerformedMassImportWithExcelFile(String importType, String importFile, DataTable dt) {
		importExport_pageObject.get().excelMassImport(importType,importFile, dt);
	}

	@Override
	public void userOpenReportToGetRecordID(String importType) {
		importExport_pageObject.get().openReportForID(importType);
	}

	@Override
	public void userValidatedTheRecordsImport(String importLevel) {
		importExport_pageObject.get().userValidatedTheRecordsImport(importLevel);
	}

	@Override
	public void importFromTermsAndConditionsTab(String fileName) {
		importExport_pageObject.get().importFromTermsAndConditionsTab(fileName);
	}

	@Override
	public void deleteFile() {
		importExport_pageObject.get().deleteFile();
	}

	@Override
	public void checkColumnExist(DataTable dt) {
		importExport_pageObject.get().CheckColumnsInExcel(dt);
	}

	@Override
	public void downloadTemplateFile(String entityType, DataTable dt) {
		importExport_pageObject.get().downloadTemplateFile(entityType, dt);
	}

	@Override
	public void userPerformedMassImportWithWrongExcelFile(String importType, String importFile, DataTable dt) {
		importExport_pageObject.get().excelMassImportWithWrongFile(importType,importFile, dt);
	}

	@Override
	public void massWorkflowJobCompletion() {
		batchManagement_pageObject.get().massWorkflowJobCompletion();
	}
	@Override
	public void exportLCTerm() {
		importExport_pageObject.get().exportLCTerm();
	}

	@Override
	public void validateAccountingImport() {
		importExport_pageObject.get().validateAccountingTabImport();
	}

	@Override
	public void downloadAndValidateImportReport(String report) {
		importExport_pageObject.get().downloadingFile(report);
	}

	@Override
	public void verifyMessage(String importType,String report, String message) {
		importExport_pageObject.get().verifyMessage(importType,report,message);
	}

	@Override
	public void updateImportFile(String importType, String massImportExcelUpdateFile) {
		importExport_pageObject.get().updateImportFile(importType,massImportExcelUpdateFile);
	}

}
