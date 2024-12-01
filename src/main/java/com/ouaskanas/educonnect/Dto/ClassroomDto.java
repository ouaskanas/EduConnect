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
    private long teacher_id;
    private List<Long> students_id;
    private List<Long> post_id;
}
