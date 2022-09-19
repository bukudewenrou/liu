package com.zcitc.advertisement.adv;

import static com.zcitc.advertisement.utils.UtilsToosKt.removeJSON;
import static com.zcitc.advertisement.utils.UtilsToosKt.saveJSON;

import android.content.Context;

import com.google.gson.Gson;
import com.zcitc.advertisement.bean.ADItemGroupsData;

import java.util.List;


/**
 * @author liuj
 */
public class ADItemGroupsUtils {

  
    /**
     * 首页金融机构广告
     */
    public final static String FIRST_PAGE_FINANCIAL_INSTITUTION_ADS = "FIRST_PAGE_FINANCIAL_INSTITUTION_ADS";
    /**
     * 首页中介广告
     */
    public final static String FIRST_PAGE_AGENT_ADS = "FIRST_PAGE_AGENT_ADS";
    /**
     * 首页中介机构广告
     */
    public final static String FIRST_PAGE_AGENCY_ADS = "FIRST_PAGE_AGENCY_ADS";
    /**
     * 首页顾问广告
     */
    public final static String FIRST_PAGE_CONSULTANT_ADS = "FIRST_PAGE_CONSULTANT_ADS";
    /**
     * 首页搜索广告
     */
    public final static String FIRST_PAGE_SEARCH_ADS = "FIRST_PAGE_SEARCH_ADS";
    /**
     * 启动页广告
     */
    public final static String FIRST_PAGE_START_ADS = "FIRST_PAGE_START_ADS";
    /**
     * 弹窗广告
     */
    public final static String FIRST_PAGE_POP_ADS = "FIRST_PAGE_POP_ADS";
    /**
     * 首页中间banner广告
     */
    public final static String FIRST_PAGE_BANNER_ADS = "FIRST_PAGE_BANNER_ADS";
    /**
     * 首页banner广告
     */
    public final static String FIRST_PAGE_TOP_CAROUSEL_ADS = "FIRST_PAGE_TOP_CAROUSEL_ADS";
    /**
     * 热门信息区
     */
    public final static String FIRST_PAGE_BLACK_ADS = "FIRST_PAGE_BLACK_ADS";

    /**
     * 保存广告数据
     * @param mContext
     * @param itemGroups
     */
    public final static void saveADItemGroup(Context mContext, List<ADItemGroupsData> itemGroups) {
        removeADItemGroup(mContext);
         for(ADItemGroupsData itemGroup :itemGroups){

             for(int i=0;i<itemGroup.getAdvItems().size();i++){
                 itemGroup.getAdvItems().get(i).setAdvPositionId(itemGroup.getId());
             }
             switch(itemGroup.getCode()) {
                 case FIRST_PAGE_FINANCIAL_INSTITUTION_ADS:
                     saveJSON(mContext, FIRST_PAGE_FINANCIAL_INSTITUTION_ADS, new Gson().toJson(itemGroup));
                     break;
                 case FIRST_PAGE_AGENT_ADS:
                     saveJSON(mContext, FIRST_PAGE_AGENT_ADS , new Gson().toJson(itemGroup));
                     break;
                 case FIRST_PAGE_AGENCY_ADS:
                     saveJSON(mContext, FIRST_PAGE_AGENCY_ADS , new Gson().toJson(itemGroup));
                     break;
                 case FIRST_PAGE_CONSULTANT_ADS:
                     saveJSON(mContext, FIRST_PAGE_CONSULTANT_ADS, new Gson().toJson(itemGroup));
                     break;
                 case FIRST_PAGE_SEARCH_ADS:
                     saveJSON(mContext, FIRST_PAGE_SEARCH_ADS , new Gson().toJson(itemGroup));
                     break;
                 case FIRST_PAGE_START_ADS:
                     saveJSON(mContext, FIRST_PAGE_START_ADS , new Gson().toJson(itemGroup));
                     break;
                 case FIRST_PAGE_POP_ADS:
                     saveJSON(mContext, FIRST_PAGE_POP_ADS , new Gson().toJson(itemGroup));
                     break;
                 case FIRST_PAGE_BANNER_ADS:
                     saveJSON(mContext, FIRST_PAGE_BANNER_ADS , new Gson().toJson(itemGroup));
                     break;
                 case FIRST_PAGE_TOP_CAROUSEL_ADS:
                     saveJSON(mContext, FIRST_PAGE_TOP_CAROUSEL_ADS , new Gson().toJson(itemGroup));
                     break;
                 case FIRST_PAGE_BLACK_ADS:
                     saveJSON(mContext, FIRST_PAGE_BLACK_ADS , new Gson().toJson(itemGroup));
                     break;

             }

         }


    }

    /**
     *
     * 首页顾问广告=FIRST_PAGE_CONSULTANT_ADS,
     *      * 首页中介机构广告=FIRST_PAGE_AGENCY_ADS,
     *      * 首页中介广告=FIRST_PAGE_AGENT_ADS,
     *      * 首页金融机构广告=FIRST_PAGE_FINANCIAL_INSTITUTION_ADS
     */
    public static void removeADItemGroup(Context mContext) {
        removeJSON(mContext,FIRST_PAGE_CONSULTANT_ADS);
        removeJSON(mContext,FIRST_PAGE_AGENCY_ADS);
        removeJSON(mContext,FIRST_PAGE_AGENT_ADS);
        removeJSON(mContext,FIRST_PAGE_FINANCIAL_INSTITUTION_ADS);

        removeJSON(mContext,FIRST_PAGE_START_ADS);
        removeJSON(mContext,FIRST_PAGE_POP_ADS);
        removeJSON(mContext,FIRST_PAGE_BANNER_ADS);
        removeJSON(mContext,FIRST_PAGE_TOP_CAROUSEL_ADS);
        removeJSON(mContext,FIRST_PAGE_BLACK_ADS);
        removeJSON(mContext,FIRST_PAGE_SEARCH_ADS);



    }
}
