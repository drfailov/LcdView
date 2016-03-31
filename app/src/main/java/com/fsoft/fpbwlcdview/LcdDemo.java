package com.fsoft.fpbwlcdview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.fsoft.fpbwlcdview.Screens.MainScreen;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * Created by Dr. Failov on 20.02.2016.
 */
public class LcdDemo extends Lcd {

    public LcdDemo(Context context) {
        super(context);
    }

    public LcdDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LcdDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public LcdDemo(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void init(){
        super.init();
        MainScreen rootView = new MainScreen(new Rect(0,0,getLcdWidth()-1, getLcdHeight()-1), this);
        //rootView.addView(new LcdLabel(new Rect(0,0,getWidth()-1, getHeight()-1), this).setText("OLOLO"));
        addScreen(rootView);
    }
}
