package com.bignerdranch.android.myrecipes;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

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
    public void onBindViewHolder(@NonNull IngredientRecyclerViewAdapter.MyViewHolder holder, int position) {
        IngredientModel model = ingredientModels.get(holder.getAdapterPosition());
        //assigns values to each row
        //based on position of the recycler view
        holder.tvName.setText(model.getInName());
        if(model.getInQuantity().equals(".") || model.getInQuantity().equals(""))
            holder.tvQuantity.setText("1.0");
        else{
            Double num = Double.parseDouble(model.getInQuantity());
            holder.tvQuantity.setText(num.toString());
        }
        holder.tvUnits.setText(model.getInUnits());
        holder.deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ingredientModels.remove(model);
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });


        holder.tvName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                ingredientModels.get(holder.getAdapterPosition()).setInName(s.toString());
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        holder.tvQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(s.length() == 1 && s.charAt(0) == ('.'))
                    ingredientModels.get(holder.getAdapterPosition()).setInQuantity(s.toString());
                else if(s.length() > 0)
                    ingredientModels.get(holder.getAdapterPosition()).setInQuantity(s.toString());
                else
                    ingredientModels.get(holder.getAdapterPosition()).setInQuantity("1.0");
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        holder.tvUnits.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                ingredientModels.get(holder.getAdapterPosition()).setInUnits(s.toString());
            }
            @Override
            public void afterTextChanged(Editable editable) {}
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

            //deleteButton.setOnClickListener();
        }
    }
}
