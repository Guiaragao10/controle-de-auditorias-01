package com.example.crudapp.repository;

import com.example.crudapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositório para entidade Task
 * Responsável por operações de acesso a dados relacionadas a tarefas.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Busca todas as tarefas associadas a um determinado projeto.
     * @param projectId ID do projeto
     * @return lista de tarefas pertencentes ao projeto
     */
    List<Task> findByProjectId(Long projectId);
}