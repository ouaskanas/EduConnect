package com.ouaskanas.educonnect.Web;

import com.ouaskanas.educonnect.Dao.Entities.User;
import com.ouaskanas.educonnect.Dao.Repositories.UserRepository;
import com.ouaskanas.educonnect.Dto.UserDto;
import com.ouaskanas.educonnect.Service.Service.ClassroomService;
import com.ouaskanas.educonnect.Service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/students")
    public List<User> getStudents() {
        List<User> students = userService.getAllStudents();
        if(students.isEmpty()) return null;
        return students;
    }

    @GetMapping("/students/classroom/{classroom_id}")
    public List<User> getStudentsByClassroom(@PathVariable int classroom_id) {
        List<User> students = classroomService.GetClassroomStudents(classroom_id);
        if(students.isEmpty()) return null;
        return students;
    }

    @GetMapping("/teachers")
    public List<User> getTeachers() {
        List<User> teachers = userService.getAllTeachers();
        if(teachers.isEmpty()) return null;
        return teachers;
    }

    @GetMapping("/teacher/classroom/{classroom_id}")
    public ResponseEntity<User> getTeachersByClassroom(@PathVariable int classroom_id) {
        User teacher = classroomService.GetClassroomTeacher(classroom_id);
        return ResponseEntity.ok(teacher);
    }

    @GetMapping("/suggestions")
    public List<User> getSuggestions(User user) {
        int user_id = user.getUser_id();
        List<User> users = userRepository.findAll();
        Collections.shuffle(users);
        return users.stream()
                .filter(current -> current.getUser_id() != user_id)
                .limit(10)
                .toList();
    }
    //todo:SignIn

    @PostMapping("/createAcc")
    public ResponseEntity<User> createAcc(@RequestBody UserDto userDto) {
        User user = userService.createUser(userDto);
        if(user == null) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return ResponseEntity.ok(user);
    }
}
