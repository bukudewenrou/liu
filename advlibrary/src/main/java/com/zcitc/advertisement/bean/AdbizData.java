package com.zcitc.advertisement.bean;

import java.io.Serializable;

public class AdbizData implements Serializable {
    private String realName;
    private String avatarUrl;
    private String tel;
    private String agencyShortName;
    private String shortName;
    private String imageUrl;
    private String enterpriseShortName;
    private String enterpriseName;
    private String districtId;
    private String districtName;
    private String multimediaUrl;
    private String name;
    private String agencyName;
    private String content;
    private String eventLink;

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getEventLink() {
        return eventLink;
    }

    public void setEventLink(String eventLink) {
        this.eventLink = eventLink;
    }

    public String getMultimediaUrl() {
        return multimediaUrl;
    }

    public void setMultimediaUrl(String multimediaUrl) {
        this.multimediaUrl = multimediaUrl;
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

    public String getRealName() {
        if(realName!=null){
            return realName;
        }
        return "";
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAgencyShortName() {
        if(agencyShortName==null){
            return agencyName;
        }
        if(agencyShortName.equals("")){
            return agencyName;
        }
        return "";
    }

    public void setAgencyShortName(String agencyShortName) {
        this.agencyShortName = agencyShortName;
    }

    public String getShortName() {
        if(shortName==null){
            return name;
        }
        if(shortName.equals("")){
            return name;
        }
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEnterpriseShortName() {
        if(enterpriseShortName==null){
            return enterpriseName;
        }
        if(enterpriseShortName.equals("")){
            return enterpriseName;
        }
        return enterpriseShortName;
    }

    public void setEnterpriseShortName(String enterpriseShortName) {
        this.enterpriseShortName = enterpriseShortName;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
