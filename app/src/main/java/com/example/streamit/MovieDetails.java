package com.example.streamit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieDetails extends AppCompatActivity {

    ImageView movieImage;
    Button playButton;
    TextView movieName;
    String mName, mId, mImage, mFileUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        movieName = findViewById(R.id.movie_name);
        movieImage = findViewById(R.id.movie_image);
        playButton = findViewById(R.id.play_button);

        // get data from intent

        mId = getIntent().getStringExtra("movieId");
        mName = getIntent().getStringExtra("movieName");
        mImage = getIntent().getStringExtra("movieImageUrl");
        mFileUrl = getIntent().getStringExtra("movieFile");

        // set data to layout
        Glide.with(this).load(mImage).into(movieImage);
        movieName.setText(mName);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MovieDetails.this, VideoPlayerActivity.class);
                i.putExtra("url", mFileUrl);
                startActivity(i);
            }
        });
    }
}