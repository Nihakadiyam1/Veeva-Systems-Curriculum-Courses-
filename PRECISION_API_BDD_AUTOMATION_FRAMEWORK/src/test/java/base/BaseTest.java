package base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import utils.ConfigReader;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected static RequestSpecification requestSpec;

    public static void setup() {

        RestAssured.baseURI = ConfigReader.get("base.url");

        requestSpec = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");
    }

    public static RequestSpecification request() {
        if (requestSpec == null) {
            setup();
        }
        return requestSpec;
    }
}