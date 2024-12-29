package com.ouaskanas.educonnect.Mappers;

import com.ouaskanas.educonnect.Dao.Entities.Classroom;
import com.ouaskanas.educonnect.Dao.Entities.Post;
import com.ouaskanas.educonnect.Dao.Entities.Role;
import com.ouaskanas.educonnect.Dao.Entities.User;
import com.ouaskanas.educonnect.Dao.Repositories.ClassroomRepository;
import com.ouaskanas.educonnect.Dao.Repositories.PostRepository;
import com.ouaskanas.educonnect.Dao.Repositories.UserRepository;
import com.ouaskanas.educonnect.Dto.ClassroomDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClassroomMapper {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClassroomRepository classroomRepository;


    public Classroom ToEntity(ClassroomDto classroomDto) {
        Classroom classroom = new Classroom();
        classroom.setName(classroomDto.getName());
        Optional<User> authorOptional = userRepository.findById(classroomDto.getTeacher_id());
        if (authorOptional.isPresent() && authorOptional.get().getRole() == Role.TEACHER) {
            classroom.setTeacher(authorOptional.get());
        } else {
            throw new RuntimeException("Utilisateur avec l'ID " + classroomDto.getTeacher_id() + " n'a pas été trouvé.");
        }
        return classroom;
    }
}
