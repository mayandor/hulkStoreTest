package com.test.hulkstore.repository.impl;

import com.test.hulkstore.model.Users;
import com.test.hulkstore.repository.UsersRepository;
import com.test.hulkstore.repository.mappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepositoryImpl implements UsersRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${user.addUsers}")
    private String addUsersQuery;

    @Value("${user.getUserById}")
    private String getUserByIdQuery;

    @Override
    public void addUsers(Users users) {
        MapSqlParameterSource params =  new MapSqlParameterSource()
                .addValue("name", users.getName())
                .addValue("lastname", users.getLastname())
                .addValue("username", users.getUsername())
                .addValue("role", users.getRole());
        namedParameterJdbcTemplate.update(addUsersQuery, params);
    }

    @Override
    public Users getUserById(Long id) {
        MapSqlParameterSource params =  new MapSqlParameterSource()
                .addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(getUserByIdQuery, params, new UserRowMapper());
    }
}
