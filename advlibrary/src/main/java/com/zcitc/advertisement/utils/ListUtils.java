package com.zcitc.advertisement.utils;

import com.zcitc.advertisement.bean.ADPlanItemsData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListUtils {

    /**
     *
     * 随机排序重新整合数据
     */
    public static   List<ADPlanItemsData> createADPlanItemsDataList(List<ADPlanItemsData> list, int n) {
        Map<Integer, String> mmap = new HashMap<Integer, String>();
        List<ADPlanItemsData> listNew = new ArrayList<ADPlanItemsData>();
        while (mmap.size() < n) {
            int random = (int) (Math.random() * list.size());
            if (!mmap.containsKey(random)) {
                mmap.put(random, "");
                listNew.add(list.get(random));
            }
        }
        return listNew;

    }

}
