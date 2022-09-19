package com.zcitc.scan.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;


public final class ViewfinderView extends View {
    private final Paint paint;
    private final Paint paintLine;
    public Rect frame;
    public int width, height, top, left, Wlength, Hlength;
    private boolean isQRCode = true;

    public ViewfinderView(Context context, int width, int height, boolean isQRCode) {
        super(context);
        paint = new Paint();
        paintLine = new Paint();
        this.width = width;
        this.height = height;
        this.isQRCode = isQRCode;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (isQRCode){
            left = width/6;
            top = height/4;
            Wlength = width -(width/3);
            Hlength = width -(width/3);
        }else{
            left = width/10;
            top = height/3;
            Wlength =  width -(width/5);
            Hlength =  height/6;
        }


        frame = new Rect(left, top, left + Wlength, top + Hlength);
        paint.setColor(Color.argb(100, 0, 0, 0));
        canvas.drawRect(0, 0, width, frame.top, paint);
        canvas.drawRect(0, frame.top, frame.left, frame.bottom, paint);
        canvas.drawRect(frame.right, frame.top, width, frame.bottom,
                paint);
        canvas.drawRect(0, frame.bottom, width, height, paint);
        paintLine.setColor(Color.rgb(43, 171, 172));
        paintLine.setStrokeWidth(5);
        paintLine.setAntiAlias(true);
//			canvas.drawLine(frame.left, frame.top, frame.left +50, frame.top, paintLine);
//			canvas.drawLine(frame.left, frame.top, frame.left, frame.top + 50, paintLine);
//			canvas.drawLine(frame.right, frame.top, frame.right - 50, frame.top, paintLine);
//			canvas.drawLine(frame.right, frame.top, frame.right, frame.top + 50, paintLine);
//			canvas.drawLine(frame.left, frame.bottom, frame.left + 50, frame.bottom, paintLine);
//			canvas.drawLine(frame.left, frame.bottom, frame.left, frame.bottom - 50, paintLine);
//			canvas.drawLine(frame.right, frame.bottom, frame.right - 50, frame.bottom, paintLine);
//			canvas.drawLine(frame.right, frame.bottom, frame.right,frame.bottom - 50, paintLine);
        canvas.drawLine(frame.left, frame.top, frame.right, frame.top, paintLine);
        canvas.drawLine(frame.left, frame.top - 2, frame.left, frame.bottom, paintLine);
        canvas.drawLine(frame.left - 2, frame.bottom, frame.right, frame.bottom, paintLine);
        canvas.drawLine(frame.right, frame.top - 2, frame.right, frame.bottom + 2, paintLine);





        if (frame == null) {
            return;
        }

    }

}
