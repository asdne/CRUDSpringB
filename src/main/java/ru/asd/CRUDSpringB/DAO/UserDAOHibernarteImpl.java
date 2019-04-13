package ru.asd.CRUDSpringB.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.asd.CRUDSpringB.entity.User;
import ru.asd.CRUDSpringB.entity.UserRole;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class UserDAOHibernarteImpl implements UserDAO, UserDetailsService {
    @PersistenceContext
    private EntityManager eMrF;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void addUser(ru.asd.CRUDSpringB.entity.User user) {
        eMrF.persist(user);
    }

    private User getUserByLogin(String login) {
        Query query = eMrF.createQuery("FROM User where login=:n");
        query.setParameter("n", login);
        List<User> user = query.getResultList();
        return user.get(0);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        System.out.println("Getting access details from User dao !!");
        User testUser = getUserByLogin(login);
        if (testUser != null) {

            UserDetails user = new org.springframework.security.core.userdetails.User(testUser.getLogin(), testUser.getPassword(), true, true, true, true, testUser.getRoles());
            return user;
        }
        //    User user = eMrF.find(User.class, login);
        //    System.out.println(user);
        // Ideally it should be fetched from database and populated instance of
        // #org.springframework.security.core.userdetails.User should be returned from this method
        //    UserDetails user = new User(login, "password", true, true, true, true, new GrantedAuthority[]{ new GrantedAuthorityImpl("ROLE_USER") });
        //      return user;
        return null;
    }

    @Transactional
    public User createUserIfNotFound(final String login, final String password, final Set<UserRole> roles) {
        Query query = eMrF.createQuery("FROM User where login=:n");
        query.setParameter("n", login);
        List<User> user = query.getResultList();
        if (user.isEmpty()) {
            User nUser = new User();
            nUser.setLogin(login);
            nUser.setPassword(passwordEncoder.encode(password));
            nUser.setRoles(roles);
            eMrF.persist(nUser);
            return nUser;
        }


        return null;
    }

    @Override
    public void deleteUserById(Long id) {
        User userToDelite = getUserById(id);
        eMrF.remove(userToDelite);
    }

    @Override
    public List<User> listAll() {
        return eMrF.createQuery("from User").getResultList();
    }


    @Override
    public User getUserById(Long id) {
        return eMrF.find(User.class, id);
    }

    @Override
    public User updateUser(User user) {
        User userForUpdate = getUserById(user.getId());
        userForUpdate.setLogin(user.getLogin());
        userForUpdate.setPassword(user.getPassword());
        userForUpdate.setRoles(user.getRoles());
        return userForUpdate;
    }
}