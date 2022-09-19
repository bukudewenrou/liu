package com.zcitc.maplibrary;

import android.content.Context;

import com.amap.api.location.AMapLocationClient;

import java.util.HashMap;

public class AMapLocationUtils {
    private static volatile AMapLocationUtils singleton = null;

    public static AMapLocationUtils getInstance() {
        if (singleton == null) {
            synchronized (AMapLocationUtils.class) {
                if (singleton == null) {
                    singleton = new AMapLocationUtils();
                }
            }
        }
        return singleton;
    }

    private AMapLocationUtils() {
        mClientList = new HashMap<>();
    }

    private HashMap<String, AMapLocationBuilder> mClientList;
    private AMapLocationClient client;

    public void init(Context context) {
        mClientList.clear();
        if (client == null) {
            try {
                client = new AMapLocationClient(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        if (client != null && !client.isStarted()) {
            client.startLocation();
        }
    }

    public void stop() {
        if (client != null && client.isStarted()) {
            client.stopLocation();
        }
    }

    public void destroy() {
        if (client != null) {
            if (client.isStarted()) {
                client.stopLocation();
            }
            client.onDestroy();
            client = null;
        }
    }

    public AMapLocationBuilder option(String tag) {
        if (null == mClientList.get(tag)) {
            return new AMapLocationBuilder(tag);
        }
        return mClientList.get(tag);
    }

    void commit(String tag, AMapLocationBuilder builder) {
        if (client != null) {
            if (null != builder.mAMapLocationListener) {
                client.setLocationListener(builder.mAMapLocationListener);
            }
            client.setLocationOption(builder.option);
            if (client.isStarted()) {
                client.stopLocation();
                client.startLocation();
            }
        }
        mClientList.put(tag, builder);
    }
}
