package com.nakisa.nlaAutomation.pageObjects;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.directory.api.util.Strings;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.Zip;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.http.HttpClient;
import org.openqa.selenium.remote.http.HttpRequest;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;

import com.nakisa.nlaAutomation.stepDefinitions.MasterHooks;
import com.nakisa.nlaAutomation.utils.DriverFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.remote.http.Contents.asJson;
import static org.openqa.selenium.remote.http.Contents.string;
import static org.openqa.selenium.remote.http.HttpMethod.*;

@Slf4j
public class Common_BasePage_PageObject extends DriverFactory {

	protected WebDriver  driver = driverThread.getDriver();
	protected WebDriverWait wait;
	protected JavascriptExecutor jsExecutor;
	protected Actions builder;

	public @FindBy(css = ".q-dialog .q-card #submit-btn") WebElement submitButton;
	public @FindBy(css = ".q-dialog .q-card #cancel-btn") WebElement cancelButton;

	public Common_BasePage_PageObject() {
		this.wait = new WebDriverWait(driver, 60);
		this.jsExecutor = ((JavascriptExecutor) driver);
		this.builder = new Actions(driver);
	}

	public boolean waitTillWebElementIsVisible(String webElementName, WebElement webElement) {
		try {
			this.wait.until(ExpectedConditions.visibilityOf(webElement));
			logInfo("webElementIsVisible", webElementName, webElement);
			return true;
		} catch (Exception exception) {
			logError("webElementIsNotVisible", webElementName, webElement, exception);
			return false;
		}
	}

