package com.fsoft.fpbwlcdview.Screens;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;

import com.fsoft.fpbwlcdview.Lcd;
import com.fsoft.fpbwlcdview.LcdButton;
import com.fsoft.fpbwlcdview.LcdDraw;
import com.fsoft.fpbwlcdview.LcdView;

/**
 *
 * Created by Dr. Failov on 21.02.2016.
 */
public class DrawApplication extends LcdView {
    Paint paint = new Paint();
    LcdDraw lcdDraw = null;
    Rect bottomRect = null;

    public DrawApplication(Rect viewRect, Lcd lcd) {
        super(viewRect, lcd);
        addView(lcdDraw = new LcdDraw(new Rect(0, 0, getWidth() - 1, threshold()), getLcd()));
        {
            int itemHeight = 20;
            int itemGap = 5;
            int footerHeight = getHeight() - threshold();
            int footerCenterY = getHeight() - footerHeight/2;
            int top = footerCenterY - itemHeight/2;
            int bottom = footerCenterY + itemHeight/2;
            int left = itemGap;
            int right = getWidth()/3;
            Rect rect = new Rect(left, top, right, bottom);
            addView(new LcdButton(rect, getLcd()).setText("<").setOnClick(new Runnable() {
                @Override
                public void run() {
                    getLcd().back();
                }
            }));
        }

        {
            int itemHeight = 20;
            int itemGap = 5;
            int footerHeight = getHeight() - threshold();
            int footerCenterY = getHeight() - footerHeight/2;
            int top = footerCenterY - itemHeight/2;
            int bottom = footerCenterY + itemHeight/2;
            int left = getWidth()/3 + itemGap;
            int right = getWidth()/3 * 2 - itemGap;
            Rect rect = new Rect(left, top, right, bottom);
            addView(new LcdButton(rect, getLcd()).setText("X").setOnClick(new Runnable() {
                @Override
                public void run() {
                    lcdDraw.clear();
                }
            }));
        }

        paint.setColor(Color.LTGRAY);
    }

    @Override
    public void onDraw(Canvas canvas) {
        if(bottomRect == null)
            bottomRect = new Rect(0, threshold(), getWidth()-1, getHeight()-1);
        canvas.clipRect(bottomRect, Region.Op.REPLACE);
        canvas.drawRect(bottomRect, paint);
        super.onDraw(canvas);
    }
    private int threshold(){
        return 9 * getHeight() / 10;
    }
}
