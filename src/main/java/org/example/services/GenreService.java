package org.example.services;

import org.example.dao.GenreI;
import org.example.models.Genre;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GenreService implements GenreI {
    @Override
    public Genre createGenre(Genre g) {
        return null;
    }

    @Override
    public boolean deleteGenre(Genre g) {
        return false;
    }

    @Override
    public List<Genre> findAllByName(String name) {
        return null;
    }

    @Override
    public Optional<Genre> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<Genre> findAll() {
        return null;
    }
}
