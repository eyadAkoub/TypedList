package me.animate.eyadakoub.com.animateme;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import me.animate.eyadakoub.com.animateme.databinding.ActivityAttributesBinding;


public class AttributesActivity extends AppCompatActivity {

    //duration
    private static final int aspectDuration = 500;

    private static int PIXEL_WIDTH;
    private static int PIXEL_HEIGHT;

    //data binding classes
    attributes_model model = new attributes_model();
    ActivityAttributesBinding binding;


    //aspect view and it's width
    View aspect_ratio_view;
    TextView aspect_view_value;
    //default width
    float aspectWidth;
    // Ratio values
    float[][] aspect_ratio;
    //current position on aspect ratio array
    int currentPosition = 0;

    //1-1 4-3 1-2.39 4-5 16-9
    int[] default_width_pixel = {600, 640, 800, 600, 1280};

    EditText width_edit;


    //max and min value of FPS
    int max_fps, min_fps;
    //second Permissible values
    int[] secondsMainValues;
    //current values of second and fps
    int currentSecond;
    int currentFps;


    //on create
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_attributes);

        //set views id
        getViews();

        // get aspect values
        aspectWidth = aspect_ratio_view.getScaleX();
        aspect_ratio = getAspectRatioValues();

        //prepare fps, second needs
        prepare_fps_seconds();
        //prepare Ratio
        changeViewsConnectingToAspectStatus();
    }

    private void prepare_fps_seconds() {
        //get max and min value for frames
        max_fps = getResources().getInteger(R.integer.max_fps);
        min_fps = getResources().getInteger(R.integer.min_fps);
        //get default values of second
        secondsMainValues = getResources().getIntArray(R.array.second_values);

        //set default values
        currentFps = 24;
        currentSecond = secondsMainValues[1];

        // notify change data
        notifyDataHasChanged();
    }

    private void getViews() {
        width_edit = findViewById(R.id.width_value);
        aspect_ratio_view = findViewById(R.id.aspect_id);
        aspect_view_value = findViewById(R.id.tv_aspect_value);


        width_edit.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.e("afterTextChanged" , "changed");
                if(editable.length() > 0) {
                    if(editable.length() > 4){
                        editable.delete(4, 5);
                        return;
                    }
                    PIXEL_WIDTH = Integer.parseInt(editable.toString());
                    PIXEL_HEIGHT = (int) (PIXEL_WIDTH * (
                            aspect_ratio[1][currentPosition] /
                                    aspect_ratio[0][currentPosition]
                    ));
                    notifyDataHasChanged();
                }
            }
        });

    }

    public void change_aspect(View view) {
        //change position
        if (currentPosition < aspect_ratio[0].length - 1)
            currentPosition++;
        else
            currentPosition = 0;

        //get width and height from position
        float width = aspect_ratio[0][currentPosition];
        float height = aspect_ratio[1][currentPosition];

        //get default width and set it to scale_x and scale_y
        float SCALE_X = aspectWidth;
        float SCALE_Y = aspectWidth;

        //condition ;>
        if (width < height) {
            SCALE_X = aspectWidth * (width //4
                    / height// 5
            );
        } else if (height < width) {//height greater
            SCALE_Y = aspectWidth * (
                    height / // 3
                            width  // 4
            );
        }

        runAnimationOnAspectView(view, SCALE_Y, View.SCALE_Y);
        runAnimationOnAspectView(view, SCALE_X, View.SCALE_X);

        changeViewsConnectingToAspectStatus();
    }

    private void changeViewsConnectingToAspectStatus() {
        float width = aspect_ratio[0][currentPosition];
        float height = aspect_ratio[1][currentPosition];

        PIXEL_WIDTH = default_width_pixel[currentPosition];

        aspect_view_value.setText(String.valueOf(width + " / " + height));
        width_edit.setText(String.valueOf(PIXEL_WIDTH));

        PIXEL_HEIGHT = (int) (PIXEL_WIDTH * (height / width));
        notifyDataHasChanged();
    }

     //run animation
    private void runAnimationOnAspectView(View view, float SCALE_Y, Property<View, Float> property_name) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, property_name, SCALE_Y);
        animator.setDuration(aspectDuration);
        animator.start();
    }

    //notify data
    private void notifyDataHasChanged() {
        model.setFPS(String.valueOf(currentFps));
        model.setSecond(String.valueOf(currentSecond));
        model.setHeight(String.valueOf(PIXEL_HEIGHT));
        binding.setModel(model);
    }

    //add 1 to fps & make sure that does not be greater than defined max
    public void right_fps(View view) {
        if (currentFps < max_fps)
            currentFps++;
        else
            currentFps = min_fps;

        notifyDataHasChanged();
    }

    public void left_fps(View view) {
        if (currentFps > min_fps)
            currentFps--;
        else
            currentFps = max_fps;

        notifyDataHasChanged();
    }

    public void right_second(View view) {

        for (int i = 0; i < secondsMainValues.length; i++) {
            if (secondsMainValues[i] == currentSecond) {
                if (i == secondsMainValues.length - 1) {
                    currentSecond = secondsMainValues[0];
                    break;
                }
                currentSecond = secondsMainValues[i + 1];
                break;
            }
        }

        //notify data has change
        notifyDataHasChanged();
    }

    public void left_second(View view) {
        for (int i = secondsMainValues.length - 1; i > -1; i--) {
            if (currentSecond == secondsMainValues[i]) {
                if (i == 0) {
                    currentSecond = secondsMainValues[secondsMainValues.length - 1];
                    break;
                }
                currentSecond = secondsMainValues[i - 1];
                break;
            }
        }
        notifyDataHasChanged();
    }

    public float[][] getAspectRatioValues() {


        return new float[][]{
                {
                        1, //one
                        4,//two
                        1,//three
                        4,//four
                        16//five
                }
                ,
                {
                        1,//one
                        3,//two
                        2.39f//three
                        , 5//four
                        , 9//five
                }
        };

    }

    public void nextOnClick(View view) {
        VideoAttributes.getAttributes().setGroupOne(currentFps , currentSecond , PIXEL_WIDTH , PIXEL_HEIGHT);
        Intent intent = new Intent(this , TitleActivity.class);
        startActivity(intent);
    }

}