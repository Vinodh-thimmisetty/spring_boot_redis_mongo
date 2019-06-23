package com.vinodh.service;

import com.vinodh.documents.Movie;

import java.util.List;

/**
 * @author thimmv
 * @createdAt 23-06-2019 15:42
 */
public interface MovieService {

    List<Movie> findAll();

    Movie findById(Long id);

    List<Movie> findByTitle(String name);

    Movie save(Movie movie);

    Movie update(Movie movie);

    void deleteById(Long id);

    void deleteAll();
}
