/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jojo.entity;

import java.time.Instant;
import java.sql.Date;

/**
 *
 * @author 1672012
 */
public class Peminjaman {
    private int id;
    private User user;
    private Book book;
    private String Task;
    private Date date;

    public Peminjaman(int id, User user, Book book, String Task, Date date) {
        this.id = id;
        this.user = new User();
        this.book = new Book();
        this.Task = Task;
        this.date = date;
    }


    public Peminjaman() {
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getTask() {
        return Task;
    }

    public void setTask(String Task) {
        this.Task = Task;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


  
    
    
            
}
