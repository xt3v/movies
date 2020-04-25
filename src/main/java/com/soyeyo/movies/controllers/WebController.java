package com.soyeyo.movies.controllers;


import com.soyeyo.movies.dto.MovieDTO;
import com.soyeyo.movies.models.Category;
import com.soyeyo.movies.models.User;
import com.soyeyo.movies.repositories.CategoryRepository;
import com.soyeyo.movies.validator.MovieDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MovieDTOValidator movieDTOValidator;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String welcome(Model model) {

        String currentUserName = "";
        boolean loggedIn = false;
        //check if user is authenticted
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
            loggedIn = true;
        }

        model.addAttribute("loggedIn",loggedIn);
        if(loggedIn){
            model.addAttribute("username",currentUserName);
        }


        return "home";
    }


    //movie
    @RequestMapping(value = "/user/addmovie",method = RequestMethod.GET)
    public String getAddMoviePage(Model model){
        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("categoriesPredifined",categories);
        model.addAttribute("movieForm", new MovieDTO());

        return "addmovie";
    }

    @RequestMapping(value = "/user/addmovie",method = RequestMethod.POST)
    public String addMoviePage(@ModelAttribute("movieForm") MovieDTO movieDTO,
                               BindingResult bindingResult, Model model){

        movieDTOValidator.validate(movieDTO, bindingResult);

//        if (bindingResult.hasErrors()) {
//            return "addmovie";
//        }

        return "redirect:/";
    }
}
