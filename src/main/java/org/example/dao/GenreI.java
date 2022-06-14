package org.example.dao;

import org.example.models.Genre;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenreI {
    Genre createGenre(Genre g);
    boolean deleteGenre(Genre g);
    List<Genre> findAllByName(String name);
    Optional<Genre> findById(UUID id);
    List<Genre> findAll();

}
