package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static final String REPORT_PATH = "target/extent-reports/ExtentReport.html";

    public static ExtentReports getInstance() {
        if (extent == null) {
            new File("target/extent-reports").mkdirs();

            ExtentSparkReporter spark = new ExtentSparkReporter(REPORT_PATH);

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Framework", "REST Assured + Cucumber 7");
            extent.setSystemInfo("Author", "Your Name");
            extent.setSystemInfo("Base URL", "https://petstore.swagger.io/v2");
        }
        return extent;
    }

    public static void openReport() {
        try {
            File report = new File(REPORT_PATH);
            if (report.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(report);
                System.out.println("Report opened: " + report.getAbsolutePath());
            } else {
                System.out.println("Report not found: " + report.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("Could not auto-open: " + e.getMessage());
        }
    }
}