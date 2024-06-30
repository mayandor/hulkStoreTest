package com.test.hulkStore.repository.mappers;

import com.test.hulkStore.model.MovementDetail;
import com.test.hulkStore.repository.enums.MovementType;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovementDetailResultSetExtractor implements ResultSetExtractor<List<MovementDetail>> {

    @Override
    public List<MovementDetail> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<MovementDetail> movementDetailList = new ArrayList<>();
        while(rs.next()) {
            MovementDetail movementDetail = new MovementDetail();
            movementDetail.setId(rs.getLong("id"));
            movementDetail.setQuantity(rs.getInt("quantity"));
//            movementDetail.getMovement().setId(rs.getLong("id"));
//            movementDetail.getMovement().setMovementDate(rs.getDate("movement_id"));
//            String typeString = rs.getString("type");
//            movementDetail.getMovement().setType(MovementType.valueOf(typeString));
//            movementDetail.getMovement().getUser().setId(rs.getLong("id"));
//            movementDetail.getMovement().getUser().setName(rs.getString("name"));
//            movementDetail.getMovement().getUser().setLastname(rs.getString("lastname"));
//            movementDetail.getMovement().getUser().setUsername(rs.getString("username"));
//            movementDetail.getMovement().getUser().setRole(rs.getString("role"));
            movementDetail.getProduct().setId(rs.getLong("id"));
            movementDetail.getProduct().setName(rs.getString("name"));
            movementDetail.getProduct().setPrice(rs.getFloat("price"));
            movementDetail.getProduct().setImage(rs.getString("image"));
            movementDetail.getProduct().setStock(rs.getInt("stock"));
            movementDetail.getProduct().getCategory().setId(rs.getLong("id"));
            movementDetail.getProduct().getCategory().setName(rs.getString("name"));
            movementDetailList.add(movementDetail);
        }
        return movementDetailList;
    }
}
