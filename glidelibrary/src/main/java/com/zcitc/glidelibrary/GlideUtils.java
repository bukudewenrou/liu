package com.zcitc.glidelibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.webp.decoder.WebpDrawable;
import com.bumptech.glide.integration.webp.decoder.WebpDrawableTransformation;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import jp.wasabeef.glide.transformations.GrayscaleTransformation;

public class GlideUtils {


    public static void preloadImg(Context mContext, String path, int w, int h) {
        Glide.with(mContext).load(path).apply(
                new RequestOptions().diskCacheStrategy(
                        DiskCacheStrategy.RESOURCE
                )
        ).preload(w, h);
    }

    public static void showGrayImage(Context mContext, String path, ImageView imgIv) {
        if (mContext == null) {
            return;
        }
        if (path == null || path.equals("")) {
            imgIv.setImageResource(R.mipmap.zwtu);
        } else {
            RequestOptions options = RequestOptions.bitmapTransform(
                            new GrayscaleTransformation()).placeholder(R.mipmap.ic_default)
                    .error(R.mipmap.ic_default);
            Glide.with(mContext).load(path)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .dontAnimate()
                    .apply(options)
                    .into(imgIv);
        }
    }


    public static void showImage(Context mContext, String path, ImageView imgIv, int placeholder) {
        if (mContext == null) {
            return;
        }
        if (path == null || path.equals("")) {
            imgIv.setImageResource(R.mipmap.zwtu);
        } else {
            Glide.with(mContext).load(path)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .placeholder(placeholder)
                    .error(placeholder)
                    .dontAnimate()
                    .into(imgIv);
        }
    }

    public static void showImage(Context mContext, String path, ImageView imgIv, Boolean isGray) {
        if (isGray) {
            showGrayImage(mContext, path, imgIv);
        } else {
            showImage(mContext, path, imgIv);
        }

    }

    public static void showGrayImage(Context mContext, String path, ImageView imgIv, int placeholder, int errorImg) {
        if (mContext == null) {
            return;
        }
        if (path == null || path.equals("")) {
            imgIv.setImageResource(R.mipmap.zwtu);
        } else {

            RequestOptions options = RequestOptions.bitmapTransform(
                            new GrayscaleTransformation()).placeholder(placeholder)
                    .error(errorImg);
//            RequestOptions options = RequestOptions.bitmapTransform(
//                            new MultiTransformation<>(
//                                    new GrayscaleTransformation()
//                            )) .placeholder(placeholder)
//                    .error(errorImg);
            Glide.with(mContext).load(path)
                    .apply(options)
                    .into(imgIv);
        }
    }

    public static void showImage(Context mContext, String path, ImageView imgIv, int placeholder, int errorImg, Boolean isGray) {
        if (isGray) {
            showGrayImage(mContext, path, imgIv, placeholder, errorImg);
        } else {
            showImage(mContext, path, imgIv, placeholder, errorImg);
        }
    }

    public static void showImage(Context mContext, String path, ImageView imgIv, int placeholder, int errorImg) {
        if (mContext == null) {
            return;
        }
        if (path == null || path.equals("")) {
            imgIv.setImageResource(errorImg);
        } else {
            Glide.with(mContext).load(path)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .placeholder(placeholder)
                    .error(errorImg)
                    .dontAnimate()
                    .into(imgIv);
        }
    }

    public static void showImage(Context mContext, int path, ImageView imgIv, int placeholder) {
        if (path == 0) {
            imgIv.setImageResource(R.mipmap.zwtu);
        } else {
            Glide.with(mContext).load(path)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .placeholder(placeholder)
                    .error(placeholder)
                    .dontAnimate()
                    .into(imgIv);
        }
    }

    public static void showImage(Context mContext, String path, ImageView imgIv) {
        showImage(mContext, path, imgIv, R.mipmap.ic_default, R.mipmap.zwtu);
    }

    public static void showImage(Context mContext, int path, ImageView imgIv) {
        Glide.with(mContext).load(path)
                .skipMemoryCache(false)
                .dontAnimate()
                .into(imgIv);
    }

    public static void showmdImage(Context mContext, String path, ImageView imgIv) {
        if (path == null || path.equals("")) {
            imgIv.setImageResource(R.mipmap.mdzwtu);
        } else {
            Glide.with(mContext).load(path)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .placeholder(R.mipmap.ic_default)
                    .error(R.mipmap.ic_default)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .dontAnimate()
                    .into(imgIv);
        }
    }

    public static void showUserImage(Context mContext, String path, ImageView imgIv) {
        if (path == null || path.equals("")) {
            imgIv.setImageResource(R.mipmap.home_image_face);
        } else {
            Glide.with(mContext).load(path)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .placeholder(R.mipmap.moren_toolbg)
                    .error(R.mipmap.moren_toolbg)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .dontAnimate()
                    .into(imgIv);
        }
    }

    /**
     * 重新
     */
    public void showAgainImg(Context mContext, String path, ImageView imgIv) {
        if (path == null || path.equals("")) {
            imgIv.setImageResource(R.mipmap.home_image_face);
        } else {
            Glide.with(mContext).load(path)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .placeholder(R.mipmap.moren_toolbg)
                    .error(R.mipmap.moren_toolbg)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                            Log.d("showAgainImg","onLoadFailed");
//                            Log.d("showAgainImg","isFirstResource="+isFirstResource);
//                            showAgainImg(mContext,path,imgIv);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                            Log.d("showAgainImg","onResourceReady");
//                            Log.d("showAgainImg","isFirstResource="+isFirstResource);
                            return false;
                        }
                    })
                    .dontAnimate()
                    .into(imgIv);
        }
    }

    public static void showActiveImage(Context mContext, String path, ImageView imgIv) {
        if (path == null || path.equals("")) {
            imgIv.setImageResource(R.mipmap.zwtu);
            return;
        }

        if (path.endsWith(".gif") || path.endsWith(".GIF")) {
            Glide.with(mContext).asGif()
                    .skipMemoryCache(false).load(path).error(R.mipmap.zwtu)
                    .placeholder(R.mipmap.ic_default).into(imgIv);
        } else if (path.endsWith(".webp") || path.endsWith(".WEBP")) {

            Transformation<Bitmap> circleCrop = new CenterCrop();
            Glide.with(mContext)
                    .load(path)
                    .optionalTransform(circleCrop)
                    .error(R.mipmap.zwtu)
                    .placeholder(R.mipmap.ic_default)
                    .optionalTransform(WebpDrawable.class, new WebpDrawableTransformation(circleCrop))
                    .into(imgIv);
        } else {
            Glide.with(mContext).load(path)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .placeholder(R.mipmap.ic_default)
                    .error(R.mipmap.zwtu)
                    .dontAnimate()
                    .into(imgIv);
        }
    }


}
