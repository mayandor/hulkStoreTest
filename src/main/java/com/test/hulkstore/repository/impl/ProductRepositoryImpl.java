package com.test.hulkstore.repository.impl;

import com.test.hulkstore.model.Paginator;
import com.test.hulkstore.model.Product;
import com.test.hulkstore.repository.ProductRepository;
import com.test.hulkstore.repository.mappers.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${product.addProduct}")
    private String addProductQuery;

    @Value("${product.getProducts}")
    private String getProductsQuery;

    @Value("${product.countProducts}")
    private String countProductsQuery;

    @Value("${product.getProductById}")
    private String getProductByIdQuery;

    @Value("${product.updateProduct}")
    private String updateProductQuery;

    @Override
    public Long addProduct(Product product) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", product.getName())
                .addValue("price", product.getPrice())
                .addValue("image", product.getImage())
                .addValue("stock", product.getStock())
                .addValue("category_id", product.getCategory().getId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(addProductQuery, params, keyHolder, new String[] {"id"});
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public List<Product> getProducts(Paginator paginator) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("limit", paginator.getPerPage())
                .addValue("offset", paginator.getPage());
        return namedParameterJdbcTemplate.query(getProductsQuery, params, new ProductRowMapper());
    }

    @Override
    public int countProducts() {
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.queryForObject(countProductsQuery, paramMap, int.class);
    }

    @Override
    public Product getProductById(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(getProductByIdQuery, params, new ProductRowMapper());
    }

    @Override
    public int updateProduct(Product product) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", product.getId())
                .addValue("name", product.getName())
                .addValue("price", product.getPrice())
                .addValue("image", product.getImage())
                .addValue("stock", product.getStock())
                .addValue("categoryId", product.getCategory().getId());
        return namedParameterJdbcTemplate.update(updateProductQuery, params);
    }
}
