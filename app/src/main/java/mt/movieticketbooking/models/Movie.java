package mt.movieticketbooking.models;

import java.util.ArrayList;
import java.util.Date;

public class Movie {
    private String title;
    private int duration;
    private int rating;
    private double price;
    private String room;
    private Date dateStart;
    private Date dateEnd;
    private String imageUrl;
    private ArrayList<String> categories;
    private ArrayList<String> timeFrame;

    public Movie() {
    }

    public Movie(String title, int duration, int rating, double price, String room, Date dateStart, Date dateEnd, String imageUrl, ArrayList<String> categories, ArrayList<String> timeFrame) {
        this.title = title;
        this.duration = duration;
        this.rating = rating;
        this.price = price;
        this.room = room;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.imageUrl = imageUrl;
        this.categories = categories;
        this.timeFrame = timeFrame;
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

    public double getPrice() {
        return price;
    }

    public String getRoom() {
        return room;
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

    public ArrayList<String> getTimeFrame() {
        return timeFrame;
    }
}
