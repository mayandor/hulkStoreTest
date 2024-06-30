package com.test.hulkStore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
public class MovementVM {
    private Movement movement;
    private List<MovementDetail> movementDetailList;
}
