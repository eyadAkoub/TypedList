package me.animate.eyadakoub.com.animateme;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.SeekableByteChannel;

import java.io.File;

public class Main2Activity extends AppCompatActivity {

    ImageView imageView;
    SeekBar seekBar;
    SeekBar seekBar2;
    float anInt = 0;
    float anTran = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageView = findViewById(R.id.image_id);
        seekBar = findViewById(R.id.seekBar);
        seekBar2 = findViewById(R.id.seekBar2);

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                anTran = i * 0.1f;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        anInt = i * 0.1f;

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Main2Activity.this, "here you are ?", Toast.LENGTH_LONG).show();
                aVoid();
            }
        });
    }

    private void aVoid() {
        Bitmap ic_raven = ClipUtil.getBitmapFromVectorDrawable(this, R.drawable.ic_raven);
        imageView.setImageBitmap(ic_raven);
        Bitmap ic_dove = ClipUtil.getBitmapFromVectorDrawable(this, R.drawable.ic_dove);
        Bitmap overly = Bitmap.createBitmap(1024, 1024, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(overly);

        float width = ClipUtil.getScaleDpInPx(this, overly.getWidth(), anInt);


        Matrix matrix = new Matrix();
        matrix.postScale(width, width);
        //matrix.setRotate(180);

        //float transition = (overly.getHeight() / 2) - (overly.getHeight() * 0.25f) * 0.5f;
        //matrix.postTranslate( transition,transition);

        float per = ClipUtil.ConvertPercentToPx(anInt , overly.getWidth() , anTran);
        matrix.postTranslate(per , per);

        //drawBitmap(canvas , ic_dove , ic_raven);
        Rect rect = new Rect(0, 0, overly.getHeight(), overly.getHeight());
        canvas.drawBitmap(ic_dove, matrix, new Paint());
        matrix.postScale(width +width , width +width);
        canvas.drawBitmap(ic_raven, matrix, new Paint());
//        rect.set(0,0,(int)(overly.getHeight() * anInt ),(int)(overly.getHeight() *anInt ) );
//        canvas.drawBitmap(ic_raven , null,rect, new Paint());
//

        Toast.makeText(this, "image sets !!", Toast.LENGTH_LONG).show();
        imageView.setImageBitmap(overly);
    }

}
