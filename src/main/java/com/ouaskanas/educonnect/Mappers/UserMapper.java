package com.ouaskanas.educonnect.Mappers;


import com.ouaskanas.educonnect.Dao.Entities.Friendship;
import com.ouaskanas.educonnect.Dao.Entities.User;
import com.ouaskanas.educonnect.Dao.Repositories.ClassroomRepository;
import com.ouaskanas.educonnect.Dao.Repositories.FriendshipRepository;
import com.ouaskanas.educonnect.Dao.Repositories.UserRepository;
import com.ouaskanas.educonnect.Dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    @Autowired
    private ClassroomRepository classroomRepository;

     @Autowired
     private UserRepository userRepository;

    public User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setClassroom(classroomRepository.findById(userDto.getClassroom_id()).get());
        user.setRole(userDto.getRole());
        return user;
    }

    public User UpdateUserDtoToUser(UserDto userDto, int id) {
        User user = userRepository.findById(id).get();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setClassroom(classroomRepository.findById(userDto.getClassroom_id()).get());
        user.setRole(userDto.getRole());
        return user;
    }

}
