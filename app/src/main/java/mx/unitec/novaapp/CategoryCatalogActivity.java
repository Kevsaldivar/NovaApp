package mx.unitec.novaapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CategoryCatalogActivity extends AppCompatActivity {

    private RecyclerView moviesRecyclerView;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList;
    private SQLiteHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalog_view);

        moviesRecyclerView = findViewById(R.id.moviesRecyclerView);
        dbHelper = new SQLiteHelper(this);
        db = dbHelper.getReadableDatabase();

        String category = getIntent().getStringExtra("CATEGORY");

        movieList = getMoviesByCategory(category);

        movieAdapter = new MovieAdapter(movieList);
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        moviesRecyclerView.setAdapter(movieAdapter);
    }

    private List<Movie> getMoviesByCategory(String category) {
        List<Movie> movies = new ArrayList<>();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM peliculas WHERE categoria_id = (SELECT id FROM categorias WHERE nombre = ?)",
                new String[]{category}
        );

        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
                String director = cursor.getString(cursor.getColumnIndexOrThrow("director"));
                int year = cursor.getInt(cursor.getColumnIndexOrThrow("anio"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("descripcion"));
                String cover = cursor.getString(cursor.getColumnIndexOrThrow("portada"));

                movies.add(new Movie(title, director, year, description, cover));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return movies;
    }
}
