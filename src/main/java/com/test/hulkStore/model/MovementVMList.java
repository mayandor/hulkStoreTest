package com.test.hulkStore.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MovementVMList {
    private List<MovementVM> movementVMList = new ArrayList<>();

    @JsonProperty(value = "pagination")
    private Paginator pagination;
}
