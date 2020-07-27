package mt.movieticketbooking.models;

public class Ticket {
    private String movieName;
    private String imageUrl;
    private String ticketDate;
    private String ticketTime;
    private int ticketAmount;
    private String ticketRoom;
    private double ticketPrice;

    public Ticket(String movieName, String imageUrl, String ticketDate, String ticketTime, int ticketAmount, String ticketRoom, double ticketPrice) {
        this.movieName = movieName;
        this.imageUrl = imageUrl;
        this.ticketDate = ticketDate;
        this.ticketTime = ticketTime;
        this.ticketAmount = ticketAmount;
        this.ticketRoom = ticketRoom;
        this.ticketPrice = ticketPrice;
    }

    public double getTotalPrice() {
        return this.ticketAmount * this.ticketPrice;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTicketDate() {
        return ticketDate;
    }

    public String getTicketTime() {
        return ticketTime;
    }

    public int getTicketAmount() {
        return ticketAmount;
    }

    public String getTicketRoom() {
        return ticketRoom;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }
}
