package com.test.hulkstore.service;

import com.test.hulkstore.model.Paginator;
import com.test.hulkstore.model.Product;
import com.test.hulkstore.model.ProductList;

public interface ProductService {
    public String addProduct(Product product);

    public ProductList getProducts(Paginator paginator);

    public Product getProductById(Long id);

    public String updateProduct(Product product);
}
