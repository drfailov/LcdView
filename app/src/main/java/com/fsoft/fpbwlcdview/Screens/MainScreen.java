package com.fsoft.fpbwlcdview.Screens;

import android.graphics.Rect;

import com.fsoft.fpbwlcdview.Lcd;
import com.fsoft.fpbwlcdview.LcdButton;
import com.fsoft.fpbwlcdview.LcdDraw;
import com.fsoft.fpbwlcdview.LcdLabel;
import com.fsoft.fpbwlcdview.LcdView;

/**
 *
 * Created by Dr. Failov on 21.02.2016.
 */
public class MainScreen extends LcdView {


    public MainScreen(Rect viewRect, final Lcd lcd) {
        super(viewRect, lcd);
        //addView(new LcdDraw(getViewRect(), lcd));
        addView(new LcdLabel(getViewRect(), lcd).setText("FP BW LCD View"));

        //TOP TIME
        {
            int width = 30;
            int height = 20;
            int top = 0;
            int left = getWidth() - width - 1;
            int bottom = height;
            int right = getWidth() - 1;
            Rect rect = new Rect(left, top , right, bottom);
            LcdLabel view = new LcdLabel(rect, lcd);
            view.setText("16:34");
            view.setTextSize(7);
            addView(view);
        }

        //CENTER TIME
        {
            int width = 70;
            int height = 40;
            int centerX = getWidth()/2;
            int centerY = getHeight()/3;
            int top = centerY-height/2;
            int left = centerX - width/2;
            int bottom = centerY+height/2;
            int right = centerX + width/2;
            Rect rect = new Rect(left, top , right, bottom);
            LcdLabel view = new LcdLabel(rect, lcd);
            view.setText("16:34");
            view.setTextSize(20);
            addView(view);
        }

        //BOTTOM MENU
        {
            int gap = 3;
            int width = getWidth()/2;
            int height = 30;
            int top = getHeight() - height - 1;
            int left = gap;
            int bottom = getHeight()-1;
            int right = width - gap;
            Rect rect = new Rect(left, top , right, bottom);
            LcdButton view = new LcdButton(rect, lcd);
            view.setText("Меню");
            view.setOnClick(new Runnable() {
                @Override
                public void run() {
                    lcd.addScreen(new MainMenuScreen(getViewRect(), lcd));
                }
            });
            addView(view);
        }
    }


}
