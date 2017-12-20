package me.animate.eyadakoub.com.animateme.ShapeClasses;

/**
 * Created by eyad on 22/11/17.
 */

public class DisappearClip extends Clip{

    private float firstPercent;
    private float lastPercent;

    public DisappearClip(float startPoint, float endPoint, float firstPercent, float lastPercent) {
        super(startPoint, endPoint);
        this.firstPercent = firstPercent;
        this.lastPercent = lastPercent;
    }

    public void setTime(float begin , float end){
        setStartPoint(begin);

        setEndPoint(end);
    }


    public float getFirstPercent() {
        return firstPercent;
    }

    public void setFirstPercent(float firstPercent) {
        this.firstPercent = firstPercent;
    }

    public float getLastPercent() {
        return lastPercent;
    }

    public void setLastPercent(float lastPercent) {
        this.lastPercent = lastPercent;
    }

}
