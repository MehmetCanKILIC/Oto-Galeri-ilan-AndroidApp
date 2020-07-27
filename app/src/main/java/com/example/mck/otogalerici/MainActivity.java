package com.example.mck.otogalerici;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.mck.otogalerici.Adapters.FavoriSliderAdapter;
import com.example.mck.otogalerici.Models.FavoriIlanSliderPojoSinifi;
import com.example.mck.otogalerici.Models.FavoriIslemPojoSinifi;
import com.example.mck.otogalerici.RestApi.ManagerAll;

import java.util.List;

import maes.tech.intentanim.CustomIntent;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button ilanVerButton, ilanlarimMenuButton, ilanlarButton,iletisimBilgileriButton,kisiMesajlari;
    SharedPreferences sharedPreferences;
    String navHeaderText, uyeId;
    TextView navHeaderTextView;
    SharedPreferences.Editor editor;
    ViewPager mainActivitySliderFavori;
    CircleIndicator mainActivitysliderCircle;
    FavoriSliderAdapter favoriSliderAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        navHeaderText = sharedPreferences.getString("uye_KullaniciAdi", null);
        uyeId = sharedPreferences.getString("uye_id", null);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        navHeaderTextView = (TextView) headerView.findViewById(R.id.navHeaderText);
        navHeaderTextView.setText(navHeaderText);
        tanimla();
        getSlider();
    }

    //İlan verme işlemi sayfsına geçiş
    public void tanimla() {
        mainActivitySliderFavori = (ViewPager) findViewById(R.id.mainActivitySliderFavori);
        mainActivitysliderCircle = (CircleIndicator) findViewById(R.id.mainActivitysliderCircle);
        ilanVerButton = (Button) findViewById(R.id.ilanVerButton);
        ilanVerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IlanBilgileri.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        ilanlarimMenuButton = (Button) findViewById(R.id.ilanlarimMenuButton);
        ilanlarimMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(MainActivity.this, IlanlarimActivity.class);
                startActivity(ıntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });

        ilanlarButton = (Button) findViewById(R.id.ilanlarButton);
        ilanlarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(MainActivity.this, Ilanlar.class);
                startActivity(ıntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });

        iletisimBilgileriButton=(Button)findViewById(R.id.iletisimBilgileriButton);
        iletisimBilgileriButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent=new Intent(MainActivity.this,KisiselBilgiAvtivity.class);
                startActivity(ıntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        kisiMesajlari = (Button)findViewById(R.id.kisiMesajlari);
        kisiMesajlari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent=new Intent(MainActivity.this,MesajlarActivity.class);
                startActivity(ıntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            System.exit(0);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.ilanlar) {
            // IlanlarActivity'e geçiş
            Intent ıntent = new Intent(MainActivity.this, Ilanlar.class);
            startActivity(ıntent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            // Mesajlar'e geçiş
        } else if (id == R.id.mesajlar) {
            Intent ıntent = new Intent(MainActivity.this, MesajlarActivity.class);
            startActivity(ıntent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            // IlanlarımActivity'e geçiş
        } else if (id == R.id.ilanlarim) {
            Intent ıntent = new Intent(MainActivity.this, IlanlarimActivity.class);
            startActivity(ıntent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            // IlanVerActivity'e geçiş
        } else if (id == R.id.ilanver) {
            Intent ıntent = new Intent(MainActivity.this, IlanBilgileri.class);
            startActivity(ıntent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            // KişiselBilgilerActivity'e geçiş
        } else if (id == R.id.bilgi) {
            Intent ıntent = new Intent(MainActivity.this, KisiselBilgiAvtivity.class);
            startActivity(ıntent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            // Hesaptan Çıkış
        } else if (id == R.id.cikisYap) {

            editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent ıntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(ıntent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //Ana Menü FavoriIlanlarım Slider Gösterimi işlemi
    public void getSlider() {
        final Call<List<FavoriIlanSliderPojoSinifi>> request = ManagerAll.getInstance().setSlider(uyeId);
        request.enqueue(new Callback<List<FavoriIlanSliderPojoSinifi>>() {
            @Override
            public void onResponse(Call<List<FavoriIlanSliderPojoSinifi>> call, Response<List<FavoriIlanSliderPojoSinifi>> response) {
                if (response.body().get(0).isTf()) {
                    if (response.body().size() > 0) {
                        favoriSliderAdapter = new FavoriSliderAdapter(response.body(), getApplicationContext(), MainActivity.this);
                        mainActivitySliderFavori.setAdapter(favoriSliderAdapter);
                        mainActivitysliderCircle.setViewPager(mainActivitySliderFavori);
                        mainActivitysliderCircle.bringToFront();
                    }
                }
                else
                {
                    favoriSliderAdapter = new FavoriSliderAdapter(response.body(), getApplicationContext(), MainActivity.this);
                    mainActivitySliderFavori.setAdapter(favoriSliderAdapter);
                    mainActivitysliderCircle.setViewPager(mainActivitySliderFavori);
                    mainActivitysliderCircle.bringToFront();
                }
            }

            @Override
            public void onFailure(Call<List<FavoriIlanSliderPojoSinifi>> call, Throwable t) {

            }
        });

    }
    //Ana Menüyü restart işlemi
    @Override
    protected void onRestart() {
        super.onRestart();
        getSlider();
    }
}

