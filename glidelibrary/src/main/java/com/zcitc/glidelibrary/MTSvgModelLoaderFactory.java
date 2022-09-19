package com.zcitc.glidelibrary;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;

import java.io.InputStream;

public class MTSvgModelLoaderFactory implements ModelLoaderFactory<String, InputStream> {
    @NonNull
    @Override
    public ModelLoader<String, InputStream> build(@NonNull MultiModelLoaderFactory multiFactory) {
        return new MTSvgModelLoader();
    }

    @Override
    public void teardown() {

    }
}
