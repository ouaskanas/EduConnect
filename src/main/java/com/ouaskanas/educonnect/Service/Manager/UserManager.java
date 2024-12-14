package com.ouaskanas.educonnect.Service.Manager;

import com.ouaskanas.educonnect.Dao.Entities.User;
import com.ouaskanas.educonnect.Dto.UserDto;

import java.util.List;

public interface UserManager {

    public User createUser(UserDto userDto);
    public User updateUser(UserDto userDto, long userId);
    public User getUserById(long id);
    public List<User> getAllStudents();
    public List<User> getAllTeachers();
    public Boolean deleteUser(long id);
}
