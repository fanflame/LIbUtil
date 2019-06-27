package com.fanyiran.libutil;

import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.fanyiran.utils.FileUtils;
import com.fanyiran.utils.activity.BaseActivity;
import java.io.File;
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FileUtils.assetCopyToSdCard(this,"ic_launcher.xml", Environment.getExternalStorageDirectory().getAbsolutePath()+"/ic_launcher.xml");

        FileUtils.createFile(new File(Environment.getExternalStorageDirectory()+"/testsss","test.aar"));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onSetContentViewEnd() {

    }
}
