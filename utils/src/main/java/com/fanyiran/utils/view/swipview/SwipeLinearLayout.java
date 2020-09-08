package com.fanyiran.utils.view.swipview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SwipeLinearLayout extends LinearLayout {
    private SwipeFinishHelper swipeFinishHelper;

    public SwipeLinearLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public SwipeLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        swipeFinishHelper = new SwipeFinishHelper(this);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = swipeFinishHelper.onInterceptTouchEvent(ev);
        return result ? result : super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = swipeFinishHelper.onTouchEvent(event);
        return result ? result : super.onTouchEvent(event);
    }
}
