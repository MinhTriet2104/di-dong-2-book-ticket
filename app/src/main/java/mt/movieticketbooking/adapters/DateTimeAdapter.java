package mt.movieticketbooking.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Vector;

import mt.movieticketbooking.BookTicketActivity;
import mt.movieticketbooking.R;
import mt.movieticketbooking.models.MyDateTimeData;

public class DateTimeAdapter extends RecyclerView.Adapter<DateTimeAdapter.MyViewHolder> {
    private Vector<MyDateTimeData> listDate;

    public DateTimeAdapter(Vector<MyDateTimeData> listDate) {
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
        final MyDateTimeData myDateTimeData = listDate.get(position);
        String date = myDateTimeData.text;
        holder.dateText.setChecked(myDateTimeData.selected);
        holder.dateText.setTextOff(date);
        holder.dateText.setTextOn(date);
        holder.dateText.setText(date);
        holder.dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (MyDateTimeData date : listDate) {
                    date.selected = false;
                }
                myDateTimeData.selected = true;
                if (myDateTimeData.text.indexOf("/") != -1) {
                    BookTicketActivity.dateSelected = myDateTimeData.text;

                    BookTicketActivity.timeData.clear();
                    for (String time : BookTicketActivity.dateMap.get(BookTicketActivity.dateSelected)) {
                        BookTicketActivity.timeData.add(new MyDateTimeData(time));
                        BookTicketActivity.adapterTime = new DateTimeAdapter(BookTicketActivity.timeData);
                        BookTicketActivity.recyclerViewTime.setAdapter(BookTicketActivity.adapterTime);
                    }
                } else {
                    BookTicketActivity.timeSelected = myDateTimeData.text;
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDate.size();
    }
}
