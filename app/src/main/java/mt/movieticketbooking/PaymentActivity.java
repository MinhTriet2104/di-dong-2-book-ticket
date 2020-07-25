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

import mt.movieticketbooking.models.Customer;
import mt.movieticketbooking.models.Ticket;

public class PaymentActivity extends AppCompatActivity {
    public static String ADDRESS_CINE = "5th Floor, Vincom Thu Duc Shopping Mall, 216 Vo Van Ngan, Binh Tho Ward, Thu Duc District";
    TextView txtTicketName;
    TextView txtTicketDate;
    TextView txtTicketTime;
    TextView txtTicketAmount;
    TextView txtAddressTicket;
    TextView txtTicketPrice;
    TextView txtTotalPrice;
    TextView txtCustomerName;
    TextView txtCustomerEmail;
    TextView txtCustomerPhone;
    Button btnComfirm;

    Ticket ticket;
    Customer customer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Payment layout
        setContentView(R.layout.payment_layout);
        //Setup view ticket
        txtTicketName = (TextView) findViewById(R.id.txtTicketName);
        txtTicketDate = (TextView) findViewById(R.id.txtTicketDate);
        txtTicketTime = (TextView) findViewById(R.id.txtTicketTime);
        txtTicketAmount = (TextView) findViewById(R.id.txtTicketAmount);
        txtAddressTicket = (TextView) findViewById(R.id.txtAddress);
        txtTicketPrice = (TextView) findViewById(R.id.txtTicketPrice);
        txtTotalPrice = (TextView) findViewById(R.id.txtTicketTotalPrice);
        //Setup view customer
        txtCustomerName = (TextView) findViewById(R.id.txtFullname);
        txtCustomerEmail = (TextView) findViewById(R.id.txtEmail);
        txtCustomerPhone = (TextView) findViewById(R.id.txtPhoneNumber);

        //hash new Ticket Model
        ticket = new Ticket("Avenger: End Game", R.drawable.avenger_movies, "May 15 - 2020", "16 : 30", 3, 2, 70000);
        //hash new Customer Model
        customer = new Customer("Khang Lê Minh", "khangleminh@gmail.com", "0985293047");

        //Set data
        bindTicket();
        bindCustomer();


        btnComfirm = (Button) findViewById(R.id.btnComfirmPayment);
        btnComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PaymentActivity.this, "Button Confirm", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bindTicket(){
        txtTicketName.setText(ticket.getTiketName());
        txtTicketDate.setText(ticket.getTicketDate());
        txtTicketTime.setText(ticket.getTicketTime());
        txtTicketAmount.setText(String.valueOf(ticket.getTicketAmount()));
        txtAddressTicket.setText("Address: " + this.ADDRESS_CINE + " - Room " + ticket.getTicketRoomId());
        txtTicketPrice.setText(String.valueOf(ticket.getTicketPrice()) + " vnđ");
        txtTotalPrice.setText(String.valueOf(ticket.getTicketTotalPrice()) + " vnđ");
    }

    private void bindCustomer(){
        txtCustomerName.setText("Full name: " + customer.getFullName());
        txtCustomerEmail.setText("Email: " + customer.getEmail());
        txtCustomerPhone.setText("Phone: " + customer.getPhoneNumber());
    }
}
