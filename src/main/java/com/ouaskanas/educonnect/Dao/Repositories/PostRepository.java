package com.ouaskanas.educonnect.Dao.Repositories;

import com.ouaskanas.educonnect.Dao.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
