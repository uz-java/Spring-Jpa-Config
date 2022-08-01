package org.example.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserCreateDto {
    private String email;
    private String password;
    private String confirmPassword;
}
