package me.animate.eyadakoub.com.animateme.ShapeClasses;

import me.animate.eyadakoub.com.animateme.ShapeClasses.move_componenet.Scale;

/**
 * Created by eyad on 22/11/17.
 */

public class ResizeClip extends Clip {
    private Scale fScale;
    private Scale lScale;

    public ResizeClip(float startPoint, float endPoint, Scale fScale, Scale lScale) {
        super(startPoint, endPoint);
        this.fScale = fScale;
        this.lScale = lScale;
    }

    public void setTime(float begin, float end) {
        setStartPoint(begin);
        setEndPoint(end);
    }

    public Scale getFirstScale() {
        return fScale;
    }

    public void setFirstScale(Scale fScale) {
        this.fScale = fScale;
    }

    public Scale getLastScale() {
        return lScale;
    }

    public void setLastScale(Scale lScale) {
        this.lScale = lScale;
    }
}
