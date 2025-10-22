package com.nakisa.nlaAutomation.stepServices;

import io.cucumber.datatable.DataTable;

public interface SAPSyncBot_StepService {

    void testConnection();
    void creatingSapSyncProfile(String name,DataTable dt);
    void creatingSapSyncPostingJob(DataTable dt);
    void deletingProfile();
    void sapSyncScheduleJob(DataTable dt);
    void changingOfState(String state);
    void deleteSapSyncScheduleJob();
}
