package com.example.mck.otogalerici;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mck.otogalerici.Adapters.IlanlarimAdapter;
import com.example.mck.otogalerici.Models.IlanSilmePojoSinifi;
import com.example.mck.otogalerici.Models.IlanlarimPojoSinifi;
import com.example.mck.otogalerici.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IlanlarimActivity extends AppCompatActivity {

    ListView listView;
    IlanlarimAdapter ılanlarimAdapter;
    List<IlanlarimPojoSinifi> list;
    SharedPreferences sharedPreferences;
    String uye_id, ilanid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilanlarim);
        listView = findViewById(R.id.ilanlarimListView);
        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        uye_id = sharedPreferences.getString("uye_id", null);
        ilanlarimiGoruntule();


        //Herhangi bir ilana basıldığında ilanın id değerini ilanlarımAlertDialog fonksiyonuna gönderme işlemi
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ilanlarimAlertDialog(IlanlarimActivity.this, list.get(position).getIlanid().toString());

            }
        });

    }

    //kişisel ilanlarımı görüntüleme işlemi
    public void ilanlarimiGoruntule() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("İlanlarım");
        progressDialog.setMessage("İlanlarınız Yükleniyor. Lüften Bekleyin ... ");
        progressDialog.setCancelable(false);
        progressDialog.show();

        list = new ArrayList<>();
        Call<List<IlanlarimPojoSinifi>> request = ManagerAll.getInstance().ilanlarim(uye_id);
        request.enqueue(new Callback<List<IlanlarimPojoSinifi>>() {
            @Override
            public void onResponse(Call<List<IlanlarimPojoSinifi>> call, Response<List<IlanlarimPojoSinifi>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    if (response.body().get(0).isTf()) {
                        Log.i("testttt", "" + response.body().get(0).isTf());
                        ılanlarimAdapter = new IlanlarimAdapter(list, getApplicationContext(), IlanlarimActivity.this);
                        listView.setAdapter(ılanlarimAdapter);
                        progressDialog.cancel();
                    } else {
                        Log.i("testttt", "" + response.body().get(0).isTf());
                        Toast.makeText(getApplicationContext(), "Hiç ilanınız bulunmamaktadır.", Toast.LENGTH_LONG).show();
                        progressDialog.cancel();
                        Intent ıntent = new Intent(IlanlarimActivity.this, MainActivity.class);
                        startActivity(ıntent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<IlanlarimPojoSinifi>> call, Throwable t) {

            }
        });
    }

    //İlan silme yada iptal etme dialogu çalıştırma işlemi
    public void ilanlarimAlertDialog(Activity activity, final String ilan_id) {
        LayoutInflater ınflater = activity.getLayoutInflater();
        View view = ınflater.inflate(R.layout.alertlayout, null);


        Button button = (Button) view.findViewById(R.id.buton);
        Button buttoncik = (Button) view.findViewById(R.id.buton2);

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setView(view);
        alert.setCancelable(false);
        final AlertDialog dialog = alert.create();
        //iptal butonu
        buttoncik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        //sil butonuna basıldığında ilan id değerini sil fonksiyonuna gönderme işlemi
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sil(ilan_id);
                dialog.cancel();
            }
        });

        dialog.show();

    }

    //ilan silme fonksiyonu
    public void sil(String ilanId) {
        Call<IlanSilmePojoSinifi> request = ManagerAll.getInstance().ilanlarimSil(ilanId);
        request.enqueue(new Callback<IlanSilmePojoSinifi>() {
            @Override
            public void onResponse(Call<IlanSilmePojoSinifi> call, Response<IlanSilmePojoSinifi> response) {
                if (response.body().isTf()) {
                    ilanlarimiGoruntule();
                }
            }

            @Override
            public void onFailure(Call<IlanSilmePojoSinifi> call, Throwable t) {

            }
        });
    }


}
