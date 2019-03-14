package com.fanyiran.utils.activity;

import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fanyiran.utils.R;


/**
 * Created by fanqiang on 2018/12/7.
 */
public abstract class ExceptionBaseActivity extends BaseActivity {
    static public String INTENT_EXTRA_EXCEPTION = "INTENT_EXTRA_EXCEPTION";
    private TextView tvException;
    private Button btnRestart;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_exception;
    }

    @Override
    protected void onSetContentViewEnd() {
        tvException = findViewById(R.id.tvException);
        btnRestart = findViewById(R.id.btnRestart);
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRestartClick();
            }
        });
        String extra = getIntent().getStringExtra(INTENT_EXTRA_EXCEPTION);
        tvException.setMovementMethod(ScrollingMovementMethod.getInstance());
        tvException.setText(extra);
    }

    protected abstract void onRestartClick();
}
