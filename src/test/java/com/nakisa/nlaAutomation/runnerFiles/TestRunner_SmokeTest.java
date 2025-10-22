package com.nakisa.nlaAutomation.runnerFiles;

import com.nakisa.nlaAutomation.RunTheAutomation;
import com.nakisa.nlaAutomation.Listeners.CustomAbstractTestNGCucumberTests;

import io.cucumber.junit.Cucumber;
import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.DataProvider;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/featureFiles"},
		glue = {"classpath:com.nakisa.nlaAutomation"},
//	tags = ("@NFSSmoke-P1 or @NFSSmoke-P2 or @NFSSmoke-P3 or @NFSSmoke-P4 or @NFSSmoke-P5 or @NFSSmoke-P6"),
		tags = "${cucumber.filter.tags}",
		plugin = {"pretty", "json:Automation-Results/Smoke.json", "summary",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		},
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

//@CucumberContextConfiguration
//@SpringBootTest(classes = RunTheAutomation.class)
class TestRunner_SmokeTest extends CustomAbstractTestNGCucumberTests {
	@DataProvider(parallel = true)
	@Override
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
