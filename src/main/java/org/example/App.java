package org.example;

import org.example.dao.MovieDAO;
import org.example.services.MovieDAOImpl;

import java.sql.*;

public class App
{
    static final String DB_URL = "jdbc:mariadb://localhost:3306/jdbcmoviedemo?createDatabaseIfNotExist=true";
    static final String USER = "root";
    static final String PASS = "root";
    public static void main( String[] args )throws ClassNotFoundException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            MovieDAO m = new MovieDAOImpl(conn);
            m.createTable();
            m.createMovie();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
