package me.animate.eyadakoub.com.animateme;

/**
 *
 * Created by Eyad on 05/12/17.
 *
 */

public class eventPostMessenger {

    private String Message = "null";

    private int percent;

    public eventPostMessenger(String message, int percent) {
        Message = message;
        this.percent = percent;
    }

    public String getMessage() {
        return Message;
    }

    public int getPercent() {
        return percent;
    }
}
