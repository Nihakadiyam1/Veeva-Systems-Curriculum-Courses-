package client;

import base.BaseTest;
import io.restassured.response.Response;

public class UserClient {

    public Response createUser(Object body) {
        return BaseTest.request()
                .body(body)
                .post("/user");
    }

    public Response getUser(String username) {
        return BaseTest.request()
                .get("/user/" + username);
    }

    public Response login(String username, String password) {
        return BaseTest.request()
                .queryParam("username", username)
                .queryParam("password", password)
                .get("/user/login");
    }
}