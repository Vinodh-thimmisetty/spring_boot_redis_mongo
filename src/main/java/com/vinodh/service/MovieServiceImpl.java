package com.vinodh.service;

import com.bol.crypt.CryptVault;
import com.vinodh.documents.Movie;
import com.vinodh.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * @author thimmv
 * @createdAt 23-06-2019 15:43
 */
@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CryptVault cryptVault;

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    @Cacheable(value = "Movie_Cache", key = "#id")
    public Movie findById(Long id) {
        return movieRepository.findById(id).get();
    }

    @Override
    @CachePut(value = "Movie_Cache", key = "#title")
    public List<Movie> findByTitle(String title) {
        byte[] encrypt = cryptVault.encrypt(title.getBytes());
        List<Movie> movies = mongoTemplate.find(query(where("title : " + encrypt)), Movie.class);
        return movieRepository.findByTitle(title);
    }

    @Override
    @CachePut(value = "Movie_Cache", key = "#movie.id")
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    @CachePut(value = "Movie_Cache", key = "#movie.id")
    public Movie update(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    @CacheEvict(value = "Movie_Cache", key = "#movie.id")
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    @CacheEvict(value = "Movie_Cache", allEntries = false)
    public void deleteAll() {
        movieRepository.deleteAll();
    }
}
