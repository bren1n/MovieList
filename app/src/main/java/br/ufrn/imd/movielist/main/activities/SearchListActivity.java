package br.ufrn.imd.movielist.main.activities;


import br.ufrn.imd.movielist.main.movieHelper.Movie;
import br.ufrn.imd.movielist.main.movieHelper.MovieAdapter;
import br.ufrn.imd.movielist.main.movieHelper.RecyclerViewInterface;
import br.ufrn.imd.movielist.main.movieHelper.MovieRequest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.movielist.R;

public class SearchListActivity extends AppCompatActivity implements RecyclerViewInterface {
    private MovieRequest movieRequest;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                get();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void get() {
        Toast.makeText(SearchListActivity.this, "Searching...", Toast.LENGTH_SHORT).show();
        movieRequest.searchMovies(searchView.getQuery().toString(), this, new MovieRequest.OnApiResultListener() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject result_obj = new JSONObject(result);
                    JSONArray movies = result_obj.getJSONArray("results");
                    movieList = new ArrayList<>();

                    for (int i = 0; i < movies.length(); i++) {
                        JSONObject temp = movies.getJSONObject(i);

                        Movie movie = new Movie(
                            temp.getInt("id"),
                            temp.getString("title"),
                            temp.optString("release_date").isEmpty() ? "No release date" : temp.optString("release_date").toString().substring(0,4),
                            temp.getString("overview").isEmpty() ? "No description" : temp.getString("overview"),
                    "https://image.tmdb.org/t/p/w500" + temp.getString("poster_path")
                        );
                        movieList.add(movie);
                    }

                    recyclerView = findViewById(R.id.recyclerView);

                    MovieAdapter adapter = new MovieAdapter(SearchListActivity.this, movieList, SearchListActivity.this);

                    //Configurar Recyclerview
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.addItemDecoration(new DividerItemDecoration(SearchListActivity.this, LinearLayout.VERTICAL));
                    recyclerView.setAdapter(adapter);
                } catch (Throwable t) {
                    return;
                }
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(SearchListActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getApplicationContext(), AddMovieActivity.class);
//        Toast.makeText(getApplicationContext(), movieList.get(position).getName(), Toast.LENGTH_SHORT).show();
        intent.putExtra("movieId", movieList.get(position).getId());
        intent.putExtra("movieName", movieList.get(position).getName());
        intent.putExtra("movieDesc", movieList.get(position).getDesc());
        intent.putExtra("movieReleaseDate", movieList.get(position).getReleaseDate());
        intent.putExtra("movieImageUrl", movieList.get(position).getImageUrl());
        startActivity(intent);
    }
}

