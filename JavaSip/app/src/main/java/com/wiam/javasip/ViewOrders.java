package com.wiam.javasip;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class ViewOrders extends AppCompatActivity {
    // creating variables for our array list, dbhandler, adapter and recycler view.
    private ArrayList<OrderModal> OrderModalArrayList;
    private DBHandler dbHandler;
    private OrderRVAdapter OrderRVAdapter;
    private RecyclerView ordersRV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);
// initializing our all variables.
        OrderModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewOrders.this);
// getting our order array list from db handler class.
        OrderModalArrayList = dbHandler.readOrders();
// on below line passing our array list to our adapter class.
        OrderRVAdapter = new OrderRVAdapter(OrderModalArrayList, ViewOrders.this);
        ordersRV = findViewById(R.id.idRVCourses);
// setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewOrders.this, RecyclerView.VERTICAL, false);
        ordersRV.setLayoutManager(linearLayoutManager);
// setting our adapter to recycler view.
        ordersRV.setAdapter(OrderRVAdapter);


/*move to confirm page order if there are items in the order
 if there aren't any items, prompt user to add items first*/
        Button confirm = (Button) findViewById(R.id.confirmButton);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager =getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OrderModalArrayList.isEmpty()){
                    Toast.makeText(ViewOrders.this, "Please add items..", Toast.LENGTH_SHORT).show();
                } else{
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(ViewOrders.this, "My Notification");
                        builder.setContentTitle("Order Update");
                        builder.setContentText("Your Order is Confirmed Successfully!");
                        builder.setSmallIcon(R.drawable.ic_launcher_background);
                        builder.setAutoCancel(true);

                        NotificationManagerCompat managerCompat=NotificationManagerCompat.from(ViewOrders.this);
                        managerCompat.notify(1,builder.build());
                        Intent i = new Intent(ViewOrders.this, ConfirmOrder.class);
                        startActivity(i);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // show toast here...
                                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(ViewOrders.this, "My Notification");
                                mBuilder.setSmallIcon(R.drawable.ic_launcher_background);
                                mBuilder.setContentTitle("Order Update");
                                mBuilder.setContentText("Your Order is Ready for Pick up!");
                                Notification notifcation = mBuilder.build();

                                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                                nm.notify(2, notifcation);

                            }
                        }, 5000); // 5 seconds
                }

            }
        });

        Button cancel = (Button) findViewById(R.id.cancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OrderModalArrayList.isEmpty()){
                    Toast.makeText(ViewOrders.this, "No order to cancel..", Toast.LENGTH_SHORT).show();
                } else{
                    dbHandler.cancelOrder();
                    OrderModalArrayList.clear();
                    ordersRV.setAdapter(OrderRVAdapter);
                }
            }
        });
    }
}

