package mt.movieticketbooking;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import mt.movieticketbooking.models.Customer;
import mt.movieticketbooking.models.DownloadImageTask;
import mt.movieticketbooking.models.Ticket;

public class PaymentActivity extends AppCompatActivity {
    public static String TICKET_CODE_REQUEST = "ticket";
    public static String CUSTOMER_CODE_REQUEST = "customer";
    public static String ADDRESS_CINE = "5th Floor, Vincom Thu Duc Shopping Mall, 216 Vo Van Ngan, Binh Tho Ward, Thu Duc District";
    //Ticket
    private ImageView imgMovie;
    private TextView txtTicketName;
    private TextView txtTicketDate;
    private TextView txtTicketTime;
    private TextView txtTicketAmount;
    private TextView txtAddressTicket;
    private TextView txtTicketPrice;
    private TextView txtTotalPrice;
    private TextView txtTicketOrderTime;
    //Customer
    private EditText edtCustomerName;
    private EditText edtCustomerPhone;
    private TextView txtAlertCustomer;
    //Button
    private Button btnConfirm;
    private Button btBackConfirm;

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

        final Intent intent = getIntent();
        // Get data from BookTicketActivity
        ticket = (Ticket) intent.getSerializableExtra(TICKET_CODE_REQUEST);
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
        edtCustomerName = (EditText) findViewById(R.id.edtCustomerName);
        edtCustomerPhone = (EditText) findViewById(R.id.edtCustomerPhone);
        txtAlertCustomer = (TextView) findViewById(R.id.txtCustomerAlert);
        //Btn confirm
        btnConfirm = (Button) findViewById(R.id.btnConfirmTickets);
        btBackConfirm = (Button) findViewById(R.id.btnBackConfirm);

        //Set data
        setupTicket();


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

        btBackConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(PaymentActivity.this, BookTicketActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customerName = String.valueOf(edtCustomerName.getText()).trim();
                String customerPhone = String.valueOf(edtCustomerPhone.getText()).trim();
                if(customerName.isEmpty() || customerPhone.isEmpty()){
                    txtAlertCustomer.setVisibility(View.VISIBLE);
                }else{
                    txtAlertCustomer.setVisibility(View.INVISIBLE);
                    customer = new Customer(customerName, customerPhone);
                    intent.setClass(PaymentActivity.this, ChoosePaymentMethodActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    Bundle bundleData = new Bundle();
                    bundleData.putString(TICKET_CODE_REQUEST, new Gson().toJson(ticket));
                    bundleData.putString(CUSTOMER_CODE_REQUEST, new Gson().toJson(customer));
                    intent.putExtras(bundleData);
                    startActivity(intent);
                }
            }
        });
    }

    private void setupTicket(){
        new DownloadImageTask(imgMovie).execute(ticket.getImageUrl());
        txtTicketName.setText(ticket.getMovieName());
        txtTicketDate.setText(ticket.getTicketDate());
        txtTicketTime.setText(ticket.getTicketTime());
        txtTicketAmount.setText(String.valueOf(ticket.getListSeat().size()));
        txtAddressTicket.setText("Address: " + this.ADDRESS_CINE + " - Room " + ticket.getTicketRoom());
        txtTicketPrice.setText(formatterPrice.format(ticket.getTicketPrice()) + " vnđ");
        txtTotalPrice.setText(formatterPrice.format(ticket.getTotalPrice()) + " vnđ");
        txtTicketOrderTime.setText("Order time: " + formatterDate.format(ticket.getOrderTime()));
    }

}
