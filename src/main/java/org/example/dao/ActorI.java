package org.example.dao;

import org.example.models.Actor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActorI {
    Actor save(Actor a);
    Optional<Actor> findById(UUID id);
    List<Actor> findAllBornAfter(int year);
    List<Actor> findAllWithLastNameEndsWith(String lastNameEndsWith);

}
