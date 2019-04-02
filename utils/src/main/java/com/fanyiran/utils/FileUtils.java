package com.fanyiran.utils;

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
    public static void fileCopyToSdCard(String originPath,String targetPath,String fileName) throws IOException {
        if (originPath == null) {
            return;
        }
        File originfile = new File(originPath,fileName);
        if (!originfile.exists()) {
            return;
        }
        File targetPathFile = new File(targetPath);
        if (!targetPathFile.exists()) {
            targetPathFile.mkdirs();
        }
        File targetFile = new File(targetPath,fileName);
        InputStream inputStream = null;
        FileChannel readChannel = null;
        OutputStream outputStream = null;
        FileChannel writeChannel = null;
        try {
            inputStream = new FileInputStream(originfile);
            readChannel = ((FileInputStream) inputStream).getChannel();
            outputStream = new FileOutputStream(targetFile);
            writeChannel = ((FileOutputStream) outputStream).getChannel();
            writeChannel.transferFrom(readChannel,0,readChannel.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
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
}
