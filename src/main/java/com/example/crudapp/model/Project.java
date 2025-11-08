package com.example.crudapp.model;

import jakarta.persistence.*; // ✅ Usar jakarta.persistence
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "projects")
@Getter // ESSENCIAL
@Setter // ESSENCIAL
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    // Nota: O Lombok gera isCompleted() para booleanos.
    private boolean completed;

    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;
}