package com.feeham.crudtask.services;

import com.feeham.crudtask.models.Movie;
import com.feeham.crudtask.utilities.MovieBase;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService implements IMoviesService{

    private final MovieBase movieBase;

    public  MovieService(MovieBase movieBase){
        this.movieBase = movieBase;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieBase.getMovies();
    }

    @Override
    public Movie getMovieById(int id) {
        return movieBase.getMovies().stream()
            .filter(movie -> movie.getId() == id)
            .findFirst()
            .orElse(null);
    }

    @Override
    public void addMovie(Movie movie) {
        movie.setId(movieBase.getMovies().size());
        movieBase.getMovies().add(movie);
    }

    @Override
    public void updateMovie(int id, Movie movie) {
        Movie existingMovie = getMovieById(id);
        if (existingMovie != null) {
            // Update the attributes of the existing movie with the new attributes from the 'movie' parameter
            existingMovie.setTitle(movie.getTitle());
            existingMovie.setDescription(movie.getDescription());
            existingMovie.setGenres(movie.getGenres());
            existingMovie.setCategory(movie.getCategory());
            existingMovie.setReleaseDate(movie.getReleaseDate());
            existingMovie.setDirector(movie.getDirector());
            existingMovie.setActors(movie.getActors());
            existingMovie.setRating(movie.getRating());
            existingMovie.setPosterUrl(movie.getPosterUrl());
        }
    }

    @Override
    public void deleteMovie(int id) {
        Movie movieToDelete = getMovieById(id);
        if (movieToDelete != null) {
            movieBase.getMovies().remove(movieToDelete);
        }
    }

    @Override
    public List<Movie> filter(String category, String genre, int releaseYear) {
        List<Movie> filteredMovies = movieBase.getMovies();

        if (category != null) {
            String lowercaseCategory = category.toLowerCase();
            filteredMovies = filteredMovies.stream()
                    .filter(movie -> movie.getCategory().toLowerCase().equals(lowercaseCategory))
                    .collect(Collectors.toList());
        }

        if (genre != null) {
            String lowercaseGenre = genre.toLowerCase();
            filteredMovies = filteredMovies.stream()
                    .filter(movie -> movie.getGenres().stream().map(String::toLowerCase).anyMatch(lowercaseGenre::equals))
                    .collect(Collectors.toList());
        }

        if (releaseYear > 0) {
            filteredMovies = filteredMovies.stream()
                    .filter(movie -> movie.getReleaseDate().getYear() == releaseYear)
                    .collect(Collectors.toList());
        }
        return filteredMovies;
    }

    @Override
    public List<Movie> sort(boolean dateOfRelease, boolean alphabetic, boolean asc) {
        List<Movie> movies = movieBase.getMovies();
        if(dateOfRelease){
            if(asc){
                movies.sort(Comparator.comparing(Movie::getReleaseDate));
            }
            else{
                movies.sort(Comparator.comparing(Movie::getReleaseDate).reversed());
            }
        }
        if(alphabetic){
            if(asc){
                Collections.sort(movies, Comparator.comparing(Movie::getTitle, String.CASE_INSENSITIVE_ORDER));
            }
            else{
                Collections.sort(movies, Comparator.comparing(Movie::getTitle, String.CASE_INSENSITIVE_ORDER).reversed());
            }
        }
        return movies;
    }

    @Override
    public List<Movie> search(String text) {
        List<Movie> searchResults = new ArrayList<>();
        int maxMatch = 0;
        for (Movie movie : movieBase.getMovies()) {
            int matchValue = movie.match(text);
            if (matchValue > 0) {
                searchResults.add(movie);
            }
            maxMatch = Math.max(maxMatch, matchValue);
        }
        searchResults.sort((movie1, movie2) -> {
            double match1 = movie1.match(text);
            double match2 = movie2.match(text);
            return Double.compare(match2, match1);
        });
        int finalMaxMatch = maxMatch;
        return searchResults.stream().filter(movie -> movie.match(text) == finalMaxMatch).collect(Collectors.toList());
    }
}
