package com.example.crudapp.config;

import com.example.crudapp.model.User;
import com.example.crudapp.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Transactional
    public void initDatabase() {
        // Verifica se já existe usuário padrão
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@crudapp.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles("ROLE_USER,ROLE_ADMIN");
            admin.setActive(true);
            userRepository.save(admin);
            System.out.println("✅ Usuário admin criado - Username: admin | Senha: admin123");
        }

        // Criar usuário comum de teste
        if (userRepository.findByUsername("user").isEmpty()) {
            User user = new User();
            user.setUsername("user");
            user.setEmail("user@crudapp.com");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRoles("ROLE_USER");
            user.setActive(true);
            userRepository.save(user);
            System.out.println("✅ Usuário user criado - Username: user | Senha: user123");
        }
        
        System.out.println("🚀 Sistema inicializado com sucesso! Acesse: http://localhost:8080");
    }
}
