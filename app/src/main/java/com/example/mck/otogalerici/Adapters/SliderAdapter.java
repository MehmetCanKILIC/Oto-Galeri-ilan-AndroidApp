package com.example.mck.otogalerici.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.mck.otogalerici.Models.SliderPojoSinifi;
import com.example.mck.otogalerici.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    List<SliderPojoSinifi> list;
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(List<SliderPojoSinifi> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (RelativeLayout) object);
    }

    //web servisten resimleri çekme ve return etme işlemi
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.sliderlayout, container, false);
        ImageView ımageView = (ImageView) view.findViewById(R.id.sliderImageView);
        Picasso.get().load("http://bgbworm.com/host/" + list.get(position).getResim()).resize(1050, 600).into(ımageView);
        container.addView(view);
        return view;
    }

    //slider içinde dolaşırken uygulamayı durdurma işleminin çözümü için kullanılan fonksiyon
    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }
}
