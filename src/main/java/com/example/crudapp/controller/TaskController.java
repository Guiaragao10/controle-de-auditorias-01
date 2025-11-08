package com.example.crudapp.controller;

import com.example.crudapp.model.Task;
import com.example.crudapp.service.TaskService;
import com.example.crudapp.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Controller para Task (Tarefas)
 * Integra Thymeleaf + API REST
 */
@Controller
@RequestMapping
public class TaskController {

    private final TaskService taskService;
    private final ProjectService projectService;

    public TaskController(TaskService taskService, ProjectService projectService) {
        this.taskService = taskService;
        this.projectService = projectService;
    }

    /* -----------------------------------------------------------------
       THYMELEAF ENDPOINTS (interface web)
       ----------------------------------------------------------------- */

    @GetMapping("/tasks")
    public String tasksPage(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        model.addAttribute("task", new Task());
        // 🔧 Corrigido: método correto é getAllProjects(), não findAll()
        model.addAttribute("projects", projectService.getAllProjects());
        return "tasks"; // src/main/resources/templates/tasks.html
    }

    @PostMapping("/tasks/save")
    public String saveTask(@ModelAttribute Task task) {
        taskService.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return "redirect:/tasks";
    }

    /* -----------------------------------------------------------------
       REST API ENDPOINTS (para uso externo, ex: frontend React, Postman)
       ----------------------------------------------------------------- */

    // Listar todas as tarefas
    @GetMapping("/api/tasks")
    public ResponseEntity<List<Task>> apiFindAll() {
        return ResponseEntity.ok(taskService.findAll());
    }

    // Buscar tarefa por ID
    @GetMapping("/api/tasks/{id}")
    public ResponseEntity<Task> apiFindById(@PathVariable Long id) {
        return taskService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Criar nova tarefa
    @PostMapping("/api/tasks")
    public ResponseEntity<Task> apiCreate(@RequestBody Task task) {
        Task created = taskService.save(task);
        return ResponseEntity.created(URI.create("/api/tasks/" + created.getId())).body(created);
    }

    // Atualizar tarefa existente
    @PutMapping("/api/tasks/{id}")
    public ResponseEntity<Task> apiUpdate(@PathVariable Long id, @RequestBody Task payload) {
        return taskService.findById(id)
                .map(existing -> {
                    payload.setId(existing.getId());
                    Task updated = taskService.save(payload);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Deletar tarefa (remoção lógica ou real, dependendo do service)
    @DeleteMapping("/api/tasks/{id}")
    public ResponseEntity<Void> apiDelete(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Listar tarefas de um projeto específico
    @GetMapping("/api/projects/{projectId}/tasks")
    public ResponseEntity<List<Task>> apiFindByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(taskService.findByProjectId(projectId));
    }
}