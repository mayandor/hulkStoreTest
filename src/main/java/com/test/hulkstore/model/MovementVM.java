package com.test.hulkstore.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovementVM {
    private Movement movement;
    private List<MovementDetail> movementDetailList;
}
