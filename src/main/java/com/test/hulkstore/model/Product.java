package com.test.hulkstore.model;

import com.test.hulkstore.utils.Utils;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    private Long id;
    @NotEmpty(message = "{product.name.required}")
    @Size(min = 2, max = 50, message = "{product.name.size}")
    @Pattern(regexp = Utils.NAME_REGEX, message = "{product.name.regex}")
    private String name;
    private Float price;
    private String image;
    private Integer stock;
    private Category category;
}
