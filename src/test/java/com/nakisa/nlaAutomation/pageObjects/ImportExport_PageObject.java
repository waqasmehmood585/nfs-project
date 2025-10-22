package com.nakisa.nlaAutomation.pageObjects;

import com.nakisa.nlaAutomation.stepDefinitions.MasterHooks;
import io.cucumber.datatable.DataTable;
import lombok.extern.slf4j.Slf4j;
import org.apache.directory.api.util.Strings;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
public class ImportExport_PageObject extends Common_BasePage_PageObject {
    MasterAgreement_PageObject masterAgreementPageObject = masterAgreement_PageObject.get();

    private File massImportExcelFile;
    public static ThreadLocal<Integer> recordImportCount = new ThreadLocal<>();
    public static ThreadLocal<Integer> aGCreatedCount = new ThreadLocal<>();
    public static ThreadLocal<Integer> lCExistingTerms = new ThreadLocal<>();
    public static ThreadLocal<Integer> aGExistingCharge = new ThreadLocal<>();
    public static ThreadLocal<String> contactId = new ThreadLocal<>();
    public static ThreadLocal<Integer> totalExportedRecords = new ThreadLocal<>();
    public static ThreadLocal<Integer> landingPageRecords = new ThreadLocal<>();
    public @FindBy(css="#q-app .q-page .q-btn-group #add-btn") WebElement addJob;
    public @FindBy(css=".q-card .dialog-body .form-input #export-type") WebElement exportType;
    public @FindBy(css=".q-card .dialog-body .form-input #erp-system-selector") WebElement systemID;
    public @FindBy(css=".q-card .dialog-body .form-input #principal-position-selector") WebElement principalPosition;
    public @FindBy(css=".q-card .dialog-body #master-excel-sheet-type-selection") WebElement sheetCheckBoxSize;
    public @FindBy(css = ".q-card .form-input #activation-group-id")WebElement objectList;
    public @FindBy(css=".q-card__actions #export-btn") WebElement exportButton;
    public @FindBy(css = "#q-app .q-page .tasks-grid #refresh-btn") WebElement refreshButton;
    public @FindBy(css = "#q-app .q-page .jobs-grid #refresh-btn") WebElement refreshJobButton;
    public @FindBy(css = "#q-app .q-page .jobs-grid .q-table tbody tr:nth-child(2) #generate-btn") WebElement exportDownloadBtn;
    public @FindBy(css = "#q-app .q-page .q-tabs__content") WebElement landingPageTabs;
    public @FindBy(css = "#q-app .q-toolbar #contextMenu-menu-btn") WebElement massActionButton;
    public @FindBy(xpath = "//div[@role='listitem' and .//span[contains(text(), 'Export')]]") WebElement exportButtonLandingPage;
    public @FindBy(css = ".q-dialog .q-card #export-btn") WebElement exportButtonDialog;
    public @FindBy(css = ".q-dialog .q-card #export-cancel-btn") WebElement refreshJobButtonDialog;
    public @FindBy(css = ".q-dialog .q-card #export-close-btn") WebElement closeButtonDialog;
    public @FindBy(css = "#q-app .q-toolbar #user-btn") WebElement userBtn;
    public @FindBy(css = "[role='menu'] .q-item[role='listitem'] div.text-grey") WebElement userEmail;
    public @FindBy(css = "#q-app .jobs-grid tr td:nth-child(2) #search-and-filter-input") WebElement createdByFilter;
    //Mass Import
    public @FindBy(css=".dialog-body .col-6:nth-child(1) .q-checkbox__label") WebElement importSheet;
    public @FindBy(css=".q-card .dialog-body .form-input #import-types-selector") WebElement importTypeDropdown;
    public @FindBy(css=".q-dialog .q-card #use-mass-workflow-transition-checkbox") WebElement importAutoTransition;
    public @FindBy(css=".q-card .dialog-body .form-input #terminal-state-type-selector") WebElement finalState;
    public @FindBy (css = "input[type='file']") WebElement importUploadBtn;
    public @FindBy (css = ".tasks-grid #report-btn") WebElement reportBtn;
    public @FindBy (css = ".q-dialog .q-card .q-mt-md .q-table__bottom  .q-field__control") WebElement recordPerPageReport;
    public @FindBy (css = ".q-dialog .q-card .q-table__bottom  .q-field__control") WebElement recordPerPageSheet;

    public @FindBy(css = "#q-app .q-drawer .q-page .q-item #contract-selector") WebElement contractSelector;
    public @FindBy(css = "#q-app .q-drawer .q-page .q-item #lease-component-selector") WebElement leaseComponentSelector;
    public @FindBy(css = "#q-app .q-drawer .q-page .q-item #activation-group-selector") WebElement activationGroupSelector;
    public @FindBy(css = "#q-app #columns-visibility-expansion #excel-btn") WebElement excelImportLCBtn;
    public @FindBy(css = "#import-menu-btn #import-menu-refresh-btn") WebElement excelImportRefreshButton;
    public @FindBy(css = "#q-app .q-page #terms-conditions-step") WebElement termsConditionsTabLeaseComponent;
    public @FindBy(css = "#q-app #columns-visibility-expansion #refresh-btn") WebElement leaseComponentRefreshButton;
    public @FindBy(css = "#import-menu-btn #import-btn") WebElement lCImportButton;
    public @FindBy(css = ".q-menu .exportDropdown #export-btn") WebElement lCExportButton;
    public @FindBy(css = ".q-menu .exportDropdown #export-cancel-btn") WebElement lCExportRefreshButton;
    public @FindBy(css = ".q-menu .exportDropdown #export-menu-close-btn") WebElement lCExportCloseButton;
    public @FindBy(css = ".q-card .dialog-footer #download-template-btn") WebElement importTemplateButton;
    public @FindBy(css = ".q-menu #export-template-with-sample-data-toggle") WebElement sampleDataToggleButton;
    public @FindBy(css = ".q-menu .q-list #download-template-btn") WebElement downloadTemplateFileButton;
    public @FindBy(css = ".jobs-grid .q-table tbody tr:nth-child(2) .massWorkflowJobId div") WebElement massWorkflowJobId;
    public @FindBy(css = ".dialog-body .form-input #erp-system-filter-type") WebElement erpSystemFilter;
    public @FindBy(css = ".dialog-body .form-input #erp-systems") WebElement erpSystemValue;
    public @FindBy(css =".q-dialog  .qcard-dialogue .dialog-body .row.col-12 .col-3 #lease-areas")WebElement leaseAreaFilter;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body .row.col-12 .col-9 #lease-areas")WebElement leaseAreaFilterValue;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body .row.col-12 .col-3 #business-units")WebElement businessUnitFilter;
    public @FindBy(css = ".q-dialog  .qcard-dialogue .dialog-body .row.col-12 .col-9 #business-units")WebElement businessUnitFilterValues;
    public @FindBy (css = ".q-dialog  .qcard-dialogue .dialog-body .row.col-12 .col-3 #companies")WebElement companyFilter;
    public @FindBy (css = ".q-dialog  .qcard-dialogue .dialog-body .row.col-12 .col-9 #companies")WebElement companyFilterValue;
    public @FindBy (css = ".q-dialog  .qcard-dialogue .dialog-body #object-type-filter")WebElement objectTypeFilter;
    public @FindBy (css = ".q-dialog  .qcard-dialogue .dialog-body #object-type-list")WebElement objectTypeFilterValue;
    public @FindBy (css = ".q-dialog  .qcard-dialogue .dialog-body #migrated-filter-type")WebElement migratedFilterType;
    public @FindBy (css = ".q-dialog  .qcard-dialogue .dialog-body #from-date-input-input")WebElement fromYearScheduleExport;
    public @FindBy (css = ".q-dialog  .qcard-dialogue .dialog-body #to-date-input-input")WebElement toYearScheduleExport;
    public @FindBy(css = "#q-app .q-page .jobs-grid .q-table tbody tr:nth-child(2) #download-report-btn") WebElement importReportButton;
    public @FindBy(css = "#q-app .q-page .jobs-grid .q-table tbody tr:nth-child(2) #download-btn") WebElement importOriginalFile;
    public @FindBy(css = ".jobs-grid tbody tr:nth-child(2) .q-btn--actionable#mass-workflow-job-btn") WebElement importToMassWorkflowButton;

    public ImportExport_PageObject() {
        super();
        log.info("Driver is inside this class: " + this.getClass().getSimpleName());
        PageFactory.initElements(driver, this);
    }

