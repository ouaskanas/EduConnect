package com.ouaskanas.educonnect.Service.Service;

import com.ouaskanas.educonnect.Dao.Entities.Role;
import com.ouaskanas.educonnect.Dao.Entities.User;
import com.ouaskanas.educonnect.Dao.Repositories.UserRepository;
import com.ouaskanas.educonnect.Dto.UserDto;
import com.ouaskanas.educonnect.Mappers.UserMapper;
import com.ouaskanas.educonnect.Service.Manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;


    @Override
    public User createUser(UserDto userDto) {
        User user = userMapper.mapUserDtoToUser(userDto);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserDto userDto, int user_id) {
        if(!userRepository.existsById(user_id)) return null;
        User user = userMapper.mapUserDtoToUser(userDto);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(int id) {
        if(!userRepository.existsById(id)) return null;
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAllStudents() {
        List<User> users = userRepository.findAll();
        List<User> students = new ArrayList<>();
        for(User user : users) {
            if(user.getRole() == Role.STUDENT) students.add(user);
        }
        return students;
    }

    @Override
    public List<User> getAllTeachers() {
        List<User> users = userRepository.findAll();
        List<User> teachers = new ArrayList<>();
        for(User user : users) {
            if(user.getRole() == Role.TEACHER) teachers.add(user);
        }
        return teachers;
    }


    @Override
    public Boolean deleteUser(int id) {
        if(!userRepository.existsById(id)) return Boolean.FALSE;
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
        return Boolean.TRUE;
    }
}
