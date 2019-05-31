package com.fanyiran.libutil;

import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.fanyiran.utils.FileUtils;
import com.fanyiran.utils.activity.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileUtils.assetCopyToSdCard(this,"ic_launcher.xml", Environment.getExternalStorageDirectory().getAbsolutePath()+"/ic_launcher.xml");
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void onSetContentViewEnd() {

    }
}
