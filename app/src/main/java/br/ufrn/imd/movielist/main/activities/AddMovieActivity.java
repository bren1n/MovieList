package br.ufrn.imd.movielist.main.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import br.ufrn.imd.movielist.R;

public class AddMovieActivity extends AppCompatActivity {

    private TextView name;
    private TextView releaseDate;
    private TextView desc;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        name = (TextView) findViewById(R.id.movieName);
        releaseDate = (TextView) findViewById(R.id.movieReleaseDate);
        desc = (TextView) findViewById(R.id.movieDesc);
        image = (ImageView) findViewById(R.id.movieImage);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String movieName = extras.getString("movieName");
            String movieDesc = extras.getString("movieDesc");
            String movieReleaseDate = extras.getString("movieReleaseDate");
            String movieImageUrl = extras.getString("movieImageUrl");

            name.setText(movieName);
            releaseDate.setText(movieReleaseDate);
            desc.setText(movieDesc);
            Glide.with(AddMovieActivity.this).load(movieImageUrl).into(image);
        }
    }

}