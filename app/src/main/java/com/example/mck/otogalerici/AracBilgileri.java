package com.example.mck.otogalerici;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mck.otogalerici.Models.IlanVerPojo;

import maes.tech.intentanim.CustomIntent;

public class AracBilgileri extends AppCompatActivity {

    EditText markaBilgiEditText, seriBilgiEditText, yilBilgiEditText, kmBilgiEditText, modelBilgiEditText;
    Button aracBilgisiButon, aracBilgisiButonGeri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arac_bilgileri);
        tanimla();
    }

    //Motor ve Performans Activity'e geçiş
    public void tanimla() {
        markaBilgiEditText = (EditText) findViewById(R.id.markaBilgiEditText);
        seriBilgiEditText = (EditText) findViewById(R.id.seriBilgiEditText);
        yilBilgiEditText = (EditText) findViewById(R.id.yilBilgiEditText);
        kmBilgiEditText = (EditText) findViewById(R.id.kmBilgiEditText);
        modelBilgiEditText = (EditText) findViewById(R.id.modelBilgiEditText);

        //Bilgi kaybı önlemek için IlanVerPojo sınıfında bilgi saklama işlemi
        markaBilgiEditText.setText(IlanVerPojo.getMarka());
        seriBilgiEditText.setText(IlanVerPojo.getSeri());
        yilBilgiEditText.setText(IlanVerPojo.getYil());
        modelBilgiEditText.setText(IlanVerPojo.getModel());
        kmBilgiEditText.setText(IlanVerPojo.getKm());
        //km bilgisi eklenecek!!
        aracBilgisiButon = (Button) findViewById(R.id.aracBilgisiButon);
        aracBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!markaBilgiEditText.getText().toString().equals("") && !seriBilgiEditText.getText().toString().equals("") && !yilBilgiEditText.getText().toString().equals("") && !kmBilgiEditText.getText().toString().equals("") && !modelBilgiEditText.getText().toString().equals("")) {
                    //geri gelme işlemlerinde bilgileri otomatik doldurma
                    IlanVerPojo.setMarka(markaBilgiEditText.getText().toString());
                    IlanVerPojo.setSeri(seriBilgiEditText.getText().toString());
                    IlanVerPojo.setYil(yilBilgiEditText.getText().toString());
                    IlanVerPojo.setModel(modelBilgiEditText.getText().toString());
                    IlanVerPojo.setKm(kmBilgiEditText.getText().toString());
                    //km bilgisi eklenecek!!
                    Intent intent = new Intent(AracBilgileri.this, MotorPerformans.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Bütün bilgileri giriniz!", Toast.LENGTH_LONG).show();
                }

            }
        });
        //Adres bilgileri Activity'e geçiş
        aracBilgisiButonGeri = (Button) findViewById(R.id.aracBilgisiButonGeri);
        aracBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AracBilgileri.this, AdresBilgileri.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();

            }
        });
    }
}
