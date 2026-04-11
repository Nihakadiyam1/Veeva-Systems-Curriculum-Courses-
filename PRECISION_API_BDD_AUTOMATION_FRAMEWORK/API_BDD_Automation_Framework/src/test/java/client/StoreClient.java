package client;

import io.restassured.response.Response;
import utils.Constants;
import static io.restassured.RestAssured.given;

public class StoreClient {
    public Response getInventory() {
        return given()
                .when()
                .get(Constants.BASE_URL + "/store/inventory")
                .then().extract().response();
    }
}