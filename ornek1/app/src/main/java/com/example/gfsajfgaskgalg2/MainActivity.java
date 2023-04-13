package com.example.gfsajfgaskgalg2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    public Button take_picture;
    public Button capture_video;
    public static int TAKE_PICTURE = 1;
    public static int CAPTURE_VIDEO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        take_picture = (Button) findViewById(R.id.bttn_takepicture);
        capture_video = (Button) findViewById(R.id.bttn_capturevideo);

        take_picture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Fotoğraf çekme olayını başlatmak için intent fonksiyonuna android in kendi bileşenini verdik
                Intent picture_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(picture_intent, TAKE_PICTURE); // intent ile birlikte request kodumuzu da gönderip activity i başlattık

            }
        });

        capture_video.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent video_intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(video_intent, CAPTURE_VIDEO);

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK)
            return;
        if(requestCode==2)
        {
            VideoView video = (VideoView)findViewById(R.id.video_view);
            video.setVideoURI(data.getData());
            video.setMediaController(new MediaController(getApplicationContext()));
            video.requestFocus();
            video.start();

        }
        else if(requestCode==1)
        {

            Bundle extras = data.getExtras();
            Bitmap bitmap_image  = (Bitmap)extras.get("data");
            ImageView img_view = (ImageView)findViewById(R.id.img_picture);
            img_view.setImageBitmap(bitmap_image);
        }

    }
}