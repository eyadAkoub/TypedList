package me.animate.eyadakoub.com.animateme.ShapeClasses.move_componenet;

import android.util.Log;

import me.animate.eyadakoub.com.animateme.ShapeClasses.Utils;

/**
 * Created by eyad on 28/11/17.
 */

public class Position {

    private static float _DEFAULT = -4325.303f;

    private float positionX = _DEFAULT;
    private float positionY = _DEFAULT;

    public Position(float positionX, float positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }



    public float getPositionXInPixel(float width , float ObjectPercent){
        float returnValue = positionX - ( (width * ObjectPercent) / 2 );
        Log.e("position" , String.valueOf(returnValue));
        return  returnValue;
    }

    public float getPositionYInPixel(float width, float ObjectPercent){
        float returnValue = positionY - ( (width * ObjectPercent) / 2 );
        Log.e("position" , String.valueOf(returnValue));
        return  returnValue;
    }

    public boolean checkValues(){
        return !(positionX == _DEFAULT || positionY == _DEFAULT);
    }
    //get and set
    public float getPositionX(){
        //if(positionX == _DEFAULT)
        //    throw new UnsupportedOperationException("no X value");
        return positionX;
    }

    public float ConvertPercentToPxX(float width){
        return  (width * positionX) - ( (width * 0.2f) / 2 ) ;
    }


    public float ConvertPercentToPxY(float width){
        return  (width * positionY) - ( (width * 0.2f) / 2 ) ;
    }

    public float getPositionY() {
        if(positionY == _DEFAULT)
            throw new UnsupportedOperationException("no X value");
        return positionY;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }
    // end of getter and setter

    @Override
    public String toString() {
        return String.format("position X is='%s' ,position Y is='%s'" , positionX , positionY);
    }
}
