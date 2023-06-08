package com.wiam.javasip;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class ConfirmOrder extends AppCompatActivity {
DBHandler dbHandler;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_confirmed_activity);

        dbHandler=new DBHandler(ConfirmOrder.this);
        TextView orderTotal=(TextView) findViewById(R.id.totalText);
        orderTotal.setText("Order Total: "+Integer.toString(dbHandler.sumPriceCartItems())+" SAR");

        Button next = (Button) findViewById(R.id.homeButton);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                Intent i = new Intent(ConfirmOrder.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
    }





