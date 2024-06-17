package com.movie.repository;

import com.movie.entity.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie,Integer> {
    public Movie findByName(String name);
}
