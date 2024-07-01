package com.test.hulkstore.controller;

import com.test.hulkstore.model.Paginator;
import com.test.hulkstore.model.Product;
import com.test.hulkstore.model.ProductList;
import com.test.hulkstore.service.ProductService;
import com.test.hulkstore.utils.Utils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Utils.PATH_PRODUCTS)
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<String> addProduct(@RequestBody @Valid Product product) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.addProduct(product));
    }


    @GetMapping("/")
    public ResponseEntity<ProductList> getProducts(
            @RequestParam(Utils.PAGE) int page,
            @RequestParam(Utils.PER_PAGE) int perPage) {
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
