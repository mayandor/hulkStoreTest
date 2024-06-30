package com.test.hulkStore.model;

import com.test.hulkStore.repository.enums.MovementType;
import lombok.*;

import java.util.Date;

@Data
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
