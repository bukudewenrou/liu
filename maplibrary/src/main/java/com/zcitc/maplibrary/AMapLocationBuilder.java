package com.zcitc.maplibrary;

import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

public class AMapLocationBuilder {
    AMapLocationClientOption option;
    String tag;
    AMapLocationListener mAMapLocationListener;

    AMapLocationBuilder(String tag) {
        this.tag = tag;
        this.option = new AMapLocationClientOption();
    }

    public void commit() {
        AMapLocationUtils.getInstance().commit(tag, this);
    }

    //设置定位场景
    public AMapLocationBuilder setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose var) {
        option.setLocationPurpose(var);
        return this;
    }

    //选择定位模式
    public AMapLocationBuilder setLocationMode(AMapLocationClientOption.AMapLocationMode var) {
        option.setLocationMode(var);
        return this;
    }

    //设置单次定位
    public AMapLocationBuilder setOnceLocation(boolean var) {
        option.setOnceLocation(var);
        return this;
    }

    //获取最近3s内精度最高的一次定位结果
    public AMapLocationBuilder setOnceLocationLatest(boolean var) {
        option.setOnceLocationLatest(var);
        return this;
    }

    //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
    public AMapLocationBuilder setInterval(long var) {
        option.setInterval(var);
        return this;
    }

    //设置是否返回地址信息（默认返回地址信息）
    public AMapLocationBuilder setNeedAddress(boolean var) {
        option.setNeedAddress(var);
        return this;
    }

    //设置是否允许模拟位置,默认为true，允许模拟位置
    public AMapLocationBuilder setMockEnable(boolean var) {
        option.setMockEnable(var);
        return this;
    }

    //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
    public AMapLocationBuilder setHttpTimeOut(long var) {
        option.setHttpTimeOut(var);
        return this;
    }

    //关闭缓存机制
    public AMapLocationBuilder setLocationCacheEnable(boolean var) {
        option.setLocationCacheEnable(var);
        return this;
    }

    //结果返回回调
    public AMapLocationBuilder setAMapLocationListener(AMapLocationListener var) {
        this.mAMapLocationListener = var;
        return this;
    }
}
