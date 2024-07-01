package com.test.hulkstore.repository.impl;

import com.test.hulkstore.model.Category;
import com.test.hulkstore.model.Paginator;
import com.test.hulkstore.model.Product;
import com.test.hulkstore.repository.mappers.ProductRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductRepositoryTest {
    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Mock
    private ProductRowMapper productRowMapper;

    @Mock
    private ResultSet rs;

    @InjectMocks
    private ProductRepositoryImpl productRepository;

    private Product product;

    @BeforeEach
    public void setup() {
        product = new Product(1L, "name", 12f, "image", 0, new Category(2L, "category"));
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getProducts() throws SQLException {
        String sql= "SELECT p.id, p.name, p.price, p.image, p.stock, " +
                "c.id AS category_id, c.name AS category_name " +
                "FROM product p " +
                "JOIN category c ON p.category_id = c.id " +
                "LIMIT :limit OFFSET :offset";
        Paginator paginator = new Paginator();
        paginator.setPage(0);
        paginator.setTotalRegisters(0);
        paginator.setTotalPages(1);
        paginator.setPerPage(10);
        List<Product> products = new ArrayList<>();
        MapSqlParameterSource params= new MapSqlParameterSource();
        params.addValue("limit", paginator.getPerPage());
        params.addValue("offset", paginator.getPage());
        when(productRowMapper.mapRow(rs, 0)).thenReturn(new Product());
        when(namedParameterJdbcTemplate.query(sql, params, productRowMapper)).thenReturn(products);

        List<Product> result = productRepository.getProducts(paginator);
        assertEquals(0, result.size());
    }

//    @Test
//    public void addProductTest() {
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        keyHolder.getKeyList().add(Map.of("id", 1L));
//        when(namedParameterJdbcTemplate.update(anyString(), any(MapSqlParameterSource.class), any(KeyHolder.class), any(String[].class)))
//                .thenAnswer(invocation -> {
//                    MapSqlParameterSource params = new MapSqlParameterSource()
//                            .addValue("name", product.getName())
//                            .addValue("price", product.getPrice())
//                            .addValue("image", product.getImage())
//                            .addValue("stock", product.getStock())
//                            .addValue("category_id", product.getCategory().getId());
//                    ((GeneratedKeyHolder) invocation.getArgument(2)).getKeyList().add(Map.of("id", 1L));
//                    return 1;
//                });
//        Long id = productRepository.addProduct(product);
//        assertNull(id);
//    }

//    @Test
//    public void testCountProducts() throws SQLException {
//        when(rs.getInt(1)).thenReturn(100);
//        when(namedParameterJdbcTemplate.queryForObject(anyString(),
//                        any(MapSqlParameterSource.class), any(Class.class)))
//                .thenReturn(100);
//        int productCount = productRepository.countProducts();
//        assertEquals(100, productCount);
//    }

    @Test
    void getProductsByIdTest() {
        Long id = 1L;
        String sql = "SELECT p.id, p.name, p.price, p.image, p.stock, " +
                "c.id AS category_id, c.name AS category_name " +
                "FROM product p " +
                "JOIN category c ON p.category_id = c.id " +
                "WHERE p.id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        when(namedParameterJdbcTemplate.queryForObject(
                sql, params, new ProductRowMapper()))
                .thenReturn(product);
        Product result = productRepository.getProductById(1L);
        assertNull(result);
    }

    @Test
    void updateProductTest() {
        String sql = "UPDATE product SET name= :name, price= :price, image= :image, stock= :stock, category_id= :categoryId WHERE id= :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", product.getId())
                .addValue("name", product.getName())
                .addValue("price", product.getPrice())
                .addValue("image", product.getImage())
                .addValue("stock", product.getStock())
                .addValue("categoryId", product.getCategory().getId());
        when(namedParameterJdbcTemplate.update(sql, params)).thenReturn(0);
        int result = productRepository.updateProduct(product);
        assertEquals(0, result);
    }
}
