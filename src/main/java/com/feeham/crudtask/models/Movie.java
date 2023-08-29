package com.feeham.crudtask.models;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private int id;
    private String title;
    private String description;
    private List<String> genres;
    private String category;
    private LocalDate releaseDate;
    private String director;
    private List<String> actors;
    private double rating;
    private String posterUrl;

    public Movie(int id, String title, String description, List<String> genres,
                 String category, LocalDate releaseDate, String director, List<String> actors,
                 double rating, String posterUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.category = category;
        this.releaseDate = releaseDate;
        this.director = director;
        this.actors = actors;
        this.rating = rating;
        this.posterUrl = posterUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public double match(String keyword) {
        int matches[] = {lcs(keyword, title), lcs(keyword, category), lcs(keyword, director),
                lcs(keyword, String.join(" ", genres)),
                lcs(keyword, String.join(" ", actors))};
        return Arrays.stream(matches).max().orElse(0);
    }

    private int lcs(String text1, String text2) {
        text1 = text1.toLowerCase();
        text2 = text2.toLowerCase();
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
