package ru.asd.CRUDSpringB.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.asd.CRUDSpringB.DAO.UserDAO;
import ru.asd.CRUDSpringB.DAO.UserRoleDAO;
import ru.asd.CRUDSpringB.entity.User;
import ru.asd.CRUDSpringB.entity.UserRole;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceIml implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAll() {
        return userDAO.listAll();
    }


    @Override
    public void deleteUserById(Long id) {
        userDAO.deleteUserById(id);
    }

    @Override
    public void addUser(User user, Set<String> roles) {
        Set<UserRole> urs = new HashSet<>();
        for (String role : roles
        ) {
            urs.add(userRoleDAO.getRoleByName(role));
        }
        user.setRoles(urs);
        userDAO.addUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User updateUser(User user,Set<String> roles) {
        Set<UserRole> urs = new HashSet<>();
        for (String role : roles
        ) {
            urs.add(userRoleDAO.getRoleByName(role));
        }
        user.setRoles(urs);
        return userDAO.updateUser(user);
    }

    @Override
    public User getUserByLogin(String login) {
        userRoleDAO.createRoleIfNotFound("ROLE_ADMIN");
        UserRole ur = userRoleDAO.getRolebyId(2L);
        Set<UserRole> urs = new HashSet<>();
        urs.add(ur);
        userDAO.createUserIfNotFound("admin", "password", urs);
        return null;
    }
}