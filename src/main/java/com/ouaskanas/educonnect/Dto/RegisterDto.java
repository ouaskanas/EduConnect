package com.ouaskanas.educonnect.Dto;

import com.ouaskanas.educonnect.Dao.Entities.Role;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterDto {
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String confirmPassword;
    private Role role;
}
