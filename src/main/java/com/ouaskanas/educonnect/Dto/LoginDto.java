package com.ouaskanas.educonnect.Dto;

import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NonNull
    private String username;
    @NonNull
    private String password;
    private String role;
}
