package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.UUID;

public class DataGenerator {

    private static final Logger log = LogManager.getLogger(DataGenerator.class);
    private static final Random random = new Random();

    // =========================
    // RANDOM ID
    // =========================
    public static int getRandomId() {
        int id = random.nextInt(100000);
        log.info("Generated Random ID: {}", id);
        return id;
    }

    // =========================
    // RANDOM NAME
    // =========================
    public static String getRandomName() {
        String name = "Pet_" + System.currentTimeMillis();
        log.info("Generated Random Name: {}", name);
        return name;
    }

    // =========================
    // RANDOM STRING
    // =========================
    public static String getRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        String result = sb.toString();
        log.info("Generated Random String: {}", result);
        return result;
    }

    // =========================
    // RANDOM EMAIL
    // =========================
    public static String getRandomEmail() {
        String email = "user_" + UUID.randomUUID().toString().substring(0, 8) + "@test.com";
        log.info("Generated Random Email: {}", email);
        return email;
    }

    // =========================
    // RANDOM NUMBER IN RANGE
    // =========================
    public static int getRandomNumber(int min, int max) {
        int number = random.nextInt((max - min) + 1) + min;
        log.info("Generated Random Number: {}", number);
        return number;
    }

    // =========================
    // RANDOM STATUS (FOR PET API)
    // =========================
    public static String getRandomStatus() {
        String[] statuses = {"available", "pending", "sold"};
        String status = statuses[random.nextInt(statuses.length)];
        log.info("Generated Random Status: {}", status);
        return status;
    }

    // =========================
    // UNIQUE USERNAME
    // =========================
    public static String getUniqueUsername() {
        String username = "user_" + System.currentTimeMillis();
        log.info("Generated Username: {}", username);
        return username;
    }
}