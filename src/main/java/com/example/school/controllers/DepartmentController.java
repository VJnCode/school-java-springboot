package com.example.school.controllers;

import com.example.school.dto.DepartmentRequest;
import com.example.school.models.Department;
import com.example.school.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public List<Department> getAll() {
        return departmentService.getAllDepartments();
    }

    @PostMapping("/add")
    public Department add(@RequestBody DepartmentRequest request) {
        return departmentService.createDepartment(request.getName(), request.getSchoolId());
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return "Department deleted successfully";
    }
}