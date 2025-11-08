package com.example.crudapp.service;

import com.example.crudapp.model.Project;
import com.example.crudapp.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // ✅ Criar novo projeto
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    // ✅ Listar todos os projetos
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // ✅ Buscar projeto por ID
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    // ✅ Atualizar projeto existente (Já contém o erro lógico do RuntimeException)
    public Project updateProject(Long id, Project projectDetails) {
        return projectRepository.findById(id)
                .map(project -> {
                    project.setName(projectDetails.getName());
                    project.setDescription(projectDetails.getDescription());
                    project.setCompleted(projectDetails.isCompleted());
                    project.setStartDate(projectDetails.getStartDate());
                    project.setEndDate(projectDetails.getEndDate());
                    return projectRepository.save(project);
                })
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado com ID: " + id));
    }

    // ✅ Deletar projeto (CORRIGIDO)
    public void deleteProject(Long id) {
        // 1. Verificar se o projeto existe
        Project project = projectRepository.findById(id)
                // 2. Lançar exceção se não for encontrado
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado para deleção com ID: " + id));

        // 3. Deletar o projeto
        projectRepository.delete(project);
    }
}