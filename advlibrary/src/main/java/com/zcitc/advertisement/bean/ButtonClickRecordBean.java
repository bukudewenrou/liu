package com.zcitc.advertisement.bean;

import com.zcitc.advertisement.bean.ButtonClickRecordData;

import java.util.ArrayList;
import java.util.List;

public class ButtonClickRecordBean {
    List<ButtonClickRecordData> buttonClickRecordData = new ArrayList<ButtonClickRecordData>();

    public List<ButtonClickRecordData> getButtonClickRecordData() {
        return buttonClickRecordData;
    }

    public void setButtonClickRecordData(List<ButtonClickRecordData> buttonClickRecordData) {
        this.buttonClickRecordData = buttonClickRecordData;
    }
}
