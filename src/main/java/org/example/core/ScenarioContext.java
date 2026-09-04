package org.example.core;

import org.example.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

import static java.util.Objects.requireNonNull;
import static java.util.Collections.unmodifiableList;
import static java.util.Comparator.comparing;

public final class ScenarioContext {
    private static final Comparator<Product> BY_NAME = comparing(Product::getName);

    private final List<Product> products = new ArrayList<>();

    public void addProduct(final Product product) {
        requireNonNull(product, "It is not allowed to add null in the products list");
        requireNonNull(product.getName(), "It is not allowed to add product with null name in the products list");

        products.add(product);
        products.sort(BY_NAME);
    }

    public List<Product> getProducts() {
        return unmodifiableList(products);
    }
}
