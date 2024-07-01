package com.test.hulkstore.repository.mappers;

import com.test.hulkstore.model.Movement;
import com.test.hulkstore.model.Users;
import com.test.hulkstore.repository.enums.MovementType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class MovementRowMapperTest {
    @Mock
    private UserRowMapper mockUserRowMapper;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private MovementRowMapper mockMovementRowMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMapRow() throws SQLException {
        when(resultSet.getLong("id")).thenReturn(1L);
        LocalDate localDate = LocalDate.now();
        Date sqlDate = Date.valueOf(localDate);
        when(resultSet.getDate("movement_date")).thenReturn(sqlDate);
        when(resultSet.getString("type")).thenReturn(MovementType.INPUT.name());
        Users user= new Users();
        when(mockUserRowMapper.mapRow(resultSet, 0)).thenReturn(user);
        Movement movement =  mockMovementRowMapper.mapRow(resultSet, 0);
        assertEquals(1L, movement.getId());
        assertEquals(sqlDate, movement.getMovementDate());
    }
}
