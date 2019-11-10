package com.shahin.infydemoapp.ui.activity.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.shahin.infydemoapp.R;
import com.shahin.infydemoapp.data.network.model.UserData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Shahin on 9/11/2019.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    public interface OnMovieAdapter{
        void onMovieClicked(UserData userData);
    }

    private List<UserData> mItems;
    private OnMovieAdapter mListener;

    public UserAdapter(OnMovieAdapter listener) {
        mListener = listener;
        mItems = new ArrayList<>();
    }

    public void setItems(List<UserData> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserData userData = getItem(position);

        holder.setOnClickListener(userData);
        holder.setTitle(userData.getTitle());
        holder.setImage(userData.getImage());
        holder.setDescription(userData.getDescription());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private UserData getItem(int position) {
        return mItems.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.image)
        AppCompatImageView image;
        @BindView(R.id.title) TextView title;
        @BindView(R.id.desc) TextView desc;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setTitle(String title) {
            this.title.setText(title);
        }

        public void setImage(String imageUrl) {
            Glide.with(itemView.getContext()).load(imageUrl).into(image);
        }

        private void setDescription(String description) {
            desc.setText(description);
        }

        private void setOnClickListener(UserData userData) {
            itemView.setTag(userData);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onMovieClicked((UserData) view.getTag());
        }
    }
}
