package com.fanyiran.utils;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

/**
 * Created by fanqiang on 2019/4/1.
 */
public class FileUtils {
    /**
     * @param context
     * @param assetPath
     * @param targetPath 需要添加targetFile名称
     */
    public static void assetCopyToSdCard(Context context, String assetPath, String targetPath) {
        try {
            InputStream is = context.getAssets().open(assetPath);
            FileOutputStream fos = new FileOutputStream(new File(targetPath));
            byte[] buffer = new byte[1024];
            int byteCount;
            while ((byteCount = is.read(buffer)) != -1) {//循环从输入流读取 buffer字节
                fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
            }
            fos.flush();//刷新缓冲区
            is.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fileCopyToSdCard(String originPath, String targetPath, String fileName) throws IOException {
        if (originPath == null) {
            return;
        }
        File originfile = new File(originPath, fileName);
        if (!originfile.exists()) {
            return;
        }
        File targetPathFile = new File(targetPath);
        if (!targetPathFile.exists()) {
            targetPathFile.mkdirs();
        }
        File targetFile = new File(targetPath, fileName);
        InputStream inputStream = null;
        FileChannel readChannel = null;
        OutputStream outputStream = null;
        FileChannel writeChannel = null;
        try {
            inputStream = new FileInputStream(originfile);
            readChannel = ((FileInputStream) inputStream).getChannel();
            outputStream = new FileOutputStream(targetFile);
            writeChannel = ((FileOutputStream) outputStream).getChannel();
            writeChannel.transferFrom(readChannel, 0, readChannel.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (readChannel != null) {
                readChannel.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (writeChannel != null) {
                writeChannel.close();
            }
        }
    }

    public static boolean createFile(File file) {
        if (file == null) {
            throw new IllegalArgumentException("file is null");
        }
        if (file.exists()) {
            return true;
        }
        File parentFile = file.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
