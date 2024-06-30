package com.test.hulkStore.repository.mappers;


import com.test.hulkStore.model.Movement;
import com.test.hulkStore.model.Users;
import com.test.hulkStore.repository.enums.MovementType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovementRowMapper implements RowMapper<Movement> {
    @Override
    public Movement mapRow(ResultSet rs, int rowNum) throws SQLException {
        String typeString = rs.getString("type");
        return new Movement(
                rs.getLong("id"),
                rs.getDate("movement_date"),
                MovementType.valueOf(typeString),
                new UserRowMapper().mapRow(rs, rowNum)
        );
    }
}
