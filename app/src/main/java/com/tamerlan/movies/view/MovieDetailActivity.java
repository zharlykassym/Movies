package com.tamerlan.movies.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tamerlan.movies.ApiFactory;
import com.tamerlan.movies.Movie;
import com.tamerlan.movies.R;
import com.tamerlan.movies.Review;
import com.tamerlan.movies.Trailer;
import com.tamerlan.movies.VideosResponse;
import com.tamerlan.movies.model.MovieDao;
import com.tamerlan.movies.model.MovieDatabase;
import com.tamerlan.movies.viewModel.MovieDetailViewModel;

import java.util.List;
import com.tamerlan.movies.model.MovieDatabase;
import com.tamerlan.movies.model.MovieDao;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "movie";
    public static final String TAG = "MovieDetailActivity";
    private MovieDetailViewModel viewModel;
    private ImageView imageViewPoster;
    private TextView textViewTittle;
    private TextView textViewYear;
    private TextView textViewDescription;
    private RecyclerView recyclerViewTrailers;
    private RecyclerView recyclerViewReviews;
    private TrailersAdapter trailersAdapter;
    private ReviewsAdapter reviewsAdapter;
    private ImageButton imageButtonToFavouriteOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);
        setContentView(R.layout.activity_movie_detail);
        initView();
        trailersAdapter = new TrailersAdapter();
        recyclerViewTrailers.setAdapter(trailersAdapter);

        reviewsAdapter = new ReviewsAdapter();
        recyclerViewReviews.setAdapter(reviewsAdapter);

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        Glide.with(this)
                .load(movie.getPoster().getUrl())
                .error("https://st.kp.yandex.net/images/no-poster.gif")
                .into(imageViewPoster);
        textViewTittle.setText(movie.getName());
        textViewYear.setText(String.valueOf(movie.getYear()));
        textViewDescription.setText(movie.getDescription());

        viewModel.loadTrailers(movie.getId());
        viewModel.getTrailers().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                trailersAdapter.setTrailers(trailers);
            }
        });
        trailersAdapter.setOnTrailerClickListener(new TrailersAdapter.OnTrailerClickListener() {
            @Override
            public void onTrailerClick(Trailer trailer) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(trailer.getUrl()));
                startActivity(intent);
            }
        });

        viewModel.loadReviews(movie.getId());
        viewModel.getReviews().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                reviewsAdapter.setReviews(reviews);
                Log.d(TAG, "getReviews.observe works");
            }
        });

        Drawable favouriteOff = ContextCompat.getDrawable(MovieDetailActivity.this, R.drawable.bookmark);
        Drawable favouriteOn = ContextCompat.getDrawable(MovieDetailActivity.this, R.drawable.bookmark_fill);
        viewModel.getFavouriteMovie(movie.getId()).observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movieFromDb) {
                if (movieFromDb == null){
                    imageButtonToFavouriteOff.setImageDrawable(favouriteOff);
                    imageButtonToFavouriteOff.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            viewModel.insertMovie(movie);
                        }
                    });
                } else {
                    imageButtonToFavouriteOff.setImageDrawable(favouriteOn);
                    imageButtonToFavouriteOff.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            viewModel.removeMovie(movie.getId());
                        }
                    });
                }

            }
        });

    }

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    private void initView() {
        imageViewPoster = findViewById(R.id.imageViewPoster);
        textViewTittle = findViewById(R.id.textViewTittle);
        textViewYear = findViewById(R.id.textViewYear);
        textViewDescription = findViewById(R.id.textViewDescription);
        recyclerViewTrailers = findViewById(R.id.recyclerViewTrailers);
        recyclerViewReviews = findViewById(R.id.recyclerViewReviews);
        imageButtonToFavouriteOff = findViewById(R.id.imageButtonToFavouriteOff);
    }
}