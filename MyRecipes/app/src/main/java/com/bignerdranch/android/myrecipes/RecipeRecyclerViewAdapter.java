package com.bignerdranch.android.myrecipes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.MyViewHolder>{
    Context context;
    ArrayList<RecipeModel> recipeModels;

    public RecipeRecyclerViewAdapter(Context context, ArrayList<RecipeModel> recipeModels){
        this.context = context;
        this.recipeModels = recipeModels;
    }

    @NonNull
    @Override
    public RecipeRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflates layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recipe_row_view, parent, false);

        return new RecipeRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.recipeNameButton.setText(recipeModels.get(holder.getAdapterPosition()).getRecipeName());
        holder.recipeNameButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                context.startActivity(new Intent(context, EditExistingRecipeActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        //shows number of recipes total
        return recipeModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        Button recipeNameButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            recipeNameButton = itemView.findViewById(R.id.name_recipe);
        }
    }
}
