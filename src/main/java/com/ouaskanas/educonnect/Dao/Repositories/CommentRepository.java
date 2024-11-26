package com.ouaskanas.educonnect.Dao.Repositories;

import com.ouaskanas.educonnect.Dao.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
