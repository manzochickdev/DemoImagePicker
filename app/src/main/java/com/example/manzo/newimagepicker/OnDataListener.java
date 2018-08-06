package com.example.manzo.newimagepicker;

import android.graphics.Bitmap;

public interface OnDataListener {
    void onSuccess(Bitmap bitmap);
    void onFail(String message);
}
