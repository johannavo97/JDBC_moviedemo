package org.example;

import org.example.dao.MovieDAO;
import org.example.models.Movie;
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
            m.createMovie(new Movie("Memory", "Action'", 2022));
            m.createMovie(new Movie("Minions", "Comedy'", 2015));
            m.createMovie(new Movie("Men", "Horror", 2022));
            m.findMovieById(2).ifPresent(System.out::println);
            m.findAll().forEach(System.out::println);
            m.updateMoviesTitle(2, "Help");
            m.findAll().forEach(System.out::println);
            m.deleteMovie(3);
            System.out.println(m.findAll().size()); // one row left in the table
            m.deleteTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
