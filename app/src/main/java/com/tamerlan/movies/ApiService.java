package com.tamerlan.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie?token=A4K2TZD-5N04CPW-J1XPPR8-PHR6QYP&rating.kp=7-10&sortField=votes.kp&sortType=-1&limit=20")
//    @GET("movie?token=A4K2TZD-5N04CPW-J1XPPR8-PHR6QYP&rating.kp=4-8&sortField=votes.kp&sortType=1&limit=100")
    Single<MovieResponse> loadMovies(@Query("page") int page);
}
