package com.nakisa.nlaAutomation.runnerFiles;

import com.nakisa.nlaAutomation.Listeners.CustomAbstractTestNGCucumberTests;
import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/featureFiles"},
        glue = {"classpath:com.nakisa.nlaAutomation"},
        tags = ("@CustomerUAT"),
        plugin = {"pretty", "json:Automation-Results/5_CustomerUAT.json", "summary",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true
)

//@CucumberContextConfiguration
//@SpringBootTest(classes = RunTheAutomation.class)
class TestRunner_5_CustomerUAT extends CustomAbstractTestNGCucumberTests {
    @DataProvider(parallel = true)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
