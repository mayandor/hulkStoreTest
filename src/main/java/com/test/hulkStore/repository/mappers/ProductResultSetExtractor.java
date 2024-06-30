package com.test.hulkStore.repository.mappers;

import com.test.hulkStore.model.Category;
import com.test.hulkStore.model.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductResultSetExtractor implements ResultSetExtractor<List<Product>> {

    @Override
    public List<Product> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Product> products = new ArrayList<>();
        while(rs.next()) {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setName(rs.getString("name"));
            product.setStock(rs.getInt("stock"));
            product.setPrice(rs.getFloat("price"));
            product.getCategory().setId(rs.getLong("id"));
            product.getCategory().setName(rs.getString("name"));
            products.add(product);
        }
        return products;
    }
}
