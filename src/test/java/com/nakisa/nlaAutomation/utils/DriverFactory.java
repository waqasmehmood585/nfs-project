package com.nakisa.nlaAutomation.utils;

import com.nakisa.nlaAutomation.Constant;
import com.nakisa.nlaAutomation.pageObjects.*;
import com.nakisa.nlaAutomation.stepDefinitions.MasterHooks;
import com.nakisa.nlaAutomation.validations.metaModel.AG_ChargePosting_MetaModel;
import com.nakisa.nlaAutomation.validations.metaModel.AG_Classifications_MetaModel;
import com.nakisa.nlaAutomation.validations.metaModel.AG_JournalPosting_MetaModel;
import com.nakisa.nlaAutomation.validations.metaModel.AG_PostingDocument_MetaModel;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.Dimension;

@Slf4j
public class DriverFactory {

	public static DriverThread driverThread = DriverThread.getDriverThread();
	public static ThreadLocal<Common_BasePage_PageObject> common_BasePage = new ThreadLocal<>();
	public static ThreadLocal<Common_Login_PageObject> common_LoginPage = new ThreadLocal<>();
	public static ThreadLocal<MasterAgreement_PageObject> masterAgreement_PageObject = new ThreadLocal<>();
	public static ThreadLocal<defaultValues_PageObject> defaultConfigValues_PageObject = new ThreadLocal<>();
	public static ThreadLocal<ERPFieldMapping_PageObject> ERPFieldMapping_PageObject = new ThreadLocal<>();
	public static ThreadLocal<C_Creation_PageObject> c_CreationPage = new ThreadLocal<>();
	public static ThreadLocal<C_LeaseComponent_PageObject> c_LeaseComponentPage = new ThreadLocal<>();
	public static ThreadLocal<C_Activation_Group_PageObject> c_ActivationGroupPage = new ThreadLocal<>();
	public static ThreadLocal<C_AG_Event_PageObject> c_AG_EventPage = new ThreadLocal<>();
	public static ThreadLocal<C_LeaseComponent_Event_PageObject> c_leaseComponent_eventPage = new ThreadLocal<>();
	public static ThreadLocal<AG_Schedules> aG_SchedulesPage = new ThreadLocal<>();
	public static ThreadLocal<BatchManagement_PageObject> batchManagement_pageObject = new ThreadLocal<>();
	public static ThreadLocal<HamburgerMenu_PageObject> hamburgerMenu_pageObject = new ThreadLocal<>();
	public static ThreadLocal<ReportingModule_PageObject> reportingModule_pageObject = new ThreadLocal<>();
	public static ThreadLocal<SAP_PostingBot_PageObject> sapPostingBot_PageObject = new ThreadLocal<>();
	public static ThreadLocal<AG_PostingDocument_MetaModel> aG_PostingDocument_MetaModel = new ThreadLocal<>();
	public static ThreadLocal<AG_JournalPosting_MetaModel> aG_JournalPosting_MetaModel = new ThreadLocal<>();
	public static ThreadLocal<AG_ChargePosting_MetaModel> aG_chargePosting_MetaModel = new ThreadLocal<>();
	public static ThreadLocal<ImportExport_PageObject> importExport_pageObject = new ThreadLocal<>();
	public static ThreadLocal<LeaseComponents_Validations> leaseComponents_validations = new ThreadLocal<>();
	public static ThreadLocal<ActivationGroup_Validations> activationGroup_validations = new ThreadLocal<>();
	public static ThreadLocal<AG_Classifications_MetaModel> activationGroup_Classifications = new ThreadLocal<>();
	public static ThreadLocal<Dashboard_PageObject> dashboard_pageObject = new ThreadLocal<>();
	public static ThreadLocal<SAPSyncBot_PageObject> sapSyncBot_PageObject = new ThreadLocal<>();
	public static ThreadLocal<AuditLogs_PageObject> auditLogs_PageObject = new ThreadLocal<>();
	public static ThreadLocal<UserAuxiliary_PageObject> userAuxiliary_PageObject = new ThreadLocal<UserAuxiliary_PageObject>();

	public static void setDriver() {
		try {
			switch (MasterHooks.configurationProperties.get().getBrowser()) {
				case "chrome":
					if (driverThread.getDriver() == null) {
						driverThread.setChromeDriver();
						if (Constant.runHeadless) {
							driverThread.getDriver().manage().window().setSize(new Dimension(1920, 1080));
						} else {
							driverThread.getDriver().manage().window().maximize();
						}
						log.info("Chrome browser is set");
					}
					break;
				case "edge":
					if (driverThread.getDriver() == null) {
						driverThread.setEdgeDriver();
						if (Constant.runHeadless) {
							driverThread.getDriver().manage().window().setSize(new Dimension(1920, 1080));
						} else {
							driverThread.getDriver().manage().window().maximize();
						}
						log.info("Edge browser is set");
					}
					break;
			}
		} catch (Exception exception) {
			log.error("Unable to load the browser \n" + exception.getMessage());
		}
	}

	public void setPageObjects() throws IOException {
		common_BasePage.set(new Common_BasePage_PageObject());
		common_LoginPage.set(new Common_Login_PageObject());
		masterAgreement_PageObject.set(new MasterAgreement_PageObject());
		c_CreationPage.set(new C_Creation_PageObject());
		c_LeaseComponentPage.set(new C_LeaseComponent_PageObject());
		c_ActivationGroupPage.set(new C_Activation_Group_PageObject());
		c_AG_EventPage.set(new C_AG_Event_PageObject());
		c_leaseComponent_eventPage.set(new C_LeaseComponent_Event_PageObject());
		aG_SchedulesPage.set(new AG_Schedules());
		batchManagement_pageObject.set(new BatchManagement_PageObject());
		hamburgerMenu_pageObject.set(new HamburgerMenu_PageObject());
		reportingModule_pageObject.set(new ReportingModule_PageObject());
		sapPostingBot_PageObject.set(new SAP_PostingBot_PageObject());
		aG_PostingDocument_MetaModel.set(new AG_PostingDocument_MetaModel());
		aG_JournalPosting_MetaModel.set(new AG_JournalPosting_MetaModel());
		aG_chargePosting_MetaModel.set(new AG_ChargePosting_MetaModel());
		importExport_pageObject.set(new ImportExport_PageObject());
		leaseComponents_validations.set(new LeaseComponents_Validations());
		activationGroup_validations.set(new ActivationGroup_Validations());
		activationGroup_Classifications.set(new AG_Classifications_MetaModel());
		dashboard_pageObject.set(new Dashboard_PageObject());
		defaultConfigValues_PageObject.set(new defaultValues_PageObject());
		ERPFieldMapping_PageObject.set(new ERPFieldMapping_PageObject());
		sapSyncBot_PageObject.set(new SAPSyncBot_PageObject());
		auditLogs_PageObject.set((new AuditLogs_PageObject()));
		userAuxiliary_PageObject.set((new UserAuxiliary_PageObject()));
	}

}
