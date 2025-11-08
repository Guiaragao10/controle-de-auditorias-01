package com.example.crudapp.repository;

import com.example.crudapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositório para entidade User
 * Responsável por operações de acesso a dados relacionadas a usuários.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}