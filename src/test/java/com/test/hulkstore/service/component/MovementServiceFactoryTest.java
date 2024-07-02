package com.test.hulkstore.service.component;

import com.test.hulkstore.repository.enums.MovementType;
import com.test.hulkstore.service.TypeMovementService;
import com.test.hulkstore.service.impl.InputMovementServiceImpl;
import com.test.hulkstore.service.impl.OutputMovementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MovementServiceFactoryTest {

    @Mock
    private OutputMovementServiceImpl outputMovementService;

    @Mock
    private InputMovementServiceImpl inputMovementService;

    @InjectMocks
    private MovementServiceFactory movementServiceFactory;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetServiceOutput() {
        MovementType movementType = MovementType.OUTPUT;
        TypeMovementService service = movementServiceFactory.getService(movementType);
        assertTrue(service instanceof OutputMovementServiceImpl);
    }

    @Test
    void testGetServiceInput() {
        MovementType movementType = MovementType.INPUT;
        TypeMovementService service = movementServiceFactory.getService(movementType);
        assertTrue(service instanceof InputMovementServiceImpl);
    }
}
