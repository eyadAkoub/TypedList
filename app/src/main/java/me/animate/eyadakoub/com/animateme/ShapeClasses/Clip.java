package me.animate.eyadakoub.com.animateme.ShapeClasses;

/**
 * Created by eyad on 19/11/17.
 */

public class Clip
{
    private static float _DEFAULT = 49384.34f;
    private float StartPoint = _DEFAULT;
    private float EndPoint = _DEFAULT;

    public Clip(float startPoint, float endPoint) {
        this.StartPoint = startPoint;
        this.EndPoint = endPoint;
    }

    public boolean CheckClip(){
        return (StartPoint != _DEFAULT || EndPoint != _DEFAULT);
    }

    public float getStartPoint() {
        return StartPoint;
    }

    public void setStartPoint(float startPoint) {
        StartPoint = startPoint;
    }

    public float getEndPoint() {
        return EndPoint;
    }

    public void setEndPoint(float endPoint) {
        this.EndPoint = endPoint;
    }
}
