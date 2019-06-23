package com.vinodh.documents;

import com.bol.secure.Encrypted;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author thimmv
 * @createdAt 23-06-2019 15:42
 */
@Document("movies")
@NoArgsConstructor
@Setter
@Getter
public class Movie {

    @Id
    private Long id;

    @Encrypted
    private String title;

    private int year, runtime;

    private LocalDateTime released;

    @Encrypted
    private String poster, plot;

    private Set<String> cast = new HashSet<>();
    private Set<String> directors = new HashSet<>();
    private Set<String> countries = new HashSet<>();
    private Set<String> genres = new HashSet<>();

    private ImdbRating imdb;

}
