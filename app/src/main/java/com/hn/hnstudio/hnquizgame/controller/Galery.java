package com.hn.hnstudio.hnquizgame.controller;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.hn.hnstudio.hnquizgame.R;

public class Galery extends AppCompatActivity {
    ImageSwitcher myImageSwitcher;
    ImageButton nextImageButton;

    int imageSwitcherImages[] = {R.drawable.building, R.drawable.room,R.drawable.student,};

    int switcherImage = imageSwitcherImages.length;
    int counter = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galery);

        myImageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        nextImageButton = (ImageButton) findViewById(R.id.nextImageButton);

        myImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView switcherImageView = new ImageView(getApplicationContext());
                switcherImageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT
                ));
                switcherImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                switcherImageView.setImageResource(R.drawable.building);
                //switcherImageView.setMaxHeight(100);
                return switcherImageView;
            }
        });

        Animation animationOut = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        Animation animationIn = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);

        myImageSwitcher.setOutAnimation(animationOut);
        myImageSwitcher.setInAnimation(animationIn);
    }

    public void nextImageButton(View view) {
        counter++;
        if (counter == switcherImage)
            counter = 0;
        myImageSwitcher.setImageResource(imageSwitcherImages[counter]);
    }
}
