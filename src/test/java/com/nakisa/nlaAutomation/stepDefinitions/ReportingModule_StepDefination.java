package com.nakisa.nlaAutomation.stepDefinitions;

import com.nakisa.nlaAutomation.stepServices.ReportingModule_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import org.springframework.beans.factory.annotation.Autowired;

public class ReportingModule_StepDefination extends DriverFactory {
    @Autowired
    private ReportingModule_StepService reportingModule_stepService;
    @Then("User create Disclosure Report {string} for {string}")
    public void userCreateDisclosureReportJob(String jobType ,String reportLevel, DataTable dataTable) {
        reportingModule_stepService.createDisclosureReportJob(jobType,reportLevel, dataTable);
    }

    @Then("User create Activity Analysis Report {string} for {string}")
    public void userCreateActivityAnalysisReportJob(String jobType,String reportLevel, DataTable dataTable) {
        reportingModule_stepService.createActivityAnalysisReportJob(jobType,reportLevel, dataTable);
    }

    @Then("User Download and Validate {string} standard {string} Report")
    public void userDownloadAndValidateReport(String standard, String sheetName) {
        reportingModule_stepService.userDownloadAndValidateReport(standard, sheetName);
    }

    @Then("User create Periodic Posting Status Report {string} for {string}")
    public void userCreatePeriodicPostingStatusReportJob(String jobType, String reportLevel, DataTable dataTable) {
        reportingModule_stepService.createPeriodicPostingStatusReportJob(jobType,reportLevel, dataTable);
    }
    @Then("User deletes Scheduled Job for {string}")
    public void userDeletesScheduledJob(String reportType) {
        reportingModule_stepService.deleteScheduledJob(reportType);
    }

    @Then("User {string} Scheduled Job for {string}")
    public void userEnableOrDisableScheduledJob(String operation,String reportType) {
        reportingModule_stepService.enableOrDisableScheduledJob(operation,reportType);
    }

    @Then("User cancel job for Created Report")
    public void cancelJobForReport() {
        reportingModule_stepService.cancelJobForReport();
    }

    @Then("Users edit the created scheduled job")
    public void usersEditTheCreatedScheduledJob() {
        reportingModule_stepService.editSchJobNFS();
    }

    @Then("User validate the status of Disclosure Report {string} for {string}")
    public void userValidateTheStatusOfDisclosureReportFor(String jobType, String reportLevel) {
        reportingModule_stepService.statusOfDisclosureReport(jobType, reportLevel);
    }

    @Then("User download and validate {string} for {string} Standard with {string} Classification")
    public void downloadAndValidateFinancialReport(String sheetName, String Standard, String Classification) {
        reportingModule_stepService.downloadAndValidateFinancialReport(sheetName, Standard, Classification);
    }

    @Then("User select filters from Contract Expiration Report")
    public void userSelectFiltersFromContractExpirationReport(DataTable dt) {
        reportingModule_stepService.selectingFilters(dt);
    }

    @Then("User download and validate {string}")
    public void userDownloadAndValidate(String sheetName) {
        reportingModule_stepService.downloadAndValidateDQIReports(sheetName);
    }

    @And("User delete Schedule Job for {string}")
    public void userDeleteScheduleJobFor(String reportName) {
        reportingModule_stepService.deleteAllScheduledJob(reportName);
    }
}

