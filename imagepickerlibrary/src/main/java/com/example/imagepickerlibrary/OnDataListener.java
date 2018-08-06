package com.example.imagepickerlibrary;

import android.graphics.Bitmap;

public interface OnDataListener {
    void onSuccess(Bitmap bitmap);
    void onFail(String message);
}
