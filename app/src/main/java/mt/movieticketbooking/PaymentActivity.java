package mt.movieticketbooking;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mt.movieticketbooking.adapter.TicketAdapter;
import mt.movieticketbooking.models.Ticket;

public class PaymentActivity extends AppCompatActivity {
    private ArrayList<Ticket> listTicket = new ArrayList<>();
    RecyclerView recyclerViewTicket;
    private int position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Payment layout
        setContentView(R.layout.payment_ticket_layout);

        Button btnComfirm = (Button) findViewById(R.id.btnComfirmPayment);
        TextView txtTotalPrice = (TextView) findViewById(R.id.txtTotalPrice);

        //set hash list ticket
        listTicket.add(new Ticket("Avenger: End Game", R.drawable.avenger_movies, "Dec-14-2020", "16:30", 22, true, 60000));
        listTicket.add(new Ticket("Mắt Biếc", R.drawable.avenger_movies, "Oct-22-2020", "17:30", 11, true, 70000));
        listTicket.add(new Ticket("Avenger: End Game", R.drawable.avenger_movies, "Dec-14-2020", "16:30", 22, false, 60000));
        listTicket.add(new Ticket("Ròm", R.drawable.avenger_movies, "Dec-05-2020", "21:00", 36, true, 80000));
        listTicket.add(new Ticket("Avenger: End Game", R.drawable.avenger_movies, "Dec-14-2020", "16:30", 22, true, 60000));
        listTicket.add(new Ticket("Tôi thấy hoa vàng trên cỏ xanh", R.drawable.avenger_movies, "Dec-25-2020", "16:30", 22, true, 50000));
        listTicket.add(new Ticket("Avenger: End Game", R.drawable.avenger_movies, "Dec-14-2020", "16:30", 22, false, 60000));

        recyclerViewTicket = (RecyclerView) findViewById(R.id.recyclerTicket);


        //Set up RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewTicket.setLayoutManager(layoutManager);
        TicketAdapter adapter = new TicketAdapter(this, listTicket, txtTotalPrice);
        recyclerViewTicket.setAdapter(adapter);
        //txtTotalPrice.setText(String.valueOf(adapter.getTotalPrice()) + " vnđ");
        btnComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PaymentActivity.this, "Button Confirm", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
