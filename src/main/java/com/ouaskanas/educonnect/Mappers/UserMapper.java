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

    public User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        List<Friendship> friendships = new ArrayList<>();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setClassroom(classroomRepository.findById(userDto.getClassroom_id()).get());
        user.setRole(userDto.getRole());
        user.setEmail(userDto.getEmail());
        return user;
    }

}
