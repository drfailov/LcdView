package com.fsoft.fpbwlcdview.Screens;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.Menu;
import android.widget.Button;

import com.fsoft.fpbwlcdview.Lcd;
import com.fsoft.fpbwlcdview.LcdButton;
import com.fsoft.fpbwlcdview.LcdLabel;
import com.fsoft.fpbwlcdview.LcdView;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * Created by Dr. Failov on 21.02.2016.
 */
public class MenuScreen extends LcdView {
    private ArrayList<ArrayList<LcdButton>> pages = new ArrayList<>();
    private Paint paint = new Paint();
    private String headerText = "Menu";
    private int headerHeight = 30;
    private int headerMargin = 10;
    private int footerHeight = 50;
    private int footerMargin = 10;
    private int itemHeight = 25;
    private int itemGap = 5;
    private int horizontalMargin = 10;
    private int currentPage = 0;

    public MenuScreen(Rect viewRect, Lcd lcd) {
        super(viewRect, lcd);
        pages.add(new ArrayList<LcdButton>());
    }
    public MenuScreen addMenuItem(String text, Runnable onClick){

        if(pages.get(pages.size()-1).size() >= itemsOnPage())
            pages.add(new ArrayList<LcdButton>());

        int numberOnPage = pages.get(pages.size()-1).size();

        int top = headerHeight + headerMargin + (itemHeight + itemGap)*numberOnPage;
        int bottom = top + itemHeight;
        int left = horizontalMargin;
        int right = getWidth() - horizontalMargin - 1;

        Rect rect = new Rect(left, top, right, bottom);

        pages.get(pages.size()-1).add(new LcdButton(rect, getLcd())
                .setOnClick(onClick)
                .setText(text));
        return this;
    }
    private int itemsOnPage(){
        return (getHeight() - headerHeight - headerMargin - footerHeight - footerMargin) / (itemGap+itemHeight);
    }
    public MenuScreen setPage(final int page) {
        clearChilds();
        addView(new LcdLabel(new Rect(0, 0, getWidth() - 1, headerHeight), getLcd()).setText(headerText).setTextSize(18));
        currentPage = page;
        for (LcdButton button:pages.get(page)){
            addView(button);
        }

        {
            int footerCenterY = getHeight() - footerHeight/2;
            int top = footerCenterY - itemHeight/2;
            int bottom = footerCenterY + itemHeight/2;
            int left = itemGap;
            int right = getWidth()/3;
            Rect rect = new Rect(left, top, right, bottom);
            addView(new LcdButton(rect, getLcd()).setText("Back").setOnClick(new Runnable() {
                @Override
                public void run() {
                    getLcd().back();
                }
            }));
        }

        {
            int footerCenterY = getHeight() - footerHeight/2;
            int top = footerCenterY - itemHeight/2;
            int bottom = footerCenterY + itemHeight/2;
            int left = getWidth()/3 + itemGap;
            int right = getWidth()/3 * 2 - itemGap;
            Rect rect = new Rect(left, top, right, bottom);
            addView(new LcdButton(rect, getLcd()).setText("<").setOnClick(new Runnable() {
                @Override
                public void run() {
                    if(currentPage > 0)
                        setPage(currentPage-1);
                }
            }));
        }

        {
            int footerCenterY = getHeight() - footerHeight/2;
            int top = footerCenterY - itemHeight/2;
            int bottom = footerCenterY + itemHeight/2;
            int left = getWidth()/3 * 2 + itemGap;
            int right = getWidth() - itemGap;
            Rect rect = new Rect(left, top, right, bottom);
            addView(new LcdButton(rect, getLcd()).setText(">").setOnClick(new Runnable() {
                @Override
                public void run() {
                    if(pages.size() > currentPage+1)
                        setPage(currentPage+1);
                }
            }));
        }
        return this;
    }
    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.LTGRAY);
        canvas.drawRect(0, 0, getWidth() - 1, headerHeight, paint);
        canvas.drawRect(0, getHeight() - 1 - footerHeight, getWidth() - 1, getHeight() - 1, paint);

        paint.setStrokeWidth(2);
        paint.setColor(Color.BLACK);
        canvas.drawLine(0, headerHeight, getWidth() - 1, headerHeight, paint);
        canvas.drawLine(0, getHeight() - 1 - footerHeight, getWidth()-1, getHeight() - 1 - footerHeight, paint);
        super.onDraw(canvas);
    }
    public MenuScreen setHeaderText(String headerText) {
        this.headerText = headerText;
        return this;
    }
}
