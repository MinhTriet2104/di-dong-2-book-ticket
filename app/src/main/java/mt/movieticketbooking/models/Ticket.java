package mt.movieticketbooking.models;

public class Ticket {
    private String tiketName;
    private int pictureTicketId;
    private String ticketDate;
    private String ticketTime;
    private int ticketChair;
    boolean isChecked = false;
    private double ticketPrice;


    public Ticket(String tiketName, int pictureTicketId, String ticketDate, String ticketTime, int ticketChair, boolean isChecked, double ticketPrice) {
        this.tiketName = tiketName;
        this.pictureTicketId = pictureTicketId;
        this.ticketDate = ticketDate;
        this.ticketTime = ticketTime;
        this.ticketChair = ticketChair;
        this.isChecked = isChecked;
        this.ticketPrice = ticketPrice;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public String getTiketName() {
        return tiketName;
    }

    public String getTicketDate() {
        return ticketDate;
    }

    public String getTicketTime() {
        return ticketTime;
    }

    public int getTicketChair() {
        return ticketChair;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public int getIdPictureTicket() {
        return pictureTicketId;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public void setTiketName(String tiketName) {
        this.tiketName = tiketName;
    }

    public void setTicketDate(String ticketDate) {
        this.ticketDate = ticketDate;
    }

    public void setTicketTime(String ticketTime) {
        this.ticketTime = ticketTime;
    }

    public void setTicketChair(int ticketChair) {
        this.ticketChair = ticketChair;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setPathPictureTicket(int pictureTicketId) {
        this.pictureTicketId = pictureTicketId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "tiketName='" + tiketName + '\'' +
                ", ticketDate='" + ticketDate + '\'' +
                ", ticketTime='" + ticketTime + '\'' +
                ", ticketChair=" + ticketChair +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
