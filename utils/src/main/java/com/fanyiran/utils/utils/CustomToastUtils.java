package com.fanyiran.utils.utils;

import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fanyiran.utils.ContextHolder;
import com.fanyiran.utils.R;

public class CustomToastUtils {
    private static Toast toast;

    public static void showToast(int resId, String content) {
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(ContextHolder.getContext());
        View view = View.inflate(ContextHolder.getContext(), R.layout.toast_custom, null);
        ((ImageView) view.findViewById(R.id.ivTip)).setImageResource(resId);
        ((TextView) view.findViewById(R.id.tvTip)).setText(content);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
