package com.ouaskanas.educonnect.Dao.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    @OneToMany
    private List<Comment> commentList;
    private boolean shared;
    private LocalDateTime createdAt = LocalDateTime.now();
}
