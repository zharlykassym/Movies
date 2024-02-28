package com.tamerlan.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie?token=A4K2TZD-5N04CPW-J1XPPR8-PHR6QYP&rating.kp=7-10&sortField=votes.kp&sortType=-1&limit=50")
//    @GET("movie?token=A4K2TZD-5N04CPW-J1XPPR8-PHR6QYP&rating.kp=4-8&sortField=votes.kp&sortType=1&limit=100")
    Single<MovieResponse> loadMovies(@Query("page") int page);

    @GET("movie?token=A4K2TZD-5N04CPW-J1XPPR8-PHR6QYP&selectFields=name&selectFields=videos&notNullFields=videos.trailers.url")
    Single<VideosResponse> loadTrailers(@Query("id") int id);

    @GET("review?token=A4K2TZD-5N04CPW-J1XPPR8-PHR6QYP&page=1&limit=10&selectFields=id&selectFields=movieId&selectFields=title&selectFields=type&selectFields=review&selectFields=date&selectFields=author&selectFields=authorId&notNullFields=review&sortField=date&sortType=-1")
    Single<ReviewResponse> loadReview(@Query("movieId") int movieId);
}
