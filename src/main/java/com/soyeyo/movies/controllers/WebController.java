package com.soyeyo.movies.controllers;


import com.soyeyo.movies.dto.MovieDTO;
import com.soyeyo.movies.models.Category;
import com.soyeyo.movies.models.Movie;
import com.soyeyo.movies.repositories.CategoryRepository;
import com.soyeyo.movies.repositories.MovieRepository;
import com.soyeyo.movies.services.FileFetcher;
import com.soyeyo.movies.services.FileUploader;
import com.soyeyo.movies.validator.MovieDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
;

@Controller
public class WebController {


    @Autowired CategoryRepository categoryRepository;
    @Autowired MovieDTOValidator movieDTOValidator;
    @Autowired FileUploader fileUploader;
    @Autowired FileFetcher fileFetcher;
    @Autowired MovieRepository movieRepository;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String welcome(Model model, @RequestParam(required = false) String name,@RequestParam(required = false) String category) {

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

        getMovies(model,name,category);

        return "home";
    }

    private void getMovies(Model model, String name, String category) {
       //categories to populate dropdown
       model.addAttribute("categories",categoryRepository.findAll());

       if(name != null){
           if(category != null && !category.trim().equals("-1")){
               List<Movie> movies = new ArrayList<>();
               movieRepository.findByTitleContainingIgnoreCase(name).forEach(
                       movie -> {
                           for (Category ct: movie.getCategories()) {
                               if(Long.parseLong(category) == ct.getId()){
                                   movies.add(movie);
                                   break;
                               }
                           }
                       }
               );
               model.addAttribute("movies",movies);
           }else{
               model.addAttribute("movies",movieRepository.findByTitleContainingIgnoreCase(name));
           }
       }else if(category != null && !category.trim().equals("-1")){
           model.addAttribute("movies",categoryRepository.findById(Long.parseLong(category)).get().getMovies());
       }else{
           model.addAttribute("movies",movieRepository.findAll());
       }

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

        if (bindingResult.hasErrors()) {
            return "addmovie";
        }

        String fileName = fileUploader.storeFile(movieDTO.getImage());

        if(fileName.equals(""))return "addmovie";

        Movie movie = new Movie();
        movie.setCategories(new HashSet<>(movieDTO.getCategories()));
        movie.setTitle(movieDTO.getTitle());
        movie.setDescription(movieDTO.getDescription());
        movie.setRating(movieDTO.getRating());
        movie.setImages(fileName);


        movieRepository.save(movie);
        return "redirect:/";

    }

    @GetMapping("/image/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileFetcher.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
