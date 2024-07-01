package com.test.hulkstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.hulkstore.model.Category;
import com.test.hulkstore.model.Paginator;
import com.test.hulkstore.model.Product;
import com.test.hulkstore.model.ProductList;
import com.test.hulkstore.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private ObjectMapper objectMapper;

    private Product product;

    @BeforeEach
    public void setup() {
        Category category = new Category(2L, "category");
        product = new Product(1L, "camiseta", 10f, "image", 0, category);
        objectMapper = new ObjectMapper();
    }

    @Test
    void addProductTest() throws Exception {
        when(productService.addProduct(product)).thenReturn(product.getName());

        mockMvc.perform(post("/api/products/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated());
    }

    @Test
    void getProductsTest() throws Exception {
        when(productService.getProducts(new Paginator())).thenReturn(new ProductList());
        mockMvc.perform(get("/api/products/")
                .param("page", "1")
                .param("per_page", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getProductByIdTest() throws Exception {
        when(productService.getProductById(anyLong())).thenReturn(product);
        mockMvc.perform(get("/api/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("camiseta"))
                .andExpect(jsonPath("$.price").value(10F))
                .andExpect(jsonPath("$.image").value("image"))
                .andExpect(jsonPath("$.stock").value(0))
                .andExpect(jsonPath("$.category.id").value(2L))
                .andExpect(jsonPath("$.category.name").value("category"));
    }

    @Test
    void updateProductTest() throws Exception {
        when(productService.updateProduct(new Product())).thenReturn(product.getName());
        mockMvc.perform(put("/api/products/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk());
    }
}
