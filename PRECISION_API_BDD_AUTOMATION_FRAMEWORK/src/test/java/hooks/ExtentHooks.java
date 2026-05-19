package hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ExtentReportManager;

public class ExtentHooks {

    private static final Logger logger = LogManager.getLogger(ExtentHooks.class);
    private static final ExtentReports extent = ExtentReportManager.getInstance();
    private static final ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
    private final TestContext context;

    public ExtentHooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        ExtentTest test = extent.createTest(scenario.getName());
        scenario.getSourceTagNames().forEach(tag ->
                test.assignCategory(tag.replace("@", "")));
        testThread.set(test);
        logger.info("SCENARIO STARTED : {}", scenario.getName());
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        ExtentTest test = testThread.get();
        if (test == null) return;
        if (scenario.isFailed()) {
            test.log(Status.FAIL, "Step FAILED");
        } else {
            test.log(Status.PASS, "Step passed");
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        ExtentTest test = testThread.get();
        if (test == null) return;

        if (scenario.isFailed()) {
            logger.error("SCENARIO FAILED : {}", scenario.getName());
            test.fail("Scenario Failed: " + scenario.getName());

            Response response = context.getResponse();
            if (response != null) {
                String evidence = "Status Code: " + response.getStatusCode()
                        + "\n\nBody:\n" + response.getBody().asPrettyString();
                test.fail("<pre>" + evidence + "</pre>");
            }
        } else {
            logger.info("SCENARIO PASSED : {}", scenario.getName());
            test.pass("Scenario Passed");
        }
        testThread.remove();
    }

    @AfterAll
    public static void afterSuite() {
        if (extent != null) {
            extent.flush();
            logger.info("Extent Report → target/extent-reports/ExtentReport.html");
            ExtentReportManager.openReport();
        }
    }
}