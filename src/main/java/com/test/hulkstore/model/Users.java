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
public class Users {

    private Long id;
    @NotEmpty(message = "{user.name.required}")
    @Size(min = 3, max = 30, message = "{user.name.size}")
    @Pattern(regexp = Utils.NAME_REGEX, message = "{user.name.regex}")
    private String name;
    @Size(min = 3, max = 30, message = "{user.lastname.size}")
    @Pattern(regexp = Utils.NAME_REGEX, message = "{user.lastname.regex}")
    private String lastname;
    @NotEmpty(message = "{user.username.required}")
    @Size(min = 3, max = 30, message = "{user.username.size}")
    @Pattern(regexp = Utils.USERNAME_REGEX, message = "{user.lastname.regex}")
    private String username;
    private String role;
}
