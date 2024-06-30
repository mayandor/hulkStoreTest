package com.test.hulkStore.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Users {
    private Long id;
    private String name;
    private String lastname;
    private String username;
    private String role;
}
