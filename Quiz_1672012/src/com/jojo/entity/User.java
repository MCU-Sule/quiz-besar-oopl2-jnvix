/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jojo.entity;

/**
 *
 * @author 1672012
 */
public class User {
    private int id;
    private String name;
    private String username;
    private String password;
    private String position;

    
    public User(int id, String name, String username, String password, String position) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.position = position;
    }

    public User() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return  name ;
    }
    

}
