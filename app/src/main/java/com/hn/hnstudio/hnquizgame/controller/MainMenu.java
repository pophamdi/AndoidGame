package com.hn.hnstudio.hnquizgame.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hn.hnstudio.hnquizgame.R;

public class MainMenu extends AppCompatActivity {
    Button about;
    Button play;
    Button exit,galery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        about=(Button) findViewById(R.id.about);
        play=(Button) findViewById(R.id.play);
        exit=(Button) findViewById(R.id.exit);
        galery=(Button) findViewById(R.id.galery);


        galery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainMenu.this,Galery.class);
                startActivity(intent);
            }
        });



        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameAct=new Intent(MainMenu.this,Play.class);
                startActivity(gameAct);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameAct=new Intent(MainMenu.this,Presentation.class);
                startActivity(gameAct);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
    }
}
