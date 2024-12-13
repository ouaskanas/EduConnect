package com.ouaskanas.educonnect.Dao.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long friendship_Id;
    @ManyToOne
    private User student;
    @ManyToOne
    private User friend;
    @Enumerated(EnumType.STRING)
    private FriendShipStatus status;
}