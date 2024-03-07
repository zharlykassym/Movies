package com.tamerlan.movies.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.tamerlan.movies.Movie;
import com.tamerlan.movies.R;
import com.tamerlan.movies.view.MoviesAdapter;
import com.tamerlan.movies.viewModel.FavouriteMoviesViewModel;

import java.util.List;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class FavouriteMoviesActivity extends AppCompatActivity {

    private BlurView blurViewBar;
    private  ImageButton imageButtonHouseDoor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_movies);
        initViews();
        imageButtonHouseDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = MainActivity.newIntent(FavouriteMoviesActivity.this);
//                startActivity(intent);
                finish();
            }
        });

        RecyclerView recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        MoviesAdapter moviesAdapter = new MoviesAdapter();
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewMovies.setAdapter(moviesAdapter);

        moviesAdapter.setOnMovieClickListener(new MoviesAdapter.OnMovieClickListener() {
            @Override
            public void onMovieClick(Movie movie) {
                Intent intent = MovieDetailActivity.newIntent(FavouriteMoviesActivity.this,
                        movie);
                startActivity(intent);
            }
        });
        FavouriteMoviesViewModel viewModel = new ViewModelProvider(this).get(
              FavouriteMoviesViewModel.class
        );
        viewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                moviesAdapter.setMovies(movies);
            }
        });
        blurBar();

    }

    public void blurBar() {
        float radius = 22f;

        View decorView = getWindow().getDecorView();
        // ViewGroup you want to start blur from. Choose root as close to BlurView in hierarchy as possible.
        ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);

        // Optional:
        // Set drawable to draw in the beginning of each blurred frame.
        // Can be used in case your layout has a lot of transparent space and your content
        // gets a too low alpha value after blur is applied.
        Drawable windowBackground = decorView.getBackground();

        blurViewBar.setupWith(rootView, new RenderScriptBlur(this)) // or RenderEffectBlur
                .setFrameClearDrawable(windowBackground) // Optional
                .setBlurRadius(radius);
    }

    public static Intent newIntent(Context context){
        return new Intent(context, FavouriteMoviesActivity.class);
    }

    private void initViews() {
        imageButtonHouseDoor= findViewById(R.id.imageButtonHouseDoor);
        blurViewBar = (BlurView) findViewById(R.id.blurViewBar);


    }
}