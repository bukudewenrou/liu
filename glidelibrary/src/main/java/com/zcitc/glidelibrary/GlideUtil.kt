package com.zcitc.glidelibrary

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.webp.decoder.WebpDrawable
import com.bumptech.glide.integration.webp.decoder.WebpDrawableTransformation
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import jp.wasabeef.glide.transformations.GrayscaleTransformation

fun showGrayImage(mContext: Context?, path: String?, imgIv: ImageView) {
    if (mContext == null) {
        return
    }
    if (path == null || path == "") {
        imgIv.setImageResource(R.mipmap.zwtu)
    } else {
        val options: RequestOptions = RequestOptions.bitmapTransform(
            GrayscaleTransformation()
        ).placeholder(R.mipmap.ic_default)
            .error(R.mipmap.ic_default)
        Glide.with(mContext).load(path)
            .skipMemoryCache(false)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .dontAnimate()
            .apply(options)
            .into(imgIv)
    }
}
fun showImage(mContext: Context?, path: String?, imgIv: ImageView) {
    if (mContext == null) {
        return
    }
    if (path == null || path == "") {
        imgIv.setImageResource(R.mipmap.zwtu)
    } else {
        Glide.with(mContext).load(path)
            .skipMemoryCache(false)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .placeholder(R.mipmap.ic_default)
            .error(R.mipmap.ic_default)
            .dontAnimate()
            .into(imgIv)
    }
}

fun showActiveImage(path: String?, imgIv: ImageView) {
    if (path == null || path == "") {
        imgIv.setImageResource(R.mipmap.zwtu)
        return
    }
    if (path.endsWith(".gif") || path.endsWith(".GIF")) {
        Glide.with(imgIv.context).asGif()
            .skipMemoryCache(false).load(path).error(R.mipmap.zwtu)
            .placeholder(R.mipmap.ic_default).into(imgIv)
    } else if (path.endsWith(".webp") || path.endsWith(".WEBP")) {
        val circleCrop: Transformation<Bitmap> = CenterCrop()
        Glide.with(imgIv.context)
            .load(path)
            .optionalTransform(circleCrop)
            .error(R.mipmap.zwtu)
            .placeholder(R.mipmap.ic_default)
            .optionalTransform(WebpDrawable::class.java, WebpDrawableTransformation(circleCrop))
            .into(imgIv)
    } else {
        Glide.with(imgIv.context).load(path)
            .skipMemoryCache(false)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .placeholder(R.mipmap.ic_default)
            .error(R.mipmap.zwtu)
            .dontAnimate()
            .into(imgIv)
    }
}

fun showImage(mContext: Context?, path: String?, imgIv: ImageView, placeholder: Int) {
    if (mContext == null) {
        return
    }
    if (path == null || path == "") {
        imgIv.setImageResource(R.mipmap.zwtu)
    } else {
        Glide.with(mContext).load(path)
            .skipMemoryCache(false)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .placeholder(placeholder)
            .error(placeholder)
            .dontAnimate()
            .into(imgIv)
    }
}

fun showImage(mContext: Context?, path: String?, imgIv: ImageView?, isGray: Boolean) {
    if (isGray) {
        GlideUtils.showGrayImage(mContext, path, imgIv)
    } else {
        GlideUtils.showImage(mContext, path, imgIv)
    }
}

fun showGrayImage(mContext: Context?, path: String?, imgIv: ImageView, placeholder: Int, errorImg: Int) {
    if (mContext == null) {
        return
    }
    if (path == null || path == "") {
        imgIv.setImageResource(R.mipmap.zwtu)
    } else {
        val options = RequestOptions.bitmapTransform(
            GrayscaleTransformation()
        ).placeholder(placeholder)
            .error(errorImg)
        //            RequestOptions options = RequestOptions.bitmapTransform(
//                            new MultiTransformation<>(
//                                    new GrayscaleTransformation()
//                            )) .placeholder(placeholder)
//                    .error(errorImg);
        Glide.with(mContext).load(path)
            .apply(options)
            .into(imgIv)
    }
}

fun showImage(mContext: Context?, path: String?, imgIv: ImageView, placeholder: Int, errorImg: Int, isGray: Boolean) {
    if (isGray) {
        showGrayImage(mContext, path, imgIv, placeholder, errorImg)
    } else {
        showImage(mContext, path, imgIv, placeholder, errorImg)
    }
}

fun showImage(mContext: Context?, path: String?, imgIv: ImageView, placeholder: Int, errorImg: Int) {
    if (mContext == null) {
        return
    }
    if (path == null || path == "") {
        imgIv.setImageResource(errorImg)
    } else {
        Glide.with(mContext).load(path)
            .skipMemoryCache(false)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .placeholder(placeholder)
            .error(errorImg)
            .dontAnimate()
            .into(imgIv)
    }
}

