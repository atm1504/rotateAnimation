package com.atm1504.rotatewheel;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    Animation rotateAnimation;
    CircleImageView centerImage;
    //    ImageView imageView;
    View circularView;
    List<Integer> images;
    Handler rotateHandler, imageHandler;
    Runnable rotateRunnable, imageRunnable;
    int time = 7000;
    int indexCLicked = 0;
    private static final String TAG = "Atreyee";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circularView = (View) findViewById(R.id.circularLayout);

//        imageView = (ImageView) findViewById(R.id.imageView);
        centerImage = (CircleImageView) findViewById(R.id.centerImage);
//        images = {R.drawable.icon1};
        images = new ArrayList<Integer>();
        images.add(R.drawable.icon1);
        images.add(R.drawable.icon2);
        images.add(R.drawable.icon3);
        images.add(R.drawable.icon4);
        images.add(R.drawable.icon5);
        images.add(R.drawable.icon6);
        images.add(R.drawable.icon7);
        images.add(R.drawable.icon8);

        // Initialise the handler
        imageHandler = new Handler();
        rotateHandler = new Handler();

        centerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Clicked for id: " + indexCLicked, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Clicked for id:" + indexCLicked);
            }
        });

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
                circularView.startAnimation(rotateAnimation);
                rotateHandler.postDelayed(this, time);
            }
        };

        rotateHandler.postDelayed(rotateRunnable, 1);
    }

    private void imageAnimation() {
        imageRunnable = new Runnable() {
            int i = 0;

            @Override
            public void run() {
                centerImage.setImageResource(images.get(i));
                indexCLicked = i;
                i++;
                if (i >= images.size()) {
                    i = 0;
                }
                imageHandler.postDelayed(this, time / images.size());
            }
        };
        imageHandler.postDelayed(imageRunnable, 10);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rotateHandler.removeCallbacks(rotateRunnable);
        imageHandler.removeCallbacks(imageRunnable);
    }
}