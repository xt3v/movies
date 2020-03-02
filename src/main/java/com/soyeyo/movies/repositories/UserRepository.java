package com.soyeyo.movies.repositories;

import com.soyeyo.movies.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
  User findByUsername(String username);
}
