package org.example.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity(name ="genres")
@Data
@EqualsAndHashCode(exclude = "movies")
@ToString(exclude = "movies")
public class Genre {
    @Id
    @GeneratedValue
    private UUID id;

    @NonNull
    private String name;


    @OneToMany
    @JoinColumn(name = "genre_id")
    private List<Movie> movies;
}