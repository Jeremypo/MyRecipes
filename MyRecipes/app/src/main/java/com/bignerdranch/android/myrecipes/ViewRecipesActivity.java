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
import java.util.HashMap;

public class ViewRecipesActivity extends AppCompatActivity {
    private HashMap<String, ArrayList<IngredientModel>> saveData = new HashMap<>();
    private ArrayList<RecipeModel> recipeModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_recipes);
        System.out.println("Content Set");
        loadSaveData();

        RecyclerView recyclerView = findViewById(R.id.recipe_list_view);
        setupRecipeModels();

        configureBackToMainButton();
        configureRecycler(recyclerView);
    }

    private void setupRecipeModels(){
        for(String recipeName: saveData.keySet()){
            recipeModels.add(new RecipeModel(recipeName));
        }
    }

    private void loadSaveData() {
        File file = new File(getDir("data", MODE_PRIVATE), "recipes");
        if(!file.exists())
            System.out.println("file DNE");
        else {
            ObjectInputStream inputStream = null;
            try {
                inputStream = new ObjectInputStream(new FileInputStream(file));
                saveData = (HashMap<String, ArrayList<IngredientModel>>)inputStream.readObject();
                System.out.println("size =" + saveData.size());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void configureRecycler(RecyclerView recyclerView){
        RecipeRecyclerViewAdapter adapter = new RecipeRecyclerViewAdapter(this,
                recipeModels);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void configureBackToMainButton() {
        Button mainMenuButton = findViewById(R.id.back_to_main_view);
        mainMenuButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
    }

}
