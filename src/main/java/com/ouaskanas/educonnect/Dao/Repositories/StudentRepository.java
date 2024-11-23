package com.ouaskanas.educonnect.Dao.Repositories;

import com.ouaskanas.educonnect.Dao.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
