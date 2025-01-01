package com.ouaskanas.educonnect.Dto;

import com.ouaskanas.educonnect.Dao.Entities.Comment;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class PostOutput {
    private String title;
    private String content;
    private UserDto author;
    private List<CommentDto> comments;
}
