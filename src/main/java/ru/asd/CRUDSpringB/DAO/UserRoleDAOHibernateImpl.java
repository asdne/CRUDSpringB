package ru.asd.CRUDSpringB.DAO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.asd.CRUDSpringB.entity.UserRole;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserRoleDAOHibernateImpl implements UserRoleDAO{

    @PersistenceContext
    private EntityManager eMrF;

    @Override
    public UserRole getRolebyId(Long id) {
        return eMrF.find(UserRole.class,id);
    }

    @Override
    public UserRole getRoleByName(String roleName) {
        Query query = eMrF.createQuery("FROM UserRole where role=:n");
        query.setParameter("n", roleName);
        List<UserRole> role = query.getResultList();
        return role.get(0);
    }

    public UserRole createRoleIfNotFound(String name) {

        Query query = eMrF.createQuery("from UserRole where name=:n");
        query.setParameter("n",name);
        List <UserRole> role = query.getResultList();
        if (role.isEmpty()) {
            UserRole newrole = new UserRole(name);
            eMrF.persist(newrole);
            eMrF.flush();
            return newrole;
        }
       return null;
    }
}
