package me.animate.eyadakoub.com.animateme;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

/**
 * Created by eyad on 10/12/17.
 */

public class attributes_model{
    private String FPS;
    private String Second;
    private String Height;

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getFPS() {
        return FPS;
    }

    public void setFPS(String FPS) {
        this.FPS = FPS;
    }

    public String getSecond() {
        return Second;
    }

    public void setSecond(String second) {
        Second = second;
    }
}
