package com.mj.mkitodl.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mj.mkitodl.R;
import com.mj.mkitodl.models.Song;
import com.mj.mkitodl.utils.M;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

/**
 * Created by Frank on 1/4/2016.
 */
public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {
    private final List<Song> songs;
    private Context context;

    public HomeListAdapter(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public HomeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext(); // context imeibwa..
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_song_item,parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTextView.setText(songs.get(position).toString());
        Target t = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                holder.mImageView.setImageBitmap(bitmap);
                M.log("Bitmap loaded at pos " + position);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                M.log("Bitmap failde pos "+position);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                M.log("Picasso preparing at pos "+position);
            }
        };

    }



    @Override
    public int getItemCount() {
        return songs.size();
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
