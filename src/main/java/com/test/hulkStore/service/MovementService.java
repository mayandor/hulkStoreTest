package com.test.hulkStore.service;

import com.test.hulkStore.model.MovementVM;
import com.test.hulkStore.model.MovementVMList;
import com.test.hulkStore.model.Paginator;

public interface MovementService {
    public String addMovementVm(MovementVM movementVM);

    public MovementVM getMovementById(Long id);

    public MovementVMList getMovements(Paginator paginator);
}
