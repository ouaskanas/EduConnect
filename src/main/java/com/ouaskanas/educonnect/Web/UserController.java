package com.ouaskanas.educonnect.Web;

import com.ouaskanas.educonnect.Dao.Entities.Role;
import com.ouaskanas.educonnect.Dao.Entities.User;
import com.ouaskanas.educonnect.Dto.LoginDto;
import com.ouaskanas.educonnect.Dto.RegisterDto;
import com.ouaskanas.educonnect.Service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/teachers")
    public List<User> getTeachers(){
        return userService.getTeachers();
    }

    @GetMapping("/students")
    public List<User> getStudents(){
        return userService.getStudents();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        return userService.loginUser(loginDto);
    }

    @PostMapping("/register")
    public @ResponseBody ResponseEntity<?> register(@RequestBody RegisterDto registerDto){
        userService.RegisterUser(registerDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/grantroles/{id}")
    public ResponseEntity<User> grantroles(@PathVariable int id, @RequestBody Role role){
        userService.grantRole(id,role);
        return ResponseEntity.ok().build();
    }

}
