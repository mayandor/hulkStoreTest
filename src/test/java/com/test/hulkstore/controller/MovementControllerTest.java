package com.test.hulkstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.hulkstore.model.*;
import com.test.hulkstore.service.MovementService;
import com.test.hulkstore.service.component.MovementServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(MovementController.class)
class MovementControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovementService movementService;

    @MockBean
    private MovementServiceFactory movementServiceFactory;

    private ObjectMapper objectMapper;

    private MovementVM movementVm;

    private Paginator paginator;

    private List<Movement> movementList;

    private MovementVMList movementVMList;

    @BeforeEach
    public void setup() {
        movementVm = new MovementVM();
        movementVm.setMovement(new Movement());
        movementVm.setMovementDetailList(new ArrayList<>());
        movementVMList = new MovementVMList();
        objectMapper = new ObjectMapper();
    }


    @Test
    void getMovementByIdTest() throws Exception {
        when(movementService.getMovementById(anyLong())).thenReturn(movementVm);
        mockMvc.perform(get("/api/movement/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getMovementsTest() throws Exception  {
        when(movementService.getMovements(new Paginator())).thenReturn(movementVMList);
        mockMvc.perform(get("/api/movement/")
                        .param("page", "1")
                        .param("per_page", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
