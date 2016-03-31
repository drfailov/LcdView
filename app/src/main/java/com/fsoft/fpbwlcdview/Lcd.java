package com.fsoft.fpbwlcdview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 *
 * Created by Dr. Failov on 20.02.2016.
 */
public class Lcd extends View {
    private int pixelSize = 5;
    private int pixelGap = 1;
    private int minimumPixelBrightness = 5;
    private int maximumPixelBrughtness = 150;
    private int basePixelColor = Color.BLACK;
    private int backgroundColor = Color.parseColor("#677a4d");
    private Bitmap buffer = null;
    private Canvas canvas = null;
    private Paint paint = null;
    private ArrayList<LcdView> screens = new ArrayList<>();
    private Rect dirty = new Rect(-1, -1, -1, -1);



    public Lcd(Context context) {
        super(context);
    }
    public Lcd(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public Lcd(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @TargetApi(21)
    public Lcd(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(buffer == null || buffer.getWidth() != getLcdWidth() || buffer.getHeight() != getLcdHeight()){
            init();
        }
        onLcdDraw(this.canvas);
        canvas.getClipBounds(dirty);

        int top = 0;
        int bottom = getLcdHeight()-1;
        int left = 0;
        int right = getLcdWidth()-1;

        if(dirty.top != -1) {
            top = dirty.top / (pixelSize + pixelGap);
            bottom = dirty.bottom / (pixelSize + pixelGap);
            left = dirty.left / (pixelSize + pixelGap);
            right = dirty.right / (pixelSize + pixelGap);
        }
//        Log.d("Lcd",
//                    "redraw: top="+top +
//                    " bottom="+bottom +
//                    " left="+left +
//                    " right="+right );

        canvas.drawColor(backgroundColor);
        paint.setColor(basePixelColor);
        for(int x=left; x<right; x++){
            for(int y=top; y<bottom; y++){
                int rx = (pixelSize + pixelGap) * x;
                int ry = (pixelSize + pixelGap) * y;
                int pixel = buffer.getPixel(x,y);
                int pixelRed = Color.red(pixel);
                int pixelGreen = Color.green(pixel);
                int pixelBlue = Color.blue(pixel);
                int pixelAvg = (pixelRed + pixelBlue + pixelGreen)/3;
                int intensivity = 255 - pixelAvg;
                int resultingIntensivity = intensivity * maximumPixelBrughtness / 255 + minimumPixelBrightness;
//                Log.d("LCD View",
//                         " pixelRed = " + pixelRed
//                        +" pixelGreen = " + pixelGreen
//                        +" pixelBlue = " + pixelBlue
//                        +" pixelAvg = " + pixelAvg
//                        +" intensivity = " + intensivity
//                        +" resultingIntensivity = " + resultingIntensivity);
                //int resultingPixel = Color.argb(resultingIntensivity, 0, 0, 0);
                paint.setAlpha(resultingIntensivity);
                canvas.drawRect(rx, ry, rx + pixelSize, ry + pixelSize, paint);
            }
        }

        {
            paint.setColor(Color.argb(15, 255, 255, 255));
            int regularity = 11;
            int startpointX = (dirty.left/regularity)*regularity;
            int startpointY = (dirty.top/regularity)*regularity;
            int circleRagius = 1;
            //Log.d("x", "startpointX="+startpointX+" startpointY="+startpointY);
            //Log.d("x", "dirty.right="+dirty.right+" dirty.bottom="+dirty.bottom);
            for (int x = startpointX; x < dirty.right; x+=regularity) {
                for (int y = startpointY; y < dirty.bottom; y+=regularity) {
                    //Log.d("x", "x="+x+" y="+y);
                    if(y%2 == 0)
                        canvas.drawCircle(x,y, circleRagius, paint);
                    else
                        canvas.drawCircle(x + regularity/2,y, circleRagius, paint);
                }
            }
        }
        dirty.set(-1,-1,-1,-1);
//        paint.setAlpha(255);
//        canvas.drawBitmap(buffer, 0, 0, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return getScreen().onTouch(MotionEvent.obtain(
                event.getDownTime(),
                event.getEventTime(),
                event.getAction(),
                event.getX() / (pixelSize + pixelGap),
                event.getY() / (pixelSize + pixelGap),
                event.getMetaState()
        ));
    }

    @Override
    public void invalidate() {
        dirty.set(0, 0, getWidth()-1, getHeight()-1);
        super.invalidate();
    }

    public void invalidateLcd(int l, int t, int r, int b) {
//        Log.d("Lcd",
//                "invalidate: top="+t +
//                        " bottom="+b +
//                        " left="+l +
//                        " right="+r );
//
        l = l * (pixelSize + pixelGap);
        t = t * (pixelSize + pixelGap);
        r = r * (pixelSize + pixelGap);
        b = b * (pixelSize + pixelGap);

        l -= pixelSize * 5;
        t -= pixelSize * 5;
        r += pixelSize * 5;
        b += pixelSize * 5;

        if(l < 0) l = 0;
        if(r > getWidth()-1) r = getWidth()-1;
        if(t < 0) t = 0;
        if(b > getHeight()-1) b = getHeight()-1;

        if(dirty.top == -1)
            dirty.set(l, t, r, b);
        else
            dirty.set(
                    Math.min(l, dirty.left),
                    Math.min(t, dirty.top),
                    Math.max(r, dirty.right),
                    Math.max(b, dirty.bottom));

        invalidate(l, t, r-1, b-1);
    }
    protected void init(){
        buffer = Bitmap.createBitmap(getLcdWidth(), getLcdHeight(), Bitmap.Config.RGB_565);
        this.canvas = new Canvas(buffer);
        paint = new Paint();
        if(screens.size() == 0)
            screens.add(new LcdView(new Rect(0,0,getLcdWidth()-1, getLcdHeight()-1), this));
        Log.d("LCD", "Resolution = " + getLcdWidth() + " x " + getLcdHeight());
    }
    public void addScreen(LcdView view){
        screens.add(view);
        invalidate();
    }
    public int getLcdWidth(){
        return getWidth() / (pixelSize+pixelGap);
    }
    public int getLcdHeight(){
        return getHeight() / (pixelSize+pixelGap);
    }
    protected void onLcdDraw(Canvas canvas){
        canvas.clipRect(getScreenRect(), Region.Op.REPLACE);
        canvas.drawColor(Color.WHITE);
        getScreen().onDraw(canvas);
    }
    private LcdView getScreen(){
        return screens.get(screens.size()-1);
    }
    public boolean back(){
        if(screens.size() > 1) {
            screens.remove(screens.size() - 1);
            invalidate();
            return true;
        }
        return false;
    }
    public Rect getScreenRect(){
        return new Rect(0, 0, getLcdWidth()-1, getLcdHeight()-1);
    }
    @Override public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        invalidate();
    }

    public void setBasePixelColor(int basePixelColor) {
        this.basePixelColor = basePixelColor;
        invalidate();
    }

    public void invertScreen() {
        int tmp = basePixelColor;
        this.basePixelColor = backgroundColor;
        backgroundColor = tmp;
        invalidate();
    }
}
