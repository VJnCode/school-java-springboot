package com.example.school.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table( name = "departments")
@Data
public class Department {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "school_id")
    @JsonBackReference(value = "school-dept")
    private School school;
    @OneToMany( mappedBy = "department")
    @JsonManagedReference(value = "dept-student")
    List<Student> students;
}
