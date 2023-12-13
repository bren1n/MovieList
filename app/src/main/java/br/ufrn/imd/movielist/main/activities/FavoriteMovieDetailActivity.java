package br.ufrn.imd.movielist.main.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import br.ufrn.imd.movielist.R;

public class FavoriteMovieDetailActivity extends AppCompatActivity {

    private TextView name;
    private TextView releaseDate;
    private TextView review;
    private TextView rate;
    private ImageView image;
    private Button buttonFavorite;
    private String movieName, movieReview, movieImageUrl;
    private int movieReleaseDate, movieRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movie_detail);

        name = (TextView) findViewById(R.id.movieDetailName);
        releaseDate = (TextView) findViewById(R.id.movieDetailReleaseDate);
        review = (TextView) findViewById(R.id.movieDetailReview);
        image = (ImageView) findViewById(R.id.movieDetailImage);
        rate = (TextView) findViewById(R.id.movieDetailRate);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            movieName = extras.getString("movieName");
            movieReview = extras.getString("movieReview");
            movieReleaseDate = extras.getInt("movieReleaseDate");
            movieImageUrl = extras.getString("movieImageUrl");
            movieRate = extras.getInt("movieRate");

            name.setText(movieName);
            releaseDate.setText(String.valueOf(movieReleaseDate));
            review.setText(movieReview);

            String stars = "";

            for (int i = 0; i < movieRate; i++) {
                stars += "⭐";
            }

            rate.setText(stars);

            Glide.with(FavoriteMovieDetailActivity.this).load(movieImageUrl).into(image);
        }
    }
}