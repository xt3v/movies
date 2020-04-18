package com.soyeyo.movies.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter @Setter @NoArgsConstructor
@Entity
public class User {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private String password;
     private String username;

     @Transient
     private String passwordConfirm;

     @ManyToMany
     private Set<Role> roles;

    public User(Long id, String password, String username) {
        this.id = id;
        this.password = password;
        this.username = username;
    }
}
