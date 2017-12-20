package me.animate.eyadakoub.com.animateme;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.jcodec.api.android.AndroidSequenceEncoder;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.SeekableByteChannel;
import org.jcodec.common.model.Rational;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.animate.eyadakoub.com.animateme.ShapeClasses.Shape;
import me.animate.eyadakoub.com.animateme.ShapeClasses.move_componenet.Translation;

/**
 * Created by eyad on 13/11/17.
 * eyadoooooooooooooooooooooooooo
 */

public class ClipCreator extends Thread {


    private VideoAttributes video = null;
    List<Translation> ListOfTranslation;

    public ClipCreator(VideoAttributes video) {
        this.video = video;
    }

    @Override
    public void run() {
        super.run();
        //createSampleVideo();
    }

    /*private String createSampleVideo() {
        SeekableByteChannel channel = null;
        try {
            File FileDirectory = ClipUtil.getFileDirectory(video.getmContext());
            //  Toast.makeText(mContext,FileDirectory.getPath(),Toast.LENGTH_LONG).show();
            channel = NIOUtils.writableFileChannel(FileDirectory.getPath() + File.separator + video.getTitle() + ".mp4");
            //encoder
            AndroidSequenceEncoder encoder = new AndroidSequenceEncoder(channel, new Rational(video.getFramesPerSecond(), 1));
            // Bitmap bitmap = ((BitmapDrawable) mContext.getResources().getDrawable(R.drawable.home_icon)).getBitmap();

            //get translations
            ListOfTranslation = getTranslations();
            //hello bitch
            int frames = video.getSecondsLong() * video.getFramesPerSecond();
            //foreach
            for (int i = 1; i <= frames; i++) {
                //percent of frames
                int percent = 100 * (frames / i) ;
                //event bus object
                eventPostMessenger postMessenger = new eventPostMessenger("done of frames " + i , percent);
                //event bus
                EventBus.getDefault().post(postMessenger);
                //get bitmap at this frame(i)
                Bitmap enBitmap = getBitmapAtTime(i);
                //encoder it
                encoder.encodeImage(enBitmap);
            }

            eventPostMessenger postMessenger = new eventPostMessenger("Done" , 100);
            EventBus.getDefault().post(postMessenger);

            Log.e("done", "hi done");
            encoder.finish();
            return null;

        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        } finally {
            if (channel != null)
                NIOUtils.closeQuietly(channel);
        }
    }

    public Bitmap getBitmapAtTime(int millisecond) {
        //get Overly and make Bitmap of Bitmaps
        Bitmap overly = Bitmap.createBitmap(video.getWidth(), video.getHeight(), Bitmap.Config.ARGB_4444);
        Canvas mainCanvas = new Canvas(overly);

        float second = millisecond / video.getSecondsLong();

        mainCanvas.drawBitmap(ListOfTranslation.get(0).getBitmap() ,
                ListOfTranslation.get(0).getMatrix(second , overly.getWidth())
                , ListOfTranslation.get(0).getPaint(second));

        return overly;
    }


    private boolean checkShape(Shape shape, int millisecond) {
        //if (shape.startPoint < millisecond) {
        //    if (shape.endPoint < millisecond) {
                video.getShapes().remove(shape);
         //       return false;
        //    }
         //   return true;
       // }
        return false;
    }

    public List<Translation> getTranslations() {
        ArrayList<Translation> listTranslations = new ArrayList<>();

        for (Shape shape :video.getShapes()
             ) {
            shape.prepare(video.getmContext() , video.getFramesPerSecond());
            listTranslations.add(shape.getTranslation());
        }
        return listTranslations;
    }
*/
    //Consssssssss

/*
    private ClipCreator(Context mContext, String mTitle, int framesPerSecond, int secondsLong, int width, int height, List<Shape> shapes) {
        this.framesPerSecond = framesPerSecond;
        title = mTitle;
        SecondsLong = secondsLong;
        this.width = width;
        this.height = height;
        this.shapes = shapes;
        this.mContext = mContext;
    }
*/

}
