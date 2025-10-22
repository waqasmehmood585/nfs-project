package com.nakisa.nlaAutomation.pageObjects;

import com.nakisa.nlaAutomation.Constant;
import com.nakisa.nlaAutomation.stepDefinitions.MasterHooks;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
public class defaultValues_PageObject extends Common_BasePage_PageObject {

    public @FindBy(css = ".q-table__container .q-btn#add-application-config-btn") WebElement addApplicationConfig;
    public @FindBy(css = ".q-card .q-expansion-item__content .row .form-input #form-location") WebElement formLocation;
    public @FindBy(css = ".q-card .q-expansion-item__content .row .form-input #field") WebElement targetField;
    public @FindBy(css = "#status-disabled-radio") WebElement inactiveStatus;
    public @FindBy(css = "#status-enabled-radio") WebElement activeStatus;
    public @FindBy(css = "#control-locked-radio") WebElement lockedRadioBtn;
    public @FindBy(css = "#control-editable-radio") WebElement editableRadioBtn;
    public @FindBy(css = "#default-field-info") WebElement defaultValues;
    public @FindBy(css = ".relative-position:nth-child(2) .q-my-xs.col-12:nth-child(1) .q-select--with-chips  div.q-field__native") WebElement condition1;
    public @FindBy(css = ".relative-position:nth-child(2) .q-my-xs.col-12:nth-child(2) .q-select--with-chips  div.q-field__native") WebElement condition2;
    public @FindBy(css = "#delete-btn") WebElement deleteBtn;
    public @FindBy(css = ".q-dialog .q-card #lease-area") WebElement masterAgreementLeaseArea;
    public @FindBy(css = ".desktop .q-header Button[aria-label='Menu']") WebElement hamburgerMenu;
    public @FindBy(css = "#q-app .q-page .q-tabs__content #master-agreement-tab") WebElement masterAgreementMenuTab;
    public @FindBy(css = "#q-app .q-toolbar button.q-btn--unelevated") WebElement addMasterAgreement;
    public @FindBy(css = ".q-dialog .q-card #name-input") WebElement masterAgreementName;
    public @FindBy(css = "#q-app .q-page #navigation-expansion #contract-nav-add-btn") WebElement addContract;
    public @FindBy(css = ".q-dialog .q-card #name-input") WebElement contractName;
    public @FindBy(css = ".q-dialog .q-card #principal-position-type") WebElement contractPrincipalPosition;
    public @FindBy(css = "#q-app .q-page #definition-step") WebElement contractDefinitionTab;
    public @FindBy(css = ".q-page .absolute #lease-component-definition-general-info-form-expansion #fair-market-value-input") WebElement fmvValueDefinition;
    public @FindBy(css = ".q-page .absolute #lease-component-definition-general-info-form-expansion #residual-value-input") WebElement salvageValueDefinition;
    public @FindBy(css = ".q-page .absolute #lease-component-definition-general-info-form-expansion #gross-book-value-input") WebElement gbvUnitDefinition;
    public @FindBy(css = "#q-app .q-page #definition-step") WebElement leaseComponentDefinitionTab;
    public @FindBy(css = ".q-page .absolute #unguaranteed-residual-value-input") WebElement UnguaranteedResidualValueDefinition;
    public @FindBy(css = ".q-page .q-field #rou-end-date-input-input") WebElement rouEndDateValueDefinition;
    public @FindBy(css = ".q-page .absolute #spreading-frequency") WebElement spreadingFrequencyDefinition;
    public @FindBy(css = ".q-page .q-field #rou-start-date-input-input") WebElement rouStartDateValueDefinition;
    public @FindBy(css = ".q-page .absolute #guaranteed-residual-value-input") WebElement residualValueDefinition;
    public @FindBy(css = ".q-page .absolute #edit-unit-distribution-btn") WebElement unitDistributionBtnDefination;
    public @FindBy(css = ".q-dialog #add-new-unit-distribution-row") WebElement addNewUnitRowDefination;
    public @FindBy(css = "#id-search-input-input") WebElement idSearch;
    public @FindBy(css = ".q-td:nth-child(5) .q-anchor--skip:nth-child(1)")WebElement targetFieldSearch;
    public @FindBy(css = ".q-td:nth-child(8)  div.q-field__native")WebElement configurationStatusSearch;


