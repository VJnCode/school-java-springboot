package com.example.school.dto;

import lombok.Data;

@Data
public class DepartmentRequest {
    private String name;
    private Long schoolId;
}