package com.zcitc.advertisement.widget;

import static com.zcitc.advertisement.utils.UtilsToosKt.dip2px;
import static com.zcitc.advertisement.utils.UtilsToosKt.dp2px;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Build;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.zcitc.advertisement.R;

@SuppressLint("AppCompatCustomView")
public class RoundImageView extends ImageView {

    private float width;
    private float height;
    private float radius;
    private boolean isCircle = true;

    private float fillet = dip2px(getContext(),16);

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        if (null != attrs) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
            isCircle = array.getBoolean(R.styleable.RoundImageView_is_circle, true);
            fillet = array.getDimension(R.styleable.RoundImageView_corners,dp2px(context,4));
        }
    }

    public void setCircle(boolean is) {
        this.isCircle = is;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
        radius = Math.min(width, height) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isCircle) {
            Path path = new Path();
            path.addCircle(width / 2, height / 2, radius, Path.Direction.CW);
            canvas.clipPath(path);
        } else {
            if (width > fillet && height > fillet) {
                Path path = new Path();
                path.moveTo(fillet, 0);
                path.lineTo(width - fillet, 0);
                path.quadTo(width, 0, width, fillet);
                path.lineTo(width, height - fillet);
                path.quadTo(width, height, width - fillet, height);
                path.lineTo(fillet, height);
                path.quadTo(0, height, 0, height - fillet);
                path.lineTo(0, fillet);
                path.quadTo(0, 0, fillet, 0);
                canvas.clipPath(path);
            }
        }
        super.onDraw(canvas);
    }

}