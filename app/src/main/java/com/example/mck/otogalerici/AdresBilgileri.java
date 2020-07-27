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

public class AdresBilgileri extends AppCompatActivity {
    Button adresBilgisiButon, adresBilgisiButonGeri;
    EditText sehirBilgiEditText, ilceBilgiEditText, mahalleBilgiEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adres_bilgileri);
        tanimla();
    }

    //Araç bilgileri Activity'e geçiş
    public void tanimla() {
        sehirBilgiEditText = (EditText) findViewById(R.id.sehirBilgiEditText);
        ilceBilgiEditText = (EditText) findViewById(R.id.ilceBilgiEditText);
        mahalleBilgiEditText = (EditText) findViewById(R.id.mahalleBilgiEditText);
        //Bilgi kaybı önlemek için IlanVerPojo sınıfında bilgi saklama işlemi
        sehirBilgiEditText.setText(IlanVerPojo.getSehir());
        ilceBilgiEditText.setText(IlanVerPojo.getIlce());
        mahalleBilgiEditText.setText(IlanVerPojo.getMahalle());
        adresBilgisiButon = (Button) findViewById(R.id.adresBilgisiButon);
        adresBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sehirBilgiEditText.getText().toString().equals("") && !ilceBilgiEditText.getText().toString().equals("") && !mahalleBilgiEditText.getText().toString().equals("")) {
                    //geri gelme işlemlerinde bilgileri otomatik doldurma
                    IlanVerPojo.setSehir(sehirBilgiEditText.getText().toString());
                    IlanVerPojo.setIlce(ilceBilgiEditText.getText().toString());
                    IlanVerPojo.setMahalle(mahalleBilgiEditText.getText().toString());
                    Intent intent = new Intent(AdresBilgileri.this, AracBilgileri.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Bütün bilgileri giriniz!", Toast.LENGTH_LONG).show();
                }

            }
        });
        //İlan türü Activity'e geçiş
        adresBilgisiButonGeri = (Button) findViewById(R.id.adresBilgisiButonGeri);
        adresBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdresBilgileri.this, IlanTuru.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();

            }
        });
    }
}

