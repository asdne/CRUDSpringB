package ru.asd.CRUDSpringB.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.asd.CRUDSpringB.DAO.UserDAO;
import ru.asd.CRUDSpringB.DAO.UserRoleDAO;
import ru.asd.CRUDSpringB.entity.UserRole;

import java.util.HashSet;
import java.util.Set;

@Component
public class DbInitializer implements CommandLineRunner {
    @Autowired
    private UserRoleDAO userRoleDAO;
    @Autowired
    private UserDAO userDAO;

    @Override
    public void run(String... args) throws Exception {
        userRoleDAO.createRoleIfNotFound("ROLE_ADMIN");
        userRoleDAO.createRoleIfNotFound("ROLE_USER");
        UserRole ur = userRoleDAO.getRoleByName("ROLE_ADMIN");
        Set<UserRole> urs = new HashSet<>();
        urs.add(ur);
        userDAO.createUserIfNotFound("admin", "password", urs);
    }
}
