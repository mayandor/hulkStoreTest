package com.test.hulkStore.repository;

import com.test.hulkStore.model.Movement;
import com.test.hulkStore.model.Paginator;

import java.util.List;

public interface MovementRepository {
    public Long addMovement(Movement movement);

    public Movement getMovementById(Long id);

    public List<Movement> getMovements(Paginator paginator);

    public int countMovements();
}
