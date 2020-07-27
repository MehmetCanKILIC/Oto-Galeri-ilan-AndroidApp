package com.example.mck.otogalerici;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MesajAdapter extends RecyclerView.Adapter {
    List<MesajModel> list;
    boolean state = false;
    static final int user=5 , other=8;
    Context context ;
    String userId;

    public MesajAdapter(List<MesajModel> list, Context context,String userId) {
        this.list = list;
        this.context = context;
        this.userId = userId;
    }


    // layout tanımlaması
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        if ( viewType == user)
        {
           view = LayoutInflater.from(context).inflate(R.layout.user,parent,false);
            return  new ViewHolder(view);
        }else
        {

            view = LayoutInflater.from(context).inflate(R.layout.other,parent,false);
            return  new ViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MesajModel m = list.get(position);
        ((ViewHolder)holder).setle(m);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView mesajbody;

        public ViewHolder(View itemView) {
            super(itemView);
            if(state)
            {
                mesajbody = (TextView) itemView.findViewById(R.id.userText);
            }else
            {
                mesajbody = (TextView) itemView.findViewById(R.id.otherText);
            }

        }

        
        void setle(MesajModel msj )
        {

            mesajbody.setText(msj.getMesaj().toString());
        }

    }



    // veri tabanıntaki from a göre layout döndürülmesini sağlayan değişkenler
    @Override
    public int getItemViewType(int position) {
        if(list.get(position).getFrom().equals(userId))
        {
            state = true;
            return  user;
        }
        else
        {
            state  = false;
            return  other;
        }
    }
}
