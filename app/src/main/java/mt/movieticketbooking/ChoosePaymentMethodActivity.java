package mt.movieticketbooking;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class ChoosePaymentMethodActivity extends AppCompatActivity {
    public static String ADDRESS_CINE = "5th Floor, Vincom Thu Duc Shopping Mall, 216 Vo Van Ngan, Binh Tho Ward, Thu Duc District";
    private TextView txtTicketName;
    private TextView txtTotalPrice;
    private TextView txtCustomerInfo;

    private TextView txtTicketOrderTime;
    private Button btnCompletePayment;

    //Process
    private CheckBox ckbPayment1;
    private CheckBox ckbPayment2;
    private TextView txtPopupPayment1;
    private LinearLayout popupLinearLayout;
    private TextView txtPaymentMethod;
    private EditText edtCardNumber;
    private EditText edtPassword;

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
        // Payment layout
        setContentView(R.layout.choose_payment_method_layout);
        // Get data from BookTicketActivity
        bundle = getIntent().getExtras();
        if(bundle != null){
            ticket = new Gson().fromJson(bundle.getString(PaymentActivity.TICKET_CODE_REQUEST), Ticket.class);
            customer = new Gson().fromJson(bundle.getString(PaymentActivity.CUSTOMER_CODE_REQUEST), Customer.class);
        }

        //Setup view process
        ckbPayment1 = (CheckBox) findViewById(R.id.ckbPayment1);
        ckbPayment2 = (CheckBox) findViewById(R.id.ckbPayment2);
        txtPopupPayment1 = (TextView) findViewById(R.id.popupPayment1);
        popupLinearLayout = (LinearLayout) findViewById(R.id.popupInfoCard);
        txtPaymentMethod = (TextView) findViewById(R.id.txtPaymentMethod);
        edtCardNumber = (EditText) findViewById(R.id.edtCardNumber);
        edtPassword = (EditText) findViewById(R.id.edtCardPassword);
        //Setup view ticket
        txtTicketName = (TextView) findViewById(R.id.txtTicketNameMovie);
        txtTotalPrice = (TextView) findViewById(R.id.txtPaymentPrice);
        txtTicketOrderTime = (TextView) findViewById(R.id.ticketPaymentTime);
        //Setup view customer
        txtCustomerInfo = (TextView) findViewById(R.id.txtInfoCustomer);
        //Btn complete payment
        btnCompletePayment = (Button) findViewById(R.id.btnCompletePayment);

        //Set data
        bindTicket();
        bindCustomer();
        getPaymentMethod(payType);


        btnCompletePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(payType == 0){
                    Toast.makeText(ChoosePaymentMethodActivity.this, "Pay type 0", Toast.LENGTH_SHORT).show();
                }else{
                    if(String.valueOf(edtCardNumber.getText()).equals("111111111111") && String.valueOf(edtPassword.getText()).equals("123456")){
                        Toast.makeText(ChoosePaymentMethodActivity.this, "Pay type 1 accept", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(ChoosePaymentMethodActivity.this, "Pay type 1 fail", Toast.LENGTH_SHORT).show();
                    }
                }

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
            popupLinearLayout.setVisibility(View.GONE);
            txtPaymentMethod.setText("Paymend method: Buy tickets at the counter");
        } else {
            txtPopupPayment1.setVisibility(View.GONE);
            popupLinearLayout.setVisibility(View.VISIBLE);
            txtPaymentMethod.setText("Paymend method: Pay by credit card");
        }
    }
}
