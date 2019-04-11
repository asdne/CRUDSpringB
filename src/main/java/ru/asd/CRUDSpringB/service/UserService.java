package ru.asd.CRUDSpringB.service;

import org.springframework.stereotype.Service;
import ru.asd.CRUDSpringB.entity.User;

import java.util.List;
import java.util.Set;

@Service
public interface UserService {
    User getUserByLogin(String login);
    List<User> getAll();

    void deleteUserById(Long id);

    void addUser(User user, Set<String> roles);

    User getUserById(Long id);

    User updateUser(User user,Set<String> roles);
}
