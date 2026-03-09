package com.example.school.repository;

import com.example.school.dto.StudentSummaryDTO;
import com.example.school.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "SELECT * FROM student WHERE student_class = :className", nativeQuery = true)
    List<Student> findStudentBySpecificClass(@Param("className") String className);

    // This tells Spring to look for the @NamedNativeQuery inside the Student model
    @Query(name = "findStudentSummary", nativeQuery = true)
    List<StudentSummaryDTO> findStudentSummary();
}