package com.nakisa.nlaAutomation.stepDefinitions;

import com.nakisa.nlaAutomation.stepServices.userAuxiliary_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

public class UserAuxiliary_StepDefinition extends DriverFactory {

    @Autowired
    private userAuxiliary_StepService userAuxiliary_stepService;

    @Then("Users create Profile for {string}")
    public void createBatchProfile(String jobType, DataTable dt) {
        userAuxiliary_stepService.createBatchProfile(jobType, dt);
    }

    @Then("Users edit the profile for {string}")
    public void usersEditTheProfile(String jobName, DataTable dt) {
        userAuxiliary_stepService.editProfile(jobName, dt);
    }

    @Then("User deletes the {string} Profile")
    public void userDeletesTheProfile(String name) {
        userAuxiliary_stepService.deleteProfile(name);
    }

    @Then("User search the records in the {string} Profile")
    public void userSearchTheRecordsInProfile(String reportType, DataTable dt) {
        userAuxiliary_stepService.searchTheRecordsInProfile(reportType, dt);
    }

}


