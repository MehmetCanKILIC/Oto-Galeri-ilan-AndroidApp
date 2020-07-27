package com.example.mck.otogalerici;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MesajlarActivity extends AppCompatActivity{

    String userId;
    SharedPreferences sharedPreferences;
    DatabaseReference reference;
    List<String> otherIdList;
    MesajlarAdapter mesajlarAdapter;
    ListView mesajlarListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesajlar);
        otherIdList = new ArrayList<>();
        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        userId = sharedPreferences.getString("uye_id", null);
        reference = FirebaseDatabase.getInstance().getReference();
        mesajlarAdapter = new MesajlarAdapter(otherIdList, userId, getApplicationContext(),MesajlarActivity.this);
        mesajlarListView = (ListView) findViewById(R.id.mesajlarListView);
        mesajlarListView.setAdapter(mesajlarAdapter);
        listele();
    }

    public void listele() {
        reference.child("messages").child(userId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                otherIdList.add(dataSnapshot.getKey());
                mesajlarAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
