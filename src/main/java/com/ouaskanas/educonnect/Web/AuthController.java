package com.ouaskanas.educonnect.Web;


import com.ouaskanas.educonnect.Dao.Entities.Post;
import com.ouaskanas.educonnect.Dao.Entities.Role;
import com.ouaskanas.educonnect.Dto.LoginDto;
import com.ouaskanas.educonnect.Dto.PostOutput;
import com.ouaskanas.educonnect.Dto.RegisterDto;
import com.ouaskanas.educonnect.Service.Service.PostService;
import com.ouaskanas.educonnect.Service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        return userService.loginUser(loginDto);
    }

    @PostMapping("/auth/register")
    public @ResponseBody ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        registerDto.setRole(Role.STUDENT);
        userService.RegisterUser(registerDto);
        return ResponseEntity.status(HttpStatus.OK).body("user created successfully");
    }

    @PostMapping("/auth/teacher_register")
    public @ResponseBody ResponseEntity<?> registerAsTeacher(@RequestBody RegisterDto registerDto) {
        registerDto.setRole(Role.TEACHER);
        userService.RegisterUser(registerDto);
        return ResponseEntity.status(HttpStatus.OK).body("teacher created successfully");
    }

    @GetMapping("/")
    public List<PostOutput> getPosts() {
        return postService.getAllPosts();
    }
}
