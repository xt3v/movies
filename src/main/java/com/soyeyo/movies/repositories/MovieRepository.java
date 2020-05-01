package com.soyeyo.movies.repositories;
import com.soyeyo.movies.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    List<Movie> findByTitleContainingIgnoreCase(String title);
}
