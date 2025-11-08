package com.example.crudapp.controller;

import com.example.crudapp.model.User;
import com.example.crudapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Controller do módulo de Usuários.
 * Integra camada web (Thymeleaf) e API REST.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // ✅ Injeção via construtor — padrão ideal com Spring
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /* ==========================================================
       🖥️ SEÇÃO: THYMELEAF (interface web simples)
       ========================================================== */

    // Página principal com listagem e formulário
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("user", new User());
        return "users"; // src/main/resources/templates/users.html
    }

    // Salva ou atualiza usuário via formulário
    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/users";
    }

    // Desativar usuário (soft delete)
    @GetMapping("/deactivate/{id}")
    public String deactivateUser(@PathVariable Long id) {
        userService.deactivateUser(id);
        return "redirect:/users";
    }

    /* ==========================================================
       ⚙️ SEÇÃO: API REST (para consumo via Postman / Front)
       ========================================================== */

    // Listar todos (GET /users/api)
    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<User>> findAllApi() {
        return ResponseEntity.ok(userService.findAll());
    }

    // Buscar por ID (GET /users/api/{id})
    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<User> findByIdApi(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Criar usuário via API (POST /users/api)
    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<User> createUserApi(@RequestBody User user) {
        User created = userService.save(user);
        return ResponseEntity.created(URI.create("/users/api/" + created.getId())).body(created);
    }

    // Atualizar via API (PUT /users/api/{id})
    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<User> updateUserApi(@PathVariable Long id, @RequestBody User user) {
        if (userService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        user.setId(id);
        User updated = userService.save(user);
        return ResponseEntity.ok(updated);
    }

    // Deletar (soft delete) via API (DELETE /users/api/{id})
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteUserApi(@PathVariable Long id) {
        userService.deactivateUser(id);
        return ResponseEntity.noContent().build();
    }
}