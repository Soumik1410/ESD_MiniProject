package org.example.esd_project_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @Column(name = "employee_id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "title")
    private String title;

    @Column(name = "photograph_path")
    private String photograph_path;

    @Column(name = "department")
    private Long department;

    @Column(name = "password")
    private String password;
}
