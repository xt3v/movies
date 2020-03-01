package com.soyeyo.movies.repositories;

import com.soyeyo.movies.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
