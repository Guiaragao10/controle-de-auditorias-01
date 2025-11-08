package com.example.crudapp.controller;

import com.example.crudapp.model.User;
import com.example.crudapp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller responsável pelas rotas públicas de segurança e navegação.
 * Mapeia /login, /cadastro e a rota raiz /.
 */
@Controller
public class SecurityController {

    private final UserService userService;

    public SecurityController(UserService userService) {
        this.userService = userService;
    }

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
     * Processa o cadastro do usuário.
     * Salva o usuário com senha criptografada e faz login automático.
     */
    @PostMapping("/cadastro")
    public String registerUser(@ModelAttribute User user, 
                               HttpServletRequest request,
                               HttpServletResponse response,
                               RedirectAttributes redirectAttributes) {
        try {
            // Verifica se o username já existe
            if (userService.findAll().stream()
                    .anyMatch(u -> u.getUsername().equals(user.getUsername()))) {
                redirectAttributes.addFlashAttribute("error", "Nome de usuário já existe!");
                return "redirect:/cadastro";
            }

            // Salva o usuário (senha será criptografada automaticamente no UserService)
            User savedUser = userService.save(user);

            // Faz login automático após cadastro
            UserDetails userDetails = userService.loadUserByUsername(savedUser.getUsername());
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            
            // Salva o contexto de segurança na sessão
            request.getSession().setAttribute(
                    HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, 
                    securityContext);

            redirectAttributes.addFlashAttribute("success", 
                    "Cadastro realizado com sucesso! Bem-vindo, " + savedUser.getUsername());
            
            return "redirect:/projects";
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                    "Erro ao criar conta: " + e.getMessage());
            return "redirect:/cadastro";
        }
    }

    /**
     * Redireciona a raiz para a listagem principal, protegida por autenticação.
     */
    @GetMapping("/")
    public String index() {
        return "redirect:/projects";
    }
}