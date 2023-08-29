package com.feeham.crudtask.utilities;

import com.feeham.crudtask.models.Movie;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieBase {
    private List<Movie> movies;
    public MovieBase(){
        movies = new ArrayList<>();

        movies.add(new Movie(1, "The Shawshank Redemption", "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
                List.of("Drama"), "Drama", LocalDate.of(1994, 9, 22), "Frank Darabont",
                List.of("Tim Robbins", "Morgan Freeman"), 9.3, "https://example.com/shawshank_redemption.jpg"));
        movies.add(new Movie(2, "The Godfather", "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
                List.of("Crime", "Drama"), "Drama", LocalDate.of(1972, 3, 24), "Francis Ford Coppola",
                List.of("Marlon Brando", "Al Pacino"), 9.2, "https://example.com/the_godfather.jpg"));
        movies.add(new Movie(5, "Pulp Fiction", "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",
                List.of("Crime", "Drama"), "Drama", LocalDate.of(1994, 10, 14), "Quentin Tarantino",
                List.of("John Travolta", "Samuel L. Jackson"), 8.9, "https://example.com/pulp_fiction.jpg"));
        movies.add(new Movie(6, "The Dark Knight", "When the menace known as The Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham.",
                List.of("Action", "Crime"), "Action", LocalDate.of(2008, 7, 18), "Christopher Nolan",
                List.of("Christian Bale", "Heath Ledger"), 9.0, "https://example.com/the_dark_knight.jpg"));
        movies.add(new Movie(7, "Inception", "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.",
                List.of("Action", "Adventure"), "Action", LocalDate.of(2010, 7, 16), "Christopher Nolan",
                List.of("Leonardo DiCaprio", "Joseph Gordon-Levitt"), 8.8, "https://example.com/inception.jpg"));
        movies.add(new Movie(8, "Forrest Gump", "The presidencies of Kennedy and Johnson, the events of Vietnam, Watergate, and other historical events unfold through the perspective of an Alabama man with an IQ of 75.",
                List.of("Drama", "Romance"), "Drama", LocalDate.of(1994, 7, 6), "Robert Zemeckis",
                List.of("Tom Hanks", "Robin Wright"), 8.8, "https://example.com/forrest_gump.jpg"));
        movies.add(new Movie(9, "The Matrix", "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
                List.of("Action", "Sci-Fi"), "Action", LocalDate.of(1999, 3, 31), "Lana Wachowski, Lilly Wachowski",
                List.of("Keanu Reeves", "Laurence Fishburne"), 8.7, "https://example.com/the_matrix.jpg"));
        movies.add(new Movie(10, "Fight Club", "An insomniac office worker and a devil-may-care soapmaker form an underground fight club that evolves into much more.",
                List.of("Drama", "Thriller"), "Drama", LocalDate.of(1999, 10, 15), "David Fincher",
                List.of("Brad Pitt", "Edward Norton"), 8.8, "https://example.com/fight_club.jpg"));
        movies.add(new Movie(11, "The Lord of the Rings: The Fellowship of the Ring", "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.",
                List.of("Adventure", "Drama", "Fantasy"), "Fantasy", LocalDate.of(2001, 12, 19), "Peter Jackson",
                List.of("Elijah Wood", "Ian McKellen"), 8.8, "https://example.com/lotr_fellowship.jpg"));
        movies.add(new Movie(12, "Inglourious Basterds", "In Nazi-occupied France during World War II, a plan to assassinate Nazi leaders by a group of Jewish U.S. soldiers coincides with a theatre owner's vengeful plans for the same.",
                List.of("Adventure", "Drama", "War"), "War", LocalDate.of(2009, 8, 21), "Quentin Tarantino",
                List.of("Brad Pitt", "Diane Kruger"), 8.3, "https://example.com/inglourious_basterds.jpg"));
        movies.add(new Movie(4, "The Social Network", "As Harvard student Mark Zuckerberg creates the social networking site that would become known as Facebook, he is sued by the twins who claimed he stole their idea, and by the co-founder who was later squeezed out of the business.",
                List.of("Biography", "Drama"), "Drama", LocalDate.of(2010, 10, 1), "David Fincher",
                List.of("Jesse Eisenberg", "Andrew Garfield"), 7.7, "https://example.com/the_social_network.jpg"));
        movies.add(new Movie(3, "The Revenant", "A frontiersman on a fur trading expedition in the 1820s fights for survival after being mauled by a bear and left for dead by members of his own hunting team.",
                List.of("Action", "Adventure", "Drama"), "Adventure", LocalDate.of(2015, 12, 25), "Alejandro González Iñárritu",
                List.of("Leonardo DiCaprio", "Tom Hardy"), 8.0, "https://example.com/the_revenant.jpg"));
    }
    public List<Movie> getMovies() {
        return movies;
    }
}