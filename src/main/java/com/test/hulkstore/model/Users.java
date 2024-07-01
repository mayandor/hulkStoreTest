package com.test.hulkstore.model;

import lombok.*;

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
