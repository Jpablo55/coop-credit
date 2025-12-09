package com.credit_application_service.domain.model;

import com.credit_application_service.domain.model.enums.Role;

import java.util.Set;

public class User {

    private Long id;
    private String username;
    private String password;
    private Set<Role> roles;

    public User() {}

    public User(Long id, String username, String password, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Set<Role> getRoles() { return roles; }

    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }

    public boolean hasRole(Role role) {
        return this.roles != null && this.roles.contains(role);
    }
}
