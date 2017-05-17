package com.example.ch.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ch.challenger.R;
import com.example.ch.model.Challenge;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Challenge> mDataset;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_challenge_name)
        public TextView text_challenge_name;

        @Bind(R.id.text_challenge_fullname)
        public TextView text_challenge_fullname;

        @Bind(R.id.event_icon)
        public ImageView event_icon;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }
    }

    public RecyclerAdapter(List<Challenge> challenges, Context context) {
        mDataset = challenges;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", java.util.Locale.getDefault());
//        holder.text_challenge_name.setText(mDataset.get(position).getId());
        holder.text_challenge_name.setText(mDataset.get(position).getShort_description());
        holder.text_challenge_fullname.setText(context.getResources().getString(R.string.info) + ": " + mDataset.get(position).getFull_description());

//        Uri uri = Uri.parse(baseUrl + "/events/icon=" + mDataset.get(position).getId());
//        Picasso.with(context)
//                .load(uri)
//                .placeholder(R.drawable.empty)
//                .error(R.drawable.empty)
//                .into(holder.event_icon);

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setmDataset(List<Challenge> mDataset) {
        this.mDataset = mDataset;
    }
}