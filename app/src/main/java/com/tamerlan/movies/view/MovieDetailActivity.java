package com.tamerlan.movies.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tamerlan.movies.Movie;
import com.tamerlan.movies.R;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "movie";
    private ImageView imageViewPoster;
    private TextView textViewTittle;
    private TextView textViewYear;
    private TextView textViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initView();
        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        Glide.with(this)
                .load(movie.getPoster().getUrl())
                .error("https://st.kp.yandex.net/images/no-poster.gif")
                .into(imageViewPoster);
        textViewTittle.setText(movie.getName());
        textViewYear.setText(String.valueOf(movie.getYear()));
        textViewDescription.setText(movie.getDescription());



    }

    public static Intent newIntent(Context context, Movie movie){
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    private void initView(){
        imageViewPoster= findViewById(R.id.imageViewPoster);
        textViewTittle= findViewById(R.id.textViewTittle);
        textViewYear= findViewById(R.id.textViewYear);
        textViewDescription= findViewById(R.id.textViewDescription);
    }
}