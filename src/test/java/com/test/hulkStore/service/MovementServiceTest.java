package com.test.hulkStore.service;

import com.test.hulkStore.model.*;
import com.test.hulkStore.repository.MovementDetailRepository;
import com.test.hulkStore.repository.MovementRepository;
import com.test.hulkStore.repository.ProductRepository;
import com.test.hulkStore.repository.enums.MovementType;
import com.test.hulkStore.service.impl.MovementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovementServiceTest {

    @InjectMocks
    private MovementServiceImpl movementServiceImpl;

    @Mock
    private MovementDetailRepository movementDetailRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private MovementRepository movementRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addMovementVMTest() {
        Users user= new Users(1L, "name", "lastname", "username", "role");
        Movement movement = new Movement(2L, new Date(), MovementType.INPUT, user);
        Category category = new Category(4L, "category");
        Product product = new Product(3L, "product", 10F, "image", 0, category);
        MovementDetail movementDetail = new MovementDetail(5L, 12, product);
        List<MovementDetail> movementDetailList = new ArrayList<>();
        movementDetailList.add(movementDetail);
        MovementVM movementVM = new MovementVM(movement, movementDetailList);

    }

}
