package client;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PetClient {

    // CREATE PET
    public Response createPet(String body) {
        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(body)
                .post("/pet");
    }

    // GET PET BY ID
    public Response getPet(int id) {
        return RestAssured
                .given()
                .get("/pet/" + id);
    }

    // UPDATE PET
    public Response updatePet(String body) {
        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(body)
                .put("/pet");
    }

    // DELETE PET
    public Response deletePet(int id) {
        return RestAssured
                .given()
                .delete("/pet/" + id);
    }

    // GET INVENTORY
    public Response getInventory() {
        return RestAssured
                .given()
                .get("/store/inventory");
    }

    // GET PETS BY STATUS
    public Response getPetsByStatus(String status) {
        return RestAssured
                .given()
                .queryParam("status", status)
                .get("/pet/findByStatus");
    }

    // CREATE USER
    public Response createUser(String body) {
        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(body)
                .post("/user");
    }

    // GET USER
    public Response getUser(String username) {
        return RestAssured
                .given()
                .get("/user/" + username);
    }

    // LOGIN USER
    public Response loginUser(String username, String password) {
        return RestAssured
                .given()
                .queryParam("username", username)
                .queryParam("password", password)
                .get("/user/login");
    }
}