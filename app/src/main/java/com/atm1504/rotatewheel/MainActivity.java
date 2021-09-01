package com.atm1504.rotatewheel;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    Animation rotateAnimation;
    CircleImageView centerImage;
    ImageView imageView;
    List<Integer> images;
    Handler rotateHandler, imageHandler;
    Runnable rotateRunnable, imageRunnable;
    int time = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        images.add(R.drawable.icon1);
        images.add(R.drawable.icon2);
        images.add(R.drawable.icon3);
        images.add(R.drawable.icon4);
        images.add(R.drawable.icon5);
        images.add(R.drawable.icon6);

        // Initialise the handler
        imageHandler = new Handler();
        rotateHandler = new Handler();

        startAnimation();
    }

    // FUcntion to control the entire animation
    private void startAnimation() {
        rotateAnimation();
        imageAnimation();
    }

    private void rotateAnimation() {
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        rotateRunnable = new Runnable() {
            @Override
            public void run() {
                imageView.startAnimation(rotateAnimation);
            }
        };

        rotateHandler.postDelayed(rotateRunnable, time);
    }

//    Handler handler = new Handler();
//    Runnable runnable = new Runnable() {
//        int i = 0;
//
//        public void run() {
//            imageView1.setImageResource(imageArray[i]);
//            i++;
//            if (i > imageArray.length - 1) {
//                i = 0;
//            }
//            handler.postDelayed(this, 2000);
//        }
//    };
//            handler.postDelayed(runnable, 2000);

    private void imageAnimation() {
        // Think hhow u r going to handle the changing of image on center


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rotateHandler.removeCallbacks(rotateRunnable);
    }
}