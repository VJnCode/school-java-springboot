package com.example.school.repository;

import com.example.school.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "SELECT * FROM students WHERE student_class = :className", nativeQuery = true)
    List<Student> findStudentBySpecificClass(@Param("className") String className);
}
