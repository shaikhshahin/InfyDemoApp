package com.shahin.infydemoapp.ui.activity.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;


import com.shahin.infydemoapp.R;
import com.shahin.infydemoapp.data.DataManager;
import com.shahin.infydemoapp.data.network.model.UserData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Shahin on 8/11/2019.
 */

public class MainActivity extends AppCompatActivity implements UserAdapter.OnMovieAdapter {

    UserAdapter movieAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.empty_view)
    TextView emptyView;

    MainViewModel viewModel;

    private MainViewModel createViewModel() {
        MainViewModelFactory factory = new MainViewModelFactory(DataManager.getInstance().getUserService());
        return ViewModelProviders.of(this, factory).get(MainViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        movieAdapter = new UserAdapter(this);
        recyclerView.setAdapter(movieAdapter);

        viewModel = createViewModel();

        viewModel.getLoadingStatus().observe(this, new LoadingObserver());
        viewModel.getUserDatas().observe(this, new MovieObserver());

        viewModel.loadUserDatasNetwork();

    }


    @Override
    public void onMovieClicked(UserData userData) {
         }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    //Observer
    private class LoadingObserver implements Observer<Boolean> {

        @Override
        public void onChanged(@Nullable Boolean isLoading) {
            if (isLoading == null) return;

            if (isLoading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        }
    }

    private class MovieObserver implements Observer<List<UserData>> {

        @Override
        public void onChanged(@Nullable List<UserData> userData) {
            if (userData == null) return;
            movieAdapter.setItems(userData);

            if (userData.isEmpty()) {
                emptyView.setVisibility(View.VISIBLE);
            } else {
                emptyView.setVisibility(View.GONE);
            }
        }
    }
}
