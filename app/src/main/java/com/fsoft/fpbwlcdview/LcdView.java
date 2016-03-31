package com.fsoft.fpbwlcdview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Region;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;

import java.util.ArrayList;

/**
 * класс отображающий инфу внутри экрана
 * Created by Dr. Failov on 20.02.2016.
 */
public class LcdView {
    private Rect viewRect = null;
    private Lcd lcd = null;
    private ArrayList<LcdView> views = new ArrayList<>();
    private LcdView touchHolderView = null;


    public LcdView(Rect viewRect, Lcd lcd) {
        this.viewRect = viewRect;
        this.lcd = lcd;
    }
    public Rect getViewRect() {
        return viewRect;
    }
    public void onDraw(Canvas canvas){
        for (LcdView lcdView:views){
            Rect rect = lcdView.getViewRect();
            canvas.clipRect(rect, Region.Op.REPLACE);
            canvas.translate(rect.left, rect.top);
            lcdView.onDraw(canvas);
            canvas.translate(-rect.left, -rect.top);
        }
    }
    public boolean onTouch(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            for (int i = views.size() - 1; i >= 0; i--) {
                LcdView lcdView = views.get(i);
                Rect rect = lcdView.getViewRect();
                if (rect.contains((int) event.getX(), (int) event.getY())) {
                    if (lcdView.onTouch(MotionEvent.obtain(
                            event.getDownTime(),
                            event.getEventTime(),
                            event.getAction(),
                            event.getX() - rect.left,
                            event.getY() - rect.top,
                            event.getMetaState()
                    ))) {
                        touchHolderView = lcdView;
                        return true;
                    }
                }
            }
        }
        else if(touchHolderView != null) {
            Rect rect = touchHolderView.getViewRect();
            touchHolderView.onTouch(MotionEvent.obtain(
                    event.getDownTime(),
                    event.getEventTime(),
                    event.getAction(),
                    event.getX() - rect.left,
                    event.getY() - rect.top,
                    event.getMetaState()
            ));
        }
        return false;
    }
    public int getWidth(){
        return viewRect.width();
    }
    public int getHeight(){
        return viewRect.height();
    }
    public void playClick(){
        lcd.playSoundEffect(SoundEffectConstants.CLICK);
    }
    public void addView(LcdView view){
        views.add(view);
        lcd.invalidate();
    }
    public void invalidate(){
        lcd.invalidateLcd(viewRect.left, viewRect.top, viewRect.right, viewRect.bottom);
    }
    public void invalidate(int l, int t, int r, int b) {
        lcd.invalidateLcd(viewRect.left+l, viewRect.top+t, viewRect.left+r, viewRect.top+b);
    }
    public Lcd getLcd() {
        return lcd;
    }
    public void clearChilds(){
        views.clear();
    }
}