	public boolean waitTillWebElementIsVisible(String webElementName, String webElement) {
		try {
			this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(webElement)));
			logInfo("webElementIsVisible", webElementName, webElement);
			return true;
		} catch (Exception exception) {
			logError("webElementIsNotVisible", webElementName, webElement, exception);
			return false;
		}
	}

	public boolean waitForWebElementToDisappear(String webElementName, String webElement) {
		try {
			WebDriverWait wait= new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(webElement))));
			logInfo("webElementIsDisappear", webElementName, webElement);
			return true;
		} catch (Exception exception) {
			log.error(webElementName + "WebElement Not Disappear"+ webElementName);
			return false;
		}
	}
	public boolean waitUnTillWebElementIsVisible(String webElementName, String webElement) {
		try {
			WebDriverWait wait= new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(webElement)));
			logInfo("webElementIsVisible", webElementName, webElement);
			return true;
		} catch (Exception exception) {
			log.info(webElementName + "WebElement Not visible"+ webElementName);
			return false;
		}
	}

	public boolean waitTillWebElementIsEnabled(String webElementName, String webElement) {
		try {

			this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(webElement))).isEnabled();
			logInfo("webElementIsEnabled", webElementName, webElement);
			return true;
		} catch (Exception exception) {
			logError("webElementIsNotEnabled", webElementName, webElement, exception);
			return false;
		}
	}

	public boolean waitTillWebElementIsEnabled(String webElementName, WebElement webElement) {
		try {

			this.wait.until(ExpectedConditions.visibilityOf(webElement)).isEnabled();
			logInfo("webElementIsEnabled", webElementName, webElement);
			return true;
		} catch (Exception exception) {
			logError("webElementIsNotEnabled", webElementName, webElement, exception);
			return false;
		}
	}

	public void sendingValueToWebElement(String webElementName, WebElement webElement, String textToSend) throws AssertionError {
		try {
//			String regex = "\\d{4}-\\d{2}-\\d{2}";
//			if (textToSend.matches(regex)) {
//				textToSend = convertDate(textToSend);
//			}
			waitTillWebElementIsVisible(webElementName, webElement);
			if (!(webElementName.equalsIgnoreCase("contractRateField") || webElementName.equalsIgnoreCase("residualValueDefinition") ||
					webElementName.equalsIgnoreCase("dashboardTitle") || webElementName.equalsIgnoreCase("contentInfoCardChart") )) {
//				webElement.clear();
				clearField(webElement);
			}
			webElement.sendKeys(textToSend);
			logInfo("webElementValueIsSet", webElementName + "'s value '" + textToSend + "'", webElement);
		} catch (Exception exception) {
			logError("webElementValueIsNotSet", webElementName + "'s value '" + textToSend + "'", webElement, exception);
		}
	}

	public void actionMoveAndClick(String webElementName, WebElement webElement) throws AssertionError {
		Actions action = new Actions(driver);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(webElement)).isEnabled();
			action.moveToElement(webElement).click().build().perform();
			logInfo("webElementIsClicked", webElementName, webElement);
		} catch (StaleElementReferenceException staleElementReferenceException) {
			Boolean isWebElementPresent = wait.until(ExpectedConditions.elementToBeClickable(webElement)).isEnabled();
			if (isWebElementPresent) {
				action.moveToElement(webElement).click().build().perform();
				logInfo("webElementIsClicked", webElementName, webElement);
			}
		} catch (Exception exception) {
			logError("webElementIsNotClicked", webElementName, webElement, exception);
		}
	}

	public void clickOnDropDownAndSelectValue(String webElementName, WebElement webElement, String value) throws InterruptedException, IOException {
		waitTillWebElementIsVisible(webElementName, webElement);
		WaitUntilElementIsClickable(webElement);
		waitAndClickOnElement(webElement);
		if(webElementName.equalsIgnoreCase("initialState") || webElementName.equalsIgnoreCase("actionSteps")){
			handleWait(1000);
		}
		final String dropDownValues;
		dropDownValues=".q-menu .q-item:nth-child(1)";

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
		wait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				// Check if the dropdown is visible
				boolean isDropdownVisible = waitTillWebElementIsVisible("dropDownValue", dropDownValues);
				// Get the dropdown value
				String dropdownText = getValueFromElement(dropDownValues).trim();
				log.info("Dropdown Value: " + dropdownText);
				// If dropdown shows "No Results" or is empty, close and reopen it
				if (isDropdownVisible && (dropdownText.equalsIgnoreCase("No results") || dropdownText.isEmpty())) {
					log.info("Dropdown is empty or showing 'No Results'. Retrying...");
					// Close the dropdown
					webElement.click();
					handleWait(1000);
					// Reopen the dropdown
					webElement.click();
					return false; // Retry the condition
				}
				// Return true only when valid values are found
				return isDropdownVisible && !dropdownText.equalsIgnoreCase("No results") && !dropdownText.isEmpty();
			}
		});

		int dropDownOptionSize;
		dropDownOptionSize=getSizeOfElements(".q-menu .q-item");

		String optionToClickWithIndex="";
		String innerTextValue="";
		for (int cssIndex = 1; cssIndex <= dropDownOptionSize; cssIndex++) {
			innerTextValue="";
			innerTextValue = getValueFromElement(".q-menu .q-item:nth-child(" + cssIndex + ") .q-item__section--main");
			if (value.equals(innerTextValue)) {
				optionToClickWithIndex = ".q-menu .q-item:nth-child(" + cssIndex + ") .q-item__section--main";
				break;
			}
		}
		if(optionToClickWithIndex.equalsIgnoreCase("")){
			log.info("Trying for 2nd Time");
			dropDownOptionSize=getSizeOfElements(".q-menu .q-item");
			for (int cssIndex = 1; cssIndex <= dropDownOptionSize; cssIndex++) {
				innerTextValue = getValueFromElement(".q-menu .q-item:nth-child(" + cssIndex + ") .q-item__section--main");
				if (value.equals(innerTextValue)) {
					optionToClickWithIndex = ".q-menu .q-item:nth-child(" + cssIndex + ") .q-item__section--main";
					break;
				}
			}
		}
		if (optionToClickWithIndex.equalsIgnoreCase("")) {
			log.error("Dropdown value is not matching, expecting value should be > " + value);
		}
		waitAndClickOnElement(getElement(optionToClickWithIndex));
		if(getSizeOfElements(".q-card .dialog-header") == 1) {
			Wait<WebDriver> wait2 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					log.info("clicking on pop up header to close any missed opened dropdown after selecting");
					try {
						waitAndClickOnElement("popupHeader", ".q-card .dialog-header");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return getSizeOfElements(".q-menu[role='listbox']") == 0;
				}
			});
		}
	}
	public void clickOnDropDownAndSelectCheckBoxes(String webElementName, WebElement webElement, String values) throws InterruptedException, IOException {
		waitTillWebElementIsVisible(webElementName, webElement);
		WaitUntilElementIsClickable(webElement);
		waitAndClickOnElement(webElement);
		String dropDownValues = "[role='listbox'] .q-item:nth-child(1)";

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
		wait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				log.info("Waiting for DropDown to Open");
				boolean isDropdownVisible = waitTillWebElementIsVisible("dropDownValue", dropDownValues);
				// Get the dropdown value
				String dropdownText = getValueFromElement(dropDownValues).trim();
				log.info("Dropdown Value: " + dropdownText);
				// If dropdown shows "No Results" or is empty, close and reopen it
				if (isDropdownVisible && (dropdownText.equalsIgnoreCase("No results") || dropdownText.isEmpty())) {
					log.info("Dropdown is empty or showing 'No Results'. Retrying...");
					// Close the dropdown
					webElement.click();
					handleWait(1000);
					// Reopen the dropdown
					webElement.click();
					return false; // Retry the condition
				}
				return waitTillWebElementIsVisible("dropDownValue", dropDownValues) &&
						!(getValueFromElement(dropDownValues).equalsIgnoreCase("No results") &&
								getValueFromElement(dropDownValues).equalsIgnoreCase(""));
			}
		});
		int dropDownOptionSize = getSizeOfElements("[role='listbox'] .q-item");
		String[] fieldValuesLength = values.split(",");
		for (String value: fieldValuesLength){
			String optionToClickWithIndex="";
			for (int cssIndex = 1; cssIndex <= dropDownOptionSize; cssIndex++) {
				String innerTextValue="";
				innerTextValue = getValueFromElement("[role='listbox'] .q-virtual-scroll__content .q-item:nth-child(" + cssIndex + ") .q-item__section--main");
				if (value.equals(innerTextValue)) {
					optionToClickWithIndex = "[role='listbox'] .q-virtual-scroll__content .q-item:nth-child(" + cssIndex + ") .q-item__section--main";
					break;
				}
			}
			if (optionToClickWithIndex.equalsIgnoreCase("")) {
				log.error("Dropdown value is not matching, expecting value should be > " + value);
			}
			waitAndClickOnElement("DropdownValueSelected",optionToClickWithIndex);
			if(getSizeOfElements(".q-card .dialog-header") == 1 && value.equalsIgnoreCase(fieldValuesLength[fieldValuesLength.length-1])) {
				Wait<WebDriver> wait2 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
				wait.until(new Function<WebDriver, Boolean>() {
					@Override
					public Boolean apply(WebDriver driver) {
						log.info("clicking on pop up header to close any missed opened dropdown after selecting");
						try {
							waitAndClickOnElement("popupHeader", ".q-card .dialog-header");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						return getSizeOfElements(".q-menu[role='listbox']") == 0;
					}
				});
			}
		}
	}

	public void clickOnDropDownToTypeAndSelectValue(String webElementName, WebElement webElement, String value) throws InterruptedException, IOException {
		waitTillWebElementIsVisible(webElementName, webElement);
		WaitUntilElementIsClickable(webElement); //added new
		waitAndClickOnElement(webElement);

		String dropDownValues = ".q-menu .q-item:nth-child(1)";
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
		wait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				log.info("Waiting for Dropdown to Open after Click");
				boolean isDropdownVisible = waitTillWebElementIsVisible("dropDownValue", dropDownValues);
				// Get the dropdown value
				String dropdownText = getValueFromElement(dropDownValues).trim();
				log.info("Dropdown Value: " + dropdownText);
				// If dropdown shows "No Results" or is empty, close and reopen it
				if (isDropdownVisible && (dropdownText.equalsIgnoreCase("No results") || dropdownText.isEmpty())) {
					log.info("Dropdown is empty or showing 'No Results'. Retrying...");
					// Close the dropdown
					webElement.click();
					handleWait(1000);
					// Reopen the dropdown
					webElement.click();
					return false; // Retry the condition
				}
				return !(getValueFromElement(dropDownValues).equalsIgnoreCase("No results") &&
						getValueFromElement(dropDownValues).equalsIgnoreCase(""));
			}
		});
        waitTillWebElementIsVisible("countBeforeSearch",".q-menu .q-item--clickable");
		int countBeforeSearch = getSizeOfElements(".q-menu .q-item--clickable");
		if(countBeforeSearch!=1) {
			String searchedValue = null;
			WebElement inputField = getElement(".q-menu[role='listbox'] input");
			waitTillWebElementIsVisible("inputFiled", inputField);
			if (value.contains("-")) {
				searchedValue = value.substring(0, value.indexOf("-") - 1);
				sendingValueToWebElement(webElementName, getElement(".q-menu[role='listbox'] input"), searchedValue);
			} else {
				sendingValueToWebElement(webElementName, getElement(".q-menu[role='listbox'] input"), value);
			}

			if (!(webElementName.equalsIgnoreCase("activationGroupSplitSelector") || webElementName.equalsIgnoreCase("splitUnit"))
					|| webElementName.equalsIgnoreCase("aaPartner")) {
				if (countBeforeSearch > 1) {
					Wait<WebDriver> waitTemp =
							new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(100)).ignoring(WebDriverException.class);
					waitTemp.until(new Function<WebDriver, Boolean>() {
						@Override
						public Boolean apply(WebDriver driver) {
							log.info("Waiting for Dropdown to rearrange values after search");
							return countBeforeSearch != getSizeOfElements(".q-menu .q-item--clickable") &&
									!(getValueFromElement(".q-menu .q-item:nth-child(1) .q-item__section").equalsIgnoreCase("") &&
											getValueFromElement(".q-menu .q-item:nth-child(1) .q-item__section").equalsIgnoreCase("No results"));
						}
					});
				}
			}
		}

		int dropDownOptionSize = driver.findElements(By.cssSelector(".q-menu .q-item")).size();
		String optionToClickWithIndex = "";
		String innerTextValue="";
		for (int cssIndex = 1; cssIndex <= dropDownOptionSize; cssIndex++) {
			innerTextValue = getValueFromElement(".q-menu .q-item:nth-child(" + cssIndex + ") .q-item__section:nth-child(2)");
			if (innerTextValue.contains(value)) {
				optionToClickWithIndex = ".q-menu .q-item:nth-child(" + cssIndex + ") .q-item__section";
				break;
			}
		}
		if(optionToClickWithIndex.equalsIgnoreCase("")){
			log.info("Trying for 2nd Time");
			dropDownOptionSize=driver.findElements(By.cssSelector(".q-menu .q-item")).size();
			for (int cssIndex = 1; cssIndex <= dropDownOptionSize; cssIndex++) {
				innerTextValue =getValueFromElement(".q-menu .q-item:nth-child(" + cssIndex + ") .q-item__section");
				if (innerTextValue.contains(value)) {
					optionToClickWithIndex = ".q-menu .q-item:nth-child(" + cssIndex + ") .q-item__section";
					break;
				}
			}
		}
		if (optionToClickWithIndex.equalsIgnoreCase("")) {
			log.error("Dropdown value is not matching, expecting value should be > " + value);
		}
		waitAndClickOnElement("DropdownValueSelected",optionToClickWithIndex);
		if(getSizeOfElements(".q-card .dialog-header") == 1) {
			Wait<WebDriver> wait2 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					log.info("Closing dropdown values if its not closed already by clicking on pop up header");
					try {
						waitAndClickOnElement("popupHeader", ".q-card .dialog-header");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return getSizeOfElements(".q-menu[role='listbox']") == 0;
				}
			});
		}
	}
	public boolean clickOnDropDownToTypeAndCheckEnableValue(String webElementName, WebElement webElement, String value) throws InterruptedException, IOException {
		waitTillWebElementIsVisible(webElementName, webElement);
		WaitUntilElementIsClickable(webElement); //added new
		webElement.click();
		Thread.sleep(500);

		String dropDownValues = ".q-menu .q-item:nth-child(1)";
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
		wait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				boolean isDropdownVisible = waitTillWebElementIsVisible("dropDownValue", dropDownValues);
				// Get the dropdown value
				String dropdownText = getValueFromElement(dropDownValues).trim();
				log.info("Dropdown Value: " + dropdownText);
				// If dropdown shows "No Results" or is empty, close and reopen it
				if (isDropdownVisible && (dropdownText.equalsIgnoreCase("No results") || dropdownText.isEmpty())) {
					log.info("Dropdown is empty or showing 'No Results'. Retrying...");
					// Close the dropdown
					webElement.click();
					handleWait(1000);
					// Reopen the dropdown
					webElement.click();
					return false; // Retry the condition
				}
				if (!driver.findElement(By.cssSelector(dropDownValues)).getText().equalsIgnoreCase("No results")) {
					return true;
				}
				return false;
			}
		});

		WebElement inputField=getElement(".q-menu[role='listbox'] input");
		waitTillWebElementIsVisible("inputFiled",inputField);
		sendingValueToWebElement(webElementName, inputField, value);
		Thread.sleep(1000);
		if(getSizeOfElements(".q-menu .q-item[aria-disabled='true']")==1){
			return true;
		}
		return false;
	}
	public void clickOnDropDownToTypeAndSelectCheckBox(String webElementName, WebElement webElement, String values) throws InterruptedException, IOException {
		waitTillWebElementIsVisible(webElementName, webElement);
		WaitUntilElementIsClickable(webElement); //added new
		webElement.click();

		String dropDownValues = ".q-menu .q-item:nth-child(1)";
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(40)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
		wait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				log.info("Waiting for dropdown values to show");
				boolean isDropdownVisible = waitTillWebElementIsVisible("dropDownValue", dropDownValues);
				// Get the dropdown value
				String dropdownText = getValueFromElement(dropDownValues).trim();
				log.info("Dropdown Value: " + dropdownText);
				// If dropdown shows "No Results" or is empty, close and reopen it
				if (isDropdownVisible && (dropdownText.equalsIgnoreCase("No results") || dropdownText.isEmpty())) {
					log.info("Dropdown is empty or showing 'No Results'. Retrying...");
					// Close the dropdown
					webElement.click();
					handleWait(1000);
					// Reopen the dropdown
					webElement.click();
					return false; // Retry the condition
				}
				return !(getValueFromElement(dropDownValues).equalsIgnoreCase("No results") &&
						getValueFromElement(dropDownValues).equalsIgnoreCase(""));
			}
		});

		String[] fieldValuesLength = values.split(",");
		for (String value: fieldValuesLength){
			clearField(getElement(".q-menu label.q-field input"));
			if(fieldValuesLength.length!=1) {
				handleWait(3000);
			}
			int countBeforeSearch = driver.findElements(By.cssSelector(".q-menu .q-item .q-checkbox")).size();
			if(countBeforeSearch!=1) {
				String searchedValue = null;
				if (value.contains("-")) {
					if (!(value.contains("AG-") || value.contains("MA-") || value.contains("LC-") || value.contains("CT-") || value.contains("UN-"))) {
						if (webElementName.equalsIgnoreCase("companyCodes")) {
							searchedValue = value.substring(value.lastIndexOf("-") + 2);
						} else {
							searchedValue = value.substring(0, value.indexOf("-") - 1);
						}
						sendingValueToWebElement(webElementName, getElement(".q-menu label.q-field input"), searchedValue);
					} else {
						sendingValueToWebElement(webElementName, getElement(".q-menu label.q-field input"), value);
					}
				} else {
					sendingValueToWebElement(webElementName, getElement(".q-menu label.q-field input"), value);
				}
				if (countBeforeSearch > 1) {
					Wait<WebDriver> waitTemp =
							new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(100)).ignoring(WebDriverException.class);
					waitTemp.until(new Function<WebDriver, Boolean>() {
						@Override
						public Boolean apply(WebDriver driver) {
							log.info("Waiting for dropdown values to rearrange");
							int afterSearchSize = getSizeOfElements(".q-menu .q-item .q-checkbox");
							String firstDropDownElement = ".q-item.q-px-sm:nth-child(1)  .q-item__section--main";
							return countBeforeSearch != afterSearchSize && !(getValueFromElement(firstDropDownElement).equalsIgnoreCase("No results") &&
									getValueFromElement(firstDropDownElement).equalsIgnoreCase(""));
						}
					});
				}
			}

			int dropDownOptionSize = getSizeOfElements(".q-menu .q-item");
			String optionToClickWithIndex = "";
			for (int cssIndex = 1; cssIndex <= dropDownOptionSize; cssIndex++) {
				String innerTextValue = getValueFromElement(".q-item.q-px-sm:nth-child("+cssIndex+")  .q-item__section--main");
				if (innerTextValue.contains(value)) {
					optionToClickWithIndex = ".q-item.q-px-sm:nth-child("+cssIndex+") .q-item__section--main";
					break;
				}
			}
			if (optionToClickWithIndex.equalsIgnoreCase("")) {
				log.error("Dropdown value is not matching, expecting value should be > " + value);
			}
//			if(!webElementName.equalsIgnoreCase("objectListMassIndexation")) {
				waitAndClickOnElement("DropdownValueSelected", optionToClickWithIndex);
