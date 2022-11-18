package com.bignerdranch.android.myrecipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class UneditableRecyclerViewAdapter extends RecyclerView.Adapter<UneditableRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<IngredientModel> ingredientModels;

    public UneditableRecyclerViewAdapter(Context context, ArrayList<IngredientModel> ingredientModels){
        this.context = context;
        this.ingredientModels = ingredientModels;
    }

    @NonNull
    @Override
    public UneditableRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ingredient_row_uneditable, parent, false);

        return new UneditableRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UneditableRecyclerViewAdapter.MyViewHolder holder, int position) {
        IngredientModel model = ingredientModels.get(holder.getAdapterPosition());

        if(model.getInName().trim().length() != 0)
            holder.name.setText(model.getInName());
        holder.quantity.setText(model.getInQuantity());
        if(model.getInUnits().trim().length() != 0)
            holder.unit.setText(model.getInUnits());
    }

    @Override
    public int getItemCount() {
        return ingredientModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, quantity, unit;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.name_ingredient_uneditable);
            quantity = itemView.findViewById(R.id.quantity_ingredient_uneditable);
            unit = itemView.findViewById(R.id.unit_ingredient_uneditable);
        }
    }
}
