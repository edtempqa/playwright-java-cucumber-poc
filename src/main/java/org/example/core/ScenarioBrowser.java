package org.example.core;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import static org.example.config.ConfigLoader.loadBrowserConfig;
import static org.example.core.BrowserFactory.createBrowser;

public final class ScenarioBrowser implements AutoCloseable {

    private final Playwright playwright;
    private final BrowserContext context;
    private final Page page;

    public ScenarioBrowser() {

        final var browserConfig = loadBrowserConfig();

        playwright = Playwright.create();

        final var browser = createBrowser(
                playwright,
                browserConfig
        );

        context = browser.newContext(
                new Browser.NewContextOptions()
                        .setViewportSize(null)
        );

        context.setDefaultTimeout(browserConfig.timeout());
        context.setDefaultNavigationTimeout(browserConfig.navigationTimeout());

        page = context.newPage();
    }

    public Page page() {
        return page;
    }

    public BrowserContext context() {
        return context;
    }

    @Override
    public void close() {
        try {
            context.close();
        } finally {
            playwright.close();
        }
    }

    @Override
    public String toString() {
        return "ScenarioBrowser@%s -> Playwright@%s | Context@%s | Page@%s"
                .formatted(
                        objectId(this),
                        objectId(playwright),
                        objectId(context),
                        objectId(page)
                );
    }

    private String objectId(final Object object) {
        return Integer.toHexString(
                System.identityHashCode(object)
        );
    }
}
