package com.ouaskanas.educonnect.Web;

import com.ouaskanas.educonnect.Dao.Entities.Classroom;
import com.ouaskanas.educonnect.Dao.Entities.User;
import com.ouaskanas.educonnect.Dto.ClassroomDto;
import com.ouaskanas.educonnect.Service.Service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classroom")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @GetMapping("/allclassrooms")
    public List<Classroom> getAllClassrooms() {
        return classroomService.getAllClassrooms();
    }

    @GetMapping("/{id}")
    public Classroom getClassroom(@PathVariable int id) {
        return classroomService.getClassroom(id);
    }

    @GetMapping("/{classroom_id}/students")
    public List<User> getStudents(@PathVariable int classroom_id) {
        return classroomService.GetClassroomStudents(classroom_id);
    }

    @GetMapping("/{classroom_id}/teacher")
    public ResponseEntity<User> getTeacher(@PathVariable int classroom_id) {
        if(classroomService.GetClassroomTeacher(classroom_id) == null) { return ResponseEntity.notFound().build(); }
        return new ResponseEntity<>(classroomService.GetClassroomTeacher(classroom_id), HttpStatus.OK);
    }

    @PostMapping("/createClassroom")
    public ResponseEntity<String> createClassroom(@RequestBody ClassroomDto classroom) {
        classroomService.addClassroom(classroom);
        return ResponseEntity.status(HttpStatus.OK).body("Created");
    }

    @PutMapping("/addstudent/{classroom_id}")
    public ResponseEntity<String> addStudent(@PathVariable int classroom_id, @RequestBody int student_id) {
        classroomService.addStudentToclassroom(classroom_id, student_id);
        return ResponseEntity.status(HttpStatus.OK).body("student added "+student_id);
    }

    @PutMapping("/deletestudent/{classroom_id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int classroom_id, @RequestBody int student_id) {
        classroomService.deleteStudentfromClassroom(classroom_id, student_id);
        return ResponseEntity.status(HttpStatus.OK).body("student deleted "+student_id);
    }

    @DeleteMapping("/deleteclassroom/{id}")
    public ResponseEntity<String> deleteClassroom(@PathVariable int id) {
        classroomService.deleteClassroom(id);
        return ResponseEntity.status(HttpStatus.OK).body("Classroom deleted "+id);
    }
}
