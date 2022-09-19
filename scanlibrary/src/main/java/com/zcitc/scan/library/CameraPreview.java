/*
 * Copyright © Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zcitc.scan.library;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * <p>QRCode Camera preview, include QRCode recognition.</p>
 * Created by Yan Zhenjie on 2017/5/10.
 */
public class CameraPreview extends FrameLayout implements SurfaceHolder.Callback {

    private CameraManager mCameraManager;
    private CameraScanAnalysis mPreviewCallback;
    private SurfaceView mSurfaceView;

    public CameraPreview(Context context) {
        this(context, null);
    }

    public CameraPreview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CameraPreview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mCameraManager = new CameraManager(context);
        mPreviewCallback = new CameraScanAnalysis();

    }

    /**
     * Set Scan results callback.
     *
     * @param callback {@link ScanCallback}.
     */
    public void setScanCallback(ScanCallback callback) {
        mPreviewCallback.setScanCallback(callback);
    }

    public void reStart() {
        mPreviewCallback.onStart();
    }

    /**
     * Camera start preview.
     */
    public boolean start() {

        try {
            mCameraManager.openDriver();
        } catch (Exception e) {
            return false;
        }
        mPreviewCallback.onStart();
        if (mSurfaceView == null) {
            mSurfaceView = new SurfaceView(getContext());
            addView(mSurfaceView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            SurfaceHolder holder = mSurfaceView.getHolder();
            holder.addCallback(this);
            holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        startCameraPreview(mSurfaceView.getHolder());
        return true;
    }

    private boolean isQRCODE;

    public void setQRCode(boolean isQRCode) {
        this.isQRCODE = isQRCode;
    }

    private boolean mask = true;

    public void setMask(boolean mask) {
        this.mask = mask;
    }

    /**
     * Camera stop preview.
     */
    public void stop() {
        removeCallbacks(mAutoFocusTask);
        mPreviewCallback.onStop();
        mCameraManager.stopPreview();
        mCameraManager.closeDriver();
    }


    private void startCameraPreview(SurfaceHolder holder) {
        try {
            mCameraManager.startPreview(holder, mPreviewCallback);
            mCameraManager.autoFocus(mFocusCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        startCameraPreview(holder);
    }

    private ViewfinderView mViewfinderView;

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (holder.getSurface() == null) {
            return;
        }
        if (mask) {
            if (mViewfinderView == null) {
                mViewfinderView = new ViewfinderView(getContext(), width, height, isQRCODE);
                addView(mViewfinderView);
            }
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mCameraManager.stopPreview();
    }

    private Camera.AutoFocusCallback mFocusCallback = new Camera.AutoFocusCallback() {
        @Override
        public void onAutoFocus(boolean success, Camera camera) {
            if (success) {
                postDelayed(mAutoFocusTask, 800);
            }
        }
    };

    private Runnable mAutoFocusTask = new Runnable() {
        @Override
        public void run() {
            mCameraManager.autoFocus(mFocusCallback);
        }
    };

    @Override
    protected void onDetachedFromWindow() {
        stop();
        super.onDetachedFromWindow();
    }

    public void takePhoto(Camera.PictureCallback callback) {
        mCameraManager.takePhoto(callback);
    }

    public int getRotate() {
        return mCameraManager.getConfiguration().getDisplayOrientation();
    }

    public Point getPictureSize() {
        return mCameraManager.getConfiguration().getPictureResolution();
    }
}