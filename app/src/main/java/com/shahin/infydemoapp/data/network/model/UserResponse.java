package com.shahin.infydemoapp.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.shahin.infydemoapp.utils.AppConstants;

import java.util.List;

/**
 * Created by Shahin on 8/11/2019.
 */

public class UserResponse {

    @Expose
    @SerializedName(AppConstants.ROWS)
    private List<UserData> userData;

    public List<UserData> getUserData() {
        return userData;
    }
}
