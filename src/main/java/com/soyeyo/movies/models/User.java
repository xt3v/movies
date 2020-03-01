package com.soyeyo.movies.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter @Setter @NoArgsConstructor
@Entity
public class User {

     @Id
     private int id;
     private String password;
     private String username;

    public User(int id, String password, String username) {
        this.id = id;
        this.password = password;
        this.username = username;
    }
}
