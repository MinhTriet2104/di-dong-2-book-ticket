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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final RoomModel room = listRoom.get(position);
        holder.roomtext.setChecked(room.selectedRoom);
        holder.roomtext.setText(room.getRoomText());
        holder.roomtext.setTextOn(room.getRoomText());
        holder.roomtext.setTextOff(room.getRoomText());
        holder.roomtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (RoomModel aroom : listRoom)
                {
                    aroom.selectedRoom = false;
                }
                room.selectedRoom = true;
                if (room.getRoomText() != "")
                {
                    HomeBookTicketActivity.roomSelected = room.getRoomText();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listRoom.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ToggleButton roomtext;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            roomtext = itemView.findViewById(R.id.roomText);
        }
    }
}
