package org.example.pageobjects;

import org.example.core.ScenarioBrowser;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PersonalInformationPage extends BasePage {

    public PersonalInformationPage(final ScenarioBrowser scenarioBrowser) {
        super(scenarioBrowser);
    }

    public void isDisplayed() {
        assertThat(getFrameLocator().locator("h1.page-title-section")).isVisible();
    }
}
