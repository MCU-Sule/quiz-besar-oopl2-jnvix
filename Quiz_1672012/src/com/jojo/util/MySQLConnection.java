package com.jojo.util;

import java.sql.*;

public class MySQLConnection {
    private static final String URL="jdbc:mysql://localhost:3306/kuis1pbo2";
    private static final String USERNAME="root";
    private static final String PASSWORD="";

    public   static Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        connection.setAutoCommit(false);
        return connection;
    }

}
