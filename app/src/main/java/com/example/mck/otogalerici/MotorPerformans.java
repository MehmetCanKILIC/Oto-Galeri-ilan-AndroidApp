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

public class MotorPerformans extends AppCompatActivity {

    EditText motorTipiEditText, motorHacmiEditText, azamiSüratEditText;
    Button motorBilgisiButton, motorBilgisiButtonGeri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_performans);
        tanimla();
    }

    //yakit Activity'e geçiş
    public void tanimla() {
        motorTipiEditText = (EditText) findViewById(R.id.motorTipiEditText);
        motorHacmiEditText = (EditText) findViewById(R.id.motorHacmiEditText);
        azamiSüratEditText = (EditText) findViewById(R.id.azamiSüratEditText);

        //Bilgi kaybı önlemek için IlanVerPojo sınıfında bilgi saklama işlemi
        motorTipiEditText.setText(IlanVerPojo.getMotortipi());
        motorHacmiEditText.setText(IlanVerPojo.getMotorhacmi());
        azamiSüratEditText.setText(IlanVerPojo.getSurat());

        motorBilgisiButton = (Button) findViewById(R.id.motorBilgisiButton);
        motorBilgisiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!motorTipiEditText.getText().toString().equals("") && !motorHacmiEditText.getText().toString().equals("") && !azamiSüratEditText.getText().toString().equals("")) {
                    //geri gelme işlemlerinde bilgileri otomatik doldurma
                    IlanVerPojo.setMotortipi(motorTipiEditText.getText().toString());
                    IlanVerPojo.setMotorhacmi(motorHacmiEditText.getText().toString());
                    IlanVerPojo.setSurat(azamiSüratEditText.getText().toString());
                    Intent intent = new Intent(MotorPerformans.this, Yakit.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Bütün bilgileri giriniz!", Toast.LENGTH_LONG).show();
                }
            }
        });
        //Araç bilgileri Activity'e geçiş
        motorBilgisiButtonGeri = (Button) findViewById(R.id.motorBilgisiButtonGeri);
        motorBilgisiButtonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MotorPerformans.this, AracBilgileri.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();

            }
        });
    }
}

