package com.zcitc.advertisement.adv;

/**
 * author : LiuJie
 * date   : 2020/10/1213:02
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.zcitc.advertisement.R;
import com.zcitc.glidelibrary.GlideUtils;


/**
 * 广告弹窗
 */
public class AdvDialog extends Dialog {
    private Context context;
    private String urlImg;

    private View.OnClickListener mOnClickListener;
    public AdvDialog(@NonNull Context context, String urlImg, View.OnClickListener mOnClickListener) {
        super(context, R.style.UpdateDialogStyle);
        this.context = context;
        this.urlImg = urlImg;
        this.mOnClickListener = mOnClickListener;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_adv);
        setCancelable(false);
        initView();
        getWindow().setDimAmount(0.4f);//核心代码 解决了无法去除遮罩问题
    }

    ImageView mDialogAdvImg,mDialogAdvClose;
    private void initView() {
        mDialogAdvImg = (ImageView) findViewById(R.id.DialogAdvImg);
        mDialogAdvClose = (ImageView) findViewById(R.id.DialogAdvClose);
        GlideUtils.showImage(context, urlImg, mDialogAdvImg,R.drawable.home_image_placeholder2);
        mDialogAdvImg.setOnClickListener(mOnClickListener);
        mDialogAdvClose.setOnClickListener(arg0 -> AdvDialog.this.dismiss());



    }
}
