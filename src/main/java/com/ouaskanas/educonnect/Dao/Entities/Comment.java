package com.ouaskanas.educonnect.Dao.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comment_id;
    private String comment;
    @ManyToOne
    private Post post;
    @ManyToOne
    private User author;
    private LocalDateTime createdAt = LocalDateTime.now();

}
