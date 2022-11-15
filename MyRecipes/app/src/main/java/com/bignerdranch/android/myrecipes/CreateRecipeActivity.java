package com.bignerdranch.android.myrecipes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
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

        Button addIngredientButton = findViewById(R.id.add_ingredient_button);
        addIngredientButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                addIngredient(recyclerView);
            }
        });

        //addIngredient(recyclerView);

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
        inputRecipeName = findViewById(R.id.inputRecipeName);

        IngredientRecyclerViewAdapter adapter = new IngredientRecyclerViewAdapter(this,
                ingredientModels);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void configureBackToMainButton() {
        Button mainMenuButton = findViewById(R.id.back_to_main_create);
        mainMenuButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(ingredientModels.size() > 0 || inputRecipeName.length() > 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(CreateRecipeActivity.this);

                    builder.setCancelable(true);
                    builder.setTitle("Warning!");
                    builder.setMessage("Exit without saving?");

                    builder.setNegativeButton("Don't Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //alertTextView.setVisibility(View.VISIBLE);
                            finish();
                        }
                    });
                    builder.show();
                }

                //if not empty

                    //exit without saving? y/n
                        //if yes
                            //finish();
                        //if no
                            //do nothing
                //if empty
                    else
                        finish();
            }
        });
    }

    private void configureSaveButton(){
        Button createSaveRecipeButton = findViewById(R.id.save_recipe_create);
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