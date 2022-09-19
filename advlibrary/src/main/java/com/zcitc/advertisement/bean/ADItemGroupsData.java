package com.zcitc.advertisement.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ADItemGroupsData implements Serializable {

    public String id = "";
    public String code = "";
    public int orderMode = 3;
    public int maxDisplays = 1;
    public boolean isNullData ;

    public List<ADPlanItemsData> advItems = new ArrayList<>() ;

    public boolean isNullData() {
        return isNullData;
    }

    public void setNullData(boolean nullData) {
        isNullData = nullData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getOrderMode() {
        return orderMode;
    }

    public void setOrderMode(int orderMode) {
        this.orderMode = orderMode;
    }

    public int getMaxDisplays() {
        return maxDisplays;
    }

    public void setMaxDisplays(int maxDisplays) {
        this.maxDisplays = maxDisplays;
    }

    public List<ADPlanItemsData> getAdvItems() {
        return advItems;
    }

    public void setAdvItems(List<ADPlanItemsData> advItems) {
        this.advItems = advItems;
    }
}

