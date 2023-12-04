package br.ufrn.imd.movielist.activity.activities;


import br.ufrn.imd.movielist.activity.movieHelper.MovieRequest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;

import br.ufrn.imd.movielist.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchListActivity extends AppCompatActivity {
    private MovieRequest movieRequest;
    private SearchView searchView;

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
                Toast.makeText(SearchListActivity.this, result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(SearchListActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

