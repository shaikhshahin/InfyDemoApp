package com.shahin.infydemoapp.ui.activity.details;

import android.content.Intent;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shahin.infydemoapp.data.network.model.UserData;


/**
 * Created by Shahin on 10/11/2019.
 */
public class DetailsViewModel extends ViewModel {

    MutableLiveData<UserData> userData;

    public DetailsViewModel() {
        this.userData = new MutableLiveData<>();
    }

    public void loadMovieData(Intent intent) {
        assert intent.getExtras() != null;
        UserData movieExtra = intent.getExtras().getParcelable("user");

        userData.postValue(movieExtra);
    }

    public MutableLiveData<UserData> getMovie() {
        return userData;
    }
}
