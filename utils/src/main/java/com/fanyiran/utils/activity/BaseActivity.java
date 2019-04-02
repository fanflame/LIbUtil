package com.fanyiran.utils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fanyiran.utils.AsycTaskUtil;
import com.fanyiran.utils.LogUtil;
import com.fanyiran.utils.R;

import java.util.concurrent.Callable;

/**
 * Created by fanqiang on 2018/12/7.
 */
public abstract class BaseActivity extends AppCompatActivity implements Callable<Object>, AsycTaskUtil.OnTaskListener {
    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getHolderView());
        AsycTaskUtil.getInstance().createAsycTask(this,this);
    }

    private ImageView getHolderView() {
        ImageView backGroundView = new ImageView(this);
        backGroundView.setImageResource(R.drawable.activity_holderbg);
        backGroundView.setScaleType(ImageView.ScaleType.FIT_XY);
        backGroundView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return backGroundView;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AsycTaskUtil.getInstance().cancelTask(this);
    }

    //<editor-fold desc = "task">
    @Override
    public void onTaskFinished(Object result) {
        setContentView((View) result);
        onSetContentViewEnd();
    }

    @Override
    public Object call() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long timeBefore = System.currentTimeMillis();
        View v = View.inflate(this,getLayoutId(),null);
        LogUtil.v(TAG,"inflate view time:",(System.currentTimeMillis() - timeBefore)+"");
        return v;
    }

    protected abstract int getLayoutId();

    protected abstract void onSetContentViewEnd();
    //</editro-fold>
}