package com.test.hulkstore.service;

import com.test.hulkstore.model.MovementVM;
import com.test.hulkstore.model.MovementVMList;
import com.test.hulkstore.model.Paginator;

public interface MovementService {

    MovementVM getMovementById(Long id);

    MovementVMList getMovements(Paginator paginator);
}
