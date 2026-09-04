package org.example.core;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import org.example.config.TestConfig.BrowserConfig;

import static java.util.List.of;

public final class BrowserFactory {

    private BrowserFactory() {
    }

    public static Browser createBrowser(
            final Playwright playwright,
            final BrowserConfig browserConfig
    ) {
        var options = new LaunchOptions()
                        .setHeadless(browserConfig.headless())
                        .setArgs(browserConfig.maximized()? of("--start-maximized") : of());

        return switch (browserConfig.type()) {
            case CHROMIUM ->
                    playwright.chromium().launch(options);
            case FIREFOX ->
                    playwright.firefox().launch(options);
            case WEBKIT ->
                    playwright.webkit().launch(options);
        };
    }
}