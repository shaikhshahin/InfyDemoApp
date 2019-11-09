package com.shahin.infydemoapp.data.network.services;




import com.shahin.infydemoapp.data.network.model.UserResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Shahin on 8/11/2019.
 */
public class UserService {

    private static final String URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/";

    private UserApi mUserApi;

    private static UserService instance;


    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    private UserService() {
        Retrofit mRetrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(URL).build();
        mUserApi = mRetrofit.create(UserApi.class);
    }

    public UserApi getUserApi() {
        return mUserApi;
    }

    public interface UserApi {
        @GET("facts.json")
        Call<UserResponse> getAllUser();
    }

}


