package com.example.mck.otogalerici;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mck.otogalerici.Adapters.IlanlarAdapter;
import com.example.mck.otogalerici.Models.IlanlarPojoSinifi;
import com.example.mck.otogalerici.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Ilanlar extends AppCompatActivity {
    ListView listView;
    List<IlanlarPojoSinifi> ılanlarPojoSinifiList;
    IlanlarAdapter ılanlarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilanlar);
        listView = findViewById(R.id.ilanlarListView);
        //Herhangi bir ilana basıldığında ilanın id değerini getIlanDetay fonksiyonuna gönderme işlemi
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent ıntent = new Intent(Ilanlar.this, IlanDetay.class);
                ıntent.putExtra("ilanid", ılanlarPojoSinifiList.get(position).getIlanid());
                startActivity(ıntent);
            }
        });
        ilanlarimiGoruntule();

    }

    public void ilanlarimiGoruntule() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("İlanlar");
        progressDialog.setMessage("İlanlar Listeleniyor. Lüften Bekleyin ... ");
        progressDialog.setCancelable(false);//iptal edilebilirliği kapatıyor.
        progressDialog.show();

        Call<List<IlanlarPojoSinifi>> request = ManagerAll.getInstance().ilanlar();
        request.enqueue(new Callback<List<IlanlarPojoSinifi>>() {
            @Override
            public void onResponse(Call<List<IlanlarPojoSinifi>> call, Response<List<IlanlarPojoSinifi>> response) {
                if (response.isSuccessful()) {
                    if (response.body().get(0).isTf()) {
                        ılanlarPojoSinifiList = response.body();
                        ılanlarAdapter = new IlanlarAdapter(ılanlarPojoSinifiList, getApplicationContext());
                        listView.setAdapter(ılanlarAdapter);
                        progressDialog.cancel();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<IlanlarPojoSinifi>> call, Throwable t) {

            }
        });
    }
}
