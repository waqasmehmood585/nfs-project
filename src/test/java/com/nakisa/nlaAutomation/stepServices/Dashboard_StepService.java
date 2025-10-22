package com.nakisa.nlaAutomation.stepServices;

import io.cucumber.datatable.DataTable;

public interface Dashboard_StepService {
    void dashboard();
    void createDashboard(DataTable dt);
    void createChart(String chartName,DataTable dt);
    void deleteTheDashboard();
}
