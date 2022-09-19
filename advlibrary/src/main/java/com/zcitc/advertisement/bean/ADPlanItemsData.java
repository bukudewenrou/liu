package com.zcitc.advertisement.bean;


import static com.zcitc.advertisement.utils.UtilsToosKt.isShowAd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class ADPlanItemsData implements Serializable {


    private int type;
    private String multimediaUrl;
    private String bizDataId;
    private String validStartDate;
    private String validEndDate;
    private String eventLink;
    private String auditTime;
    private String elementPlanItemOrderNo;
    private String advPositionId;
    private String id;
    private String advItemId = "";
    private String contentType;
    private String multimediaContentType;
    private String bizDataSource;
    private String autoCloseSecond;
    private String elementGroupId;
    private String orderNo;
    private String displayMode;
    private String userId;
    private long triggerTime;
    private String deviceModel;
    private String deviceId;
    private String deviceNetwork;
    private AdbizData bizData;
    private String name;
    private String content;
    private String imgUrl = "";

    public String getImgUrl() {
        if("1".equals(contentType)){
            return getMultimediaUrl();
        }else if("2".equals(contentType)){
            if(bizData!=null){
                if(null == bizData.getImageUrl()){
                    if(null != bizData.getAvatarUrl()){
                        return  bizData.getAvatarUrl();
                    }
                    return  "";
                }
                return  bizData.getImageUrl();
            }
            return "";
        }
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public String getId() {
        if(id==null||id.equals("")){
            return advItemId;
        }
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isShow(){
        if (isShowAd(validStartDate, validEndDate)) {
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AdbizData getBizData() {
        return bizData;
    }

    public void setBizData(AdbizData bizData) {
        this.bizData = bizData;
    }

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

    private List<ADTextsData> texts = new ArrayList<ADTextsData>();



    public String getMultimediaUrl() {
        if(multimediaUrl!=null){
            String tags = multimediaUrl;
            String[] tagsList = null;
            if(tags!=null&&!tags.equals("")){
                tagsList = tags.split(",");
            }
            if(tagsList.length>0){
                return tagsList[0];
            }else{
                return multimediaUrl;
            }
        }
        return "";
    }
    public String[] getMultimediaUrlList() {
        String[] tagsList = null;
        if(multimediaUrl!=null){
            String tags = multimediaUrl;

            if(tags!=null&&!tags.equals("")){
                tagsList = tags.split(",");
            }

        }
        return tagsList;
    }
    public void setMultimediaUrl(String multimediaUrl) {
        this.multimediaUrl = multimediaUrl;
    }

    public String getBizDataId() {
        return bizDataId;
    }

    public void setBizDataId(String bizDataId) {
        this.bizDataId = bizDataId;
    }

    public String getValidStartDate() {
        return validStartDate;
    }

    public void setValidStartDate(String validStartDate) {
        this.validStartDate = validStartDate;
    }

    public String getValidEndDate() {
        return validEndDate;
    }

    public void setValidEndDate(String validEndDate) {
        this.validEndDate = validEndDate;
    }

    public String getEventLink() {
        return eventLink;
    }

    public void setEventLink(String eventLink) {
        this.eventLink = eventLink;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getElementPlanItemOrderNo() {
        return elementPlanItemOrderNo;
    }

    public void setElementPlanItemOrderNo(String elementPlanItemOrderNo) {
        this.elementPlanItemOrderNo = elementPlanItemOrderNo;
    }





    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getMultimediaContentType() {
        return multimediaContentType;
    }

    public void setMultimediaContentType(String multimediaContentType) {
        this.multimediaContentType = multimediaContentType;
    }

    public String getBizDataSource() {
        return bizDataSource;
    }

    public void setBizDataSource(String bizDataSource) {
        this.bizDataSource = bizDataSource;
    }

    public String getAutoCloseSecond() {
        return autoCloseSecond;
    }
    public int getAutoCloseSecondInt() {
        if(autoCloseSecond==null){
            return 4;
        }
        if(autoCloseSecond.equals("")){
            return 4;
        }
        try{
            int autoCloseSecondInt = Integer.parseInt(autoCloseSecond)  ;
            if(autoCloseSecondInt<=0){
                return 4;
            }else{
                return autoCloseSecondInt+1;
            }
        }catch(Exception e) {

        }

        return 4;
    }
    public void setAutoCloseSecond(String autoCloseSecond) {
        this.autoCloseSecond = autoCloseSecond;
    }

    public String getElementGroupId() {
        return elementGroupId;
    }

    public void setElementGroupId(String elementGroupId) {
        this.elementGroupId = elementGroupId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getDisplayMode() {
        return displayMode;
    }

    public void setDisplayMode(String displayMode) {
        this.displayMode = displayMode;
    }

    public List<ADTextsData> getTexts() {
        return texts;
    }

    public void setTexts(List<ADTextsData> texts) {
        this.texts = texts;
    }

    @Override
    public String toString() {
        return "ADPlanItemsData{" +
                "type=" + type +
                ", multimediaUrl='" + multimediaUrl + '\'' +
                ", bizDataId='" + bizDataId + '\'' +
                ", validStartDate='" + validStartDate + '\'' +
                ", validEndDate='" + validEndDate + '\'' +
                ", eventLink='" + eventLink + '\'' +
                ", auditTime='" + auditTime + '\'' +
                ", elementPlanItemOrderNo='" + elementPlanItemOrderNo + '\'' +
                ", advPositionId='" + advPositionId + '\'' +
                ", id='" + id + '\'' +
                ", advItemId='" + advItemId + '\'' +
                ", contentType='" + contentType + '\'' +
                ", multimediaContentType='" + multimediaContentType + '\'' +
                ", bizDataSource='" + bizDataSource + '\'' +
                ", autoCloseSecond='" + autoCloseSecond + '\'' +
                ", elementGroupId='" + elementGroupId + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", displayMode='" + displayMode + '\'' +
                ", userId='" + userId + '\'' +
                ", triggerTime='" + triggerTime + '\'' +
                ", deviceModel='" + deviceModel + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", deviceNetwork='" + deviceNetwork + '\'' +
                ", bizData=" + bizData +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", texts=" + texts +
                '}';
    }
}
