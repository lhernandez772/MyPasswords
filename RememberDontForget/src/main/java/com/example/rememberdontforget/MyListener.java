package com.example.rememberdontforget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Created by Luis on 22/11/13.
 */
public class MyListener implements View.OnTouchListener {
    public static final int TIME_ANIM = 150;
    private int swipe = -1;
    public float pressX = 0;
    boolean itemPress = false, dragging = false;
    public static Activity context;
    public static ViewGroup mContainerView;
    public static ScrollView scroll;
    public MyListener (ViewGroup mContainerView,Activity context,ScrollView scroll) {
        super();
        this.mContainerView = mContainerView;
        this.context = context;
        this.scroll = scroll;
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (swipe < 0)
            swipe = ViewConfiguration.get(context).getScaledTouchSlop();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (itemPress)
                    return false;
                itemPress = true;
                pressX = event.getX();
                break;
            case MotionEvent.ACTION_CANCEL:
                v.setAlpha(1);
                v.setTranslationX(0);
                dragging = false;
                itemPress = false;
                break;
            case MotionEvent.ACTION_MOVE:
            {
                float x = event.getX() + v.getTranslationX();
                float deltaX = x - pressX;
                if (!dragging) {
                    if (Math.abs(deltaX) > swipe) {
                        dragging = true;
                        scroll.requestDisallowInterceptTouchEvent(true);
                    }
                }
                if (dragging) {
                    v.setTranslationX((x - pressX));
                    v.setAlpha(1 - Math.abs(deltaX) / v.getWidth());
                }
            }
            break;
            case MotionEvent.ACTION_UP:
            {
                if (dragging) {
                    float x = event.getX() + v.getTranslationX();
                    float deltaX = x - pressX;
                    // Si voy mas alla que lo deseado el elemento se borra
                    if (Math.abs(deltaX) > v.getWidth() / 4) {
                        mContainerView.invalidate();
                        v.animate().setDuration(TIME_ANIM).translationX(1000).alphaBy(0);
                        mContainerView.removeView(v);
                    } else {
                        v.setAlpha(1);
                        v.animate().setDuration(TIME_ANIM).translationX(0);
                        dragging = false;
                    }
                }
                itemPress = false;
            }
            break;
        }
        return true;
    }
}
