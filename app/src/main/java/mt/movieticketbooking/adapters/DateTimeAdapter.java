package mt.movieticketbooking.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

import mt.movieticketbooking.R;

public class DateTimeAdapter extends RecyclerView.Adapter<DateTimeAdapter.MyViewHolder> {
    private Vector<String> listDate;

    public DateTimeAdapter(Vector<String> listDate) {
        this.listDate = listDate;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        ToggleButton dateText;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dateText = itemView.findViewById(R.id.dateText);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        itemView = inflater.inflate(R.layout.datetime_view_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String date = listDate.get(position);
        holder.dateText.setTextOff(date);
        holder.dateText.setTextOn(date);
        holder.dateText.setText(date);
    }

    @Override
    public int getItemCount() {
        return listDate.size();
    }
}
