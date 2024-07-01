package com.test.hulkstore.repository;

import com.test.hulkstore.model.MovementDetail;

import java.util.List;

public interface MovementDetailRepository {
    public void addMovementDetail(MovementDetail movementDetail);

    public List<MovementDetail> getMovementDetailByMovementId(Long id);
}
