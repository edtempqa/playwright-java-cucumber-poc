package org.example.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.example.core.ScenarioBrowser;
import org.example.core.ScenarioContext;
import org.example.domain.Product;
import org.example.pageobjects.HomePage;
import org.example.pageobjects.SearchListPage;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class SearchProductSteps extends BaseSteps {

    private final HomePage homePage;
    private final SearchListPage searchListPage;
    private final ScenarioContext scenarioContext;

    public SearchProductSteps(final ScenarioBrowser scenarioBrowser,
                              final HomePage homePage,
                              final ScenarioContext scenarioContext,
                              final SearchListPage searchListPage) {
        super(scenarioBrowser);

        this.homePage = homePage;
        this.searchListPage = searchListPage;
        this.scenarioContext = scenarioContext;
    }

    @When("I search for {string}")
    public void iSearchFor(final String keywords) {
        log.info("I search for {}", keywords);
        homePage.searchBy(keywords);
    }

    @Then("I see a list of {string} products")
    public void iSeeListOfProducts(final String product) {
        log.info("Check if products containing {} are displayed", product);
        final var products = searchListPage.getSearchResult();
        assertThat(products).hasSizeGreaterThan(0);
        assertThat(products)
                .extracting(Product::getName)
                .allSatisfy(productName ->
                        assertThat(productName).containsIgnoringCase(product));
        log.info("I see a list of {} products", products);
    }

    @And("I open the product page for {string}")
    public void iOpenTheProductPageFor(final String productName) {
        log.info("Opening the product page for first product with name {}", productName);
        final var product = searchListPage.clickOnFirstProductWithName(productName);
        scenarioContext.addProduct(product);
        log.debug("Selected product {}", product);
    }
}
