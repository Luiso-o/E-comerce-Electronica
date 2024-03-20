package com.luis.pcstore.controller;

import com.luis.pcstore.dto.UserDto;
import com.luis.pcstore.dto.UserProfileDto;
import com.luis.pcstore.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerUser(Model model){
        UserDto userInfo = new UserDto();
        model.addAttribute("userInfo", userInfo);
        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(@Valid @ModelAttribute UserDto userInfo, RedirectAttributes redirectAttributes){
        userService.register(userInfo);
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
        Optional<UserProfileDto> userProfile = userService.authenticateUser(email, password);

        if (userProfile.isPresent()) {
            model.addAttribute("userProfile", userProfile.get());
            return "users/profile";
        } else {
            model.addAttribute("error", "Invalid email or password.");
            return "users/login";
        }
    }

}

