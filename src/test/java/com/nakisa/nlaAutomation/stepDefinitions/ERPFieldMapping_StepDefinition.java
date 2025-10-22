package com.nakisa.nlaAutomation.stepDefinitions;

import com.nakisa.nlaAutomation.stepServices.ERPFieldMapping_StepService;
import io.cucumber.java.en.Then;
import com.nakisa.nlaAutomation.utils.DriverFactory;
import io.cucumber.datatable.DataTable;
import org.springframework.beans.factory.annotation.Autowired;

public class ERPFieldMapping_StepDefinition extends DriverFactory {
    @Autowired
    private ERPFieldMapping_StepService ERPFieldMappingStepService;
    //private defaultValues_StepService defaultValuesStepService;

    @Then("User tries to create an ERP Field Mapping entry for {string}")
    public void userTriesToCreateAnERPFieldMappingEntry(String principalPosition, DataTable dt) {
        ERPFieldMappingStepService.creatingERPMapping(principalPosition, dt);
    }

    @Then("User tries to delete the added ERP Field Mapping entry")
    public void userTriesToDeleteTheAddedERPFieldMappingEntry() {
        ERPFieldMappingStepService.deletingERPMapping();
    }

    @Then("User validates filters")
    public void userValidatesFilters() {
        ERPFieldMappingStepService.userValidatesFilters();
    }
}
