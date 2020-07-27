package com.example.mck.otogalerici;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mck.otogalerici.Models.IlanSonucPojoSinifi;
import com.example.mck.otogalerici.Models.IlanVerPojo;
import com.example.mck.otogalerici.RestApi.ManagerAll;

import maes.tech.intentanim.CustomIntent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Yakit extends AppCompatActivity {

    EditText yakitTipiEditText, ortalamaYakitEditText, depoHacimEditText;
    Button yakitTuketimBilgisiButton, yakitTuketimBilgisiButtonGeri;
    SharedPreferences sharedPreferences;
    private View mProgressView;
    private View yakitFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yakit);
        tanimla();
    }

    //İlan resimleri Activity'e geçiş
    public void tanimla() {

        yakitFormView = findViewById(R.id.yakit_view);
        mProgressView = findViewById(R.id.login_progressYakit);
        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        IlanVerPojo.setUye_id(sharedPreferences.getString("uye_id", null));
        yakitTipiEditText = (EditText) findViewById(R.id.yakitTipiEditText);
        ortalamaYakitEditText = (EditText) findViewById(R.id.ortalamaYakitEditText);
        depoHacimEditText = (EditText) findViewById(R.id.depoHacimEditText);

        //Bilgi kaybı önlemek için IlanVerPojo sınıfında bilgi saklama işlemi
        yakitTipiEditText.setText(IlanVerPojo.getYakittipi());
        ortalamaYakitEditText.setText(IlanVerPojo.getOrtalamayakit());
        depoHacimEditText.setText(IlanVerPojo.getDepohacmi());


        yakitTuketimBilgisiButton = (Button) findViewById(R.id.yakitTuketimBilgisiButton);
        yakitTuketimBilgisiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!yakitTipiEditText.getText().toString().equals("") && !ortalamaYakitEditText.getText().toString().equals("") && !depoHacimEditText.getText().toString().equals("")) {
                    //geri gelme işlemlerinde bilgileri otomatik doldurma
                    showProgress(true);
                    IlanVerPojo.setYakittipi(yakitTipiEditText.getText().toString());
                    IlanVerPojo.setOrtalamayakit(ortalamaYakitEditText.getText().toString());
                    IlanVerPojo.setDepohacmi(depoHacimEditText.getText().toString());
                    ilanıayinla(IlanVerPojo.getUcret(), IlanVerPojo.getUye_id(), IlanVerPojo.getSehir(), IlanVerPojo.getIlce(), IlanVerPojo.getMahalle(), IlanVerPojo.getMarka(), IlanVerPojo.getSeri(), IlanVerPojo.getModel(), IlanVerPojo.getYil(), IlanVerPojo.getIlantipi(), IlanVerPojo.getKimden(), IlanVerPojo.getBaslik(), IlanVerPojo.getAciklama(), IlanVerPojo.getMotortipi(), IlanVerPojo.getMotorhacmi(), IlanVerPojo.getSurat(), IlanVerPojo.getYakittipi(), IlanVerPojo.getOrtalamayakit(), IlanVerPojo.getDepohacmi(), IlanVerPojo.getKm());

                } else {
                    Toast.makeText(getApplicationContext(), "Bütün bilgileri giriniz!", Toast.LENGTH_LONG).show();
                }

            }
        });
        //Motor ve Performans Activity'e geçiş
        yakitTuketimBilgisiButtonGeri = (Button) findViewById(R.id.yakitTuketimBilgisiButtonGeri);
        yakitTuketimBilgisiButtonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Yakit.this, MotorPerformans.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();


            }
        });
    }

    public void ilanıayinla(String ucret, String uye_id, String sehir, String ilce, String mahalle, String marka, String seri, String model, String yil, String ilantipi, String kimden, String baslik, String aciklama, String motortipi, String motorhacmi, String surat, String yakittipi, String ortalamayakit, String depohacmi, String km) {
        Call<IlanSonucPojoSinifi> reguest = ManagerAll.getInstance().ilanVer(uye_id, sehir, ilce, mahalle, marka, seri, model, yil, ilantipi, kimden, baslik, aciklama, motortipi, motorhacmi, surat, yakittipi, ortalamayakit, depohacmi, km, ucret);
        reguest.enqueue(new Callback<IlanSonucPojoSinifi>() {
            @Override
            public void onResponse(Call<IlanSonucPojoSinifi> call, Response<IlanSonucPojoSinifi> response) {
                if (response.body().isTf()) {
                    Toast.makeText(getApplicationContext(), "İlanınız yayınlanmıştır.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Yakit.this, IlanResimler.class);
                    intent.putExtra("ilan_id", response.body().getIlanid());
                    intent.putExtra("uye_id", response.body().getUyeid());
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    showProgress(true);
                } else {
                    Toast.makeText(getApplicationContext(), "İlanınız yayınlanmamıştır !", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<IlanSonucPojoSinifi> call, Throwable t) {

            }
        });
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            yakitFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            yakitFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    yakitFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            yakitFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
