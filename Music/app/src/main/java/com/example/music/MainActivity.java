package com.example.music;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        media = MediaPlayer.create(this,R.raw.song);

        Button button1 = (Button) findViewById(R.id.play);

        Button button2 = (Button) findViewById(R.id.pause);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 media.start();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                media.pause();
            }
        });

    }
}
