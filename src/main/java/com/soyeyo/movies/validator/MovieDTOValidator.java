package com.soyeyo.movies.validator;

import com.soyeyo.movies.dto.MovieDTO;
import com.soyeyo.movies.models.User;
import com.soyeyo.movies.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MovieDTOValidator implements Validator {

    @Override
    public boolean supports(Class aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        MovieDTO movieDTO = (MovieDTO)o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rating", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");

        if(movieDTO.getDescription().length() < 50){
            errors.rejectValue("description", "Size.movieForm.description");
        }

        if(movieDTO.getCategories().size() < 1){
            errors.rejectValue("categories", "Size.movieForm.categories");
        }

        try{
           if( movieDTO.getImage().getSize() <= 0)throw new Exception("Not size");

        }catch (Exception e){
            errors.rejectValue("image","NotEmpty");
        }

    }
}

