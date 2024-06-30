package com.test.hulkStore.service.impl;

import com.test.hulkStore.exceptions.NotFoundException;
import com.test.hulkStore.model.Paginator;
import com.test.hulkStore.model.Product;
import com.test.hulkStore.model.ProductList;
import com.test.hulkStore.repository.ProductRepository;
import com.test.hulkStore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public String addProduct(Product product) {
        productRepository.addProduct(product);
        return product.getName();
    }

    @Override
    public ProductList getProducts(Paginator paginator) {
        ProductList productList = new ProductList();
        productList.setProducts(productRepository.getProducts(paginator));
        int totalElements =  productRepository.countProducts();
        paginator.setTotalRegisters(totalElements);
        paginator.setTotalPages((int) Math.ceil((double) totalElements/paginator.getPerPage()));
        productList.setPagination(paginator);
        return productList;
    }

    @Override
    public Product getProductById(Long id) {
        Product product = productRepository.getProductById(id);
        if(product == null) {
            throw new NotFoundException("No se encontró el producto!");
        }
        return product;
    }

    @Override
    public String updateProduct(Product product) {
        Product productUpdate = productRepository.getProductById(product.getId());
        if(productUpdate == null) {
            throw new NotFoundException("No se encontró el producto!");
        }
        productUpdate.setName(product.getName());
        productUpdate.setPrice(product.getPrice());
        productUpdate.setImage(product.getImage());
        productUpdate.setStock(product.getStock());
        productUpdate.setCategory(product.getCategory());
        productRepository.updateProduct(productUpdate);
        return product.getName();
    }
}
