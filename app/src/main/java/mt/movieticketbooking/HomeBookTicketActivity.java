package mt.movieticketbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;

import mt.movieticketbooking.models.DownloadImageTask;
import mt.movieticketbooking.models.Movie;

public class HomeBookTicketActivity extends AppCompatActivity {

    private CollectionReference nCollectionRefMovies = FirebaseFirestore.getInstance().collection("movies");
    private Button btnBuyTicket;
    private ImageView imageMovie;
    private EditText edtRate;
    private EditText edtYearsOld;
    private EditText edtMoviesType;
    private EditText edtSC;
    private TextView lblTitle;
    private ArrayList<Movie> listMovies;
    String titleMovie;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_book_ticket_layout);
        btnBuyTicket = findViewById(R.id.btnBuyTicket);
        imageMovie = findViewById(R.id.imgMovies);
        edtRate = findViewById(R.id.edtRate);
        edtMoviesType = findViewById(R.id.movieType);
        edtSC = findViewById(R.id.edtSC);
        edtYearsOld = findViewById(R.id.edtYearsOld);
        lblTitle = findViewById(R.id.lblTitleMovies);
        intent = getIntent();

        // Set Event for btn Buy ticket
        btnBuyTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(HomeBookTicketActivity.this, BookTicketActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        // Set Event for movie image clicked
        imageMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(HomeBookTicketActivity.this, BookTicketActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        // Set Event for movie image clicked
        edtRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edtText = edtRate.getText() + "";
                Toast.makeText(HomeBookTicketActivity.this, edtText , Toast.LENGTH_SHORT).show();
            }
        });

        // Set Event for movie edt clicked
        edtYearsOld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edtText = edtYearsOld.getText() + "";
                Toast.makeText(HomeBookTicketActivity.this, edtText , Toast.LENGTH_SHORT).show();
            }
        });
        // Set Event for edt clicked
        edtMoviesType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edtText = edtMoviesType.getText() + "";
                Toast.makeText(HomeBookTicketActivity.this, edtText , Toast.LENGTH_SHORT).show();
            }
        });
        // Set Event for edt clicked
        edtSC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edtText = edtSC.getText() + "";
                Toast.makeText(HomeBookTicketActivity.this, edtText , Toast.LENGTH_SHORT).show();
            }
        });

        nCollectionRefMovies.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("getdoc", document.getId() + " => " + document.getData());
                        titleMovie = document.getData().get("title").toString();
                        new DownloadImageTask(imageMovie).execute(document.getData().get("imageUrl")+"");
                    }
                } else {
                    Log.w("noGet", "Error getting documents.", task.getException());
                }
            }
        });
    }

}
