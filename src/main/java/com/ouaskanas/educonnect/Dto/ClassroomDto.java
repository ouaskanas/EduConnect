package com.ouaskanas.educonnect.Dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassroomDto {
    private String name;
    private int teacher_id;
}
