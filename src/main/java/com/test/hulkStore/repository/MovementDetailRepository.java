package com.test.hulkStore.repository;

import com.test.hulkStore.model.MovementDetail;

import java.util.List;

public interface MovementDetailRepository {
    public void addMovementDetail(MovementDetail movementDetail);

    public List<MovementDetail> getMovementDetailByMovementId(Long id);
}
