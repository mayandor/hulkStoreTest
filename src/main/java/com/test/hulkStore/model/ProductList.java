package com.test.hulkStore.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductList {
    private List<Product> products = new ArrayList<>();

    @JsonProperty(value = "pagination")
    private Paginator pagination;

}
