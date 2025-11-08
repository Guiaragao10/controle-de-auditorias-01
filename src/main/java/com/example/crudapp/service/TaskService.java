package com.example.crudapp.service;

import com.example.crudapp.model.Task;
import com.example.crudapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService extends BaseService<Task, TaskRepository> {

    public TaskService(TaskRepository repository) {
        super(repository);
    }

    @Override
    public Task save(Task task) {
        validarTask(task);
        return super.save(task);
    }

    // 🔹 Buscar todas as tasks de um projeto
    public List<Task> findByProjectId(Long projectId) {
        return repository.findByProjectId(projectId);
    }

    // 🔹 Atualizar status de uma task
    public Task updateStatus(Long id, Task.Status newStatus) {
        Optional<Task> optionalTask = repository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new IllegalArgumentException("Task não encontrada para ID: " + id);
        }

        Task task = optionalTask.get();
        task.setStatus(newStatus);
        return repository.save(task);
    }

    // 🔹 Marcar task como COMPLETED
    public Task markAsCompleted(Long id) {
        Optional<Task> optionalTask = repository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new IllegalArgumentException("Task não encontrada para ID: " + id);
        }

        Task task = optionalTask.get();
        task.setStatus(Task.Status.COMPLETED);
        return repository.save(task);
    }

    // 🔍 Validação antes de salvar
    private void validarTask(Task task) {
        if (task.getTitle() == null || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("O título da tarefa não pode estar vazio.");
        }

        if (task.getProject() == null) {
            throw new IllegalArgumentException("A tarefa deve estar vinculada a um projeto.");
        }
    }
}