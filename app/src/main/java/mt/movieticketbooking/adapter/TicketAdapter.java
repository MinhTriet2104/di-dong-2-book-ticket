package mt.movieticketbooking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import mt.movieticketbooking.PaymentActivity;
import mt.movieticketbooking.R;
import mt.movieticketbooking.models.Ticket;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.DataViewHolder>{

    private ArrayList<Ticket> tickets;
    private Context context;
    private double totalPrice = 0;
    TextView txtTotalPrice;

    public TicketAdapter(Context context, ArrayList<Ticket> tickets, TextView txtTotal) {
        this.context = context;
        this.tickets = tickets;
        txtTotalPrice = txtTotal;
    }

    @Override
    public int getItemCount() {
        return tickets == null ? 0 : tickets.size();
    }

    @Override
    public TicketAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket_layout, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public void onBindViewHolder(final TicketAdapter.DataViewHolder holder, final int position) {
        Ticket ticket = tickets.get(position);
        holder.imgTicket.setImageResource(ticket.getIdPictureTicket());
        holder.txtTicketName.setText(ticket.getTiketName());
        holder.txtTicketDate.setText(ticket.getTicketDate());
        holder.txtTicketTime.setText(ticket.getTicketTime());
        holder.txtTicketChair.setText(String.valueOf(ticket.getTicketChair()));
        holder.txtTicketPrice.setText(String.valueOf(ticket.getTicketPrice()) + " vnđ");
        holder.ckbCheckTicket.setChecked(ticket.isChecked());

        holder.ckbCheckTicket.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tickets.get(position).setChecked(holder.ckbCheckTicket.isChecked());
                totalPrice = getTotalPrice(tickets);
                txtTotalPrice.setText(String.valueOf(totalPrice));
                Toast.makeText(context, String.valueOf(totalPrice), Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnRemoveTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // tickets.remove(position);
                Toast.makeText(context, "remove" + position, Toast.LENGTH_SHORT).show();
                tickets.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    /**
     * Data ViewHolder class.
     */
    public static class DataViewHolder extends RecyclerView.ViewHolder {
            ImageView imgTicket;
            TextView txtTicketName;
            TextView txtTicketDate;
            TextView txtTicketTime;
            TextView txtTicketChair;
            TextView txtTicketPrice;
            CheckBox ckbCheckTicket;
            Button btnRemoveTicket;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTicket = (ImageView) itemView.findViewById(R.id.imgViewTicket);
            txtTicketName = (TextView) itemView.findViewById(R.id.txtNameMovie);
            txtTicketDate = (TextView) itemView.findViewById(R.id.txtDateMovie);
            txtTicketTime = (TextView) itemView.findViewById(R.id.txtTimeMovie);
            txtTicketChair = (TextView) itemView.findViewById(R.id.txtChairMovie);
            txtTicketPrice = (TextView) itemView.findViewById(R.id.txtPriceMovie);
            ckbCheckTicket = (CheckBox) itemView.findViewById(R.id.ckbTicket);
            btnRemoveTicket = (Button) itemView.findViewById(R.id.btnRemove);
        }

    }

    public double getTotalPrice() {
        return getTotalPrice(tickets);
    }

    public double getTotalPrice(ArrayList<Ticket> list){
        double total = 0;
        for(Ticket ticket : list){
            if(ticket.isChecked())
                total += ticket.getTicketPrice();
        }
        return total;
    }
}
