package mt.movieticketbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeBookTicketActivity extends AppCompatActivity {

    private Button btnBuyTicket;
    private ImageView imageMovie;
    private EditText edtRate;
    private EditText edtYearsOld;
    private EditText edtMoviesType;
    private EditText edtSC;
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
    }

}
