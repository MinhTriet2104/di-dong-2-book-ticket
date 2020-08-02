package mt.movieticketbooking.models;

import java.util.ArrayList;

public class MovieModel {
    private String movieId;
    private String title;
    private int duration;
    private String age;
    private double rating;
    private double price;
    private String imageUrl;
    private ArrayList<String> categories;

    public MovieModel() {
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public MovieModel(String movieId, String title, int duration, String age, double rating, double price, String imageUrl, ArrayList<String> categories) {
        this.title = title;
        this.duration = duration;
        this.age = age;
        this.rating = rating;
        this.price = price;
        this.imageUrl = imageUrl;
        this.categories = categories;
        this.movieId = movieId;
    }
}
