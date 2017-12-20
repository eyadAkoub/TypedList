package me.animate.eyadakoub.com.animateme.ShapeClasses;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

/**
 * Created by eyad on 01/12/17.
 */

public class Utils {

    private int ScreenWidth;
    private int ScreenHeight;
    private float density;

    private static Utils mUtils = null;

    private Utils(Activity activity) {
        getHW(activity);
    }

    public void getHW(Activity mActivity){
        //DisplayMetrics to handle Screen attributes
        DisplayMetrics displayMetrics = new DisplayMetrics();
        //get Screen attributes
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        //set Screen Width
        ScreenWidth = displayMetrics.widthPixels;
        //set ScreenHeight
        ScreenHeight = displayMetrics.heightPixels;
        //set density
        density = displayMetrics.density;
    }


    public static Utils getInstance(Activity activity){
        if(mUtils == null) {
            mUtils = new Utils(activity);
        }
        return mUtils;
    }

    public static Utils getInstance() throws UnsupportedOperationException{
        if(mUtils ==null)
            throw new UnsupportedOperationException("mUtils did not exist yet!");

        return mUtils;
    }


    // getter
    public int getScreenWidth() {
        return ScreenWidth;
    }

    public int getScreenHeight() {
        return ScreenHeight;
    }

    public float getDensity() {
        return density;
    }

}
