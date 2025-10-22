package com.nakisa.nlaAutomation.pageObjects;

import com.nakisa.nlaAutomation.stepDefinitions.MasterHooks;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
public class Dashboard_PageObject extends Common_BasePage_PageObject {

    public @FindBy(css = ".desktop .q-header Button[aria-label='Menu']") WebElement hamburgerMenu;
    public @FindBy(css = ".q-page .q-btn#dashboardsPageCreatePageBtn") WebElement createDashboardButton;
    public @FindBy(css = ".q-dialog .q-card #pageEditorPageTitleInputInput") WebElement dashboardTitle;
    public @FindBy(css = ".q-dialog .q-card #pageEditorPageDescriptionAddBtn") WebElement dashboardDescriptionAddButton;
    public @FindBy(css = ".q-dialog .q-card #pageEditorPageDescriptionInput") WebElement dashboardDescriptionInputField;
    public @FindBy(css = ".q-dialog .q-card #pageEditorPageDescriptionRemoveBtn") WebElement dashboardDescriptionRemoveButton;
    public @FindBy(css = ".q-dialog .q-card #pageEditorPageTypeSelect") WebElement dashboardType;
    public @FindBy(css = ".q-dialog .q-card #pageEditorListToggle") WebElement dashboardEnableDisableList;
    public @FindBy(css = ".q-dialog .q-card #pageEditorDatasetSelectInput") WebElement dashboardData;
    public @FindBy(css = ".q-dialog .q-card #pageEditorCreateBtn") WebElement dashboardCreateButton;
    public @FindBy(css = ".q-page .page-container #pageNoContentAddChartDiv") WebElement createChartPageButton;
    public @FindBy(css = ".q-page .q-toolbar #pageAddNewChartBtn") WebElement addChartButton;
    public @FindBy(css = ".q-menu .q-list #pageChartOptionsItem") WebElement newChartMenuButton;
    public @FindBy(css = ".q-page .page-container #pageNoContentAddChartDiv") WebElement chartType;
    public @FindBy(css = ".q-page .chart-types-container #pageNoContentAddChartDiv") WebElement pieChart;
    public @FindBy(css = ".q-page .chart-types-container #chartTypeSelectTypeDivLineChart") WebElement lineChart;
    public @FindBy(css = ".q-page .chart-types-container #chartTypeSelectTypeDivBarChart") WebElement barChart;
    public @FindBy(css = ".q-page .chart-types-container #chartTypeSelectTypeDivCircularBarChart") WebElement circularBarChart;
    public @FindBy(css = ".q-page .chart-types-container #chartTypeSelectTypeDivBubbleChart") WebElement bubbleChart;
    public @FindBy(css = ".q-page .chart-types-container #chartTypeSelectTypeDivCircularBubbleChart") WebElement circularBubbleChart;
    public @FindBy(css = ".q-page .chart-types-container #chartTypeSelectTypeDivSquareChart") WebElement squareChart;
    public @FindBy(css = ".q-page .chart-types-container #chartTypeSelectTypeDivRadarChart") WebElement radarChart;
    public @FindBy(css = ".q-page .chart-types-container #chartTypeSelectTypeDivParallelChart") WebElement parallelChart;
    public @FindBy(css = ".q-page .chart-types-container #chartTypeSelectTypeDivSunburstChart") WebElement sunBurstChart;
    public @FindBy(css = ".q-page .chart-types-container #chartTypeSelectTypeDivThemeRiverChart") WebElement themeRiverChart;
    public @FindBy(css = ".q-page .chart-types-container #chartTypeSelectTypeDivMapChart") WebElement mapChart;
    public @FindBy(css = ".q-page .chart-types-container #chartTypeSelectTypeDivInfoCard") WebElement infoCardChart;
    public @FindBy(css = ".q-page .chart-types-container #chartTypeSelectTypeDivMetricChart") WebElement metricChart;
    public @FindBy(css = ".q-page .chart-types-container #chartTypeSelectTypeDivPivotTable") WebElement pivotTableChart;
    public @FindBy(css = ".q-page .chart-types-container #chartTypeSelectTypeDivPivotTableRow") WebElement pivotTableRowChart;
    public @FindBy(css = ".q-page .chart-builder-menu #titleMenuTitleInputInput") WebElement chartTitle;
    public @FindBy(css = ".q-page .chart-builder-menu #titleMenuTitleSizeSelect") WebElement chartTitleSize;
    public @FindBy(css = ".q-page .chart-builder-menu #titleMenuSubtitleInputInput") WebElement chartSubTitle;
    public @FindBy(css = ".q-page .chart-builder-menu #titleMenuSubtitleSizeSelect") WebElement chartSubTitleSize;
    public @FindBy(css = ".q-page .chart-builder-menu #hAlignInputUpdateLeftBtn") WebElement chartLeftAlignButton;
    public @FindBy(css = ".q-page .chart-builder-menu #hAlignInputUpdateCenterBtn") WebElement chartCenterAlignButton;
    public @FindBy(css = ".q-page .chart-builder-menu #hAlignInputUpdateRightBtn") WebElement chartRightAlignButton;
    public @FindBy(css = ".q-page #pieBuilderDatasetMultipleAxesCheckbox") WebElement multipleAxisCheckbox;
    public @FindBy(css = ".q-page #pieBuilderAddAxisBtn") WebElement addAxisButtonPieChart;
    public @FindBy(css = ".q-page .q-field #axisComponentNameInputInput") WebElement axisNameInputField;
    public @FindBy(css = ".q-page .chart-builder-menu #pieBuilderAddAxisBtn") WebElement axisFieldDropDownPieChart;
    public @FindBy(css = ".q-page #axisComponentFieldSelect") WebElement axisFieldDropDownLineChart;
    public @FindBy(css = ".q-page .q-form #metricComponentOperationFieldSelect") WebElement operationField;
    public @FindBy(css = ".q-page .q-form #metricComponentOperationSelect") WebElement operation;
    public @FindBy(css = ".q-page .q-field #axisComponentNameInputInput") WebElement mainAndSecondAxisNameInputField;
    public @FindBy(css = ".q-page #infoCardBuilderContentEditor .q-editor__content") WebElement contentInfoCardChart;
    public @FindBy(css = ".q-page #metricBuilderDescriptionInputInput") WebElement descriptionMetricChart;
    public @FindBy(css = ".q-page #metricBuilderPercentageCheckbox") WebElement percentageCheckboxMetricChart;
    public @FindBy(css = ".q-page #metricBuilderComplementCheckbox") WebElement complementCheckboxMetricChart;
    public @FindBy(css = ".q-page #pivotTableBuilderAddPivotTableDataBtn") WebElement addDataButtonPivotTableChart;
    public @FindBy(css = ".q-page #pivotTableRowBuilderAddPivotTableDataBtn") WebElement addDataButtonPivotTableRowChart;
    public @FindBy(css = ".q-page #pivotTableBuilderAddColumnsBtn") WebElement addColumnButtonPivotTableChart;
    public @FindBy(css = ".q-page #pivotTableBuilderAddValuesBtn") WebElement addValuesButtonPivotTableChart;

