package com.example.mck.otogalerici;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {
    String userId, otherId, userTable, otherTable, key;
    SharedPreferences sharedPreferences;
    DatabaseReference reference;
    Button sendMessageButton;
    EditText messageEditText;
    List<MesajModel> list;
    MesajAdapter adapter;
    RecyclerView mesajListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        tanimla();
        action();
        load();
    }

    public void tanimla() {

        reference = FirebaseDatabase.getInstance().getReference();
        otherId = OtherId.getOtherId();
        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        userId = sharedPreferences.getString("uye_id", null);
        list = new ArrayList<>();
        adapter = new MesajAdapter(list, getApplicationContext(), userId);
        messageEditText = (EditText) findViewById(R.id.messageEditText);
        sendMessageButton = (Button) findViewById(R.id.sendMessageButton);
        mesajListView = (RecyclerView) findViewById(R.id.mesajListView);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        mesajListView.setLayoutManager(manager);
        mesajListView.setAdapter(adapter);
    }

    public void action() {
        if (messageEditText.getText().toString() != ""){
            sendMessageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendMessage(messageEditText.getText().toString(), userId, otherId);
                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(), "Boş Mesaj Gönderemezsiniz", Toast.LENGTH_LONG).show();
        }

    }

    public void sendMessage(String mesajbody, String UsrId, String othId) {
        userTable = "messages/" + userId + "/" + otherId;
        otherTable = "messages/" + otherId + "/" + userId;
        key = reference.child("messages").child(userTable).child(otherTable).push().getKey();

        Log.i("keyim", key);
        Map map = send(mesajbody, UsrId, othId);
        Map map2 = new HashMap();
        map2.put(userTable + "/" + key, map);
        map2.put(otherTable + "/" + key, map);

        messageEditText.setText("");
        reference.updateChildren(map2, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

            }
        });
    }

    public Map send(String mesajbody, String userId, String otherId) {
        Map msj = new HashMap();
        msj.put("mesaj", mesajbody);
        msj.put("from", userId);
        msj.put("to", otherId);
        return msj;
    }

    public void load() {
        reference.child("messages").child(userId).child(otherId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                MesajModel m = dataSnapshot.getValue(MesajModel.class);
                list.add(m);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                MesajModel m = dataSnapshot.getValue(MesajModel.class);
                list.add(m);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                MesajModel m = dataSnapshot.getValue(MesajModel.class);
                list.add(m);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
