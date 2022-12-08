package com.bignerdranch.android.myrecipes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GenerateGroceryListActivity extends AppCompatActivity {
    private HashMap<String, ArrayList<IngredientModel>> saveData = new HashMap<>();
    private RecyclerView recyclerView;
    private GroceryRecyclerViewAdapter adapter;
    private ArrayList<String> groceryIngredients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_grocery_list);

        loadSaveData();
        generateList();
        configureBackButton();
        recyclerView = findViewById(R.id.recycler_grocery);
        configureRecycler();

    }

    private void generateList(){
        for(String key: saveData.keySet()){
            for(int i = 0; i < saveData.get(key).size(); i++){
                String ingredient = saveData.get(key).get(i).getInName();
                if(ingredient.equals(""))
                    ingredient = "Nameless Ingredient";
                ingredient = ingredient.substring(0,1).toUpperCase()
                        + ingredient.substring(1).toLowerCase();
                if(!groceryIngredients.contains(ingredient))
                    groceryIngredients.add(ingredient);
            }
        }
        Collections.sort(groceryIngredients);
    }

    private void configureRecycler() {
        adapter = new GroceryRecyclerViewAdapter(this,
                groceryIngredients);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void configureBackButton() {
        Button backButton = findViewById(R.id.back_grocery);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
    }

    private void loadSaveData(){
        File file = new File(getDir("data", MODE_PRIVATE), "recipes");

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
}

