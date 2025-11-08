package com.example.crudapp.controller;

import com.example.crudapp.model.Project;
import com.example.crudapp.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller; // Mudança: de @RestController para @Controller
import org.springframework.ui.Model; // Novo
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller // Usar @Controller para servir HTML
@RequestMapping
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /* -----------------------------------------------------------------
       THYMELEAF ENDPOINTS (interface web)
       ----------------------------------------------------------------- */

    // 🎯 Novo: Página de listagem e formulário de projetos
    @GetMapping("/projects")
    public String projectsPage(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        model.addAttribute("project", new Project());
        return "projects/list"; // src/main/resources/templates/projects/list.html
    }

    // 🎯 Novo: Formulário de edição ou criação
    @GetMapping("/projects/new")
    public String showNewProjectForm(Model model) {
        model.addAttribute("project", new Project());
        return "projects/form"; // src/main/resources/templates/projects/form.html
    }

    @GetMapping("/projects/edit/{id}")
    public String showEditProjectForm(@PathVariable Long id, Model model) {
        Project project = projectService.getProjectById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado com ID: " + id));
        model.addAttribute("project", project);
        return "projects/form";
    }

    @PostMapping("/projects/save")
    public String saveProject(@ModelAttribute Project project) {
        // Se o ID for null, o Service cria; se não for, ele atualiza
        projectService.createProject(project);
        return "redirect:/projects";
    }

    @GetMapping("/projects/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return "redirect:/projects";
    }


    /* -----------------------------------------------------------------
       REST API ENDPOINTS (para uso externo)
       (Manteremos a URL /api/projects para evitar conflito com /projects)
       ----------------------------------------------------------------- */

    @GetMapping("/api/projects")
    @ResponseBody // Indica que o retorno é o corpo da resposta HTTP (JSON/XML)
    public ResponseEntity<List<Project>> apiFindAll() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    // ... Incluir os demais métodos @PostMapping, @PutMapping, @DeleteMapping da API aqui ...
    // Note: Você pode mover todos os métodos @RestController (POST, PUT, DELETE) para cá e apenas adicionar @ResponseBody.
}