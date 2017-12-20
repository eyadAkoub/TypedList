package me.animate.eyadakoub.com.animateme.ShapeClasses;

import me.animate.eyadakoub.com.animateme.ShapeClasses.move_componenet.Position;

/**
 * Created by eyad on 22/11/17.
 */

public class MoveClip extends Clip {
    private Position FPosition = null;
    private Position LPosition = null;

    public MoveClip(float startPoint, float endPoint, Position FPosition, Position LPosition) {
        super(startPoint, endPoint);
        this.FPosition = FPosition;
        this.LPosition = LPosition;
    }

    public void setTime(float begin , float end){
        setStartPoint(begin);
        setEndPoint(end);
    }

    public boolean checkCapabilities(){
        if(super.CheckClip() && FPosition != null && LPosition != null){
            return true;
        }
        return false;
    }

    public Position getFPosition() {
        return FPosition;
    }

    public void setFPosition(Position FPosition) {
        this.FPosition = FPosition;
    }

    public Position getLPosition() {
        return LPosition;
    }

    public void setLPosition(Position LPosition) {
        this.LPosition = LPosition;
    }
}
