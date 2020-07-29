package mt.movieticketbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import mt.movieticketbooking.adapters.DateTimeAdapter;
import mt.movieticketbooking.models.Movie;
import mt.movieticketbooking.models.MyDateTimeData;
import mt.movieticketbooking.models.Ticket;

public class BookTicketActivity extends AppCompatActivity {
    private Movie movie = new Movie();
    private ArrayList<String> listSeatSelected = new ArrayList<>();
    private Vector<MyDateTimeData> dateData = new Vector<>();
    private Vector<MyDateTimeData> timeData = new Vector<>();
    private String movieTitle;
    private String room = "A1";
    public static String dateSelected = "";
    public static String timeSelected = "";
    private String strCategories = "";
    private String duration;
    private double price;
    private String imageUrl;
    private DocumentReference nDocRefMovie = FirebaseFirestore.getInstance().document("movies/aNwkXJ1HXiDT3fh4Qykx");

    private RecyclerView recyclerViewDate;
    private RecyclerView recyclerViewTime;
    private Button btnBack;
    private Button btnBuyNow;
    private TextView lblTitle;
    private TextView lblTag;
    private TextView lblRoomAndDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Triet layout
        setContentView(R.layout.book_ticket_layout);
        final Intent intent = getIntent();

        //get ID from layout
        btnBack = findViewById(R.id.btnBack);
        btnBuyNow = findViewById(R.id.btnBuyNow);
        lblTitle = findViewById(R.id.movieTitle);
        lblTag = findViewById(R.id.movieTag);
        lblRoomAndDuration = findViewById(R.id.movieRoomAndDuration);

        dateData.add(new MyDateTimeData("20/08"));
        dateData.add(new MyDateTimeData("23/08"));
        dateData.add(new MyDateTimeData("26/08"));
        dateData.add(new MyDateTimeData("27/08"));
        dateData.add(new MyDateTimeData("02/09"));
        dateData.add(new MyDateTimeData("05/09"));

        timeData.add(new MyDateTimeData("9:00"));
        timeData.add(new MyDateTimeData("10:30"));
        timeData.add(new MyDateTimeData("13:00"));
        timeData.add(new MyDateTimeData("17:15"));
        timeData.add(new MyDateTimeData("20:00"));
        timeData.add(new MyDateTimeData("22:00"));

        recyclerViewDate = findViewById(R.id.movieDate);
        recyclerViewTime = findViewById(R.id.movieTime);

        LinearLayoutManager layoutManagerDate = new LinearLayoutManager(this);
        LinearLayoutManager layoutManagerTime = new LinearLayoutManager(this);
        layoutManagerDate.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManagerTime.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerViewDate.setLayoutManager(layoutManagerDate);
        recyclerViewTime.setLayoutManager(layoutManagerTime);

        DateTimeAdapter adapterDate = new DateTimeAdapter(dateData);
        DateTimeAdapter adapterTime = new DateTimeAdapter(timeData);
        recyclerViewDate.setAdapter(adapterDate);
        recyclerViewTime.setAdapter(adapterTime);

        //Set Event for btn Back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(BookTicketActivity.this, HomeBookTicketActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // return condition
                if (listSeatSelected.size() == 0) {
                    Toast.makeText(BookTicketActivity.this, "Please Select Your Seat!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (dateSelected.equals("")) {
                    Toast.makeText(BookTicketActivity.this, "Please Select a Date!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (timeSelected.equals("")) {
                    Toast.makeText(BookTicketActivity.this, "Please Select a Time!", Toast.LENGTH_LONG).show();
                    return;
                }
                intent.setClass(BookTicketActivity.this, PaymentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                Ticket ticket = new Ticket(movieTitle, imageUrl, dateSelected, timeSelected, room, price, listSeatSelected);
                intent.putExtra("ticket", ticket);
                startActivity(intent);
            }
        });

        nDocRefMovie.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d("triet ", task.getResult().getData().toString());
                    Map<String, Object> data = task.getResult().getData();
                    movieTitle = data.get("title").toString();
                    price = Double.parseDouble(data.get("price").toString());
                    imageUrl = data.get("imageUrl").toString();
//                    Map<String, Object> roomData = data.get("rooms");
//                    room = roomData.get("name").toString();
                    int hours = Integer.parseInt(data.get("duration").toString()) / 60;
                    int minutes = Integer.parseInt(data.get("duration").toString()) % 60;
                    duration = String.format("%d:%02d", hours, minutes);
                    ArrayList<String> categories = (ArrayList<String>) data.get("categories");
                    for (String category : categories) {
                        strCategories += category + ", ";
                    }
                    strCategories = strCategories.substring(0, strCategories.length() - 2);
                    lblTitle.setText(movieTitle);
                    lblTag.setText(strCategories);
                    lblRoomAndDuration.setText(room + " - " + duration);
                }
            }
        });
    }

    public void onCheckboxClicked(View v) {
        // Is the view now checked?
        boolean checked = ((CheckBox) v).isChecked();
        String buttonId = (v.getResources().getResourceName(v.getId())).split("/")[1];
        if (checked) {
            listSeatSelected.add(buttonId);
        } else {
            listSeatSelected.remove(buttonId);
        }
        Toast.makeText(BookTicketActivity.this, listSeatSelected+"", Toast.LENGTH_SHORT).show();
        Log.d("seat", listSeatSelected+"");
    }
}
