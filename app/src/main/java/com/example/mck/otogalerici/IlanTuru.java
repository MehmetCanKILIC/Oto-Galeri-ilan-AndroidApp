package com.example.mck.otogalerici;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.mck.otogalerici.Models.IlanVerPojo;

import java.util.ArrayList;
import java.util.List;

import maes.tech.intentanim.CustomIntent;

public class IlanTuru extends AppCompatActivity {
    Spinner ilanturuSpinner, kimdenSpinner;
    Button ilanTuruButton, ilanTuruButtonGeri;

    List<String> turList;
    List<String> sahipList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_turu);
        listedoldur();
        tanimla();
    }

    //Adres bilgileri Activity'e geçiş
    public void tanimla() {
        ilanturuSpinner = (Spinner) findViewById(R.id.ilanturuSpinner);
        kimdenSpinner = (Spinner) findViewById(R.id.kimdenSpinner);
        //spinner içeriği doldurma
        ArrayAdapter<String> turListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, turList);
        turListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ilanturuSpinner.setAdapter(turListAdapter);
        ArrayAdapter<String> sahipListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sahipList);
        sahipListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kimdenSpinner.setAdapter(sahipListAdapter);

        ilanTuruButton = (Button) findViewById(R.id.ilanTuruButton);
        ilanTuruButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //geri gelme işlemlerinde bilgileri otomatik doldurma
                IlanVerPojo.setIlantipi(ilanturuSpinner.getSelectedItem().toString());
                IlanVerPojo.setKimden(kimdenSpinner.getSelectedItem().toString());
                Intent intent = new Intent(IlanTuru.this, AdresBilgileri.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        });
        //İlan bilgileri Activity'e geçiş
        ilanTuruButtonGeri = (Button) findViewById(R.id.ilanTuruButtonGeri);
        ilanTuruButtonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IlanTuru.this, IlanBilgileri.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
            }
        });
    }

    //Spinner içerik ekleme işlemi
    public void listedoldur() {
        turList = new ArrayList<>();
        sahipList = new ArrayList<>();
        turList.add("Satılık");
        turList.add("Kiralık");
        sahipList.add("Sahibinden");
        sahipList.add("Galeriden");
    }
}

/*ilanturuSpinner
kimdenSpinner
ilanTuruButton
ilanTuruButtonGeri
 */
