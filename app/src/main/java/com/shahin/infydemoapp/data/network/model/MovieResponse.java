package com.shahin.infydemoapp.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Shahin on 8/11/2019.
 */

public class MovieResponse {

    @Expose
    @SerializedName("rows")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }
}
