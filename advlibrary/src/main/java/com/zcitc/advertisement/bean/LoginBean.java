package com.zcitc.advertisement.bean;

import java.util.ArrayList;
import java.util.List;

public class LoginBean {
    private String accessToken;
    private String accessTokenExpiredDate;
    private Long accessTokenExpiredIn;





    private String refreshCode;
    private String refreshCodeExpiredDate;
    private String userId;
    private String anonymous;
    private String avatarUrl;
    private Long refreshCodeExpiredIn;


    public String getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(String anonymous) {
        this.anonymous = anonymous;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAccessToken() {
        return checkNull(accessToken);
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessTokenExpiredDate() {
        return accessTokenExpiredDate;
    }

    public void setAccessTokenExpiredDate(String accessTokenExpiredDate) {
        this.accessTokenExpiredDate = accessTokenExpiredDate;
    }

    public String getRefreshCode() {
        return checkNull(refreshCode);
    }

    public void setRefreshCode(String refreshCode) {
        this.refreshCode = refreshCode;
    }

    public String getRefreshCodeExpiredDate() {
        return refreshCodeExpiredDate;
    }

    public void setRefreshCodeExpiredDate(String refreshCodeExpiredDate) {
        this.refreshCodeExpiredDate = refreshCodeExpiredDate;
    }

    public String getUserId() {
        return checkNull(userId);
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getAccessTokenExpiredIn() {
        return accessTokenExpiredIn;
    }

    public void setAccessTokenExpiredIn(Long accessTokenExpiredIn) {
        this.accessTokenExpiredIn = accessTokenExpiredIn;
    }

    public Long getRefreshCodeExpiredIn() {
        return refreshCodeExpiredIn;
    }

    public void setRefreshCodeExpiredIn(Long refreshCodeExpiredIn) {
        this.refreshCodeExpiredIn = refreshCodeExpiredIn;
    }

    private String checkNull(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "accessToken='" + accessToken + '\'' +
                ", accessTokenExpiredDate='" + accessTokenExpiredDate + '\'' +
                ", refreshCode='" + refreshCode + '\'' +
                ", refreshCodeExpiredDate='" + refreshCodeExpiredDate + '\'' +
                ", userId='" + userId + '\'' +
                ", accessTokenExpiredIn=" + accessTokenExpiredIn +
                ", refreshCodeExpiredIn=" + refreshCodeExpiredIn +
                '}';
    }
}
