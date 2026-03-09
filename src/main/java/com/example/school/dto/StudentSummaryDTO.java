package com.example.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentSummaryDTO {
    private String studentName;
    private String deptName;
}