package com.wiam.javasip;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//start of drinks menu
        //create list reference
        ListView listview =findViewById(R.id.options_list);
        List<String> list =new ArrayList<>();
        list.add("Americano");
        list.add("Latte");
        list.add("Cappuccino");

        //create adapter
        ArrayAdapter arrayAdapter=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_expandable_list_item_1,list);

        //link adapter to list
        listview.setAdapter(arrayAdapter);

        //listener
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              if (position==0){
                  //clicked on Americano
                  startActivity(new Intent(MainActivity.this,AmericanoActivity.class));

               }else if (position==1) {
                  //clicked on latte
                  startActivity(new Intent(MainActivity.this,LatteActivity.class));

              } else if (position==2){
                  //clicked on cappuccino
                  startActivity(new Intent(MainActivity.this,CapActivity.class));
              }
            }
        });// end of drinks menu

        //move to order page
        Button next = (Button) findViewById(R.id.orderButton);
        //create onClick listener for the button
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                Intent i = new Intent(MainActivity.this, ViewOrders.class);
                startActivity(i);
            }

        });
    }



}
