package com.luis.pcstore.controller;

import com.luis.pcstore.dto.UserDto;
import com.luis.pcstore.dto.UserProfileDto;
import com.luis.pcstore.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    private final AuthService authService;

    @Autowired
    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/register")
    public String registerUser(Model model){
        UserDto userInfo = new UserDto();
        model.addAttribute("userInfo", userInfo);
        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(@Valid @ModelAttribute UserDto userInfo, RedirectAttributes redirectAttributes){
        authService.register(userInfo);
        redirectAttributes.addFlashAttribute("successMessage", "User successfully register!");
        return "redirect:/";
    }

   @GetMapping("/login")
    public String authenticate(Model model) {
       String email = "";
       String password = "";
        model.addAttribute("email",email);
        model.addAttribute("password",password);
       return "users/login";
    }

    @PostMapping("/login")
    public String authenticate(@RequestParam String email, @RequestParam String password, Model model) {
        try {
            UserProfileDto userProfile = authService.authenticateUser(email, password);
            model.addAttribute("userProfile", userProfile);
            return "users/profile"; // Redirige a la vista del perfil del usuario
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            model.addAttribute("error", "Invalid email or password.");
            return "users/login";
        }
    }

}

