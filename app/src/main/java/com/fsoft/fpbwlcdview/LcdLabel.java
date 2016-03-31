package com.fsoft.fpbwlcdview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

/**
 * to draw text
 * Created by Dr. Failov on 20.02.2016.
 */
public class LcdLabel extends LcdView {
    private String text = "";
    private Paint paint = new Paint();

    public LcdLabel(Rect viewRect, Lcd lcdDemo) {
        super(viewRect, lcdDemo);
        paint.setTextSize(9);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
    }

    public LcdLabel setText(String text) {
        this.text = text;
        return this;
    }
    public LcdLabel setTextSize(int size){
        paint.setTextSize(size);
        return this;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Log.d("LcdLabel", "Drawing = " + text + " rect = " + getViewRect().toShortString());
        int y = getHeight()/2 + (int)(paint.getTextSize()*0.4f);
        int x = (getWidth() - (int)paint.measureText(text))/2;
        canvas.drawText(text, x, y, paint);
    }
}
