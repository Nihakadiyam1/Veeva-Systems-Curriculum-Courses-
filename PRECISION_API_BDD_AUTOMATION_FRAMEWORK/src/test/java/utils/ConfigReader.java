package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Logger log = LogManager.getLogger(ConfigReader.class);

    private static final Properties prop = new Properties();

    private static final String DEFAULT_CONFIG_PATH = "src/test/resources/config.properties";

    static {
        loadProperties(DEFAULT_CONFIG_PATH);
    }

    // =========================
    // LOAD PROPERTIES FILE
    // =========================
    private static void loadProperties(String filePath) {
        try (InputStream input = new FileInputStream(filePath)) {

            prop.load(input);
            log.info("Configuration loaded successfully from: {}", filePath);

        } catch (Exception e) {
            log.error("Failed to load config file: {}", filePath);
            throw new RuntimeException("Unable to load configuration file", e);
        }
    }

    // =========================
    // GET VALUE
    // =========================
    public static String get(String key) {
        String value = prop.getProperty(key);

        if (value == null) {
            log.error("Key not found in config: {}", key);
            throw new RuntimeException("Missing config key: " + key);
        }

        log.info("Fetching config -> {} = {}", key, value);
        return value;
    }

    // =========================
    // GET WITH DEFAULT VALUE
    // =========================
    public static String get(String key, String defaultValue) {
        String value = prop.getProperty(key, defaultValue);
        log.info("Fetching config with default -> {} = {}", key, value);
        return value;
    }

    // =========================
    // RELOAD CONFIG (OPTIONAL)
    // =========================
    public static void reload() {
        log.info("Reloading configuration...");
        loadProperties(DEFAULT_CONFIG_PATH);
    }

    // =========================
    // LOAD CUSTOM ENV FILE (ADVANCED)
    // =========================
    public static void loadEnv(String env) {
        String path = "src/test/resources/config-" + env + ".properties";
        log.info("Loading environment config: {}", env);
        loadProperties(path);
    }
}