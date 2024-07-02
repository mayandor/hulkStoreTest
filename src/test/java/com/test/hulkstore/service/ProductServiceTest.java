package com.test.hulkstore.service;

import com.test.hulkstore.exceptions.NotFoundException;
import com.test.hulkstore.model.Category;
import com.test.hulkstore.model.Paginator;
import com.test.hulkstore.model.Product;
import com.test.hulkstore.model.ProductList;
import com.test.hulkstore.repository.ProductRepository;
import com.test.hulkstore.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class ProductServiceTest {
    @Mock
    private ProductRepository mockProductRepository;

    @InjectMocks
    private ProductServiceImpl mockProductService;

    private Product product;

    private Paginator paginator;

    private List<Product> products;

    @BeforeEach
    public void setup() {
        Category category = new Category();
        category.setId(4L);
        category.setName("category");

        product = new Product();
        product.setId(1L);
        product.setName("product");
        product.setStock(0);
        product.setPrice(10F);
        product.setImage("image");
        product.setCategory(category);

        paginator = new Paginator();
        paginator.setPage(0);
        paginator.setPerPage(10);
        paginator.setTotalPages(1);
        paginator.setTotalRegisters(1);

        products = new ArrayList<>();
        products.add(product);
        ProductList productList = new ProductList();
        productList.setProducts(products);
        productList.setPagination(paginator);

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addProductTest() {
        when(mockProductRepository.addProduct(product)).thenReturn(anyLong());
        String result = mockProductService.addProduct(product);
        assertEquals(result, product.getName());
    }

    @Test
    void getProductsTest() {
        when(mockProductRepository.getProducts(paginator)).thenReturn(products);
        when(mockProductRepository.countProducts()).thenReturn(1);

        ProductList result = mockProductService.getProducts(paginator);
        assertEquals(1, result.getProducts().size());
        assertEquals(1, paginator.getTotalRegisters());
        assertEquals(1, paginator.getTotalPages());
    }

    @Test
    void getProductsByIdTest() {
        when(mockProductRepository.getProductById(anyLong())).thenReturn(product);
        Product result = mockProductService.getProductById(1L);
        assertEquals(result, product);
    }

    @Test
    void getProductByIdNotFoundTest() {
        Long productId = 1L;
        when(mockProductRepository.getProductById(productId)).thenReturn(null);
        assertThrows(NotFoundException.class, () -> mockProductService.getProductById(productId));
    }

    @Test
    void updateProductTest() {
        when(mockProductRepository.getProductById(anyLong())).thenReturn(product);
        when(mockProductRepository.updateProduct(new Product())).thenReturn(1);
        String result = mockProductService.updateProduct(product);
        assertEquals(result, product.getName());
    }
}
