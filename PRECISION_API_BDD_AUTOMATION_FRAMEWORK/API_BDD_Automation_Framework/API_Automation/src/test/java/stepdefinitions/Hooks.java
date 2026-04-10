package stepdefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import utils.ExtentManager;

public class Hooks {

    // ✅ Make final (good practice)
    private static final ExtentReports extent = ExtentManager.getInstance();

    // ❗ Do NOT make final
    private static ExtentTest test;

    @Before
    public void beforeScenario(Scenario scenario) {

        test = extent.createTest(scenario.getName());
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            test.fail("Step failed: " + scenario.getName());
        } else {
            test.pass("Step passed");
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            test.fail("Scenario failed: " + scenario.getName());
        } else {
            test.pass("Scenario passed");
        }

        extent.flush(); // write report
    }
}