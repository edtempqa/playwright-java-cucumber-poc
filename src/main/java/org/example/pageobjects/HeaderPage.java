package org.example.pageobjects;

import com.microsoft.playwright.Locator;
import org.example.core.ScenarioBrowser;

public class HeaderPage extends BasePage {

    public HeaderPage(final ScenarioBrowser scenarioBrowser) {
        super(scenarioBrowser);
    }

    private Locator getSearchBoxLocator() {
        return getFrameLocator()
                .locator("input.js-search-input");
    }

    public void searchBy(final String keywords) {
        getSearchBoxLocator().fill(keywords);
        getSearchBoxLocator().press("Enter");
    }
}
