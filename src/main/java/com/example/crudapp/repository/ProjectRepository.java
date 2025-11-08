package com.example.crudapp.repository;

import com.example.crudapp.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositório para entidade Project
 * Responsável pelas operações de persistência de projetos.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    /**
     * Busca todos os projetos que ainda não estão concluídos.
     * (Requer que a entidade Project tenha um campo boolean "completed")
     *
     * @return lista de projetos não finalizados
     */
    List<Project> findByCompletedFalse();
}