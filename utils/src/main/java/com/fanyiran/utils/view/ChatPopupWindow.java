package com.fanyiran.utils.view;

import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.fanyiran.utils.Utils;

public class ChatPopupWindow extends PopupWindow {
    private static final String TAG = "ChatPopupWindow";
    private static int screenHeight;

    public ChatPopupWindow(View view) {
        setContentView(view);
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());

        this.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        // 设置PopupWindow弹出窗体的高
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
    }

    public void show(float[] lastPoint, View parent, int bgDrawableID) {
        if (screenHeight == 0) {
            screenHeight = Utils.getScreenSize(parent.getContext())[1];
        }
//        int[] location = new int[2];
//        parent.getLocationInWindow(location);
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        getContentView().measure(w, h);
        int height = getContentView().getMeasuredHeight();
        int width = getContentView().getMeasuredWidth();
        int yOff;
        boolean up = lastPoint[1] <= (screenHeight / 2);

        if (up) {
            getContentView().setBackgroundResource(bgDrawableID);
            yOff = 0;
        } else {
            getContentView().setBackgroundResource(bgDrawableID);
            yOff = -height;
        }
//        int yOff = up ? (location[1] + parent.getHeight() / 2) : (location[1] - parent.getHeight() / 2);
//        int xOff = location[0] + parent.getWidth() / 2 - width / 2;

        showAtLocation(parent.getRootView(), Gravity.NO_GRAVITY, (int) lastPoint[0] - width / 2, (int) lastPoint[1] + yOff);

        // TODO: 2019-08-21 显示在点击的坐标位置

    }
}
