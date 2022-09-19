package com.zcitc.glidelibrary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;

import java.io.InputStream;

public class MTSvgModelLoader implements ModelLoader<String, InputStream> {
    @Nullable
    @Override
    public LoadData<InputStream> buildLoadData(@NonNull String mtigqsvgItem, int width, int height, @NonNull Options options) {
        Key diskCacheKey = new ObjectKey(mtigqsvgItem);
        return new LoadData<>(diskCacheKey, new MTSvgFetcher(mtigqsvgItem));
    }

    @Override
    public boolean handles(@NonNull String mtigqsvgItem) {
        return true;

    }
}
