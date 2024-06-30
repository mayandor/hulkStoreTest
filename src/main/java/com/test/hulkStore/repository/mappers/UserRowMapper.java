package com.test.hulkStore.repository.mappers;

import com.test.hulkStore.model.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<Users> {

    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Users(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("lastname"),
                rs.getString("username"),
                rs.getString("role")
        );
    }
}
