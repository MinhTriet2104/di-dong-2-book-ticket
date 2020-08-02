package mt.movieticketbooking.models;

public class RoomModel {
    public boolean selectedRoom = false;
    private String roomText;

    public String getRoomText() {
        return roomText;
    }

    public void setRoomText(String roomText) {
        this.roomText = roomText;
    }

    public RoomModel(String roomText) {
        this.roomText = roomText;
    }
}
