package mt.movieticketbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import mt.movieticketbooking.models.Movie;

public class BookTicketActivity extends AppCompatActivity {
    private Movie movie = new Movie();
    private ArrayList<String> listSeatSelected = new ArrayList<>();
    private DocumentReference nDocRefMovie = FirebaseFirestore.getInstance().document("book-ticket/movie");
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
                intent.setClass(BookTicketActivity.this, PaymentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        nDocRefMovie.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d("test ", task.getResult().getData().toString());
                    Toast.makeText(BookTicketActivity.this, task.getResult().getData().toString(), Toast.LENGTH_LONG).show();
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
        Log.d("list", listSeatSelected+"");
    }
}
