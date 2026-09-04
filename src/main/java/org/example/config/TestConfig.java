package org.example.config;

public record TestConfig(BrowserConfig browser) {

    public static final TestConfig DEFAULT = new TestConfig(BrowserConfig.DEFAULT);

    public record BrowserConfig(
            BrowserType type,
            boolean headless,
            boolean maximized,
            double timeout,
            double navigationTimeout
    ) {
        public static final BrowserConfig DEFAULT =
                new BrowserConfig(
                        BrowserType.CHROMIUM,
                        true,
                        false,
                        10000,
                        20000
                );
    }
}
