package client;

import io.restassured.response.Response;
import utils.Constants;
import static io.restassured.RestAssured.given;

public class UserClient {
    public Response getUserByUsername(String username) {
        return given()
                .when()
                .get(Constants.BASE_URL + "/user/" + username)
                .then().extract().response();
    }

    public Response login(String username, String password) {
        return given()
                .queryParam("username", username)
                .queryParam("password", password)
                .when()
                .get(Constants.BASE_URL + "/user/login")
                .then().extract().response();
    }
}