package com.example.camrab;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_TAKE_PHOTO = 1;
    private static final int REQUEST_TAKE_VIDEO = 2;
    public int chose;
    private ImageView img;
    private Button btnIMG, btnMP4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        img = findViewById(R.id.img);
        btnIMG = findViewById(R.id.btnIMG);
        btnMP4 = findViewById(R.id.btnMP4);

        btnMP4.setOnClickListener(view -> {
            chose = 1;
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null)
            {
                startActivityForResult(intent, REQUEST_TAKE_VIDEO);
            }
        });

        btnIMG.setOnClickListener(view -> {
            chose = 0;
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_TAKE_PHOTO);

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if (chose ==0){
            super.onActivityResult(requestCode, resultCode, data);
            Bundle extras = data.getExtras();
            Bitmap thumbnailBitmap = (Bitmap) extras.get("data");
            img.setImageBitmap(thumbnailBitmap);
        }
    }
}
