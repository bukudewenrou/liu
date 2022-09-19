package com.zcitc.advertisement.bean;

import java.io.Serializable;

public class ADTextsData implements Serializable {

    /**
     * name : 标题
     * code : title
     * content : 舒适好房 公园天下
     */

    private String name;
    private String code;
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
