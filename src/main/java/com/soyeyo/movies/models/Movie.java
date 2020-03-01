package com.soyeyo.movies.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Movie {

    @Id
    private  int id;

    private String title;
    private String images;
    private int rating;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private List<Category> categories;

    public Movie(int id, String title, String images, int rating, String description, List<Category> categories) {
        this.id = id;
        this.title = title;
        this.images = images;
        this.rating = rating;
        this.description = description;
        this.categories = categories;
    }
}
