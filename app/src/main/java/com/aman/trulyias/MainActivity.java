package com.aman.trulyias;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity  {
    private VideoView videoView;
    private SeekBar seekBar;
    private ImageButton playPauseButton;
    private boolean isPlaying = false;
    private final Handler handler = new Handler();


    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        videoView = findViewById(R.id.videoView);
        seekBar = findViewById(R.id.seekBar2);
        playPauseButton = findViewById(R.id.button);
        Button showHandoutButton = findViewById(R.id.show_handout);

        Uri uri = Uri.parse("https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4");

        videoView.setVideoURI(uri);

        videoView.setOnPreparedListener(mediaPlayer -> {
            seekBar.setMax(mediaPlayer.getDuration());

            // Start updating the seekbar progress
            updateSeekBar();
        });

        // Play/Pause button click listener
        playPauseButton.setOnClickListener(view -> {
            if (isPlaying) {
                videoView.pause();
                playPauseButton.setImageDrawable(getDrawable(R.drawable.baseline_play_arrow_24));
            } else {
                videoView.start();
                playPauseButton.setImageDrawable(getDrawable(R.drawable.baseline_pause_24));
            }
            isPlaying = !isPlaying;
        });

        // Seekbar listener
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    videoView.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        showHandoutButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, PdfViewActivity.class);
            startActivity(intent);
        });

    }

    private void updateSeekBar() {
        handler.postDelayed(() -> {
            if (videoView != null) {
                int currentPosition = videoView.getCurrentPosition();
                seekBar.setProgress(currentPosition);
                updateSeekBar(); // Call the method recursively to update progress continuously
            }
        }, 10); // Update the progress every 1 second
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null); // Remove callbacks to prevent memory leaks
    }





}