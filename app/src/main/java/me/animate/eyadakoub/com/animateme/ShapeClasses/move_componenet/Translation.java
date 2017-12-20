package me.animate.eyadakoub.com.animateme.ShapeClasses.move_componenet;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;

import java.util.List;

import me.animate.eyadakoub.com.animateme.ClipUtil;

/**
 * Created by eyad on 28/11/17.
 */

public class Translation{

    private Bitmap Bitmap;

    private float StartTimeMove = 0;
    private float StartTimeResize = 0;
    private float StartTimeRotate = 0;
    private float StartTimeDisappear = 0;

    private Position currentPosition = null;
    private Rotation currentRotation = null;
    private Scale currentScale = null;
    private float currentAlpha = -11;

    private List<Rotation> RotateListInFrame = null;
    private List<Position> PositionListInFrame = null;
    private List<Scale> ScaleListInFrame = null;
    private List<Float> AlphaFloatListInFrame = null;

    /**
     * get matrix from translation component
     * @param key_time the time you draw for to change animation
     * @param width Bitmap width that you want to draw on
     * @return return matrix set by translation component
     */
    public Matrix getMatrix(float key_time , float width){

        Matrix matrix = new Matrix();
        //set Rotation
        //set Position

        //set Scale
        setScaleToMatrix(key_time, width, matrix);

        setRotateToMatrix(key_time, width, matrix);

        setPositionToMatrix(key_time,width ,  matrix);
        return matrix;
    }

    /**
     * get paint up set
     * @param key_time the time to be animate
     * @return return a setup of paint to be using in
     */
    public Paint getPaint(float key_time){
        Paint paint = new Paint();
        if(AlphaFloatListInFrame != null) {
            if (StartTimeDisappear < key_time) {
                if (AlphaFloatListInFrame.size() > 0) {
                    //Set Current
                    currentAlpha = AlphaFloatListInFrame.get(0);
                    //Remove First
                    AlphaFloatListInFrame.remove(0);
                    //setPaint
                }
            }
        }
        paint.setAlpha((int) (currentAlpha));
        return paint;
    }

    /**
     * this method does convert
     * @param key_time
     * @param width
     * @param matrix
     */
    //set Scale to Matrix
    private void setScaleToMatrix(float key_time, float width, Matrix matrix) {
        if(ScaleListInFrame != null) {
            if (StartTimeResize < key_time) {
                if (ScaleListInFrame.size() > 0) {
                    //set current
                    currentScale = ScaleListInFrame.get(0);
                    //remove first
                    ScaleListInFrame.remove(0);
                    //set Matrix
                }
            }
        }

        matrix.postScale(currentScale.getScaleDpInPx(width) , currentScale.getScaleDpInPx(width));
    }
    //set position to matrix
    private void setPositionToMatrix(float key_time,float width, Matrix matrix) {
        float currentPercent = currentScale.getPercent();
        if(PositionListInFrame != null) {
            if (StartTimeMove < key_time) {
                if (PositionListInFrame.size() > 0) {
                    // get first position
                    currentPosition = PositionListInFrame.get(0);
                    // remove it
                    PositionListInFrame.remove(0);

                }

            }
        }

        //set matrix
        matrix.postTranslate(
                        currentPosition.getPositionXInPixel(width , currentPercent)
                        , currentPosition.getPositionYInPixel(width , currentPercent));
    }
    //set rotation to matrix
    private void setRotateToMatrix(float key_time, float width, Matrix matrix) {
        //get width od shape and spilt to two to rotate around him self
        float percent  = currentScale.getPercent();//currentScale.getWHInPx(width) ;

        if(RotateListInFrame != null) {
            if (StartTimeRotate < key_time) {
                if (RotateListInFrame.size() > 0) {
                    //get the first item
                    currentRotation = RotateListInFrame.get(0);
                    //remove it from
                    RotateListInFrame.remove(0);
                    //set current

                    //set matrix rotation

                    //currentPosition.getPositionXInPixel(width ,percent)

                    //,currentPosition.getPositionYInPixel(width , percent) );
                }
            }
        }

        matrix.postRotate(currentRotation.getRotateZ() , currentScale.getWHInPx(width) / 2, currentScale.getWHInPx(width) / 2);
    }

    //getter and setter

    //1 - Bitmap
    public void setBitmap(android.graphics.Bitmap bitmap) {
        Bitmap = bitmap;
    }
    //2 - Move start time
    public void setStartTimeMove(float startTimeMove) {
        StartTimeMove = startTimeMove;
    }
    //3 - Resize start time
    public void setStartTimeResize(float startTimeResize) {
        StartTimeResize = startTimeResize;
    }
    //4 - Rotate start time
    public void setStartTimeRotate(float startTimeRotate) {
        StartTimeRotate = startTimeRotate;
    }
    //5 - Disappear start time
    public void setStartTimeDisappear(float startTimeDisappear) {
        StartTimeDisappear = startTimeDisappear;
    }
    //6 - current Position
    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }
    //7 - current Rotation
    public void setCurrentRotation(Rotation currentRotation) {
        this.currentRotation = currentRotation;
    }
    //8 - current Scale
    public void setCurrentScale(Scale currentScale) {
        this.currentScale = currentScale;
    }
    //9 - current Alpha
    public void setCurrentAlpha(float currentAlpha) {
        this.currentAlpha = currentAlpha;
    }
    //10 - Rotations List
    public void setRotateListInFrame(List<Rotation> rotateListInFrame) {
        RotateListInFrame = rotateListInFrame;
    }
    //11 - Positions List
    public void setPositionListInFrame(List<Position> positionListInFrame) {
        PositionListInFrame = positionListInFrame;
    }
    //12 - Scales List
    public void setScaleListInFrame(List<Scale> scaleListInFrame) {
        ScaleListInFrame = scaleListInFrame;
    }
    //13 - Alphas List
    public void setAlphaFloatListInFrame(List<Float> alphaFloatListInFrame) {
        AlphaFloatListInFrame = alphaFloatListInFrame;
    }

    //1 - Bitmap
    public Bitmap getBitmap() {
        return Bitmap;
    }
    //2 - Move start time
    public float getStartTimeMove() {
        return StartTimeMove;
    }
    //3 - Resize start time
    public float getStartTimeResize() {
        return StartTimeResize;
    }
    //4 - Rotate start time
    public float getStartTimeRotate() {
        return StartTimeRotate;
    }
    //5 - Disappear start time
    public float getStartTimeDisappear() {
        return StartTimeDisappear;
    }
    //6 - current Position
    public Position getPosition() {
        return currentPosition;
    }
    //7 - current Rotation
    public Rotation getCurrentRotation() {
        return currentRotation;
    }
    //8 - current Scale
    public Scale getCurrentScale() {
        return currentScale;
    }
    //9 - current Alpha
    public float getCurrentAlpha() {
        return currentAlpha;
    }
    //10 - Rotations List
    public List<Rotation> getRotateListInFrame() {
        return RotateListInFrame;
    }
    //11 - Positions List
    public List<Position> getPositionListInFrame() {
        return PositionListInFrame;
    }
    //12 - Scales List
    public List<Scale> getScaleListInFrame() {
        return ScaleListInFrame;
    }
    //13 - Alphas List
    public List<Float> getAlphaFloatListInFrame() {
        return AlphaFloatListInFrame;
    }
}
