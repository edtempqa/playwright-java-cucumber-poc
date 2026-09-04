package org.example.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.example.core.ScenarioBrowser;
import org.example.core.ScenarioContext;
import org.example.pageobjects.ProductPage;

import static java.lang.Integer.parseInt;

@Slf4j
public class ProductPageSteps extends BaseSteps {

    private final ProductPage productPage;
    private final ScenarioContext scenarioContext;

    public ProductPageSteps(final ScenarioBrowser scenarioBrowser,
                         final ScenarioContext scenarioContext,
                         final ProductPage productPage) {
        super(scenarioBrowser);
        this.productPage = productPage;
        this.scenarioContext = scenarioContext;
    }

    @And("I select the {string} product size")
    public void iSelectProductSize(final String productSize) {
        log.info("I select the {} product size", productSize);
        productPage.selectProductSize(productSize);
        // Set size for the first product in the list (it is safe as null products are not allowed in the list)
        // In case list is empty it throws NoSuchElementException
        scenarioContext.getProducts().getFirst().setSize(productSize);
    }

    @When("I enter valid {string} value in the quantity field")
    public void iEnterValidQuantity(final String quantityValue) {
        log.info("I enter valid {} value in the quantity field", quantityValue);
        productPage.setQuantity(quantityValue);
        // Set product quantity for the first product in the list
        // (it is safe as null products are not allowed in the list)
        // In case list is empty it throws NoSuchElementException
        scenarioContext.getProducts().getFirst().setQuantity(parseInt(quantityValue));
    }

    @When("I select valid {string} variant")
    public void iEnterColorVariant(final String color) {
        log.info("I select valid {} variant", color);
        productPage.selectProductVariantColor(color);
        // Set product color for the first product in the list
        // (it is safe as null products are not allowed in the list)
        // In case list is empty it throws NoSuchElementException
        scenarioContext.getProducts().getFirst().setColor(color);
    }

    @When("I click the Add to cart a button")
    public void iClickTheAddToCartButton() {
        log.info("I click the Add to cart a button");
        productPage.clickAddToCartButton();
    }
}
