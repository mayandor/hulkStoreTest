package com.test.hulkstore.repository.mappers;

import com.test.hulkstore.model.Category;
import com.test.hulkstore.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category(
                rs.getLong("category_id"),
                rs.getString("category_name")
        );
        return new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getFloat("price"),
                rs.getString("image"),
                rs.getInt("stock"),
                category
        );
    }
}
