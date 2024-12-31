package com.ouaskanas.educonnect.Dto;

import com.ouaskanas.educonnect.Dao.Entities.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Setter
@Getter
@ToString
public class CommentDto {
    private String comment;
    private int post_id;
    private UserDto Author;
    private LocalDateTime comment_date;
}
