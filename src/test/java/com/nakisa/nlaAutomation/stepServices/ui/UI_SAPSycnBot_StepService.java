package com.nakisa.nlaAutomation.stepServices.ui;

import com.nakisa.nlaAutomation.stepServices.SAPPostingBot_StepService;
import com.nakisa.nlaAutomation.stepServices.SAPSyncBot_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;
import io.cucumber.datatable.DataTable;
import org.springframework.stereotype.Component;

@Component
public class UI_SAPSycnBot_StepService extends DriverFactory implements SAPSyncBot_StepService {
    @Override
    public void testConnection(){
        sapSyncBot_PageObject.get().testConnection();
    }
    @Override
    public void creatingSapSyncProfile(String name, DataTable dt){
        sapSyncBot_PageObject.get().creatingSapSyncProfile(name, dt);
    }
    @Override
    public void creatingSapSyncPostingJob(DataTable dt){
        sapSyncBot_PageObject.get().creatingSapSyncPostingJob(dt);
    }
    @Override
    public void deletingProfile() {
        sapSyncBot_PageObject.get().deletingProfile();
    }
    @Override
    public void sapSyncScheduleJob(DataTable dt){
        sapSyncBot_PageObject.get().sapSyncScheduleJob(dt);
    }
    @Override
    public void  changingOfState(String state){
        sapSyncBot_PageObject.get().changingOfState(state);
    }
    @Override
    public   void deleteSapSyncScheduleJob(){
        sapSyncBot_PageObject.get().deleteSapSyncScheduleJob();
    }
}
