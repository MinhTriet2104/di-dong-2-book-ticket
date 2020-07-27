package mt.movieticketbooking;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import mt.movieticketbooking.models.Customer;
import mt.movieticketbooking.models.DownloadImageTask;
import mt.movieticketbooking.models.Ticket;

public class PaymentActivity extends AppCompatActivity {
    public static String ADDRESS_CINE = "5th Floor, Vincom Thu Duc Shopping Mall, 216 Vo Van Ngan, Binh Tho Ward, Thu Duc District";
    private ImageView imgMovie;
    private TextView txtTicketName;
    private TextView txtTicketDate;
    private TextView txtTicketTime;
    private TextView txtTicketAmount;
    private TextView txtAddressTicket;
    private TextView txtTicketPrice;
    private TextView txtTotalPrice;
    private TextView txtCustomerName;
    private TextView txtCustomerEmail;
    private TextView txtCustomerPhone;
    private TextView txtTicketOrderTime;
    private Button btnComfirm;

    //Models
    private Ticket ticket;
    private Customer customer;

    //Time now
    private Date date;

    SimpleDateFormat formatterDate = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    DecimalFormat formatterPrice = new DecimalFormat("#.##");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Payment layout
        setContentView(R.layout.payment_layout);

        // Get data from BookTicketActivity
        ticket = (Ticket) getIntent().getSerializableExtra("ticket");
        //Log.d("ticket", ticket.getMovieName());

        //Setup view ticket
        imgMovie = (ImageView) findViewById(R.id.imgMovie);
        txtTicketName = (TextView) findViewById(R.id.txtTicketName);
        txtTicketDate = (TextView) findViewById(R.id.txtTicketDate);
        txtTicketTime = (TextView) findViewById(R.id.txtTicketTime);
        txtTicketAmount = (TextView) findViewById(R.id.txtTicketAmount);
        txtAddressTicket = (TextView) findViewById(R.id.txtAddress);
        txtTicketPrice = (TextView) findViewById(R.id.txtTicketPrice);
        txtTotalPrice = (TextView) findViewById(R.id.txtTicketTotalPrice);
        txtTicketOrderTime = (TextView) findViewById(R.id.ticketOrderTime);
        //Setup view customer
        txtCustomerName = (TextView) findViewById(R.id.txtFullname);
        txtCustomerEmail = (TextView) findViewById(R.id.txtEmail);
        txtCustomerPhone = (TextView) findViewById(R.id.txtPhoneNumber);
        //Btn confirm
        btnComfirm = (Button) findViewById(R.id.btnComfirmPayment);
        //Set time now
        date = new Date();
        txtTicketOrderTime.setText("Order time: " + formatterDate.format(date));

        //hash new Ticket Model
//        ticket = new Ticket("Avenger: End Game", R.drawable.avenger_movies, "May 15 - 2020", "16 : 30", 3, 2, 70000);
        //hash new Customer Model
        customer = new Customer("Khang Lê Minh", "khangleminh@gmail.com", "0985293047");

        //Set data
        bindTicket();
        bindCustomer();


        txtTicketAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(PaymentActivity.this).create();
                alertDialog.setTitle("Ticket List");
                alertDialog.setMessage(ticket.getTicketListString());
                // Alert dialog button
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Alert dialog action goes here
                                // onClick button code here
                                dialog.dismiss();// use dismiss to cancel alert dialog
                            }
                        });
                alertDialog.show();
            }
        });

        btnComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PaymentActivity.this, "Button Confirm", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void bindTicket(){
        new DownloadImageTask(imgMovie).execute(ticket.getImageUrl());
        txtTicketName.setText(ticket.getMovieName());
        txtTicketDate.setText(ticket.getTicketDate());
        txtTicketTime.setText(ticket.getTicketTime());
        txtTicketAmount.setText(String.valueOf(ticket.getListSeat().size()));
        txtAddressTicket.setText("Address: " + this.ADDRESS_CINE + " - Room " + ticket.getTicketRoom());
        txtTicketPrice.setText(formatterPrice.format(ticket.getTicketPrice()) + " vnđ");
        txtTotalPrice.setText(formatterPrice.format(ticket.getTotalPrice()) + " vnđ");
    }

    private void bindCustomer(){
        txtCustomerName.setText("Full name: " + customer.getFullName());
        txtCustomerEmail.setText("Email: " + customer.getEmail());
        txtCustomerPhone.setText("Phone: " + customer.getPhoneNumber());
    }


}
