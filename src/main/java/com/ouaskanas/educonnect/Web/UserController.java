package com.ouaskanas.educonnect.Web;

import com.ouaskanas.educonnect.Dao.Entities.Role;
import com.ouaskanas.educonnect.Dao.Entities.User;
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

    @GetMapping("/currentuser")
    public User getCurrentUser(){
        return userService.CurrentUser();
    }

    @PutMapping("/grantroles/{id}")
    public ResponseEntity<User> grantroles(@PathVariable int id, @RequestBody Role role){
        userService.grantRole(id,role);
        return ResponseEntity.ok().build();
    }

}
