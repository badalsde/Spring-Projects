package com.movie.api;

import com.movie.dto.MovieDTO;
import com.movie.exception.MovieException;
import com.movie.service.MovieServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@Validated
public class MovieAPI {
    @Autowired
    private Environment environment;
    @Autowired
    private MovieServiceImpl movieService;

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<String> addMovie(@Valid @RequestBody MovieDTO movieDTO) throws MovieException {
        Integer movieId = movieService.addMovie(movieDTO);
        String successMessage = environment.getProperty("API.CREATION_SUCCESS")+ movieId;
        return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies() throws MovieException {
        List<MovieDTO> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateMovieRating(@Valid @RequestBody MovieDTO movieDTO) throws MovieException {
        Integer movieIdUpdated = movieService.updateMovieRating(movieDTO.getMovieId(), movieDTO.getRating());
        String successMessage = environment.getProperty("API.UPDATE_SUCCESS")+": "+movieIdUpdated;
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable("movieId") Integer movieId) throws MovieException {
        Integer movieID=movieService.deleteMovie(movieId);
        String successMessage = environment.getProperty("API.DELETE_SUCCESS")+ movieID;
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }
}
