package mt.movieticketbooking.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Ticket implements Serializable {
    private String movieName;
    private String imageUrl;
    private String ticketDate;
    private String ticketTime;
    private String ticketRoom;
    private double ticketPrice;
    private Date orderTime;
    private boolean ticketStatus;
    private ArrayList<String> listSeat;

    public Ticket() {}

    public Ticket(String movieName, String imageUrl, String ticketDate, String ticketTime, String ticketRoom, double ticketPrice, ArrayList<String> listSeat) {
        this.movieName = movieName;
        this.imageUrl = imageUrl;
        this.ticketDate = ticketDate;
        this.ticketTime = ticketTime;
        this.ticketRoom = ticketRoom;
        this.ticketPrice = ticketPrice;
        this.listSeat = listSeat;
        orderTime = new Date();
        ticketStatus = false;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public boolean isTicketStatus() {
        return ticketStatus;
    }

    public double getTotalPrice() {
        return this.ticketPrice * this.listSeat.size();
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

    public String getTicketRoom() {
        return ticketRoom;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public String getTicketListString(){
        String result = "";
        for(String str: listSeat){
            result += str + " - ";
        }
        return result.substring(0, result.length() - 3);
    }

    public ArrayList<String> getListSeat() {
        return listSeat;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setTicketDate(String ticketDate) {
        this.ticketDate = ticketDate;
    }

    public void setTicketTime(String ticketTime) {
        this.ticketTime = ticketTime;
    }

    public void setTicketRoom(String ticketRoom) {
        this.ticketRoom = ticketRoom;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public void setListSeat(ArrayList<String> listSeat) {
        this.listSeat = listSeat;
    }

    public void setTicketStatus(boolean ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
}
