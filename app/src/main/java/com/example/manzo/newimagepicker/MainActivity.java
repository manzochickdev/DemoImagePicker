package com.example.manzo.newimagepicker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.iv);
        findViewById(R.id.btnChoose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImagePickerHandler imagePickerHandler = ImagePickerHandler.getImagePickerDialog(MainActivity.this);
                imagePickerHandler.showDialog(new OnDataListener() {
                    @Override
                    public void onSuccess(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFail(String message) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }
}
