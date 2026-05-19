package client;

import base.BaseTest;
import io.restassured.response.Response;

public class PetClient {

    public Response createPet(Object body) {
        return BaseTest.request()
                .body(body)
                .post("/pet");
    }

    public Response getPet(int id) {
        return BaseTest.request()
                .get("/pet/" + id);
    }

    public Response updatePet(Object body) {
        return BaseTest.request()
                .body(body)
                .put("/pet");
    }

    public Response deletePet(int id) {
        return BaseTest.request()
                .delete("/pet/" + id);
    }

    public Response getInventory() {
        return BaseTest.request()
                .get("/store/inventory");
    }

    public Response getPetsByStatus(String status) {
        return BaseTest.request()
                .queryParam("status", status)
                .get("/pet/findByStatus");
    }
}