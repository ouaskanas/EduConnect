package com.ouaskanas.educonnect.Mappers;

import com.ouaskanas.educonnect.Dao.Entities.Role;
import com.ouaskanas.educonnect.Dao.Entities.User;
import com.ouaskanas.educonnect.Dao.Repositories.ClassroomRepository;
import com.ouaskanas.educonnect.Dao.Repositories.UserRepository;
import com.ouaskanas.educonnect.Dto.RegisterDto;
import com.ouaskanas.educonnect.Dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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
        user.setClassroom(classroomRepository.findById(userDto.getClassroom_id()).get());
        user.setRole(userDto.getRole());
        return user;
    }

    public UserDto mapUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        if(user.getClassroom() != null){userDto.setClassroom_id(user.getClassroom().getClassroom_id());}
        return userDto;
    }

    public User UpdateUserDtoToUser(UserDto userDto, int id) {
        User user = userRepository.findById(id).get();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setClassroom(classroomRepository.findById(userDto.getClassroom_id()).get());
        user.setRole(userDto.getRole());
        return user;
    }

    public User mapRegisterDtoToUser(RegisterDto registerDto) {
        User user = new User();
        user.setFirstname(registerDto.getFirstName());
        user.setLastname(registerDto.getLastName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());
        user.setRole(Role.STUDENT);
        return user;
    }
}
