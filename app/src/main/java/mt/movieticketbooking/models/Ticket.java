package mt.movieticketbooking.models;

public class Ticket {
    private String tiketName;
    private int pictureTicketId;
    private String ticketDate;
    private String ticketTime;
    private int ticketAmount;
    private int ticketRoomId;
    private double ticketPrice;
    private double ticketTotalPrice;

    public Ticket(String tiketName, int pictureTicketId, String ticketDate, String ticketTime, int ticketAmount, int ticketRoomId, double ticketPrice) {
        this.tiketName = tiketName;
        this.pictureTicketId = pictureTicketId;
        this.ticketDate = ticketDate;
        this.ticketTime = ticketTime;
        this.ticketAmount = ticketAmount;
        this.ticketRoomId = ticketRoomId;
        this.ticketPrice = ticketPrice;
        this.ticketTotalPrice = this.ticketAmount * this.ticketPrice;
    }

    public String getTiketName() {
        return tiketName;
    }

    public int getPictureTicketId() {
        return pictureTicketId;
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

    public double getTicketPrice() {
        return ticketPrice;
    }

    public double getTicketTotalPrice() {
        return ticketTotalPrice;
    }

    public int getTicketRoomId() {
        return ticketRoomId;
    }

    public void setTiketName(String tiketName) {
        this.tiketName = tiketName;
    }

    public void setPictureTicketId(int pictureTicketId) {
        this.pictureTicketId = pictureTicketId;
    }

    public void setTicketDate(String ticketDate) {
        this.ticketDate = ticketDate;
    }

    public void setTicketTime(String ticketTime) {
        this.ticketTime = ticketTime;
    }

    public void setTicketAmount(int ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setTicketTotalPrice(double ticketTotalPrice) {
        this.ticketTotalPrice = ticketTotalPrice;
    }

    public void setTicketRoomId(int ticketRoomId) {
        this.ticketRoomId = ticketRoomId;
    }
}
