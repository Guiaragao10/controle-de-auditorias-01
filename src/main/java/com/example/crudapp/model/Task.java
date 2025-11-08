package com.example.crudapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    public enum Status {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    // Cada tarefa pertence a um projeto (Mapeamento existente)
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    // 💡 CORREÇÃO: Mapeamento para o Usuário (user) que está atribuído à tarefa
    @ManyToOne
    @JoinColumn(name = "assigned_user_id") // Nome da coluna na tabela 'tasks'
    private User user; // O nome DEVE ser 'user' para que User.tasks (mappedBy="user") funcione

    public Task() {}

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    // 💡 NOVO: Getter e Setter para o Usuário
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}