package com.example.mck.otogalerici.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mck.otogalerici.Models.IlanlarPojoSinifi;
import com.example.mck.otogalerici.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IlanlarAdapter extends BaseAdapter {

    List<IlanlarPojoSinifi> ılanlarPojoSinifis;
    Context context;

    public IlanlarAdapter(List<IlanlarPojoSinifi> ılanlarPojoSinifis, Context context) {
        this.ılanlarPojoSinifis = ılanlarPojoSinifis;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ılanlarPojoSinifis.size();
    }

    @Override
    public Object getItem(int position) {
        return ılanlarPojoSinifis.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.ilanlarlayout, parent, false);
        TextView baslik, fiyat, adres;
        ImageView resim;

        resim = (ImageView) convertView.findViewById(R.id.ilanlarIlanResim);
        baslik = (TextView) convertView.findViewById(R.id.ilanlarIlanBaslik);
        fiyat = (TextView) convertView.findViewById(R.id.ilanlarIlanFiyat);
        adres = (TextView) convertView.findViewById(R.id.ilanlarIlanAdres);

        baslik.setText(ılanlarPojoSinifis.get(position).getBaslik());
        fiyat.setText(ılanlarPojoSinifis.get(position).getFiyat());
        adres.setText(ılanlarPojoSinifis.get(position).getIl() + " / " + ılanlarPojoSinifis.get(position).getIlce() + " / " + ılanlarPojoSinifis.get(position).getMahalle());
        Picasso.get().load("http://bgbworm.com/host/" + ılanlarPojoSinifis.get(position).getResim()).resize(100, 100).into(resim);

        return convertView;
    }
}
