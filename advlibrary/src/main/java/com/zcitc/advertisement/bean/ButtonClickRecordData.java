package com.zcitc.advertisement.bean;

import java.io.Serializable;

public class ButtonClickRecordData implements Serializable {

    /**
     *userId	string($guid)
     * 用户
     *
     * triggerTime	string($date-time)
     * title: 触发时间
     *
     *
     *
     * deviceModel	string
     * title: 设备型号
     *
     *
     * deviceId	string
     * title: 设备标识
     *
     *
     * deviceNetwork	string
     * title: 网络环境
     *
     *
     * page	AppPageOptionsinteger
     * APP页面 1 = 新房详情页 2 = 摇号房详情页 3 = 预展房详情页
     *
     * button	AppButtonOptionsinteger
     * APP按钮 1001 = 拨打电话按钮
     *
     * 1002 = 在线咨询按钮
     *
     * 1003 = 顾问咨询按钮
     *
     * bizDataType	BizDataSourceOptionsinteger
     * 业务数据来源 0 = 未指定 1 = 新闻 2 = 房源 3 = 二手房房源 4 = 摇号房房源 5 = 新房房源:->BizDataId记用途Id(=PurposeId) 6 = 租赁房房源 7 = 预展楼盘 11 = 顾问 12 = 中介机构(含:门店) 13 = 中介 14 = 金融机构 15 = 广告详情 21 = 小区
     *
     * bizDataId	string
     * title: 业务数据Id
     * nullable: true
     * 业务数据Id
     */

    private String userId;
    private long triggerTime;
    private String deviceModel;
    private String deviceId;
    private String deviceNetwork;
    private String page;
    private String button;
    private String bizDataType;
    private String bizDataId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(long triggerTime) {
        this.triggerTime = triggerTime;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceNetwork() {
        return deviceNetwork;
    }

    public void setDeviceNetwork(String deviceNetwork) {
        this.deviceNetwork = deviceNetwork;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getBizDataType() {
        return bizDataType;
    }

    public void setBizDataType(String bizDataType) {
        this.bizDataType = bizDataType;
    }

    public String getBizDataId() {
        return bizDataId;
    }

    public void setBizDataId(String bizDataId) {
        this.bizDataId = bizDataId;
    }

    @Override
    public String toString() {
        return "ButtonClickRecordData{" +
                "userId='" + userId + '\'' +
                ", triggerTime=" + triggerTime +
                ", deviceModel='" + deviceModel + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", deviceNetwork='" + deviceNetwork + '\'' +
                ", page='" + page + '\'' +
                ", button='" + button + '\'' +
                ", bizDataType='" + bizDataType + '\'' +
                ", bizDataId='" + bizDataId + '\'' +
                '}';
    }
}
