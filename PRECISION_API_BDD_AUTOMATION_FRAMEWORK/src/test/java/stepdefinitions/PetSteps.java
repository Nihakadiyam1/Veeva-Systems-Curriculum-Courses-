package stepdefinitions;

import context.TestContext;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ApiUtils;
import utils.DataGenerator;

import java.util.*;

        import static org.junit.jupiter.api.Assertions.*;

public class PetSteps {
    private static final Logger logger = LogManager.getLogger(PetSteps.class);
    private final TestContext context;

    public PetSteps(TestContext context) {
        this.context = context;
    }

    @Given("I have a pet with name {string} and status {string}")
    public void iHaveAPetWithNameAndStatus(String petName, String status) {
        String uniquePetName = DataGenerator.generateUniquePetName(petName);
        long petId = DataGenerator.generateUniqueId();

        Map<String, Object> pet = new HashMap<>();
        pet.put("id", petId);
        pet.put("name", uniquePetName);
        pet.put("status", status);
        pet.put("photoUrls", List.of("[example.com](https://example.com/photo.jpg)"));

        Map<String, Object> category = new HashMap<>();
        category.put("id", 1);
        category.put("name", "Dogs");
        pet.put("category", category);

        context.setContext("petPayload", pet);
        context.setContext("expectedPetName", uniquePetName);
        context.setContext("expectedStatus", status);

        logger.info("Prepared pet payload with name: {} and status: {}", uniquePetName, status);
    }

    @Given("I have a pet with category {string} and status {string}")
    public void iHaveAPetWithCategoryAndStatus(String categoryName, String status) {
        String uniquePetName = DataGenerator.generateUniquePetName("Pet");
        long petId = DataGenerator.generateUniqueId();

        Map<String, Object> pet = new HashMap<>();
        pet.put("id", petId);
        pet.put("name", uniquePetName);
        pet.put("status", status);
        pet.put("photoUrls", List.of("[example.com](https://example.com/photo.jpg)"));

        Map<String, Object> category = new HashMap<>();
        category.put("id", DataGenerator.generateUniqueId());
        category.put("name", categoryName);
        pet.put("category", category);

        context.setContext("petPayload", pet);
        context.setContext("expectedPetName", uniquePetName);
        context.setContext("expectedStatus", status);
        context.setContext("categoryName", categoryName);

        logger.info("Prepared pet payload with category: {} and status: {}", categoryName, status);
    }

    @When("I create the pet via POST \\/pet")
    public void iCreateThePetViaPOSTPet() {
        Map<String, Object> petPayload = context.getContext("petPayload");
        Response response = ApiUtils.post("/pet", petPayload);
        context.setResponse(response);
    }

    @When("I retrieve the pet via GET \\/pet\\/\\{petId}")
    public void iRetrieveThePetViaGETPetPetId() {
        Long petId = context.getContext("petId");
        Response response = ApiUtils.get("/pet/" + petId);
        context.setResponse(response);
    }

    @When("I update the pet status to {string} via PUT \\/pet")
    public void iUpdateThePetStatusToPUTPet(String newStatus) {
        Map<String, Object> petPayload = context.getContext("petPayload");
        Long petId = context.getContext("petId");

        petPayload.put("id", petId);
        petPayload.put("status", newStatus);
        context.setContext("expectedStatus", newStatus);

        Response response = ApiUtils.put("/pet", petPayload);
        context.setResponse(response);
    }

    @When("I delete the pet via DELETE \\/pet\\/\\{petId}")
    public void iDeleteThePetViaDELETEPetPetId() {
        Long petId = context.getContext("petId");
        Response response = ApiUtils.delete("/pet/" + petId);
        context.setResponse(response);
    }

    @When("I fetch pets by status {string} via GET \\/pet\\/findByStatus")
    public void iFetchPetsByStatusViaGETPetFindByStatus(String status) {
        Map<String, String> params = Map.of("status", status);
        Response response = ApiUtils.get("/pet/findByStatus", params);
        context.setResponse(response);
        context.setContext("currentStatus", status);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        Response response = context.getResponse();
        assertEquals(expectedStatusCode, response.getStatusCode(),
                "Expected status code " + expectedStatusCode + " but got " + response.getStatusCode());
    }

    @Then("I extract the pet ID from the response")
    public void iExtractThePetIdFromTheResponse() {
        Response response = context.getResponse();
        Long petId = response.jsonPath().getLong("id");
        context.setContext("petId", petId);
        logger.info("Extracted pet ID: {}", petId);
    }

    @Then("the pet name should be {string}")
    public void thePetNameShouldBe(String expectedName) {
        // Using dynamic name stored in context
        String expectedPetName = context.getContext("expectedPetName");
        Response response = context.getResponse();
        String actualName = response.jsonPath().getString("name");
        assertTrue(actualName.startsWith(expectedName) || actualName.equals(expectedPetName),
                "Pet name mismatch. Expected to start with: " + expectedName);
    }

    @Then("the pet status should be {string}")
    public void thePetStatusShouldBe(String expectedStatus) {
        Response response = context.getResponse();
        String actualStatus = response.jsonPath().getString("status");
        assertEquals(expectedStatus, actualStatus, "Pet status mismatch");
    }

    @Then("the created pet should exist in the sold pets list using streams")
    public void theCreatedPetShouldExistInTheSoldPetsListUsingStreams() {
        Response response = context.getResponse();
        Long createdPetId = context.getContext("petId");

        List<Map<String, Object>> pets = response.jsonPath().getList("");

        boolean petFound = pets.stream()
                .filter(pet -> pet.get("id") != null)
                .anyMatch(pet -> {
                    Object idObj = pet.get("id");
                    long petId;
                    if (idObj instanceof Integer) {
                        petId = ((Integer) idObj).longValue();
                    } else if (idObj instanceof Long) {
                        petId = (Long) idObj;
                    } else {
                        petId = Long.parseLong(idObj.toString());
                    }
                    return petId == createdPetId;
                });

        assertTrue(petFound, "Created pet with ID " + createdPetId + " not found in sold pets list");
        logger.info("Successfully found pet ID {} in sold pets list using Java Streams", createdPetId);
    }
}
