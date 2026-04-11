package utils;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class AssertionUtils {

    private static final Logger log = LogManager.getLogger(AssertionUtils.class);

    // =========================
    // STATUS CODE VALIDATION
    // =========================
    public static void verifyStatusCode(Response res, int expected) {
        int actual = res.getStatusCode();
        log.info("Validating Status Code -> Expected: {}, Actual: {}", expected, actual);
        Assert.assertEquals(actual, expected, "Status code mismatch");
    }

    // =========================
    // GENERIC EQUALITY CHECK
    // =========================
    public static void verifyEqual(Object actual, Object expected, String message) {
        log.info("Validating Equality -> Expected: {}, Actual: {}", expected, actual);
        Assert.assertEquals(actual, expected, message);
    }

    // =========================
    // NOT NULL CHECK
    // =========================
    public static void verifyNotNull(Object object, String message) {
        log.info("Validating Not Null");
        Assert.assertNotNull(object, message);
    }

    // =========================
    // TRUE CONDITION CHECK
    // =========================
    public static void verifyTrue(boolean condition, String message) {
        log.info("Validating Condition is TRUE");
        Assert.assertTrue(condition, message);
    }

    // =========================
    // FALSE CONDITION CHECK
    // =========================
    public static void verifyFalse(boolean condition, String message) {
        log.info("Validating Condition is FALSE");
        Assert.assertFalse(condition, message);
    }

    // =========================
    // RESPONSE BODY CONTAINS
    // =========================
    public static void verifyContains(Response res, String expectedText) {
        String body = res.asString();
        log.info("Validating Response Contains -> {}", expectedText);
        Assert.assertTrue(body.contains(expectedText),
                "Response does not contain expected text: " + expectedText);
    }

    // =========================
    // JSON FIELD VALIDATION
    // =========================
    public static void verifyJsonField(Response res, String jsonPath, Object expectedValue) {
        Object actual = res.jsonPath().get(jsonPath);
        log.info("Validating JSON Field -> {} | Expected: {}, Actual: {}", jsonPath, expectedValue, actual);
        Assert.assertEquals(actual, expectedValue,
                "Mismatch at JSON path: " + jsonPath);
    }

    // =========================
    // RESPONSE TIME VALIDATION
    // =========================
    public static void verifyResponseTime(Response res, long maxTimeMs) {
        long time = res.getTime();
        log.info("Validating Response Time -> {} ms (Max allowed: {} ms)", time, maxTimeMs);
        Assert.assertTrue(time <= maxTimeMs,
                "Response time exceeded: " + time + "ms");
    }
}