    public @FindBy(css = ".q-page #pieBuilderAddFilterBtn") WebElement addFilterButtonPieChart;
    public @FindBy(css = ".q-page #lineBuilderAddFilterBtn") WebElement addFilterButtonLineChart;
    public @FindBy(css = ".q-page #barBuilderAddFilterBtn") WebElement addFilterButtonBarChart;
    public @FindBy(css = ".q-page #circularBarBuilderAddFilterBtn") WebElement addFilterButtonCircularBarChart;
    public @FindBy(css = ".q-page .q-field #filterComponentFieldSelectInput") WebElement filterFieldDropdown;
    public @FindBy(css = ".q-page #filterComponentSelectValueSelect") WebElement filterFieldValueDropdown;
    public @FindBy(css = ".q-page .q-checkbox#stackCOmponentStackBarsCheckbox") WebElement stackCheckBox;
    public @FindBy(css = ".q-page .q-field #stackComponentNameInputInput") WebElement stackName;
    public @FindBy(css = ".q-page #stackComponentFieldSelect") WebElement stackField;
    public @FindBy(css = ".q-dialog .q-toolbar #pageBuilderSaveChartBtn") WebElement saveChartButton;
    public @FindBy(css = ".q-page .dashboard-wrapper #pageSettingsBtn") WebElement pageSettingButton;
    public @FindBy(css = ".q-list #pageSettingsDeleteItem") WebElement pageDeleteButton;

    //Threads
    public static ThreadLocal<Integer> chartRecord = new ThreadLocal<>();


    public Dashboard_PageObject() {
        super();
        log.info("Driver is inside this class: " + this.getClass().getSimpleName());
        PageFactory.initElements(driver, this);
    }

