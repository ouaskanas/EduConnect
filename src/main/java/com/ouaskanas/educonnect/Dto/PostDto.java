package com.ouaskanas.educonnect.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private String title;
    private String content;
    private int author_id;
    private int classroom_id;
    private boolean shared;

    public Integer getclassRoomId(){
        return classroom_id;
    }
}
