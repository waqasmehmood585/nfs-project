package com.nakisa.nlaAutomation.pageObjects;

import java.time.Duration;
import java.util.*;
import java.util.function.Function;

import com.nakisa.nlaAutomation.stepDefinitions.MasterHooks;

import com.nakisa.nlaAutomation.utils.DriverFactory;
import lombok.extern.slf4j.Slf4j;

import org.apache.directory.api.util.Strings;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

@Slf4j
public class Common_Login_PageObject extends Common_BasePage_PageObject {

	/**
	 * NCP build login page web-elements
	 **/
	public @FindBy(css = ".card-pf  #kc-content") WebElement loginPage;
	public @FindBy(css = ".card-pf  #kc-content input[name='username']") WebElement username;
	public @FindBy(css = ".card-pf  #kc-content input[name='password']") WebElement password;
	public @FindBy(css = ".card-pf  #kc-content input[name='login']") WebElement loginButton;
	public @FindBy(css = "body textarea[name='accessToken']") WebElement accessToken;
	public @FindBy(css = "body form input[value=Login]") WebElement loginLocal;

	private static Queue<String> userQueue = new LinkedList<>();
	private static ThreadLocal<String> pwd = new ThreadLocal<>();

	public Common_Login_PageObject() {
		super();
        log.info("Driver is inside this class: {}", this.getClass().getSimpleName());
		PageFactory.initElements(driver, this);
	}

	public void getLoginPage() {
		WebDriver currentDriver = ensureDriverIsInitialized();

		String cockpitLink = MasterHooks.configurationProperties.get().getCockpitLink();

		if (cockpitLink == null || cockpitLink.isEmpty()) {
			System.out.println("Secret file is empty. Forcefully stopping Automation.");
			Assert.fail("CockpitLink is missing or null. Test execution stopped.");
		}
		try {
			currentDriver.get(cockpitLink);
			log.info("Navigated to login page: {}", cockpitLink);
		} catch (NoSuchSessionException e) {
			log.error("WebDriver session not found. Browser may have been closed or crashed.", e);
			Assert.fail("WebDriver session not found. Browser may have been closed or crashed.");
		} catch (NoSuchWindowException e) {
			log.error("Unexpected error while navigating to login page.", e);
			Assert.fail("Unexpected error while navigating to login page: " + e.getMessage());
		}
	}

	private WebDriver ensureDriverIsInitialized() {
		try {
			if (driver == null || !isDriverValid(driver)) {
				log.warn("Driver is null or invalid. Attempting to get from thread...");
				driver = driverThread.getDriver();
			}

			if (driver == null || !isDriverValid(driver)) {
				log.error("Driver is still null/invalid after getting from thread. Reinitializing...");
				reinitializeDriver();
				driver = driverThread.getDriver();
			}

			if (driver == null) {
				Assert.fail("Failed to initialize driver after all attempts");
			}

			return driver;
		} catch (Exception e) {
			log.error("Exception while ensuring driver initialization", e);
			throw new RuntimeException("Failed to ensure driver is initialized", e);
		}
	}

	private boolean isDriverValid(WebDriver driver) {
		try {
			driver.getTitle(); // Simple check to see if driver is responsive
			return true;
		} catch (Exception e) {
			log.warn("Driver is not valid: {}", e.getMessage());
			return false;
		}
	}

	private void reinitializeDriver() {
		try {
			log.info("Reinitializing driver...");
			if (driverThread.getDriver() != null) {
				try {
					driverThread.getDriver().quit();
					driverThread.removeDriver();
				} catch (Exception e) {
					log.warn("Error quitting existing driver: {}", e.getMessage());
				}
			}
			DriverFactory.setDriver();
			driver = driverThread.getDriver();
			log.info("Driver reinitialized successfully");
		} catch (Exception e) {
			log.error("Failed to reinitialize driver", e);
			throw new RuntimeException("Failed to reinitialize driver", e);
		}
	}

