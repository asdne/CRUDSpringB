package ru.asd.CRUDSpringB.DAO;

import ru.asd.CRUDSpringB.entity.UserRole;

public interface UserRoleDAO {
    UserRole getRolebyId(Long id);

    UserRole getRoleByName(String roleName);

    UserRole createRoleIfNotFound(final String name);
}