    public void createExportJob(String entityType, String exportLevel) {
        log.info("Creating Export job for " + entityType);
        try {
            if(!(getSizeOfElements("#q-app .jobs-grid tr td:nth-child(2) .q-icon")==1)) {
                applyCreatedByFilter();
            }
            waitTillWebElementIsVisible("addExportJob", addJob);
            waitAndClickOnElement(addJob);
//            waitTillWebElementIsVisible("exportType",exportType);
            if(getSizeOfElements(".q-card .dialog-body")!=1){
                waitAndClickOnElement(cancelButton);
                waitTillWebElementIsVisible("addExportJob", addJob);
                waitAndClickOnElement(addJob);
            }
            handleWait(2000);
            waitTillWebElementIsVisible("exportType",exportType);
            clickOnDropDownAndSelectValue("exportType", exportType, entityType);
//            if(entityType.equalsIgnoreCase("Master Agreement") || entityType.equalsIgnoreCase("Contract") || entityType.equalsIgnoreCase("Lease Component") ||
//                    entityType.equalsIgnoreCase("Activation Group")){
//                waitTillWebElementIsVisible("systemID",systemID);
//                clickOnDropDownAndSelectValue("systemID",systemID, getValuesFromExcel(exportLevel).get("eventdata").get("System").get(0));
//            }
            if(!(entityType.equalsIgnoreCase("Master Agreement") || entityType.equalsIgnoreCase("Contact"))){
                if(!(getValuesFromExcel(exportLevel, "eventdata", "Principal Position",0).equalsIgnoreCase("Lessee+Lessor"))){
                    if(getValuesFromExcel(exportLevel,"eventdata","Principal Position",0).equalsIgnoreCase("Lessee")) {
                        if(getValueFromElement(".q-card .dialog-body .q-chip:nth-child(1) .ellipsis").equalsIgnoreCase("Lessee")){
                            waitAndClickOnElement("principlePosition",".q-card .dialog-body .q-chip:nth-child(2) [aria-label='Remove']");
                        }
                        else{
                            waitAndClickOnElement("principlePosition",".q-card .dialog-body .q-chip:nth-child(1) [aria-label='Remove']");
                        }
//                        clickOnDropDownAndSelectCheckBoxes("principalPosition",principalPosition,"Lessor");
                    }
//                    else{
//                        clickOnDropDownAndSelectCheckBoxes("principalPosition",principalPosition,"Lessee");
//                    }
                    waitAndClickOnElement("exportDataPopUp",".q-dialog .q-card .dialog-header");
                }
            }
            if (!(entityType.equalsIgnoreCase("Contact"))) {
                waitTillWebElementIsVisible("migratedFilterType", migratedFilterType);
                clickOnDropDownAndSelectValue("migratedFilterType", migratedFilterType,
                    getValuesFromExcel(exportLevel, "eventdata", "Migrated Filter Type",0));
            }
            if(entityType.equalsIgnoreCase("Schedule")){
                waitTillWebElementIsVisible("fromYear",fromYearScheduleExport);
                fromYearScheduleExport.click();
                sendingValueToWebElement("fromYear",fromYearScheduleExport,getValuesFromExcel(exportLevel,"eventdata","From Year",0));
                toYearScheduleExport.click();
                sendingValueToWebElement("toYear",toYearScheduleExport,getValuesFromExcel(exportLevel, "eventdata", "To Year", 0));
                waitAndClickOnElement("exportDataPopUp",".q-dialog .q-card .dialog-header");
            }

            if (!entityType.equalsIgnoreCase("Contact")){
                if (!getValuesFromExcel(exportLevel, "eventdata", "ERP System Filter", 0).equalsIgnoreCase("All")) {
                    clickOnDropDownAndSelectValue("erpSystemFilter", erpSystemFilter, "List");
                    waitTillWebElementIsVisible("erpSystemValue", erpSystemValue);
                    clickOnDropDownToTypeAndSelectValue("erpSystemValue", erpSystemValue, MasterHooks.configurationProperties.get().getErpSystem());
                    waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                    waitForWebElementToDisappear("dropDownOptions", ".q-menu");
                }
                if (!getValuesFromExcel(exportLevel,"eventdata","Lease Area Filter",0).equalsIgnoreCase("All")) {
                    clickOnDropDownAndSelectValue("leaseAreaFilter", leaseAreaFilter, "List");
                    waitTillWebElementIsVisible("leaseAreaFieldValue", leaseAreaFilterValue);
                    clickOnDropDownToTypeAndSelectCheckBox("leaseAreaValue", leaseAreaFilterValue,
                        getValuesFromExcel(exportLevel,"eventdata","Lease Area",0));
                    waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                    waitForWebElementToDisappear("dropDownOptions", ".q-menu");
                }
                if (!getValuesFromExcel(exportLevel,"eventdata","Business Unit Filter",0).equalsIgnoreCase("All")) {
                    clickOnDropDownAndSelectValue("businessUnitFilter", businessUnitFilter, "List");
                    waitTillWebElementIsVisible("businessUnitFilterValue", businessUnitFilterValues);
                    clickOnDropDownToTypeAndSelectCheckBox("businessUnitFilterValue", businessUnitFilterValues,
                        getValuesFromExcel(exportLevel,"eventdata","Business Unit",0));
                    waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                    waitForWebElementToDisappear("dropDownOptions", ".q-menu");
                }
                if (!getValuesFromExcel(exportLevel,"eventdata","Company Filter",0).equalsIgnoreCase("All")) {
                    clickOnDropDownAndSelectValue("companyFilter", companyFilter, "List");
                    waitTillWebElementIsVisible("companyValue", companyFilterValue);
                    clickOnDropDownToTypeAndSelectCheckBox("companyValue", companyFilterValue,
                        getValuesFromExcel(exportLevel,"eventdata","Company Filter",0));
                    waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                    waitForWebElementToDisappear("dropDownOptions", ".q-menu");
                }
                if (!getValuesFromExcel(exportLevel,"eventdata","Object Type Filter",0).equalsIgnoreCase("null")) {
                    String ID = null;
                    switch (entityType) {
                        case "Master Agreement":
                            ID = MasterHooks.searchMLAID.get();
                            break;
                        case "Contract":
                            ID = MasterHooks.searchCTID.get();
                            break;
                        case "Lease Component":
                            ID = MasterHooks.searchLCID.get();
                            break;
                        case "Activation Group":
                        case "Charge":
                        case "Schedule":
                            ID = MasterHooks.searchAGID.get();
                            break;
                        case "Unit":
                            ID = MasterHooks.searchUNID.get();
                            break;
                    }
                    waitTillWebElementIsVisible("ObjectTypeFilter", objectTypeFilter);
                    if(entityType.equalsIgnoreCase("Charge") || entityType.equalsIgnoreCase("Schedule")){
                        clickOnDropDownAndSelectValue("objectTypeFilter",objectTypeFilter,"Activation Group");
                    }else {
                        clickOnDropDownAndSelectValue("objectTypeFilter", objectTypeFilter, entityType);
                    }
                    waitTillWebElementIsEnabled("objectListValue",".q-dialog  .qcard-dialogue .dialog-body #object-type-list");
                    clickOnDropDownToTypeAndSelectCheckBox("objectListValue", objectTypeFilterValue, ID);
                    waitAndClickOnElement("popupHeader", ".desktop .q-dialog .q-card .dialog-header");
                }

            }

            if(entityType.equalsIgnoreCase("Master Agreement")){
                if(getValuesFromExcel(exportLevel,"eventdata","Master Agreement",0).equalsIgnoreCase("No")){
                    waitAndClickOnElement("masterAgreementCheckbox",".q-card .dialog-body [aria-label='Master Agreement']");
                }
                else if (getValuesFromExcel(exportLevel,"eventdata","MLA Partner",0).equalsIgnoreCase("No")) {
                    waitAndClickOnElement("mLAPartnerCheckbox",".q-card .dialog-body [aria-label='MLA Partner']");
                }
                else if (getValuesFromExcel(exportLevel,"eventdata","MLA Partner Contact",0).equalsIgnoreCase("No")) {
                    waitAndClickOnElement("mLAPartnerContactCheckbox",".q-card .dialog-body [aria-label='MLA Partner Contact']");
                }
            }
            if (entityType.equalsIgnoreCase("Contract")) {
                if (getValuesFromExcel(exportLevel,"eventdata","Contract",0).equalsIgnoreCase("No")) {
                    waitAndClickOnElement("contractCheckbox",".q-card .dialog-body [aria-label='Contract']");
                }
                if(getValuesFromExcel(exportLevel,"eventdata","Principal Position",0).contains("Lessee")){
                    if (getValuesFromExcel(exportLevel,"eventdata","LSE Lease Determination",0).equalsIgnoreCase("No")) {
                        waitAndClickOnElement("LSELeaseDetermination",".q-card .dialog-body [aria-label='LSE Lease Determination']");
                    }
                    else if (getValuesFromExcel(exportLevel,"eventdata","LSE Contract Accounting",0).equalsIgnoreCase("No")) {
                        waitAndClickOnElement("LSEContractAccounting",".q-card .dialog-body [aria-label='LSE Contract Accounting']");
                    }
                } else if (getValuesFromExcel(exportLevel,"eventdata","Principal Position",0).contains("Lessor")) {
                    if (getValuesFromExcel(exportLevel,"eventdata","LSR Lease Determination",0).equalsIgnoreCase("No")) {
                        waitAndClickOnElement("LSRLeaseDetermination",".q-card .dialog-body [aria-label='LSR Lease Determination']");
                    }
                    else if (getValuesFromExcel(exportLevel,"eventdata","LSR Contract Accounting",0).equalsIgnoreCase("No")) {
                        waitAndClickOnElement("LSRContractAccounting",".q-card .dialog-body [aria-label='LSR Contract Accounting']");
                    }
                }
                else if (getValuesFromExcel(exportLevel,"eventdata","Contract Partner",0).equalsIgnoreCase("No")) {
                    waitAndClickOnElement("ContractPartner",".q-card .dialog-body [aria-label='Contract Partner']");
                }
                else if (getValuesFromExcel(exportLevel,"eventdata","Contract Partner Contact",0).equalsIgnoreCase("No")) {
                    waitAndClickOnElement("ContractPartnerContact",".q-card .dialog-body [aria-label='Contract Partner Contact']");
                }
            }
            if (entityType.equalsIgnoreCase("Lease Component")) {
                if(getValuesFromExcel(exportLevel,"eventdata","Principal Position",0).contains("Lessee")){
                    if (getValuesFromExcel(exportLevel,"eventdata","LSE Lease Component",0).equalsIgnoreCase("No")) {
                        waitAndClickOnElement("LSELeaseComponent",".q-card .dialog-body [aria-label='LSE Lease Component']");
                    }
                    if (getValuesFromExcel(exportLevel,"eventdata","LSE LC Carry Over Balances",0).equalsIgnoreCase("No")) {
                        waitAndClickOnElement("LSELeaseDetermination",".q-card .dialog-body [aria-label='LSE LC Carry Over Balances']");
                    }
                } else if (getValuesFromExcel(exportLevel,"eventdata","Principal Position",0).contains("Lessor")) {
                    if (getValuesFromExcel(exportLevel,"eventdata","LSR Lease Component",0).equalsIgnoreCase("No")) {
                        waitAndClickOnElement("LSRLeaseComponent",".q-card .dialog-body [aria-label='LSR Lease Component']");
                    }
                    if (getValuesFromExcel(exportLevel,"eventdata","LSR LC Carry Over Balances",0).equalsIgnoreCase("No")) {
                        waitAndClickOnElement("LSRLCCarryOverBalances",".q-card .dialog-body [aria-label='LSR LC Carry Over Balances']");
                    }
                }
                else if (getValuesFromExcel(exportLevel,"eventdata","LC T&C",0).equalsIgnoreCase("No")) {
                    waitAndClickOnElement("LCT&C",".q-card .dialog-body [aria-label='LC T&C']");
                }
            }
            if (entityType.equalsIgnoreCase("Activation Group")) {
                if (getValuesFromExcel(exportLevel,"eventdata","AG Definition",0).equalsIgnoreCase("No")) {
                    waitAndClickOnElement("AGDefinition",".q-card .dialog-body [aria-label='AG Definition']");
                }
                if(getValuesFromExcel(exportLevel,"eventdata","Principal Position",0).contains("Lessee")){
                    if (getValuesFromExcel(exportLevel,"eventdata","LSE AG Accounting",0).equalsIgnoreCase("No")) {
                        waitAndClickOnElement("LSEAGAccounting",".q-card .dialog-body [aria-label='LSE AG Accounting']");
                    }
                    else if (getValuesFromExcel(exportLevel,"eventdata","LSE AG Classification",0).equalsIgnoreCase("No")) {
                        waitAndClickOnElement("LSEAGClassification",".q-card .dialog-body [aria-label='AG T&C']");
                    }
                } else if (getValuesFromExcel(exportLevel,"eventdata","Principal Position",0).contains("Lessor")) {
                    if (getValuesFromExcel(exportLevel,"eventdata","LSR AG Accounting",0).equalsIgnoreCase("No")) {
                        waitAndClickOnElement("LSRAGAccounting", ".q-card .dialog-body [aria-label='LSR AG Accounting']");
                    } else if (getValuesFromExcel(exportLevel,"eventdata","LSR AG Classification",0).equalsIgnoreCase("No")) {
                        waitAndClickOnElement("LSRAGClassification", ".q-card .dialog-body [aria-label='LSR AG Classification']");
                    }
                }
                if (getValuesFromExcel(exportLevel,"eventdata","AG T&C",0).equalsIgnoreCase("No")) {
                    waitAndClickOnElement("AGT&C", ".q-card .dialog-body [aria-label='AG T&C']");
                }
            }
            if (entityType.equalsIgnoreCase("Unit")) {
//                if(getValuesFromExcel(exportLevel,"eventdata","Activation Group ID",0).equalsIgnoreCase("-"))
//                waitTillWebElementIsVisible("objectList", objectList);
//                clickOnDropDownToTypeAndSelectCheckBox("objectList", objectList, MasterHooks.searchAGID.get());
                if (getValuesFromExcel(exportLevel,"eventdata","Unit",0).equalsIgnoreCase("No")) {
                    waitAndClickOnElement("Unit", ".q-card .dialog-body [aria-label='Unit']");
                } else if (getValuesFromExcel(exportLevel,"eventdata","Unit Cost Center Allocation",0).equalsIgnoreCase("No")) {
                    waitAndClickOnElement("UnitCostCenterAllocation", ".q-card .dialog-body [aria-label='Unit Cost Center Allocation']");
                }

            }
            if (entityType.equalsIgnoreCase("Charge")) {
//                waitTillWebElementIsVisible("objectList", objectList);
//                clickOnDropDownToTypeAndSelectCheckBox("objectList", objectList, MasterHooks.searchAGID.get());
                if (getValuesFromExcel(exportLevel,"eventdata","Charge",0).equalsIgnoreCase("No")) {
                    waitAndClickOnElement("Charge", ".q-card .dialog-body [aria-label='Charge']");
                } else if (getValuesFromExcel(exportLevel,"eventdata","Vendor Payment Split",0).equalsIgnoreCase("No")) {
                    waitAndClickOnElement("VendorPaymentSlip", ".q-card .dialog-body [aria-label='Vendor Payment Split']");
                }

            }
            if (entityType.equalsIgnoreCase("Contact")) {
                log.info("Contact is selected as Export Type");
            }
            if (entityType.equalsIgnoreCase("Schedule")) {
//                waitTillWebElementIsVisible("objectList", objectList);
//                clickOnDropDownToTypeAndSelectCheckBox("objectList", objectList, MasterHooks.searchAGID.get());
                if (getValuesFromExcel(exportLevel,"eventdata","Principal Position",0).contains("Lessee")) {
                    if (getValuesFromExcel(exportLevel,"eventdata","LSE Schedule",0).equalsIgnoreCase("No")) {
                        waitAndClickOnElement("LSESchedule", ".q-card .dialog-body [aria-label='LSE Schedule']");
                    } else if (getValuesFromExcel(exportLevel,"eventdata","LSE All Columns Schedule",0).equalsIgnoreCase("No")) {
                        waitAndClickOnElement("LSEAllColumnsSchedule", ".q-card .dialog-body [aria-label='LSE All Columns Schedule]");
                    } else if (getValuesFromExcel(exportLevel,"eventdata","LSE Liability Schedule",0).equalsIgnoreCase("No")) {
                        waitAndClickOnElement("LSELiabilitySchedule", ".q-card .dialog-body [aria-label='LSE Liability Schedule']");
                    } else if (getValuesFromExcel(exportLevel,"eventdata","LSE Payment Schedule",0).equalsIgnoreCase("No")) {
                        waitAndClickOnElement("LSEPaymentSchedule", ".q-card .dialog-body [aria-label='LSE Payment Schedule']");
                    } else if (getValuesFromExcel(exportLevel,"eventdata","LSE Asset Transition Schedule",0).equalsIgnoreCase("No")) {
                        waitAndClickOnElement("LSEAssetTransitionSchedule", ".q-card .dialog-body [aria-label='LSE Asset Transition Schedule']");
                    }
                } else if (getValuesFromExcel(exportLevel,"eventdata","Principal Position",0).contains("Lessor")) {
                    if (getValuesFromExcel(exportLevel,"eventdata","LSR Schedule",0).equalsIgnoreCase("No")) {
                        waitAndClickOnElement("LSRSchedule", ".q-card .dialog-body [aria-label='LSR Schedule']");
                    } else if (getValuesFromExcel(exportLevel,"eventdata","LSR All Columns Schedule",0).equalsIgnoreCase("No")) {
                        waitAndClickOnElement("LSRAllColumnsSchedule", ".q-card .dialog-body [aria-label='LSR All Columns Schedule']");
                    } else if (getValuesFromExcel(exportLevel,"eventdata","LSR Liability Schedule",0).equalsIgnoreCase("No")) {
                        waitAndClickOnElement("LSRLiabilitySchedule", ".q-card .dialog-body [aria-label='LSR Liability Schedule']");
                    } else if (getValuesFromExcel(exportLevel,"eventdata","LSR Payment Schedule",0).equalsIgnoreCase("No")) {
                        waitAndClickOnElement("LSRPaymentSchedule", ".q-card .dialog-body [aria-label='LSR Payment Schedule']");
                    } else if (getValuesFromExcel(exportLevel,"eventdata","LSR Asset Transition Schedule",0).equalsIgnoreCase("No")) {
                        waitAndClickOnElement("LSRAssetTransitionSchedule", ".q-card .dialog-body [aria-label='LSR Asset Transition Schedule']");
                    }
                }
            }
            waitAndClickOnElement("popUpHeader", ".q-dialog  .q-card .q-card__section .text-h6.col");
            waitTillWebElementIsVisible("exportButton",exportButton);
            waitAndClickOnElement(exportButton);
            waitTillWebElementIsVisible("refreshButton",refreshButton);
            waitAndClickOnElement(refreshButton);
            handleWait(2000);
            int jobsSize = getSizeOfElements("#q-app .q-page .tasks-grid .q-table tr [role='status']");
            System.out.println("Export job size for "+entityType+" is: "+jobsSize);
            totalExportedRecords.set(0);
            for(int index=0;index<jobsSize;index++) {
                checkJobCompletion("Mass Export","Done",index);
            }
            waitAndClickOnElement(refreshJobButton);
            waitUntilLoadingSpinnerIsShown("nlaListLoader");
            waitUntilLoadingSpinnerIsGone("nlaListLoader");
            if (!driver.findElement(By.cssSelector(".jobs-grid .q-table tbody tr:nth-child(2) .status .q-badge[role='status']")) .getText().equalsIgnoreCase("Done")){
                waitAndClickOnElement("firstJobSelected","#q-app .q-page .jobs-grid .q-table tbody tr.selected-job-row");
                waitAndClickOnElement(refreshJobButton);
                waitUntilLoadingSpinnerIsShown("nlaListLoader");
                waitUntilLoadingSpinnerIsGone("nlaListLoader");
            }
            if (driver.findElement(By.cssSelector(".jobs-grid .q-table tbody tr:nth-child(2) [role='status']")) .getText().equalsIgnoreCase("Failed")){
                Assert.fail("Export Job failed for "+ entityType+ " Job");
            }
            log.info("Refresh left button is clicked");
            log.info("Export Job for "+entityType+" is Done Successfully");
            //Downloading Export File
            downloadingFile("Mass Export");
            //Validating Data in Export File
            exportFileValidation(entityType, "Mass Export");
        }catch (InterruptedException | IOException exception) {
            exception.printStackTrace();
        }


    }
    public void checkJobCompletion(String jobType, String jobStatusToCheck,int jobNumber) {
        log.info("Checking the Jobs Completed Successfully");

        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(1800)).pollingEvery(Duration.ofMillis(15000)).ignoring(WebDriverException.class);
            wait.until(new Function<WebDriver, Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    try {
                        if(jobType.equalsIgnoreCase("Mass Export")) {
                            waitAndClickOnElement(refreshButton);
                            waitUntilLoadingSpinnerIsShown("nlaListLoader");
                            waitUntilLoadingSpinnerIsGone("nlaListLoader");
                            List<WebElement> export = getElements(".tasks-grid .q-table tr [role='status']");
                            List<WebElement> exportData = getElements(".tasks-grid .q-table .q-tr--no-hover .q-td:nth-child(5)");
                            if (export.get(jobNumber).getText().equalsIgnoreCase(jobStatusToCheck)) {
                                if(exportData.get(jobNumber).getText().contains(",")) {
                                    totalExportedRecords.set(totalExportedRecords.get() + Integer.parseInt(exportData.get(jobNumber).getText().replaceAll(",", "")));
                                } else if(exportData.get(jobNumber).getText().contains(" ")) {
                                    totalExportedRecords.set(totalExportedRecords.get() + Integer.parseInt(exportData.get(jobNumber).getText().replaceAll(" ", "")));
                                } else{
                                    totalExportedRecords.set(totalExportedRecords.get() + Integer.parseInt(exportData.get(jobNumber).getText()));
                                }
                                return true;
                            }else if (export.get(jobNumber).getText().equalsIgnoreCase("Failed")) {
                                Assert.fail("The Mass Export Job: "+jobNumber+ " is Failed");
                            }
                        } else if (jobType.equalsIgnoreCase("Landing Page Export")) {
                            waitAndClickOnElement(refreshJobButtonDialog);
//                            .q-card  .q-item[role='listitem'] [role='status']
                            List<WebElement> export = getElements(".q-dialog .q-card [role='progressbar'] .flex.inline");
                            if (export.get(jobNumber).getText().equalsIgnoreCase(jobStatusToCheck)) {
                                return true;
                            }else if (export.get(jobNumber).getText().equalsIgnoreCase("Failed")) {
                                Assert.fail("The Landing Page Mass Export Job: "+jobNumber+ " is Failed");
                            }
                        }
                        else if (jobType.equalsIgnoreCase("Lease Component Terms Export")) {
                            waitAndClickOnElement(lCExportRefreshButton);
                            List<WebElement> export = getElements(".q-menu .exportDropdown [role='progressbar'] .flex.inline");
                            if (export.get(jobNumber).getText().equalsIgnoreCase(jobStatusToCheck)) {
                                return true;
                            }else if (export.get(jobNumber).getText().equalsIgnoreCase("Failed")) {
                                Assert.fail("The Lease Component Export Job: "+jobNumber+ " is Failed");
                            }
                        }
                        else if(jobType.equalsIgnoreCase("Mass Import")){
                            waitAndClickOnElement(refreshButton);
                            waitUntilLoadingSpinnerIsShown("nlaListLoader");
                            waitUntilLoadingSpinnerIsGone("nlaListLoader");
                            List<WebElement> importStatus= getElements(".tasks-grid .q-table tr [role='status']");
                            if(importStatus.get(jobNumber-2).getText().equalsIgnoreCase(jobStatusToCheck)){
                                log.info("Job Number: "+ (jobNumber-1)+" Completed Successfully, Refreshing Task Section");
                                waitAndClickOnElement(refreshJobButton);
                                waitUntilLoadingSpinnerIsShown("nlaListLoader");
                                waitUntilLoadingSpinnerIsGone("nlaListLoader");
                                if(getSizeOfElements(".jobs-grid tbody tr:nth-child(2) td .status-badge .q-icon")==1){
                                    Assert.fail("The Mass Import Job: "+(jobNumber-1)+ " is Failed with error "+
                                            "'The import was done with errors/warning, please check report'");
                                }
                                return true;
                            } else if (importStatus.get(jobNumber-2).getText().equalsIgnoreCase("Failed")) {
                                Assert.fail("The Mass Import Job: "+(jobNumber-1)+ " is Failed");
                            }
                        }
                        else if(jobType.equalsIgnoreCase("Mass Import With Errors")) {
                            waitAndClickOnElement(refreshButton);
                            waitUntilLoadingSpinnerIsShown("nlaListLoader");
                            waitUntilLoadingSpinnerIsGone("nlaListLoader");
                            List<WebElement> importStatus= getElements(".tasks-grid .q-table tr [role='status']");
                            if(importStatus.get(jobNumber-2).getText().equalsIgnoreCase(jobStatusToCheck)){
                                log.info("Job Number: "+ (jobNumber-1)+" Completed Successfully, Refreshing Task Section");
                                waitAndClickOnElement(refreshJobButton);
                                waitUntilLoadingSpinnerIsShown("nlaListLoader");
                                waitUntilLoadingSpinnerIsGone("nlaListLoader");
                                return true;
                            } else if (importStatus.get(jobNumber-2).getText().equalsIgnoreCase("Failed")) {
                                Assert.fail("The Mass Import Job: "+(jobNumber-1)+ " is Failed");
                            }
                        }else if (jobType.equalsIgnoreCase("LC Import")) {
                            waitAndClickOnElement(excelImportRefreshButton);
                            List<WebElement> importLC = getElements("#import-menu-btn [role='progressbar'] .flex.inline");
                            if (importLC.get(jobNumber).getText().equalsIgnoreCase(jobStatusToCheck)) {
                                return true;
                            }else if (importLC.get(jobNumber).getText().equalsIgnoreCase("Failed")) {
                                Assert.fail("The LC Import Job: "+jobNumber+ " is Failed");
                            }
                        }
                        handleWait(200);
                        log.info("Job Number: " + jobNumber +" In Progress");
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return false;
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Job Completed Successfully");

    }

    public void downloadingFile(String jobType){
        log.info("Downloading "+ jobType +" File");
        try {
            File folder=new File(MasterHooks.downloadedExcelFilePath.get());
            File [] listOfFilesBeforeDown=folder.listFiles();
            int sizeOfFileBeforeDown=listOfFilesBeforeDown.length;
            System.out.println("The list of files before Excel download are " + sizeOfFileBeforeDown);

            switch(jobType) {
                case "Mass Export":
                    waitTillWebElementIsVisible("exportDownloadButton", exportDownloadBtn);
                    waitAndClickOnElement(exportDownloadBtn);
                    waitUntilLoadingSpinnerIsShown("downloadLoader");
                    waitUntilLoadingSpinnerIsGone("downloadLoader");
                    break;
                case "Landing Page Export":
                    List<WebElement> downloadButton = getElements(".q-dialog .q-card #generate-btn");
                    waitAndClickOnElement(downloadButton.get(0));
                    break;
                case "Import Template":
                    waitTillWebElementIsVisible("importTemplateDownload", downloadTemplateFileButton);
                    waitAndClickOnElement(downloadTemplateFileButton);
                    break;
                case "Lease Component Terms Export":
                    List<WebElement> downloadExportTermButton = getElements(".q-menu .exportDropdown #generate-btn");
                    waitAndClickOnElement(downloadExportTermButton.get(0));
                    break;
                case "Import Report":
                    waitTillWebElementIsVisible("importReportButton", importReportButton);
                    waitAndClickOnElement(importReportButton);
                    break;
                case "Original Import File":
                    waitTillWebElementIsVisible("importOriginalFile", importOriginalFile);
                    waitAndClickOnElement(importOriginalFile);
                    break;
            }

            if(MasterHooks.configurationProperties.get().getRunEnvironment().equalsIgnoreCase("remote")) {
                download_file_selenium_grid(folder);
            }
            Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(300)).pollingEvery(Duration.ofMillis(5000)).ignoring(WebDriverException.class);
            wait1.until(new Function<WebDriver, Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    File [] listOfFilesAfterDown=folder.listFiles();
                    int sizeAfterDown=listOfFilesAfterDown.length;
                    if(sizeAfterDown!=sizeOfFileBeforeDown){
                        log.info("File downloaded & available in folder");
                        return true;
                    }
                    log.info("File is not downloaded Yet");
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void exportFileValidation(String entityType, String jobType) {
        log.info("Checking the data in Exported File");
        int allSheetsRecord=0;
        XSSFSheet mySheet = null;
        try {
//            if (jobType.equalsIgnoreCase("Mass Export") && !(entityType.equalsIgnoreCase("Contact"))) {
//                if (totalExportedRecords.get() > landingPageRecords.get()) {
//                    Assert.fail(entityType + ": Mass Exported Records '" +totalExportedRecords.get()  + "' are Greater than Landing Page Records '" + landingPageRecords.get() +"'");
//                }
//                log.info(entityType + ": Mass Exported Records '" +totalExportedRecords.get()  + "' are Less/Equal to Landing Page Records '" + landingPageRecords.get() +"'");
//            }
            File folder = new File(MasterHooks.downloadedExcelFilePath.get());
            File[] downloadedFiles = folder.listFiles();
            String downloadedFilePath = downloadedFiles[0].getAbsolutePath();
            File exportFile = new File(downloadedFilePath);
            FileInputStream file = new FileInputStream(exportFile);
            XSSFWorkbook myWorkbook = new XSSFWorkbook(file);
            int totalSheets = myWorkbook.getNumberOfSheets()-1;
            log.info("This File has "+totalSheets+" sheets");

            if(jobType.equalsIgnoreCase("Mass Export")) {
                for (int i = 0; i <= totalSheets; i++) {
                    mySheet = myWorkbook.getSheetAt(i);
                    if (!myWorkbook.getSheetName(i).equalsIgnoreCase("List Options")) {
                        int rowCount = mySheet.getPhysicalNumberOfRows() - 1;// Get the actual row count
                        allSheetsRecord = allSheetsRecord + rowCount;
                    }
                }
            }else {
                if(entityType.equalsIgnoreCase("Contract")){
                    mySheet = myWorkbook.getSheet("CT - LSE Definition");
                }else {
                    mySheet = myWorkbook.getSheetAt(0);
                }
                if (!myWorkbook.getSheetName(0).equalsIgnoreCase("List Options")) {
                    int rowCount = mySheet.getPhysicalNumberOfRows() - 1;// Get the actual row count
                    allSheetsRecord = allSheetsRecord + rowCount;
                }
            }

            if(jobType.equalsIgnoreCase("Mass Export")){
                if (totalExportedRecords.get() != allSheetsRecord) {
                    Assert.fail("Total Mass Exported Records (" + totalExportedRecords.get() + ") of " + entityType + " are not matching with the Number of Records in the Downloaded File (" + allSheetsRecord + ")");
                }
                log.info(" ** Total Mass Exported Records (" + totalExportedRecords.get() + ") of '" + entityType + "' are matching with the Number of Records in the Downloaded File (" + allSheetsRecord + ") **");
            } else if(jobType.equalsIgnoreCase("Landing Page Export")){
                if (5 != allSheetsRecord) {
                    Assert.fail("Total Landing Page Exported Records (5) of '" + entityType + "' are not matching with the Number of Records in the Downloaded File (" + allSheetsRecord + ")");
                }
                log.info(" ** Total Landing Page Exported Records (5) of '"+ entityType + "' are matching with the Number of Records in the Downloaded File (" + allSheetsRecord + ") **");
            }
            else if(jobType.equalsIgnoreCase("Lease Component Terms Export")){
                int totalNumberOfTerms = masterAgreementPageObject.getInputValues().get("Inception").get("Lease Component Level").get("Term Category").size();
                if(totalNumberOfTerms!=allSheetsRecord){
                    Assert.fail("Total Terms ("+ (totalNumberOfTerms) +") at Lease Component are not matching with the Number of Terms ("+allSheetsRecord+") in the Downloaded File");
                }
                log.info(" ** Total Terms ("+ (totalNumberOfTerms) +") at Lease Component are matching with the Number of Terms ("+allSheetsRecord+") in the Downloaded File **");
            }
            myWorkbook.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void landingPage(String entityType){
        log.info("User is on "+entityType+" landing page");
        String entity = null;
        try {
            waitUntilLoadingSpinnerIsShown("nlaLoadingPage");
            waitUntilLoadingSpinnerIsGone("nlaLoadingPage");
            waitTillWebElementIsVisible("landingPageTabs",landingPageTabs);
            waitAndClickOnElement(entityType,"#q-app .q-page .q-tabs__content #"+entityType);
            if(!entityType.equalsIgnoreCase("contract-tab")) {
                waitUntilLoadingSpinnerIsShown("nlaLoadingPage");
                waitUntilLoadingSpinnerIsGone("nlaLoadingPage");
            }
            waitTillWebElementIsVisible("pageTitle","#q-app .q-page .page-title");
            switch (entityType) {
                case "master-agreement-tab":
                    entity = "Master Agreements";
                    break;
                case "contract-tab":
                    entity = "Contracts";
                    break;
                case "lease-component-tab":
                    entity = "Lease Components";
                    break;
                case "activation-group-tab":
                    entity = "Activation Groups";
                    break;
                case "unit-tab":
                    entity = "Units";
                    break;
            }
            if(!driver.findElement(By.cssSelector("#q-app .q-page .page-title")).getText().equalsIgnoreCase(entity)){
                Assert.fail("User is not on "+entityType+" landing page");
            }
            log.info("User is on "+entityType);
            waitTillWebElementIsVisible("landingPageRecords","#q-app .q-table__bottom .q-table__control:nth-child(3) div");
            String records = driver.findElement(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) div")).getText();
            int startIndex = records.lastIndexOf("/") + 2; // Index after the '/' character
            int endIndex = records.lastIndexOf(" records");
            landingPageRecords.set(Integer.parseInt(records.substring(startIndex, endIndex)));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public  void exportRecord(String entityType){
        log.info("Checking the checkbox of first 5 records and exporting it");
        //Moving to 5th Page
//        waitTillWebElementIsVisible("nextPageButton",".q-page .q-table__bottom .q-btn:nth-child(4) .q-btn__content");
//        for(int page=1; page<=5;page++){
//            try {
//                waitAndClickOnElement("nextPageButton",".q-page .q-table__bottom .q-btn:nth-child(4) .q-btn__content");
//                waitUntilLoadingSpinnerIsShown("nlaLoadingPage");
//                waitUntilLoadingSpinnerIsGone("nlaLoadingPage");
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
        //Selecting 5 to 10 Checkboxes
        try {
            int recordSize = 10;
            waitTillWebElementIsVisible("recordCheckBox",".q-table tbody tr:nth-child(1) .q-checkbox[role='checkbox']");
            for(int i=6; i<=recordSize; i++){
                waitAndClickOnElement("checkBox", ".q-table tbody tr:nth-child("+i+") .q-checkbox[role='checkbox']");
                log.info("Checkbox "+i+" is Checked");
            }
            waitAndClickOnElement(massActionButton);
            if (entityType.equalsIgnoreCase("Activation Group")) {
                waitTillWebElementIsVisible("exportButton", ".q-menu #contextMenu-menu-item:nth-child(1)");
                waitAndClickOnElement("agExport",".q-menu #contextMenu-menu-item:nth-child(1)");
            } else{
                waitTillWebElementIsVisible("exportButton", exportButtonLandingPage);
                waitAndClickOnElement(exportButtonLandingPage);
            }
            waitTillWebElementIsVisible("exportButtonDialog",exportButtonDialog);
            waitAndClickOnElement(exportButtonDialog);
            handleWait(2000);
//            waitAndClickOnElement(refreshJobButtonDialog);
            checkJobCompletion("Landing Page Export","Done",0);
            downloadingFile("Landing Page Export");
            exportFileValidation(entityType,"Landing Page Export");
            waitAndClickOnElement(closeButtonDialog);
            waitAndClickOnElement("defaultCheckBox", ".dynamic-page #searchResults-selectHeaderValues-checkbox");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void applyCreatedByFilter(){
        log.info("Applying Created By filter in Jobs section");
        waitUntilLoadingSpinnerIsShown("nlaListLoader");
        waitUntilLoadingSpinnerIsGone("nlaListLoader");
        try {
            waitAndClickOnElement(userBtn);
        } catch (InterruptedException e) { e.printStackTrace();}
        waitTillWebElementIsVisible("userEmail", userEmail);
        createdByFilter.click();
        sendingValueToWebElement("createdByFilter", createdByFilter, userEmail.getText());
        waitUntilLoadingSpinnerIsShown("nlaListLoader");
        waitUntilLoadingSpinnerIsGone("nlaListLoader");
        log.info("Applied Created By filter in Jobs section");
    }

    public void excelMassImport(String importType, String importFile, DataTable dt) {
        log.info("user is going to perform Mass Import of "+importType+ " with file " +importFile);
        String importTypeFail = importType;
        if(importType.contains("Errors")) {
            importType = importType.substring(0, importType.indexOf("-"));
        }
        List<Map<String,String>> importJobData = dt.asMaps(String.class, String.class);
        massImportExcelFile = new File("src/test/resources/inputExcelFiles/ExcelUpload/MassImport/"
                + importType + "/" + importFile + ".xlsx");
        if(!(importType.equalsIgnoreCase("Contact"))) {
            modifyImportExcelFileBeforeUpload(importType, massImportExcelFile,importJobData.get(0).get("Principal Position"), importJobData);
        }
        if(!(getSizeOfElements("#q-app .jobs-grid tr td:nth-child(2) .q-icon")==1)) {
            applyCreatedByFilter();
        }
        waitTillWebElementIsVisible("addImportJob", addJob);
        //Taking Record Size before adding Job
        int recordBeforeJob;
        if(getSizeOfElements("#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item") == 0){
            recordBeforeJob=0;
        }else {
            String records = getValueFromElement("#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item");
            recordBeforeJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
        }
        try {
            waitAndClickOnElement(addJob);

            waitTillWebElementIsVisible("addImportJobPopUp",".q-dialog  .qcard-dialogue");
            waitTillWebElementIsVisible("importType",importTypeDropdown);
            if(importType.equalsIgnoreCase("LC T&C")) {
                clickOnDropDownAndSelectCheckBoxes("importType", importTypeDropdown, "Lease Component");
            }else{
                clickOnDropDownAndSelectCheckBoxes("importType", importTypeDropdown, importType);
            }
            if(importType.equalsIgnoreCase("Master Agreement") || importType.equalsIgnoreCase("Contract")){
                waitTillWebElementIsVisible("importSystemID",systemID);
                clickOnDropDownToTypeAndSelectValue("importSystemID",systemID,importJobData.get(0).get("ERP System"));
                waitAndClickOnElement("exportDataPopUp",".q-dialog .q-card .dialog-header");
            }
            if(importType.equalsIgnoreCase("Contract") || importType.equalsIgnoreCase("Lease Component")
                    || importType.equalsIgnoreCase("LC T&C") || importType.equalsIgnoreCase("Activation Group")
                    || importType.equalsIgnoreCase("Unit")) {
                if(importJobData.get(0).get("Principal Position").equalsIgnoreCase("Lessee")) {
                    clickOnDropDownAndSelectValue("importPrincipalPosition", principalPosition, "Lessee");
                }else{
                    clickOnDropDownAndSelectValue("importPrincipalPosition", principalPosition, "Lessor");
                }
                waitAndClickOnElement("exportDataPopUp",".q-dialog .q-card .dialog-header");
            }
            if (!(importType.equalsIgnoreCase("Charge") || importType.equalsIgnoreCase("Contact")) &&
                    importJobData.get(0).get("Auto Transition").equalsIgnoreCase("Yes")
//                    && importAutoTransition.getAttribute("aria-checked").equalsIgnoreCase("false")
        ) {
                handleWait(1000);
                waitAndClickOnElement(importAutoTransition);
                waitTillWebElementIsVisible("finalState",finalState);
                clickOnDropDownAndSelectValue("finalState",finalState, importJobData.get(0).get("Final State"));
            }
            if (!importType.equalsIgnoreCase("Contact")){
                waitTillWebElementIsVisible("firstSheetCheckBox",".dialog-body .col-4:nth-child(1) .q-checkbox__label");
                String[] sheetNames = importJobData.get(0).get("SheetsToImport").split(",");
                int checkboxSize =getSizeOfElements(".dialog-body .col-4 .q-checkbox__label");
                for(int index=1;index<=checkboxSize;index++){
                    if(!Arrays.asList(sheetNames).contains(getValueFromElement(".dialog-body .col-4:nth-child(" + index + ") .q-checkbox__label")) &&
                            getSizeOfElements(".dialog-body .col-4:nth-child(" + index + ")  [aria-checked='true']")==1 ){
                        waitAndClickOnElement("sheetsCheckBoxes",".dialog-body .col-4:nth-child(" + index + ") .q-checkbox__label");
                    }
                }
            }
            if(massImportExcelFile.exists()){
                log.info("Uploading Mass Import Excel file " + importFile);
                if (MasterHooks.configurationProperties.get().getRunEnvironment().equalsIgnoreCase("remote")){
                    upload_file_selenium_grid(massImportExcelFile.getAbsolutePath());
                }else{
                    importUploadBtn.sendKeys(massImportExcelFile.getAbsolutePath());
                }
            }else{
                Assert.fail("The expected Mass Import File "+ importFile +" is not present in Project Directory" + massImportExcelFile.getAbsolutePath());
            }
            handleWait(300);
            clickOnSubmitPopup("Submit / Add");
            //Waiting for page records to Increase
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
            wait.until(new Function<WebDriver, Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    String records = driver.findElement(By.cssSelector("#q-app .jobs-grid .q-table__bottom  .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
                    int recordAfterJob = Integer.parseInt(records.substring(records.indexOf("f") + 2));
                    if(recordAfterJob!=recordBeforeJob) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            handleWait(1000);
            //Waiting for Job Status to be Done/Complete
            int jobsSize = getSizeOfElements(".tasks-grid .q-table tr [role='status']");
            for(int index=2;index<jobsSize+2;index++) {
                if(importTypeFail.contains("Errors")){
                    checkJobCompletion("Mass Import with Errors","Done",index);
                }else {
                    checkJobCompletion("Mass Import", "Done", index);
                }
            }
//            if(importJobData.get(0).get("Auto Transition").equalsIgnoreCase("Yes")){
//                waitTillWebElementIsVisible("importToMassWorkflowButton",importToMassWorkflowButton);
//                waitAndClickOnElement(importToMassWorkflowButton);
//                MasterHooks.massWorkflowJobID.set(driver.findElement(By.cssSelector(".jobs-grid .q-table tbody tr:nth-child(2) .massWorkflowJobId div")).getText());
//            }
        } catch (Exception e) { e.printStackTrace(); }
        log.info("Mass Import Completed Successfully for "+importType);
    }

    public void modifyImportExcelFileBeforeUpload(String importType, File massImportExcelFile, String principlePosition, List<Map<String, String>> importJobData) {
        log.info("user is going to add previous Entity ID into "+importType+ " Import Excel file");
        try {
            FileInputStream file = new FileInputStream(massImportExcelFile);
            XSSFWorkbook myWorkbook = new XSSFWorkbook(file);
            XSSFSheet mySheet = null;
            if(importType.equalsIgnoreCase("Master Agreement") && !Strings.isEmpty(contactId.get())){
                mySheet = myWorkbook.getSheet("MA - Partner Contacts");
                int rowNum = mySheet.getLastRowNum();
                for (int row = 1; row <= rowNum; row++) {
                    mySheet.getRow(row).getCell(2).setCellValue(contactId.get());
                }
            }else if(importType.equalsIgnoreCase("Contract")) {
                if(principlePosition.equalsIgnoreCase("Lessee")) {
                    mySheet = myWorkbook.getSheet("CT - LSE Definition");
                } else if(principlePosition.equalsIgnoreCase("Lessor")) {
                    mySheet = myWorkbook.getSheet("CT - LSR Definition");
                }
                int rowNum = mySheet.getLastRowNum();
                recordImportCount.set(rowNum);
                for (int row = 1; row <= rowNum; row++) {
                    mySheet.getRow(row).getCell(2).setCellValue(MasterHooks.searchMLAID.get());
                }
                if (!Strings.isEmpty(contactId.get())) {
                    mySheet = myWorkbook.getSheet("CT - Partner Contacts");
                    int rowNo = mySheet.getLastRowNum();
                    for (int row = 1; row <= rowNo; row++) {
                        mySheet.getRow(row).getCell(2).setCellValue(contactId.get());
                    }
                }
            }else if(importType.equalsIgnoreCase("Lease Component")){
                if(principlePosition.equalsIgnoreCase("Lessee")) {
                    mySheet = myWorkbook.getSheet("LC - LSE Definition");
                    int rowNum = mySheet.getLastRowNum();
                    recordImportCount.set(rowNum);
                    for (int row = 1; row <= rowNum; row++) {
                        mySheet.getRow(row).getCell(0).setCellValue(MasterHooks.searchCTID.get());
                    }
                }else if(principlePosition.equalsIgnoreCase("Lessor")){
                    mySheet = myWorkbook.getSheet("LC - LSR Definition");
                    int rowNum = mySheet.getLastRowNum();
                    recordImportCount.set(rowNum);
                    for (int row = 1; row <= rowNum; row++) {
                        mySheet.getRow(row).getCell(2).setCellValue(MasterHooks.searchCTID.get());
                    }
                }
                mySheet = myWorkbook.getSheet("LC - Unit Distributions");
                int rowNums = mySheet.getLastRowNum();
                aGCreatedCount.set(rowNums);
            }else if(importType.equalsIgnoreCase("LC T&C")){
                mySheet = myWorkbook.getSheet("LC - LSE Terms & Conditions");
                int rowNum = mySheet.getLastRowNum();
                recordImportCount.set(rowNum);
                for (int row = 1; row <= rowNum; row++) {
                    mySheet.getRow(row).getCell(2).setCellValue(MasterHooks.searchLCID.get());
                }
            }else if(importType.equalsIgnoreCase("Charge")) {
                mySheet = myWorkbook.getSheet("Charge");
                int rowNum = mySheet.getLastRowNum();
                recordImportCount.set(rowNum);
                for (int row = 1; row <= rowNum; row++) {
                    mySheet.getRow(row).getCell(2).setCellValue(MasterHooks.searchUNID.get());
                }
            }else if(importType.equalsIgnoreCase("Unit")) {
                mySheet = myWorkbook.getSheet("Unit");
                int rowNum = mySheet.getLastRowNum();
                recordImportCount.set(rowNum);
                for (int row = 1; row < rowNum; row++) {
                    mySheet.getRow(row).getCell(1).setCellValue(MasterHooks.searchAGID.get());
                    mySheet.getRow(row).getCell(2).setCellValue(MasterHooks.searchUNID.get());
                }
            } else if (importType.equalsIgnoreCase("Activation Group")) {
                int rowNum;
                String[] sheetNames = importJobData.get(0).get("SheetsToImport").split(",");
                for (int index = 0; index < sheetNames.length; index++) {
                    if (sheetNames[index].contains("Definition")) {
                        if(principlePosition.equalsIgnoreCase("Lessee")){
                        mySheet = myWorkbook.getSheet("AG - LSE Definition");
                            rowNum = mySheet.getLastRowNum();
                            recordImportCount.set(rowNum);
                            for (int row = 1; row <= rowNum; row++) {
                                mySheet.getRow(row).getCell(1).setCellValue(MasterHooks.searchAGID.get());
                                mySheet.getRow(row).getCell(2).setCellValue(MasterHooks.searchLCID.get());
                            }
                        } else if (principlePosition.equalsIgnoreCase("Lessor")) {
                            mySheet = myWorkbook.getSheet("AG - LSR Definition");
                            rowNum = mySheet.getLastRowNum();
                            recordImportCount.set(rowNum);
                            for (int row = 1; row <= rowNum; row++) {
                                mySheet.getRow(row).getCell(7).setCellValue(MasterHooks.searchAGID.get());
                                mySheet.getRow(row).getCell(8).setCellValue(MasterHooks.searchLCID.get());
                            }
                        }

                    } else if (sheetNames[index].contains("Accounting")) {
                        if(principlePosition.equalsIgnoreCase("Lessee")) {
                            mySheet = myWorkbook.getSheet("AG - LSE Accounting");
                            rowNum = mySheet.getLastRowNum();
                            for (int row = 1; row <= rowNum; row++) {
                                mySheet.getRow(row).getCell(1).setCellValue(MasterHooks.searchAGID.get());
                            }
                        } else if (principlePosition.equalsIgnoreCase("Lessor")) {
                            mySheet = myWorkbook.getSheet("AG - LSR Accounting");
                            rowNum = mySheet.getLastRowNum();
                            for (int row = 1; row <= rowNum; row++) {
                                mySheet.getRow(row).getCell(5).setCellValue(MasterHooks.searchAGID.get());
                            }
                        }
                    } else if (sheetNames[index].contains("Classifications")) {
                        if(principlePosition.equalsIgnoreCase("Lessee")) {
                            mySheet = myWorkbook.getSheet("AG - LSE Classifications");
                            rowNum = mySheet.getLastRowNum();
                            for (int row = 1; row <= rowNum; row++) {
                                mySheet.getRow(row).getCell(1).setCellValue(MasterHooks.searchAGID.get());
                            }
                        } else if (principlePosition.equalsIgnoreCase("Lessor")) {
                            mySheet = myWorkbook.getSheet("AG - LSR Classifications");
                            rowNum = mySheet.getLastRowNum();
                            for (int row = 1; row <= rowNum; row++) {
                                mySheet.getRow(row).getCell(5).setCellValue(MasterHooks.searchAGID.get());
                            }
                        }
                    } else if (sheetNames[index].equalsIgnoreCase("Terms & Conditions")) {
                        if(principlePosition.equalsIgnoreCase("Lessee")) {
                            mySheet = myWorkbook.getSheet("AG - LSE Terms & Conditions");
                            rowNum = mySheet.getLastRowNum();
                            for (int row = 1; row <= rowNum; row++) {
                                mySheet.getRow(row).getCell(1).setCellValue(MasterHooks.searchLCTCID.get());
                                mySheet.getRow(row).getCell(2).setCellValue(MasterHooks.searchLCTCID.get());
                                mySheet.getRow(row).getCell(3).setCellValue(MasterHooks.searchAGID.get());
                            }
                        } else if (principlePosition.equalsIgnoreCase("Lessor")) {
                            mySheet = myWorkbook.getSheet("AG - LSR Terms & Conditions");
                            rowNum = mySheet.getLastRowNum();
                            for (int row = 1; row <= rowNum; row++) {
                                mySheet.getRow(row).getCell(6).setCellValue(MasterHooks.searchLCTCID.get());
                                mySheet.getRow(row).getCell(6).setCellValue(MasterHooks.searchLCTCID.get());
                                mySheet.getRow(row).getCell(7).setCellValue(MasterHooks.searchAGID.get());
                            }
                        }
                    }
//                    else if(sheetNames[index].equalsIgnoreCase("Unit")) {
//                        mySheet = myWorkbook.getSheet("Unit");
//                        rowNum = mySheet.getLastRowNum();
//                        recordImportCount.set(rowNum);
//                        for (int row = 1; row <= rowNum; row++) {
//                            mySheet.getRow(row).getCell(1).setCellValue(MasterHooks.searchAGID.get());
//                            mySheet.getRow(row).getCell(2).setCellValue(MasterHooks.searchUNID.get());
//                        }
//                    }else if(sheetNames[index].equalsIgnoreCase("Charge")) {
//                        mySheet = myWorkbook.getSheet("Charge");
//                        rowNum = mySheet.getLastRowNum();
//                        recordImportCount.set(rowNum);
//                        for (int row = 1; row <= rowNum; row++) {
//                            mySheet.getRow(row).getCell(2).setCellValue(MasterHooks.searchUNID.get());
//                        }
//                    }
                }
            }
            file.close();
            FileOutputStream fileOutput = new FileOutputStream(massImportExcelFile);
            myWorkbook.write(fileOutput);
            myWorkbook.close();
            fileOutput.close();
        } catch (IOException e) {e.printStackTrace();}
    }

    public void openReportForID(String importType){
        log.info("user open report of "+importType+ " For ID ");
        try {
            waitAndClickOnElement(reportBtn);
            waitTillWebElementIsVisible("addImportReportPopUp", ".q-dialog .q-card tbody tr");
            clickOnDropDownAndSelectValue("recordPerPagePer",recordPerPageSheet,"All");
            handleWait(300);
            int rowSize = getSizeOfElements(".q-dialog .q-card tbody tr");
            for (int index = 1; index <= rowSize; index++) {
                if (driver.findElement(By.cssSelector(".q-dialog .q-card tbody tr:nth-child(" + index + ") td.q-td")).getText().equalsIgnoreCase(importType)) {
                    waitAndClickOnElement("reportButton", ".q-dialog .q-card tbody tr:nth-child(" + index + ") Button.q-btn");
                    waitTillWebElementIsVisible("reportOF" + importType, ".q-card .q-mt-md tbody tr td:nth-child(3)");
                    break;
                }
            }
            String entityId = driver.findElement(By.cssSelector(".q-card .q-mt-md tbody tr td:nth-child(2)")).getText();
//            String entityIdSubstring = entityId.substring(entityId.indexOf(":") + 2, entityId.indexOf("]") - 1);
            switch(importType){
                case "MA - Definition":
                    MasterHooks.searchMLAID.set(entityId);
                    break;
                case "CT - LSE Definition":
                case "CT - LSR Definition":
                    MasterHooks.searchCTID.set(entityId);
                    break;
                case "LC - LSE Definition":
                case "LC - LSR Definition":
                    MasterHooks.searchLCID.set(entityId);
                    break;
                case "LC - LSE Terms & Conditions":
                    MasterHooks.searchLCTCID.set(entityId);
                    break;
                case "Contact":
                    contactId.set(entityId);
                    break;
            }
            waitAndClickOnElement("popUoCancel",".q-card #cancel-btn");
            waitUntilLoadingSpinnerIsGone("nlaDropDownPopUp");
        }catch(Exception e){e.printStackTrace();}
        log.info("user stored the "+importType+ " ID");
    }

    public void userValidatedTheRecordsImport(String importLevel) {
        log.info("user validating records count at "+importLevel+ " Level");
        try {
            if(!(importLevel.equalsIgnoreCase("LC T&C") || importLevel.equalsIgnoreCase("Charge"))) {
                switch (importLevel) {
                    case "Master Agreement":
                        WaitUntilElementIsClickable(contractSelector);
                        waitAndClickOnElement(contractSelector);
                        break;
                    case "Contract":
                        WaitUntilElementIsClickable(leaseComponentSelector);
                        waitAndClickOnElement(leaseComponentSelector);
                        break;
                    case "Lease Component":
                        WaitUntilElementIsClickable(activationGroupSelector);
                        waitAndClickOnElement(activationGroupSelector);
                        break;
                }
                handleWait(500);
                String dropDownValues = ".q-menu .q-item:nth-child(1)";
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
                wait.until(new Function<WebDriver, Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        if (!driver.findElement(By.cssSelector(dropDownValues)).getText().equalsIgnoreCase("No results")) {
                            return true;
                        }
                        return false;
                    }
                });
                int recordSize = getSizeOfElements(".q-menu .q-item");
                int expected;
                if (importLevel.equalsIgnoreCase("Lease Component")) {
                    expected = aGCreatedCount.get();
                } else {
                    expected = recordImportCount.get();
                }
                System.out.println("Expected: " + expected + " - Actual: " + recordSize);
                Assert.assertEquals(recordSize, expected, "The Records are not Imported on " + importLevel);
            }else{
                String records = driver.findElement(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
                int recordAfterImport = Integer.parseInt(records.substring(records.indexOf("f") + 2));
                if(importLevel.equalsIgnoreCase("Charge")){
                    Assert.assertEquals(recordAfterImport, recordImportCount.get() + aGExistingCharge.get(), "The Charge are not Imported");
                }else {
                    Assert.assertEquals(recordAfterImport, recordImportCount.get() + lCExistingTerms.get(), "The LC T&C are not Imported");
                }
            }
        } catch (InterruptedException e) { e.printStackTrace(); }
        log.info("user validated the records count at "+importLevel+ " Level");
    }

    public void importFromTermsAndConditionsTab(String fileName) {
        log.info("Import from Terms And Conditions Tab Of Lease Component");
        massImportExcelFile = new File("src/test/resources/inputExcelFiles/ExcelUpload/MassImport/LC T&C/"
                + fileName + ".xlsx");
        try {
            List<Map<String, String>> importJobData = null;
            modifyImportExcelFileBeforeUpload("LC T&C", massImportExcelFile,"Lessee", null);
            waitTillWebElementIsVisible("excelImportLC",excelImportLCBtn);
            waitAndClickOnElement(excelImportLCBtn);
            waitTillWebElementIsVisible("import/Export",".q-menu #import-terms-btn .q-icon");
            waitAndClickOnElement("importBtn",".q-menu #import-terms-btn");
            waitTillWebElementIsVisible("excelAttachButton",lCImportButton);
            if(massImportExcelFile.exists()){
                log.info("Uploading Mass Import Excel file " + fileName);
                if (MasterHooks.configurationProperties.get().getRunEnvironment().equalsIgnoreCase("remote")){
                    upload_file_selenium_grid(massImportExcelFile.getAbsolutePath());
                }else{
                    importUploadBtn.sendKeys(massImportExcelFile.getAbsolutePath());
                }
            }else{
                Assert.fail("The expected Mass Import File "+ fileName +" is not present in Project Directory" + massImportExcelFile.getAbsolutePath());
            }
            waitAndClickOnElement(lCImportButton);
            waitUntilLoadingSpinnerIsShown("excelUploadLoader");
            waitUntilLoadingSpinnerIsGone("excelUploadLoader");
            handleWait(2000);
            checkJobCompletion("LC Import","Done",0);
            termsConditionsTabLeaseComponent.click();
            waitAndClickOnElement(leaseComponentRefreshButton);
            //waiting for LC terms record size to change
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofMillis(1000)).ignoring(WebDriverException.class);
            wait.until(new Function<WebDriver, Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    String records = driver.findElement(By.cssSelector("#q-app .q-table__bottom .q-table__control:nth-child(3) span.q-table__bottom-item")).getText();
                    int recordAfterImport = Integer.parseInt(records.substring(records.indexOf("f") + 2));
                    if(lCExistingTerms.get() != recordAfterImport ){
                        return true;
                    }
                    return false;
                }

            });
        } catch (Exception e) {e.printStackTrace();}
        log.info("Import Completed from Terms And Conditions Tab Of Lease Component");
    }

    public void releasingClassThreads(){
        recordImportCount.remove();
        aGCreatedCount.remove();
        lCExistingTerms.remove();
        aGExistingCharge.remove();
        contactId.remove();
        totalExportedRecords.remove();
        landingPageRecords.remove();
    }

    public void deleteFile() {
        log.info("Deleting the Excel file from Project");
        File folder = new File(MasterHooks.downloadedExcelFilePath.get());
        for (File file :folder.listFiles()) {
            file.delete();
            log.info("Excel file deleted successfully!!!");
        }
    }

    public void CheckColumnsInExcel(DataTable dt) {
        log.info("Checking the specific Column Exist In Exported Excel File");
        List<Map<String, String>> data = dt.asMaps(String.class, String.class);
        try {
            File folder = new File(MasterHooks.downloadedExcelFilePath.get());
            File[] downloadedFiles = folder.listFiles();
            String downloadedFilePath = downloadedFiles[0].getAbsolutePath();
            File exportFile = new File(downloadedFilePath);
            FileInputStream file = new FileInputStream(exportFile);
            XSSFWorkbook myWorkbook = new XSSFWorkbook(file);
            for (int sheets = 0; sheets < data.size(); sheets++) {
                Sheet sheet =myWorkbook.getSheet(data.get(sheets).get("Sheet Name"));
                String columns=data.get(sheets).get("Column Name");
                String[] fieldValuesLength = columns.split(",");
                for (int column = 0; column < fieldValuesLength.length; column++){
                    boolean valueFound = false;
                    Row firstRow = sheet.getRow(0);
                    for (Cell cell : firstRow) {
                        if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equals(fieldValuesLength[column])) {
                            log.info("The Column '"+fieldValuesLength[column] +"' is present in Excel File");
                            valueFound = true;
                            break;
                        }
                    }
                    if (!valueFound) {
                        Assert.fail("The Column '"+fieldValuesLength[column]+"' is not present in the Excel File");
                    }
                }

            }
            myWorkbook.close();
            file.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void downloadTemplateFile(String entityType, DataTable dt) {
        try {
            List<Map<String,String>> importJobData = dt.asMaps(String.class, String.class);
            waitTillWebElementIsVisible("addJob", addJob);
            waitAndClickOnElement(addJob);
//            waitTillWebElementIsVisible("exportType",exportType);
            if (getSizeOfElements(".q-card .dialog-body") != 1) {
                waitAndClickOnElement(cancelButton);
                waitTillWebElementIsVisible("addExportJob", addJob);
                waitAndClickOnElement(addJob);
            }
            handleWait(2000);
            waitTillWebElementIsVisible("importType", importTypeDropdown);
            clickOnDropDownAndSelectValue("importType", importTypeDropdown, entityType);
            if(entityType.equalsIgnoreCase("Master Agreement") || entityType.equalsIgnoreCase("Contract")){
                waitTillWebElementIsVisible("systemID",systemID);
                clickOnDropDownAndSelectValue("systemID",systemID, importJobData.get(0).get("ERP System"));
            }
            if(!(entityType.equalsIgnoreCase("Master Agreement") || entityType.equalsIgnoreCase("Contact") || entityType.equalsIgnoreCase("Charge"))){
                if(!(importJobData.get(0).get("Principal Position").equalsIgnoreCase("Lessee+Lessor"))){
                    if(importJobData.get(0).get("Principal Position").equalsIgnoreCase("Lessee")) {
                        clickOnDropDownAndSelectValue("principalPosition",principalPosition,"Lessee");
                    }else{
                        clickOnDropDownAndSelectValue("principalPosition",principalPosition,"Lessor");
                    }
                    waitAndClickOnElement("exportDataPopUp",".q-dialog .q-card .dialog-header");
                }
            }
            if (!entityType.equalsIgnoreCase("Contact")){
                waitTillWebElementIsVisible("firstSheetCheckBox",".dialog-body .col-4:nth-child(1) .q-checkbox__label");
                String[] sheetNames = importJobData.get(0).get("SheetsToImport").split(",");
                int checkboxSize =getSizeOfElements(".dialog-body .col-4 .q-checkbox__label");
                for(int index=1;index<=checkboxSize;index++){
                    if(!Arrays.asList(sheetNames).contains(getValueFromElement(".dialog-body .col-4:nth-child(" + index + ") .q-checkbox__label")) &&
                            getSizeOfElements(".dialog-body .col-4:nth-child(" + index + ")  [aria-checked='true']")==1 ){
                        waitAndClickOnElement("sheetsCheckBoxes",".dialog-body .col-4:nth-child(" + index + ") .q-checkbox__label");
                    }
                }
            }
            waitAndClickOnElement(importTemplateButton);
            waitTillWebElementIsVisible("ToggleButton",sampleDataToggleButton);
            waitAndClickOnElement(sampleDataToggleButton);
            //Downloading the Import File
            downloadingFile("Import Template");
            waitAndClickOnElement(cancelButton);
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void excelMassImportWithWrongFile(String importType, String importFile, DataTable dt) {
        log.info("Verifying the Import Error Message by uploading the wrong file");
        List<Map<String, String>> importJobData = dt.asMaps(String.class, String.class);
        if(importFile.contains("Incorrect")){
            massImportExcelFile = new File("src/test/resources/inputExcelFiles/ExcelUpload/MassImport/"
                    + importType + "/" + importFile + ".json");
        }else {
            massImportExcelFile = new File("src/test/resources/inputExcelFiles/ExcelUpload/MassImport/"
                    + importType + "/" + importFile + ".xlsx");
        }
        if (!(getSizeOfElements("#q-app .jobs-grid tr td:nth-child(2) .q-icon") == 1)) {
            applyCreatedByFilter();
        }
        waitTillWebElementIsVisible("addImportJob", addJob);
        try {
            waitAndClickOnElement(addJob);

            waitTillWebElementIsVisible("addImportJobPopUp", ".q-dialog  .qcard-dialogue");
            waitTillWebElementIsVisible("importType", importTypeDropdown);
            clickOnDropDownAndSelectValue("importType", importTypeDropdown, importType);

            if (importType.equalsIgnoreCase("Master Agreement") || importType.equalsIgnoreCase("Contract")) {
                waitTillWebElementIsVisible("importSystemID", systemID);
                clickOnDropDownToTypeAndSelectValue("importSystemID", systemID, importJobData.get(0).get("ERP System"));
            }
            if (importType.equalsIgnoreCase("Contract") || importType.equalsIgnoreCase("Lease Component") || importType.equalsIgnoreCase("Activation Group") || importType.equalsIgnoreCase("Unit")) {
                if (importJobData.get(0).get("Principal Position").equalsIgnoreCase("Lessee")) {
                    clickOnDropDownAndSelectValue("importPrincipalPosition", principalPosition, "Lessee");
                } else {
                    clickOnDropDownAndSelectValue("importPrincipalPosition", principalPosition, "Lessor");
                }
                waitAndClickOnElement("exportDataPopUp", ".q-dialog .q-card .dialog-header");
            }

            if (massImportExcelFile.exists()) {
                log.info("Uploading Mass Import Excel file " + importFile);
                if (MasterHooks.configurationProperties.get().getRunEnvironment().equalsIgnoreCase("remote")) {
                    upload_file_selenium_grid(massImportExcelFile.getAbsolutePath());
                } else {
                    importUploadBtn.sendKeys(massImportExcelFile.getAbsolutePath());
                }
            } else {
                Assert.fail("The expected Mass Import File " + importFile + " is not present in Project Directory" + massImportExcelFile.getAbsolutePath());
            }
            waitAndClickOnElement(submitButton);
            waitUntilLoadingSpinnerIsShown("nlaAlertMessage");
            handleWait(1000);
            String alertMessage = driver.findElement(By.cssSelector(".desktop .q-notifications .q-notification[role='alert'] .q-notification__message")).getText();
            waitUntilLoadingSpinnerIsGone("nlaAlertMessage");
            if(getSizeOfElements(".q-dialog .q-card") == 0){
                Assert.fail("Import job is Scheduled with Wrong File");
            }
            if (!alertMessage.equalsIgnoreCase(importJobData.get(0).get("Alert Message"))) {
                Assert.fail("Incorrect Error Message: " +alertMessage+ " Expected: " +importJobData.get(0).get("Alert Message"));
            }
            log.info(importJobData.get(0).get("Alert Message"));
            waitAndClickOnElement(cancelButton);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportLCTerm() {
        log.info("Exporting the Term at Lease Component Level");
        try {
            waitTillWebElementIsVisible("excelExportLC",excelImportLCBtn);
            waitAndClickOnElement(excelImportLCBtn);
            waitTillWebElementIsVisible("import/Export",".q-menu #export-terms-btn .q-icon");
            waitAndClickOnElement("exportBtn",".q-menu #export-terms-btn");
            waitTillWebElementIsVisible("excelAttachButton",lCExportButton);
            waitAndClickOnElement(lCExportButton);
            handleWait(2000);
            waitAndClickOnElement(refreshButton);
            //Checking the Job Completion
            checkJobCompletion("Lease Component Terms Export","Done",0);
            //Generating the Terms Export Report
            downloadingFile("Lease Component Terms Export");
            //Validating the Export File
            exportFileValidation("Lease Component","Lease Component Terms Export");
            waitAndClickOnElement(lCExportCloseButton);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void validateAccountingTabImport() {
        log.info("Validating the Import for the Accounting tab");
        try {
            waitAndClickOnElement("AccountingTab","#q-app .q-page #accounting-step");
            waitUntilLoadingSpinnerIsShown("nlaTabChange");
            waitUntilLoadingSpinnerIsGone("nlaTabChange");
            waitTillWebElementIsVisible("WBS","#q-app .q-page #cost-object-work-breakdown-structure");
            //WBS
            waitAndClickOnElement("WBS","#q-app .q-page #cost-object-work-breakdown-structure");
            waitTillWebElementIsVisible("WBS","#q-app .q-page .absolute #work-breakdown-structure");
            if(driver.findElement(By.cssSelector("#q-app .q-page .absolute #work-breakdown-structure-input")).getAttribute("value").equalsIgnoreCase("")){
                Assert.fail("WBS is not imported Successfully");
            }
            log.info("WBS is imported Successfully");
            //Functional Area
            waitTillWebElementIsVisible("Functional Area","#q-app .q-page .absolute #functional-area");
            if(driver.findElement(By.cssSelector("#q-app .q-page .absolute #functional-area-input")).getAttribute("value").equalsIgnoreCase("")){
                Assert.fail("Functional Area is not imported Successfully");
            }
            log.info("Functional Area is imported Successfully");
            //Business Area
            if(driver.findElement(By.cssSelector("#q-app .q-page .absolute #business-area-input")).getAttribute("value").equalsIgnoreCase("")){
                Assert.fail("Business Area is not imported Successfully");
            }
            log.info("Business Area is imported Successfully");
            //Segment
            if(driver.findElement(By.cssSelector("#q-app .q-page .absolute #segment-input")).getAttribute("value").equalsIgnoreCase("")){
                Assert.fail("Segment is not imported Successfully");
            }
            log.info("Segment is imported Successfully");
            //Internal Order Type
            if(driver.findElement(By.cssSelector("#q-app .q-page .absolute #internal-order-type-input")).getAttribute("value").equalsIgnoreCase("")){
                Assert.fail("Internal Order Type is not imported Successfully");
            }
            log.info("Internal Order Type is imported Successfully");
            //Track Cost Checkbox
            if(driver.findElement(By.cssSelector("#q-app .q-page .absolute #track-cost")).getAttribute("aria-checked").equalsIgnoreCase("false")){
                Assert.fail("Track Cost Checkbox is not imported Successfully");
            }
            log.info("Track Cost Checkbox is imported Successfully");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void verifyMessage(String importType, String report,String message){
        log.info("Checking the Expected Message "+message);
        try {
            waitAndClickOnElement(reportBtn);
            waitTillWebElementIsVisible("addImportReportPopUp", ".q-dialog .q-card tbody tr");
            clickOnDropDownAndSelectValue("recordPerPagePer",recordPerPageSheet,"All");
            handleWait(300);
            int rowSize = getSizeOfElements(".q-dialog .q-card tbody tr");
            if(!importType.equalsIgnoreCase("Lease Component")){
                for (int index = 1; index <= rowSize; index++) {
                    if (driver.findElement(By.cssSelector(".q-dialog .q-card tbody tr:nth-child(" + index + ") td.q-td")).getText().equalsIgnoreCase(importType)) {
                        waitAndClickOnElement("reportButton", ".q-dialog .q-card tbody tr:nth-child(" + index + ") Button.q-btn");
                        waitTillWebElementIsVisible("reportOF" + importType, ".q-card .q-mt-md tbody tr td:nth-child(3)");
                        break;
                    }
                }
            } else{
                waitAndClickOnElement("reportButton", ".q-dialog .q-card tbody tr:nth-child(1) Button.q-btn");
                waitTillWebElementIsVisible("reportOF" + importType, ".q-card .q-mt-md tbody tr td:nth-child(3)");
            }
            handleWait(2000);
            if(report.equalsIgnoreCase("Message")) {
                String reportMessage = driver.findElement(By.cssSelector(".q-card .q-mt-md tbody tr td:nth-child(4)")).getText();
                String messageSubstring = reportMessage.substring(reportMessage.indexOf("[") + 2, reportMessage.indexOf("]") - 1);
                if (!messageSubstring.equalsIgnoreCase(message)) {
                    Assert.fail("Error Message is not same as Expected message: " + message);
                }
                log.info("Error Message: '" + message + "' is Correct");
            } else if ((report.equalsIgnoreCase("Type"))){
                String reportType = driver.findElement(By.cssSelector(".q-card .q-mt-md tbody tr td:nth-child(2)")).getText();
                if(!reportType.equalsIgnoreCase(message)){
                    Assert.fail("Report Type is not expected " + message);
                }
                log.info("Report Type: '" + message + "' is Correct");
            }
            waitAndClickOnElement("popUoCancel",".q-card #cancel-btn");
            waitUntilLoadingSpinnerIsGone("nlaDropDownPopUp");
        }catch(Exception e){e.printStackTrace();}
    }

    public void updateImportFile(String importType, String massImportExcelUpdateFile){
        log.info("user is going to add previous Entity ID into "+importType+ " Import Excel file");
        int rowNum=0;
        try {
            massImportExcelFile = new File("src/test/resources/inputExcelFiles/ExcelUpload/MassImport/"
                    + importType + "/" + massImportExcelUpdateFile + ".xlsx");
            FileInputStream file = new FileInputStream(massImportExcelFile);
            XSSFWorkbook myWorkbook = new XSSFWorkbook(file);
            XSSFSheet mySheet = null;
            if(importType.equalsIgnoreCase("Master Agreement")){
                mySheet = myWorkbook.getSheet("Master Agreement");
                for (int row = 1; row <= mySheet.getLastRowNum(); row++) {
                    mySheet.getRow(row).getCell(1).setCellValue(MasterHooks.searchMLAID.get());
                }
            }else if(importType.equalsIgnoreCase("Contract")) {
                mySheet = myWorkbook.getSheet("Contract");
                rowNum = mySheet.getLastRowNum();
                recordImportCount.set(rowNum);
                for (int row = 1; row <= rowNum; row++) {
                    mySheet.getRow(row).getCell(1).setCellValue(MasterHooks.searchCTID.get());
                    mySheet.getRow(row).getCell(2).setCellValue(MasterHooks.searchMLAID.get());
                }
                //LSE Contract Accounting
                mySheet = myWorkbook.getSheet("LSE Contract Accounting");
                mySheet.getRow(1).getCell(1).setCellValue(MasterHooks.searchCTID.get());
                //Cost Center Allocation
                mySheet = myWorkbook.getSheet("Contract Cost Center Allocation");
                rowNum = mySheet.getLastRowNum();
                for (int row = 1; row <= rowNum; row++) {
                    mySheet.getRow(row).getCell(1).setCellValue(MasterHooks.searchCTID.get());
                }
            }else if(importType.equalsIgnoreCase("Lease Component")){
                    mySheet = myWorkbook.getSheet("LSE Lease Component");
                rowNum = mySheet.getLastRowNum();
                recordImportCount.set(rowNum);
                for (int row = 1; row <= rowNum; row++) {
                    mySheet.getRow(row).getCell(0).setCellValue(MasterHooks.searchCTID.get());
                    mySheet.getRow(row).getCell(4).setCellValue(MasterHooks.searchLCID.get());
                }
                mySheet = myWorkbook.getSheet("LC Unit Distribution");
                mySheet.getRow(1).getCell(1).setCellValue(MasterHooks.searchLCID.get());
            }else if(importType.equalsIgnoreCase("LC T&C")){
                mySheet = myWorkbook.getSheet("LC T&C");
                rowNum = mySheet.getLastRowNum();
                recordImportCount.set(rowNum);
                for (int row = 1; row <= rowNum; row++) {
                    mySheet.getRow(row).getCell(2).setCellValue(MasterHooks.searchLCID.get());
                }
            }
            file.close();
            FileOutputStream fileOutput = new FileOutputStream(massImportExcelFile);
            myWorkbook.write(fileOutput);
            myWorkbook.close();
            fileOutput.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}
