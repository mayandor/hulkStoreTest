package com.test.hulkStore.repository.impl;

import com.test.hulkStore.model.MovementDetail;
import com.test.hulkStore.repository.MovementDetailRepository;
import com.test.hulkStore.repository.mappers.MovementDetailRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovementDetailRepositoryImpl implements MovementDetailRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public void addMovementDetail(MovementDetail movementDetail) {
        String sql = "INSERT INTO movement_detail(quantity, product_id, movement_id) values(:quantity, :productId, :movementId)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("quantity", movementDetail.getQuantity())
                .addValue("productId", movementDetail.getProduct().getId())
                .addValue("movementId", movementDetail.getMovement().getId());
        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public List<MovementDetail> getMovementDetailByMovementId(Long id) {
        String sql = "select md.id, md.quantity, p.id, p.\"name\", p.price, p.image, p.stock, " +
                " c.id as category_id, c.\"name\" as category_name " +
                "from movement_detail md " +
                "join movement m on md.movement_id = m.id " +
                "join product p on md.product_id = p.id " +
                "join category c on p.category_id = c.id " +
                "where m.id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        return namedParameterJdbcTemplate.query(sql, params, new MovementDetailRowMapper());
    }


}
