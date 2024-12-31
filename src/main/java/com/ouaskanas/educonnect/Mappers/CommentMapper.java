package com.ouaskanas.educonnect.Mappers;

import com.ouaskanas.educonnect.Dao.Entities.Comment;
import com.ouaskanas.educonnect.Dto.CommentDto;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public class CommentMapper {

    @Autowired
    private UserMapper userMapper;

    public CommentDto toDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setComment(comment.getComment());
        commentDto.setPost_id(comment.getPost().getPost_Id());
        commentDto.setAuthor(userMapper.mapUserToUserDto(comment.getAuthor()));
        commentDto.setComment_date(comment.getCreatedAt());
        return commentDto;
    }
}
