package com.security.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.security.demo.model.HoD;
import com.security.demo.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    /*~~(class org.openrewrite.java.tree.J$Erroneous cannot be cast to class org.openrewrite.java.tree.J$Assignment (org.openrewrite.java.tree.J$Erroneous and org.openrewrite.java.tree.J$Assignment are in unnamed module of loader 'app'))~~>*/@Query("SELECT s FROM Student s WHERE s.roll = :roll")
    List<Student> findByRoll(Integer roll);

	List<Student> findByUsername(String username);

	List<Student> findByDiv(String div);

	List<Student> findByYear(String year);

	Student findByMob(String mob);

	Student findByEmail(String email);

	boolean existsByMob(String mob);
}
