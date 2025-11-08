package com.example.crudapp.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Classe base genérica para serviços de entidades JPA.
 * Fornece operações CRUD básicas (Create, Read, Update, Delete)
 * e pode ser estendida por serviços específicos como UserService, TaskService, etc.
 *
 * @param <T> Tipo da entidade
 * @param <R> Tipo do repositório (JpaRepository)
 */
@Transactional
public abstract class BaseService<T, R extends JpaRepository<T, Long>> {

    protected final R repository;

    protected BaseService(R repository) {
        this.repository = repository;
    }

    /**
     * Salva ou atualiza uma entidade no banco.
     * @param entity entidade a ser persistida
     * @return entidade salva
     */
    public T save(T entity) {
        return repository.save(entity);
    }

    /**
     * Retorna todas as entidades.
     */
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return repository.findAll();
    }

    /**
     * Busca uma entidade pelo ID.
     * @param id identificador
     * @return Optional contendo a entidade, se encontrada
     */
    @Transactional(readOnly = true)
    public Optional<T> findById(Long id) {
        return repository.findById(id);
    }

    /**
     * Verifica se uma entidade existe pelo ID.
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    /**
     * Exclui uma entidade pelo ID, se existir.
     * @param id identificador
     */
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Entidade com ID " + id + " não encontrada.");
        }
        repository.deleteById(id);
    }
}