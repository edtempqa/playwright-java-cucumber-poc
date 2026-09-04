package org.example.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.example.core.ScenarioBrowser;
import org.example.core.ScenarioContext;
import org.example.pageobjects.AddedToCartPage;
import org.example.pageobjects.ShoppingCartPage;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ShoppingCartSteps extends BaseSteps {

    private final AddedToCartPage addedToCartPage;
    private final ShoppingCartPage shoppingCartPage;
    private final ScenarioContext scenarioContext;

    public ShoppingCartSteps(final ScenarioBrowser scenarioBrowser,
                         final ScenarioContext scenarioContext,
                         final AddedToCartPage addedToCartPage,
                         final ShoppingCartPage shoppingCartPage) {
        super(scenarioBrowser);
        this.addedToCartPage = addedToCartPage;
        this.shoppingCartPage = shoppingCartPage;
        this.scenarioContext = scenarioContext;
    }

    @Then("The Added to cart page is displayed")
    public void addedToCartPageDisplayed() {
        log.info("The Added to cart page is displayed");
        final var productFromCart = addedToCartPage
                .getAddedToCartProduct(!isBlank(scenarioContext.getProducts().getFirst().getColor()));
        assertThat(scenarioContext.getProducts().getFirst())
                .usingRecursiveComparison()
                .ignoringFields("price")
                .isEqualTo(productFromCart);
    }

    @When("I click the Proceed to checkout button")
    public void iClickProceedToCheckout() {
        log.info("I click the Proceed to checkout button");
        addedToCartPage.clickProceedToCheckoutButton();
    }

    @Then("The Shopping Cart page is displayed and contains the added product")
    public void shoppingCartPageIsDisplayed() {
        log.info("The Shopping Cart page is displayed");
        shoppingCartPage.cartNotEmpty();
        final var productsInCart = shoppingCartPage.getCartProducts();
        log.info("Products from cart: {}", productsInCart);
        assertThat(productsInCart).hasSize(1);
        assertThat(scenarioContext.getProducts().getFirst())
                .usingRecursiveComparison()
                // Ignore price because the Prestashop demo site has a bug on listing product prices
                .ignoringFields("price")
                .isEqualTo(productsInCart.getFirst());
    }

    @When("I click the Cart Proceed to checkout button")
    public void iClickProceedToCheckoutInCart() {
        log.info("I click the Cart Proceed to checkout button");
        shoppingCartPage.clickProceedToCheckoutButton();
    }
}
