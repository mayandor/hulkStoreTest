package com.test.hulkstore.repository;

import com.test.hulkstore.model.Paginator;
import com.test.hulkstore.model.Product;

import java.util.List;

public interface ProductRepository {

    public Long addProduct(Product product);

    public List<Product> getProducts(Paginator paginator);

    public int countProducts();

    public Product getProductById(Long id);

    public int updateProduct(Product product);
}