//			}
			if(driver.findElements(By.cssSelector(".q-card .dialog-header")).size() == 1 && value.equalsIgnoreCase(fieldValuesLength[fieldValuesLength.length-1])) {
				Wait<WebDriver> wait2 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
				wait.until(new Function<WebDriver, Boolean>() {
					@Override
					public Boolean apply(WebDriver driver) {
						log.info("Closing dropdown values if its not closed already by clicking on pop up header");
						try {
							waitAndClickOnElement("popupHeader", ".q-card .dialog-header");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						return getSizeOfElements(".q-menu[role='listbox']") == 0;
					}
				});
			}

		}

	}

	public void waitAndClickOnElement(String webElementName, String cssPathToClick) throws InterruptedException {
		Wait<WebDriver> tempWait = new WebDriverWait(driver, 5);
		boolean clicked = false;
		int attempts = 0;
		Exception exceptionError = null;
		while (!clicked && attempts < 10) {
			try {
				tempWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssPathToClick)));
				driver.findElement(By.cssSelector(cssPathToClick)).click();
				logInfo("webElementIsClicked", webElementName, cssPathToClick);
				clicked = true;
			} catch (Exception exception) {
				exceptionError = exception;
				logInfo("webElementIsClicked", webElementName, cssPathToClick);
				log.info("Clicking attempt is > " + attempts);
				attempts++;
			}
		}
		if (attempts >= 9) {
			logError("webElementIsNotClicked", webElementName, cssPathToClick, exceptionError);
		}
	}

	public void waitAndClickOnElement(WebElement element) throws InterruptedException {
		Wait<WebDriver> tempWait = new WebDriverWait(driver, 2);
		boolean clicked = false;
		int attempts = 0;
		Exception exceptionError = null;
		while (!clicked && attempts < 10) {
			try {
				tempWait.until(ExpectedConditions.elementToBeClickable(element));
				element.click();
				System.out.println("Element has been clicked, Element :"+ element.toString());
				clicked = true;
			} catch (Exception exception) {
				exceptionError = exception;
				System.out.println("Unable to wait and click on WebElement, Exception: " + exception.getMessage());
				log.info("Clicking attempt is > " + attempts);
				attempts++;
			}
		}
		if (attempts >= 9) {
			System.err.println("unable to click: " + getSelectorOfElement(element));
		}
	}

	public static String getSelectorOfElement(WebElement element) {
		String str = element.toString();
		return str.substring(str.indexOf("selector:") + 9, str.length() - 1);
	}

	public void clickOnSubmitPopup(String clickOnWhichButton) {
		if (clickOnWhichButton.equalsIgnoreCase("Submit / Add")) {
			try {
				this.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".q-dialog .q-card .q-btn--actionable#submit-btn")));
			} catch (Exception exception) {
				log.error("Unable to click on the submit button");
				Assert.fail("Unable to click on the submit button " + exception.getMessage());
			}

			//
			String submitButton = ".q-dialog .q-card #submit-btn";
			WaitUntilElementIsClickable(driver.findElement(By.cssSelector(submitButton)));
			try {
				waitAndClickOnElement(driver.findElement(By.cssSelector(submitButton)));
			} catch (InterruptedException e) {e.printStackTrace();}
			waitUntilLoadingSpinnerIsGone("nlaDropDownPopUp");
		} else if(clickOnWhichButton.equalsIgnoreCase("Batch / Submit")){
			try {
				this.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".q-dialog .q-card button[v-css-selectors='submit-btn']")));
			} catch (Exception exception) {
				log.error("Unable to click on the submit button");
				Assert.fail("Unable to click on the submit button " + exception.getMessage());
			}

			//WaitUntilElementIsClickable(submitButton);
			String submitButton = ".q-dialog .q-card button[v-css-selectors='submit-btn']";
			driver.findElement(By.cssSelector(submitButton)).click();
			waitUntilLoadingSpinnerIsGone("nlaDropDownPopUp");
		}
	}


	public boolean isWebElementDisplayed(String webElementName, WebElement webElement) {
		return webElement.isDisplayed();
	}

	public String beautifyWebElement(WebElement webElement) {
		if (webElement.toString().contains("By.cssSelector")) {
			return webElement.toString().substring(webElement.toString().indexOf("cssSelector"), webElement.toString().length() - 1);
		} else {
			return "cssSelector".concat(webElement.toString().substring(webElement.toString().indexOf("css selector") + 12, webElement.toString().length() - 1));
		}
	}

	public String convertWebElementIntoString(WebElement webElement) {
		return webElement.toString().substring(webElement.toString().indexOf("css selector:") + 14, webElement.toString().length() - 1);
	}

	public void logInfo(String type, String webElementName, WebElement webElement) {
		switch (type) {
			case "webElementIsVisible":
				log.info("This web-element " + webElementName + " (" + beautifyWebElement(webElement) + ") is visible");
				break;
			case "webElementValueIsSet":
				log.info("For this web-element " + webElementName + " (" + beautifyWebElement(webElement) + ") is set");
				break;
			case "webElementIsClicked":
				log.info("This web-element " + webElementName + " (" + beautifyWebElement(webElement) + ") is clicked");
				break;
		}
	}

	public void logError(String type, String webElementName, WebElement webElement, Exception exception) {
		switch (type) {
			case "webElementIsNotVisible":
				log.error("This web-element " + webElementName + " (" + beautifyWebElement(webElement) + ") is not visible");
				Assert.fail("This web-element " + webElementName + " (" + beautifyWebElement(webElement) + ") is not visible \n" + exception.getMessage());
				break;
			case "webElementValueIsNotSet":
				log.error("For this web-element " + webElementName + " (" + beautifyWebElement(webElement) + ") is not set");
				Assert.fail("For this web-element " + webElementName + " (" + beautifyWebElement(webElement) + ") is not set \n" + exception.getMessage());
				break;
			case "webElementIsNotClicked":
				log.error("This web-element " + webElementName + " (" + beautifyWebElement(webElement) + ") is having an issue while clicking");
				Assert.fail("This web-element " + webElementName + " (" + beautifyWebElement(webElement) + ") is having an issue while clicking \n" + exception.getMessage());
				break;
		}
	}

	public void logInfo(String type, String webElementName, String webElement) {
		switch (type) {
			case "webElementIsVisible":
				log.info("This web-element " + webElementName + " (cssSelector: " + webElement + ") is visible");
				break;
			case "webElementValueIsSet":
				log.info("For this web-element " + webElementName + " (cssSelector: " + webElement + ") is set");
				break;
			case "webElementIsClicked":
				log.info("This web-element " + webElementName + " (cssSelector: " + webElement + ") is clicked");
				break;
		}
	}

	public void logError(String type, String webElementName, String webElement, Exception exception) {
		switch (type) {
			case "webElementIsNotVisible":
				log.error("This web-element " + webElementName + " (cssSelector: " + webElement + ") is not visible");
				Assert.fail("This web-element " + webElementName + " (cssSelector: " + webElement + ") is not visible \n" + exception.getMessage());
				break;
			case "webElementValueIsNotSet":
				log.error("For this web-element " + webElementName + " (cssSelector: " + webElement + ") is not set");
				Assert.fail("For this web-element " + webElementName + " (cssSelector: " + webElement + ") is not set \n" + exception.getMessage());
				break;
			case "webElementIsNotClicked":
				log.error("This web-element " + webElementName + " (cssSelector: " + webElement + ") is having an issue while clicking");
				Assert.fail("This web-element " + webElementName + " (cssSelector: " + webElement + ") is having an issue while clicking \n" + exception.getMessage());
				break;
		}
	}

	public void waitUntilLoadingSpinnerIsShown(String loadingSpinnerPath) {
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(100)).ignoring(WebDriverException.class);
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					if (loadingSpinnerPath.equalsIgnoreCase("ncpTableLoader")) {
						log.info("Waiting for NCP table loader to show");
						return driver.findElements(By.cssSelector("#app .application--wrap li:nth-child(1) table tbody tr")).size() == 1;
					} else if (loadingSpinnerPath.equalsIgnoreCase("nlaLoadingPage")) {
						log.info("Waiting for NLA page loading spinner to show");
						return driver.findElements(By.cssSelector("#q-app .q-page-container .search-results-container .q-inner-loading")).size() == 1;
					} else if (loadingSpinnerPath.equalsIgnoreCase("nlaDropDownPopUp")) {
						log.info("Waiting for NLA dropdown pop-up to show");
						return driver.findElements(By.cssSelector(".q-dialog .q-card")).size() == 1;
					} else if (loadingSpinnerPath.equalsIgnoreCase("nlaWorkFlowLoader")) {
						log.info("Waiting for NLA workflow loader to show");
						return driver.findElements(By.cssSelector("#q-app .q-page-container .q-card--bordered .q-spinner")).size() == 1;
					}else if (loadingSpinnerPath.equalsIgnoreCase("nlaAddButtonLoader")) {
						log.info("Waiting for NLA add button loader to show");
						return driver.findElements(By.cssSelector(".q-dialog .q-card .q-btn .absolute-full .q-spinner")).size() == 1;
					}else if (loadingSpinnerPath.equalsIgnoreCase("nlaRevisionButtonLoader")) {
						log.info("Waiting for NLA revision button loader to show");
						return driver.findElements(By.cssSelector(".q-menu .q-list .q-item .q-spinner")).size() == 1;
					} else if (loadingSpinnerPath.equalsIgnoreCase("nlaCalenderLoader")) {
						log.info("Waiting for NLA Calender to show");
						return driver.findElements(By.cssSelector(".q-page .q-field [role='presentation'].mdi-calendar")).size() >= 1;
					}  else if (loadingSpinnerPath.equalsIgnoreCase("nlaFieldSpinner")) {
						log.info("Waiting for NLA Field Spinner to show");
						return driver.findElements(By.cssSelector(".q-dialog .q-card .q-field .q-spinner")).size() == 1;
					} else if (loadingSpinnerPath.equalsIgnoreCase("nlaJournalSpinner")) {
						log.info("Waiting for NLA Journal Spinner to show");
						return driver.findElements(By.cssSelector(".q-dialog .q-inner-loading .q-spinner")).size() == 1;
					}else if (loadingSpinnerPath.equalsIgnoreCase("nlaAlertMessage")) {
						log.info("Waiting for NLA Alert Message to Show");
						return driver.findElements(By.cssSelector(".desktop .q-notifications .q-notification[role='alert']")).size() == 1;
					}else if (loadingSpinnerPath.equalsIgnoreCase("nlaERPSpinner")) {
						log.info("Waiting for NLA ERP Spinner to Show");
						return driver.findElements(By.cssSelector(".q-page .q-inner-loading .q-spinner")).size() == 1;
					}else if (loadingSpinnerPath.equalsIgnoreCase("nlaGlobalSearchSpinner")){
						log.info("Waiting for NLA Global Search Loader to Show");
						return driver.findElements(By.cssSelector("#q-app .q-list .q-spinner")).size() == 1;
					}else if (loadingSpinnerPath.equalsIgnoreCase("nlaTabChange")){
						log.info("Waiting for NLA Tab Change to show");
						return driver.findElements(By.cssSelector("#q-app .q-page .q-panel:nth-child(2)[role='tabpanel']")).size() == 1;
					}else if (loadingSpinnerPath.equalsIgnoreCase("nlaAGWorkFlowLoader")){
						log.info("Waiting for NLA AG WorkFlow Loader to show");
						return driver.findElements(By.cssSelector("#q-app .q-page-container .q-card--bordered .q-spinner")).size() == 1;
					}else if (loadingSpinnerPath.equalsIgnoreCase("nlaBatchJobListLoader")){
						log.info("Waiting for NLA Batch Job List Loader to show");
						return driver.findElements(By.cssSelector("#q-app .q-page-container .q-spinner")).size() == 1;
					}else if (loadingSpinnerPath.equalsIgnoreCase("excelUploadLoader")){
						log.info("Waiting for NLA LC Import Loader to show");
						return driver.findElements(By.cssSelector("#import-menu-btn .q-uploader__overlay .q-spinner")).size() == 1;
					}else if (loadingSpinnerPath.equalsIgnoreCase("activationGroupWizards")){
						log.info("Waiting for NLA AG Wizard to show");
						return driver.findElements(By.cssSelector("#q-app  .q-page-container [from='Wizards']")).size() == 1;
					}else if (loadingSpinnerPath.equalsIgnoreCase("downloadLoader")){
						log.info("Waiting for NLA Download Loader to show");
						return driver.findElements(By.cssSelector("#q-app .jobs-grid tr:nth-child(2) .q-spinner")).size() == 1;
					}else if (loadingSpinnerPath.equalsIgnoreCase("nlaListLoader")) {
						log.info("Waiting for NLA List Loader to show");
						return driver.findElements(By.cssSelector("#q-app .q-page-container .q-inner-loading .q-spinner")).size() == 1;
					} else if (loadingSpinnerPath.equalsIgnoreCase("nlaDashboardPageLoader")) {
						log.info("Waiting for NLA Dashboard Loader to show");
						return driver.findElements(By.cssSelector("#q-app .q-page-container .q-inner-loading")).size() == 1;
					} else if (loadingSpinnerPath.equalsIgnoreCase("nlaChartSaveLoader")) {
						log.info("Waiting for NLA Chart Save Loader to show");
						return driver.findElements(By.cssSelector(".q-loading .q-loading__box")).size() == 1;
					} else if (loadingSpinnerPath.equalsIgnoreCase("nlaPopupLoader")) {
						log.info("Waiting for NLA Popup Loader to show");
						return driver.findElements(By.cssSelector(".q-dialog .q-card .q-spinner")).size() == 1;
					}else if (loadingSpinnerPath.equalsIgnoreCase("nlaScheduleLoader")) {
						log.info("Waiting for NLA Schedule Loader to show");
						return driver.findElements(By.cssSelector(".nfs-main-layout-page-container .schedules-area .q-spinner")).size() == 1;
					}else if (loadingSpinnerPath.equalsIgnoreCase("nlaAGCloseLoader")) {
						log.info("Waiting for AG Close Loader to show");
						return driver.findElements(By.cssSelector(".q-dialog .dialog-footer .q-btn .q-spinner")).size() == 1;
					}else if (loadingSpinnerPath.equalsIgnoreCase("sapReportPageLoader")) {
						log.info("Waiting for SAP Report Page Loader to show");
						return driver.findElements(By.cssSelector("#q-app .q-page-container .q-spinner")).size() == 1;
					}else if (loadingSpinnerPath.equalsIgnoreCase("batchJobGridLoader")) {
						log.info("Waiting for Batch Job Grid Loader to show");
						return driver.findElements(By.cssSelector("#q-app .q-page .jobs-grid .q-spinner")).size() == 1;
					}
					return false;
				}
			});
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void waitUntilLoadingSpinnerIsGone(String loadingSpinnerPath) {
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(800)).pollingEvery(Duration.ofMillis(100)).ignoring(WebDriverException.class);
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					if (loadingSpinnerPath.equalsIgnoreCase("ncpTableLoader")) {
						log.info("Waiting for NCP table loader to go");
						return driver.findElements(By.cssSelector("#app .application--wrap li:nth-child(1) table tbody tr")).size() > 1;
					} else if (loadingSpinnerPath.equalsIgnoreCase("nlaLoadingPage")) {
						log.info("Waiting for NLA page loading spinner to go");
						return driver.findElements(By.cssSelector("#q-app .q-page-container .search-results-container .q-inner-loading")).size() == 0;
					} else if (loadingSpinnerPath.equalsIgnoreCase("nlaDropDownPopUp")) {
						log.info("Waiting for NLA dropdown pop-up to go");
						return driver.findElements(By.cssSelector(".q-dialog .q-card")).size() == 0;
					} else if (loadingSpinnerPath.equalsIgnoreCase("nlaWorkFlowLoader")) {
						log.info("Waiting for NLA workflow loader to go");
						return driver.findElements(By.cssSelector("#q-app .q-page-container .q-card--bordered .q-spinner")).size() == 0;
					}else if (loadingSpinnerPath.equalsIgnoreCase("nlaERPSpinner")) {
						log.info("Waiting for NLA ERP Spinner to go");
						return driver.findElements(By.cssSelector(".q-page .q-inner-loading .q-spinner")).size() == 1;
					}else if (loadingSpinnerPath.equalsIgnoreCase("nlaAddButtonLoader")) {
						log.info("Waiting for NLA add button loader to go");
						return driver.findElements(By.cssSelector(".q-dialog .q-card .q-btn .absolute-full .q-spinner")).size() == 0;
					}else if (loadingSpinnerPath.equalsIgnoreCase("nlaRevisionButtonLoader")) {
						log.info("Waiting for NLA revision button loader to go");
						return driver.findElements(By.cssSelector(".q-menu .q-list .q-item .q-spinner")).size() == 0;
					}else if (loadingSpinnerPath.equalsIgnoreCase("nlaCalenderLoader")) {
						log.info("Waiting for NLA Calender to go");
						return driver.findElements(By.cssSelector(".q-page .q-field [role='presentation'].mdi-calendar")).size() == 0;
					}else if (loadingSpinnerPath.equalsIgnoreCase("nlaFieldSpinner")) {
						log.info("Waiting for NLA Field Spinner to go");
						return driver.findElements(By.cssSelector(".q-dialog .q-card .q-field .q-spinner")).size() == 0;
					} else if (loadingSpinnerPath.equalsIgnoreCase("nlaJournalSpinner")) {
						log.info("Waiting for NLA Journal Spinner to go");
						return driver.findElements(By.cssSelector(".q-dialog .q-inner-loading .q-spinner")).size() == 0;
					}else if (loadingSpinnerPath.equalsIgnoreCase("nlaAlertMessage")) {
						log.info("Waiting for NLA Alert Message to go");
						return driver.findElements(By.cssSelector(".desktop .q-notifications .q-notification[role='alert']")).size() == 0;
					}else if (loadingSpinnerPath.equalsIgnoreCase("nlaGlobalSearchSpinner")) {
						log.info("Waiting for NLA Global Search Loader to go");
						return driver.findElements(By.cssSelector("#q-app .q-list .q-spinner")).size() == 0;
					}else if (loadingSpinnerPath.equalsIgnoreCase("nlaTabChange")) {
						log.info("Waiting for NLA Tab Change to go");
						return driver.findElements(By.cssSelector("#q-app .q-page .q-panel:nth-child(2)[role='tabpanel']")).size() == 0;
					}else if (loadingSpinnerPath.equalsIgnoreCase("nlaAGWorkFlowLoader")) {
						log.info("Waiting for NLA AG WorkFlow Loader to go");
						return driver.findElements(By.cssSelector("#q-app .q-page-container .q-card--bordered .q-spinner")).size() == 0;
					}else if (loadingSpinnerPath.equalsIgnoreCase("nlaBatchJobListLoader")) {
						log.info("Waiting for NLA Batch Job List Loader to go");
						return driver.findElements(By.cssSelector("#q-app .q-page-container .q-spinner")).size() == 0;
					}else if (loadingSpinnerPath.equalsIgnoreCase("excelUploadLoader")) {
						log.info("Waiting for NLA LC Import Loader to go");
						return driver.findElements(By.cssSelector("#import-menu-btn .q-uploader__overlay .q-spinner")).size() == 0;
					}else if (loadingSpinnerPath.equalsIgnoreCase("downloadLoader")) {
						log.info("Waiting for NLA Download Loader to go");
						return driver.findElements(By.cssSelector("#q-app .jobs-grid tr:nth-child(2) .q-spinner")).size() == 0;
					}else if (loadingSpinnerPath.equalsIgnoreCase("nlaListLoader")) {
						log.info("Waiting for NLA List Loader to go");
						return driver.findElements(By.cssSelector("#q-app .q-page-container .q-inner-loading .q-spinner")).size() == 0;
					}else if (loadingSpinnerPath.equalsIgnoreCase("nlaDashboardPageLoader")) {
						log.info("Waiting for NLA Dashboard Loader to go");
						return driver.findElements(By.cssSelector("#q-app .q-page-container .q-inner-loading")).size() == 0;
					} else if (loadingSpinnerPath.equalsIgnoreCase("nlaChartSaveLoader")) {
						log.info("Waiting for NLA Chart Save Loader to go");
						return driver.findElements(By.cssSelector(".q-loading .q-loading__box")).size() == 0;
					} else if (loadingSpinnerPath.equalsIgnoreCase("nlaPopupLoader")) {
						log.info("Waiting for NLA Popup Loader to go");
						return driver.findElements(By.cssSelector(".q-dialog .q-card .q-spinner")).size() == 0;
					}else if (loadingSpinnerPath.equalsIgnoreCase("nlaScheduleLoader")) {
						log.info("Waiting for NLA Schedule Loader to go");
						return driver.findElements(By.cssSelector(".nfs-main-layout-page-container .schedules-area .q-spinner")).size() == 0;
					}else if (loadingSpinnerPath.equalsIgnoreCase("nlaAGCloseLoader")) {
						log.info("Waiting for AG Close Loader to go");
						return (driver.findElements(By.cssSelector(".q-dialog .dialog-footer .q-btn .q-spinner")).isEmpty() &&
                                driver.findElements(By.cssSelector(".q-dialog .dialog-footer .q-inner-loading .q-spinner")).isEmpty());
					} else if (loadingSpinnerPath.equalsIgnoreCase("sapReportPageLoader")) {
						log.info("Waiting for SAP Report Page Loader to go");
						return driver.findElements(By.cssSelector("#q-app .q-page-container .q-spinner")).size() == 0;
					}else if (loadingSpinnerPath.equalsIgnoreCase("batchJobGridLoader")) {
						log.info("Waiting for Batch Job Grid Loader to go");
						return driver.findElements(By.cssSelector("#q-app .q-page .jobs-grid .q-spinner")).size() == 0;
					}
					return false;
				}
			});
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void waitForClickablility(String cssPath) {
		try {
			this.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(cssPath)));
			this.wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssPath)));
		} catch (Exception exception) {
			log.error("Unable to click on the element");
			Assert.fail("Unable to click on the element " + exception.getMessage());
		}
	}

	public void WaitUntilElementIsClickable(WebElement element) {
		try {
			this.wait.until(ExpectedConditions.elementToBeClickable(element));
			System.out.println("WebElement is clickable using locator: " + "<" + element.toString() + ">");
		} catch (StaleElementReferenceException elementUpdated) {
			WebElement staleElement = element;
			Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(staleElement)).isEnabled();
			if (elementPresent == true) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", elementPresent);
				System.out.println("(Stale Exception) Successfully JS clicked on the following WebElement: " + "<" + element.toString() + ">");
			}
		} catch (Exception e) {
			System.out.println("WebElement is NOT clickable using locator: " + "<" + element.toString() + ">");
			Assert.fail("WebElement is NOT clickable using locator: " + e.getMessage());
		}
	}

	public void changeWorkFlowState(String entityLevel, String workFlowStateToChange) throws InterruptedException {
		log.info("Clicking on the " + workFlowStateToChange + " workflow button");
		String mlaStatus = null;
		String entityStatus = null;
		String finalWorkFlowPath = ".q-page-container .q-card .q-btn--actionable#event-id-" + workFlowStateToChange;
		waitTillWebElementIsVisible("workFlowStateToChange", finalWorkFlowPath);

//		WaitUntilElementIsClickable(driver.findElement(By.cssSelector(finalWorkFlowPath)));
//		waitForClickablility(finalWorkFlowPath);
		waitAndClickOnElement(driver.findElement(By.cssSelector(finalWorkFlowPath)));
		if (workFlowStateToChange.equalsIgnoreCase("mla-approve-btn") || workFlowStateToChange.equalsIgnoreCase("contract-approve-btn") ||
				workFlowStateToChange.equalsIgnoreCase("lc-approve-btn") ||	workFlowStateToChange.equalsIgnoreCase("ag-approve-btn")||
				workFlowStateToChange.equalsIgnoreCase("contract-rework-btn")|| workFlowStateToChange.equalsIgnoreCase("mla-rework-btn") ||
				workFlowStateToChange.equalsIgnoreCase("lc-rework-btn") || workFlowStateToChange.equalsIgnoreCase("ag-classified-rework-btn")
				|| workFlowStateToChange.equalsIgnoreCase("ag-reject-btn") || workFlowStateToChange.equalsIgnoreCase("ag-assessment-rework-btn")
		        || workFlowStateToChange.equalsIgnoreCase("mla-discard-btn") ) {

			if(!waitUnTillWebElementIsVisible("CommentPopUp",".q-dialog .dialog-header .text-h6 div")){
				waitAndClickOnElement(driver.findElement(By.cssSelector(finalWorkFlowPath)));
				waitTillWebElementIsVisible("CommentPopUp",".q-dialog .dialog-header .text-h6 div");
			}
			String headerValue = driver.findElement(By.cssSelector(".q-dialog .dialog-header .text-h6 div")).getText();
			if(headerValue.contains("Modification Summary")){
//				waitUntilLoadingSpinnerIsShown("nlaAddButtonLoader");
				waitUntilLoadingSpinnerIsGone("nlaAddButtonLoader");
				String approveButtonEvent = ".q-dialog .q-card #delta-submit-btn";
				waitTillWebElementIsVisible("approveButtonEvent", approveButtonEvent);
				driver.findElement(By.cssSelector(approveButtonEvent)).click();
			}
			String approveButton = ".q-dialog .q-card #discussion-submit-btn";
			waitTillWebElementIsVisible("approveButton", approveButton);
			driver.findElement(By.cssSelector(approveButton)).click();
		}
		waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
		
		switch(workFlowStateToChange){
			case "ag-activate-btn":
				int navSize = driver.findElements(By.cssSelector("#q-app .q-drawer #schedule-nav-expansion  .q-list .q-item")).size();
				boolean flag=false;
				if(navSize>=2){
					if(getValueFromElement("#q-app .q-drawer #schedule-nav-expansion  .q-list .q-item:nth-child(2) .q-item__section--main").equalsIgnoreCase("GAAP - Service Contract")){
						flag=true;
					}
				}
				if(!flag) {
					clickOnSubmitPopup("Submit / Add");
				}
				break;
			case "ag-close-btn":
				int maxRetries = 4;
				boolean clicked = false;

				for (int attempt = 1; attempt <= maxRetries; attempt++) {
					try {
						waitUntilLoadingSpinnerIsShown("nlaAGCloseLoader");
						waitUntilLoadingSpinnerIsGone("nlaAGCloseLoader");

						// ✅ if Submit button is visible
						if (waitUntilElementIsShown(".q-dialog .q-card .q-btn--actionable#submit-btn", 6)) {
							clickOnSubmitPopup("Submit / Add");
							clicked = true;
							break; // success, stop retrying
						}

						// ❌ fallback: cancel and reopen workflow
						waitAndClickOnElement("cancelButton", ".q-dialog .q-card .q-btn--actionable#close-btn");
						waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");

						handleWait(5000); // small buffer wait
						waitAndClickOnElement(driver.findElement(By.cssSelector(finalWorkFlowPath)));

						waitUntilLoadingSpinnerIsShown("nlaAGCloseLoader");
						waitUntilLoadingSpinnerIsGone("nlaAGCloseLoader");

						if (waitUntilElementIsShown(".q-dialog .q-card .q-btn--actionable#submit-btn", 10)) {
							clickOnSubmitPopup("Submit / Add");
							clicked = true;
							break;
						}

					} catch (Exception e) {
						log.info("Attempt {} failed for ag-close-btn submit", attempt);
						if (attempt < maxRetries) {
							handleWait(2000); // short wait before retry
						}
					}
				}

				if (!clicked) {
					throw new RuntimeException("Failed to click Submit button after " + maxRetries + " attempts");
				}
				break;
		}

		String idPath = driver.findElement(By.cssSelector("#q-app .q-page .q-item.col-auto:nth-child(1) .q-item__label:nth-child(2)")).getText();

		switch(workFlowStateToChange) {
			case "mla-approve-btn":
				MasterHooks.searchMLAID.set(idPath);
				break;
			case "contract-approve-btn":
				MasterHooks.searchCTID.set(idPath);
				break;
			case "lc-approve-btn":
				MasterHooks.searchLCID.set(idPath);
				break;
			case "ag-activate-btn":
				MasterHooks.searchAGID.set(idPath);
				break;
		}

		waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
		handleWait(3000);
		waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");


		if (workFlowStateToChange.equalsIgnoreCase("lc-approve-btn")) {
			waitUntilLoadingSpinnerIsShown("activationGroupWizards");
			if(waitUnTillWebElementIsVisible("agNameField",".q-page .absolute #name-input")) {
//				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
//				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				if(driver.findElements(By.cssSelector(".q-page-container .q-checkbox#specialized-asset[aria-checked='true']")).size()==1) {
					waitAndClickOnElement("specializedAsset", ".q-page-container .q-checkbox#specialized-asset[aria-checked='true']");
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				}
				log.info("User is on Activation Group Level");
			} else {
				WebElement activationGroupSelector=driver.findElement(By.cssSelector("#q-app .q-drawer .q-page .q-item #activation-group-selector"));
				waitTillWebElementIsVisible("activationGroupSelector", activationGroupSelector);
				try {
					if(Strings.isEmpty(MasterHooks.agSplit.get())){
						clickOnDropDownToTypeAndSelectValue("activationGroupSelector",activationGroupSelector, MasterAgreement_PageObject.testCaseName.get());
						waitTillWebElementIsVisible("activationGroupNameField", ".q-page .absolute #name-input");
					}else{
						clickOnDropDownToTypeAndSelectValue("activationGroupSplitSelector",activationGroupSelector, MasterAgreement_PageObject.testCaseName.get());
						waitTillWebElementIsVisible("activationGroupNameField", ".q-page .absolute #name-input");
					}
					waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
					if(driver.findElements(By.cssSelector(".q-page-container .q-checkbox#specialized-asset[aria-checked='true']")).size()==1) {
						waitAndClickOnElement("specializedAsset", ".q-page-container .q-checkbox#specialized-asset[aria-checked='true']");
						waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
						waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
					}
				} catch (InterruptedException|IOException e) {
					e.printStackTrace();
				}

			}
			waitTillWebElementIsVisible("activationGroupID","#q-app .q-page .q-item.col-auto:nth-child(1) .q-item__label:nth-child(2)");
			String AGidPath = driver.findElement(By.cssSelector("#q-app .q-page .q-item.col-auto:nth-child(1) .q-item__label:nth-child(2)")).getText();
			MasterHooks.searchAGID.set(AGidPath);
		}
		// Assertions for Workflow states
		if(workFlowStateToChange.equalsIgnoreCase("mla-send-to-approval-btn") || workFlowStateToChange.equalsIgnoreCase("mla-approve-btn") ||
				workFlowStateToChange.equalsIgnoreCase("mla-callback-btn") || workFlowStateToChange.equalsIgnoreCase("mla-rework-btn") ||
				workFlowStateToChange.equalsIgnoreCase("mla-close-btn") || workFlowStateToChange.equalsIgnoreCase("mla-discard-btn")){
			entityStatus ="#q-app .q-page .q-item.col-auto:nth-child(3) .q-item__label:nth-child(2)";
		}else{
			entityStatus = "#q-app .q-page .q-item.col-auto:nth-child(4) .q-item__label:nth-child(2)";
		}

		switch (workFlowStateToChange){
			case "mla-send-to-approval-btn":
			case "contract-send-to-approval-btn":
			case "lc-send-to-approval-btn":
				String message=entityLevel+" is not in 'Waiting for Approval' State";
				checkStateTransitionValidation(message, "Waiting for Approval",entityStatus);
				log.info(entityLevel+" is in 'Waiting for Approval' State");
				break;
			case "mla-approve-btn":
			case "contract-approve-btn":
				checkStateTransitionValidation(entityLevel+" is not in 'Active' State","Active",entityStatus);
				log.info(entityLevel+" is in 'Active' State");
				break;
			case "lc-approve-btn":
				waitTillWebElementIsVisible("activationGroupNameField", ".q-page .absolute #name-input");
				int activationGroupName = driver.findElements(By.cssSelector(".q-page .absolute #name-input")).size();
				Assert.assertEquals("Lease Component is not in 'Active' State",1,activationGroupName);
				log.info("Lease Component is in 'Active' State");
				break;
			case "mla-callback-btn":
			case "contract-callback-btn":
			case "lc-callback-btn":
			case "mla-rework-btn":
			case "contract-rework-btn":
			case "lc-rework-btn":
			case "ag-classified-rework-btn":
				checkStateTransitionValidation(entityLevel+ " is not in 'Define' State","Define",entityStatus);
				log.info(entityLevel+" is in 'Define' State");
				break;
			case "mla-close-btn":
			case "contract-close-btn":
			case "lc-close-btn":
			case "ag-close-btn":
				checkStateTransitionValidation(entityLevel+" is not in 'Closed' State","Closed",entityStatus);
				log.info(entityLevel+" is in 'Closed' State");
				break;
			case "ag-generate-schedules-btn":
				int scheduleSize = driver.findElements(By.cssSelector("#q-app .q-drawer #schedule-nav-expansion  .q-list .q-item:nth-child(2) .q-item__section--main")).size();
				Assert.assertEquals("Schedules are not generated", 1,scheduleSize);
				if(!(driver.findElements(By.cssSelector(".q-drawer .q-expansion-item__container .q-list .q-item:nth-child(1) .q-badge")).size()==1)) {
					log.info("Schedules are Generated");
				}else {
					int statusSize = driver.findElements(By.cssSelector(".q-drawer .q-expansion-item__container .q-list .q-item .q-badge")).size();
					for (int index = 1; index <= statusSize; index++) {
						String scheduleStatus = driver.findElement(By.cssSelector(".q-drawer .q-expansion-item__container .q-list .q-item:nth-child("+index+") .q-badge")).getText();
						if (scheduleStatus.equalsIgnoreCase("Failed") && index==1) {
							log.info("Schedules Generation is failing for IAS");
						}else if(scheduleStatus.equalsIgnoreCase("Failed") && index==2) {
							log.info("Schedules Generation is failing for GAAP");
						}
					}
				}
				break;
			case "ag-send-to-assessment-btn":
				checkStateTransitionValidation("Activation Group is not in 'Pending Assessment' State", "Pending Assessment",entityStatus);
				log.info("Activation Group is in 'Pending Assessment' State");
				break;
			case "ag-approve-btn":
				checkStateTransitionValidation("Activation Group is not in 'Pending Classification' State", "Pending Classification",entityStatus);
				log.info("Activation Group is in 'Pending Classification' State");
				break;
			case "ag-confirm-classification-btn":
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				checkStateTransitionValidation("Activation Group is not in 'Classified' State", "Classified", entityStatus);
				log.info("Activation Group is in 'Classified' State");
				break;
			case "ag-reject-btn":
				checkStateTransitionValidation("Activation Group is not in 'Rejected' State", "Rejected",entityStatus);
				log.info("Activation Group is in 'Rejected' State");
				break;
			case "ag-activate-btn":
				if(driver.findElement(By.cssSelector(entityStatus)).getText().equalsIgnoreCase("Active")){
					checkStateTransitionValidation("Activation Group is not in 'Active' State", "Active",entityStatus);
					log.info("Activation Group is in 'Active' State");
				} else if (driver.findElement(By.cssSelector(entityStatus)).getText().equalsIgnoreCase("Active (Pending Termination)")) {
					checkStateTransitionValidation("Activation Group is not in 'Active (Pending Termination)' State", "Active (Pending Termination)",entityStatus);
					log.info("Activation Group is in 'Active (Pending Termination)' State");
				}
				MasterHooks.agReverted.remove();
				break;
			case "ag-lease-end-btn":
				if(!driver.findElement(By.cssSelector(entityStatus)).getText().equalsIgnoreCase("Lease End")){
					waitAndClickOnElement(driver.findElement(By.cssSelector(finalWorkFlowPath)));
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
					handleWait(2000);
					waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
				} else {
					checkStateTransitionValidation("Activation Group is not in 'Lease End' State", "Lease End", entityStatus);
					log.info("Activation Group is in 'Lease End' State");
				}break;
			case "mla-discard-btn":
				checkStateTransitionValidation(entityLevel+" is not in 'Discarded' State","Discarded",entityStatus);
				log.info(entityLevel+" is in 'Discarded' State");
				break;
		}
