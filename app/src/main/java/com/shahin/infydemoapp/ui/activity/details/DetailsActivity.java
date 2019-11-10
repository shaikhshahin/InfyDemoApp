package com.shahin.infydemoapp.ui.activity.details;


import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.shahin.infydemoapp.R;
import com.shahin.infydemoapp.data.network.model.UserData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Shahin on 10/11/2019.
 */
public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.image)
    AppCompatImageView image;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.desc)
    TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        DetailsViewModel viewModel = createViewModel();

        viewModel.getMovie().observe(this, new MovieObserver());

        viewModel.loadMovieData(getIntent());
    }

    private DetailsViewModel createViewModel() {
        return ViewModelProviders.of(this).get(DetailsViewModel.class);
    }

    private class MovieObserver implements Observer<UserData> {
        @Override
        public void onChanged(@Nullable UserData userData) {
            if (userData == null) return;

            title.setText(userData.getTitle());
            desc.setText(userData.getDescription());
            Glide.with(getApplicationContext()).load(userData.getImage()).into(image);
        }
    }

}

