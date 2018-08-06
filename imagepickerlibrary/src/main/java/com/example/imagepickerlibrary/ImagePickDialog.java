package com.example.imagepickerlibrary;

import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;

public class ImagePickDialog extends DialogFragment {

    OnDataListener onDataListener;

    void setOnDataListener(OnDataListener onDataListener){
        this.onDataListener = onDataListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Dialog_Alert);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_pick_dialog_layout,container,false);

        view.findViewById(R.id.btnCamera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,10000);


            }
        });

        view.findViewById(R.id.btnGallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(intent.createChooser(intent,"Select"),10001);


            }
        });

        view.findViewById(R.id.btnStorage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent.createChooser(intent,"Select"),10001);


            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            switch (requestCode){
                case 10000:
                    try{
                        Bundle extras = data.getExtras();
                        Bitmap bitma1p = (Bitmap) extras.get("data");
                        onDataListener.onSuccess(bitma1p);
                    }
                    catch (Exception e){
                        onDataListener.onFail(e.getMessage());
                    }
                    finally {
                        dismiss();
                    }
                    break;
                case 10001:
                    Uri uri = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),uri);
                        onDataListener.onSuccess(bitmap);

                    }
                    catch (IOException e) {
                        onDataListener.onFail(e.getMessage());
                    }
                    finally {
                        dismiss();
                    }
                    break;

            }
        }
    }
}
