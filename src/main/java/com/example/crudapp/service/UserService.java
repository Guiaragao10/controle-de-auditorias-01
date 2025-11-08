package com.example.crudapp.service;

import com.example.crudapp.model.User;
import com.example.crudapp.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    // usado pelo controller e pela API
    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    // ao salvar, criptografa a senha se necessário
    public User save(User user) {
        if (user.getPassword() != null && !user.getPassword().startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        // garante que o usuário esteja ativo por padrão
        if (user.getRoles() == null || user.getRoles().isBlank()) {
            user.setRoles("ROLE_USER");
        }
        return repository.save(user);
    }

    // desativa usuário (soft delete)
    public void deactivateUser(Long id) {
        repository.findById(id).ifPresent(user -> {
            user.setActive(false); // exige que User tenha campo "active"
            repository.save(user);
        });
    }

    // método obrigatório para o Spring Security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        // converte roles "ROLE_USER,ROLE_ADMIN" para GrantedAuthorities
        List<SimpleGrantedAuthority> authorities = Arrays.stream(
                        Optional.ofNullable(user.getRoles()).orElse("ROLE_USER").split(","))
                .map(String::trim)
                .filter(r -> !r.isEmpty())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        boolean enabled = user.isActive(); // exige que User tenha isActive()
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .disabled(!enabled) // se active == false, será desabilitado
                .build();
    }
}
