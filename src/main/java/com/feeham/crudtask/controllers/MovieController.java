package com.feeham.crudtask.controllers;

import com.feeham.crudtask.models.Movie;
import com.feeham.crudtask.services.IMoviesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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
                               @RequestParam(required = false) String releaseYear,
                               Model model) {
        int year = 0;
        if(category == null || category.equals("") || category.equals("All Categories")) category = null;
        if(genre == null || genre.equals("") || genre.equals("All Categories")) genre = null;
        if(releaseYear == null || releaseYear.equals("")) year = 0;
        else year = Integer.parseInt(releaseYear);
        model.addAttribute("movies", moviesService.filter(category, genre, year));
        return "home-page.html";
    }
    @GetMapping("/search")
    public String seach(@RequestParam(required = true) String searchText, Model model){
        model.addAttribute("movies", moviesService.search(searchText));
        return "home-page.html";
    }

    @GetMapping("/sort")
    public String sort(@RequestParam String sortType, Model model){
        boolean dateOfRelease = sortType.contains("release");
        boolean alphabetically = sortType.contains("title") ;
        boolean ascending = sortType.contains("asc");
        model.addAttribute("movies", moviesService.sort(dateOfRelease, alphabetically, ascending));
        return "home-page.html";
    }

    @GetMapping("/add")
    public String addMoviePage(){
        return "add-movie-page.html";
    }

    @GetMapping("/update")
    public String updateMoviePage(Model model){
        model.addAttribute("movie", new Movie(-1, "", "", null, "", LocalDate.now(), "", null, 0, "" ));
        return "update-movie-page.html";
    }

    @GetMapping("/delete")
    public String deleteMoviePage(){
        return "delete-movie-page.html";
    }

    @PostMapping("/add")
    public String addMovie(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String genre,
            @RequestParam String genre2,
            @RequestParam String genre3,
            @RequestParam String category,
            @RequestParam String releaseDate,
            @RequestParam String director,
            @RequestParam String actors,
            @RequestParam double rating,
            @RequestParam String poster) {

        List<String> genres = Arrays.asList(genre, genre2, genre3);
        List<String> actorList = Arrays.asList(actors.split("\\s*,\\s*"));
        LocalDate releaseLocalDate = LocalDate.parse(releaseDate);

        for(String gen: genres){
            if (gen.startsWith("All")){
                genres.remove(gen);
            }
        }

        Movie movie = new Movie(0, title, description, genres, category, releaseLocalDate, director, actorList, rating, poster);
        moviesService.addMovie(movie);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String loadMovieData(@RequestParam("id") String id, Model model) {
        if(id == null || id.length() == 0) {
            model.addAttribute("alert", "No movie found!");
            model.addAttribute("movie", new Movie(-1, "", "", null, "", LocalDate.now(), "", null, 0, "" ));
            return "update-movie-page.html";
        }
        Movie movie = moviesService.getMovieById(Integer.parseInt(id));
        if(movie == null) {
            model.addAttribute("alert", "No movie found!");
            model.addAttribute("movie", new Movie(-1, "", "", null, "", LocalDate.now(), "", null, 0, "" ));
            return "update-movie-page.html";
        }
        model.addAttribute("movie", movie);
        return "update-movie-page.html";
    }

    @PostMapping("/updateSave")
    public String addMovie(
            @RequestParam int movieId,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String genre,
            @RequestParam String genre2,
            @RequestParam String genre3,
            @RequestParam String category,
            @RequestParam String releaseDate,
            @RequestParam String director,
            @RequestParam String actors,
            @RequestParam double rating,
            @RequestParam String poster) {

        List<String> genres = Arrays.asList(genre, genre2, genre3);
        List<String> actorList = Arrays.asList(actors.split("\\s*,\\s*"));
        LocalDate releaseLocalDate = LocalDate.parse(releaseDate);

        for(String gen: genres){
            if (gen.startsWith("All")){
                genres.remove(gen);
            }
        }

        Movie movie = new Movie(0, title, description, genres, category, releaseLocalDate, director, actorList, rating, poster);
        moviesService.updateMovie(movieId, movie);
        return "redirect:/";
    }
    @PostMapping("/delete")
    public String deleteMovie(@RequestParam("id") String id, Model model) {
        if(id == null || id.length() == 0) {
            model.addAttribute("alert", "No movie found!");
            return "delete-movie-page.html";
        }
        Movie movie = moviesService.getMovieById(Integer.parseInt(id));
        if(movie == null) {
            model.addAttribute("alert", "No movie found!");
            return "delete-movie-page.html";
        }
        moviesService.deleteMovie(Integer.parseInt(id));
        model.addAttribute("alert", "Movie was deleted!");
        return "delete-movie-page.html";
    }
}
