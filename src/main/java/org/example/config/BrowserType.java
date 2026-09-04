package org.example.config;

import java.util.Locale;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.upperCase;
import static org.apache.commons.lang3.StringUtils.trim;

public enum BrowserType {

    CHROMIUM,
    FIREFOX,
    WEBKIT;

    public static BrowserType fromString(final String value) {
        if (isBlank(value)) {
            return CHROMIUM;
        }

        try {
            return BrowserType.valueOf(upperCase(trim(value), Locale.ROOT));
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(
                    "Unsupported browser type '%s'. Supported values: chromium, firefox, webkit"
                            .formatted(value),
                    exception
            );
        }
    }
}
