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

import android.hardware.Camera;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.yanzhenjie.zbar.Config;
import com.yanzhenjie.zbar.Image;
import com.yanzhenjie.zbar.ImageScanner;
import com.yanzhenjie.zbar.Symbol;
import com.yanzhenjie.zbar.SymbolSet;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Yan Zhenjie on 2017/5/5.
 */
class CameraScanAnalysis implements Camera.PreviewCallback {

    private static final String TAG = "CameraScanAnalysis";
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    private ImageScanner mImageScanner;
    private Handler mHandler;
    private ScanCallback mCallback;

    private boolean allowAnalysis = true;
    private Image barcode;

    CameraScanAnalysis() {
        mImageScanner = new ImageScanner();
        mImageScanner.setConfig(0, Config.X_DENSITY, 3);
        mImageScanner.setConfig(0, Config.Y_DENSITY, 3);


        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (mCallback != null) {
                    mCallback.onScanResult((String) msg.obj);
                }
            }
        };
    }

    void setScanCallback(ScanCallback callback) {
        this.mCallback = callback;
    }

    void onStop() {
        this.allowAnalysis = false;
    }

    void onStart() {
        this.allowAnalysis = true;
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        if (allowAnalysis) {
            allowAnalysis = false;
            Camera.Size size = camera.getParameters().getPreviewSize();
            barcode = new Image(size.width, size.height, "Y800");
            barcode.setData(data);
            executorService.execute(mAnalysisTask);
        }
    }





    private Runnable mAnalysisTask = new Runnable() {
        @Override
        public void run() {
            int result = mImageScanner.scanImage(barcode);
            StringBuilder sb = new StringBuilder();
            String resultStr = null;
            if (result != 0) {
                SymbolSet symSet = mImageScanner.getResults();
                for (Symbol sym : symSet) {
                    switch (sym.getType()) {
                        case Symbol.CODABAR:

                            //条形码
                            sb.append(sym.getData() + "\n");
                            break;
                        case Symbol.CODE128:
                            //128编码格式二维码

                            sb.append(sym.getData() + "\n");
                            break;
                        case Symbol.QRCODE:
                            //QR码二维码

                            sb.append(sym.getData() + "\n");
                            break;
                        case Symbol.ISBN10:
                            //ISBN10图书查询

                            sb.append(sym.getData() + "\n");
                            break;
                        case Symbol.ISBN13:
                            //ISBN13图书查询

                            sb.append(sym.getData() + "\n");
                            break;
                        case Symbol.NONE:

                            sb.append(sym.getData() + "\n");
                            break;
                        default:

                            sb.append(sym.getData() + "\n");
                            break;
                    }
                    resultStr = sym.getData();
                }
//                for (Symbol sym : symSet)
//                    resultStr = sym.getData();
            }

            if (!TextUtils.isEmpty(resultStr)) {
                Message message = mHandler.obtainMessage();
                message.obj = resultStr;
                message.sendToTarget();
            } else {
                allowAnalysis = true;
            }
        }
    };
}