package com.ouaskanas.educonnect.Dao.Repositories;

import com.ouaskanas.educonnect.Dao.Entities.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}
