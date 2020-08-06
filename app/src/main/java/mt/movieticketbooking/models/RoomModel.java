package mt.movieticketbooking.models;

public class RoomModel {
    public boolean selectedRoom = false;
    private String roomText;
    private String roomID;

    public String getRoomText() {
        return roomText;
    }

    public void setRoomText(String roomText) {
        this.roomText = roomText;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public RoomModel(String roomText, String roomID) {
        this.roomText = roomText;
        this.roomID = roomID;
    }
}
