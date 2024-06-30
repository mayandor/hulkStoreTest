package com.test.hulkStore.model;

import lombok.*;
import org.springframework.data.relational.core.sql.In;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovementDetail {
    private Long id;
    private Integer quantity;
    private Product product;
    private Movement movement;

    public MovementDetail(Long id, Integer quantity, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    }
}
