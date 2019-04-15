package com.fanyiran.libutil;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fanyiran.utils.FileUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileUtils.assetCopyToSdCard(this,"ic_launcher.xml", Environment.getExternalStorageDirectory().getAbsolutePath()+"/ic_launcher.xml");
    }
}
