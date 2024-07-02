package com.test.hulkstore.service.component;

import com.test.hulkstore.repository.enums.MovementType;
import com.test.hulkstore.service.TypeMovementService;
import com.test.hulkstore.service.impl.InputMovementServiceImpl;
import com.test.hulkstore.service.impl.OutputMovementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MovementServiceFactory {

    @Autowired
    private OutputMovementServiceImpl outputMovementService;

    @Autowired
    private InputMovementServiceImpl inputMovementService;

    public TypeMovementService getService(MovementType movementType) {
        if (movementType.equals(MovementType.OUTPUT)) {
            return outputMovementService;
        } else {
            return inputMovementService;
        }
    }
}
