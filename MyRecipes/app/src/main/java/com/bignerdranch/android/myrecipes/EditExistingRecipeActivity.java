package com.bignerdranch.android.myrecipes;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EditExistingRecipeActivity extends AppCompatActivity {
    private String recipeName;
    private ArrayList<IngredientModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);


    }


}