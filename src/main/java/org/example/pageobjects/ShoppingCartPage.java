package org.example.pageobjects;

import com.microsoft.playwright.Locator;
import org.example.core.ScenarioBrowser;
import org.example.domain.Product;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.trim;

public class ShoppingCartPage extends BasePage {

    public ShoppingCartPage(final ScenarioBrowser scenarioBrowser) {
        super(scenarioBrowser);
    }

    private Locator getProceedToCheckoutLocator() {
        return getFrameLocator().locator("div.cart-summary__actions.checkout a");
    }

    public void clickProceedToCheckoutButton() {
        getProceedToCheckoutLocator().click();
    }

    private Locator getProductsLocator() {
        return getFrameLocator().locator("div.cart__item");
    }

    private String getProductColor(final Locator cartProduct) {
        final var colorLocator = cartProduct.locator("div.color span.product-line__item-value");

        return colorLocator.count() == 0
                ? EMPTY
                : trim(colorLocator.first().innerText());
    }

    private String getProductName(final Locator cartProduct) {
        return trim(cartProduct.locator("div.product-line__content a.product-line__title").textContent());
    }

    private String getProductPrice(final Locator cartProduct) {
        return trim(cartProduct.locator("span.product-line__item-price").textContent());
    }

    private String getProductSize(final Locator cartProduct) {
        return trim(cartProduct.locator("div.size span.product-line__item-value").textContent());
    }

    private int getProductQuantity(final Locator cartProduct) {
        return parseInt(trim(cartProduct.locator("input.js-cart-line-product-quantity").inputValue()));
    }

    public List<Product> getCartProducts() {
        return map(getProductsLocator(), this::toProduct);
    }

    private Product toProduct(final Locator cartProduct) {
        return new Product()
                .setName(getProductName(cartProduct))
                .setPrice(getProductPrice(cartProduct))
                .setSize(getProductSize(cartProduct))
                .setColor(getProductColor(cartProduct))
                .setQuantity(getProductQuantity(cartProduct));
    }

    public void cartNotEmpty() {
        assertThat(getProductsLocator().first()).isAttached();
    }
}
