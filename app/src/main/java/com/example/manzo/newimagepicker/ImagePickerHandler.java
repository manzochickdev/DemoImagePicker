package com.example.manzo.newimagepicker;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

public class ImagePickerHandler {
    private static ImagePickerHandler imagePickerHandler;
    private Activity activity;
    private ImagePickerHandler(Activity activity){
        this.activity = activity;
    }

    public static ImagePickerHandler getImagePickerDialog(Activity activity){
        imagePickerHandler = new ImagePickerHandler(activity);
        return imagePickerHandler;
    }

    void showDialog(final OnDataListener onDataListener){
        ImagePickDialog imagePickDialog = new ImagePickDialog();
        imagePickDialog.show(activity.getFragmentManager(),"Image Pick Dialog");
        imagePickDialog.setOnDataListener(new OnDataListener() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                onDataListener.onSuccess(bitmap);
            }

            @Override
            public void onFail(String message) {
                onDataListener.onFail(message);

            }
        });
    }


}
