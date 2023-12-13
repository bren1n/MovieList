package br.ufrn.imd.movielist.main.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.movielist.R;
import br.ufrn.imd.movielist.main.database.MovieContract.MovieData;
import br.ufrn.imd.movielist.main.database.MovieDbHelper;
import br.ufrn.imd.movielist.main.movieHelper.Movie;
import br.ufrn.imd.movielist.main.movieHelper.MovieFavoriteAdapter;
import br.ufrn.imd.movielist.main.movieHelper.RecyclerViewInterface;

public class FavoritesListActivity extends AppCompatActivity implements RecyclerViewInterface {
    private RecyclerView recyclerView;
    private List<Movie> movieList;
    private MovieFavoriteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_list);

        recyclerView = findViewById(R.id.recyclerViewFavorite);

        getFavoriteMovies();

        adapter = new MovieFavoriteAdapter(FavoritesListActivity.this, movieList, FavoritesListActivity.this);

        //Configurar Recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        FloatingActionButton buttonAdd = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FavoritesListActivity.this, SearchListActivity.class);
                startActivity(intent);
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);
    }

    private void getFavoriteMovies() {
        MovieDbHelper dbHelper = new MovieDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String stringQuery = "SELECT * FROM " + MovieData.TABLE_NAME;

        Cursor query = db.rawQuery(stringQuery, null);

//        CREATE TABLE " + MovieData.TABLE_NAME + " (" +
//        MovieData._ID + " INTEGER PRIMARY KEY," +
//                MovieData.COLUMN_NAME_ID_API + " INTEGER," +
//                MovieData.COLUMN_NAME_NAME + " TEXT," +
//                MovieData.COLUMN_NAME_DESCRIPTION + " TEXT," +
//                MovieData.COLUMN_NAME_RELEASE_DATE + " INTEGER," +
//                MovieData.COLUMN_NAME_RATE + " INTEGER," +
//                MovieData.COLUMN_NAME_IMAGE_URL + " TEXT)";
        movieList = new ArrayList<>();

        if (query.moveToFirst()) {
            while (query != null) {
                int tempIdApi = query.getInt(1);
                String tempName = query.getString(2);
                String tempDesc = query.getString(3);
                int tempReleaseDate = Integer.parseInt(query.getString(4));
                int tempRate = Integer.parseInt(query.getString(5));
                String tempReview = query.getString(6);
                String tempImageUrl = query.getString(7);

//                int id, String name, String releaseDate, String desc, String imageUrl, int rate

                Movie movie = new Movie(tempIdApi, tempName, tempReleaseDate, tempDesc, tempImageUrl, tempRate, tempReview);

                movieList.add(movie);

                if(!query.moveToNext()) {
                    break;
                }
            }
        }
    }

    @Override
    public void onItemClick(int position) {
        Movie movie = movieList.get(position);

        Intent intent = new Intent(FavoritesListActivity.this, FavoriteMovieDetailActivity.class);
        intent.putExtra("movieId", movie.getId());
        intent.putExtra("movieName", movie.getName());
        intent.putExtra("movieReview", movie.getReview());
        intent.putExtra("movieReleaseDate", movie.getReleaseDate());
        intent.putExtra("movieImageUrl", movie.getImageUrl());
        intent.putExtra("movieRate", movie.getRate());

        startActivity(intent);

    }

    ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            Movie movie = movieList.get(viewHolder.getAdapterPosition());
            movieList.remove(viewHolder.getAdapterPosition());

            MovieDbHelper dbHelper = new MovieDbHelper(FavoritesListActivity.this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            int qttMovieDeleted = db.delete(MovieData.TABLE_NAME, MovieData.COLUMN_NAME_ID_API + "=" + movie.getId(), null);

            if (qttMovieDeleted != 0)
                Toast.makeText(FavoritesListActivity.this, "Movie deleted", Toast.LENGTH_SHORT).show();

            adapter.notifyDataSetChanged();
        }
    };
}