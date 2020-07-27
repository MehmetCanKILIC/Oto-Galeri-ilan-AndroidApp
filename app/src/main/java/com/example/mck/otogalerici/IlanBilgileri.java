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

public class IlanBilgileri extends AppCompatActivity {
    Button ilanBilgisiButon, ilanBilgisiButonGeri;
    EditText ilanAciklamaEditText, ilanFiyatEditText, ilanBaslikEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_bilgileri);
        tanimla();
    }

    //İlan türü Activity'e geçiş
    public void tanimla() {
        ilanBaslikEditText = (EditText) findViewById(R.id.ilanBaslikEditText);
        ilanAciklamaEditText = (EditText) findViewById(R.id.ilanAciklamaEditText);
        ilanFiyatEditText = (EditText) findViewById(R.id.ilanFiyatEditText);

        ilanBilgisiButon = (Button) findViewById(R.id.ilanBilgisiButon);
        //Bilgi kaybı önlemek için IlanVerPojo sınıfında bilgi saklama işlemi
        ilanBaslikEditText.setText(IlanVerPojo.getBaslik());
        ilanAciklamaEditText.setText(IlanVerPojo.getAciklama());
        ilanFiyatEditText.setText(IlanVerPojo.getUcret());
        ilanBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ilanBaslikEditText.getText().toString().equals("") && !ilanAciklamaEditText.getText().toString().equals("") && !ilanFiyatEditText.getText().toString().equals("")) {
                    //geri gelme işlemlerinde bilgileri otomatik doldurma
                    IlanVerPojo.setAciklama(ilanAciklamaEditText.getText().toString());
                    IlanVerPojo.setBaslik(ilanBaslikEditText.getText().toString());
                    IlanVerPojo.setUcret(ilanFiyatEditText.getText().toString());
                    Intent intent = new Intent(IlanBilgileri.this, IlanTuru.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Bütün bilgileri giriniz!", Toast.LENGTH_LONG).show();
                }
            }
        });

        //MainActivity'e geçiş
        ilanBilgisiButonGeri = (Button) findViewById(R.id.ilanBilgisiButonGeri);
        ilanBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IlanBilgileri.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();

            }
        });
    }
}
