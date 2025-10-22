package com.nakisa.nlaAutomation.runnerFiles;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;

import com.nakisa.nlaAutomation.Listeners.CustomAbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/featureFiles"},
		glue = {"classpath:com.nakisa.nlaAutomation"},
		tags = ("@pfizer"),
		plugin = {"pretty", "html:target/cucumber-results.html", "summary",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		monochrome = true
)
//@GenericTest
//@UF/CLPTest
//@SmokeTest
//@PfizerTest
//@WatersTest
//@SouthAfricaTest
//@LessorSmokeTest
//
//@WMPreUpgradeTest

//@CucumberContextConfiguration
//@SpringBootTest(classes = RunTheAutomation.class)
class TestRunner_Pfizer extends CustomAbstractTestNGCucumberTests {
	@DataProvider(parallel = true)
	@Override
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
