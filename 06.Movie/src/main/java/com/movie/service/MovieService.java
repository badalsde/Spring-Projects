package com.movie.service;

import com.movie.dto.MovieDTO;
import com.movie.exception.MovieException;

import java.util.List;

public interface MovieService {
    public Integer addMovie(MovieDTO movieDTO) throws MovieException;
    public List<MovieDTO> getAllMovies() throws MovieException;
    public Integer updateMovieRating(Integer movieId, Float rating) throws MovieException;
    public Integer deleteMovie(Integer movieId) throws MovieException;
}
