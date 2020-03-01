package com.soyeyo.movies.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Category {

    @Id
    private int id;

    private String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
