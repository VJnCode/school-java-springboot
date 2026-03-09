package com.example.school.services;

import com.example.school.dto.StudentSummaryDTO;
import com.example.school.models.*;
import com.example.school.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServices {

    private final StudentRepository studentRepository;
    private final SchoolRepository schoolRepository;
    private final DepartmentRepository departmentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Implementation of @SqlResultSetMapping Query
    public List<StudentSummaryDTO> getStudentSummary() {
        return studentRepository.findStudentSummary();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    //  Implementation of MultipartFile Upload
    public void updateProfilePic(Long id, MultipartFile file) throws IOException {
        Student student = getStudentById(id);

        if (!file.isEmpty()) {
            // Using MultipartFile Important Methods
            student.setProfilePic(file.getBytes());
            student.setFileName(file.getOriginalFilename());
            student.setFileType(file.getContentType());

            studentRepository.save(student);
        }
    }

    public void createStudent(String name, String sClass, Long deptId, Long schoolId) {
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new RuntimeException("School not found"));
        Department dept = departmentRepository.findById(deptId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Student student = new Student();
        student.setName(name);
        student.setStudentClass(sClass);
        student.setSchool(school);
        student.setDepartment(dept);
        studentRepository.save(student);
    }

    public void updateStudent(Long id, String name, String sClass) {
        Student student = getStudentById(id);
        student.setName(name);
        student.setStudentClass(sClass);
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot delete: Student not found");
        }
        studentRepository.deleteById(id);
    }
}