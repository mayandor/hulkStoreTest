package com.test.hulkStore.repository.impl;

import com.test.hulkStore.model.Paginator;
import com.test.hulkStore.model.Product;
import com.test.hulkStore.repository.ProductRepository;
import com.test.hulkStore.repository.mappers.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public Long addProduct(Product product) {
        String sql = "INSERT INTO product(name, price, image, stock, category_id) values(:name, :price, :image, :stock, :category_id)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", product.getName())
                .addValue("price", product.getPrice())
                .addValue("image", product.getImage())
                .addValue("stock", product.getStock())
                .addValue("category_id", product.getCategory().getId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[] {"id"});
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public List<Product> getProducts(Paginator paginator) {
        String sql= "SELECT p.id, p.name, p.price, p.image, p.stock, " +
                "c.id AS category_id, c.name AS category_name " +
                "FROM product p " +
                "JOIN category c ON p.category_id = c.id " +
                "LIMIT :limit OFFSET :offset";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("limit", paginator.getPerPage())
                .addValue("offset", paginator.getPage());
        return namedParameterJdbcTemplate.query(sql, params, new ProductRowMapper());
    }

    @Override
    public int countProducts() {
        String sql= "SELECT COUNT(*) FROM product";
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, int.class);
    }

    @Override
    public Product getProductById(Long id) {
        String sql = "SELECT p.id, p.name, p.price, p.image, p.stock, " +
                "c.id AS category_id, c.name AS category_name " +
                "FROM product p " +
                "JOIN category c ON p.category_id = c.id " +
                "WHERE p.id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, params, new ProductRowMapper());
    }

    @Override
    public int updateProduct(Product product) {
        String sql = "UPDATE product SET name= :name, price= :price, image= :image, stock= :stock, category_id= :categoryId WHERE id= :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", product.getId())
                .addValue("name", product.getName())
                .addValue("price", product.getPrice())
                .addValue("image", product.getImage())
                .addValue("stock", product.getStock())
                .addValue("categoryId", product.getCategory().getId());
        return namedParameterJdbcTemplate.update(sql, params);
    }
}
