package com.example.crudapp.controller;

import com.example.crudapp.model.User; // Certifique-se de que este import está correto
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller responsável pelas rotas públicas de segurança e navegação.
 * Mapeia /login, /cadastro e a rota raiz /.
 */
@Controller
public class SecurityController {

    // Mapeia a URL /login para o template login.html
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    /**
     * Mapeia a URL /cadastro para o template cadastro.html.
     * Passa o objeto 'user' vazio para que o formulário th:object="${user}" funcione.
     */
    @GetMapping("/cadastro")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "cadastro";
    }

    /**
     * Redireciona a raiz para a listagem principal, protegida por autenticação.
     */
    @GetMapping("/")
    public String index() {
        return "redirect:/projects";
    }
}