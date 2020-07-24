package mt.movieticketbooking.models;

import java.util.ArrayList;
import java.util.Date;

public class Movie {
    private String title;
    private int duration;
    private int rating;
    private Date dateStart;
    private Date dateEnd;
    private String imageUrl;
    private ArrayList<String> categories;

    public Movie() {
    }

    public Movie(String title, int duration, int rating, Date dateStart, Date dateEnd, String imageUrl, ArrayList<String> categories) {
        this.title = title;
        this.duration = duration;
        this.rating = rating;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.imageUrl = imageUrl;
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public int getRating() {
        return rating;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }
}
