package com.test.hulkstore.repository;

import com.test.hulkstore.model.Movement;
import com.test.hulkstore.model.Paginator;

import java.util.List;

public interface MovementRepository {
    public Long addMovement(Movement movement);

    public Movement getMovementById(Long id);

    public List<Movement> getMovements(Paginator paginator);

    public int countMovements();
}
