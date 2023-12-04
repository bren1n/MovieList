package br.ufrn.imd.movielist.activity.movieHelper;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class MovieRequest {

    private static final String API_KEY = "141a479ec62f7a96621da45ebf6b926f";
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    public interface OnApiResultListener {
        void onSuccess(String result);

        void onFailure(String errorMessage);
    }

    public static void searchMovies(String query, Context context, OnApiResultListener listener) {
        OkHttpClient client = new OkHttpClient();

        query.replace(" ", "%20");

        Request request = new Request.Builder()
                .url(BASE_URL + "search/movie?query=" + query + "&api_key=" + API_KEY)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                notifyError(context, listener, "Bad request");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    String json = response.body().string();
                    notifySuccess(context, listener, json);
                } catch (Exception e) {
                    e.printStackTrace();
                    notifyError(context, listener, "Bad request");
                }
            }
        });
    }

    private static void notifySuccess(Context context, OnApiResultListener listener, String result) {
        new Handler(Looper.getMainLooper()).post(() -> {
            if (listener != null) {
                listener.onSuccess(result);
            }
        });
    }

    private static void notifyError(Context context, OnApiResultListener listener, String errorMessage) {
        new Handler(Looper.getMainLooper()).post(() -> {
            if (listener != null) {
                listener.onFailure(errorMessage);
            } else {
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
