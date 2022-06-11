package org.example.services;

import org.example.DatabaseActionException;
import org.example.dao.MovieDAO;
import org.example.models.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieDAOImpl implements MovieDAO {
    private final Connection conn;

    public MovieDAOImpl(final Connection conn) {
        this.conn= conn;
    }
    @Override
    public void createTable() {
        try (Statement CreateS = conn.createStatement()) {
            String sql = "create table if not exists MOVIES (" +
                    "                          id int auto_increment primary key," +
                    "                          title varchar(255) not null," +
                    "                          genre varchar(255) not null," +
                    "                          yearOfRelease INTEGER" +
                    ")";
            CreateS.execute(sql);
        } catch (SQLException e) {
            throw new DatabaseActionException(e);
        }
    }

    @Override
    public void deleteTable() {
        try (Statement deleteS = conn.createStatement()) {
            deleteS.executeUpdate("DROP TABLE IF EXISTS MOVIES");
        } catch (SQLException e) {
            throw new DatabaseActionException(e);
        }

    }

    @Override
    public void createMovie(Movie movie) {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO MOVIES (title, genre, yearOfRelease) VALUES (?, ?, ?)")) {
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getGenre());
            ps.setInt(3, movie.getYearOfRelease());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseActionException(e);
        }

    }

    @Override
    public void deleteMovie(int id) {
        try (PreparedStatement deS = conn.prepareStatement("DELETE FROM MOVIES WHERE id = ?")) {
            deS.setInt(1, id);
            deS.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseActionException(e);
        }

    }

    @Override
    public void updateMoviesTitle(int id, String newTitle) {
        try (PreparedStatement upS = conn.prepareStatement("UPDATE MOVIES SET title = ? WHERE id = ?")) {
            upS.setString(1, newTitle);
            upS.setInt(2, id);
            upS.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseActionException(e);
        }

    }

    @Override
    public Optional<Movie> findMovieById(int id) {
        try (PreparedStatement findS = conn.prepareStatement("SELECT * FROM MOVIES WHERE id = ?")) {
            findS.setInt(1, id);
            boolean findRs = findS.execute();
            if (findRs){
                ResultSet found = findS.getResultSet();
                if (found.next())
                    return Optional.of(new Movie(found.getInt(1),found.getString(2),found.getString(3), found.getInt(4)));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DatabaseActionException(e);
        }
    }

    @Override
    public List<Movie> findAll() {
        try (Statement moviesS = conn.createStatement()) {
            ResultSet rs = moviesS.executeQuery("SELECT * FROM MOVIES");
            List<Movie> movies = new ArrayList<>();
            while (rs.next()){
                Movie e = new Movie(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getInt(4));
                movies.add(e);
            }
            return movies;
        } catch (SQLException e) {
            throw new DatabaseActionException(e);
        }

    }
}
