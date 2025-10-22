package com.nakisa.nlaAutomation.stepServices.ui;

import com.nakisa.nlaAutomation.pageObjects.ERPFieldMapping_PageObject;
import com.nakisa.nlaAutomation.stepServices.ERPFieldMapping_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;
import io.cucumber.datatable.DataTable;
import org.springframework.stereotype.Component;

@Component
public class UI_ERPFieldMapping_StepService extends DriverFactory implements ERPFieldMapping_StepService {
    //defaultValues_PageObject defaultValuesPageObject;
    ERPFieldMapping_PageObject ERPFieldMappingPageObject;

    public void creatingERPMapping(String principalPosition, DataTable dt) {
        ERPFieldMapping_PageObject.get().creatingERPMapping(principalPosition, dt);
    }

    public void deletingERPMapping() {
        ERPFieldMapping_PageObject.get().deletingERPMapping();
    }

    public void userValidatesFilters() {
        ERPFieldMapping_PageObject.get().userValidatesFilters();
    }

}
