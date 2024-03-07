package com.tamerlan.movies.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tamerlan.movies.Movie;
import com.tamerlan.movies.model.MovieDao;
import com.tamerlan.movies.model.MovieDatabase;

import java.util.List;

public class FavouriteMoviesViewModel extends AndroidViewModel {
    private final MovieDao movieDao;
    public FavouriteMoviesViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getInstance(application).movieDao();
    }

    public LiveData<List<Movie>> getMovies(){
        return movieDao.getAllFavouriteMovies();
    }
}
