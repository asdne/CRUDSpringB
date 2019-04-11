package ru.asd.CRUDSpringB.entity;

import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles", schema = "javalearning")
public class UserRole implements GrantedAuthority {

    @Id
    @GeneratedValue(generator = "roleseq")
    private Long id;

    @NaturalId
    @Column(name = "name")
    private String role;

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @ManyToMany (fetch = FetchType.EAGER,mappedBy = "roles")
    private Set<User> users;

    public Long getId() {
        return id;
    }

    public UserRole(String role) {
        this.role = role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserRole() {
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}
