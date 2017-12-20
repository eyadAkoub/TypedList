package me.animate.eyadakoub.com.animateme;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.List;

import me.animate.eyadakoub.com.animateme.ShapeClasses.Shape;

/**
 * Created by eyad on 23/11/17.
 *
 */

public class VideoAttributes {



    private static VideoAttributes mAttributes = null;
    private VideoAttributes() {
    }
    public static VideoAttributes getAttributes(){

        if(mAttributes == null)
            mAttributes = new VideoAttributes();

        return mAttributes;
    }


    //group one
    private int FPS = 0;
    private int SECONDS = 0;
    private int WIDTH = 0;
    private int HEIGHT = 0;

    public static VideoAttributes getmAttributes() {
        return mAttributes;
    }

    public int getFPS() {
        return FPS;
    }

    public int getSECONDS() {
        return SECONDS;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public String getTITLE() {
        return TITLE;
    }

    public int getBACKGROUND_COLOR() {
        return BACKGROUND_COLOR;
    }

    //set group one
    public void setGroupOne(int FPS , int SECONDS , int WIDTH , int HEIGHT){
        this.FPS = FPS;
        this.SECONDS = SECONDS;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }
    //check group one
    public boolean checkGroupOne(){
        return !(FPS == 0 ||
                SECONDS == 0 ||
                WIDTH == 0 ||
                HEIGHT == 0);
    }

    //Group two
    private String TITLE;
    private int BACKGROUND_COLOR = 0;

    //set group two
    public void setGroupTwo(String TITLE , int BACKGROUND_COLOR){
        this.TITLE = TITLE;
        this.BACKGROUND_COLOR = BACKGROUND_COLOR;
    }

    //check group two
    public boolean checkGroupTwo(){
        return !(TextUtils.isEmpty(TITLE)
                || BACKGROUND_COLOR == 0
                );
    }

    //check all
    public boolean checkAll(){
        return (checkGroupOne() && checkGroupTwo());
    }

    @Override
    public String toString() {
        return String.format(
                "'%s' FPS , '%s' Second , '%s' Width , '%s' Height , '%s' Title , '%s' Background Color Resource Id "
                ,FPS
                ,SECONDS
                ,WIDTH
                ,HEIGHT
                ,TITLE
                ,BACKGROUND_COLOR
        );
    }
}