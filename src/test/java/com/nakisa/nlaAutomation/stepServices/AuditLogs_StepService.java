package com.nakisa.nlaAutomation.stepServices;

import io.cucumber.datatable.DataTable;

public interface AuditLogs_StepService {
     void selectingFiltersForAuditLogs(DataTable dt);
     void creatingProfile(String name);
     void deletingProfile();
     void toggleFilterOperations();
     void exportTableType(String exportType);
     void scheduleAuditLogReport(String reportFormat, DataTable dt);
     void exportArchivedAuditLogs(DataTable dt);
}
