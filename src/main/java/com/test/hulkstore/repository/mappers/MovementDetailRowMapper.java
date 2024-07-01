package com.test.hulkstore.repository.mappers;

import com.test.hulkstore.model.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovementDetailRowMapper implements RowMapper<MovementDetail> {

    @Override
    public MovementDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new MovementDetail(
                rs.getLong("id"),
                rs.getInt("quantity"),
                new ProductRowMapper().mapRow(rs, rowNum)
        );
    }
}
