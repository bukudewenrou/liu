package com.zcitc.advertisement.bean;



import static com.zcitc.advertisement.adv.ADItemGroupsUtils.FIRST_PAGE_AGENCY_ADS;
import static com.zcitc.advertisement.adv.ADItemGroupsUtils.FIRST_PAGE_AGENT_ADS;
import static com.zcitc.advertisement.adv.ADItemGroupsUtils.FIRST_PAGE_CONSULTANT_ADS;
import static com.zcitc.advertisement.adv.ADItemGroupsUtils.FIRST_PAGE_FINANCIAL_INSTITUTION_ADS;

import java.util.ArrayList;
import java.util.List;

/**
 * author : LiuJie
 * date   : 2021/3/114:39
 */
public class AdvertisementRecommendationData {

    /**
     * count : 4
     * datas : [{"advertisementId":"155d2990-80af-4064-a8f8-0d6a4a88d5f4","tags":"优秀经纪人","groupName":"firstpage_recommendation","imageUrl":null,"startTime":"2021-02-26T08:25:13.367","endTime":"2022-02-26T08:25:13.367","orderNo":2,"redirectUrl":"https://gateway.cnnbfdc.com/agency-union/api/practitioner/list?page1&size=3&order=1","isPublished":false,"createUserGUID":"00000000-0000-0000-0000-000000000000","createDate":"2021-02-26T16:39:50.5479145","lastModifiedUserGUID":null,"lastModifiedDate":null,"validity":false,"memo":"优秀中介"},{"advertisementId":"0a591c14-fd9f-4fdf-82e2-af3061f569f4","tags":"金融机构","groupName":"firstpage_recommendation","imageUrl":null,"startTime":"2021-02-26T08:25:13.367","endTime":"2022-02-26T08:25:13.367","orderNo":3,"redirectUrl":"https://gateway.fyitc.com/admin/nbfc/api/fang/advertisement/list?groupName=firstpage_bank","isPublished":false,"createUserGUID":"00000000-0000-0000-0000-000000000000","createDate":"2021-02-26T16:38:41.8630844","lastModifiedUserGUID":null,"lastModifiedDate":null,"validity":false,"memo":"优秀门店"},{"advertisementId":"0a591c14-fd9f-4fdf-82e2-af3061f569fb","tags":"优秀门店","groupName":"firstpage_recommendation","imageUrl":null,"startTime":"2021-02-26T08:25:13.367","endTime":"2022-02-26T08:25:13.367","orderNo":1,"redirectUrl":"https://gateway.cnnbfdc.com/nbfc/gz/2.0/api/cx/agency/list?orderby=1&page1&size=3","isPublished":false,"createUserGUID":"00000000-0000-0000-0000-000000000000","createDate":"2021-02-26T16:38:41.8630844","lastModifiedUserGUID":null,"lastModifiedDate":null,"validity":false,"memo":"优秀门店"},{"advertisementId":"fb0c04b4-9ea3-46fc-be40-563ca97f7711","tags":"优秀顾问","groupName":"firstpage_recommendation","imageUrl":null,"startTime":"2021-02-26T08:25:13.367","endTime":"2022-02-26T08:25:13.367","orderNo":0,"redirectUrl":"https://gateway.cnnbfdc.com/nbfc/gz/2.0/to_be_sold/project_batch/sale_agency_party?toBeSoldProjectBatchGUID=d716e475-490f-eb11-8ac5-a41f72ef1652&page1&size=3","isPublished":false,"createUserGUID":"00000000-0000-0000-0000-000000000000","createDate":"2021-02-26T16:37:01.7141799","lastModifiedUserGUID":null,"lastModifiedDate":null,"validity":false,"memo":"优秀顾问"}]
     */
    private boolean isNullData;
    private int count;
    private List<DatasBean> datas = new ArrayList<>();
    private List<ElementPlanItemGroupsBean> elementPlanItemGroups  = new ArrayList<>();
    private List<BizDataSourceApiConfigsBean> bizDataSourceApiConfigs  = new ArrayList<>();

    public boolean isNullData() {
        return isNullData;
    }

