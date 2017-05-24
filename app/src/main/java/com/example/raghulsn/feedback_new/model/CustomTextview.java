package com.example.raghulsn.feedback_new.model;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import java.util.Hashtable;

/**
 * Created by raghul.sn on 3/11/16.
 */

public class CustomTextview extends TextView {

    public static  final String asset="fonts/HelveticaNeueLTStd_Lt.ttf";
    public CustomTextview(Context context) {
        super(context);
    }

    public CustomTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, asset);

    }

    public CustomTextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(context, asset);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomTextview(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setCustomFont(context, asset);

    }

    private boolean setCustomFont(Context ctx, String asset) {

        setTypeface(TextTypeFace.get(ctx,asset));
        return true;
    }
}

class TextTypeFace{
    private static final String TAG="Typefaces";
    private static final Hashtable<String, Typeface> cache= new Hashtable <String, Typeface>();
    public static Typeface get(Context c,String assetPath){
        synchronized (cache){
            if(!cache.containsKey(assetPath)){
                try{
                    Typeface t=Typeface.createFromAsset(c.getAssets(),assetPath);
                    cache.put(assetPath,t);
                }catch (Exception e){
                    Log.e(TAG, "Could not get typeface '" + assetPath + "' because" + e.getMessage());
                    return null;
                }
            }
            return cache.get(assetPath);
        }

    }
}