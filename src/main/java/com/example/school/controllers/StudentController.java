package com.example.school.controllers;

import com.example.school.dto.StudentRequest;
import com.example.school.dto.StudentSummaryDTO;
import com.example.school.models.Student;
import com.example.school.services.StudentServices;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/{id}/upload-profile")
    public String upload(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            studentServices.updateProfilePic(id, file);
            return "File uploaded: " + file.getOriginalFilename();
        } catch (Exception e) {
            return "Upload failed: " + e.getMessage();
        }
    }

    @GetMapping("/{id}/view-photo")
    public ResponseEntity<byte[]> getPhoto(@PathVariable Long id) {
        Student student = studentServices.getStudentById(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(student.getFileType())) // Tells browser "this is an image"
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + student.getFileName() + "\"")
                .body(student.getProfilePic());
    }

    // 2. Endpoint for @SqlResultSetMapping
    @GetMapping("/summary")
    public List<StudentSummaryDTO> getSummary() {
        return studentServices.getStudentSummary();
    }
}
