package com.jocata.dao.impl;

import com.jocata.dao.UserDao;
import com.jocata.entity.User;
import com.jocata.entity.UserRole;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void persistUserAndRole(User user, UserRole userRole) {
        entityManager.persist(user);
        userRole.setId(user.getId());
        userRole.setUser(user);
        entityManager.persist(userRole);

    }

    @Override
    public boolean usernameExists(String username) {
        Long count = entityManager.createQuery(
                        "SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class)
                .setParameter("username", username)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public User login(String username, String password) {
        try {
            return entityManager.createQuery(
                            "SELECT u FROM User u WHERE u.username = :username AND u.password = :password",
                            User.class
                    ).setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
