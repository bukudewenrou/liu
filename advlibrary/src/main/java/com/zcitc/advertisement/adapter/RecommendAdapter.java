package com.zcitc.advertisement.adapter;


import static com.zcitc.advertisement.utils.UtilsToosKt.takePhone;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zcitc.advertisement.R;
import com.zcitc.advertisement.bean.ADPlanItemsData;
import com.zcitc.advertisement.dialog.AlertDialog;
import com.zcitc.glidelibrary.GlideUtils;


import java.util.ArrayList;
import java.util.List;

public class RecommendAdapter extends BaseQuickAdapter<ADPlanItemsData, BaseViewHolder> {
    List<ADPlanItemsData> informationDataList = new ArrayList<>();
    int type = 0;
    Activity activity ;

    public RecommendAdapter(Activity activity, List<ADPlanItemsData> informationDataList, int layoutId, int type) {
        super(layoutId, informationDataList);
        this.informationDataList = informationDataList;
        this.type = type;
        this.activity = activity;
    }


    @Override
    protected void convert(BaseViewHolder helper, ADPlanItemsData item) {
        if (item == null) {
            return;
        }
        ImageView imgIv = (ImageView) helper.getView(R.id.imgIv);

        if (type == 1) {
            if (item.getBizData() == null) {
                return;
            }
            helper.setGone(R.id.titleTv,false);
            helper.setText(R.id.titleTv, item.getBizData().getEnterpriseShortName()).setText(R.id.nameTv, item.getBizData().getShortName()).setText(R.id.contentTv, item.getBizData().getDistrictName());
//            GlideUtils.showmdImage(getContext(), item.getBizData().getImageUrl(), imgIv);
            GlideUtils.showmdImage(getContext(), item.getImgUrl(), imgIv);

        } else if (type == 21) {
            if (item.getBizData() == null) {
                return;
            }
            helper.setGone(R.id.advisoryTv,false);
            helper.setText(R.id.nameTv, item.getBizData().getRealName()).setText(R.id.contentTv, item.getBizData().getEnterpriseShortName());
//            //优秀中介
//            GlideUtils.showUserImage(getContext(), item.getBizData().getAvatarUrl(), imgIv);
            GlideUtils.showUserImage(getContext(), item.getImgUrl(), imgIv);
            helper.getView(R.id.advisoryTv).setOnClickListener(v -> showDialog(item.getBizData().getTel()));
        } else if (type == 11) {
            if (item.getBizData() == null) {
                return;
            }

            helper.setGone(R.id.lineView,true);
            helper.setGone(R.id.contentTv,true);
            helper.setGone(R.id.advisoryTv,false);
            helper.setText(R.id.nameTv, item.getBizData().getRealName());


//            //优秀顾问

//            GlideUtils.showUserImage(getContext(), item.getBizData().getAvatarUrl(), imgIv);
            GlideUtils.showUserImage(getContext(), item.getImgUrl(), imgIv);
            helper.getView(R.id.advisoryTv).setOnClickListener(v -> {
                if (!item.getBizData().getTel().isEmpty()) {
                    showDialog(item.getBizData().getTel());
                }

            });
        } else if (type == 31) {
            GlideUtils.showmdImage(getContext(), item.getImgUrl(), imgIv);
            helper.setText(R.id.nameTv, item.getName()).setText(R.id.contentTv, item.getContent());
        }

    }

    private void showDialog(String phone) {

        new AlertDialog(getContext()).builder().setTitle("提示").setMsg("是否拨打电话" + phone).setNegativeButton("呼叫", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhone(phone,activity);
            }
        }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();


    }
}

