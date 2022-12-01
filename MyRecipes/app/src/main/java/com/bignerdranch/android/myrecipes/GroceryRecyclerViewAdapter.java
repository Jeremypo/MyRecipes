package com.bignerdranch.android.myrecipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GroceryRecyclerViewAdapter extends RecyclerView.Adapter<GroceryRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> groceryIngredients;

    public GroceryRecyclerViewAdapter(Context context, ArrayList<String> groceryIngredients){
        this.context = context;
        this.groceryIngredients = groceryIngredients;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            tvName = itemView.findViewById(R.id.name_grocery);
        }
    }

    @NonNull
    @Override
    public GroceryRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.grocery_row_view, parent, false);

        return new GroceryRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryRecyclerViewAdapter.MyViewHolder holder, int position) {
        String model = groceryIngredients.get(holder.getAdapterPosition());
        holder.tvName.setText(model);
    }

    @Override
    public int getItemCount() {
        return groceryIngredients.size();
    }
}
