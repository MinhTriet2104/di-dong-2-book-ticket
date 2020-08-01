package mt.movieticketbooking;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import mt.movieticketbooking.models.Customer;
import mt.movieticketbooking.models.DownloadImageTask;
import mt.movieticketbooking.models.Ticket;

public class ChoosePaymentMethodActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static String ADDRESS_CINE = "5th Floor, Vincom Thu Duc Shopping Mall, 216 Vo Van Ngan, Binh Tho Ward, Thu Duc District";
    private TextView txtTicketName;
    private TextView txtTotalPrice;
    private TextView txtCustomerInfo;

    private TextView txtTicketOrderTime;
    private EditText edtAddressCustomer;
    private Button btnBackPayment;
    private Button btnConfirmPayment;

    //Process
    private CheckBox ckbPayment1;
    private CheckBox ckbPayment2;
    private TextView txtPopupPayment1;
    private LinearLayout linearPopupPayment2;
    private TextView txtPaymentMethod;

    //Models
    private Ticket ticket;
    private Customer customer;
    private Bundle bundle;
    private int payType = 0;

    //Time now
    private Date date;


    //Formater
    SimpleDateFormat formatterDate = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    DecimalFormat formatterPrice = new DecimalFormat("#.##");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = getIntent();
        // Payment layout
        setContentView(R.layout.choose_payment_method_layout);
        // Get data from BookTicketActivity
        bundle = intent.getExtras();
        if(bundle != null){
            ticket = new Gson().fromJson(bundle.getString(PaymentActivity.TICKET_CODE_REQUEST), Ticket.class);
            customer = new Gson().fromJson(bundle.getString(PaymentActivity.CUSTOMER_CODE_REQUEST), Customer.class);
        }

        //Setup view process
        ckbPayment1 = (CheckBox) findViewById(R.id.ckbPayment1);
        ckbPayment2 = (CheckBox) findViewById(R.id.ckbPayment2);
        txtPopupPayment1 = (TextView) findViewById(R.id.popupPayment1);
        linearPopupPayment2 = (LinearLayout) findViewById(R.id.popupPayment2);
        txtPaymentMethod = (TextView) findViewById(R.id.txtPaymentMethod);
        //Setup view ticket
        txtTicketName = (TextView) findViewById(R.id.txtTicketNameMovie);
        txtTotalPrice = (TextView) findViewById(R.id.txtPaymentPrice);
        txtTicketOrderTime = (TextView) findViewById(R.id.ticketPaymentTime);
        //Setup view customer
        txtCustomerInfo = (TextView) findViewById(R.id.txtInfoCustomer);
        edtAddressCustomer = (EditText) findViewById(R.id.edtAddressCustomer);
        //Btn complete payment
        btnBackPayment = (Button) findViewById(R.id.btnBackPayment);
        btnConfirmPayment = (Button) findViewById(R.id.btnConfirmPayment);

        //Set data
        bindTicket();
        bindCustomer();
        getPaymentMethod(payType);


        btnConfirmPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create ticketDoc (ticket document)
                Map<String, Object> ticketDoc = new HashMap<>();
                ticketDoc.put("movieName", ticket.getMovieName());
                ticketDoc.put("movieDate", ticket.getTicketDate());
                ticketDoc.put("movieTime", ticket.getTicketTime());
                ticketDoc.put("ticketRoom", ticket.getTicketRoom());
                ticketDoc.put("listSeat", ticket.getListSeat());
                ticketDoc.put("totalPrice", ticket.getTotalPrice());
                ticketDoc.put("orderDate", new Timestamp(ticket.getOrderTime()));
                ticketDoc.put("paymentType", payType);
                ticketDoc.put("paymentStatus", 0);
                ticketDoc.put("customerFullName", customer.getFullName());
                ticketDoc.put("customerPhone", customer.getPhoneNumber());
                Log.d("triet-debug", ticketDoc.toString());

                if(payType == 0){
                    Toast.makeText(ChoosePaymentMethodActivity.this, "Pay type 0", Toast.LENGTH_SHORT).show();
                    // Add a new ticket document
                    db.collection("tickets")
                            .add(ticketDoc)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    // get id of ticket document
                                    Log.d("triet-debug", "ticket ID: " + documentReference.getId());
                                    Toast.makeText(ChoosePaymentMethodActivity.this, "ticket ID: " + documentReference.getId(), Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(ChoosePaymentMethodActivity.this, "Error: " + e, Toast.LENGTH_LONG).show();
                                }
                            });
                }else{
                    if(!String.valueOf(edtAddressCustomer.getText()).trim().isEmpty()){
                        ticketDoc.put("customerAddress", String.valueOf(edtAddressCustomer.getText()).trim());
                        Toast.makeText(ChoosePaymentMethodActivity.this, "Pay type 1 accept", Toast.LENGTH_SHORT).show();
                        // Payment success add a new ticket document
                        db.collection("tickets")
                                .add(ticketDoc)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        // get id of ticket document
                                        Log.d("triet-debug", "ticket ID: " + documentReference.getId());
                                        Toast.makeText(ChoosePaymentMethodActivity.this, "ticket ID: " + documentReference.getId(), Toast.LENGTH_LONG).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(ChoosePaymentMethodActivity.this, "Error: " + e, Toast.LENGTH_LONG).show();
                                    }
                                });
                    }
                    else{
                        Toast.makeText(ChoosePaymentMethodActivity.this, "Pay type 1 fail", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btnBackPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(ChoosePaymentMethodActivity.this, PaymentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        ckbPayment1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ckbPayment2.setChecked(false);
                    payType = 0;
                }else
                {
                    ckbPayment2.setChecked(true);
                    payType = 1;
                }
                getPaymentMethod(payType);
            }
        });

        ckbPayment2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ckbPayment1.setChecked(false);
                    payType = 1;
                }else
                {
                    ckbPayment1.setChecked(true);
                    payType = 0;
                }
                getPaymentMethod(payType);
            }
        });


    }

    private void bindTicket(){
        txtTicketName.setText("Movie name: " + ticket.getMovieName());
        txtTotalPrice.setText(formatterPrice.format(ticket.getTotalPrice()) + " vnđ");
        txtTicketOrderTime.setText("Order time: " + formatterDate.format(ticket.getOrderTime()));
    }

    private void bindCustomer(){
        txtCustomerInfo.setText("Information : " + customer.getFullName() + " - " + customer.getPhoneNumber());
    }

    private void getPaymentMethod(int type) {
        if (type == 0) {
            txtPopupPayment1.setVisibility(View.VISIBLE);
            linearPopupPayment2.setVisibility(View.GONE);
            txtPaymentMethod.setText("Paymend method: Buy tickets at the counter");
        } else {
            txtPopupPayment1.setVisibility(View.GONE);
            linearPopupPayment2.setVisibility(View.VISIBLE);
            txtPaymentMethod.setText("Paymend method: Cash of delivery");
        }
    }
}
