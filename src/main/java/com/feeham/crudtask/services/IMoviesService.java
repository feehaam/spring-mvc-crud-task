package com.feeham.crudtask.services;
import com.feeham.crudtask.models.Movie;
import java.util.Date;
import java.util.List;

public interface IMoviesService {
    List<Movie> getAllMovies();
    Movie getMovieById(int id);
    void addMovie(Movie movie);
    void updateMovie(int id, Movie movie);
    void deleteMovie(int id);
    List<Movie> filter(String category, String genre, int releaseYear);
    List<Movie> sort(boolean dateOfRelease, boolean alphabetic);
    List<Movie> search(String text);
}