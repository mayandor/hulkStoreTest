package com.test.hulkStore.repository;

import com.test.hulkStore.model.Paginator;
import com.test.hulkStore.model.Product;

import java.util.List;

public interface ProductRepository {

    public Long addProduct(Product product);

    public List<Product> getProducts(Paginator paginator);

    public int countProducts();

    public Product getProductById(Long id);

    public int updateProduct(Product product);
}
