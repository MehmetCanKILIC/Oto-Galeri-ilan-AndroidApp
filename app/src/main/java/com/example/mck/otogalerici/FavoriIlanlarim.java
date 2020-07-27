package com.example.mck.otogalerici;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mck.otogalerici.Adapters.IlanlarAdapter;
import com.example.mck.otogalerici.Models.FavoriIlanSliderPojoSinifi;
import com.example.mck.otogalerici.Models.IlanlarPojoSinifi;
import com.example.mck.otogalerici.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriIlanlarim extends AppCompatActivity {
    ListView listView;
    List<IlanlarPojoSinifi> ılanlarPojoSinifiList;
    IlanlarAdapter ılanlarAdapter;
    SharedPreferences sharedPreferences;
    String uyeId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favori_ilanlarim);
        listView = (ListView) findViewById(R.id.favoriIlanlarListView);
        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        uyeId = sharedPreferences.getString("uye_id", null);
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent ıntent = new Intent(FavoriIlanlarim.this, IlanDetay.class);
                ıntent.putExtra("ilanid", ılanlarPojoSinifiList.get(position).getIlanid());
                startActivity(ıntent);
            }
        });*/
    }

}
