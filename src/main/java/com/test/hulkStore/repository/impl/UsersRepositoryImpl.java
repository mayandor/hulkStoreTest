package com.test.hulkStore.repository.impl;

import com.test.hulkStore.model.Users;
import com.test.hulkStore.repository.UsersRepository;
import com.test.hulkStore.repository.mappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepositoryImpl implements UsersRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public void addUsers(Users users) {
        String sql = "INSERT INTO users(name, lastname, username, role) values(:name, :lastname, :username, :role)";
        MapSqlParameterSource params =  new MapSqlParameterSource()
                .addValue("name", users.getName())
                .addValue("lastname", users.getLastname())
                .addValue("username", users.getUsername())
                .addValue("role", users.getRole());
        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Users getUserById(Long id) {
        String sql = "SELECT * FROM users WHERE id=:id";
        MapSqlParameterSource params =  new MapSqlParameterSource()
                .addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, params, new UserRowMapper());
    }
}