    public void setNullData(boolean nullData) {
        isNullData = nullData;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public List<ElementPlanItemGroupsBean> getElement_plan_item_groups() {
        return elementPlanItemGroups;
    }

    public void setElement_plan_item_groups(List<ElementPlanItemGroupsBean> elementPlanItemGroups) {
        this.elementPlanItemGroups = elementPlanItemGroups;
    }

    public List<BizDataSourceApiConfigsBean> getBiz_data_source_api_configs() {
        return bizDataSourceApiConfigs;
    }

    public void setBiz_data_source_api_configs(List<BizDataSourceApiConfigsBean> bizDataSourceApiConfigs) {
        this.bizDataSourceApiConfigs = bizDataSourceApiConfigs;
    }

    public static class DatasBean {
        /**
         * advertisementId : 155d2990-80af-4064-a8f8-0d6a4a88d5f4
         * tags : 优秀经纪人
         * groupName : firstpage_recommendation
         * imageUrl : null
         * startTime : 2021-02-26T08:25:13.367
         * endTime : 2022-02-26T08:25:13.367
         * orderNo : 2
         * redirectUrl : https://gateway.cnnbfdc.com/agency-union/api/practitioner/list?page1&size=3&order=1
         * isPublished : false
         * createUserGUID : 00000000-0000-0000-0000-000000000000
         * createDate : 2021-02-26T16:39:50.5479145
         * lastModifiedUserGUID : null
         * lastModifiedDate : null
         * validity : false
         * memo : 优秀中介
         */

        private String advertisementId;
        private String districtName;
        private String tags;
        private String groupName;
        private String imageUrl;
        private String startTime;
        private String endTime;
        private int orderNo;

        private int menuCode = 0;
        private String redirectUrl;
        private boolean isPublished;
        private String createUserGUID;
        private String createDate;
        private String lastModifiedUserGUID;
        private String lastModifiedDate;
        private boolean validity;
        private String memo;
        private String officeAddress;
        private String agencyName;
        private String codeStr = "";



        /**
         *
         *   {
         *         /// <summary>
         *         ///新房热销榜
         *         /// </summary>
         *         [Display(Name = "新房热销榜")]
         *         NewHotSaleRank = 1,
         *
         *         /// <summary>
         *         /// 新房认购榜
         *         /// </summary>
         *         [Display(Name = "新房认购榜")]
         *         NewBuyIntentionRank = 11,
         *
         *         /// <summary>
         *         /// 二手房人气榜
         *         /// </summary>
         *         [Display(Name = "二手房人气榜")]
         *         ESFPopularityRank = 21,
         *
         *         /// <summary>
         *         ///二手房捡漏榜
         *         /// </summary>
         *         [Display(Name = "二手房捡漏榜")]
         *         ESFLeakPicking = 31,
         *
         *         /// <summary>
         *         ///优秀顾问
         *         /// </summary>
         *         [Display(Name = "优秀顾问")]
         *         GoodConsultant = 41,
         *
         *         /// <summary>
         *         /// 优秀中介
         *         /// </summary>
         *         [Display(Name = "优秀中介")]
         *         GoodPractitioner = 51,
         *
         *         /// <summary>
         *         /// 优秀门店
         *         /// </summary>
         *         [Display(Name = "优秀门店")]
         *         GoodAgencyStore = 61,
         *
         *         /// <summary>
         *         ///金融机构
         *         /// </summary>
         *         [Display(Name = "金融机构")]
         *         FinancialInstitution = 71
         *     }
         *      if (type.equals("FIRST_PAGE_CONSULTANT_ADS")) {
         *             //优秀顾问,
         *             adapter = RecommendAdapter(dataList, R.layout.item_recommend, 11)
         *
         *         } else if (type.equals("FIRST_PAGE_AGENT_ADS")) {
         *             //优秀中介
         *             adapter = RecommendAdapter(dataList, R.layout.item_recommend, 21)
         *         } else if (type.equals("FIRST_PAGE_AGENCY_ADS")) {
         *             //优秀门店
         *             adapter = RecommendAdapter(dataList, R.layout.item_recommend2, 1)
         *         } else if (type.equals("FIRST_PAGE_FINANCIAL_INSTITUTION_ADS")) {
         */

        public String getCodeStr() {
            codeStr = "";
            switch (menuCode){
                case 41:
                    codeStr = FIRST_PAGE_CONSULTANT_ADS;
                    break;
                case 51:
                    codeStr = FIRST_PAGE_AGENT_ADS;
                    break;
                case 61:
                    codeStr = FIRST_PAGE_AGENCY_ADS;
                    break;
                case 71:
                    codeStr = FIRST_PAGE_FINANCIAL_INSTITUTION_ADS;
                    break;
            }
            return codeStr;
        }

        public int getMenuCode() {
            return menuCode;
        }

        public void setMenuCode(int menuCode) {
            this.menuCode = menuCode;
        }

        public String getAgencyName() {
            return agencyName;
        }

        public void setAgencyName(String agencyName) {
            this.agencyName = agencyName;
        }

        public String getOfficeAddress() {
            return officeAddress;
        }

        public void setOfficeAddress(String officeAddress) {
            this.officeAddress = officeAddress;
        }

        public String getDistrictName() {
            return districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }

        public String getAdvertisementId() {
            return advertisementId;
        }

        public void setAdvertisementId(String advertisementId) {
            this.advertisementId = advertisementId;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public Object getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(int orderNo) {
            this.orderNo = orderNo;
        }

        public String getRedirectUrl() {
            return redirectUrl;
        }

        public void setRedirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
        }

        public boolean isIsPublished() {
            return isPublished;
        }

        public void setIsPublished(boolean isPublished) {
            this.isPublished = isPublished;
        }

        public String getCreateUserGUID() {
            return createUserGUID;
        }

        public void setCreateUserGUID(String createUserGUID) {
            this.createUserGUID = createUserGUID;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getLastModifiedUserGUID() {
            return lastModifiedUserGUID;
        }

        public void setLastModifiedUserGUID(String lastModifiedUserGUID) {
            this.lastModifiedUserGUID = lastModifiedUserGUID;
        }

        public String getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(String lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public boolean isValidity() {
            return validity;
        }

        public void setValidity(boolean validity) {
            this.validity = validity;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }


    }



    public static class ElementPlanItemGroupsBean {
        /**
         * group_code : FIRST_PAGE_CONSULTANT_ADS
         * groupTitle : 优秀顾问
         * group_order_no : 1
         * children_element_groups : []
         * element_plan_items : [{"id":"82dd53ee3dbf4b70b6abacee00d3038a","multimedia_url":"http://files.k8s.zcpk.work/slot1.parking/202103/17/1000-1100/20210317102032388-830849cbdf64480283c24303ad6c7b7d.png","biz_data_id":null,"event_link":"事件链接","element_plan_id":"1bff2d090c1c48db8635aced00b1a42d","element_box_config_id":"95bf3c22252144d39823aced011c1e5b","texts":[],"order_no":1,"content_type":2,"multimedia_content_type":0,"multimedia_count":0,"biz_data_source":11,"display_mode":1,"auto_close_second":null,"is_open":false,"group_code":"FIRST_PAGE_CONSULTANT_ADS"},{"id":"2349fd1000004b569439acee00d32510","multimedia_url":"http://files.k8s.zcpk.work/slot1.parking/202103/17/1000-1100/20210317102032388-830849cbdf64480283c24303ad6c7b7d.png","biz_data_id":null,"event_link":"事件链接","element_plan_id":"1bff2d090c1c48db8635aced00b1a42d","element_box_config_id":"16ea9fb138cc49489420aced011c468f","texts":[],"order_no":2,"content_type":2,"multimedia_content_type":0,"multimedia_count":0,"biz_data_source":11,"display_mode":1,"auto_close_second":null,"is_open":false,"group_code":"FIRST_PAGE_CONSULTANT_ADS"},{"id":"2311b96cfa2f4e319f97acee00d32efd","multimedia_url":"http://files.k8s.zcpk.work/slot1.parking/202103/17/1000-1100/20210317102032388-830849cbdf64480283c24303ad6c7b7d.png","biz_data_id":null,"event_link":"事件链接","element_plan_id":"1bff2d090c1c48db8635aced00b1a42d","element_box_config_id":"c8badba3869e448d98e2aced011c5714","texts":[],"order_no":3,"content_type":2,"multimedia_content_type":0,"multimedia_count":0,"biz_data_source":11,"display_mode":1,"auto_close_second":null,"is_open":false,"group_code":"FIRST_PAGE_CONSULTANT_ADS"}]
         */

        private String groupCode;
        private String groupTitle;
        private String groupOrderNo;
        private int orderNo;

        public int getContentType() {
            return contentType;
        }

        public void setContentType(int contentType) {
            this.contentType = contentType;
        }

        /**
         * 0按链接取
         * 1直接拿值
         */
        private int contentType = 0;

        public int getOrderNo() {
            if (FIRST_PAGE_CONSULTANT_ADS.equals(groupCode)) {
                orderNo = 11;
            } else if (FIRST_PAGE_AGENCY_ADS.equals(groupCode)) {
                orderNo = 1;
            } else if (FIRST_PAGE_AGENT_ADS.equals(groupCode)) {
                orderNo = 21;
            } else if (FIRST_PAGE_FINANCIAL_INSTITUTION_ADS.equals(groupCode)) {
                orderNo = 31;
            }
            return orderNo;
        }

        private List<ElementPlanItemsBean> elementPlanItems = new ArrayList<>();
        private String event_url = "";

        public String getEvent_url() {
            String bizDataIds = "";
            for (int i = 0; i < elementPlanItems.size(); i++) {
                if(i==0){
                    bizDataIds =  elementPlanItems.get(i).bizDataId;
                }else {
                    bizDataIds = bizDataIds+ "," + elementPlanItems.get(i).bizDataId ;
                }

            }
            if (elementPlanItems!=null&&elementPlanItems.size() > 0) {
                if(biz_data_source_api_configs!=null){
                    for (int i = 0; i < biz_data_source_api_configs.size(); i++) {
                        if (biz_data_source_api_configs.get(i).bizDataSource == elementPlanItems.get(0).getBiz_data_source()) {
                            event_url = biz_data_source_api_configs.get(i).apiUrlTpl;
                            event_url = event_url.replace("{ids}", bizDataIds);
                        }
                    }
                }


            }

            return event_url;
        }

        private List<BizDataSourceApiConfigsBean> biz_data_source_api_configs;

        public List<BizDataSourceApiConfigsBean> getBiz_data_source_api_configs() {
            return biz_data_source_api_configs;
        }

        public void setBiz_data_source_api_configs(List<BizDataSourceApiConfigsBean> biz_data_source_api_configs) {
            this.biz_data_source_api_configs = biz_data_source_api_configs;
        }

        public String getGroup_code() {
            return groupCode;
        }

        public void setGroup_code(String groupCode) {
            this.groupCode = groupCode;
        }

        public String getGroup_title() {
            return groupTitle;
        }

        public void setGroup_title(String groupTitle) {
            this.groupTitle = groupTitle;
        }

        public String getGroup_order_no() {
            return groupOrderNo;
        }

        public void setGroup_order_no(String groupOrderNo) {
            this.groupOrderNo = groupOrderNo;
        }


        public List<ElementPlanItemsBean> getElement_plan_items() {
            return elementPlanItems;
        }

        public void setElement_plan_items(List<ElementPlanItemsBean> elementPlanItems) {
            this.elementPlanItems = elementPlanItems;
        }

        public static class ElementPlanItemsBean {
            /**
             * id : 82dd53ee3dbf4b70b6abacee00d3038a
             * multimedia_url : http://files.k8s.zcpk.work/slot1.parking/202103/17/1000-1100/20210317102032388-830849cbdf64480283c24303ad6c7b7d.png
             * biz_data_id : null
             * event_link : 事件链接
             * element_plan_id : 1bff2d090c1c48db8635aced00b1a42d
             * element_box_config_id : 95bf3c22252144d39823aced011c1e5b
             * texts : []
             * order_no : 1
             * content_type : 2
             * multimedia_content_type : 0
             * multimedia_count : 0
             * biz_data_source : 11
             * display_mode : 1
             * auto_close_second : null
             * is_open : false
             * group_code : FIRST_PAGE_CONSULTANT_ADS
             * "texts": [
             *                             {
             *                                 "name": "标题",
             *                                 "code": "title",
             *                                 "content": "舒适好房 公园天下"
             *                             },
             *                             {
             *                                 "name": "副标题",
             *                                 "code": "subTitle",
             *                                 "content": "左抱公园 右抱交通"
             *                             }
             *                         ]
             */
            private List<TextsBean> texts;
            public List<TextsBean> getTexts() {
                return texts;
            }

            public void setTexts(List<TextsBean> texts) {
                this.texts = texts;
            }

            public static class TextsBean {
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
            private String id;
            private String multimediaUrl;
            private String bizDataId;
            private String eventLink;
            private String elementPlanId;
            private String elementBoxConfigId;
            private int orderNo;
            private int contentType;
            private int multimediaContentType;
            private int multimediaCount;
            private int bizDataSource;
            private int displayMode;
            private String autoCloseSecond;
            private boolean isOpen;
            private String groupCode;

            private String startTimeTicks = "0";
            private String endTimeTicks = "0";

            public String getStartTimeTicks() {
                return startTimeTicks;
            }

            public void setStartTimeTicks(String startTimeTicks) {
                this.startTimeTicks = startTimeTicks;
            }

            public String getEndTimeTicks() {
                return endTimeTicks;
            }

            public void setEndTimeTicks(String endTimeTicks) {
                this.endTimeTicks = endTimeTicks;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMultimedia_url() {
                return multimediaUrl;
            }

            public void setMultimedia_url(String multimediaUrl) {
                this.multimediaUrl = multimediaUrl;
            }

            public String getBiz_data_id() {
                return bizDataId;
            }

            public void setBiz_data_id(String bizDataId) {
                this.bizDataId = bizDataId;
            }

            public String getEvent_link() {
                return eventLink;
            }

            public void setEvent_link(String eventLink) {
                this.eventLink = eventLink;
            }

            public String getElement_plan_id() {
                return elementPlanId;
            }

            public void setElement_plan_id(String elementPlanId) {
                this.elementPlanId = elementPlanId;
            }

            public String getElement_box_config_id() {
                return elementBoxConfigId;
            }

            public void setElement_box_config_id(String elementBoxConfigId) {
                this.elementBoxConfigId = elementBoxConfigId;
            }

            public int getOrder_no() {
                return orderNo;
            }

            public void setOrder_no(int orderNo) {
                this.orderNo = orderNo;
            }

            public int getContent_type() {
                return contentType;
            }

            public void setContent_type(int contentType) {
                this.contentType = contentType;
            }

            public int getMultimedia_content_type() {
                return multimediaContentType;
            }

            public void setMultimedia_content_type(int multimediaContentType) {
                this.multimediaContentType = multimediaContentType;
            }

            public int getMultimedia_count() {
                return multimediaCount;
            }

            public void setMultimedia_count(int multimediaCount) {
                this.multimediaCount = multimediaCount;
            }

            public int getBiz_data_source() {
                return bizDataSource;
            }

            public void setBiz_data_source(int bizDataSource) {
                this.bizDataSource = bizDataSource;
            }

            public int getDisplay_mode() {
                return displayMode;
            }

            public void setDisplay_mode(int displayMode) {
                this.displayMode = displayMode;
            }

            public Object getAuto_close_second() {
                return autoCloseSecond;
            }

            public void setAuto_close_second(String autoCloseSecond) {
                this.autoCloseSecond = autoCloseSecond;
            }

            public boolean isIs_open() {
                return isOpen;
            }

            public void setIs_open(boolean isOpen) {
                this.isOpen = isOpen;
            }

            public String getGroup_code() {
                return groupCode;
            }

            public void setGroup_code(String groupCode) {
                this.groupCode = groupCode;
            }

            private String userId;
            private String triggerTime;
            private String deviceModel;
            private String deviceId;
            private String deviceNetwork;
            private String elementPlanItemId;


            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getTriggerTime() {
                return triggerTime;
            }

            public void setTriggerTime(String triggerTime) {
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

            public String getElementPlanItemId() {
                return elementPlanItemId;
            }

            public void setElementPlanItemId(String elementPlanItemId) {
                this.elementPlanItemId = elementPlanItemId;
            }



        }

        @Override
        public String toString() {
            return "ElementPlanItemGroupsBean{" +
                    "group_code='" + groupCode + '\'' +
                    ", group_title='" + groupTitle + '\'' +
                    ", group_order_no='" + groupOrderNo + '\'' +
                    ", element_plan_items=" + elementPlanItems +
                    ", event_url='" + getEvent_url() + '\'' +
                    ", biz_data_source_api_configs="  +
                    '}';
        }
    }

    public static class BizDataSourceApiConfigsBean {
        /**
         * biz_data_source : 11
         * api_url_tpl : 接口地址?参数名={ids}
         */

        private int bizDataSource;
        private String apiUrlTpl;

        public int getBiz_data_source() {
            return bizDataSource;
        }

        public void setBiz_data_source(int bizDataSource) {
            this.bizDataSource = bizDataSource;
        }

        public String getApi_url_tpl() {
            return apiUrlTpl;
        }

        public void setApi_url_tpl(String apiUrlTpl) {
            this.apiUrlTpl = apiUrlTpl;
        }


    }
}
