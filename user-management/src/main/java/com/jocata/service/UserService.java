package com.jocata.service;

import com.jocata.form.LoginForm;
import com.jocata.form.RegisterForm;

public interface UserService {

    String register(RegisterForm form);

    String login(LoginForm loginForm);
}
