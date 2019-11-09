package com.shahin.infydemoapp.ui.activity.main;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.shahin.infydemoapp.data.network.services.UserService;


/**
 * Created by Shahin on 9/11/2019.
 */
public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final UserService userService;

    public MainViewModelFactory(UserService userService) {
        this.userService = userService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(userService);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
