package io.github.icekingoz.config;

import java.io.InputStream;
import java.util.Properties;

/**
 * Single source of truth for configuration.
 * Precedence: JVM system property (-Dkey=value)  >  config.properties  >  hard default.
 * This is what lets the SAME suite run locally, in CI, and against any environment.
 */
public final class ConfigReader {

    private static final Properties PROPS = new Properties();

    static {
        try (InputStream in = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config/config.properties")) {
            if (in == null) throw new IllegalStateException("config/config.properties not found on classpath");
            PROPS.load(in);
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Failed to load configuration: " + e.getMessage());
        }
    }

    private ConfigReader() { }

    public static String get(String key) {
        return System.getProperty(key, PROPS.getProperty(key));
    }

    public static String get(String key, String defaultValue) {
        return System.getProperty(key, PROPS.getProperty(key, defaultValue));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }
}
