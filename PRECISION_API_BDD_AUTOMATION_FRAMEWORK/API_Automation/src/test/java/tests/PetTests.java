package tests;

import base.BaseTest;
import client.PetClient;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class PetTests extends BaseTest {

    private static final Logger log = LogManager.getLogger(PetTests.class);

    PetClient client;
    int petId;
    String petName;

    @BeforeMethod
    public void init() {
        setup();
        client = new PetClient();

        petId = new Random().nextInt(100000);
        petName = "pet_" + petId;

        log.info("Initialized test data -> ID: {}, Name: {}", petId, petName);
    }

    // =========================
    // TEST CASE 1: PET LIFECYCLE (CRUD)
    // =========================
    @Test
    public void testPetLifeCycle() {

        log.info("Starting Pet Lifecycle Test");

        // CREATE
        String createBody = "{ \"id\": " + petId + ", \"name\": \"" + petName + "\", \"status\": \"available\" }";
        Response create = client.createPet(createBody);
        Assert.assertEquals(create.getStatusCode(), 200, "Create failed");

        int id = create.jsonPath().getInt("id");

        // READ
        Response get = client.getPet(id);
        Assert.assertNotNull(get, "GET response is null");
        Assert.assertEquals(get.getStatusCode(), 200);
        Assert.assertEquals(get.jsonPath().getString("name"), petName);
        Assert.assertEquals(get.jsonPath().getString("status"), "available");

        // UPDATE
        String updateBody = "{ \"id\": " + id + ", \"name\": \"" + petName + "\", \"status\": \"sold\" }";
        Response update = client.updatePet(updateBody);
        Assert.assertEquals(update.getStatusCode(), 200, "Update failed");

        // DELETE
        Response delete = client.deletePet(id);
        Assert.assertEquals(delete.getStatusCode(), 200, "Delete failed");

        // VERIFY DELETE
        Response afterDelete = client.getPet(id);
        Assert.assertEquals(afterDelete.getStatusCode(), 404, "Pet should not exist");

        log.info("Pet Lifecycle Test Completed");
    }

    // =========================
    // TEST CASE 2: INVENTORY VALIDATION
    // =========================
    @Test
    public void testInventory() {

        log.info("Starting Inventory Test");

        Response inventory = client.getInventory();
        Assert.assertEquals(inventory.getStatusCode(), 200);

        int availableCount = inventory.jsonPath().getInt("available");

        Response pets = client.getPetsByStatus("available");
        List<Object> petList = pets.jsonPath().getList("");

        int apiCount = petList.size();

        log.info("Inventory count: {}, API count: {}", availableCount, apiCount);

        int difference = Math.abs(availableCount - apiCount);

        Assert.assertTrue(difference <= 5,
                "Inventory mismatch: inventory=" + availableCount +
                        ", apiCount=" + apiCount);
    }

    // =========================
    // TEST CASE 3: NEGATIVE TESTING
    // =========================
    @Test
    public void negativeTestUser() {

        log.info("Starting Negative User Test");

        int id = new Random().nextInt(100000);
        String username = "user_" + id;

        // CREATE USER (INVALID EMAIL)
        String body = "{ \"id\": " + id + ", \"username\": \"" + username + "\", \"email\": \"invalid_email\" }";
        Response create = client.createUser(body);

        log.info("Create user response code: {}", create.getStatusCode());

        // GET NON-EXISTENT USER
        Response getUser = client.getUser("nonExistentUser123");
        Assert.assertEquals(getUser.getStatusCode(), 404);
        Assert.assertTrue(getUser.asString().contains("User not found"));

        // INVALID LOGIN
        Response login = client.loginUser("wrong", "wrong");

        log.info("Login response: {}", login.asString());

        Assert.assertEquals(login.getStatusCode(), 200);

        //Assert.assertEquals(login.getStatusCode(), 200);
        Assert.assertTrue(login.asString().contains("code"));
    }

    // =========================
    // TEST CASE 4: CROSS ENDPOINT VALIDATION
    // =========================
    @Test
    public void testCrossEndPoint() {

        log.info("Starting Cross Endpoint Test");

        // CREATE PET WITH CATEGORY
        String createBody = "{ \"id\": " + petId + ", \"name\": \"" + petName + "\", \"status\": \"available\", " +
                "\"category\": { \"id\": 1, \"name\": \"HighValueBulldog\" } }";

        client.createPet(createBody);

        // UPDATE STATUS
        String updateBody = "{ \"id\": " + petId + ", \"name\": \"" + petName + "\", \"status\": \"sold\" }";
        client.updatePet(updateBody);

        // VERIFY INVENTORY
        Response inventory = client.getInventory();
        Assert.assertEquals(inventory.getStatusCode(), 200);

        // FETCH SOLD PETS
        Response response = client.getPetsByStatus("sold");
        List<Number> ids = response.jsonPath().getList("id");

        boolean found = ids.stream().anyMatch(id -> id.intValue()==petId);

        Assert.assertTrue(found, "Pet ID not found in sold list");

        log.info("Cross Endpoint Test Completed");
    }
}