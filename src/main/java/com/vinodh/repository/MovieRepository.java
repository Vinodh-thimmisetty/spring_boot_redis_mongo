package com.vinodh.repository;

import com.vinodh.documents.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author thimmv
 * @createdAt 23-06-2019 15:43
 */
@Repository
public interface MovieRepository extends MongoRepository<Movie, Long> {
    List<Movie> findByTitle(String name);

}
