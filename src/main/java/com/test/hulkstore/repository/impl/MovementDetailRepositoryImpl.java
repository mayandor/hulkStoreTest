package com.test.hulkstore.repository.impl;

import com.test.hulkstore.model.MovementDetail;
import com.test.hulkstore.repository.MovementDetailRepository;
import com.test.hulkstore.repository.mappers.MovementDetailRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovementDetailRepositoryImpl implements MovementDetailRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${movementDetail.addMovementDetail}")
    private String addMovementDetailQuery;

    @Value("${movementDetail.getMovementDetailByMovementId}")
    private String getMovementDetailByMovementIdQuery;

    @Override
    public void addMovementDetail(MovementDetail movementDetail) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("quantity", movementDetail.getQuantity())
                .addValue("productId", movementDetail.getProduct().getId())
                .addValue("movementId", movementDetail.getMovement().getId());
        namedParameterJdbcTemplate.update(addMovementDetailQuery, params);
    }

    @Override
    public List<MovementDetail> getMovementDetailByMovementId(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        return namedParameterJdbcTemplate.query(getMovementDetailByMovementIdQuery, params, new MovementDetailRowMapper());
    }


}
