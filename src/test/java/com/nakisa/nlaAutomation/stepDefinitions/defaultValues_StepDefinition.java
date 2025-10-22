package com.nakisa.nlaAutomation.stepDefinitions;


import com.nakisa.nlaAutomation.stepServices.defaultValues_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

public class defaultValues_StepDefinition extends DriverFactory {
    @Autowired
    private defaultValues_StepService defaultValuesStepService;

    @Then("User click on {string} button for defining configurations with row number {string}")
    public void userClickOnButtonForDefiningConfigurationsWithRowNumber(String operation, String row, DataTable dt) {
        defaultValuesStepService.definingDefaultValueConfigs(operation, row, dt);
    }

    @Then("User validate the Default Value Configs for {string}")
    public void userValidateTheDefaultValueConfigsFor(String entity) {
        defaultValuesStepService.validateDVConfigs(entity);

    }

    @Then("User create a Contract with Default Field Value")
    public void userCreateAContractWithDefaultFieldValue() {
        defaultValuesStepService.createContractWithdfValue();
    }

    @Then("User enters data under LC Definition page for Default Value")
    public void userEntersDataUnderLCDefinitionPageForDefaultValue() {
        defaultValuesStepService.enterDataInLCDefinition();
    }

    @Then("User search the record on Default Value Config Page")
    public void userSearchTheRecordOnDefaultValueConfigPage(DataTable dt) {
        defaultValuesStepService.searchRecordOnDefaultValuePage(dt);
    }

    @Then("Verify if user created the same Config then error message should be shown")
    public void verifyIfUserCreatedTheSameConfigThenErrorMessageShouldBeShown(DataTable dt) {
        defaultValuesStepService.validationForsameDefaultConfigs(dt);
    }

}

