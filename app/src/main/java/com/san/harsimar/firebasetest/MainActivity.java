package com.san.harsimar.firebasetest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.realtime.util.StringListReader;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private Button sendbt;
    private EditText textTosend;
    private Firebase reftoFireBase;
    private EditText mkey;
    private ListView mList;
    private ArrayList<String> users =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        reftoFireBase=new Firebase("https://testingfirebase-ccfd8.firebaseio.com/");
        sendbt=(Button)findViewById(R.id.buttonFire);
        textTosend=(EditText)findViewById(R.id.editText);
        mkey=(EditText)findViewById(R.id.key);
        mList=(ListView)findViewById(R.id.listView);

        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,users);
        mList.setAdapter(adapter);
        sendbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Firebase reftoChildFireBase=reftoFireBase.child("names");
                //reftoChildFireBase.setValue(textTosend.getText().toString());
                reftoChildFireBase.child(mkey.getText().toString()).setValue(textTosend.getText().toString());
            }
        });

        reftoFireBase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String retrievedData=dataSnapshot.getValue().toString();
                
                    Map<String,String> nmap=dataSnapshot.getValue(Map.class);
                   users.add(retrievedData);
                String string=new String();
                Log.d("Simar",retrievedData);
             /*   for(int i=0;i<retrievedData.length();i++){
                    if(retrievedData.charAt(i)=='='){
                    while (retrievedData.charAt(i)!=','){
                        string+=retrievedData.charAt(i);
                    }
                        Log.d("Simar",string);
                        string="";
                    }
                }*/
                adapter.notifyDataSetChanged();
                /*
                Map<String,String> map=dataSnapshot.getValue(Map.class);
                users.add(map.get(mkey.getText()));
                adapter.notifyDataSetChanged();
               */

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
