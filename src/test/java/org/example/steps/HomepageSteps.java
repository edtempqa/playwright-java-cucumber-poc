package org.example.steps;

import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import org.example.pageobjects.HomePage;
import org.example.core.ScenarioBrowser;

@Slf4j
public class HomepageSteps extends BaseSteps {

    private final HomePage homePage;

    public HomepageSteps(final ScenarioBrowser scenarioBrowser,
                         final HomePage homePage) {
        super(scenarioBrowser);
        this.homePage = homePage;
    }

    @Given("I am on the Prestashop website {string}")
    public void iAmOnPrestashopWebsite(final String url) {
        log.info("I navigate to the Prestashop website {}", url);
        homePage.open(url);
        log.info("I am on the Prestashop website {}", url);
    }
}
