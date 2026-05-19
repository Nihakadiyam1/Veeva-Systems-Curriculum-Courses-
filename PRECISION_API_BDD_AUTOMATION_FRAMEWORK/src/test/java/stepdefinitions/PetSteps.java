package stepdefinitions;

import client.PetClient;
import client.UserClient;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.DataGenerator;

import java.util.List;

public class PetSteps {

    PetClient petClient = new PetClient();
    UserClient userClient = new UserClient();

    Response response;
    int petId;
    String petName;
    String initialStatus;

    // =========================
    // TEST CASE 1: PET LIFECYCLE
    // =========================

    @Given("I create a pet with status {string}")
    public void createPet(String status) {

        petId = DataGenerator.getRandomId();
        petName = DataGenerator.getRandomName();
        initialStatus = status;

        String body = "{ \"id\": " + petId + ", \"name\": \"" + petName + "\", \"status\": \"" + status + "\" }";

        response = petClient.createPet(body);

        Assert.assertEquals(response.getStatusCode(), 200);

        // Extract ID from response
        petId = response.jsonPath().getInt("id");
    }

    @When("I get the pet")
    public void getPet() {
        response = petClient.getPet(petId);
    }

    @Then("validate pet details")
    public void validatePet() {
        Assert.assertEquals(response.jsonPath().getString("name"), petName);
        Assert.assertEquals(response.jsonPath().getString("status"), initialStatus);
    }

    @When("I update pet to {string}")
    public void updatePet(String status) {

        String body = "{ \"id\": " + petId + ", \"name\": \"" + petName + "\", \"status\": \"" + status + "\" }";

        response = petClient.updatePet(body);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @When("I delete the pet")
    public void deletePet() {
        response = petClient.deletePet(petId);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Then("pet should be deleted")
    public void verifyDeleted() {
        response = petClient.getPet(petId);
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    // =========================
    // TEST CASE 2: INVENTORY
    // =========================

    @When("I fetch inventory")
    public void fetchInventory() {
        response = petClient.getInventory();
    }

    @Then("inventory should match available pets")
    public void validateInventory() {

        int inventoryCount = response.jsonPath().getInt("available");

        Response pets = petClient.getPetsByStatus("available");
        int apiCount = pets.jsonPath().getList("$").size();

        Assert.assertEquals(apiCount, inventoryCount);
    }

    // =========================
    // TEST CASE 3: NEGATIVE
    // =========================

    @Given("I create user with invalid email")
    public void createInvalidUser() {

        String body = "{ \"username\": \"" + DataGenerator.getUniqueUsername() + "\", \"email\": \"invalid_email\" }";

        response = userClient.createUser(body);
    }

    @When("I fetch non existing user")
    public void fetchInvalidUser() {
        response = userClient.getUser("nonExistentUser123");
    }

    @Then("user should not exist")
    public void validateUserNotFound() {
        Assert.assertEquals(response.getStatusCode(), 404);
        Assert.assertTrue(response.asString().contains("User not found"));
    }

    @When("I login with invalid credentials")
    public void invalidLogin() {
        response = userClient.login("wrongUser", "wrongPass");
    }

    @Then("no session token should be returned")
    public void validateInvalidLogin() {
        Assert.assertFalse(response.asString().toLowerCase().contains("session"));
    }

    // =========================
    // TEST CASE 4: CROSS ENDPOINT
    // =========================

    @Then("pet should exist in sold list")
    public void crossEndpointValidation() {

        Response soldPets = petClient.getPetsByStatus("sold");

        List<Integer> ids = soldPets.jsonPath().getList("id");

        boolean found = ids.stream().anyMatch(id -> id == petId);

        Assert.assertTrue(found);
    }
}