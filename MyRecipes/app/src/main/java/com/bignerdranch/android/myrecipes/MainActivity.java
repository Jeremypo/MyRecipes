package com.bignerdranch.android.myrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private HashMap<String, ArrayList<IngredientModel>> saveData = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureCreateRecipeButton();
        configureViewRecipeButton();
        configureGroceryListButton();
    }

    @Override
    protected void onResume(){
        configureViewRecipeButton();
        configureGroceryListButton();
        super.onResume();
    }

    private void configureCreateRecipeButton() {
        Button createRecipeButton = (Button) findViewById(R.id.create_main);
        createRecipeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, CreateRecipeActivity.class));
            }
        });
    }

    private void configureViewRecipeButton() {
        Button viewRecipeButton = (Button) findViewById(R.id.view_main);
        viewRecipeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                System.out.println("Starting");
                startActivity(new Intent(MainActivity.this, ViewRecipesActivity.class));
                System.out.println("Started");
            }
        });
        if(noRecipes()){
            viewRecipeButton.setAlpha((float)0.25);
            viewRecipeButton.setClickable(false);
        }
        else{
            viewRecipeButton.setAlpha(1);
            viewRecipeButton.setClickable(true);
        }
    }

    private void configureGroceryListButton(){
        Button viewRecipeButton = (Button) findViewById(R.id.grocery_main);
        viewRecipeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //startActivity(new Intent(MainActivity.this, CreateRecipeActivity.class));
                System.out.println(saveData.size());
            }
        });
        if(noRecipes()){
            viewRecipeButton.setAlpha((float)0.25);
            viewRecipeButton.setClickable(false);
        }
        else{
            viewRecipeButton.setAlpha(1);
            viewRecipeButton.setClickable(true);
        }
    }

    private boolean noRecipes(){
        boolean empty = true;

        File file = new File(getDir("data", MODE_PRIVATE), "recipes");
        if(!file.exists())
            System.out.println("file DNE");
        else {
            ObjectInputStream inputStream = null;
            try {
                inputStream = new ObjectInputStream(new FileInputStream(file));
                saveData = (HashMap<String, ArrayList<IngredientModel>>)inputStream.readObject();
                if(saveData.size() > 2)
                    empty = false;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return empty;
    }

}