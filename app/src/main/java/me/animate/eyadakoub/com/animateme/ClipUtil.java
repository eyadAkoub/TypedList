package me.animate.eyadakoub.com.animateme;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.DisplayMetrics;
import android.util.Log;

import java.io.File;

import me.animate.eyadakoub.com.animateme.ShapeClasses.Shape;

/**
 * Created by eyad on 19/11/17.
 */

public class ClipUtil {
    public static String height_key = "height";
    public static String width_key = "width";
    public static String density = "density";

    public static Bundle getHW(Activity mActivity){
        //DisplayMetrics to handle Screen attributes
        DisplayMetrics displayMetrics = new DisplayMetrics();
        //get Screen attributes
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        //Create Bundle to return data
        Bundle bundle = new Bundle();
        //put height
        bundle.putInt(height_key , displayMetrics.heightPixels);
        //put width
        bundle.putInt(width_key , displayMetrics.widthPixels);
        //return
        float mDensity = displayMetrics.density;
        bundle.putFloat(density ,mDensity);
        return bundle;
    }


    /**
     * @param mActivity Activity to get screen width and height
     * @param width Image width to fit
     * @param Percent Percent to fit width
     * @return float of
     */
    public static float getScaleDpInPx(Activity mActivity, float width , float Percent){
        Bundle bundle = getHW(mActivity);
        float mDensity = bundle.getFloat(density);

        float Y = 512 * mDensity;
        float Z = width / Y;
        return Z * Percent;
    }

    public static float ConvertPercentToPx(float ObjectPercent , float width , float MovePercent){
        return  (width * MovePercent ) - ( (width * ObjectPercent) / 2 ) ;
    }

    public static int getRandomColor(String typeColor , Context mContext)
    {
        // Create Default Color
        int returnColor = Color.BLACK;
        //Get Array in Specific Dgree in xml file
        int arrayId = mContext.getResources().getIdentifier("mdcolor_" + typeColor, "array", mContext.getApplicationContext().getPackageName());

        if (arrayId != 0)
        {
            //Create Typed array of colors
            TypedArray colors = mContext.getResources().obtainTypedArray(arrayId);
            //get Random int
            int index = (int) (Math.random() * colors.length());
            //get Color at index
            returnColor = colors.getColor(index, Color.BLACK);
            //Close TypedArray
            colors.recycle();
        }
        //return Color
        return returnColor;
    }


    public static TypedArray get400Color(String typeColor , Context mContext)
    {
        //Get Array in Specific Dgree in xml file
        int arrayId = mContext.getResources().getIdentifier("mdcolor_" + typeColor, "array", mContext.getApplicationContext().getPackageName());

        if (arrayId != 0)
        {
            //Create Typed array of colors
            return mContext.getResources().obtainTypedArray(arrayId);
        }
        //return Color
        throw new UnsupportedOperationException("did not found the color");
    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {

        //get Drawable

        Drawable drawable = ContextCompat.getDrawable(context, drawableId);


        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);

        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());

        drawable.draw(canvas);

        return bitmap;
    }


    public static File getFileDirectory(Context mContext) {
        String s = mContext.getResources().getString(R.string.app_name);
        File directory = new File(Environment.getExternalStorageDirectory() + File.separator + s);
        directory.mkdirs();
        return directory;
    }
}
