package me.animate.eyadakoub.com.animateme.ShapeClasses;

import android.support.annotation.NonNull;

import java.util.LinkedList;
import java.util.List;

import me.animate.eyadakoub.com.animateme.ShapeClasses.move_componenet.Position;
import me.animate.eyadakoub.com.animateme.ShapeClasses.move_componenet.Rotation;
import me.animate.eyadakoub.com.animateme.ShapeClasses.move_componenet.Scale;

/**
 * Created by eyad on 19/11/17.
 */

public class ShapeUtils {

    public static final float defaultSmallest = -1f;
    public static final float defaultGreatest = 1705040.343f;

    public static List<Float> getAlpha(DisappearClip clip , int FPS){
        float time = clip.getEndPoint() - clip.getStartPoint();
        int p = Math.round(time * FPS);

        float diff = getDifferentBetweenPos(p , clip.getFirstPercent() , clip.getLastPercent());

        float current = clip.getFirstPercent();

        List<Float> percents = new LinkedList<>();

        for(int i = 0 ;i <= p; i++){
            float v = current + (diff * i);

            percents.add(v);

            System.out.println(v);
        }
        return percents;
    }

    public static List<Scale> getScales(ResizeClip resizeClip ,int FPS){
        float time = resizeClip.getEndPoint() - resizeClip.getStartPoint();
        int p = Math.round(time * FPS);
        //get different between first scale and end than multiply it to frames
        float diffPercent = getDifferentBetweenPos(p , resizeClip.getFirstScale().getPercent() , resizeClip.getLastScale().getPercent());
        //get current
        float currentPercent = resizeClip.getFirstScale().getPercent();
        //Create List of scales
        List<Scale> scales = new LinkedList<>();

        for(int i = 1 ; i <= p ; i++){
            //Create new Scale
            Scale scale = new Scale(currentPercent+ (i * diffPercent));
            //add it to percent
            scales.add(scale);
        }

        return scales;
    }

    public static List<Position> getPositions(MoveClip moveClip , int FPS){
        if(!moveClip.checkCapabilities())
            throw new UnsupportedOperationException("move clip has no Capabilities to work on");

        float time = moveClip.getEndPoint() - moveClip.getStartPoint();
        int p = Math.round(time * FPS);

        float diffX = getDifferentBetweenPos(p , moveClip.getFPosition().getPositionX() , moveClip.getLPosition().getPositionX());
        float diffY = getDifferentBetweenPos(p , moveClip.getFPosition().getPositionY() , moveClip.getLPosition().getPositionY());

        float currentPosX = moveClip.getFPosition().getPositionX();
        float currentPosY = moveClip.getFPosition().getPositionY();

        List<Position> positions = new LinkedList<>();

        for(int i = 1 ; i <= p ; i++){
            Position position = new Position(currentPosX + (diffX * i) , currentPosY + (diffY * i));
            positions.add(position);
        }

        return positions;
    }

    public static List<Rotation> getRotation(RotateClip rotateClip , int FPS){
        float time = rotateClip.getEndPoint() - rotateClip.getStartPoint();
        int p = Math.round(time * FPS);

        List<Rotation> rotations = new LinkedList<>();
        float dgreeInSecond = 360 *  (rotateClip.getSpeedInSecond() / FPS) ;

        for(int i = 1;i <= p; i++){
            Rotation rotation = new Rotation(i * dgreeInSecond);
            rotations.add(rotation);
            System.out.println(rotation.getRotateZ());
        }
        return rotations;
    }

    private static float getDifferentBetweenPos(int p, float firstPoint , float lastPoint) {
        float diff = Math.abs(firstPoint - lastPoint);
        float diffInFPS = diff / p;

        if(firstPoint > lastPoint)
            return -diffInFPS;
        else
            return diffInFPS;
    }

    public static  float getFirstStartPoint(@NonNull List<? extends Clip> clips) {
        if(clips.size() < 1){
            return defaultGreatest;
        }

        float current = defaultGreatest;

        for (int i = 0 ; i < clips.size() ; i++) {
            if (clips.get(i).getStartPoint() < current) {
                current = clips.get(i).getStartPoint();
            }
        }

        return current;
    }
    public static  float getLastStartPoint(@NonNull List<? extends Clip> clips) {
        if(clips.size() < 1){
            return defaultSmallest;
        }

        float current = defaultSmallest;

        for (int i = 0 ; i < clips.size() ; i++) {
            if (clips.get(i).getStartPoint() > current) {
                current = clips.get(i).getStartPoint();
            }
        }

        return current;
    }
    public static float getGreatestNumber(@NonNull float... floats) {
        if(floats.length <= 1){
            return floats[0];
        }
        else{
            float greatest = 0;
            for (float v: floats
                 ) {
                if(v > greatest){
                    greatest = v;
                }
            }
            return greatest;
        }
    }
    public static float getSmallestNumber(@NonNull float... floats) {
        if(floats.length <= 1){
            return floats[0];
        }
        else{
            float greatest = 10000f;

            for (float v: floats
                    ) {
                if(v < greatest){
                    greatest = v;
                }
            }
            return greatest;
        }
    }
}
