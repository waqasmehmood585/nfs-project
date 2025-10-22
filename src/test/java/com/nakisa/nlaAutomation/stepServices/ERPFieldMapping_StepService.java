package com.nakisa.nlaAutomation.stepServices;

import io.cucumber.datatable.DataTable;

public interface ERPFieldMapping_StepService {

    void creatingERPMapping(String principalPosition, DataTable dt);

    void deletingERPMapping();

    void userValidatesFilters();
}
