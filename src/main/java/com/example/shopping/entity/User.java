package com.example.shopping.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user_main")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Username")
    private String username;

    @Column(name = "Email")
    private String email;

    @Column(name = "Role")
    private int role;

    @Column(name = "Sex")
    private String sex;

    @Column(name = "Password")
    private String password;

    @Column(name = "RegisterTime")
    private Date registerTime;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<ShoppingCar> shoppingCarSet;

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

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Set<ShoppingCar> getShoppingCarSet() {
        return shoppingCarSet;
    }

    public void setShoppingCarSet(Set<ShoppingCar> shoppingCarSet) {
        this.shoppingCarSet = shoppingCarSet;
    }
}
