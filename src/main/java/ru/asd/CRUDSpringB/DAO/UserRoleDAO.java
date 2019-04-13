package ru.asd.CRUDSpringB.DAO;

import org.springframework.stereotype.Repository;
import ru.asd.CRUDSpringB.entity.UserRole;

@Repository
public interface UserRoleDAO {
    UserRole getRolebyId(Long id);

    UserRole getRoleByName(String roleName);

    UserRole createRoleIfNotFound(String name);
}
