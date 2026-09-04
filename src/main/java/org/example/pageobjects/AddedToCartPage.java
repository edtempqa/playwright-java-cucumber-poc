package org.example.pageobjects;

import com.microsoft.playwright.Locator;
import org.example.core.ScenarioBrowser;
import org.example.domain.Product;

import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.trim;
import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.apache.commons.lang3.StringUtils.replace;

public class AddedToCartPage extends BasePage {

    public AddedToCartPage(final ScenarioBrowser scenarioBrowser) {
        super(scenarioBrowser);
    }

    private Locator getProductNameLocator() {
        return getFrameLocator().locator("div.blockcart-modal__name");
    }

    private Locator getProductPriceLocator() {
        return getFrameLocator().locator("div.blockcart-modal__price.d-lg-block");
    }

    private Locator getProductSizeLocator() {
        return getFrameLocator().locator("div.blockcart-modal__property.size");
    }

    private Locator getProductColorLocator() {
        return getFrameLocator().locator("div.blockcart-modal__property.color");
    }

    private Locator getProductQuantityLocator() {
        return getFrameLocator().locator("div.blockcart-modal__quantity");
    }

    private Locator getProceedToCheckoutLocator() {
        return getFrameLocator().locator("div#blockcart-modal div.modal-footer a");
    }

    public Product getAddedToCartProduct(final boolean getColor) {
        return Product.builder()
                .name(getProductNameLocator().textContent())
                .price(getProductPriceLocator().textContent())
                .size(getValueAfterColon(getProductSizeLocator().textContent()))
                .color(getColor ? getValueAfterColon(getProductColorLocator().textContent()) : EMPTY)
                .quantity(parseInt(getValueAfterColon(getProductQuantityLocator().textContent())))
                .build();
    }

    public void clickProceedToCheckoutButton() {
        getProceedToCheckoutLocator().click();
    }

    private String getValueAfterColon(final String stringValue) {
        return trim(substringAfter(replace(stringValue, "\u00A0", ""), ":"));
    }
}
