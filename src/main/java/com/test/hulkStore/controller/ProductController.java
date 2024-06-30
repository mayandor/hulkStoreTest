package com.test.hulkStore.controller;

import com.test.hulkStore.model.Paginator;
import com.test.hulkStore.model.Product;
import com.test.hulkStore.model.ProductList;
import com.test.hulkStore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.addProduct(product));
    }


    @GetMapping("/")
    public ResponseEntity<ProductList> getProducts(
            @RequestParam("page") int page,
            @RequestParam("per_page") int perPage) {
        return ResponseEntity.ok(productService.getProducts(new Paginator(page, perPage)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/")
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(product));
    }
}
