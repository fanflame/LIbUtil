package com.fanyiran.utils.utils.imageload;

import android.net.Uri;
import android.widget.ImageView;

public class ImageLoaderUtils implements IImageLoader {
    private IImageLoader imageLoader;

    private ImageLoaderUtils() {
        init();
    }

    private void init() {
        imageLoader = new GlideIImageLoader();
    }

    private static class ImageLoaderUtilsHolder {
        public static ImageLoaderUtils imageLoaderUtils = new ImageLoaderUtils();
    }


    public static ImageLoaderUtils getInstance() {
        return ImageLoaderUtilsHolder.imageLoaderUtils;
    }

    @Override
    public void loadImage(String url, int placeHolderResId, ImageView imageView) {
        imageLoader.loadImage(url, placeHolderResId, imageView);
    }

    @Override
    public void loadImageUri(Uri uri, int placeHolderResId, ImageView imageView) {
        imageLoader.loadImageUri(uri, placeHolderResId, imageView);
    }

    @Override
    public void loadResImage(int resId, ImageView imageView) {
        imageLoader.loadResImage(resId, imageView);
    }

    @Override
    public void downloadImage(String url, final String targetPath, OnDownloadImageListener onDownloadImageListener) {
        imageLoader.downloadImage(url, targetPath, onDownloadImageListener);
    }
}
