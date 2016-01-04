package com.mj.mkitodl.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mj.mkitodl.R;

/**
 * Created by Frank on 1/4/2016.
 */
public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {
    @Override
    public HomeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_song_item,parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }



    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder  extends  RecyclerView.ViewHolder{
        public TextView mTextView;
        public CardView mCardView;
        public ImageView mImageView;
        public ViewHolder(View v) {
            super(v);
            mCardView = (CardView) v;
            mTextView = (TextView) v.findViewById(R.id.tv_song_item);
            mImageView = (ImageView) v.findViewById(R.id.img_view_song_item);
        }
    }
}
