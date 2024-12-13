package com.ouaskanas.educonnect.Dto;

import com.ouaskanas.educonnect.Dao.Entities.Friendship;

import java.util.List;

public class UserDto {
    private long user_id;
    private String username;
    private String password;
    private String role;
    private long classroom_id;
    private List<Long> friendship_id;
    private List<Long> posts;
}
