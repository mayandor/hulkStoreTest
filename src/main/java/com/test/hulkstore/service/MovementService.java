package com.test.hulkstore.service;

import com.test.hulkstore.model.MovementVM;
import com.test.hulkstore.model.MovementVMList;
import com.test.hulkstore.model.Paginator;

public interface MovementService {
    public String addMovementVm(MovementVM movementVM);

    public MovementVM getMovementById(Long id);

    public MovementVMList getMovements(Paginator paginator);
}
