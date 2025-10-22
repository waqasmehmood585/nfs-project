package com.nakisa.nlaAutomation.runnerFiles;

import com.nakisa.nlaAutomation.RunTheAutomation;
import com.nakisa.nlaAutomation.Listeners.CustomAbstractTestNGCucumberTests;

import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.DataProvider;

import io.cucumber.testng.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/featureFiles"},
		glue = {"classpath:com.nakisa.nlaAutomation"},
		tags = ("@Smoke"),
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
//@WMPreUpgradeTest

@CucumberContextConfiguration
@SpringBootTest(classes = RunTheAutomation.class)
class E1_Runner extends CustomAbstractTestNGCucumberTests {
	@DataProvider(parallel = true)
	@Override
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
