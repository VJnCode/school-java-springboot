package com.example.school.controllers;

import com.example.school.dto.StudentRequest;
import com.example.school.models.Student;
import com.example.school.services.StudentServices;
import lombok.Data;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentServices studentServices;

    public StudentController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }

    @GetMapping
    public List<Student> getAll() {
        return studentServices.getAllStudents();
    }

    @PostMapping("/add")
    public String add(@RequestBody StudentRequest req) {
        studentServices.createStudent(req.name, req.studentClass, req.deptId, req.schoolId);
        return "Student added successfully";
    }

    @PutMapping("/updatestudent/{id}")
    public String update(@PathVariable Long id, @RequestBody StudentRequest req) {
        studentServices.updateStudent(id, req.name, req.studentClass);
        return "Student updated";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        studentServices.deleteStudent(id);
        return "Student deleted";
    }
}
