package com.test.hulkstore.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalExceptionHandlerTest {
    @InjectMocks
    private GlobalExceptionHandler handler;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleCustomExceptionTest() {
        NotFoundException ex = new NotFoundException("Error");
        ErrorResponse errorResponse = new ErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage());
        ResponseEntity<ErrorResponse> responseEntity = handler.handleCustomException(ex);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals((responseEntity.getBody()).getCode(), String.valueOf(HttpStatus.NOT_FOUND.value()));
        assertEquals(responseEntity.getBody().getDescription(), errorResponse.getDescription());
    }
}
