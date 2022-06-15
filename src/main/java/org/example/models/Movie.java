package org.example.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "actors")
@ToString(exclude = "actors")
public class Movie {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    private Genre genre;

    @Column(name = "year_of_release")
    private int yearOfRelease;

    @ManyToMany(mappedBy = "movies")
    private List<Actor> actors = new ArrayList<>();
}
