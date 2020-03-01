package com.soyeyo.movies.repositories;

import com.soyeyo.movies.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}
