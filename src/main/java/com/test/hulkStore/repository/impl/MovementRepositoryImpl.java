package com.test.hulkStore.repository.impl;

import com.test.hulkStore.model.Movement;
import com.test.hulkStore.model.Paginator;
import com.test.hulkStore.repository.MovementRepository;
import com.test.hulkStore.repository.mappers.MovementRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public Long addMovement(Movement movement) {
        String sql=  "INSERT INTO movement(movement_date, type, user_id) values(:movementDate, :type, :userId)";
        MapSqlParameterSource params= new MapSqlParameterSource()
                .addValue("movementDate", movement.getMovementDate())
                .addValue("type", movement.getType().name())
                .addValue("userId", movement.getUser().getId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[] {"id"});
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Movement getMovementById(Long id) {
        String sql = "select m.id, m.movement_date, m.type, u.id, u.name, u.lastname, u.username, u.role " +
        "from movement m " +
        "join users u on m.user_id = u.id " +
        "where m.id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, params, new MovementRowMapper());
    }

    @Override
    public List<Movement> getMovements(Paginator paginator) {
        String sql= "select m.id, m.movement_date, m.type, u.id, u.name, u.lastname, u.username, u.role " +
                "from movement m " +
                "join users u on m.user_id = u.id " +
                "LIMIT :limit OFFSET :offset";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("limit", paginator.getPerPage())
                .addValue("offset", paginator.getPage());
        return namedParameterJdbcTemplate.query(sql, params, new MovementRowMapper());
    }

    @Override
    public int countMovements() {
        String sql= "SELECT COUNT(*) FROM movement";
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, int.class);
    }
}
