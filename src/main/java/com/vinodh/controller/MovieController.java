package com.vinodh.controller;

import com.bol.crypt.CryptVault;
import com.vinodh.documents.Movie;
import com.vinodh.service.MovieService;
import com.vinodh.service.NextSequenceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author thimmv
 * @createdAt 23-06-2019 15:41
 */
@RestController
@RequestMapping("/movie")
@Api(value = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Movie information.")
@Slf4j
public class MovieController {


    @Autowired
    private MovieService movieService;

    @Autowired
    private CryptVault cryptVault;

    @Autowired
    private NextSequenceService nextSequenceService;

    @GetMapping("/findAll")
    @ApiOperation(value = "Find All available movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/findById")
    @ApiOperation(value = "Find Movie based on ID")
    public ResponseEntity<Movie> findById(@ApiParam(value = "Find Movie based on ID.", required = true) @RequestParam Long id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    @GetMapping("/findByTitle")
    @ApiOperation(value = "Find Movie based on NAME")
    public ResponseEntity<List<Movie>> findByTitle(@ApiParam(value = "Find Movie based on Name.", required = true) @RequestParam String title) {
        return ResponseEntity.ok(movieService.findByTitle(title));
    }

    @PostMapping("/save")
    @ApiOperation(value = "Save new Movie details")
    public ResponseEntity<Movie> save(@ApiParam(value = "Movie information to be created.", required = true) @RequestBody Movie movie) {
        if (movie.getId() == null) movie.setId(nextSequenceService.getNextSequence());
        return ResponseEntity.ok(movieService.save(movie));
    }

    @PutMapping("/update")
    @ApiOperation(value = "Update Movie details")
    public ResponseEntity<Movie> update(@ApiParam(value = "Movie information to be updated.", required = true) @RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.update(movie));
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "Delete Movie details")
    public void deleteUserByID(@ApiParam(value = "Delete Movie based on ID.", required = true) @RequestParam Long id) {
        movieService.deleteById(id);
    }

    @DeleteMapping("/deleteAll")
    @ApiOperation(value = "Delete All Movie details")
    public void deleteAll() {
        movieService.deleteAll();
    }

}
