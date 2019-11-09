package com.shahin.infydemoapp.ui.activity.main;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shahin.infydemoapp.data.network.model.UserData;
import com.shahin.infydemoapp.data.network.model.UserResponse;
import com.shahin.infydemoapp.data.network.services.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shahin on 9/11/2019.
 */
public class MainViewModel extends ViewModel {

    private MutableLiveData<List<UserData>> userList;
    private MutableLiveData<Boolean> isLoading;

    private UserService userService;

    MainViewModel(UserService UserDataService) {
        this.userService = UserDataService;
        userList = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
    }

    MutableLiveData<List<UserData>> getUserDatas() {
        return userList;
    }
    MutableLiveData<Boolean> getLoadingStatus() {
        return isLoading;
    }

    void loadUserDatasNetwork() {
        setIsLoading(true);

        Call<UserResponse> UserDataCall = userService.getUserApi().getAllUser();
        UserDataCall.enqueue(new UserDataCallback());
    }
    void loadUserDataLocal() {
        setIsLoading(true);

        String name = "Breaking Bad";
        String image = "https://coderwall-assets-0.s3.amazonaws.com/uploads/picture/file/622/breaking_bad_css3_svg_raw.png";

        List<UserData> UserDatas = new ArrayList<>();
        UserDatas.add(new UserData(name,image,name));
        UserDatas.add(new UserData(name,image,name));
        UserDatas.add(new UserData(name,image,name));
        setUserDatas(UserDatas);
    }
    void showEmptyList() { setUserDatas(Collections.<UserData>emptyList()); }

    private void setIsLoading(boolean loading) {
        isLoading.postValue(loading);
    }
    private void setUserDatas(List<UserData> UserDatas) {
        setIsLoading(false);
        userList.postValue(UserDatas);
    }

    /**
     * Callback
     **/
    private class UserDataCallback implements Callback<UserResponse> {

        @Override
        public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
            UserResponse UserDataResponse = response.body();

            if (UserDataResponse != null) {
                setUserDatas(UserDataResponse.getUserData());
            } else {
                setUserDatas(Collections.<UserData>emptyList());
            }
        }

        @Override
        public void onFailure(Call<UserResponse> call, Throwable t) {
            setUserDatas(Collections.<UserData>emptyList());

        }
    }
}
