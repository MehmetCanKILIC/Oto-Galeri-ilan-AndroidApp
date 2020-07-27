package com.example.mck.otogalerici;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mck.otogalerici.Models.UserPojoSinifi;
import com.example.mck.otogalerici.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MesajlarAdapter extends BaseAdapter {
    List<String> otherIdList;
    String userId;
    Context context;
    Activity activity;
    SharedPreferences sharedPreferences;
    String ilanId;



    public MesajlarAdapter(List<String> otherIdList, String userId, Context context,Activity activity) {
        this.otherIdList = otherIdList;
        this.userId = userId;
        this.context = context;
        this.activity=activity;
    }
    @Override
    public int getCount() {
        return otherIdList.size();
    }

    @Override
    public Object getItem(int position) {
        return otherIdList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.other,parent,false);
        TextView textView;
        textView=(TextView) convertView.findViewById(R.id.otherText);
        istekAt(otherIdList.get(position).toString(),textView);
        LinearLayout linearLayout=convertView.findViewById(R.id.linearmesaj);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(activity, ChatActivity.class);
                OtherId.setOtherId(otherIdList.get(position).toString());
               activity.startActivity(ıntent);
            }
        });
        return convertView;
    }

    public void istekAt(String uye_id, final TextView textView){
        Call<UserPojoSinifi> request= ManagerAll.getInstance().getInformation(uye_id);
        request.enqueue(new Callback<UserPojoSinifi>() {
            @Override
            public void onResponse(Call<UserPojoSinifi> call, Response<UserPojoSinifi> response) {
                if (response.isSuccessful()){
                    textView.setText(response.body().getKadi().toString());
                }
            }

            @Override
            public void onFailure(Call<UserPojoSinifi> call, Throwable t) {

            }
        });
    }
}
