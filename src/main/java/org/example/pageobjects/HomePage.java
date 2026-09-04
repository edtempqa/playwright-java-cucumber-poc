package org.example.pageobjects;

import com.microsoft.playwright.options.LoadState;
import org.example.core.ScenarioBrowser;

public class HomePage extends BasePage {

    private final HeaderPage headerPage;

    public HomePage(final ScenarioBrowser scenarioBrowser,
                    final HeaderPage headerPage) {
        super(scenarioBrowser);
        this.headerPage = headerPage;
    }

    public void open(final String url) {
        page.navigate(url);
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void searchBy(final String keywords) {
        headerPage.searchBy(keywords);
    }
}
