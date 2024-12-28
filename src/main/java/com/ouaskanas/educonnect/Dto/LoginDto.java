package com.ouaskanas.educonnect.Dto;

import com.ouaskanas.educonnect.Dao.Entities.Role;
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
    private String role= Role.STUDENT.name();
}
