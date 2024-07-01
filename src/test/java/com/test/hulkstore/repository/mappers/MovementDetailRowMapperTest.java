package com.test.hulkstore.repository.mappers;

import com.test.hulkstore.model.Category;
import com.test.hulkstore.model.MovementDetail;
import com.test.hulkstore.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MovementDetailRowMapperTest {
    @InjectMocks
    private MovementDetailRowMapper mockMovementDetailRowMapper;

    @Mock
    private ProductRowMapper mockProductRowMapper;

    @Mock
    private ResultSet resultSet;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMapRow() throws SQLException {
        when(resultSet.getLong("id")).thenReturn(1L);
        when(resultSet.getInt("quantity")).thenReturn(10);
        Product mockProduct = new Product(1L, null, 0F, null, 0, null);
        mockProduct.setCategory(new Category(0L, null));
        when(mockProductRowMapper.mapRow(resultSet, 0)).thenReturn(mockProduct);
        MovementDetail movementDetail = mockMovementDetailRowMapper.mapRow(resultSet, 0);

        assertEquals(1L, movementDetail.getId());
        assertEquals(10, movementDetail.getQuantity());
        assertEquals(mockProduct.getId(), movementDetail.getProduct().getId());
    }
}
