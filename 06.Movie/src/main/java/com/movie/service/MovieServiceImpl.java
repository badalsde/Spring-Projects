package com.movie.service;

import com.movie.dto.MovieDTO;
import com.movie.entity.Movie;
import com.movie.exception.MovieException;
import com.movie.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service("movieService")
@Transactional
public class MovieServiceImpl implements MovieService{
    @Autowired
    private Environment environment;
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Integer addMovie(MovieDTO movieDTO) throws MovieException {
        Movie movies = movieRepository.findByName(movieDTO.getName());
        if(movies!=null){
            throw new MovieException("Service.MOVIE_ALREADY_EXISTS");
        }
        Movie movie = new Movie();
        movie.setName(movieDTO.getName());
        movie.setRating(movieDTO.getRating());
        movie= movieRepository.save(movie);
        return movie.getMovieId();
    }

    @Override
    public List<MovieDTO> getAllMovies() throws MovieException {
        List<Movie> movies = (List<Movie>) movieRepository.findAll();
        if(movies.isEmpty()){
            throw new MovieException("Service.NO_MOVIE_FOUND");
        }
        List<MovieDTO> movieDTOS=new ArrayList<>();
        for(Movie movie:movies){
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setMovieId(movie.getMovieId());
            movieDTO.setName(movie.getName());
            movieDTO.setRating(movie.getRating());
            movieDTOS.add(movieDTO);
        }
        return movieDTOS;
    }

    @Override
    public Integer updateMovieRating(Integer movieId, Float rating) throws MovieException {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        Movie movie = optionalMovie.orElseThrow(()->new MovieException("Service.MOVIE_NOT_FOUND"));
        movie.setRating(rating);
        movieRepository.save(movie);
        return movieId;
    }

    @Override
    public Integer deleteMovie(Integer movieId) throws MovieException {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        Movie movie = optionalMovie.orElseThrow(()->new MovieException("Service.MOVIE_NOT_FOUND"));
        movieRepository.delete(movie);
        return movieId;
    }
}