fun showImage(mContext: Context?, path: Int, imgIv: ImageView, placeholder: Int) {
    if (path == 0) {
        imgIv.setImageResource(R.mipmap.zwtu)
    } else {
        Glide.with(mContext!!).load(path)
            .skipMemoryCache(false)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .placeholder(placeholder)
            .error(placeholder)
            .dontAnimate()
            .into(imgIv)
    }
}

fun showmdImage(mContext: Context?, path: String?, imgIv: ImageView) {
    if (path == null || path == "") {
        imgIv.setImageResource(R.mipmap.mdzwtu)
    } else {
        Glide.with(mContext!!).load(path)
            .skipMemoryCache(false)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .placeholder(R.mipmap.ic_default)
            .error(R.mipmap.ic_default)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable?>, isFirstResource: Boolean): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any,
                    target: Target<Drawable?>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            })
            .dontAnimate()
            .into(imgIv)
    }
}

fun showUserImage(mContext: Context?, path: String?, imgIv: ImageView) {
    if (path == null || path == "") {
        imgIv.setImageResource(R.mipmap.home_image_face)
    } else {
        Glide.with(mContext!!).load(path)
            .skipMemoryCache(false)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .placeholder(R.mipmap.moren_toolbg)
            .error(R.mipmap.moren_toolbg)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable?>, isFirstResource: Boolean): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any,
                    target: Target<Drawable?>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            })
            .dontAnimate()
            .into(imgIv)
    }
}

/**
 * 重新加载
 */
fun showAgainImg(mContext: Context?, path: String?, imgIv: ImageView,onLoadFailed: (() -> Unit)? = null) {
    if (path == null || path == "") {
        imgIv.setImageResource(R.mipmap.home_image_face)
    } else {
        Glide.with(mContext!!).load(path)
            .skipMemoryCache(false)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .placeholder(R.mipmap.moren_toolbg)
            .error(R.mipmap.moren_toolbg)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable?>, isFirstResource: Boolean): Boolean {
                   Log.d("showAgainImg","onLoadFailed 图片加载失败")
                    onLoadFailed?.invoke()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any,
                    target: Target<Drawable?>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
//  Log.d("showAgainImg","onLoadFailed 图片加载成功")
//                    imgIv.setImageDrawable(resource)
                    return false
                }
            })
//            .dontAnimate()
            .into(imgIv)
    }
}


fun showRoundedImg(mContext: Context, path: Int, imgIv: ImageView, roundedCorners:Int){
    var roundedCorners : RoundedCorners =  RoundedCorners(roundedCorners);//数字为圆角度数
    var coverRequestOptions : RequestOptions =  RequestOptions()
        .transforms( CenterCrop(),roundedCorners)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//不做磁盘缓存
        .skipMemoryCache(false);//不做内存缓存
    Glide.with(mContext)
        .load(path).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .error(R.mipmap.home_image_placeholder3)
        .placeholder(R.mipmap.home_image_placeholder3)
        .apply(coverRequestOptions)
        .dontAnimate()
        .into(imgIv)
}
fun showRoundedImg(mContext: Context, path: String, imgIv: ImageView, roundedCorners:Int){
    var roundedCorners : RoundedCorners =  RoundedCorners(roundedCorners);//数字为圆角度数
    var coverRequestOptions : RequestOptions =  RequestOptions()
        .transforms( CenterCrop(),roundedCorners)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//不做磁盘缓存
        .skipMemoryCache(false);//不做内存缓存
    Glide.with(mContext)
        .load(path).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .error(R.mipmap.home_image_placeholder3)
        .placeholder(R.mipmap.home_image_placeholder3)
        .apply(coverRequestOptions)
        .dontAnimate()
        .into(imgIv)
}
fun showUserImage(mContext: Context, path: String, imgIv: ImageView,placeholder:Int) {
    if (path == null || path == "") {
        imgIv.setImageResource(placeholder)
    } else {
        Glide.with(mContext)
            .load(path).skipMemoryCache(false).diskCacheStrategy(
                DiskCacheStrategy.RESOURCE
            )
            .placeholder(placeholder)
            .error(placeholder)
            .into(imgIv)
    }
}

fun getBitmapImg (mContext: Context, path: String, resourceReady:((resource: Bitmap) -> Unit)? = null){
    Glide.with(mContext).asBitmap().load(path).into(object : SimpleTarget<Bitmap?>() {

        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?) {
//            decode(resource)
            resourceReady?.invoke(resource)
        }
    })
}




