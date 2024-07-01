package com.test.hulkstore.model;

import com.test.hulkstore.repository.enums.MovementType;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movement {
    private Long id;
    private Date movementDate;
    private MovementType type;
    private Users user;
}
