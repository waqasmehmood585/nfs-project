package com.nakisa.nlaAutomation.stepServices.ui;

import com.nakisa.nlaAutomation.stepServices.BatchManagement_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;
import io.cucumber.datatable.DataTable;
import org.springframework.stereotype.Component;

@Component
public class UI_BatchManagement_StepService extends DriverFactory implements BatchManagement_StepService {

	@Override
	public void createPostingJobs(DataTable dt) {
		batchManagement_pageObject.get().createPostingJobs(dt);
	}

	@Override
	public void createIndexationPostingJobs(DataTable dt) {
		batchManagement_pageObject.get().createIndexationPostingJobs(dt);
	}

	@Override
	public void createIndexationPostingScheduleJobs(DataTable dt) {
		batchManagement_pageObject.get().createIndexationPostingScheduleJobs(dt);
	}
	@Override
	public void createModificationPostingJobs(DataTable dt) {
		batchManagement_pageObject.get().createModificationJobs(dt);
	}
	@Override
	public void createWorkflowPostingJobs(DataTable dt) {
		batchManagement_pageObject.get().createWorkflowPostingJobs(dt);
	}

	@Override
	public void changeExcelFile(String jobName) {
		batchManagement_pageObject.get().changeExcelFile(jobName);
	}

	@Override
	public void uploadDownloadedExcel(String jobType) {
		batchManagement_pageObject.get().uploadDownloadedExcel(jobType);
	}

	@Override
	public void verifyStatusOfJob(String jobType){
		batchManagement_pageObject.get().verifyStatusOfJob(jobType);
	}

	@Override
	public void cancelTheCreatedJob(){
		batchManagement_pageObject.get().cancelTheCreatedJob();
	}

	@Override
	public void createBatchPostingScheduleJob(DataTable dt){
		batchManagement_pageObject.get().createBatchPostingScheduleJob(dt);
	}
	@Override
	public void enableOrDisableIndexationScheduleJob(){
		batchManagement_pageObject.get().enableOrDisableIndexationScheduleJob();
	}
	@Override
	public void deleteScheduleJobForIndexation(){
		batchManagement_pageObject.get().deleteScheduleJobForIndexation();
	}

	@Override
	public void createInterCompanyTransferJob(DataTable dt) {
		batchManagement_pageObject.get().createInterCompanyTransferJob(dt);
	}

	@Override
	public void revertJob(String jobName) {
		batchManagement_pageObject.get().revertJob(jobName);
	}
	@Override
	public void viewJob(String jobName) {
		batchManagement_pageObject.get().viewJob(jobName);
	}

	@Override
	public void userCancelJob(String cancelOperation, String jobName) {
		batchManagement_pageObject.get().userCancelJob(cancelOperation,jobName);
	}
	@Override
	public void userCopyJob(String jobName) {
		batchManagement_pageObject.get().userCopyJob(jobName);
	}

	@Override
	public void userCheckJobStatus(String jobType,String status) {
		batchManagement_pageObject.get().userCheckJobStatus(jobType,status);
	}
}