	public void enterUsernamePasswordAndClickOnSumbit(String userType) {


		if (MasterHooks.configurationProperties.get().getRunAutomationOnNCPBuild()) {
			String buildUsername;
			if(userType.equalsIgnoreCase("Specific")){
				buildUsername = initializeSpecificUsers();
			}else{
				buildUsername = getCurrentUsername();
			}
			String buildPassword = getPassword();
			waitTillWebElementIsVisible("loginPage", loginPage);
			sendingValueToWebElement("username", username, buildUsername);
			sendingValueToWebElement("password", password, buildPassword);
			try {
				waitAndClickOnElement(loginButton);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

//			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
//			wait.until(new Function<WebDriver, Boolean>() {
//				@Override
//				public Boolean apply(WebDriver driver) {
//					try {
//						if (isWebElementDisplayed("loginPage", loginPage)) {
//							sendingValueToWebElement("username", username, buildUsername);
//							sendingValueToWebElement("password", password, buildPassword);
//							actionMoveAndClick("loginButton", loginButton);
//							return false;
//						}
//					} catch (NoSuchElementException e) {
//					}
//					return true;
//				}
//			});
			MasterHooks.loginFlag.set("True");
			waitUntilLoadingSpinnerIsShown("ncpTableLoader");
			waitUntilLoadingSpinnerIsGone("ncpTableLoader");
			getStackInfo();
		}else{
			waitTillWebElementIsVisible("accessTokenField", accessToken);
			if(Strings.isEmpty(MasterHooks.loginFlag.get())) {
				accessToken.clear();
				String buildUser;
				if(userType.equalsIgnoreCase("Specific")){
					buildUser = initializeSpecificUsers();
				}else if(userType.equalsIgnoreCase("es user1")){
					buildUser = MasterHooks.configurationProperties.get().getEssToken1();
				}else {
					buildUser = getCurrentUsername();
				}
				sendingValueToWebElement("accessToken", accessToken, buildUser);
				MasterHooks.loginFlag.set("True");
			}
			waitTillWebElementIsVisible("LoginButton", loginLocal);
			loginLocal.click();
//			waitUntilLoadingSpinnerIsShown("nlaLoadingPage");
//			waitUntilLoadingSpinnerIsGone("nlaLoadingPage");
		}
	}

	private void getStackInfo() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {e.printStackTrace();}
		int envSize=driver.findElements(By.cssSelector("#app .application--wrap li:nth-child(1) table tbody tr")).size();
		for(int index=1;index<=envSize;index++){
			String envName=driver.findElement(By.cssSelector("#app .application--wrap li:nth-child(1) table tbody tr:nth-child("+ index+") td:nth-child(1)")).getText();
			if(envName.equalsIgnoreCase(MasterHooks.configurationProperties.get().getEnvName())){
				MasterHooks.CoreServiceVersion.set(driver.findElement(By.cssSelector("#app .application--wrap li:nth-child(1) table tbody tr:nth-child("+ index+") td:nth-child(3)")).getText());
				MasterHooks.stackName.set(driver.findElement(By.cssSelector("#app .application--wrap li:nth-child(1) table tbody tr:nth-child("+ index+") td:nth-child(4)")).getText());
				break;
			}
		}
	}

	public void initializeUsers() {
		if (MasterHooks.configurationProperties.get().getRunAutomationOnNCPBuild()) {
			String[] randomUsers=MasterHooks.configurationProperties.get().getEmail().split(",");
			// Shuffle the array
			Collections.shuffle(Arrays.asList(randomUsers));
			// Add shuffled elements to the userQueue
			userQueue.addAll(Arrays.asList(randomUsers));
			pwd.set(MasterHooks.configurationProperties.get().getPassword());
		}else{
			String[] randomUsersToken = MasterHooks.configurationProperties.get().getTokens().split(",");
			Collections.shuffle(Arrays.asList(randomUsersToken));
			userQueue.addAll(Arrays.asList(randomUsersToken));
		}
	}

