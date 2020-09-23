package com.fanyiran.utils.base;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.fanyiran.utils.AsycTaskUtil;
import com.fanyiran.utils.LogUtil;
import com.fanyiran.utils.R;

import java.util.concurrent.Callable;

/**
 * Created by fanqiang on 2018/12/7.
 */
public abstract class BaseActivity extends AppCompatActivity implements Callable<Object>, AsycTaskUtil.OnTaskListener {
    private static final String TAG = "BaseActivity";
    private boolean forceWait = false;
    private long forceWaitTime = 1000;
    private int defaultDrawable;
    private boolean asyncCreateView = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (asyncCreateView) {
            setContentView(getWaitView());
            AsycTaskUtil.getInstance().createAsycTask(this, this);
        } else {
            forceWait = false;
            onTaskFinished(call());
        }
    }

    public View getWaitView() {
        return getHolderView();
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
        if (forceWait) {
            try {
                Thread.sleep(forceWaitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long timeBefore = System.currentTimeMillis();
        View v = inflateView();
        LogUtil.v(TAG,"inflate view time:",(System.currentTimeMillis() - timeBefore)+"");
        return v;
    }

    protected View inflateView() {
        return View.inflate(this, getLayoutId(), null);
    }

    protected abstract int getLayoutId();

    protected abstract void onSetContentViewEnd();

    public void setForceWait(boolean forceWait, long forceWaitTime) {
        this.forceWait = forceWait;
        this.forceWaitTime = forceWaitTime;
    }

    public void setDefaultDrawable(int defaultDrawable) {
        this.defaultDrawable = defaultDrawable;
    }
    //</editro-fold>

    public Fragment replaceNewFragment(int containerId, String fName) {
        return replaceNewFragment(containerId, fName, null);
    }

    public Fragment replaceNewFragment(int containerId, String fName, Bundle args) {
        Fragment fragment = Fragment.instantiate(this, fName);
        fragment.setArguments(args);
        replaceFragment(containerId, fragment);
        return fragment;
    }

    public void replaceFragment(int containerId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerId, fragment);
        fragmentTransaction.commit();
    }

    public void setAsyncCreateView(boolean asyncCreateView) {
        this.asyncCreateView = asyncCreateView;
    }
}
