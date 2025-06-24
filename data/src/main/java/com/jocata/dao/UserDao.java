package com.jocata.dao;

import com.jocata.entity.User;
import com.jocata.entity.UserRole;

public interface UserDao {
    void persistUserAndRole(User user, UserRole userRole);

    boolean usernameExists(String username);

    User login(String username, String password);
}
