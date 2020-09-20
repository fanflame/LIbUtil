package com.fanyiran.utils.utils.imageload;

import android.net.Uri;
import android.widget.ImageView;

public interface IImageLoader {
    void loadImage(String url, int placeHolderResId, ImageView imageView);

    void loadImageUri(Uri uri, int placeHolderResId, ImageView imageView);

    void loadResImage(int resid, ImageView imageView);

    void downloadImage(String url, final String targetPath, OnDownloadImageListener onDownloadImageListener);


    interface OnDownloadImageListener {
        void onDownload(String path);
    }
}
