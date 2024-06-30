package com.test.hulkStore.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    private Long id;
    private String name;
    private Float price;
    private String image;
    private Integer stock;
    private Category category;
}
