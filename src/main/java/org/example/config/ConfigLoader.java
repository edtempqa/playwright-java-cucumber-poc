package org.example.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.InputStream;
import org.example.config.TestConfig.BrowserConfig;

import org.apache.commons.lang3.Strings;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.trim;
import static java.lang.Boolean.parseBoolean;
import static org.example.config.BrowserType.CHROMIUM;
import static org.example.config.BrowserType.fromString;
import static org.example.config.TestConfig.BrowserConfig.DEFAULT;

public final class ConfigLoader {

    private static final String CONFIG_RESOURCE = "config.yaml";
    private static final String BROWSER_TYPE = "browser.type";
    private static final String BROWSER_HEADLESS = "browser.headless";
    private static final String BROWSER_MAXIMIZED = "browser.maximized";

    private static final ObjectMapper YAML_MAPPER =
            new ObjectMapper(new YAMLFactory())
                    .configure(
                            DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                            true
                    );

    private ConfigLoader() {
    }

    public static BrowserConfig loadBrowserConfig() {
        final var testConfigFromFile = loadTestConfigFromFile();

        final var browserConfigFromFile =
                testConfigFromFile.browser() == null
                        ? DEFAULT
                        : testConfigFromFile.browser();

        final var browserType =
                firstNonBlankValue(
                        System.getProperty(BROWSER_TYPE),
                        browserConfigFromFile.type().name(),
                        CHROMIUM.name()
                );

        final var browserHeadless =
                resolveBoolean(
                        BROWSER_HEADLESS,
                        browserConfigFromFile.headless(),
                        true
                );

        final var browserStartMaximized =
                resolveBoolean(
                        BROWSER_MAXIMIZED,
                        browserConfigFromFile.maximized(),
                        true
                );

        return new BrowserConfig(
                fromString(browserType),
                browserHeadless,
                browserStartMaximized,
                browserConfigFromFile.timeout(), // Load Playwright global timeout from config file only
                browserConfigFromFile.navigationTimeout() // Load Playwright global navigation timeout from config file only
        );
    }

    private static TestConfig loadTestConfigFromFile() {
        try (final InputStream inputStream =
                     ConfigLoader.class
                             .getClassLoader()
                             .getResourceAsStream(CONFIG_RESOURCE)) {

            if (inputStream == null) {
                return TestConfig.DEFAULT;
            }

            final TestConfig testConfigFromFile = YAML_MAPPER.readValue(inputStream, TestConfig.class);

            return testConfigFromFile == null
                    ? TestConfig.DEFAULT
                    : testConfigFromFile;

        } catch (IOException exception) {
            throw new IllegalStateException(
                    "Could not read configuration from file: '%s'. reason: '%s'"
                            .formatted(CONFIG_RESOURCE, exception.getMessage()),
                    exception
            );
        }
    }

    private static String firstNonBlankValue(
            final String firstValue,
            final String secondValue,
            final String fallbackValue
    ) {
        if (!isEmpty(firstValue)) {
            return trim(firstValue);
        }

        if (!isEmpty(secondValue)) {
            return trim(secondValue);
        }

        return fallbackValue;
    }

    private static boolean resolveBoolean(
            String systemProperty,
            Boolean yamlValue,
            boolean defaultValue
    ) {
        final var commandLineValue = System.getProperty(systemProperty);

        if (!isEmpty(commandLineValue)) {
            return parseBooleanProperty(
                    systemProperty,
                    commandLineValue
            );
        }

        return yamlValue != null
                ? yamlValue
                : defaultValue;
    }

    private static boolean parseBooleanProperty(
            final String propertyName,
            final String value
    ) {
        final var normalized = trim(value);
        if (!Strings.CI.equalsAny(normalized, "true", "false")) {
            throw new IllegalArgumentException(
                    "Property '%s' must be true or false, but was '%s'"
                            .formatted(propertyName, value)
            );
        }

        return parseBoolean(normalized);
    }
}
