package com.ouaskanas.educonnect.Service.Service;

import com.ouaskanas.educonnect.Dao.Entities.Role;
import com.ouaskanas.educonnect.Dao.Entities.User;
import com.ouaskanas.educonnect.Dao.Repositories.ClassroomRepository;
import com.ouaskanas.educonnect.Dao.Repositories.UserRepository;
import com.ouaskanas.educonnect.Dto.AuthResponseDto;
import com.ouaskanas.educonnect.Dto.LoginDto;
import com.ouaskanas.educonnect.Dto.RegisterDto;
import com.ouaskanas.educonnect.Dto.UserDto;
import com.ouaskanas.educonnect.Mappers.UserMapper;
import com.ouaskanas.educonnect.Security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtGenerator jwtGenerator;

    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public User RegisterUser(RegisterDto registerDto) {
        if(userRepository.findByEmail(registerDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already in use");
        }
        User user = userMapper.mapRegisterDtoToUser(registerDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public ResponseEntity<?> loginUser(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken test = new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword());
        System.out.println("i m here so hello ");
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),
                            loginDto.getPassword()));
            System.out.println("authentication" + authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            return new ResponseEntity<AuthResponseDto>(new AuthResponseDto(token), HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<String>("Username or Password are wrong "+e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public List<User> getStudents() {
        List<User> users = userRepository.findAll();
        List<User> students = new ArrayList<>();
        for(User user : users) {
            if( user.getRole() == Role.STUDENT)
                students.add(user);
        }
        return students;
    }

    public List<User> getTeachers() {
        List<User> users = userRepository.findAll();
        List<User> teachers = new ArrayList<>();
        for(User user : users) {
            if (user.getRole() == Role.TEACHER)
                teachers.add(user);
        }
        return teachers;
    }

    public User grantRole(int id, Role role){
        User user = userRepository.findById(id).get();
        if(user.getRole() == role){
            return null;
        }
        user.setRole(role);
        return userRepository.save(user);
    }



}