    public void dashboard() {
        log.info("Opening the Dashboard from Hamburger Menu");
        try {
            Thread.sleep(1000);
            waitTillWebElementIsVisible("hamburgerMenu", hamburgerMenu);
            WaitUntilElementIsClickable(hamburgerMenu);
            waitAndClickOnElement(hamburgerMenu);
            Thread.sleep(200);
            waitTillWebElementIsVisible("hamburgerDrawer", ".q-body--prevent-scroll #q-app .main-menu .q-drawer");
            String dashboardSelector = "#q-app .main-menu #main-menu-item-dashboards";
            waitTillWebElementIsVisible("dashboard", dashboardSelector);
            waitAndClickOnElement("dashboard", dashboardSelector);
            waitUntilLoadingSpinnerIsShown("nlaDashboardPageLoader");
            waitUntilLoadingSpinnerIsGone("nlaDashboardPageLoader");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void createDashboard(DataTable dt) {
        log.info("Creating New Dashboard");
        try {
            List<Map<String, String>> dashboard = dt.asMaps(String.class, String.class);
            waitTillWebElementIsVisible("createDashboardButton", createDashboardButton);
            waitAndClickOnElement(createDashboardButton);
            waitTillWebElementIsVisible("createDashboardPopUp", ".q-dialog .q-card");
            waitTillWebElementIsVisible("dashboardTitle", dashboardTitle);
            dashboardTitle.click();
            sendingValueToWebElement("dashboardTitle", dashboardTitle, dashboard.get(0).get("Title"));
            Thread.sleep(1000);
            if (!dashboard.get(0).get("Description").equalsIgnoreCase("null")) {
                waitAndClickOnElement(dashboardDescriptionAddButton);
                waitTillWebElementIsVisible("dashboardDescriptionInputField", dashboardDescriptionInputField);
                sendingValueToWebElement("dashboardDescription", dashboardDescriptionInputField, dashboard.get(0).get("Description"));
            }
            waitTillWebElementIsVisible("dashboardType", dashboardType);
            clickOnDropDownAndSelectValue("dashboardType", dashboardType, dashboard.get(0).get("Dashboard Type"));
            waitAndClickOnElement("popupHeader", ".q-dialog .q-card .q-card__section .text-h6");
            if (dashboard.get(0).get("Remove Description").equalsIgnoreCase("Yes")) {
                waitAndClickOnElement(dashboardDescriptionRemoveButton);
            }
            if (dashboard.get(0).get("Enable/Disable list").equalsIgnoreCase("Yes")) {
                waitAndClickOnElement(dashboardEnableDisableList);
            }
            int dashboardDataLength = dashboard.get(0).get("Dashboard Data").length();
            if (dashboardDataLength > 1) {
                String[] dashboardsData = dashboard.get(0).get("Dashboard Data").split(",");
                for (String param : dashboardsData) {
                    clickOnDropDownAndSelectValue("dashboardData", dashboardData, param);
                }
            } else {
                clickOnDropDownAndSelectValue("dashboardData", dashboardData, dashboard.get(0).get("Dashboard Data"));
            }
            waitAndClickOnElement("popupHeader", ".q-dialog .q-card .q-card__section .text-h6");
            waitTillWebElementIsVisible("dashboardCreateButton", dashboardCreateButton);
            waitAndClickOnElement(dashboardCreateButton);
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            Thread.sleep(1000);
            String message = driver.findElement(By.cssSelector(".desktop .q-notifications .q-notification[role='alert'] .q-notification__message div:nth-child(1)")).getText();
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
            if (!message.equalsIgnoreCase("Success")) {
                Assert.fail("Dashboard has not been Created Successfully");
            }
            log.info("Chart has been Created Successfully");

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public void createChart(String chartName, DataTable dt) {
        log.info("Creating a " + chartName + " chart");
        List<Map<String, String>> chart = dt.asMaps(String.class, String.class);
        boolean chartExists = false;
        try {
            if (driver.findElements(By.cssSelector(".q-page .page-container #pageNoContentAddChartDiv")).size() == 1) {
                waitTillWebElementIsVisible("createChartButton", createChartPageButton);
                waitAndClickOnElement(createChartPageButton);
            } else {
                waitTillWebElementIsVisible("addChartButton", addChartButton);
                waitAndClickOnElement(addChartButton);
                waitTillWebElementIsVisible("newChartButton", newChartMenuButton);
                waitAndClickOnElement(newChartMenuButton);
            }
            if (!chartName.equalsIgnoreCase("Pie")) {
                waitTillWebElementIsVisible("chartType", ".q-page .selected-type");
                waitAndClickOnElement("type", ".q-page .selected-type");
                if(!(driver.findElements(By.cssSelector(".q-page .chart-types-container.active")).size() ==1)){
                    driver.findElement(By.cssSelector(".q-page .selected-type")).click();
                }
                //Chart Type
                String chartType = ".q-page .chart-types-container #" + chartTypeCSSSelector(chartName);
                waitAndClickOnElement("chartType", chartType);

            }
            //Title and Subtitle
            if(!(chartName.equalsIgnoreCase("Pivot Table") || chartName.equalsIgnoreCase("Pivot Table Row"))) {
                waitTillWebElementIsVisible("chartTitle", chartTitle);
                Thread.sleep(2000);
                chartTitle.click();
                chartTitle.clear();
                chartTitle.sendKeys(Keys.chord(Keys.CONTROL, "a"), (chart.get(0).get("Title")));
                if (!chart.get(0).get("Title Size").equalsIgnoreCase("null")) {
                    clickOnDropDownAndSelectValue("chartTitleSize", chartTitleSize, chart.get(0).get("Title Size"));
                }
                waitTillWebElementIsVisible("chartSubtitle", chartSubTitle);
                clearField(chartSubTitle);
                sendingValueToWebElement("chartSubTitle", chartSubTitle, chart.get(0).get("Subtitle"));
                if (!chart.get(0).get("Subtitle Size").equalsIgnoreCase("null")) {
                    clickOnDropDownAndSelectValue("chartSubTitleSize", chartSubTitleSize, chart.get(0).get("Subtitle Size"));
                }
                //Alignment
                if (chart.get(0).get("Alignment").equalsIgnoreCase("Left")) {
                    waitAndClickOnElement(chartLeftAlignButton);
                } else if (chart.get(0).get("Alignment").equalsIgnoreCase("Right")) {
                    waitAndClickOnElement(chartRightAlignButton);
                } else if (chart.get(0).get("Alignment").equalsIgnoreCase("Center")) {
                    log.info("Alignment is already centered");
                }
            }
            //Pie Chart
            if (chartName.equalsIgnoreCase("Pie") || chartName.equalsIgnoreCase("Sunburst")
                    || chartName.equalsIgnoreCase("Map")) {
                //Axis
                if (chartName.equalsIgnoreCase("Pie") || chartName.equalsIgnoreCase("Map")) {
                    if (chart.get(0).get("Multiple Axis Checkbox").equalsIgnoreCase("Yes")) {
                        waitTillWebElementIsVisible("multipleAxis", ".q-page #"+multipleAxesCheckbox(chartName));
                        waitAndClickOnElement("multipleAxesCheckboxCheckbox",".q-page #"+multipleAxesCheckbox(chartName));
                    }
                }
                if (chart.get(0).get("Add Axis").equalsIgnoreCase("Yes")) {
                    waitAndClickOnElement("addAxisButton", ".q-page #" + addAxisButtonSelector(chartName));
                    String[] axisName = chart.get(0).get("Axis Name").split(",");
                    String[] axisField = chart.get(0).get("Axis Field").split(",");
                    int axisNameSize = axisName.length;
                    for (int i = 0; i < axisNameSize; i++) {
                        List<WebElement> axisNameSelector = driver.findElements(By.cssSelector(".q-page .q-form #axisComponentNameInputInput"));
                        List<WebElement> axisFieldSelector = driver.findElements(By.cssSelector(".q-page .q-form #axisComponentFieldSelect"));
                        waitTillWebElementIsVisible("axisNameFields", axisNameInputField);
                        sendingValueToWebElement("axisName", axisNameSelector.get(i), axisName[i]);
                        clickOnDropDownAndSelectValue("axisField", axisFieldSelector.get(i), axisField[i]);
                    }
                } else {
                    waitTillWebElementIsVisible("axisNameField", axisNameInputField);
                    sendingValueToWebElement("axisNameField", axisNameInputField, chart.get(0).get("Axis Name"));
                    clickOnDropDownAndSelectValue("AxisField", axisFieldDropDownPieChart, chart.get(0).get("Axis Field"));
                }
                //Operations
                operationFields(chartName,chart);
                //Filters Fields
//                filtersFields(chartName, chart);
            }
            // Line, Bar, Circular, Radar, Parallel Charts
            else if (chartName.equalsIgnoreCase("Line") || chartName.equalsIgnoreCase("Bar") ||
                    chartName.equalsIgnoreCase("Circular Bar") || chartName.equalsIgnoreCase("Radar") ||
                    chartName.equalsIgnoreCase("Parallel")) {
                //Axis
                waitTillWebElementIsVisible("axisNameField", axisNameInputField);
                sendingValueToWebElement("axisNameField", axisNameInputField, chart.get(0).get("Axis Name"));
                clickOnDropDownAndSelectValue("AxisField", axisFieldDropDownLineChart, chart.get(0).get("Axis Field"));
                //Stack
                if (chartName.equalsIgnoreCase("Line") || chartName.equalsIgnoreCase("Bar") ||
                        chartName.equalsIgnoreCase("Circular Bar")) {
                    if (chart.get(0).get("Stack Chart Checkbox").equalsIgnoreCase("Yes")) {
                        waitTillWebElementIsVisible("stackCheckBox", stackCheckBox);
                        waitAndClickOnElement(stackCheckBox);
                    }
                    waitTillWebElementIsVisible("stackName", stackName);
                    sendingValueToWebElement("stackName", stackName, chart.get(0).get("Stack Name"));
                    clickOnDropDownAndSelectValue("stackField", stackField, chart.get(0).get("Stack Field"));
                }
                //Operation
                operationFields(chartName,chart);
                //Filters Fields
//                filtersFields(chartName,chart);
            }
            else if (chartName.equalsIgnoreCase("Bubble") || chartName.equalsIgnoreCase("Circular Bubble") ||
                    chartName.equalsIgnoreCase("Square") || chartName.equalsIgnoreCase("Theme River") )   {
                //Axis
                waitTillWebElementIsVisible("axisNameField", mainAndSecondAxisNameInputField);
                List<WebElement> axisNames = driver.findElements(By.cssSelector(".q-page .q-field #axisComponentNameInputInput"));
                List<WebElement> axisField = driver.findElements(By.cssSelector(".q-page .q-field#axisComponentFieldSelect"));
                if(chartName.equalsIgnoreCase("Theme River")){
                    sendingValueToWebElement("timeAxisName", axisNames.get(0), chart.get(0).get("Time Axis Name"));
//                    clickOnDropDownAndSelectValue("timeAxisField", axisField.get(0), chart.get(0).get("Time Axis Field"));
                    sendingValueToWebElement("valueAxisName", axisNames.get(1), chart.get(0).get("Value Axis Name"));
                    clickOnDropDownAndSelectValue("valueAxisName", axisField.get(1), chart.get(0).get("Value Axis Field"));
                }else {
                    sendingValueToWebElement("mainAxisName", axisNames.get(0), chart.get(0).get("Main Axis Name"));
                    clickOnDropDownAndSelectValue("mainAxisField", axisField.get(0), chart.get(0).get("Main Axis Field"));
                    sendingValueToWebElement("SecondAxisName", axisNames.get(1), chart.get(0).get("Second Axis Name"));
                    clickOnDropDownAndSelectValue("SecondAxisName", axisField.get(1), chart.get(0).get("Second Axis Field"));
                }
                //Operation Fields
                operationFields(chartName,chart);
                //Filters Fields
//                filtersFields(chartName,chart);
            }
            else if (chartName.equalsIgnoreCase("Info Card")){
                //Content
                waitTillWebElementIsVisible("contentInfoCard",contentInfoCardChart);
                contentInfoCardChart.click();
                sendingValueToWebElement("contentInfoCardChart",contentInfoCardChart,chart.get(0).get("Content"));
                //Operation
                operationFields(chartName,chart);
                //Filters
//                filtersFields(chartName,chart);
            }else if (chartName.equalsIgnoreCase("Metric")){
                //Description
                waitTillWebElementIsVisible("descriptionMetricChart",descriptionMetricChart);
                descriptionMetricChart.click();
                sendingValueToWebElement("descriptionMetricChart",descriptionMetricChart,chart.get(0).get("Description"));
                if(chart.get(0).get("Percentage Checkbox").equalsIgnoreCase("Yes")){
                    waitAndClickOnElement(percentageCheckboxMetricChart);
                    if(chart.get(0).get("Complement Checkbox").equalsIgnoreCase("Yes")){
                        waitAndClickOnElement(complementCheckboxMetricChart);
                        operationFields(chartName,chart);
                    }
                }else{
                    operationFields(chartName,chart);
                }
                //Filters
//               filtersFields(chartName,chart);
            }else if(chartName.equalsIgnoreCase("Pivot Table") || chartName.equalsIgnoreCase("Pivot Table Row")){
                waitTillWebElementIsEnabled("chartTitle",".q-page #titleMenuTitleInputInput");
                Thread.sleep(2000);
                driver.findElement(By.cssSelector(".q-page #titleMenuTitleInputInput")).click();
                driver.findElement(By.cssSelector(".q-page #titleMenuTitleInputInput")).clear();
                driver.findElement(By.cssSelector(".q-page #titleMenuTitleInputInput")).sendKeys(Keys.chord(Keys.CONTROL, "a"), (chart.get(0).get("Title")));
//                sendingValueToWebElement("chartTitle",driver.findElement(By.cssSelector(".q-page #titleMenuTitleInputInput")),chart.get(0).get("Title"));
                waitTillWebElementIsEnabled("chartTitle",".q-page #titleMenuSubtitleInput");
                sendingValueToWebElement("chartTitle",driver.findElement(By.cssSelector(".q-page #titleMenuSubtitleInputInput")),chart.get(0).get("Subtitle"));
                //Data Section
                for(int i=0; i<Integer.parseInt(chart.get(0).get("Data Size")); i++){
                    if(chartName.equalsIgnoreCase("Pivot Table")){
                        waitTillWebElementIsVisible("addDataButton",addDataButtonPivotTableChart);
                        waitTillWebElementIsVisible("addDataButton", addDataButtonPivotTableChart);
                        waitAndClickOnElement(addDataButtonPivotTableChart);
                    }else{
                        waitTillWebElementIsVisible("addDataButtonPivotRow",addDataButtonPivotTableRowChart);
                        waitTillWebElementIsVisible("addDataButtonPivotRowChart", addDataButtonPivotTableRowChart);
                        waitAndClickOnElement(addDataButtonPivotTableRowChart);
                    }

                }
                String[] dataName = chart.get(0).get("Data Name").split(",");
                String[] dataField = chart.get(0).get("Data Field").split(",");
                int dataSize = dataName.length;
                for (int i = 0; i < dataSize; i++) {
                    List<WebElement> dataNameSelector = driver.findElements(By.cssSelector(".q-page .q-field #axisComponentNameInputInput"));
                    List<WebElement> dataFieldSelector = driver.findElements(By.cssSelector(".q-page .q-field#axisComponentFieldSelect"));
                    waitTillWebElementIsVisible("dataNameFields", axisNameInputField);
                    sendingValueToWebElement("dataName", dataNameSelector.get(i), dataName[i]);
                    clickOnDropDownAndSelectValue("axisField", dataFieldSelector.get(i), dataField[i]);
                }
                if(chartName.equalsIgnoreCase("Pivot Table")) {
                    //Column Section
                    for (int i = 0; i < Integer.parseInt(chart.get(0).get("Column Size")); i++) {
                        waitTillWebElementIsVisible("addColumnButton", addColumnButtonPivotTableChart);
                        waitAndClickOnElement(addColumnButtonPivotTableChart);
                    }
                    String[] columnName = chart.get(0).get("Column Name").split(",");
                    String[] columnField = chart.get(0).get("Column Field").split(",");
                    int columnSize = columnName.length;
                    for (int i = dataSize; i < (columnSize + dataSize); i++) {
                        List<WebElement> columnNameSelector = driver.findElements(By.cssSelector(".q-page .q-field #axisComponentNameInputInput"));
                        List<WebElement> columnFieldSelector = driver.findElements(By.cssSelector(".q-page .q-field#axisComponentFieldSelect"));
                        waitTillWebElementIsVisible("columnNameFields", axisNameInputField);
                        sendingValueToWebElement("columnName", columnNameSelector.get(i), columnName[i - dataSize]);
                        clickOnDropDownAndSelectValue("axisField", columnFieldSelector.get(i), columnField[i - dataSize]);
                    }
                    //Values
                    if (chart.get(0).get("Add Values").equalsIgnoreCase("Yes")) {
                        waitTillWebElementIsVisible("addValuesButton", addValuesButtonPivotTableChart);
                        waitAndClickOnElement(addValuesButtonPivotTableChart);
                        waitTillWebElementIsVisible("ValuesName", ".q-page .q-field #metricComponentOperationFieldInputInput");
                        sendingValueToWebElement("ValuesName", driver.findElement(By.cssSelector(".q-page .q-field #metricComponentOperationFieldInputInput")), chart.get(0).get("Values Name"));
                        List<WebElement> valuesFieldSelector = driver.findElements(By.cssSelector(".q-page .q-field #metricComponentOperationFieldSelectInput"));
                        clickOnDropDownAndSelectValue("ValuesField", valuesFieldSelector.get(1), chart.get(0).get("Values Field"));
                        waitTillWebElementIsVisible("valuesDropdown", ".q-page .q-field#metricComponentOperationSelect");
                        clickOnDropDownAndSelectValue("ValuesOperation", driver.findElement(By.cssSelector(".q-page .q-field#metricComponentOperationSelect")), chart.get(0).get("Values Operation"));
                    }
                }
                //Filters
                filtersFields(chartName,chart);
            }

            waitTillWebElementIsVisible("saveChartButton", saveChartButton);
            waitAndClickOnElement(saveChartButton);
            waitUntilLoadingSpinnerIsShown("nlaChartSaveLoader");
            waitUntilLoadingSpinnerIsGone("nlaChartSaveLoader");
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            Thread.sleep(1000);
            String message = driver.findElement(By.cssSelector(".desktop .q-notifications .q-notification[role='alert'] .q-notification__message div:nth-child(1)")).getText();
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
            int chartRecords = driver.findElements(By.cssSelector(".q-page .page-container .chart-view")).size();
            if (!message.equalsIgnoreCase("Success") && chartRecords==chartRecord.get()) {
                Assert.fail("Chart has not been Created Successfully");
            }
            chartRecord.set(chartRecords);
            log.info("Chart has been Created Successfully");
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public void operationFields(String chartName, List<Map<String, String>> chart){
        waitTillWebElementIsVisible("operationField", operationField);
        try {
            if(chartName.equalsIgnoreCase("Radar") || chartName.equalsIgnoreCase("Parallel") ||
                    chartName.equalsIgnoreCase("Map") || chartName.equalsIgnoreCase("Info Card") ||
                    chartName.equalsIgnoreCase("Metric")){
                boolean addOperationCheck = false;
                boolean percentageCheckboxCheck = false;
                if(chart.get(0).containsKey("Add Operation") && chart.get(0).get("Add Operation").equalsIgnoreCase("Yes")) {
                    addOperationCheck = true;
                }
                if(chart.get(0).containsKey("Percentage Checkbox") && chart.get(0).get("Percentage Checkbox").equalsIgnoreCase("Yes")) {
                    percentageCheckboxCheck = true;
                }
                if (addOperationCheck || percentageCheckboxCheck) {
                    if(addOperationCheck) {
                        waitTillWebElementIsVisible("addOperationButton", ".q-page #" + addOperationButtonSelector(chartName));
                        waitAndClickOnElement("chartType", ".q-page #" + addOperationButtonSelector(chartName));
                    }
                    String[] operation_Field = chart.get(0).get("Operation Field").split(",");
                    String[] operationValue = chart.get(0).get("Operation").split(",");
                    int operationFieldLength = operation_Field.length;
                    for (int i = 0; i < operationFieldLength; i++) {
                        List<WebElement> OperationFieldSelector = driver.findElements(By.cssSelector(".q-page .q-form #metricComponentOperationFieldSelect"));
                        waitTillWebElementIsVisible("OperationFieldSelector", operationField);
                        clickOnDropDownAndSelectValue("operationField", OperationFieldSelector.get(i), operation_Field[i]);
                    }
                    for (int i = 0; i < operationFieldLength; i++) {
                        List<WebElement> OperationValueSelector = driver.findElements(By.cssSelector(".q-page .q-form #metricComponentOperationSelect"));
                        waitTillWebElementIsVisible("Operation", operation);
                        clickOnDropDownAndSelectValue("Operation", OperationValueSelector.get(i), operationValue[i]);
                    }
                }else {
                    clickOnDropDownAndSelectValue("operationField", operationField, chart.get(0).get("Operation Field"));
                    waitTillWebElementIsVisible("Operation", operation);
                    clickOnDropDownAndSelectValue("Operation", operation, chart.get(0).get("Operation"));
                }
            }else{
                clickOnDropDownAndSelectValue("operationField", operationField, chart.get(0).get("Operation Field"));
                waitTillWebElementIsVisible("Operation", operation);
                clickOnDropDownAndSelectValue("Operation", operation, chart.get(0).get("Operation"));
            }

        }catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
    public void filtersFields(String chartName, List<Map<String, String>> chart){
        try {
        if (chart.get(0).get("Add Filter").equalsIgnoreCase("Yes") && !chartName.equalsIgnoreCase("Metric")) {
            waitTillWebElementIsVisible("addFilterButton", ".q-page #" + addFilterButtonSelector(chartName));
            waitAndClickOnElement("chartType", ".q-page #" + addFilterButtonSelector(chartName));
            waitAndClickOnElement(filterFieldDropdown);
            waitAndClickOnElement("type", ".q-page .selected-type");
            String[] filterField = chart.get(0).get("Filter Field").split(",");
            String[] filterFieldValue = chart.get(0).get("Field Value").split(",");
            int filterFieldSize = filterField.length;
            for (int i = 0; i < filterFieldSize; i++) {
                List<WebElement> filterFieldSelector = driver.findElements(By.cssSelector(".q-page #filterComponentFieldSelect"));
                waitTillWebElementIsVisible("filterFieldDropdown", filterFieldDropdown);
                clickOnDropDownAndSelectValue("filterFieldSelector", filterFieldSelector.get(i), filterField[i]);
            }
            for (int i = 0; i < filterFieldSize; i++) {
                List<WebElement> filterFieldValueSelector = driver.findElements(By.cssSelector(".q-page #filterComponentSelectValueSelect"));
                waitTillWebElementIsVisible("filterFieldValue", filterFieldValueDropdown);
                clickOnDropDownAndSelectValue("filterFieldValue", filterFieldValueSelector.get(i), filterFieldValue[i]);
            }
        } else {
            if(chartName.equalsIgnoreCase("Metric") && !(chart.get(0).get("Percentage Checkbox").equalsIgnoreCase("No"))){
                waitAndClickOnElement("chartType", ".q-page #" + addFilterButtonSelector(chartName));
                waitAndClickOnElement(filterFieldDropdown);
                waitAndClickOnElement("type", ".q-page .selected-type");
            }
            waitTillWebElementIsVisible("filterFieldDropdown", filterFieldDropdown);
            clickOnDropDownAndSelectValue("filterFieldSelector", filterFieldDropdown, chart.get(0).get("Filter Field"));
            clickOnDropDownAndSelectValue("filterFieldValue", filterFieldValueDropdown, chart.get(0).get("Field Value"));
        }
        }catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteTheDashboard() {
        log.info("Deleting the Newly Created Dashboard Page");
        try {
         waitTillWebElementIsVisible("pageSettingButton",pageSettingButton);
         waitAndClickOnElement(pageSettingButton);
         waitTillWebElementIsVisible("pageDeleteButton",pageDeleteButton);
         waitAndClickOnElement(pageDeleteButton);
         waitTillWebElementIsVisible("dialogPopup",".q-dialog .q-card .q-btn:nth-child(2)");
         waitAndClickOnElement("okButton",".q-dialog .q-card .q-btn:nth-child(2)");
         waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
         Thread.sleep(1000);
         String message = driver.findElement(By.cssSelector(".desktop .q-notifications .q-notification[role='alert'] .q-notification__message div:nth-child(1)")).getText();
         waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
         if (!message.equalsIgnoreCase("Success")) {
             Assert.fail("Dashboard Page has not been Deleted Successfully");
         }
         log.info("Dashboard Page has been Deleted Successfully");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String chartTypeCSSSelector(String chartType) {
        String ids = null;
        switch (chartType) {
            case "Line":
                ids = "chartTypeSelectTypeDivLineChart";
                break;
            case "Bar":
                ids = "chartTypeSelectTypeDivBarChart";
                break;
            case "Circular Bar":
                ids = "chartTypeSelectTypeDivCircularBarChart";
                break;
            case "Bubble":
                ids = "chartTypeSelectTypeDivBubbleChart";
                break;
            case "Circular Bubble":
                ids = "chartTypeSelectTypeDivCircularBubbleChart";
                break;
            case "Square":
                ids = "chartTypeSelectTypeDivSquareChart";
                break;
            case "Radar":
                ids = "chartTypeSelectTypeDivRadarChart";
                break;
            case "Parallel":
                ids = "chartTypeSelectTypeDivParallelChart";
                break;
            case "Sunburst":
                ids = "chartTypeSelectTypeDivSunburstChart";
                break;
            case "Theme River":
                ids = "chartTypeSelectTypeDivThemeRiverChart";
                break;
            case "Map":
                ids = "chartTypeSelectTypeDivMapChart";
                break;
            case "Info Card":
                ids = "chartTypeSelectTypeDivInfoCard";
                break;
            case "Metric":
                ids = "chartTypeSelectTypeDivMetricChart";
                break;
            case "Pivot Table":
                ids = "chartTypeSelectTypeDivPivotTable";
                break;
            case "Pivot Table Row":
                ids = "chartTypeSelectTypeDivPivotTableRow";
                break;
        }
        return ids;

    }

    public String addFilterButtonSelector(String chartType) {
        String ids = null;
        switch (chartType) {
            case "Pie":
                ids = "pieBuilderAddFilterBtn";
                break;
            case "Line":
                ids = "lineBuilderAddFilterBtn";
                break;
            case "Bar":
                ids = "barBuilderAddFilterBtn";
                break;
            case "Circular Bar":
                ids = "circularBarBuilderAddFilterBtn";
                break;
            case "Bubble":
                ids = "bubbleBuilderAddFilterBtn";
                break;
            case "Circular Bubble":
                ids = "circularBubbleBuilderAddFilterBtn";
                break;
            case "Square":
            case "Sunburst":
                ids = "squareBuilderAddFilterBtn";
                break;
            case "Radar":
                ids = "radarBuilderAddFilterBtn";
                break;
            case "Parallel":
                ids = "parallelBuilderAddFilterBtn";
                break;
            case "Theme River":
                ids = "themeRiverBuilderAddFilterBtn";
                break;
            case "Map":
            ids = "mapBuilderAddOperationBtn";
            break;
            case "Info Card":
                ids = "infoCardBuilderAddFilterBtn";
                break;
            case "Metric":
                ids = "metricBuilderAddFilterBtn";
                break;
            case "Pivot Table":
                ids = "pivotTableBuilderAddFilterBtn";
                break;
            case "Pivot Table Row":
                ids = "pivotTableRowBuilderAddFilterBtn";
                break;

        }
        return ids;
    }

    public String addOperationButtonSelector(String chartType) {
        String ids = null;
        switch (chartType) {
            case "Radar":
                ids = "radarBuilderAddOperationBtn";
                break;
            case "Parallel":
                ids = "parallelBuilderAddOperationBtn";
                break;
            case "Map":
                ids = "mapBuilderAddOperationBtn";
                break;
            case "Info Card":
                ids = "infoCardBuilderAddOperationBtn";
                break;
        }
        return ids;
    }

    public String addAxisButtonSelector(String chartType) {
        String ids = null;
        switch (chartType) {
            case "Pie":
                ids = "pieBuilderAddAxisBtn";
                break;
            case "Sunburst":
                ids = "squareBuilderAddAxisBtn";
                break;
            case "Map":
                ids = "mapBuilderAddAxisBtn";
                break;
        }
        return ids;
    }

    public String multipleAxesCheckbox(String chartType) {
        String ids = null;
        switch (chartType) {
            case "Pie":
                ids = "pieBuilderDatasetMultipleAxesCheckbox";
                break;
            case "Map":
                ids = "mapBuilderMultipleAxesCheckbox";
                break;
        }
        return ids;
    }

    public void releasingClassThreads(){
        chartRecord.remove();
    }
}
