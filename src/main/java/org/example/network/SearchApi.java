package org.example.network;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import org.example.core.ScenarioBrowser;
import org.example.domain.Product;
import org.example.network.dto.SearchResponse;

import java.io.IOException;
import java.util.List;

public final class SearchApi {

    private final Page page;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public SearchApi(final ScenarioBrowser scenarioBrowser) {
        this.page = scenarioBrowser.page();
    }

    public Response captureSearchResponse(final Runnable searchAction) {
        return page.waitForResponse(
                response -> response.url().matches("https://[^/]+/search(?:\\?.*)?")
                        && response.request().method().equals("POST"),
                searchAction
        );
    }

    public List<Product> getProducts(final Response response) {
        try {
            final SearchResponse searchResponse =
                    MAPPER.readValue(response.body(), SearchResponse.class);

            return searchResponse.products().stream()
                    .map(product -> new Product()
                            .setName(product.name())
                            .setPrice(product.price()))
                    .toList();

        } catch (IOException exception) {
            throw new IllegalStateException(
                    "Cannot parse Prestashop search response",
                    exception
            );
        }
    }
}
