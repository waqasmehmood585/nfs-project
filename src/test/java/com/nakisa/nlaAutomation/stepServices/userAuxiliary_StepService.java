package com.nakisa.nlaAutomation.stepServices;

import io.cucumber.datatable.DataTable;

public interface userAuxiliary_StepService {

    void createBatchProfile(String jobType, DataTable dt);

    void editProfile(String jobName,DataTable dt);

    void deleteProfile(String name);

    void searchTheRecordsInProfile(String reportType,DataTable dt);
}
