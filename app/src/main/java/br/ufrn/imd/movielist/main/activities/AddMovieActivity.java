package br.ufrn.imd.movielist.main.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import br.ufrn.imd.movielist.R;
import br.ufrn.imd.movielist.main.database.MovieContract.MovieData;
import br.ufrn.imd.movielist.main.database.MovieDbHelper;

public class AddMovieActivity extends AppCompatActivity {

    private TextView name;
    private TextView releaseDate;
    private TextView desc;
    private ImageView image;
    private Button buttonFavorite;
    private String movieName, movieDesc, movieImageUrl;
    private int movieReleaseDate, movieIdApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        name = (TextView) findViewById(R.id.movieName);
        releaseDate = (TextView) findViewById(R.id.movieReleaseDate);
        desc = (TextView) findViewById(R.id.movieDetailReview);
        image = (ImageView) findViewById(R.id.movieImage);
        buttonFavorite = (Button) findViewById(R.id.buttonFavorite);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            movieName = extras.getString("movieName");
            movieDesc = extras.getString("movieDesc");
            movieReleaseDate = extras.getInt("movieReleaseDate");
            movieImageUrl = extras.getString("movieImageUrl");
            movieIdApi = extras.getInt("movieId");

            name.setText(movieName);
            releaseDate.setText(String.valueOf(movieReleaseDate));
            desc.setText(movieDesc);
            Glide.with(AddMovieActivity.this).load(movieImageUrl).into(image);
        }

        buttonFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MovieDbHelper dbHelper = new MovieDbHelper(AddMovieActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues register = new ContentValues();
                register.put("id_api", movieIdApi);
                register.put("name", movieName);
                register.put("description", movieDesc);
                register.put("release_date", movieReleaseDate);
                register.put("image_url", movieImageUrl);

                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                register.put("rate", Integer.parseInt(radioButton.getText().toString()));

                EditText reviewInput = (EditText) findViewById(R.id.reviewInput);
                register.put("review", reviewInput.getText().toString());

                db.insert(MovieData.TABLE_NAME, null, register);
                db.close();

                Toast.makeText(AddMovieActivity.this, "Successfully favorited movie", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(AddMovieActivity.this, FavoritesListActivity.class);
                startActivity(intent);
            }
        });
    }

}