package com.nakisa.nlaAutomation.stepDefinitions;

import com.nakisa.nlaAutomation.stepServices.Dashboard_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import org.springframework.beans.factory.annotation.Autowired;

public class Dashboard_StepDefinition extends DriverFactory {

    @Autowired
    private Dashboard_StepService dashboard_StepService;

    @Given("User opens the Dashboards from Hamburger Menu")
    public void userOpensTheDashboardsFromHamburgerMenu() {
        dashboard_StepService.dashboard();
    }

    @And("User creates a new Dashboard Page")
    public void userCreatesANewDashboard(DataTable dt) {
        dashboard_StepService.createDashboard(dt);
    }

    @Then("User creates a new {string} Chart")
    public void userCreatesANewChart(String chartName, DataTable dt) {dashboard_StepService.createChart(chartName,dt);}

    @Then("User deletes the Dashboard Page")
    public void userDeletesTheDashboard() {dashboard_StepService.deleteTheDashboard();}
}