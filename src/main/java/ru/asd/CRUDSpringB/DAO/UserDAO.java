package ru.asd.CRUDSpringB.DAO;

import org.springframework.security.core.userdetails.UserDetails;
import ru.asd.CRUDSpringB.entity.User;
import ru.asd.CRUDSpringB.entity.UserRole;

import java.util.List;
import java.util.Set;

public interface UserDAO {
    void addUser(User user);

    UserDetails loadUserByUsername(String login);

    User createUserIfNotFound(final String login, final String password, final Set<UserRole> roles);

    List<User> listAll();

    void deleteUserById(Long id);

    User getUserById(Long id);

    User updateUser(User user);
}
