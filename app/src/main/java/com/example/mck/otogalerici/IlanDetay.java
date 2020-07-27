package com.example.mck.otogalerici;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mck.otogalerici.Adapters.SliderAdapter;
import com.example.mck.otogalerici.Models.FavoriIslemPojoSinifi;
import com.example.mck.otogalerici.Models.FavoriKontrolPojoSinifi;
import com.example.mck.otogalerici.Models.IlanDetayPojoSinifi;
import com.example.mck.otogalerici.Models.SliderPojoSinifi;
import com.example.mck.otogalerici.RestApi.ManagerAll;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IlanDetay extends AppCompatActivity {

    private TextView ilanDetayBaslik, ilanDetayFiyat, ilanDetayMarka, ilanDetayModel, ilanDetaySeri, ilanDetayYil, ilanDetayIlanTipi, ilanDetayKimden, ilanDetayMotorTipi, ilanDetayMotorHacmi, ilanDetaySurat, ilanDetayYakitTipi, ilanDetayOrtalamaYakit, ilanDetayDepoHacmi, ilanDetayKm;
    private Button ilanMesajGonder, ilanDetayFovoriButton;
    private ViewPager ilanDetaySlider;
    SharedPreferences sharedPreferences;
    String IlanId, UyeId , otherId;
    List<SliderPojoSinifi> list;
    SliderAdapter sliderAdapter;
    CircleIndicator circleIndicator;

    public String getIlanId() {
        return IlanId;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_detay);
        Bundle bundle = getIntent().getExtras();
        IlanId = bundle.getString("ilanid");
        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        UyeId = sharedPreferences.getString("uye_id", null);
        //mesajlarAdapter.ilanidal(IlanId);
        tanımla();
        getIlanDetay();
        getResim();
        getText();
        action();
    }


    //İlanDetay sayfası id'lerini tanımlama
    public void tanımla() {
        circleIndicator = (CircleIndicator) findViewById(R.id.sliderNokta);
        ilanDetayBaslik = (TextView) findViewById(R.id.ilanDetayBaslik);
        ilanDetayFiyat = (TextView) findViewById(R.id.ilanDetayFiyat);
        ilanDetayMarka = (TextView) findViewById(R.id.ilanDetayMarka);
        ilanDetayModel = (TextView) findViewById(R.id.ilanDetayModel);
        ilanDetaySeri = (TextView) findViewById(R.id.ilanDetaySeri);
        ilanDetayYil = (TextView) findViewById(R.id.ilanDetayYil);
        ilanDetayIlanTipi = (TextView) findViewById(R.id.ilanDetayIlanTipi);
        ilanDetayKimden = (TextView) findViewById(R.id.ilanDetayKimden);
        ilanDetayMotorTipi = (TextView) findViewById(R.id.ilanDetayMotorTipi);
        ilanDetayMotorHacmi = (TextView) findViewById(R.id.ilanDetayMotorHacmi);
        ilanDetaySurat = (TextView) findViewById(R.id.ilanDetaySurat);
        ilanDetayYakitTipi = (TextView) findViewById(R.id.ilanDetayYakitTipi);
        ilanDetayOrtalamaYakit = (TextView) findViewById(R.id.ilanDetayOrtalamaYakit);
        ilanDetayDepoHacmi = (TextView) findViewById(R.id.ilanDetayDepoHacmi);
        ilanDetayKm = (TextView) findViewById(R.id.ilanDetayKm);
        ilanMesajGonder = (Button) findViewById(R.id.ilanMesajGonder);
        ilanDetayFovoriButton = (Button) findViewById(R.id.ilanDetayFovoriButton);
        ilanDetaySlider = (ViewPager) findViewById(R.id.ilanDetaySlider);
    }


    //Web servisten gelen verileri TextView'lere doldurma
    public void getIlanDetay() {
        final Call<IlanDetayPojoSinifi> request = ManagerAll.getInstance().ilanDetay(IlanId);
        request.enqueue(new Callback<IlanDetayPojoSinifi>() {
            @Override
            public void onResponse(Call<IlanDetayPojoSinifi> call, Response<IlanDetayPojoSinifi> response) {

                ilanDetayBaslik.setText(response.body().getBaslik());
                ilanDetayFiyat.setText(response.body().getUcret());
                ilanDetayMarka.setText(response.body().getMarka());
                ilanDetayModel.setText(response.body().getModel());
                ilanDetaySeri.setText(response.body().getSeri());
                ilanDetayYil.setText(response.body().getYil());
                ilanDetayIlanTipi.setText(response.body().getIlantipi());
                ilanDetayKimden.setText(response.body().getKimden());
                ilanDetayMotorTipi.setText(response.body().getMotortipi());
                ilanDetayMotorHacmi.setText(response.body().getMotorhacmi());
                ilanDetaySurat.setText(response.body().getSurat());
                ilanDetayYakitTipi.setText(response.body().getYakittipi());
                ilanDetayOrtalamaYakit.setText(response.body().getOrtalamayakit());
                ilanDetayDepoHacmi.setText(response.body().getDepohacmi());
                ilanDetayKm.setText(response.body().getKm());
                otherId=response.body().getUyeid();
            }

            @Override
            public void onFailure(Call<IlanDetayPojoSinifi> call, Throwable t) {

            }
        });
    }

    public void getResim() {
        Call<List<SliderPojoSinifi>> request = ManagerAll.getInstance().ilanResimleri(IlanId);
        request.enqueue(new Callback<List<SliderPojoSinifi>>() {
            @Override
            public void onResponse(Call<List<SliderPojoSinifi>> call, Response<List<SliderPojoSinifi>> response) {
                list = response.body();
                sliderAdapter = new SliderAdapter(list, getApplicationContext());
                ilanDetaySlider.setAdapter(sliderAdapter);
                circleIndicator.setViewPager(ilanDetaySlider);
                circleIndicator.bringToFront();
            }

            @Override
            public void onFailure(Call<List<SliderPojoSinifi>> call, Throwable t) {

            }
        });
    }

    //favoriye al butonu favoriye alma fonksiyonu
    public void action() {
        ilanDetayFovoriButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<FavoriIslemPojoSinifi> request = ManagerAll.getInstance().favoriIslem(UyeId, IlanId);
                request.enqueue(new Callback<FavoriIslemPojoSinifi>() {
                    @Override
                    public void onResponse(Call<FavoriIslemPojoSinifi> call, Response<FavoriIslemPojoSinifi> response) {
                        if (response.body().isTf()) {
                            Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_LONG).show();
                            getText();

                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_LONG).show();
                            getText();

                        }
                    }

                    @Override
                    public void onFailure(Call<FavoriIslemPojoSinifi> call, Throwable t) {

                    }
                });
            }
        });

        ilanMesajGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent=new Intent(IlanDetay.this,ChatActivity.class);
                OtherId.setOtherId(otherId);
                startActivity(ıntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    //favoriye al butonu text değiştirme fonksiyonu
    public void getText() {
        Call<FavoriKontrolPojoSinifi> request = ManagerAll.getInstance().getButonText(UyeId, IlanId);
        request.enqueue(new Callback<FavoriKontrolPojoSinifi>() {
            @Override
            public void onResponse(Call<FavoriKontrolPojoSinifi> call, Response<FavoriKontrolPojoSinifi> response) {
                if (response.body().isTf()) {
                    ilanDetayFovoriButton.setText(response.body().getText());
                } else {
                    ilanDetayFovoriButton.setText(response.body().getText());
                }
            }

            @Override
            public void onFailure(Call<FavoriKontrolPojoSinifi> call, Throwable t) {

            }
        });
    }
}

