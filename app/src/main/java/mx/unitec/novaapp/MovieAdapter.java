package mx.unitec.novaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movieList;

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView, directorTextView, yearTextView, descriptionTextView;
        public ImageView movieCoverImageView;

        public MovieViewHolder(View view) {
            super(view);
            titleTextView = view.findViewById(R.id.titleTextView);
            directorTextView = view.findViewById(R.id.directorTextView);
            yearTextView = view.findViewById(R.id.yearTextView);
            descriptionTextView = view.findViewById(R.id.descriptionTextView);
            movieCoverImageView = view.findViewById(R.id.movieCoverImageView);
        }
    }

    public MovieAdapter(List<Movie> movies) {
        this.movieList = movies;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);

        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.titleTextView.setText(movie.getTitle());
        holder.directorTextView.setText("Director: " + movie.getDirector());
        holder.yearTextView.setText("Año: " + movie.getYear());
        holder.descriptionTextView.setText(movie.getDescription());

        // Si estás cargando imágenes desde un recurso o URL, hazlo aquí
        // Por ahora asumimos que el campo "cover" guarda el nombre de la imagen
        Context context = holder.itemView.getContext();
        int resId = context.getResources().getIdentifier(movie.getCover(), "drawable", context.getPackageName());
        holder.movieCoverImageView.setImageResource(resId);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
