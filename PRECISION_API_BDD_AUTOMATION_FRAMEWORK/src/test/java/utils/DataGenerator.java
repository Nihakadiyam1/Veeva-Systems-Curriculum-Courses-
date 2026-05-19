package utils;

import java.util.Random;
import java.util.UUID;

public class DataGenerator {

    private static final Random random = new Random();

    public static String generateUniquePetName(String baseName) {
        return baseName + "_" + UUID.randomUUID().toString().substring(0, 8);
    }

    public static long generateUniqueId() {
        return System.currentTimeMillis() + random.nextInt(10000);
    }

    public static String generateUniqueUsername() {
        return "user_" + UUID.randomUUID().toString().substring(0, 8);
    }

    public static String generateInvalidEmail() {
        return "invalid_email_" + random.nextInt(1000);
    }
}
