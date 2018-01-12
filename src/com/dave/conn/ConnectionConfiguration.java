/*
name could be also "ConnectionConfiguration"
 */
package com.dave.conn;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConfiguration {
    public static final String URL = "jdbc:mysql://localhost:8080/test_1";
    public static final String USER = "root";
    public static final String PASS = "";

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
}
