package com.example.shopping.entity;

import com.sun.javafx.geom.transform.Identity;

import javax.persistence.*;

/**
 * Author: HuangHaoce
 * Create at: 2018/9/4
 **/
@Entity
@Table(name="sys_role")
public class SysRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
