package com.mmashyr.staffmanager.model;

import org.hibernate.validator.constraints.NotEmpty;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mark on 11.11.2016.
 */
@Entity
@Table(name = "users")
public class User extends BaseModel {

    @NotEmpty
    @NotNull
    @Column(name = "login", unique = true)
    private String login;

    @NotEmpty
    @NotNull
    @Column(name = "password")
    private String password;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<Role> roles = new HashSet<Role>();

    public User() {
        super();
    }

   public User(String login, String password){
       this.login = login;
       this.password = password;
   }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
