/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jojo.dao;
import com.jojo.entity.Book;
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
public class BookDaoImpl
        implements DaoService<Book> {

    @Override
    public List<Book> fetchAllList() throws SQLException, ClassNotFoundException {
        List<Book> booklists = new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT * from laboratorium ";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {

                        Book book = new Book();
                        book.setId(rs.getInt("id"));
                        book.setName(rs.getString("name"));
                        book.setCapacity(rs.getInt("capacity"));
                        booklists.add(book);
                    }
                }
            }
        }
        return booklists;
    }

    @Override
    public int addData(Book t) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "INSERT INTO laboratorium(name,capacity) VALUES(?,?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, t.getName());
                ps.setInt(2, t.getCapacity());
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
    public int deleteData(Book t) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "DELETE FROM laboratorium WHERE  id=?";
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
    public int updateData(Book t) throws ClassNotFoundException, SQLException {
      int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "UPDATE laboratorium SET name=?, capacity =? WHERE id=?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, t.getName());
                ps.setInt(2, t.getCapacity());
                ps.setInt(3,t.getId());
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

}
