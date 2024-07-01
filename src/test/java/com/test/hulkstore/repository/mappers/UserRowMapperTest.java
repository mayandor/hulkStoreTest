package com.test.hulkstore.repository.mappers;

import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

class UserRowMapperTest {

    @Test
    void testMapRow() throws SQLException {
        boolean failed = false;
        try {
            UserRowMapper map = new UserRowMapper();
            map.mapRow(mock(ResultSet.class), 1);
        }catch(Exception e) {
            failed = true;
        }
        assertFalse(failed);
    }
}
