package base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ConfigReader;
import utils.Constants;

import static io.restassured.RestAssured.given;

public class BaseTest {

    private static final Logger log = LogManager.getLogger(BaseTest.class);

    protected static RequestSpecification requestSpec;

    public static void setup() {

        log.info("Initializing Test Setup...");

        // Set Base URI from config
        RestAssured.baseURI = ConfigReader.get("base.url");

        // Build reusable request specification
        requestSpec = given()
                .header("Content-Type", Constants.CONTENT_TYPE_JSON)
                .header("Accept", Constants.CONTENT_TYPE_JSON);

        log.info("Base URI set to: {}", RestAssured.baseURI);
    }

    public static RequestSpecification getRequest() {
        return requestSpec;
    }
}