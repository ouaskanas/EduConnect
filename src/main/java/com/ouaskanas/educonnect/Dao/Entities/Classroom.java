package com.ouaskanas.educonnect.Dao.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long classroom_id;
    private String name;

    @ManyToMany
    private List<Student> student;

    @ManyToOne
    private Teacher teacher;

    @OneToMany
    private List<Post> posts;
}
