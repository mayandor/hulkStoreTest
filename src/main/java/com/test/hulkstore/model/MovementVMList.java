package com.test.hulkstore.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MovementVMList {
    private List<MovementVM> movementVmList = new ArrayList<>();

    @JsonProperty(value = "pagination")
    private Paginator pagination;
}
