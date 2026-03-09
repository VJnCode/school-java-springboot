package com.example.school.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "schools")
@Data
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String district;
    private String State;
    private String address;

    @OneToMany(mappedBy = "school" , cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    @JsonManagedReference(value = "school-student")
    List<Student> students;

//    @OneToMany(mappedBy = "school" , cascade = CascadeType.ALL)
//    @JsonManagedReference(value = "school-dept")
//    List<Department> departments;
}
