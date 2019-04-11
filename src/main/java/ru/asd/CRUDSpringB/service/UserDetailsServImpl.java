package ru.asd.CRUDSpringB.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.asd.CRUDSpringB.DAO.UserDAO;
import ru.asd.CRUDSpringB.entity.User;

@Service
public class UserDetailsServImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails userDetails= userDAO.loadUserByUsername(s);
        User user1= userService.getUserByLogin("fsd");
     /*   Set<GrantedAuthority> roles = new HashSet<>();
        roles.add((new SimpleGrantedAuthority"ROLE_USER"));
        UserDetails userDetails= new org.springframework.security.core.userdetails.User(user1.getLogin(),user1.getPassword(),roles);*/
        return userDetails;
    }
}
