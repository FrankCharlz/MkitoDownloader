package com.mj.mkitodl.activities;

import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.mj.mkitodl.R;
import com.mj.mkitodl.models.Song;
import com.mj.mkitodl.utils.M;
import com.squareup.picasso.Picasso;

/**
 * Created by Frank on 1/5/2016.
 */
public class SongActivity extends AppCompatActivity  {
    private Button btnPlay, btnDownload;
    private ImageView backgroundImageView;
    private SeekBar seekBar;
    private Song song;
    private MediaPlayer mediaPlayer;
    private MediaControl mediaControl;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_details);

        song = (Song) getIntent().getSerializableExtra(Song.SERIALIZED_CLASS);
        M.log(song.toString());

        /*
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;
        */

        backgroundImageView = (ImageView) findViewById(R.id.iv_bg_song_activity);
        Picasso
                .with(this)
                .load(song.getImageUrl())
                .placeholder(R.drawable.mk_header)
                .fit().centerCrop()
                .into(backgroundImageView);

        mediaControl = new MediaControl();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnBufferingUpdateListener(mediaControl);
        mediaPlayer.setOnCompletionListener(mediaControl);


        initView();



    }

    private void initView() {

        btnDownload = (Button) findViewById(R.id.btn_download);
        btnPlay = (Button) findViewById(R.id.btn_play_pause);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start web view for downloading.............
            }
        });

        btnPlay.setOnClickListener(mediaControl);

        seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar.setMax(100);
        seekBar.setOnTouchListener(mediaControl);


    }

    class MediaControl implements View.OnTouchListener, View.OnClickListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnBufferingUpdateListener {

        private long song_length = 1;

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_play_pause) {
                playOrPause();
            }

        }

        private void seekBarPrimaryProgressUpdate() {
            int progress = (int)((float)100*mediaPlayer.getCurrentPosition()/song_length);
            seekBar.setProgress(progress);

            if (mediaPlayer.isPlaying()) {
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        seekBarPrimaryProgressUpdate(); //calls itself evry 1 second
                    }
                };
                handler.postDelayed(r, 1000);
            }
        }

        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            btnPlay.setText("PLAY");
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (view.getId() == R.id.seekbar) {
                if (mediaPlayer.isPlaying()) {
                    int song_pos =(int)(((float)seekBar.getProgress()/100)*song_length);
                    mediaPlayer.seekTo(song_pos);
                }
            }

            return false;
        }

        @Override
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int progress) {
            seekBar.setSecondaryProgress(progress);
        }

        private void playOrPause() {
            try {
                mediaPlayer.setDataSource(song.getSongpreviewUrl());
                mediaPlayer.prepare();
                song_length = mediaPlayer.getDuration();

            } catch (Exception e) {
                M.log("Data src fail : " + e.getLocalizedMessage());
                e.printStackTrace();
            }

            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
                btnPlay.setText("PAUSE");

            } else {
                mediaPlayer.pause();
                btnPlay.setText("PLAY");
            }

            seekBarPrimaryProgressUpdate();
        }
    }




}
