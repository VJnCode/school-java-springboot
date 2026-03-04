package com.example.school.services;

import com.example.school.models.Department;
import com.example.school.models.School;
import com.example.school.repository.DepartmentRepository;
import com.example.school.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final SchoolRepository schoolRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department createDepartment(String name, Long schoolId) {
        // Find the parent school first
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new RuntimeException("Cannot create department: School not found"));

        Department department = new Department();
        department.setName(name);
        department.setSchool(school); // Links the department to the school

        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}