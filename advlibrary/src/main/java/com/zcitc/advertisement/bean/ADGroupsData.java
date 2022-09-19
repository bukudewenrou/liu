package com.zcitc.advertisement.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ADGroupsData implements Serializable {
    public String version;
    public boolean isNullData;
    public List<ADItemGroupsData> advConfigs = new ArrayList<ADItemGroupsData>();

    public List<ADItemGroupsData> getAdvConfigs() {
        return advConfigs;
    }

    public void setAdvConfigs(List<ADItemGroupsData> advConfigs) {
        this.advConfigs = advConfigs;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isNullData() {
        return isNullData;
    }

    public void setNullData(boolean nullData) {
        isNullData = nullData;
    }
}
