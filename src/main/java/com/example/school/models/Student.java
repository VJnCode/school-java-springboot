package com.example.school.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @Column( name = "student_class")
    private String studentClass;

    @ManyToOne
    @JoinColumn(name = "school_id")
    @JsonBackReference(value = "school-student")
    private School school;

    @ManyToOne
    @JoinColumn( name = "dept_id")
    @JsonBackReference(value = "dept-student")
    private Department department;
}
