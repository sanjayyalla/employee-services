package com.jocata.service.impl;

import com.jocata.dao.UserDao;
import com.jocata.entity.User;
import com.jocata.entity.UserRole;
import com.jocata.form.LoginForm;
import com.jocata.form.RegisterForm;
import com.jocata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public String register(RegisterForm form) {
        if (userDao.usernameExists(form.getUsername())) {
            return "Username already exists";
        }
        User user = new User();
        user.setUsername(form.getUsername());
        user.setPassword(form.getPassword());

        UserRole userRole = new UserRole();
        userRole.setRole(form.getRole().toUpperCase());

        userDao.persistUserAndRole(user, userRole);

        return "User registered successfully";
    }

    @Override
    public String login(LoginForm loginForm) {

        User user = userDao.login(loginForm.getUsername(), loginForm.getPassword());
        if (user == null) {
            return "Invalid username or password";
        }

        return "Login successful. Welcome, " + user.getUsername() + "!";
    }
}

