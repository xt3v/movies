package com.soyeyo.movies.services;

import com.soyeyo.movies.models.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    User findByEmail(String email);
}
