package com.example.shopping.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_main")
public class User {

    @Id
    @Column(name = "Username")
    private String username;

    @Column(name = "Email")
    private String email;

    @Column(name = "Role")
    private int role;

    @Column(name = "Password")
    private String password;

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