//		if (workFlowStateToChange.equalsIgnoreCase("ag-close-btn")){
//			AG_Schedules.writeURL(driver.getCurrentUrl());
//		}
	}

	public void clickOnTab (String tab) {
		log.info("Clicking on the " + tab + " Tab");
		String tabPath = "#q-app .q-page #" + tab + "-step";
		waitTillWebElementIsVisible("tabPath", tabPath);
		WaitUntilElementIsClickable(driver.findElement(By.cssSelector(tabPath)));
		driver.findElement(By.cssSelector(tabPath)).click();
		log.info("Clicked on the " + tab + " Tab");

	}

	public void clearField(WebElement element) {

		int lenText = element.getAttribute("value").length();
		element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
//		for(int i = 0; i < lenText; i++){
//			element.sendKeys(Keys.BACK_SPACE);
//			element.clear();
//		}
	}

	public void clearFieldByBackSpace(WebElement element) {
		int lenText = element.getAttribute("value").length();
		for (int i = 0; i < lenText; i++) {
			element.sendKeys(Keys.BACK_SPACE);
			element.clear();
		}
	}

	public void moveToFirstPosition(WebElement element) {

		int lenText = element.getAttribute("value").length();

		for(int i = 0; i < lenText; i++){
			element.sendKeys(Keys.ARROW_LEFT);
		}
	}

	//checking this method
	public void clickWithJS(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	//wait till the whole page is loaded
	public void waitForPageToLoad(long timeOutInSeconds) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(expectation);
		} catch (Throwable error) {
			error.printStackTrace();
		}
	}

	public void download_file_selenium_grid(File folderPath) throws Exception {
		// The download happens on a remote browser node.
		// For demonstration purposes, this example uses a 10-second sleep which should
		// be enough time for a file to be downloaded. Please avoid harcoding sleeps and
		// rely on an app-side capabilities to figure out when the transfer completed.
		TimeUnit.SECONDS.sleep(10);

		// This is an endpoint on the browser service which will provide us with a list
		// of files downloaded in current session and lets us download a specific file.
		String downloadsEndpoint = String.format("/session/%s/se/files", MasterHooks.sessionId.get());

//		String fileToDownload = null;
		List<String> names = null;

		try (HttpClient client = HttpClient.Factory.createDefault().createClient(new URL("http://selenium-hub:4444/wd/hub"))) {
			// To list all files that are were downloaded on the remote node for the current
			// session we trigger GET request.
			HttpRequest request = new HttpRequest(GET, downloadsEndpoint);
			HttpResponse response = client.execute(request);
			Map<String, Object> jsonResponse = new Json().toType(string(response), Json.MAP_TYPE);
			@SuppressWarnings("unchecked")
			Map<String, Object> value = (Map<String, Object>) jsonResponse.get("value");
			names = (List<String>) value.get("names");
			// Let's say there were "n" files downloaded for the current session, we would
			// like to retrieve ONLY the first file.
//			fileToDownload = names.get(0);
		}

		// Download file from browser node to local node
		for (String fileName : names) {
			//Storing the files name, so that when we have to move multiple file to local, we will skip the already moved files.
			if (!MasterHooks.downloadedFiles.get().contains(fileName)) {
				try (HttpClient client = HttpClient.Factory.createDefault().createClient(new URL("http://selenium-hub:4444/wd/hub"))) {
					// To retrieve a specific file from one or more files that were downloaded by
					// the current session on a remote node, we use a POST request.
					HttpRequest request = new HttpRequest(POST, downloadsEndpoint);
					request.setContent(asJson(ImmutableMap.of("name", fileName)));
					HttpResponse response = client.execute(request);

					// Unpack the response JSON
					Map<String, Object> jsonResponse = new Json().toType(string(response), Json.MAP_TYPE);
					@SuppressWarnings("unchecked")
					Map<String, Object> value = (Map<String, Object>) jsonResponse.get("value");
					// The returned map would contain 2 keys:
					// filename
					// contents - zip file encoded as Base64 String
					String zippedContents = value.get("contents").toString();

					// The file contents would always be a zip file and has to be unzipped.
					// File downloadDir = Zip.unzipToTempDir(zippedContents, "download", "");
					Zip.unzip(zippedContents, folderPath);
					MasterHooks.downloadedFiles.get().add(fileName);
					System.out.println("The file which was "
							+ "downloaded on the remote node is now available locally in the directory: "
							+ folderPath);
				}
			}
		}

	}

	public void upload_file_selenium_grid(String filePath) throws Exception {
		WebElement element = driver.findElement(By.cssSelector("input[type='file']"));
		LocalFileDetector detector = new LocalFileDetector();
		File file = detector.getLocalFile(filePath);
		((RemoteWebElement) element).setFileDetector(detector);
		element.sendKeys(file.getAbsolutePath());

	}

	public void checkStateTransitionValidation(String message, String expected, String statusCSSPath) {
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					String actual = getValueFromElement(statusCSSPath);
					if (actual.equalsIgnoreCase(expected)) {
						return true;
					}
					return false;
				}
			});
		} catch (Exception e) {
			boolean flag = getValueFromElement(statusCSSPath).equalsIgnoreCase(expected);
			Assert.assertTrue("workFlow State not changed Expected: " + expected + " Actual: " + getValueFromElement(statusCSSPath),
					flag);
		}
	}

	public void checkRequiredField(String webElementName, String webElement){
		log.info("Checking the Field "+ webElementName +" with CSS: "+webElement +" is required");
		if(driver.findElements(By.cssSelector(webElement)).size()==1){
			log.info("The Field "+ webElementName +" with CSS: "+webElement +" is required");
		}else{
			Assert.fail("The WebElement "+ webElementName + " is not Present or Required");
		}
	}

	public void checkPopulatedField(String webElementName, String webElement){
		log.info("Checking the Field "+ webElementName +" with CSS: "+webElement +" is populated with Value");
		String value1 = getValueFromElement(webElement);
		if(!getValueFromElement(webElement).isEmpty()){
			String value = getValueFromElement(webElement);
			log.info("The Field "+ webElementName +" with CSS: "+webElement +" is populated with Value " + value);
		}else{
			Assert.fail("The WebElement "+ webElementName + " is not populated with Value");
		}
	}

	public void clickOnDropDownToTypeAndVerifyValueIsNull(String webElementName, WebElement webElement, String values) throws InterruptedException, IOException {
		waitTillWebElementIsVisible(webElementName, webElement);
		WaitUntilElementIsClickable(webElement); //added new
		webElement.click();
		Thread.sleep(500);

		String[] fieldValuesLength = values.split(",");
		for (String value: fieldValuesLength) {
			clearField(driver.findElement(By.cssSelector(".q-menu label.q-field input")));
//			Thread.sleep(300);
			String searchedValue = null;
			if (value.contains("-")) {
				if (!(value.contains("AG-") || value.contains("MA-") || value.contains("LC-") || value.contains("CT-"))) {
					if (webElementName.equalsIgnoreCase("companyCodes")) {
						searchedValue = value.substring(value.lastIndexOf("-") + 2);
					} else {
						searchedValue = value.substring(0, value.indexOf("-") - 1);
					}
					sendingValueToWebElement(webElementName, driver.findElement(By.cssSelector(".q-menu label.q-field input")), searchedValue);
				} else {
					sendingValueToWebElement(webElementName, driver.findElement(By.cssSelector(".q-menu label.q-field input")), value);
				}
			} else {
				sendingValueToWebElement(webElementName, driver.findElement(By.cssSelector(".q-menu label.q-field input")), value);
			}
		}
		Thread.sleep(2000);
		int dropDownOptionSize = driver.findElements(By.cssSelector(".q-menu .q-item")).size();
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
		wait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
                return dropDownOptionSize == 1;
            }
		});
		String dropDownValues = ".q-menu .q-item .q-item__section";
		if(getValueFromElement(dropDownValues).equalsIgnoreCase("No results")){
			log.info("DropDown Values are Null");
		}else{
			Assert.fail(webElementName+ " DropDown Values are not Null");
		}
	}

	public void approverRejectorComment(String entityLevel, String workFlowStateToChange, String comment) {
		try {
			log.info("Clicking on the " + workFlowStateToChange + " workflow button");
			String mlaStatus = null;
			String entityStatus = null;
			String finalWorkFlowPath = ".q-page-container .q-card .q-btn--actionable#event-id-" + workFlowStateToChange;
			waitTillWebElementIsVisible("workFlowStateToChange", finalWorkFlowPath);
			WaitUntilElementIsClickable(driver.findElement(By.cssSelector(finalWorkFlowPath)));
			waitForClickablility(finalWorkFlowPath);

			waitAndClickOnElement(driver.findElement(By.cssSelector(finalWorkFlowPath)));

			if (workFlowStateToChange.equalsIgnoreCase("mla-approve-btn") || workFlowStateToChange.equalsIgnoreCase("mla-discard-btn") ||
					workFlowStateToChange.equalsIgnoreCase("contract-approve-btn") || workFlowStateToChange.equalsIgnoreCase("lc-approve-btn") ||
					workFlowStateToChange.equalsIgnoreCase("ag-approve-btn") || workFlowStateToChange.equalsIgnoreCase("contract-rework-btn") ||
					workFlowStateToChange.equalsIgnoreCase("mla-rework-btn") || workFlowStateToChange.equalsIgnoreCase("lc-rework-btn") ||
					workFlowStateToChange.equalsIgnoreCase("ag-classified-rework-btn") || workFlowStateToChange.equalsIgnoreCase("ag-reject-btn") ||
					workFlowStateToChange.equalsIgnoreCase("ag-assessment-rework-btn")||(workFlowStateToChange.equalsIgnoreCase("contract-discard-btn")
					||(workFlowStateToChange.equalsIgnoreCase("lc-discard-btn")))){

				Thread.sleep(1000);
				waitTillWebElementIsVisible("CommentPopUp", ".q-dialog .dialog-header .text-h6 div");
				String headerValue = getValueFromElement(".q-dialog .dialog-header .text-h6 div");
				if (headerValue.contains("Modification Summary")) {
//				waitUntilLoadingSpinnerIsShown("nlaAddButtonLoader");
					waitUntilLoadingSpinnerIsGone("nlaAddButtonLoader");
					String approveButtonEvent = ".q-dialog .q-card #delta-submit-btn";
					waitTillWebElementIsVisible("approveButtonEvent", approveButtonEvent);
					driver.findElement(By.cssSelector(approveButtonEvent)).click();
				}
				Thread.sleep(1000);
				String approverComment = ".q-card__section .q-field--outlined #comment-textarea";
				waitTillWebElementIsVisible("approverComment", approverComment);
				waitAndClickOnElement(driver.findElement(By.cssSelector(approverComment)));
				sendingValueToWebElement("Comment", driver.findElement(By.cssSelector(approverComment)), comment);
				Thread.sleep(1000);
				String approveButton = ".q-dialog .q-card #discussion-submit-btn";
				waitTillWebElementIsVisible("approveButton", approveButton);
				driver.findElement(By.cssSelector(approveButton)).click();
				waitUntilLoadingSpinnerIsShown("nlaWorkFlowLoader");
				Thread.sleep(3000);
				waitUntilLoadingSpinnerIsGone("nlaWorkFlowLoader");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void validatingCommentorMessage(String entityLevel, String validationMessage) {
		try{
			if (entityLevel.equalsIgnoreCase("Contract") || (entityLevel.equalsIgnoreCase("Lease Component"))||
					entityLevel.equalsIgnoreCase("Activation Group")) {
				waitTillWebElementIsVisible("definitionStep",".q-card .q-stepper__header #definition-step");
				waitAndClickOnElement(driver.findElement(By.cssSelector(".q-card .q-stepper__header #definition-step")));
				Thread.sleep(2000);
			}
			String approverComment = driver.findElement(By.cssSelector(".q-mt-md .q-field #approver-comment-textarea")).getAttribute("value");
			if (!approverComment.equalsIgnoreCase(validationMessage)) {
				Assert.assertEquals("Expected Success message is " + validationMessage, "Actual Success message is " + approverComment);
			}
			log.info("Correct message: " + approverComment + " is showing");
		}
		catch(InterruptedException e){
			throw new RuntimeException(e);
		}
	}

	public int clickOnDropDownAndGetDropDownSize(String webElementName, WebElement webElement) throws
			InterruptedException, IOException {
		waitTillWebElementIsVisible(webElementName, webElement);
		WaitUntilElementIsClickable(webElement); //added new
		waitAndClickOnElement(webElement);
		handleWait(500);

		String dropDownValues = ".q-menu .q-item:nth-child(1)";
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
		wait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				if (!getValueFromElement(dropDownValues).equalsIgnoreCase("No results")) {
					return true;
				}
				return false;
			}
		});
		int dropDownOptionSize = driver.findElements(By.cssSelector(".q-menu .q-item")).size();
		log.info("Dropdown Size is: " + dropDownOptionSize);
		waitAndClickOnElement(webElement);
		return dropDownOptionSize;
	}

	public boolean elementsVisibility(String webElementName, String webElement) {
		logInfo("Checking the visibility of Element present", webElementName, webElement);
        return driver.findElements(By.cssSelector(webElement)).size() == 1;
	}

	public void clickOnDropDownAndSelectValueFromPages(String webElementName, WebElement webElement, String value) throws InterruptedException, IOException {
		waitTillWebElementIsVisible(webElementName, webElement);
		WaitUntilElementIsClickable(webElement);
		Thread.sleep(500);
		waitAndClickOnElement(webElement);
		final String dropDownValues;
		dropDownValues=".q-menu .q-item:nth-child(1)";

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).ignoring(WebDriverException.class);
		wait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				if (waitTillWebElementIsVisible("dropDownValue", dropDownValues) &&
						!getValueFromElement(dropDownValues).equalsIgnoreCase("No results")) {
					return true;
				}
				return false;
			}
		});

		int dropDownOptionSize;
		dropDownOptionSize=driver.findElements(By.cssSelector(".q-menu .q-item")).size();
		int pageSize;
		String optionToClickWithIndex="";
		String innerTextValue="";
		if(dropDownOptionSize==11){
			pageSize=driver.findElements(By.cssSelector(".q-menu .q-item .q-pagination Button")).size();
			for(int pageNo=1;pageNo<=pageSize;pageNo++){
				if(pageNo!=1){
					waitAndClickOnElement("dropDownPage",".q-menu .q-item .q-pagination Button.q-btn-item:nth-child("+ pageNo+")");
					Thread.sleep(300);
				}
				for (int cssIndex = 1; cssIndex <= dropDownOptionSize-1; cssIndex++) {
					innerTextValue="";
					waitTillWebElementIsVisible("DropDownValue",".q-menu .q-item:nth-child(" + cssIndex + ") .q-item__section--main");
					innerTextValue = getValueFromElement(".q-menu .q-item:nth-child(" + cssIndex + ") .q-item__section--main");
					if (value.equals(innerTextValue)) {
						optionToClickWithIndex = ".q-menu .q-item:nth-child(" + cssIndex + ") .q-item__section--main";
						break;
					}
				}
				if(!optionToClickWithIndex.equalsIgnoreCase("")){
					break;
				}
			}
		}else{
			for (int cssIndex = 1; cssIndex <= dropDownOptionSize; cssIndex++) {
				innerTextValue="";
				waitTillWebElementIsVisible("DropDownValue",".q-menu .q-item:nth-child(" + cssIndex + ") .q-item__section--main");
				innerTextValue = getValueFromElement(".q-menu .q-item:nth-child(" + cssIndex + ") .q-item__section--main");
				if (value.equals(innerTextValue)) {
					optionToClickWithIndex = ".q-menu .q-item:nth-child(" + cssIndex + ") .q-item__section--main";
					break;
				}
			}
		}
		if (optionToClickWithIndex.equalsIgnoreCase("")) {
			log.error("Dropdown value is not matching, expecting value should be > " + value);
		}
		waitAndClickOnElement("DropdownValueSelected",optionToClickWithIndex);
	}

	public void clickOnCalendarAndPerformAction(String webElementName, WebElement webElement, String input) {
		log.info("Clicking on the " + webElementName + " for Date Selection");
		// Handle calender field separately
		String dateString = "";
		List<String> dateList = new ArrayList<String>();
		// click on calender icon
		try {
			waitAndClickOnElement(webElement);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (!input.equalsIgnoreCase("")) {
			dateList = Arrays.asList(input.split("-"));
			if (dateList.size() == 3) {
				String year = dateList.get(0);
				String month = dateList.get(1);
				String day = dateList.get(2);
				if (year.matches("^.{4}$") && month.matches("^.{2,3}$") && day.matches("^.{1,2}$")) {
					if (!month.matches("^[^0-9]{3}$"))
						month = convertNumericToAlphabeticMonth(dateList.get(1));
					if (day.startsWith("0"))
						day = day.substring(1);
					selectCalenderDate(year, month, day);
				} else {
					System.out.println("Invalid Date format!! Please use the format YYYY-MM-DD(2022-01-09) OR YYYY-Mmm-D(2022-Jan-9)");
				}
			} else {
				System.out.println("Invalid Date format!! Please use the format YYYY-MM-DD(2022-01-09) OR YYYY-Mmm-D(2022-Jan-9)");
			}
		} else {
			System.out.println("Date input is blank");
		}
		log.info("Date Selected from Calender is " + input);
	}
	public void selectCalenderDate(String year, String month, String day) {
		try {
			// TODO if role attribute is 'img' it will open 2 calender windows
			List<WebElement> calenderButtonList;
			List<WebElement> yearsList;
			List<WebElement> monthsList;
			List<WebElement> daysList;
			WebElement calenderElement = driver.findElement(By.cssSelector(".q-menu .q-date"));
			waitTillWebElementIsVisible("calendarDateForm", calenderElement);
			waitForClickablility(".q-menu .q-date");
			// This will contain the month and year buttons,where first element is month while second element is year
			calenderButtonList = driver.findElements(By.cssSelector(".q-date .q-btn--no-uppercase"));
			waitAndClickOnElement(calenderButtonList.get(0));
			waitUnTillWebElementIsVisible("dateMonths", ".q-menu .q-date__months");
			monthsList = driver.findElements(By.cssSelector(".q-date__content .q-date__months-item .q-btn__content .block"));
			for (WebElement element : monthsList) {
				if (element.getText().equalsIgnoreCase(month)) {
					waitAndClickOnElement(element);
					Thread.sleep(500);
					break;
				}
			}
			//Select a year in calendar
			// Load month year button again to avoid stale element exception
			calenderButtonList = driver.findElements(By.cssSelector(".q-date .q-btn--no-uppercase"));
			waitAndClickOnElement(calenderButtonList.get(1));
			waitUnTillWebElementIsVisible("dateYear", ".q-menu .q-date__years");
			yearsList = driver.findElements(By.cssSelector(".q-date__content .q-date__years-item .q-btn .q-btn__content .block"));
			// select a specific calender window based on input year
			while ((driver.findElements(By.xpath("//*[@class='q-date__years-item flex flex-center']//span[text()='" + year + "']")).isEmpty())) {
				yearsList = driver.findElements(By.cssSelector(".q-date__content .q-date__years-item .q-btn .q-btn__content .block"));
				WebElement firstYearElement = yearsList.get(0);
				WebElement lastYearElement = yearsList.get(yearsList.size() - 1);
				if (compareNumericStrings(year, firstYearElement.getText()) < 0) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'q-date')]//i[text()='chevron_left']")));
					WebElement chevron_left = driver.findElement(By.xpath("//div[contains(@class, 'q-date')]//i[text()='chevron_left']"));
					waitAndClickOnElement(chevron_left);
				} else if (compareNumericStrings(year, lastYearElement.getText()) > 0) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'q-date')]//i[text()='chevron_right']")));
					WebElement chevron_right = driver.findElement(By.xpath("//div[contains(@class, 'q-date')]//i[text()='chevron_right']"));
					waitAndClickOnElement(chevron_right);
				}
			}
			yearsList = driver.findElements(By.cssSelector(".q-date__content .q-date__years-item .q-btn .q-btn__content .block"));
			for (WebElement element : yearsList) {
				if (element.getText().equalsIgnoreCase(year)) {
					waitAndClickOnElement(element);
					Thread.sleep(500);
					break;
				}
			}
			//Select a day in calendar
			waitUnTillWebElementIsVisible("dateDay", ".q-menu .q-date__calendar-days-container");
			daysList = driver.findElements(By.cssSelector(".q-date__content .q-date__calendar-item--in .q-btn__content .block"));
			for (WebElement element : daysList) {
				if (element.getText().equalsIgnoreCase(day)) {
					waitAndClickOnElement(element);
					Thread.sleep(500);
					break;
				}
			}
		} catch (Exception exception) {
			System.out.println("Exception encountered: " + exception.toString());
		}
	}
	public int compareNumericStrings(final String string1, final String string2) {
		double num1 = Double.parseDouble(string1);
		double num2 = Double.parseDouble(string2);
		if (num1 < num2) {
			return -1; // str1 is less than str2
		} else if (num1 > num2) {
			return 1; // str1 is greater than str2
		} else {
			return 0; // str1 is equal to str2
		}
	}
	public String convertNumericToAlphabeticMonth(final String monthString) {
		String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
				"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		int month = Integer.parseInt(monthString);
		if (month >= 1 && month <= 12) {
			return months[month - 1];
		} else {
			return "Invalid month";
		}
	}

	public String convertDate(String dateStr) {
		log.info("Converting date format from 'yyyy-mm-dd' to 'mm/dd/yyyy'");
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");

		try {
			// Check if the date is already in 'MM/dd/yyyy' format
			outputFormat.setLenient(false);
			outputFormat.parse(dateStr);
			log.info("Date is already in 'MM/dd/yyyy' format.");
			return dateStr;
		} catch (ParseException e) {
			System.out.println("The input date is not in MM/dd/yyyy format.");
		}

		try {
			// Parse the date string in 'yyyy-MM-dd' format
			Date date = inputFormat.parse(dateStr);
			// Format and return the date in 'MM/dd/yyyy' format
			log.info("Date format changed to 'MM/dd/yyyy'");
			return outputFormat.format(date);
		} catch (ParseException e) {
			System.out.println("The input date is not in yyyy-MM-dd or MM/dd/yyyy format.");
			return null;
		}
	}

	public String getValuesFromExcel(String header, String subHeader, String fieldName, int index) {
		try {
			return masterAgreement_PageObject.get()
					.getInputValues()
					.get(header)
					.get(subHeader)
					.get(fieldName)
					.get(index);
		} catch (Exception exception) {
			Assert.fail("Exception while retrieving value from Excel: " + exception.getMessage());
			throw exception;
		}
	}

	public void handleWait(long timeoutInMillis) {
		try {
			FluentWait<WebDriver> wait = new FluentWait<>(driver)
					.withTimeout(Duration.ofMillis(timeoutInMillis)) // Set the timeout in milliseconds
					.pollingEvery(Duration.ofMillis(50))  // Poll every 100 ms
					.ignoring(Exception.class);  // Handle all exceptions (you can customize as needed)
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					System.out.println("Checking condition at " + LocalDateTime.now());
					WebElement element = driver.findElement(By.cssSelector("#someElement"));
					return element.isDisplayed(); // Condition to check if the element is visible
				}
			});
		} catch (Exception ex) {
			System.out.println("Waited for " + timeoutInMillis + "miliSeconds");
		}
	}

	public String getValueFromElement(String cssSelector) {
		try {
			waitTillWebElementIsVisible("dropDownValue", cssSelector);
			log.info("The WebElement value is '" + driver.findElement(By.cssSelector(cssSelector)).getText() +"'");
			return driver.findElement(By.cssSelector(cssSelector)).getText();
		} catch (Exception exception) {
			Assert.fail("Unable to get WebElement value due to exception: " + exception.getMessage());
			throw exception;
		}
	}


	public void storeId(String entityName) {
		log.info("Storing the " + entityName + " Id");
		String idPath = null;
		waitTillWebElementIsVisible("id", "#q-app .q-page .q-item.col-auto:nth-child(1) .q-item__label:nth-child(2)");
		if(entityName.equalsIgnoreCase("InterCompanyTransfer Activation Group") || entityName.equalsIgnoreCase("InterCompanyTransfer Contract")) {
			idPath = driver.findElement(By.cssSelector("#q-app .q-page #clear-btn .q-btn__content")).getText().split(" ")[0].trim();;
		}else{
			idPath = driver.findElement(By.cssSelector("#q-app .q-page .q-item.col-auto:nth-child(1) .q-item__label:nth-child(2)")).getText();
		}
		HashMap<String, String> threadMap = MasterHooks.map.get();
		// Add or update the entity name and ID in the map
		threadMap.put(entityName, idPath);
		// Update the map in MasterHooks
		MasterHooks.map.set(threadMap);
		// Log the current state of the map (for debugging)
		log.info("Current thread map: " + threadMap);

	}

	public int getSizeOfElements(String cssSelector) {
		try {
			int size = driver.findElements(By.cssSelector(cssSelector)).size();
			log.info("The WebElement Size is '" + size + "'");
			return size;
		} catch (Exception exception) {
			Assert.fail("Unable to get WebElement size due to exception: " + exception.getMessage());
			throw exception;
		}
    }

	public WebElement getElement(String cssSelector) {
		try {
			waitTillWebElementIsVisible("dropDownValue", cssSelector);
			WebElement element = driver.findElement(By.cssSelector(cssSelector));
			log.info("WebElement found using selector: " + cssSelector);
			return element;
		} catch (NoSuchElementException e) {
			Assert.fail("No element found using selector: " + cssSelector);
			throw e; // Ensures failure is properly recorded
		} catch (Exception exception) {
			Assert.fail("Unable to get WebElement due to exception: " + exception.getMessage());
			throw exception;
		}
	}

	public List<WebElement> getElements(String cssSelector) {
		try {
			List<WebElement> elements = driver.findElements(By.cssSelector(cssSelector));
			log.info("Found " + elements.size() + " elements using selector: " + cssSelector);
			return elements;
		} catch (Exception exception) {
			log.error("Unable to get elements due to exception: " + exception.getMessage());
			throw exception; // Propagate the exception to fail the test
		}
	}

	public void checkColumnOnLandingPage(String columnName, String entity) {
		log.info("Checking that " + columnName + " exists on " + entity + " Landing Page");
		int columnSize = getSizeOfElements(".q-page .q-table #searchResults-sortColumn-iconBtn");
		boolean columnExists = false;
		for (int i = 1; i <= columnSize; i++) {
			String columnNameOnApplication = getValueFromElement(".q-page .q-table #searchResults-sortColumn-iconBtn:nth-child(" + (i + 1) + ")");
			if (columnNameOnApplication.equalsIgnoreCase(columnName)) {
				log.info(columnName + " Column exists on " + entity + " Landing Page");
				columnExists = true;
				break; // Stop checking further once found
			}
		}
		if (!columnExists) {
			Assert.fail(columnName + " Column does not exist on " + entity + " Landing Page");
		}
	}

	public void removeAIAssistant(){
		log.info("Closing the AI Assistant");
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(
					"let copilot = document.querySelector('.nakisa-copilot-button'); if(copilot) copilot.style.display='none';"
			);
		} catch (Exception e) {
            e.printStackTrace();
        }

    }

	public boolean waitUntilElementIsShown(String elementSelector, long pollTime) {
		try {
			Wait<WebDriver> wait =
					new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(pollTime)).pollingEvery(Duration.ofMillis(100)).ignoring(WebDriverException.class);
			return wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver1) {
					boolean isPresent = getSizeOfElements(elementSelector) == 1;
					log.info("Waiting for element to appear with selector: " + elementSelector);
					if (isPresent) {
						log.info("Element appeared with selector: " + elementSelector);
					}
					return isPresent;
				}
			});
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}

	public String getRecordId(String keyFilter, HashMap<String, String> threadMap, boolean exactMatch) {
		if (threadMap == null || threadMap.isEmpty()) {
			System.out.println("ThreadMap is empty or not initialized");
			return "";
		}

		StringBuilder result = new StringBuilder();

		for (Map.Entry<String, String> entry : threadMap.entrySet()) {
			if (exactMatch) {
				if (entry.getKey().equals(keyFilter)) {
					return entry.getValue(); // return immediately if exact match found
				}
			} else {
				if (entry.getKey().contains(keyFilter)) {
					result.append(entry.getValue()).append(",");
				}
			}
		}

		// If partial match, remove trailing comma
		if (!exactMatch && result.length() > 0) {
			result.setLength(result.length() - 1);
			return result.toString();
		}

		return ""; // nothing found
	}

	public String mapObjectType(String objectType) {
		switch (objectType) {
			case "Contract": return "Contract";
			case "Lease Component": return "LeaseComponent";
			case "Activation Group": return "ActivationGroup";
			case "Unit": return "Unit";
			default:
				log.warn("Unknown ObjectList type: {}", objectType);
				return "";
		}
	}

	public boolean waitUntilTextEqualsIgnoreCase(String cssSelector, String expectedText, int timeInSeconds) {
		try {
			Wait<WebDriver> wait = new FluentWait<>(driver)
					.withTimeout(Duration.ofSeconds(timeInSeconds))
					.pollingEvery(Duration.ofMillis(100))
					.ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class);

			return wait.until(driver -> {
				String actualText  = getValueFromElement(cssSelector);
				return actualText.contains(expectedText);
			});
		} catch (TimeoutException e) {
			log.warn("Timeout: Expected text '" + expectedText + "' not found in selector: " + cssSelector);
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
