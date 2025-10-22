package com.nakisa.nlaAutomation.stepServices.ui;

import com.nakisa.nlaAutomation.stepServices.Dashboard_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;
import io.cucumber.datatable.DataTable;
import org.springframework.stereotype.Component;

@Component
public class UI_Dashboard_StepService extends DriverFactory implements Dashboard_StepService {

    @Override
    public void dashboard(){
        dashboard_pageObject.get().dashboard();
    }

    @Override
    public void createDashboard(DataTable dt){
        dashboard_pageObject.get().createDashboard(dt);
    }

    @Override
    public void createChart(String chartName,DataTable dt){
        dashboard_pageObject.get().createChart(chartName,dt);
    }

    @Override
    public void deleteTheDashboard(){dashboard_pageObject.get().deleteTheDashboard();}
}
