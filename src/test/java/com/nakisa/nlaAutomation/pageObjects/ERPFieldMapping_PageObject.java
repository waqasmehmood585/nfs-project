package com.nakisa.nlaAutomation.pageObjects;

import com.amazonaws.services.dynamodbv2.xspec.S;
import io.cucumber.datatable.DataTable;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class ERPFieldMapping_PageObject extends Common_BasePage_PageObject {
    //public @FindBy(css = ".q-page .q-spinner") WebElement firstERPSpinner;
    public @FindBy(css = ".q-table__top #create-job-btn") WebElement addButton;
    public @FindBy(css = ".q-table__top #remove-btn") WebElement deleteBtn;
    public @FindBy(css = ".q-table #select-all-checkbox") WebElement selectAllCheckbox;
    //public @FindBy(css = ".q-table__bottom .q-table__control") WebElement numberOfRecords;
    public @FindBy(css = ".q-expansion-item__content #erp-system-ref") WebElement ERPSystemDropdown;
    public @FindBy(css = ".q-expansion-item__content #principal-position-type") WebElement principalPositionDropdown;
    public @FindBy(css = ".q-expansion-item__content #mapping-nla-table") WebElement NLATableDropdown;
    public @FindBy(css = ".q-expansion-item__content #mapping-nla-field") WebElement NLAFieldDropdown;
    public @FindBy(css = ".q-card #submit-btn") WebElement submitBtn;
    public @FindBy(css = ".q-card #submit-btn") WebElement popupSubmitBtn;
    public @FindBy(css = ".q-inner-loading .q-spinner") WebElement spinnerAfterSubmit;
    public @FindBy(css = ".q-notification .q-notification__wrapper") WebElement deleteSuccessMsg;
    public @FindBy(css = ".q-table__middle #erp-system") WebElement erpFilterDropdown;
    public @FindBy(css = ".q-table__middle #principal-position-type") WebElement principalPositionFilter;
    public @FindBy(css = ".q-tr .q-field .q-icon[type]") WebElement filterCrossBtn;
    public @FindBy(css = ".q-table__middle #source-table-name") WebElement NLATableFilterDropdown;
    public @FindBy(css = ".q-table__middle #source-field-name-caption-input") WebElement searchFilter;

    public ERPFieldMapping_PageObject() {
        super();
        log.info("Driver is inside this class: " + this.getClass().getSimpleName());
        PageFactory.initElements(driver, this);
    }

    public void waitForSpinner(String spinnerPath) {
        waitUntilLoadingSpinnerIsShown(spinnerPath);
        waitUntilLoadingSpinnerIsGone(spinnerPath);
    }

    public void creatingERPMapping(String principalPosition, DataTable dt) {
        try {
            List<Map<String, String>> ERPField = dt.asMaps(String.class, String.class);
            log.info("User will try to add ERP Field Mapping");
            List<String> erpSystems = Arrays.asList("FINQ8S-300", "QA300");
            if (Objects.equals(principalPosition, "Lessee") || Objects.equals(principalPosition, "Lessor")) {
                for (String erpSystem : erpSystems) {
                    for (int i = 0; i < 2; i++) {
                        waitTillWebElementIsVisible("addButton", addButton);
                        waitAndClickOnElement(addButton);
                        clickOnDropDownToTypeAndSelectValue("ERP System", ERPSystemDropdown, erpSystem);
                        clickOnDropDownAndSelectValue("Principal Position", principalPositionDropdown, principalPosition);
                        clickOnDropDownAndSelectValue("NLA Table", NLATableDropdown, ERPField.get(i).get("NLA Table"));
                        clickOnDropDownAndSelectValue("NLA Field", NLAFieldDropdown, ERPField.get(i).get("NLA Field"));
                        waitAndClickOnElement(submitBtn);
                        waitForSpinner("nlaERPSpinner");
                        log.info("ERP Field Mapping #" + (i + 1) + " added for ERP System: " + erpSystem);
                    }
                }
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int calculatingSize() {
        handleWait(800);
        int timeoutInSeconds = 10;
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".q-page tbody tr")));
        List<WebElement> rows = driver.findElements(By.cssSelector(".q-page tbody tr"));
        int numberOfRows = rows.size() - 1;
        System.out.println("Number of rows: " + numberOfRows);
        return numberOfRows;
    }

    public void deletingERPMapping() {
        try {
            log.info("Verifying if any field exists in table");
            waitTillWebElementIsVisible("addButton", addButton);
            waitForSpinner("nlaERPSpinner");
            waitTillWebElementIsVisible("tableBody", ".q-page tbody");
            int numberOfRows = calculatingSize();
            if (numberOfRows == 0) {
                log.info("No data exists in table!");
            } else if (numberOfRows > 0) {
                log.info("Data exists in table, deleting the entries...");
                waitAndClickOnElement(selectAllCheckbox);
                waitTillWebElementIsEnabled("Delete Button", ".q-table__top #remove-btn");
                waitAndClickOnElement(deleteBtn);
                waitAndClickOnElement(popupSubmitBtn);
                waitTillWebElementIsVisible("Delete Success Msg", deleteSuccessMsg);
                waitForSpinner("nlaERPSpinner");
                System.out.println("Number of rows: " + numberOfRows);
                log.info("Items have been deleted successfully");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void applyFilterAndValidate(String filterName, WebElement filterElement, String filterValue) {
        try {
            int sizeBefore = calculatingSize();
            log.info("Size before applying filter [" + filterName + "]: " + sizeBefore);
            if (Objects.equals(filterName, "Principal Position Dropdown")) {
                clickOnDropDownAndSelectValue(filterName, filterElement, filterValue);
            } else if (Objects.equals(filterName, "ERP Filter Dropdown")) {
                clickOnDropDownToTypeAndSelectValue(filterName, filterElement, filterValue);
            } else if (Objects.equals(filterName, "NLA Table Dropdown")) {
                clickOnDropDownAndSelectValue(filterName, filterElement, filterValue);
            }
            waitForSpinner("nlaERPSpinner");
            int sizeAfter = calculatingSize();
            log.info("Size after applying filter [" + filterName + "]: " + sizeAfter);
            if (sizeAfter < sizeBefore) {
                log.info(filterName + " filter is working fine.");
            } else {
                Assert.fail("Filters are not working");
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void verifySearch() {
        int sizeBefore = calculatingSize();
        log.info("Size before searching: " + sizeBefore);
        sendingValueToWebElement("Search Filter", searchFilter, "master");
        waitForSpinner("nlaERPSpinner");
        int sizeAfter = calculatingSize();
        if (sizeAfter < sizeBefore) {
            log.info("Search filter is working fine.");
        } else {
            Assert.fail("Search is not working");
        }
    }

    public void userValidatesFilters() {
        try {
            verifySearch();
            waitAndClickOnElement(filterCrossBtn);
            applyFilterAndValidate("Principal Position Dropdown", principalPositionFilter, "Lessee");
            applyFilterAndValidate("ERP Filter Dropdown", erpFilterDropdown, "FINQ8S-300");
            applyFilterAndValidate("NLA Table Dropdown", NLATableFilterDropdown, "Master Agreement");
            waitAndClickOnElement(filterCrossBtn);
            waitForSpinner("nlaERPSpinner");
            waitAndClickOnElement(filterCrossBtn);
            waitForSpinner("nlaERPSpinner");
            waitAndClickOnElement(filterCrossBtn);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
