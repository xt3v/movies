package com.soyeyo.movies.repositories;

import com.soyeyo.movies.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class LoadCategories {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostConstruct
    public void init() {
        if(categoryRepository.findAll().size() > 0)return;
        Category cats[] =  {
                new Category("horror") ,
                new Category("action"),
                new Category("comedy") ,
                new Category("drama"),
                new Category("thriller") ,
                new Category("documentary"),
                new Category("romance") ,
                new Category("fantasy")
        };
        List<Category> categories = Arrays.asList(cats);
        categoryRepository.saveAll(categories);
    }
}
