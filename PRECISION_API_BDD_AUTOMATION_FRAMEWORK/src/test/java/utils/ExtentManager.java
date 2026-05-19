package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

    private static final Logger log = LogManager.getLogger(ExtentManager.class);

    private static ExtentReports extent;

    // =========================
    // GET INSTANCE (Singleton)
    // =========================
    public static synchronized ExtentReports getInstance() {

        if (extent == null) {

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportPath = "target/ExtentReport_" + timestamp + ".html";

            log.info("Initializing Extent Report at: {}", reportPath);

            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

            // ===== REPORT CONFIG =====
            reporter.config().setReportName("API Automation Report");
            reporter.config().setDocumentTitle("Test Execution Report");
            reporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");

            extent = new ExtentReports();
            extent.attachReporter(reporter);

            // ===== SYSTEM INFO =====
            extent.setSystemInfo("Project", "PetStore API Automation");
            extent.setSystemInfo("Tester", System.getProperty("user.name"));
            extent.setSystemInfo("Environment", System.getProperty("env", "QA"));
            extent.setSystemInfo("OS", System.getProperty("os.name"));

            log.info("Extent Report initialized successfully");
        }

        return extent;
    }
}