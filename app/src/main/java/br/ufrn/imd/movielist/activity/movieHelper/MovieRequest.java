package br.ufrn.imd.movielist.activity.movieHelper;


import android.widget.Toast;

import androidx.annotation.NonNull;

import org.json.JSONObject;

import java.io.IOException;

import br.ufrn.imd.movielist.activity.activities.SearchListActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieRequest {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "141a479ec62f7a96621da45ebf6b926f";

    public static JSONObject searchMovie(String query) {
        JSONObject jsonObject = null;
        Request request = new Request.Builder()
                .url(BASE_URL + "search/movie?query=" + query + "&api_key=" + API_KEY)
                .build();

        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

            }
        });
        return jsonObject;
    }

}
