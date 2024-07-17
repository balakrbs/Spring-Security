package com.onlinebus.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.onlinebus.Model.Users;
import com.onlinebus.Service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class AuthController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String forward(Model model) {
        return "login"; // Ensure "login" is the correct Thymeleaf template name
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "login"; // Ensure "login" is the correct Thymeleaf template name
    }

    @PostMapping("/login")
    public String login() {
        // Implement your login logic using Spring Security mechanisms or custom logic
        System.out.println("Login request received"); // Check if this message is logged
        return "redirect:/home"; // Redirect to home page after successful login
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute Users user) {
        userService.save(user);
        return "redirect:/home"; // Redirect to home page after successful registration
    }

    @GetMapping("/home")
    public String home(Model model) {
        // Retrieve authenticated user's username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // This will return the username

        // Add username to the model
        model.addAttribute("username", username);

        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout"; // Redirect to login page after logout
    }
}
