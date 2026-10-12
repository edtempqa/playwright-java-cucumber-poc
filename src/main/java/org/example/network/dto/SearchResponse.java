package org.example.network.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SearchResponse(List<SearchProduct> products) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record SearchProduct(String name, String price) {
    }
}
