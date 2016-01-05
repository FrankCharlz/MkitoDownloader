package com.mj.mkitodl.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mj.mkitodl.R;
import com.mj.mkitodl.activities.SongActivity;
import com.mj.mkitodl.models.Song;
import com.mj.mkitodl.utils.CircleTransform;
import com.squareup.picasso.Picasso;

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
    public HomeListAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        context = parent.getContext(); // context imeibwa..
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_song_item,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar
                        .make(holder.mCardView.getRootView(), songs.get(position).toString(), Snackbar.LENGTH_SHORT)
                        .show();
                startSongActivity(songs.get(position));

            }
        });

        holder.mTvName.setText(songs.get(position).getSongName());
        holder.mTvArtist.setText(songs.get(position).getArtistName());
        Picasso
                .with(context)
                .load(songs.get(position).getImageUrl())
                .resize(64, 0)
                .transform(new CircleTransform())
                .into(holder.mImgView);

    }

    private void startSongActivity(Song song) {
        Intent intent = new Intent(context, SongActivity.class);
    }


    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ViewHolder  extends  RecyclerView.ViewHolder{
        public TextView mTvName, mTvArtist;
        public ImageView mImgView;
        public RelativeLayout mCardView;
        public ViewHolder(View v) {
            super(v);
            mCardView = (RelativeLayout) v;
            mTvName = (TextView) v.findViewById(R.id.tv_song_item_name);
            mTvArtist = (TextView) v.findViewById(R.id.tv_song_item_artist);
            mImgView = (ImageView) v.findViewById(R.id.imgv_song_item);
        }
    }
}
