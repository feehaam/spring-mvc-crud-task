package com.feeham.crudtask.controllers;

import com.feeham.crudtask.models.Movie;
import com.feeham.crudtask.services.IMoviesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MovieController {
    private IMoviesService moviesService;
    public MovieController(IMoviesService moviesService){
        this.moviesService = moviesService;
    }
    @GetMapping("/{id}")
    public String movieDetails(@PathVariable int id, Model model) {
        Movie movie = moviesService.getMovieById(id);
        model.addAttribute("movie", movie);
        return "movie-details.html";
    }
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("movies", moviesService.getAllMovies());
        return "home-page.html";
    }
    @GetMapping("/filter")
    public String filterMovies(@RequestParam(required = false) String category,
                               @RequestParam(required = false) String genre,
                               @RequestParam(required = false) int releaseYear,
                               Model model) {
        if(category == null || category.equals("") || category.equals("All Categories")) category = null;
        if(genre == null || genre.equals("") || genre.equals("All Categories")) genre = null;
        model.addAttribute("movies", moviesService.filter(category, genre, releaseYear));
        return "home";
    }
}
