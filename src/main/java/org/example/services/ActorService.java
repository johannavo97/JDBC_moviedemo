package org.example.services;

import org.example.dao.ActorI;
import org.example.models.Actor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ActorService implements ActorI {
    @Override
    public Actor save(Actor a) {
        return null;
    }

    @Override
    public Optional<Actor> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<Actor> findAllBornAfter(int year) {
        return null;
    }

    @Override
    public List<Actor> findAllWithLastNameEndsWith(String lastNameEndsWith) {
        return null;
    }
}
