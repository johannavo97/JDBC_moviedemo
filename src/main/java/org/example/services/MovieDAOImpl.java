package org.example.services;

import org.example.dao.MovieDAO;
import org.example.models.Movie;

import java.util.List;
import java.util.Optional;

public class MovieDAOImpl implements MovieDAO {
    @Override
    public void createTable() {

    }

    @Override
    public void deleteTable() {

    }

    @Override
    public void createMovie(Movie movie) {

    }

    @Override
    public void deleteMovie(int id) {

    }

    @Override
    public void updateMoviesTitle(int id, String newTitle) {

    }

    @Override
    public Optional<Movie> findMovieById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Movie> findAll() {
        return null;
    }
}
