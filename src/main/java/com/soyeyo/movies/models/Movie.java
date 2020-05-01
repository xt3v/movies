package com.soyeyo.movies.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String images;
    private int rating;

    @Type(type="text")
    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private Set<Category> categories;

    public Movie(Long id, String title, String images, int rating, String description, Set<Category> categories) {
        this.id = id;
        this.title = title;
        this.images = images;
        this.rating = rating;
        this.description = description;
        this.categories = categories;
    }
}
