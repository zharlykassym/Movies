package com.tamerlan.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideosResponse {
    @SerializedName("docs")
    private List<Videos> videos;

    public VideosResponse(List<Videos> videos) {
        this.videos = videos;
    }

    public List<Videos> getVideos() {
        return videos;
    }

    @Override
    public String toString() {
        return "VideosResponse{" +
                "videos=" + videos +
                '}';
    }
}
