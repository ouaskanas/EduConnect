package com.ouaskanas.educonnect.Dao.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class    Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int post_Id;
    private String title;
    private String content;
    @ManyToOne
    private User author;
    @ManyToOne
    private Classroom classroom;
    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private List<Comment> commentList = new ArrayList<>();
    private boolean shared;
    private LocalDateTime createdAt = LocalDateTime.now();

    public void addComment(Comment comment) {
        commentList.add(comment);
        comment.setPost(this);
    }
}
