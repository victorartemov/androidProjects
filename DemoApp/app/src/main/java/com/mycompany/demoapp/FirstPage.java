package com.mycompany.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.Toast;
import android.content.Context;
import android.view.LayoutInflater;
import android.content.Intent;


public class FirstPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        String[] subjects = {"Арифметика"};

        ListAdapter myAdapter = new CustomAdapter(this,subjects);
        ListView firstPageListView = (ListView) findViewById(R.id.firstPageListView);
        firstPageListView.setAdapter(myAdapter);

        firstPageListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(FirstPage.this, GetReady.class);
                        startActivity(i);
                    }
                }
        );
    }


}
