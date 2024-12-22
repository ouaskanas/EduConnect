package com.ouaskanas.educonnect.Service.Manager;

import com.ouaskanas.educonnect.Dao.Entities.User;
import com.ouaskanas.educonnect.Dto.UserDto;

import java.util.List;

public interface UserManager {

    public User createUser(UserDto userDto);
    public User updateUser(UserDto userDto, int userId);
    public User getUserById(int id);
    public List<User> getAllStudents();
    public List<User> getAllTeachers();
    public Boolean deleteUser(int id);
}
