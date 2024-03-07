package com.tamerlan.movies.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.tamerlan.movies.Movie;
import com.tamerlan.movies.R;
import com.tamerlan.movies.viewModel.MainViewModel;

import java.util.List;


import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private MainViewModel mainViewModel;

    private RecyclerView recyclerViewMovies;
    private MoviesAdapter moviesAdapter;
    private ProgressBar progressBarLoading;
    private BlurView blurViewBar;
    private ImageButton imageButtonFavourites;


    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        blurBar();
        moviesAdapter = new MoviesAdapter();
        recyclerViewMovies.setAdapter(moviesAdapter);
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, 3));
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                moviesAdapter.setMovies(movies);
            }
        });

        mainViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    progressBarLoading.setVisibility(View.VISIBLE);
                } else {
                    progressBarLoading.setVisibility(View.GONE);
                }
            }
        });
        moviesAdapter.setOnReachEndListener(new MoviesAdapter.OnReachEndListener() {
            @Override
            public void onReachEnd() {
                mainViewModel.loadMovies();
            }
        });
        moviesAdapter.setOnMovieClickListener(new MoviesAdapter.OnMovieClickListener() {
            @Override
            public void onMovieClick(Movie movie) {
                Intent intent = MovieDetailActivity.newIntent(MainActivity.this, movie);
                startActivity(intent);
            }
        });
        imageButtonFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = FavouriteMoviesActivity.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });

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
        return new Intent(context, MainActivity.class);
    }

    private void initViews() {
        progressBarLoading = findViewById(R.id.progressBarLoading);
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        blurViewBar = (BlurView) findViewById(R.id.blurViewBar);
        imageButtonFavourites = findViewById(R.id.imageButtonFavourites);

    }
}