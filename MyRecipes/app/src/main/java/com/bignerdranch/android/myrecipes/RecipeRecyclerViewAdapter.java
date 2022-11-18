package com.bignerdranch.android.myrecipes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.MyViewHolder>{
    private Context context;
    private ArrayList<RecipeModel> recipeModels;
    private HashMap<String, ArrayList<IngredientModel>> saveData;

    public RecipeRecyclerViewAdapter(Context context, ArrayList<RecipeModel> recipeModels){
        this.context = context;
        this.recipeModels = recipeModels;
        loadSaveData();
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
        RecipeModel model = recipeModels.get(holder.getAdapterPosition());

        holder.recipeNameButton.setText(model.getRecipeName());
        holder.recipeNameButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(context, ViewUneditableActivity.class);
                String recipeName = model.getRecipeName().toString();

                System.out.println("Putting: " + recipeName);
                /*ArrayList<IngredientModel> ingredients = saveData.get(recipeName);
                for(IngredientModel stuff: ingredients){
                    System.out.println("\tWith: " +
                            stuff.getInName() + " " +
                            stuff.getInQuantity() + " " +
                            stuff.getInUnits() + " ");
                }*/

                i.putExtra("recipeName", recipeName);
                //i.putExtra("ingredients", ingredients);

                context.startActivity(i);
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setCancelable(true);
                builder.setTitle("Warning!");
                builder.setMessage("Are you sure you want to delete \""
                        + model.getRecipeName() + "\" ?");

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        makeToast("\"" + model.getRecipeName() + "\" deleted.");
                        recipeModels.remove(model);
                        removeData(model.getRecipeName());
                        notifyItemRemoved(holder.getAdapterPosition());
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }
        });
    }

    private void makeToast(String msg){
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, msg, duration);
        toast.show();
    }

    private void loadSaveData() {
        File file = new File(context.getDir("data", context.MODE_PRIVATE), "recipes");
        if(!file.exists())
            System.out.println("file DNE");
        else {
            ObjectInputStream inputStream = null;
            try {
                inputStream = new ObjectInputStream(new FileInputStream(file));
                saveData = (HashMap<String, ArrayList<IngredientModel>>)inputStream.readObject();
                inputStream.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeData(String name){
        saveData.remove(name);

        File file = new File(context.getDir("data", context.MODE_PRIVATE), "recipes");
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(saveData);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        //shows number of recipes total
        return recipeModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        Button recipeNameButton;
        ImageButton deleteButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            recipeNameButton = itemView.findViewById(R.id.name_recipe);
            deleteButton = itemView.findViewById(R.id.delete_recipe);
        }
    }

}
