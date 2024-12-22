package com.ouaskanas.educonnect.Dao.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classroom_id;
    private String name;
    @ManyToMany
    private List<User> student;
    @ManyToOne
    private User teacher;
    @OneToMany
    private List<Post> posts;
}
