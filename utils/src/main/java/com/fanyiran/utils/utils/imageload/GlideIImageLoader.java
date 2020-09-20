package com.fanyiran.utils.utils.imageload;

import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.fanyiran.utils.ContextHolder;
import com.fanyiran.utils.FileUtils;

import java.io.File;

public class GlideIImageLoader implements IImageLoader {
    private InternalCacheDiskCacheFactory diskLruCacheFactory = new InternalCacheDiskCacheFactory(
            ContextHolder.getContext());

    public GlideIImageLoader() {
        Glide.init(ContextHolder.getContext(), new GlideBuilder().setDiskCache(diskLruCacheFactory));
    }

    @Override
    public void loadImageUri(Uri uri, int placeHolderResId, ImageView imageView) {
        Glide.with(ContextHolder.getContext()).load(uri).placeholder(placeHolderResId).into(imageView);
    }

    @Override
    public void loadImage(String url, int placeHolderResId, ImageView imageView) {
        Glide.with(ContextHolder.getContext()).load(url).placeholder(placeHolderResId).into(imageView);
    }

    @Override
    public void loadResImage(int resid, ImageView imageView) {
        Glide.with(ContextHolder.getContext()).load(resid).into(imageView);
    }

    @Override
    public void downloadImage(String url, final String targetPath, final OnDownloadImageListener onDownloadImageListener) {
        RequestManager mRequestManager = Glide.with(ContextHolder.getContext());

        RequestBuilder<File> mRequestBuilder = mRequestManager.downloadOnly().load(url).listener(new RequestListener<File>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<File> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(File resource, Object model, Target<File> target, DataSource dataSource, boolean isFirstResource) {
                File file;
                FileUtils.copy(resource, file = new File(targetPath, resource.getName()));
                if (onDownloadImageListener != null) {
                    onDownloadImageListener.onDownload(file.getAbsolutePath());
                }
                return false;
            }
        });

        mRequestBuilder.preload();
    }


}
