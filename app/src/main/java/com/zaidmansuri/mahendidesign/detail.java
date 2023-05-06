package com.zaidmansuri.mahendidesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

public class detail extends AppCompatActivity {
    private PhotoView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        image=findViewById(R.id.full_image);
        Intent intent=getIntent();
        String url=intent.getStringExtra("url");
        Glide.with(image).load(url).into(image);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.share:
                BitmapDrawable drawable=(BitmapDrawable)image.getDrawable();
                Bitmap bitmap=drawable.getBitmap();
                String bitmapPath= MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,"image",null);
                Uri uri=Uri.parse(bitmapPath);
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("image/png");
                intent.putExtra(Intent.EXTRA_STREAM,uri);
                intent.putExtra(Intent.EXTRA_TEXT,"Playsotre Link: https://play.google.com/store/apps/details?id="+getPackageName());
                startActivity(Intent.createChooser(intent,"Share Via"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}