package org.example._002.entities;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "Users")
public class Users {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    public Users() {
    }

    ;

    public Users(String username, String email, String password, Role role) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
