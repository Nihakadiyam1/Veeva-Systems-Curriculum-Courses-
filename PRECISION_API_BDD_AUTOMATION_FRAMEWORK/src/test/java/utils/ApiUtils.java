package utils;

import config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class ApiUtils {
    private static final Logger logger = LogManager.getLogger(ApiUtils.class);
    private static final String BASE_URL = ConfigManager.getInstance().getBaseUrl();

    public static RequestSpecification getRequestSpec() {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().all();
    }

    public static Response post(String endpoint, Object body) {
        logger.info("POST request to: {}", endpoint);
        Response response = getRequestSpec()
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract().response();
        logger.info("Response status: {}", response.getStatusCode());
        return response;
    }

    public static Response get(String endpoint) {
        logger.info("GET request to: {}", endpoint);
        Response response = getRequestSpec()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract().response();
        logger.info("Response status: {}", response.getStatusCode());
        return response;
    }

    public static Response get(String endpoint, Map<String, String> queryParams) {
        logger.info("GET request to: {} with params: {}", endpoint, queryParams);
        Response response = getRequestSpec()
                .queryParams(queryParams)
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract().response();
        logger.info("Response status: {}", response.getStatusCode());
        return response;
    }

    public static Response put(String endpoint, Object body) {
        logger.info("PUT request to: {}", endpoint);
        Response response = getRequestSpec()
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .extract().response();
        logger.info("Response status: {}", response.getStatusCode());
        return response;
    }

    public static Response delete(String endpoint) {
        logger.info("DELETE request to: {}", endpoint);
        Response response = getRequestSpec()
                .when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract().response();
        logger.info("Response status: {}", response.getStatusCode());
        return response;
    }
}
