package com.ouaskanas.educonnect.Dto;

import com.ouaskanas.educonnect.Dao.Entities.Friendship;
import com.ouaskanas.educonnect.Dao.Entities.Role;
import lombok.*;

import java.util.List;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String firstname;
    private String lastname;
    private String email;
    private Role role;
    private int classroom_id;
}
