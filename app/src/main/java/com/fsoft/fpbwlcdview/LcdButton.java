package com.fsoft.fpbwlcdview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

/**
 *
 * Created by Dr. Failov on 21.02.2016.
 */
public class LcdButton extends LcdView {
    private LcdLabel lcdLabel = null;
    private Paint paint = new Paint();
    private boolean pressed = false;
    private Runnable onClick = null;
    private RectF buttonRect = null;



    public LcdButton(Rect viewRect, Lcd lcd) {
        super(viewRect, lcd);
        lcdLabel = new LcdLabel(viewRect, lcd);
        paint.setStrokeWidth(1f);
        buttonRect = new RectF(0,0, getWidth()-1, getHeight()-1);
    }

    public LcdButton setText(String text) {
        lcdLabel.setText(text);
        return this;
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            pressed = true;
            invalidate();
            return true;
        }
        else if (event.getAction() == MotionEvent.ACTION_MOVE){
            if(event.getX() < 0 || event.getY() < 0 || event.getX() > getWidth()-1 || event.getY() > getHeight()-1) {
                Log.d("LcdButton", "Event = " + event);
                pressed = false;
                invalidate();
            }
            return true;
        }
        else if (event.getAction() == MotionEvent.ACTION_UP){
            if(pressed) {
                if(onClick != null) {
                    playClick();
                    onClick.run();
                }
            }
            pressed = false;
            invalidate();
            return true;
        }
        return super.onTouch(event);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(pressed)
            paint.setColor(Color.LTGRAY);
        else
            paint.setColor(Color.WHITE);

        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(buttonRect, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawRect(buttonRect, paint);

        lcdLabel.onDraw(canvas);
    }

    public LcdButton setOnClick(Runnable onClick) {
        this.onClick = onClick;
        return this;
    }
}
