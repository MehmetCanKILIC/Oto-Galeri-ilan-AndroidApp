package com.example.mck.otogalerici;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mck.otogalerici.Models.ResimEklePojoSinifi;
import com.example.mck.otogalerici.RestApi.ManagerAll;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import maes.tech.intentanim.CustomIntent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IlanResimler extends AppCompatActivity {
    Button ilanYayinlaButtonGeri, resimSecButton, ilanYayinlaButton, resimEkleButton;
    ImageView secilenIlanResmiImageView;
    Bitmap bitmap;
    String uye_id, ilan_id, image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_resimler);
        tanimla();

        Bundle bundle = getIntent().getExtras();
        uye_id = bundle.getString("uye_id");
        ilan_id = bundle.getString("ilan_id");
    }

    //Yakit Activity'e geçiş
    public void tanimla() {
        resimSecButton = (Button) findViewById(R.id.resimSecButton);
        ilanYayinlaButton = (Button) findViewById(R.id.ilanYayinlaButton);
        resimEkleButton = (Button) findViewById(R.id.resimEkleButton);
        secilenIlanResmiImageView = (ImageView) findViewById(R.id.secilenIlanResmiImageView);
        ilanYayinlaButtonGeri = (Button) findViewById(R.id.ilanYayinlaButtonGeri);
        ilanYayinlaButtonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IlanResimler.this, Yakit.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
            }
        });

        resimSecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resimGoster();
            }
        });

        resimEkleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yukle();
            }
        });

    }

    public void yukle() {
        image = imageToString();
        Call<ResimEklePojoSinifi> request = ManagerAll.getInstance().resimEkle(uye_id, ilan_id, image);
        request.enqueue(new Callback<ResimEklePojoSinifi>() {
            @Override
            public void onResponse(Call<ResimEklePojoSinifi> call, Response<ResimEklePojoSinifi> response) {
                if (response.body().isTf()) {
                    Toast.makeText(getApplicationContext(),"Resim Yüklendi.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Resim Yüklenemedi!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResimEklePojoSinifi> call, Throwable t) {

            }
        });
    }


    //Galeriyi açma fonksiyonu

    public void resimGoster() {
        Intent ıntent = new Intent();
        ıntent.setType("image/*");
        ıntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(ıntent, 777);
    }

    //Resimi Bitmap'a dönüştürme

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 777 && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                secilenIlanResmiImageView.setImageBitmap(bitmap);
                secilenIlanResmiImageView.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    //resimi Base64 String'e çevirme

    public String imageToString() {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byt = byteArrayOutputStream.toByteArray();
        String imageToString = Base64.encodeToString(byt, Base64.DEFAULT);
        return imageToString;
    }
}
