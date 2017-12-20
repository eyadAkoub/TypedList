package me.animate.eyadakoub.com.animateme;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import me.animate.eyadakoub.com.animateme.ShapeClasses.Utils;

public class MainActivity extends AppCompatActivity {


    // Second
    private static final int TimeBetweenCubeCreation = 900;
    private static final int CUBES_SPEED = 9000;

    //Random
    Random random ;
    //ViewGroup
    ViewGroup layout;

    //Screen width
    int width = 0;
    //Screen height
    int height = 0;
    //Handler
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.getInstance(this);
        //get Screen size

        //set width and height
        width = Utils.getInstance().getScreenWidth();
        height = Utils.getInstance().getScreenHeight();

        //random
        random = new Random();
        //fb
        layout = findViewById(R.id.layout_sd);
        // Begin
        CreateCubes();
    }




    // create cubes
    private void CreateCubes() {
        CreateCube(0);
        CreateCube(height / 4);
        CreateCube(height / 2);
        CreateCube(height - height / 4 );
    }

    // this method create CUBE and call animation method
    private void CreateCube(int translationY) {
        //Create view
        View view = new View(this );
        //get random width
        int cube_width = random.nextInt(150) + 150;
        //set width and height
        view.setLayoutParams(new RelativeLayout.LayoutParams(cube_width,
                cube_width));
        //set a color
        view.setBackgroundColor(ClipUtil.getRandomColor("500" , this));
        //set Translate to random (ScreenHeight / 4)
        view.setTranslationY(translationY + random.nextInt(height / 4));
        //add view to the layout
        layout.addView(view , 0);

        //Run animation
        runAnimation(view);
    }

    void postDelay(Runnable runnable , long millisecond){
        handler.postDelayed(runnable , millisecond);
    }

    // Delete passing View from layout
    private Runnable deleteView(final View view){
        return new Runnable() {
            @Override
            public void run() {
                //delete
                layout.removeView(view);
            }
        };
    }
    //this method tale view and animate it
    private void runAnimation(View view) {
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(view , View.ALPHA , 0,1);
        //Create Object Animator to move horizontally and set the Cube to move from left to Right
        ObjectAnimator translateAnimator = ObjectAnimator.ofFloat(view , View.TRANSLATION_X , -100 , width + 100);
        //set Duration between 2s to 3s
        int millisecond = random.nextInt(CUBES_SPEED)+1000;
        //set duration
        translateAnimator.setDuration(millisecond);
        alphaAnimator.setDuration(millisecond / 6);
        //Start animating
        alphaAnimator.start();
        translateAnimator.start();
        //Call Delete Method to Delete
        postDelay(deleteView(view) , millisecond);

    }
    //button on Click
    public void CreateClip(View view) {
        Intent intent = new Intent(this , AttributesActivity.class);
        startActivity(intent);
    }

}
