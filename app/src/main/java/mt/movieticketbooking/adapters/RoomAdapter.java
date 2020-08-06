package mt.movieticketbooking.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

import mt.movieticketbooking.HomeBookTicketActivity;
import mt.movieticketbooking.R;
import mt.movieticketbooking.models.RoomModel;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.MyViewHolder> {
    private Vector<RoomModel> listRoom;

    public RoomAdapter(Vector<RoomModel> listRoom) {
        this.listRoom = listRoom;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        itemView = inflater.inflate(R.layout.card_view_room_layout, parent, false);
        return new RoomAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final RoomModel room = listRoom.get(position);
        holder.roomText.setChecked(room.selectedRoom);
        holder.roomText.setText(room.getRoomText());
        holder.roomText.setTextOn(room.getRoomText());
        holder.roomText.setTextOff(room.getRoomText());
        holder.roomText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!holder.roomText.isChecked())
                {
                    for (RoomModel aRoom : listRoom)
                    {
                        aRoom.selectedRoom = false;
                    }
                    HomeBookTicketActivity.roomSelected = room.getRoomText();
                }
                else{
                    for (RoomModel aRoom : listRoom)
                    {
                        aRoom.selectedRoom = false;
                    }
                    room.selectedRoom = true;
                    if (room.getRoomText() != "")
                    {
                        HomeBookTicketActivity.roomSelected = room.getRoomText();
                    }else{
                        HomeBookTicketActivity.roomSelected = room.getRoomText();
                    }
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listRoom.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ToggleButton roomText;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            roomText = itemView.findViewById(R.id.roomText);
        }
    }
}
