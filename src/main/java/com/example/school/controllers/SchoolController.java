package com.example.school.controllers;

import com.example.school.dto.SchoolRequest;
import com.example.school.models.School;
import com.example.school.services.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @GetMapping
    public List<School> getAll() {
        return schoolService.getAllSchools();
    }

    @PostMapping("/add")
    public School addSchool(@RequestBody SchoolRequest request) {
        return schoolService.createSchool(request.getName(), request.getAddress());
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        schoolService.deleteSchool(id);
        return "School deleted successfully";
    }
}