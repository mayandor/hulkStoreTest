package com.test.hulkstore.service;

import com.test.hulkstore.model.*;
import com.test.hulkstore.repository.MovementDetailRepository;
import com.test.hulkstore.repository.MovementRepository;
import com.test.hulkstore.repository.ProductRepository;
import com.test.hulkstore.repository.enums.MovementType;
import com.test.hulkstore.service.impl.MovementServiceImpl;
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

class MovementServiceTest {

    @Mock
    private MovementDetailRepository mockMovementDetailRepository;

    @Mock
    private ProductRepository mockProductRepository;

    @Mock
    private MovementRepository mockMovementRepository;

    @InjectMocks
    private MovementServiceImpl mockMovementServiceImpl;

    private MovementVM movementVm;
    private Movement movement;
    private Product product;

    private List<MovementDetail> movementDetailList;

    private Paginator paginator;

    private List<Movement> movementList;

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
        movement.setType(MovementType.INPUT);
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

        movementDetailList = new ArrayList<>();
        movementDetailList.add(movementDetail);

        movementVm = new MovementVM();
        movementVm.setMovement(movement);
        movementVm.setMovementDetailList(movementDetailList);

        paginator = new Paginator();
        paginator.setPage(0);
        paginator.setPerPage(10);
        paginator.setTotalPages(1);
        paginator.setTotalRegisters(1);

        movementList = new ArrayList<>();
        movementList.add(movement);

        List<MovementVM> movementVms = new ArrayList<>();
        movementVms.add(movementVm);

        MovementVMList movementVMList = new MovementVMList();
        movementVMList.setMovementVmList(movementVms);
        movementVMList.setPagination(paginator);
    }

    @Test
    void addMovementVMInputTest() {
        when(mockMovementRepository.addMovement(any(Movement.class))).thenReturn(1L);
        when(mockProductRepository.getProductById(anyLong())).thenReturn(product);

        String result = mockMovementServiceImpl.addMovementVm(movementVm);

        verify(mockMovementRepository, times(1)).addMovement(movement);
        verify(mockProductRepository, times(1)).getProductById(product.getId());
        verify(mockProductRepository, times(1)).updateProduct(any(Product.class));
        verify(mockMovementDetailRepository, times(1)).addMovementDetail(any(MovementDetail.class));

        assertEquals("Successful", result);
    }

    @Test
    void addMovementVMOutputTest() {
        movementVm.getMovement().setType(MovementType.OUTPUT);
        product.setStock(30);
        when(mockMovementRepository.addMovement(any(Movement.class))).thenReturn(1L);
        when(mockProductRepository.getProductById(anyLong())).thenReturn(product);

        String result = mockMovementServiceImpl.addMovementVm(movementVm);

        verify(mockMovementRepository, times(1)).addMovement(movement);
        verify(mockProductRepository, times(2)).getProductById(product.getId());
        verify(mockProductRepository, times(1)).updateProduct(any(Product.class));
        verify(mockMovementDetailRepository, times(1)).addMovementDetail(any(MovementDetail.class));

        assertEquals("Successful", result);
    }

    @Test
    void getMovementsTest() {
        when(mockMovementRepository.getMovements(paginator)).thenReturn(movementList);
        when(mockMovementRepository.countMovements()).thenReturn(1);
        when(mockMovementDetailRepository.getMovementDetailByMovementId(anyLong())).thenReturn(new ArrayList<>());

        MovementVMList result = mockMovementServiceImpl.getMovements(paginator);

        verify(mockMovementRepository, times(1)).getMovements(paginator);
        verify(mockMovementRepository, times(1)).countMovements();
        verify(mockMovementDetailRepository, times(1)).getMovementDetailByMovementId(anyLong());

        assertEquals(1, result.getMovementVmList().size());
        assertEquals(1, paginator.getTotalRegisters());
        assertEquals(1, paginator.getTotalPages());
    }

    @Test
    void getMovementByIdTest() {
        when(mockMovementRepository.getMovementById(anyLong())).thenReturn(movement);
        when(mockMovementDetailRepository.getMovementDetailByMovementId(anyLong())).thenReturn(movementDetailList);

        MovementVM result =  mockMovementServiceImpl.getMovementById(2L);

        verify(mockMovementRepository, times(1)).getMovementById(2L);
        verify(mockMovementDetailRepository, times(1)).getMovementDetailByMovementId(2L);
        assertEquals(movementVm.getMovement().getId(), result.getMovement().getId());
    }
}
