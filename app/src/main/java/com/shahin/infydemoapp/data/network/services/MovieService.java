package com.shahin.infydemoapp.data.network.services;



import com.shahin.infydemoapp.data.network.model.MovieResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Shahin on 8/11/2019.
 */
public class MovieService {

    private static final String URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"; //"http://demo6483760.mockable.io/";

    private MovieApi mMovieApi;

    private static MovieService instance;


    public static MovieService getInstance() {
        if (instance == null) {
            instance = new MovieService();
        }
        return instance;
    }

    private MovieService() {
        Retrofit mRetrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(URL).build();
        mMovieApi = mRetrofit.create(MovieApi.class);
    }

    public MovieApi getMovieApi() {
        return mMovieApi;
    }

    public interface MovieApi {
        @GET("facts.json")
        Call<MovieResponse> getAllMovie();
    }

}


