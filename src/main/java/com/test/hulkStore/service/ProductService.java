package com.test.hulkStore.service;

import com.test.hulkStore.model.Paginator;
import com.test.hulkStore.model.Product;
import com.test.hulkStore.model.ProductList;

import java.util.List;

public interface ProductService {
    public String addProduct(Product product);

    public ProductList getProducts(Paginator paginator);

    public Product getProductById(Long id);

    public String updateProduct(Product product);
}
