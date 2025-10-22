package com.nakisa.nlaAutomation.stepServices.ui;

import com.nakisa.nlaAutomation.stepServices.AuditLogs_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;
import io.cucumber.datatable.DataTable;
import org.springframework.stereotype.Component;

@Component
public class UI_AuditLogs_StepService extends DriverFactory implements AuditLogs_StepService {
    @Override
public void selectingFiltersForAuditLogs (DataTable dt ){
        auditLogs_PageObject.get().selectingFiltersForAuditLogs(dt);
    }
    public void creatingProfile(String name){
        auditLogs_PageObject.get().creatingProfile(name);
    }
    public void deletingProfile(){
        auditLogs_PageObject.get().deletingProfile();
    }
    public void toggleFilterOperations(){
        auditLogs_PageObject.get().toggleFilterOperations();
    }
    public void exportTableType(String exportType){
        auditLogs_PageObject.get().exportTableType(exportType);
    }
    public void scheduleAuditLogReport(String reportFormat, DataTable dt){
        auditLogs_PageObject.get().scheduleAuditLogReport(reportFormat, dt);
    }
    public void exportArchivedAuditLogs(DataTable dt){
        auditLogs_PageObject.get().exportArchivedAuditLogs(dt);
    }
}
