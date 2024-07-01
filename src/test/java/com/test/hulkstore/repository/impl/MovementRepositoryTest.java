package com.test.hulkstore.repository.impl;

import com.test.hulkstore.model.Movement;
import com.test.hulkstore.model.Paginator;
import com.test.hulkstore.model.Users;
import com.test.hulkstore.repository.enums.MovementType;
import com.test.hulkstore.repository.mappers.MovementRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class MovementRepositoryTest {
    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Mock
    private MovementRowMapper movementRowMapper;

    @Mock
    private ResultSet rs;

    @InjectMocks
    private MovementRepositoryImpl movementRepository;

    private Movement movement;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        Users user = new Users(1L, "name", "lastname", "username", "role");
        movement= new Movement(null, new Date(), MovementType.INPUT, user);
    }

    @Test
    void getMovementByIdTest() {
        Long id = 1L;
        String sql = "select m.id, m.movement_date, m.type, u.id, u.name, u.lastname, u.username, u.role " +
                "from movement m " +
                "join users u on m.user_id = u.id " +
                "where m.id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        when(namedParameterJdbcTemplate.queryForObject(sql, params, new MovementRowMapper()))
                .thenReturn(movement);
        Movement result = movementRepository.getMovementById(1L);
        assertNull(result);
    }

    @Test
    void getMovementsTest() throws SQLException {
        String sql= "select m.id, m.movement_date, m.type, u.id, u.name, u.lastname, u.username, u.role " +
                "from movement m " +
                "join users u on m.user_id = u.id " +
                "LIMIT :limit OFFSET :offset";
        Paginator paginator = new Paginator();
        paginator.setPage(0);
        paginator.setTotalRegisters(0);
        paginator.setTotalPages(1);
        paginator.setPerPage(10);
        List<Movement> movementList = new ArrayList<>();
        MapSqlParameterSource params= new MapSqlParameterSource();
        params.addValue("limit", paginator.getPerPage());
        params.addValue("offset", paginator.getPage());
        when(movementRowMapper.mapRow(rs, 0)).thenReturn(new Movement());
        when(namedParameterJdbcTemplate.query(sql, params, movementRowMapper)).thenReturn(movementList);
        List<Movement> result = movementRepository.getMovements(paginator);
        assertEquals(0, result.size());
    }
}
