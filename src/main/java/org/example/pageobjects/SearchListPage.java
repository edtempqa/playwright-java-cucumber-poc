package org.example.pageobjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.example.core.ScenarioBrowser;
import org.example.domain.Product;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.trim;

public class SearchListPage extends BasePage {

    public SearchListPage(final ScenarioBrowser scenarioBrowser) {
        super(scenarioBrowser);
    }

    private Locator getProductsLocators() {
        return getFrameLocator()
                .locator("div.products div.product-miniature__inner");
    }

    private Locator getProductsByName(final String productName) {
        return getProductsLocators()
                .filter(new Locator.FilterOptions()
                        .setHas(page.locator("a.product-miniature__title"))
                        .setHasText(productName));
    }

    public Product clickOnFirstProductWithName(final String productName) {
        final var productLocator = getProductsByName(productName).first();
        final var product = toProduct(productLocator);

        productLocator.locator("a.product-miniature__image-link").click();

        return product;
    }

    public List<Product> getSearchResult() {
        getFrameLocator().locator("nav.breadcrumb__wrapper li > span")
                .waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        return map(getProductsLocators(), this::toProduct);
    }

    private Product toProduct(final Locator productLocator) {
        return new Product()
                .setName(trim(productLocator.locator("a.product-miniature__title").textContent()))
                .setPrice(trim(productLocator.locator("div.product-miniature__price").textContent()));
    }
}
