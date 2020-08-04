package mt.movieticketbooking.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.Vector;

import mt.movieticketbooking.R;
import mt.movieticketbooking.models.MovieModel;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private Vector<MovieModel> listMovie;
    private OnItemClickListener mListener;
    public MovieAdapter(Vector<MovieModel> listMovie) {
        this.listMovie = listMovie;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        itemView = inflater.inflate(R.layout.card_view_home_layout, parent, false);

        return new MovieAdapter.MyViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MovieModel movie = listMovie.get(position);
        holder.edtAge.setText(movie.getAge());
        holder.edtRating.setText(movie.getRating()+"");
        holder.edtType.setText(movie.getCategories().get(0));
        holder.lblTitle.setText(movie.getTitle());
        int hours = movie.getDuration() / 60;
        int minutes = movie.getDuration() % 60;
        String duration = String.format("%d:%02d", hours, minutes);
        holder.lblDuration.setText(duration);
        Picasso.get().load(movie.getImageUrl()).into(holder.imgMovie);
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    //set event click for item
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        mListener = onItemClickListener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lblTitle, lblDate, lblDuration;
        EditText edtRating, edtType, edtAge;
        ImageView imgMovie;
        Button btnBuyTicket;
        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            lblTitle = itemView.findViewById(R.id.lblTitleMovies);
            lblDate = itemView.findViewById(R.id.lblDate);
            lblDuration = itemView.findViewById(R.id.lblDuration);
            edtRating = itemView.findViewById(R.id.edtRate);
            edtAge = itemView.findViewById(R.id.edtAge);
            edtType  = itemView.findViewById(R.id.edtMovieType);
            imgMovie = itemView.findViewById(R.id.imgMovies);
            imgMovie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                    {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (listener != null)
//                    {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION)
//                        {
//                            listener.onItemClick(position);
//                        }
//                    }
//                }
//            });
        }
    }
}
