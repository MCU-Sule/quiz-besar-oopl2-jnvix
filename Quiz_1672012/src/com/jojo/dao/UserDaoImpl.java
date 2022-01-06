/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jojo.dao;

import com.jojo.entity.Book;
import com.jojo.entity.Peminjaman;
import com.jojo.entity.User;
import com.jojo.util.DaoService;
import com.jojo.util.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 1672012
 */
public class UserDaoImpl implements DaoService<User> {

    @Override
    public List<User> fetchAllList() throws SQLException, ClassNotFoundException {
        List<User> userList = new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT * from user";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        User user = new User();
                        user.setId(rs.getInt("id"));
                        user.setName(rs.getString("name"));
                        user.setPassword(rs.getString("password"));
                        user.setPosition(rs.getString("position"));
                        user.setUsername(rs.getString("username"));
                        userList.add(user);
                    }
                }
            }
        }
        return userList;
    }

    public User fetchUser(User user) throws ClassNotFoundException, SQLException {
        User result;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT * from user where username = ? and password = md5(?) ";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                try (ResultSet rs = ps.executeQuery()) {
                    result = new User();
                    while (rs.next()) {

                        result.setId(rs.getInt("id"));
                        result.setName(rs.getString("name"));
                        result.setPassword(rs.getString("password"));
                        result.setPosition(rs.getString("position"));
                        result.setUsername(rs.getString("username"));
                    }
                    return result;
                }
            }
        }
    }

    @Override
    public int addData(User t) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "INSERT INTO user (name,username, password, position) VALUES(?,?,md5(?),?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, t.getName());
                ps.setString(2, t.getUsername());
                ps.setString(3, t.getPassword());
                ps.setString(4, t.getPosition());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }

    @Override
    public int deleteData(User t) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "DELETE FROM user WHERE  id=?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, t.getId());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }

    @Override
    public int updateData(User t) throws ClassNotFoundException, SQLException {
int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "UPDATE user SET name=?,username=?, password=?, position=? WHERE id=?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, t.getName());
                ps.setString(2, t.getUsername());
                ps.setString(3, t.getPassword());
                ps.setString(4,t.getPosition());
                ps.setInt(5,t.getId());
                
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;    }

}
