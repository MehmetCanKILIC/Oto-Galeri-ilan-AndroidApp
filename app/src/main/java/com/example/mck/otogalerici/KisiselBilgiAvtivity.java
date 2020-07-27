package com.example.mck.otogalerici;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mck.otogalerici.Models.GuncellePojoSinifi;
import com.example.mck.otogalerici.Models.UserPojoSinifi;
import com.example.mck.otogalerici.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KisiselBilgiAvtivity extends AppCompatActivity {
    EditText userbilgi_username,userbilgi_pass;
    Button userbilgi_button;
    SharedPreferences sharedPreferences;
    String uye_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kisisel_bilgi_avtivity);
        tanimla();
        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        uye_id = sharedPreferences.getString("uye_id", null);
        istekAt(uye_id);

    }
    public void tanimla(){
        userbilgi_username=(EditText)findViewById(R.id.userbilgi_username);
        userbilgi_pass=(EditText)findViewById(R.id.userbilgi_pass);
        userbilgi_button=(Button) findViewById(R.id.userbilgi_button);
        userbilgi_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chande(uye_id,userbilgi_username.getText().toString(),userbilgi_pass.getText().toString());

            }
        });
    }

    public void istekAt(String uye_id){
        Call<UserPojoSinifi> request= ManagerAll.getInstance().getInformation(uye_id);
        request.enqueue(new Callback<UserPojoSinifi>() {
            @Override
            public void onResponse(Call<UserPojoSinifi> call, Response<UserPojoSinifi> response) {
                if (response.isSuccessful()){
                    userbilgi_username.setText(response.body().getKadi());
                    userbilgi_pass.setText(response.body().getSifre());
                }
            }

            @Override
            public void onFailure(Call<UserPojoSinifi> call, Throwable t) {

            }
        });
    }
    public void chande(String uye_id,String user , String pass){
        final Call<GuncellePojoSinifi> request=ManagerAll.getInstance().chandeInformation(uye_id,user,pass);
        request.enqueue(new Callback<GuncellePojoSinifi>() {
            @Override
            public void onResponse(Call<GuncellePojoSinifi> call, Response<GuncellePojoSinifi> response) {
                if (response.body().isTf()){
                    Intent ıntent=new Intent(KisiselBilgiAvtivity.this,KisiselBilgiAvtivity.class);
                    startActivity(ıntent);
                    Toast.makeText(getApplicationContext(),"Kişisel Bilgiler Güncellendi.",Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<GuncellePojoSinifi> call, Throwable t) {

            }
        });
    }
}
