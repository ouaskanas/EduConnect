package com.ouaskanas.educonnect.Dao.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long post_Id;
    private String title;
    private String content;

    @ManyToOne
    private User author;

    @ManyToOne
    private Classroom classroom;

    private boolean shared;
}
