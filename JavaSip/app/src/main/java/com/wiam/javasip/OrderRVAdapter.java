package com.wiam.javasip;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;
public class OrderRVAdapter extends RecyclerView.Adapter<OrderRVAdapter.ViewHolder> {
    // variable for our array list and context
    private ArrayList<OrderModal> OrderModalArrayList;
    private Context context;
    // constructor
    public OrderRVAdapter(ArrayList<OrderModal> OrderModalArrayList, Context context) {
        this.OrderModalArrayList = OrderModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
// on below line we are inflating our layout file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_rv_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
// on below line we are setting data to our views of recycler view item.
        OrderModal modal = OrderModalArrayList.get(position);
        holder.coffeeNameTV.setText(modal.getCoffeeName());
        holder.sizeTV.setText(modal.getSize());
        holder.creamTV.setText(modal.getCream());
        holder.priceTV.setText(modal.getPrice());
    }
    @Override
    public int getItemCount() {
// returning the size of our array list
        return OrderModalArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        private TextView coffeeNameTV, sizeTV, creamTV, priceTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
// initializing our text views
            coffeeNameTV = itemView.findViewById(R.id.idTVCoffeeName);
            sizeTV = itemView.findViewById(R.id.idTVsize);
            creamTV = itemView.findViewById(R.id.idTVcream);
            priceTV = itemView.findViewById(R.id.idTVprice);
        }
    }
}

