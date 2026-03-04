package com.example.school.repository;

import com.example.school.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department ,Long> {
}
