package com.zcitc.advertisement.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zcitc.advertisement.R;
import com.zcitc.advertisement.bean.ADPlanItemsData;
import com.zcitc.glidelibrary.GlideUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @author liujie
 */
public class PlateRecommendAdapter extends BaseQuickAdapter<ADPlanItemsData, BaseViewHolder> {
    List<ADPlanItemsData> informationDataList = new ArrayList<>();

    public PlateRecommendAdapter(List<ADPlanItemsData> informationDataList) {
        super(R.layout.item_recommend_plate, informationDataList);
        this.informationDataList = informationDataList;
    }


    @Override
    protected void convert(BaseViewHolder helper, ADPlanItemsData item) {

        String[] tagsList = item.getMultimediaUrlList();

        helper.setGone(R.id.imgIv1, true);

        helper.setGone(R.id.imgIv2, true);

        helper.setGone(R.id.viewwidth, true);
        if(tagsList!=null){
            if (tagsList.length == 1) {
                helper.setGone(R.id.imgIv1, false);
                helper.setGone(R.id.viewwidth, true);
                helper.setGone(R.id.imgIv2, true);
                GlideUtils.showImage(getContext(), tagsList[0], (ImageView) helper.getView(R.id.imgIv1),R.mipmap.ic_default);
            }
            if (tagsList.length >= 2) {
                helper.setGone(R.id.imgIv1, false);
                helper.setGone(R.id.imgIv2, false);
                helper.setGone(R.id.viewwidth, false);
                GlideUtils.showImage(getContext(), tagsList[0], (ImageView) helper.getView(R.id.imgIv1),R.mipmap.ic_default);
                GlideUtils.showImage(getContext(), tagsList[1], (ImageView) helper.getView(R.id.imgIv2),R.mipmap.ic_default);

            }
        }

        for (int i=0;i< item.getTexts().size();i++) {
            if (item.getTexts().get(i).getCode().equals("title")) {
                helper.setText(R.id.titleTv, item.getTexts().get(i).getContent());
            }
            if (item.getTexts().get(i).getCode().equals("subTitle")) {
                helper.setText(R.id.contentTv, item.getTexts().get(i).getContent());
            }

        }

    }


}