    private String[] rowList = null;

    public defaultValues_PageObject() {
        super();
        log.info("Driver is inside this class: " + this.getClass().getSimpleName());
        PageFactory.initElements(driver, this);
    }

    public void definingDefaultValueConfigs(String operation, String row, DataTable dt) {
        try {
            log.info("User is going to add DefaultField Values");
            List<Map<String, String>> dvConfig = dt.asMaps(String.class, String.class);
            switch (operation) {
                case "Create":
                    for (int i = 0; i < dvConfig.size(); i++) {
                        Map<String, String> currentRow = dvConfig.get(i);
                        waitTillWebElementIsVisible("addApplicationConfig", addApplicationConfig);
                        waitAndClickOnElement(addApplicationConfig);
                        waitTillWebElementIsVisible("formLocation", formLocation);
                        if (!currentRow.get("Form Location").equalsIgnoreCase("null")) {
                            clickOnDropDownAndSelectValue("formLocationValue", formLocation, currentRow.get("Form Location"));
                        }
                        if (!currentRow.get("Target Field").equalsIgnoreCase("null")) {
                            clickOnDropDownAndSelectValue("targetFieldValue", targetField, currentRow.get("Target Field"));
                        }
                        formFilling(currentRow);
                    }
                    break;
                case "Update":
                    if (row.length() <= 1) {
                        System.out.println("Index length is 1");
                        int numberLength = row.length();
                        rowList = row.split("", numberLength);
                    } else {
                        System.out.println("Index length is more than 1");
                        int numberLength = ((row.length() / 2) + 1);
                        rowList = row.split(",", numberLength);
                    }
                    for (int rowIndex = 0; rowIndex < rowList.length; rowIndex++) {
                        String currentRowIndex = rowList[rowIndex].trim();
                        int rowIdx = Integer.parseInt(currentRowIndex) - 1;

                        if (rowIdx >= 0 && rowIdx < dvConfig.size()) {
                            Map<String, String> currentRow = dvConfig.get(rowIdx);
                            int nthChild = rowIdx + 2;
                            waitTillWebElementIsVisible("editIcon", ".cursor-pointer:nth-child(" + nthChild + ") .q-btn--rectangle.text-primary");
                            WaitUntilElementIsClickable(driver.findElement(By.cssSelector(".cursor-pointer:nth-child(" + nthChild + ") .q-btn--rectangle.text-primary")));
                            driver.findElement(By.cssSelector(".cursor-pointer:nth-child(" + nthChild + ") .q-btn--rectangle.text-primary")).click();
                            waitTillWebElementIsVisible("configurationStatus", defaultValues);
                            formFilling(currentRow);
                        }
                    }
                    break;
                case "Delete":
                    if (row.length() <= 1) {
                        System.out.println("Index length is 1");
                        int numberLength = row.length();
                        rowList = row.split("", numberLength);
                    } else {
                        System.out.println("Index length is more than 1");
                        int numberLength = ((row.length() / 2) + 1);
                        rowList = row.split(",", numberLength);
                    }
                    for (int rowIndex = rowList.length - 1; rowIndex >= 0; rowIndex--) {
                        String currentRowIndex = rowList[rowIndex].trim();
                        int rowIdx = Integer.parseInt(currentRowIndex) - 1;

                        if (rowIdx >= 0) {
                            int nthChild = rowIdx + 2;
                            waitTillWebElementIsVisible("editIcon", ".cursor-pointer:nth-child(" + nthChild + ") .q-btn--rectangle.text-primary");
                            WaitUntilElementIsClickable(driver.findElement(By.cssSelector(".cursor-pointer:nth-child(" + nthChild + ") .q-btn--rectangle.text-primary")));
                            driver.findElement(By.cssSelector(".cursor-pointer:nth-child(" + nthChild + ") .q-btn--rectangle.text-primary")).click();
                            waitTillWebElementIsVisible("configurationStatus", defaultValues);
                            waitAndClickOnElement(deleteBtn);
                            waitTillWebElementIsVisible("popupConfirmation", ".q-focusable.q-hoverable.bg-red:nth-child(2)");
                            waitAndClickOnElement("popupConfirmation", ".q-focusable.q-hoverable.bg-red:nth-child(2)");
                            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
                            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
                        }

                    }
                    break;
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void formFilling(Map<String, String> currentRow) {
        try {
            if (currentRow.get("Configuration Status").equalsIgnoreCase("Active")) {
                if (!(driver.findElements(By.cssSelector("[aria-checked='true']#status-enabled-radio")).size() == 1)) {
                    waitAndClickOnElement(activeStatus);
                }
            } else {
                if (driver.findElements(By.cssSelector("[aria-checked='false']#status-disabled-radio")).size() == 1) {
                    waitAndClickOnElement(inactiveStatus);

                }
            }
            if (currentRow.get("Field Editability").equalsIgnoreCase("Locked")) {
                if (!(driver.findElements(By.cssSelector("[aria-checked='true']#control-locked-radio")).size() == 1)) {
                    waitAndClickOnElement(lockedRadioBtn);
                }
            } else {
                if (driver.findElements(By.cssSelector("[aria-checked='false']#control-editable-radio")).size() == 1) {
                    waitAndClickOnElement(editableRadioBtn);

                }
            }
            if (!currentRow.get("Condition1").equalsIgnoreCase("null")) {
                clickOnDropDownToTypeAndSelectCheckBox("Condition1", condition1, currentRow.get("Condition1"));
            }
            if (!currentRow.get("Condition2").equalsIgnoreCase("null")) {
                clickOnDropDownToTypeAndSelectCheckBox("Condition2", condition2, currentRow.get("Condition2"));
            }
            if (!currentRow.get("Default Values").equalsIgnoreCase("null")) {
                clickOnDropDownToTypeAndSelectValue("defaultField", defaultValues, currentRow.get("Default Values"));
            }
            if (!(submitButton.isEnabled())) {
                Assert.fail("Mandatory fields are missing");
            } else {
                clickOnSubmitPopup("Submit / Add");
                waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
                waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void validateDVConfigs(String entity) {
        log.info("User is going to validate the " + entity + " default value configs");
        switch (entity) {
            case "Master Agreement":
                waitTillWebElementIsVisible("hamburgerMenu", hamburgerMenu);
                try {
                    waitAndClickOnElement(hamburgerMenu);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                waitTillWebElementIsVisible("hamburgerDrawer", "#q-app .main-menu .q-drawer");
                driver.findElement(By.cssSelector("#q-app .main-menu #main-menu-item-landing-pages")).click();
                waitUntilLoadingSpinnerIsShown("nlaLoadingPage");
                waitUntilLoadingSpinnerIsGone("nlaLoadingPage");
                log.info("Creating a new master agreement");

                if (MasterHooks.configurationProperties.get().getRunAutomationOnNCPBuild()) {
                    waitUntilLoadingSpinnerIsShown("nlaLoadingPage");
                    waitUntilLoadingSpinnerIsGone("nlaLoadingPage");
                }
                waitTillWebElementIsVisible("masterAgreementTab", masterAgreementMenuTab);
                try {
                    waitAndClickOnElement(masterAgreementMenuTab);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("var style = document.createElement('style');" + "style.innerHTML = '[data-wfx-element=\"true\"] { display: none !important; }';" + "document.head.appendChild(style);");
                masterAgreementMenuTab.click();
                waitUntilLoadingSpinnerIsShown("nlaLoadingPage");
                waitUntilLoadingSpinnerIsGone("nlaLoadingPage");
                waitTillWebElementIsVisible("addMasterAgreement", addMasterAgreement);
                WaitUntilElementIsClickable(addMasterAgreement);
                addMasterAgreement.click();
                try {
                    waitAndClickOnElement(masterAgreementName);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                sendingValueToWebElement("masterAgreementName", masterAgreementName, getValuesFromExcel("Inception", "Master Agreement Level", "Master Agreement Name", 0) + " " + Constant.getTodaysDate());
                clickOnSubmitPopup("Submit / Add");
                waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
                //Storing the MLA ID
                String idPath = driver.findElement(By.cssSelector("#q-app .q-page .q-item.col-auto:nth-child(1) .q-item__label:nth-child(2)")).getText();
                MasterHooks.searchMLAID.set(idPath);
                log.info("Master Agreement is created successfully");
                waitTillWebElementIsVisible("leaseArea",".form-input .q-field[aria-readonly='true']#lease-area");
                if (!(driver.findElements(By.cssSelector(".form-input .q-field[aria-readonly='true']#lease-area")).size() == 1)) {
                    Assert.fail("Master Agreement Lease Area field should be locked and Readable");
                } else {
                    log.info("Master Agreement Lease Area field is locked and Readable");
                }
                if (!(driver.findElements(By.cssSelector(".form-input .q-field#business-unit [aria-readonly='false']")).size() == 1)) {
                    Assert.fail("Master Agreement Business Unit field should be unlocked and Editable");
                } else {
                    log.info("Master Agreement Business Unit field is unlocked and Editable");
                }
                if (!(driver.findElements(By.cssSelector(".form-input .q-field[aria-readonly='true']#company-code")).size() == 1)) {
                    Assert.fail("Master Agreement Company code field should be locked and Readable");
                } else {
                    log.info("Master Agreement Company Code field is locked and Readable");
                }
                if (!(driver.findElements(By.cssSelector(".form-input .q-field#department [aria-readonly='false']")).size() == 1)) {
                    Assert.fail("Master Agreement Lease Department field should be unlocked and Editable");
                } else {
                    log.info("Master Agreement Lease Department field is unlocked and Editable");
                }
                if (!(driver.findElements(By.cssSelector(".form-input .q-field#lease-group [aria-readonly='false']")).size() == 1)) {
                    Assert.fail("Master Agreement Lease Group field should be unlocked and Editable");
                } else {
                    log.info("Master Agreement Lease Group field is unlocked and Editable");
                }
                break;
            case "Contract":
                try {
                    waitAndClickOnElement(contractDefinitionTab);
                    waitUntilLoadingSpinnerIsShown("nlaTabChange");
                    waitUntilLoadingSpinnerIsGone("nlaTabChange");
                    String ctPath = driver.findElement(By.cssSelector("#q-app .q-page .q-item.col-auto:nth-child(1) .q-item__label:nth-child(2)")).getText();
                    MasterHooks.searchCTID.set(ctPath);

//                    waitTillWebElementIsVisible("leaseArea", "#lease-area-input");
//                    waitTillWebElementIsVisible("currency", "#currency");
                    // Thread.sleep(1500);

                    if (!(driver.findElements(By.cssSelector(".form-input .q-field[aria-readonly='true']#currency")).size() == 1)) {
                        Assert.fail("Contract Currency field should be locked and Readable");
                    } else {
                        log.info("Contract Currency field is locked and Readable");
                    }
                    if (!(driver.findElements(By.cssSelector(".form-input .q-field[aria-readonly='true']#business-unit")).size() == 1)) {
                        Assert.fail("Contract Business Unit field should be locked and Readable and it is coming from MLA");
                    } else {
                        log.info("Contract Business Unit field is locked and Readable and it is coming from MLA");
                    }
                    if (!(driver.findElements(By.cssSelector(".form-input .q-field[aria-readonly='true']#company-code")).size() == 1)) {
                        Assert.fail("Contract Company Code field should be locked and Readable and it is coming from MLA");
                    } else {
                        log.info("Contract Company Code field is locked and Readable and it is coming from MLA");
                    }
                    if (!(driver.findElements(By.cssSelector(".form-input .q-field[aria-readonly='true']#department")).size() == 1)) {
                        Assert.fail("Contract Lease Department field should be locked and Readable and it is coming from MLA");
                    } else {
                        log.info("Contract Lease Department field is locked and Readable and it is coming from MLA");
                    }
                    if (!(driver.findElements(By.cssSelector(".form-input .q-field[aria-readonly='true']#lease-group")).size() == 1)) {
                        Assert.fail("Contract Lease Group field should be locked and Readable and it is coming from MLA");
                    } else {
                        log.info("Contract Lease Group field is locked and Readable and it is coming from MLA");
                    }

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "Lease Component":
                //                    waitAndClickOnElement(leaseComponentDefinitionTab);
//                    waitTillWebElementIsVisible("FMV/Unit", fmvValueDefinition);
                if (!(driver.findElements(By.cssSelector(".form-input .q-field#asset-class [aria-readonly='false']")).size() == 1)) {
                    Assert.fail("Lease Component Asset Class field should be unlocked and Editable");
                } else {
                    log.info("Lease Component Asset Class field is unlocked and Editable");
                }
                if (!(driver.findElements(By.cssSelector(".form-input .q-field[aria-readonly='true']#unit-of-measure")).size() == 1)) {
                    Assert.fail("Lease Component Unit of Measure field should be locked and Readable");
                } else {
                    log.info("Lease Component Unit of Measure field is locked and Readable");
                }
                break;
        }
    }

    public void createContractWithdfValue() {
        log.info("User is going to create a Contract with Default Value Configs");
        try {
            WaitUntilElementIsClickable(addContract);
            waitAndClickOnElement(addContract);
            waitUntilLoadingSpinnerIsGone("nlaFieldSpinner");
            WaitUntilElementIsClickable(contractName);
            //waitTillWebElementIsVisible("MasterAgreementValue",MasterAgreementValue);
            WaitUntilElementIsClickable(contractPrincipalPosition);
            Thread.sleep(300);
            clickOnDropDownAndSelectValue("contractPrincipalPosition", contractPrincipalPosition, getValuesFromExcel("Inception", "Contract Level", "Principal Position", 0));
            contractName.click();
            sendingValueToWebElement("contractName", contractName, getValuesFromExcel("Inception", "Contract Level", "Contract Name", 0) + " " + Constant.getTodaysDate());
            clickOnSubmitPopup("Submit / Add");
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void enterDataInLCDefinition() {
        log.info("User is going to add the data under LC Definition Page");
        try {
            waitTillWebElementIsVisible("fmvValueDefinition", fmvValueDefinition);
            String leaseType = getValuesFromExcel("Inception", "Contract Level", "Lease Type", 0);
            if (!(leaseType.equalsIgnoreCase("Lease Low Value") ||leaseType.equalsIgnoreCase("Lease Short Term")||
                    leaseType.equalsIgnoreCase("05 - Low Value Lease Contract") || leaseType.equalsIgnoreCase("04 - Short-Term Lease Contract"))) {
                WaitUntilElementIsClickable(fmvValueDefinition);
                fmvValueDefinition.click();
                sendingValueToWebElement("fmvValueDefinition", fmvValueDefinition, getValuesFromExcel("Inception", "Lease Component Level", "FMV / Unit", 0));

                waitAndClickOnElement(leaseComponentDefinitionTab);

                leaseComponentDefinitionTab.click();

                waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
                if (!getValuesFromExcel("Inception", "Contract Level", "Principal Position", 0).equalsIgnoreCase("Lessor")) {
                    WaitUntilElementIsClickable(salvageValueDefinition);
                    checkRequiredField("salvageUnitValue", ".q-page .absolute #lease-component-definition-general-info-form-expansion #residual-value-input");
                    salvageValueDefinition.click();
                    sendingValueToWebElement("salvageValueDefinition", salvageValueDefinition, getValuesFromExcel("Inception", "Lease Component Level", "Salvage Value / Unit", 0));
                    leaseComponentDefinitionTab.click();
                    waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                    waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
                }
            }
            if (driver.findElements(By.cssSelector(".q-page .absolute #lease-component-definition-general-info-form-expansion #gross-book-value")).size() == 1) {
                waitTillWebElementIsVisible("gbvUnitDefinition", gbvUnitDefinition);
                WaitUntilElementIsClickable(gbvUnitDefinition);
                gbvUnitDefinition.click();
                sendingValueToWebElement("gbvUnitDefinition", gbvUnitDefinition, getValuesFromExcel("Inception", "Lease Component Level", "GBV / Unit", 0));
                leaseComponentDefinitionTab.click();
                waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
            }

            //Master-0 Quantity
//        quantityValueDefinition.click();
//        sendingValueToWebElement("quantityValueDefinition", quantityValueDefinition,
//                getValuesFromExcel("Inception","Lease Component Level","Quantity",0));
//        leaseComponentDefinitionTab.click();
//        waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
//        waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
            if (!(getValuesFromExcel("Inception", "Contract Level", "Provisioning", 0).equalsIgnoreCase("No") || getValuesFromExcel("Inception", "Lease Component Level", "Provisioning Frequency", 0).equalsIgnoreCase("Straight-Line"))) {
                clickOnDropDownAndSelectValue("spreadingFrequencyDefinition", spreadingFrequencyDefinition, getValuesFromExcel("Inception", "Lease Component Level", "Provisioning Frequency", 0));
                waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
                waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
            }
        } catch (InterruptedException | IOException exception) {
            exception.printStackTrace();
        }
        rouStartDateValueDefinition.click();
        sendingValueToWebElement("rouStartDateValueDefinition", rouStartDateValueDefinition, getValuesFromExcel("Inception", "Lease Component Level", "ROU Start Date", 0));
        rouEndDateValueDefinition.click();
        waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
        waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        sendingValueToWebElement("rouEndDateValueDefinition", rouEndDateValueDefinition, getValuesFromExcel("Inception", "Lease Component Level", "ROU End Date", 0));
        leaseComponentDefinitionTab.click();
        waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
        waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        //Master-1 Quantity
        unitDistributionBtnDefination.click();
        waitUntilLoadingSpinnerIsShown("nlaDropDownPopUp");
        waitTillWebElementIsVisible("activationGroupNameField", ".q-dialog tbody tr input[aria-label='Activation Group Name *']");
        String[] AGNameLength = getValuesFromExcel("Inception", "Lease Component Level", "Activation Group Name", 0).split(",");
        String[] quantityUnitsLength = getValuesFromExcel("Inception", "Lease Component Level", "Quantity", 0).split(",");
        for (int agNameCount = 1; agNameCount <= AGNameLength.length; agNameCount++) {
            try {
                if (agNameCount != 1) {
                    waitAndClickOnElement(addNewUnitRowDefination);
                }
                WebElement activationGroupName = driver.findElement(By.cssSelector(".q-dialog tbody tr:nth-child(" + (agNameCount + 1) + ") input[aria-label='Activation Group Name *']"));
                waitForClickablility(".q-dialog tbody tr:nth-child(" + (agNameCount + 1) + ") input[aria-label='Activation Group Name *']");
                sendingValueToWebElement("activationGroupName", activationGroupName, AGNameLength[agNameCount - 1] + " " + Constant.getTodaysDate());
//				clickOnDropDownToTypeAndSelectValue("activationGroupName", activationGroupName, AGNameLength[agNameCount-1]);

                WebElement quantityUnits = driver.findElement(By.cssSelector(".q-dialog tbody tr:nth-child(" + (agNameCount + 1) + ") input[aria-label='Number of Units *']"));
                waitForClickablility(".q-dialog .q-card tr:nth-child(" + (agNameCount + 1) + ") input[aria-label='Number of Units *']");
                sendingValueToWebElement("quantityUnits", quantityUnits, quantityUnitsLength[agNameCount - 1]);
//				clickOnDropDownToTypeAndSelectValue("quantityUnits", quantityUnits, quantityUnitsLength[agNameCount-1]);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        clickOnSubmitPopup("Submit / Add");
        waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
        waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        //Storing the Lease Component ID
        String idPath = driver.findElement(By.cssSelector("#q-app .q-page .q-item.col-auto:nth-child(1) .q-item__label:nth-child(2)")).getText();
        MasterHooks.searchLCID.set(idPath);
        residualValueDefinition.click();
        sendingValueToWebElement("residualValueDefinition", residualValueDefinition, getValuesFromExcel("Inception", "Lease Component Level", "Guaranteed Residual Value", 0));
        // leaseComponentDefinitionTab.click();
        waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
        waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        if (driver.findElements(By.cssSelector(".q-page .absolute #unguaranteed-residual-value")).size() == 1) {
            waitTillWebElementIsVisible("UnguaranteedResidualValueDefinition", UnguaranteedResidualValueDefinition);
            UnguaranteedResidualValueDefinition.click();
            sendingValueToWebElement("UnguaranteedResidualValueDefinition", UnguaranteedResidualValueDefinition, getValuesFromExcel("Inception", "Lease Component Level", "Unguaranteed Residual Value", 0));
            leaseComponentDefinitionTab.click();
            waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
            waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
        }
        log.info("Completed filling the Definition of Lease Components");
    }
    public void searchRecordOnDefaultValuePage(DataTable dt){
        log.info("User is going to search the fields on Default Value Config Page");
        List<Map<String, String>> search = dt.asMaps(String.class, String.class);
        try {
            waitTillWebElementIsVisible("id", ".q-table .q-tr:nth-child(2) .q-td:nth-child(1)");
            int recordBeforeSearch = driver.findElements(By.cssSelector(".q-table .q-tr")).size();
            String ID = driver.findElement(By.cssSelector(".q-table .q-tr:nth-child(2) .q-td:nth-child(1)")).getText();
            waitAndClickOnElement(idSearch);
            sendingValueToWebElement("ID", idSearch, ID);
            waitForFilterResult(recordBeforeSearch);
            int recordAfterSearch=driver.findElements(By.cssSelector(".q-table .q-tr")).size();
            if(!(recordBeforeSearch >recordAfterSearch)){
                Assert.fail("User is not able to search the record");
            }
            else{
                log.info("User searched the record successfully");
            }
            int recordBeforeCancel=driver.findElements(By.cssSelector(".q-table .q-tr")).size();
            waitAndClickOnElement("cancelSearch", "button.q-icon");
            waitForFilterCancel(recordBeforeCancel);
            waitTillWebElementIsVisible("record", ".q-table .q-tr:nth-child(4)");
            if(!search.get(0).get("Target Field").equalsIgnoreCase("null")){
                recordBeforeSearch = driver.findElements(By.cssSelector(".q-table .q-tr")).size();
                clickOnDropDownAndSelectValue("targetField", targetFieldSearch, search.get(0).get("Target Field") );
                waitForFilterResult(recordBeforeSearch);
                recordAfterSearch=driver.findElements(By.cssSelector(".q-table .q-tr")).size();
                if(!(recordBeforeSearch >recordAfterSearch)){
                    Assert.fail("User is not able to search the record");
                }
                else{
                    log.info("User searched the record successfully");
                }
                waitTillWebElementIsVisible("cancelSearch", "button.q-icon");
                recordBeforeCancel=driver.findElements(By.cssSelector(".q-table .q-tr")).size();
                waitAndClickOnElement("cancelSearch", "button.q-icon");
                waitForFilterCancel(recordBeforeCancel);
                waitTillWebElementIsVisible("record", ".q-table .q-tr:nth-child(4)");
            }
            if(!search.get(0).get("Configuration Status").equalsIgnoreCase("null")){
                recordBeforeSearch = driver.findElements(By.cssSelector(".q-table .q-tr")).size();
                clickOnDropDownAndSelectValue("configurationStatus", configurationStatusSearch, search.get(0).get("Configuration Status"));
                //Thread.sleep(1000);
                //waitForWebElementToDisappear("searchedRecord", ".q-table .q-tr:nth-child(4)");
                waitForFilterResult(recordBeforeSearch);
                recordAfterSearch=driver.findElements(By.cssSelector(".q-table .q-tr")).size();
                if(!(recordBeforeSearch >recordAfterSearch)){
                    Assert.fail("User is not able to search the record");
                }
                else{
                    log.info("User searched the record successfully");
                }
                waitTillWebElementIsVisible("cancelSearch", "button.q-icon");
                recordBeforeCancel=driver.findElements(By.cssSelector(".q-table .q-tr")).size();
                waitAndClickOnElement("cancelSearch", "button.q-icon");
                waitForFilterCancel(recordBeforeCancel);
                waitTillWebElementIsVisible("record", ".q-table .q-tr:nth-child(4)");
            }

        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void waitForFilterResult(int recordBeforeSearch) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(WebDriverException.class);

        // Wait until the record count changes
        wait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                int recordAfterSearch = driver.findElements(By.cssSelector(".q-table .q-tr")).size();
                // If the record count has changed, return true
                return recordBeforeSearch > recordAfterSearch;
            }
        });

    }
    public void waitForFilterCancel(int recordBeforeCancel){
        Wait<WebDriver> cancelWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(WebDriverException.class);

        // Wait until the record count changes
        cancelWait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                int recordAfterCancel = driver.findElements(By.cssSelector(".q-table .q-tr")).size();
                // If the record count has changed, return true
                return recordBeforeCancel == recordAfterCancel;
            }
        });

    }

    public void validationForsameDefaultConfigs(DataTable dt) {
        log.info("User is going to validate validations for adding the same DF config");
        try {
            List<Map<String, String>> dvConfig = dt.asMaps(String.class, String.class);
            for (int i = 0; i < dvConfig.size(); i++) {
                Map<String, String> currentRow = dvConfig.get(i);
                waitTillWebElementIsVisible("addApplicationConfig", addApplicationConfig);
                waitAndClickOnElement(addApplicationConfig);

                waitTillWebElementIsVisible("formLocation", formLocation);
                if (!currentRow.get("Form Location").equalsIgnoreCase("null")) {
                    clickOnDropDownAndSelectValue("formLocationValue", formLocation, currentRow.get("Form Location"));
                }
                if (!currentRow.get("Target Field").equalsIgnoreCase("null")) {
                    clickOnDropDownAndSelectValue("targetFieldValue", targetField, currentRow.get("Target Field"));
                }
                if (currentRow.get("Configuration Status").equalsIgnoreCase("Active")) {
                    if (!(driver.findElements(By.cssSelector("[aria-checked='true']#status-enabled-radio")).size() == 1)) {
                        waitAndClickOnElement(activeStatus);
                    }
                } else {
                    if (driver.findElements(By.cssSelector("[aria-checked='false']#status-disabled-radio")).size() == 1) {
                        waitAndClickOnElement(inactiveStatus);

                    }
                }
                if (currentRow.get("Field Editability").equalsIgnoreCase("Locked")) {
                    if (!(driver.findElements(By.cssSelector("[aria-checked='true']#control-locked-radio")).size() == 1)) {
                        waitAndClickOnElement(lockedRadioBtn);
                    }
                } else {
                    if (driver.findElements(By.cssSelector("[aria-checked='false']#control-editable-radio")).size() == 1) {
                        waitAndClickOnElement(editableRadioBtn);

                    }
                }
                if (!currentRow.get("Condition1").equalsIgnoreCase("null")) {
                    clickOnDropDownToTypeAndSelectCheckBox("Condition1", condition1, currentRow.get("Condition1"));
                }
                if (!currentRow.get("Condition2").equalsIgnoreCase("null")) {
                    clickOnDropDownToTypeAndSelectCheckBox("Condition2", condition2, currentRow.get("Condition2"));
                }
                if (!currentRow.get("Default Values").equalsIgnoreCase("null")) {
                    clickOnDropDownToTypeAndSelectValue("defaultField", defaultValues, currentRow.get("Default Values"));
                }
                if (!(submitButton.isEnabled())) {
                    Assert.fail("Mandatory fields are missing");
                } else {
                    waitAndClickOnElement("submitButton", "#submit-btn");
                }
                    waitTillWebElementIsVisible("notification", ".desktop .q-notifications .q-notification[role='alert']");
                    if(driver.findElement(By.cssSelector(".desktop .q-notifications .q-notification[role='alert']")).getText().equalsIgnoreCase("success")){
                        Assert.fail("User is able to add same default value conflicting config");
                    }
                    else{
                        log.info("User is not able to add same default value conflicting config");
                    }
                    waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
                    waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
                    waitAndClickOnElement("cancelButton",".q-card #cancel-btn");
                }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}


