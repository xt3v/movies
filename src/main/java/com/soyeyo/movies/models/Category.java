package com.soyeyo.movies.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;



    @ManyToMany(mappedBy = "categories")
    private Set<Movie> movies;

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String name){
        this.name = name;
    }
}
