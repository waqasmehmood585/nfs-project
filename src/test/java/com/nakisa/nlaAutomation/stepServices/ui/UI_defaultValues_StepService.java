package com.nakisa.nlaAutomation.stepServices.ui;

import com.nakisa.nlaAutomation.pageObjects.defaultValues_PageObject;
import com.nakisa.nlaAutomation.stepServices.defaultValues_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;
import io.cucumber.datatable.DataTable;
import org.springframework.stereotype.Component;

@Component
public class UI_defaultValues_StepService extends DriverFactory implements defaultValues_StepService {
    defaultValues_PageObject defaultValuesPageObject;

    public void definingDefaultValueConfigs(String operation, String row, DataTable dt) {
        defaultConfigValues_PageObject.get().definingDefaultValueConfigs(operation, row, dt);
    }

    public void validateDVConfigs(String entity) {
        defaultConfigValues_PageObject.get().validateDVConfigs(entity);
    }

    public void createContractWithdfValue() {
        defaultConfigValues_PageObject.get().createContractWithdfValue();

    }

    public void enterDataInLCDefinition() {
        defaultConfigValues_PageObject.get().enterDataInLCDefinition();

    }
    public void searchRecordOnDefaultValuePage(DataTable dt){
        defaultConfigValues_PageObject.get().searchRecordOnDefaultValuePage(dt);
    }
    public void validationForsameDefaultConfigs(DataTable dt){
        defaultConfigValues_PageObject.get().validationForsameDefaultConfigs(dt);
    }

}
