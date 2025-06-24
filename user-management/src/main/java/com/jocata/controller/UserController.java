package com.jocata.controller;

import com.jocata.form.LoginForm;
import com.jocata.form.RegisterForm;
import com.jocata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterForm form) {
        if (form.getUsername() == null || form.getUsername().isEmpty() || form.getPassword() == null || form.getPassword().isEmpty() || form.getRole() == null || form.getRole().isEmpty()) {
            return "Username, password, and role are required";
        }
        return userService.register(form);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginForm loginForm) {
        if (loginForm.getUsername() == null || loginForm.getPassword() == null) {
            return "Username and password are required";
        }
        return userService.login(loginForm);
    }

}
