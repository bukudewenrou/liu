package com.zcitc.advertisement.bean;

/**
 * author : LiuJie
 * date   : 2021/3/2310:50
 */
public class AdvBean {


    /**
     * userId : string
     * triggerTime : 2021-03-26T04:51:22.200Z
     * deviceModel : string
     * deviceId : string
     * deviceNetwork : string
     * elementPlanItemId : string
     * elementPlanId : string
     * elementBoxConfigId : string
     */

    private String userId;
    private long triggerTime;
    private String deviceModel;
    private String deviceId;
    private String deviceNetwork;
    private String advItemId;
    private String advPositionId;
    private String elementBoxConfigId;

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

    public String getAdvItemId() {
        return advItemId;
    }

    public void setAdvItemId(String advItemId) {
        this.advItemId = advItemId;
    }

    public String getAdvPositionId() {
        return advPositionId;
    }

    public void setAdvPositionId(String advPositionId) {
        this.advPositionId = advPositionId;
    }

    public String getElementBoxConfigId() {
        return elementBoxConfigId;
    }

    public void setElementBoxConfigId(String elementBoxConfigId) {
        this.elementBoxConfigId = elementBoxConfigId;
    }
}
