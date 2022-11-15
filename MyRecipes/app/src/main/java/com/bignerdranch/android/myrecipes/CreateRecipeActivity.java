package com.bignerdranch.android.myrecipes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class CreateRecipeActivity extends AppCompatActivity {
    EditText inputRecipeName;
    ArrayList<IngredientModel> ingredientModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        RecyclerView recyclerView = findViewById(R.id.ingredient_list_view);

        configureBackToMainButton();
        configureSaveButton();

        Button addIngredientButton = (Button) findViewById(R.id.add_ingredient_button);
        addIngredientButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                addIngredient(recyclerView);
            }
        });

        addIngredient(recyclerView);

        configureRecycler(recyclerView);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putParcelableArrayList("ingredientModels", ingredientModels);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        ingredientModels = savedInstanceState.getParcelableArrayList("ingredientModels");
        RecyclerView recyclerView = findViewById(R.id.ingredient_list_view);
        configureRecycler(recyclerView);
    }

    private void configureRecycler(RecyclerView recyclerView){
        inputRecipeName = (EditText) findViewById(R.id.inputRecipeName);

        IngredientRecyclerViewAdapter adapter = new IngredientRecyclerViewAdapter(this,
                ingredientModels);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void configureBackToMainButton() {
        Button mainMenuButton = (Button) findViewById(R.id.back_to_main_create);
        mainMenuButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //if not empty
                    //exit without saving? y/n
                        //if yes
                            //finish();
                        //if no
                            //do nothing
                //if empty
                finish();
            }
        });
    }

    private void configureSaveButton(){
        Button createSaveRecipeButton = (Button) findViewById(R.id.save_recipe_create);
        createSaveRecipeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                saveRecipe();
            }
        });
    }

    private void addIngredient(RecyclerView recyclerView){
        ingredientModels.add(new IngredientModel());
        configureRecycler(recyclerView);
    }

    private void saveRecipe(){
        //validate input
            //if valid
                //if exits
                    //prompt override
                        //if yes
                            //save to data
                            //exit
                        //if no
                            //do nothing
                //if does not exist already
                    //save to data
                    //exit
            //else
                //error message
    }
}