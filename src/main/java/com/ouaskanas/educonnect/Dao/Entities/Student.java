package com.ouaskanas.educonnect.Dao.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student extends User{
    private String groupe;
    @ManyToMany
    private List<Classroom> classroom;
    @ManyToMany
    private List<Friendship> frienship;
}
