package com.hn.hnstudio.hnquizgame.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.hn.hnstudio.hnquizgame.R;

public class Presentation extends AppCompatActivity {
    private Button playVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);
        playVideo=(Button)findViewById(R.id.playVideo);

        playVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameAct=new Intent(Presentation.this,AboutActivity.class);
                startActivity(gameAct);
            }
        });
    }
}
