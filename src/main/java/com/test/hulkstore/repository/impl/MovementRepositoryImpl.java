package com.test.hulkstore.repository.impl;

import com.test.hulkstore.model.Movement;
import com.test.hulkstore.model.Paginator;
import com.test.hulkstore.repository.MovementRepository;
import com.test.hulkstore.repository.mappers.MovementRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class MovementRepositoryImpl implements MovementRepository {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${movement.addMovement}")
    private String addMovementQuery;

    @Value("${movement.getMovementById}")
    private String getMovementByIdQuery;

    @Value("${movement.getMovements}")
    private String getMovementsQuery;

    @Value("${movement.countMovements}")
    private String countMovementsQuery;


    @Override
    public Long addMovement(Movement movement) {
        MapSqlParameterSource params= new MapSqlParameterSource()
                .addValue("movementDate", movement.getMovementDate())
                .addValue("type", movement.getType().name())
                .addValue("userId", movement.getUser().getId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(addMovementQuery, params, keyHolder, new String[] {"id"});
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Movement getMovementById(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(getMovementByIdQuery, params, new MovementRowMapper());
    }

    @Override
    public List<Movement> getMovements(Paginator paginator) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("limit", paginator.getPerPage())
                .addValue("offset", paginator.getPage());
        return namedParameterJdbcTemplate.query(getMovementsQuery, params, new MovementRowMapper());
    }

    @Override
    public int countMovements() {
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.queryForObject(countMovementsQuery, paramMap, int.class);
    }
}
