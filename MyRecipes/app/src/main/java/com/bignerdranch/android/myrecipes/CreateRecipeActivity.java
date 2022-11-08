package com.bignerdranch.android.myrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateRecipeActivity extends AppCompatActivity {
    EditText inputRecipeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        configureBackToMainButton();
        configureSaveButton();

        inputRecipeName = (EditText) findViewById(R.id.inputRecipeName);
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