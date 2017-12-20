package me.animate.eyadakoub.com.animateme.ShapeClasses.move_componenet;

import android.app.Activity;
import android.os.Bundle;

import me.animate.eyadakoub.com.animateme.ShapeClasses.Utils;

/**
 * Created by eyad on 28/11/17.
 */

public class Scale {
    //base on overly bitmap width
    public static float _default = 0.15f;
    //percent
    private float percent = _default;
    //Constructor
    public Scale(float percent) {
        this.percent = percent;
    }
    //get width and height in pixel
    public float getWHInPx(float width){
        return width * percent;
    }

    /**
     *
     * @param width
     * @return percent of Bitmap overly to dp
     */
    public float getScaleDpInPx(float width ){

        float mDensity = Utils.getInstance().getDensity();

        float Y = 512 * mDensity;
        float Z = width / Y;
        return Z * percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public float getPercent() {
        return percent;
    }


    @Override
    public String toString() {
        return String.format("Scale Percent is '%s'" , percent);
    }
}
