package com.bignerdranch.android.myrecipes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IngredientRecyclerViewAdapter extends RecyclerView.Adapter<IngredientRecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<IngredientModel> ingredientModels;


    public IngredientRecyclerViewAdapter(Context context, ArrayList<IngredientModel> ingredientModels){
        this.context = context;
        this.ingredientModels = ingredientModels;
    }

    @NonNull
    @Override
    public IngredientRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflates layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ingredient_row_view, parent, false);

        return new IngredientRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientRecyclerViewAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        //assigns values to each row
        //based on position of the recycler view
        holder.tvName.setText(ingredientModels.get(position).getInName());
        holder.tvQuantity.setText(ingredientModels.get(position).getInQuantity().toString());
        holder.tvUnits.setText(ingredientModels.get(position).getInUnits());
        holder.deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ingredientModels.remove(ingredientModels.indexOf(ingredientModels.get(position)));
            }
        });

    }

    @Override
    public int getItemCount() {
        //shows number of items total
        return ingredientModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        EditText tvName, tvQuantity, tvUnits;
        ImageButton deleteButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.name_ingredient);
            tvQuantity = itemView.findViewById(R.id.quantity_ingredient);
            tvUnits = itemView.findViewById(R.id.unit_ingredient);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }
}
