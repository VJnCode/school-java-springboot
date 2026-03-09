package com.example.school.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@NamedNativeQuery(
        name = "findStudentSummary",
        query = "SELECT s.name AS sName, d.name AS dName FROM student s JOIN department d ON s.dept_id = d.id",
        resultSetMapping = "Mapping.StudentSummaryDTO"
)
@SqlResultSetMapping(
        name = "Mapping.StudentSummaryDTO",
        classes = @ConstructorResult(
                targetClass = com.example.school.dto.StudentSummaryDTO.class,
                columns = {
                        @ColumnResult(name = "sName"),
                        @ColumnResult(name = "dName")
                }
        )
)
@Entity
@Table(name="student")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "student_class")
    private String studentClass;

    // --- MultipartFile implementation fields ---
    @Lob // Required for large binary data
    @Column(name = "profile_pic", columnDefinition = "LONGBLOB") // LONGBLOB for MySQL/Docker
    private byte[] profilePic;

    private String fileName;
    private String fileType;
    // -------------------------------------------

    @ManyToOne
    @JoinColumn(name = "school_id")
    @JsonBackReference(value = "school-student")
    private School school;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    @JsonBackReference(value = "dept-student")
    private Department department;
}