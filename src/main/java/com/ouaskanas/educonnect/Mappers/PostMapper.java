package com.ouaskanas.educonnect.Mappers;

import com.ouaskanas.educonnect.Dao.Entities.Comment;
import com.ouaskanas.educonnect.Dao.Entities.Post;
import com.ouaskanas.educonnect.Dao.Entities.User;
import com.ouaskanas.educonnect.Dao.Repositories.ClassroomRepository;
import com.ouaskanas.educonnect.Dao.Repositories.PostRepository;
import com.ouaskanas.educonnect.Dao.Repositories.UserRepository;
import com.ouaskanas.educonnect.Dto.CommentDto;
import com.ouaskanas.educonnect.Dto.PostDto;
import com.ouaskanas.educonnect.Dto.PostOutput;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public class PostMapper {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentMapper commentMapper;

    public void UpdateEntityfromDto(Post post,PostDto postDto) {
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setShared(postDto.isShared());
    }

    public PostDto ToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setShared(post.isShared());
        postDto.setAuthor_id(post.getAuthor().getUser_id());
        postDto.setClassroom_id(post.getClassroom().getClassroom_id());
        return postDto;
    }

    public Post ToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setShared(postDto.isShared());
        Optional<User> authorOptional = userRepository.findById(postDto.getAuthor_id());
        if (authorOptional.isPresent()) {
            post.setAuthor(authorOptional.get());
        } else {
            throw new RuntimeException("Utilisateur avec l'ID " + postDto.getAuthor_id() + " n'a pas été trouvé.");
        }
        if (postDto.getclassRoomId() != null) {
            classroomRepository.findById(postDto.getClassroom_id()).ifPresent(post::setClassroom);
        }
        return post;
    }

    public PostOutput postOutput(Post post){
        PostOutput postOutput = new PostOutput();
        postOutput.setTitle(post.getTitle());
        postOutput.setContent(post.getContent());
        postOutput.setAuthor(userMapper.mapUserToUserDto(post.getAuthor()));
        List<Comment> comments = post.getCommentList();
        List<CommentDto> commentDtos = new ArrayList<>();
        for(Comment comment : comments){
            commentDtos.add(commentMapper.toDto(comment));
        }
        postOutput.setComments(commentDtos);
        return postOutput;
    }

}
