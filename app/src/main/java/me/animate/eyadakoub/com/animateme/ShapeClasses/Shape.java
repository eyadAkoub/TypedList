package me.animate.eyadakoub.com.animateme.ShapeClasses;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import java.util.List;

import me.animate.eyadakoub.com.animateme.ClipUtil;
import me.animate.eyadakoub.com.animateme.ShapeClasses.move_componenet.Position;
import me.animate.eyadakoub.com.animateme.ShapeClasses.move_componenet.Rotation;
import me.animate.eyadakoub.com.animateme.ShapeClasses.move_componenet.Scale;
import me.animate.eyadakoub.com.animateme.ShapeClasses.move_componenet.Translation;

/**
 * Created by --eyad akoub-- on 19/11/17.
 *
 */

public class Shape {

    //Resource Id For get and deal with Drawable
    private int BitmapDrawableId;

    //end and last time to be drawing
    private float endPoint;
    private float startPoint;

    //current variables
    private Position currentPosition = null;
    private Rotation currentRotation = null;
    private Scale currentScale = null;
    private float currentAlpha = 1;

    public Shape(float endPoint,
                 float startPoint,
                 @NonNull Position currentPosition,
                 @NonNull Rotation currentRotation,
                 @NonNull Scale currentScale,
                 float currentAlpha) {
        this.endPoint = endPoint;
        this.startPoint = startPoint;
        this.currentPosition = currentPosition;
        this.currentRotation = currentRotation;
        this.currentScale = currentScale;
        this.currentAlpha = currentAlpha;
    }

    //shape Component
    private DisappearClip mDisappearClip = null;
    private ResizeClip mResizeClip = null;
    private RotateClip mRotateClip = null;
    private MoveClip mMoveClip = null;


    //Local Variables has no getter and setter

    Bitmap mainBitmap;

    // make sure that the shape does not prepared twice
    private boolean prepared = false;

    //positions ,rotation ,scales and alphas
    List<Position> positions  = null;
    List<Rotation> rotations = null;
    List<Scale> scales = null;
    List<Float> Alphas = null;
    // end local


    //prepared Component and Bitmap
    public void  prepare(Context context , int FPS){
        if(!prepared) {
            prepareComponent(FPS);
            getBitmap(context);
            prepared = true;
        }
    }

    //getter and setter for shape component
    public void setMoveClip(MoveClip mMoveClip) {
        this.mMoveClip = mMoveClip;
    }
    public void setResizeClip(ResizeClip mResizeClip) {
        this.mResizeClip = mResizeClip;
    }
    public void setDisappearClip(DisappearClip mDisappearClip) {
        this.mDisappearClip = mDisappearClip;
    }
    public void setRotateClip(RotateClip mRotateClip) {
        this.mRotateClip = mRotateClip;
    }
    // end of setter and getter of shape component

    //get translation
    public Translation getTranslation(){
        //Check if prepared or not
        if (!prepared) throw new UnsupportedOperationException("not prepared yet!");

        Translation translation = new Translation();

        if(mMoveClip != null){
            assert positions != null;
            translation.setStartTimeMove(mMoveClip.getStartPoint());
            translation.setPositionListInFrame(positions);
        }

        if(mRotateClip != null){
            assert rotations != null;
            translation.setStartTimeRotate(mRotateClip.getStartPoint());
            translation.setRotateListInFrame(rotations);
        }

        if(mResizeClip != null){
            assert scales != null;
            translation.setStartTimeMove(mResizeClip.getStartPoint());
            translation.setScaleListInFrame(scales);
        }

        if(mDisappearClip != null) {
            assert Alphas != null;
            translation.setStartTimeMove(mDisappearClip.getStartPoint());
            translation.setAlphaFloatListInFrame(Alphas);
        }

        translation.setCurrentAlpha(currentAlpha);
        translation.setCurrentPosition(currentPosition);
        translation.setCurrentRotation(currentRotation);
        translation.setCurrentScale(currentScale);

        translation.setBitmap(mainBitmap);
        return translation;
    }

    //get Bitmap By id
    private void getBitmap(Context context){
        mainBitmap = ClipUtil.getBitmapFromVectorDrawable(context, BitmapDrawableId);
    }

    //prepare Component rotate ,move ,alpha and scale
    private void prepareComponent(int FPS){



        if(mMoveClip != null) {
            mMoveClip.setFPosition(currentPosition);
            positions = ShapeUtils.getPositions(mMoveClip, FPS);
        }

        if(mRotateClip != null){
            rotations = ShapeUtils.getRotation(mRotateClip , FPS);
        }

        if(mResizeClip != null ){
            scales = ShapeUtils.getScales(mResizeClip , FPS);
        }

        if (mDisappearClip != null){
            Alphas = ShapeUtils.getAlpha(mDisappearClip , FPS);
        }

    }


    //setter
    public void setBitmapDrawableId(int bitmapDrawableId) {
        BitmapDrawableId = bitmapDrawableId;
    }

    public void setEndPoint(float endPoint) {
        this.endPoint = endPoint;
    }

    public void setStartPoint(float startPoint) {
        this.startPoint = startPoint;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void setCurrentRotation(Rotation currentRotation) {
        this.currentRotation = currentRotation;
    }

    public void setCurrentScale(Scale currentScale) {
        this.currentScale = currentScale;
    }

    public void setCurrentAlpha(float currentAlpha) {
        this.currentAlpha = currentAlpha;
    }

    //getter
    public int getBitmapDrawableId() {
        return BitmapDrawableId;
    }

    public float getEndPoint() {
        return endPoint;
    }

    public float getStartPoint() {
        return startPoint;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public Rotation getCurrentRotation() {
        return currentRotation;
    }

    public Scale getCurrentScale() {
        return currentScale;
    }

    public float getCurrentAlpha() {
        return currentAlpha;
    }
}
