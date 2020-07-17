package com.fanyiran.libutil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import com.fanyiran.utils.FileUtils;
import com.fanyiran.utils.base.BaseActivity;
import com.fanyiran.utils.utils.CharBmpUtil;

import java.io.File;
public class MainActivity extends BaseActivity {
    private ImageView ivResult;
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
        ivResult = findViewById(R.id.ivResult);
    }

    public void onToCharBmpClick(View view) {
        Bitmap scaled = CharBmpUtil.INSTANCE.charToBmp(BitmapFactory.decodeResource(getResources(), R.mipmap.chartobmp));
        ivResult.setImageBitmap(scaled);
    }
}
