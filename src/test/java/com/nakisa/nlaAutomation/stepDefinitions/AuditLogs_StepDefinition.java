package com.nakisa.nlaAutomation.stepDefinitions;

import com.nakisa.nlaAutomation.stepServices.AuditLogs_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

public class AuditLogs_StepDefinition extends DriverFactory {
    @Autowired
    private AuditLogs_StepService auditLogsStepService;

   @Then("User select filters for Audit Logs")
   public void userselectfiltersforauditlogs(DataTable dt) {
    auditLogsStepService.selectingFiltersForAuditLogs(dt);
}

    @Then("User click on Filter Profiles to create a profile with name {string}")
    public void userClickOnFilterProfilesToCreateAProfileWithName(String name) {
       auditLogsStepService.creatingProfile(name);
    }

    @Then("User click on Filter Profiles to delete a created profile")
    public void userClickOnFilterProfilesToDeleteACreatedProfile() {
       auditLogsStepService.deletingProfile();
    }

    @Then("User click on toggle filters for ascending and descending operations")
    public void userClickOnToggleFiltersForAscendingAndDescendingOperations() {
       auditLogsStepService.toggleFilterOperations();
    }

    @Then("User click on Export Table button to export as {string}")
    public void userClickOnExportTableButtonToExportAs(String exportType) {
       auditLogsStepService.exportTableType(exportType);
    }

    @Then("User schedule the Audit log report with format as {string}")
    public void userScheduleTheAuditLogReportWithFormatAs(String reportFormat, DataTable dt) {
       auditLogsStepService.scheduleAuditLogReport(reportFormat, dt);
    }

    @Then("User export archived audit logs")
    public void userExportArchivedAuditLogs(DataTable dt) {
       auditLogsStepService.exportArchivedAuditLogs(dt);
    }
}

