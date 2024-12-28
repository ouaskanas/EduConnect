package com.ouaskanas.educonnect.Service.Service;

import com.ouaskanas.educonnect.Dao.Entities.Classroom;
import com.ouaskanas.educonnect.Dao.Entities.Role;
import com.ouaskanas.educonnect.Dao.Entities.User;
import com.ouaskanas.educonnect.Dao.Repositories.ClassroomRepository;
import com.ouaskanas.educonnect.Dao.Repositories.UserRepository;
import com.ouaskanas.educonnect.Dto.ClassroomDto;
import com.ouaskanas.educonnect.Mappers.ClassroomMapper;
import com.ouaskanas.educonnect.Service.Manager.ClassroomManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ClassroomService implements ClassroomManager {

    @Autowired
    public final ClassroomRepository classroomRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClassroomMapper classroomMapper;
    @Autowired
    private UserService userService;

    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @Override
    public Classroom getClassroom(int id) {
        return classroomRepository.findById(id).get();
    }

    @Override
    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    @Override
    public Classroom addClassroom(ClassroomDto classroomdto) {
        User teacher = null;
        //todo:add authentication user;
        if(teacher.getRole()!=Role.TEACHER){
            return null;
        }
        classroomdto.setTeacher_id(teacher.getUser_id());
        Classroom classroom = classroomMapper.ToEntity(classroomdto);
        return classroomRepository.save(classroom);
    }

    @Override
    public Classroom addStudentToclassroom(int classroom_id, int student_id) {
        User user = userRepository.findById(student_id).get();
        if(user.getRole()!=Role.STUDENT){return null;}
        Classroom classroom = classroomRepository.findById(classroom_id).get();
        classroom.getStudent().add(user);
        return classroomRepository.save(classroom);
    }

    @Override
    public Classroom deleteStudentfromClassroom(int classroom_id, int student_id) {
        User user = userRepository.findById(student_id).get();
        Classroom classroom = classroomRepository.findById(classroom_id).get();
        classroom.getStudent().remove(user);
        return classroomRepository.save(classroom);
    }

    @Override
    public List<User> GetClassroomStudents(int classroom_id) {
        if(!classroomRepository.existsById(classroom_id)){  return null; }
        List<User> students = userService.getStudents();
        List<User> classroomStudents = new ArrayList<>();
        if(students.isEmpty()){ return null; }
        for(User user : students){
            if(user.getClassroom() == classroomRepository.findById(classroom_id).get())
                classroomStudents.add(user);
        }
        return classroomStudents;
    }

    @Override
    public User GetClassroomTeacher(int classroom_id){
        if(!classroomRepository.existsById(classroom_id)){  return null; }
        List<User> teachers = userService.getTeachers();
        for( User user : teachers){
            if(user.getClassroom() == classroomRepository.findById(classroom_id).get())
                return user;
        }
        return null;
    }
    @Override
    public void deleteClassroom(int id) {
        classroomRepository.deleteById(id);
    }
}
