package me.animate.eyadakoub.com.animateme.ShapeClasses;

/**
 * Created by eyad on 22/11/17.
 */

public class RotateClip extends Clip {

    public RotateClip(float startPoint, float endPoint, float speedInSecond) {
        super(startPoint, endPoint);
        this.speedInSecond = speedInSecond;
    }

    private float speedInSecond;


    public void setTime(float begin , float end){
        setStartPoint(begin);
        setEndPoint(end);
    }

    public float getSpeedInSecond() {
        return speedInSecond;
    }

    public void setSpeedInSecond(float speedInSecond) {
        this.speedInSecond = speedInSecond;
    }

}
