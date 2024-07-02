package com.test.hulkstore.service;

import com.test.hulkstore.model.*;
import com.test.hulkstore.repository.MovementDetailRepository;
import com.test.hulkstore.repository.MovementRepository;
import com.test.hulkstore.repository.ProductRepository;
import com.test.hulkstore.repository.enums.MovementType;
import com.test.hulkstore.service.impl.OutputMovementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class OutputMovementServiceTest {
    @Mock
    private MovementDetailRepository mockMovementDetailRepository;

    @Mock
    private ProductRepository mockProductRepository;

    @Mock
    private MovementRepository mockMovementRepository;

    @InjectMocks
    private OutputMovementServiceImpl mockOutputMovementService;

    private MovementVM movementVm;
    private Movement movement;
    private Product product;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        Users user = new Users();
        user.setId(1L);
        user.setName("name");
        user.setLastname("lastname");
        user.setUsername("username");
        user.setRole("role");

        movement = new Movement();
        movement.setId(2L);
        movement.setMovementDate(new Date());
        movement.setType(MovementType.OUTPUT);
        movement.setUser(user);

        Category category = new Category();
        category.setId(4L);
        category.setName("category");

        product = new Product();
        product.setId(3L);
        product.setName("product");
        product.setPrice(10F);
        product.setImage("image");
        product.setStock(0);
        product.setCategory(category);

        MovementDetail movementDetail = new MovementDetail();
        movementDetail.setId(5L);
        movementDetail.setQuantity(12);
        movementDetail.setProduct(product);
        movementDetail.setMovement(movement);

        List<MovementDetail> movementDetailList = new ArrayList<>();
        movementDetailList.add(movementDetail);

        movementVm = new MovementVM();
        movementVm.setMovement(movement);
        movementVm.setMovementDetailList(movementDetailList);

        Paginator paginator = new Paginator();
        paginator.setPage(0);
        paginator.setPerPage(10);
        paginator.setTotalPages(1);
        paginator.setTotalRegisters(1);

        List<MovementVM> movementVms = new ArrayList<>();
        movementVms.add(movementVm);

        MovementVMList movementVMList = new MovementVMList();
        movementVMList.setMovementVmList(movementVms);
        movementVMList.setPagination(paginator);
    }

    @Test
    void addMovementTest() {
        movementVm.getMovement().setType(MovementType.OUTPUT);
        product.setStock(30);
        when(mockMovementRepository.addMovement(any(Movement.class))).thenReturn(1L);
        when(mockProductRepository.getProductById(anyLong())).thenReturn(product);

        String result = mockOutputMovementService.addMovement(movementVm);

        verify(mockMovementRepository, times(1)).addMovement(movement);
        verify(mockProductRepository, times(2)).getProductById(product.getId());
        verify(mockProductRepository, times(1)).updateProduct(any(Product.class));
        verify(mockMovementDetailRepository, times(1)).addMovementDetail(any(MovementDetail.class));

        assertEquals("Successful", result);
    }
}
