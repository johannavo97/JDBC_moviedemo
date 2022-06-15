package org.example;

import lombok.extern.java.Log;
import org.example.models.Actor;
import org.example.models.Genre;
import org.example.models.Movie;
import org.example.services.ActorService;
import org.example.services.GenreService;
import org.example.services.MovieService;

import java.sql.*;
import java.util.List;

@Log
public class App
{
    public static void main(String[] args) {
        ActorService actorService = new ActorService();
        GenreService genreService = new GenreService();
        MovieService movieService = new MovieService();

        Actor actor1 = actorService.save(new Actor("Betty", "White", 1922));
        actorService.save(new Actor("Tia", "Carre", 1967));
        actorService.save(new Actor("Liza", "Minnelli", 1946));

        Genre genre1 = genreService.createGenre(new Genre(null,"Action", null));
        Movie m1 = new Movie(null,"Predator", genre1, 1980, List.of(actor1));


        log.info("Size Actors: " + actorService.findAllBornAfter(1922).toString());
        log.info("Information of Predator" + movieService.findAllByTitle("Predator").toString());
    }
}
