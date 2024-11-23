package com.ouaskanas.educonnect.Dao;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Student extends User{
    private String groupe;
    private List<Classroom> classroom;
    private List<Friendship> frienship;
}
