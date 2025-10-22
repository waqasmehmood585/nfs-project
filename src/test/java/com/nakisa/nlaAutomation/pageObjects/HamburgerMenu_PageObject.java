package com.nakisa.nlaAutomation.pageObjects;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.function.Function;

@Slf4j
public class HamburgerMenu_PageObject extends Common_BasePage_PageObject {

    public @FindBy(css = ".desktop .q-header Button[aria-label='Menu']") WebElement hamburgerMenu;

    public HamburgerMenu_PageObject() {
        super();
        log.info("Driver is inside this class: " + this.getClass().getSimpleName());
        PageFactory.initElements(driver, this);
    }

    public void openHamburgerMenuAndClickOnOptions(String mainOption, String subOption, String option) {
        log.info("Opening Hamburger Menu");
        try {
           String mainOptionCssSelector;
            handleWait(1000);
            WaitUntilElementIsClickable(hamburgerMenu);
            waitAndClickOnElement(hamburgerMenu);
            waitUntilElementIsShown("aside.q-drawer[style*='transform: translateX(0px)']", 3);

            if (!(subOption.equalsIgnoreCase("Batch Job Profiles") || subOption.equalsIgnoreCase("Report Profiles"))) {
                removeAIAssistant();
            }

            if (mainOption.equalsIgnoreCase("Dashboards")) {
                String dashboardSelector = "#q-app .main-menu #main-menu-item-dashboard-expansion-dashboards .q-pr-xs .q-icon";
                waitTillWebElementIsVisible("dashboard", dashboardSelector);
                waitAndClickOnElement("dashboard", dashboardSelector);
            } else {
                if (!mainOption.equalsIgnoreCase("")) {
                    if(mainOption.equalsIgnoreCase("SAP Posting Bot")){
                         mainOptionCssSelector = "#q-app .main-menu #sap-main-menu-item-" + menuItemCSSSelectorIds(mainOption);
                    }else{
                         mainOptionCssSelector = "#q-app .main-menu #main-menu-item-" + menuItemCSSSelectorIds(mainOption);
                    }
                    waitTillWebElementIsVisible("mainOption", mainOptionCssSelector);
                    WaitUntilElementIsClickable(driver.findElement(By.cssSelector(mainOptionCssSelector)));
                    waitAndClickOnElement("mainOption", mainOptionCssSelector);
                    //Validating Hamburger Main Option is Opened
                    if (!mainOption.equalsIgnoreCase("Landing Pages")) {
                        String expansionCssSelector;
                        if(mainOption.equalsIgnoreCase("SAP Posting Bot")){
                             expansionCssSelector = "#sap-main-menu-item-dashboard-expansion-" + menuItemCSSSelectorIds(mainOption);
                        }else{
                             expansionCssSelector = "#main-menu-item-dashboard-expansion-" + menuItemCSSSelectorIds(mainOption);
                        }
                        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
                        wait.until(new Function<WebDriver, Boolean>() {
                            @Override
                            public Boolean apply(WebDriver driver) {
                                if (driver.findElements(By.cssSelector(expansionCssSelector + " .q-expansion-item__toggle-icon--rotated")).size() >= 1) {
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                    }
                    log.info("clicked on " + mainOption);
                }
            }

            if (!subOption.equalsIgnoreCase("")) {
                String expansionCssSelector;
                String subOptionCssSelector = "#q-app .main-menu #main-menu-item-" + subMenuItemCSSSelectorIds(subOption);
                waitTillWebElementIsVisible("subOption", subOptionCssSelector);
                WaitUntilElementIsClickable(driver.findElement(By.cssSelector(subOptionCssSelector)));
                waitAndClickOnElement("subOption", subOptionCssSelector);
//                if(mainOption.equalsIgnoreCase("SAP Posting Bot")){
//                    expansionCssSelector = "#sap-main-menu-item-dashboard-expansion-" + subMenuItemCSSSelectorIds(subOption);
//                }else{
                expansionCssSelector = "#main-menu-item-dashboard-expansion-" + subMenuItemCSSSelectorIds(subOption);
//                }
                //Validating Hamburger Sub Option is Opened
                if (!option.equalsIgnoreCase("")) {
                    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
                    wait.until(new Function<WebDriver, Boolean>() {
                        @Override
                        public Boolean apply(WebDriver driver) {
                            if (driver.findElements(By.cssSelector(expansionCssSelector + " .q-expansion-item__toggle-icon--rotated")).size() >= 1) {
                                return true;
                            } else {
                                return false;
                            }

                        }
                    });
                }
                log.info("clicked on " + subOption);
            }
            if (!option.equalsIgnoreCase("")) {
                String OptionCssSelector = "#q-app .main-menu #main-menu-item-" + ItemCSSSelectorIds(option);
                waitTillWebElementIsVisible("subOption", OptionCssSelector);
                WaitUntilElementIsClickable(driver.findElement(By.cssSelector(OptionCssSelector)));
                waitAndClickOnElement("subOption", OptionCssSelector);
                log.info("clicked on " + option);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("Inside " + option + " option");
    }

    public String menuItemCSSSelectorIds(String optionName) {
        String ids = null;
        switch (optionName) {
            case "Create Master Agreement":
                ids = "create-master-agreement";
                break;
            case "Import/Export Information":
                ids = "import-export-information";
                break;
            case "Landing Pages":
                ids = "landing-pages";
                break;
            case "Dashboards":
                ids = "dashboards";
                break;
            case "Reporting Module":
                ids = "reporting-modules";
                break;
            case "Batch Management":
                ids = "batch-management";
                break;
            case "Audit Logs":
                ids = "audit-logs";
                break;
            case "SAP Posting Bot":
                ids = "sap-posting-bot";
                break;
            case "Admin":
                ids = "admin";
                break;
            case "Profiles":
                ids = "profile";
                break;
        }
        return ids;

    }

    public String subMenuItemCSSSelectorIds(String subOption) {
        String ids = null;
        switch (subOption) {
            case "View Latest Audit Logs":
                ids = "latest-audit-logs";
                break;
            case "Export Archived Audit Logs":
                ids = "export-archived-audit-logs";
                break;
            case "Export":
                ids = "export";
                break;
            case "Import":
                ids = "import";
                break;
            case "Create Dashboard":
                ids = "create-dashboard";
                break;
            case "Master Agreement":
                ids = "master-agreement";
                break;
            case "Contract":
                ids = "contract";
                break;
            case "Lease Component":
                ids = "lease-component";
                break;
            case "Activation Group":
                ids = "activation-group";
                break;
            case "Unit":
                ids = "unit";
                break;
            case "Reports":
                ids = "reports";
                break;
            case "Disclosure Reports":
                ids = "disclosure-reports";
                break;
            case "Activity Analysis Reports":
                ids = "activity-analysis-reports";
                break;
            case "Periodic Posting Status Reports":
                ids = "periodic-posting-status-reports";
                break;
            case "Operational Postings":
                ids = "operational-postings";
                break;
            case "Mass Indexation":
                ids = "mass-indexation";
                break;
            case "Mass Workflow Transition":
                ids = "mass-workflow-transition";
                break;
            case "Mass Modification":
                ids = "mass-modification";
                break;
            case "Inter Company Transfer":
                ids = "mass-inter-company-transfer";
                break;
            case "Ledger Transactions":
                ids = "ledger-transaction";
                break;
            case "Journal Voucher":
                ids = "journal-voucher";
                break;
            case "Account Balance":
                ids = "account-balance";
                break;
            case "ERP Systems":
                ids = "erp-systems";
                break;
            case "Settings":
                ids = "settings";
                break;
            case "Posting Job":
                ids = "posting-job";
                break;
            case "Financial Reports":
                ids = "financial-reports";
                break;
            case "Contract Expiration Report":
                ids = "contract-expiration-report";
                break;
            case "DQI Reports":
                ids = "dqi-reports";
                break;
            case "Default Value Configs":
                ids = "default-value-configs";
                break;
            case "ERP Field Mapping":
                ids = "erp-field-mapping";
                break;
            case "Batch Job Profiles":
                ids = "batch-job-profiles";
                break;
            case "Report Profiles":
                ids = "report-profiles";
                break;
            case "Consolidated Transaction Report":
                ids = "consolidated-transaction-report";
                break;
            case "GL Balance Report":
                ids = "gl-balance-report";
                break;
        }
        return ids;

    }

    public String ItemCSSSelectorIds(String Option) {
        String ids = null;
        switch (Option) {
            case "Disclosure Profiles":
                ids = "disclosure-reports-profiles";
                break;
            case "Disclosure Jobs":
                ids = "disclosure-reports-jobs";
                break;
            case "Disclosure Schedule Job":
                ids = "disclosure-reports-scheduled-jobs";
                break;
            case "Activity Profiles":
                ids = "activity-analysis-reports-profiles";
                break;
            case "Activity Jobs":
                ids = "activity-analysis-reports-jobs";
                break;
            case "Activity Schedule Jobs":
                ids = "activity-analysis-reports-scheduled-jobs";
                break;
            case "Periodic Profiles":
                ids = "periodic-posting-status-reports-profiles";
                break;
            case "Periodic Jobs":
                ids = "periodic-posting-status-reports-jobs";
                break;
            case "Periodic Schedule Jobs":
                ids = "periodic-posting-status-reports-scheduled-jobs";
                break;
            case "Operational Profiles":
                ids = "operational-postings-profiles";
                break;
            case "Operational Jobs":
                ids = "operational-postings-jobs";
                break;
            case "Operational Schedule Jobs":
                ids = "operational-postings-scheduled-jobs";
                break;
            case "Indexation Profiles":
                ids = "mass-indexation-profiles";
                break;
            case "Indexation Jobs":
                ids = "mass-indexation-jobs";
                break;
            case "Indexation Scheduled Jobs":
                ids = "mass-indexation-scheduled-jobs";
                break;
            case "Workflow Profiles":
                ids = "mass-workflow-transition-profiles";
                break;
            case "Workflow Jobs":
                ids = "mass-workflow-transition-jobs";
                break;
            case "Modification Profiles":
                ids = "mass-modification-profiles";
                break;
            case "Modification Jobs":
                ids = "mass-modification-jobs";
                break;
            case "Inter Company Transfer Jobs":
                ids = "mass-inter-company-transfer-jobs";
                break;
            case "General ERP Settings":
                ids = "general-erp-settings";
                break;
            case "ERP System Settings":
                ids = "erp-system-settings";
                break;
            case "SAP Posting Profiles":
                ids = "posting-profiles";
                break;
            case "SAP Posting Jobs":
                ids = "posting-jobs";
                break;
            case "SAP Scheduled Jobs":
                ids = "posting-scheduled-jobs";
                break;
            case "Cashflow Report":
                ids = "cashflow-report";
                break;
            case "Income Statement Report":
                ids = "income-statement-report";
                break;
            case "Balance Sheet Report":
                ids = "balance-sheet-report";
                break;
            case "DQI AG Report":
                ids = "dqi-ag-report";
                break;
            case "DQI Contract Report":
                ids = "dqi-contract-report";
                break;
            case "User Auxiliary Batch Profiles":
                ids = "user-aux-batch-profile";
                break;
            case "Consolidated Jobs":
                ids = "consolidated-transaction-jobs";
                break;
            case "Consolidated Scheduled Jobs":
                ids = "consolidated-transaction-schedule-jobs";
                break;
            case "GL Jobs":
                ids = "gl-balance-report-jobs";
                break;
            case "GL Scheduled Jobs":
                ids = "gl-balance-report-schedule-job";
                break;
        }
        return ids;
    }
}