	public String initializeSpecificUsers() {
		String firstUser = null;
		if (MasterHooks.configurationProperties.get().getRunAutomationOnNCPBuild()) {
			firstUser = MasterHooks.configurationProperties.get().getSchedulerEmail();
			pwd.set(MasterHooks.configurationProperties.get().getPassword());
		} else if (!MasterHooks.configurationProperties.get().getRunAutomationOnNCPBuild()) {
			firstUser = MasterHooks.configurationProperties.get().getSchedulerToken();
		}
		return firstUser;
	}



	public String getPassword() {
		return pwd.get();
	}

	public String getCurrentUsername() {
		String currentUser = userQueue.remove();
		userQueue.add(currentUser);
		return currentUser;
	}

	public void selectAndOpenAppLinkInNewTab(String microServiceName) {
		openAppLinkInNewTab(selectAppLink(microServiceName));
	}

	public String selectAppLink(String microServiceName) {
		if(MasterHooks.configurationProperties.get().getRunAutomationOnNCPBuild()) {
			return MasterHooks.configurationProperties.get().getBuildURL()+ MasterHooks.configurationProperties.get().getEnvName()+"/" + microServiceName + "/auth/login";
		}
		return "http://"+MasterHooks.configurationProperties.get().getEnvName()+"/dev/"+microServiceName+ "/auth/login";
	}

	public void openAppLinkInNewTab(String appLink) {
//		String cssPath = "#app .application--wrap li:nth-child(1) table tbody tr";
//		int getListOfMicroServices = driver.findElements(By.cssSelector(cssPath)).size();
//		for (int index = 1; index <= getListOfMicroServices; index++) {
//			String environmentName = driver.findElement(By.cssSelector(cssPath + ":nth-child(" + index + ") td:nth-child(1)")).getText();
//			if (appLink.contains(environmentName)) {
//				log.info("Instance name is matched");
//				index = getListOfMicroServices + 1;
//				((JavascriptExecutor) driver).executeScript("window.open()");
//				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
//				driver.switchTo().window(tabs.get(1));
//		if (driverThread.getDriver() == null) {
//			Assert.fail("Driver has not been initialized");
//		}
		if (appLink == null) {
			System.out.println("Secret file is empty. Forcefully stopping Automation.");
			Assert.fail("appLink is missing or null. Test execution stopped.");
		}
		try {
			driver.get(appLink);
			log.info("Navigated to appLink: {}", appLink);
		} catch (NoSuchSessionException | NoSuchWindowException e) {
			log.error("WebDriver session/window issue encountered. Retrying with a new driver.", e);
			driverThread.getDriver().quit();
			driverThread.removeDriver();
			DriverFactory.setDriver();
			driverThread.getDriver().get(appLink);
//			Assert.fail("WebDriver session not found. Browser may have been closed or crashed.");
		}
//		catch (NoSuchWindowException ex) {
//			log.error("Unexpected error while navigating to appLink.", ex);
//			Assert.fail("Unexpected error while navigating to appLink: " + ex.getMessage());
//		}
		log.info("App link is - " + appLink);
//		driver.get(appLink);
//				driver.switchTo().window(tabs.get(0));
//				driver.close();
//				driver.switchTo().window(tabs.get(1));
//			}
//		}
	}

	public void closeWhatsNewPopUp() {
		if (waitUntilElementIsShown(".q-dialog .q-card .q-carousel__slides-container",5)) {
//			driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#wfx-frame-popup")));
			try {
				waitAndClickOnElement("closeWhatsNewPopUp", ".q-dialog--modal .q-card button.close-icon");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			waitForWebElementToDisappear("whatsNewPopUpToGo", ".q-dialog--modal .q-card button.close-icon");
//			driver.switchTo().defaultContent();
		}
	}
}
