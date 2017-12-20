package me.animate.eyadakoub.com.animateme;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import me.animate.eyadakoub.com.animateme.ShapeClasses.DisappearClip;
import me.animate.eyadakoub.com.animateme.ShapeClasses.MoveClip;
import me.animate.eyadakoub.com.animateme.ShapeClasses.ResizeClip;
import me.animate.eyadakoub.com.animateme.ShapeClasses.RotateClip;
import me.animate.eyadakoub.com.animateme.ShapeClasses.Shape;
import me.animate.eyadakoub.com.animateme.ShapeClasses.Utils;
import me.animate.eyadakoub.com.animateme.ShapeClasses.move_componenet.Position;
import me.animate.eyadakoub.com.animateme.ShapeClasses.move_componenet.Rotation;
import me.animate.eyadakoub.com.animateme.ShapeClasses.move_componenet.Scale;

//            android:screenOrientation="landscape"


public class ClipCreatorActivity extends AppCompatActivity {

    private static final int PickRequestCode = 92;

    private ClipCreator mCreator = null;

    TextView ErrorMessage , tv_message , tv_percent , _position, second;
    ProgressBar progressBar;

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 34;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_creator);
        //get Utils ?/important
        Utils.getInstance(this);

        tv_message = findViewById(R.id.tv_message);
        tv_percent = findViewById(R.id.tv_percent);

        _position = findViewById(R.id.position);
        second = findViewById(R.id.second);

        ErrorMessage = findViewById(R.id.tv_error);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        Log.e("VideoAttributes" , VideoAttributes.getAttributes().toString());
        Log.e("VideoAttributes" , VideoAttributes.getAttributes().checkGroupOne()
                + "\n" +
                VideoAttributes.getAttributes().checkAll());
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void setLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void setLoadingDone() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void updateData (eventPostMessenger postMessenger){
        tv_message.setText(postMessenger.getMessage());
        tv_percent.setText(postMessenger.getPercent());
    }

    public void pick_images(View view) {
        setLoading();
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT_WATCH) {
            CreateClip();
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED) {
                checkPermission();
            } else {
                CreateClip();
            }
        }
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("image/*");
//        startActivityForResult(intent , PickRequestCode);
    }

    private void CreateClip() {
//        new async().execute();
        if(mCreator != null)
            mCreator.interrupt();

        float fPosition = Float.parseFloat(_position.getText().toString());

        Rotation currentRotation = new Rotation(0);
        Scale currentScale = new Scale(0.2f);
        Position currentPosition = new Position(100,250);
        float currentAlpha = 1;

        Shape shape = new Shape(0,2,currentPosition , currentRotation , currentScale , currentAlpha);
        shape.setBitmapDrawableId(R.drawable.ic_dove);
        shape.setStartPoint(0);
        shape.setEndPoint(3);


        //move
        Position position  = new Position(0,0);
        Position position1 = new Position(fPosition,250);
        MoveClip moveClip = new MoveClip(0,1,position , position1);
        shape.setMoveClip(moveClip);

        //set Clip
        DisappearClip mClip = new DisappearClip(0,1,1,0);
        shape.setDisappearClip(mClip);

        //rotate
        RotateClip clip = new RotateClip(0 , 1,2);
        shape.setRotateClip(clip);

        //set scale
        Scale fScale = new Scale(0.2f);
        Scale lScale = new Scale(0.3f);
        ResizeClip clip1 = new ResizeClip(0,1 ,fScale,lScale);
        shape.setResizeClip(clip1);

        List<Shape> shapes = new ArrayList<>();
        shapes.add(shape);


        mCreator = new ClipCreator(VideoAttributes.getAttributes());

        mCreator.start();

//
//        String s = ClipCreator.getCreator(
//                ClipCreatorActivity.this,
//                "eyad",
//                24,
//                2,
//                400,
//                400,
//                shapes)
//                .createSampleVideo();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mCreator != null)
            mCreator.interrupt();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    CreateClip();
                    Toast.makeText(this, "Done", Toast.LENGTH_LONG);

                } else {
                    CreateClip();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "you must allow the permission", Toast.LENGTH_LONG);
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    void checkPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PickRequestCode && resultCode == RESULT_OK) {

            Toast.makeText(this, data.getData().toString(), Toast.LENGTH_LONG).show();

            //pass the path to the method in ClipCreator

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
