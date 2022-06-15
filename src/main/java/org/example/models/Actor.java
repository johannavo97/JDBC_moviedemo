package org.example.models;

import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity(name = "actors")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "movies")
@ToString(exclude = "movies")
public class Actor {
    @Id
    @GeneratedValue
    private UUID id;

    @NonNull
    private String name;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @ManyToMany
    @JoinTable(name = "actors_to_movies",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id"))
    private List<Movie> movies = new ArrayList<>();
}
