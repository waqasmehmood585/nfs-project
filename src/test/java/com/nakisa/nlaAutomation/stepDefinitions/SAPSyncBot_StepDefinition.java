package com.nakisa.nlaAutomation.stepDefinitions;

import com.nakisa.nlaAutomation.stepServices.SAPPostingBot_StepService;
import com.nakisa.nlaAutomation.stepServices.SAPSyncBot_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

public class SAPSyncBot_StepDefinition extends DriverFactory {
    @Autowired
    private SAPSyncBot_StepService sapSyncBotStepService;

    @Then("user test the connection of ERP System")
    public void testERPConnection() {
        sapSyncBotStepService.testConnection();
    }

    @Then("Verify user is able to create sap sync profile with settings and name {string}")
    public void verifyUserIsAbleToCreateSapSyncProfileWithSettingsAndName(String name, DataTable dt) {
        sapSyncBotStepService.creatingSapSyncProfile(name,dt);
    }

    @Then("User creates a SAP Sync Job with details")
    public void userCreatesASAPSyncJobWithDetails(DataTable dt) {
        sapSyncBotStepService.creatingSapSyncPostingJob(dt);
    }

    @Then("User delete the Sap sync profile")
    public void userDeleteTheSapSyncProfile() {
        sapSyncBotStepService.deletingProfile();
    }

    @Then("User creates a SAP Sync schedule Job with details")
    public void userCreatesASAPSyncScheduleJobWithDetails(DataTable dt) {
        sapSyncBotStepService.sapSyncScheduleJob(dt);
    }

    @Then("User click on {string} button to disable the scheduled job")
    public void userClickOnButtonToDisableTheScheduledJob(String state) {
        sapSyncBotStepService.changingOfState(state);
    }

    @Then("User delete the Sap sync schedule job")
    public void userDeleteTheSapSyncScheduleJob() {
        sapSyncBotStepService.deleteSapSyncScheduleJob();
    }
}

