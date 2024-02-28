package com.tamerlan.movies;

import com.google.gson.annotations.SerializedName;

public class Review {
    @SerializedName("id")
    private int id;
    @SerializedName("movieId")
    private int movieId;
    @SerializedName("title")
    private String title;
    @SerializedName("type")
    private String type;
    @SerializedName("review")
    private String review;
    @SerializedName("date")
    private String date;
    @SerializedName("author")
    private String author;
    @SerializedName("authorId")
    private int authorId;

    public Review(int id, int movieId, String title, String type, String review, String date, String author, int authorId) {
        this.id = id;
        this.movieId = movieId;
        this.title = title;
        this.type = type;
        this.review = review;
        this.date = date;
        this.author = author;
        this.authorId = authorId;
    }

    public int getId() {
        return id;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getReview() {
        return review;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    public int getAuthorId() {
        return authorId;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", review='" + review + '\'' +
                ", date='" + date + '\'' +
                ", author='" + author + '\'' +
                ", authorId=" + authorId +
                '}';
    }
}
