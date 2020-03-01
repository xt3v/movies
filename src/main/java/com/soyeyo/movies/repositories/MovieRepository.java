package com.soyeyo.movies.repositories;

import com.soyeyo.movies.models.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie,Long> {
}
