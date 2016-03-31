package com.fsoft.fpbwlcdview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

/**
 *
 * Created by Dr. Failov on 21.02.2016.
 */
public class LcdDraw extends LcdView {
    private Bitmap bitmap = null;
    private Canvas canvas = null;
    private Paint paint = null;
    private int lastX = -1;
    private int lastY = -1;

    public LcdDraw(Rect viewRect, Lcd lcd) {
        super(viewRect, lcd);
    }

    @Override
    public boolean onTouch(MotionEvent motionEvent) {
        //Log.d("LcdDraw", "motionEvent = " + motionEvent);
        if(bitmap == null)
            return false;
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            lastX = (int)motionEvent.getX();
            lastY = (int)motionEvent.getY();
            return true;
        }
        else if(motionEvent.getAction() == MotionEvent.ACTION_MOVE){
            if(lastX == -1)
                return true;
            canvas.drawLine(lastX, lastY, motionEvent.getX(), motionEvent.getY(), paint);
            invalidate(
                    Math.min(lastX, (int) motionEvent.getX()),
                    Math.min(lastY, (int) motionEvent.getY()),
                    Math.max(lastX, (int) motionEvent.getX()),
                    Math.max(lastY, (int) motionEvent.getY())
            );
            lastX = (int)motionEvent.getX();
            lastY = (int)motionEvent.getY();
            return true;
        }
        else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
            if(lastX == -1)
                return true;
            canvas.drawLine(lastX, lastY, motionEvent.getX(), motionEvent.getY(), paint);
            invalidate(
                    Math.min(lastX, (int) motionEvent.getX()),
                    Math.min(lastY, (int) motionEvent.getY()),
                    Math.max(lastX, (int) motionEvent.getX()),
                    Math.max(lastY, (int) motionEvent.getY())
            );
            lastX = -1;
            lastY = -1;
            return true;
        }
        return super.onTouch(motionEvent);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Log.d("LcdDraw", "onDraw = ");
        if(bitmap == null || bitmap.getWidth() != getWidth()){
            bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.RGB_565);
            this.canvas = new Canvas(bitmap);
            this.canvas.drawColor(Color.WHITE);
            paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(2);
        }
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }
    public void clear(){
        canvas.drawColor(Color.WHITE);
        invalidate();
    }
}
