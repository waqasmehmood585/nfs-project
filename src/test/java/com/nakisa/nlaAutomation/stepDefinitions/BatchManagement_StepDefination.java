package com.nakisa.nlaAutomation.stepDefinitions;

import com.nakisa.nlaAutomation.stepServices.BatchManagement_StepService;
import com.nakisa.nlaAutomation.stepServices.Common_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class BatchManagement_StepDefination extends DriverFactory {
    @Autowired
    private BatchManagement_StepService batchManagement_stepService;

    @Then("User tries to create Batch Posting Job")
    public void userTriesToCreateBatchPostingJob(DataTable dt) {
        batchManagement_stepService.createPostingJobs( dt);
    }

    @Then("User tries to create Indexation Posting Job")
    public void userTriesToCreateIndexationPostingJob(DataTable dt) {
        batchManagement_stepService.createIndexationPostingJobs(dt);
    }

    @Then("User tries to create Indexation Posting Schedule Job")
    public void userTriesToCreateIndexationPostingScheduleJob(DataTable dt) {
        batchManagement_stepService.createIndexationPostingScheduleJobs(dt);
    }

    @Then("User tries to create Modification Posting Job")
    public void userTriesToCreateModificationPostingJob(DataTable dt) {
        batchManagement_stepService.createModificationPostingJobs(dt);
    }

    @Then("User tries to create Workflow Posting Job")
    public void userTriesToCreateWorkflowPostingJob(DataTable dt) {
        batchManagement_stepService.createWorkflowPostingJobs(dt);
    }

    @Then("User Update the downloaded excel file of {string}")
    public void changeExcelFile(String jobName) {
        batchManagement_stepService.changeExcelFile(jobName);
    }

    @Then("User Upload the downloaded excel file of {string}")
    public void userUploadDownloadedExcel(String jobType) {
        batchManagement_stepService.uploadDownloadedExcel(jobType);
    }
    @Then("User verify the Status of {string}")
    public void userVerifyTheStatusOfJob(String jobType) {

        batchManagement_stepService.verifyStatusOfJob(jobType);
    }

    @Then("User cancel the created job")
    public void userCancelTheCreatedJob() {
        batchManagement_stepService.cancelTheCreatedJob();
    }

    @Then("User tries to create Batch Posting Schedule Job")
    public void userTriesToCreateBatchPostingScheduleJob(DataTable dt) {
        batchManagement_stepService.createBatchPostingScheduleJob(dt);
    }

    @Then("User Enable or Disable Scheduled Job for Mass Indexation")
    public void enableOrDisableIndexationScheduleJob() {
        batchManagement_stepService.enableOrDisableIndexationScheduleJob();
    }

    @Then("User deletes Scheduled Job for Mass Indexation")
    public void DeletesScheduledJobForMassIndexation() {
        batchManagement_stepService.deleteScheduleJobForIndexation();
    }

    @Then("User tries to create Inter Company Transfer Job")
    public void userTriesToCreateInterCompanyTransferJob(DataTable dt) {
        batchManagement_stepService.createInterCompanyTransferJob(dt);
    }

  @Then("User revert the {string} Job")
  public void userRevertTheJob(String jobName) {
      batchManagement_stepService.revertJob(jobName);
  }

    @Then("User view the {string} Job")
    public void userViewTheJob(String jobName) {
        batchManagement_stepService.viewJob(jobName);
    }

    @Then("User cancel {string} of {string}")
    public void userCancelJob(String cancelOperation, String jobName) {
        batchManagement_stepService.userCancelJob(cancelOperation,jobName);
    }

    @Then("User copy the Job of {string}")
    public void userCopyTheJobOf(String jobName) {
        batchManagement_stepService.userCopyJob(jobName);
    }

    @Then("the {string} job should be completed with status {string}")
    public void theJobShouldBeCompletedWithStatus(String jobType, String status) {
        batchManagement_stepService.userCheckJobStatus(jobType,status);
    }
}

