package com.nakisa.nlaAutomation.stepServices;

import io.cucumber.datatable.DataTable;
import org.elasticsearch.common.recycler.Recycler;

public interface BatchManagement_StepService {

    void createPostingJobs(DataTable dt);

    void createIndexationPostingJobs(DataTable dt);

    void createIndexationPostingScheduleJobs(DataTable dt);
    
    void createModificationPostingJobs(DataTable dt);
    
    void createWorkflowPostingJobs(DataTable dt);

	void changeExcelFile(String jobName);

	void uploadDownloadedExcel(String jobType);

    void verifyStatusOfJob(String jobType);
    void cancelTheCreatedJob();
    void createBatchPostingScheduleJob(DataTable dt);

    void enableOrDisableIndexationScheduleJob();

    void deleteScheduleJobForIndexation();

    void createInterCompanyTransferJob(DataTable dt);

    void revertJob(String jobName);

    void viewJob(String jobName);

    void userCancelJob(String cancelOperation, String jobName);

    void userCopyJob(String jobName);

    void userCheckJobStatus(String jobType, String status);
}
