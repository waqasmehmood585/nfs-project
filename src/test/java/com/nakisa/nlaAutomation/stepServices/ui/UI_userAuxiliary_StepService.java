package com.nakisa.nlaAutomation.stepServices.ui;

import com.nakisa.nlaAutomation.stepServices.userAuxiliary_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;
import io.cucumber.datatable.DataTable;
import org.springframework.stereotype.Component;

@Component
public class UI_userAuxiliary_StepService extends DriverFactory implements userAuxiliary_StepService {

    @Override
    public void createBatchProfile(String jobType, DataTable dt){
        userAuxiliary_PageObject.get().createProfile(jobType,dt);
    }

    @Override
    public void editProfile(String jobName,DataTable dt) {
        userAuxiliary_PageObject.get().editProfile(jobName,dt);
    }

    @Override
    public void deleteProfile(String name) {
        userAuxiliary_PageObject.get().deleteProfile(name);
    }

    @Override
    public void searchTheRecordsInProfile(String reportType,DataTable dt){
        userAuxiliary_PageObject.get().searchTheRecordsInProfile(reportType, dt);
    }
}
