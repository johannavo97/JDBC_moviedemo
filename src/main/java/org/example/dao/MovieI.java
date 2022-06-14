package org.example.dao;

import org.example.models.Movie;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieI {
    Movie createMovie(Movie m);
    boolean deleteMovie(Movie m);
    List<Movie> findAllByTitle(String title);
    Optional<Movie> findById(UUID id);
    List<Movie> findAll();
    List<Movie> findAllWithActors();
}
