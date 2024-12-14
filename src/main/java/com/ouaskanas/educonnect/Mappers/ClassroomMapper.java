package com.ouaskanas.educonnect.Mappers;

import com.ouaskanas.educonnect.Dao.Entities.Classroom;
import com.ouaskanas.educonnect.Dao.Entities.Post;
import com.ouaskanas.educonnect.Dao.Entities.User;
import com.ouaskanas.educonnect.Dao.Repositories.ClassroomRepository;
import com.ouaskanas.educonnect.Dao.Repositories.PostRepository;
import com.ouaskanas.educonnect.Dao.Repositories.UserRepository;
import com.ouaskanas.educonnect.Dto.ClassroomDto;
import com.ouaskanas.educonnect.Dto.PostDto;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public class ClassroomMapper {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;


    public Classroom ToEntity(ClassroomDto classroomDto) {
        Classroom classroom = new Classroom();
        classroom.setName(classroomDto.getName());
        classroom.setTeacher(userRepository.findById(classroomDto.getTeacher_id()).get());
        if (classroomDto.getStudents_id() != null && !classroomDto.getStudents_id().isEmpty()) {
        List<User> students = userRepository.findAllById(classroomDto.getStudents_id());
        classroom.setStudent(students);}
        if(classroomDto.getPost_id() != null && !classroomDto.getPost_id().isEmpty()) {
            List<Post> posts = postRepository.findAllById(classroomDto.getPost_id());
            classroom.setPosts(posts);
        }
        return classroom;
    }
}
