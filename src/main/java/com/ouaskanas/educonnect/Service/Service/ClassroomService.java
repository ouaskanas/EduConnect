package com.ouaskanas.educonnect.Service.Service;

import com.ouaskanas.educonnect.Dao.Entities.Classroom;
import com.ouaskanas.educonnect.Dao.Entities.Role;
import com.ouaskanas.educonnect.Dao.Entities.User;
import com.ouaskanas.educonnect.Dao.Repositories.ClassroomRepository;
import com.ouaskanas.educonnect.Dao.Repositories.UserRepository;
import com.ouaskanas.educonnect.Dto.ClassroomDto;
import com.ouaskanas.educonnect.Dto.Mappers.ClassroomMapper;
import com.ouaskanas.educonnect.Service.Manager.ClassroomManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClassroomService implements ClassroomManager {

    @Autowired
    public final ClassroomRepository classroomRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClassroomMapper classroomMapper;

    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @Override
    public Classroom getClassroom(long id) {
        return classroomRepository.findById(id).get();
    }

    @Override
    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    @Override
    public Classroom addClassroom(ClassroomDto classroomdto) {
        User teacher = userRepository.findById(classroomdto.getTeacher_id()).get();
        if(!teacher.getRole().equals(Role.TEACHER)){
            return null;
        }
        Classroom classroom = classroomMapper.ToEntity(classroomdto);
        return classroomRepository.save(classroom);
    }

    @Override
    public Classroom addStudentToclassroom(long classroom_id, long student_id) {
        User user = userRepository.findById(student_id).get();
        Classroom classroom = classroomRepository.findById(classroom_id).get();
        classroom.getStudent().add(user);
        return classroomRepository.save(classroom);
    }

    @Override
    public Classroom deleteStudentfromClassroom(long classroom_id, long student_id) {
        User user = userRepository.findById(student_id).get();
        Classroom classroom = classroomRepository.findById(classroom_id).get();
        classroom.getStudent().remove(user);
        return classroomRepository.save(classroom);
    }

    @Override
    public void deleteClassroom(long id) {
        classroomRepository.deleteById(id);
    }
}
