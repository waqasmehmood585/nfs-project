package com.nakisa.nlaAutomation.stepServices;

import io.cucumber.datatable.DataTable;

public interface defaultValues_StepService {

    void definingDefaultValueConfigs(String operation, String row, DataTable dt);

    void validateDVConfigs(String entity);

    void createContractWithdfValue();

    void enterDataInLCDefinition();

    void searchRecordOnDefaultValuePage(DataTable dt);
    void validationForsameDefaultConfigs(DataTable dt);
}
