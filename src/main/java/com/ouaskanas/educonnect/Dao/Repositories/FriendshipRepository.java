package com.ouaskanas.educonnect.Dao.Repositories;

import com.ouaskanas.educonnect.Dao.Entities.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Integer> {
}
