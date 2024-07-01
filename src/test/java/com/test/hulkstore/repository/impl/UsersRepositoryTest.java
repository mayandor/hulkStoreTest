package com.test.hulkstore.repository.impl;

import com.test.hulkstore.model.Users;
import com.test.hulkstore.repository.mappers.UserRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsersRepositoryTest {
    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @InjectMocks
    private UsersRepositoryImpl usersRepository;

    @Value("${user.addUsers}")
    private String addUsersQuery;

    @Value("${user.getUserById}")
    private String getUserByIdQuery;
    private Users user;

    @BeforeEach
    public void setup() {
        user = new Users();
        user.setName("John");
        user.setLastname("Doe");
        user.setUsername("johndoe");
        user.setRole("user");
        ReflectionTestUtils.setField(usersRepository, "addUsersQuery", "INSERT INTO users (name, lastname, username, role) VALUES (:name, :lastname, :username, :role)");
        ReflectionTestUtils.setField(usersRepository, "getUserByIdQuery", "SELECT * FROM users WHERE id=:id");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addUsersTest() {
        when(namedParameterJdbcTemplate.update(any(String.class), any(MapSqlParameterSource.class))).thenReturn(1);
        usersRepository.addUsers(user);
        verify(namedParameterJdbcTemplate).update(any(String.class), any(MapSqlParameterSource.class));
    }

    @Test
    void getUserByIdTest() {
        when(namedParameterJdbcTemplate.queryForObject(any(String.class), any(MapSqlParameterSource.class), any(UserRowMapper.class))).thenReturn(user);
        Users result= usersRepository.getUserById(1L);
        assertEquals(user, result);
    }

}
