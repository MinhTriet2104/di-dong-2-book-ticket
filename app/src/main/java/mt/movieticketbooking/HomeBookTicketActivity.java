package mt.movieticketbooking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import mt.movieticketbooking.adapters.MovieAdapter;
import mt.movieticketbooking.adapters.RoomAdapter;
import mt.movieticketbooking.models.MovieModel;
import mt.movieticketbooking.models.RoomModel;

public class HomeBookTicketActivity extends AppCompatActivity {

    private CollectionReference nCollectionRefMovies = FirebaseFirestore.getInstance().collection("movies");
    private Button btnBuyTicket;
    public Vector<MovieModel> listMovies = new Vector<MovieModel>();
    public Vector<RoomModel> listRoom = new Vector<RoomModel>();
    MovieAdapter movieAdapter;
    RoomAdapter roomAdapter;
    public int duration;
    public static String roomSelected= "";
    public double price, rating;
    public String imageUrl, titleMovie, movieID, age;
    public ArrayList<String> categories;
    public int lastPosition;
    RecyclerView recyclerView;
    RecyclerView roomRecyclerView;
    private BottomSheetDialog bottomSheetDialog;
    public ImageView imgChooseRoom;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_book_ticket_layout);
        btnBuyTicket = findViewById(R.id.btnBuyTicket);
        recyclerView = findViewById(R.id.recyclerViewHome);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager inflater = new LinearLayoutManager(this);
        inflater.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(inflater);

        intent = getIntent();
        listRoom.add(new RoomModel("A1"));
        listRoom.add(new RoomModel("B1"));
        listRoom.add(new RoomModel("F1"));
        listRoom.add(new RoomModel("D1"));
        roomAdapter = new RoomAdapter(listRoom);
        // get position of card view
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                lastPosition = inflater.findFirstVisibleItemPosition();
            }
        });

        nCollectionRefMovies.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("getdoc", document.getId() + " => " + document.getData());
                        titleMovie = document.getData().get("title").toString();
                        rating = Double.parseDouble(document.getData().get("rating").toString());
                        age = document.getData().get("age").toString();
                        categories = (ArrayList<String>) document.getData().get("categories");
                        imageUrl = document.getData().get("imageUrl").toString();
                        duration = Integer.parseInt(document.getData().get("duration").toString());
                        movieID = document.getId();
//                        listRoom =(ArrayList<String>) document.getData().get("rooms");
                        Map<String, String> roomData = new HashMap<>();
                        listMovies.add(new MovieModel(movieID, titleMovie, duration, age, rating, price,  imageUrl, categories));
                    }
                    movieAdapter = new MovieAdapter(listMovies);
                    recyclerView.setAdapter(movieAdapter);

                    movieAdapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            intent.putExtra("movieId", listMovies.get(position).getMovieId());
                            intent.putExtra("room", roomSelected);
                            showChooseRoomDialog();
                            Picasso.get().load(listMovies.get(position).getImageUrl()).into(imgChooseRoom);
                            Toast.makeText(HomeBookTicketActivity.this, "ID =" + listMovies.get(position).getMovieId(),Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Log.w("noGet", "Error getting documents.", task.getException());
                }
            }
        });
        btnBuyTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(HomeBookTicketActivity.this, BookTicketActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("movieId", listMovies.get(lastPosition).getMovieId());
                intent.putExtra("room", roomSelected);
                showChooseRoomDialog();
                Picasso.get().load(listMovies.get(lastPosition).getImageUrl()).into(imgChooseRoom);
                Toast.makeText(HomeBookTicketActivity.this, "ID =" + listMovies.get(lastPosition).getMovieId(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showChooseRoomDialog() {
        bottomSheetDialog = new BottomSheetDialog(HomeBookTicketActivity.this);
        View view = getLayoutInflater().from(HomeBookTicketActivity.this).inflate(R.layout.choose_room_dialog_layout, null);
        imgChooseRoom = view.findViewById(R.id.imageChoosed);
        view.findViewById(R.id.btnChoosedRoom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent);
                bottomSheetDialog.dismiss();
            }
        });
        roomRecyclerView = view.findViewById(R.id.recyclerViewRoom);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        roomRecyclerView.setLayoutManager(layoutManager);
        roomRecyclerView.setAdapter(roomAdapter);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }
}
