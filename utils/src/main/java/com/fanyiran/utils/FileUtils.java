package com.fanyiran.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static boolean removeFile(String fileName) {
        if (fileName == null) {
            return false;
        }
        File file = new File(fileName);
        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return file.delete();
        }
        return false;
    }


    public static boolean saveBitmap(String path, Bitmap bitmap) {
        File file = new File(path);
        if (!file.exists() || file.isDirectory()) {
            return false;
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static File createImageFile(Context context) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = null;
        try {
            imageFile = File.createTempFile(imageFileName, ".jpg", storageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageFile;
    }

    public static String getFileName(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        String[] split = path.split("/");
        return split[split.length - 1];
    }

    public static void copy(File source, File target) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(source);
            fileOutputStream = new FileOutputStream(target);
            byte[] buffer = new byte[1024];
            while (fileInputStream.read(buffer) > 0) {
                fileOutputStream.write(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
