package com.soyeyo.movies.dto;

import com.soyeyo.movies.models.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MovieDTO {

    private String title;
    private int rating;
    private String description;
    private MultipartFile image;
    private List<Category> categories;

    public MovieDTO(String title,int rating, String description, MultipartFile image, List<Category> categories) {
        this.title = title;
        this.rating = rating;
        this.description = description;
        this.image = image;
        this.categories = categories;
    }
}
