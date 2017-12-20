package me.animate.eyadakoub.com.animateme;

import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jcodec.codecs.mjpeg.tools.AssertionException;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.animate.eyadakoub.com.animateme.ShapeClasses.Utils;

public class Clip_Creation_Activity extends AppCompatActivity {

    @Bind(R.id.mainLayout)
    RelativeLayout mLayout;

    @Bind(R.id.background)
    FrameLayout mFrameLayout;

    @Bind(R.id.debug_tv)
    TextView mTextView;


    int video_width = VideoAttributes.getAttributes().getWIDTH();
    int video_height = VideoAttributes.getAttributes().getHEIGHT();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip__creation);
        //should be first
        ButterKnife.bind(this);


        //progressing background attributes
        //LoadBackgroundToFitScreen();

        mFrameLayout.setBackgroundColor(VideoAttributes.getAttributes().getBACKGROUND_COLOR());

        mLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                LoadBackgroundToFitScreen();
            }
        });

    }



    private void LoadBackgroundToFitScreen() {

        Point mMainWidth_height = new Point(mLayout.getWidth(), mLayout.getHeight());
        LogOut(mMainWidth_height.toString() + video_height+" " + mMainWidth_height.y);

        float wPercent = ((float) mMainWidth_height.y / (float) video_height);

        if(video_width * wPercent > mMainWidth_height.x) {
            wPercent = ((float) mMainWidth_height.x / (float) video_height);
        }
        int nWidth = (int) (video_width * wPercent);
        int nHeight = (int) (video_height * wPercent);

        LogOut(nWidth +" "+ nHeight);

        mFrameLayout.setLayoutParams(new RelativeLayout.LayoutParams(nWidth , nHeight));
        mFrameLayout.setTranslationX(mMainWidth_height.x);
        mFrameLayout.setTranslationY(mMainWidth_height.y);

        mTextView.setText(String.valueOf(nHeight));
//        mFrameLayout.setScaleX(nWidth);
        //mFrameLayout.setScaleY(nHeight);
    }













    //Debug methods
    void LogOut(String s){
        Log.e(getClass().getName() , s);
    }

    void throwError(String s){
        throw new AssertionException(s);
    }

    void printInLine(String...args){
        StringBuilder stringBuffer = new StringBuilder();
        for(String s : args){
            stringBuffer.append(s).append("  ,");
        }
        LogOut(stringBuffer.toString());
    }

}


