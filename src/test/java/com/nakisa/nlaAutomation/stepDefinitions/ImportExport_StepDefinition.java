package com.nakisa.nlaAutomation.stepDefinitions;

import com.nakisa.nlaAutomation.stepServices.ImportExport_StepService;
import com.nakisa.nlaAutomation.stepServices.MasterAgreement_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

public class ImportExport_StepDefinition extends DriverFactory {
    @Autowired
    private ImportExport_StepService importExport_stepService;
    private MasterAgreement_StepService masterAgreement_StepService;

    @Then("User create {string} Export job for {string}")
    public void userCreateExportJob(String entityType, String level) {
        importExport_stepService.createExportJob(entityType, level);

    }

    @Given("User is on {string} Landing page")
    public void userOnLandingPage(String entityType){
    importExport_stepService.landingPage(entityType);

    }

    @Given("User Selects the {string} records and Export it")
    public void exportRecord(String entityType){
        importExport_stepService.exportRecord(entityType);

    }

    @Given("Reading base test case inputs {string}")
    public void readingBaseTestCaseInputs(String testCaseNumber) {
        masterAgreement_PageObject.get().readBaseTCInputs(testCaseNumber);
    }

    @Then("user performed Mass Import for {string} with Excel File {string}")
    public void userPerformedMassImportWithExcelFile(String importType, String importFile, DataTable dt) {
        importExport_stepService.userPerformedMassImportWithExcelFile(importType,importFile,dt);
    }

    @And("user open report of {string} to get Record ID")
    public void userOpenReportToGetRecordID(String importType) {
        importExport_stepService.userOpenReportToGetRecordID(importType);
    }

    @And("user validated the Records Import on {string}")
    public void userValidatedTheRecordsImport(String importLevel) {
        importExport_stepService.userValidatedTheRecordsImport(importLevel);
    }

    @Then("User Import {string} from Terms and Conditions tab of Lease Component")
    public void userImportFromTermsAndConditionsTabOfLeaseComponent(String fileName) {
        importExport_stepService.importFromTermsAndConditionsTab(fileName);
    }

    @Then("Delete the File")
    public void deleteTheFile() {
        importExport_stepService.deleteFile();
    }

    @Then("User checks the File for Column Exist")
    public void userChecksTheFileForColumnExist(DataTable dt) {
        importExport_stepService.checkColumnExist(dt);
    }

    @Then("User download the Import Template file of {string}")
    public void userDownloadTheTemplateFile(String entityType, DataTable dt) {
        importExport_stepService.downloadTemplateFile(entityType, dt);
    }
    @Then("user verify Error message of Mass Import for {string} with Wrong Excel File {string}")
    public void userPerformedMassImportForWithWrongExcelFile(String importType, String importFile, DataTable dt) {
        importExport_stepService.userPerformedMassImportWithWrongExcelFile(importType,importFile,dt);
    }

    @And("verify that Mass Workflow Job is Completed")
    public void verifyThatMassWorkflowJobIsCompleted() {
        importExport_stepService.massWorkflowJobCompletion();
    }

    @Then("User export the Terms")
    public void userExportTheTerms() {
        importExport_stepService.exportLCTerm();
    }

    @Then("User enters the Accounting tab to validate the Import")
    public void userEntersTheAccountingTabToValidateTheImport() {
        importExport_stepService.validateAccountingImport();
    }

    @Then("user download the {string}")
    public void userDownloadTheImportReport(String report) {
        importExport_stepService.downloadAndValidateImportReport(report);
    }

    @And("user open report of {string} to Check the Report {string} as {string}")
    public void userOpenReportOfToCheckTheErrorMessage(String importType,String report,String message) {
        importExport_stepService.verifyMessage(importType,report, message);
    }

    @Then("user update {string} ID in Excel File {string}")
    public void userUpdateIDInExcelFile(String importType, String massImportExcelUpdateFile) {
        importExport_stepService.updateImportFile(importType,massImportExcelUpdateFile);
    }
}

