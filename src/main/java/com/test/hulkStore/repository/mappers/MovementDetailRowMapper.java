package com.test.hulkStore.repository.mappers;

import com.test.hulkStore.model.*;
import com.test.hulkStore.repository.enums.MovementType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovementDetailRowMapper implements RowMapper<MovementDetail> {

    @Override
    public MovementDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Category category = new Category(
//                rs.getLong("category_id"),
//                rs.getString("category_name")
//        );
//        Product product = new Product(
//                rs.getLong("id"),
//                rs.getString("name"),
//                rs.getFloat("price"),
//                rs.getString("image"),
//                rs.getInt("stock"),
//                category
//        );

//        Users user = new Users(
//                rs.getLong("id"),
//                rs.getString("name"),
//                rs.getString("lastname"),
//                rs.getString("username"),
//                rs.getString("role")
//        );
//
//        String typeString = rs.getString("type");
//        Movement movement =  new Movement(
//                rs.getLong("id"),
//                rs.getDate("movementDate"),
//                MovementType.valueOf(typeString),
//                new UserRowMapper().mapRow(rs, rowNum)
//        );

        return new MovementDetail(
                rs.getLong("id"),
                rs.getInt("quantity"),
                new ProductRowMapper().mapRow(rs, rowNum)
//                new MovementRowMapper().mapRow(rs, rowNum)
        );
    }
}
