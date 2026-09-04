package org.example.pageobjects;

import com.microsoft.playwright.Locator;
import org.example.core.ScenarioBrowser;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class ProductPage extends BasePage {

    public ProductPage(final ScenarioBrowser scenarioBrowser) {
        super(scenarioBrowser);
    }

    private Locator getProductSizeDropdownLocator() {
        return getFrameLocator().locator("div.product__variants select");
    }

    private Locator getProductQuantityLocator() {
        return getFrameLocator().locator("input#quantity_wanted");
    }

    private Locator getProductColorVariants() {
        return getFrameLocator().locator("div.product-variant__color");
    }

    private Locator getProductVariantForColorLocator(final String color) {
        return getProductColorVariants().filter(new Locator.FilterOptions()
                .setHas(page.locator("span.color")
                        .filter(new Locator.FilterOptions()
                                .setHasText("Color - " + color))));
    }

    private Locator getAddToCartButtonLocator() {
        return getFrameLocator().locator("button.product__add-to-cart-button");
    }

    public void selectProductSize(final String productSize) {
        getProductSizeDropdownLocator().selectOption(productSize);
    }

    public void setQuantity(final String quantityValue) {
        getProductQuantityLocator().fill(quantityValue);
    }

    public void selectProductVariantColor(final String color) {
        if (isBlank(color)) {
            return;
        }

        getProductVariantForColorLocator(color).locator("label.input-color__label").click();
    }

    public void clickAddToCartButton() {
        getAddToCartButtonLocator().click();
    }
}
