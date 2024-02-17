package com.tamerlan.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Videos implements Serializable {
    @SerializedName("videos")
    private TrailersList trailersList;

    public Videos(TrailersList trailersList) {
        this.trailersList = trailersList;
    }

    public TrailersList getTrailersList() {
        return trailersList;
    }

    @Override
    public String toString() {
        return "Videos{" +
                "trailersList=" + trailersList +
                '}';
    }
}
